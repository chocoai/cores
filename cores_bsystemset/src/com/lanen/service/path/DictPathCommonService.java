package com.lanen.service.path;

import java.util.List;

import com.lanen.base.BaseDao;
import com.lanen.model.path.DictPathCommon;

/**
 * 毒性病理字典service
 * @author 黄国刚
 *
 */
public interface DictPathCommonService extends BaseDao<DictPathCommon> {

	/**判断 名称  是否存在
	 * @param descCn
	 * @return
	 */
	boolean hasDescCn(String descCn);
	
	/**判断 名称  是否存在(除itemCode外)
	 * @param descCn
	 * @param itemCode    //主键
	 * @return
	 */
	boolean hasDescCn(String descCn,String itemCode);

	/**获取下一个序号
	 * @return
	 */
	int getNextSn();

	/**新建保存
	 * @param dictPathCommon
	 */
	void addOne(DictPathCommon dictPathCommon);

	/**根据字典项查询,
	 * @param dictType
	 * @return
	 */
	List<DictPathCommon> queryList(Integer dictType);

	/**根据字典项\脏器编号
	 * @param dictType
	 * @param visceraCode
	 * @return
	 */
	List<DictPathCommon> queryList(Integer dictType, String visceraCode);

	/**编辑更新
	 * @param dictPathCommon
	 */
	void editOne(DictPathCommon dictPathCommon);

	/**删除,  成功返回 true,  否则false
	 * @param itemCode
	 * @return
	 */
	boolean delOne(String itemCode);

//	/**根据序号得到实体(不能用)
//	 * @param sortNum
//	 * @return
//	 */
//	DictPathCommon getBySn(Integer sortNum);

	/**  移动(上移,下移)
	 * @param itemCode
	 * @param sortNum
	 * @param nextSortNum
	 * @return
	 */
	boolean sortOne(String itemCode,Integer sortNum, Integer nextSortNum);
	
	/**根据任务dictType字典项类别和脏器，查询字典项对应的毒性病理字典列表(List);
	 * Map中有descCn 中文名称
	 * @param dictType
	 * @param VisceraCode
	 * @return
	 */
	List<DictPathCommon> getListByDictTypeAndVisceraCode(Integer dictType,String visceraCode,Integer order);
	
	
	/**根据任务dictType字典项类别和脏器，查询字典项对应的毒性病理字典列表(List);
	 * @param dictType
	 * @return
	 */
	List<DictPathCommon> getListByDictType(Integer dictType,Integer order);

	/**同一类型下，名称是否相同
	 * @param descCn
	 * @param dictType
	 * @return
	 */
	boolean hasDescCn(String descCn, Integer dictType);

	/**在这类型下,除这个 是否还是有名称相同
	 * @param descCn
	 * @param itemCode
	 * @param dictType
	 * @return
	 */
	boolean hasDescCn(String descCn, String itemCode, Integer dictType);

	/**同一类型同一脏器下，名称是否相同
	 * @param descCn
	 * @param dictType
	 * @param visceraCode
	 * @return
	 */
	boolean hasDescCn(String descCn, Integer dictType, String visceraCode);

	/**在这类型下这个脏器,除这个 是否还是有名称相同
	 * @param descCn
	 * @param itemCode
	 * @param dictType
	 * @param visceraCode
	 * @return
	 */
	boolean hasDescCn(String descCn, String itemCode, Integer dictType,
			String visceraCode);
	
	/**对应类别及脏器，对应区间 都减一
	 * @param sortNum（不含）
	 * @param nextSortNum（包含）
	 * @param dictType
	 * @param visceraCode
	 */
	void sortNumMinus1(Integer sortNum, Integer nextSortNum,
			Integer dictType, String visceraCode);
	/**对应类别及脏器，对应区间 都加一
	 * @param sortNum（不含）
	 * @param nextSortNum（包含）
	 * @param dictType
	 * @param visceraCode
	 */
	void sortNumPlus1(Integer sortNum, Integer nextSortNum,
			Integer dictType, String visceraCode);
}
