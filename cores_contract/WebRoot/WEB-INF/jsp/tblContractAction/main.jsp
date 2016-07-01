<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>合同信息管理</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/contractAddEdit.css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/tblContractAction/contractAddEdit.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/tblContractAction/studyItemAddEdit.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/tblContractAction/studyItemMultiAdd.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/tblContractAction/studyTypeMultiSelect.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/tblContractAction/studyTypeSelect.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/tblContractAction/attachmentAddEdit.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/tblCustomerAction/selectCustomer.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblCustomerAction/customerAddEdit.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblContractAction/tblTestItem.js" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/customerAddEdit.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/studyItemMultiAdd.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/tblTestItem.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
<style type="text/css">
	.tree-icon {
		width:0px;
	}
	.datagrid-row-selected{
		background:#d2dfff;
	}
</style>
<script type="text/javascript">

	var currentId ;   	 //合同ID
	var contractState;    //合同状态       0 未提交,1 进行中,2再编辑,3结束了，-1终止
	var owner ;			  //是否     是自己合同
	var write ;			  //是否     有写的权限   
	var contractTabs ;    //选项卡
	$(function(){
		currentId = $('#currentId').val();
		contractState = $('#contractState').val();
		owner = $('#owner').val();
		write = $('#write').val();
		
	    var tableHeight = (document.body.clientHeight - 64)*0.85;
		var tableWidth  = (document.body.clientWidth - 400)*0.99;
		/**选项卡*/
		contractTabs=$('#contractTabs');
    		contractTabs.tabs({
    			onSelect:function(title,index){
    			var contractCode = $('#parentContractCode').val();
    			   if(index == 1 ){
                       var tab2=contractTabs.tabs('getTab',index);
                       contractTabs.tabs('update', {
			    			tab: tab2,
			    			options: {
			    				title: '项目进度',
			    				content: '<iframe src="' + '${pageContext.request.contextPath}/tblStudyScheduleAction_list.action?contractCode='+contractCode+ ' "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
			    			}
			    		});
			       }else if(index == 2 ){
                       var tab2=contractTabs.tabs('getTab',index);
                       contractTabs.tabs('update', {
			    			tab: tab2,
			    			options: {
			    				title: '报告登记',
			    				content: '<iframe src="' + '${pageContext.request.contextPath}/tblReportRecordAction_list.action?contractCode='+contractCode+ ' "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
			    			}
			    		});
			       }else if(index == 3){
				        var tab3=contractTabs.tabs('getTab',index);
				        contractTabs.tabs('update', {
				    			tab: tab3,
				    			options: {
				    				title: '付款记录',
				    				content: '<iframe src="' + '${pageContext.request.contextPath}/tblPaymentRecordAction_paymentRecord.action?contractCode='+contractCode+ ' "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
				    			}
				    		});
			       
			       }
    			}
    		});
		
	    $('#testItemAndStudyItemTreeTable').treegrid({ 
	        url : sybp()+'/tblContractAction_testItemAndStudyItemloadList.action?id='+currentId,
	        idField:'id',    
		    treeField:'tiNo', 
		    height:tableHeight,
			width:tableWidth,
		    animate:false,   
		    singleSelect:true, //不支持多选
		    title:'供试品&委托项目',
		    columns:[[  
                {title:'id',field:'id',width:180,hidden:true},
                {field:'_parentId',title:'_parentId',width:180,hidden:true},
		        {field:'tiNo',title:'编号',width:190,halign:'center'},
		        {field:'tiName',title:'名称',width:200,halign:'center'},
		        //{field:'studyNo',title:'委托试验名称',width:180},
		        //{field:'content',title:'含量/浓度/纯度',width:80},
		        {field:'tiType',title:'类型',width:50,halign:'center'},
		        {field:'text',title:'',width:285},
		        {field:'kind',title:'',width:10,hidden:true},
		        {field:'tstate',title:'',width:10,hidden:true}
		    ]],
			onSelect :function(row){
			   if(row && row.kind == "G"){//供试品
				    $('#editTestItemButton').linkbutton('disable');
				    $('#delTestItemButton').linkbutton('disable');
				    $('#addStudyItemButton').linkbutton('disable');
				    
				    $('#editStudyItemButton').linkbutton('disable');
				    $('#delStudyItemButton').linkbutton('disable');
				    if( (contractState == 0 || contractState == 2) && ( write == 1 || owner == 1 ) ){
					     $('#editTestItemButton').linkbutton('enable');
						 $('#addStudyItemButton').linkbutton('enable');
						 var node = $('#testItemAndStudyItemTreeTable').treegrid('find', row.id);
					     if($('#testItemAndStudyItemTreeTable').treegrid('getChildren', node.id).length == 0){
					    	 $('#delTestItemButton').linkbutton('enable');
						 }
					}
				    
			   }else if(row ){
				     $('#editTestItemButton').linkbutton('disable');
				     $('#delTestItemButton').linkbutton('disable');
				     $('#addStudyItemButton').linkbutton('disable');
				     
				     $('#editStudyItemButton').linkbutton('disable');
				     $('#delStudyItemButton').linkbutton('disable');
				     if( (contractState == 0 || contractState == 2) && ( write == 1 || owner == 1 ) ){
					    $('#editStudyItemButton').linkbutton('enable');
					     if(row.tstate == 0){
						     $('#delStudyItemButton').linkbutton('enable');
						 }else{
							 /*
					            var isValidate = $.ajax({   
					            		url: sybp()+'/tblStudyItemAction_hasSD.action',   
					            		dataType: "json",   
					            		data: {
											id:row.id
					            		},   
					            		async: false,   
					            		cache: false,   
					            		type: "post"  
					            	}).responseText;
				            	if(isValidate == "false"){
				            		$('#editStudyItemButton').linkbutton('enable');
								   
					            }
					           */
						 }
					 }
			   }
			   
			   //增加一个个判断供试品是否签字确认
			   if( ($('#editTestItemButton').linkbutton('options').disabled)  ){
			     // alert("已经不可编辑");
			   }else{
			      //alert("编辑加判断");
				    $.ajax({
				        	url:sybp()+'/tblTestItemAction_checkTestItemConfirmSign.action',
					     	data:{
				      			tiNo:row.tiNo
					  		},
					 		dataType:'json',
					 		success:function(r){
					 			if(r && r.success){
					 				//alert("不可编辑");
					 				 $('#editTestItemButton').linkbutton('disable');
				                     $('#delTestItemButton').linkbutton('disable');
					 			}else{
					 				
					 			}
					 		}
				       });
			   }
			   
			   
			   
	     	},
	     	onDblClickRow:function(row){
	     	    if(row.kind == "G"){
	     	    	if( (contractState == 0 || contractState == 2) && ( write == 1 || owner == 1 ) ){
	     	    		$.ajax({
				        	url:sybp()+'/tblTestItemAction_checkTestItemConfirmSign.action',
					     	data:{
				      			tiNo:row.tiNo
					  		},
					 		dataType:'json',
					 		success:function(r){
					 			if(r && r.success){
						 			//已确认不可编辑
					     	        showTestItemAddEditDialog('','select','查看供试品');
					 			}else{
					 				if(row.tstate == 1 ){
					 					document.getElementById('tblTestItemtiNo').disabled = true;
					 				}else{
					 					document.getElementById('tblTestItemtiNo').disabled = false;
					 				}
					 		      	showTestItemAddEditDialog('afterTestItemEditDialog','edit','编辑供试品');
					 			}
					 		}
				       });
		     	    }else{
		     	        showTestItemAddEditDialog('','select','查看供试品');
			     	}
	     	    }else{
		     	    
	     	      	studyItemFullEditData();
				   	if((contractState == 0 || contractState == 2) && ( write == 1 || owner == 1 ) ){
					    if(row.tstate == 0){
					   		document.getElementById('studyItem_studyNo').disabled = false;
							showStudyItemAddEditDialog('afterOnEditStudyItemButton','edit','编辑委托项目');
						}else{
				            var isValidate = $.ajax({   
				            		url: sybp()+'/tblStudyItemAction_hasSD.action',   
				            		dataType: "json",   
				            		data: {
										id:row.id
				            		},   
				            		async: false,   
				            		cache: false,   
				            		type: "post"  
				            	}).responseText;
			            	
			            	if(isValidate == "false"){
			            		document.getElementById('studyItem_studyNo').disabled = false;
								showStudyItemAddEditDialog('afterOnEditStudyItemButton','edit','编辑委托项目');
				            }else{
							   	showStudyItemAddEditDialog('','select','查看委托项目');
					        }
						 }
					}else{
					   	showStudyItemAddEditDialog('','select','查看委托项目');
					}
	     	    }
	     	},
	     	onLoadSuccess:function(row, data){
	     	    $('#editTestItemButton').linkbutton('disable');
			    $('#delTestItemButton').linkbutton('disable');
			    $('#addStudyItemButton').linkbutton('disable');
			    $('#editStudyItemButton').linkbutton('disable');
			    $('#delStudyItemButton').linkbutton('disable');
			    var rowid = $('#selectrowId').val();
		        if(rowid){
	                $('#testItemAndStudyItemTreeTable').treegrid('select',rowid);
	                $('#selectrowId').val("");
		        }else{
					if(!data.rows.length){

						if($('#contractTabs').is(':visible')){
							//无数据时,添加供试品
							document.getElementById('tblTestItemtiNo').disabled = false;
						    showTestItemAddEditDialog('afterTestItemAddDialog','add','添加供试品');
						}
					}
			    }
		    },
		    onLoadError:function(row, data){
		    },
		    rowStyler: function(field,row){
				if (field.kind == "G" ){
					return 'color:green;';
				}else{
					return 'color:#FF8C00;';
				}
			}
		    
	     });//treegrid end
	    
	    //隐藏选项卡
	    if(contractState == 0 ){
	      hiddenTabs();
	    }
	
		//显示整个布局
		$('#layoutMainDiv').css('display','');
		if($('#currentId').val()){
			$('#contractTabs').css('display','');
		}
		
    });//匿名函数结束
    
    /**隐藏选项卡*/
    function  hiddenTabs(){
       for (i=1;i<4 ;i++ ){ 
		    var tab_option = $('#contractTabs').tabs('getTab',i).panel('options').tab;  
             tab_option.hide();  
         }
    }
	//合同提交     onclick
    function onSubmitContractButton(){
        //合同提交前检查
    	$.ajax({
        	url:sybp()+'/tblContractAction_checkBeforeSubmit.action',
			data:{
    			currentId:currentId
			},
			dataType:'json',
			success:function(r){
				if(r && r.success){
					qm_showQianmingDialog('submitContract');
				}else{
					$.messager.alert('警告',r.msg);
				}
			}
        });
    }
	//合同真正提交
    function submitContract(){
	    $.ajax({
	    	url:sybp()+'/tblContractAction_submit.action',
			data:{
				currentId:currentId
			},
			dataType:'json',
			success:function(r){
				if(r && r.success){
					 parent.showMessager(1,'合同提交成功',true,5000);
					 window.location.href = sybp()+'/tblContractAction_main.action?currentId='+currentId;
				}else{
					$.messager.alert('警告',r.msg);
				}
			}
	    });
    }
    
  	//合同登记  按钮事件
    function onAddButton(){
        document.getElementById('contractCode').disabled = false;
        fullEditData();
    	showContractAddEditDialog('afterAddDialog','add','登记合同');
    	getNextContractPoolNum();
    	
    	
    }
   
   
    
	//合同登记(add)Dialog后事件
    function afterAddDialog(){
       parent.showMessager(1,'合同登记成功',true,5000);
       parent.window.frames["left"].frames["left1"].updateCodeCombobox($('#contractCode').val());
    }
	//编辑合同
    function editContract(currentContractId){
    	$('#id').val(currentContractId);
    	$.ajax({
        	url:sybp()+'/tblContractAction_editUI.action',
			data:{
    			currentId:currentContractId
			},
			dataType:'json',
			success:function(r){
				if(r){
					fullEditData(r);
					if(contractState == 2){
						document.getElementById('contractCode').disabled = true;
					}else{
						document.getElementById('contractCode').disabled = false;
					}
			     	showContractAddEditDialog('afterEditDialog','edit','编辑合同');
				}
			}
        });
    }
	
   //合同登记(edit)Dialog后事件
    function afterEditDialog(){
       parent.showMessager(1,'合同编辑成功',true,5000);
       parent.window.frames["left"].frames["left1"].updateCodeCombobox($('#contractCode').val());
    }
	//合同附件
	function onContractAttachmentButton(){
		showAttachementAddEditDialog('afterOnContractAttachmentButton','add','合同附件上传');
	}
	//合同附件上传成功后
	function afterOnContractAttachmentButton(){
		 parent.showMessager(1,'合同附件上传成功',true,5000);
		 window.location.href = sybp()+'/tblContractAction_main.action?currentId='+currentId;
		//parent.window.frames["left"].frames["left1"].updateCodeCombobox($('#currentContractCode').val());
	}
	//再编辑
	function onAgainEditButton(){
		if(contractState == 1 && write == 1){
			qm_showQianmingDialog('againEdit');
		}else{
			$.messager.alert('警告','无此权限');
		}
	}
	//真正再编辑
	function againEdit(){
		$.ajax({
	    	url:sybp()+'/tblContractAction_againEdit.action',
			data:{
				currentId:currentId
			},
			dataType:'json',
			success:function(r){
				if(r && r.success){
					 parent.showMessager(1,'合同置为可编辑',true,5000);
					 window.location.href = sybp()+'/tblContractAction_main.action?currentId='+currentId;
				}else{
					$.messager.alert('警告',r.msg);
				}
			}
	    });
	}
    
    //添加供试品  by 小万
    function onAddTestItemButton(){
       document.getElementById('tblTestItemtiNo').disabled = false;
       showTestItemAddEditDialog('afterTestItemAddDialog','add','添加供试品');
    }
    //编辑供试品
    function  onEditTestItemButton(){
    	var row = $("#testItemAndStudyItemTreeTable").treegrid('getSelected');
    	if(row.tstate == 1 ){
			document.getElementById('tblTestItemtiNo').disabled = true;
		}else{
			document.getElementById('tblTestItemtiNo').disabled = false;
		}
      showTestItemAddEditDialog('afterTestItemEditDialog','edit','编辑供试品');
    }
    
    function  onEditFullTestItem(){
       var tid=$("#testItemAndStudyItemTreeTable").treegrid('getSelected');
		if(tid!=null){
			testItemtFullEditData(tid.id);
		}else{
			$.messager.alert('提示','请选择编辑的供试品');
		}
    }
  
	//添加委托项目   onclick
    function onAddStudyItemButton(){
		var row = $('#testItemAndStudyItemTreeTable').treegrid('getSelected');
    	if(null != row){
	    	 showStudyItemMultiAddDialog(afterOnAddStudyItemButton,'添加委托项目');
    	}else{
          	$.messager.alert('提示','请选择所需要添加委托项目的供试品!');
       	}
    }
	//添加委托项目   onclick 后执行
    function afterOnAddStudyItemButton(){
    	 parent.showMessager(1,'委托项目添加成功',true,5000);
    	 $('#testItemAndStudyItemTreeTable').treegrid('reload');
    }
    //编辑委托项目
    function onEditStudyItemButton(){
      studyItemFullEditData();
      var row = $('#testItemAndStudyItemTreeTable').treegrid('getSelected');
      //清空数据
      if(null != row && row.kind ==  "W" ){
    	  	if(row.tstate == 1){
	  			document.getElementById('studyItem_studyNo').disabled = true;
	  		}else{
	  			document.getElementById('studyItem_studyNo').disabled = false;
	  		}
    	  	if(row.tstate == 0){
    	  		showStudyItemAddEditDialog('afterOnEditStudyItemButton','edit','编辑委托项目');
        	}else{
	    	  	var isValidate = $.ajax({   
	        		url: sybp()+'/tblStudyItemAction_hasSD.action',   
	        		dataType: "json",   
	        		data: {
	    				id:row.id
	        		},   
	        		async: false,   
	        		cache: false,   
	        		type: "post"  
	        	}).responseText;
	    	
	    		if(isValidate == "false"){
					showStudyItemAddEditDialog('afterOnEditStudyItemButton','edit','编辑委托项目');
	    		}else{
	    			$.messager.confirm('确认','该委托项目已任命SD，确定继续？',function(r){    
	    			    if (r){    
	    			    	showStudyItemAddEditDialog('afterOnEditStudyItemButton','edit','编辑委托项目');
	    			    }    
	    			});
		    	}  
            }
      }else{
          $.messager.alert('提示','请选择委托项目!');
      }
    }
    //委托项目编辑后执行
   function  afterOnEditStudyItemButton(){
         var row = $('#testItemAndStudyItemTreeTable').treegrid('getSelected');
          $('#selectrowId').val(row.id);
          $('#testItemAndStudyItemTreeTable').treegrid('reload');
    	 parent.showMessager(1,'委托项目编辑成功',true,5000);
   }

    //删除供试品
    function onDelTestItemButton(){
       var row = $('#testItemAndStudyItemTreeTable').treegrid('getSelected');
       if(null != row ){
         	$.messager.confirm('确认对话框', '确认删除此供试品？', function(r){
				if (r){
					delTestItemlAndStudyItem(row.id);
					
				}
			});
       }else{
          $.messager.alert('提示','请选择删除的供试品!');
       }
     
    }

  	//根据供试品Id删除相应供试品
    function delTestItemlAndStudyItem(id){
	   	$.ajax({
			url:sybp()+'/tblTestItemAction_delTblTestItem.action',
			type:'post',
			data:{id:id},
			dataType:'json',
			success:function(r){
				if(r && r.success){
					 parent.showMessager(1,'供试品删除成功',true,5000);
					 $('#testItemAndStudyItemTreeTable').treegrid('reload');
				}else{
					 parent.showMessager(2,'供试品删除失败',true,5000);
				}
			}
		});
    }

    
  
	//删除委托项目
	function ondelStudyItemButton() {
		var row = $('#testItemAndStudyItemTreeTable').treegrid('getSelected');
		if (null != row && row.kind == "W") {
			$.messager.confirm('确认对话框', '确认删除此委托项目？', function(r) {
				if (r) {
					delStudyItem(row.id);
				}
			});
		} else {
			$.messager.alert('提示', '请选择删除的委托项目!');
		}
	}

	//删除委托试验
	function delStudyItem(id){
		$.ajax({
			url:sybp()+'/tblStudyItemAction_delStudyItem.action',
			type:'post',
			data:{
			 id:id
			},
			dataType:'json',
			success:function(r){
				if(r && r.success){
					 parent.showMessager(1,'委托项目删除成功',true,5000);
					 $('#testItemAndStudyItemTreeTable').treegrid('reload');
				}else{
					 parent.showMessager(2,'委托项目删除失败',true,5000);
				}
			}
		});
		
	}

	
	//合同附件删除
	function onDelteAttachment(id, attchmentName) {
		$.messager.confirm('确认', '您确认想要删除附件:' + attchmentName + ' ？', function(
				r) {
			if (r) {
				$.ajax( {
					url : sybp()
							+ '/tblContractAttachmentAction_delete.action?id='
							+ id,
					dataType : 'json',
					success : function(r) {
						if (r && r.success) {
							parent.showMessager(1, '附件删除成功', true, 5000);
							// window.location.href = sybp()+'/tblContractAction_main.action?currentId='+$('#currentId').val();
					var tab = document.getElementById("contractView");
					var row = document.getElementById('tr' + id);
					var index = row.rowIndex;//
					tab.deleteRow(index); //从table中删除

					//重新排列序号，如果没有序号，这一步省略
					var nextid;
					for (i = index; i < tab.rows.length; i++) {
						tab.rows[i].cells[0].innerHTML = '附件' + (i - 14);
						nextid = i + 1;
					}

				} else {
					parent.showMessager(2, '附件删除失败', true, 5000);
				}
			}
				});

			}
		});
	}
	//执行完添加
	function afterTestItemAddDialog() {
		parent.showMessager(1, '供试品添加成功', true, 5000);
		$('#testItemAddEditDialog').dialog('close');

		var roots = $('#testItemAndStudyItemTreeTable').treegrid('getRoots');
		if(!roots.length){
			//之前无数据
			showStudyItemMultiAddDialog(afterOnAddStudyItemButton,'添加委托项目');
			$('#testItemAndStudyItemTreeTable').treegrid('reload');
		}else{
			$('#testItemAndStudyItemTreeTable').treegrid('reload');
		}
			
	}

	//执行编辑操作
	function afterTestItemEditDialog() {
		parent.showMessager(1, '供试品编辑成功', true, 5000);
		$('#testItemAddEditDialog').dialog('close');
		$('#testItemAndStudyItemTreeTable').treegrid('reload');
	}
