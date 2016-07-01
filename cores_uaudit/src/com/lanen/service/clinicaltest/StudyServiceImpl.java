package com.lanen.service.clinicaltest;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lanen.base.MapResultTransformer;

@Service
@Transactional
public class StudyServiceImpl implements StudyService {
	@Resource
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<String> getYearList() {
//		String sql = "select distinct convert(varchar(4),study.studyStartDate,120)"+
//					" from CoresStudy.dbo.tblStudyPlan as study"+
//					" order by convert(varchar(4),study.studyStartDate,120) desc";
		String sql = "select r.year1 "+
					" from("+
					" 	select distinct convert(varchar(4),study.studyStartDate,120) as year1"+
					" 	from CoresStudy.dbo.tblStudyPlan as study"+
	
					" 		union"+

					" 	select distinct convert(varchar(4),c.submitDate,120) as year1"+
					" 	from CoresContract.dbo.tblStudyItem as si join CoresContract.dbo.tblContract as c"+
					" 		on si.contractCode = c.contractCode"+
					" 	where c.submitDate is not null"+
					" 	)as r"+
					" order by r.year1 desc";
		
		List<String> yearList = sessionFactory.getCurrentSession().createSQLQuery(sql).list();
		return yearList;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getStudyNoMapListByYear(String year,
			String testItemType) {
//		String sql = "select study.studyNo"+
//					" from CoresStudy.dbo.tblStudyPlan as study join CoresSystemSet.dbo.dictStudyType as st"+
//					" 	on study.studyTypeCode = st.studyTypeCode"+
//					" where convert(varchar(4),study.studyStartDate,120) =:year and st.tiCode = :testItemType "+
//					" order by study.studyNo";
		String sql = "select r.studyNo"+
					" from ("+
					" 		select si.studyNo "+
					" 		from CoresContract.dbo.tblStudyItem as si join CoresContract.dbo.tblContract as c"+
					" 			on si.contractCode = c.contractCode join CoresSystemSet.dbo.dictStudyType as st"+
					" 			on si.studyTypeCode = st.studyTypeCode"+
					" 		where c.contractState > 0 and convert(varchar(4),c.submitDate,120) =:year and st.tiCode = :testItemType"+
					" 		union"+
					" 		select CoresStudy.dbo.studyNoRemoveFN(study.studyNo) as studyNo"+
					" 		from CoresStudy.dbo.tblStudyPlan as study join CoresSystemSet.dbo.dictStudyType as st"+
					" 					 			on study.studyTypeCode = st.studyTypeCode"+
					" 		where convert(varchar(4),study.studyStartDate,120) =:year and st.tiCode = :testItemType "+
					" 	) as r"+
					" order by r.studyNo";
		List<Map<String,Object>> mapList = sessionFactory.getCurrentSession()
														.createSQLQuery(sql)
														.setParameter("year", year)
														.setParameter("testItemType", testItemType)
														.setResultTransformer(new MapResultTransformer())
														.list();
		return mapList;
	}

	
}
