package com.lanen.service.qa;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.qa.QAStudyChkIndex;
import com.lanen.service.qa.QAStudyChkIndexService;
@Service
public class QAStudyChkIndexServiceImpl extends BaseDaoImpl<QAStudyChkIndex> implements	QAStudyChkIndexService {
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> getQAStudyChkIndexByStudyNo(String studyNo)
	{
		Query query = getSession().createSQLQuery(" select studyNo,inspector from [CoresQA].[dbo].[QAStudyChkIndex] where studyNo=:studyNo")
									.setString("studyNo", studyNo)
									.setResultTransformer(new MapResultTransformer());
		List<Map<String,Object>> list = query.list();
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}
	
	public void updateScheduleChangedFlag(String studyNo,Integer scheduleChangedFlag)
	{
		Query query = getSession().createSQLQuery(" update [CoresQA].[dbo].[QAStudyChkIndex] " +
				"set scheduleChangedFlag=:scheduleChangedFlag,scheduleState=1,scheduleSubmitTime=GETDATE() " +
				"where studyNo=:studyNo")
									.setString("studyNo", studyNo)
									.setInteger("scheduleChangedFlag", scheduleChangedFlag);
		query.executeUpdate();

	}
}
