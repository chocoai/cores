package com.lanen.service.path;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.path.DictViscera;
import com.lanen.model.path.DictVisceraAnimal;

/**
 * 
 * 脏器字典     serviceImpl
 * @author 黄国刚
 *
 */
@Service
public class DictVisceraServiceImpl extends BaseDaoImpl<DictViscera> implements DictVisceraService{

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryDataMapList(Integer sortType) {
		String sql = "SELECT    dictv.visceraCode, dictv.visceraType, dictv.visceraName, dictv.visceraNameJp, dictv.visceraNameEn, dictv.py"+
					" 	, dictv.gender, dictv.[level], dictv.pVisceraCode as _parentId, "+
					"                 dictv.isPart, dictv.sn,dictv.snWeight,dictv.snFixed,dictv.animalFlag,(case when dictv.animalFlag = 1 then '全部' else '' end )as animalTypeNames"+
					" 			   ,(case when sv.sons is not null then 'open' else '' end )as state "+
					" FROM   dictViscera as dictv left join "+
					" 	(select dv.pVisceraCode, count(dv.pVisceraCode) as sons"+
					" 	from dictViscera as dv"+
					" 	where dv.pVisceraCode is not null and dv.pVisceraCode !=''"+
					" 	group by dv.pVisceraCode ) "+
					" 	as sv on sv.pVisceraCode = dictv.visceraCode and dictv.level = 1" +
					"  " ;
		if(sortType==2){
			sql=sql+" order by dictv.snWeight ";
		}else if(sortType==3){
			sql=sql+" order by dictv.snFixed ";
		}else{
			sql=sql+" order by dictv.sn";
		}
					
		
		List<Map<String,Object>> dataMapList = getSession().createSQLQuery(sql)
													.setResultTransformer(new MapResultTransformer())
													.list();
		return dataMapList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryVisceraTypeDataMapList() {
		String sql = "SELECT id, visceraTypeName"+
					" FROM      dictVisceraType"+
					" order by id";
		List<Map<String,Object>> dataMapList = getSession().createSQLQuery(sql)
				.setResultTransformer(new MapResultTransformer())
				.list();
		return dataMapList;
	}

	public boolean isExistByVisceraName(String visceraName) {
		String sql = "SELECT count(*)"+
					" FROM  dictViscera as dv"+
					" where dv.visceraName = ?";
		Integer count = (Integer) getSession().createSQLQuery(sql)
									.setParameter(0, visceraName)
									.uniqueResult();
		return count > 0 ;
	}
	public boolean isExistByVisceraName(String visceraName, String visceraCode) {
		String sql = "SELECT count(*)"+
				" FROM  dictViscera as dv"+
				" where dv.visceraName = ? and dv.visceraCode != ? ";
		Integer count = (Integer) getSession().createSQLQuery(sql)
								.setParameter(0, visceraName)
								.setParameter(1, visceraCode)
								.uniqueResult();
		return count > 0 ;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryAnimalTypeDataMapList() {
		String sql = "select dat.id,dat.typeName"+
					" from CoresStudy.dbo.dictAnimalType as dat"+
					" order by dat.id";
		List<Map<String,Object>> dataMapList = getSession().createSQLQuery(sql)
								.setResultTransformer(new MapResultTransformer())
								.list();
		return dataMapList;
	}

	


	public void addOne(DictViscera dictViscera, List<String> animalTypeNameArray) {
		getSession().save(dictViscera);
		if(null != animalTypeNameArray && animalTypeNameArray.size() > 0 ){
			DictVisceraAnimal dictVisceraAnimal = null;
			for(int i = 0;i < animalTypeNameArray.size() ;i++){
				dictVisceraAnimal = new DictVisceraAnimal();
				dictVisceraAnimal.setId(getKey("DictVisceraAnimal"));
				dictVisceraAnimal.setVisceraCode(dictViscera.getVisceraCode());
				dictVisceraAnimal.setAnimalTypeName(animalTypeNameArray.get(i));
				getSession().save(dictVisceraAnimal);
			}
		}
		
	}

	public void updateOne(DictViscera dictViscera ,String[] animalTypeNameArray) {
//		//更新一级脏器信息 
		getSession().update(dictViscera);
		//删除 添加动物时用
		List<String> visceraCodeList = new ArrayList<String>();
		visceraCodeList.add(dictViscera.getVisceraCode());
		
		List<DictViscera> subVisceraList = this.getSubVisceraListByVisceraCode(dictViscera.getVisceraCode());
		if(null != subVisceraList){
			//更新对应二级脏器 的   脏器类别  ,性别  
//			Integer animalFlag = dictViscera.getAnimalFlag();
			Integer visceraType = dictViscera.getVisceraType();
			Integer gender = dictViscera.getGender();
			for(DictViscera obj :subVisceraList){
//				obj.setAnimalFlag(animalFlag);
				obj.setVisceraType(visceraType);
				obj.setGender(gender);
				getSession().update(obj);
//				visceraCodeList.add(obj.getVisceraCode());  //不删除二级脏器动物列表,
			}
		}
		
		//删除原动物列表
		String delSql = "delete from CoresSystemSet.dbo.dictVisceraAnimal where visceraCode in (:visceraCodeList)";
		getSession().createSQLQuery(delSql).setParameterList("visceraCodeList", visceraCodeList).executeUpdate();
		//添加新动物列表
		if(null != animalTypeNameArray && animalTypeNameArray.length > 0 ){
			DictVisceraAnimal dictVisceraAnimal = null;
			for(String code:visceraCodeList){
				for(int i = 0;i < animalTypeNameArray.length ;i++){
					dictVisceraAnimal = new DictVisceraAnimal();
					dictVisceraAnimal.setId(getKey("DictVisceraAnimal"));
					dictVisceraAnimal.setVisceraCode(code);
					dictVisceraAnimal.setAnimalTypeName(animalTypeNameArray[i]);
					getSession().save(dictVisceraAnimal);
				}
			}
		}
		
//		
//		//更新对应二级脏器 的动物 类别    与   脏器类别  ,性别  
//		
//		String sql = "update CoresSystemSet.dbo.dictViscera"+
//					" set animalType =:animalType ,visceraType = :visceraType " +
//					" ,gender = :gender , isPart = :isPart "+
//					" where pVisceraCode =:pVisceraCode  and level = 2";
//		
//		getSession().createSQLQuery(sql)
//						.setParameter("animalType", dictViscera.getAnimalType())
//						.setParameter("visceraType", dictViscera.getVisceraType())
//						.setParameter("pVisceraCode", dictViscera.getpVisceraCode())
//						.setParameter("gender", dictViscera.getGender())
//						.setParameter("isPart", dictViscera.getIsPart())
//						.executeUpdate();
//		
//		
	}


	@SuppressWarnings("unchecked")
	public List<DictViscera> getSubVisceraListByVisceraCode(String visceraCode) {
		String hql = "from DictViscera where pVisceraCode = ? ";
		List<DictViscera> list = getSession().createQuery(hql)
											.setParameter(0, visceraCode)
											.list();
		return list;
	}

	public void updateSon(DictViscera dictViscera,String[] animalTypeNameArray) {
		//更新er级脏器信息
		getSession().update(dictViscera);
		//删除 添加动物时用
		List<String> visceraCodeList = new ArrayList<String>();
		visceraCodeList.add(dictViscera.getVisceraCode());
		
		//删除原动物列表
		String delSql = "delete from CoresSystemSet.dbo.dictVisceraAnimal where visceraCode in (:visceraCodeList)";
		getSession().createSQLQuery(delSql).setParameterList("visceraCodeList", visceraCodeList).executeUpdate();
		
		//添加新动物列表
		if(null != animalTypeNameArray && animalTypeNameArray.length > 0 ){
			DictVisceraAnimal dictVisceraAnimal = null;
			for(String code:visceraCodeList){
				for(int i = 0;i < animalTypeNameArray.length ;i++){
					dictVisceraAnimal = new DictVisceraAnimal();
					dictVisceraAnimal.setId(getKey("DictVisceraAnimal"));
					dictVisceraAnimal.setVisceraCode(code);
					dictVisceraAnimal.setAnimalTypeName(animalTypeNameArray[i]);
					getSession().save(dictVisceraAnimal);
				}
			}
		}
		
	}

	public void delOne(String visceraCode) {
		//删除 添加动物时用
		List<String> visceraCodeList = new ArrayList<String>();
		
		List<DictViscera> subVisceraList = getSubVisceraListByVisceraCode(visceraCode);
		visceraCodeList.add(visceraCode);
		
		if(null !=subVisceraList ){
			for(DictViscera obj :subVisceraList){
				visceraCodeList.add(obj.getVisceraCode());
			}
		}
		
		String hql = "delete From DictViscera where visceraCode = :visceraCode or pVisceraCode = :visceraCode";
		getSession().createQuery(hql)
					.setParameter("visceraCode", visceraCode)
					.executeUpdate();
		
		//删除原动物列表
		String delSql = "delete from CoresSystemSet.dbo.dictVisceraAnimal where visceraCode in (:visceraCodeList)";
		getSession().createSQLQuery(delSql).setParameterList("visceraCodeList", visceraCodeList).executeUpdate();
		
		
		//删除  浅表肿瘤脏器
		String delSuperficialSql = "delete"+
									" from CoresSystemSet.dbo.tblSuperficialTumorViscera "+
									" where visceraCode = ? ";
		getSession().createSQLQuery(delSuperficialSql).setParameter(0, visceraCode).executeUpdate();
	}

	public boolean downOneBySn(Integer sn,Integer sortType, Integer lineNum) {
		DictViscera currentDictViscera = getOneBySn(sn,sortType);
		DictViscera nextDictViscera = getNextOneBySn(sn,sortType,lineNum);
		if(null != currentDictViscera && null != nextDictViscera){
			if(sortType==1){
				Integer nextSn = nextDictViscera.getSn();
				//减一
				oneLevelSnMinus1(sn, nextSn, sortType);
				currentDictViscera.setSn(nextSn);
				getSession().update(currentDictViscera);
				return true;
			}else if(sortType==2){
				Integer nextSn = nextDictViscera.getSnWeight();
				//减一
				oneLevelSnMinus1(sn, nextSn, sortType);
				currentDictViscera.setSnWeight(nextSn);
				getSession().update(currentDictViscera);
				return true;
			}else if(sortType==3){
				Integer nextSn = nextDictViscera.getSnFixed();
				//减一
				oneLevelSnMinus1(sn, nextSn, sortType);
				currentDictViscera.setSnFixed(nextSn);
				getSession().update(currentDictViscera);
				return true;
			}
			
		}
		
		return false;
	}

	public boolean downOneBySnPvisceraCode(Integer sn, String pvisceraCode,Integer sortType,Integer lineNum) {
		DictViscera currentDictViscera = getOneBySnPvisceraCode(sn,sortType,pvisceraCode);
		DictViscera nextDictViscera = getNextOneBySnPvisceraCode(sn, pvisceraCode,sortType,lineNum);
		if(null != currentDictViscera && null != nextDictViscera){
			if(sortType==1){
				Integer nextSn = nextDictViscera.getSn();
				//减一
				twoLevelSnMinus1(sn, nextSn, pvisceraCode, sortType);
				currentDictViscera.setSn(nextSn);
				getSession().update(currentDictViscera);
				return true;
			}else if(sortType==2){
				Integer nextSn = nextDictViscera.getSnWeight();
				//减一
				twoLevelSnMinus1(sn, nextSn, pvisceraCode, sortType);
				currentDictViscera.setSnWeight(nextSn);
				getSession().update(currentDictViscera);
				return true;
			}else if(sortType==3){
				Integer nextSn = nextDictViscera.getSnFixed();
				//减一
				twoLevelSnMinus1(sn, nextSn, pvisceraCode, sortType);
				currentDictViscera.setSnFixed(nextSn);
				getSession().update(currentDictViscera);
				return true;
			}
			
		}
		
		return false;
	}

	@SuppressWarnings("unchecked")
	public DictViscera getNextOneBySn(Integer sn,Integer sortType,Integer lineNum) {
		String hql="";
		if(sortType==1){
			hql = "From DictViscera where sn > ? and level = 1 order by sn asc";
		}else if(sortType==2){
			hql = "From DictViscera where snWeight > ? and level = 1 order by snWeight asc";
		}else if(sortType==3){
			hql = "From DictViscera where snFixed > ? and level = 1 order by snFixed asc";
		}
		 
		List<DictViscera> dictVisceraList = getSession()
											.createQuery(hql)
											.setParameter(0, sn)
											.setMaxResults(lineNum)
											.list();
		if(null != dictVisceraList && dictVisceraList.size()>0){
			return dictVisceraList.get(dictVisceraList.size() - 1);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public DictViscera getNextOneBySnPvisceraCode(Integer sn,
			String pvisceraCode,Integer sortType,Integer lineNum) {
		String hql="";
		if(sortType==1){
			hql = "From DictViscera where sn > ? and level = 2 and pVisceraCode = ? order by sn asc";
		}else if(sortType==2){
			hql = "From DictViscera where snWeight > ? and level = 2 and pVisceraCode = ? order by snWeight asc";
		}else if(sortType==3){
			hql = "From DictViscera where snFixed > ? and level = 2 and pVisceraCode = ? order by snFixed asc";
		}
		
		List<DictViscera> dictVisceraList = getSession()
											.createQuery(hql)
											.setParameter(0, sn)
											.setParameter(1, pvisceraCode)
											.setMaxResults(lineNum)
											.list();
		if(null != dictVisceraList && dictVisceraList.size()>0){
			return dictVisceraList.get(dictVisceraList.size()-1);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public DictViscera getPrevOneBySn(Integer sn,Integer sortType,Integer lineNum) {
		String hql="";
		if(sortType==1){
			hql = "From DictViscera d where sn < :sn and level = 1 order by sn desc";
		}else if(sortType==2){
			hql = " From DictViscera d where snWeight < :sn and level = 1 order by snWeight desc";
		}else if(sortType==3){
			hql = " From DictViscera d where snFixed < :sn and level = 1 order by snFixed desc";
		}
		
		List<DictViscera> dictVisceraList = getSession()
											.createQuery(hql)
											.setParameter("sn", sn)
											.setMaxResults(lineNum)
											.list();
		if(null != dictVisceraList && dictVisceraList.size()>0){
			return dictVisceraList.get(dictVisceraList.size()-1);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public DictViscera getPrevOneBySnPvisceraCode(Integer sn,
			String pvisceraCode,Integer sortType,Integer lineNum) {
		String hql="";
		if(sortType==1){
			hql = "From DictViscera where sn < ? and level = 2 and pVisceraCode = ? order by sn desc";
		}else if(sortType==2){
			hql = "From DictViscera where snWeight < ? and level = 2 and pVisceraCode = ? order by snWeight desc";
		}else if(sortType==3){
			hql = "From DictViscera where snFixed < ? and level = 2 and pVisceraCode = ? order by snFixed desc";
		}
		
		List<DictViscera> dictVisceraList = getSession()
											.createQuery(hql)
											.setParameter(0, sn)
											.setParameter(1, pvisceraCode)
											.setMaxResults(lineNum)
											.list();
		if(null != dictVisceraList && dictVisceraList.size()>0){
			return dictVisceraList.get(dictVisceraList.size()-1);
		}
		return null;
	}

	public boolean upOneBySn(Integer sn,Integer sortType,Integer lineNum) {
		DictViscera currentDictViscera = getOneBySn(sn,sortType);
		DictViscera prevDictViscera = getPrevOneBySn(sn,sortType,lineNum);
		if(null != currentDictViscera && null != prevDictViscera){
			if(sortType==1){
				Integer prevSn = prevDictViscera.getSn();
				//  sn <sn  and sn>= prevSn  都加 1 
				oneLevelSnPlus1(prevSn,sn,sortType);
				currentDictViscera.setSn(prevSn);
				
				getSession().update(currentDictViscera);
				return true;
			}else if(sortType==2){
				Integer prevSn = prevDictViscera.getSnWeight();
			//  sn <sn  and sn>= prevSn  都加 1 
				oneLevelSnPlus1(prevSn,sn,sortType);
				currentDictViscera.setSnWeight(prevSn);
				
				getSession().update(currentDictViscera);
				return true;
			}else if(sortType==3){
				Integer prevSn = prevDictViscera.getSnFixed();
			//  sn <sn  and sn>= prevSn  都加 1 
				oneLevelSnPlus1(prevSn,sn,sortType);
				currentDictViscera.setSnFixed(prevSn);
				
				getSession().update(currentDictViscera);
				return true;
			}
			
		}
		
		return false;
	}

	
	public void oneLevelSnPlus1(Integer prevSn, Integer sn, Integer sortType) {
		String sql="";
		if(sortType==1){
			sql="update dictViscera"+
				" set sn = sn+1"+
				" from dictViscera as d"+
				" where d.sn >= ? and d.sn <? and d.level = 1";
		}else if(sortType==2){
			sql="update dictViscera"+
			" set snWeight = snWeight+1"+
			" from dictViscera as d"+
			" where d.snWeight >= ? and d.snWeight <? and d.level = 1";
		}else if(sortType==3){
			sql="update dictViscera"+
			" set snFixed = snFixed+1"+
			" from dictViscera as d"+
			" where d.snFixed >= ? and d.snFixed <? and d.level = 1";
		}
		getSession().createSQLQuery(sql)
					.setParameter(0, prevSn)
					.setParameter(1, sn)
					.executeUpdate();
		
	}
	public void twoLevelSnPlus1( Integer prevSn,Integer sn,
			String pvisceraCode, Integer sortType) {
		String sql="";
		if(sortType==1){
			sql="update dictViscera"+
				" set sn = sn+1"+
				" from dictViscera as d"+
				" where d.sn >= ? and d.sn <? and d.level = 2 and d.pVisceraCode = ? ";
		}else if(sortType==2){
			sql="update dictViscera"+
			" set snWeight = snWeight+1"+
			" from dictViscera as d"+
			" where d.snWeight >= ? and d.snWeight <? and d.level = 2 and d.pVisceraCode = ? ";
		}else if(sortType==3){
			sql="update dictViscera"+
			" set snFixed = snFixed+1"+
			" from dictViscera as d"+
			" where d.snFixed >= ? and d.snFixed <? and d.level = 2 and d.pVisceraCode = ? ";
		}
		getSession().createSQLQuery(sql)
					.setParameter(0, prevSn)
					.setParameter(1, sn)
					.setParameter(2, pvisceraCode)
					.executeUpdate();
		
	}
	public boolean upOneBySnPvisceraCode(Integer sn, String pvisceraCode,Integer sortType,Integer lineNum) {
		DictViscera currentDictViscera = getOneBySnPvisceraCode(sn,sortType,pvisceraCode);
		DictViscera prevDictViscera = getPrevOneBySnPvisceraCode(sn, pvisceraCode,sortType,lineNum);
		if(null != currentDictViscera && null != prevDictViscera){
			if(sortType==1){
				Integer prevSn = prevDictViscera.getSn();
				twoLevelSnPlus1(prevSn,sn,pvisceraCode,sortType);
				
				currentDictViscera.setSn(prevSn);
				getSession().update(currentDictViscera);
				return true;
			}else if(sortType==2){
				Integer prevSn = prevDictViscera.getSnWeight();
				twoLevelSnPlus1(prevSn,sn,pvisceraCode,sortType);
				
				currentDictViscera.setSnWeight(prevSn);
				getSession().update(currentDictViscera);
				return true;
			}else if(sortType==3){
				Integer prevSn = prevDictViscera.getSnFixed();
				twoLevelSnPlus1(prevSn,sn,pvisceraCode,sortType);
				
				currentDictViscera.setSnFixed(prevSn);
				getSession().update(currentDictViscera);
				return true;
			}
			
		}
		
		return false;
	}

	

	public DictViscera getOneBySn(Integer sn,Integer sortType) {
		String hql="";
		if(sortType==1){
			hql="From DictViscera where sn = ? and level = 1";
		}else if(sortType==2){
			hql="From DictViscera where snWeight = ? and level = 1";
		}else if(sortType==3){
			hql="From DictViscera where snFixed = ? and level = 1";
		}
		DictViscera dictViscera  = (DictViscera) getSession()
											.createQuery(hql)
											.setParameter(0, sn)
											.uniqueResult();
		return dictViscera;
	}
	public DictViscera getOneBySnPvisceraCode(Integer sn,Integer sortType,String pVisceraCode) {
		String hql="";
		if(sortType==1){
			hql="From DictViscera where sn = ? and level = 2 and pVisceraCode = ? ";
		}else if(sortType==2){
			hql="From DictViscera where snWeight = ? and level = 2 and pVisceraCode = ? ";
		}else if(sortType==3){
			hql="From DictViscera where snFixed = ? and level = 2 and pVisceraCode = ? ";
		}
		DictViscera dictViscera  = (DictViscera) getSession()
														.createQuery(hql)
														.setParameter(0, sn)
														.setParameter(1, pVisceraCode)
														.uniqueResult();
		return dictViscera;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> query1LCodeNameList() {
		String sql = "select dv.visceraCode,dv.visceraName"+
					" from CoresSystemSet.dbo.dictViscera as dv"+
					" where dv.level = 1"+
					" order by dv.sn";
		List<Map<String,Object>> list = getSession()
											.createSQLQuery(sql)
											.setResultTransformer(new MapResultTransformer())
											.list();
		return list;
	}
    @SuppressWarnings("unchecked")
	public List<DictViscera> get1LListByAnimalType(String animalType){
    	String hql="from DictViscera where level=1 and (animalType='0' or animalType=:animalType) order by sn";
    	List<DictViscera> list=getSession().createQuery(hql).setParameter("animalType", animalType).list();
		return list;
    	
    }

	@SuppressWarnings("unchecked")
	public DictViscera getByVisceraName(String visceraName) {
		String hql="from DictViscera where visceraName=:visceraName";
		List<DictViscera> list=getSession().createQuery(hql).setParameter("visceraName", visceraName).list();
		DictViscera dictViscera=null;
		if(list!=null&&list.size()>0){
			dictViscera=list.get(0);
		}
		return dictViscera;
	}
	@SuppressWarnings("unchecked")
	public List<DictViscera> getByVisceraNameList(List<String> visceraNameList) {
		String visNameList = "";
		for(String name:visceraNameList)
		{
			visNameList+="'"+name+"',";
		}
		visNameList = visNameList.substring(0, visNameList.length()-1);
	//	String hql="from DictViscera where visceraName in ( :visNameList)";
	//	List<DictViscera> list=getSession().createQuery(hql).setParameter("visNameList", visNameList).list();
		String hql="from DictViscera where visceraName in ("+visNameList+")";
		List<DictViscera> list=getSession().createQuery(hql).list();
		return list;
	}
	public int allAnimalTypeNumber() {
		String sql = "select count(dat.id)"+
		" from CoresStudy.dbo.dictAnimalType as dat ";
		Integer number = (Integer) getSession().createSQLQuery(sql)
					.uniqueResult();
		return number;
	}

	@SuppressWarnings("unchecked")
	public List<String> getAnimalTypeNameByVisceraCode(String visceraCode) {
		String sql = "select va.animalTypeName"+
					" from CoresSystemSet.dbo.dictVisceraAnimal as va"+
					" where va.visceraCode = :visceraCode";
		List<String> list = getSession().createSQLQuery(sql)
										.setParameter("visceraCode", visceraCode)
										.list();
		return list;
	}

	public void oneLevelSnMinus1(Integer sn, Integer nextSn, Integer sortType) {
		String sql="";
		if(sortType==1){
			sql="update dictViscera"+
				" set sn = sn-1"+
				" from dictViscera as d"+
				" where d.sn > ? and d.sn <=? and d.level = 1";
		}else if(sortType==2){
			sql="update dictViscera"+
				" set snWeight = snWeight-1"+
				" from dictViscera as d"+
				" where d.snWeight > ? and d.snWeight <=? and d.level = 1";
		}else if(sortType==3){
			sql="update dictViscera"+
				" set snFixed = snFixed-1"+
				" from dictViscera as d"+
				" where d.snFixed > ? and d.snFixed <=? and d.level = 1";
		}
		getSession().createSQLQuery(sql)
					.setParameter(0, sn)
					.setParameter(1, nextSn)
					.executeUpdate();
		
	}

	public void twoLevelSnMinus1(Integer sn, Integer nextSn,
			String pvisceraCode, Integer sortType) {
		String sql="";
		if(sortType==1){
			sql="update dictViscera"+
				" set sn = sn-1"+
				" from dictViscera as d"+
				" where d.sn > ? and d.sn <=? and d.level = 2 and d.pVisceraCode = ? ";
		}else if(sortType==2){
			sql="update dictViscera"+
			" set snWeight = snWeight-1"+
			" from dictViscera as d"+
			" where d.snWeight > ? and d.snWeight <=? and d.level = 2 and d.pVisceraCode = ? ";
		}else if(sortType==3){
			sql="update dictViscera"+
			" set snFixed = snFixed-1"+
			" from dictViscera as d"+
			" where d.snFixed > ? and d.snFixed <=? and d.level = 2 and d.pVisceraCode = ? ";
		}
		getSession().createSQLQuery(sql)
					.setParameter(0, sn)
					.setParameter(1, nextSn)
					.setParameter(2, pvisceraCode)
					.executeUpdate();
		
		
	}
	
	public Integer getNextSn() {
		String sql = "select Max(dv.sn)"+
					" from CoresSystemSet.dbo.dictViscera as dv"+
					" ";
		Integer maxSn = (Integer) getSession().createSQLQuery(sql).uniqueResult();
		if(null == maxSn){
			maxSn = 0;
		}
		String sql2 = "select Max(dv.snWeight)"+
		" from CoresSystemSet.dbo.dictViscera as dv"+
		" ";
		Integer maxSnWeight = (Integer) getSession().createSQLQuery(sql2).uniqueResult();
		if(null == maxSnWeight){
			maxSnWeight = 0;
		}
		String sql3 = "select Max(dv.snFixed)"+
		" from CoresSystemSet.dbo.dictViscera as dv"+
		" ";
		Integer maxSnFixed = (Integer) getSession().createSQLQuery(sql3).uniqueResult();
		if(null == maxSnFixed){
			maxSnFixed = 0;
		}
		
		Integer max = 0;
		if(maxSn > maxSnWeight){
			max = maxSn;
		}else{
			max = maxSnWeight;
		}
		if(max < maxSnFixed){
			max = maxSnFixed;
		}
		
		return max+1;
	}
	@SuppressWarnings("unchecked")
	List<DictViscera> getAllOrderBySnFixed()
	{
		String sql = " from DictViscera order by snFixed";
		List<DictViscera> list=  getSession().createQuery(sql).list();
					
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<DictViscera> getListByParentVisceraCode(String visceraCode) {
		String hql = "from DictViscera where pVisceraCode = ? ";
		List<DictViscera> list = getSession().createQuery(hql)
											.setParameter(0, visceraCode)
											.list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getNotSameViscListByList(String[] visceraNamesCheck,Integer gender)
	{
		String sql = " SELECT [visceraName] FROM [CoresSystemSet].[dbo].[dictViscera]" +
				"  where (gender!=0 and gender!=:gender)" +
				"  and visceraName in (:viscList);";
		
		List<String> list=  getSession().createSQLQuery(sql)
										.setParameter("gender", gender)
										.setParameterList("viscList", visceraNamesCheck)
										.list();
					
		return list;
	}
}
