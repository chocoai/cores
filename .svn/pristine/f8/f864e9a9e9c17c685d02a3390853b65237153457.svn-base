package com.lanen.service.studyplan;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.studyplan.DictTestItemType;
@Service
public class DictTestItemTypeServiceImpl extends BaseDaoImpl<DictTestItemType> implements DictTestItemTypeService{

	@SuppressWarnings("unchecked")
	public List<DictTestItemType> getAll() {
		return getSession().createQuery("From DictTestItemType o Order by o.tiCode ").list();
	}

	public void svaeOrUpdate(DictTestItemType model) {
		if(null!=model.getTiCode() && !"".equals(model.getTiCode())&& null!=model.getTiType() && !"".equals(model.getTiType())){
			getSession().saveOrUpdate(model);
		}
	}

	@SuppressWarnings("unchecked")
	public boolean isExistByTiCode(String tiCode) {
		List<DictTestItemType> list=getSession().createQuery("From DictTestItemType o WHERE o.tiCode= ? ").setParameter(0, tiCode).list();
		if(null!=list &&list.size()>0){
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public boolean isExistByTiType(String tiType) {
		List<DictTestItemType> list=getSession().createQuery("From DictTestItemType o WHERE o.tiType= ? ").setParameter(0, tiType).list();
		if(null!=list &&list.size()>0){
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public boolean isExistByTiType(String tiType, String tiCode) {
		List<DictTestItemType> list=getSession().createQuery("From DictTestItemType o WHERE o.tiType= ? AND o.tiCode != ? ")//
		.setParameter(0, tiType)//
		.setParameter(1, tiCode)
		.list();
		if(null!=list &&list.size()>0){
			return true;
		}
		return false;
	}

}
