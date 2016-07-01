package com.lanen.service.studyplan;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.studyplan.TblDoseSettingHis;
@Service
public class TblDoseSettingHisServiceImpl  extends BaseDaoImpl<TblDoseSettingHis> implements TblDoseSettingHisService {

	public void saveAll(List<TblDoseSettingHis> list) {
		for(TblDoseSettingHis obj:list){
			getSession().save(obj);
		}
	}

}
