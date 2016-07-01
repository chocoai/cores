package com.lanen.service.clinicaltest;


import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.clinicaltest.ComputerAndInstrument;

public interface ComputerAndInstrumentService extends BaseDao<ComputerAndInstrument> {
	/**
	 * 保存
	 * @param obj
	 */
	public void saveOne(ComputerAndInstrument obj);
	
	/**
	 * 根据计算机名字获取所有的有关联的仪器
	 * @param computerName
	 * @param testItem
	 * @return
	 */
	public List<ComputerAndInstrument> getByComputerName(String computerName);
	/**
	 * 根据计算机名字和测试项目获取所有的有关联的仪器
	 * @param computerName
	 * @param testItem
	 * @return
	 */
	public List<ComputerAndInstrument> getByComputerNameAndInstrumentId(String computerName, String instrumentId);

}
