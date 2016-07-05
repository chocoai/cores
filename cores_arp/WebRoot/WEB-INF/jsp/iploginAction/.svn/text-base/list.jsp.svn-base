<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>系统日志</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
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
			 var username=$('#username').val();
          	 /*var start=$('#start').datebox('getValue');
         	 var end=$('#end').datebox('getValue');*/
		     showIploginTable(username);
			}, 
			prompt:'模糊查询,请输入结论关键字' 
			}); 
				
		showIploginTable('');
		
		
		//显示整个布局
		$('#layoutMainDiv').css('display','');
    });//匿名函数结束
    function showIploginTable(username){
    	$('#iploginTable').datagrid({
			url : sybp()+'/iploginAction_loadList.action?username='+encodeURIComponent(username),
			title:'',
			height:tableHeight,
			width:tableWidth,
			nowarp:  false,//单元格里自动换行
			singleSelect:true,
			fitColumns:false,
		    pagination:true,//分页
			sortName:'id',
			columns :[[{
				title:'id',
				field:'id',
				width:150,
				hidden:true
			},{
				title:'登录IP',
				field:'ip',
				width:180,
				halign:'center',
				align:'center'
			},{
				title:'登录用户',
				field:'username',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'登录时间',
				field:'logintime',
				width:280,
				halign:'center',
				align:'center'
			}
			]],
			onSelect:function(rowIndex, rowData){
			},
			onLoadSuccess:function(data){
	         
			},
			toolbar:'#toolbar',
			pageSize : 12,//默认选择的分页是每页5行数据         
			pageList : [ 5, 10, 12, 15, 20 ],//可以选择的分页集合       
		    nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取       
		    striped : true,//设置为true将交替显示行背景。      
		    collapsible : true//显示可折叠按钮 
			
	   	});
    }
     function onSearchButton(){
          var username=$('#username').val();
          /*var start=$('#start').datebox('getValue');
          var end=$('#end').datebox('getValue');*/
          showIploginTable(username);
      }
</script>
</head>
<body>
<s:hidden id="onchange" name="onchange"></s:hidden>
<div id = "progressBar" style="position:absolute;z-index:100;left:30%; top:50%; "> <div id="p" style="width:400px;"></div> 
</div>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
 	<div region="center" title="" style="overflow: hidden;">
 	 	<div id="tabs" class="easyui-tabs" fit="true" border="false">
            <div title="系统日志" border="false" style="overflow: auto;">
                <div style="height:30px;">
			       <div style="float:left; width:100%;padding-top:5px;height:22px;">
			    		&nbsp;&nbsp;登录用户：
			    		<input id="username" type="text" name="username"   style="width:100px;"></input><!--
			    	    &nbsp;&nbsp;&nbsp;&nbsp; 登陆时间：
		                &nbsp;
			        	&nbsp;<input id="start" type="text" class="easyui-datebox" style="width:100px;height:19px;" editable="false"></input>至	
			      	  	<input id="end" type="text" class="easyui-datebox" style="width:100px;height:19px;" editable="false"></input>&nbsp;-->
			      	  	<a id="searchButton" class="easyui-linkbutton" onclick="onSearchButton();" data-options="iconCls:'icon-search',plain:true">搜索</a>
			    	</div>
			    </div>
				<table id="iploginTable" ></table>
            </div>
		</div>
        <div id="toolbar"><!--
			<a id="addWeaningButton" class="easyui-linkbutton" onclick="onAddButton();"  data-options="iconCls:'icon-groupadd',plain:true,disabled:true">添加</a>
		--></div>
 	</div>
</div>
</body>
</html>