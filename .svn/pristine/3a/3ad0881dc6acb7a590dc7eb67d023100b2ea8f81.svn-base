package com.lanen.service.studyplan;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.studyplan.DictAnimalStrain;
@Service
public class DictAnimalStrainServiceImpl extends BaseDaoImpl<DictAnimalStrain> implements  DictAnimalStrainService{

	@SuppressWarnings("unchecked")
	public List<DictAnimalStrain> findByTypeId(String id) {
		List<DictAnimalStrain> list = getSession().createQuery("FROM DictAnimalStrain d where d.animalTypeId = ? ORDER BY d.orderNo ")//
		.setParameter(0, id)//
		.list();
		return list;
	}

}
