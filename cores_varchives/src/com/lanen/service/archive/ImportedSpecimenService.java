package com.lanen.service.archive;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.ImportedSpecimen;

public interface ImportedSpecimenService extends BaseDao<ImportedSpecimen> {

	List<ImportedSpecimen> getListByCondition(String studydNo, String searchCond);
	
	List<ImportedSpecimen> getByStudyNoAndSpecimenCode(String studyNo,String specimenCode);
}
