<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>防疫管理</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/treasuryAction/treasuryAddEdit.js" charset="utf-8"></script>
<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 30
	var tableWidth;
	$(function(){
		tableHeight = document.body.clientHeight - 60;
		tableWidth  = document.body.clientWidth;
		$('#tabs').tabs({    
		    onSelect:function(title,index){
		      if(index == 1){
		    	  var tab2=$('#tabs').tabs('getTab',index);
		    	  $('#tabs').tabs('update', {
		    			tab: tab2,
		    			options: {
		    				title: '疾病系统',
		    				content: '<iframe src="' + '${pageContext.request.contextPath}/symptomsareaAction_list.action'+ ' "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>'
		    			}
		    		});
		      }
		      if(index == 2){
		    	  var tab2=$('#tabs').tabs('getTab',index);
		    	  $('#tabs').tabs('update', {
		    			tab: tab2,
		    			options: {
		    				title: '症状',
		    				content: '<iframe src="' + '${pageContext.request.contextPath}/symptomsAction_list.action'+ ' "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>'
		    			}
		    		});
		      }
		     
		    }    
		});  
				
		//初始化出场记录信息表
		showTreasuryTable('');
		
		 //end treegrid
		
		//显示整个布局
		$('#layoutMainDiv').css('display','');
    });//匿名函数结束
    function showTreasuryTable(parasitename){
    	$('#treasuryTable').datagrid({
			url : sybp()+'/treasuryAction_loadList.action?name='+parasitename,
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
				title:'疾病名称',
				field:'name',
				width:100,
				halign:'center',
				align:'center'
			},{
				title:'疾病系统',
				field:'symptomssite',
				width:80,
				halign:'center',
				align:'center'
				//formatter:function(value,row,index){
				///	if ( value == 1 ){
				//		return "呼吸系统";
				//	}else if(value == 2){
				//		return "生殖系统";
				//	}else if(value == 3){
				//	   return "消化系统";
				//	}else{
				//	    return "外伤";
				//	}
				//}
			},{
				title:'入库时间',
				field:'treasurydate',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'发病原因',
				field:'reason',
				width:280,
				halign:'center',
				align:'center'
			},{
				title:'症状描述',
				field:'symptomsremark',
				width:380,
				halign:'center',
				align:'center'
			},{
				title:'治疗及预防',
				field:'prevention',
				width:280,
				halign:'center',
				align:'center'
			}	
			]],
			
			onSelect:function(rowIndex, rowData){
    		  $('#addTreasuryButton').linkbutton('enable');
   		  	  $('#editTreasuryButton').linkbutton('enable');
    		  $('#deleteTreasuryButton').linkbutton('enable');
			},
			onLoadSuccess:function(data){
			   
			   $('#addTreasuryButton').linkbutton('enable');
	    	   $('#editTreasuryButton').linkbutton('disable');
	           $('#deleteTreasuryButton').linkbutton('disable');
	           var selectid=$('#ttid').val();
	           if(selectid != ""){
			          for(var i = 0 ; i< data.rows.length;i++){
			             if(selectid == data.rows[i].id){
			            	$('#treasuryTable').datagrid('selectRow',i);
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
  //查找按钮事件
    function onSearchButton(){
    	var treasuryname =  $('#treasuryname').val();
    	showTreasuryTable(treasuryname);
    }
	
	//添加按钮事件
    function onAddButton(){
    	showTreasuryAddEditDialog('afterAddDialog','add','添加疾病字典');
    }
    
	//	添加Dialog后事件
    function afterAddDialog(){
       parent.parent.showMessager(1,'添加成功',true,5000);
       $('#treasuryTable').datagrid('reload');
    }
   function onEditButton(){
         var row= $('#treasuryTable').datagrid('getSelected');
        if(row != null ){
        	showTreasuryAddEditDialog('afterEditDialog','edit','编辑疾病字典');
        }else{
           $.messager.alert('提示','请选择编辑的疾病字典!');
        }
    }
     function afterEditDialog(){
    	 parent.parent.showMessager(1,'编辑成功',true,5000);
    	 $('#treasuryTable').datagrid('reload');
     }
  
   //删除按钮事件(数据库还保存)
       function onDeleteButton(){
     	  var row= $('#treasuryTable').datagrid('getSelected');
     	 if(null != row ){
          	$.messager.confirm('确认对话框', '确认删除此疾病字典？', function(r){
 				if (r){
 					delTreasury(row.id);
 					
 				}
 			});
         }else{
           $.messager.alert('提示','请选择要删除的疾病字典!');
         }
      }
       function delTreasury(id){
   	   	$.ajax({
   			url:sybp()+'/treasuryAction_delTreasury.action',
   			type:'post',
   			data:{id:id},
   			dataType:'json',
   			success:function(r){
   				if(r && r.success){
   					 parent.parent.showMessager(1,'疾病字典删除成功',true,5000);
   					$('#treasuryTable').datagrid('reload');
   				}else{
   					 parent.parent.showMessager(2,'疾病字典删除失败',true,5000);
   				}
   			}
   		});
       } 
     
</script>
</head>
<body>
<s:hidden id="ttid" name="ttid"></s:hidden>
<s:hidden id="onchange" name="onchange"></s:hidden>
<div id = "progressBar" style="position:absolute;z-index:100;left:30%; top:50%; "> <div id="p" style="width:400px;"></div> 
</div>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
 	<div region="center" title="" style="overflow: hidden;">
 	 	<div id="tabs" class="easyui-tabs" fit="true" border="false">
            <div title="疾病字典" border="false" style="overflow: auto;">
                <div style="height:30px;">
			       <div style="float:left; width:100%;padding-top:5px;height:22px;">
			    		&nbsp;&nbsp;疾病名称
			    		<input id="treasuryname" type="text"  name=""  style="width:100px;"></input> 
			      	  	&nbsp;<a id="searchButton" class="easyui-linkbutton" onclick="onSearchButton();" data-options="iconCls:'icon-search',plain:true">搜索</a>
			    	</div>
			    
			    </div>
				<table id="treasuryTable" ></table>
            </div>
            <div title="疾病系统" border="false" style="overflow: auto;">
				<table id="symptomsareaTable" ></table>
            </div>
            <div title="症状" border="false" style="overflow: auto;">
				<table id="symptomsTable" ></table>
            </div>
            
		</div>
        <div id="toolbar">
			<a id="addTreasuryButton" class="easyui-linkbutton" onclick="onAddButton();"  data-options="iconCls:'icon-groupadd',plain:true,disabled:true">添加</a>
		  	<a id="editTreasuryButton" class="easyui-linkbutton" onclick="onEditButton();" data-options="iconCls:'icon-groupedit',plain:true,disabled:true">编辑</a>
			<a id="deleteTreasuryButton" class="easyui-linkbutton" onclick="onDeleteButton()" data-options="iconCls:'icon-groupdelete',plain:true,disabled:true">删除</a>
		 	
		</div>
 	</div>
    <%@include file="/WEB-INF/jsp/treasuryAction/treasuryAddEdit.jspf" %>
</div>
</body>
</html>