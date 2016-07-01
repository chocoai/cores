package com.lanen.service.studyplan;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.studyplan.DictAnimalType;
@Service
public class DictAnimalTypeServiceImpl extends BaseDaoImpl<DictAnimalType> implements  DictAnimalTypeService{

	@SuppressWarnings("unchecked")
	public List<DictAnimalType> findAllOrderByOrderNo() {
		List<DictAnimalType> list  =getSession().createQuery("FROM DictAnimalType t ORDER BY t.orderNo asc").list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public DictAnimalType getByName(String typeName) {
		List<DictAnimalType> list  =getSession().createQuery("FROM DictAnimalType t WHERE t.typeName = ? ")//
		.setParameter(0, typeName)//
		.list();
		if(null!=list && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public boolean isBigAnimal(String animalType) {
		List<DictAnimalType> list  =getSession().createQuery("FROM DictAnimalType t WHERE t.typeName = ? ")//
		.setParameter(0, animalType)//
		.list();
		if(null!=list && list.size()>0){
			
			return list.get(0).getIsBigAnimal()==1;
		}
		return false;
	}

}
