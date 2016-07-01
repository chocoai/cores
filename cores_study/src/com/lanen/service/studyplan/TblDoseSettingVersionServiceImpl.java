package com.lanen.service.studyplan;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.studyplan.TblDoseSettingVersion;
@Service
public class TblDoseSettingVersionServiceImpl extends BaseDaoImpl<TblDoseSettingVersion> implements  TblDoseSettingVersionService{
	
	public List<TblDoseSettingVersion> getMaxVersionByStudyNo(String studyNoPara)
	{
		String sql = "SELECT [id] ,[dosageNum],[dosageDesc] ,[dosage],[maleNum],[femaleNum],[femaleDosage] ,[maleVolume],[femaleVolume],[maleThickness],[femaleThickness] ,[version],[studyNo]" +
				" FROM " +
				" (select [id] ,[dosageNum],[dosageDesc] ,[dosage],[maleNum],[femaleNum],[femaleDosage] ,[maleVolume],[femaleVolume],[maleThickness],[femaleThickness] ,[version],[studyNo]" +
				"	 ,dense_rank() over(partition by studyNo order by version desc) rn from [CoresStudy].[dbo].[tblDoseSettingVersion]   " +
				"	 where studyNo=:studyNo " +
				" ) as a" +
				" where rn=1";
		
		List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
													.setParameter("studyNo", studyNoPara)
													.setResultTransformer(new MapResultTransformer())
													.list();
		 List<TblDoseSettingVersion> list = new ArrayList<TblDoseSettingVersion>();
		 for(Map<String,Object> map:mapList)
		 {
			 TblDoseSettingVersion ver = new TblDoseSettingVersion();
			 ver.setDosage((String)map.get("dosage"));
			 
			 ver.setDosageDesc((String)map.get("dosageDesc"));
			 ver.setDosageNum((Integer)map.get("dosageNum"));
			 ver.setFemaleDosage((String)map.get("femaleDosage"));
			 ver.setFemaleNum((Integer)map.get("femaleNum"));
			 ver.setFemaleThickness((String)map.get("femaleThickness"));
			 ver.setFemaleVolume((String)map.get("femaleVolume"));
			 ver.setId((String)map.get("id"));
			 ver.setMaleNum((Integer)map.get("maleNum"));
			 ver.setMaleThickness((String)map.get("maleThickness"));
			 ver.setMaleVolume((String)map.get("maleVolume"));
			 ver.setStudyNo((String)map.get("studyNo"));
			 ver.setVersion((Integer)map.get("version"));
			 
			 
			 
			 list.add(ver);
		 }
		
		
		return list;
	}
}
