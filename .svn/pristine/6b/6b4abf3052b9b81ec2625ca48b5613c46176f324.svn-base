package com.lanen.view.action.contract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.model.contract.TblAppointSD;
import com.lanen.model.contract.TblContract;
import com.lanen.model.contract.TblNotification;
import com.lanen.model.contract.TblStudyItem;
import com.lanen.model.contract.TblStudyItemHis;
import com.lanen.model.contract.TblTestItemHis;
import com.lanen.model.studyplan.DictStudyType;
import com.lanen.model.studyplan.DictTestItemType;
import com.lanen.service.UserService;
import com.lanen.service.clinicaltest.TblLogService;
import com.lanen.service.contract.TblContractService;
import com.lanen.service.contract.TblNotificationService;
import com.lanen.service.contract.TblStudyItemService;
import com.lanen.service.contract.TblTestItemService;
import com.lanen.service.studyplan.DictStudyTypeService;
import com.lanen.service.studyplan.DictTestItemTypeService;
import com.lanen.util.CopyUtil;
import com.lanen.util.DateUtil;
import com.lanen.util.StringUtil;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;

/**
 * 委托项目Action
 * @author 黄国刚
 *
 */
@Controller
@Scope("prototype")
public class TblStudyItemAction  extends BaseAction<TblStudyItem>{

	private static final long serialVersionUID = -3778449706641177628L;

	/**
	 * 委托项目编辑原因
	 */
	private String studyitemEditRsn;
	@Resource
	private DictTestItemTypeService dictTestItemTypeService;
	@Resource
	private TblNotificationService tblNotificationService;
	@Resource
	private DictStudyTypeService dictStudyTypeService;
	@Resource
	private TblStudyItemService tblStudyItemService;
	@Resource
	private TblContractService tblContractService;
	@Resource
	private TblTestItemService tblTestItemService;
	@Resource
	private UserService userService;
	@Resource
	private TblLogService tblLogService;
	
	
	private String studyNos;
	private String studyNames;
	private String studyTypeCodes;
	private String animaltypes;
	private String tiCode;	//供试品编号
	private String animalTypeId;	//课题类别主键
	private String finishDates;
	
	/**检查课题编号的唯一性*/
	public void checkStudyNo(){
		if(null!=model.getStudyNo() && !"".equals(model.getStudyNo())){
			boolean isExist = tblStudyItemService.isExistByStudyNo(model.getStudyNo());
			if(!isExist){
				writeJson("true");
			}else{
				writeJson("false");
			}
		}else{
			writeJson("false");
		}
	}
	
	/**判断是否任命 SD*/
	public void hasSD(){
		if(null!=model.getId() && !"".equals(model.getId())){
			String id = model.getId();
			if(id.startsWith("s")){
				id= id.substring(1);
			}
			boolean isExist = tblStudyItemService.isHasSDById(id);
			if(isExist){
				writeJson("true");
			}else{
				writeJson("false");
			}
		}else{
			writeJson("true");
		}
	}
	
	/**课题类别dialog上  供试品类别下拉框*/
	public void loadTestItemList(){
		List<DictTestItemType> list = dictTestItemTypeService.getAll();
		if(null!=list && list.size()>0){
			List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
			Map<String,String> map =null;
			for(DictTestItemType obj:list){
				map =  new HashMap<String,String>();
				map.put("id", obj.getTiCode());
				map.put("text", obj.getTiType());
				mapList.add(map);
			}
			String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
			writeJson(jsonStr);
		}else{
			writeJson("");
		}
	}
	
	/**课题类别dialog上  供试品类别下拉框 带空白选项*/
	public void loadTestItemAndNOList(){
		List<DictTestItemType> list = dictTestItemTypeService.getAll();
		if(null!=list && list.size()>0){
			List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
			Map<String,String> map =null;
			map =  new HashMap<String,String>();
			map.put("id", "-1");
			map.put("text", "&nbsp;");
			mapList.add(map);
			for(DictTestItemType obj:list){
				map =  new HashMap<String,String>();
				map.put("id", obj.getTiCode());
				map.put("text", obj.getTiType());
				mapList.add(map);
			}
			String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
			writeJson(jsonStr);
		}else{
			writeJson("");
		}
	}
	
