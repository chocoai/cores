package com.lanen.view.action.schdeule;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.Json;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.User;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.model.contract.TblNotification;
import com.lanen.model.schedule.TblAttachment;
import com.lanen.model.schedule.TblAttachmentIndex;
import com.lanen.service.contract.TblNotificationService;
import com.lanen.service.schdeule.TblAttachmentIndexService;
import com.lanen.util.DateUtil;
import com.lanen.util.FileUtil;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;

/**
 * 附件索引Action
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class TblAttachmentIndexAction extends BaseAction<TblAttachmentIndex> {

	private static final long serialVersionUID = -1112930580657976653L;
	
	@Resource
	private TblAttachmentIndexService tblAttachmentIndexService;
	
	/**
	 * 发送通知
	 */
	@Resource
	private TblNotificationService tblNotificationService;
	
	private Date startDate;		//开始日期
	private Date endDate;		//结束日期
	private String condition;	//模糊查询条件，studyno，describe
	
	
	//uploadFile对应页面<input type="file" name="uploadFile">  
	private List<File> uploadFile;   
	//文件名对应uploadFile+“FileName”，要不获取不到文件名  
	private List<String> uploadFileFileName;    

	private List<String> uploadFileContentType;//文件类型
	
	private List<Integer> printNums;
	
	private InputStream fileInput;  
    private String fileName; 
	
	/**
	 * 主页面
	 * @return
	 */
	public String list (){
		
		String currentDateStr = DateUtil.getNow("yyyy-MM-dd");
		String oneMonthAgoDateStr = DateUtil.getDateAgo(6);
		
		ActionContext.getContext().put("startDate", oneMonthAgoDateStr);
		ActionContext.getContext().put("endDate", currentDateStr);
		
		//是否有打印所有的权限
		boolean printAll = userService.checkPrivilege(getCurrentUser(), "文件打印-打印");
		boolean write = userService.checkPrivilege(getCurrentUser(), "文件打印-登记");
		ActionContext.getContext().getSession().put("printAll", printAll);
		ActionContext.getContext().getSession().put("write", write);
		
		return "list";
	}
	/**
	 * 附件索引表  加载数据 
	 * @return
	 */
	public void loadIndexList (){
		
		if(null != startDate && null != endDate){
			boolean printAll = (Boolean) ActionContext.getContext().getSession().get("printAll");
			 String creater = model.getCreater();
			if(!printAll){
				User user = getCurrentUser();
				if(null != user){
					creater = user.getUserName();
				}
			}
			List<Map<String,Object>> mapList = tblAttachmentIndexService
			.getMapListByDateCreaterStateStudyNoDescribe(startDate,endDate,creater,model.getState(),condition);
			
			if(null != mapList){
				String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
				writeJson(jsonStr);
			}
		}
	}

	/**
	 * 加载创建者列表
	 */
	public void loadAllCreater(){
		List<Map<String,Object>> list = null;
		Map<String,Object> map =null;
		boolean printAll = (Boolean) ActionContext.getContext().getSession().get("printAll");
		if(printAll){
			list = tblAttachmentIndexService.getAllCreaterMapList();
		}else{
			list = new ArrayList<Map<String,Object>>();
			User user = getCurrentUser();
			if(null != user){
				map = new HashMap<String,Object>();
				map.put("id", user.getUserName());
				map.put("text", user.getRealName());
				list.add(map);
			}
		}
		if(list == null ){
			list = new ArrayList<Map<String,Object>>();
		}
		map = new HashMap<String,Object>();
		map.put("id", "");
		map.put("text", "全部");
		list.add(0, map);
		
		String jsonStr = JsonPluginsUtil.beanListToJson(list);
		writeJson(jsonStr);
	}
	/**
	 * 验证是否是未提交
	 */
	public void isUncommitted(){
		String flag = "false";
		if(null != model.getId()){
			boolean isUncommitted = tblAttachmentIndexService.isUncommittedById(model.getId());
			if(isUncommitted){
				flag = "true";
			}else{
				flag = "false";
			}
		}
		writeJson(flag);
	}
	/**
	 * 验证是否    未撤销（未撤销为true）
	 */
	public void isNoCancel(){
		String flag = "false";
		if(null != model.getId()){
			boolean isNoCancel = tblAttachmentIndexService.isNoCancelById(model.getId());
			if(isNoCancel){
				flag = "true";
			}else{
				flag = "false";
			}
		}
		writeJson(flag);
	}
	
	/**
	 * 撤销
	 */
	public void cancel(){
		Json json = new Json();
		if(null != model.getId()){
			//是未提交 ？
			boolean isUncommitted = tblAttachmentIndexService.isUncommittedById(model.getId());
			if(isUncommitted){
				boolean write = (Boolean) ActionContext.getContext().getSession().get("write");
				TblAttachmentIndex tblAttachmentIndex = tblAttachmentIndexService.getById(model.getId());
				String userName = "";
				User user = getCurrentUser();
				if(null != user){
					userName = user.getUserName();
				}
				if(write && userName.equals(tblAttachmentIndex.getCreater())){
					
					tblAttachmentIndexService.cancelById(model.getId());
					json.setSuccess(true);
					json.setMsg("文件已撤销.");
				}else{
					json.setMsg("当前用户无权撤销文件！");
				}
			}else{
				json.setMsg("已打印，不可撤销！");
			}
		}else{
			json.setMsg("与服务器交互错误！");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	
	/**
	 * 标记已打印（索引及对应未标记的附件）
	 */
	public void signHasPrint(){
		Json json = new Json();
		if(null != model.getId()){
			//是否存在未提交的
			boolean isHasUncommitted = tblAttachmentIndexService.isHasUncommittedById(model.getId());
			if(isHasUncommitted){
				String userName ="";
				User user = getCurrentUser();
				if(null != user){
					userName = user.getUserName();
				}
				tblAttachmentIndexService.signHasPrintById(model.getId(),userName);
				
				json.setSuccess(true);
				json.setMsg("附件已标记打印.");
				List<String> receiverList = new ArrayList<String>();
				TblAttachmentIndex tblAttachmentIndex = tblAttachmentIndexService.getById(model.getId());
				receiverList.add(tblAttachmentIndex.getCreater());
	    		if(null != receiverList && receiverList.size()>0){
	    			writeNotification3(tblAttachmentIndex.getDescribe(),receiverList);
	    		}
	    		
	    		writeLog("文件已打印","文件描述："+tblAttachmentIndex.getDescribe());
			}else{
				json.setMsg("已撤销，不可标记打印！");
			}
		}else{
			json.setMsg("与服务器交互错误！");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	/**
	 * 标记已打印（附件,如果都标记了，把索引也标记了）
	 */
	public void signHasPrintOne(){
		Json json = new Json();
		if(null != model.getId()){
			TblAttachment tblAttachment = tblAttachmentIndexService.getTblAttachmentById(model.getId());
			if(null != tblAttachment && tblAttachment.getState() == 0){
				String userName ="";
				User user = getCurrentUser();
				if(null != user){
					userName = user.getUserName();
				}
				tblAttachmentIndexService.signHasPrintOneById(model.getId(),userName,tblAttachment.getIndexId());
				
				json.setSuccess(true);
				json.setMsg("附件已标记打印.");
				
				List<String> receiverList = new ArrayList<String>();
				TblAttachmentIndex tblAttachmentIndex = tblAttachmentIndexService.getById(tblAttachment.getIndexId());
				receiverList.add(tblAttachmentIndex.getCreater());
	    		if(null != receiverList && receiverList.size()>0){
	    			writeNotification2(tblAttachment.getFileName(),receiverList);
	    		}
	    		
	    		writeLog("文件已打印","文件名称："+tblAttachment.getFileName());
			}else{
				json.setMsg("已撤销，不可标记打印！");
			}
		}else{
			json.setMsg("与服务器交互错误！");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	/**
	 * 转到view
	 * @return
	 */
	public String view(){
		if(null != model.getId()){
			List<Map<String,Object>> resultList = tblAttachmentIndexService.getMapListById(model.getId());
			String remark = "";
			TblAttachmentIndex tblAttachmentIndex = tblAttachmentIndexService.getById(model.getId());
			if(null != tblAttachmentIndex){
				remark = tblAttachmentIndex.getRemark();
			}
			ActionContext.getContext().put("resultList", resultList);
			ActionContext.getContext().put("remark", remark);
		}
		return "view";
	}
	
	/**
	 * 文件上传(file)
	 */
	public void upload(){
		Map<String,String> json  = new HashMap<String,String>();
		json.put("success", "false");
		json.put("msg", "");
		
		if(dataCheck()){
			boolean write = (Boolean) ActionContext.getContext().getSession().get("write");
			if(write){
				String rootDirectory = tblAttachmentIndexService.getRootDirectory();
				if(null != rootDirectory){
					//附件索引
					TblAttachmentIndex tblAttachmentIndex = new TblAttachmentIndex();
					String id = tblAttachmentIndexService.getKey();
					tblAttachmentIndex.setId(id);
					tblAttachmentIndex.setStudyNo(model.getStudyNo());
					tblAttachmentIndex.setDescribe(model.getDescribe());
					tblAttachmentIndex.setRemark(model.getRemark());
					Date date = new Date();
					String userName = null;
					User user = getCurrentUser();
					if(null != user){
						userName = user.getUserName();
					}
					tblAttachmentIndex.setCreater(userName);
					tblAttachmentIndex.setCreateTime(date);
					tblAttachmentIndex.setState(0);
					//附件列表
					List<TblAttachment> tblAttachmentList = new ArrayList<TblAttachment>();
					TblAttachment tblAttachment = null;
					int i = 0;
					 boolean flag = true;// 如果 有异常， flag = false; break;
					for(String fileName:uploadFileFileName){
						tblAttachment = new TblAttachment();
						tblAttachment.setId(tblAttachmentIndexService.getKey("TblAttachment"));
						tblAttachment.setIndexId(id);
						String realFileName ="";
						String fileUrl = rootDirectory+"\\"+userName;
						int printNum = printNums.get(i);
						try {
							realFileName = FileUtil.generateFileName(fileName);
							FileUtil.makeDir(fileUrl);
							
							FileUtil.uploadFile(uploadFile.get(i), new File(fileUrl+"\\"+realFileName));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						} catch (IOException e) {
							flag = false;
							e.printStackTrace();
							
							json.put("msg","网络磁盘不存在或拒绝访问！");
							break;
						}
						
						tblAttachment.setFileUrl(fileUrl);
						tblAttachment.setFileName(fileName);
						tblAttachment.setRealFileName(realFileName);
						tblAttachment.setPrintNum(printNum);
						tblAttachment.setState(0);
						tblAttachmentList.add(tblAttachment);
						i++;
					}
					if(flag){
						tblAttachmentIndexService.save(tblAttachmentIndex,tblAttachmentList);
						json.put("success", "true");
						json.put("msg", id);
						
						List<?> list =userService.findUserNameRealNameByPrivilegeName("文件打印-打印");
						List<String> receiverList = new ArrayList<String>();
						if(null != list){
							for(Object obj:list){
								Object[] objs = (Object[]) obj;
								receiverList.add((String)objs[0]);
							}
						}
						
						if(null != receiverList && receiverList.size()>0){
							
							writeNotification(model.getDescribe(),receiverList);
						}
						
						writeLog("文件上传","文件描述："+model.getDescribe());
					}
				}else{
					//没有‘根目录’的权限
					//success = "false";
					json.put("msg","未设置文件保存路径！");
				}
			}else{
				//没有‘写’的权限
				//success = "false";
				json.put("msg","您没有上传文件权限！");
			}
		}else{
			json.put("msg","与服务器交互错误！");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	/**
	 * 文件上传(smb  jcifs.jar)
	 */
//	public void upload(){
//		Map<String,String> json  = new HashMap<String,String>();
//		json.put("success", "false");
//		json.put("msg", "");
//		
//		if(dataCheck()){
//			boolean write = (Boolean) ActionContext.getContext().getSession().get("write");
//			if(write){
//				String rootDirectory = tblAttachmentIndexService.getRootDirectory();
//				String smbRootDicectory = tblAttachmentIndexService.getSmbRootDirectory();
//				if(null != rootDirectory && !"".equals(rootDirectory) && null != smbRootDicectory
//						 && !"".equals(smbRootDicectory)){
//					//附件索引
//					TblAttachmentIndex tblAttachmentIndex = new TblAttachmentIndex();
//					String id = tblAttachmentIndexService.getKey();
//					tblAttachmentIndex.setId(id);
//					tblAttachmentIndex.setStudyNo(model.getStudyNo());
//					tblAttachmentIndex.setDescribe(model.getDescribe());
//					tblAttachmentIndex.setRemark(model.getRemark());
//					Date date = new Date();
//					String userName = null;
//					User user = getCurrentUser();
//					if(null != user){
//						userName = user.getUserName();
//					}
//					tblAttachmentIndex.setCreater(userName);
//					tblAttachmentIndex.setCreateTime(date);
//					tblAttachmentIndex.setState(0);
//					//附件列表
//					List<TblAttachment> tblAttachmentList = new ArrayList<TblAttachment>();
//					TblAttachment tblAttachment = null;
//					int i = 0;
//					InputStream in = null;
//			        SmbFileOutputStream out = null;
//			        
//			        boolean flag = true;// 如果 有异常， flag = false; break;
//					for(String fileName:uploadFileFileName){
//						tblAttachment = new TblAttachment();
//						tblAttachment.setId(tblAttachmentIndexService.getKey("TblAttachment"));
//						tblAttachment.setIndexId(id);
//						
//						String realFileName ="";
//						String fileUrl = rootDirectory+"/"+userName;
//						String realFileUrl = smbRootDicectory+"/"+userName;
//						int printNum = printNums.get(i);
//						realFileName = FileUtil.generateFileName(fileName);
//						try {
//							SmbFile smbFile = new SmbFile(realFileUrl);
//							
//							//SmbException    The network name cannot be found
//							if(!smbFile.isDirectory()){
//								smbFile.mkdirs();
//							}
//							SmbFile myfile = new SmbFile(realFileUrl+"/"+realFileName);
//							in = new java.io.BufferedInputStream (new FileInputStream(uploadFile.get(i)), 1024*16);
//							out = new SmbFileOutputStream(myfile);
//							byte[] buffer = new byte[1024*16];   
//				            while (in.read(buffer) > 0) {   
//				                out.write(buffer);   
//				            } 
//							
//							
//						} catch (MalformedURLException e) {
//							e.printStackTrace();
//						} catch (SmbException e) {
//							flag = false;
//							e.printStackTrace();
//							
//							json.put("msg","网络磁盘不存在或拒绝访问！");
//							break;
//						} catch (UnknownHostException e) {
//							e.printStackTrace();
//						} catch (IOException e) {
//							e.printStackTrace();
//						}finally{
//							if (null != in) {   
//				                try {
//									in.close();
//								} catch (IOException e) {
//									e.printStackTrace();
//								}   
//				            }   
//				            if (null != out) {   
//				                try {
//									out.close();
//								} catch (IOException e) {
//									e.printStackTrace();
//								}   
//				            } 
//						}
//						
//						tblAttachment.setFileUrl(fileUrl);
//						tblAttachment.setFileName(fileName);
//						tblAttachment.setRealFileName(realFileName);
//						tblAttachment.setPrintNum(printNum);
//						tblAttachment.setState(0);
//						tblAttachmentList.add(tblAttachment);
//						i++;
//					}
//					//无异常，
//					if(flag){
//						//保存
//						tblAttachmentIndexService.save(tblAttachmentIndex,tblAttachmentList);
//						json.put("success", "true");
//						json.put("msg", id);
//						
//						List<?> list =userService.findUserNameRealNameByPrivilegeName("文件打印-打印");
//						List<String> receiverList = new ArrayList<String>();
//						if(null != list){
//							for(Object obj:list){
//								Object[] objs = (Object[]) obj;
//								receiverList.add((String)objs[0]);
//							}
//						}
//						
//						if(null != receiverList && receiverList.size()>0){
//							
//							writeNotification(model.getDescribe(),receiverList);
//						}
//						
//						writeLog("文件上传","文件描述："+model.getDescribe());
//					}
//				}else{
//					//没有‘根目录’的权限
//					//success = "false";
//					json.put("msg","未设置文件保存路径！");
//				}
//			}else{
//				//没有‘写’的权限
//				//success = "false";
//				json.put("msg","您没有上传文件权限！");
//			}
//		}else{
//			json.put("msg","与服务器交互错误！");
//		}
//		String jsonStr = JsonPluginsUtil.beanToJson(json);
//		writeJson(jsonStr);
//	}
	
	/**
	 * 检查参数项
	 * @return
	 */
	private boolean dataCheck() {
		if(null != model.getStudyNo() && model.getStudyNo().getBytes().length>50){
			return false;
		}else if(null == model.getDescribe() || model.getDescribe().getBytes().length>200){
			return false;
		}else if(null != model.getRemark() && model.getRemark().getBytes().length>200){
			return false;
		}
//		private List<File> uploadFile;   
//		//文件名对应uploadFile+“FileName”，要不获取不到文件名  
//		private List<String> uploadFileFileName;    
//
//		private List<String> uploadFileContentType;//文件类型
//		
//		private List<Integer> printNums;
		
		if(null != printNums && printNums.size()>0 && 
			null != uploadFile && uploadFile.size()>0 && 
			null != uploadFileFileName && uploadFileFileName.size()>0 && 
			null != uploadFileContentType && uploadFileContentType.size()>0 ){
			
		}else{
			return false;
		}
		
		return true;
	}
	/**
	 * 下载
	 */
	public String download(){
		if(null != model.getId() && !"".equals(model.getId())){
			TblAttachment attchment = tblAttachmentIndexService.getTblAttachmentById(model.getId());
			if(null != attchment){
				//fileInput = new ByteArrayInputStream(attchment.getFileUrl()+"\\\\"+attchment.getRealFileName());
				File file = new File(attchment.getFileUrl()+"\\"+attchment.getRealFileName());
				try {
					fileInput = new FileInputStream(file);
				} catch (FileNotFoundException e) {
					
					e.printStackTrace();
					return "nofile";
				}
				fileName = attchment.getFileName();
//				System.out.println(request.getCharacterEncoding());
				try {
					fileName= java.net.URLEncoder.encode(fileName,request.getCharacterEncoding());
					fileName = new String(fileName.getBytes(request.getCharacterEncoding()), "ISO_8859_1"); 
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
	    return "download";  
	}
	
	
	/**
	 * 发送通知
	 * 
	 * @param
	 */
	private void writeNotification(String descibe,
			List<String> receiverList) {
		String sender = getCurrentRealName();
		// 当前时间
		//String currentDate = DateUtil.dateToString(new Date(),
		//		"yyyy年MM月dd日 HH时mm分");
		// 通知消息
		TblNotification tblNotification = new TblNotification();
		tblNotification.setMsgTitle("文件待打印提醒");
		String msgContent = sender+" 上传了一些文件(描述："+descibe+"),特此提醒";
		tblNotification.setMsgContent(msgContent);
		tblNotification.setMsgType(1);// 系统消息

		tblNotification.setSender(sender);

		tblNotification.setSendTime(new Date());
		tblNotificationService.save(tblNotification, receiverList);

	}
	/**
	 * 发送通知（单个文件）
	 * 
	 * @param
	 */
	private void writeNotification2(String fileName,
			List<String> receiverList) {
		String sender = getCurrentRealName();
		// 当前时间
		//String currentDate = DateUtil.dateToString(new Date(),
		//		"yyyy年MM月dd日 HH时mm分");
		// 通知消息
		TblNotification tblNotification = new TblNotification();
		tblNotification.setMsgTitle("文件已打印提醒");
		String msgContent = sender+" 打印了文件(名称："+fileName+"),特此提醒";
		tblNotification.setMsgContent(msgContent);
		tblNotification.setMsgType(1);// 系统消息
		
		tblNotification.setSender(sender);
		
		tblNotification.setSendTime(new Date());
		tblNotificationService.save(tblNotification, receiverList);
		
	}
	/**
	 * 发送通知（整个索引）
	 * 
	 * @param
	 */
	private void writeNotification3(String describe,
			List<String> receiverList) {
		String sender = getCurrentRealName();
		// 当前时间
		//String currentDate = DateUtil.dateToString(new Date(),
		//		"yyyy年MM月dd日 HH时mm分");
		// 通知消息
		TblNotification tblNotification = new TblNotification();
		tblNotification.setMsgTitle("文件已打印提醒");
		String msgContent = sender+" 打印了文件(描述："+describe+"),特此提醒";
		tblNotification.setMsgContent(msgContent);
		tblNotification.setMsgType(1);// 系统消息
		
		tblNotification.setSender(sender);
		
		tblNotification.setSendTime(new Date());
		tblNotificationService.save(tblNotification, receiverList);
		
	}
	/**
	 * 写日志
	 * @return
	 */
	private void writeLog(String operatType,String operatContent){
		//记录日志
		  TblLog tblLog = new TblLog();
		  tblLog.setSystemName(SystemMessage.getSystemName());//系统名称
		  tblLog.setSystemVersion(SystemMessage.getSystemVersion());//系统版本
		  tblLog.setOperatType(operatType);
		  tblLog.setOperatOject("文件打印");
		  tblLog.setOperator(getCurrentRealName());
		  tblLog.setOperatContent(operatContent);
		  tblLog.setOperatHost(SystemTool.getIPAddress(request));
		  tblLogService.save(tblLog);
	}
	

	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public TblAttachmentIndexService getTblAttachmentIndexService() {
		return tblAttachmentIndexService;
	}
	public void setTblAttachmentIndexService(
			TblAttachmentIndexService tblAttachmentIndexService) {
		this.tblAttachmentIndexService = tblAttachmentIndexService;
	}
	
	public List<File> getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(List<File> uploadFile) {
		this.uploadFile = uploadFile;
	}
	public List<String> getUploadFileFileName() {
		return uploadFileFileName;
	}
	public void setUploadFileFileName(List<String> uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}
	public List<String> getUploadFileContentType() {
		return uploadFileContentType;
	}
	public void setUploadFileContentType(List<String> uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
	}
	public InputStream getFileInput() {
		return fileInput;
	}
	public void setFileInput(InputStream fileInput) {
		this.fileInput = fileInput;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public List<Integer> getPrintNums() {
		return printNums;
	}
	public void setPrintNums(List<Integer> printNums) {
		this.printNums = printNums;
	}
	
	
	
}
