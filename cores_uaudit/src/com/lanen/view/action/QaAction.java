package com.lanen.view.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.service.QaService;
import com.lanen.service.TblYYDBService;
import com.opensymphony.xwork2.ActionSupport;

/**QA管理
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class QaAction extends ActionSupport{

	private static final long serialVersionUID = -5146590998281197112L;

	@Resource
	private QaService qaService;
	
	private int operateType;//类型：
	
	private String studyNo;
	
	public void loadlist(){
		List<Map<String,Object>> dataMapList = null;
		if(null != studyNo ){
			dataMapList = qaService.getList(studyNo,operateType);
		}
		
		String studyNoListStr = "";
		if(null != dataMapList){
			studyNoListStr = JsonPluginsUtil.beanListToJson(dataMapList);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write(studyNoListStr);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//-------------------------
	public int getOperateType() {
		return operateType;
	}
	public void setOperateType(int operateType) {
		this.operateType = operateType;
	}
	public String getStudyNo() {
		return studyNo;
	}
	public void setStudyNo(String studyNo) {
		this.studyNo = studyNo;
	}
	
	
	
}
