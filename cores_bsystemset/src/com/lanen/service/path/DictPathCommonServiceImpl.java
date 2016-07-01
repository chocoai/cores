package com.lanen.service.path;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.path.DictPathCommon;

/**
 * 
 * @author 黄国刚
 *
 */
@Service
public class DictPathCommonServiceImpl extends BaseDaoImpl<DictPathCommon> implements DictPathCommonService{

	public boolean hasDescCn(String descCn) {
		String sql = "select count(*)"+
					" from CoresSystemSet.dbo.dictPathCommon as dp"+
					" where dp.descCn = ? ";
		Integer count = (Integer) getSession()
											.createSQLQuery(sql)
											.setParameter(0, descCn)
											.uniqueResult();
		return count > 0;
	}
	public boolean hasDescCn(String descCn,String itemCode) {
		String sql = "select count(*)"+
		" from CoresSystemSet.dbo.dictPathCommon as dp"+
		" where dp.descCn = ? and itemCode != ?";
		Integer count = (Integer) getSession()
		.createSQLQuery(sql)
		.setParameter(0, descCn)
		.setParameter(1, itemCode)
		.uniqueResult();
		return count > 0;
	}
	public int getNextSn() {
		String sql = "select Max(dp.sortNum)"+
				" from CoresSystemSet.dbo.dictPathCommon as dp"+
				" ";
		Integer maxSn = (Integer) getSession().createSQLQuery(sql).uniqueResult();
		if(null == maxSn){
			maxSn = 0;
		}
		return maxSn+1;
	}
	public void addOne(DictPathCommon dictPathCommon) {
		getSession().save(dictPathCommon);
	}
	@SuppressWarnings("unchecked")
	public List<DictPathCommon> queryList(Integer dictType) {
		String hql = "FROM DictPathCommon where dictType = ? order by sortNum ";
		List<DictPathCommon> list = getSession().createQuery(hql)
												.setParameter(0, dictType)
												.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<DictPathCommon> queryList(Integer dictType, String visceraCode) {
		String hql = "FROM DictPathCommon where dictType = ? and visceraCode = ? order by sortNum ";
		List<DictPathCommon> list = getSession().createQuery(hql)
												.setParameter(0, dictType)
												.setParameter(1, visceraCode)
												.list();
		return list;
	}
	public void editOne(DictPathCommon dictPathCommon) {
		getSession().update(dictPathCommon);
		
	}
	public boolean delOne(String itemCode) {
		String hql = "delete FROM DictPathCommon where itemCode = ?";
		
		Integer count = getSession().createQuery(hql)
									.setParameter(0, itemCode)
									.executeUpdate();
		return count>0;
	}
//	public DictPathCommon getBySn(Integer sortNum) {
//		String hql = "From DictPathCommon where sortNum = ? ";
//		DictPathCommon dictPathCommon = (DictPathCommon) getSession().createQuery(hql)
//													.setParameter(0, sortNum)
//													.uniqueResult();
//		return dictPathCommon;
//	}
	public boolean sortOne(String itemCode,Integer sortNum, Integer nextSortNum) {
		DictPathCommon dictPathCommon = getById(itemCode);
		if(null == dictPathCommon){
			return false;
		}
		Integer dictType = dictPathCommon.getDictType();
		String visceraCode = dictPathCommon.getVisceraCode();
		//1.上移（都加 1）
		if(sortNum > nextSortNum ){
			sortNumPlus1(sortNum,nextSortNum,dictType,visceraCode);
		}else{
			//2.下移（减一）
			sortNumMinus1(sortNum,nextSortNum,dictType,visceraCode);
		}
		
		dictPathCommon.setSortNum(nextSortNum);
		getSession().update(dictPathCommon);
		
		return true;
	}
	public void sortNumMinus1(Integer sortNum, Integer nextSortNum,
			Integer dictType, String visceraCode) {
		String sql = "update CoresSystemSet.dbo.dictPathCommon"+
					" set sortNum = sortNum - 1"+
					" from CoresSystemSet.dbo.dictPathCommon "+
					" where sortNum > ? and sortNum <= ? and dictType = ? "+
					" 	and (visceraCode is null or visceraCode = ?)";
		getSession().createSQLQuery(sql)
					.setParameter(0, sortNum)
					.setParameter(1, nextSortNum)
					.setParameter(2, dictType)
					.setParameter(3, visceraCode)
					.executeUpdate();
		
	}
	public void sortNumPlus1(Integer sortNum, Integer nextSortNum,
			Integer dictType, String visceraCode) {
		String sql = "update CoresSystemSet.dbo.dictPathCommon"+
					" set sortNum = sortNum +1"+
					" from CoresSystemSet.dbo.dictPathCommon "+
					" where sortNum >= ? and sortNum < ? and dictType = ? "+
					" 	and (visceraCode is null or visceraCode = ?)";
		getSession().createSQLQuery(sql)
					.setParameter(0, nextSortNum)
					.setParameter(1, sortNum)
					.setParameter(2, dictType)
					.setParameter(3, visceraCode)
					.executeUpdate();
		
	}
	@SuppressWarnings("unchecked")
	public List<DictPathCommon> getListByDictTypeAndVisceraCode(
			Integer dictType, String visceraCode,Integer order) {
		String hql = "From DictPathCommon where dictType = :dictType and visceraCode = :visceraCode ";
		if(order == 1 ){
			 hql =hql + " order by sortNum ";
		}else if(order == 2){
			hql =hql + " order by py ";
		}else if(order == 3){
			hql = hql +" order by freqCount desc ";
		}
		
		List<DictPathCommon> list = (List<DictPathCommon>)getSession().createQuery(hql)
		.setParameter("dictType", dictType).setParameter("visceraCode", visceraCode).list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<DictPathCommon> getListByDictType(
			Integer dictType,Integer order) {
		String hql = "From DictPathCommon where dictType = :dictType ";
		if(order == 1 ){
			 hql =hql + " order by sortNum ";
		}else if(order == 2){
			hql =hql + " order by py ";
		}else if(order == 3){
			hql = hql +" order by freqCount desc ";
		}
		List<DictPathCommon> list = (List<DictPathCommon>)getSession().createQuery(hql)
		.setParameter("dictType", dictType).list();
		return list;
	}
	public boolean hasDescCn(String descCn, Integer dictType) {
		String sql = "select count(*)"+
					" from CoresSystemSet.dbo.dictPathCommon as dp"+
					" where dp.descCn = ? and dp.dictType = ?";
			Integer count = (Integer) getSession()
											.createSQLQuery(sql)
											.setParameter(0, descCn)
											.setParameter(1, dictType)
											.uniqueResult();
			return count > 0;
	}
	public boolean hasDescCn(String descCn, String itemCode, Integer dictType) {
		String sql = "select count(*)"+
						" from CoresSystemSet.dbo.dictPathCommon as dp"+
						" where dp.descCn = ? and itemCode != ? and dp.dictType = ?";
		Integer count = (Integer) getSession()
											.createSQLQuery(sql)
											.setParameter(0, descCn)
											.setParameter(1, itemCode)
											.setParameter(2, dictType)
											.uniqueResult();
		return count > 0;
	}
	public boolean hasDescCn(String descCn, Integer dictType, String visceraCode) {
		String sql = "select count(*)"+
				" from CoresSystemSet.dbo.dictPathCommon as dp"+
				" where dp.descCn = ? and dp.dictType = ? and dp.visceraCode = ?";
		Integer count = (Integer) getSession()
										.createSQLQuery(sql)
										.setParameter(0, descCn)
										.setParameter(1, dictType)
										.setParameter(2, visceraCode)
										.uniqueResult();
		return count > 0;
	}
	public boolean hasDescCn(String descCn, String itemCode, Integer dictType,
			String visceraCode) {
		String sql = "select count(*)"+
				" from CoresSystemSet.dbo.dictPathCommon as dp"+
				" where dp.descCn = ? and itemCode != ? and dp.dictType = ? and dp.visceraCode = ? ";
		Integer count = (Integer) getSession()
									.createSQLQuery(sql)
									.setParameter(0, descCn)
									.setParameter(1, itemCode)
									.setParameter(2, dictType)
									.setParameter(3, visceraCode)
									.uniqueResult();
		return count > 0;
	}

}
