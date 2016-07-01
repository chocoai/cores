package com.lanen.service.studyplan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.studyplan.TblAnimalDetailDissectPlan;
import com.lanen.model.studyplan.TblApplyRevise;
import com.lanen.model.studyplan.TblDoseSetting;
import com.lanen.model.studyplan.TblDoseSettingHis;
import com.lanen.model.studyplan.TblDoseSettingVersion;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.util.DateUtil;
import com.lanen.util.StringUtil;

@Service
public class TblDoseSettingServiceImpl extends BaseDaoImpl<TblDoseSetting> implements TblDoseSettingService {

	@SuppressWarnings("unchecked")
	public List<TblDoseSetting> getByStudyNo(TblStudyPlan tblStudyPlan) {
		return getSession().createQuery("FROM TblDoseSetting WHERE tblStudyPlan = ? ORDER BY dosageNum").setParameter(0, tblStudyPlan).list();
	}

	@SuppressWarnings("unchecked")
	public boolean uniqueCheck(TblStudyPlan tblStudyPlan, int dosageNumPara, String dosId) {
		List<TblDoseSetting> retList = new ArrayList<TblDoseSetting>();
		if(dosId == null){
			retList = getSession().createQuery("FROM TblDoseSetting WHERE tblStudyPlan = ? AND dosageNum = ? ORDER BY id").setParameter(0, tblStudyPlan).setParameter(1, dosageNumPara).list();
		}else {
			retList = getSession().createQuery("FROM TblDoseSetting WHERE tblStudyPlan = ? AND dosageNum = ? AND id != ? ORDER BY id").setParameter(0, tblStudyPlan).setParameter(1, dosageNumPara).setParameter(2, dosId).list();
		}
		if(retList!=null && !retList.isEmpty()){
			return false;
		} else {
			return true;
		}
	}

	public void save(TblDoseSetting doseSetting) {
		doseSetting.setId(getKey("TblDoseSetting"));
	    getSession().save(doseSetting);
	
	}

	public void saveAll(List<TblDoseSetting> tblDoseSettings) {//TODO
		int animalNum = 0;
		int a = 1;
		for(TblDoseSetting obj : tblDoseSettings){
			animalNum = animalNum+obj.getMaleNum() + obj.getFemaleNum();
		}
		Collections.sort(tblDoseSettings, new Comparator<TblDoseSetting>(){

			public int compare(TblDoseSetting o1, TblDoseSetting o2) {
				return o1.getDosageNum()-o2.getDosageNum();
			}
			
		});
		int  animalCodeFlag = 1;   //1 编码规则A    2 编码规则B
		boolean  animalCodeFlag2 =true; //  true:A编号1101   false：A编号11001
		if(tblDoseSettings.get(0).getTblStudyPlan().getAnimalCodeMode()==2){
			animalCodeFlag=2;
		}else{
			animalCodeFlag=1;
			if(animalNum>99){
				animalCodeFlag2 =false;
			}
		}
		for(TblDoseSetting obj : tblDoseSettings){
			//保存剂量设置
			String id =getKey("TblDoseSetting");
			obj.setId(id);
			getSession().save(obj);
			//设置动物详细解剖计划
			if(animalCodeFlag==1){//编码规则A
				for(int i=0; i<obj.getMaleNum(); i++){
					TblAnimalDetailDissectPlan tblAnimalDetailDissectPlan = new TblAnimalDetailDissectPlan();
					tblAnimalDetailDissectPlan.setId(getKey("TblAnimalDetailDissectPlan"));
					tblAnimalDetailDissectPlan.setGender(1);
					tblAnimalDetailDissectPlan.setTblStudyPlan(obj.getTblStudyPlan());
					if(animalCodeFlag2){
						tblAnimalDetailDissectPlan.setAnimalCode(""+obj.getDosageNum()+"1"+StringUtil.intToString(a, 2));
					}else{
						tblAnimalDetailDissectPlan.setAnimalCode(""+obj.getDosageNum()+"1"+StringUtil.intToString(a, 3));
					}
					tblAnimalDetailDissectPlan.setGroupId(obj.getDosageNum());
					getSession().save(tblAnimalDetailDissectPlan);
					a++;
				}
				for(int i=0; i<obj.getFemaleNum(); i++){
					TblAnimalDetailDissectPlan tblAnimalDetailDissectPlan = new TblAnimalDetailDissectPlan();
					tblAnimalDetailDissectPlan.setId(getKey("TblAnimalDetailDissectPlan"));
					tblAnimalDetailDissectPlan.setGender(2);
					tblAnimalDetailDissectPlan.setTblStudyPlan(obj.getTblStudyPlan());
					if(animalCodeFlag2){
						tblAnimalDetailDissectPlan.setAnimalCode(""+obj.getDosageNum()+"2"+StringUtil.intToString(a, 2));
					}else{
						tblAnimalDetailDissectPlan.setAnimalCode(""+obj.getDosageNum()+"2"+StringUtil.intToString(a, 3));
					}
					tblAnimalDetailDissectPlan.setGroupId(obj.getDosageNum());
					getSession().save(tblAnimalDetailDissectPlan);
					a++;
				}
			}else{// 编号规则B
				int groupNum =0;//组内序号
				for(int i=0; i<obj.getMaleNum(); i++){
					TblAnimalDetailDissectPlan tblAnimalDetailDissectPlan = new TblAnimalDetailDissectPlan();
					tblAnimalDetailDissectPlan.setId(getKey("TblAnimalDetailDissectPlan"));
					tblAnimalDetailDissectPlan.setGender(1);
					tblAnimalDetailDissectPlan.setTblStudyPlan(obj.getTblStudyPlan());
					tblAnimalDetailDissectPlan.setAnimalCode("1"+(obj.getDosageNum()-1)+StringUtil.intToString(groupNum, 2));
					tblAnimalDetailDissectPlan.setGroupId(obj.getDosageNum());
					getSession().save(tblAnimalDetailDissectPlan);
					groupNum++;
				}
				groupNum=0;
				for(int i=0; i<obj.getFemaleNum(); i++){
					TblAnimalDetailDissectPlan tblAnimalDetailDissectPlan = new TblAnimalDetailDissectPlan();
					tblAnimalDetailDissectPlan.setId(getKey("TblAnimalDetailDissectPlan"));
					tblAnimalDetailDissectPlan.setGender(2);
					tblAnimalDetailDissectPlan.setTblStudyPlan(obj.getTblStudyPlan());
						tblAnimalDetailDissectPlan.setAnimalCode("2"+(obj.getDosageNum()-1)+StringUtil.intToString(groupNum, 2));
					tblAnimalDetailDissectPlan.setGroupId(obj.getDosageNum());
					getSession().save(tblAnimalDetailDissectPlan);
					groupNum++;
				}
				
				
			}
		}
		
	}
    
