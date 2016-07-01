package com.lanen.service.studyplan;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.studyplan.TblDoseSettingVersion;

public interface TblDoseSettingVersionService extends BaseDao<TblDoseSettingVersion>{

	List<TblDoseSettingVersion> getMaxVersionByStudyNo(String studyNoPara);
}
