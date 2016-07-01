package com.lanen.service.company;


import java.io.File;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.company.CompanyInfo;
import com.lanen.util.FileOperateUtil;

@Service
public class CompanyInfoServiceImpl extends BaseDaoImpl<CompanyInfo> implements CompanyInfoService{

	
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> save(String companyName,String imgName,File file){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("success", true);
		try{
			String key = getKey("CompanyInfo");
			
			byte[] buffer = FileOperateUtil.getInstance().getBytes(file);  
	       
			CompanyInfo com = new CompanyInfo();
			com.setId(key);
			com.setImgName(imgName);
			com.setCompanyName(companyName);
			
			com.setCompanyLogo(buffer);
			save(com);
			
		}catch(Exception e){
			map.put("success", false);
			map.put("msg", "数据库交互异常");
		}
		
		return map;
		
	}

	public CompanyInfo getNewestRecord()
	{
		String hql = "from CompanyInfo order by id desc";
		List<CompanyInfo> list = getSession().createQuery(hql).list();
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}else{
			return null;
		}
		
	}
	

	
	/*public List<DictReportCode> getAllReportCodeList(){
		
		String hql = "from DictReportCode order by id desc";
		List<DictReportCode> list = getSession().createQuery(hql).list();
		
		return list;
	}*/
}
