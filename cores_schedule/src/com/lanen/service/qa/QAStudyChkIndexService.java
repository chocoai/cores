package com.lanen.service.qa;

import com.lanen.base.BaseDao;
import com.lanen.model.qa.QAStudyChkIndex;

public interface QAStudyChkIndexService extends BaseDao<QAStudyChkIndex>{

	QAStudyChkIndex getByStudyNo(String studyNo);
	
}