</script>
</head>
<body>
<!-- 合同主键 -->
<s:hidden id="currentId" name="currentId"></s:hidden>
<s:hidden id="selectrowId" name="selectrowId"></s:hidden>
<!-- 选择时行颜色改变 
<s:hidden id="selectRow" name="selectRow"></s:hidden>-->
<s:hidden id="currentContractCode" name="model.contractCode"></s:hidden>
<s:hidden id="owner" name="owner"></s:hidden>
<s:hidden id="write" name="write"></s:hidden>
<s:hidden id="contractState" name="model.contractState"></s:hidden>
<div id="layoutMainDiv" class="easyui-layout" fit="true" style="width:100%;height:100%; display:none;"> 
  	<div region="north" title="" style="height:30px;">
  		<div style=" padding-left:5px;margin-top: 1px;">
	  		<a class="easyui-linkbutton" onclick="onAddButton();"
	       				 data-options="iconCls:'icon-tableadd',plain:true" ${addContract == 0 ? 'disabled':'' }>合同登记</a>  
  		</div>
  	</div>
 	<div region="center" title="" style="overflow: hidden;">
 	 	<div id="contractTabs" class="easyui-tabs" fit="true" border="false"  style="display:none;">
			<div title="合同信息" border="false" style="overflow:hidden;">
				<!-- 工具栏（签字）开始 -->
				<div style="height:27px; padding-left:5px; padding-top:1px; border-bottom:1px solid #c9c9c9;overflow:hidden;">
						<a id="submitContractButton" class="easyui-linkbutton" plain="true"  onclick="onSubmitContractButton();"
							 	data-options="iconCls:'icon-tablego'" ${contractConfrim == 0 ? 'disabled':'' }>合同提交</a>
							 	
						<a id="editContractButton" class="easyui-linkbutton" plain="true" 
								onclick="editContract('${currentId}');" 
								data-options="iconCls:'icon-tableedit2'" ${contractEdit == 0 ? 'disabled':'' }>编辑合同</a>
						<a id="contractAttachmentButton" class="easyui-linkbutton" plain="true" 
								onclick="onContractAttachmentButton();" 
								data-options="iconCls:'icon-attach'" ${attachment == 0 ? 'disabled':'' }>合同附件</a>
						<a id="contractAgainEditButton" class="easyui-linkbutton" plain="true" 
								onclick="onAgainEditButton();" 
								data-options="iconCls:'icon-tablerefresh'" ${contractAgainEdit == 0 ? 'disabled':'' }>置为可编辑</a>
								
								<span style="color:#cdcdcd;">|</span>
								
						<a id="addTestItemButton" class="easyui-linkbutton" plain="true" 
								onclick="onAddTestItemButton();" 
									data-options="iconCls:'icon-tagblueadd'" ${testItemAdd == 0 ? 'disabled':'' }>添加供试品</a>
						<a id="editTestItemButton" class="easyui-linkbutton" plain="true" 
								onclick="onEditTestItemButton()" 
								data-options="iconCls:'icon-tagblueedit',disabled:true">编辑供试品</a>
								
						<a id="delTestItemButton" class="easyui-linkbutton" plain="true" 
								onclick="onDelTestItemButton()" 
								data-options="iconCls:'icon-tagbluedelete',disabled:true">删除供试品</a>
								
								<span style="color:#cdcdcd;">|</span>
								
						<a id="addStudyItemButton" class="easyui-linkbutton" plain="true" 
								onclick="onAddStudyItemButton();" 
								data-options="iconCls:'icon-databaseadd',disabled:true">添加委托项目</a>
								
						<a id="editStudyItemButton" class="easyui-linkbutton" plain="true" 
								onclick="onEditStudyItemButton();" 
								data-options="iconCls:'icon-databaseedit',disabled:true">编辑委托项目</a>
								
						<a id="delStudyItemButton" class="easyui-linkbutton" plain="true" 
								onclick="ondelStudyItemButton();" 
								data-options="iconCls:'icon-databasedelete',disabled:true">删除委托项目</a>
						<!-- 	
								<a id="" class="easyui-linkbutton" plain="true" 
								onclick="" 
								data-options="iconCls:'icon-tablemultiple'"></a>
								<a id="" class="easyui-linkbutton" plain="true" 
								onclick="" 
								data-options="iconCls:'icon-tableedit'"></a>
								<a id="" class="easyui-linkbutton" plain="true" 
								onclick="" 
								data-options="iconCls:'icon-shapesquare'"></a>
								<a id="" class="easyui-linkbutton" plain="true" 
								onclick="" 
								data-options="iconCls:'icon-pageadd'"></a>
								<a id="" class="easyui-linkbutton" plain="true" 
								onclick="" 
								data-options="iconCls:'icon-booklink'"></a>
								<a id="" class="easyui-linkbutton" plain="true" 
								onclick="" 
								data-options="iconCls:'icon-attach'"></a>
								<a id="" class="easyui-linkbutton" plain="true" 
								onclick="" 
								data-options="iconCls:'icon-arrowup'"></a> -->	
				</div>
				<!-- 工具栏结束 -->
				  <!-- 表格开始-->
				<div  style="margin-top:5px;margin-left:10px;height:85%;width:380px;overflow:auto;">
                <table id="contractView" class="contratView">
                    <tr><td width="60px;" >合同编号</td>
                        <td width="300px;">
                        	<s:property  value="model.contractCode"/>
                        	<input type="hidden" id="parentContractCode"  value="${model.contractCode}" />
                        </td>
                    </tr>
                    <tr><td>合同名称</td>
                        <td>
                        	<s:property value="model.contractName"/>
						</td>
                    </tr>
                    <tr><td>委托方</td>
                        <td>
							<s:property value="model.sponsorName"/>
							<input type="hidden" id="oldsponsorName"  value="${model.sponsorName}" />
						</td>
                    </tr>
                    <tr><td>地址</td>
                        <td>
                        	<s:property value="model.sponsorAddress"/>
						</td>
                    </tr>
                    <tr><td>联系人</td>
                        <td>
							<s:property value="model.sponsorLinkman"/>
						</td>
                    </tr>
                    <tr><td>电话</td>
                        <td>
							<s:property value="model.sponsorTel"/>
						</td>
                    </tr>
                    <tr><td>手机</td>
                        <td>
                        	<s:property value="model.sponsorMobile"/>
						</td>
                    </tr>
                    <tr><td>电子邮件</td>
                        <td>
							<s:property value="model.sponsorEmail"/>
						</td>
                    </tr>
                    <tr><td>传真</td>
                        <td>
							<s:property value="model.sponsorFax"/>
						</td>
                    </tr>
	                   <tr><td>报告出具方</td>
	                        <td>
								<s:property value="model.venderName"/>
								<input type="hidden" id="oldvenderNameC"  value="${model.venderName}" />
							</td>
	                    </tr>
	                    <tr><td>地址</td>
	                        <td>
	                        	<s:property value="model.venderAddress"/>
							</td>
	                    </tr>
	                    <tr><td>联系人</td>
	                        <td>
								<s:property value="model.venderLinkman"/>
							</td>
	                    </tr>
	                    <tr><td>电话</td>
	                        <td>
								<s:property value="model.venderTel"/>
							</td>
	                    </tr>
	                    <tr><td>手机</td>
	                        <td>
	                        	<s:property value="model.venderMobile"/>
							</td>
	                    </tr>
	                    <tr><td>电子邮件</td>
	                        <td>
								<s:property value="model.venderEmail"/>
							</td>
	                    </tr>
	                    <tr><td>传真</td>
	                        <td>
								<s:property value="model.venderFax"/>
							</td>
	                    </tr>
					<s:if test='model.contractPrice !=null && model.contractPrice !=""  '>
                    <tr><td>合同金额</td>
                        <td>
							<s:property value="model.contractPrice"/>&nbsp;
							<s:if test="model.priceUnit == 1">元</s:if>
							<s:if test="model.priceUnit == 4">万元</s:if>
							<s:elseif test="model.priceUnit == 2">美元</s:elseif>
							<s:elseif test="model.priceUnit == 3">欧元</s:elseif>
						</td>
                    </tr>
                    </s:if>
                    <tr><td>签订日期</td>
                        <td>
							<s:date name="model.signingDate" format="yyyy-MM-dd"/>
						</td>
                    </tr>
                    <tr><td>生效日期</td>
                        <td>
							<s:date name="model.effectiveDate" format="yyyy-MM-dd"/>
						</td>
                    </tr>
                    <tr><td>结束日期</td>
                        <td>
							<s:date name="model.finishDate" format="yyyy-MM-dd"/>
						</td>
                    </tr>
                    <tr><td>操作者</td>
                        <td>
							<s:property value="model.operator"/>
						</td>
                    </tr>
                    <tr><td>合同状态</td>
                        <td>
							<s:if test="model.contractState == 0">未提交</s:if>
							<s:elseif test="model.contractState == 1">进行中</s:elseif>
							<s:elseif test="model.contractState == 2">可编辑</s:elseif>
							<s:elseif test="model.contractState == 3">完成</s:elseif>
							<s:else>终止</s:else>
						</td>
                    </tr>
                    <tr><td>备注</td>
                        <td>
							<s:property value="model.remark"/>
						</td>
                    </tr>
                    <s:iterator value="#attachmentList" var="obj" status="st">
	                    <tr id="tr${obj.id}"><td>附件${st.count}</td>
	                        <td>
								<a href="${pageContext.request.contextPath}/tblContractAttachmentAction_download2.action?id=${obj.id}"
								    style="color: blue;"  >${obj.attachmentName}</a>
								<s:if test="%{#obj.state == 0}">
									<a href="javascript:onDelteAttachment(${obj.id},'${obj.attachmentName}');">
										<img style="vertical-align: bottom;" src="${pageContext.request.contextPath}/style/images/delete.gif"/>
									</a>
								</s:if>
							</td>
	                    </tr>
                    </s:iterator>
                </table>
            	</div>
           		 <!-- 表格结束 -->
            	<div style="position:absolute;left:400px; top:65px;height:85%;width:65%;overflow:auto;">
            	<!-- 供试品表格 -->
            		<table id="testItemAndStudyItemTreeTable" ></table>
            	</div>
			</div>
			<div title="项目进度" border="false" style="overflow: hidden;" >
			   
			</div>
			<div title="报告登记" border="false" style="overflow: hidden;" >
			</div>
			<div title="付款记录" border="false" style="overflow: hidden;" >
			<!--   <iframe name="paymentRecord" src="${pageContext.request.contextPath}/tblContractAction_paymentRecord.action" width=100% height=100% frameborder=no border=0 scrolling=no ></iframe>--> 
			</div>
		</div>
 	 </div>
 	  <%@ include file="/WEB-INF/jsp/tblContractAction/contractAddEdit.jspf"%>
 	  <%@ include file="/WEB-INF/jsp/tblContractAction/studyItemAddEdit.jspf"%>
 	  <%@ include file="/WEB-INF/jsp/tblContractAction/studyItemMultiAdd.jspf"%>
 	  <%@ include file="/WEB-INF/jsp/tblContractAction/studyTypeMultiSelect.jspf"%>
 	  <%@ include file="/WEB-INF/jsp/tblCustomerAction/selectCustomer.jspf"%>
 	  <%@include file="/WEB-INF/jsp/tblCustomerAction/customerAddEdit.jspf" %>
 	  <%@include file="/WEB-INF/jsp/tblContractAction/testItemAddorEdit.jspf" %>
 	  <%@include file="/WEB-INF/jsp/tblContractAction/studyTypeSelect.jspf" %>
 	  <%@include file="/WEB-INF/jsp/tblContractAction/attachmentAddEdit.jspf" %>
 	  <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
</div>
</body>
</html>
