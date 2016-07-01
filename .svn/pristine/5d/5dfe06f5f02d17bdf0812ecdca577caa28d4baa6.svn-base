package com.lanen.service.contract;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.User;
import com.lanen.model.contract.TblNotification;
import com.lanen.service.UserService;
import com.lanen.util.mail.SimpleMailSender;

@Service
public class TblNotificationServiceImpl  extends BaseDaoImpl<TblNotification> implements TblNotificationService{

	@Resource
	private UserService userService;
	@SuppressWarnings("unchecked")
	public void save(TblNotification tblNotification, List<String> receiverList) {

		if(null != receiverList){
			for(String userName:receiverList){
				TblNotification tblNotification2 = new TblNotification();
				
				tblNotification2.setId(getKey());
				tblNotification2.setMsgTitle(tblNotification.getMsgTitle());
				tblNotification2.setMsgContent(tblNotification.getMsgContent());
				tblNotification2.setMsgLink(tblNotification.getMsgLink());
				tblNotification2.setMsgType(tblNotification.getMsgType());
				tblNotification2.setNeedReceipt(tblNotification.getNeedReceipt());
				tblNotification2.setAttachmentFlag(tblNotification.getAttachmentFlag());
				tblNotification2.setMsgSource(tblNotification.getMsgSource());
//				tblNotification2.setReadFlag(0);
				tblNotification2.setReceiver(userName);//接收者
//				tblNotification2.setRecTime(null);
				tblNotification2.setSender(tblNotification.getSender());
				tblNotification2.setSendTime(tblNotification.getSendTime());
				
				getSession().save(tblNotification2);
				
			}
		}
		if(null != receiverList){
			String sql = "select smtpServer,mailCode,password,isAuto"+
						" from CoresSystemSet.dbo.tblMailServer "+
						" where id = '1'";
			Map<String,Object> map = (Map<String, Object>) getSession().createSQLQuery(sql)
												.setResultTransformer(new MapResultTransformer())
												.uniqueResult();
			if(null != map){
				String smtpServer = (String) map.get("smtpServer");
				String mailCode = (String) map.get("mailCode");
				String password = (String) map.get("password");
				Integer isAuto = (Integer) map.get("isAuto");
				if(null != isAuto && isAuto == 1 && null != smtpServer && !"".equals(smtpServer) && 
						null != mailCode && !"".equals(mailCode) && null != password && !"".equals(password)){
					
					for(String userName:receiverList){
						//				tblNotification2.setMsgTitle(tblNotification.getMsgTitle());
						//				tblNotification2.setMsgContent(tblNotification.getMsgContent());
						//				tblNotification2.setReceiver(userName);//接收者
						//				tblNotification2.setSender(tblNotification.getSender());
						User user = userService.getByUserName(userName);
						if(null == user || null == user.getEmail() || "".equals(user.getEmail())){
							continue;
						}
						SimpleMailSender mailSender = new SimpleMailSender(smtpServer,mailCode,password);
						String recipients = user.getEmail();
						String subject = tblNotification.getMsgTitle();
						String content = tblNotification.getMsgContent()+"<br>http://cores.sac.com<br>系统邮件，请勿回复！";
						try {
							mailSender.send(recipients, subject, content);
						} catch (AddressException e) {
//						System.out.println("--------------"+"发送邮件异常AddressException");
							e.printStackTrace();
						} catch (MessagingException e) {
//						System.out.println("--------------"+"发送邮件异常MessagingException");
							e.printStackTrace();
						}catch (Exception e) {
//						System.out.println("--------------"+"发送邮件异常Exception");
							e.printStackTrace();
						}finally{
							System.out.println("--------------"+"发送邮件异常finally");
						}
					}
				}
			}
		}
		
	}


	public void setRecTime(List<String> idList, Date date) {
		if(null != idList && idList.size()>0 && null != date){
			String sql = "update CoresContract.dbo.tblNotification"+
						" set recTime = :date"+
						" where id in (:idList) and recTime is null";
			getSession().createSQLQuery(sql)
						.setParameter("date", date)
						.setParameterList("idList", idList)
						.executeUpdate();
		}
		
	}


	public List<String> getUnRecMail(String userName) {
		String sql="SELECT id  FROM [CoresContract].[dbo].[tblNotification] where recTime is null and receiver=:userName ";
		List<String> list=new ArrayList<String>();
		List<?> objList=getSession().createSQLQuery(sql)
									.setParameter("userName", userName)
									.list();
		if(null!=objList){
			for(Object obj:objList){
				list.add((String)obj);
			}
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, String>> getAllSenderByReceiver(String receiver) {
		String sql ="select id,text"+
					" from("+
					" 		select distinct '' as id,'全部' as text"+
					" 		union"+
					" 		select distinct noti.sender as id,noti.sender as text"+
					" 		from CoresContract.dbo.tblNotification as noti"+
					" 		where noti.receiver = :receiver) as t"+
					" order by id";
		List<Map<String,String>> list = getSession().createSQLQuery(sql)
													.setParameter("receiver", receiver)
													.setResultTransformer(new MapResultTransformer())
													.list();
		return list;
	}


	@SuppressWarnings("unchecked")
	public List<TblNotification> getListBySenderReceiverDate(String receiver,
			String sender, Date startDate, Date endDate) {
		String hql =" From TblNotification as noti"+
					" where( ( CONVERT(date,noti.sendTime,120)) between :startDate and :endDate " +
					" or ( CONVERT(date,noti.sendTime,120)) between :endDate and  :startDate )"+
					" and noti.receiver = :receiver"+
					" and (noti.sender = :sender or :sender = '')" +
					" order by noti.sendTime desc";
		List<?> list = getSession().createQuery(hql)
									.setParameter("startDate", startDate)
									.setParameter("endDate", endDate)
									.setParameter("receiver", receiver)
									.setParameter("sender", sender)
									.list();
		
		return (List<TblNotification>) list;
	}


}
