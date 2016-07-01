package com.lanen.service.contract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.contract.TblStudyItem;
import com.lanen.model.contract.TblStudyItemHis;
import com.lanen.model.contract.TblStudySchedule;
import com.lanen.model.contract.TblTestItem;
import com.lanen.model.studyplan.DictStudyType;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.studyplan.DictStudyTypeService;
import com.lanen.util.DateUtil;

@Service
public class TblStudyItemServiceImpl extends BaseDaoImpl<TblStudyItem> implements TblStudyItemService {

	@Resource
	private TblStudyScheduleService tblStudyScheduleService;
	@Resource
	private TblESService tblESService;
	@Resource
	private TblESLinkService tblESLinkService;
	
	public boolean isExistByStudyNo(String studyNo) {
		if(null != studyNo){
			List<?> list = getSession().createQuery("From TblStudyItem where studyNo = ? ")
						.setParameter(0, studyNo)
						.list();
			if(null != list && list.size()>0){
				return true;
			}else{
				return false;
			}
		}
		return true;
	}

	public List<Map<String, String>> findDictAnimalTypeOrderByOrderNo() {
		String  sql = "   select animaltype.id,animaltype.typeName"+
						" from CoresStudy.dbo.dictAnimalType animaltype "+
						" order by animaltype.orderNo";
		List<?> list = getSession().createSQLQuery(sql).list();
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		if(null != list && list.size()>0){
			Map<String,String> map = null;
			map = new HashMap<String, String>();
			map.put("id", "-1");
			map.put("text","&nbsp;");
			mapList.add(map);
			for(Object obj:list){
				map = new HashMap<String, String>();
				Object[] objs = (Object[]) obj;
				map.put("id", (String)objs[0]);
				map.put("text",(String)objs[1]);
				mapList.add(map);
			}
		}
		return mapList;
	}

	public List<Map<String, String>> findDictAnimalStrainByTypeId(
			String animalTypeId) {
		String  sql = "   select animalstrain.strainName"+
						" from CoresStudy.dbo.dictAnimalStrain animalstrain"+
						" where animalstrain.animalTypeId = ? "+
						" order by animalstrain.strainName";
		List<?> list = getSession().createSQLQuery(sql)
									.setParameter(0, animalTypeId)
									.list();
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		if(null != list && list.size()>0){
			Map<String,String> map = null;
			for(Object obj:list){
				map = new HashMap<String, String>();
				map.put("id", (String)obj);
				map.put("text",(String)obj);
				mapList.add(map);
			}
		}
		return mapList;
	}

