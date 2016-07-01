package com.lanen.service.qa;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.qa.DictChkItemQAFileReg;

public interface DictChkItemQAFileRegService extends BaseDao<DictChkItemQAFileReg> {

	List<DictChkItemQAFileReg> getByChkItemId(String chkItemId);
	List<DictChkItemQAFileReg> getByFileRegId(String fileRegId);
	DictChkItemQAFileReg getByChkItemIdAndChkTblId(String chkItemId,String fileRegId);
	
}
