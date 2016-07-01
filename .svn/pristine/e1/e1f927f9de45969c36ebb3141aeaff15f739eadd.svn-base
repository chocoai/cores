<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>委托项目</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf" %>

<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblCustomerAction/selectCustomer.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblCustomerAction/customerAddEdit.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/customerAddEdit.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/contractAddEdit.css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/tblContractAction/studyTypeSelect.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/tblIntegratedInformAction/tblIntegratedInformAction.js" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/contractAddEdit.css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/tblContractAction/contractAddEdit.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/tblContractAction/studyItemAddEdit.js"></script>
<script type="text/javascript">
var tableHeight;//当前页面可见区域高度 - 30
var tableWidth;
$(function(){
    var startDate = $('#startTime').val();
	var endDate = $('#endTime').val();
	var tiCode = $('#tiCode').val();
	tableHeight = document.body.clientHeight - 60;
	tableWidth  = document.body.clientWidth;
    var  selectStudyName=  $('#selectStudyName').val();
   
   $('#searchContract').searchbox({ 
    height:19,
    width:450,
	searcher:function(value,name){ 
	 var startTime =  $('#mindatebox').datebox('getValue');
	 var endTime = $('#maxdatebox').datebox('getValue');
	 $('#startTime').val(startTime);
	 $('#endTime').val(endTime);
	 var code = $('#studyType_testItemAndNo').combobox('getValue');
     $('#selectStudyName').val(value);
     showStudyItemTable(startTime,endTime,code,value);
     parent.window.frames["left"].frames["left2"].updateStartTimeAndEndTimeAndTiCode(startTime,endTime,code);
	}, 
	prompt:'模糊查询,请输入项目编号、名称、合同编号、供试品编号、SD' 
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
	   var selectStudyName = $('#searchContract').searchbox('getValue');
	   showStudyItemTable(startTime,endTime,newValue,selectStudyName);
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
	            var selectStudyName = $('#searchContract').searchbox('getValue');
	            parent.window.frames["left"].frames["left2"].updateStartTimeAndEndTimeAndTiCode(startTime,endTime,tiCode);
	            showStudyItemTable(startTime,endTime,tiCode,selectStudyName);
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
	            var selectStudyName = $('#searchContract').searchbox('getValue');
	            parent.window.frames["left"].frames["left2"].updateStartTimeAndEndTimeAndTiCode(startTime,endTime,tiCode);
	            showStudyItemTable(startTime,endTime,tiCode,selectStudyName);
		  }
	    
	});  
    
	
	initTestItemAndNOCombobox();
   	initstartTimeAndEndTimeAndtiCode();
    showStudyItemTable(startDate,endDate,tiCode,selectStudyName);
    var pager = $('#tblStudyItemTable').datagrid('getPager');    // get the pager of datagrid
	var read = $('#read').val();
	    if(read == "true" ){
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
		//初始化datagrid
		function  showStudyItemTable(startDate,endDate,tiCode,selectStudyName){
        	$('#tblStudyItemTable').datagrid({
			url : sybp()+'/tblIntegratedInformAction_loadStudyItemList.action?startDate='+startDate+'&endDate='+endDate+'&tiCode='+tiCode+'&studyName='+encodeURIComponent(selectStudyName),
			title:'',
			height:tableHeight,
			width:tableWidth,
			nowarp:  false,//单元格里自动换行
			singleSelect:false,//可多选
			fitColumns:false,
			pagination:true,//分页
			sortName:'id',
			columns :[[{
				title:'id',
				field:'id',
				width:150,
				halign:'center',
				align:'center',
				hidden:true
			},{
				title:'项目编号',
				field:'studyNo',
				width:100,
				halign:'center',
				align:'left'
			},{
				title:'项目名称',
				field:'studyName',
				width:200,
				halign:'center',
				align:'left'
			},{
				title:'合同编号',
				field:'contractCode',
				width:90,
				halign:'center',
				align:'left'
			},{
				title:'供试品编号',
				field:'tiNo',
				width:100,
				halign:'center',
				align:'left'
			},{
				title:'项目类型编码',
				field:'studyTypeCode',
				width:100,
				hidden:true,
				halign:'center',
				align:'left'
				
			},{
				title:'是否GLP',
				field:'glpFlag',
				width:70,
				halign:'center',
				align:'center',
				formatter: function(value,row,index){
				if ( value == 0 ){
					return "否";
				}else{
					return "GLP";
				}
			}},{
				title:'备注',
				field:'remark',
				width:100,
				halign:'center',
				align:'left'
		    },{
				title:'SDCode',
				field:'sdCode',
				width:90,
				hidden:true,
				halign:'center',
				align:'left'
		    },{
				title:'SD',
				field:'sd',
				width:100,
				halign:'center',
				align:'center'
		    },{
		    	title:'项目状态',
				field:'studyState',
				width:100,
				halign:'center',
				align:'cente',
				formatter: function(value,row,index){
				if ( value == 0 ){
					return "项目未启动";
				}else if(value==1){
					return "项目审批中";
				}else if(value==2){
					return "方案已确认";
				}else if(value==3){
					return "试验进行中";
				}else if(value==4){
					return "试验完成";
				}else if(value==5){
					return "试验报告完成";
				}else if(value==6){
					return "试验报告归档";
				}else{
					return "课题终止";
				}
			}},
			{
				field:'finishDateStr',
				title:'要求完成日期',
				width:80,
				halign:'center',
				align:'left',
			},
			{
						title:'进度',
						field:'progress',
						width:200,
						halign:'center',
						align:'left',
						formatter:formatProgress

		    }
			]],
			onSelect:function(rowIndex, rowData){
			   var falg = $('#addContract').val();
               if(falg == 0 ){
                   $('#printCommissionButton').linkbutton('disable');
               }else{
				    $('#printCommissionButton').linkbutton('disable');
				    if(rowData && rowData.sd){
					    $('#printCommissionButton').linkbutton('enable');
					}
               }
                $('#selectContractButton').linkbutton('enable');
			},
			onDblClickCell: function(field,value){
				showStudyItemAddEditDialog('','select1','查看项目信息');	
			},
			onLoadSuccess:function(data){
			   $('#selectContractButton').linkbutton('disable');
			   var falg = $('#addContract').val();
               if(falg == 0 ){
                   $('#printCommissionButton').linkbutton('disable');
               }else{
                   if(data){
					    	$('#tblStudyItemTable').datagrid('selectRow',0);
					}
               }
				    
			},
			
			toolbar:'#toolbar',
			
	   	}); //end datagrid
    }
    
    //加载进度条
    function formatProgress(value){
	    	if (value){
	    	   var strs= new Array(); //定义一数组 
				strs=value.split("#"); //字符分割 
				var show;
				if(strs[1] && strs[2]){
				   show = strs[1]+''+ strs[2];
				}else{
				   show ='&nbsp;';
				}
	    	   
		    	var s ;
				if(strs[3] != '' && strs[3] && dateCompare(strs[3],strs[2])){
				    s = '<div style="width:100%;border:1px solid #ccc;text-align:center;">' +
		    			'<div style="width:' + strs[0] + '%;background:#EEB422;color:#FF0000";>' +show + '</div>'
		    			'</div>';
				}else{
				    s = '<div style="width:100%;border:1px solid #ccc;text-align:center;">' +
		    			'<div style="width:' + strs[0] + '%;background:#EEB422;color:#000";>' +show + '</div>'
		    			'</div>';
				}
		    	return s;
	    	} else {
		    	return '';
	    	}
		}
	
	//比较时间大小
	function dateCompare(startdate,enddate){   
	     var arr=startdate.split("-");    
	     var starttime=new Date(arr[0],arr[1],arr[2]);    
	     var starttimes=starttime.getTime();   
	     var arrs=enddate.split("-");    
	     var lktime=new Date(arrs[0],arrs[1],arrs[2]);    
	     var lktimes=lktime.getTime();   
	     if(starttimes>=lktimes){   
	       return false;   
	     }else{
	       return true;
	     }   
	 }
	
	function accessControlAndStudy(contractCode,studyid){
        $.ajax({
			   url:sybp()+'/tblIntegratedInformAction_accessControlAndStudyItem.action',
			   type:'post',
			   data:{contractCode:contractCode,studyid:studyid},
			   dataType:'json',
			   success:function(r){
			       if(r && r.success){
			         if(r.sd != null && r.start != 2 ){
			            $('#deleteCustomerButton').linkbutton('disable');
			         }else{
			            $('#deleteCustomerButton').linkbutton('enable');
			           
			         }
			         if(r.start == 2){
				  			document.getElementById('studyItem_studyNo').disabled = true;
				  		}else{
				  			document.getElementById('studyItem_studyNo').disabled = false;
				  		} 
			         if(r.start == 0 ){
			            if(r.name){
			                $('#editCustomerButton').linkbutton('enable');
			             }else{
			             if(r.write){
			                 $('#editCustomerButton').linkbutton('enable');
			              }else{
			                $('#editCustomerButton').linkbutton('disable');
			              }
			            }
			         }else if(r.start == 1 ){
			             $('#editCustomerButton').linkbutton('disable');
			         }else if(r.start == 2){
			               if(r.write){
			                  $('#editCustomerButton').linkbutton('enable');
			               }else{
			                  $('#editCustomerButton').linkbutton('disable')
			               }
			         }else if(r.start == 3 || r.start == -1 ){
			           $('#editCustomerButton').linkbutton('disable');
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
   
	
	//添加按钮事件

    //wan 编辑
    function onEditButton(){
         var row= $('#tblStudyItemTable').datagrid('getSelected');
        if(row != null ){
        	showStudyItemAddEditDialog('afterEditDialog','edit1','编辑项目信息');
        }else{
           $.messager.alert('提示','请选择编辑的项目!');
        }
    }

     function afterEditDialog(){
          var row=$('#tblStudyItemTable').datagrid('getSelected');
          parent.showMessager(1,'编辑成功',true,5000);
          $('#tblStudyItemTable').datagrid('reload');
     }

    function onDeleteButton(){
    	  var row= $('#tblStudyItemTable').datagrid('getSelected');
          if(row != null ){
        	  qm_showQianmingDialog('afterDelFunction');
          }else{
             $.messager.alert('提示','请选择删除的项目!');
          }
    	
        
   }
       function afterDelFunction(){
    	     var row= $('#tblStudyItemTable').datagrid('getSelected');
	    	$.ajax({
				url:sybp()+'/tblStudyItemAction_delStudyItem.action?id='+row.id,
				type:'post',
				dataType:'json',
				success:function(r){
	    		  if(r && r.success){
	    			  parent.showMessager(1,'删除成功',true,5000);
	    		       var data = $('#tblStudyItemTable').datagrid('getRows');
	    		       	  for(var i = 0;i<data.length;i++){
						    if(data[i].id == row.id){
						        $('#tblStudyItemTable').datagrid('deleteRow',i);
						     }
						  }
		           }
	    	    }  
		    });
       }

	/**查看合同*/
	function onViewContract(){
		 var row= $('#tblStudyItemTable').datagrid('getSelected');
		 if(row != null ){
			  parent.window.frames["left"].frames["left1"].updateCodeCombobox(row.contractCode);
         }else{
          $.messager.alert('提示','请选择项目!');
       }
	}
	/**打印任命书*/
	function printer(){
		var rows = $('#tblStudyItemTable').datagrid('getSelections');
	     var ary = new Array();
	      for(var j=0;j<rows.length;j++){
		   var getid =  $('#tblStudyItemTable').datagrid('getSelections')[j].studyNo;
		   ary = ary.concat(getid);
	     }
	     var getSelections = ary.join(",");
	  
	//	if(rowData && rowData.sd && rowData.studyNo){
		$.ajax({
        		  	url : sybp()+'/tblIntegratedInformAction_printNumber.action',
        		  	type: 'post',
        		  	contentType: "application/x-www-form-urlencoded; charset=utf-8", 
        		  	data: {
        		 // 	studyNo:rowData.studyNo,//单个的
        		  		studyNo:getSelections,
        		  	},
        		  	dataType:'json',
        		  	success:function(r){
        		  	        if(r && r.success){
        		  	        	//	parent.showMessager(2,'数据加载中...',true,5000);
								//  window.location.href = sybp()+'/tblIntegratedInformAction_ireport.action?studyNo='+rowData.studyNo;
								  if(getSelections){
									  	window.location.href = sybp()+'/tblIntegratedInformAction_ireport.action?studyNo='+getSelections;
									}
			        		 }else{
	        		  	      /*$.messager.confirm('确认对话框', '专题编号：'+rowData.studyNo +' 已打印，确定继续吗？', function(r){
									if (r){
										parent.showMessager(2,'数据加载中...',true,5000);
										window.location.href = sybp()+'/tblIntegratedInformAction_ireport.action?studyNo='+rowData.studyNo;
									}*/
								$.messager.confirm('确认对话框', '专题编号：'+r.studyNolist +' 已打印，确定继续吗？', function(r){
									if (r){
										window.location.href = sybp()+'/tblIntegratedInformAction_ireport.action?studyNo='+getSelections;
									}
								});
	        		  	    }
        		  			
        		  	}
        }); 
		
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
<!-- 模糊查询客户名 -->
<s:hidden id="selectStudyName"  ></s:hidden>
<!-- 是否有查看全部的权限 -->
<s:hidden id="read"  name="read"></s:hidden>
<!-- 是否只读 -->
<s:hidden id="addContract" name="addContract"></s:hidden>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
 	 	<div region="center" title="" style="overflow: hidden;">
 	 	<div  class="easyui-tabs" fit="true" border="false">
			<div title="委托项目信息" border="false" style="overflow: auto;">
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
			    		<input id="searchContract" ></input> 
			    		</span>
			    	</div>
			    
			    </div>
				<table id="tblStudyItemTable" ></table>
            </div>
		</div>
        <div id="toolbar">
        <!-- <a id="addCustomerButton" class="easyui-linkbutton" onclick="onAddButton();" data-options="iconCls:'icon-add',plain:true">添加</a> -->
			<!-- 
			<a id="editCustomerButton" class="easyui-linkbutton" onclick="onEditButton();" data-options="iconCls:'icon-databaseedit',plain:true,disabled:true">编辑</a>
			<a id="deleteCustomerButton" class="easyui-linkbutton" onclick="onDeleteButton()" data-options="iconCls:'icon-databasedelete',plain:true,disabled:true">删除</a>
			 -->
		    <a id="selectContractButton" class="easyui-linkbutton" onclick="onViewContract();" data-options="iconCls:'icon-tablemultiple',plain:true,disabled:true">查看合同</a>
		    <a id="printCommissionButton" class="easyui-linkbutton" onclick="printer();" data-options="iconCls:'icon-printer',plain:true,disabled:true">打印任命书</a>
		
		</div>
 	</div>
 	<%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
 	 <%@ include file="/WEB-INF/jsp/tblContractAction/contractAddEdit.jspf"%>
 	  <%@ include file="/WEB-INF/jsp/tblContractAction/studyItemAddEdit.jspf"%>
</div>
</body>
</html>
