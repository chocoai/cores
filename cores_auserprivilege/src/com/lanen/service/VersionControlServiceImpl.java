package com.lanen.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.VersionControl;
@Service
public class VersionControlServiceImpl extends BaseDaoImpl<VersionControl> implements VersionControlService {

	@SuppressWarnings("unchecked")
	public boolean isValidVersion(String systemName, String version) {
		List<VersionControl> list = getSession()
		.createQuery("From VersionControl where systemName = ? ")
		.setParameter(0, systemName)
		.list();
		if(null != list && list.size()>0){
			String leastVersion = list.get(0).getLeastVersion();
			if(null !=leastVersion && null !=version ){
				if(leastVersion.matches("(\\d\\.){1,3}\\d+") && version.matches("(\\d\\.){1,3}\\d+")){
					String[] leastVersions = leastVersion.split("\\.");
					String[] versions = version.split("\\.");
					int length_leastVersions = leastVersions.length; 
					int length_versions = versions.length; 
					int min = length_versions;
					boolean isValid = false;// 当1.0  与 1.0.1 时，长的1.0.1大
					if(length_versions>=length_leastVersions){
						isValid =true;
						min = length_leastVersions;
					}
					for(int i=0;i<min;i++){
						String lv = leastVersions[i];
						String v = versions[i];
						int lvInt = Integer.valueOf(lv);
						int vInt = Integer.valueOf(v);
						if(lvInt>vInt){
							return false;
						}else if((lvInt<vInt)){
							return true;
						}
					}
					return isValid;
				}else{
					return true;
				}
			}else{
				return true;
			}
		}else{
			return true;
		}
	}

}