	@Resource
	private TblDoseSettingHisService tblDoseSettingHisService;
  
	@Resource
	private TblApplyReviseService tblApplyReviseService;
	
	public void updateAllINApplyRevise(List<TblDoseSetting> tblDoseSettings,
			TblStudyPlan studyPlan) {
		// TODO 再编辑情况下
		List<TblDoseSettingHis> listHis = new ArrayList<TblDoseSettingHis>();
		for(TblDoseSetting doseSetting:tblDoseSettings){
			TblDoseSetting oldDoseSetting = getById(doseSetting.getId());
			oldDoseSetting.setDosageNum(doseSetting.getDosageNum());
			oldDoseSetting.setDosageDesc(doseSetting.getDosageDesc());
			oldDoseSetting.setDosage(doseSetting.getDosage());
			getSession().update(oldDoseSetting);//TODO
			TblDoseSettingHis tblDoseSettingHis = new TblDoseSettingHis();
			tblDoseSettingHis.setId(tblDoseSettingHisService.getKey());
			//tblDoseSettingHis.setTblStudyPlan(oldDoseSetting.getTblStudyPlan());
			tblDoseSettingHis.setDosageNum(oldDoseSetting.getDosageNum());
			tblDoseSettingHis.setDosageDesc(oldDoseSetting.getDosageDesc());
			tblDoseSettingHis.setDosage(oldDoseSetting.getDosage());
			tblDoseSettingHis.setMaleNum(oldDoseSetting.getMaleNum());
			tblDoseSettingHis.setFemaleNum(oldDoseSetting.getMaleNum());
			tblDoseSettingHis.setOldID(oldDoseSetting.getId());
			TblApplyRevise tblApplyRevise = tblApplyReviseService.getByStudyNo(oldDoseSetting.getTblStudyPlan().getStudyNo());
			tblDoseSettingHis.setTblApplyReviseID(tblApplyRevise.getId());
			tblDoseSettingHis.setOperate("编辑");
			tblDoseSettingHis.setOperateDate(new Date());
			listHis.add(tblDoseSettingHis);
		}
		tblDoseSettingHisService.saveAll(listHis);
	}
	public void updateAll(List<TblDoseSetting> tblDoseSettings ,TblStudyPlan studyPlan) {
		int animalNum = 0;
		int a = 1;//排序号
		for(TblDoseSetting obj : tblDoseSettings){
			animalNum += obj.getMaleNum()+ obj.getFemaleNum();
		}
		//排序
		Collections.sort(tblDoseSettings, new Comparator<TblDoseSetting>(){

			public int compare(TblDoseSetting o1, TblDoseSetting o2) {
				return o1.getDosageNum()-o2.getDosageNum();
			}
			
		});
		//清除原有详细解剖计划
		getSession().createQuery("DELETE FROM TblAnimalDetailDissectPlan WHERE tblStudyPlan=?").setParameter(0, studyPlan).executeUpdate();
		//清楚原有解剖计划
		getSession().createQuery("DELETE FROM TblDissectPlan  WHERE tblStudyPlan = ?").setParameter(0, studyPlan).executeUpdate();
		
		int  animalCodeFlag = 1;   //1 编码规则A    2 编码规则B
		boolean  animalCodeFlag2 =true; //  true:A编号1101   false：A编号11001
		if(tblDoseSettings.get(0).getTblStudyPlan().getAnimalCodeMode()==2){
			animalCodeFlag=2;
		}else{
			animalCodeFlag=1;
			if(animalNum>99){
				animalCodeFlag2 =false;
			}
		}
		for(TblDoseSetting obj : tblDoseSettings){
			TblDoseSetting doseSetting = getById(obj.getId());
			doseSetting.setDosageNum(obj.getDosageNum());
			doseSetting.setDosageDesc(obj.getDosageDesc());
			doseSetting.setDosage(obj.getDosage());
			doseSetting.setMaleNum(obj.getMaleNum());
			doseSetting.setFemaleNum(obj.getFemaleNum());
			getSession().update(doseSetting);//TODO
			
			//设置动物详细解剖计划
			if(animalCodeFlag==1){//编码规则A
				for(int i=0; i<obj.getMaleNum(); i++){
					TblAnimalDetailDissectPlan tblAnimalDetailDissectPlan = new TblAnimalDetailDissectPlan();
					tblAnimalDetailDissectPlan.setId(getKey("TblAnimalDetailDissectPlan"));
					tblAnimalDetailDissectPlan.setGender(1);
					tblAnimalDetailDissectPlan.setTblStudyPlan(obj.getTblStudyPlan());
					if(animalCodeFlag2){
						tblAnimalDetailDissectPlan.setAnimalCode(""+obj.getDosageNum()+"1"+StringUtil.intToString(a, 2));
					}else{
						tblAnimalDetailDissectPlan.setAnimalCode(""+obj.getDosageNum()+"1"+StringUtil.intToString(a, 3));
					}
					tblAnimalDetailDissectPlan.setGroupId(obj.getDosageNum());
					getSession().save(tblAnimalDetailDissectPlan);
					a++;
				}
				for(int i=0; i<obj.getFemaleNum(); i++){
					TblAnimalDetailDissectPlan tblAnimalDetailDissectPlan = new TblAnimalDetailDissectPlan();
					tblAnimalDetailDissectPlan.setId(getKey("TblAnimalDetailDissectPlan"));
					tblAnimalDetailDissectPlan.setGender(2);
					tblAnimalDetailDissectPlan.setTblStudyPlan(obj.getTblStudyPlan());
					if(animalCodeFlag2){
						tblAnimalDetailDissectPlan.setAnimalCode(""+obj.getDosageNum()+"2"+StringUtil.intToString(a, 2));
					}else{
						tblAnimalDetailDissectPlan.setAnimalCode(""+obj.getDosageNum()+"2"+StringUtil.intToString(a, 3));
					}
					tblAnimalDetailDissectPlan.setGroupId(obj.getDosageNum());
					getSession().save(tblAnimalDetailDissectPlan);
					a++;
				}
			}else{// 编号规则B
				int groupNum =0;//组内序号
				for(int i=0; i<obj.getMaleNum(); i++){
					TblAnimalDetailDissectPlan tblAnimalDetailDissectPlan = new TblAnimalDetailDissectPlan();
					tblAnimalDetailDissectPlan.setId(getKey("TblAnimalDetailDissectPlan"));
					tblAnimalDetailDissectPlan.setGender(1);
					tblAnimalDetailDissectPlan.setTblStudyPlan(obj.getTblStudyPlan());
					tblAnimalDetailDissectPlan.setAnimalCode("1"+(obj.getDosageNum()-1)+StringUtil.intToString(groupNum, 2));
					tblAnimalDetailDissectPlan.setGroupId(obj.getDosageNum());
					getSession().save(tblAnimalDetailDissectPlan);
					groupNum++;
				}
				groupNum=0;
				for(int i=0; i<obj.getFemaleNum(); i++){
					TblAnimalDetailDissectPlan tblAnimalDetailDissectPlan = new TblAnimalDetailDissectPlan();
					tblAnimalDetailDissectPlan.setId(getKey("TblAnimalDetailDissectPlan"));
					tblAnimalDetailDissectPlan.setGender(2);
					tblAnimalDetailDissectPlan.setTblStudyPlan(obj.getTblStudyPlan());
						tblAnimalDetailDissectPlan.setAnimalCode("2"+(obj.getDosageNum()-1)+StringUtil.intToString(groupNum, 2));
					tblAnimalDetailDissectPlan.setGroupId(obj.getDosageNum());
					getSession().save(tblAnimalDetailDissectPlan);
					groupNum++;
				}
				
				
			}
		}
	}
	@SuppressWarnings("unchecked")
	public TblDoseSetting getByStudyNoGroup(TblStudyPlan tblStudyPlan,int dosageNum) {
       List<TblDoseSetting> list = getSession().createQuery("FROM TblDoseSetting WHERE tblStudyPlan = ? AND dosageNum = ?").setParameter(0, tblStudyPlan).
       setParameter(1, dosageNum).list();
       if( list != null && list.size() >0 ){
    	   return (TblDoseSetting) list.get(0);
       }else{
    	   return null;
       }
	}

