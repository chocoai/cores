<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script type="text/javascript">
    var ttFieldTable;
    $(function(){
         var ttaskKind = $('#ttaskKind').val();
       
          
         ttFieldTable = $('#ttFieldTable').datagrid({    
            url:'/cores_bsystemset/script/taskKindCanSee.json',  
            singleSelect:false,
            collapsible:false,
            width:150,
            height:260, 
		    columns:[[    
		        {field:'id',title:'id',width:10,checkbox:true},    
		        {field:'value',title:'任务种类',width:100} 
		    ]],
		    onClickRow :function(rowIndex, rowData){
		        var kind = $('#thisTaskKind').val();
		        if(kind ==rowData.id ){
		           $('#ttFieldTable').datagrid('selectRow',(rowData.id - 1) );   
		        }
		    },
		    onLoadSuccess:function(data){
                 var array = ttaskKind.split(",");
                 for (var i=0 ; i< array.length ; i++){   
                  $('#ttFieldTable').datagrid('selectRow', (array[i]-1));  
              }
		    }   
       }); 
         
         
         
    	var thisTaskKind = $('#thisTaskKind').val();
        if(thisTaskKind == 1){
        	$('#taskKind').val('委托管理');
        }else if(thisTaskKind == 2){
        	$('#taskKind').val('动物试验');
        }else if(thisTaskKind == 3){
        	$('#taskKind').val('临床检验');
        }else if(thisTaskKind == 4){
        	$('#taskKind').val('毒性病理');
        }else if(thisTaskKind == 5){
        	$('#taskKind').val('QA管理');
        }else if(thisTaskKind == 6){
        	$('#taskKind').val('供试品管理');
        }else if(thisTaskKind == 7){
        	$('#taskKind').val('分析');
        }else if(thisTaskKind == 8){
        	$('#taskKind').val('生态毒理');
        }
        });
        
       
        
        
        
	function onSaveButton(){
	      var rows = $('#ttFieldTable').datagrid('getSelections' ); 
	        var ary = new Array();
			for(var i = 0 ;i< rows.length;i++){
				var id = rows[i].id;
				ary = ary.concat(id);
		    }
		  var ids = ary.join(","); 
		  $('#ttFields').val(ids);
		if($('#taskTypeeditForm').form('validate')){
			$('#taskTypeeditForm').submit();
		}
	}
	function onExitButton(){
		window.location.href=sybp()+'/tblTaskTypeAction_list.action?taskIds='+$('#taskTypeid').val();
	}
</script>
</head>
<body>
<!-- taskKind -->
<input type="hidden" id="ttaskKind" value="${ttaskKind}"/>
<input type="hidden" id="thisTaskKind" value="${taskKind}"/>
	<div class="easyui-layout" fit="true" border="false" ">
		<div region="north" border="false"  style="height:26px;">
			<div class="page_tab">
		      <ul>
		          <li class="active"><span><span><a href="javascript:void(0);">常规任务-编辑</a></span></span></li>
		      </ul>
			</div>
		</div>
		<!-- table start -->
		<div region="center" border=false style="border: 1px solid #c8c8c8;">
			<!-- 工具栏（保存 返回）start -->
				<div style="height:27px; padding-left:5px; padding-top:1px; border-bottom:1px solid #c9c9c9;">
					<a id="saveButton" class="easyui-linkbutton" plain="true"  
						onclick="onSaveButton();" data-options="iconCls:'icon-save'">保存</a>
					<a id="editButton" class="easyui-linkbutton" plain="true" 
						onclick="onExitButton();" data-options="iconCls:'icon-back'">返回</a>
				</div>
				<!-- 工具栏结束 -->
			<form id="taskTypeeditForm" action="tblTaskTypeAction_edit.action" method="post">
			<table width="400px;" style="margin-top: 20px;">
			   <tr style="display:none;">
					<td width="150px;" height="30px;" align="right">ID</td>
					<td><input  style="width:150px;" readonly="readonly" 
						class="easyui-validatebox" required="true" 
						name="taskTypeid" id ="taskTypeid" value="${id}" type="text"/></td>
				</tr>
					<!-- 	validType="remote[sybp()+'/dictDoseUnitAction_checkOtherAbbr.action?oldAbbr=${specKind}','abbr']"  -->
				<tr>
					<td width="150px;" height="30px;" align="right">任务名称</td>
					<td><input  style="width:150px;"  missingMessage="必填项" invalidMessage="缩写已存在" 
						class="easyui-validatebox" required="true"  value="${taskName}"
						validType="remote[sybp()+'/tblTaskTypeAction_checkOthertaskName.action?id=${id}','taskName']" 
						name="taskName" type="text"/></td>
				</tr>
				<tr>
					<td width="150px;" height="30px;" align="right">任务类型</td>
					<td>
					   <input id="taskKind" style="width:150px;" readonly="readonly"  disabled="disabled" 
						name="taskKind"  type="text"/>
					</td>
				</tr>
				<tr><td width="150px;" height="30px;" align="right">可见范围</td>
				<td>
                       <table id="ttFieldTable" ></table>
                       <input style="display:none;" name="ttField"  id="ttFields" />
				</td></tr>
			</table>
			</form>
		</div>
		<!-- table end -->
	</div>
</body>
</html>