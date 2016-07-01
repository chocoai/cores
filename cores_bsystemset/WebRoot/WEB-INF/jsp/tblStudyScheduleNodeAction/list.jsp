<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/tblStudyScheduleNodeAction/list.js" charset="utf-8"></script>
<script type="text/javascript">
  
   $(function(){ document.onkeydown = KeyDown;});
</script>
</head>
<body>
	<input id = "currentId" type="hidden" />
	<input id = "studyTypeCode" type="hidden" value= "${studyTypeCode}"/>
	<div id="maindiv" class="easyui-layout" fit="true" border="false" style="display:none;">
		<div region="north" border="false"  style="height:26px;">
			<div class="page_tab">
		      <ul>
		          <li class="active"><span><span><a href="javascript:void(0);">专题进度节点设置</a></span></span></li>
		      </ul>
			</div>
		</div>
		<!-- table start -->
		<div region="center" border=false style="border: 1px solid #c8c8c8;">
			<div style="height:16px;padding:5px 15px;border-bottom: 1px solid #c8c8c8;">专题名称：${studyName}</div>
			<table id="studyNodeTable" ></table>
		</div>
		<!-- table end -->
		<div id="toolbar">
			<a id="addToolbarButton"  class="easyui-linkbutton" onclick="onAddToolbarButton();" data-options="iconCls:'icon-add',disabled:true,plain:true">添加</a>
			<a id="editToolbarButton" class="easyui-linkbutton" onclick="onEditToolbarButton();" data-options="iconCls:'icon-edit',disabled:true,plain:true">编辑</a>
			<a id="delToolbarButton" class="easyui-linkbutton" onclick="onDelToolbarButton();" data-options="iconCls:'icon-remove',disabled:true,plain:true">删除</a>
			<a id="upToolbarButton" class="easyui-linkbutton" onclick="onUpToolbarButton();" data-options="iconCls:'icon-undo',disabled:true,plain:true">上移</a>
			<a id="downToolbarButton" class="easyui-linkbutton" onclick="onDownToolbarButton();" data-options="iconCls:'icon-redo',disabled:true,plain:true">下移</a>
			<a class="easyui-linkbutton" plain="true" href="${pageContext.request.contextPath}/dictStudyTypeAction_list.action?studyTypeCode=${studyTypeCode}" data-options="iconCls:'icon-back'">返回</a>
		</div>
		<!-- 添加/编辑dialog -->
		<div id="studyNodeAddEditDialog" class="easyui-dialog" title=" 添加进度节点" modal="true" closed="true" data-options="toolbar:'#dialogToolbar'" style="display:'';width:300px;height:210px;" closable="true">
		    <div  style=" margin:5px; border:0;overflow-x:auto; overflow-y:auto;">
				<input type="hidden" id="studyNodeAddOrEdit"/>
			  	<form id="studyNodeForm" name="studyNodeForm" action="" method="post">
		   	 	 <table width="100%">
			          <tr style="height:60px;">
        				<td width="90px;" align="right">节点名称</td>
        				<td>
        				    <input type="hidden" id="id" name="id" />
        				    <input type="hidden" id="oldnodeName"  />
                            <input id="_nodeName" name="_nodeName" class="easyui-validatebox" style="position:absolute;left:115px;width:113px;height:16px;"
                            	data-options="validType:'maxLength[40]'" 
	                        	required="true" missingMessage="必填项" invalidMessage="节点名称已存在"
	                        	validType="remotett[sybp()+'/tblStudyScheduleNodeAction_checkNodeName.action?studyTypeCode=${studyTypeCode}','nodeName','#oldnodeName']" 
                        	 />  
                             <input id="nodeNameCombobox" class="easyui-combobox" style="width:134px;height:20px;" data-options="
                        	 panelHeight:100,valueField: 'id',  textField: 'text',onSelect:function(record){setNodeNameValue(record.id);},   
        						url: '${pageContext.request.contextPath}/tblStudyScheduleNodeAction_loadNodeName.action'"
                        	/>
       				 	</td>
        			</tr>
        			<tr >
        				<td align="right">试验分组后</td>
        				<td><input id=planDays name="planDays" class="easyui-validatebox" 
        					data-options="validType:'isInteger'"  style="width:130px;height:16px;"></input>&nbsp;天
        				</td>
        			</tr>
		       	</table>
		        </form>
		        <a id="studyNode_click"></a>
		    </div>
		</div>
		<!-- 工具栏 开始-->
		<div id="dialogToolbar">
			<a id="saveDialogButton" class="easyui-linkbutton" onclick="onDialogStudyNodeSaveButton();" data-options="iconCls:'icon-ok',plain:true">确定</a>
			<a id="backButton" class="easyui-linkbutton" onclick="javascript:$('#studyNodeAddEditDialog').dialog('close');" data-options="iconCls:'icon-nook',plain:true">取消</a>
		</div>
		<!-- 工具栏 结束-->
	<!-- 添加/编辑dialog 结束 -->
	</div>
</body>
</html>