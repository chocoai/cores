package com.lanen.view.action.company;


import java.io.File;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.User;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.company.CompanyInfo;
import com.lanen.model.studyplan.DictReportNumber;
import com.lanen.model.studyplan.DictReportNumberHis;
import com.lanen.service.clinicaltest.TblESLinkService;
import com.lanen.service.clinicaltest.TblESService;
import com.lanen.service.company.CompanyInfoService;
import com.lanen.service.studyplan.DictReportNumberHisService;
import com.lanen.service.studyplan.DictReportNumberService;
import com.lanen.util.FileOperateUtil;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class CompanyInfoAction extends BaseAction<CompanyInfo>{

	private static final long serialVersionUID = -8316905808843938772L;
	
	@Resource
	private CompanyInfoService companyInfoService;
	@Resource
	private DictReportNumberService dictReportNumberService;
	@Resource
	private DictReportNumberHisService dictReportNumberHisService;
	@Resource
	private TblESService tblESService;
	@Resource
	private TblESLinkService tblESLinkService;
	
	
	private String uploadImgName;
	private File uploadImg;
	private String uploadImgFileName;    
	private String uploadImgContentType;
	
	private String number;
	private String reason;
	
	public String companyInfo(){
		CompanyInfo info = companyInfoService.getNewestRecord();
		
		if(info==null||"".equals(info))
		{
			info = new CompanyInfo();
			info.setCompanyName("西山中科");
			
			info.setImgName("logo.jpg");
			
			File file = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath()+"/logo.jpg");
			
			if(file.isFile())
			{
				info.setCompanyLogo(FileOperateUtil.getInstance().getBytes(file));
			}
			
		}
		
		request.setAttribute("companyInfo", info);
		
		return "companyInfo";
	}
	
	public String image(){
		
		CompanyInfo info = companyInfoService.getNewestRecord();
		request.setAttribute("companyInfo", info);
		
		
		return "image";
	}
	
	public void saveCompanyInfo()
	{
		
		
		Map<String,Object> map = companyInfoService.save(model.getCompanyName(),uploadImgFileName, uploadImg);
		
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}
	
	//报告编号
	public void loadReportCodeList(){
		List<DictReportNumber> list = dictReportNumberService.getAllReportCodeList();
		
		
		writeJson(JsonPluginsUtil.beanListToJson(list));
	}
	
	public String reportCodeInfo()
	{
		
		
		return "reportCodeInfo";
	}
	
	public void saveReportCode(){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			
			//签名链接
			TblESLink esLink = new TblESLink();
			//电子签名
			TblES es = new TblES();
			//验证通过则进行一下操作
			DictReportNumber dictReportCode = dictReportNumberService.getById(model.getId());
			
			User tempUser = (User) ActionContext.getContext().getSession().get("user");
			es.setSigner(tempUser.getRealName());
			es.setEsTypeDesc("修改报告编码");
			es.setEsType(846);
			es.setDateTime(new Date());
			String eid = tblESService.getKey("TblES");
			es.setEsId(eid);
		
			tblESService.save(es);
			
			esLink.setTableName("DictReportCode");
			esLink.setDataId(model.getId());
			esLink.setTblES(es);
			esLink.setEsType(846);
			esLink.setEsTypeDesc("修改 报告编码"+"签字确认");
			esLink.setRecordTime(new Date());
			esLink.setLinkId(tblESLinkService.getKey("TblESLink"));
			tblESLinkService.save(esLink);
			

			DictReportNumberHis his = new DictReportNumberHis();
			his.setEsLinkId(esLink.getLinkId());
			his.setChangeTime(new Date());
			his.setReason(reason);
			his.setId(dictReportNumberHisService.getKey("DictReportNumberHis"));
			his.setReportName(dictReportCode.getReportName());
			his.setNumber(dictReportCode.getNumber());
			
			dictReportNumberHisService.save(his);
			
			dictReportCode.setNumber(number);
			
			dictReportNumberService.update(dictReportCode);
			
			map.put("success", true);
			map.put("number",number);
			
			map.put("msg","更新成功！");
		}catch(Exception e){
			map.put("success", false);
			map.put("msg","数据库交互失败！");
		}
		writeJson(JsonPluginsUtil.beanToJson(map));
		
	}

	public File getUploadImg() {
		return uploadImg;
	}

	public void setUploadImg(File uploadImg) {
		this.uploadImg = uploadImg;
	}

	public String getUploadImgName() {
		return uploadImgName;
	}

	public void setUploadImgName(String uploadImgName) {
		this.uploadImgName = uploadImgName;
	}

	public String getUploadImgFileName() {
		return uploadImgFileName;
	}

	public void setUploadImgFileName(String uploadImgFileName) {
		this.uploadImgFileName = uploadImgFileName;
	}

	public String getUploadImgContentType() {
		return uploadImgContentType;
	}

	public void setUploadImgContentType(String uploadImgContentType) {
		this.uploadImgContentType = uploadImgContentType;
	}



	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	
	

}
