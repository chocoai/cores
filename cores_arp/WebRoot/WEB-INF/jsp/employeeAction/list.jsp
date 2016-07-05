<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>员工信息管理</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/alterpassword.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/employeeAction/employeeAddEdit.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/employeeAction/departmentOperate.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/employeeAction/positionOperate.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/employeeAction/titleOperate.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/employeeAction/validatePassword.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/employeeAction/role.js" charset="utf-8"></script>
<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 30
	var tableWidth;
	$(function(){
		tableHeight = document.body.clientHeight - 30;
		tableWidth  = document.body.clientWidth;
		//初始化datagrid
		showEmployeeTable('');
		 //end datagrid
	   	$('#searchEmployee').searchbox({ 
		    height:23,
		    width:200,
			searcher:function(value,name){ 
	         showEmployeeTable(value);
			}, 
			prompt:'模糊查询,请输入员工姓名' 
			}); 
		//显示整个布局
		$('#layoutMainDiv').css('display','');
    });//匿名函数结束

    //表数据加载
    function showEmployeeTable(value){
    	$('#employeeTable').datagrid({
			url : sybp()+'/employeeAction_loadList.action?name='+encodeURIComponent(value),
			title:'',
			height:tableHeight,
			width:tableWidth,
			nowarp:  false,//单元格里自动换行
			singleSelect:true,
			fitColumns:false,
		    pagination:false,//分页
			sortName:'id',
			columns :[[{
				title:'id',
				field:'id',
				width:150,
				halign:'center',
				align:'center',
				hidden:true
			},{
				title:'姓名',
				field:'name',
				width:100,
				halign:'center',
				align:'left'
			},{
				title:'工号',
				field:'employeeid',
				width:60,
				halign:'center',
				align:'left'
			},{
				title:'角色',
				field:'roles',
				width:80,
				halign:'center',
				align:'center'
				
			},{
				title:'职位',
				field:'position',
				width:100,
				halign:'center',
				align:'center'
			},{
				title:'部门',
				field:'department',
				width:100,
				halign:'center',
				align:'center'
			},{
				title:'电话',
				field:'phone',
				width:100,
				halign:'center',
				align:'left'
		    },{
				title:'手机',
				field:'mobile',
				width:100,
				halign:'center',
				align:'left'
		    },{
				title:'电子邮件',
				field:'email',
				width:130,
				halign:'center',
				align:'left'
		    },{
				title:'状态',
				field:'deleted',
				width:80,
				halign:'center',
				align:'left',
				formatter: function(value,row,index){
				if ( value == 0 ||value == 2){
					return "在职";
				}else if(value==-1){
                    return "离职";
				}else{
					return "";
				}
			}},{
				title:'性别',
				field:'sex',
				width:60,
				halign:'center',
				align:'center',
				formatter: function(value,row,index){
				if ( value == 0 ){
					return "女";
				}else if(value==1){
                    return "男";
				}else{
					return "";
				}
			}},{
				title:'婚否',
				field:'married',
				width:60,
				halign:'center',
				align:'center',
				formatter: function(value,row,index){
				if ( value == 0 ){
					return "未婚";
				}else if(value==1){
                    return "已婚";
				}else{
					return "";
				}
			}},{
				title:'学历',
				field:'xueliName',
				width:60,
				halign:'center',
				align:'center'
		    },{
				title:'毕业学校',
				field:'school',
				width:155,
				halign:'center',
				align:'center'
		    },{
				title:'籍贯',
				field:'jguan',
				width:80,
				halign:'center',
				align:'center'
		    },{
				title:'名族',
				field:'national',
				width:60,
				halign:'center',
				align:'center'
		    },{
				title:'职称',
				field:'zhicName',
				width:70,
				halign:'center',
				align:'left'
		    },{
				title:'身份证号',
				field:'sfz',
				width:140,
				halign:'center',
				align:'center'
		    },{
				title:'出生年月',
				field:'bod',
				width:100,
				halign:'center',
				align:'center'
		    },{
				title:'地址',
				field:'address',
				width:150,
				halign:'center',
				align:'center'
		    },{
				title:'入职时间',
				field:'intime',
				width:100,
				halign:'center',
				align:'center'
		    },{
				title:'描述',
				field:'description',
				width:100,
				halign:'center',
				align:'center'
		    }
			]],
			onSelect:function(rowIndex, rowData){
			    $('#addEmployeeButton').linkbutton('enable');
			    $('#editEmployeeButton').linkbutton('enable');
			    $('#deleteEmployeeButton').linkbutton('enable');
                 $('#deleteEmployeeRealButton').linkbutton('enable');
                 $('#departmentButton').linkbutton('enable');
                 $('#positionButton').linkbutton('enable');
                 $('#zhicButton').linkbutton('enable');
                 $('#roleButton').linkbutton('enable');
			},
			onLoadSuccess:function(data){
				$('#addEmployeeButton').linkbutton('enable');
			    $('#editEmployeeButton').linkbutton('disable');
			    $('#deleteEmployeeButton').linkbutton('disable');
			    $('#deleteEmployeeRealButton').linkbutton('disable');
			    $('#departmentButton').linkbutton('enable');
			    $('#positionButton').linkbutton('enable');
			    $('#zhicButton').linkbutton('enable');
			    $('#roleButton').linkbutton('disable');
			    var selectid = $('#eid').val();
			        if(selectid != ""){
			          for(var i = 0 ; i< data.rows.length;i++){
			             if(selectid == data.rows[i].id){
			            	$('#employeeTable').datagrid('selectRow',i);
			          }
			       }
			     }
			},
			toolbar:'#toolbar'
			
	   	});
        
    }
	
	//添加按钮事件
    function onAddButton(){
    	showEmployeeAddEditDialog('afterAddDialog','add','添加员工信息');
    }
    
	//	添加Dialog后事件
    function afterAddDialog(){
       parent.showMessager(1,'添加成功',true,5000);
       $('#employeeTable').datagrid('reload');
    }
    //wan 编辑按钮事件
    function onEditButton(){
         var row= $('#employeeTable').datagrid('getSelected');
        if(row != null ){
        	showEmployeeAddEditDialog('afterEditDialog','edit','编辑员工信息');
        }else{
           $.messager.alert('提示','请选择编辑的客户!');
        }
    }
   //编辑后事件
     function afterEditDialog(){
    	 parent.showMessager(1,'编辑成功',true,5000);
		 $('#employeeTable').datagrid('reload');
     }
  
   
   //删除按钮事件(数据库还保存)
       function onDeleteButton(){
     	  var row= $('#employeeTable').datagrid('getSelected');
     	 if(null != row ){
          	$.messager.confirm('确认对话框', '确认删除此员工？', function(r){
 				if (r){
 					delEmployee(row.id);
 					
 				}
 			});
         }else{
           $.messager.alert('提示','请选择要删除的员工!');
         }
      }
     //根据Id删除相应员工(数据库还保存)
       function delEmployee(id){
   	   	$.ajax({
   			url:sybp()+'/employeeAction_delEmployee.action',
   			type:'post',
   			data:{id:id},
   			dataType:'json',
   			success:function(r){
   				if(r && r.success){
   					 parent.showMessager(1,'员工删除成功',true,5000);
   					 $('#employeeTable').datagrid('reload');
   				}else{
   					 parent.showMessager(2,'员工删除失败',true,5000);
   				}
   			}
   		});
       } 
       //删除按钮事件(数据库不保存) 
	 function onDeleteRealButton(){
		 var row= $('#employeeTable').datagrid('getSelected');
     	 if(null != row ){
          	$.messager.confirm('确认对话框', '确认删除此员工？', function(r){
 				if (r){
 					delRealEmployee(row.id);
 					
 				}
 			});
         }else{
           $.messager.alert('提示','请选择要删除的员工!');
         }
    }
	 //根据Id删除相应员工(数据库不保存)
     function delRealEmployee(id){
 	   	$.ajax({
 			url:sybp()+'/employeeAction_delRealEmployee.action',
 			type:'post',
 			data:{id:id},
 			dataType:'json',
 			success:function(r){
 				if(r && r.success){
 					 parent.showMessager(1,'员工删除成功',true,5000);
 					 $('#employeeTable').datagrid('reload');
 				}else{
 					 parent.showMessager(2,'员工删除失败',true,5000);
 				}
 			}
 		});
     }
     //部门设置按钮事件 
    function onDepartmentButton(){
    	showDepartmentOperateDialog('','','部门设置');
    }
    //职位设置按钮事件
    function onPositionButton(){
    	showPositionOperateDialog('','','职位设置');
    }
    //职称设置按钮事件
    function onZhicButton(){
    	showTitleOperateDialog('','','职称设置');
    }
    
    //角色
    function onRoleButton(){
    	showRoleDialog('afterRoleDialog','选择角色');
         var row= $('#roleTable').datagrid('getSelected');
      
    }
   //角色后事件
     function afterRoleDialog(){
    	 parent.showMessager(1,'赋予角色',true,5000);
    	 $('#roleTable').datagrid('reload');
     }
