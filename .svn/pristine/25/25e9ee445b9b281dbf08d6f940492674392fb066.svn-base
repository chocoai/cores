package com.lanen.view.action.qa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.qa.QAChkReport;
import com.lanen.service.qa.QAChkReportService;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class QAChkAction extends BaseAction<QAChkReport> {

	private static final long serialVersionUID = 3581040531398819998L;
	@Resource
	private QAChkReportService qAChkReportService;
	
	private String selectReportCode;
	
	
	public void putSelectChkReportCodeInSession(){
		ActionContext.getContext().getSession().put("selectReportCode", selectReportCode);
	}
	
	public String list()
	{
		Object s = ActionContext.getContext().getSession().get("selectReportCode");
		if(s!=null&&!"".equals(s)){
			ActionContext.getContext().put("selectReportCode",s );
			ActionContext.getContext().getSession().put("selectReportCode", "");
		}
		return "list";
	}
	public void getYears()
	{
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		
		List<Integer> years = qAChkReportService.getYears();
		if (years!=null&&years.size()>0) 
		{
			
			for(Integer integer : years)
			{
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("year", integer);
				if(years.indexOf(integer)==(years.size()-1))
				{
					map.put("selected", true);
				}
				mapList.add(map);
			}
			String json = JsonPluginsUtil.beanListToJson(mapList);
			writeJson(json);
		}
		
	}
	
	
	
	
	public QAChkReportService getqAChkReportService() {
		return qAChkReportService;
	}

	public void setqAChkReportService(QAChkReportService qAChkReportService) {
		this.qAChkReportService = qAChkReportService;
	}
	public String getSelectReportCode() {
		return selectReportCode;
	}
	public void setSelectReportCode(String selectReportCode) {
		this.selectReportCode = selectReportCode;
	}
	
	

}
