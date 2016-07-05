<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>养殖场信息</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/tableCss.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/yzcAction/yzcAddEdit.js" charset="utf-8"></script>
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
			 var yzcname=$('#yzcname').val();
		     showYZCTable(yzcname);
			}, 
			prompt:'模糊查询,请输入结论关键字' 
			}); 
				
		showYZCTable('');
		
		
		//显示整个布局
		$('#layoutMainDiv').css('display','');
    });//匿名函数结束
    function showYZCTable(yzcname){
    	$('#yzcTable').datagrid({
			url : sybp()+'/yzcAction_loadList.action?yzcmane='+encodeURIComponent(yzcname),
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
				title:'养殖场名称',
				field:'yzcmane',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'详细地址',
				field:'xxdz',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'单位性质',
				field:'dwxz',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'负责人',
				field:'fzr',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'法人代表',
				field:'frdb',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'邮政编码',
				field:'yzbm',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'联系电话',
				field:'lxdh',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'中级人数',
				field:'zjrs',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'技术人数',
				field:'jsrs',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'兽医人数',
				field:'syyrs',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'饲养人数',
				field:'syrs',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'检测人数',
				field:'jcrs',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'后勤人数',
				field:'hqrs',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'管理员数',
				field:'glrs',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'职工总人数',
				field:'zgzrs',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'建筑面积',
				field:'jzmj',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'饲养面积',
				field:'symj',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'实验室',
				field:'sys',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'检疫房',
				field:'jyf',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'饲料房',
				field:'slf',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'兽医室',
				field:'syf',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'仪器设备',
				field:'yqsb',
				width:80,
				halign:'center',
				align:'center'
			}
			]],
			onSelect:function(rowIndex, rowData){
				$('#addYzcButton').linkbutton('enable');
    		  $('#editYzcButton').linkbutton('enable');
    		  $('#deleteYzcButton').linkbutton('enable');
			},
			onLoadSuccess:function(data){
				$('#addYzcButton').linkbutton('enable');
	    	   $('#editYzcButton').linkbutton('disable');
	           $('#deleteYzcButton').linkbutton('disable');
	           var selectid=$('#yzcid').val();
	           if(selectid != ""){
			          for(var i = 0 ; i< data.rows.length;i++){
			             if(selectid == data.rows[i].id){
			            	$('#yzcTable').datagrid('selectRow',i);
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
    //添加按钮事件
    function onAddButton(){
    	showYzcAddEditDialog('afterAddDialog','add','添加养殖场信息');
    }
    
	//	添加Dialog后事件
    function afterAddDialog(){
       parent.showMessager(1,'添加成功',true,5000);
       $('#yzcTable').datagrid('reload');
    }
    // 编辑按钮事件
    function onEditButton(){
         var row= $('#yzcTable').datagrid('getSelected');
        if(row != null ){
        	showYzcAddEditDialog('afterEditDialog','edit','编辑养殖场信息');
        }else{
           $.messager.alert('提示','请选择编辑的养殖场!');
        }
    }
   //编辑后事件
     function afterEditDialog(){
    	 parent.showMessager(1,'编辑成功',true,5000);
    	 $('#yzcTable').datagrid('reload');
     }
  
   
   //删除按钮事件(数据库还保存)
       function onDeleteButton(){
     	  var row= $('#yzcTable').datagrid('getSelected');
     	 if(null != row ){
          	$.messager.confirm('确认对话框', '确认删除此养殖场记录？', function(r){
 				if (r){
 					delYZC(row.id);
 					
 				}
 			});
         }else{
           $.messager.alert('提示','请选择要删除的养殖场记录!');
         }
      }
       function delYZC(id){
   	   	$.ajax({
   			url:sybp()+'/yzcAction_delYzc.action',
   			type:'post',
   			data:{id:id},
   			dataType:'json',
   			success:function(r){
   				if(r && r.success){
   					 parent.showMessager(1,'记录删除成功',true,5000);
   					$('#yzcTable').datagrid('reload');
   				}else{
   					 parent.showMessager(2,'记录删除失败',true,5000);
   				}
   			}
   		});
       } 
    
     function onSearchButton(){
          var yzcname=$('#yzcname').val();
          showYZCTable(yzcname);
      }
</script>
</head>
<body>
<s:hidden id="yzcid" name="yzcid"></s:hidden>
<s:hidden id="onchange" name="onchange"></s:hidden>
<div id = "progressBar" style="position:absolute;z-index:100;left:30%; top:50%; "> <div id="p" style="width:400px;"></div> 
</div>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
 	<div region="center" title="" style="overflow: hidden;">
 	 	<div id="tabs" class="easyui-tabs" fit="true" border="false">
            <div title="养殖场信息" border="false" style="overflow: auto;">
                <div style="height:30px;">
			       <div style="float:left; width:100%;padding-top:5px;height:22px;">
			    		&nbsp;&nbsp;养殖场名称：
			    		<input id="yzcname" type="text" name="yzcname"   style="width:100px;"></input>
			      	  	<a id="searchButton" class="easyui-linkbutton" onclick="onSearchButton();" data-options="iconCls:'icon-search',plain:true">搜索</a>
			    	</div>
			    </div>
				<table id="yzcTable" ></table>
            </div>
		</div>
        <div id="toolbar">
			<a id="addYzcButton" class="easyui-linkbutton" onclick="onAddButton();"  data-options="iconCls:'icon-groupadd',plain:true,disabled:true">添加</a>
			<a id="editYzcButton" class="easyui-linkbutton" onclick="onEditButton();" data-options="iconCls:'icon-groupedit',plain:true,disabled:true">编辑</a>
			<a id="deleteYzcButton" class="easyui-linkbutton" onclick="onDeleteButton()" data-options="iconCls:'icon-groupdelete',plain:true,disabled:true">删除</a>
		</div>
 	</div>
 	<%@include file="/WEB-INF/jsp/yzcAction/yzcAddEdit.jspf" %>
</div>
</body>
</html>