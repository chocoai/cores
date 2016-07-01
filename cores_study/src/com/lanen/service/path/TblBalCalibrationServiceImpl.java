package com.lanen.service.path;


import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.path.TblBalCalibration;
import com.lanen.model.path.TblBalCalibrationIndex;
/**
 * 天平校准记录  serviceImpl
 * @author Administrator
 */
@Service
public class TblBalCalibrationServiceImpl extends BaseDaoImpl<TblBalCalibration> implements
		TblBalCalibrationService {

	@SuppressWarnings("unchecked")
	public List<TblBalCalibration> loadListByCalIndexID(String calIndexId) {
		String hql=" from TblBalCalibration where calIndexId=:calIndexId";
		List<TblBalCalibration> list=getSession().createQuery(hql)
		                                         .setParameter("calIndexId", calIndexId)
		                                         .list();
		return list;
	}

	public void saveBalCalibration(List<TblBalCalibration> list,
			TblBalCalibrationIndex blBalCalibrationIndex) {
		if(null!=list && list.size()>0){
			getSession().save(blBalCalibrationIndex);
			for(TblBalCalibration t:list){
				getSession().save(t);
			}
		}
		
	}

	
}
