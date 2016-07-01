package com.lanen.service.contract;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.model.contract.TblAppointSD;
import com.lanen.model.contract.TblAppointSD_JSON;
import com.lanen.model.contract.TblStudyItem;

/**
 * @author 黄国刚
 *
 */
public interface TblAppointSDService extends BaseDao<TblAppointSD>  {
	
	/**
	 * 按照指定列名排序分页查询
	 * @param startime
	 * @param endtime
	 * @param start
	 * @param sort
	 * @param order
	 * @param qastate
	 * @param pastate
	 * @param tiNo
	 * @return
	 */
	
	Map<String, Object> getBytartimeAndEndtimeAndStartAndSortAndOrderAndPage
	(Date startime,Date endtime,int start,String sort,String order,int qastate,int pastate,String tiCode,String page,String rows,String searchString);
	
	/**
	 * 根据时间状态分页查询
	 * @param startime
	 * @param endtime
	 * @param name
	 * @param sort
	 * @param order
	 * @param tiNo
	 * @param chooseOwn
	 * @param page
	 * @param rows
	 * @return
	 */
    Map<String,Object> 	getBystartimeAndendtimeAndstartOnlyByOwnAndSortAndOrderAndPage
    (Date startime,Date endtime,String name,String sort,String order,String tiNo,String chooseOwn,String page,String rows,String searchString);
	/**
	 * 根据时间，状态查询委托试验
	 * @param startime
	 * @param endtime
	 * @param start
	 * @return
	 */
	//List<TblAppointSD_JSON> getBystartimeAndendtimeAndstartOnlyByOwn(Date startime,Date endtime,String name,String tiNo,String chooseOwn);
	/**
	 * 按照指定列名排序
	 * @param startime
	 * @param endtime
	 * @param name
	 * @param sort
	 * @param order
	 * @return
	 */
	List<TblAppointSD_JSON> getBystartimeAndendtimeAndstartOnlyByOwnAndSortAndOrder(Date startime,Date endtime,String name,String sort,String order,String tiNo,String chooseOwn);
	
	
	
	List<Integer> getStartStudyItem();
	/**
	 * 批量保存
	 * @param list
	 */
	void saveAll(List<TblAppointSD> list);
	
	
	/**提交任命
	 * @param list
	 * @param list2
	 */
	void saveAllAndUpdate(List<TblAppointSD> list,List<TblStudyItem> list2);

	/**
	 * 批量更新
	 */
	void updateAll(List<TblAppointSD> list);
	
	/**
	 * @param list 原任命（置为无效的）
	 * @param list2(重新任命的)
	 * @param list3(项目表里的更新，同时把专题表的也更新)
	 */
	void updateAgainAll(List<TblAppointSD> list,List<TblAppointSD> list2,List<TblStudyItem> list3);
	
	/**
	 * @param list更新任命表
	 * @param list2(项目表里的更新，同时把专题表的也更新)
	 */
	void updateAll(List<TblAppointSD> list,List<TblStudyItem> list2);
	/**
	 * 根据专题编号获得实体
	 * @param studyNo
	 * @return
	 */
	TblAppointSD getByStudyNo(String studyNo);
	
	/**获取课题任务SD日期
	 * @param studyNo
	 * @return
	 */
	Date getappointDateByStudyNo(String studyNo);


}
