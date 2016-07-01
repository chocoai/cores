<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>客户信息管理</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf" %>

<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblCustomerAction/selectCustomer.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblCustomerAction/customerAddEdit.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/customerAddEdit.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/contractAddEdit.css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/tblContractAction/contractAddEdit.js"></script>
<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 30
	var tableWidth;
	$(function(){
		tableHeight = document.body.clientHeight - 60;
		tableWidth  = document.body.clientWidth;
		//初始化datagrid
		$('#tblCustomerTable').datagrid({
			url : sybp()+'/tblCustomerAction_loadList.action?regionId='+$('#regionId').val()+'&cuid='+$('#cuid').val(),
			title:'',
			height:tableHeight,
			width:tableWidth,
			nowarp:  false,//单元格里自动换行
			singleSelect:true,
			fitColumns:false,
			pagination:true,		//分页
			sortName:'id',
			columns :[[{
				title:'id',
				field:'id',
				width:150,
				halign:'center',
				align:'center',
				hidden:true
			},{
				title:'省份/自治区',
				field:'region',
				width:80,
				halign:'center',
				align:'left'
				
			},{
				title:'市区',
				field:'region1',
				width:80,
				halign:'center',
				align:'left'
				
			},{
				title:'客户名称',
				field:'customerName',
				width:200,
				halign:'center',
				align:'left'
			},{
				title:'联系人',
				field:'linkman',
				width:100,
				halign:'center',
				align:'left'
			},{
				title:'地址',
				field:'address',
				width:300,
				halign:'center',
				align:'left'
				
			},{
				title:'电话',
				field:'tel',
				width:100,
				halign:'center',
				align:'left'
			},{
				title:'手机',
				field:'mobile',
				width:100,
				halign:'center',
				align:'center'
			},{
				title:'电子邮件',
				field:'email',
				width:150,
				halign:'center',
				align:'left'
		    },{
				title:'网址',
				field:'http',
				width:100,
				halign:'center',
				align:'left'
		    },{
				title:'传真',
				field:'fax',
				width:100,
				halign:'center',
				align:'left'
		    },{
				title:'邮编',
				field:'postalCode',
				width:60,
				halign:'center',
				align:'center'
		    },{
				title:'主要产品类型',
				field:'tiCode',
				width:80,
				halign:'center',
				align:'left',
				formatter: function(value,row,index){
				if ( value == "01" ){
					return "医药";
				}else if(value=="02"){
                    return "农药";
				}else{
					return "化学品";
				}
			}},{
				title:'有无合同',
				field:'contractMark',
				width:100,
				hidden:true,
				halign:'center',
				align:'center'
			},{
				title:'能否查看合同',
				field:'viewMark',
				width:100,
				hidden:true,
				halign:'center',
				align:'center'
			}]],
			onSelect:function(rowIndex, rowData){
			    var falg =   $('#addContract').val();
			    if( falg == 1){ 
			        $('#editCustomerButton').linkbutton('enable');
			        if(rowData.contractMark == 0){
			          $('#deleteCustomerButton').linkbutton('disable');
			        }else{
			          $('#deleteCustomerButton').linkbutton('enable');
			        }
			       
                   
                    $('#addContractButton').linkbutton('enable');
                }else{
                    $('#editCustomerButton').linkbutton('disable');
			        $('#deleteCustomerButton').linkbutton('disable');
                    $('#addContractButton').linkbutton('disable');
                }
			    if(rowData.contractMark==0 && rowData.viewMark==0){
			      $('#selectContractButton').linkbutton('enable');
			    }else{
			      $('#selectContractButton').linkbutton('disable'); 
			    }
			    
			},
			onLoadSuccess:function(data){
			   var falg =   $('#addContract').val();
			   $('#editCustomerButton').linkbutton('disable');
			   $('#deleteCustomerButton').linkbutton('disable');
			   $('#selectContractButton').linkbutton('disable');
			   $('#addContractButton').linkbutton('disable');
			   if( falg == 0){
			   
			   }else{
			        //是否有默认选中的,
				    var flag = false;
				 	if(data.cuid){
						for(var i = 0;i<data.rows.length;i++){
							if(data.rows[i].id == data.cuid){
								flag = true;
					        	$('#tblCustomerTable').datagrid('selectRow',i);
					        	parent.window.frames["left"].frames["left0"].updateucid();
					     	}
					  	}
				 	}
				 	//如果没有默认选中的
				 	if(!flag){
					 	if(data && data.rows && data.rows.length>0){
					 		$('#tblCustomerTable').datagrid('selectRow',0);
						}
					}
			   
			   }
			  
			},
			toolbar:'#toolbar'
	   	}); //end datagrid
        var pager = $('#tblCustomerTable').datagrid('getPager');    // get the pager of datagrid
	     pager.pagination({
		        showPageList:false,
		        showRefresh:false,
		        loading:true,
		        displayMsg:	' 共 &nbsp;&nbsp;{total}&nbsp;&nbsp;条记录',
		        beforePageText:'',
		        afterPageText:'',
		    });
	 	 //搜索框
	    $('#searchBox').searchbox({ 
	 	    height:19,
	 	    width:450,
	 		searcher:function(value,name){ 
	 		     searchForTable(value);
	 		}, 
	 		prompt:'模糊查询,请输入客户名称、联系人、电话或手机' 
	 	});

		//显示整个布局
		$('#layoutMainDiv').css('display','');   
    });//匿名函数结束

    //查询事件
    function searchForTable(value){
        if(value){
            $.ajax({
                url:sybp()+'/tblCustomerAction_seach.action',
                type:'post',
                data:{
                    	content:value
                  	},
                dataType:'json',
                success:function(r){
                        	if(r){
                        		$('#tblCustomerTable').datagrid('loadData',r);
                            }
                    	}
            });

        }else{
        	$('#tblCustomerTable').datagrid('reload'); 
        }
    }
   
	
	//添加按钮事件
    function onAddButton(){
    	showCustomerAddEditDialog('afterAddDialog','add','添加客户信息');
    }
    
	//	添加Dialog后事件
    function afterAddDialog(){
       var cid = $('#regionId').val();
       var cuid = $('#cuid').val();
      // parent.parent.parent.afterAddCustomer(cid,cuid);
       parent.window.frames["left"].frames["left0"].afterAddCustomer(cid,cuid);
       parent.showMessager(1,'添加成功',true,5000);
      
    }
    //wan 编辑
    function onEditButton(){
         var row= $('#tblCustomerTable').datagrid('getSelected');
        if(row != null ){
          showCustomerAddEditDialog('afterEditDialog','edit','编辑客户信息');
        }else{
           $.messager.alert('提示','请选择编辑的客户!');
        }
    }

     function afterEditDialog(){
        var cid = $('#regionId').val();
        var cuid = $('#cuid').val();
        parent.showMessager(1,'编辑成功',true,5000);
		 parent.window.frames["left"].frames["left0"].afterAddCustomer(cid,cuid);
         //$('#tblCustomerTable').datagrid('reload');
     }

    function onDeleteButton(){
    	  var row= $('#tblCustomerTable').datagrid('getSelected');
          if(row != null ){
        	  qm_showQianmingDialog('afterDelFunction');
          }else{
             $.messager.alert('提示','请选择删除的客户!');
          }
   }
       function afterDelFunction(){
    	     var row= $('#tblCustomerTable').datagrid('getSelected');
	    	$.ajax({
				url:sybp()+'/tblCustomerAction_delete.action?id='+row.id,
				type:'post',
				dataType:'json',
				success:function(r){
	    		  if(r && r.success){
	    			  parent.showMessager(1,'删除成功',true,5000);
	    		       var data = $('#tblCustomerTable').datagrid('getRows');
	    		       	  for(var i = 0;i<data.length;i++){
						    if(data[i].id == row.id){
						        $('#tblCustomerTable').datagrid('deleteRow',i);
						     }
						  }
		           }else{
		              $.messager.alert('提示','与数据库交互异常!');
		           }
	    	    }  
		    });
       }

	/**查看合同*/
	function onViewContract(){
		 var row= $('#tblCustomerTable').datagrid('getSelected');
        if(row != null ){
            $.ajax({
                url:sybp()+'/tblContractAction_checkCustomer.action?sponsorId='+row.id,
                dataType:'json',
                success:function(r){
					if(r && r.success){
			            parent.window.frames["left"].frames["left1"].updateNameCombobox(row.id,row.customerName);
					}else{
						$.messager.alert('提示',r.msg);
					}
                }
            });
        }else{
           $.messager.alert('提示','请选择编辑的客户!');
        }
	}
	/**添加合同*/
	function onAddContractButton(){
		 var row= $('#tblCustomerTable').datagrid('getSelected');
	        if(row != null ){
	                fullEditData();
	            	showContractAddEditDialog('afterAddContractDialog','add','登记合同');
	            	$('#sponsorId').val(row.id);
	                $('#sponsorName').val(row.customerName);
	                $('#venderId').val(row.id);
	                $('#venderNameC').val(row.customerName);
	                getNextContractPoolNum();
	        }else{
	           $.messager.alert('提示','请选择编辑的客户!');
	        }
	}
    
   	//合同登记(add)Dialog后事件
       function afterAddContractDialog(){
          parent.showMessager(1,'合同登记成功',true,5000);
          $('#selectContractButton').linkbutton('enable'); 
          parent.window.frames["left"].frames["left1"].updateCodeCombobox($('#contractCode0').val());
       }