	/**课题类别dialog上  课题类别列表（部分）*/
	public void loadStudyTypePartList(){
		List<DictStudyType>  objList=null;
		
		if(null != model.getTiNo() && !"".equals(model.getTiNo())){
			tiCode = tblTestItemService.getTiCodeByTiNo(model.getTiNo());
		}
		
		if(tiCode!=null && !"".equals(tiCode)){
			objList = dictStudyTypeService.getByTiCode(tiCode);
		}else{
			objList=dictStudyTypeService.getAll();
		}
		//list排序
		if(tiCode.equals("02")||tiCode.equals("03") ){
			Collections.sort(objList,new Comparator<DictStudyType>(){
				public int compare(DictStudyType o1,
						DictStudyType o2) {
					if(o1.getStudyCode()== null || o1.getStudyCode().equals("")){
						return 1;
					}else 	if(o2.getStudyCode()== null || o2.getStudyCode().equals("")){
						return -1;
					}else{
						int result = o1.getStudyCode().compareTo(o2.getStudyCode());
				        return result;   
					}
				}
				
			});
		}
		String[] _nory_changes ={"tiCode","studyTypeCode","studyName","studyPeriod","studyPeriodUnit","studyCode"};
		String jsonStr = JsonPluginsUtil.beanListToJson(objList, _nory_changes , true);
		writeJson(jsonStr);
	}
	
	
	/**课题类别dialog上  课题类别列表（全部）*/
	public void loadStudyTypeList(){
		List<DictStudyType>  objList=dictStudyTypeService.getAll();
		String[] _nory_changes ={"tiCode","studyTypeCode","studyName","studyPeriod","studyPeriodUnit"};
		String jsonStr = JsonPluginsUtil.beanListToJson(objList, _nory_changes , true);
		writeJson(jsonStr);
	}
	
	/**动物种类下拉框*/
	public void aniamlType(){
		List<Map<String,String>> animalTypeMapList =null;
		//处理动物种类下拉框
		animalTypeMapList = tblStudyItemService.findDictAnimalTypeOrderByOrderNo();
		String jsonStr = JsonPluginsUtil.beanListToJson(animalTypeMapList);
		writeJson(jsonStr);
	}
	
	/**动物类别  品系  联动*/
	public void getAnimalStrain(){
		List<Map<String,String>> animalStrainMapList = null;
		if(null!=animalTypeId && !animalTypeId.isEmpty()){
			animalStrainMapList=  tblStudyItemService.findDictAnimalStrainByTypeId(animalTypeId);
			String jsonStr = JsonPluginsUtil.beanListToJson(animalStrainMapList);
			writeJson(jsonStr);
		}
	}
	/**SD推荐人下拉框*/
	public void loadSDManagerList(){
		//处理SD负责人下拉框
		List<?> qaList =userService.findUserNameRealNameByPrivilegeName("SD");
		List<Map<String,String>> sdMapList = new ArrayList<Map<String,String>>();
		Map<String,String> map = null;
		map = new HashMap<String,String>();
		map.put("id","-1");
		map.put("text","&nbsp;");
		sdMapList.add(map);
		if(null!=qaList && qaList.size()>0){
			for(Object obj:qaList){
				map = new HashMap<String,String>();
				Object[] objs = (Object[]) obj;
				map.put("id",(String)objs[1]);
				map.put("text",(String)objs[1]);
				sdMapList.add(map);
			}
		}
		
		String jsonStr = JsonPluginsUtil.beanListToJson(sdMapList);
		writeJson(jsonStr);
	}
	
	public void  loadSDList(){
		//处理SD负责人下拉框
		List<?> qaList =userService.findUserNameRealNameByPrivilegeName("SD");
		List<Map<String,String>> sdMapList = new ArrayList<Map<String,String>>();
		Map<String,String> map = null;
		if(null!=qaList && qaList.size()>0){
			for(Object obj:qaList){
				map = new HashMap<String,String>();
				Object[] objs = (Object[]) obj;
				map.put("id",(String)objs[1]);
				map.put("text",(String)objs[1]);
				sdMapList.add(map);
			}
		}
		String jsonStr = JsonPluginsUtil.beanListToJson(sdMapList);
		writeJson(jsonStr);
	}
	
