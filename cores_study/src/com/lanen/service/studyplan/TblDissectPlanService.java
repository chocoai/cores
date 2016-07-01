package com.lanen.service.studyplan;

import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.model.studyplan.TblAnimalDetailDissectPlan;
import com.lanen.model.studyplan.TblDissectPlan;
import com.lanen.model.studyplan.TblStudyPlan;

public interface TblDissectPlanService extends BaseDao<TblDissectPlan> {

	/**
	 * 根据试验计划查找内容
	 * @return List<TblDissectPlan>
	 */
	List<TblDissectPlan> getByStudyNo(TblStudyPlan studyPlan);

	/**
	 * 主键唯一性检查
	 * @return boolean
	 */
	boolean uniqueCheck(TblStudyPlan studyPlan , int dissectNumPara , String disId);

	/**
	 * 获得下一个解剖次数
	 * @return
	 */
	int getNextNum(TblStudyPlan studyPlan);
	
	/**
	 * 覆写BaseDao方法，保存时自动获得id
	 * @param dissectPlan
	 */
	void save(TblDissectPlan dissectPlan,List<TblAnimalDetailDissectPlan> detailDissectPlans);
	
	/**
	 * 覆写BaseDao方法，自动更新动物详细解剖计划
	 * @param dissectPlan
	 * @param newDetailDissectPlans
	 */
	void update(TblDissectPlan dissectPlan,List<TblAnimalDetailDissectPlan> newDetailDissectPlans);
	
	/**
	 * 更新，保存历史痕迹
	 * @param dissectPlan
	 * @param newDetailDissectPlans
	 */
	void updateAndSaveHis(List<TblAnimalDetailDissectPlan> newDetailDissectPlans);

	/**
	 * 删除该实验计划下的解剖计划
	 * @param tblStudyPlan
	 */
	void deleteByStudyPlan(TblStudyPlan tblStudyPlan);
	/**
	 * 覆写BaseDao方法，保存时自动获得id
	 * @param dissectPlan
	 */
	void save(TblDissectPlan dissectPlan);
	/**
	 * 根据试验计划和解剖次数
	 * @return List<TblDissectPlan>
	 */
	TblDissectPlan getByStudyNo(TblStudyPlan tblStudyPlan, int dissectNum);
	
	/**
	 * 保存是保存历史记录
	 * @param dissectPlan
	 */
	void saveAndSaveHis(TblDissectPlan dissectPlan);
	/**
	 * 编辑时保存历史记录
	 * @param dissectPlan
	 */
	void upDateAndSaveHis(TblDissectPlan dissectPlan);

	/**根据课题编号和解剖次数加载解剖设定的开始和结束日期
	 * @param studyNoPara
	 * @param dissectNumPara
	 * @return
	 */
	Map<String, Object> getDissectPlanDateByDissectNum(String studyNoPara,
			int dissectNumPara);

	/**得到数据库现有的阶段描述
	 * @return
	 */
	List<Map<String, Object>> getDescribeList();
	
}
