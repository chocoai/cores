package com.lanen.service.studyplan;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.studyplan.TblTestIndexPlanHis;
@Service
public class TblTestIndexPlanHisServiceImpl extends BaseDaoImpl< TblTestIndexPlanHis> implements TblTestIndexPlanHisService{

	public void saveAll(List<TblTestIndexPlanHis> list) {
		for(TblTestIndexPlanHis testIndexPlanHis:list){
			getSession().save(testIndexPlanHis);
		}
		
	}

}
