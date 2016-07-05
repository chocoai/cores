<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<style type="text/css">
    .tree-icon{
    	width:0px;
    }
</style>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/exportAction/exportAddEdit.js" charset="utf-8"></script>
<script type="text/javascript">
	
	function addQACheckItemAndQAFileReg()
	{
		var fileRows = $('#QAFileRegs').datagrid("getSelections");
		if(fileRows!=null&&fileRows!='')
		{
				var itemRow = $('#QACheckItems').datagrid("getSelected");
				if(itemRow!=null&&itemRow!='')
				{
					var fileRegIds = new Array();
					for(var i=0;i<fileRows.length;i++)
					{
						fileRegIds.push(fileRows[i].monkeyid);
					}
					//document.getElementById("checkItemAndQAFileTable").innerHTML=fileRegIds;
					var old=document.getElementById("monkeylist");
					var str="";
					for(var i=0;i<fileRegIds.length;i++){
						str=str+(fileRegIds[i]+",");
					}
					//$('#checkItemAndQAFileTable').append(fileRegIds);
					$('#monkeylist').append(str);
				}else
				{
					$.messager.alert("提示","请选择房舍！");
				}
			
		}else
		{
			$.messager.alert("提示","请选择动物！");
		}
		
	}
	function removeQACheckItemAndQAFileReg()
	{
		document.getElementById("monkeylist").innerHTML="";
	}
</script>
<script type="text/javascript">
	$(function(){
		var tableSize ;
		var tableHeight = document.body.clientHeight - 80;   //datagrid 高度
		var tableWidth = document.body.clientWidth;
		
		$('#boss').combobox({    
 			url : sybp()+'/employeeAction_loadListEmployee.action',
 	    	valueField:'id',    
 	    	textField:'text',
 	    	editable:false,
 	    	onLoadSuccess:function(none){},
 	    	onSelect:function(rec){
 	    		if(rec.id=="-1"){
 	    			$('#boss').combobox('select',"");
 	    		}
 	    	}
 		});
 		$('#veterinarian').combobox({    
	 		url : sybp()+'/employeeAction_loadListEmployee.action',
	 	    valueField:'id',    
	 	    textField:'text',
	 	    editable:false,
	 	    onLoadSuccess:function(none){   
	 		},
	 		onSelect:function(rec){
	 			if(rec.id=="-1"){
	 				$('#veterinarian').combobox('select',"");
	 			}
	 		}	 
	 	});
 		var addOrEditName=$('#addOrEdit').val();
 		if(addOrEditName=='add'){
 		 document.getElementById("addOrEditName").innerHTML="添加检疫";
 		}else if(addOrEditName=="edit"){
 			document.getElementById("addOrEditName").innerHTML="编辑检疫";
 		}
		var normalid=$('#normalid').val();
		if(normalid!=''){
			
			//$("#right").attr("disabled", true);
			//$("#left").attr("disabled", true);
			$('#monkeylist').append($('#monkeylist1').val());
			$('#title').val($('#title1').val());
			$('#checkdate').datebox('setValue',$('#checkdate1').val());
			$('#boss').combobox('select',$('#boss1').val());
			$('#veterinarian').combobox('select',$('#veterinarian1').val());
			//$('#protector').combobox('select',$('#getProtector').val());
			
		}
		$('#QACheckItems').datagrid({
			url : sybp()+"/individualAction_loadArea.action",
			title:'',
			iconCls:'',//'icon-save',
			singleSelect:true,//只能选一行
			pagination:false,//下面状态栏
			height:tableHeight,
			fitColumns:false,//不出现横向滚动条
			nowarp:  false,//单元格里自动换行
			border:false,
			idField:'areaId',
			columns :[[
				{title :'',field :'id',hidden:true},
				{title:'房舍名称',field :'areaname',width:100,halign:'left'}
			
			]],
			onSelect:function(rowIndex, rowData){
				document.getElementById("TITLE").innerHTML=" 房间 "+rowData.areaname+" 动物：";
				$('#QAFileRegs').datagrid({
					height:tableHeight,
					url:sybp()+'/individualAction_loadMonkeys.action?roomid='+rowData.id,
				});
				//$('#QAFileRegs').datagrid('reload');
			}
			
		});
		//动物
		
		$('#QAFileRegs').datagrid({
			columns:[[			  	    
						 {
					          field:'monkeyid',
					          title:'动物编号',
					          width:100
					        
					      }

			]],

		});
		
		
 		
	 	$('#protector').combobox({    
	 		url : sybp()+'/employeeAction_loadListEmployee.action',
	 	    valueField:'id',    
	 	    textField:'text',
	 	    editable:false,
	 	    onLoadSuccess:function(none){  
	 		},onSelect:function(rec){if(rec.id=="-1"){$('#protector').combobox('select',"");}}	 
	 	});

	});
	function back(){
		window.location.href="${pageContext.request.contextPath}/exportAction_list.action";
	}
	function onDialogSaveButton(){
		
		var normalid=$('#normalid').val();
		var addOrEdit=$('#addOrEdit').val();
		var monkeylist=$('#monkeylist').val();
		//校验选候编号是否有重复.
		var result=[];
		var hash={};
		var str=monkeylist.split(',');
		for(var i =0,elem;i<str.length;i++){
			elem = str[i];
			if(!hash[elem]){
				hash[elem]=true;
				result.push(elem);
			}
		}
		var resultStr = result.join(",");
		if(str!=resultStr){
			parent.showMessager(3,'存在重复的猴子编号',true,5000);
			return ;
		}
		var title=$('#title').val();
		var boss=$('#boss').combobox('getValue');
		var checkdate=$('#checkdate').datebox('getValue');
		var veterinarian=$('#veterinarian').combobox('getValue');
		if(title==''){
			parent.showMessager(3,'检疫编号不能为空',true,5000);
			return ;
		}
		if(monkeylist==''){
			parent.showMessager(3,'请先选择猴子',true,5000);
			return ;
		}
			$.ajax({
					url:sybp()+'/exportAction_addExport.action?addOrEdit='+addOrEdit+'&monkeylist='+monkeylist+'&title='+title+'&boss='+boss+'&checkdate='+checkdate
					+'&veterinarian='+veterinarian
					+'&normalid='+normalid,
					type:'post',
					dataType:'json',
					success:function(r){
					if(r && r.success){
						//$.messager.alert("操作提示", "保存成功！","info");
						parent.parent.showMessager(1,'保存成功',true,5000);
						window.location.href=sybp()+'/exportAction_list.action?editOrAddId='+r.normalId;
					}else{
						$.messager.alert('提示','请检查录入的数据');		
					}
				 }
			});
		
	}
