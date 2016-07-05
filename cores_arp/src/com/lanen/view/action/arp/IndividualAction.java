package com.lanen.view.action.arp;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Animaltype;
import com.lanen.model.Area;
import com.lanen.model.Bacteria;
import com.lanen.model.Employee;
import com.lanen.model.Individual;
import com.lanen.model.Individual_Json;
import com.lanen.model.Parasite;
import com.lanen.model.Publiccode;
import com.lanen.model.Vaccine;
import com.lanen.model.Virus;
import com.lanen.model.Weight;
import com.lanen.service.arp.AnimaltypeService;
import com.lanen.service.arp.AreaService;
import com.lanen.service.arp.BacteriaService;
import com.lanen.service.arp.EmployeeService;
import com.lanen.service.arp.IndividualService;
import com.lanen.service.arp.ParasiteService;
import com.lanen.service.arp.PubliccodeService;
import com.lanen.service.arp.VaccineService;
import com.lanen.service.arp.VirusService;
import com.lanen.service.arp.WeightService;
import com.lanen.util.Constant;
import com.lanen.util.DateUtil;
import com.lanen.util.ExcelFileGenerator;
import com.lanen.util.WordFileGenerator1;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class IndividualAction extends BaseAction<Individual> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -273310049764858447L;

	@Resource
	private IndividualService individualService;
	@Resource
	private EmployeeService employeeService;
	@Resource
	AreaService areaService;

	private String area;
	private String roomid;
	private Double mincurrentweight;// 最小当前体重

	private Double maxcurrentweight;// 最大当前体重

	private Date birthdaymin; // 最小生日

	private Date birthdaymax;// 最大生日

	private Date yjdatemin;// 最小引进时间

	private Date yjdatemax;// 最大引进时间

	private String monkeyidMin;// 最小动物编号

	private String monkeyidMax;// 最大动物编号

	private int quyu;// 区域

	private String mailMonkeyids;// 公猴子编号组

	private String rows;// 每页显示的记录数
	private String page;// 当前第几页

	private InputStream fileInput;
	private String fileName;

	//病毒
	private String bv;
	private String stlv;
	private String srv;
	private String siv;
	private String measles;
	private String hepatitisa;
	//寄生虫
	private String amb;
	private String gxc;
	private String lyc;
	private String bmc;
	private String twjsc;
	//细菌
	private String shig;
	private String salm;
	private String yers;
	private String mazhen;
	private String jiag;
	private String yig;
	private String fileNameForWord;
	@Resource
	private WeightService weightService;
	private String areaName;
	@Resource
	private PubliccodeService publiccodeService;
	@Resource
	private VirusService virusService;
	@Resource
	private VaccineService vaccineService;
	@Resource
	private BacteriaService bacteriaService;
	@Resource
	private ParasiteService parasiteService;
	
	@Resource
	private AnimaltypeService animaltypeService;
	private String AddOrEdityjaddress;
	public String list() {
		return "list";
	}

	/**
	 * 根据条件加载猴子
	 */
	public void loadList() {
		Map<String, Object> mapList = individualService.getAllIndividual(page,
				rows, model.getMonkeyid(), model.getAnimaltype(),
				model.getSex(), model.getKeeper(), model.getYjaddress(),
				model.getSourceaddress(), mincurrentweight, maxcurrentweight,
				birthdaymin, birthdaymax, yjdatemin, yjdatemax, monkeyidMin,
				monkeyidMax, quyu);
		String jsonStr = JsonPluginsUtil.beanToJson(mapList, "yyyy-MM-dd");
		writeJson(jsonStr);
	}

	/**
	 * 引进地
	 */
	public void loadListYjaddress() {
		List<Map<String, Object>> mapList = individualService.getAllYjaddressMap(Constant.ADDRESS_TYPE_MARK);
		String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(jsonStr);
	}
	public void loadListYJAddress(){
		List<Publiccode> list=individualService.getYJAddressInfo(Constant.ADDRESS_TYPE_MARK);
		String json=JsonPluginsUtil.beanListToJson(list);
		writeJson(json);
	}
	/**
	 * 引进地删除
	 */
	public void delAddress(){
		Map<String,Object> map=new HashMap<String,Object>();
		if(!"".equals(model.getId())&&model.getId()!=null){
			publiccodeService.delete(model.getId());
			map.put("success", true);
			map.put("msg","删除成功");
		}
		String json=JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	/**
	 * 引进地添加
	 */
	public void addAddress(){
		Map<String,Object> map=new HashMap<String,Object>();
		Publiccode pc=new Publiccode();
		pc.setName(request.getParameter("name"));
		pc.setDesciption(request.getParameter("desciption"));
		pc.setMark(Constant.ADDRESS_TYPE_MARK);
		publiccodeService.save(pc);
		map.put("success", true);
		map.put("msg", "添加成功");
		map.put("id", pc.getId());
		String json=JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	/**
	 * 引进地编辑
	 */
	public void toEditAddress(){
		if(!"".equals(model.getId())&&model.getId()!=null){
			Publiccode pc=publiccodeService.getById(model.getId());
		
			String json=JsonPluginsUtil.beanToJson(pc);
			writeJson(json);
		}
	}
	public void editSaveAddress(){
		Map<String,Object> map=new HashMap<String,Object>();
		String addressId=request.getParameter("addressId");
		if(!"".equals(addressId)&&addressId!=null){
			Publiccode pc=publiccodeService.getById(Integer.parseInt(addressId));
			pc.setDesciption(request.getParameter("desciption"));
			pc.setName(request.getParameter("name"));
			publiccodeService.update(pc);
			map.put("success", true);
			map.put("msg", "编辑成功");
			map.put("id", addressId);
		}
		String json=JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	/**
	 * 来源
	 */
	public void loadListSourceaddress() {
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		Map<String,String> map=new HashMap<String,String>();
		map.put("id", "-1");
		map.put("text", "&nbsp;");
		list.add(map);
		list.addAll(weightService.getWeightTypeMap(Constant.SOURCE_TYPE_MARK));
		
		String jsonStr=JsonPluginsUtil.beanListToJson(list);
		writeJson(jsonStr);
	}

	/**
	 * 猴子种类
	 */
	public void loadListAnimaltype() {
		List<Map<String, Object>> mapList = individualService.getAnimaltypeMap();
		String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(jsonStr);
	}

	/**
	 * 猴子种类必须
	 * 
	 */
	public void addOreditloadListAnimaltype() {
		List<Map<String, String>> mapList = individualService
				.getAnimaltypeMapNo();
		String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(jsonStr);

	}

	/**
	 * 导出
	 * 
	 * @throws Exception
	 */
	public String export() throws Exception {
		// 获取导出的表头和数据
		// 获取表头,存放到ArrayList filedName对象中(登录名 用户姓名 性别 联系电话 是否在职)
		ArrayList<Object> filedName = individualService.getExcelFiledNameList();
		// /**获取数据, 是值存放到ArrayList dataList集合中
		// *再实例化一个ArrayList filedData集合 filedData.add(dataList);
		// */
		//
		ArrayList<Object> filedData = individualService.getExcelFiledDataList(
				model.getMonkeyid(), model.getAnimaltype(), model.getSex(),
				model.getKeeper(), model.getYjaddress(),
				model.getSourceaddress(), mincurrentweight, maxcurrentweight,
				birthdaymin, birthdaymax, yjdatemin, yjdatemax, monkeyidMin,
				monkeyidMax, quyu);
		ExcelFileGenerator generator = new ExcelFileGenerator(filedName,
				filedData);
		fileInput = generator.getInputStream();
		fileName = getFileName();
		return "export";
	}

	/**
	 * 执行删除操作
	 */
	public void delIndividual() {
		Map<String, Object> map = new HashMap<String, Object>();
		if ((model.getMonkeyid() != null) && (!model.getMonkeyid().equals(""))) {
			Individual individual = individualService.getByMonkeyid(model
					.getMonkeyid());
			individual.setDeleted(1);
			individualService.update(individual);
			map.put("success", true);
		} else {
			map.put("success", false);
		}
		map.put("success", true);
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);

	}

	/**
	 * 返回类型为"中文名字-20130612231234.xls"
	 * 
	 * @return
	 */
	public String getFileName() throws Exception {
		String tempName = "数据导出" + "-"
				+ DateUtil.dateToString(new Date(), "yyyy-MM-dd") + ".xls";

		fileName = new String(tempName.getBytes(), "ISO8859-1");
		return fileName;
	}

	/** 检查动物编号是否重复 */
	public void checkMonkeyid() {
		if (null != model.getMonkeyid() && !"".equals(model.getMonkeyid())) {
			boolean isExist = individualService.isExistMonkeyid(model
					.getMonkeyid());
			if (!isExist) {
				writeJson("true");
			} else {
				writeJson("false");
			}
		} else {
			writeJson("false");
		}
	}

	/**
	 * 保存添加
	 */
	public void addSave() {
		Map<String, Object> map = new HashMap<String, Object>();
		Employee user = (Employee) ActionContext.getContext().getSession()
				.get("user");
		if (null != model) {
			model.setDeleted(0);
			model.setCreatetime(new Date());
			model.setCreated_by(Integer.parseInt(String.valueOf(user.getId())));
			model.setYjaddress(AddOrEdityjaddress);
			model.setChipid(model.getChipid().replaceAll("\\s*", ""));
			individualService.save(model);
			map.put("success", true);
		} else {
			map.put("success", false);
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);

	}

	/**
	 * 根据猴子id查询
	 */
	public void findIndividula() {
		Individual individual = individualService.findtById(model.getId());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", individual.getId());
		map.put("monkeyid", individual.getMonkeyid());
		map.put("animaltype", individual.getAnimaltype());
		map.put("birthday", individual.getBirthday());
		map.put("agetype", individual.getAgetype());
		map.put("iszhongqun", individual.getIszhongqun());
		map.put("sex", individual.getSex());
		map.put("source", individual.getSource());
		map.put("generation", individual.getGeneration());
		map.put("leavebreastdate", individual.getLeavebreastdate());
		map.put("leavebreastweight", individual.getLeavebreastweight());
		map.put("birthdayweight", individual.getBirthdayweight());
		map.put("currentweight", individual.getCurrentweight());
		map.put("weighingDate", DateUtil.dateToString(
				individual.getWeighingDate(), "yyyy-MM-dd"));
		map.put("fatherid", individual.getFatherid());
		map.put("motherid", individual.getMotherid());
		map.put("blongarea", individual.getBlongarea());
		map.put("room", individual.getRoom());
		map.put("keeper", individual.getKeeper());
		map.put("veterinarian", individual.getVeterinarian());
		map.put("operater", individual.getOperater());
		map.put("tnid", individual.getTnid());
		map.put("status", individual.getStatus());
		map.put("ysz", individual.getYsz());
		map.put("yjdate", individual.getYjdate());
		map.put("yjaddress", individual.getYjaddress());
		map.put("remark", individual.getRemark());
		map.put("lhao", individual.getLhao());
		map.put("chipid", individual.getChipid());
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	/**
	 * 编辑保存
	 */
	public void editSave() {
		Map<String, Object> map = new HashMap<String, Object>();
		Employee user = (Employee) ActionContext.getContext().getSession()
				.get("user");
		if (null != model) {
			Individual individual = individualService.findtById(model.getId());
			individual.setSex(model.getSex());
			individual.setAnimaltype(model.getAnimaltype());
			individual.setBirthday(model.getBirthday());
			individual.setGeneration(model.getGeneration());
			individual.setSource(model.getSource());
				individual.setKeeper(model.getKeeper());
			
			
				individual.setVeterinarian(model.getVeterinarian());
			
			
			individual.setCurrentweight(model.getCurrentweight());
			individual.setWeighingDate(model.getWeighingDate());
			individual.setBlongarea(model.getBlongarea());
			individual.setRoom(model.getRoom());
			individual.setTnid(model.getTnid());
			individual.setStatus(model.getStatus());
			individual.setLhao(model.getLhao());
			individual.setMotherid(model.getMotherid());
			individual.setFatherid(model.getFatherid());
			individual.setLeavebreastdate(model.getLeavebreastdate());
			individual.setLeavebreastweight(model.getLeavebreastweight());
			individual.setBirthdayweight(model.getBirthdayweight());
			individual.setYjaddress(request.getParameter("AddOrEdityjaddress"));
			individual.setYjdate(model.getYjdate());
			individual.setYsz(model.getYsz());
			individual.setChipid(model.getChipid().replaceAll("\\s*", ""));
			individual.setRemark(model.getRemark());
			individual.setModified_by(Integer.parseInt(String.valueOf(user
					.getId())));
			individual.setLastmodifytime(new Date());
			individualService.update(individual);
			map.put("success", true);
		} else {
			map.put("success", false);
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);

	}

	/**
	 * 检查猴子编号是否存在
	 */
	public void checkMonkeyidHave() {
		if (null != model.getMonkeyid() && !"".equals(model.getMonkeyid())) {
			boolean isExist = individualService.isExistMonkeyid(model
					.getMonkeyid());
			if (isExist) {
				writeJson("true");
			} else {
				writeJson("false");
			}
		} else {
			writeJson("false");
		}
	}

	/**
	 * 检查猴子编号是否存在(母)
	 */
	public void checkMonkeyidHaveM() {
		if (null != model.getMotherid() && !"".equals(model.getMonkeyid())) {
			boolean isExist = individualService.isExistMonkeyidM(model
					.getMotherid());
			if (isExist) {
				writeJson("true");
			} else {
				writeJson("false");
			}
		} else {
			writeJson("false");
		}
	}

	/**
	 * 检查猴子编号是否存在(公)
	 */
	public void checkMonkeyidHaveF() {
		if (null != model.getFatherid() && !"".equals(model.getFatherid())) {
			boolean isExist = individualService.isExistMonkeyidF(model
					.getFatherid());
			if (isExist) {
				writeJson("true");
			} else {
				writeJson("false");
			}
		} else {
			writeJson("false");
		}
	}

	/**
	 * 检查多只猴子编号是否存在（公）
	 */
	public void checkMailMonkeyidsHave() {
		if (null != mailMonkeyids && !mailMonkeyids.equals("")) {
			String[] mailMonkeys = mailMonkeyids.split(",");
			for (int i = 0; i < mailMonkeys.length; i++) {
				boolean isExist = individualService
						.isExistMonkeyidF(mailMonkeys[i]);
				if (isExist == false) {
					writeJson("false");
					return;
				}
			}
			writeJson("true");
		} else {
			writeJson("true");
		}
	}

	/**
	 * 导出word
	 * 
	 * @return
	 * @throws Exception
	 */
	public String exportExcelOne() throws Exception {
		if (!"".equals(model.getId())&&model.getId()!=null) {
			
			List<Individual_Json> individual = individualService.getIndividualJsonById(model.getId());
			
			if (individual.size() > 0) {
				Individual_Json ij = individual.get(0);
				//获取最新4次称重信息。
				List<Weight> listWeightInfo=weightService.getLast4WeightRecord(ij.getMonkeyid());
				//病毒最后一次
				List<?> listVirusInfo=virusService.getLastOneVirusInfo(ij.getMonkeyid());
				Virus virus=new Virus();
				for(Object ob:listVirusInfo){
					Object[]objs=(Object[])ob;
					virus.setBv((String)objs[0]);
					virus.setStlv((String)objs[1]);
					virus.setSiv((String)objs[2]);
					virus.setSrv((String)objs[3]);
					virus.setFilo((String)objs[4]);
				}
				//疫苗最近一次
				List<Vaccine> listVaccineInfo=vaccineService.getLast3VaccineRecord(ij.getMonkeyid());
				//细菌最近7
				List<Bacteria> listBacteria=bacteriaService.getLast7BacteriaRecord(ij.getMonkeyid());
				//寄生虫6
				List<Parasite> listParasite=parasiteService.getLast6ParasiteRecord(ij.getMonkeyid());
				WordFileGenerator1 generator = new WordFileGenerator1(ij,listWeightInfo,virus,listVaccineInfo,listBacteria,listParasite);
				fileInput = generator.createWorkbook();
				fileNameForWord = getFileNameForWord();
			}
		}
		return "exportWord";

	}
	/**
	 * 返回类型为"中文名字-20130612231234.doc"
	 * 
	 * @return
	 */
	public String getFileNameForWord() throws Exception {
		String tempName = "个体数据导出" + "-"
				+ DateUtil.dateToString(new Date(), "yyyy-MM-dd") + ".doc";

		fileNameForWord = new String(tempName.getBytes(), "ISO8859-1");
		return fileNameForWord;
	}
	/**
	 * 根据猴子编号查找猴子
	 */
	public void findMonkeyByMonkeyid() {
		Individual individual = individualService.getByMonkeyidAndDeleted(model
				.getMonkeyid());
		Individual_Json json = new Individual_Json();
		if (individual != null) {
			json.setId(individual.getId());
			json.setMonkeyid(individual.getMonkeyid());
			Integer sex = individual.getSex();
			Byte sByte = Byte.valueOf(sex + "");
			json.setSex(sByte);
			json.setAnimaltype(individual.getAnimaltype());
			if (!"".equals(individual.getAnimaltype())&&individual.getAnimaltype()!=null) {
				Long animaltype = Long.parseLong(individual.getAnimaltype()+ "");
				String animaltypeName = getAnimaltypeName(animaltype);
				json.setAnimaltypeName(animaltypeName);
			}
			json.setAgetype(individual.getAgetype());
			Integer keepper = individual.getKeeper();
			Long keep = null;
			if (!"".equals(keepper)&&keepper!=null) {
				keep = Long.parseLong(keepper + "");
			}
			String keeperp = getEmpName(keep);
			json.setKeeperp(keeperp);
			Double cweight = individual.getCurrentweight();
			if (cweight!=null&&!"".equals(cweight)) {
				json.setCurrentweight(Float.valueOf(cweight + ""));
			}
			Integer blongarea = individual.getBlongarea();
			Long blong = Long.parseLong(blongarea + "");
			String quyu = getAreaName(blong);
			json.setQuyu(quyu);
			Integer roomInteger = individual.getRoom();
			Long room = Long.parseLong(roomInteger + "");
			String roomName = getAreaName(room);
			json.setRoomName(roomName);
			json.setCurrentdate(individual.getCurrentdate());
			// json.setWeighingDate(individual.getWeighingDate());
			json.setStatus(individual.getStatus());
			json.setRemark(individual.getRemark());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		List<Individual_Json> list = new ArrayList<Individual_Json>();
		list.add(json);
		map.put("rows", list);
		map.put("total", list.size());
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public String breedList() {
		return "breedList";

	}

	/**
	 * 根据条件查询繁殖区的动物
	 */
	public void loadBreedingList() {
		Map<String, Object> mapList = individualService.getBreedingIndividual(
				page, rows, model.getMonkeyid(), model.getAnimaltype(),
				model.getSex(), model.getKeeper(), birthdaymin, birthdaymax,
				quyu, model.getRoom());
		
		String jsonStr = JsonPluginsUtil.beanToJson(mapList, "yyyy-MM-dd");
		writeJson(jsonStr);

	}

	/**
	 * 根据区域ID获得区域名
	 * 
	 * @param id
	 * @return
	 */
	public String getAreaName(Long id) {
		String areaName = null;
		Area area = areaService.getById(id);
		if (area != null) {
			areaName = area.getAreaname();
		}
		return areaName;
	}

	/**
	 * 根据员工ID获得员工姓名
	 * 
	 * @param id
	 * @return
	 */
	public String getEmpName(Long id) {
		String name = null;
		if (id != null) {
			Employee e = employeeService.getById(id);
			if (e != null) {
				name = e.getName();
			}
		}
		return name;
	}
	
	/**
	 * 根据员工ID获得动物类型名
	 * 
	 * @param id
	 * @return
	 */
	public String getAnimaltypeName(Long id) {
		String name = null;
		if (id != null) {
			Animaltype e = animaltypeService.getById(id);
			if (e != null) {
				name = e.getName();
			}
		}
		return name;
	}

	// 根据区域，房间获取猴子
	public void getRoomIndividual() {
		List<Map<String, Object>> list = individualService.getRoomIndividual(
				area, roomid);
		String jsonStr = JsonPluginsUtil.beanListToJson(list);
		writeJson(jsonStr);
	}

	/**
	 * 总揽
	 */
	public String listAnimalByArea(){
		return "listAnimalByArea";
	}
	public void loadListAnimalByArea(){
		Map<String,Object> map=new HashMap<String,Object>();
		List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
		Map<String,Object> m=areaService.getAnimalByArea(rows, page, area);
		if (!"".equals(m)&&m!=null) {
			map.put("total", m.get("total"));
			List<Map<String, Object>> l = (List<Map<String, Object>>) m.get("rows");
			for (int i = 0; i < l.size(); i++) {
				Map<String, Object> mm = l.get(i);
				String room = mm.get("roomid") + "";
				List<?> lists = areaService.getAnimalRoomByArea(room);
				for (Object ob : lists) {
					Object[] objs = (Object[]) ob;
					Map mmm = new HashMap<String, Object>();
					mmm.put("room", objs[0]);
					mmm.put("animaltype", objs[1]);
					mmm.put("malemonkey", mm.get("malemonkey"));
					mmm.put("femalemonkey", mm.get("femalemonkey"));
					mmm.put("yuchengmonkey", mm.get("yuchengmonkey"));
					mmm.put("cubmonkey", mm.get("cubmonkey"));
					mmm.put("keeper", objs[2]);
					mmm.put("veterinarian", objs[3]);
					mmm.put("boss", objs[4]);
					listMap.add(mmm);
				}
			}
		}else{
			map.put("total", 0);
		}
		map.put("rows", listMap);
		String json=JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	/**
	 * 综合查询
	 */
	public String listAnimalByMore(){
		return "listAnimalByMore";
	}
	public void loadListAnimalByMore(){
		Map<String, Object> mapList = individualService.getAllIndividualByMores(page,
				rows, model.getMonkeyid(), model.getAnimaltype(),
				model.getSex(), model.getKeeper(), model.getYjaddress(),
				model.getSourceaddress(), mincurrentweight, maxcurrentweight,
				birthdaymin, birthdaymax, yjdatemin, yjdatemax, monkeyidMin,
				monkeyidMax, quyu,bv,stlv,srv,siv,measles,hepatitisa,
				amb,gxc,lyc,bmc,twjsc,
				shig,salm,yers,mazhen,jiag,yig);
		
		String jsonStr = JsonPluginsUtil.beanToJson(mapList, "yyyy-MM-dd");
		writeJson(jsonStr);
	}
	public void loadArea(){
		List<Area> alist = areaService.getAreas();
		String json=JsonPluginsUtil.beanListToJson(alist);
		writeJson(json);
	}
	public void loadMonkeys(){
		List<Individual> ilist=individualService.getindividuals(roomid);
		String json=JsonPluginsUtil.beanListToJson(ilist);
		writeJson(json);
	}
	/**
	 * 动物来源类型
	 */
	public void listAllSourceType(){
		List<Map<String,String>> list=weightService.getWeightTypeMap(Constant.SOURCE_TYPE_MARK);
		String jsonStr=JsonPluginsUtil.beanListToJson(list);
		writeJson(jsonStr);
	}
	private InputStream excelFile; // 下载文件流

	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}

	public IndividualService getIndividualService() {
		return individualService;
	}

	public void setIndividualService(IndividualService individualService) {
		this.individualService = individualService;
	}

	public Double getMincurrentweight() {
		return mincurrentweight;
	}

	public void setMincurrentweight(Double mincurrentweight) {
		this.mincurrentweight = mincurrentweight;
	}

	public Double getMaxcurrentweight() {
		return maxcurrentweight;
	}

	public void setMaxcurrentweight(Double maxcurrentweight) {
		this.maxcurrentweight = maxcurrentweight;
	}

	public Date getBirthdaymin() {
		return birthdaymin;
	}

	public void setBirthdaymin(Date birthdaymin) {
		this.birthdaymin = birthdaymin;
	}

	public Date getBirthdaymax() {
		return birthdaymax;
	}

	public void setBirthdaymax(Date birthdaymax) {
		this.birthdaymax = birthdaymax;
	}

	public Date getYjdatemin() {
		return yjdatemin;
	}

	public void setYjdatemin(Date yjdatemin) {
		this.yjdatemin = yjdatemin;
	}

	public Date getYjdatemax() {
		return yjdatemax;
	}

	public void setYjdatemax(Date yjdatemax) {
		this.yjdatemax = yjdatemax;
	}

	public String getMonkeyidMin() {
		return monkeyidMin;
	}

	public void setMonkeyidMin(String monkeyidMin) {
		this.monkeyidMin = monkeyidMin;
	}

	public String getMonkeyidMax() {
		return monkeyidMax;
	}

	public void setMonkeyidMax(String monkeyidMax) {
		this.monkeyidMax = monkeyidMax;
	}

	public int getQuyu() {
		return quyu;
	}

	public void setQuyu(int quyu) {
		this.quyu = quyu;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
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

	public void setMailMonkeyids(String mailMonkeyids) {
		this.mailMonkeyids = mailMonkeyids;
	}

	public String getMailMonkeyids() {
		return mailMonkeyids;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getRoomid() {
		return roomid;
	}

	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}

	public String getBv() {
		return bv;
	}

	public void setBv(String bv) {
		this.bv = bv;
	}

	public String getStlv() {
		return stlv;
	}

	public void setStlv(String stlv) {
		this.stlv = stlv;
	}

	public String getSrv() {
		return srv;
	}

	public void setSrv(String srv) {
		this.srv = srv;
	}

	public String getSiv() {
		return siv;
	}

	public void setSiv(String siv) {
		this.siv = siv;
	}

	public String getMeasles() {
		return measles;
	}

	public void setMeasles(String measles) {
		this.measles = measles;
	}

	public String getHepatitisa() {
		return hepatitisa;
	}

	public void setHepatitisa(String hepatitisa) {
		this.hepatitisa = hepatitisa;
	}

	public String getAmb() {
		return amb;
	}

	public void setAmb(String amb) {
		this.amb = amb;
	}

	public String getGxc() {
		return gxc;
	}

	public void setGxc(String gxc) {
		this.gxc = gxc;
	}

	public String getLyc() {
		return lyc;
	}

	public void setLyc(String lyc) {
		this.lyc = lyc;
	}

	public String getBmc() {
		return bmc;
	}

	public void setBmc(String bmc) {
		this.bmc = bmc;
	}

	public String getTwjsc() {
		return twjsc;
	}

	public void setTwjsc(String twjsc) {
		this.twjsc = twjsc;
	}

	public String getShig() {
		return shig;
	}

	public void setShig(String shig) {
		this.shig = shig;
	}

	public String getSalm() {
		return salm;
	}

	public void setSalm(String salm) {
		this.salm = salm;
	}

	public String getYers() {
		return yers;
	}

	public void setYers(String yers) {
		this.yers = yers;
	}

	public void setFileNameForWord(String fileNameForWord) {
		this.fileNameForWord = fileNameForWord;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getMazhen() {
		return mazhen;
	}

	public void setMazhen(String mazhen) {
		this.mazhen = mazhen;
	}

	public String getJiag() {
		return jiag;
	}

	public void setJiag(String jiag) {
		this.jiag = jiag;
	}

	public String getYig() {
		return yig;
	}

	public void setYig(String yig) {
		this.yig = yig;
	}

	public String getAddOrEdityjaddress() {
		return AddOrEdityjaddress;
	}

	public void setAddOrEdityjaddress(String addOrEdityjaddress) {
		AddOrEdityjaddress = addOrEdityjaddress;
	}

}
