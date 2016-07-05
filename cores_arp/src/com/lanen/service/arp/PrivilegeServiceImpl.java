package com.lanen.service.arp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.Privilege;
@Service
public class PrivilegeServiceImpl extends BaseDaoImpl<Privilege> implements
		PrivilegeService {


	public List<Privilege> getByPrivilegeNameList(List<String> privilegeNameList) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getAllPrivilegeUrl() {
		List<String> privilegeUrlList=new ArrayList<String>();
		List<Privilege> privilegeList=findAll();
		if(null!=privilegeList&&privilegeList.size()>0){
			for(Privilege obj:privilegeList){
				if(null!=obj.getUrl()&&!"".equals(obj.getUrl())){
					privilegeUrlList.add(obj.getUrl());
				}
			}
			return privilegeUrlList;
		}
		return null;
	}


}
