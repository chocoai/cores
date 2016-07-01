package com.lanen.service.schdeule;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.schedule.TblAnimalHouse;

@Service
public class TblAnimalHouseServiceImpl  extends BaseDaoImpl<TblAnimalHouse> implements TblAnimalHouseService{

	@SuppressWarnings("unchecked")
	public List<TblAnimalHouse> getAll() {
		List<TblAnimalHouse> list=getSession().createQuery("FROM TblAnimalHouse  d WHERE d.validFlag = 0  order by d.orderNo ").list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public boolean isExistByResName(String name) {
		List<TblAnimalHouse> list=getSession().createQuery("FROM TblAnimalHouse  d WHERE  d.resName = ?  and d.validFlag = 0 ")//
		.setParameter(0, name)
		.list();
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public boolean isExistByResNameAndPid(String name, String pid) {
		List<TblAnimalHouse> list=getSession().createQuery("FROM TblAnimalHouse  d WHERE  d.resName = ? and d.parentId = ?  and d.validFlag = 0 ")//
		.setParameter(0, name).setParameter(1, pid)
		.list();
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<TblAnimalHouse> getresKind(int resKind) {
		List<TblAnimalHouse> list=getSession().createQuery("FROM TblAnimalHouse  d WHERE d.resKind = ?  and d.validFlag = 0 order by d.orderNo ").setParameter(0, resKind).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TblAnimalHouse> getParentId(String id) {
		List<TblAnimalHouse> list=getSession().createQuery("FROM TblAnimalHouse  d WHERE d.parentId = ?  and d.validFlag = 0 order by d.orderNo ").setParameter(0, id).list();
		return list;
	}

	public void updateAll(List<TblAnimalHouse> list) {
		for(TblAnimalHouse obj : list){
			getSession().update(obj);
		}
		
	}

	public int getNextOrderNo() {
		Integer orderNo = (Integer) getSession().createSQLQuery("select  max(d.orderNo) from tblAnimalHouse  d ").uniqueResult();
		if(null == orderNo){
			return 1;
		}else{
			return orderNo+1;
		}
		
	}

	public boolean referencedRid(String id) {
		Integer  count =(Integer)getSession().createSQLQuery("SELECT  COUNT(id) AS Expr1 FROM  [CoresSchedule].[dbo].[tblStudyRes] as a WHERE  (a.resId = :resid)").setParameter("resid", id).uniqueResult();
		System.out.println(count);
		if( null ==count || count == 0 ){
			return true;
		}else{
			return false;
		}
	}

	public List<TblAnimalHouse> getAllByResIdlist(List<String> residlist) {
		String sql = "select distinct house3.id,house3.resKind,house3.resName,house3.parentId "+
        " from CoresSystemSet.dbo.tblAnimalHouse as house1 "+
        " 	left join CoresSystemSet.dbo.tblAnimalHouse as house2  "+
        " 	on (house1.resKind = 1 and house1.id = house2.parentId)  "+
        " 	or (house1.resKind = 2 and (house1.id = house2.parentId or house1.parentId = house2.id)) "+
        " 	or (house1.resKind = 3 and ( house1.parentId = house2.id)) "+
        " 	or( house2.id = house1.id) "+
        " 	left join CoresSystemSet.dbo.tblAnimalHouse as house3  "+
        " 	on (house1.resKind =1 and house2.resKind = 2 and (house2.id = house3.parentId or house2.id = house3.id)) "+
        " 	 or (house1.resKind =2 and house2.resKind = 1 and house2.id = house3.id) "+
        " 	 or (house1.resKind =2 and house2.resKind = 3 and house2.id = house3.id) "+
        " 	 or (house1.resKind =3 and house2.resKind = 2 and (house2.parentId = house3.id  or house2.id = house3.id)) "+
        " 	 or (house1.resKind = house2.resKind and house2.id = house3.id) "+
        "  where house1.id in ( :residlist)" ;
		List<?> list=getSession().createSQLQuery(sql).setParameterList("residlist", residlist).list();
		List<TblAnimalHouse> houseList = new ArrayList<TblAnimalHouse>();
		if(null !=list && list.size()>0){
			for(int i =0;i<list.size();i++){
				TblAnimalHouse animalHouse  = new TblAnimalHouse();
				Object[] objs = (Object[]) list.get(i);
				animalHouse.setId((String)objs[0]);
				animalHouse.setResKind((Integer)objs[1]);
				animalHouse.setResName((String)objs[2]);
				animalHouse.setParentId((String)objs[3]);
				houseList.add(animalHouse);
			}
			
		}
		return houseList;
	}


	

}
