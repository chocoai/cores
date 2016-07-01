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
    	 var taskKind = $('#theTaskKind').val();
    	 
    	 //ttFieldTable
        
		$('#taskKind').combobox( {
			onSelect:function(record){
			  // $('#ttField').combobox('select', record.value);
			  //alert( record.value);
			  $('#ttFieldTable').datagrid('unselectAll');   
			  $('#ttFieldTable').datagrid('selectRow',(record.value-1));     
			}
		});
		
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
		        var kind = $('#taskKind').combobox('getValue');
		        if(kind ==rowData.id ){
		           $('#ttFieldTable').datagrid('selectRow',(rowData.id - 1) );   
		        }
		    },
		    onLoadSuccess:function(data){
		        if(taskKind != ""){
    		   $('#taskKind').combobox('select', taskKind);
			   $('#ttFieldTable').datagrid('selectRow', (taskKind-1));     
                }else{
			     $('#ttFieldTable').datagrid('selectRow', 0);   
              }   
		    }   
       });  
		
		//$('#ttField').combobox( {
		//	onSelect:function(record){
		//	  var taskKind = $('#taskKind').combobox('getValue');
		//	      $('#ttField').combobox('select',taskKind);
		//	},
		//	onUnselect:function(record){
		//	  var taskKind = $('#taskKind').combobox('getValue');
		//	   if(record.value == taskKind){
		//	      $('#ttField').combobox('select',taskKind);
		//	   }
		//	 
		//	}
		//});
		
		
         
         
	});

	function onSaveButton() {
	    var rows = $('#ttFieldTable').datagrid('getSelections' ); 
	        var ary = new Array();
			for(var i = 0 ;i< rows.length;i++){
				var id = rows[i].id;
				ary = ary.concat(id);
		    }
		  var ids = ary.join(","); 
		  $('#ttField').val(ids);
	    
		if ($('#taskTypeAddForm').form('validate')) {
			$('#taskTypeAddForm').submit();
		}
	}
	function onExitButton() {
		window.location.href = sybp() + '/tblTaskTypeAction_list.action?taskIds='+$('#taskIds').val();
	}
</script>
</head>
<body>
<s:hidden id="theTaskKind" name="theTaskKind"></s:hidden>
<s:hidden id="taskIds" name="taskIds"></s:hidden>
	<div class="easyui-layout" fit="true" border="false" ">
		<div region="north" border="false"  style="height:26px;">
			<div class="page_tab">
		      <ul>
		          <li class="active"><span><span><a href="javascript:void(0);">常规任务-添加</a></span></span></li>
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
			<form id="taskTypeAddForm" action="tblTaskTypeAction_add.action" method="post">
			<table width="400px;" style="margin-top: 20px;">
				<tr>
					<td width="150px;" height="30px;" align="right">任务名称</td>
					<td><input  style="width:150px;" missingMessage="必填项" invalidMessage="名称已存在" 
						class="easyui-validatebox" required="true" 
						validType="remote[sybp()+'/tblTaskTypeAction_checktaskName.action','taskName']" 
						name="taskName" type="text"/></td>
				</tr>
				<tr>
				<!-- //任务类别  1:委托管理  2：动物试验3：临床检验 4：毒性病理 5：QA管理 6：供试品管理 -->
						
					<td width="150px;" height="30px;" align="right">任务种类</td>
					<td> <select id="taskKind" class="easyui-combobox" style="width:150px;height:19px;"  editable="false" panelHeight="160" name="taskKind" required="true" >
                        		<option value="1">委托管理</option>
                        		<option value="2">动物试验</option>
                        		<option value="3">临床检验</option>
                        		<option value="4">毒性病理</option>
                        		<option value="5">QA管理</option>
                        		<option value="6">供试品管理</option>
                        		<option value="7">分析</option>
                        		<option value="8">生态毒理</option>
                        </select>
                    </td>
				</tr>
				<tr><td width="150px;" height="30px;" align="right">可见范围</td>
				<td>
				     <!-- 
				     <select id="ttField" class="easyui-combobox" style="width:300px;height:19px;" multiple="true" editable="false" panelHeight="160" name="ttField" required="true" >
                        		<option value="1">委托管理</option>
                        		<option value="2">动物试验</option>
                        		<option value="3">临床检验</option>
                        		<option value="4">毒性病理</option>
                        		<option value="5">QA管理</option>
                        		<option value="6">供试品管理</option>
                        		<option value="7">分析</option>
                        		<option value="8">生态毒理</option>
                     </select> -->
                     <table id="ttFieldTable" ></table>
                     <input style="display:none;" name="ttField"  id="ttField" />
				</td></tr>
			</table>
			</form>
		</div>
		<!-- table end -->
	</div>
</body>
</html>