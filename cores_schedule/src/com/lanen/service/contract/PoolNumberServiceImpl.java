package com.lanen.service.contract;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.contract.PoolNumber;
import com.lanen.util.DateUtil;

@Service
public class PoolNumberServiceImpl extends BaseLongDaoImpl<PoolNumber> implements PoolNumberService {

	public String getNextSDCommissionSerizlnumber() {
		
		String year = DateUtil.getNow("yyyy");
		
		PoolNumber poolNumber = getById(1L);
		if(null == poolNumber ){
			
			poolNumber = new PoolNumber();
			poolNumber.setId(1L);
			poolNumber.setCurrentYear(year);
			poolNumber.setPrefix(year+"-");
			poolNumber.setSerialNumLen(4);
			poolNumber.setCurrentSerialNum("0001");
			
			getSession().save(poolNumber);
			
			return year+"-0001";
		}else{
			if(year.equals(poolNumber.getCurrentYear())){
				int t = 10000;
				int currentSerialNum = Integer.parseInt(poolNumber.getCurrentSerialNum());
//				if(currentSerialNum >=(999)){
//					t=10000;
//				}
				t = t+currentSerialNum;
				t++;
				String s=t+"";
				poolNumber.setCurrentSerialNum(s.substring(1));
				
				getSession().update(poolNumber);
				
				return poolNumber.getPrefix()+s.substring(1);
			}else{
				poolNumber.setCurrentYear(year);
				poolNumber.setPrefix(year+"-");
				poolNumber.setSerialNumLen(4);
				poolNumber.setCurrentSerialNum("0001");
				
				getSession().update(poolNumber);
				
				return year+"-0001";
			}
		}
	}

}
