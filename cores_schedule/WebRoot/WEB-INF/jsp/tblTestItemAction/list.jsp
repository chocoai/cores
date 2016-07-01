<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>合同信息管理</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf" %>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblTestItemAction/tblTestItem.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblTestItemAction/selectCustomer.js" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/tblTestItem.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/table.css" media="screen" />
<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 30
	var tableWidth;
	$(function(){
	    var startDate = $('#startTime').val();
		var endDate = $('#endTime').val();
		tableHeight = document.body.clientHeight - 90;
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
		        var tiCode =  $('#studyType_testItemAndNo').combobox('getValue');
		        var selecttiName = $('#searchtiName').searchbox('getValue');
		        var isConfirm = $('#isConfirm').combobox('getValue');
		       // var startTime = null;
		       // var endTime =null;
               // var tiCode = null;
               // var isConfirm =null;
		     	$('#testItemTable').datagrid('clearSelections');
		        showtestItemTable(startTime,endTime,tiCode,value,isConfirm);
			}, 
			prompt:'模糊查询,请输入供试品编号、供试品名称、合同编号' 
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
		           // var selecttiName ="";
		            var isConfirm = $('#isConfirm').combobox('getValue');

		         	   $('#testItemTable').datagrid('clearSelections');
		            
		            showtestItemTable(startTime,endTime,tiCode,selecttiName,isConfirm);
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
		              //var selecttiName ="";
		              var isConfirm = $('#isConfirm').combobox('getValue');

		           	   $('#testItemTable').datagrid('clearSelections');
		              
		             showtestItemTable(startTime,endTime,tiCode,selecttiName,isConfirm);
			  }
		    
		});  
		
			
		$('#isConfirm').combobox({    
		    onSelect:function(record){
		        var startTime =  $('#mindatebox').datebox('getValue');
		        var endTime = $('#maxdatebox').datebox('getValue');
		        $('#startTime').val(startTime);
		        $('#endTime').val(endTime);
		        var tiCode =  $('#studyType_testItemAndNo').combobox('getValue');
		        var selecttiName = $('#searchtiName').searchbox('getValue');
		        // var selecttiName ="";
		        if(record.text != '全部'){
		     	   $('#testItemTable').datagrid('clearSelections');
		        }
		        showtestItemTable(startTime,endTime,tiCode,selecttiName,record.value);
		    } 
		}); 
		 $('#studyType_testItemAndNo').combobox({
			url:sybp()+'/tblTestItemAction_loadTestItemAndNOList.action',
			valueField:'id',
			textField:'text',
			onSelect: function(record){   
		        if(record.id == -1){
		        	  $('#studyType_testItemAndNo').combobox('clear');
		        	 
		         }
	            var startTime =  $('#mindatebox').datebox('getValue');
		        var endTime = $('#maxdatebox').datebox('getValue');
		        $('#startTime').val(startTime);
		        $('#endTime').val(endTime);
		        var tiCode =  $('#studyType_testItemAndNo').combobox('getValue');
		        var selecttiName = $('#searchtiName').searchbox('getValue');
		       // var selecttiName ="";
		        var isConfirm = $('#isConfirm').combobox('getValue');
		        
		     	   $('#testItemTable').datagrid('clearSelections');
		     	   
		       showtestItemTable(startTime,endTime,tiCode,selecttiName,isConfirm);
			}
		});
	   
	   $('#layoutMainDiv').layout('panel','east').panel({
			onCollapse:function(){
				$('#collapseOrExpand').val('1');
			},
			onExpand:function(){
				$('#collapseOrExpand').val('0');
			}
		});
		
		 $('#selectTestItemAttachmentTable').datagrid({
			title:'',
			height:437,
			width:640,
			nowarp:  false,//单元格里自动换行
			singleSelect:true,
			fitColumns:false,
			pagination:false,//分页
			idField:'id',
			columns :[[{
				title:'id',
				field:'id',
				width:10,
				halign:'center',
				align:'center',
				hidden:true
			},{
				title:'供试品编号',
				field:'testItemCode',
				width:150,
				halign:'center',
				align:'left',
			    hidden:true
			},{
				title:'附件名称',
				field:'attachmentName',
				width:150,
				halign:'center',
				align:'left'
				
			},{
				title:'操作者',
				field:'operator',
				width:60,
				halign:'center',
				align:'left'
				
			},{
				title:'上传时间',
				field:'appendDate',
				width:120,
				halign:'center',
				align:'left'
				
			},{
				title:'备注',
				field:'remark',
				width:160,
				halign:'center',
				align:'left'
				
			},{
				title:'操作',
				field:'operate',
				width:120,
				halign:'center',
				align:'left',
			    formatter: function(value,row,index){
			        var  dengji = $('#dengji').val();
			        if(dengji != "true" ){
			            return "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style='cursor:pointer;'  onclick='downloadTestItemAttachment("+row.id+");'>查看</a> ";
			        }else{
			            return "&nbsp;&nbsp;&nbsp;<a  style='cursor:pointer;' onclick='delectTestItemAttachment("+row.id+");'>删除</a>&nbsp;&nbsp;&nbsp;&nbsp;<a style='cursor:pointer;'  onclick='downloadTestItemAttachment("+row.id+");'>查看</a> ";
			        }
			   }
				
				
			}]],
			toolbar:'#dialogToolbar6',
	   	}); //end datagrid
	   	
	     initstartTimeAndEndTimeAndtiCode();
	     
	     initTestItemTable(startDate,endDate);
	    
		//显示整个布局
		$('#layoutMainDiv').css('display','');   
    });//匿名函数结束
    
    function initTestItemTable(startDate,endDate){
       $('#testItemTable').datagrid({
			url : sybp()+'/tblTestItemAction_loadtestItemList.action?startDate='
			+startDate+'&endDate='+endDate,
			title:'',
			height:tableHeight,
			width:tableWidth-30,
			nowarp:  false,//单元格里自动换行
			singleSelect:true,
			fitColumns:false,
			pagination:false,//分页
			idField:'id',
			columns :[[{
				title:'id',
				field:'id',
				width:10,
				halign:'center',
				align:'center',
				//checkbox:true
				hidden:true
			},{
				title:'类型',
				field:'tiType',
				width:40,
				halign:'center',
				align:'left'
				
			},{
				title:'合同编号',
				field:'contractCode',
				width:130,
				halign:'center',
				align:'left'
			},{
				title:'委托方',
				field:'sponsorName',
				width:120,
				halign:'center',
				align:'left'
				
			},{
				title:'供试品编号',
				field:'tiNo',
				width:140,
				halign:'center',
				align:'left'
			},{
				title:'供试品名称',
				field:'tiName',
				width:200,
				halign:'center',
				align:'left'
			},{
				title:'label',
				field:'contentLabel',
				hidden:true
				
			},{
				title:'含量',
				field:'content',
				width:160,
				halign:'center',
				align:'left',
				formatter: function(value,row,index){
					
					if (value==null || value==''){
						return '';
					}else{
						return (row.contentLabel==null?'含量/浓度/纯度：':row.contentLabel+'：')+value;
					}
					
				}
			},{
				title:'外观',
				field:'physical',
				width:100,
				halign:'center',
				align:'left'
				
			},{
				title:'存储条件',
				field:'storageCondition',
				width:100,
				halign:'center',
				align:'left'
				
			},{
				title:'有效期限/失效期',
				field:'isFailureDateFlag',
				hidden:true
			},{
				title:'精度',
				field:'failureDatePrecision',
				hidden:true
				
			},{
				title:'有效期/失效期',
				field:'validityPeriod',
				width:150,
				halign:'center',
				align:'left',
				formatter: function(value,row,index){
					
					if (value==null || value==''){
						return '';
					}else if(row.failureDatePrecision==1){
					
						return (row.isFailureDateFlag==0?'有效期：':'失效期：')+value.substr(0,4);
					}else if(row.failureDatePrecision==2){
						return (row.isFailureDateFlag==0?'有效期：':'失效期：')+value.substr(0,7);
					}else if(row.failureDatePrecision==3){
						return (row.isFailureDateFlag==0?'有效期：':'失效期：')+value;
					}
					
				}
				
			},{
				title:'留样量',
				field:'reserveNum',
				width:100,
				halign:'center',
				align:'left'
				
			},{
				title:'供试品确认',
				field:'confirmSign',
				width:130,
				halign:'center',
				align:'left',
				
			},{
				title:'SD',
				field:'sd',
				width:40,
				halign:'center',
				align:'center',
				hidden:true
			},{
				title:'委托方地址',
				field:'sponsorAddress',
				width:223,
				halign:'center'
			},{
				title:'委托方联系人',
				field:'sponsorLinkman',
				width:80,
				halign:'center'
			},{
				title:'委托方电话',
				field:'sponsorTel',
				width:90,
				halign:'center'
			},{
				title:'委托方电子邮件',
				field:'sponsorEmail',
				width:90,
				halign:'center'
			},{
				title:'委托方传真',
				field:'sd5',
				width:90,
				halign:'sponsorFax'
			},{
				title:'报告出具方',
				field:'venderName',
				width:200,
				halign:'center'
			},{
				title:'报告出具方地址',
				field:'venderAddress',
				width:223,
				halign:'center'
			},{
				title:'报告出具方联系人',
				field:'venderLinkman',
				width:80,
				halign:'center'
			},{
				title:'报告出具方电话',
				field:'venderTel',
				width:90,
				halign:'center'
			},{
				title:'报告出具方电子邮件',
				field:'venderEmail',
				width:90,
				halign:'center'
			},{
				title:'报告出具方传真',
				field:'venderFax',
				width:90,
				halign:'center'
			},{
				title:'供试品提供方',
				field:'testItem_venderName',
				width:200,
				halign:'center'
			}]],
			onUnselect:function(rowIndex, rowData){
	    	   	if(isAllNotConfirm()){
		   	   		$('#confirm').linkbutton('enable');
		   	   	}else{
		   	   		$('#confirm').linkbutton('disable');
		      	}
	    	   	if(isOneSelected()){
		   	   		$('#editConfirm').linkbutton('enable');
		   	   		$('#tblTestItemAttachment').linkbutton('enable');
		   	   	}else{
		   	   		$('#editConfirm').linkbutton('disable');
		   	   		$('#tblTestItemAttachment').linkbutton('disable');
		      	}
		      	var queren = $('#queren').val();
		      	if(queren == "false"){
		      	    $('#confirm').linkbutton('disable');
		      	}
		      
			},
			onSelect:function(rowIndex, rowData){
			    
    	   		if(isAllNotConfirm()){
        	   		$('#confirm').linkbutton('enable');
        	   	    //$('#tblTestItemAttachment').linkbutton('enable');
        	   	}else{
        	   		$('#confirm').linkbutton('disable');
        	   		//$('#tblTestItemAttachment').linkbutton('enable');
           	   	}
           	   	
    	   		if(isOneSelected()){
		   	   		$('#editConfirm').linkbutton('enable');
		   	   		$('#tblTestItemAttachment').linkbutton('enable');
		   	   	}else{
		   	   		$('#editConfirm').linkbutton('disable');
		   	   		$('#tblTestItemAttachment').linkbutton('disable');
		      	}
		      	
		      	 $.ajax({
					url : sybp()+ '/tblTestItemAttachmentAction_selectAttachment.action?testItemCode='+rowData.tiNo,
					dataType : 'json',
					success : function(r) {
						if (r && r.success) {
						    //$('#layoutMainDiv').layout('expand','east');
		      	            selectTestItemAttachmentTable(rowData.tiNo);
				        }else{
				            //$('#layoutMainDiv').layout('collapse','east');
				            $('#selectTestItemAttachmentTable').datagrid('loadData', { total: 0, rows: [] });
				        }
			        }
			     });
			     var queren = $('#queren').val();
		      	 if(queren == "false"){
		      	    $('#confirm').linkbutton('disable');
		      	 }
			},
			
			onDblClickRow:function(rowIndex, rowData){
				if(rowData.confirmSign == '未确认'){
					showTestItemAddEditDialog('confirmAndEditOne','confirm','确认签字',rowData.id);
				}else{
					var queren = $('#queren').val();
			      	if(queren == "false"){
			      		showTestItemAddEditDialog('','','查看/编辑',rowData.id);
			      	}else{
						showTestItemAddEditDialog('AfterEditOne','edit','查看/编辑',rowData.id);
				    }
				}
	     	},
			onLoadSuccess:function(data){
	     		//$('#selectrowId').val();
	     		$('#confirm').linkbutton('disable');
	     		$('#editConfirm').linkbutton('disable');
				var selectrowId = $('#selectrowId').val();
				if(selectrowId){
					$('#testItemTable').datagrid('unselectAll');
					$('#testItemTable').datagrid('selectRecord',selectrowId);
				}
				  var  dengji = $('#dengji').val();
				  if(dengji == "true"){
				     $('#uploadAttachmentsButton').linkbutton('enable');
				  }else{
				     $('#uploadAttachmentsButton').linkbutton('disable');
				  } 
			},
			toolbar:'#toolbar',
			
	   	}); //end datagrid
    }

    /**编辑后*/
	function AfterEditOne(){
        parent.showMessager(1,'保存成功',true,5000);
		$('#testItemAddEditDialog').dialog('close'); 
		$('#testItemTable').datagrid('reload');
		//$('#isConfirm').combobox('select','');
	}

    /**编辑+签字*/
	function confirmAndEditOne(){
		//$('#selectrowId').val();
        parent.showMessager(1,'签字成功',true,5000);
		$('#testItemAddEditDialog').dialog('close'); 
		//$('#testItemTable').datagrid('reload');
		$('#isConfirm').combobox('select','');
		//var startTime =  $('#mindatebox').datebox('getValue');
        //var endTime = $('#maxdatebox').datebox('getValue');
        //var tiCode =  $('#studyType_testItemAndNo').combobox('getValue');
       // var selecttiName = $('#searchtiName').searchbox('getValue');
       // var isConfirm = $('#isConfirm').combobox('getValue');
       // showtestItemTable(startTime,endTime,tiCode,selecttiName,isConfirm);
	}
    
    function showtestItemTable(startDate,endDate,tiCode,selecttiName,isConfirm){
       //
     /*  if(startDate == null && endDate==null){
           $('#testItemTable').datagrid({
			 url : sybp()+'/tblTestItemAction_loadtestItemList.action?tiName='+encodeURIComponent(selecttiName),
			
	     	}); //end datagrid
       }else{*/
	         $('#testItemTable').datagrid({
				url : sybp()+'/tblTestItemAction_loadtestItemList.action?startDate='
				+startDate+'&endDate='+endDate+'&tiCode='+tiCode+'&tiName='+encodeURIComponent(selecttiName)+'&isConfirm='+isConfirm,
				
		   	}); //end datagrid
      /* }*/
       
     
    }

    /**判断表格选中的是否都是未确认的*/
	function isAllNotConfirm(){
		var rows = $('#testItemTable').datagrid('getSelections');
		if(rows && rows.length){
			for(var i = 0 ;i < rows.length;i++){
				if(rows[i].confirmSign != '未确认'){
					return false;
				}
			}
		}else{
			return false;
		}
		return true;
	}
    /**判断表格选中的是否仅选中一个*/
	function isOneSelected(){
		var rows = $('#testItemTable').datagrid('getSelections');
		if(rows && rows.length == 1){
			return true;
		}else{
			return false;
		}
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

	/**确认签字*/
	function onConfirm(){
		var rows = $('#testItemTable').datagrid('getSelections');
		if(rows && rows.length){

			var ids = new Array();
			for(var i = 0 ;i < rows.length;i++){
				if(rows[i].confirmSign == '未确认'){
					ids.push(rows[i].id);
				}
			}
			if(ids && ids.length){
				qm_showQianmingDialog('afterConfirm');
			}
		}
	}
	//签字确认后置方法,
	function afterConfirm(){
		var rows = $('#testItemTable').datagrid('getSelections');
		if(rows && rows.length){
			var ids = new Array();
			for(var i = 0 ;i < rows.length;i++){
				if(rows[i].confirmSign == '未确认'){
					ids.push(rows[i].id);
				}
			}
			if(ids && ids.length){
				$.ajax({
					url:sybp()+'/tblTestItemAction_confirm.action?ids='+ids,
					type:'post',
					dataType:'json',
					success:function(r){
						if(r && r.success){
							//按钮置为无效
							parent.showMessager(1,'签字成功',true,5000);
							$('#selectrowId').val('');
							$('#isConfirm').combobox('select','');
						}else{
						    parent.showMessager(3,'签字失败',true,5000);
						}
					}
				});
			}
		}
	}
	/**详细信息  响应函数*/
	function onEditConfirm(){
		var rows = $('#testItemTable').datagrid('getSelections');
		if(rows && rows.length == 1){
			if(rows[0].confirmSign == '未确认'){
				showTestItemAddEditDialog('confirmAndEditOne','confirm','确认签字',rows[0].id);
			}else{
				var queren = $('#queren').val();
		      	if(queren == "false"){
		      		showTestItemAddEditDialog('','','查看/编辑',rows[0].id);
		      	}else{
					showTestItemAddEditDialog('AfterEditOne','edit','查看/编辑',rows[0].id);
			    }
			}
		}
	}
	//附件开关
	function testItemAttachment(){
	  var collapseOrExpand =  $('#collapseOrExpand').val();
	  if(collapseOrExpand == 1){
	     $('#layoutMainDiv').layout('expand','east');
	  }else{
	     $('#layoutMainDiv').layout('collapse','east');
	  }
	}
	//上传附件
	function uploadAttachmentsButton(){
	   showAttachementAddEditDialog('afterupLoadAttachment','add','上传附件');
	   var tiNo = $('#theTiNo').val();
	   var tiName = $('#theTiName').val();
	   $('#attachmenttestItemName').val(tiName);
	   $('#attachmenttestItemCode').val(tiNo);
	}
   //上传附件
	function uploadSelectAttachmentsButton(){
	   showAttachementAddEditDialog('afterupLoadAttachment','add','上传附件');
	   var row = $('#testItemTable').datagrid('getSelected');
	   $('#attachmenttestItemName').val(row.tiName);
	   $('#attachmenttestItemCode').val(row.tiNo);
	 }
	
	/**显示Dialog*/
	function showAttachementAddEditDialog(clickName,addOrEdit,title){
		/*签名Dialog*/
		document.getElementById("attachmentAddEditDialog2").style.display="block";
		$('#attachmentAddOrEdit').val(addOrEdit);
		$('#attachmentAddEditDialog').dialog('setTitle',title);
		$('#attachmentAddEditDialog').dialog('open'); 
		document.getElementById("attachmentAddEdit_event").href="javascript:"+clickName+"();";
		//清空数据
		attachmentFullEditData();
		//初始化form
		initAttachmentForm();
	}

    function selectTestItemAttachmentTable(testItemCode){
        $('#selectTestItemAttachmentTable').datagrid({
			url : sybp()+'/tblTestItemAttachmentAction_loadAttachment.action?testItemCode='+testItemCode,});
    }
    //testItemAttachmentTable
    function testItemAttachmentTable(){
           var tiNo = $('#theTiNo').val();
           $('#testItemAttachmentTable').datagrid({
			url : sybp()+'/tblTestItemAttachmentAction_loadAttachment.action?testItemCode='+tiNo,
			title:'',
			height:400,
			width:650,
			nowarp:  false,//单元格里自动换行
			singleSelect:true,
			fitColumns:false,
			pagination:false,//分页
			idField:'id',
			columns :[[{
				title:'id',
				field:'id',
				width:10,
				halign:'center',
				align:'center',
				hidden:true
			},{
				title:'供试品编号',
				field:'testItemCode',
				width:150,
				halign:'center',
				align:'left',
			    hidden:true
			},{
				title:'附件名称',
				field:'attachmentName',
				width:150,
				halign:'center',
				align:'left'
				
			},{
				title:'操作者',
				field:'operator',
				width:80,
				halign:'center',
				align:'left'
				
			},{
				title:'上传时间',
				field:'appendDate',
				width:110,
				halign:'center',
				align:'left'
				
			},{
				title:'备注',
				field:'remark',
				width:160,
				halign:'center',
				align:'left'
				
			},{
				title:'操作',
				field:'operate',
				width:120,
				halign:'center',
				align:'left',
			    formatter: function(value,row,index){
			        var  dengji = $('#dengji').val();
			        if(dengji != "true" ){
			            return "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style='cursor:pointer;'  onclick='downloadTestItemAttachment("+row.id+");'>查看</a> ";
			        }else{
			            return "&nbsp;&nbsp;&nbsp;<a  style='cursor:pointer;' onclick='delectTestItemAttachment("+row.id+");'>删除</a>&nbsp;&nbsp;&nbsp;&nbsp;<a style='cursor:pointer;'  onclick='downloadTestItemAttachment("+row.id+");'>查看</a> ";
			        }
			   }
				
				
			}]],
			onSelect:function(rowIndex, rowData){
			},
			onLoadSuccess:function(data){
			},
			
	   	}); //end datagrid
        
    }
    
    

    function delectTestItemAttachment(id){
         //var row =  $('#testItemAttachmentTable').datagrid('getSelected');
        qm_showQianmingDialog("afterDelectTestItemAttachment("+id+")");
    }
    
    function afterDelectTestItemAttachment(id){
         $.ajax({
					url : sybp()+ '/tblTestItemAttachmentAction_delete.action?id='+id,
					dataType : 'json',
					success : function(r) {
						if (r && r.success) {
							parent.showMessager(1, '附件删除成功', true, 5000);
							//$('#testItemAttachmentTable').datagrid('reload');
							 $('#selectTestItemAttachmentTable').datagrid("reload");
				        }else{
					        parent.showMessager(2, '附件删除失败', true, 5000);
				        }
			        }
			});
    
    }
    
    function downloadTestItemAttachment(id){
       //var row =  $('#testItemAttachmentTable').datagrid('getSelected');
       window.location.href='${pageContext.request.contextPath}/tblTestItemAttachmentAction_download2.action?id='+id;
    }
	//填充数据(或清空数据)
	function attachmentFullEditData(){
	    document.attachmentAddEditForm.reset();//
	}
	//上传成功后
    function afterupLoadAttachment(){
       //alert("上传成功");
       //$('#testItemAttachmentTable').datagrid("reload");
        var row = $('#selectTestItemAttachmentTable').datagrid("getRows");
       if(row.length < 1){
           var table = $('#testItemTable').datagrid("getSelected");
           selectTestItemAttachmentTable(table.tiNo);
       }else{
         $('#selectTestItemAttachmentTable').datagrid("reload");
       }
       parent.showMessager(1, '附件上传成功', true, 5000);
       
    }
    
	/**
	 * 文件选择框   onchange事件
	 * @param obj
	 * @return
	 */
	function attachmentFileChange(obj){
		var fullFileName = obj.value;
		if(!fullFileName){
			return false;
		}
	//	var pos = fileName.lastIndexOf("\");
	//			fileName.substring(pos+1); 
		
		
		//判断文件名是否存在，若存在则清空且提示
		var uploadFiles = document.getElementsByName('uploadFile');
		
		var num = 0;
		for(var i = 0;i < uploadFiles.length; i++){
			if(uploadFiles[i].value == fullFileName){
				num++;
			}
		} 
		var pos = fullFileName.lastIndexOf('\\');
		var fileName = fullFileName.substring(pos+1);
		if(num >1){
			$(obj).val('');
			$.messager.alert('警告',"文件'"+fileName+"'已选择，请选择其他文件。"); 
		}else{
			var pos1 = fileName.lastIndexOf('.');
			//有无后缀
			if(pos1>-1){
				suffix = fileName.substring(pos1+1);
				if(suffix == 'exe' || suffix == 'bat'){
					//无后缀
					$(obj).val('');
					$.messager.alert('警告',"不能上传以.exe或.bat结尾的文件。"); 
				}
			}else{
				//无后缀
				$(obj).val('');
				$.messager.alert('警告',"文件格式不正确。"); 
			}
			//if(!$('#describe').val()){
			//	$('#describe').val(fileName);
			//}
			//alert(fileName);
			//alert(fullFileName);
			var  object = document.getElementsByName('uploadFile');
	        for ( var  i = 0 ;i < object.length;i ++ ){
			    if(fullFileName == object[i].value){
			       var  object = document.getElementsByName('attachmentNames');
	               object[i].value=fileName;
	               break;
			    }
			}
		}
	}
	
	function onDialogAttachmentSaveButton(){
	   if($('#attachmentAddEditForm').form('validate')){
			qm_showQianmingDialog('afterOnDialogAttachmentSaveButton');	   
	   }
	}
   
    function afterOnDialogAttachmentSaveButton(){
           $.messager.progress({ text :'上传中...' });	// 显示进度条
	       $('#attachmentAddEditForm').submit(); 
    }
    
	function initAttachmentForm(){
		$('#attachmentAddEditForm').form({    
		    url:sybp()+'/tblTestItemAttachmentAction_importFile.action',
		    onSubmit: function(){    
				var isValid = $(this).form('validate');
				if (!isValid){
					$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
				}
				return isValid;	// 返回false终止表单提交
		    },    
		    success:function(data){   
		    	$.messager.progress('close');	// 如果提交成功则隐藏进度条
		    	if(data == 'true'){
		    		$('#attachmentAddEditDialog').dialog('close');
		    		
		    		document.getElementById("attachmentAddEdit_event").click();
		    	}else{
		    		$.messager.alert('警告','文件上传失败'); 
		    	}
		    } 
		});    
	}
	
	/**
	 * 添加一行文件选择框
	 * @return
	 */
	function addFileList(){
		var fileList = $('#fileList');
		var fileList_1 = $('#fileList_1');
		var deleteA = $('#deleteA');
		var fileList_1_clone = fileList_1.clone(true);
		var deleteA_clone = deleteA.clone(true);
		deleteA_clone.css('display','');
		
		fileList_1_clone.attr('id','11');
		fileList_1_clone.append(deleteA_clone);
		fileList.append(fileList_1_clone);
		var  object = document.getElementsByName('attachmentNames');
	    object[object.length-1].value="";
	    var  object1 = document.getElementsByName('remarks');
	    object1[object1.length-1].value="";
	}
	/**
	 * 删除行
	 * @param currentA
	 * @return
	 */
	function deleteCurrentDiv(currentA){
	//	document.getElementById("1").parentNode.firstChild.nodeValue
		$(currentA.parentNode).remove();
	}
	//展开右侧
	function collapseEast(){
	  $('#layoutMainDiv').layout('collapse','east');
	}
</script>
</head>
<body>
<s:hidden id="collapseOrExpand" value="1"></s:hidden>
<s:hidden id="selectrowId" ></s:hidden>
<!-- 开始时间 -->
<s:hidden id="startTime" name="startTime"></s:hidden>
<!-- 结束时间 -->
<s:hidden id="endTime" name="endTime" ></s:hidden>
<!-- 供试品类型 -->
<s:hidden id="tiCode" name="tiCode" ></s:hidden>
<!-- 模糊查询内容 -->
<s:hidden id="selecttiName"  ></s:hidden>
<!--  用户地区-->
<s:hidden id="cuid"  ></s:hidden>
<!-- 用户名 -->
<s:hidden id="customerNameadd"  ></s:hidden>
<!-- 供试品文件查看 -->
<s:hidden id="chakan" name="chakan" ></s:hidden>
<!-- 供试品文件登记 -->
<s:hidden id="dengji" name="dengji" ></s:hidden>
<!-- 供试品信息确认 -->
<s:hidden id="queren" name="queren" ></s:hidden>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
     <div data-options="region:'north',border:false," style="height:60px;">
     <div  class="easyui-tabs" fit="true" border="false">
			<div title="供试品信息确认" border="false" style="overflow: auto;">
			     <div style="height:30px;">
			       <div style="float:left; width:100%;padding-top:5px;height:20px;">
			       		&nbsp;&nbsp;确认状态
			       		<select id="isConfirm" class="easyui-combobox" data-options="panelHeight:'auto'" name="isConfirm" style="width:70px;height:19px;">   
						    <option value="">全部</option>   
						    <option value="0">未确认</option>   
						    <option value="1">已确认</option>
						</select> 
			    		&nbsp;&nbsp;供试品类型
			    		<input id="studyType_testItemAndNo" class="easyui-combobox" style="height:19px;width:80px;"
			    		data-options="panelHeight:'auto'"/>
			    	    &nbsp;&nbsp;&nbsp;&nbsp; 提交日期范围
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
			 </div>
		</div>
     </div> 
 	<div region="center" title="" style="overflow: hidden;" data-options="border:false" >
				<table id="testItemTable" ></table>
        <div id="toolbar">
			<a id="confirm" class="easyui-linkbutton" onclick="onConfirm();" data-options="iconCls:'icon-ok',plain:true,disabled:true">确认签字</a>		
			<a id="editConfirm" class="easyui-linkbutton" onclick="onEditConfirm();" data-options="iconCls:'icon-edit',plain:true,disabled:true">详细信息</a>
			<a id="tblTestItemAttachment" class="easyui-linkbutton" onclick="testItemAttachment();" data-options="iconCls:'icon-attach',plain:true,disabled:true">附件信息</a>	  			
		</div>
 	</div>
 	 <div data-options="region:'east',title:'附件信息',collapsed:true,border:false,iconCls:'icon-attach'"  style="width:640px;">
 	     <table id="selectTestItemAttachmentTable" ></table>
 	     <div id="dialogToolbar6">
	        <a id="uploadAttachmentsButton" class="easyui-linkbutton" onclick="uploadSelectAttachmentsButton();" data-options="iconCls:'icon-attach',plain:true">上传附件</a>
	        <a id="attachmentbackButton" class="easyui-linkbutton" onclick="collapseEast();" data-options="iconCls:'icon-nook',plain:true">关闭</a>
         </div>
 	 </div>   
 <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
 <%@ include file="/WEB-INF/jsp/tblTestItemAction/testItemAddorEdit.jspf"%>
 <%@ include file="/WEB-INF/jsp/tblTestItemAction/selectCustomer.jspf"%>
 <%@ include file="/WEB-INF/jsp/tblTestItemAction/attachmentAddEdit.jspf" %>
</div>

</body>
</html>
