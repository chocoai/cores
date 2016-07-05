package com.lanen.service.arp;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Customer;
@Service
public class CustomerServiceImpl extends BaseLongDaoImpl<Customer> implements
		CustomerService {

	public String getAddressById(Long id) {
		String sql="select address from customer where id=:id";
		List<?> list=getSession().createSQLQuery(sql).setParameter("id", id).list();
		String address=null;
		if(list!=null){
			for(Object obj:list){
				address=(String)obj;
			}
		}
		return address;
	}

	/**
	 * 销售联系人地列表
	 */
	public List<Map<String, String>> getAddressMap() {
		String sql = " SELECT id,address from customer where deleted != 1  ";
		Query query = getSession().createSQLQuery(sql);
		List<?> list = query.list();
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		if (list != null) {
			for (Object obj : list) {
				Object[] objs = (Object[]) obj;
				map = new HashMap<String, String>();
				map.put("id", (Integer) objs[0] + "");
				map.put("text", (String) objs[1]);
				mapList.add(map);
			}
		}
		return mapList;
	}

}
