<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>合同信息管理</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/tblIntegratedInformAction/tblIntegratedInformAction.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblContractAction/tblTestItem.js" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/tblTestItem.css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/tblContractAction/contractAddEdit.js"></script>
<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 30
	var tableWidth;
	$(function(){
	    var startDate = $('#startTime').val();
		var endDate = $('#endTime').val();
		var tiCode = $('#tiCode').val();
		tableHeight = document.body.clientHeight - 60;
		tableWidth  = document.body.clientWidth;
        var  selectContractCode=  $('#selecttiName').val();   
	     $('#searchtiName').searchbox({ 
		     height:19,
		     width:450,
			 searcher:function(value,name){ 
				 var startTime =  $('#mindatebox').datebox('getValue');
				 var endTime = $('#maxdatebox').datebox('getValue');
				 $('#startTime').val(startTime);
				 $('#endTime').val(endTime);
				 var code = $('#studyType_testItemAndNo').combobox('getValue');
		         $('#selecttiName').val(value);
		         showtestItemTable(startTime,endTime,code,value);
		         parent.window.frames["left"].frames["left2"].updateStartTimeAndEndTimeAndTiCode(startTime,endTime,code);
			}, 
			prompt:'模糊查询,请输入供试品编号、供试品名称、合同编号' 
		}); 
		$('#studyType_testItemAndNo').combobox({    
		   onChange:function(newValue, oldValue){
		    var startTime =  $('#mindatebox').datebox('getValue');
		    var endTime = $('#maxdatebox').datebox('getValue');
		    $('#startTime').val(startTime);
		    $('#endTime').val(endTime);
		    if(newValue == '-1'){
			    newValue='';
		    }
		   parent.window.frames["left"].frames["left2"].updateStartTimeAndEndTimeAndTiCode(startTime,endTime,newValue);
		   //var  selecttiName=   $('#selecttiName').val();
		  var selecttiName = $('#searchtiName').searchbox('getValue');
		   showtestItemTable(startTime,endTime,newValue,selecttiName);
		   }
		});  
		
		 $('#mindatebox').datebox({    
		    required:true,
		     onSelect: function(date){
			        var startTime =  $('#mindatebox').datebox('getValue');
		            var endTime = $('#maxdatebox').datebox('getValue');
		            $('#startTime').val(startTime);
		            $('#endTime').val(endTime);
		            var tiCode =  $('#studyType_testItemAndNo').combobox('getValue');
		            var selecttiName = $('#searchtiName').searchbox('getValue');
		            parent.window.frames["left"].frames["left2"].updateStartTimeAndEndTimeAndTiCode(startTime,endTime,tiCode);
		            showtestItemTable(startTime,endTime,tiCode,selecttiName);
			  }
		    
		});  
		 $('#maxdatebox').datebox({    
		    required:true,
		     onSelect: function(date){
			        var startTime =  $('#mindatebox').datebox('getValue');
		            var endTime = $('#maxdatebox').datebox('getValue');
		            $('#startTime').val(startTime);
		            $('#endTime').val(endTime);
		            var tiCode =  $('#studyType_testItemAndNo').combobox('getValue');
		            var selecttiName = $('#searchtiName').searchbox('getValue');
		            parent.window.frames["left"].frames["left2"].updateStartTimeAndEndTimeAndTiCode(startTime,endTime,tiCode);
		            showtestItemTable(startTime,endTime,tiCode,selecttiName);
			  }
		    
		});  
		 initTestItemAndNOCombobox();
	     initstartTimeAndEndTimeAndtiCode();
	     showtestItemTable(startDate,endDate,tiCode,"");
	     var pager = $('#testItemTable').datagrid('getPager');    // get the pager of datagrid
		var read = $('#read').val();
	    if(read == "true"){
	         pager.pagination({
		        showPageList:false,
		        showRefresh:false,
		        loading:true,
		        displayMsg:	' 共 &nbsp;&nbsp;{total}&nbsp;&nbsp;条记录',
		        beforePageText:'',
		        afterPageText:'',
		    });
	    }else{
	        pager.pagination({
		        showPageList:false,
		        showRefresh:false,
		        loading:true,
		        displayMsg:	'(&nbsp;&nbsp;仅显示当前用户登记的合同信息&nbsp;&nbsp;)&nbsp;&nbsp;  共&nbsp;&nbsp;{total}&nbsp;&nbsp;条记录',
		        beforePageText:'',
		        afterPageText:'',
		    });
	    }
	    
	    
		//显示整个布局
		$('#layoutMainDiv').css('display','');   
    });//匿名函数结束
    
    function showtestItemTable(startDate,endDate,tiCode,selecttiName){
       $('#testItemTable').datagrid({
			url : sybp()+'/tblIntegratedInformAction_loadtestItemList.action?startDate='
			+startDate+'&endDate='+endDate+'&tiCode='+tiCode+'&tiName='+encodeURIComponent(selecttiName),
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
				width:10,
				halign:'center',
				align:'center',
				hidden:true
			},{
				title:'供试品编号',
				field:'tiNo',
				width:160,
				halign:'center',
				align:'left'
			},{
				title:'供试品名称',
				field:'tiName',
				width:200,
				halign:'center',
				align:'left'
			},{
				title:'合同编号',
				field:'contractCode',
				width:150,
				halign:'center',
				align:'left'
			},{
				title:'类型',
				field:'tiType',
				width:40,
				halign:'center',
				align:'left'
				
			},{
				title:'委托项目数量',
				field:'studyItemCount',
				width:100,
				halign:'center',
				align:'center'
				
			},{
				title:'供试品确认',
				field:'confirmSign',
				width:180,
				halign:'center',
				align:'center',
				
			}]],
			onSelect:function(rowIndex, rowData){
			   $('#selectContractButton').linkbutton('enable');
			},onDblClickCell:function(field,row){
	     	        showTestItemAddEditDialog('','select','查看供试品');
	     	},
			onLoadSuccess:function(data){
			   // $('#editTestItemButton').linkbutton('disable'); 
			   // $('#delTestItemButton').linkbutton('disable');
			   // $('#addStudyItemButton').linkbutton('disable');
			    $('#selectContractButton').linkbutton('disable');
			    // var selectid = $('#selectrowId').val();
			     //if(selectid != ""){
			      // for(var i = 0 ; i< data.rows.length;i++){
			       //   if(selectid == data.rows[i].id){
			       //     	$('#testItemTable').datagrid('selectRow',i);
			      //      	$('#selectrowId').val('');
			     //     }
			     //  }
			     //}
			     if(data){
				    	$('#testItemTable').datagrid('selectRow',0);
				}
			},
			toolbar:'#toolbar',
			
	   	}); //end datagrid
    }
    
    function accessControl(id){
        $.ajax({
			   url:sybp()+'/tblIntegratedInformAction_accessControl.action',
			   type:'post',
			   data:{id:id},
			   dataType:'json',
			   success:function(r){
			   //r.start r.name r.write r.start 
			       if(r && r.success){
			        if(r.start == 2){
						document.getElementById('tblTestItemtiNo').disabled = true;
					}else{
						document.getElementById('tblTestItemtiNo').disabled = false;
					}
			       
			       
			         if(r.start == 0 ){
			            if(r.name){
			                $('#editTestItemButton').linkbutton('enable'); 
			                $('#addStudyItemButton').linkbutton('enable');
			            }else{
			               if(r.write){
			                  $('#editTestItemButton').linkbutton('enable'); 
			                  $('#addStudyItemButton').linkbutton('enable');
			               }else{
			                  $('#editTestItemButton').linkbutton('disable'); 
			                  $('#addStudyItemButton').linkbutton('disable');
			               }
			            }
			         }else if(r.start == 1 ){
			            $('#editTestItemButton').linkbutton('disable'); 
			            $('#addStudyItemButton').linkbutton('disable');
			         }else if(r.start == 2){
			             if(r.name){
			                $('#editTestItemButton').linkbutton('enable'); 
			                $('#addStudyItemButton').linkbutton('enable');
			            }else{
			               if(r.write){
			                  $('#editTestItemButton').linkbutton('enable'); 
			                  $('#addStudyItemButton').linkbutton('enable');
			               }else{
			                  $('#editTestItemButton').linkbutton('disable'); 
			                  $('#addStudyItemButton').linkbutton('disable');
			               }
			            }
			         }else if(r.start == 3 || r.start == -1 ){
			           $('#editTestItemButton').linkbutton('disable'); 
			           $('#delTestItemButton').linkbutton('disable');
			           $('#addStudyItemButton').linkbutton('disable');
			           $('#selectContractButton').linkbutton('disable');
			         }
			       }
			   }
			   });
    }
    
    
      //初始化 供试品类型 开始日期 结束日期
    function  initstartTimeAndEndTimeAndtiCode(){
        var startDate = $('#startTime').val();
		var endDate = $('#endTime').val();
		var tiCode = $('#tiCode').val();
		 if(tiCode && tiCode != ""){
	       $('#studyType_testItemAndNo').combobox('select', tiCode);
		 }else{
		    $('#studyType_testItemAndNo').combobox('select', "");
		 }
		$('#mindatebox').datebox('setValue', startDate);
		$('#maxdatebox').datebox('setValue', endDate);
    }
    
     //删除供试品
    function onDelTestItemButton(){
       var row = $('#testItemTable').datagrid('getSelected');
       if(null != row ){
         $.messager.confirm('确认对话框', '确认删除此供试品，及此供试品下的所有委托项目？', function(r){
									if (r){
									    delTestItemlAndStudyItem(row.id);
									    $('#testItemTable').datagrid('reload');
									}
							});
       }else{
          $.messager.alert('提示','请选择删除的供试品!');
       }
     
    }
    
     function  onEditTestItemButton(){
      showTestItemAddEditDialog('afterTestItemEditDialog','edit','编辑供试品');
    }

      function  onEditFullTestItem(){
        var row = $('#testItemTable').datagrid('getSelected');
		if(row!=null){
			 testItemtFullEditData(row.id);
			 $('#tblTestItemcontractCode').val(row.contractCode);
		}else{
			$.messager.alert('提示','请选择编辑的供试品');
		}
    }
    
    //执行编辑操作
		function afterTestItemEditDialog(){
			parent.showMessager(1,'编辑成功',true,5000);
			$('#testItemAddEditDialog').dialog('close'); 
			 var row = $('#testItemTable').datagrid('getSelected');
			$('#selectrowId').val(row.id);
			$('#testItemTable').datagrid('reload');
		}
		
	//添加委托项目   onclick
    function onAddStudyItemButton(){
        //清空数据
    	studyItemFullEditData();
    	 var row = $('#testItemTable').datagrid('getSelected');
    	  if(null != row){
         $('#studyItem_tiNo').val(row.tiNo);
         $('#studyItem_studyNo').val(row.tiNo+"-");
         $('#tblstudyItemcontractCode').val(row.contractCode);
    	 showStudyItemAddEditDialog('afterOnAddStudyItemButton','add','添加委托项目');
    	}else{
          $.messager.alert('提示','请选择所需要添加委托项目的供试品!');
       }
    }
    function afterOnAddStudyItemButton(){
		parent.showMessager(1,'添加成功',true,5000);
		 var row = $('#testItemTable').datagrid('getSelected');
		 //$('#selectrowId').val(row.id)
		 //$('#testItemTable').datagrid('reload');
		  parent.window.frames["left"].frames["left1"].updateCodeCombobox(row.contractCode);
	}
    /**查看合同*/
	function onViewContract(){
		 var row= $('#testItemTable').datagrid('getSelected');
		 if(row != null ){
			  parent.window.frames["left"].frames["left1"].updateCodeCombobox(row.contractCode);
         }else{
          $.messager.alert('提示','请选择项目!');
       }
	}
