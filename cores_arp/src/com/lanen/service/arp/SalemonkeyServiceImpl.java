package com.lanen.service.arp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Animaltype;
import com.lanen.model.Approval;
import com.lanen.model.Employee;
import com.lanen.model.Salemonkey;
import com.lanen.model.Salemonkey_Json;
@Service
public class SalemonkeyServiceImpl extends BaseLongDaoImpl<Salemonkey>
		implements SalemonkeyService {
	@Resource
	private AnimaltypeService animaltypeService;
	@Resource
	private ApprovalService approvalService;
	@Resource
	private EmployeeService employeeService;
	@Resource
	private SaleService saleService;

	public Map<String, Object> getListByConditions(String page, String rows,
			String monkeyid, Date outdate, String blongsale) {
		/*String sql="SELECT s1.id,s1.monkeyid,s2.monkeytype,s2.outdate,s2.title,s2.tiaojian,s2.salecount,s2.approveserial,s2.trance,s2.saletype,s2.boss,s2.remark"+ 
                   " FROM salemonkey s1 JOIN sale s2 ON s1.sale_id=s2.id WHERE s2.deleted!=1";*/
		
		String sql="select s1.id,s1.monkeyid,s2.monkeytype,s2.outdate,s2.title,s2.tiaojian,s2.salecount,s2.approveserial,s2.trance,s2.saletype,s2.boss,s2.remark," +
				" (select name from publiccode p where p.id=s2.saletype) as saletypeName from salemonkey s1,sale s2 where s1.sale_id=s2.id and s2.deleted=0";
		if(monkeyid!=null&&!"".equals(monkeyid)){
			sql=sql+" and s1.monkeyid=:monkeyid";
		}
		if(outdate!=null){
			sql=sql+" and s2.outdate=:outdate";
		}
		if(blongsale!=null&&!"".equals(blongsale)){
			sql=sql+" and s2.title like :blongsale ";
		}
		sql= sql+" order by s2.outdate desc";
		Query query=getSession().createSQLQuery(sql);
		if(monkeyid!=null&&!"".equals(monkeyid)){
			query.setParameter("monkeyid", monkeyid);
		}
		if(outdate!=null){
			query.setParameter("outdate", outdate);
		}
		if(blongsale!=null&&!"".equals(blongsale)){
			query.setParameter("blongsale", "%"+blongsale+"%");
		}
		List<?> listtotal=query.list();
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行 
		List<?> list = query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list(); 
		
		List<Salemonkey_Json> list2=new ArrayList<Salemonkey_Json>();
		int addmark=1;//是否有订单猴子已出场但是未添加出场记录标志status==5
		List<?> list3=saleService.getListByAddSalemonkey();
		if(list3!=null&&list3.size()>0){
			addmark=0;
		}else{
			addmark=1;
		}
		
		if(null!=list){
			for(Object obj:list){
				Object[] objs = (Object[]) obj;
				Salemonkey_Json json=new Salemonkey_Json();
				//json.setId((BigInteger) objs[0]);
				json.setId((Integer) objs[0]);
				json.setMonkeyid((String)objs[1]);
				Integer animaltype=(Integer)objs[2];
				if(animaltype!=null){
					Long at=new Long(animaltype.toString());
					Animaltype a=animaltypeService.getById(at);
					if(a!=null){
						json.setTypeName(a.getName());
					}
				}
				
				json.setOutdate((Date)objs[3]);
				json.setTitle((String)objs[4]);
				json.setTiaojian((String)objs[5]);
				json.setSalecount((String)objs[6]);
				Integer appId=(Integer)objs[7];  //销售许可ID
				if(appId!=null){
					Long aid=new Long(appId.toString());
					Approval a1=approvalService.getById(aid);
					if(a1!=null){
						json.setAphao(a1.getPhao());
					}
				}
				
				Integer tranceId=(Integer)objs[8];  //运输许可ID（和销售许可同一张表）
				if(tranceId!=null){
					Long tid=new Long(tranceId.toString());
					Approval a2=approvalService.getById(tid);
					if(a2!=null){
						json.setTphao(a2.getPhao());
					}
				}
				
				Integer saletype=(Integer)objs[9]; 
				
				Integer bossId=(Integer)objs[10];
				if(bossId!=null){
					Long bid=new Long(bossId);
					Employee e=employeeService.getById(bid);
					if(e!=null){
						json.setBossName(e.getName());
					}
				}
				json.setRemark((String)objs[11]);
				json.setAddmark(addmark);
				json.setSaletypeName((String)objs[12]);
				list2.add(json);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list2);
		map.put("total", listtotal.size());
		map.put("addMark", addmark);
		return map;
	}

	

}
