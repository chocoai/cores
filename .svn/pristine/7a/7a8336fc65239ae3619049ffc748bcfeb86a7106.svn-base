package com.lanen.view.action.contract;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.Json;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.User;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.model.contract.TblStudySchedule;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.contract.TblStudyScheduleService;
import com.lanen.util.DateUtil;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;

/**
 * 项目进度节点
 * @author 黄国刚
 *
 */
@Controller
@Scope("prototype")
public class TblStudyScheduleAction extends BaseAction<TblStudySchedule>{

	private static final long serialVersionUID = 7952968889030133328L;
	
	@Resource
	private TblStudyScheduleService tblStudyScheduleService;
	@Resource
	private TblESService tblESService;
	@Resource
	private TblESLinkService tblESLinkService;
	/**加载数据 (专题进度计划)*/
	public void loadList(){
		if(null != model.getStudyNo() && !"".equals(model.getStudyNo())){
			List<TblStudySchedule> list = tblStudyScheduleService.getListByStudyNo(model.getStudyNo());
			if(null != list){
				int index = -1;//SD任命
				int i = 0;
				for(TblStudySchedule obj:list){
					if(obj.getNodeName().equals("SD任命")){
						index = i;
						break;
					}
				}
				//移除SD任命
				if(index>-1){
					list.remove(index);
				}
				Map<String,Object> map = new HashMap<String,Object>();
				//专题进度计划提交       签字1:已签   0未签
				int isES = tblESLinkService.isESLink("TblStudySchedule",model.getStudyNo(),432);
				map.put("rows", list);
				map.put("es", isES == 1);
				String jsonStr = JsonPluginsUtil.beanToJson(map,"yyyy-MM-dd");
				writeJson(jsonStr);
			}
		}
	}
	
	/** 加载数据(专题进度)*/
	public void loadList2(){
		if(null != model.getStudyNo() && !"".equals(model.getStudyNo())){
			Map<String,Object> map = new HashMap<String,Object>();
			//专题进度计划提交       签字1:已签   0未签
			int isES = tblESLinkService.isESLink("TblStudySchedule",model.getStudyNo(),432);
			if(isES == 1){
				List<TblStudySchedule> list = tblStudyScheduleService.getListByStudyNo(model.getStudyNo());
				if(null != list){
					map.put("rows", list);
					map.put("es", isES == 1);
				}
			}else{
				map.put("rows", new ArrayList<TblStudySchedule>());
				map.put("es", isES == 1);
			}
			String jsonStr = JsonPluginsUtil.beanToJson(map,"yyyy-MM-dd");
			writeJson(jsonStr);
		}
	}
	
	/**保存或更新计划日期*/
	public void updatePlanDate(){
		Json json = new Json();
		if(null != model.getStudyNo() && null != model.getNodeName() && null != model.getPlanDate()){
			boolean isHasStudyNo = tblStudyScheduleService.isHasStudyNo(model.getStudyNo());
			if(!isHasStudyNo){
				//不存在，初始化，更新
				List<TblStudySchedule> list = tblStudyScheduleService.getListByStudyNo(model.getStudyNo());
				if(null != list){
					TblStudySchedule tblStudySchedule = null;
					for(TblStudySchedule obj:list){
						tblStudySchedule = new TblStudySchedule();
						tblStudySchedule.setId(tblStudyScheduleService.getKey());
						tblStudySchedule.setNodeName(obj.getNodeName());
						tblStudySchedule.setNodeSn(obj.getNodeSn());
						tblStudySchedule.setStudyNo(model.getStudyNo());
						if(obj.getNodeName().equals(model.getNodeName())){
							tblStudySchedule.setPlanDate(model.getPlanDate());
							writeLog("计划日期设置","专题进度计划","专题编号："+model.getStudyNo()+" 节点名称："+model.getNodeName()+" 设置日期："+DateUtil.dateToString(model.getPlanDate(), "yyyy-MM-dd"));
						}
//						//SD任命,设置   完成日期
						if(obj.getNodeSn() == 100){
							continue;
//							Date appointDate =tblAppointSDService.getappointDateByStudyNo(model.getStudyNo());
//							if(null != appointDate){
//								String dateStr = DateUtil.dateToString(appointDate, "yyyy-MM-dd");
//								Date date = DateUtil.stringToDate(dateStr, "yyyy-MM-dd");
//								tblStudySchedule.setActualDate(date);
//							}
						}
						tblStudyScheduleService.save(tblStudySchedule);
					}
					json.setSuccess(true);
					json.setMsg("日期设置成功");
				}
			}else{
				TblStudySchedule tblStudySchedule = 
					tblStudyScheduleService.getByStudyNoNodeName(model.getStudyNo(),model.getNodeName());
				if(null != tblStudySchedule){
					String oldDate = DateUtil.dateToString(tblStudySchedule.getPlanDate(), "yyyy-MM-dd");
					tblStudySchedule.setPlanDate(model.getPlanDate());
					tblStudyScheduleService.update(tblStudySchedule);
					json.setSuccess(true);
					json.setMsg("日期设置成功");
					writeLog("计划日期设置","专题进度计划","专题编号："+model.getStudyNo()+",节点名称："+model.getNodeName()
							+",原日期："+oldDate+" 设置日期："+DateUtil.dateToString(model.getPlanDate(), "yyyy-MM-dd"));
				}
			}
		}
		
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
		
	}
	
