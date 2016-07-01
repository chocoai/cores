package com.lanen.service.path;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.path.TblPathStudyIndex;

@Service
@Transactional
public class TblPathStudyIndexServiceImpl extends BaseDaoImpl<TblPathStudyIndex> implements TblPathStudyIndexService {

	
	public TblPathStudyIndex getByStudyNo(String studyNo) {
		String hql = " from TblPathStudyIndex where studyNo = ? ";
		TblPathStudyIndex tblPathStudyIndex = null;
		List<?> list = getSession().createQuery(hql).setParameter(0, studyNo).list();
		if(null != list && list.size() > 0){
			tblPathStudyIndex = (TblPathStudyIndex) list.get(0);
		}
		
		return tblPathStudyIndex;
	}

}
