package com.lanen.service.contract;

import java.util.List;
import java.util.Map;

import com.lanen.base.BaseDao;
import com.lanen.model.contract.TblContractAttachment;

/**合同附件servcie
 * @author 黄国刚
 *
 */
public interface TblContractAttachmentService extends BaseDao<TblContractAttachment>{

	/**根据合同编号查询mapList （id，attachmentName,state）
	 * @param contractCode
	 * @return
	 */
	List<Map<String, Object>> getMapListByContractCode(String contractCode);

}