</script>
</head>
<input type="hidden" id="addOrEdit" value="${addOrEdit }"/>
  <input type="hidden" id="normalid" value="${normalid }"/>
  <input type="hidden" id="monkeylist1" value="${monkeylist}"/>
  <s:hidden id="title1" name="title"/>
  <s:hidden id="checkdate1" name="checkdate"/>
  <s:hidden id="boss1" name="boss"></s:hidden>
 <s:hidden id="veterinarian1" name="veterinarian"></s:hidden>
  <!--<s:hidden id="protector" name="protector"></s:hidden>-->
  <body>
	<div class="easyui-layout" fit="true" border="false">
		<div region="north" border="false"  style="height:26px;">
			<div class="page_tab">
		      <ul>
		          <li class="active"><span><span><a href="javascript:void(0);" id="addOrEditName"></a></span></span></li>
		      </ul>
			</div>
		</div>
		<div region="center" border=false style="border: 1px solid #c8c8c8;">
			<div id="toolbar" style=" position:absolute;top:0px;height=38px;width=800;">
				<a id="addStudyGroup"  class="easyui-linkbutton" onclick="onDialogSaveButton();" data-options="iconCls:'icon-add',plain:true">确定</a>
				<a id="editStudyGroup"  class="easyui-linkbutton" onclick="back();" data-options="iconCls:'icon-edit',plain:true">返回</a>
			</div>
	        <span style=" position:absolute;top:30px;left:10px;width:200px;">房舍列表：</span>	<br/>
			<div style=" position:absolute;top:50px;left:10px;bottom:22px;width:200px; border: 1px solid #c8c8c8; overflow-y: auto;">
				<div id="QACheckItems" ></div>
			</div>
			<span id="TITLE" style=" position:absolute;top:30px;left:220px;width:200px;">猴子列表：</span><br/>
			<div style=" position:absolute;top:50px;left:220px;bottom:22px;width:200px; border: 1px solid #c8c8c8; overflow-y: auto;">
					
				<div id="QAFileRegs" class="easyui-datagrid" style=" width:200px; border: 1px solid #c8c8c8; overflow-y: auto; float:left;"></div>
					
			</div>
			<div style="position:absolute;top:20px;left:420px;bottom:22px;width:50px;">
				<div style="position:absolute;top:30%;left:30%;">
					<button onclick="addQACheckItemAndQAFileReg();" style="width: 40px;" id="right">选猴></button><br/><br/>
					<button onclick="removeQACheckItemAndQAFileReg();" style="width: 40px;" id="left">清除<</button>
				</div>	
			</div>
			<form id="exportAddForm" action="" method="post">
			<span style=" position:absolute;top:30px;left:480px;">选中猴子：</span><br/>
			<div style=" position:absolute;top:50px;bottom:520px;left:480px;width:622px; border: 1px solid #c8c8c8; ">
				<div id="checkItemAndQAFileTable" style=" width:622px; border: 1px solid #c8c8c8; overflow-y: auto; float:left;">
					<textarea id="monkeylist" name="monkeylist" rows="10" cols="75" ></textarea>
				</div>
			</div>
			<div style=" position:absolute;top:290px;bottom:22px;left:480px;width:622px; border: 1px solid #c8c8c8; ">
				<div class="edit_table">
                <table  class="export" style="top:70px;">
                  <tr>
                    <td>检疫编号：</td>
                    <td colspan="3">
                        	 <input id="title" type="text" name="title" 
                        	 class="easyui-validatebox"  data-options="required:true"
                        	 missingMessage="必填项" invalidMessage="检疫编号已被使用" 
                        	 validType="remotett[sybp()+'/normalAction_checkNormalidHave.action','title','#oldtitle']" />
                    
                  </tr>
                  <tr>
                    <td>检疫主管：</td>
                    <td><input id="boss" name="boss" />
                    </td>
                    <td>检疫时间：</td>
                    <td><input id="checkdate" name="checkdate" class="easyui-datebox" editable="false"/>
                    </td>
                  </tr> 
                  <tr>
                  	<td>检疫兽医：</td>
                    <td><input id="veterinarian" name="veterinarian" />
                    </td>
                    <td>保定人员：</td>
                    <td><input id="protector" name="protector" />
                    </td>
                  </tr>
                  </table>
            </div>
			</div>
			</form>
			<a id="exportAdd_event"></a>
		</div>
		
	</div>
  </body>
</html>
