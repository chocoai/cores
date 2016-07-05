package com.lanen.service.arp;

import java.util.List;
import java.util.Map;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Approval;

public interface ApprovalService extends BaseLongDao<Approval> {

	Map<String,Object> getAllApprovalInfo(String page,String rows);
	List<Map<String,Object>> getApprovalMap();
}