</script>
</head>
<body>
<s:hidden id="selectrowId" ></s:hidden>
<!-- 开始时间 -->
<s:hidden id="startTime" name="startTime"></s:hidden>
<!-- 结束时间 -->
<s:hidden id="endTime" name="endTime" ></s:hidden>
<!-- 供试品类型 -->
<s:hidden id="tiCode" name="tiCode" ></s:hidden>
<!-- 模糊查询内容 -->
<s:hidden id="selecttiName"  ></s:hidden>
<!-- 是否有查看全部的权限 -->
<s:hidden id="read"  name="read"></s:hidden>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
   <!--  
    <div region="north" title="" style="height:30px;">
  		<div style=" padding-left:5px;margin-top: 1px;">
	  		<a class="easyui-linkbutton" onclick="onAddButton();"
	       				 data-options="iconCls:'icon-add',plain:true" >添加客户</a>  
  		</div>
  	</div>-->
 	<div region="center" title="" style="overflow: hidden;">
 	 	<div  class="easyui-tabs" fit="true" border="false">
			<div title="供试品信息查询" border="false" style="overflow: auto;">
			    <div style="height:30px;">
			       <div style="float:left; width:100%;padding-top:5px;height:22px;">
			    		&nbsp;&nbsp;供试品类型
			    		<input id="studyType_testItemAndNo" class="easyui-combobox" style="height:19px;width:80px;"
			    		data-options="panelHeight:'auto'"/>
			    	    &nbsp;&nbsp;&nbsp;&nbsp; 合同日期范围
		                &nbsp;
			        	&nbsp;从&nbsp;&nbsp;<input id="mindatebox" type="text" class="easyui-datebox" style="width:100px;height:19px;" editable="false"></input>	
			      	  	&nbsp;至&nbsp;&nbsp;<input id="maxdatebox" type="text" class="easyui-datebox" style="width:100px;height:19px;" editable="false"></input>   	
			    		<!-- 搜索框 -->
			    		&nbsp;&nbsp;&nbsp;&nbsp;
			    		<span style="position:absolute; top:35px;">
			    		<input id="searchtiName" ></input> 
			    		</span>
			    	</div>
			    
			    </div>
				<table id="testItemTable" ></table>
            </div>
		</div>
        <div id="toolbar">
        <!-- 
           <a id="editTestItemButton" class="easyui-linkbutton" plain="true" 
								onclick="onEditTestItemButton()" 
								data-options="iconCls:'icon-tagblueedit',disabled:true">编辑供试品</a>
								
			<a id="delTestItemButton" class="easyui-linkbutton" plain="true" 
								onclick="onDelTestItemButton()" 
								data-options="iconCls:'icon-tagbluedelete',disabled:true">删除供试品</a>
								
			<a id="addStudyItemButton" class="easyui-linkbutton" plain="true" 
								onclick="onAddStudyItemButton();" 
								data-options="iconCls:'icon-databaseadd',disabled:true">添加委托项目</a>
         -->
			<a id="selectContractButton" class="easyui-linkbutton" onclick="onViewContract();" data-options="iconCls:'icon-tablemultiple',plain:true,disabled:true">查看合同</a>		
		</div>
 	</div>
</div>
</body>
</html>
