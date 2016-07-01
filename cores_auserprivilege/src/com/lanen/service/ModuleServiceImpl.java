package com.lanen.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.Module;
import com.lanen.model.Systems;

@Service
public class ModuleServiceImpl extends BaseDaoImpl<Module> implements ModuleService{

	@SuppressWarnings("unchecked")
	public boolean isIdNameExist(String name) {
		List<Systems> list=getSession().createQuery("FROM Module m WHERE m.moduleName = ? OR m.id=? ")//
		.setParameter(0, name)//
		.setParameter(1, name)
		.list();
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}

}
