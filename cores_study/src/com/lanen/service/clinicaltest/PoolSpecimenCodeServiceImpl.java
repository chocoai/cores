package com.lanen.service.clinicaltest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.clinicaltest.PoolSpecimenCode;
import com.lanen.model.studyplan.TblClinicalTestReqIndex2;
import com.lanen.service.studyplan.TblClinicalTestReqIndex2Service;
import com.lanen.util.DateUtil;

@Service
public class PoolSpecimenCodeServiceImpl extends BaseDaoImpl<PoolSpecimenCode> implements PoolSpecimenCodeService{

	@Resource
	private TblClinicalTestReqIndex2Service tblClinicalTestReqIndex2Service;
	
	public Map<String, String> getMuchNextSpecimenCode(String studyNo,
			int reqNo, int testItem, List<String> animalIdList) {
		String currentDateStr = DateUtil.dateToString(new Date(), "yyyy-MM-dd");
		if(null == studyNo || "".equals(studyNo)|| reqNo<1
				||testItem <1 ||testItem >4 || null == animalIdList ||
				animalIdList.size()<0){
//			map.put("success", "false");
//			map.put("msg", "参数问题");
			
			System.out.println(currentDateStr+"产生检验编号，参数有为问题，studyNo："+studyNo+" reqNo:"+reqNo
					+" testItem:"+testItem+" animalIsList:"+animalIdList);
			return null;
		}
		Map<String, String> map = new HashMap<String,String>();
		
		//1. 判断 申请单是否 已经产生检验编号
		boolean isExist = isExistSpecimenCode(studyNo,reqNo,testItem);
		
		//2.若否，则产生
		if(!isExist){
			//2.1  //获得申请单，动物列表
			List<TblClinicalTestReqIndex2> tblClinicalTestReqIndex2List = tblClinicalTestReqIndex2Service.findByStudyNoAndReqNO(studyNo, reqNo);
			if(null == tblClinicalTestReqIndex2List || tblClinicalTestReqIndex2List.size()<1){
				System.out.println(currentDateStr+"产生检验编号,---------------查询申请单对应动物时出错--------------------------------------");
				return null;
			}
			//2.2 获得下一个编号
			String nextSpecimenCode = getNextSpecimenCode(testItem);
			if(null ==nextSpecimenCode || "".equals(nextSpecimenCode)){
				System.out.println(currentDateStr+"产生检验编号,---------------检验编号超界--------------------------------------");
				return null;
			}
			//2.3 保存  检验编号列表
			PoolSpecimenCode poolSpecimenCode = null;
			String id ="";
			int i = 0;
			for( TblClinicalTestReqIndex2 tblClinicalTestReqIndex2:tblClinicalTestReqIndex2List){
				poolSpecimenCode  = new PoolSpecimenCode();
				id = getKey();
				poolSpecimenCode.setId(id);
				poolSpecimenCode.setStudyNo(studyNo);
				poolSpecimenCode.setReqNo(reqNo);
				poolSpecimenCode.setTestItem(testItem);
				poolSpecimenCode.setCreateDate(currentDateStr);
				
				String goalSpecimenCode = plusSpecimenCode(testItem,nextSpecimenCode,i);
				String animalId = tblClinicalTestReqIndex2.getAnimalId();
				
				poolSpecimenCode.setSpecimenCode(goalSpecimenCode);
				poolSpecimenCode.setAnimalId(animalId);
				poolSpecimenCode.setFlag(0);
				getSession().save(poolSpecimenCode);
				if(animalIdList.contains(animalId)){
					map.put(animalId, goalSpecimenCode);
				}
				i++;
			}
		}else{
			//3.根据动物列表、申请单检测项目、申请单（或含日期）      查询检验编号
			String sql ="select animalId , specimenCode from poolSpecimenCode where  " +
					" studyNo = :studyNo  and reqNo = :reqNo " +
					" and testItem = :testItem " ;
			if(testItem == 4){
				sql = sql +" and createDate =:createDate ";
			}
			sql = sql+" and  animalId in (:animalIdList) ";
			Query query = getSession().createSQLQuery(sql);
			query.setParameter("studyNo", studyNo);
			query.setParameter("reqNo", reqNo);
			query.setParameter("testItem", testItem);
			query.setParameterList("animalIdList", animalIdList);
			if(testItem == 4){
				query.setParameter("createDate", currentDateStr);
			}
			List<?> list = query.list();
			
			if(null != list && list.size()>0){
				for(Object obj:list){
					Object[] objs = (Object[]) obj;
					map.put((String)objs[0],(String) objs[1]);
				}
			}else{
				return null;
			}
			
		}
		if(testItem == 4){
			map.put("currentDate", currentDateStr);
		}
		return map;
	}