	/**计算其他项默认日期默认(以试验分组为基准点)*/
	public void setDefaultDate(){
		Json json = new Json();
		if(null != model.getStudyNo()){
			String studyNo = model.getStudyNo();
			boolean isHasStudyNo = tblStudyScheduleService.isHasStudyNo(studyNo);
			if(isHasStudyNo){
				TblStudySchedule tblStudySchedule = tblStudyScheduleService.getByStudyNoNodeName(studyNo, "试验分组");
				if(null != tblStudySchedule){
					//试验分组计划日期
					Date scgyDate = tblStudySchedule.getPlanDate();
					Map<String,String> map = tblStudyScheduleService.getMapByStudyNo(studyNo);
					if(null != map ){
						List<TblStudySchedule> list = tblStudyScheduleService.getListByStudyNo(studyNo);
						for(TblStudySchedule obj:list){
							System.out.println(obj.getNodeName());
							if(null == obj.getPlanDate() && null != map.get(obj.getNodeName())){
								int planDays = Integer.parseInt(map.get(obj.getNodeName())) ;
								Date computeDate = DateUtil.AddDate(scgyDate, planDays);
								obj.setPlanDate(computeDate);
								tblStudyScheduleService.update(obj);
								json.setSuccess(true);
								json.setMsg("设置日期成功");
								writeLog("计划日期设置","专题进度计划","专题编号："+model.getStudyNo()+",节点名称："+model.getNodeName()
										+",原日期： 设置日期："+DateUtil.dateToString(computeDate, "yyyy-MM-dd"));
							}
						}
						if(!json.isSuccess()){
							json.setMsg("未设置默认日期或其他项日期已设置");
						}
					}else{
						json.setMsg("其他项未设置默认日期！");	
					}
				}else{
					json.setMsg("与服务器交互错误！");
				}
			}else{
				json.setMsg("与服务器交互错误！");
			}
		}else{
			json.setMsg("与服务器交互错误！");
		}
		
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}