	public List<TblStudyItem> loadStudyItemsByCondition(String type,
			Date start, Date end, String name,boolean readAll,String reader) {
		String sql="SELECT si.[id],si.[contractCode],si.[tiNo],si.[studyTypeCode],si.[studyName],si.[studyNo] "+
                   ",si.[glpFlag],si.[remark],si.[sdCode],si.[sd],si.[studyState],si.finishDate  "+ 
                   "FROM [CoresContract].[dbo].[tblStudyItem] as si join [CoresContract].[dbo].[tblContract] as ct on "+
                   "si.contractCode=ct.contractCode join [CoresContract].[dbo].[tblTestItem] as ti on "+ 
                   "si.tiNo=ti.tiNo where  si.state!=0 and  ti.state!=0 and  ct.contractState !=0  and ct.signingDate between :minDate and :maxDate ";
		if(null !=type && !"".equals(type)){
			sql=sql+" and ti.tiCode =:type ";
		}
		if(null !=name && !"".equals(name) ){
			sql = sql +"  and ( si.studyNo like '%"+name+"%' or si.studyName like '%"+name+"%' or si.contractCode like '%"+name+"%' or si.tiNo like '%"+name+"%' or si.sd like '%"+name+"%')";
		}
		if(!readAll){
        	sql = sql+" and ct.[operator] = :reader ";
        }
		List<TblStudyItem> list = new ArrayList<TblStudyItem>();
		Query query = getSession().createSQLQuery(sql);
		query.setParameter("minDate", start);
		query.setParameter("maxDate", end);
		
		if(null !=type && !"".equals(type) ){
			query.setParameter("type", type);
		}
		if(!readAll){
			query.setParameter("reader", reader);
		}
		List<?> listSql = query.list();
		if(null != listSql){
			for(Object obj:listSql){
				Object[] objs = (Object[]) obj;
				TblStudyItem t=new TblStudyItem();
	            t.setId((String)objs[0]);
	            t.setContractCode((String)objs[1]);
	            t.setTiNo((String)objs[2]);
	            t.setStudyTypeCode((String)objs[3]);
	            t.setStudyName((String)objs[4]);
	            t.setStudyNo((String)objs[5]);
	            t.setGlpFlag((Integer)objs[6]);
	            t.setRemark((String)objs[7]);
	            t.setSdCode((String)objs[8]);
	            t.setSd((String)objs[9]);
	            t.setStudyState((Integer)objs[10]);
	            if(null != objs[11] && !objs[11].equals("")){
	            	 t.setFinishDateStr(DateUtil.dateToString((Date)objs[11], "yyyy-MM-dd"));
	            }
	           
	           //进度
				String  progress= tblStudyScheduleService.getPercentageByStudyNo(t.getStudyNo());
				double aa=Double.parseDouble(progress);
			    String    p  =Double.toString(aa * 100);
				TblStudySchedule schedule =  tblStudyScheduleService.getByStudyNoMaxStudySchedule(t.getStudyNo());
				t.setProgress(p+"#"+schedule.getNodeName()+"#"+DateUtil.dateToString(schedule.getActualDate(), "yyyy-MM-dd")+"#"+DateUtil.dateToString(schedule.getPlanDate(), "yyyy-MM-dd"));
	            
				list.add(t);
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TblStudyItem> getListByTiNo(String tiNo) {
		List<TblStudyItem> list = getSession().createQuery("From TblStudyItem where tiNo = ? ")
												.setParameter(0, tiNo)
												.list();
		return list;
	}

	public boolean isHasSDById(String id) {
		TblStudyItem tblStudyItem = getById(id);
		if(tblStudyItem != null ){
			return (tblStudyItem.getSdState() == 1 && null != tblStudyItem.getSd());
		}
		return false;
	}


	public Map<Integer, Integer> getCountStudyItemsByState(Date startDate,Date endDate,String tiCode,String operator) {
		String sql=" SELECT st.studyState,count(st.id) FROM [CoresContract].[dbo].[tblStudyItem] as st join "+
        "  [CoresContract].[dbo].[tblContract] as ct on st.contractCode=ct.contractCode join "+ 
		"  [CoresContract].[dbo].[tblTestItem] as ti on st.tiNo=ti.tiNo" +
        "  where ct.contractState!=0 and st.state != 0 and ct.signingDate between :startDate and :endDate  " ;
        if(operator != null && !operator.equals("")){
            sql = sql + "and ct.operator = :operator " ;
        }
        if(tiCode != null  && !tiCode.equals("")){
      	  sql = sql +" and ti.tiCode = :tiCode ";
        }  
          sql = sql + " group by st.studyState ";
          Query query = getSession().createSQLQuery(sql);
          query.setParameter("startDate", startDate);
  		  query.setParameter("endDate", endDate);
          if(operator != null && !operator.equals("")){
         	query.setParameter("operator", operator);
          }
          if(tiCode != null  && !tiCode.equals("")){	  
        	  query.setParameter("tiCode", tiCode);
          }
  		List<?> list = query.list();
		Map<Integer, Integer> map=new HashMap<Integer, Integer>();
		if(null!=list){
			for(Object obj:list){
				Object[] objs = (Object[]) obj;
				map.put((Integer)objs[0], (Integer)objs[1]);
			}
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMapListForImprot(String studyNo) {
		List<Map<String,Object>> mapList = null;
		if(null != studyNo){
			String sql = "select tasd.sd as sd,tasd.tiName as tiName,tasd.studyName as studyName," +
						" tasd.studyNo as studyNo,tasd.poolNum as serialnumber,tasd.partner"+
						" from CoresSchedule.dbo.tblAppointSD as tasd"+
						" where tasd.studyNo = ? and tasd.appointSignID is not null and  tasd.appointSignID !='' and state = 1  ";
			mapList = getSession().createSQLQuery(sql)
								  .setParameter(0, studyNo)
								  .setResultTransformer(new ResultTransformer(){
									private static final long serialVersionUID = -2046383977593075250L;

									public List transformList(List list) {
										return list;
									}

									public Object transformTuple(Object[] values,
											String[] columns) {
										TblStudyItem studyItem =getByStudyNoStudyItem((String)values[3]);
										String tiNo = studyItem.getTiNo();
										String testItemName =tblTestItemService.getTiNameByContractAndTiNo(studyItem.getContractCode(), tiNo);
										DictStudyType dictStudyType ;
										try{
										       dictStudyType = dictStudyTypeService.getById(studyItem.getStudyTypeCode());
											}catch(Exception e){
												dictStudyType = null ;
											}
											String studyNoName = "";
											if(null != dictStudyType && dictStudyType.getAnimalHave() == 1){
											studyNoName = testItemName+studyItem.getAnimalType()+studyItem.getStudyName();
										}else{
											studyNoName = testItemName+studyItem.getStudyName();
										}
										Map<String, Object> map = new LinkedHashMap<String, Object>(1);
									    map.put(columns[0], values[0]);
									   // map.put("tiNoStudyName", studyNoName); 
									    if(null != values[5] && !values[5].equals("")){
                                      	  map.put("tiNoStudyName", "任命您为    "+studyNoName+"    课题(课题编号："+values[3]+" )的负责人，并由 "+values[5]+" 等共同参与该课题的研究工作。"); 
									    }else{
									    	  map.put("tiNoStudyName", "任命您为    "+studyNoName+"    课题(课题编号："+values[3]+" )的负责人，并由　　　等共同参与该课题的研究工作。"); 
									    }
									    map.put(columns[3], values[3]);
									    map.put(columns[4], values[4]); 
									    String remark = getRemarkByStudyNo((String)values[3]);
									    if(null != remark && (!remark.equals(""))){
									    	map.put("remark", "注："+remark); 
									    }else{
									    	map.put("remark", ""); 
									    }
									    
									    return map;
									}
									  
								  })
								  .list();
		}
		return mapList;
	}
	
	public String getRemarkByStudyNo(String studyNo) {
		List<?> list1 = getSession().createSQLQuery("select remark from CoresSchedule.dbo.tblAppointSD where studyNo = ? and state = 1 ")
		.setParameter(0, studyNo)
		.list();
		if(null != list1 && list1.size()>0){
			return (String) list1.get(0);
		}else{
			return null;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public TblStudyItem getByStudyNoStudyItem(String studyNo) {
		List<TblStudyItem> list = getSession().createQuery("From TblStudyItem where studyNo = ? ")
		.setParameter(0, studyNo)
		.list();
		if(null != list && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<TblStudyItem> getByStudyNos(String[] studyNo) {
		List<TblStudyItem> list = getSession().createQuery("From TblStudyItem where studyNo in (:studyNos) ")
											.setParameterList("studyNos", studyNo)
											.list();
		return list;
		
	}
	/**
	 * 供试品
	 */
	@Resource
	private TblTestItemService tblTestItemService;
	@Resource
	protected DictStudyTypeService dictStudyTypeService;
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMapMoreListForImprot(
			List<String> studyNoList) {
		List<Map<String,Object>> mapList = null;
		if(null != studyNoList){
			String sql = "select tasd.sd as sd,tasd.tiName as tiName,tasd.studyName as studyName," +
						" tasd.studyNo as studyNo,tasd.poolNum as serialnumber,tasd.partner "+
						" from CoresSchedule.dbo.tblAppointSD as tasd"+
						" where tasd.studyNo in (:studyNoList) and tasd.appointSignID is not null and  tasd.appointSignID !='' and state = 1 ";
			mapList = getSession().createSQLQuery(sql)
								  .setParameterList("studyNoList", studyNoList)
								  .setResultTransformer(new ResultTransformer(){
									private static final long serialVersionUID = -2046383977593075250L;

									public List transformList(List list) {
										return list;
									}

									public Object transformTuple(Object[] values,
											String[] columns) {
										TblStudyItem studyItem =getByStudyNoStudyItem((String)values[3]);
										String tiNo = studyItem.getTiNo();
										String testItemName =tblTestItemService.getTiNameByContractAndTiNo(studyItem.getContractCode(), tiNo);
										   DictStudyType dictStudyType ;
										try{
									       dictStudyType = dictStudyTypeService.getById(studyItem.getStudyTypeCode());
										}catch(Exception e){
											dictStudyType = null ;
										}
										String studyNoName = "";
										if(null != dictStudyType && dictStudyType.getAnimalHave() == 1){
											studyNoName = testItemName+studyItem.getAnimalType()+studyItem.getStudyName();
										}else{
											studyNoName = testItemName+studyItem.getStudyName();
										}
										Map<String, Object> map = new LinkedHashMap<String, Object>(1);
									    map.put(columns[0], values[0]);
									   // map.put("tiNoStudyName", studyNoName);  
									    //values[5]
                                        if(null != values[5] && !values[5].equals("")){
                                        	  map.put("tiNoStudyName", "任命您为    "+studyNoName+"    课题(课题编号："+values[3]+" )的负责人，并由 "+values[5]+" 等共同参与该课题的研究工作。"); 
									    }else{
									    	  map.put("tiNoStudyName", "任命您为    "+studyNoName+"    课题(课题编号："+values[3]+" )的负责人，并由　　　等共同参与该课题的研究工作。"); 
									    }
									  
									    map.put(columns[3], values[3]);
									    map.put(columns[4], values[4]); 
									    String remark = getRemarkByStudyNo((String)values[3]);
									    if(null != remark && (!remark.equals(""))){
									    	map.put("remark", "注："+remark); 
									    }else{
									    	map.put("remark", ""); 
									    }
									    
									    return map;
									}
									  
								  })
								  .list();
		}
		return mapList;
	}
    
	public void addPrintNumber(List<String> studyNoList) {
		String sql = "update CoresSchedule.dbo.tblAppointSD  set printNumber = printNumber+1 where studyNo in (:studyNoList)";
		 Query query = getSession().createSQLQuery(sql);
     	 query.setParameterList("studyNoList", studyNoList);
     	 query.executeUpdate();
	}
	
	public List<String> selectPrintNumber(List<String> studyNoList) {
		String sql = "select  distinct  tasd.studyNo as studyNo "+
		" from CoresSchedule.dbo.tblAppointSD as tasd"+
		" where tasd.studyNo in (:studyNoList)  and tasd.appointSignID is not null and  tasd.appointSignID !='' and state = 1 and printNumber > 0  ";
		 Query query = getSession().createSQLQuery(sql);
        	query.setParameterList("studyNoList", studyNoList);
        	List<?> list = query.list();
        	List<String> slist =new ArrayList<String>();
        	if(null!=list){
    			for(Object obj:list){
    				slist.add((String)obj);
    			}
    		}
		return slist;
	}

	public String getStudyTypeCodeByStudyNo(String studyNo) {
		String sql = "select studyItem.studyTypeCode from CoresContract.dbo.tblStudyItem as studyItem where studyItem.studyNo = ?";
		String studyTypeCode = (String) getSession().createSQLQuery(sql)
											.setParameter(0, studyNo)
											.uniqueResult();
		return studyTypeCode;
	}

	@SuppressWarnings("unchecked")
	public List<TblStudyItem> getByContractCode(String contractCode) {
		if(null != contractCode){
			String hql = "from TblStudyItem where contractCode = ?";
			List<TblStudyItem> list = getSession().createQuery(hql).setParameter(0, contractCode).list();
			return list;
		}
		return null;
	}

	public boolean isExistByTiNoStudyTypeCode(String tiNo, String studyTypeCode) {
		if(null != tiNo && null != studyTypeCode){
			List<?> list = getSession().createQuery("From TblStudyItem where tiNo = ?  and studyTypeCode = ?")
						.setParameter(0, tiNo)
						.setParameter(1, studyTypeCode)
						.list();
			if(null != list && list.size()>0){
				return true;
			}else{
				return false;
			}
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMapListByuserName(String userName) {
		if(null != userName && !"".equals(userName)){
			String sql ="select studyitem.studyNo as studyCode ,studyitem.studyName,cont.sponsorName,testitem.tiName,testitem.tiCode" +
					" ,testitem.confirmSign as isConfirm"+
						" from CoresContract.dbo.tblStudyItem as studyitem left join CoresStudy.dbo.tblStudyPlan as studyplan"+
						" on studyitem.studyNo = studyplan.studyNo left join CoresContract.dbo.tblContract as cont"+
						" on studyitem.contractCode  = cont.contractCode left join CoresContract.dbo.tblTestItem as testitem"+
						" on testitem.contractCode = studyitem.contractCode and testitem.tiNo = studyitem.tiNo"+
						" where studyitem.sdCode = :sdCode and studyplan.studyNo is null";
			List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
															.setParameter("sdCode", userName)
															.setResultTransformer(new MapResultTransformer())
															.list();
			return mapList;
		}
		return null;
	}

	public String getTiCodeByStudyNo(String studyNo) {
		String tiCode = "";
		String sql = "select testitem.tiCode"+
					" from CoresContract.dbo.tblStudyItem as studyitem left join CoresContract.dbo.tblTestItem as testitem"+
					" on studyitem.tiNo = testitem.tiNo"+
					" where studyitem.studyNo = :studyNo";
		tiCode = (String) getSession().createSQLQuery(sql)
										.setParameter("studyNo", studyNo)
										.uniqueResult();
		return tiCode;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getByStudyCodesAndTiNo(String studyCodes,
			String tiNo) {
		// TODO 
		String hql = " FROM  TblTestItem WHERE  tiNo = :tiNo ";
		List<TblTestItem> tblTestItemList = getSession().createQuery(hql).setParameter("tiNo", tiNo).list();
		TblTestItem tblTestItem = null;
		if(tblTestItemList != null && tblTestItemList.size() > 0){
			 tblTestItem = tblTestItemList.get(0);
		}
	    List<String> studyList = new ArrayList<String>();  
		String[] strarray=studyCodes.split(","); 
	    for (int i = 0; i < strarray.length; i++) {
	          studyList.add(strarray[i].trim());
	    }                                                                                               
		String sql = "select a.studyTypeCode,a.studyName,a.studyCode,a.tiCode,a.animalHave from [CoresSystemSet].[dbo].[dictStudyType] as a " +
				" where a.studyTypeCode in ( :studyTypeCode )  ";
		Query query = getSession().createSQLQuery(sql);
     	query.setParameterList("studyTypeCode", studyList);
     	query.setResultTransformer(new MapResultTransformer());
     	List<Map<String,Object>> list = query.list();
     	List<DictStudyType> list1 = new ArrayList<DictStudyType>();
    	for(Map<String,Object> map :list){
    		DictStudyType dictStudyType =  new DictStudyType();
    		dictStudyType.setStudyTypeCode((String)map.get("studyTypeCode"));
    		dictStudyType.setStudyName((String)map.get("studyName"));
    		dictStudyType.setStudyCode((String)map.get("studyCode"));
    		dictStudyType.setTiCode((String)map.get("tiCode"));
    		dictStudyType.setAnimalHave((Integer)map.get("animalHave"));
    		list1.add(dictStudyType);
    	}
    	Collections.sort(list1,new Comparator<DictStudyType>(){
			public int compare(DictStudyType o1,
					DictStudyType o2) {
				if(o1.getStudyCode()== null || o1.getStudyCode().equals("")){
					return 1;
				}else 	if(o2.getStudyCode()== null || o2.getStudyCode().equals("")){
					return -1;
				}else{
					int result = o1.getStudyCode().compareTo(o2.getStudyCode());
			        return result;   
				}
				
				
			}
			
		});
     	List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
     	//Map<String,Object> map = new HashMap<String,Object>();
     	int j = 1;
     	for(DictStudyType obj : list1){
    		//Object[] objs= (Object[]) obj;
     		String studyNo = "";
     		if((obj.getTiCode()).equals("01")){
     			if(null != (obj.getStudyCode())){
         			studyNo = tblTestItem.getContractCode()+"-"+obj.getStudyCode();
         		}else{
         			studyNo = tblTestItem.getContractCode()+"-";
         		}
     		}else{
     			//String countThql = "FROM  TblTestItem WHERE   ( tiCode in ( 02 , 03 )) ";
     			//List<?> countT = getSession().createQuery(countThql).list();
     			String countShql = "FROM  TblStudyItem WHERE  ( tiNo  = :tiNo ) ";
     			List<?> countS = getSession().createQuery(countShql).setParameter("tiNo", tiNo).list();
     			//String countTid = String.format("%02d", countT.size()+1);//样品内部id
     			String countSid = String.format("%02d", countS.size()+j);//测试项目编码
     			studyNo =  tblTestItem.getTiNo()  + "-" + countSid; 
     			int i = 1;
     			while (isExistByStudyNo(studyNo)) {
         		    countSid = String.format("%02d" , countS.size()+i);//测试项目编码
         			studyNo =  tblTestItem.getTiNo()  + "-" + countSid; 
     				 i++;
     			}
     			j++;
     		}
     		
     		Map<String,Object> map = new HashMap<String,Object>(); 
     		map.put("studyNo" , studyNo);
     		map.put("studyName" , obj.getStudyName());
     		map.put("studyTypeCode" ,obj.getStudyTypeCode());
     		map.put("animalHave" ,obj.getAnimalHave());
     		mapList.add(map);
     	}
		return mapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getByStudyCodesAndTiNo2(String studyCodes,
			String tiNo) {
		// TODO 
		String hql = " FROM  TblTestItem WHERE  tiNo = :tiNo ";
		List<TblTestItem> tblTestItemList = getSession().createQuery(hql).setParameter("tiNo", tiNo).list();
		TblTestItem tblTestItem = null;
		if(tblTestItemList != null && tblTestItemList.size() > 0){
			 tblTestItem = tblTestItemList.get(0);
		}
	    List<String> studyList = new ArrayList<String>();  
		String[] strarray=studyCodes.split(","); 
	    for (int i = 0; i < strarray.length; i++) {
	          studyList.add(strarray[i].trim());
	    }                                                                                               
		String sql = "select a.studyTypeCode,a.studyName,a.studyCode,a.tiCode,a.animalHave  from [CoresSystemSet].[dbo].[dictStudyType] as a " +
				" where a.studyTypeCode in ( :studyTypeCode )  ";
		Query query = getSession().createSQLQuery(sql);
     	query.setParameterList("studyTypeCode", studyList);
     	query.setResultTransformer(new MapResultTransformer());
     	List<Map<String,Object>> list = query.list();
     	List<DictStudyType> list1 = new ArrayList<DictStudyType>();
    	for(Map<String,Object> map :list){
    		DictStudyType dictStudyType =  new DictStudyType();
    		dictStudyType.setStudyTypeCode((String)map.get("studyTypeCode"));
    		dictStudyType.setStudyName((String)map.get("studyName"));
    		dictStudyType.setStudyCode((String)map.get("studyCode"));
    		dictStudyType.setTiCode((String)map.get("tiCode"));
    		dictStudyType.setAnimalHave((Integer)map.get("animalHave"));
    		list1.add(dictStudyType);
    	}
    	Collections.sort(list1,new Comparator<DictStudyType>(){
			public int compare(DictStudyType o1,
					DictStudyType o2) {
				if(o1.getStudyCode()== null || o1.getStudyCode().equals("")){
					return 1;
				}else 	if(o2.getStudyCode()== null || o2.getStudyCode().equals("")){
					return -1;
				}else{
					int result = o1.getStudyCode().compareTo(o2.getStudyCode());
			        return result;   
				}
				
				
			}
			
		});
     	List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
     	for(DictStudyType obj : list1){
     		String studyNo = "";
     		if((obj.getTiCode()).equals("01")){
     			if(null != (obj.getStudyCode())){
         			studyNo = tblTestItem.getContractCode()+"-"+obj.getStudyCode();
         		}else{
         			studyNo = tblTestItem.getContractCode()+"-";
         		}
     		}else{
     			studyNo =  tblTestItem.getTiNo()  + "-" + obj.getStudyCode(); 
     		}
     		
     		Map<String,Object> map = new HashMap<String,Object>(); 
     		map.put("studyNo" , studyNo);
     		map.put("studyName" , obj.getStudyName());
     		map.put("studyTypeCode" ,obj.getStudyTypeCode());
     		map.put("animalHave" ,obj.getAnimalHave());
     		mapList.add(map);
     	}
		return mapList;
	}

	public boolean isExistByTiNostudyName(String tiNo, String studyName) {
		if(null != tiNo && null != studyName){
			List<?> list = getSession().createQuery("From TblStudyItem where tiNo = ?  and studyName = ?")
						.setParameter(0, tiNo)
						.setParameter(1, studyName)
						.list();
			if(null != list && list.size()>0){
				return true;
			}else{
				return false;
			}
		}
		return true;
	}

	public boolean isExistByTiNostudyName(String tiNo, String studyName,
			String animalType) {
		if(null == animalType){
			if(null != tiNo && null != studyName){
				String sql = "select item.id from  CoresContract.dbo.tblStudyItem as item " +
						" where item.tiNo = :tiNo  and item.studyName = :studyName and isnull(item.animalType,'') = '' ";
				List<?> list = getSession().createSQLQuery(sql)
				.setParameter("tiNo", tiNo)
				.setParameter("studyName", studyName)
				.list();
				if(null != list && list.size()>0){
					return true;
				}else{
					return false;
				}
			}
			return true;
		}else{
			if(null != tiNo && null != studyName){
				List<?> list = getSession().createQuery("From TblStudyItem where tiNo = ?  and studyName = ? and animalType = ?")
				.setParameter(0, tiNo)
				.setParameter(1, studyName)
				.setParameter(2, animalType)
				.list();
				if(null != list && list.size()>0){
					return true;
				}else{
					return false;
				}
			}
			return true;
		}
	}

	public String getSDByStudyNo(String studyNo) {
		String sql = "select sd"+
		" from CoresStudy.dbo.view_studyNoSD"+
		" where studyNo = :studyNo";
		String sd = (String) getSession().createSQLQuery(sql)
							.setParameter("studyNo", studyNo)
							.uniqueResult();
		return sd;
	}

	public void update(TblStudyItem tblStudyItem,
			TblStudyItemHis tblStudyItemHis, String realName) {
		String hisId = getKey("TblStudyItemHis");
		tblStudyItemHis.setId(hisId);
		String signId = writeES(hisId,"TblStudyItemHis",626,"委托项目编辑",realName);
		tblStudyItemHis.setOperateSign(signId);
		
		getSession().update(tblStudyItem);
		getSession().save(tblStudyItemHis);
		
	}
	
	/**
	 * 签字
	 */
	private String writeES(String dataId,String tableName,int estype,String typeDesc,String operator){
		
		//签名链接
		TblESLink esLink = new TblESLink();
		//电子签名
		TblES es = new TblES();
		es.setSigner(operator);
        es.setEsType(estype);
        es.setEsTypeDesc(typeDesc);
        es.setDateTime(new Date());
        String esId = tblESService.getKey("TblES");
		es.setEsId(esId);
        esLink.setTableName(tableName);
		esLink.setDataId(dataId);
		esLink.setTblES(es);
		esLink.setEsType(estype);
        esLink.setEsTypeDesc(typeDesc);
        esLink.setRecordTime(new Date());
		esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
			tblESService.save(es);
			tblESLinkService.save(esLink);
		return esId;
	}
	
}
