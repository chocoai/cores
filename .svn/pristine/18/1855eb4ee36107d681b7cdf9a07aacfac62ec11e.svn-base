package com.lanen.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Regulation;

@Service
public class RegulationServiceImpl extends BaseLongDaoImpl<Regulation> implements RegulationService{

	@SuppressWarnings("unchecked")
	public boolean isExist(String regulationName) {
		List<Regulation> list =this.getSession()
		.createQuery("FROM Regulation r WHERE  r.regulationName = ? ")//
		.setParameter(0, regulationName)//
		.list();
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}

	public Long saveReturnId(Regulation entity) {
		super.save(entity);
		return entity.getId();
	}

	public void updateList(List<Regulation> list) {
		if(null!=list&&list.size()>0){
			for(Regulation obj:list){
				obj.setSetTime(new Date());
				update(obj);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public Regulation getByName(String regulationName) {
		List<Regulation> list =this.getSession()
		.createQuery("FROM Regulation r WHERE  r.regulationName = ? ")//
		.setParameter(0, regulationName)//
		.list();
		if(null!=list&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public boolean isOverTime(int updatePasswordTime) {
		Regulation regulation =getByName("密码有效期（天）");
		int time=0;
		if(null==regulation.getSetValue()||"".equals(regulation.getSetValue().trim())){
			time=Integer.parseInt(regulation.getDefaultValue().trim());
		}else{
			time=Integer.parseInt(regulation.getSetValue().trim());
		}
		if(time>updatePasswordTime){
			return false;
		}else{
			return true;
		}
	}
	

//	public void saveWithTime(Regulation regulation) {
//		regulation.setCreateTime(new Date());
//		updateFlag();
//		save(regulation);
//	}
//	public void updateFlag(){
//		 Regulation regulation= (Regulation) this.getSession().createQuery("FROM Regulation r WHERE r.flag='启用'").uniqueResult();
//		 regulation.setFlag("停用");
//		 update(regulation);
//	}
//	public void updatewithFlag(Regulation regulation) {
//		updateFlag();
//		update(regulation);
//		
//	}
	

}
