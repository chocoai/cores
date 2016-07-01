package com.lanen.service.contract;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.contract.TblStudyScheduleNode;

@Service
public class TblStudyScheduleNodeServiceImpl extends BaseDaoImpl<TblStudyScheduleNode> implements TblStudyScheduleNodeService{

	@SuppressWarnings("unchecked")
	public List<TblStudyScheduleNode> getListByStudyTypeCode(
			String studyTypeCode) {
		if(null != studyTypeCode && !"".equals(studyTypeCode)){
			List<TblStudyScheduleNode> list = null;
			String hql = "From TblStudyScheduleNode where studyTypeCode = ? order by nodeSn ";
			list =  getSession().createQuery(hql)
								.setParameter(0, studyTypeCode)
								.list();
			if(null == list || list.size()<1){
				list =  getSession().createQuery(hql)
									.setParameter(0, "@@@@@@")
									.list();
			}
			return list;
		}
		return null;
	}

	public boolean isExistNodeName(String studyTypeCode, String nodeName) {
		String sql = "select count(*)"+
					" from CoresSystemSet.dbo.tblStudyScheduleNode as node"+
					" where ( node.studyTypeCode = '@@@@@@' or node.studyTypeCode =? )" +
					" and node.nodeName = ? ";
		Integer count = (Integer) getSession().createSQLQuery(sql)
									.setParameter(0, studyTypeCode)
									.setParameter(1, nodeName)
									.uniqueResult();
		if(count>0){
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<String> getNoDefaultNodeNameList() {
		String sql= "select distinct node.nodeName"+
					" from CoresSystemSet.dbo.tblStudyScheduleNode as node"+
					" where node.defaultNode = 0 ";
		List<String> list = getSession().createSQLQuery(sql).list();
		return list;
	}

	public boolean isHasInit(String studyTypeCode) {
		String sql = "select count(*)"+
					" from CoresSystemSet.dbo.tblStudyScheduleNode as node"+
					" where (  node.studyTypeCode =? )" ;
			Integer count = (Integer) getSession().createSQLQuery(sql)
									.setParameter(0, studyTypeCode)
									.uniqueResult();
			if(count>0){
				return true;
			}
			return false;
	}

	@SuppressWarnings("unchecked")
	public void initStudyNode(String studyTypeCode) {
		String hql = "From TblStudyScheduleNode where studyTypeCode = ? ";
		List<TblStudyScheduleNode> list =  getSession().createQuery(hql)
							.setParameter(0, "@@@@@@")
							.list();
		if(null != list && list.size()>0){
			TblStudyScheduleNode tblStudyScheduleNode = null;
			for(TblStudyScheduleNode obj:list){
				tblStudyScheduleNode = new TblStudyScheduleNode();
				tblStudyScheduleNode.setId(getKey());
				tblStudyScheduleNode.setDefaultNode(1);
				tblStudyScheduleNode.setNodeName(obj.getNodeName());
				tblStudyScheduleNode.setNodeSn(obj.getNodeSn());
				tblStudyScheduleNode.setPlanDays(obj.getPlanDays());
				tblStudyScheduleNode.setStudyTypeCode(studyTypeCode);
				getSession().save(tblStudyScheduleNode);
			}
		}
		
	}

	public int getNextNodeSn(int nodeSn,String studyTypeCode) {
		if(nodeSn < 0){
			nodeSn = 0;
		}
		int endNodeSn = (nodeSn/50+1)*50;
		String sql = "select max(node.nodeSn)"+
					" from CoresSystemSet.dbo.tblStudyScheduleNode as node"+
					" where node.nodeSn>=? and node.nodeSn<? and node.studyTypeCode = ? ";
		Integer maxNodeSn = (Integer) getSession().createSQLQuery(sql)
													.setParameter(0, nodeSn)
													.setParameter(1, endNodeSn)
													.setParameter(2, studyTypeCode)
													.uniqueResult();
		if(null !=maxNodeSn ){
			return maxNodeSn+1;
		}else{
			return 101;
		}
	}

	public TblStudyScheduleNode getByNodeNameStudyTypeCode(String nodeName,
			String studyTypeCode) {
		String hql = "From TblStudyScheduleNode where studyTypeCode = ? and nodeName = ? ";
		TblStudyScheduleNode tblStudyScheduleNode =  (TblStudyScheduleNode) getSession().createQuery(hql)
							.setParameter(0, studyTypeCode)
							.setParameter(1, nodeName)
							.uniqueResult();
		return tblStudyScheduleNode;
	}

	public boolean upNode(String id) {
		//当前节点
		TblStudyScheduleNode tblStudyScheduleNode = getById(id);
		if(null != tblStudyScheduleNode && tblStudyScheduleNode.getDefaultNode() == 0 ){
			//当前节点序号
			int  currentNodeSn = tblStudyScheduleNode.getNodeSn();
			//前一个节点
			TblStudyScheduleNode tblStudyScheduleNode2 = getBeforeCurrentNode(currentNodeSn,tblStudyScheduleNode.getStudyTypeCode());
			if(null != tblStudyScheduleNode2){
				/**
				 * 若前一节点不是默认节点，则交换节点序号
				 * 若前一节点     是默认节点，则当前节点的节点序号设为前一节点前的最大节点序号
				 */
				if(tblStudyScheduleNode2.getDefaultNode() == 0 ){
					tblStudyScheduleNode.setNodeSn(tblStudyScheduleNode2.getNodeSn());
					tblStudyScheduleNode2.setNodeSn(currentNodeSn);
					update(tblStudyScheduleNode);
					update(tblStudyScheduleNode2);
					return true;
				}else{
					int nextNodeSn = 0;
					if(tblStudyScheduleNode2.getNodeSn() == 850 || 
							tblStudyScheduleNode2.getNodeSn()== 200 ||
							tblStudyScheduleNode2.getNodeSn()== 900 ||
							tblStudyScheduleNode2.getNodeSn()==150){
						nextNodeSn = getNextNodeSn(
								tblStudyScheduleNode2.getNodeSn()-50,
								tblStudyScheduleNode.getStudyTypeCode());
					}else{
						nextNodeSn = getNextNodeSn(
								tblStudyScheduleNode2.getNodeSn()-100,
								tblStudyScheduleNode.getStudyTypeCode());
					}
					tblStudyScheduleNode.setNodeSn(nextNodeSn);
					update(tblStudyScheduleNode);
					return true;
				}
			}
		}
		return false;
	}
	public boolean downNode(String id) {
		//当前节点  a
		TblStudyScheduleNode tblStudyScheduleNode = getById(id);
		if(null != tblStudyScheduleNode && tblStudyScheduleNode.getDefaultNode() == 0 ){
			//当前节点序号
			int  currentNodeSn = tblStudyScheduleNode.getNodeSn();
			//后一个节点  b
			TblStudyScheduleNode tblStudyScheduleNode2 = getAfterCurrentNode(currentNodeSn,tblStudyScheduleNode.getStudyTypeCode());
			if(null != tblStudyScheduleNode2){
				/**
				 * 若   b 不是默认节点，则交换节点序号
				 */
				if(tblStudyScheduleNode2.getDefaultNode() == 0 ){
					tblStudyScheduleNode.setNodeSn(tblStudyScheduleNode2.getNodeSn());
					tblStudyScheduleNode2.setNodeSn(currentNodeSn);
					update(tblStudyScheduleNode);
					update(tblStudyScheduleNode2);
					return true;
				}else{
					/**
					 * 若   b 是默认节点，则把   bNodeSn +100 > nodeSn >bNodeSn   的节点序号都加1 ，aNodeSn = bNodeSn+1
					 */
					int bNodeSn = tblStudyScheduleNode2.getNodeSn();
					
					TblStudyScheduleNode tblStudyScheduleNode_c =getCurrentNode(bNodeSn+1,tblStudyScheduleNode.getStudyTypeCode());
					if(null != tblStudyScheduleNode_c){
						String sql ="update tblStudyScheduleNode"+
						" set nodeSn = nodeSn+1"+
						" where nodeSn>? and nodeSn <? and defaultNode = 0 and studyTypeCode=?";
						getSession().createSQLQuery(sql)
						.setParameter(0, bNodeSn)
						.setParameter(1, bNodeSn+100)
						.setParameter(2, tblStudyScheduleNode.getStudyTypeCode())
						.executeUpdate();
					}
					
					tblStudyScheduleNode.setNodeSn(bNodeSn+1);
					update(tblStudyScheduleNode);
					return true;
				}
			}
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	private TblStudyScheduleNode getBeforeCurrentNode(int currentNodeSn,String studyTypeCode){
		String hql = "From TblStudyScheduleNode where studyTypeCode = ? and nodeSn < ? order by nodeSn desc ";
		List<TblStudyScheduleNode> list =  getSession().createQuery(hql)
							.setParameter(0, studyTypeCode)
							.setParameter(1, currentNodeSn)
							.list();
		if(null != list && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	private TblStudyScheduleNode getAfterCurrentNode(int currentNodeSn,String studyTypeCode){
		String hql = "From TblStudyScheduleNode where studyTypeCode = ? and nodeSn > ? order by nodeSn asc ";
		List<TblStudyScheduleNode> list =  getSession().createQuery(hql)
		.setParameter(0, studyTypeCode)
		.setParameter(1, currentNodeSn)
		.list();
		if(null != list && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	private TblStudyScheduleNode getCurrentNode(int currentNodeSn,String studyTypeCode){
		String hql = "From TblStudyScheduleNode where studyTypeCode = ? and nodeSn = ?  ";
		TblStudyScheduleNode tblStudyScheduleNode =  (TblStudyScheduleNode) getSession().createQuery(hql)
		.setParameter(0, studyTypeCode)
		.setParameter(1, currentNodeSn)
		.uniqueResult();
		
		return tblStudyScheduleNode;
	}
	
}
