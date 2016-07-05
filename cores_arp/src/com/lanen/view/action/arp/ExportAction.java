package com.lanen.view.action.arp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Bacteria;
import com.lanen.model.Employee;
import com.lanen.model.Normal;
import com.lanen.model.Parasite;
import com.lanen.model.Qc;
import com.lanen.model.Surface;
import com.lanen.model.Tb;
import com.lanen.model.Vaccine;
import com.lanen.model.Virus;
import com.lanen.model.Xcg;
import com.lanen.model.Xysh;
import com.lanen.service.arp.BacteriaService;
import com.lanen.service.arp.ExportService;
import com.lanen.service.arp.NormalService;
import com.lanen.service.arp.ParasiteService;
import com.lanen.service.arp.QcService;
import com.lanen.service.arp.SurfaceService;
import com.lanen.service.arp.TbService;
import com.lanen.service.arp.VaccineService;
import com.lanen.service.arp.VirusService;
import com.lanen.service.arp.XcgService;
import com.lanen.service.arp.XyshService;
import com.lanen.util.Constant;
import com.lanen.util.DateUtil;
import com.lanen.util.ExcelFileGenerator1;
import com.lanen.util.ExcelFileGenerator2;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class ExportAction extends BaseAction<Normal> {

	/**
	 * 出口检疫
	 */
	private static final long serialVersionUID = 5490271774256749L;

	@Resource
	private ExportService exportService;
	@Resource
	private NormalService normalService;
	private String page;
	private String rows;
	private String startdate;
	private String enddate;

	private String orderid;// 订单编号

	private File uploadFile;
	private String attachmentNames;
	@Resource
	private SurfaceService surfaceService;
	private InputStream fileInput;
	private String fileName;
	@Resource
	private TbService tbService;
	@Resource
	private VaccineService vaccineService;
	@Resource
	private VirusService virusService;
	@Resource
	private BacteriaService bacteriaService;
	@Resource
	private ParasiteService parasiteService;
	@Resource
	private QcService qcService;
	@Resource
	private XcgService xcgService;
	@Resource
	private XyshService xyshService;
	private String normalid;
	private String monkeylist;
	private String title;
	private String checkdate;
	private String boss;
	private String veterinarian;
	private String protector;
	private String addOrEdit;
	private String kind;
	
	public String list() {
		if (!"".equals(request.getParameter("editOrAddId"))&&request.getParameter("editOrAddId")!=null) {
			ActionContext.getContext().put("exid",request.getParameter("editOrAddId"));
		}
	
		return "exportList";
	}

	public void loadList() {
		Map<String, Object> map = exportService.getExportQuarantine(page, rows,
				orderid, startdate, enddate);
		String jsonStr = JsonPluginsUtil.beanToJson(map, "yyyy-MM-dd");
		writeJson(jsonStr);
	}

	public void loadMonkeyList() {
		ActionContext.getContext().put("kind", kind);//检疫类别
		Normal normal=normalService.getById(Long.valueOf(orderid));
		String title=normal.getTitle();
		ActionContext.getContext().put("title", title);//检疫编号
		Map<String, Object> map = exportService.getMonkeyList(page, rows,orderid);
		String jsonStr = JsonPluginsUtil.beanToJson(map, "yyyy-MM-dd");
		writeJson(jsonStr);
	}

	/**
	 * 出口检疫
	 * @return
	 */
	public void addExport(){
		Map map=new HashMap<String,Object>();
		
		String exportid=request.getParameter("title");
		if(addOrEdit.equals("add")){
			Normal normal=new Normal();
			//normal.setId(Long.parseLong(exportid));
			normal.setTitle(exportid);
			if (!"".equals(request.getParameter("boss"))&&request.getParameter("boss")!=null) {
				normal.setBoss(Integer.parseInt(request.getParameter("boss")));
			}
			String checkdate=request.getParameter("checkdate");
			if (!"".equals(checkdate)&&checkdate!=null) {
				normal.setCheckdate(DateUtil.stringToDate(checkdate, "yyyy-MM-dd"));
			}
			normal.setMonkeylist(request.getParameter("monkeylist"));
			Employee user=(Employee)ActionContext.getContext().getSession().get("user");
			normal.setCreated_by(Integer.parseInt(user.getId()+""));
			normal.setCreatetime(DateUtil.stringToDate(DateUtil.getNow(""), "yyyy-MM-dd hh:mm:ss"));
			normal.setQuarantinetype(Constant.export);
			normal.setDeleted(Byte.parseByte(Constant.deleted_0+""));
			if (request.getParameter("veterinarian")!=null&&!"".equals(request.getParameter("veterinarian"))) {
				normal.setModified_by(Integer.valueOf(request.getParameter("veterinarian")));//检疫兽医
			}
			normalService.save(normal);
			map.put("success", true);
			map.put("msg", "检疫基础信息添加成功");
			map.put("normalId", normal.getId());
		}else if(addOrEdit.equals("edit")){
			String normalid=request.getParameter("normalid");
			if (!"".equals(normalid)&&normalid!=null) {
				Normal normal = normalService.getById(Long.valueOf(normalid));
				normal.setTitle(exportid);
				if (!"".equals(request.getParameter("boss"))
						&& request.getParameter("boss") != null) {
					normal.setBoss(Integer.parseInt(request
							.getParameter("boss")));
				}
				String checkdate = request.getParameter("checkdate");
				if (!"".equals(checkdate) && checkdate != null) {
					normal.setCheckdate(DateUtil.stringToDate(checkdate,
							"yyyy-MM-dd"));
				}
				normal.setMonkeylist(request.getParameter("monkeylist"));
				normalService.update(normal);
				map.put("success", true);
				map.put("msg", "检疫编辑成功");
				map.put("normalId", normal.getId());
			}
		}
		String json=JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	/**
	 * 导出
	 * 
	 * @throws Exception
	 */
	public String export() throws Exception {
		// 获取导出的表头和数据
		// 获取表头,存放到ArrayList filedName对象中(登录名 用户姓名 性别 联系电话 是否在职)
		ArrayList<Object> filedName = exportService.getExcelFiledNameList();
		// /**获取数据, 是值存放到ArrayList dataList集合中
		// *再实例化一个ArrayList filedData集合 filedData.add(dataList);
		ArrayList<Object> filedData = exportService.getExcelFiledDataList(model.getId());
		ExcelFileGenerator1 generator = new ExcelFileGenerator1(filedName,filedData);
		fileInput = generator.getInputStream();
		fileName = new String(("动物检疫" + "-"+ DateUtil.dateToString(new Date(), "yyyy-MM-dd")).getBytes(),"ISO8859-1");
		return "export";
	}
	/**
	 * 返回类型为"中文名字-20150912231234.xls"
	 * 
	 * @return
	 */
	public String getFileName() throws Exception {
		//String tempName = "采样表" + "-"+ DateUtil.dateToString(new Date(), "yyyy-MM-dd") + ".xls";
		//String tempName = "采样表" + "-"+ DateUtil.dateToString(new Date(), "yyyy-MM-dd");
		//fileName = new String(tempName.getBytes(), "ISO8859-1");
		return fileName;
	}
	/**
	 * 上传，导入.
	 */
	public void uploadFile(){
		String success =  "";
	    	String directory = "/upload";    
	        String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory);    
	        //生成上传的文件对象    
	        File target = new File(targetDirectory,attachmentNames);    
	        //如果文件已经存在，则删除原有文件    
	        if(target.exists()){    
	            target.delete();    
	        }    
	        //复制file对象，实现上传    
	        try {    
	            FileUtils.copyFile(uploadFile, target);    
	        } catch (IOException e) {    
	            e.printStackTrace();    
	        }  
	        //从导入的文件中读取数据  
	        loadCheckItemsInfo(attachmentNames);
	        success="true";
	        writeJson(success);
	}  
	private void loadCheckItemsInfo(String uploadFileName) {
		//获取该检疫的基础信息,.
		Normal normal=normalService.getById(model.getId());
		
	        //读取刚才上传文件，确保路径相同  
	        String directory = "/upload";  
	        String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory);  
	        File target = new File(targetDirectory,uploadFileName);  
	        // HSSFWorkbook只能用来读取2003前（含）的版本， .xls  读取Excel2007时发生如下异常：  
	                 //org.apache.poi.poifs.filesystem.OfficeXmlFileException: The supplied data appears to be in the Office 2007+ XML.   
	                      //You are calling the part of POI that deals with OLE2 Office Documents.   
	                      //You need to call a different part of POI to process this data (eg XSSF instead of HSSF)  
	        //XSSFWorkbook 只能读取2007版本 .xlsx  读取Excel2003以前（包括2003）的版本时却发生了如下新异常：  
	                 //org.apache.poi.openxml4j.exceptions.InvalidOperationException: Can't open the specified file: '*.xls'  
	          
	       //XSSF和HSSF虽然在不同的包里，但却引用了同一接口Workbook，可以用下面判断  
	       // Workbook wb = null;  
	        try{  
	            FileInputStream fi = new FileInputStream(target);
	            Workbook wb = null;
				
					wb = WorkbookFactory.create(fi);//可以读取xls格式或xlsx格式。
				
				/*if (uploadFileName.toLowerCase().endsWith("xls")) { 
	                wb = (Workbook) new HSSFWorkbook(fi);  
	            }else if(uploadFileName.toLowerCase().endsWith("xlsx")) {   
	                wb =new XSSFWorkbook(fi);    
	            }*/   
	  
	            //Sheet sheet = wb.getSheetAt(0);
				/*Sheet sheet = wb.getSheet("体表检疫");
	            int rowNum = sheet.getLastRowNum()+1;    
	            List<Surface> surfaceList= new ArrayList<Surface>();  
	            //i 从1开始表示第一行为标题 不包含在数据中  
	            for(int i=1;i<rowNum;i++){  
	                Surface surface= new Surface();  
	                Row row = sheet.getRow(i);    
	                int cellNum = row.getLastCellNum();    
	                for(int j=0;j<cellNum;j++){    
	                    Cell cell = row.getCell((short) j);    
	                    String cellValue = null;    
	                    switch(cell.getCellType()){ //判断excel单元格内容的格式，并对其进行转换，以便插入数据库    
	                        case 0 : cellValue = String.valueOf((int)cell.getNumericCellValue()); break;    
	                        case 1 : cellValue = cell.getStringCellValue(); break;    
	                        case 2 : cellValue = String.valueOf(cell.getDateCellValue()); break;    
	                        case 3 : cellValue = ""; break;    
	                        case 4 : cellValue = String.valueOf(cell.getBooleanCellValue()); break;    
	                        case 5 : cellValue = String.valueOf(cell.getErrorCellValue()); break;    
	                    }    
	                        
	                    switch(j){//通过列数来判断对应插如的字段    
	                        //数据中不应该保护ID这样的主键记录                        
	                        //case 0 : user.setId(Integer.valueOf(cellValue));break;    
	                        case 0 : surface.setMonkeyid(cellValue);break;    
	                        case 1 : surface.setRemark(cellValue);break;    
	                        //case 2 : surface.setFirstName(cellValue);break;  
	                        //case 3 : surface.setLastName(cellValue);break;  
	                        //case 4 : surface.setAddress(cellValue);break;  
	                    }    
	                } 
	                surface.setNormal_id(Integer.valueOf(model.getId()+""));
	                surfaceList.add(surface);
	                
	                normal.setSurface("√");
	            }   
	            surfaceIntoDB(surfaceList);*/
	            
	            //Tb  
	            Sheet sheet1 = wb.getSheet("TB检疫");   
	            int rowNum1 = sheet1.getLastRowNum()+1;    
	            List<Tb> TbList= new ArrayList<Tb>();  
	            //i 从1开始表示第一行为标题 不包含在数据中  
	            for(int i=1;i<rowNum1;i++){  
	                Tb tb= new Tb();  
	                Row row = sheet1.getRow(i);    
	                int cellNum = row.getLastCellNum();    
	                for(int j=1;j<cellNum;j++){    
	                    Cell cell = row.getCell((short) j);    
	                    String cellValue = null;    
	                    switch(cell.getCellType()){ //判断excel单元格内容的格式，并对其进行转换，以便插入数据库
	                        case 0 : cellValue = String.valueOf((int)cell.getNumericCellValue()); break;    
	                        case 1 : cellValue = cell.getStringCellValue(); break;    
	                        case 2 : cellValue = String.valueOf(cell.getDateCellValue()); break;    
	                        case 3 : cellValue = ""; break;    
	                        case 4 : cellValue = String.valueOf(cell.getBooleanCellValue()); break;    
	                        case 5 : cellValue = String.valueOf(cell.getErrorCellValue()); break;    
	                    }    
	                        
	                    switch(j){//通过列数来判断对应插如的字段    
	                        //数据中不应该保护ID这样的主键记录        
	                        //case 0 : user.setId(Integer.valueOf(cellValue));break;    
	                        case 3 : tb.setMonkeyid(cellValue);break;    
	                        case 10 : tb.setRemark(cellValue);break;    
	                        case 7 : tb.setTb24(cellValue);break;  
	                        case 8 : tb.setTb48(cellValue);break;  
	                        case 9 : tb.setTb72(cellValue);break;  
	                    }    
	                } 
	                tb.setNormal_id(Integer.valueOf(model.getId()+""));
	                tb.setCdate(normal.getCheckdate());
	                //tb.setVeterinarian(normal.get)
	                TbList.add(tb);
	                normal.setTb("√");
	            }   
	            tbIntoDB(TbList);
	            
	          //疫苗.  
	            Sheet sheet2 = wb.getSheet("疫苗检疫");   
	            int rowNum2 = sheet2.getLastRowNum()+1;    
	            List<Vaccine> vaccineList= new ArrayList<Vaccine>();  
	            //i 从1开始表示第一行为标题 不包含在数据中  
	            for(int i=1;i<rowNum2;i++){  
	            	Vaccine vaccine= new Vaccine();  
	                Row row = sheet2.getRow(i);    
	                int cellNum = row.getLastCellNum();    
	                for(int j=0;j<cellNum;j++){    
	                    Cell cell = row.getCell((short) j);    
	                    String cellValue = null;    
	                    switch(cell.getCellType()){ //判断excel单元格内容的格式，并对其进行转换，以便插入数据库    
	                        case 0 : cellValue = String.valueOf((int)cell.getNumericCellValue()); break;    
	                        case 1 : cellValue = cell.getStringCellValue(); break;    
	                        case 2 : cellValue = String.valueOf(cell.getDateCellValue()); break;    
	                        case 3 : cellValue = ""; break;    
	                        case 4 : cellValue = String.valueOf(cell.getBooleanCellValue()); break;    
	                        case 5 : cellValue = String.valueOf(cell.getErrorCellValue()); break;    
	                    }    
	                        
	                    switch(j){//通过列数来判断对应插如的字段    
	                        //数据中不应该保护ID这样的主键记录                        
	                        //case 0 : user.setId(Integer.valueOf(cellValue));break;    
	                        case 3 : vaccine.setMonkeyid(cellValue);break;
	                        case 6 : if(("麻疹").equals(cellValue)){
	                        			vaccine.setQ_id("");vaccine.setYpin(cellValue);
	                        		}else if(("甲肝").equals(cellValue)){
	                        			vaccine.setQ_id("");vaccine.setYpin(cellValue);
	                        		} else{
	                        			vaccine.setQ_id("");vaccine.setYpin(cellValue);
	                        		}break;
	                        case 8 : vaccine.setRemark(cellValue);break;    
	                        //case 6 : if(cellValue!=null&&!"".equals(cellValue)){vaccine.setQ_id("");vaccine.setYpin(cellValue);}break;//麻疹  
	                        //case 7 : if(cellValue!=null&&!"".equals(cellValue)){vaccine.setQ_id("");vaccine.setYpin(cellValue);}break;//甲肝
	                        //case 8 : if(cellValue!=null&&!"".equals(cellValue)){vaccine.setQ_id("");vaccine.setYpin(cellValue);}break;//乙肝.
	                    }    
	                } 
	                vaccine.setNormal_id(Integer.valueOf(model.getId()+""));
	                vaccine.setCdate(normal.getCheckdate());
	                vaccineList.add(vaccine);
	                
	                normal.setVaccine("√");
	            }   
	            vaccineIntoDB(vaccineList);
	            
	          //病毒.  
	            Sheet sheet3 = wb.getSheet("病毒检疫");   
	            int rowNum3 = sheet3.getLastRowNum()+1;    
	            List<Virus> virusList= new ArrayList<Virus>();  
	            //i 从1开始表示第一行为标题 不包含在数据中  
	            for(int i=1;i<rowNum3;i++){  
	            	Virus virus= new Virus();  
	                Row row = sheet3.getRow(i);    
	                int cellNum = row.getLastCellNum();    
	                for(int j=0;j<cellNum;j++){    
	                    Cell cell = row.getCell((short) j);    
	                    String cellValue = null;    
	                    switch(cell.getCellType()){ //判断excel单元格内容的格式，并对其进行转换，以便插入数据库    
	                        case 0 : cellValue = String.valueOf((int)cell.getNumericCellValue()); break;    
	                        case 1 : cellValue = cell.getStringCellValue(); break;    
	                        case 2 : cellValue = String.valueOf(cell.getDateCellValue()); break;    
	                        case 3 : cellValue = ""; break;    
	                        case 4 : cellValue = String.valueOf(cell.getBooleanCellValue()); break;    
	                        case 5 : cellValue = String.valueOf(cell.getErrorCellValue()); break;    
	                    }    
	                        
	                    switch(j){//通过列数来判断对应插如的字段    
	                        //数据中不应该保护ID这样的主键记录                        
	                        //case 0 : user.setId(Integer.valueOf(cellValue));break;    
	                        case 3 : virus.setMonkeyid(cellValue);break;    
	                        case 6 : virus.setXueq(cellValue);break;  
	                        case 8 : virus.setBv(cellValue);break;  
	                        case 9 : virus.setStlv(cellValue);break;
	                        case 10: virus.setSrv(cellValue);break;
	                        case 11:virus.setSiv(cellValue);break;
	                        case 12:virus.setFilo(cellValue);break;
	                        case 14:virus.setRemark(cellValue);break;
	                    }    
	                } 
	                virus.setNormal_id(Integer.valueOf(model.getId()+""));
	                virus.setCdate(normal.getCheckdate());
	                virusList.add(virus); 
	                normal.setVirus("√");
	            }   
	            virusIntoDB(virusList);
	            
	          //细菌.  
	            Sheet sheet4 = wb.getSheet("细菌检疫");   
	            int rowNum4 = sheet4.getLastRowNum()+1;    
	            List<Bacteria> bacteriaList= new ArrayList<Bacteria>();  
	            //i 从1开始表示第一行为标题 不包含在数据中  
	            for(int i=1;i<rowNum4;i++){  
	            	Bacteria bacteria= new Bacteria();  
	                Row row = sheet4.getRow(i);    
	                int cellNum = row.getLastCellNum();    
	                for(int j=0;j<cellNum;j++){    
	                    Cell cell = row.getCell((short) j);    
	                    String cellValue = null;    
	                    switch(cell.getCellType()){ //判断excel单元格内容的格式，并对其进行转换，以便插入数据库    
	                        case 0 : cellValue = String.valueOf((int)cell.getNumericCellValue()); break;    
	                        case 1 : cellValue = cell.getStringCellValue(); break;    
	                        case 2 : cellValue = String.valueOf(cell.getDateCellValue()); break;    
	                        case 3 : cellValue = ""; break;    
	                        case 4 : cellValue = String.valueOf(cell.getBooleanCellValue()); break;    
	                        case 5 : cellValue = String.valueOf(cell.getErrorCellValue()); break;    
	                    }    
	                        
	                    switch(j){//通过列数来判断对应插如的字段    
	                        //数据中不应该保护ID这样的主键记录                        
	                        //case 0 : user.setId(Integer.valueOf(cellValue));break;    
	                        case 3 : bacteria.setMonkeyid(cellValue);break;    
	                        case 11 : bacteria.setRemark(cellValue);break;    
	                        case 6 : bacteria.setYpid(cellValue);break;  
	                        case 8 : bacteria.setSalm(cellValue);break;  
	                        case 9 : bacteria.setShig(cellValue);break;
	                        case 10:bacteria.setYers(cellValue);break;
	                    }    
	                } 
	                bacteria.setNormal_id(Integer.valueOf(model.getId()+""));
	                bacteria.setCdate(normal.getCheckdate());
	                bacteriaList.add(bacteria);
	                
	                normal.setBacteria("√");
	            }   
	            bacteriaIntoDB(bacteriaList);
	            
	          //体内寄生虫.  
	            Sheet sheet5 = wb.getSheet("体内寄生虫检疫");   
	            int rowNum5 = sheet5.getLastRowNum()+1;    
	            List<Parasite> parasiteList= new ArrayList<Parasite>();  
	            //i 从1开始表示第一行为标题 不包含在数据中  
	            for(int i=1;i<rowNum5;i++){  
	            	Parasite parasite= new Parasite();  
	                Row row = sheet5.getRow(i);    
	                int cellNum = row.getLastCellNum();    
	                for(int j=0;j<cellNum;j++){    
	                    Cell cell = row.getCell((short) j);    
	                    String cellValue = null;    
	                    switch(cell.getCellType()){ //判断excel单元格内容的格式，并对其进行转换，以便插入数据库    
	                        case 0 : cellValue = String.valueOf((int)cell.getNumericCellValue()); break;    
	                        case 1 : cellValue = cell.getStringCellValue(); break;    
	                        case 2 : cellValue = String.valueOf(cell.getDateCellValue()); break;    
	                        case 3 : cellValue = ""; break;    
	                        case 4 : cellValue = String.valueOf(cell.getBooleanCellValue()); break;    
	                        case 5 : cellValue = String.valueOf(cell.getErrorCellValue()); break;    
	                    }    
	                        
	                    switch(j){//通过列数来判断对应插如的字段    
	                        //数据中不应该保护ID这样的主键记录                        
	                        //case 0 : user.setId(Integer.valueOf(cellValue));break;    
	                        case 3 : parasite.setMonkeyid(cellValue);break;    
	                        case 12 : parasite.setRemark(cellValue);break;
	                        case 6:parasite.setBhao(cellValue);break;
	                        //case 6 : parasite.setYb_id(Integer.valueOf(cellValue));break;  
	                        //case 8 : parasite.setLastName(cellValue);break;//溶组织内阿米  
	                        //case 9 : parasite.set(cellValue);break;//蠕虫
	                        case 10:parasite.setBmc(cellValue);break;
	                    }    
	                } 
	                parasite.setNormal_id(Integer.valueOf(model.getId()+""));
	                parasite.setCdate(normal.getCheckdate());
	                parasiteList.add(parasite);
	                
	                normal.setParasite("√");
	            }   
	            parasiteIntoDB(parasiteList);
	            
	            
	          //体外寄生虫.  
	            Sheet sheet9 = wb.getSheet("体外寄生虫检疫");   
	            int rowNum9 = sheet9.getLastRowNum()+1;    
	            List<Parasite> outParasiteList= new ArrayList<Parasite>();  
	            //i 从1开始表示第一行为标题 不包含在数据中  
	            for(int i=1;i<rowNum9;i++){  
	            	Parasite parasite= new Parasite();  
	                Row row = sheet9.getRow(i);    
	                int cellNum = row.getLastCellNum();    
	                for(int j=0;j<cellNum;j++){    
	                    Cell cell = row.getCell((short) j);    
	                    String cellValue = null;    
	                    switch(cell.getCellType()){ //判断excel单元格内容的格式，并对其进行转换，以便插入数据库    
	                        case 0 : cellValue = String.valueOf((int)cell.getNumericCellValue()); break;    
	                        case 1 : cellValue = cell.getStringCellValue(); break;    
	                        case 2 : cellValue = String.valueOf(cell.getDateCellValue()); break;    
	                        case 3 : cellValue = ""; break;    
	                        case 4 : cellValue = String.valueOf(cell.getBooleanCellValue()); break;    
	                        case 5 : cellValue = String.valueOf(cell.getErrorCellValue()); break;    
	                    }    
	                        
	                    switch(j){//通过列数来判断对应插如的字段    
	                        //数据中不应该保护ID这样的主键记录                        
	                        //case 0 : user.setId(Integer.valueOf(cellValue));break;    
	                        case 3 : parasite.setMonkeyid(cellValue);break;    
	                        //case 6 : parasite.setYb_id(Integer.valueOf(cellValue));break;
	                        case 6 : parasite.setBhao(cellValue);break;
	                        case 8 : parasite.setTwjsc(cellValue);break;
	                        case 9 : parasite.setRemark(cellValue);break;
	                    }    
	                } 
	                parasite.setNormal_id(Integer.valueOf(model.getId()+""));
	                parasite.setCdate(normal.getCheckdate());
	                outParasiteList.add(parasite);
	                
	                normal.setParasite("√");
	            }   
	            parasiteIntoDB(outParasiteList);
	            
	          //驱虫.  
	            Sheet sheet6 = wb.getSheet("驱虫检疫");   
	            int rowNum6 = sheet6.getLastRowNum()+1;    
	            List<Qc> qcList= new ArrayList<Qc>();  
	            //i 从1开始表示第一行为标题 不包含在数据中  
	            for(int i=1;i<rowNum6;i++){  
	            	Qc qc= new Qc();  
	                Row row = sheet6.getRow(i);    
	                int cellNum = row.getLastCellNum();    
	                for(int j=0;j<cellNum;j++){    
	                    Cell cell = row.getCell((short) j);    
	                    String cellValue = null;    
	                    switch(cell.getCellType()){ //判断excel单元格内容的格式，并对其进行转换，以便插入数据库    
	                        case 0 : cellValue = String.valueOf((int)cell.getNumericCellValue()); break;    
	                        case 1 : cellValue = cell.getStringCellValue(); break;    
	                        case 2 : cellValue = String.valueOf(cell.getDateCellValue()); break;    
	                        case 3 : cellValue = ""; break;    
	                        case 4 : cellValue = String.valueOf(cell.getBooleanCellValue()); break;    
	                        case 5 : cellValue = String.valueOf(cell.getErrorCellValue()); break;    
	                    }    
	                        
	                    switch(j){//通过列数来判断对应插如的字段    
	                        //数据中不应该保护ID这样的主键记录                        
	                        //case 0 : user.setId(Integer.valueOf(cellValue));break;    
	                        case 3 : qc.setMonkeyid(cellValue);break;    
	                        //case 1 : qc.setRemark(cellValue);break;    
	                        case 6 : qc.setQcyp(cellValue);break;  
	                        case 7 : qc.setQcrq(cellValue);break;  
	                        case 8 : qc.setQcyl(cellValue);break;  
	                    }    
	                } 
	                qc.setNormal_id(Integer.valueOf(model.getId()+""));
	                qc.setCdate(normal.getCheckdate());
	                qcList.add(qc);
	                normal.setQc("√");
	            }   
	            qcIntoDB(qcList);
 
	          //血常规.  
	            Sheet sheet7 = wb.getSheet("血常规检疫");   
	            int rowNum7 = sheet6.getLastRowNum()+1;    
	            List<Xcg> xcgList= new ArrayList<Xcg>();  
	            //i 从1开始表示第一行为标题 不包含在数据中  
	            for(int i=1;i<rowNum7;i++){  
	            	Xcg xcg= new Xcg();  
	                Row row = sheet7.getRow(i);    
	                int cellNum = row.getLastCellNum();    
	                for(int j=0;j<cellNum;j++){    
	                    Cell cell = row.getCell((short) j);    
	                    String cellValue = null;    
	                    switch(cell.getCellType()){ //判断excel单元格内容的格式，并对其进行转换，以便插入数据库    
	                        case 0 : cellValue = String.valueOf((int)cell.getNumericCellValue()); break;    
	                        case 1 : cellValue = cell.getStringCellValue(); break;    
	                        case 2 : cellValue = String.valueOf(cell.getDateCellValue()); break;    
	                        case 3 : cellValue = ""; break;    
	                        case 4 : cellValue = String.valueOf(cell.getBooleanCellValue()); break;    
	                        case 5 : cellValue = String.valueOf(cell.getErrorCellValue()); break;    
	                    }    
	                        
	                    switch(j){//通过列数来判断对应插如的字段    
	                        //数据中不应该保护ID这样的主键记录                        
	                        //case 0 : user.setId(Integer.valueOf(cellValue));break;    
	                        case 3 : xcg.setMonkeyid(cellValue);break;    
	                        //case 1 : xcg.setRemark(cellValue);break;    
	                        case 6 : xcg.setBhao(cellValue);break;  
	                        //case 3 : surface.setLastName(cellValue);break;  
	                        //case 4 : surface.setAddress(cellValue);break;  
	                    }    
	                } 
	                xcg.setNormal_id(Integer.valueOf(model.getId()+""));
	                xcg.setCdate(normal.getCheckdate());
	                xcgList.add(xcg);
	                normal.setXcg("√");
	            }   
	            xcgIntoDB(xcgList);
	            
	          //血液生化.  
	            Sheet sheet8 = wb.getSheet("血液生化检疫");   
	            int rowNum8 = sheet8.getLastRowNum()+1;    
	            List<Xysh> xyshList= new ArrayList<Xysh>();  
	            //i 从1开始表示第一行为标题 不包含在数据中  
	            for(int i=1;i<rowNum8;i++){  
	            	Xysh xysh= new Xysh();  
	                Row row = sheet8.getRow(i);    
	                int cellNum = row.getLastCellNum();    
	                for(int j=0;j<cellNum;j++){    
	                    Cell cell = row.getCell((short) j);    
	                    String cellValue = null;    
	                    switch(cell.getCellType()){ //判断excel单元格内容的格式，并对其进行转换，以便插入数据库    
	                        case 0 : cellValue = String.valueOf((int)cell.getNumericCellValue()); break;    
	                        case 1 : cellValue = cell.getStringCellValue(); break;    
	                        case 2 : cellValue = String.valueOf(cell.getDateCellValue()); break;    
	                        case 3 : cellValue = ""; break;    
	                        case 4 : cellValue = String.valueOf(cell.getBooleanCellValue()); break;    
	                        case 5 : cellValue = String.valueOf(cell.getErrorCellValue()); break;    
	                    }    
	                        
	                    switch(j){//通过列数来判断对应插如的字段    
	                        //数据中不应该保护ID这样的主键记录                        
	                        //case 0 : user.setId(Integer.valueOf(cellValue));break;    
	                        case 3 : xysh.setMonkeyid(cellValue);break;    
	                        //case 1 : xysh.setRemark(cellValue);break;    
	                        case 6 : xysh.setBhao(cellValue);break;  
	                        //case 3 : surface.setLastName(cellValue);break;  
	                        //case 4 : surface.setAddress(cellValue);break;  
	                    }    
	                } 
	                xysh.setNormal_id(Integer.valueOf(model.getId()+""));
	                xysh.setCdate(normal.getCheckdate());
	                xyshList.add(xysh);
	                
	                normal.setXysh("√");
	            }   
	            xyshIntoDB(xyshList);
	            //更新normal表.
	            	//normal.setStatus("2");
	            normalService.update(normal);
	        }catch(IOException e){    
	            e.printStackTrace();  
	        } catch (OpenXML4JException e) {
				e.printStackTrace();
			}  catch (InvalidOperationException e) {
				e.printStackTrace();
			}   
	    }  
	  
	    private void surfaceIntoDB(List<Surface> surfaceList) {  
	        int num = surfaceList.size();  
	        for(int i=0;i<num;i++){
	        	surfaceService.save(surfaceList.get(i));
	        }
	    }
	    private void tbIntoDB(List<Tb> tbList) {  
	        int num = tbList.size();  
	        for(int i=0;i<num;i++){
	        	tbService.save(tbList.get(i));
	        }
	    }
	    private void vaccineIntoDB(List<Vaccine> vaccineList) {  
	        int num = vaccineList.size();  
	        for(int i=0;i<num;i++){
	        	vaccineService.save(vaccineList.get(i));
	        }
	    }
	    private void virusIntoDB(List<Virus> virusList) {  
	        int num = virusList.size();  
	        for(int i=0;i<num;i++){
	        	virusService.save(virusList.get(i));
	        }
	    }
	    private void bacteriaIntoDB(List< Bacteria> bacteriaList) {  
	        int num = bacteriaList.size();  
	        for(int i=0;i<num;i++){
	        	bacteriaService.save(bacteriaList.get(i));
	        }
	    }
	    private void parasiteIntoDB(List<Parasite> parasiteList) {  
	        int num = parasiteList.size();  
	        for(int i=0;i<num;i++){
	        	parasiteService.save(parasiteList.get(i));
	        }
	    }
	    private void qcIntoDB(List<Qc> qcList) {  
	        int num = qcList.size();  
	        for(int i=0;i<num;i++){
	        	qcService.save(qcList.get(i));
	        }
	    }
	    private void xcgIntoDB(List<Xcg> xcgList) {  
	        int num = xcgList.size();  
	        for(int i=0;i<num;i++){
	        	xcgService.save(xcgList.get(i));
	        }
	    }
	    private void xyshIntoDB(List<Xysh> xyshList) {  
	        int num = xyshList.size();  
	        for(int i=0;i<num;i++){
	        	xyshService.save(xyshList.get(i));
	        }
	    }
	/**
	 * 加载新增检疫页.编辑
	 * @return
	 */
	public String addList(){
		if (!"".equals(normalid)&&normalid!=null) {
			Normal normal = normalService.getById(Long.valueOf(normalid));
			monkeylist = normal.getMonkeylist();
			title = normal.getTitle();
			this.setTitle(title);
			if (!"".equals(normal.getCheckdate())) {
				checkdate = (DateUtil.dateToString(normal.getCheckdate(),
						"yyyy-MM-dd"));
			}
			if (!"".equals(normal.getBoss()) && normal.getBoss() != null) {
				//boss = employeeService.getById(normal.getBoss()).getName();
			}
			ActionContext.getContext().put("monkeylist", monkeylist);
			ActionContext.getContext().put("title", title);
			ActionContext.getContext().put("checkdate", checkdate);
			ActionContext.getContext().put("boss", normal.getBoss());
			ActionContext.getContext().put("veterinarian", normal.getModified_by());//检疫兽医.
			//ActionContext.getContext().put("addOrEdit", "edit");
		}else{
			//ActionContext.getContext().put("addOrEdit", "add");
		}
		return "addList";
	}
	
	/**
	 * 导出
	 * 
	 * @throws Exception
	 */
	public String exportCheckItems() throws Exception {
		String kindNo=kind;
		ArrayList<Object> filedName = exportService.getExcelFiledNameList(kindNo);
		
		if ("vaccine".equals(kindNo)) {
			ArrayList<Object> filedData = exportService.getVaccineExcelFiledDataList(model.getId());
			ExcelFileGenerator2 generator = new ExcelFileGenerator2(filedName,filedData,kindNo);
			fileInput = generator.getInputStream();
			String tempName = "疫苗检疫采集结果表" + "-"+ DateUtil.dateToString(new Date(), "yyyy-MM-dd");
			fileName = new String(tempName.getBytes(), "ISO8859-1");
		}
		if ("virus".equals(kindNo)) {
			ArrayList<Object> filedData = exportService.getVirusExcelFiledDataList(model.getId());
			ExcelFileGenerator2 generator = new ExcelFileGenerator2(filedName,filedData,kindNo);
			fileInput = generator.getInputStream();
			String tempName = "病毒检疫采集结果表" + "-"+ DateUtil.dateToString(new Date(), "yyyy-MM-dd");
			fileName = new String(tempName.getBytes(), "ISO8859-1");
		}
		if ("parasite".equals(kindNo)) {
			ArrayList<Object> filedData = exportService.getParasiteExcelFiledDataList(model.getId());
			ExcelFileGenerator2 generator = new ExcelFileGenerator2(filedName,filedData,kindNo);
			fileInput = generator.getInputStream();
			String tempName = "寄生虫检疫采集结果表" + "-"+ DateUtil.dateToString(new Date(), "yyyy-MM-dd");
			fileName = new String(tempName.getBytes(), "ISO8859-1");
		}
		if ("bacteria".equals(kindNo)) {
			ArrayList<Object> filedData = exportService.getBacteriaExcelFiledDataList(model.getId());
			ExcelFileGenerator2 generator = new ExcelFileGenerator2(filedName,filedData,kindNo);
			fileInput = generator.getInputStream();
			String tempName = "细菌检疫采集结果表" + "-"+ DateUtil.dateToString(new Date(), "yyyy-MM-dd");
			fileName = new String(tempName.getBytes(), "ISO8859-1");
		}
		if ("qc".equals(kindNo)) {
			ArrayList<Object> filedData = exportService.getQCExcelFiledDataList(model.getId());
			ExcelFileGenerator2 generator = new ExcelFileGenerator2(filedName,filedData,kindNo);
			fileInput = generator.getInputStream();
			String tempName = "驱虫检疫采集结果表" + "-"+ DateUtil.dateToString(new Date(), "yyyy-MM-dd");
			fileName = new String(tempName.getBytes(), "ISO8859-1");
		}
		if ("xcg".equals(kindNo)) {
			ArrayList<Object> filedData = exportService.getXCGExcelFiledDataList(model.getId());
			ExcelFileGenerator2 generator = new ExcelFileGenerator2(filedName,filedData,kindNo);
			fileInput = generator.getInputStream();
			String tempName = "血常规检疫采集结果表" + "-"+ DateUtil.dateToString(new Date(), "yyyy-MM-dd");
			fileName = new String(tempName.getBytes(), "ISO8859-1");
		}
		if ("xysh".equals(kindNo)) {
			ArrayList<Object> filedData = exportService.getXYSHExcelFiledDataList(model.getId());
			ExcelFileGenerator2 generator = new ExcelFileGenerator2(filedName,filedData,kindNo);
			fileInput = generator.getInputStream();
			String tempName = "血生化检疫采集结果表" + "-"+ DateUtil.dateToString(new Date(), "yyyy-MM-dd");
			fileName = new String(tempName.getBytes(), "ISO8859-1");
		}
		if ("tb".equals(kindNo)) {
			ArrayList<Object> filedData = exportService.getTBExcelFiledDataList(model.getId());
			ExcelFileGenerator2 generator = new ExcelFileGenerator2(filedName,filedData,kindNo);
			fileInput = generator.getInputStream();
			String tempName = "TB检疫采集结果表" + "-"+ DateUtil.dateToString(new Date(), "yyyy-MM-dd");
			fileName = new String(tempName.getBytes(), "ISO8859-1");
		}
		//------
		if ("infectious".equals(kindNo)) {
			ArrayList<Object> filedData = exportService.getInfectiousExcelFiledDataList(model.getId());
			ExcelFileGenerator2 generator = new ExcelFileGenerator2(filedName,filedData,kindNo);
			fileInput = generator.getInputStream();
			String tempName = "传染病检疫采集结果表" + "-"+ DateUtil.dateToString(new Date(), "yyyy-MM-dd");
			fileName = new String(tempName.getBytes(), "ISO8859-1");
		}
		if ("x".equals(kindNo)) {
			ArrayList<Object> filedData = exportService.getInfectiousExcelFiledDataList(model.getId());
			ExcelFileGenerator2 generator = new ExcelFileGenerator2(filedName,filedData,kindNo);
			fileInput = generator.getInputStream();
			String tempName = "X光检疫采集结果表" + "-"+ DateUtil.dateToString(new Date(), "yyyy-MM-dd");
			fileName = new String(tempName.getBytes(), "ISO8859-1");
		}
		if ("surface".equals(kindNo)) {
			ArrayList<Object> filedData = exportService.getSurfaceExcelFiledDataList(model.getId());
			ExcelFileGenerator2 generator = new ExcelFileGenerator2(filedName,filedData,kindNo);
			fileInput = generator.getInputStream();
			String tempName = "体表检疫采集结果表" + "-"+ DateUtil.dateToString(new Date(), "yyyy-MM-dd");
			fileName = new String(tempName.getBytes(), "ISO8859-1");
		}
		return "export";
	}
	/**
	 * 编辑-加载房舍
	 * @return
	 */
	public void loadSelectArea(){
		List<Map<String,Object>> lists=new ArrayList<Map<String,Object>>();
		Normal normal=normalService.getById(Long.valueOf(normalid));
		String monkeylist=normal.getMonkeylist();
		String [] str=monkeylist.split(",");
		for(int i=0;i<str.length;i++){
			String monkeyid=str[i];
			List<?> l=exportService.getSelectHouse(monkeyid);
			Map m=new HashMap<String,Object>();
			
				m.put("areaname", l.get(0));
			
			lists.add(m);
		}
		String json=JsonPluginsUtil.beanListToJson(lists);
		writeJson(json);
	}
	/**
	 * 编辑--加载动物
	 * @return
	 */
	public void loadSelectMonkey(){
		List<Map<String,Object>> lists=new ArrayList<Map<String,Object>>();
		Normal normal=normalService.getById(Long.valueOf(normalid));
		String monkeylists=normal.getMonkeylist();
		String [] str=monkeylists.split(",");
		for(int i=0;i<str.length;i++){
			String monkeyid=str[i];
			Map m=new HashMap<String,Object>();
			m.put("monkeyid", monkeyid);
			lists.add(m);
		}
		String json=JsonPluginsUtil.beanListToJson(lists);
		writeJson(json);
		
	}
	/**
	 * 保存修改
	 */
	public void editSave(){
		
	}

	/**
	 * 查看
	 * @return
	 */
	public void showCheck(){
		Normal normal=normalService.getById(model.getId());
		String json=JsonPluginsUtil.beanToJson(normal,"yyyy-MM-dd");
		writeJson(json);
	}
	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getAttachmentNames() {
		return attachmentNames;
	}

	public void setAttachmentNames(String attachmentNames) {
		this.attachmentNames = attachmentNames;
	}

	public InputStream getFileInput() {
		return fileInput;
	}

	public void setFileInput(InputStream fileInput) {
		this.fileInput = fileInput;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getNormalid() {
		return normalid;
	}

	public void setNormalid(String normalid) {
		this.normalid = normalid;
	}

	public void setMonkeylist(String monkeylist) {
		this.monkeylist = monkeylist;
	}

	public String getCheckdate() {
		return checkdate;
	}

	public void setCheckdate(String checkdate) {
		this.checkdate = checkdate;
	}

	public String getBoss() {
		return boss;
	}

	public void setBoss(String boss) {
		this.boss = boss;
	}

	public String getMonkeylist() {
		return monkeylist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddOrEdit() {
		return addOrEdit;
	}

	public void setAddOrEdit(String addOrEdit) {
		this.addOrEdit = addOrEdit;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

}
