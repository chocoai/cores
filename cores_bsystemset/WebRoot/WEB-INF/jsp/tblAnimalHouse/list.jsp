<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/animalHouse.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblAnimalHouse.js" charset="utf-8"></script>
<script type="text/javascript">
	var AnimalHouseTable;
	var tableSize ;
	var AnimalHouseUl;
	$(function(){
		AnimalHouseTable= $('#AnimalHouseTable').treegrid({ 
			url : sybp()+'/tblAnimalHouseAction_loadList2.action?rid='+$('#rid').val(),
			idField:'id',    
			height: 525,
		    treeField:'resName', 
		    animate:false,   
		    singleSelect: true, //不支持多选
		    columns:[[  
		                {title:'id',field:'id',width:180,hidden:true},
				        {field:'resKind',title:'resKind',width:180,hidden:true}, 
				        {title:'资源名称',field:'resName',width:180},    
				        {field:'_parentId',title:'_parentId',width:180,hidden:true},
				    ]],
				  //工具栏
					toolbar:'#toolbar',
			    	onClickRow :function(row){
			          $(this).treegrid('unselectAll');
			          $(this).treegrid('select',row.id);
			          $('#editAnimalHouseButton').linkbutton('enable');
					  $('#delAnimalHouseButton').linkbutton('enable');
			       },	
					onLoadSuccess:function(row, data){
		     	      if(data && data.rid){
	     			     $('#AnimalHouseTable').treegrid('expandTo',data.rid);
	     			     $('#AnimalHouseTable').treegrid('select',data.rid);
	     			     $('#editAnimalHouseButton').linkbutton('enable');
					     $('#delAnimalHouseButton').linkbutton('enable');
				      }
		           }
		 });
	});

	
	
	//新增
	function onaddAnimalHouseButton(){
		//var parentId = $('#nodeid').val();
          var rows = $('#AnimalHouseTable').treegrid('getSelected');
          var parentId;
          if(rows != null ){
        	  parentId =  rows.id;
        	  $.ajax({
      			url : sybp()+'/tblAnimalHouseAction_CheckResKind.action?parentId='+parentId,
      			type: 'post',
      			contentType: "application/x-www-form-urlencoded; charset=utf-8", 
      			dataType:'json',
      			success:function(r){
      				if(r.success && r){
      					window.location.href=sybp()+'/tblAnimalHouseAction_addUI.action?parentId='+parentId;
      					$('#nodeid').val("");
      			    }else{
      			    	$.messager.alert('提示','请选择建筑或者楼层进行相应的设置操作','info');
      				}
      			}
             });	
          }else{
        	  window.location.href=sybp()+'/tblAnimalHouseAction_addUI.action';
          }
          
		
	}
	//编辑
	function oneditAnimalHouseButton(){
		var rows = $('#AnimalHouseTable').treegrid('getSelected');
		if(rows.id != ""){
			window.location.href=sybp()+'/tblAnimalHouseAction_editUI.action?id='+rows.id;
		}else if(rows.id == ""){
			parent.showMessager(2,'请选择编辑行',true,5000);
		}
	}
	//删除
	function ondelAnimalHouseButton(){
		var rows = $('#AnimalHouseTable').treegrid('getSelected');
		if(rows.id != ""){
		$.ajax({
			   url:sybp()+'/tblAnimalHouseAction_beforDelete.action',
			   type:'post',
			   data:{
					id:rows.id
				},
			   dataType:'json',
			   success:function(r){
				  if(r && r.success){
							$.messager.confirm('提示', '确定删除\' '+rows.resName+'\' ?', function(r){
				    if (r){
							$.ajax({
								url:sybp()+'/tblAnimalHouseAction_delete.action',
								type:'post',
								data:{
									id:rows.id
								},
								dataType:'json',
								success:function(r){
									if(r && r.success){
										//删除节点
										// $('#AnimalHouseUl').tree('remove',nodes.target);
										$('#AnimalHouseTable').treegrid('reload');
										//禁用按钮（编辑，删除）
										$('#editAnimalHouseButton').linkbutton('disable');
										$('#delAnimalHouseButton').linkbutton('disable');
										parent.showMessager(1,r.msg,true,5000);
										
									}else{
										parent.showMessager(3,r.msg,true,5000);
									}
								}
							});
						}
					});
				    }else{
						parent.showMessager(3,r.msg,true,5000);
					}
			    }
		});		

		}else if(nodes.id == ""){
			parent.showMessager(2,'请选择删除项',true,5000);
		}
	}
	
</script>
</head>
<body>
<s:hidden id="rid" name="rid"></s:hidden>
<s:hidden id="nodeid" name="nodeid"></s:hidden>
	
	  <div region="north" border="false"  style="height:26px;">
			<div class="page_tab">
		      <ul>
		          <li class="active"><span><span><a href="javascript:void(0);">动物房设置</a></span></span></li>
		      </ul>
			</div>
	</div>
		<!-- table start -->
		<div region="center" border="false" style="border: 1px solid #c8c8c8;">
			<div><table id="AnimalHouseTable" ></table></div>
		</div>
		<!-- table end -->
		<div id="toolbar">
			<a id="addAnimalHouseButton"  class="easyui-linkbutton" onclick="onaddAnimalHouseButton();" data-options="iconCls:'icon-add',plain:true">新增</a>
			<a id="editAnimalHouseButton" class="easyui-linkbutton" onclick="oneditAnimalHouseButton();" data-options="iconCls:'icon-edit',disabled:true,plain:true">编辑</a>
			<a id="delAnimalHouseButton" class="easyui-linkbutton" onclick="ondelAnimalHouseButton();" data-options="iconCls:'icon-remove',disabled:true,plain:true">删除</a>
		</div>
	
	
</body>
</html>