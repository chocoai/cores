package com.lanen.jsonAndModel;

/**
 * easyui-datagrid 列
 * @author 黄国刚
 *
 */
public class Columns {
	private String title;//标题
	private String field;//字段
	private int width = 105;//宽度
	private int colspan = 1;//  占 n  列
	private int rowspan = 1;//  占 n  行
	private String halign = "center";
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getColspan() {
		return colspan;
	}
	public void setColspan(int colspan) {
		this.colspan = colspan;
	}
	public int getRowspan() {
		return rowspan;
	}
	public void setRowspan(int rowspan) {
		this.rowspan = rowspan;
	}
	public String getHalign() {
		return halign;
	}
	public void setHalign(String halign) {
		this.halign = halign;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null){  
            return false;  
        }else {           
                if(this.getClass() == obj.getClass()){  
                	Columns c = (Columns) obj;  
                    if(this.getField().equals(c.getField())){  
                        return true;  
                    }else{  
                        return false;  
                    }  
                  
            }else{  
                return false;  
            }  
        }
	}
	
}