	public int getNextNumByStudyNo(String studyNoPara) {
		String sql = "select max(d.dosageNum)"+
					" from CoresStudy.dbo.tblDoseSetting as d"+
					" where d.studyNo = :studyNoPara";
		
		Integer count = (Integer) getSession().createSQLQuery(sql)
									.setParameter("studyNoPara", studyNoPara)
									.uniqueResult();
		return  null != count ? count+1 : 1;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getVerListByStudyNo(String studyNoPara){
		String sql = "select  a1.num,a2.num," +
				"	case when a1.num is not null then a1.[version]" +
				"	else '9999' end  id ," +
				"	case when a1.num is not null then '第'+cast(a1.[version] as varchar)+'历史版本'" +
				"	else '现版本' end text, a2.[doseEffectiveDate]" +
				" from ( SELECT  ROW_NUMBER() OVER(ORDER BY cast(version as int) desc) num,studyNo,[version] ,[doseEffectiveDate]" +
				"	  FROM [CoresStudy].[dbo].[tblApplyRevise]" +
				"	  where studyNo=:studyNoPara and (type=0 or type is null)" +
				"  ) as a1" +
				"  full join " +
				"  (	select ROW_NUMBER() OVER(ORDER BY cast(version as int) desc) num,studyNo,[version],[doseEffectiveDate] from [CoresStudy].[dbo].[tblApplyRevise] " +
				"	where studyNo=:studyNoPara and (type=0 or type is null)" +
				"  )as a2 " +
				"  on a2.studyNo = a1.studyNo and a2.num = a1.num+1" +
				"  order by id desc";

		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
												.setParameter("studyNoPara", studyNoPara)
												.setResultTransformer(new MapResultTransformer())
												.list();
		return mapList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMapListByStudyNo(String studyNoPara) {
		String sql = "SELECT   id, dosageNum, dosageDesc, dosage, maleNum, femaleNum, studyNo, femaleDosage, "+
                	" maleVolume, femaleVolume, maleThickness, femaleThickness"+
                	" FROM      tblDoseSetting"+
                	" where studyNo = :studyNoPara"+
                	" order by dosageNum";
		
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
														.setParameter("studyNoPara", studyNoPara)
														.setResultTransformer(new MapResultTransformer())
														.list();
		return mapList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMapListByStudyNoAndVersion(String studyNoPara,Integer version) {
		String sql = "SELECT [id] ,[dosageNum],[dosageDesc] ,[dosage],[maleNum],[femaleNum],[femaleDosage]," +
				" [maleVolume],[femaleVolume],[maleThickness],[femaleThickness],[version],[studyNo]" +
				" FROM [CoresStudy].[dbo].[tblDoseSettingVersion]" +
				"  where studyNo=:studyNoPara and version=:version" +
				" order by dosageNum";
		
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
														.setParameter("studyNoPara", studyNoPara)
														.setParameter("version", version)
														.setResultTransformer(new MapResultTransformer())
														.list();
		return mapList;
	}
	
	

	@SuppressWarnings("unchecked")
	public List<TblDoseSetting> getTblDoseSettingListByStudyNo(String studyNoPara) {
		String sql =" FROM  TblDoseSetting"+
                	" where studyNo = :studyNoPara"+
                	" order by dosageNum";
		
		List<TblDoseSetting> mapList = getSession().createQuery(sql)
													.setParameter("studyNoPara", studyNoPara)
													.list();
		return mapList;
	}
	public void delete(String id, int dosageNum, String studyNo) {
		TblDoseSetting tblDoseSetting = getById(id);
		
		getSession().delete(tblDoseSetting);
		
		String sql = "update tblDoseSetting set dosageNum = dosageNum -1"+
					" where studyNo = :studyNo and dosageNum > :dosageNum";
		
		getSession().createSQLQuery(sql)
					.setParameter("studyNo", studyNo)
					.setParameter("dosageNum", dosageNum)
					.executeUpdate();
	}

	public void deleteByStudyNo(String studyNo) {
		String sql = "delete tblDoseSetting "+
					" where studyNo = :studyNo ";
		getSession().createSQLQuery(sql)
					.setParameter("studyNo", studyNo)
					.executeUpdate();
	}

	public void createAnimalCodeAll(List<TblDoseSetting> tblDoseSettingList,
			TblStudyPlan tblStudyPlan) {
		//先删除
		String sql = "delete"+
					" from CoresStudy.dbo.tblAnimalDetailDissectPlan"+
					" where studyNo = ?";
		getSession().createSQLQuery(sql).setParameter(0, tblStudyPlan.getStudyNo()).executeUpdate();
		
		int animalNum = 0;
		int a = 1;
		for(TblDoseSetting obj : tblDoseSettingList){
			animalNum = animalNum+obj.getMaleNum() + obj.getFemaleNum();
		}
		Collections.sort(tblDoseSettingList, new Comparator<TblDoseSetting>(){

			public int compare(TblDoseSetting o1, TblDoseSetting o2) {
				return o1.getDosageNum()-o2.getDosageNum();
			}
			
		});
		int  animalCodeFlag = 1;   //1 编码规则A    2 编码规则B    3编码规则C
		boolean  animalCodeFlag2 =true; //  true:AC编号1101   false：AC编号11001
		if(tblStudyPlan.getAnimalCodeMode()==2){
			animalCodeFlag=2;
		}else {
			
			animalCodeFlag = tblStudyPlan.getAnimalCodeMode();
			if(animalNum>99){
				animalCodeFlag2 =false;
			}
		}
		for(TblDoseSetting obj : tblDoseSettingList){
			
			//设置动物详细解剖计划
			if(animalCodeFlag==1){//编码规则A
				for(int i=0; i<obj.getMaleNum(); i++){
					TblAnimalDetailDissectPlan tblAnimalDetailDissectPlan = new TblAnimalDetailDissectPlan();
					tblAnimalDetailDissectPlan.setId(getKey("TblAnimalDetailDissectPlan"));
					tblAnimalDetailDissectPlan.setGender(1);
					tblAnimalDetailDissectPlan.setTblStudyPlan(obj.getTblStudyPlan());
					if(animalCodeFlag2){
						tblAnimalDetailDissectPlan.setAnimalCode(""+obj.getDosageNum()+"1"+StringUtil.intToString(a, 2));
					}else{
						tblAnimalDetailDissectPlan.setAnimalCode(""+obj.getDosageNum()+"1"+StringUtil.intToString(a, 3));
					}
					tblAnimalDetailDissectPlan.setGroupId(obj.getDosageNum());
					getSession().save(tblAnimalDetailDissectPlan);
					a++;
				}
				for(int i=0; i<obj.getFemaleNum(); i++){
					TblAnimalDetailDissectPlan tblAnimalDetailDissectPlan = new TblAnimalDetailDissectPlan();
					tblAnimalDetailDissectPlan.setId(getKey("TblAnimalDetailDissectPlan"));
					tblAnimalDetailDissectPlan.setGender(2);
					tblAnimalDetailDissectPlan.setTblStudyPlan(obj.getTblStudyPlan());
					if(animalCodeFlag2){
						tblAnimalDetailDissectPlan.setAnimalCode(""+obj.getDosageNum()+"2"+StringUtil.intToString(a, 2));
					}else{
						tblAnimalDetailDissectPlan.setAnimalCode(""+obj.getDosageNum()+"2"+StringUtil.intToString(a, 3));
					}
					tblAnimalDetailDissectPlan.setGroupId(obj.getDosageNum());
					getSession().save(tblAnimalDetailDissectPlan);
					a++;
				}
			}else if(animalCodeFlag==2){// 编号规则B
				int groupNum =0;//组内序号
				for(int i=0; i<obj.getMaleNum(); i++){
					TblAnimalDetailDissectPlan tblAnimalDetailDissectPlan = new TblAnimalDetailDissectPlan();
					tblAnimalDetailDissectPlan.setId(getKey("TblAnimalDetailDissectPlan"));
					tblAnimalDetailDissectPlan.setGender(1);
					tblAnimalDetailDissectPlan.setTblStudyPlan(obj.getTblStudyPlan());
					tblAnimalDetailDissectPlan.setAnimalCode("1"+(obj.getDosageNum()-1)+StringUtil.intToString(groupNum, 2));
					tblAnimalDetailDissectPlan.setGroupId(obj.getDosageNum());
					getSession().save(tblAnimalDetailDissectPlan);
					groupNum++;
				}
				groupNum=0;
				for(int i=0; i<obj.getFemaleNum(); i++){
					TblAnimalDetailDissectPlan tblAnimalDetailDissectPlan = new TblAnimalDetailDissectPlan();
					tblAnimalDetailDissectPlan.setId(getKey("TblAnimalDetailDissectPlan"));
					tblAnimalDetailDissectPlan.setGender(2);
					tblAnimalDetailDissectPlan.setTblStudyPlan(obj.getTblStudyPlan());
						tblAnimalDetailDissectPlan.setAnimalCode("2"+(obj.getDosageNum()-1)+StringUtil.intToString(groupNum, 2));
					tblAnimalDetailDissectPlan.setGroupId(obj.getDosageNum());
					getSession().save(tblAnimalDetailDissectPlan);
					groupNum++;
				}
				
				
			}else if(animalCodeFlag == 3){
				for(int i=0; i<obj.getMaleNum(); i++){
					TblAnimalDetailDissectPlan tblAnimalDetailDissectPlan = new TblAnimalDetailDissectPlan();
					tblAnimalDetailDissectPlan.setId(getKey("TblAnimalDetailDissectPlan"));
					tblAnimalDetailDissectPlan.setGender(1);
					tblAnimalDetailDissectPlan.setTblStudyPlan(obj.getTblStudyPlan());
					if(animalCodeFlag2){
//						tblAnimalDetailDissectPlan.setAnimalCode(""+obj.getDosageNum()+"1"+StringUtil.intToString(a, 2));
						tblAnimalDetailDissectPlan.setAnimalCode("1"+(obj.getDosageNum()-1)+""+StringUtil.intToString(a, 2));
					}else{
//						tblAnimalDetailDissectPlan.setAnimalCode(""+obj.getDosageNum()+"1"+StringUtil.intToString(a, 3));
						tblAnimalDetailDissectPlan.setAnimalCode("1"+(obj.getDosageNum()-1)+""+StringUtil.intToString(a, 3));
					}
					tblAnimalDetailDissectPlan.setGroupId(obj.getDosageNum());
					getSession().save(tblAnimalDetailDissectPlan);
					a++;
				}
				for(int i=0; i<obj.getFemaleNum(); i++){
					TblAnimalDetailDissectPlan tblAnimalDetailDissectPlan = new TblAnimalDetailDissectPlan();
					tblAnimalDetailDissectPlan.setId(getKey("TblAnimalDetailDissectPlan"));
					tblAnimalDetailDissectPlan.setGender(2);
					tblAnimalDetailDissectPlan.setTblStudyPlan(obj.getTblStudyPlan());
					if(animalCodeFlag2){
//						tblAnimalDetailDissectPlan.setAnimalCode(""+obj.getDosageNum()+"2"+StringUtil.intToString(a, 2));
						tblAnimalDetailDissectPlan.setAnimalCode("2"+(obj.getDosageNum()-1)+""+StringUtil.intToString(a, 2));
					}else{
//						tblAnimalDetailDissectPlan.setAnimalCode(""+obj.getDosageNum()+"2"+StringUtil.intToString(a, 3));
						tblAnimalDetailDissectPlan.setAnimalCode("2"+(obj.getDosageNum()-1)+""+StringUtil.intToString(a, 3));
					}
					tblAnimalDetailDissectPlan.setGroupId(obj.getDosageNum());
					getSession().save(tblAnimalDetailDissectPlan);
					a++;
				}
			}
		}
		
	}

	public void updateAndHis(TblDoseSetting tblDoseSetting) {
		TblDoseSettingHis his = new TblDoseSettingHis();
		his.setId(tblDoseSettingHisService.getKey());
		his.setDosageDesc(tblDoseSetting.getDosageDesc());
		his.setDosage(tblDoseSetting.getDosage());
		his.setFemaleDosage(tblDoseSetting.getFemaleDosage());
		his.setMaleVolume(tblDoseSetting.getMaleVolume());
		his.setFemaleVolume(tblDoseSetting.getFemaleVolume());
		his.setMaleThickness(tblDoseSetting.getMaleThickness());
		his.setFemaleThickness(tblDoseSetting.getFemaleThickness());
		his.setOldID(tblDoseSetting.getId());
		his.setDosageNum(tblDoseSetting.getDosageNum());
		his.setStudyNo(tblDoseSetting.getTblStudyPlan().getStudyNo());
		his.setMaleNum(tblDoseSetting.getMaleNum());
		his.setFemaleNum(tblDoseSetting.getFemaleNum());
		his.setOperate("编辑");
		his.setOperateDate(new Date());
		TblApplyRevise tblApplyRevise =tblApplyReviseService.getByStudyNo(tblDoseSetting.getTblStudyPlan().getStudyNo());
		his.setTblApplyReviseID(tblApplyRevise.getId());
		getSession().save(his);
		getSession().update(tblDoseSetting);
	}

	public List<String> getdosageDescList() {
		String sql = " select distinct dosageDesc,dosageNum"+
						" from CoresStudy.dbo.tblDoseSetting "+
						" order by dosageNum,dosageDesc desc" ;
		List<?> list=getSession().createSQLQuery(sql).list();
		List<String> dosageDescList = new ArrayList<String>();
		if(null != list && list.size()>0){
			for(Object obj:list){
				Object[] objs = (Object[]) obj;
				String dosageDesc = (String) objs[0];
				if(!dosageDescList.contains(dosageDesc)){
					dosageDescList.add(dosageDesc);
				}
			}
		}
		
		return dosageDescList;
	}

	public void downMove(String studyNoPara, int dosageNum) {
		TblDoseSetting tblDoseSetting = getByStudyNoDosageNum(studyNoPara,dosageNum);
		TblDoseSetting tblDoseSetting2 = getByStudyNoDosageNum(studyNoPara,dosageNum+1);
		if(null !=tblDoseSetting && null != tblDoseSetting2){
			tblDoseSetting.setDosageNum(dosageNum+1);
			tblDoseSetting2.setDosageNum(dosageNum);
			getSession().update(tblDoseSetting);
			getSession().update(tblDoseSetting2);
		}
		
	}

	@SuppressWarnings("unchecked")
	private TblDoseSetting getByStudyNoDosageNum(String studyNoPara,
			int dosageNum) {
		String hql = "From TblDoseSetting " +
				" where tblStudyPlan.studyNo = :studyNoPara and dosageNum = :dosageNum ";
		List<TblDoseSetting> list = getSession().createQuery(hql)
									.setParameter("studyNoPara", studyNoPara)
									.setParameter("dosageNum", dosageNum)
									.list();
		if(null != list && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public void upMove(String studyNoPara, int dosageNum) {
		TblDoseSetting tblDoseSetting = getByStudyNoDosageNum(studyNoPara,dosageNum);
		TblDoseSetting tblDoseSetting2 = getByStudyNoDosageNum(studyNoPara,dosageNum-1);
		if(null !=tblDoseSetting && null != tblDoseSetting2){
			tblDoseSetting.setDosageNum(dosageNum-1);
			tblDoseSetting2.setDosageNum(dosageNum);
			getSession().update(tblDoseSetting);
			getSession().update(tblDoseSetting2);
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<TblDoseSetting> getListByStudyNo(String studyNo) {
		String hql = "from TblDoseSetting where tblStudyPlan.studyNo = :studyNo";
		List<TblDoseSetting> list = getSession().createQuery(hql)
								.setParameter("studyNo", studyNo)
								.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<TblDoseSetting> getListByStudyNoAndDate(String studyNo,Date reqCreateTime){
		/*String applyReviseHql = "SELECT TOP 1 [version] FROM [CoresStudy].[dbo].[tblApplyRevise]" +
				"  where applyFlag=1 and (type=0 or type is null)" +
				" and approvalDate<:createDate and studyNo=:studyNo " +
				" order by approvalDate desc";*/
		/*String applyReviseHql = "SELECT TOP 1 [version],(select max(version) from [CoresStudy].[dbo].[tblApplyRevise]) maxVer FROM [CoresStudy].[dbo].[tblApplyRevise]" +
				"	 where applyFlag=1 and (type=0 or type is null)" +
				"		and doseEffectiveDate<=:createDate " +
				"		and studyNo=:studyNo " +
				"		group by version " +
				"		order by version desc ";*/
		String applyReviseHql = " " +
				" select case when (select top 1 doseEffectiveDate from [CoresStudy].[dbo].[tblApplyRevise] " +
				"	where studyNo=:studyNo and (type=0 or type is null) and applyFlag =1 and doseEffectiveDate is not null order by doseEffectiveDate asc)>:createDate " +
				" then 0 " +//解剖申请时间比第一版本的变更申请时间还早的话，就是版本为1的剂量设置
				" else (SELECT TOP 1 [version] FROM [CoresStudy].[dbo].[tblApplyRevise] " +
				"       where applyFlag=1 and (type=0 or type is null) " +
				"		and doseEffectiveDate<=:createDate " +
				"		and studyNo=:studyNo " +
				"		group by version  " +
				"		order by version desc ) " +
				"		end version," +
				" (select max(version) from [CoresStudy].[dbo].[tblApplyRevise] where studyNo=:studyNo and applyFlag=1 and (type=0 or type is null)) maxVer";
		
		List<Map<String,Object>> versionInfo = getSession().createSQLQuery(applyReviseHql)
									.setParameter("createDate", DateUtil.dateToString(reqCreateTime, "yyyy-MM-dd"))
									.setParameter("studyNo", studyNo)
									.setResultTransformer(new MapResultTransformer())
									.list();
		Integer version = null,maxVer = null;
		if(versionInfo!=null&&versionInfo.size()>0)
		{
			Map<String,Object> verInfo = versionInfo.get(0);
			version = (Integer)verInfo.get("version");
			maxVer = (Integer)verInfo.get("maxVer");
		}
		
		List<TblDoseSetting> list = new ArrayList<TblDoseSetting>();
		if(version==null||maxVer == version){//最大版本的要比查找出来的版本大一
		
			String hql = "from TblDoseSetting where tblStudyPlan.studyNo = :studyNo order by dosageNum";
			list = getSession().createQuery(hql)
									.setParameter("studyNo", studyNo)
									.list();
		}else{
			String nextV = "SELECT top 1  [version] FROM [CoresStudy].[dbo].[tblApplyRevise]" +
					"		where studyNo=:studyNo and (type=0 or type is null) " +
					"		and applyFlag =1 and version>:version";
			Object nextVersion = getSession().createSQLQuery(nextV)
												.setParameter("studyNo", studyNo)
												.setParameter("version", version)
												.uniqueResult();
			
			String hql = "from TblDoseSettingVersion where studyNo = :studyNo and version=:version order by dosageNum";
			List<TblDoseSettingVersion> versionList = getSession().createQuery(hql)
									.setParameter("studyNo", studyNo)
									.setInteger("version", (Integer)nextVersion)
									.list();
			for(TblDoseSettingVersion ver:versionList)
			{
				TblDoseSetting dos = new TblDoseSetting();
				dos.setDosage(ver.getDosage());
				dos.setDosageDesc(ver.getDosageDesc());
				dos.setDosageNum(ver.getDosageNum());
				dos.setFemaleNum(ver.getFemaleNum());
				dos.setFemaleThickness(ver.getFemaleThickness());
				dos.setFemaleVolume(ver.getFemaleVolume());
				dos.setMaleNum(ver.getMaleNum());
				dos.setMaleThickness(ver.getMaleThickness());
				dos.setMaleVolume(ver.getMaleVolume());
				//dos.setStudyNo(ver.getStudyNo());
				dos.setFemaleDosage(ver.getFemaleDosage());
				
				list.add(dos);
			}
			
		}
		
		return list;
	}
	

}