</script>
</head>
<body>
<!-- 员工id -->
<s:hidden id="eid" name="eid"></s:hidden>
<!-- 部门id -->
<s:hidden id="did" name="did"></s:hidden>
<!-- 职位id -->
<s:hidden id="pid" name="pid"></s:hidden>
<!-- 职称id -->
<s:hidden id="tid" name="tid"></s:hidden>
<s:hidden id="onchange" name="onchange"></s:hidden>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
 	<div region="center" title="" style="overflow: hidden;">
 	 	<div  class="easyui-tabs" fit="true" border="false">
			<div title="员工信息" border="false" style="overflow: auto;">
				<table id="employeeTable" ></table>
            </div>
		</div>
        <div id="toolbar">
        <!-- <a id="addCustomerButton" class="easyui-linkbutton" onclick="onAddButton();" data-options="iconCls:'icon-add',plain:true">添加</a> -->
			<a id="addEmployeeButton" class="easyui-linkbutton" onclick="onAddButton();"  data-options="iconCls:'icon-groupadd',plain:true,disabled:true">添加</a>
			<a id="editEmployeeButton" class="easyui-linkbutton" onclick="onEditButton();" data-options="iconCls:'icon-groupedit',plain:true,disabled:true">编辑</a>
			<a id="deleteEmployeeButton" class="easyui-linkbutton" onclick="onDeleteButton()" data-options="iconCls:'icon-groupdelete',plain:true,disabled:true">删除</a>
		 	<a id="deleteEmployeeRealButton" class="easyui-linkbutton" onclick="onDeleteRealButton();" data-options="iconCls:'icon-remove',plain:true,disabled:true">真删</a>
			<a id="departmentButton" class="easyui-linkbutton" onclick="onDepartmentButton();" data-options="iconCls:'icon-link',plain:true,disabled:true">部门设置</a>
			<a id="positionButton" class="easyui-linkbutton" onclick="onPositionButton();" data-options="iconCls:'icon-server',plain:true,disabled:true">职位设置</a>
			<a id="zhicButton" class="easyui-linkbutton" onclick="onZhicButton();" data-options="iconCls:'icon-drive',plain:true,disabled:true">职称设置</a>
			<a id="roleButton" class="easyui-linkbutton" onclick="onRoleButton();" data-options="iconCls:'icon-drive',plain:true,disabled:true">角色设置</a>
		</div>
		<span style="position:absolute; top:35px;left:700px">
			    		<input id="searchEmployee" ></input> 
			    		</span> 
 	</div>
    <%@include file="/WEB-INF/jsp/employeeAction/employeeAddEdit.jspf" %>
    <%@include file="/WEB-INF/jsp/employeeAction/departmentOperate.jspf" %>
    <%@include file="/WEB-INF/jsp/employeeAction/departmentAddEdit.jspf" %>
    <%@include file="/WEB-INF/jsp/employeeAction/positionOperate.jspf" %>
    <%@include file="/WEB-INF/jsp/employeeAction/positionAddEdit.jspf" %>
    <%@include file="/WEB-INF/jsp/employeeAction/titleOperate.jspf" %>
    <%@include file="/WEB-INF/jsp/employeeAction/titleAddEdit.jspf" %>
    <%@include file="/WEB-INF/jsp/employeeAction/employeeRole.jspf" %>
</div>
</body>
</html>