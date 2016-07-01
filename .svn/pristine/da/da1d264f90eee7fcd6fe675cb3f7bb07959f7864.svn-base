<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/commCheck.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/ajaxfileupload.js" charset="utf-8"></script>
<script type="text/javascript">
var MemberlistTable;
var UserMemberTable;

$(function(){   
	MemberlistTable=$('#MemberlistTable').datagrid({
		url : sybp()+"/tblStudyMemberAction_loadList.action?studyNoPara="+$('#studyNoPara').val(),
		title:'',
		//height: 240,
		//width:560,
		iconCls:'',//'icon-save',
		pagination:false,//下面状态栏
		//pageSize:50,
		//pageList:[50,100],
		fit:true,
		fitColumns:false,//不出现横向滚动条
		nowarp:  false,//单元格里自动换行
		border:false,
		idField:'id', //pk
		singleSelect:true,
		//sortName:'aniSerialNum',//排序字段
		//sortOrder:'desc',//排序方法
	    columns :[[{
			title :'',
			field :'id',
			//checkbox:true
			hidden:true
		},{
			title:'专题编号',
			field:'studyNo',
			width:100
		},{
			title:'专题成员',
			field:'name',
			width:80
		},{
			title:'成员部门',
			field:'departmentname',
			width:80
		}]],
		//单击事件
		onClickRow : function(rowIndex, rowData){
		},
		onLoadSuccess:function(data){
			
		}
		
	}); //end datagrid

	initSerialNum();
	
	 
	$('.datagrid-header div').css('textAlign','center');
	MemberReadOnly();
	onChangefunction();
	$('#OtherMainDiv').css('display','');


	
});//匿名函数   end


function onChangefunction(){
	$('#OneCauseofdepartmentSerialNum').combobox({
	    onChange:function(newValue,oldValue){
		 $('#UserMemberTable').datagrid('rejectChanges');
		  var rows =$('#UserMemberTable').datagrid('getRows');
		  var length = rows.length;
		  var j = 0;
		 if( newValue && newValue != -1 ){
	          for(var i=0;i<length;i++){
	        	 var departmentname =  $('#UserMemberTable').datagrid('getRows')[j].departmentname;
	        	 if(departmentname != newValue ){
	        	    $('#UserMemberTable').datagrid('deleteRow',j);
	        	 }else{
	                j++;
	             }
	           }
			 }
	      
	    }
	});
}
function AddMemberButton(){
	initSerialNum();
	initDepartmentname();
	$('#StudyMemberstudyNo').val($('#studyNoPara').val());
	$('#OneCauseofdepartmentSerialNum').combobox('setValues', ['  ']);
	$('#AddStudyMemberDialog').dialog('open');
	$('#AddStudyMemberDialog2').dialog('open');
}

//序号下拉框初始化 
function initDepartmentname(){
	$.ajax({
        type:"POST",
        url:"${pageContext.request.contextPath}/tblStudyMemberAction_DepartmentnameList.action",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType:"json",
        data:"studyNoPara="+$('#studyNoPara').val(),
        async:false,
        success:function(r)
        {
			if(r){
				$('#OneCauseofdepartmentSerialNum').combobox('loadData',r.serialNumList);
			}
        }
   });
}

