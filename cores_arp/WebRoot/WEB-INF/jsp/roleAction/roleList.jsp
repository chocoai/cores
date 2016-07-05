<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>系统角色</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/roleAction/roleAddEdit.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/roleAction/privilege.js" charset="utf-8"></script>
<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 30
	var tableWidth;
	$(function(){
		tableHeight = document.body.clientHeight - 60;
		tableWidth  = document.body.clientWidth;
		$('#SearchButton').searchbox({ 
		    height:19,
		    width:450,
			searcher:function(value,name){ 
			 var rolename=$('#rolename').val();
		     showRoleTable(rolename);
			}, 
			prompt:'模糊查询,请输入结论关键字' 
			}); 
				
		showRoleTable('','','');
		
		 //end treegrid
		
		//显示整个布局
		$('#layoutMainDiv').css('display','');
    });//匿名函数结束
    function showRoleTable(rolename){
    	$('#roleTable').datagrid({
			url : sybp()+'/roleAction_loadList.action?name='+encodeURIComponent(rolename),
			title:'',
			height:tableHeight,
			width:tableWidth,
			nowarp:  false,//单元格里自动换行
			singleSelect:true,
			fitColumns:false,
		    pagination:true,//分页
			sortName:'id',
			columns :[[{
				title:'角色编号',
				field:'id',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'角色名称',
				field:'name',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'角色描述',
				field:'description',
				width:280,
				halign:'center',
				align:'center'
			}
			]],
			onSelect:function(rowIndex, rowData){
    		  $('#addWeaningButton').linkbutton('enable');
    		  $('#privilegeButton').linkbutton('enable');
    		  $('#deleteWeaningButton').linkbutton('enable');
			},
			onLoadSuccess:function(data){
			   $('#addWeaningButton').linkbutton('enable');
	    	   $('#privilegeButton').linkbutton('disable');
	           $('#deleteWeaningButton').linkbutton('disable');
	           var selectid=$('#rid').val();
	           if(selectid != ""){
			          for(var i = 0 ; i< data.rows.length;i++){
			             if(selectid == data.rows[i].id){
			            	$('#roleTable').datagrid('selectRow',i);
			             }
			          }
			   }
			},
			toolbar:'#toolbar',
			pageSize : 12,//默认选择的分页是每页5行数据         
			pageList : [ 5, 10, 12, 15, 20 ],//可以选择的分页集合       
		    nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取       
		    striped : true,//设置为true将交替显示行背景。      
		    collapsible : true//显示可折叠按钮 
			
	   	});
    } 
	
    //权限
    function onPrivilegeButton(){
    	showPrivilegeDialog('afterPrivilegeDialog','角色授权');
         var row= $('#roleTable').datagrid('getSelected');
      
    }
   //权限后事件
     function afterPrivilegeDialog(){
    	 parent.showMessager(1,'授权成功',true,5000);
    	 $('#roleTable').datagrid('reload');
     }
      
     function onSearchButton(){
          var rolename=$('#rolename').val();
          showRoleTable(rolename);
      }
</script>
</head>
<body>
<s:hidden id="rid" name="rid"></s:hidden>
<s:hidden id="onchange" name="onchange"></s:hidden>
<div id = "progressBar" style="position:absolute;z-index:100;left:30%; top:50%; "> <div id="p" style="width:400px;"></div> 
</div>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
 	<div region="center" title="" style="overflow: hidden;">
 	 	<div id="tabs" class="easyui-tabs" fit="true" border="false">
            <div title="角色列表" border="false" style="overflow: auto;">
                <div style="height:30px;">
			       <div style="float:left; width:100%;padding-top:5px;height:22px;">
			    		&nbsp;&nbsp;角色名称
			    		<input id="rolename" type="text" name="rolename"   style="width:100px;"></input>
			    	    <a id="searchButton" class="easyui-linkbutton" onclick="onSearchButton();" data-options="iconCls:'icon-search',plain:true">搜索</a>
			    	</div>
			    </div>
				<table id="roleTable" ></table>
            </div>
		</div>
        <div id="toolbar">
			<a id="privilegeButton" class="easyui-linkbutton" onclick="onPrivilegeButton();" data-options="iconCls:'icon-groupedit',plain:true,disabled:true">权限</a>
		</div>
 	</div>
    <%@include file="/WEB-INF/jsp/roleAction/roleAddEdit.jspf" %>
    <%@include file="/WEB-INF/jsp/roleAction/privilege.jspf" %>
</div>
</body>
</html>