</script>
</head>
<body>
<!-- 地区id -->
<s:hidden id="regionId" name="regionId"></s:hidden>
<!-- 第一次查找的pid -->
<s:hidden id="level1pid" name="level1pid"></s:hidden>
<!-- 第二次查找的pid -->
<s:hidden id="level2pid" name="level2pid"></s:hidden>
<!-- 添加的合同编号 -->
<s:hidden id="contractCode0" name="contractCode0"></s:hidden>
<s:hidden id="onchange" name="onchange"></s:hidden>
<!-- 是否只读 -->
<s:hidden id="addContract" name="addContract"></s:hidden>
<div id="layoutMainDiv" class="easyui-layout" fit="true" style="width:100%;height:100%; display:none;"> 
    <div region="north" title="" style="height:30px;">
  		<div style=" padding-left:5px;margin-top: 1px;">
	  		<a class="easyui-linkbutton" onclick="onAddButton();"
	       				 data-options="iconCls:'icon-groupadd',plain:true"  ${addContract == 0 ? 'disabled':'' }  >添加客户</a>  
  		</div>
  	</div>
 	<div region="center" title="" style="overflow: hidden;">
 	 	<div  class="easyui-tabs" fit="true" border="false">
			<div title="客户信息" border="false" style="overflow: auto;">
				<table id="tblCustomerTable" ></table>
            </div>
		</div>
        <div id="toolbar">
        <!-- <a id="addCustomerButton" class="easyui-linkbutton" onclick="onAddButton();" data-options="iconCls:'icon-add',plain:true">添加</a> -->
			
			<a id="editCustomerButton" class="easyui-linkbutton" onclick="onEditButton();" data-options="iconCls:'icon-groupedit',plain:true" disabled="true">编辑</a>
			<a id="deleteCustomerButton" class="easyui-linkbutton" onclick="onDeleteButton()" data-options="iconCls:'icon-groupdelete',plain:true,disabled:true">删除</a>
			<a id="selectContractButton" class="easyui-linkbutton" onclick="onViewContract();" data-options="iconCls:'icon-tablemultiple',plain:true,disabled:true">查看合同</a>
			<a id="addContractButton" class="easyui-linkbutton" onclick="onAddContractButton();" data-options="iconCls:'icon-tableadd',plain:true,disabled:true">添加合同</a>
			<span style="position:absolute; left:350px;top:5px;">
				<input id="searchBox" ></input>
			</span>
		</div>
 	</div>
 	<%@include file="/WEB-INF/jsp/tblCustomerAction/customerAddEdit.jspf" %>
 	<%@include file="/WEB-INF/jsp/tblCustomerAction/selectCustomer.jspf" %>
 	<%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
 	 <%@ include file="/WEB-INF/jsp/tblContractAction/contractAddEdit.jspf"%>
</div>
</body>
</html>