	/**检验编号  +  i
	 * @param testItem
	 * @param specimenCode
	 * @param i
	 * @return
	 */
	private String plusSpecimenCode(int testItem,String specimenCode,int i){
		String goalSpecimenCode ="";
		if(testItem ==1 || testItem ==2 || testItem ==3){
			if(specimenCode.length()!=9){
				return null;
			}
			//"140730"
			goalSpecimenCode = specimenCode.substring(0, 6);
			//"101"
			String numberStr = specimenCode.substring(6);
			//1101
			int number = Integer.valueOf(1+numberStr);
			number=number+i;
			if(number>1999){
				//超界
				return null;
			}
			
			numberStr =number+"";
			numberStr = numberStr.substring(1);
			goalSpecimenCode =goalSpecimenCode+numberStr;
		}else{
			if(specimenCode.length()!=4){
				return null;
			}
			String numberStr = 1+specimenCode;
			int number = Integer.valueOf(numberStr);
			number=number+i;
			if(number>19999){
				//超界
				return null;
			}
			
			numberStr =number+"";
			numberStr = numberStr.substring(1);
			goalSpecimenCode = numberStr;
		}
		
		return goalSpecimenCode;
	};
	
	/**获取下一个检验编号，当前项目的指定日期
	 * @param testItem
	 * @return
	 */
	private String getNextSpecimenCode(int testItem) {
		Date date = new Date();
		String currentDateStr = DateUtil.dateToString(date, "yyyy-MM-dd");
		String maxSpecimenCode = "";
		String nextSpecimenCode = "";
		Object ttt = getSession().createQuery("SELECT MAX(specimenCode) FROM PoolSpecimenCode where testItem = :testItem and createDate = :createDate ")
		.setParameter("testItem", testItem) 
		.setParameter("createDate", currentDateStr) 
		.uniqueResult();
		if(null!=ttt ){
			maxSpecimenCode =(String) ttt;
			if(testItem ==1 || testItem ==2 || testItem ==3){
				String numberStr = maxSpecimenCode.substring(6);
				int number = Integer.valueOf(1+numberStr);
				number = number /10;
				number=number+1;
				if(number>199){
					//超界
					return null;
				}
				number = number*10;
				number =number+1;
				
				numberStr =number+"";
				numberStr = numberStr.substring(1);
				nextSpecimenCode = DateUtil.dateToString(date, "yyMMdd")+numberStr;
			}else{
				String numberStr = 1+maxSpecimenCode;
				int number = Integer.valueOf(numberStr);
				number = number /10;
				number=number+1;
				if(number>1999){
					//超界
					return null;
				}
				number = number*10;
				number =number+1;
				
				numberStr =number+"";
				numberStr = numberStr.substring(1);
				nextSpecimenCode = numberStr;
			}
			
		}else{
			if(testItem ==1 || testItem ==2){
				nextSpecimenCode = DateUtil.dateToString(date, "yyMMdd")+"001";
			}else if(testItem == 3){
				nextSpecimenCode = DateUtil.dateToString(date, "yyMMdd")+"101";
			}else{
				nextSpecimenCode = "0001";
			}
		}
		return nextSpecimenCode;
	}

	public boolean isExistSpecimenCode(String studyNo, int reqNo, int testItem) {
		String currentDateStr = DateUtil.dateToString(new Date(), "yyyy-MM-dd");
		
		String sql = "From PoolSpecimenCode where  studyNo = :studyNo  and reqNo = :reqNo " +
				" and testItem = :testItem " ;
//		if(testItem == 4){
			sql = sql +" and createDate =:createDate ";
//		}
		Query query = getSession().createQuery(sql);
		query.setParameter("studyNo", studyNo);
		query.setParameter("reqNo", reqNo);
		query.setParameter("testItem", testItem);
//		if(testItem == 4){
			query.setParameter("createDate", currentDateStr);
//		}
		List<?> list = query.list();
		if(null != list && list.size()>0){
			return true;
		}
		return false;
	}

	public boolean isExistSpecimenCode(String studyNo, int reqNo) {
		String sql = "From PoolSpecimenCode where  studyNo = :studyNo  and reqNo = :reqNo "
				+ "  ";
		Query query = getSession().createQuery(sql);
		query.setParameter("studyNo", studyNo);
		query.setParameter("reqNo", reqNo);
		List<?> list = query.list();
		if (null != list && list.size() > 0) {
			return true;
		}
		return false;
	}

}
