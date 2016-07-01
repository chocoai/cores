package com.lanen.service.path;


import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.path.TblAnatomyReqAttachedViscera;
/**
 * 解剖申请-脏器称重-附加脏器   serviceImpl
 *@author 
 */
@Service
public class TblAnatomyReqAttachedVisceraServiceImpl extends BaseDaoImpl<TblAnatomyReqAttachedViscera>
		implements TblAnatomyReqAttachedVisceraService {

	@SuppressWarnings("unchecked")
	public List<TblAnatomyReqAttachedViscera> getListByPid(String Pid) {
		String hql="from TblAnatomyReqAttachedViscera where anatomyReqVisceraWeighID=:Pid";
		List<TblAnatomyReqAttachedViscera> list=getSession().createQuery(hql).setParameter("Pid", Pid).list();
		return list;
	}

	public String getAttachedVisceraNamesByPid(String id) {
		
		String attachedVisceraNames = "";
		List<TblAnatomyReqAttachedViscera>  tblAnatomyReqAttachedVisceraList = getListByPid(id);
		if(null != tblAnatomyReqAttachedVisceraList){
			int i = 0;
			for(TblAnatomyReqAttachedViscera obj :tblAnatomyReqAttachedVisceraList){
				String visceraName = obj.getVisceraName();
				if(i == 0){
					attachedVisceraNames = visceraName;
				}else{
					attachedVisceraNames = attachedVisceraNames+ "、"+visceraName;
				}
				i++;
			}
		}
		return attachedVisceraNames;
	}

	

}
