package com.lanen.jsonAndModel;

import java.io.Serializable;
/**
 * Json模型
 * @author 黄国刚
 *
 */

public class Json implements Serializable{
	
	private static final long serialVersionUID = -6256293619391233179L;
	
	private boolean success =false;// 是否成功
	private String msg ="";		   //提示信息
	private Object obj = null;     //其他信息
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	
}