	/**项目进度计划提交*/
	public void submit(){
		Json json = new Json();
		if(null != model.getStudyNo()){
			String studyNo = model.getStudyNo();
			boolean isHasStudyNo = tblStudyScheduleService.isHasStudyNo(studyNo);
			if(!isHasStudyNo){
				//不存在，初始化，更新
				List<TblStudySchedule> list = tblStudyScheduleService.getListByStudyNo(studyNo);
				if(null != list){
					TblStudySchedule tblStudySchedule = null;
					for(TblStudySchedule obj:list){
						tblStudySchedule = new TblStudySchedule();
						tblStudySchedule.setId(tblStudyScheduleService.getKey());
						tblStudySchedule.setNodeName(obj.getNodeName());
						tblStudySchedule.setNodeSn(obj.getNodeSn());
						tblStudySchedule.setStudyNo(studyNo);
						//SD任命,设置   完成日期
						if(obj.getNodeSn() == 100){
							continue;
//							Date appointDate =tblAppointSDService.getappointDateByStudyNo(studyNo);
//							if(null != appointDate){
//								String dateStr = DateUtil.dateToString(appointDate, "yyyy-MM-dd");
//								Date date = DateUtil.stringToDate(dateStr, "yyyy-MM-dd");
//								tblStudySchedule.setActualDate(date);
//							}
						}
						tblStudyScheduleService.save(tblStudySchedule);
					}
					
					//签字   432 专题进度计划提交
					try{
						  saveES(432,"专题进度计划提交成功",studyNo);
						  json.setSuccess(true);
						  json.setMsg("专题进度计划提交成功");
					    }catch(Exception e){
					        json.setSuccess(false);
					        json.setMsg("与数据库交互异常");
					        System.out.println("执行失败，出错种类"+e.getMessage()+".");
					   }finally{ 
					        System.out.println("执行结束");
					   }
					
				}else{
					json.setMsg("与服务器交互错误！");	
				}
			}else{
				//签字    432 专题进度计划提交
				try{
				  saveES(432,"专题进度计划提交成功",studyNo);
				  json.setSuccess(true);
				  json.setMsg("专题进度计划提交成功");
			    }catch(Exception e){
			        json.setSuccess(false);
			        json.setMsg("与数据库交互异常");
			        System.out.println("执行失败，出错种类"+e.getMessage()+".");
			   }finally{ 
			        System.out.println("执行结束");
			   }   
			}
		}else{
			json.setMsg("与服务器交互错误！");
		}
		
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	//判断是否签字
	public void isSubmit(){
		if(null != model.getStudyNo() && !"".equals(model.getStudyNo())){
			//专题进度计划提交       签字1:已签   0未签
			int isES = tblESLinkService.isESLink("TblStudySchedule",model.getStudyNo(),432);
			if(isES == 1){
				writeJson("true");
			}else{
				writeJson("false");
			}
		}else{
			writeJson("false");
		}
	}
	/**是否设置实际日期*/
	public void isHasActualDate(){
		if(null != model.getStudyNo() && !"".equals(model.getStudyNo())
			&& null != model.getNodeName() && !"".equals(model.getNodeName())){
			//专题进度计划提交       签字1:已签   0未签
			int isES = tblESLinkService.isESLink("TblStudySchedule",model.getStudyNo(),432);
			if(isES == 1){
				TblStudySchedule tblStudySchedule = tblStudyScheduleService
				.getByStudyNoNodeName(model.getStudyNo(), model.getNodeName());
				if(null != tblStudySchedule && tblStudySchedule.getActualDate() != null){
					writeJson("true");
				}else{
					writeJson("false");
				}
			}else{
				writeJson("false");
			}
		}else{
			writeJson("false");
		}
	}
	
	/**保存或更新实际完成日期*/
	public void updateActualDate(){
		Json json = new Json();
		if(null != model.getStudyNo() && null != model.getNodeName() && null != model.getActualDate()){
			
				TblStudySchedule tblStudySchedule = 
					tblStudyScheduleService.getByStudyNoNodeName(model.getStudyNo(),model.getNodeName());
				if(null != tblStudySchedule){
					Date actualDate=tblStudySchedule.getActualDate();
					tblStudySchedule.setActualDate(model.getActualDate());
					tblStudyScheduleService.update(tblStudySchedule);
					if(actualDate!=null){
					   writeLog("实际完成日期设置","专题进度","专题编号："+model.getStudyNo()+" 节点名称："+tblStudySchedule.getNodeName()+" 原日期："+DateUtil.dateToString(actualDate,"yyyy-MM-dd")+" 更新后日期："+DateUtil.dateToString(tblStudySchedule.getActualDate(),"yyyy-MM-dd"));
					}else{
					   writeLog("实际完成日期设置","专题进度","专题编号："+model.getStudyNo()+" 节点名称："+tblStudySchedule.getNodeName()+" 设置日期："+DateUtil.dateToString(tblStudySchedule.getActualDate(),"yyyy-MM-dd"));
					}
					json.setSuccess(true);
					json.setMsg("日期设置成功");
				}
			
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	public void isHasNoActualDateBefore(){
		TblStudySchedule tblStudySchedule = 
			tblStudyScheduleService.getByStudyNoNodeName(model.getStudyNo(),model.getNodeName());
		int currentNodeSn=tblStudySchedule.getNodeSn();
		List<Map<Integer, Object>> list=tblStudyScheduleService.getMapBeforeNodeSn(model.getStudyNo(), currentNodeSn);
		Json json = new Json();
		if(list.size()<1){
			json.setSuccess(true);
		}else{
			for(Map<Integer, Object> map:list){
				Set<Integer> keySet=map.keySet();
				for(Integer k:keySet){
					if(map.get(k)!=null){
						json.setSuccess(true);
					}else{
						json.setSuccess(false);
						json.setMsg("请按节点顺序设置日期");
					}
				}
			}
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	//--------------------------------------------------------------------------------------
	/**
	 * 保存签字
	 */
	private void saveES(int esType,String esTypeDesc,String studyNo){
		//签名链接
		TblESLink esLink = new TblESLink();
		//电子签名
		TblES es = new TblES();
		User tempUser = getCurrentUser();
		
		es.setSigner(tempUser.getRealName());
        es.setEsType(esType);
        es.setEsTypeDesc(esTypeDesc);
        es.setDateTime(new Date());
		es.setEsId(tblESService.getKey("TblES"));
		tblESService.save(es);
		
		
        esLink.setTableName("TblStudySchedule");
		esLink.setDataId(studyNo);
		esLink.setTblES(es);
		esLink.setEsType(esType);
        esLink.setEsTypeDesc(esTypeDesc);
        esLink.setRecordTime(new Date());
		esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
		tblESLinkService.save(esLink);
		
		writeLog("专题进度计划提交","专题进度计划","专题编号："+studyNo);
	}
	/**
	 * 写日志
	 * @return
	 */
	private void writeLog(String operatType,String operatOject,String operatContent){
		//记录设备登记日志
		  TblLog tblLog = new TblLog();
		  tblLog.setSystemName(SystemMessage.getSystemName());//系统名称
		  tblLog.setSystemVersion(SystemMessage.getSystemVersion());//系统版本
		  tblLog.setOperatType(operatType);
		  tblLog.setOperatOject(operatOject);
		  tblLog.setOperator(getCurrentRealName());
		  tblLog.setOperatContent(operatContent);
		  tblLog.setOperatHost(SystemTool.getIPAddress(request));
		  tblLogService.save(tblLog);
	}
	
}
