package com.lanen.service.studyplan;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDao;
import com.lanen.model.studyplan.TblTestIndexPlanHis;

public interface TblTestIndexPlanHisService extends BaseDao<TblTestIndexPlanHis>{

	void saveAll(List<TblTestIndexPlanHis>  list);
}
