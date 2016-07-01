package com.lanen.model.path;

import java.io.Serializable;

/**
 * 脏器所属动物
 * @author 黄国刚
 *
 */
public class DictVisceraAnimal implements Serializable{

	private static final long serialVersionUID = 8304892387784541065L;
	
	private String id;					//
	private String visceraCode;			//脏器编号
	private String animalTypeName;      //动物类别名称
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVisceraCode() {
		return visceraCode;
	}
	public void setVisceraCode(String visceraCode) {
		this.visceraCode = visceraCode;
	}
	public String getAnimalTypeName() {
		return animalTypeName;
	}
	public void setAnimalTypeName(String animalTypeName) {
		this.animalTypeName = animalTypeName;
	}
	
	
	
}
