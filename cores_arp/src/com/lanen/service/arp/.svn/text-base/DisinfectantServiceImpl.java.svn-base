package com.lanen.service.arp;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Disinfectant;
@Service
public class DisinfectantServiceImpl extends BaseLongDaoImpl<Disinfectant>
		implements DisinfectantService {

	public List<Map<String, Object>> getAllRoomIdName() {
		String sql="SELECT * FROM disinfectant ORDER BY createdDate desc";
		List<?> listSql=getSession().createSQLQuery(sql).list();
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		if(listSql!=null){
			for(Object obj:listSql){
				Map<String, Object> map=new HashMap<String, Object>();
				Object[] objs=(Object[])obj;
				map.put("id", objs[0]);
				map.put("text",(String)objs[1] );
				list.add(map);
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Disinfectant> getALLbyCreateDate() {
		List<Disinfectant> list=null;
		String hql="from Disinfectant order by createdDate desc";
		list=getSession().createQuery(hql).list();
		return list;
	}

	public boolean isExistName(String disinfectantCode) {
		if(null != disinfectantCode){
			List<?> list = getSession().createQuery(" From Disinfectant where disinfectantCode = :disinfectantCode ")
						.setParameter("disinfectantCode", disinfectantCode)
						.list();
			if(null != list && list.size()>0){
				return true;
			}else{
				return false;
			}
		}
		return true;
	}


	

}
