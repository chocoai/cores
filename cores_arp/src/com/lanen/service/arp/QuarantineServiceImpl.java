package com.lanen.service.arp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Quarantine;
import com.lanen.util.Constant;
/**
 * 防疫配置
 * @author Administrator
 *
 */
@Service
public class QuarantineServiceImpl extends BaseLongDaoImpl<Quarantine> implements
		QuarantineService {
	//小写.
	public Map<String, Object> getQuarantine(String page,String rows,String name,String type) {
		String sql="select id,name,remark "+
				   "FROM quarantine  WHERE  deleted !=1  ";
		if(null !=type && !("").equals(type)){
			sql = sql +" and type = :type";
		}
		if(null!=name && !("").equals(name)){
			sql = sql +" and name = :name ";
		}else{
			sql = sql +" order by lastmodifytime DESC";
		}
		Query query=getSession().createSQLQuery(sql);
		if(null!=type && !("").equals(type)){
			query.setParameter("type", type);
		}
		if(null!=name && !("").equals(name)){
			query.setParameter("name", name);
		}
		List<?> listSql=query.list();
		//当为缺省值的时候进行赋值        
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行 
		List<?> list = query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();
		List<Quarantine> lists=new ArrayList<Quarantine>();

		if(list!=null){
			for(Object obj:list){
				Quarantine json=new Quarantine();
				Object[] objs=(Object[])obj;
				json.setId((Long.valueOf(objs[0]+"")));
				json.setName((String)objs[1]);
				json.setRemark((String)objs[2]);
				lists.add(json);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", lists);
		map.put("total", listSql.size());
		return map;
	}
	//小写.
	public List<Quarantine> getQuarantineByMark(String mark){  //根据病毒或者检测方法
		List<Quarantine> qlist = null;
		String sql = "select id,name from quarantine q where q.type=:mark and q.deleted=0";
		Query query=getSession().createSQLQuery(sql);
		if(!"".equals(mark)&&mark!=null){
			query.setParameter("mark", mark);
		}
		qlist=query.list();
		return 	qlist;
	}
	
	public String getQuarantineAndMBy(String Remark,String Mremark){
		String retvalue = "";
		String tmp = "";
		List<Quarantine> qlist = getQuarantineByMark(Remark);
		List<Quarantine> qmlist = getQuarantineByMark(Mremark);
		if(qlist!=null && qlist.size()>0 ){
			tmp= "<TR><TH  align=\"right\">p_name:</TH>" +
				 "<td>&nbsp;&nbsp;阴性</td><td><input type=\"radio\" name=\"p_result\" checked=\"checked\" value=\"0\"></td>";
			tmp+="<td>&nbsp;&nbsp;阳性</td><td><input type=\"radio\" name=\"p_result\" value=\"1\"></td>";
			tmp+="<td>&nbsp;&nbsp;检测方法: </td><td><select style=\"width: 180\" name=\"p_select\">p_opValue</select></td>";
			tmp+="<td>&nbsp;&nbsp;药品名:</td><td><input type=\"text\" name=\"p_drugs_name\" id=\"p_drugs_name_d\" style=\"width:100\"/></td>" +
					"<script>"+
            "new Autocomplete(\"p_drugs_name_d\", function() {"+
	        	"return \"/ARP/getData.do?q=\" + this.value+\"&SQLtype=QWDrugTypeQW\""+
            "});"+
        "</script>" ;
			tmp+="<td>&nbsp;&nbsp;用药量:</td><td><input type=\"text\" name=\"p_drugs_count\" style=\"width:60px\"/></td>";
			tmp+="</TR>";
			
			
			String tmp1= "<TR><TH  align=\"right\">p_name:</TH><td  align=\"left\" colspan=\"3\">" +
				 "&nbsp;&nbsp;阴性<input type=\"radio\" name=\"p_result\" checked=\"checked\" value=\"0\">";
			tmp1+="&nbsp;&nbsp;阳性<input type=\"radio\" name=\"p_result\" value=\"1\">";
			tmp1+="&nbsp;&nbsp;检测方法: <select style=\"width: 180\" name=\"p_select\">p_opValue</select>";
			tmp1+="</td></TR>";
			
			String tmpm = "<option value=\"pm_id\">pm_name</option>";
			String p_opValue = "";
			String pm_String = "";
			/*for(int i=0;i<qmlist.size();i++){
				Quarantine q1 = (Quarantine)qmlist.get(i);
				if(q1!=null && q1.getName()!=null && !"".equals(q1.getName())){
					p_opValue = tmpm;
					p_opValue=p_opValue.replace("pm_id", q1.getId()+"");
					p_opValue=p_opValue.replace("pm_name", q1.getName()+"");
					pm_String += p_opValue;
				}
			}*/
			for(Object o:qmlist){
				Quarantine q1=new Quarantine();
				Object [] ob=(Object[])o;
				q1.setId(Long.valueOf(ob[0]+""));
				q1.setName((String)ob[1]);
				if(q1!=null && q1.getName()!=null && !"".equals(q1.getName())){
					p_opValue = tmpm;
					p_opValue=p_opValue.replace("pm_id", q1.getId()+"");
					p_opValue=p_opValue.replace("pm_name", q1.getName()+"");
					pm_String += p_opValue;
				}
			}
			String qstring = "";
			for(Object obj:qlist){
				Quarantine q = new Quarantine();
				Object[] ob=(Object[])obj;
				q.setId(Long.valueOf(ob[0]+""));
				q.setName((String)ob[1]);
				qstring = tmp;
				if(Constant.virus.equals(Remark)){  //
					qstring = tmp;
				}
				if(Constant.parasite.equals(Remark)){  //
					qstring = tmp1;
				}
				if(q!=null){
					if(q.getName()!=null && !"".equals(q.getName())){
						String re = q.getId()+"_";
						qstring=qstring.replace("p_name", q.getName());
						qstring=qstring.replace("p_result",re+"result");
						qstring=qstring.replace("p_select",re+"select");
						qstring=qstring.replace("p_drugs_name", re+"drugs_name");
						qstring=qstring.replace("p_drugs_count", re+"drugs_count");
						if(p_opValue!=null && !"".equals(p_opValue)){
							qstring = qstring.replace("p_opValue", pm_String);
						}
					}
				}
				retvalue+=qstring;
			}
		}
		//Bacteria=细菌
		/*if(Constants.QuarantineTypeBacteria.equals(Remark)){  //
			retvalue=retvalue.replace("QWDrugTypeQW", "DrugQQ&DrugQQ=xj");
		}
		//Parasite=寄生虫
		if(Constants.QuarantineTypeParasite.equals(Remark)){  //
			retvalue=retvalue.replace("QWDrugTypeQW", "DrugQQ&DrugQQ=jsc");
		}
		//Virus=病毒
		if(Constants.QuarantineVirusType.equals(Remark)){  //
			retvalue=retvalue.replace("QWDrugTypeQW", "DrugQQ&DrugQQ=bd");
		}
		//System.out.println("retvalue : "+retvalue);
*/		return retvalue;
	}

	//得到检测方法,
	public List<Map<String, String>> getMethod(String type) {
		String sql="SELECT ID,NAME FROM quarantine WHERE type=:type and deleted!=1";
		Query query = getSession().createSQLQuery(sql);
		if(!"".equals(type)&&type!=null){
			query.setParameter("type", type);
		}
		List<?> list = query.list();
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		map = new HashMap<String, String>();
		map.put("id", "-1");
		map.put("text", "&nbsp;");
		mapList.add(map);
		for(Object obj:list){
			Object[] objs = (Object[]) obj;
			map = new HashMap<String, String>();
			map.put("id", objs[0]+"");
			map.put("text", (String) objs[1]);
			mapList.add(map);
		}
		return mapList;
	}

	public List<Map<String, String>> getQuarantineName(String type) {
		String sql="SELECT ID,NAME FROM quarantine WHERE deleted!=-1 and type=:type";
		Query query = getSession().createSQLQuery(sql);
		query.setParameter("type", type);
		List<?> list = query.list();
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		for(Object obj:list){
			Object[] objs = (Object[]) obj;
			map = new HashMap<String, String>();
			map.put("id", objs[0]+"");
			map.put("text", (String) objs[1]);
			mapList.add(map);
		}
		return mapList;
	}
}
