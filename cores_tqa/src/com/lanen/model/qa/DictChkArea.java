package com.lanen.model.qa;

import java.util.HashSet;
import java.util.Set;


/**
 * QafileType entity. @author MyEclipse Persistence Tools
 */

public class DictChkArea  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 4012313763240015768L;
	
	 private String areaID;
     private String parentAreaID;
     private String areaName;
     
     /** full constructor */
     public DictChkArea(String areaID, String parentAreaID, String areaName) {
        this.areaID=areaID;
        this.parentAreaID=parentAreaID;
        this.areaName = areaName;
     }

    
     // Property accessors

   
    public String getAreaID() {
		return areaID;
	}

	public void setAreaID(String areaID) {
		this.areaID = areaID;
	}

	public String getParentAreaID() {
		return parentAreaID;
	}

	public void setParentAreaID(String parentAreaID) {
		this.parentAreaID = parentAreaID;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}



}