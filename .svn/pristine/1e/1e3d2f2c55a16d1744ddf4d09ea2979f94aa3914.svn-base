package com.lanen.service.schdeule;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.jsonAndModel.ComboTreeModel;
import com.lanen.model.schedule.TblAnimalHouse;
import com.lanen.model.schedule.TblStudyInfo;
import com.lanen.model.schedule.TblStudyRes;
@Service
public class TblStudyInfoServiceImpl extends BaseDaoImpl<TblStudyInfo> implements TblStudyInfoService{
	@Resource
	private TblAnimalHouseService tblAnimalHouseService;
	
	public List<ComboTreeModel> loadAnimalHouseTable() {
		// TODO Auto-generated method stub
		List<ComboTreeModel> list = new ArrayList<ComboTreeModel>();
		List<TblAnimalHouse> build  = tblAnimalHouseService.getresKind(1);
        List<TblAnimalHouse> area = tblAnimalHouseService.getresKind(2);
    	ComboTreeModel ctm =null;
        for (TblAnimalHouse obj1:build) {
			ctm = new ComboTreeModel();
			ctm.setId(obj1.getId());
			ctm.setText(obj1.getResName());
			ctm.setIconCls("icon-house");
			ctm.setState("open");
			List<ComboTreeModel> children = new ArrayList<ComboTreeModel>();
				for (TblAnimalHouse obj2:area) {
					if(obj2.getParentId().equals(obj1.getId())){
						ComboTreeModel comboTreeModel = new ComboTreeModel();
						comboTreeModel.setId(obj2.getId());
						comboTreeModel.setText(obj2.getResName());
						comboTreeModel.setIconCls("icon-door");
						children.add(comboTreeModel);
					}
				}
			if(children.size()>0){
				ctm.setChildren(children);
				list.add(ctm);
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public boolean isExistByStudyNo(String studyNo) {
		List<TblStudyInfo> list=getSession().createQuery("FROM TblStudyInfo  d WHERE  d.studyNo = ?   ")//
		.setParameter(0, studyNo)
		.list();
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public TblStudyInfo getByStudyNo(String studyNo) {
		List<TblStudyInfo> list=getSession().createQuery("FROM TblStudyInfo  d WHERE  d.studyNo = ?   ")//
		.setParameter(0, studyNo)
		.list();
		if(null!=list&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public void signStudyInfoUpdate(List<TblStudyRes> list) {
		 for(TblStudyRes studyRes:list){
			 TblStudyInfo studyInfo = getByStudyNo(studyRes.getStudyNo());
			 studyInfo.setResID(studyRes.getAuditId());
			 getSession().update(studyInfo);
		 }
		
	}

}
