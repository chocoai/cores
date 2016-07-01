package com.lanen.service.path;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.path.DictLevel;

@Service
@Transactional
public class DictLevelServiceImpl extends BaseDaoImpl<DictLevel> implements DictLevelService{

	public boolean isExistByLevel(String level) {
		String hql = "from DictLevel where level = ? ";
		List<?> list = getSession().createQuery(hql)
									.setParameter(0, level)
									.list();
		return (null != list && list.size() > 0);
	}

}
