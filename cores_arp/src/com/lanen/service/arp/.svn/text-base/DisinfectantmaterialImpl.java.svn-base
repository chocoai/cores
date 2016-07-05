package com.lanen.service.arp;

import java.util.List;

import javax.jms.Session;
import javax.management.Query;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Disinfectantmaterial;
@Service
public class DisinfectantmaterialImpl extends
		BaseLongDaoImpl<Disinfectantmaterial> implements
		DisinfectantmaterialService {

	@SuppressWarnings("unchecked")
	public List<Disinfectantmaterial> getListByDisinfectantId(
			Long disinfectantId) {
		String hql="from Disinfectantmaterial where disinfectant_id=:disinfectant_id";
		List<Disinfectantmaterial> list=getSession().createQuery(hql).setParameter("disinfectant_id", disinfectantId).list();
		
		return list;
	}

	public void deleteByDisinfectantId(Long id) {
		
	}

	

}
