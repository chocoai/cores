package com.lanen.jsonAndModel;

import java.io.Serializable;

public class HighGradeTreeGrid implements Serializable {

	private static final long serialVersionUID = 9221167012181508055L;
	private String id;
	private String code;			//编号
	private String name;			//名称
	private String type ;			//类型
	private String text;			//其他
	
	private int level;			//1:合同，2：供试品   3：委托项目
	private String _parentId;		//父级Id
	private String state;  			//'open','closed'
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String get_parentId() {
		return _parentId;
	}
	public void set_parentId(String parentId) {
		_parentId = parentId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
	
}
