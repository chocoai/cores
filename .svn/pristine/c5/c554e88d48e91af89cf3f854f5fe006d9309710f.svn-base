package com.lanen.service.path;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.path.TblBalCalibrationPoint;
@Service
public class TblBalCalibrationPointServiceImpl extends BaseDaoImpl<TblBalCalibrationPoint> implements TblBalCalibrationPointService{

	@SuppressWarnings("unchecked")
	public TblBalCalibrationPoint getBalCalibrationPointByCalType(
			Integer calType) {
		List<TblBalCalibrationPoint> list=getSession().createQuery("FROM TblBalCalibrationPoint  d WHERE  d.calType = ? ")
		.setParameter(0, calType)
		.list();
		if(null!=list&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

}
