package com.lanen.view.action.arp;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Employee;
import com.lanen.model.Treasury;
import com.lanen.service.arp.TreasuryService;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class TreasuryAction extends BaseAction<Treasury> {

	/**
	 * 疾病字典
	 */
	private static final long serialVersionUID = 5490271779626743429L;

	@Resource
	private TreasuryService treasuryService;
	private String page;
	private String rows;
	private String sys;

	private Vector<Map<String,Object>> dataSourceList;
	public String list() {
		return "treasuryList";
	}

	public void loadList() {
		Map<String, Object> map = treasuryService.getTreasury(page, rows,
				model.getName(), sys);
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void add() {
		Map<String, Object> map = new HashMap<String, Object>();
		Treasury t = new Treasury();
		if (model.getName() != null && !"".equals(model.getName())) {
			
			t.setName(model.getName());
			t.setSymptomssite(model.getSymptomssite());
			t.setTreasurydate(new Date());
			t.setReason(model.getReason());
			t.setSymptomsremark(model.getSymptomsremark());
			t.setPrevention(model.getPrevention());
			t.setDeleted(0);
			Employee user = (Employee) ActionContext.getContext().getSession()
					.get("user");
			treasuryService.save(t);
			map.put("success", true);
			map.put("msg", "添加成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void delTreasury() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null) {
			Treasury t = treasuryService.getById(model.getId());
			t.setDeleted(1);
			treasuryService.update(t);
			map.put("success", true);
			map.put("msg", "删除成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void toEdit() {
		if (model.getId() != null) {
			Treasury t = treasuryService.getById(model.getId());
			String jsonStr = JsonPluginsUtil.beanToJson(t, "yyyy-MM-dd");
			writeJson(jsonStr);
		}
	}

	public void editSave() {
		Map<String, Object> map = new HashMap<String, Object>();
		if ( model.getId() != null
				&& !"".equals(model.getId())) {
			Treasury t = treasuryService.getById(model.getId());
			t.setId(model.getId());
			t.setName(model.getName());
			t.setSymptomssite(model.getSymptomssite());
			t.setTreasurydate(new Date());
			t.setReason(model.getReason());
			t.setSymptomsremark(model.getSymptomsremark());
			t.setPrevention(model.getPrevention());
			treasuryService.update(t);
			map.put("success", true);
			map.put("msg", "编辑成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	/**
	 * 疾病发病图
	 * @return
	 */
	public String reportTreasury(){
		return "treasuryReport";
	}
	public String treasuryByReport(){
		dataSourceList=new Vector<Map<String,Object>>();
		//呼吸系统1，生殖系统2，消化系统3，外伤11
		Map<String,Object> map=null;
		Date nows=new Date();
		int year=nows.getYear()+1900;
		for(int i=1;i<=12;i++){
			
			List<?> list=null;
			for (int j = 1; j <= 4; j++) {
				map=new HashMap<String,Object>();
				
				if (i == 2) {
					list = treasuryService.getTreasuryCount(year + "-02-01",year + "-02-29",j);
				}
				else if(10<=i&&i<=12){
					list = treasuryService.getTreasuryCount(year + i + "-01", year +  i + "-31",j);
				}else{
					list = treasuryService.getTreasuryCount(year + "-0" + i + "-01", year + "-0" + i + "-31",j);
				}
				BigInteger count = (BigInteger) list.get(0);
				if (count.intValue() > 0) {
					map.put("treasurySum", count);
				} else {
					map.put("treasurySum", 0);
				}
				//Integer date=Integer.parseInt(year+"-"+i);
				//map.put("treasuryDate", year+"-"+i);
				map.put("treasuryDate", i);
				if (j==1) {
					map.put("treasuryType", "呼吸系统");
				}else if(j==2){
					map.put("treasuryType", "生殖系统");
				}else if(j==3){
					map.put("treasuryType", "消化系统");
				}else{
					map.put("treasuryType", "外伤");
				}
				
				dataSourceList.add(map);
			}
		}
		
		return "treasuryByReport";
	}
	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getSys() {
		return sys;
	}

	public void setSys(String sys) {
		this.sys = sys;
	}

	public Vector<Map<String, Object>> getDataSourceList() {
		return dataSourceList;
	}

	public void setDataSourceList(Vector<Map<String, Object>> dataSourceList) {
		this.dataSourceList = dataSourceList;
	}

}
