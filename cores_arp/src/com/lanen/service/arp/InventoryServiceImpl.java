package com.lanen.service.arp;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Area;
import com.lanen.model.Employee;
import com.lanen.model.Inventory;
@Service
public class InventoryServiceImpl extends BaseLongDaoImpl<Inventory> implements
		InventoryService {
     @Resource
     private EmployeeService employeeService;
     @Resource
     private AreaService areaService;
	@SuppressWarnings("unchecked")
	public List<Inventory> getListByInventoryDate(Long keeper,
			Date inventoryDate) {
		String hql="from Inventory where inventoryDate=:inventoryDate";
		if(keeper!=null&&keeper!=0){
			hql=hql+" and keeper=:keeper";
		}
		Query query=getSession().createQuery(hql);
		if(keeper!=null&&keeper!=0){
			query.setParameter("keeper", keeper);
		}
		query.setParameter("inventoryDate", inventoryDate);
		List<Inventory> list=query.list();
		return list;
	}

	public ArrayList<Object> getExcelFiledDataList(Long keeper,
			Date inventoryDate) {
		List<Inventory> list=getListByInventoryDate(keeper,inventoryDate);
		ArrayList<Object> filedData = new ArrayList<Object>();
		if(list!=null){
			int i=1;
			for(Inventory inventory:list){
				ArrayList<Object> dataList = new ArrayList<Object>();
				dataList.add(i);
				i++;
				dataList.add(getAreaName(inventory.getRoomId()));
				dataList.add(inventory.getMaleMonkeyCount());
				dataList.add(inventory.getRealmaleMonkeyCount());
				dataList.add(inventory.getFemaleMonkeyCount());
				dataList.add(inventory.getRealfemaleMonkeyCount());
				dataList.add(inventory.getYuchengMonkeyCount());
				dataList.add(inventory.getRealyuchengMonkeyCount());
				dataList.add(inventory.getCubMonkeyCount());
				dataList.add(inventory.getRealcubMonkeyCount());
				dataList.add(inventory.getTotalCount());
				dataList.add(inventory.getRealtotalCount());
				dataList.add(getEmpName(inventory.getKeeper()));
				Date date=inventory.getInventoryDate();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				String dateString=sdf.format(date);
				dataList.add(dateString);
				filedData.add(dataList);
			}
		}
		return filedData;
	}

	public ArrayList<Object> getExcelFiledNameList() {
		String [] titles = {"序号","房间名","应盘公猴数量","实盘公猴数量","应盘母猴数量","实盘母猴数量","应盘育成猴数量","实盘育成猴数量","应盘仔猴数量","实盘仔猴数量","应盘合计","实盘合计","饲养员","盘点日期"};
		ArrayList<Object> filedName = new ArrayList<Object>();
		for(int i=0;i<titles.length;i++){
			String title = titles[i];
			filedName.add(title);
		}
		return filedName;
	}
   
	
	/**根据区域ID获得区域名
     * @param id
     * @return
     */
    public String getAreaName(Long id){
    	String areaName=null;
    	Area area=areaService.getById(id);
    	if(area!=null){
    		areaName=area.getAreaname();
    	}
    	return areaName;
    }
    /**根据员工ID获得员工姓名
	 * @param id
	 * @return
	 */
	public  String getEmpName(Long id){
		String name=null;
		if(id!=null){
			Employee e=employeeService.getById(id);
			if(e!=null){
				name=e.getName();
			}
		}
		return name;
	}

}
