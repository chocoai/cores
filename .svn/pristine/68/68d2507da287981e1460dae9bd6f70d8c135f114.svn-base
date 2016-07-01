package com.lanen.service.contract;


import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.contract.ContractPoolNum;
import com.lanen.util.DateUtil;
@Service
public class ContractPoolNumServiceImpl  extends BaseLongDaoImpl<ContractPoolNum> implements  ContractPoolNumService{
	
		public String getNextSDCommissionSerizlnumber() {
				
				String year = DateUtil.getNow("yyyy");
				
				ContractPoolNum poolNumber = getById(1L);
				if(null == poolNumber ){
					
					poolNumber = new ContractPoolNum();
					poolNumber.setId(1L);
					poolNumber.setCurrentYear(year);
					poolNumber.setPrefix(year+"-");
					poolNumber.setSerialNumLen(3);
					poolNumber.setCurrentSerialNum("001");
					
					getSession().save(poolNumber);
					
					return year+"-001";
				}else{
					if(year.equals(poolNumber.getCurrentYear())){
						int t = 1000;
						int currentSerialNum = Integer.parseInt(poolNumber.getCurrentSerialNum());
						if(currentSerialNum >=(999)){
							t=10000;
						}
						t = t+currentSerialNum;
						t++;
						String s=t+"";
						poolNumber.setCurrentSerialNum(s.substring(1));
						
						getSession().update(poolNumber);
						
						return poolNumber.getPrefix()+s.substring(1);
					}else{
						poolNumber.setCurrentYear(year);
						poolNumber.setPrefix(year+"-");
						poolNumber.setSerialNumLen(3);
						poolNumber.setCurrentSerialNum("001");
						
						getSession().update(poolNumber);
						
						return year+"-001";
					}
				}
			}
		
		public String getNextContractPoolNum() {
			String year = DateUtil.getNow("yyyy");
			ContractPoolNum poolNumber = getById(1L);
			if(null == poolNumber ){
				poolNumber = new ContractPoolNum();
				poolNumber.setId(1L);
				poolNumber.setCurrentYear(year);
				poolNumber.setPrefix(year+"-");
				poolNumber.setSerialNumLen(3);
				poolNumber.setCurrentSerialNum("001");
				poolNumber.setKind(1);
				getSession().save(poolNumber);
				return year+"-001";
			}else{
				if(year.equals(poolNumber.getCurrentYear())){
					int t = 1000;
					int currentSerialNum = Integer.parseInt(poolNumber.getCurrentSerialNum());
					if(currentSerialNum >=(999)){
						t=10000;
					}
					t = t+currentSerialNum;
					t++;
					String s=t+"";
					poolNumber.setCurrentSerialNum(s.substring(1));
					
					getSession().update(poolNumber);
					
					return poolNumber.getPrefix()+s.substring(1);
				}else{
					poolNumber.setCurrentYear(year);
					poolNumber.setPrefix(year+"-");
					poolNumber.setSerialNumLen(3);
					poolNumber.setCurrentSerialNum("001");
					poolNumber.setKind(1);
					getSession().update(poolNumber);
					
					return year+"-001";
				}
			}
		}

		public int getTblTestItemTotal(String contractCode) {
			if(null != contractCode){
				List<?> list = getSession().createQuery("From TblTestItem where contractCode = ? ")
							.setParameter(0, contractCode)
							.list();
				if(null != list && list.size()>0){
					return list.size();
				}else{
					return 0;
				}
			}else{
				return 0;
			}
			
		}



}
