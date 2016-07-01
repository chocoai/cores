package com.lanen.service.qa;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.qa.DictQAStatementTemple;

public interface DictQAStatementTempleService extends BaseDao<DictQAStatementTemple>{
	
	List<DictQAStatementTemple> getByTiCode(String tiCode );
	
	boolean isExistByTiAndName(String tiCode,String templeName);
	
	boolean isExistByTiAndNameExceptOne(String tiCode,String templeName,String templeId);

}
