<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>main</title>
	<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/dictVisceraAction/list.js"></script>
	<style type="text/css">
		.tree-icon {
			width:0px;
		}
	</style>
</head>
<body>
<!-- 存放treegrid加载后,待选中的行id -->
<input type="hidden" id="selectId"/>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
	<div region="center" title="" style="overflow: hidden;">
		<div class="easyui-tabs" fit="true" border="false">
			<div title="脏器字典" border="false" style="overflow: auto;">
			<table id="visceraTable" ></table>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/jsp/dictVisceraAction/visceraAddEdit.jspf" %>
</div>
<div id="toolbar" style="display:none;">
	<a id="addOne" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">新建</a>
	<a id="addSon" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add',disabled:true">新建下一级</a>
	<a id="editOne" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit',disabled:true">编辑</a>
	<a id="delOne" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove',disabled:true">删除</a>
	<a id="upOne" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo',disabled:true">上移</a>
	<a id="downOne" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-redo',disabled:true">下移</a>
	<input id="lineNum" type="text" class="easyui-numberbox" 
	    value="1" data-options="min:1,precision:0,max:1000" style="width:35px;"></input>
	行
	&nbsp;<select id="sortType" panelHeight=70/>
	        <option value="1" selected=true>按系统顺序</option>   
		    <option value="2" >按称重顺序</option>   
		    <option value="3">按固定顺序</option>   
   </select>  
	       
</div>
</body>
</html>