//序号下拉框初始化 
function initSerialNum(){

	UserMemberTable=$('#UserMemberTable').datagrid({
   	    url : sybp()+"/tblStudyMemberAction_MemberloadList.action?studyNoPara="+$('#studyNoPara').val(),
   	 title:'',
		height: 180,
		width:240,
		iconCls:'',//'icon-save',
		//pagination:true,//下面状态栏
		pageSize:100,
		pageList:[50,100],
		//fit:true,
		fitColumns:true,//不出现横向滚动条
		nowarp:  false,//单元格里自动换行
		//border:false,
		//idField:'id', //pk
		//sortName:'aniSerialNum',//排序字段
		//sortOrder:'desc',//排序方法
		columns :[[{
			title:'',
			field:'id',
			checkbox:true
		},{
			title:'专题成员',
			field:'realName',
			width:60
		},{
			title:'成员部门',
			field:'departmentname',
			width:80
		}]],
		onClickRow :function(rowIndex, rowData){
		}

		}); //end 
	
 }


   function  AddStudyMemberSaveButton(){
	   var rows =$('#UserMemberTable').datagrid('getChecked');
       var rows1 =$('#UserMemberTable').datagrid('getRows');
       if(rows.length <1){
			$.messager.alert('提示','请选择专题成员');
		}else{
        var selecteds ='?';
		for(var i=0;i<rows.length;i++){ 
			selecteds = selecteds +'selecteds ='+$('#UserMemberTable').datagrid('getChecked')[i].id+'&'
		 }
		 var selecteds1 = ' ';
		for(var i=0;i<rows1.length;i++){ 
			selecteds1 = selecteds1 +'selecteds1 ='+$('#UserMemberTable').datagrid('getRows')[i].id+'&'
		 }
		$.ajax({
			url:sybp()+'/tblStudyMemberAction_AddStudyMemberSave.action'+selecteds+selecteds1+'studyNoPara='+$('#studyNoPara').val(),
			type:'post',
			cache:false,
		    dataType:'json',
		    success:function(r){
			   if(r ){
				   window.location.href = "${pageContext.request.contextPath}/tblStudyMemberAction_list.action?studyNoPara="+$('#studyNoPara').val();
				  }
		    	
		    }
		});
	    }
	   }

  function RemoveMembersButton(){
	   var row= $('#MemberlistTable').datagrid('getSelected');
	   if(row == null){
		   $.messager.alert('提示','请选择专题成员');
	    }else{
		    var rowid = row.id;
	    	$.ajax({
				url:sybp()+'/tblStudyMemberAction_removeMembersaction.action',
				type:'post',
				data:"MemberId="+rowid,
				cache:false,
			    dataType:'json',
			    success:function(r){
				   if(r && r.success){
					  var rows = $('#MemberlistTable').datagrid('getSelected');
					  var index = $('#MemberlistTable').datagrid('getRowIndex',rows);//获取某行的行号
					  $('#MemberlistTable').datagrid('deleteRow',index);
					   parent.parent.showMessager(1,'专题成员已删除!',true,3000);
					  }else{
                        parent.parent.showMessager(1,'专题成员未删除!',true,3000);
					  }
			    	
			    }
			});
		}
	  }

  function  MemberReadOnly(){
      
  var	member = $('#left_member').val();
     if(member==''){
    	  $('#toolbar').css('display','');  
    }
 
  }
</script>
</head>
<body >

    <s:hidden id="studyNoPara" name="studyNoPara"></s:hidden>
	 <s:hidden id="rowIndex" name="rowIndex"></s:hidden>
  <s:hidden id="left_member" name="left_member"></s:hidden>
	<div id="toolbar" style="display:none;">
		<a id="AddMemberButton"  class="easyui-linkbutton" onclick="AddMemberButton();" data-options="iconCls:'icon-add',plain:true">选择成员</a>
	    <a id="RemoveMembers" class="easyui-linkbutton" onclick="RemoveMembersButton();" data-options="iconCls:'icon-redo',plain:true">删除成员</a>
	</div>

	
	<div class="easyui-layout" fit="true" border="false" >
		<div region="west" border="false" >
			<table id="MemberlistTable" ></table>
		</div>


		<div region="center" border="false" style="width:300px;">
			<div class="easyui-layout" fit="true" border="false" >
				<div region="center" border="false">
					<table id="" ></table>
				</div>
			</div>
		</div>

	</div>	
	<div id="OtherMainDiv" style="display:none;">
	 <%@include file="/WEB-INF/jsp/TblStudyMember/AddStudyMember.jspf" %>
    </div>
</body>
</html>
