package com.lanen.view.action.arp;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.ComboTreeModel;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Area;
import com.lanen.model.AreaJson;
import com.lanen.model.Employee;
import com.lanen.service.arp.AnimaltypeService;
import com.lanen.service.arp.AreaService;
import com.lanen.service.arp.EmployeeService;
import com.lanen.service.arp.IndividualService;
import com.lanen.util.Constant;
import com.lanen.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class AreaAction extends BaseAction<Area> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 719479901935536249L;
	@Resource
	private AreaService areaService;
	@Resource
	private AnimaltypeService animaltypeService;
	@Resource
	private EmployeeService employeeService;
	@Resource
	private IndividualService individualService;
	private InputStream fileInput;
	private String fileName;

	private String areaname = "治疗区";

	private String rows;
	private String page;
	private List<Map<String, Object>> dataSourceList;
	private Map<String, Object> paraMap;

	/**
	 * 加载所有的区域及区域下的房间（加载成树形下拉框）
	 */
	public void loadAreaCom() {
		List<Area> alist = areaService.getAllArea();

		List<ComboTreeModel> list = new ArrayList<ComboTreeModel>();
		List<ComboTreeModel> children = new ArrayList<ComboTreeModel>();
		ComboTreeModel ctm = null;
		ctm = new ComboTreeModel();
		ctm.setId(-1 + "");
		ctm.setText("全部");
		ctm.setIconCls("icon-space");
		list.add(ctm);
		for (Area area : alist) {
			if (area.getBlongarea() == null) {
				ctm = new ComboTreeModel();
				ctm.setId(area.getId() + "");
				ctm.setText(area.getAreaname());
				ctm.setIconCls("icon-space");
				children=new ArrayList<ComboTreeModel>();
				for (Area area2 : alist) {
					System.out.println(area2.getBlongarea());
					System.out.println(area.getId());
					
					if (area2.getBlongarea() != null) {
						System.out.println(area.getId() == area2.getBlongarea()
								.longValue());
					}
					if (area2.getBlongarea() != null
							&& (area.getId() == area2.getBlongarea().longValue())) {
						ComboTreeModel comboTreeModel = new ComboTreeModel();
						comboTreeModel.setId(area2.getId() + "");
						comboTreeModel.setText(area2.getAreaname());
						comboTreeModel.setIconCls("icon-space");
						children.add(comboTreeModel);
					}
				}
				if (children.size() > 0) {
					ctm.setState("closed");
					ctm.setChildren(children);
					list.add(ctm);
				}
			}
		}

		System.out.println("----json--");
		String json = JSONArray.fromObject(list).toString();// 转化为JSON
		System.out.print(json);
		writeJson(json);

	}

	/**
     * 
     */
	public void loadAreaComNo() {
		List<Area> alist = areaService.getAllArea();

		List<ComboTreeModel> list = new ArrayList<ComboTreeModel>();
		List<ComboTreeModel> children = new ArrayList<ComboTreeModel>();
		ComboTreeModel ctm = null;
		for (Area area : alist) {
			if (area.getBlongarea() == null) {
				ctm = new ComboTreeModel();
				ctm.setId(area.getId() + "");
				ctm.setText(area.getAreaname());
				ctm.setIconCls("icon-space");
				for (Area area2 : alist) {
					System.out.println(area2.getBlongarea());
					System.out.println(area.getId());
					if (area2.getBlongarea() != null) {
						System.out.println(area.getId() == area2.getBlongarea()
								.longValue());
					}
					if (area2.getBlongarea() != null
							&& (area.getId() == area2.getBlongarea()
									.longValue())) {
						ComboTreeModel comboTreeModel = new ComboTreeModel();
						comboTreeModel.setId(area2.getId() + "");
						comboTreeModel.setText(area2.getAreaname());
						comboTreeModel.setIconCls("icon-space");
						children.add(comboTreeModel);
					}
				}
				if (children.size() > 0) {
					ctm.setState("closed");
					ctm.setChildren(children);
					list.add(ctm);
				}
			}
		}

		System.out.println("----json--");
		String json = JSONArray.fromObject(list).toString();// 转化为JSON
		System.out.print(json);
		writeJson(json);

	}

	public String list() {
		return "list";
	}

	// 主页面数据加载
	public void loadList() {
		List<?> list = areaService.getAllAreaList();
		List<AreaJson> jsonList = new ArrayList<AreaJson>();
		for (Object obj : list) {
			Object[] objs = (Object[]) obj;
			AreaJson json = new AreaJson();
			json.setId(Long.valueOf((Integer) objs[0] + ""));// id
			if ((Integer) objs[2] != null && (Integer) objs[2] != 0) {
				json.set_parentId(Long.valueOf((Integer) objs[2] + ""));// 父类id
			}

			json.setAreaname((String) objs[1]);// 地区名称
			// if(a.getAnimaltype()!=null){
			// Animaltype at=animaltypeService.getById(a.getAnimaltype());
			// if(at!=null){
			// json.setAnimalType(at.getName());
			// }
			// }
			json.setAnimalType((String) objs[3]);
			if ((Integer) objs[2] == null || (Integer) objs[2] == 0) {
				json.setState("closed");
			}
			json.setIconCls("icon-space");
			json.setRoompinxi((Integer) objs[4]);
			json.setKeeperName((String) objs[5]);
			// json.setKeeper(a.getKeeper());
			// json.setBoss(a.getBoss());
			json.setBossName((String) objs[6]);
			json.setRemarks((String) objs[7]);
			// json.setReader((String)objs[5]);
			json.setReaderName((String) objs[9]);
			json.setVeterinarianName((String) objs[10]);
			jsonList.add(json);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", jsonList);
		map.put("total", jsonList.size());
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
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
	 * 获取所有的父区域ID，NAME
	 */
	public void getPareaNameId() {
		List<Map<String, Object>> list = areaService.getAllPareaIdName();
		String jsonStr = JsonPluginsUtil.beanListToJson(list);
		writeJson(jsonStr);
	}

	/**
	 * 获取所有的动物类型表的动物名字和ID
	 */
	public void getAllAnimaltypeIdName() {
		List<Map<String, Object>> list = animaltypeService
				.getAllAnimalTypeIdName();
		String jsonStr = JsonPluginsUtil.beanListToJson(list);
		writeJson(jsonStr);
	}

	/**
	 * 检查区域（房间）名是否已存在
	 */
	public void checkAreaName() {
		if (null != model.getAreaname() && !"".equals(model.getAreaname())) {
			boolean isExist = areaService.isExistName(model.getAreaname());
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
	 * 添加区域（房间）
	 */
	public void add() {
		Map<String, Object> map = new HashMap<String, Object>();
		Area a = new Area();
		if (model.getAreaname() != null && !"".equals(model.getAreaname())) {
			a.setAreaname(model.getAreaname());
			a.setBlongarea(model.getBlongarea());
			a.setAnimaltype(model.getAnimaltype());
			a.setKeeper(model.getKeeper());
			a.setBoss(model.getBoss());
			a.setReader(model.getReader());
			a.setVeterinarian(model.getVeterinarian());
			a.setRemarks(model.getRemarks());
			a.setDeleted(0);
			Date date = new Date();
			a.setCreatetime(date);
			a.setLastmodifytime(date);
			Employee user = (Employee) ActionContext.getContext().getSession()
					.get("user");
			a.setCreated_by(user.getId());
			a.setModified_by(user.getId());
			areaService.save(a);
			map.put("success", true);
			map.put("msg", "添加成功");
			map.put("id", a.getId());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	/**
	 * 编辑前数据加载
	 */
	public void toEdit() {
		if (model.getId() != null) {
			Area a = areaService.getById(model.getId());
			String jsonStr = JsonPluginsUtil.beanToJson(a, "yyyy-MM-dd");
			writeJson(jsonStr);
		}
	}

	/**
	 * 编辑后保存
	 */
	public void editSave() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null) {
			Area a = areaService.getById(model.getId());
			a.setAreaname(model.getAreaname());
			a.setBlongarea(model.getBlongarea());
			a.setAnimaltype(model.getAnimaltype());
			a.setKeeper(model.getKeeper());
			a.setBoss(model.getBoss());
			a.setReader(model.getReader());
			a.setVeterinarian(model.getVeterinarian());
			a.setRemarks(model.getRemarks());
			Employee user = (Employee) ActionContext.getContext().getSession()
					.get("user");
			a.setModified_by(user.getId());
			Date date = new Date();
			a.setLastmodifytime(date);
			areaService.update(a);
			map.put("success", true);
			map.put("msg", "编辑成功");
			map.put("id", a.getId());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	/**
	 * 区域（房间）删除
	 */
	public void delArea() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null) {
			Area a = areaService.getById(model.getId());
			a.setDeleted(1);
			areaService.update(a);
			if (a.getBlongarea() == null || a.getBlongarea() == 0) {
				List<Area> list = areaService.getListByBlongId(a.getId());
				if (list != null && list.size() > 0) {
					for (Area area : list) {
						area.setDeleted(1);
						areaService.update(area);
					}
				}
			}
			map.put("success", true);
			map.put("msg", "删除成功");
			String jsonStr = JsonPluginsUtil.beanToJson(map);
			writeJson(jsonStr);
		}
	}

	/**
	 * 获取区域下所有房间的ID和name
	 */
	public void getAllRoomIdName() {
		Long blongArea = model.getBlongarea();
		List<Map<String, Object>> list = areaService
				.getAllRoomIdName(blongArea);
		String jsonStr = JsonPluginsUtil.beanListToJson(list);
		writeJson(jsonStr);
	}

	/**
	 * 获取区域下所有房间的ID和name(添加ID为-1，name为不限的选项)
	 */
	public void getAllRoomIdName1() {
		Long blongArea = model.getBlongarea();
		List<Map<String, Object>> list = areaService
				.getAllRoomIdName(blongArea);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", -1);
		map.put("text", "不限");
		list.add(map);
		String jsonStr = JsonPluginsUtil.beanListToJson(list);
		writeJson(jsonStr);
	}

	/**
	 * 通过区域（房间）名，得到区域（房间）
	 */
	public void getAreaByAreaname() {
		String areaname = model.getAreaname();
		Area area = areaService.getAreaByName(areaname);
		String jsonStr = JsonPluginsUtil.beanToJson(area, "yyyy-MM-dd");
		writeJson(jsonStr);
	}

	/**
	 * 得到所有的饲养员
	 */
	public void getAllKeeper() {
		List<Long> list = areaService.getAllKeeper();
		List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
		for (Long keeper : list) {
			String name = getEmpName(keeper);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", keeper);
			map.put("text", name);
			list2.add(map);
		}
		String jsonStr = JsonPluginsUtil.beanListToJson(list2);
		writeJson(jsonStr);
	}

	/**
	 * 加载盘点数据
	 */
	public void loadListByKeeper() {
		Long keeper = model.getKeeper();
		Map<String, Object> map = new HashMap<String, Object>();;
		if (keeper!=null&&!"".equals(keeper)) {
			List<Area> list = areaService.getIdsByKeeper(keeper);
			List<AreaJson> list2 = new ArrayList<AreaJson>();
			if (list != null) {
				for (Area a : list) {
					AreaJson json = areaService.getAreanameAndMonkeyCount(a
							.getId());
					list2.add(json);
				}
			}
			
			map.put("rows", list2);
			map.put("total", list2.size());
		}
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}

	/**
	 * 加载所有的楼栋
	 */
	public void getAllBuilding() {

	}

	public void loadListTreatRoom() {
		List<Map<String, String>> mapList = areaService.getTreatRoom(areaname);
		String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(jsonStr);
	}

	/**
	 * 动物明细报表
	 * 
	 * @return
	 */
	public String listAnimal() {
		return "animalRecord";
	}

	public void animalByJson() {
		Long keeper = model.getKeeper();
		Long blongarea = model.getBlongarea();
		// List<Area> list = areaService.getIdsByKeeper(keeper);
		Map<String,Object> listmap = areaService.getRoomByKeeper(rows, page, keeper,blongarea);
		List<Map> listrows =  (List<Map>) listmap.get("rows");
		List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
		if (listrows != null) {
			for (Map map1 : listrows) {
				Map<String,Object> map = new HashMap<String, Object>();
				List<?> json = areaService.getAnimal(Long.parseLong(map1.get("id")+""),keeper);
				map.put("roomid", map1.get("id"));
				map.put("roomname", map1.get("areaname"));
				map.put("monkeylist", json);
				list2.add(map);
			}
		}
		Map map = new HashMap<String, Object>();
		map.put("rows", list2);
		map.put("total", listmap.get("total"));
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}

	public String reportAnimal() {
		return "animalReport";
	}

	public String animalByReport() {
		paraMap = new HashMap<String, Object>();
		URL logoImage=null;
		logoImage = this.getClass().getResource("logo.jpg");
		paraMap.put("logoImage", logoImage);
		paraMap.put("company", Constant.COMPANY_NAME);
		paraMap.put("title", Constant.TITLE_ANIMAL_DETAILED);
		Long keeper = model.getKeeper();
		Long blongarea = model.getBlongarea();
		paraMap.put("keeper", employeeService.getById(keeper).getName());
		/*if (!"".equals(blongarea) && blongarea != null) {
			paraMap.put("blongarea", areaService.getById(blongarea)
					.getAreaname());
		} else {
			// 获取该饲养员所有饲养区.
			paraMap.put("blongarea", "所有区域");
		}*/
		paraMap.put("printdate",DateUtil.dateToString(new Date(), "yyyy-MM-dd"));
		fileName = "AnimalDetailed"+ DateUtil.dateToString(new Date(), "yyyyMMddsss");
		List<AreaJson> list = areaService.getRoomByKeeperAndArea(keeper,blongarea);
		List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
		Set set=new HashSet();
		if (list != null) {
			for (AreaJson a : list) {
				set.add(a.getBlongareaName());
				Map<String, Object> map = new HashMap<String, Object>();
				List<?> json = areaService.getAnimal(a.getId(),keeper);
				// 假设一个房间不超过多少30只动物.

				for (int i = 0; i < 25; i++) {
					map = new HashMap<String, Object>();
					map.put("roomname", a.getAreaname());
					if (i < 10) {
						map.put("index", "00" + i);
					}
					if (i >= 10) {
						map.put("index", "0" + i);
					}
					// map.put("count",json.size() );每个房间的数量
					if (i < json.size()) {
						map.put("monkeyid", json.get(i));
					}
					list2.add(map);
				}
				map.put("roomname", a.getAreaname());
				map.put("index", "合计");
				map.put("monkeyid", json.size());
				list2.add(map);

			}
		}
		String blongareaName="";
		for( Iterator   it = set.iterator();  it.hasNext(); )  
		{    
			blongareaName+=it.next().toString()+",";
		}
		if (!"".equals(blongarea) && blongarea != null) {
			paraMap.put("blongarea", areaService.getById(blongarea).getAreaname());
		} else {
			// 获取该饲养员所有饲养区.
			paraMap.put("blongarea", blongareaName);
		}
		dataSourceList = list2;
		return "animalByReport";
	}

	public void loadListRoomNo() {
		/*
		 * List<Map<String, Object>> mapList = areaService .getRoom();
		 */// 房间名有中文，在Combobox转换中用Object只会显示Id不显示name
		List<Map<String, String>> mapList = areaService.getRoom();
		String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(jsonStr);
	}

	/**
	 * 饲养统计
	 * 
	 * @param fileInput
	 */
	public String listArea() {
		return "areaRecord";
	}

	public void areaByJson() {
		Map map = areaService.getAllRoomName(rows, page);
		String strJson = JsonPluginsUtil.beanToJson(map, "yyyy-MM-dd");
		writeJson(strJson);
	}

	public String reportArea() {
		return "areaReport";
	}
	/**
	 * 饲养数量报表
	 * @return
	 */
	public String areaByReport() {
		Date nows=new Date();
		List<AreaJson> list = areaService.getAllRoomName();
		paraMap = new HashMap<String, Object>();
		dataSourceList = new ArrayList<Map<String, Object>>();
		
		
		
		for (AreaJson aj : list) {
			Map<String,Object> MonkeyMap = new HashMap<String, Object>();
			MonkeyMap.put("male", aj.getMaleMonkeyCount());
			MonkeyMap.put("female", aj.getFemaleMonkeyCount());
			MonkeyMap.put("yucheng", aj.getYuchengMonkeyCount());
			MonkeyMap.put("cub", aj.getCubMonkeyCount());
			MonkeyMap.put("room", aj.getRoom());
			MonkeyMap.put("dong", aj.getDong());
			dataSourceList.add(MonkeyMap);
			
		}
		
		//统计.
		List<?> leavebreastMonkeys=individualService.getLeavebreastCount(nows);
		List<?> deathMonkeys=individualService.getDeathCount(nows);
		List<?> allMonkeys=individualService.getMonkeyCount();
		for(Object ob:leavebreastMonkeys){
			Object[]objs=(Object[])ob;
			paraMap.put("sumItem1", objs[0]);
			paraMap.put("sumItem2", objs[1]);
			paraMap.put("sumItem3", objs[2]);
			paraMap.put("sumItem4", objs[3]);
			
		}
		for(Object ob:deathMonkeys){
			Object[]objs=(Object[])ob;
			paraMap.put("sumItem5", objs[0]);
			paraMap.put("sumItem6", objs[1]);
			paraMap.put("sumItem7", objs[2]);
			paraMap.put("sumItem8", objs[3]);
			
		}
		for(Object ob:allMonkeys){
			Object[]objs=(Object[])ob;
			double d9=(Double)objs[0];
			double d10=(Double)objs[1];
			double d11=(Double)objs[2];
			double d12=(Double)objs[3];
			int sum9=(int)d9;
			int sum10=(int)d10;
			int sum11=(int)d11;
			int sum12=(int)d12;
			paraMap.put("sumItem9", sum9);
			paraMap.put("sumItem10", sum10);
			paraMap.put("sumItem11", sum11);
			paraMap.put("sumItem12", sum12);
			
		}
		fileName = "AreaAnimalCountDetailed"+ DateUtil.dateToString(new Date(), "yyyyMMddsss");

		Employee e = (Employee) ActionContext.getContext().getSession().get("user");
		String username = e.getName();
		List<String> name=animaltypeService.getAnimalTypes();
		String animalType="";
		for(int i=0;i<name.size();i++){
			if (i!=(name.size()-1)) {
				animalType += name.get(i) + ",";
			}else{
				animalType += name.get(i) ;
			}
		}
		//String animalType = Constant.animalType;
		
		URL logoImage = null;
		logoImage = this.getClass().getResource("logo.jpg");

		paraMap.put("printName", username);
		paraMap.put("animalType", animalType);
		paraMap.put("inventoryDate",DateUtil.dateToString(new Date(), "yyyy-MM-dd"));
		paraMap.put("logoImage", logoImage);
		
		String title=(nows.getYear()+1900)+"年"+(nows.getMonth()+1)+"月份饲养数量统计表";
		paraMap.put("title", title);
		return "areaByReport";
	}
	/*public String areaByReport() {
		List<AreaJson> list = areaService.getAllRoomName();
		paraMap = new HashMap<String, Object>();
		dataSourceList = new ArrayList<Map<String, Object>>();
		for (AreaJson aj : list) {
			Map maleMonkeyMap = new HashMap<String, Object>();
			Map femaleMonkeyMap = new HashMap<String, Object>();
			Map yuchengMonkeyMap = new HashMap<String, Object>();
			Map cubMonkeyMap = new HashMap<String, Object>();

			for (int i = 0; i < 5; i++) {
				maleMonkeyMap.put("monkeyType", "公猴");
				maleMonkeyMap.put("monkeyCount", aj.getMaleMonkeyCount());
				maleMonkeyMap.put("room", aj.getRoom());
				maleMonkeyMap.put("dong", aj.getDong());
				dataSourceList.add(maleMonkeyMap);
				femaleMonkeyMap.put("monkeyType", "母猴");
				femaleMonkeyMap.put("monkeyCount", aj.getMaleMonkeyCount());
				femaleMonkeyMap.put("room", aj.getRoom());
				femaleMonkeyMap.put("dong", aj.getDong());
				dataSourceList.add(femaleMonkeyMap);
				yuchengMonkeyMap.put("monkeyType", "育成");
				yuchengMonkeyMap.put("monkeyCount", aj.getYuchengMonkeyCount());
				yuchengMonkeyMap.put("room", aj.getRoom());
				yuchengMonkeyMap.put("dong", aj.getDong());
				dataSourceList.add(yuchengMonkeyMap);
				cubMonkeyMap.put("monkeyType", "仔猴");
				cubMonkeyMap.put("monkeyCount", aj.getCubMonkeyCount());
				cubMonkeyMap.put("room", aj.getRoom());
				cubMonkeyMap.put("dong", aj.getDong());
				dataSourceList.add(cubMonkeyMap);
			}
		}
		List<?> mCount = individualService.getMonkeyCountByType(3, 0);// 公猴
		List<?> fCount = individualService.getMonkeyCountByType(3, 1);// 母猴
		List<?> yCount = individualService.getMonkeyCountByType(2, 10);// 育成猴
		List<?> cubCount = individualService.getMonkeyCountByType(1, 10);// 仔猴
		Date nows = new Date();
		// 断奶
		List<?> lmCount = individualService.getLeavebreastCountByType(nows, 3,
				0);// 公猴
		List<?> lfCount = individualService.getLeavebreastCountByType(nows, 3,
				1);// 母猴
		List<?> lyCount = individualService.getLeavebreastCountByType(nows, 2,
				10);// 育成猴
		List<?> lcubCount = individualService.getLeavebreastCountByType(nows,
				1, 10);// 仔猴
		// 调出
		//List<?> smCount = individualService.getSaleCountByType(nows, 3, 0);// 公猴
		//List<?> sfCount = individualService.getSaleCountByType(nows, 3, 1);// 母猴
		//List<?> syCount = individualService.getSaleCountByType(nows, 2, 10);// 育成猴
		//List<?> scubCount = individualService.getSaleCountByType(nows, 1, 10);// 仔猴
		// 死亡
		List<?> dmCount = individualService.getDeathCountByType(nows, 3, 0);// 公猴
		List<?> dfCount = individualService.getDeathCountByType(nows, 3, 1);// 母猴
		List<?> dyCount = individualService.getDeathCountByType(nows, 2, 10);// 育成猴
		List<?> dcubCount = individualService.getDeathCountByType(nows, 1, 10);// 仔猴
		paraMap.put("parameter1", mCount.get(0));
		paraMap.put("parameter2", fCount.get(0));
		paraMap.put("parameter3", yCount.get(0));
		paraMap.put("parameter4", cubCount.get(0));
		paraMap.put("parameter5", lmCount.get(0));
		paraMap.put("parameter6", lfCount.get(0));
		paraMap.put("parameter7", lyCount.get(0));
		paraMap.put("parameter8", lcubCount.get(0));
		//paraMap.put("parameter9", smCount.get(0));
		//paraMap.put("parameter10", sfCount.get(0));
		//paraMap.put("parameter11", syCount.get(0));
		//paraMap.put("parameter12", scubCount.get(0));
		paraMap.put("parameter13", dmCount.get(0));
		paraMap.put("parameter14", dfCount.get(0));
		paraMap.put("parameter15", dyCount.get(0));
		paraMap.put("parameter16", dcubCount.get(0));

		fileName = "AreaAnimalCountDetailed"+ DateUtil.dateToString(new Date(), "yyyyMMddsss");

		Employee e = (Employee) ActionContext.getContext().getSession().get("user");
		String username = e.getName();
		String animalType = Constant.animalType;

		URL logoImage = null;
		logoImage = this.getClass().getResource("logo.jpg");

		paraMap.put("printName", username);
		paraMap.put("animalType", animalType);
		paraMap.put("checkDate",DateUtil.dateToString(new Date(), "yyyy-MM-dd"));
		paraMap.put("logoImage", logoImage);
		//paraMap.put("year", nows.getYear()+1900);
		//paraMap.put("month", nows.getMonth()+1);
		String title=(nows.getYear()+1900)+"年"+(nows.getMonth()+1)+"月份饲养数量统计表";
		paraMap.put("title", title);
		return "areaByReport";
	}*/

	public void setFileInput(InputStream fileInput) {
		this.fileInput = fileInput;
	}

	public InputStream getFileInput() {
		return fileInput;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
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

	public List<Map<String, Object>> getDataSourceList() {
		return dataSourceList;
	}

	public void setDataSourceList(List<Map<String, Object>> dataSourceList) {
		this.dataSourceList = dataSourceList;
	}

	public Map<String, Object> getParaMap() {
		return paraMap;
	}

	public void setParaMap(Map<String, Object> paraMap) {
		this.paraMap = paraMap;
	}
}
