package com.lanen.service.clinicaltest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.clinicaltest.PoolSpecimenId;
import com.lanen.util.DateUtil;

@Service
public class BillNoServiceImpl extends BaseLongDaoImpl<PoolSpecimenId> implements BillNoService{

	public String getNextBillNo(int  item) {
		String nextBillNo="";
		PoolSpecimenId billNo=getById(new Long(item));
		String today =DateUtil.dateToString(new Date(), "yyyy-MM-dd");
		String todayPrefix=DateUtil.dateToString(new Date(), "yyMMdd");
		if(today.equals(billNo.getCurrentDate())){
			//设置当前流水号
			
			int t =(int) Math.pow(10, billNo.getSerialNumLen());
			t=t+Integer.parseInt(billNo.getCurrentSerialNum());
			t++;
			String s=t+"";
			billNo.setCurrentSerialNum(s.substring(1));
			nextBillNo=billNo.getPrefix()+s.substring(1);
		}else{
			billNo.setCurrentDate(today);
			billNo.setPrefix(todayPrefix);
			//血凝数据比较特殊   --连续的号必须从101开始
			if(item==3){
				switch (billNo.getSerialNumLen()) {
				case 2:
					nextBillNo=billNo.getPrefix()+"11";
					billNo.setCurrentSerialNum("11");
					break;
				case 3:
					nextBillNo=billNo.getPrefix()+"101";
					billNo.setCurrentSerialNum("101");
					break;
				case 4:
					nextBillNo=billNo.getPrefix()+"1001";
					billNo.setCurrentSerialNum("1001");
					break;
				case 5:
					nextBillNo=billNo.getPrefix()+"10001";
					billNo.setCurrentSerialNum("10001");
					break;
					
				default:
					break;
				}
			}else{
				switch (billNo.getSerialNumLen()) {
				case 2:
					nextBillNo=billNo.getPrefix()+"01";
					billNo.setCurrentSerialNum("01");
					break;
				case 3:
					nextBillNo=billNo.getPrefix()+"001";
					billNo.setCurrentSerialNum("001");
					break;
				case 4:
					nextBillNo=billNo.getPrefix()+"0001";
					billNo.setCurrentSerialNum("0001");
					break;
				case 5:
					nextBillNo=billNo.getPrefix()+"00001";
					billNo.setCurrentSerialNum("00001");
					break;
					
				default:
					break;
				}
			}
		}
		update(billNo);
		return nextBillNo;
	}

	public List<String> getMuchNextBillNo(int item, int selected) {
		List<String> list=new ArrayList<String>();
		for(int i=0;i<selected;i++){
			list.add(getNextBillNo(item));
		}
		return list;
	}

}
