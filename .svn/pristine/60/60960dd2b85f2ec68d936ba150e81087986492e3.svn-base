package com.lanen.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.UserRoleLog;

@Service
public class UserRoleLogServiceImpl extends BaseLongDaoImpl<UserRoleLog> implements UserRoleLogService{

	public void saveList(List<UserRoleLog> userRoleLogList) {
		if(null!=userRoleLogList){
			for(UserRoleLog userRoleLog:userRoleLogList){
				this.save(userRoleLog);
			}
		}
		
	}

}
