package com.lanen.view.action.schdeule;

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
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.clinicaltest.TblLog;
import com.lanen.model.schedule.TblAnimalHouse;
import com.lanen.model.schedule.TblAnmialHouseJson;
import com.lanen.model.schedule.TblResManager;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.schdeule.TblAnimalHouseService;
import com.lanen.service.schdeule.TblResManagerService;
import com.lanen.util.SystemMessage;
import com.lanen.util.SystemTool;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class TblAnimalHouseAction extends BaseAction<TblAnimalHouse> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	@Resource
	private TblAnimalHouseService tblAnimalHouseService;

	private int resKind;// 类型

	private String parentId;// 父级Id

	// 签名链接表Service
	@Resource
	private TblESLinkService tblESLinkService;
	@Resource
	private TblESService tblESService;
	
	@Resource
	private TblResManagerService tblResManagerService;

	private String oldresName;

	private String rid;

	/** 列表 */
	public String list() throws Exception {
		ActionContext.getContext().put("rid", rid);
		return "list";
	}
    /**树表格*/
	public void loadList2() {
		List<TblAnimalHouse> objList = tblAnimalHouseService.getAll();
		List<TblAnmialHouseJson> list = new ArrayList<TblAnmialHouseJson>();
		for (TblAnimalHouse houes : objList) {
			TblAnmialHouseJson houseJson = new TblAnmialHouseJson();
			houseJson.setId(houes.getId());
			houseJson.setResKind(houes.getResKind());
			houseJson.setResName(houes.getResName());
			houseJson.set_parentId(houes.getParentId());
			if (houes.getResKind() == 1) {
				houseJson.setState("open");
			} else if (houes.getResKind() == 2) {
				houseJson.setState("closed");
			}
			houseJson.setIconCls("icon-space");
			list.add(houseJson);
		}

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", list);
		map.put("rid", rid);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);

	}
	/**树表格*/
	public void loadList3() {
		//根据负责人管理的区域显示
		User user = (User) ActionContext.getContext().getSession().get("user");
		List<TblResManager> managerList =  tblResManagerService.getByResManager(user.getUserName());
		List<String> residlist = new ArrayList<String>();
		for( TblResManager manager:managerList){
			residlist.add(manager.getResId());
		}
		
		List<TblAnimalHouse> objList = tblAnimalHouseService.getAllByResIdlist(residlist);
		
		List<TblAnmialHouseJson> list = new ArrayList<TblAnmialHouseJson>();
		for (TblAnimalHouse houes : objList) {
			TblAnmialHouseJson houseJson = new TblAnmialHouseJson();
			houseJson.setId(houes.getId());
			houseJson.setResKind(houes.getResKind());
			houseJson.setResName(houes.getResName());
			houseJson.set_parentId(houes.getParentId());

			if (houes.getResKind() == 1) {
				houseJson.setState("open");
			} else if (houes.getResKind() == 2) {
				houseJson.setState("closed");
			}

			houseJson.setIconCls("icon-space");
			list.add(houseJson);
		}

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", list);
		map.put("rid", rid);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);

	}
	
	/**树表格*/
	public void loadList4() {
		TblAnimalHouse house = tblAnimalHouseService.getById(rid);
		TblAnimalHouse house1 = tblAnimalHouseService.getById(house.getParentId());
		List<TblAnimalHouse> houselist = new ArrayList<TblAnimalHouse>();
		houselist.add(house);
		houselist.add(house1);
		List<TblAnimalHouse> houselist1 = tblAnimalHouseService.getParentId(rid);
		houselist.addAll(houselist1);
		List<TblAnmialHouseJson> list = new ArrayList<TblAnmialHouseJson>();
		for (TblAnimalHouse houes : houselist) {
			TblAnmialHouseJson houseJson = new TblAnmialHouseJson();
			houseJson.setId(houes.getId());
			houseJson.setResKind(houes.getResKind());
			houseJson.setResName(houes.getResName());
			houseJson.set_parentId(houes.getParentId());

			if (houes.getResKind() == 1) {
				houseJson.setState("open");
			} else if (houes.getResKind() == 2) {
				houseJson.setState("closed");
			}

			houseJson.setIconCls("icon-space");
			list.add(houseJson);
		}

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", list);
		map.put("rid", rid);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);

	}

	/** 增加页面 */
	public String addUI() throws Exception {
		String parentId = model.getParentId();
		if (null != parentId && !parentId.equals("")) {
			ActionContext.getContext().put("parentId", model.getParentId());
			TblAnimalHouse animalHouse = tblAnimalHouseService.getById(parentId);
			int resKind = animalHouse.getResKind();
			if (resKind != 3) {
				ActionContext.getContext().put("resKind1", resKind + 1);
				int res = resKind + 1;
				if (res == 1) {
					ActionContext.getContext().put("resKindName", "建筑");
				} else if (res == 2) {
					ActionContext.getContext().put("resKindName", "楼层（区域）");
					ActionContext.getContext().put("parentname", animalHouse.getResName());
				} else if (res == 3) {
					String pid = animalHouse.getParentId();
					TblAnimalHouse house = tblAnimalHouseService.getById(pid);
					ActionContext.getContext().put("parentname",house.getResName() + " "+ animalHouse.getResName());
					ActionContext.getContext().put("resKindName", "房间");
				}
			} else {
				ActionContext.getContext().put("resKind1", 3);
				ActionContext.getContext().put("resKindName", "房间");
			}
		} else {
			ActionContext.getContext().put("resKind1", 1);
			ActionContext.getContext().put("resKindName", "建筑");
		}
		return "addUI";
	}

	public void CheckResKind() {
		Map<String,Object> map = new HashMap<String,Object>();
		if (null != model.getParentId() && !"".equals(model.getParentId())) {
			TblAnimalHouse animalHouse = tblAnimalHouseService.getById(model.getParentId());
			int resKind = animalHouse.getResKind();
			if (resKind != 3) {
				map.put("success", true);
			} else {
				map.put("success", false);
			}
		} else {
			map.put("success", true);
		}
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}

	/** 异步检查名称是否存在 */
	public void checkresName() {
		if (null != model.getResName() && !"".equals(model.getResName())) {
			String pid = model.getParentId();
			boolean isExist;
			if (null != pid && !pid.equals("")) {
				isExist = tblAnimalHouseService.isExistByResNameAndPid(model.getResName(), pid);
			} else {
				isExist = tblAnimalHouseService.isExistByResName(model.getResName());
			}
			if (!isExist) {
				writeJson("true");
			} else {
				writeJson("false");
			}
		} else {
			writeJson("false");
		}
	}

	/** 检查名称是否存在（自己除外） */
	public void checkOtherresName() {
		if (null != model.getResName() && !"".equals(model.getResName())
				&& null != oldresName && !"".equals(oldresName)) {
			if (oldresName.trim().equals(model.getResName().trim())) {
				writeJson("true");
			} else {
				String pid = model.getParentId();
				boolean isExist;
				if (null != pid && !pid.equals("")) {
					isExist = tblAnimalHouseService.isExistByResNameAndPid(model.getResName(), pid);
				} else {
					isExist = tblAnimalHouseService.isExistByResName(model.getResName());
				}
				if (!isExist) {
					writeJson("true");
				} else {
					writeJson("false");
				}
			}
		} else {
			writeJson("false");
		}
	}

	/** 删除(异步 json) */
	public void delete() {
		Json json = new Json();
		if (null != model.getId() && !"".equals(model.getId())) {
			TblAnimalHouse animalHouse = tblAnimalHouseService.getById(model.getId());
			animalHouse.setValidFlag("1");
			List<TblAnimalHouse> houselist = tblAnimalHouseService.getParentId(model.getId());
			List<TblAnimalHouse> validList = new ArrayList<TblAnimalHouse>();
			if (houselist.size() > 0 && null != houselist) {
				for (TblAnimalHouse house : houselist) {
					house.setValidFlag("1");
					validList.add(house);
				}
			}
			validList.add(animalHouse);

			User tempUser = (User) ActionContext.getContext().getSession().get("user");
			String realName = tempUser.getRealName();
			// 签名链接
			TblESLink esLink = new TblESLink();
			// 电子签名
			TblES es = new TblES();
			es.setEsType(411);
			es.setSigner(realName);
			es.setEsTypeDesc("删除资源签字确认");
			es.setDateTime(new Date());
			String esId = tblESService.getKey("TblES");
			es.setEsId(esId);
			esLink.setTableName("TblAnimalHouse");
			esLink.setDataId(model.getId());
			esLink.setTblES(es);
			esLink.setEsType(411);
			esLink.setEsTypeDesc("删除资源签字确认");
			esLink.setRecordTime(new Date());
			esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
			try{
			    tblESService.save(es);
				tblESLinkService.save(esLink);
			    tblAnimalHouseService.updateAll(validList);
			    json.setSuccess(true);
			    json.setMsg("删除成功");
				// 日志录入
				writeLog("签字", "删除资源签字确认，签字");
			}catch(Exception e){
				 json.setSuccess(false);
				 json.setMsg("与数据库交互异常");
				 System.out.println("执行失败，出错种类"+e.getMessage()+".");
			}finally{ 
					 System.out.println("执行结束");
			} 

		} else {
			json.setMsg("删除失败");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}

	/** 增加 （保存） */
	public String add() throws Exception {
		TblAnimalHouse house = new TblAnimalHouse();
		house.setId(tblAnimalHouseService.getKey());
		int orderNo = tblAnimalHouseService.getNextOrderNo();
		house.setOrderNo(orderNo);
		house.setParentId(model.getParentId());
		house.setResKind(model.getResKind());
		house.setResName(model.getResName());
		house.setValidFlag("0");
		ActionContext.getContext().put("rid", house.getId());

		User tempUser = (User) ActionContext.getContext().getSession().get("user");
		String realName = tempUser.getRealName();
		// 签名链接
		TblESLink esLink = new TblESLink();
		// 电子签名
		TblES es = new TblES();
		es.setEsType(409);
		es.setSigner(realName);
		es.setEsTypeDesc("添加资源签字确认");
		es.setDateTime(new Date());
		String esId = tblESService.getKey("TblES");
		es.setEsId(esId);
		
		esLink.setTableName("TblAnimalHouse");
		esLink.setDataId(house.getId());
		esLink.setTblES(es);
		esLink.setEsType(409);
		esLink.setEsTypeDesc("添加资源签字确认");
		esLink.setRecordTime(new Date());
		esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
		try{
			tblESService.save(es);
		    tblESLinkService.save(esLink);
		    tblAnimalHouseService.save(house);
			// 日志录入
			writeLog("签字", "添加资源签字确认，签字");
		}catch(Exception e){
			 System.out.println("执行失败，出错种类"+e.getMessage()+".");
		}finally{ 
				 System.out.println("执行结束");
		} 
		return "toList";
	}

	/**
	 * 写日志
	 * 
	 * @return
	 */
	private void writeLog(String operatType, String operatContent) {
		// 记录设备登记日志
		TblLog tblLog = new TblLog();
		tblLog.setSystemName(SystemMessage.getSystemName());// 系统名称
		tblLog.setSystemVersion(SystemMessage.getSystemVersion());// 系统版本
		tblLog.setOperatType(operatType);
		tblLog.setOperatOject("动物房设置");
		User user = (User) ActionContext.getContext().getSession().get("user");
		if (null != user) {
			tblLog.setOperator(user.getRealName());
		}
		tblLog.setOperatContent(operatContent);
		tblLog.setOperatHost(SystemTool.getIPAddress(request));
		tblLogService.save(tblLog);
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		if (null != model.getId() && !"".equals(model.getId())) {
			TblAnimalHouse obj = tblAnimalHouseService.getById(model.getId());
			int resKind = obj.getResKind();
			if (resKind == 1) {
				ActionContext.getContext().put("resKindName", "建筑");
			} else if (resKind == 2) {
				ActionContext.getContext().put("resKindName", "楼层（区域）");
				TblAnimalHouse house = tblAnimalHouseService.getById(obj
						.getParentId());
				ActionContext.getContext()
						.put("parentname", house.getResName());
			} else if (resKind == 3) {
				ActionContext.getContext().put("resKindName", "房间");
				TblAnimalHouse house = tblAnimalHouseService.getById(obj
						.getParentId());
				TblAnimalHouse house1 = tblAnimalHouseService.getById(house
						.getParentId());
				ActionContext.getContext().put("parentname",
						house1.getResName() + " " + house.getResName());
			}

			ActionContext.getContext().getValueStack().push(obj);
			return "editUI";
		} else {
			return "toList";
		}
	}

	/** 修改 */
	public String edit() throws Exception {
		TblAnimalHouse house = tblAnimalHouseService.getById(model.getId());
		ActionContext.getContext().put("rid", model.getId());
		User tempUser = (User) ActionContext.getContext().getSession().get(
				"user");
		String realName = tempUser.getRealName();
		// 签名链接
		TblESLink esLink = new TblESLink();
		// 电子签名
		TblES es = new TblES();
		es.setEsType(410);
		es.setSigner(realName);
		es.setEsTypeDesc("修改资源签字确认");
		es.setDateTime(new Date());
		String esId = tblESService.getKey("TblES");
		es.setEsId(esId);
	

		esLink.setTableName("TblAnimalHouse");
		esLink.setDataId(model.getId());
		esLink.setTblES(es);
		esLink.setEsType(410);
		esLink.setEsTypeDesc("修改资源签字确认");
		esLink.setRecordTime(new Date());
		esLink.setLinkId(tblESLinkService.getKey("TblESLink"));

		try{
			tblESService.save(es);
			tblESLinkService.save(esLink);
			house.setResName(model.getResName());
			tblAnimalHouseService.update(house);
		    //日志录入
			writeLog("签字", "修改资源签字确认，签字");
		}catch(Exception e){
			 System.out.println("执行失败，出错种类"+e.getMessage()+".");
		}finally{ 
				 System.out.println("执行结束");
		} 
		
		return "toList";
	}



	/**
	 * 根据ID查询组合的名称
	 * 
	 * @return
	 */
	public void selectResName() {
		List<String> rids = new ArrayList<String>();
		if(rid.contains(",")){
			String[] strarray = rid.split(",");		
			for (int j = 0; j < strarray.length; j++) {
				rids.add(strarray[j]);
			}
		}else{
			rids.add(rid);
		}
		String resname = "";
		String resids = "";
		Map<String,Object> map = new HashMap<String,Object>();
		for(String id:rids){
			TblAnimalHouse obj = tblAnimalHouseService.getById(id);
			int resKind = obj.getResKind();
			if (resKind == 1) {
				if(resname != ""){
					resname = resname+",";
				}
				if(resids != ""){
					resids = resids+",";
				}
				resname =  obj.getResName();
			} else if (resKind == 2) {
				if(resname != ""){
					resname = resname+",";
				}
				if(resids != ""){
					resids = resids+",";
				}
				TblAnimalHouse house = tblAnimalHouseService.getById(obj
						.getParentId());
				resname = house.getResName() + " " + obj.getResName();
			} else if (resKind == 3) {
				TblAnimalHouse house = tblAnimalHouseService.getById(obj
						.getParentId());
				TblAnimalHouse house1 = tblAnimalHouseService.getById(house
						.getParentId());
				if(resname != ""){
					resname = resname+",";
				}
				if(resids != ""){
					resids = resids+",";
				}
				resname =resname + house1.getResName() + " " + house.getResName()
				+ "  " + obj.getResName();
				resids = resids+obj.getId();
			}
			
		}
		map.put("resname",resname);
		map.put("success", true);
		map.put("resid", resids);
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	
	public int getResKind() {
		return resKind;
	}

	public void setResKind(int resKind) {
		this.resKind = resKind;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getOldresName() {
		return oldresName;
	}

	public void setOldresName(String oldresName) {
		this.oldresName = oldresName;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

}
