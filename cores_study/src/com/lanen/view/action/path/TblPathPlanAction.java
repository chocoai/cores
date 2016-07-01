package com.lanen.view.action.path;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.service.studyplan.TblStudyPlanService;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class TblPathPlanAction extends BaseAction<TblStudyPlan> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1892410255216443958L;
	/**
	 * 专题计划  Service
	 */
	@Resource
	private TblStudyPlanService tblStudyPlanService;
	
	/**
	 * 课题编号
	 */
	private String studyNoPara;
	
    /**转到病理计划主界面
     * @return
     */
    public String pathPlanList(){
    	//获取试验计划
		TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNoPara);
		ActionContext.getContext().put("studyState", tblStudyPlan.getStudyState());
		ActionContext.getContext().put("animalType", tblStudyPlan.getAnimalType());
    	return "pathPlanList";
    }

	public void setStudyNoPara(String studyNoPara) {
		this.studyNoPara = studyNoPara;
	}

	public String getStudyNoPara() {
		return studyNoPara;
	}
}