	/**添加保存*/
	public void addSave() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		//检查是否有写的权限或自己的，且当前合同是否可编辑
		TblContract tblContract = tblContractService.getByCode(model.getContractCode());
		if(null != tblContract){
			if(tblContract.getContractState() == 0 || tblContract.getContractState()==2){
				boolean writeAll = (Boolean) ActionContext.getContext().getSession().get("write"); 
				String reader = getCurrentRealName();
				if((tblContract.getContractState() == 0 && reader.equals(tblContract.getOperator()))
					||(tblContract.getContractState() == 2 &&(reader.equals(tblContract.getOperator()) || writeAll ))){
					if(dataCheck(model)){
						String id = tblStudyItemService.getKey();
						TblStudyItem tblStudyItem = new TblStudyItem();
						tblStudyItem.setId(id);
						tblStudyItem.setContractCode(model.getContractCode());
						tblStudyItem.setTiNo(model.getTiNo());
						tblStudyItem.setStudyTypeCode(model.getStudyTypeCode());
						tblStudyItem.setStudyName(model.getStudyName());
						tblStudyItem.setStudyNo(model.getStudyNo());
						tblStudyItem.setGlpFlag(model.getGlpFlag());
						tblStudyItem.setSdManager(model.getSdManager());
						tblStudyItem.setRemark(model.getRemark());
						tblStudyItem.setAnimalAge(model.getAnimalAge());
						tblStudyItem.setNumMale(model.getNumMale());
						tblStudyItem.setNumFemale(model.getNumFemale());
						tblStudyItem.setStudyState(0);
						tblStudyItem.setAnimalType(model.getAnimalType());
						tblStudyItem.setAnimalStrain(model.getAnimalStrain());
						tblStudyItem.setState(0);
						try{
							 //保存
							 tblStudyItemService.save(tblStudyItem);
							 writeLog("委托项目添加","合同编号："+model.getContractCode()+" 供试品编号："+model.getTiNo()+" 委托项目名称"+model.getStudyName());
							 map.put("id", id);
							 map.put("success", true);
						}catch(Exception e){
							 map.put("success", false);
							 map.put("msg", "与数据库交互异常");
							 System.out.println("执行失败，出错种类"+e.getMessage()+".");
						}finally{ 
							System.out.println("执行结束");
						} 
						
					}
				}else{
//					json.setMsg("无权限此操作");
				}
			}else{
//				json.setMsg("当前合同不可编辑");
			}
		}
		
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	/**添加多个*/
	public void multiAdd() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		//1.检查是否有写的权限或自己的，且当前合同是否可编辑
		TblContract tblContract = tblContractService.getByCode(model.getContractCode());
		if(null != tblContract){
			if(tblContract.getContractState() == 0 || tblContract.getContractState()==2){
				boolean writeAll = (Boolean) ActionContext.getContext().getSession().get("write"); 
				String reader = getCurrentRealName();
				if((tblContract.getContractState() == 0 && reader.equals(tblContract.getOperator()))
						||(tblContract.getContractState() == 2 &&(reader.equals(tblContract.getOperator()) || writeAll ))){
					if(dataCheck(model)){
						//2.判断课题编号 是否有重的,是否已经存在,和  三数组长度是否一致
						String[] studyNoArray = studyNos.split(",");
						String[] studyNameArray = studyNames.split(",");
						String[] studyTypeCodeArray = studyTypeCodes.split(",");
						String[] animaltypeArray = animaltypes.split(",");
						String[] finishDatesArray = finishDates.split(",");
						if(null != studyNoArray && studyNoArray.length>0 && studyNoArray.length == studyNameArray.length && studyNameArray.length== studyTypeCodeArray.length){
							boolean error = false;
							String studyNo="";
							String studyName="";
							String animal="";
							for(int i = 0; i< studyNoArray.length;i++){
								for(int j = i+1;j<studyNoArray.length;j++){
									if(studyNoArray[i].equals(studyNoArray[j])){
										error = true;
										studyNo = studyNoArray[i];
										break;
									}
								}
								if(error){
									break;
								}
								//判断数据库是否存在
								error = tblStudyItemService.isExistByStudyNo(studyNoArray[i]);
								if(error){
									studyNo =studyNoArray[i];
									break;
								}
								//3.判断
								//error = tblStudyItemService.isExistByTiNoStudyTypeCode(model.getTiNo(),studyTypeCodeArray[i]);
								if(animaltypeArray[i].equals("-1")){
									error = tblStudyItemService.isExistByTiNostudyName(model.getTiNo(),studyNameArray[i],null);
									if(error){
										studyName =studyNameArray[i];
										break;
									}
								}else{
									error = tblStudyItemService.isExistByTiNostudyName(model.getTiNo(),studyNameArray[i],animaltypeArray[i]);
									if(error){
										studyName =studyNameArray[i];
										break;
									}
								}
								
								for(int j = 0; j< studyNoArray.length;j++){
									if(studyNameArray[i].equals(studyNameArray[j])&& (i != j) && animaltypeArray[i].equals(animaltypeArray[j]) ){
										error = true;
										break;
									}
								}
								
								if(error){
									studyName =studyNameArray[i];
									break;
								}
								DictStudyType  dictStudyType =dictStudyTypeService.getById(studyTypeCodeArray[i]);
								if(dictStudyType.getAnimalHave() == 1 && animaltypeArray[i].equals("-1")){
									animal = "have";
									studyNo =studyNoArray[i];
									error = true;
									break;
								}
								
							}
							if(!error){
								System.out.println(studyNoArray.length);
								for(int i = 0;i<studyNoArray.length;i++){
									String id = tblStudyItemService.getKey();
									TblStudyItem tblStudyItem = new TblStudyItem();
									tblStudyItem.setId(id);
									tblStudyItem.setContractCode(model.getContractCode());
									tblStudyItem.setTiNo(model.getTiNo());
									tblStudyItem.setStudyTypeCode(studyTypeCodeArray[i]);
									tblStudyItem.setStudyName(studyNameArray[i]);
									tblStudyItem.setStudyNo(studyNoArray[i]);
									if(!finishDatesArray[i].equals("-1")){
										tblStudyItem.setFinishDate(DateUtil.stringToDate(finishDatesArray[i], "yyyy-MM-dd"));
									}else{
										tblStudyItem.setFinishDate(null);
									}
							
									tblStudyItem.setGlpFlag(model.getGlpFlag());
									tblStudyItem.setSdManager(model.getSdManager());
									tblStudyItem.setRemark(model.getRemark());
									tblStudyItem.setAnimalAge(model.getAnimalAge());
									tblStudyItem.setNumMale(model.getNumMale());
									tblStudyItem.setNumFemale(model.getNumFemale());
									tblStudyItem.setStudyState(0);
									if( !animaltypeArray[i].equals("-1")){
										 tblStudyItem.setAnimalType(animaltypeArray[i]);
									}else{
										 tblStudyItem.setAnimalType("");
									}
									tblStudyItem.setAnimalStrain("");
									tblStudyItem.setState(0);
									
									try{
										 //保存
										tblStudyItemService.save(tblStudyItem);
										writeLog("委托项目添加","合同编号："+model.getContractCode()+" 供试品编号："+model.getTiNo()+" 委托项目名称"+studyNameArray[i]);
										map.put("id", id);
										map.put("success", true);
									}catch(Exception e){
										 map.put("success", false);
										 map.put("msg", "与数据库交互异常");
										 System.out.println("执行失败，出错种类"+e.getMessage()+".");
									}finally{ 
										System.out.println("执行结束");
									} 
								}
							}else{
								if(animal.equals("have") && !studyNo.equals("") ){
									map.put("msg","请选择项目编号："+studyNo+" 的动物种类!");
								}else if(!studyNo.equals("")){
									map.put("msg","项目编号："+studyNo+" 已存在!");
								}else{
									map.put("msg","该供试品已添加委托项目： "+studyName+"!");
								}
								
							}
						}else{
							map.put("msg","数据传输有误,请刷新后重试");
						}
						
						
					}else{
						map.put("msg","数据传输有误,请刷新后重试");
					}
				}else{
					map.put("msg","无权限此操作");
				}
			}else{
				map.put("msg","当前合同不可编辑");
			}
		}
		
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	/**委托项目编辑保存*/
	public void editSave(){
	 Map<String,Object> map = new HashMap<String,Object>();
	 //检查是否有写的权限或自己的，且当前合同是否可编辑
	 TblContract tblContract = tblContractService.getByCode(model.getContractCode());
	 if(null != tblContract){
		 if(tblContract.getContractState() == 0 || tblContract.getContractState()==2){
				boolean writeAll = (Boolean) ActionContext.getContext().getSession().get("write"); 
				String reader = getCurrentRealName();
				if((tblContract.getContractState() == 0 && reader.equals(tblContract.getOperator()))
					||(tblContract.getContractState() == 2 &&(reader.equals(tblContract.getOperator()) || writeAll ))){
					
					if(dataCheck(model)){
						String id 	= model.getId();
						TblStudyItem tblStudyItem = tblStudyItemService.getById(id);
						
						TblStudyItem tblStudyItem2 = new TblStudyItem();
						try {
							CopyUtil.Copy(tblStudyItem, tblStudyItem2);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						
						tblStudyItem.setId(id);
						tblStudyItem.setContractCode(model.getContractCode());
						tblStudyItem.setTiNo(model.getTiNo());
						tblStudyItem.setStudyTypeCode(model.getStudyTypeCode());
						tblStudyItem.setStudyName(model.getStudyName());
						if(tblStudyItem.getState() == 0){
							tblStudyItem.setStudyNo(model.getStudyNo());
						}
						tblStudyItem.setGlpFlag(model.getGlpFlag());
						tblStudyItem.setSdManager(model.getSdManager());
						tblStudyItem.setRemark(model.getRemark());
						tblStudyItem.setAnimalAge(model.getAnimalAge());
						tblStudyItem.setNumMale(model.getNumMale());
						tblStudyItem.setNumFemale(model.getNumFemale());
						tblStudyItem.setStudyState(0);
						tblStudyItem.setAnimalType(model.getAnimalType());
						tblStudyItem.setAnimalStrain(model.getAnimalStrain());
						tblStudyItem.setFinishDate(model.getFinishDate());
						
						if(tblContract.getContractState() != 2 || tblStudyItem.getState() != 1){
							try{
								tblStudyItemService.update(tblStudyItem);
								writeLog("委托项目编辑","合同编号："+model.getContractCode()+" 供试品编号："+model.getTiNo()+" 委托项目名称："+model.getStudyName());
								map.put("id", id);
								map.put("success", true);
								if(tblContract.getContractState()==2 && tblStudyItem.getSdState() == 1){
									//TODO
									List<String> receiverList = new ArrayList<String>();
									receiverList.add(tblStudyItem.getSdCode());
									writeNotification(tblStudyItem,receiverList);
								}
							}catch(Exception e){
								map.put("success", false);
								map.put("msg", "与数据库交互异常");
								System.out.println("执行失败，出错种类"+e.getMessage()+".");
							}finally{ 
								System.out.println("执行结束");
							} 
						}else{
							//合同再编辑状态下  编辑委托项目
							
							TblStudyItemHis tblStudyItemHis = new TblStudyItemHis();
							try {
								CopyUtil.Copy(tblStudyItem2, tblStudyItemHis);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							
							tblStudyItemHis.setId(null);
							tblStudyItemHis.setOldId(tblStudyItem.getId());
							tblStudyItemHis.setOperateRsn(studyitemEditRsn);
							tblStudyItemHis.setOperateTime(new Date());
							tblStudyItemHis.setOperateType("编辑");
							String realName = getCurrentRealName();
							try{
								tblStudyItemService.update(tblStudyItem,tblStudyItemHis,realName);
								writeLog("委托项目编辑","合同编号："+model.getContractCode()+" 供试品编号："+model.getTiNo()+" 委托项目名称："+model.getStudyName());
								map.put("id", id);
								map.put("success", true);
								if(tblContract.getContractState()==2 && tblStudyItem.getSdState() == 1){
									//TODO
									List<String> receiverList = new ArrayList<String>();
									receiverList.add(tblStudyItem.getSdCode());
									writeNotification(tblStudyItem,receiverList);
								}
							}catch(Exception e){
								map.put("success", false);
								map.put("msg", "与数据库交互异常");
								System.out.println("执行失败，出错种类"+e.getMessage()+".");
							}finally{ 
								System.out.println("执行结束");
							}
						}
					}
				}else{
//					json.setMsg("无权限此操作");
				}
			}else{
//				json.setMsg("当前合同不可编辑");
			}
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	
	/**删除委托项目*/
	public void delStudyItem(){
		String delId =model.getId().replace("s","");
		Map<String,Object> map = new HashMap<String,Object>();
		
		TblStudyItem tblStudyItem = tblStudyItemService.getById(delId);
		
		//检查是否有写的权限或自己的，且当前合同是否可编辑
		TblContract tblContract = tblContractService.getByCode(tblStudyItem.getContractCode());
		if(null != tblContract){
			if(tblContract.getContractState() == 0 || tblContract.getContractState()==2){
				boolean writeAll = (Boolean) ActionContext.getContext().getSession().get("write"); 
				String reader = getCurrentRealName();
				if((tblContract.getContractState() == 0 && reader.equals(tblContract.getOperator()))
					||(tblContract.getContractState() == 2 &&(reader.equals(tblContract.getOperator()) || writeAll ))){
					if(!tblStudyItemService.isHasSDById(delId)){
						try{
							tblStudyItemService.delete(delId);
							writeLog("委托项目删除","合同编号："+tblStudyItem.getContractCode()+" 供试品编号："+tblStudyItem.getTiNo()+" 委托项目名称"+tblStudyItem.getStudyName());
							map.put("success",true);
						}catch(Exception e){
							 map.put("success", false);
							 map.put("msg", "与数据库交互异常");
							 System.out.println("执行失败，出错种类"+e.getMessage()+".");
						}finally{ 
							System.out.println("执行结束");
						} 
						
					}
				}else{
//					json.setMsg("无权限此操作");
				}
			}else{
//				json.setMsg("当前合同不可编辑");
			}
		}
		
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	
	/**根据传过来的Id查询实体*/
	public void selectStudyItem(){
		TblStudyItem tblStudyItem = tblStudyItemService.getById(model.getId().replace("s",""));
		if(null !=tblStudyItem){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id",tblStudyItem.getId());
			map.put("contractCode",tblStudyItem.getContractCode()); //合同编号
			map.put("tiNo",tblStudyItem.getTiNo());        // 供试品编码
			map.put("studyTypeCode",tblStudyItem.getStudyTypeCode());// 项目编码
			map.put("studyName",tblStudyItem.getStudyName());// 项目名称
			map.put("studyNo",tblStudyItem.getStudyNo());// 课题编号
			map.put("glpFlag",tblStudyItem.getGlpFlag());// 是否GLP
			map.put("remark",tblStudyItem.getRemark());// 备注
			map.put("sdCode",tblStudyItem.getSd());// SD编号
			map.put("sd",tblStudyItem.getSd());// SD姓名
			map.put("qaCode",tblStudyItem.getQaCode());// QA编号
			map.put("qa",tblStudyItem.getQa());// QA姓名
			map.put("sdManager",tblStudyItem.getSdManager());// SD推荐人
			map.put("sdState",tblStudyItem.getSdState());// SD任命状态
			map.put("qaState",tblStudyItem.getQaState());	// QA任命状态
			map.put("sdAppointDate",tblStudyItem.getSdAppointDate());// SD任命时间
			map.put("qaAppointDate",tblStudyItem.getQaAppointDate());// QA任命时间
			map.put("fmCode",tblStudyItem.getFmCode());// FM编号
			map.put("fm",tblStudyItem.getFm());// FM姓名
			map.put("studyState",tblStudyItem.getStudyState());// 课题状态
			map.put("animalType",tblStudyItem.getAnimalType());// 动物种类
			map.put("animalStrain",tblStudyItem.getAnimalStrain());// 动物品系
			map.put("numMale",tblStudyItem.getNumMale());// 动物个数（雄）
			map.put("numFemale",tblStudyItem.getNumFemale());// 动物个数（雌）
			map.put("animalAge",tblStudyItem.getAnimalAge());// 动物年龄
			map.put("finishDate",DateUtil.dateToString(tblStudyItem.getFinishDate(), "yyyy-MM-dd"));// 动物年龄
			DictStudyType dictStudyType =dictStudyTypeService.getById(tblStudyItem.getStudyTypeCode());
			if(null != dictStudyType ){
				map.put("animalHave", dictStudyType.getAnimalHave());
			}	
			
			TblContract tblContract = tblContractService.getByContractCode(tblStudyItem.getContractCode());
			map.put("contractState", tblContract.getContractState());
			map.put("state", tblStudyItem.getState());
			map.put("success",true);
			String jsonStr = JsonPluginsUtil.beanToJson(map);
			writeJson(jsonStr);
		}
		
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
		  tblLog.setOperatOject("委托项目");
		  tblLog.setOperator(getCurrentRealName());
		  tblLog.setOperatContent(operatContent);
		  tblLog.setOperatHost(SystemTool.getIPAddress(request));
		  tblLogService.save(tblLog);
	}

	/**
	 * 发送通知（单个文件）
	 * 
	 * @param
	 */
	private void writeNotification(TblStudyItem tblStudyItem,
			List<String> receiverList) {
		String sender = getCurrentRealName();
		// 当前时间
		String currentDate = DateUtil.dateToString(new Date(),
				"yyyy年MM月dd日 HH时mm分");
		// 通知消息
		TblNotification tblNotification = new TblNotification();
		tblNotification.setMsgTitle("委托项目编辑提醒");
		String msgContent = sender+" 于 "+currentDate+" 编辑了委托项目（合同编号："+model.getContractCode()+" 供试品编号："+model.getTiNo()+" 委托项目名称："+model.getStudyName()+"),特此提醒";
		tblNotification.setMsgContent(msgContent);
		tblNotification.setMsgType(1);// 系统消息
		
		tblNotification.setSender(sender);
		
		tblNotification.setSendTime(new Date());
		tblNotificationService.save(tblNotification, receiverList);
		
	}
	
	/**
	 * 数据检查（非空，长度），false：空
	 * @param obj
	 * @return boolean
	 */
	public boolean dataCheck(TblStudyItem obj){
		boolean flag = true;
		if(!StringUtil.checkStingAndLength(obj.getContractCode(), 50)){
			flag = false;
		}else if(!StringUtil.checkStingAndLength(obj.getTiNo(), 50)){
			flag = false;
//		}else if(!StringUtil.checkStingAndLength(obj.getStudyTypeCode(), 50)){
//			flag = false;
//		}else if(!StringUtil.checkStingAndLength(obj.getStudyName(), 200)){
//			flag = false;
		}
//		else if(!StringUtil.checkStingAndLength(obj.getStudyNo(), 50)){
//			flag = false;
//		}
		return flag;
	}
	
	/**专题编号*/
	public void studyItemTablemultiLoadDate(){
		//老的专题编号
        //List<Map<String, Object>> loadData = tblStudyItemService.getByStudyCodesAndTiNo(studyTypeCodes,model.getTiNo());
		//新的专题编号
		//农药化学品   X X X X - X X X- X X - X X X
		//          申请单年份-申请单序号-样品内部ID-专题代号
		List<Map<String, Object>> loadData = tblStudyItemService.getByStudyCodesAndTiNo2(studyTypeCodes,model.getTiNo());
		String jsonStr = JsonPluginsUtil.beanListToJson(loadData);
		writeJson(jsonStr);
	}
	
	public String getTiCode() {
		return tiCode;
	}

	public void setTiCode(String tiCode) {
		this.tiCode = tiCode;
	}

	public String getAnimalTypeId() {
		return animalTypeId;
	}

	public void setAnimalTypeId(String animalTypeId) {
		this.animalTypeId = animalTypeId;
	}
	
	public String getStudyNos() {
		return studyNos;
	}
	
	public String getStudyNames() {
		return studyNames;
	}
	
	public String getStudyTypeCodes() {
		return studyTypeCodes;
	}
	
	public void setStudyNos(String studyNos) {
		this.studyNos = studyNos;
	}
	
	public void setStudyNames(String studyNames) {
		this.studyNames = studyNames;
	}
	
	public void setStudyTypeCodes(String studyTypeCodes) {
		this.studyTypeCodes = studyTypeCodes;
	}
	
	public String getAnimaltypes() {
		return animaltypes;
	}
	
	public void setAnimaltypes(String animaltypes) {
		this.animaltypes = animaltypes;
	}

	public String getFinishDates() {
		return finishDates;
	}

	public void setFinishDates(String finishDates) {
		this.finishDates = finishDates;
	}

	public String getStudyitemEditRsn() {
		return studyitemEditRsn;
	}

	public void setStudyitemEditRsn(String studyitemEditRsn) {
		this.studyitemEditRsn = studyitemEditRsn;
	}

}
