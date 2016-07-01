package com.lanen.service.contract;

import java.util.List;
import java.util.Map;

import com.lanen.base.studyitem.StudyItemBaseDao;
import com.lanen.model.contract.TblStudyItem;

public interface TblStudyItemService extends StudyItemBaseDao<TblStudyItem>{

	
	/**
	 * 根据课题编号查询实体
	 * @param studyNo
	 * @return
	 */
	TblStudyItem getByStudyNoStudyItem(String studyNo);

	/**
	 * 查询委托项目列表(已任命SD,SD==自己,且还未建专题的,studyCode(studyNo),studyName,sponsorName,tiName,tiCode,isConfirm对应供试品是否确认)
	 * @param userName
	 * @return
	 */
	List<Map<String, Object>> getMapListByuserName(String userName);

	/**通过编号查询  供试品类别
	 * @param studyNo
	 * @return
	 */
	String getTiCodeByStudyNo(String studyNo);

}
