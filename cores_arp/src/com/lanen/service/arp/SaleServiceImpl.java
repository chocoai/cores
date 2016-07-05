package com.lanen.service.arp;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Approval;
import com.lanen.model.Customer;
import com.lanen.model.Sale;
import com.lanen.util.Constant;
@Service
public class SaleServiceImpl extends BaseLongDaoImpl<Sale> implements SaleService {

	public List<?> getListByAddSalemonkey() {
		//String sql="SELECT * FROM sale WHERE id NOT IN( SELECT DISTINCT sale_id FROM salemonkey) AND (STATUS='4' OR STATUS='5')";
		//String sql="select * from sale where deleted=0 and status=5";完成选候的订单.
		String sql="select id,title,status,tiaojian,salecount,outdate,monkeylist,(select name from publiccode p where p.id=s.saletype) as saletype," +
				"(select address from customer c where c.id=s.saleaddress_id)as saleaddress_id1," +
				"(select name from employee e where e.id=s.boss)as boss from sale s where deleted=0 and status=5";
		List<?> sqlList=getSession().createSQLQuery(sql).list();
		return sqlList;
	}

	@SuppressWarnings("unchecked")
	public List<Sale> getListBySelect() {
		//String hql="from Sale where deleted!=1 and status=1";
		String hql="from Sale where deleted!=1";
		List<Sale> list=getSession().createQuery(hql).list();
		return list;
	}

	public Map<String,Object> getListBySelect1(String page,String rows) {
		Map<String,Object> map=new HashMap<String,Object>();
		String hql="select id,title,status,tiaojian,salecount,(select name from publiccode p where p.id=s.saletype)as saletype," +
				"saleaddress_id,(select address from customer c where c.id=s.saleaddress_id)as saleaddress_id1," +
				"(select name from employee e where e.id=s.boss)as boss from sale s where s.deleted!=1";
		Query query=getSession().createSQLQuery(hql);
		List<?> total=query.list();
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行 
		List<?> list = query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();
		map.put("rows", list);
		map.put("total", total.size());
		return map;
	}
	public boolean isExistTitle(String title) {
		if (null != title) {
			List<?> list = getSession()
					.createQuery(
							" From Sale where deleted != 1 and title = ? ")
					.setParameter(0, title).list();
			if (null != list && list.size() > 0) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	public boolean isExistOutMonkey(String monkeyid) {
		if (null != monkeyid) {
			List<?> list = getSession()
					.createSQLQuery(
							"select monkeyid From individual where deleted != 1 and status=1 and monkeyid=?")
					.setParameter(0, monkeyid).list();
			if (null != list && list.size() > 0) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}
	public List<Map<String,String>> getSaleTypeMap(){
		String sql="select id,name from publiccode where mark=:mark";
		Query query=getSession().createSQLQuery(sql);
		query.setParameter("mark", Constant.SALE_TYPE_MARK);
		List<?> list=query.list();
		List<Map<String,String>> listMaps=new ArrayList<Map<String,String>>();
		if (list!=null) {
			for (Object ob : list) {
				Object[] objs = (Object[]) ob;
				Map<String, String> m = new HashMap<String, String>();
				m.put("id", objs[0] + "");
				m.put("text", objs[1] + "");
				listMaps.add(m);
			}
		}
		return listMaps;
	}

	public List<?> getListAddress() {
		String hql="from Customer where deleted!=1";
		List<?> list=getSession().createQuery(hql).list();
		return list;
	}

	public Customer getAddressById(Long id) {
		String hql="from Customer where deleted!=1";
		if(!"".equals(id)&&id!=null){
			hql=hql+" and id=?";
		}
		Query query=getSession().createQuery(hql);
		if(!"".equals(id)&&id!=null){
			query.setParameter(0, id);
		}
		List<Customer> list=query.list();
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	

}
