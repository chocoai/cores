<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/tblAnimal.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblAnimal.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/commCheck.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/ajaxfileupload.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblTiprpAppInd.js" charset="utf-8"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/tblTiprpAppInd.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblWeight.js" charset="utf-8"></script>
<script type="text/javascript">
    
	var tiprpAppInd;
	var tiprpAppData;
	var tiprpAppRecDt;
	var electiondate;
	var studyNoPara=$('#studyNoPara').val();
	var tiprpApprecdtdate;
	var AppInd;
	var usetblWeighInd;
	$(function(){
		tiprpAppInd=$('#tiprpAppInd').datagrid({
			url : sybp()+"/tblTiprpAppIndAction_selectAppIndList.action?studyNoPara="+$('#studyNoPara').val(),
			title:'',
			height: 445,
			width:280,
			iconCls:'',//'icon-save',
			//pagination:true,//下面状态栏
			pageSize:100,
			pageList:[50,100],
			//fit:true,
			fitColumns:true,//不出现横向滚动条
			nowarp:  false,//单元格里自动换行
			//border:false,
			//idField:'id', //pk
			singleSelect:true,
			//sortName:'aniSerialNum',//排序字段
			//sortOrder:'desc',//排序方法
			columns :[[{
				title : '申请序号',
				field : 'appSn',
				width : 60,
				align :'center'
			},{
				title :'申请时间',
				field :'appTime',
				width :90,
				align :'center'
			},{
				title :'状态',
				field :'appStatus',
				width :100,
				align:'center',
				formatter: function(value,row,index){
				if ( value == 1){
					return "已提交";
				}else if(value == -1){
                     return "已撤销";
				}else if(value == 2){
                     return "已受理";
				}else if(value==3){
                     return "待复核";
				}else if(value == 4){
					return "配制完成";
					}
			 }
			}]],
			onClickRow :function(rowIndex, rowData){
			var rows =$('#tiprpAppInd').datagrid('getRows');
			var AppInd1 = $('#tiprpAppInd').datagrid("getRows").length
			var appStatus = rows[rowIndex].appStatus;
		    if(appStatus == 1){
		    	AppIndrescindSignButton=$('#AppIndrescindSignButton').linkbutton('enable');
			}else{
				AppIndrescindSignButton=$('#AppIndrescindSignButton').linkbutton('disable');
			}
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow',rowIndex);	
				$('#AppInd').val(rowData.appSn);	
				tiprpAppData=$('#tiprpAppData').datagrid({
					url : sybp()+"/tblTiprpAppIndAction_selectAppDataList.action?studyNoPara="+$('#studyNoPara').val()+"&AppInd="+rowData.appSn,
					title:'',
					height: 445,
					width:360,
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
						title : '动物编号',
						field : 'aniCode',
						width : 100,
					},{
						title :'动物体重',
						field :'aniWeight',
						width :100,
						align:'right'
					},{
						title :'',
						field :'weighUnit',
						width :35,
						align:'left'
					},{
						title :'供试品重量',
						field :'smplWeight',
						width :100,
						align:'right'
					},{
						title :'',
						field :'smplUnit',
						width :35,
						align:'left'
					}
					,{
						title :'胶囊数量',
						field :'capsNum',
						width :100,
						align:'center'
					}]],
					onClickRow :function(rowIndex, rowData){
						$(this).datagrid('unselectAll');
						$(this).datagrid('selectRow',rowIndex);
						$('#rowIndex').val(rowIndex);	
					},
					
					
				}); //end 

				tiprpAppRecDt=$('#tiprpAppRecDt').datagrid({
					url : sybp()+"/tblTiprpAppIndAction_selectAppRecDt.action?studyNoPara="+$('#studyNoPara').val()+"&AppInd="+rowData.appSn,
					title:'',
					height: 445,
					width:200,
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
						title : '领用日期',
						field : 'showtime',
						width : 100,
					},{
						title :'状态',
						field :'prpStatus',
						width :100,
						align:'center',
						formatter: function(value,row,index){
						 if(value == 4){
							return "配制完成";
						 }else {
							return "";
					     }
					 }
					}]],
					onClickRow :function(rowIndex, rowData){
						$(this).datagrid('unselectAll');
						$(this).datagrid('selectRow',rowIndex);
						
					},

				   
				}); //end 

				
				
			},
			 onDblClickRow :function(rowIndex, rowData){
				
			},
			onLoadSuccess:function(data){
				var rows =$('#tiprpAppInd').datagrid('getRows');
				var AppInd1 = $('#tiprpAppInd').datagrid("getRows").length
				var appStatus = rows[AppInd1-1].appStatus;
			    if(appStatus == 1){
			    	AppIndrescindSignButton=$('#AppIndrescindSignButton').linkbutton('enable');
				}else{
					AppIndrescindSignButton=$('#AppIndrescindSignButton').linkbutton('disable');
				}
				
				$('#AppInd').val(AppInd1);
				$('#tiprpAppInd').datagrid('selectRow',AppInd1-1);
				tiprpAppData=$('#tiprpAppData').datagrid({
					url : sybp()+"/tblTiprpAppIndAction_selectAppDataList.action?studyNoPara="+$('#studyNoPara').val()+"&AppInd="+AppInd1 ,
					title:'',
					height: 445,
					width:360,
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
						title : '动物编号',
						field : 'aniCode',
						width : 100,
					},{
						title :'动物体重',
						field :'aniWeight',
						width :100,
						align:'right',
					},{
						title :'',
						field :'weighUnit',
						width :35,
						align:'left'
					},{
						title :'供试品重量',
						field :'smplWeight',
						width :100,
						align:'right',
						halign:'center'
					},{
						title :'',
						field :'smplUnit',
						width :35,
						align:'left'
					}
					,{
						title :'胶囊数量',
						field :'capsNum',
						width :100,
						align:'center'
					}]],
					onClickRow :function(rowIndex, rowData){
						$(this).datagrid('unselectAll');
						$(this).datagrid('selectRow',rowIndex);
						$('#rowIndex').val(rowIndex);	
					},
					
					
				}); //end 


				tiprpAppRecDt=$('#tiprpAppRecDt').datagrid({
					url : sybp()+"/tblTiprpAppIndAction_selectAppRecDt.action?studyNoPara="+$('#studyNoPara').val()+"&AppInd="+AppInd1,
					title:'',
					height: 445,
					width:200,
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
						title : '领用日期',
						field : 'showtime',
						width : 100,
					},{
						title :'状态',
						field :'prpStatus',
						width :100,
						align:'center',
						formatter: function(value,row,index){
						 if(value == 4){
							return "配制完成";
						 }else {
							return "";
					     }
					 }
					}]],
					onClickRow :function(rowIndex, rowData){
						$(this).datagrid('unselectAll');
						$(this).datagrid('selectRow',rowIndex);
						
					},

				   
				}); //end 
				
				
			}
		}); //end 
		
		

		
		//表头居中
		$('.datagrid-header div').css('textAlign','center');

		addRexDttimerows();
		usetblWeighInd();
		selectAdmin();
		
		
        //申请时间
		electiondat=$('#electiondate').datebox({    
		    onSelect:function(date){
			 $.ajax({
					url:sybp()+'/tblTiprpAppIndAction_adddate.action',
					type:'post',
					cache:false,
					data:{
					  endTime:$('#electiondate').datebox('getValue'),
					},
					dataType:'json',
					success:function(r){
						 if(r && r.success){
							  addRexDttimerows();
							  $('#electiondate').datebox('setValue', r.nexttime);
							 
					      }else{
					    	  $.messager.alert('提示','请检查重复录入的日期');
						  }
					}
					});
			} 
		});  
       
	   var tblTiprpAppData;
       var company;
       var company1;

 	   $('#SmplUnit').combobox({
		    onSelect: function(param){
		    var peo = $('#SmplUnit').combobox('getValue');
		    var peo1 = $('#DevType').combobox('getValue');
               if(peo1==1){
            	   company = $("#Company").val(peo) ;
                   }else{
                   company = $("#Company").val("%"); 
                   }
			   
			   company1 = $("#Company1").val(peo) ;
			 }
		
		});

	   $('#DevType').combobox({
		    onSelect: function(param){
		    var peo = $('#DevType').combobox('getValue');
		    if(peo == 2){
			   company = $("#Company").val("%"); 
		      }else{
		    	   var peo2 = $('#SmplUnit').combobox('getValue');
				   company = $("#Company").val(peo2) ;
			   }
			 }
		
		});
		
	   var Fixednumber;
	   $('#Automatic').combobox({
		    onSelect: function(param){
		    var peo = $('#Automatic').combobox('getValue');
		    if(peo == 2){
		    	document.getElementById('Fixednumber1').style.display="block";
		       }else{
		        document.getElementById('Fixednumber1').style.display="none";
			   }
			 }
		
		});

	   //剂量设置
	   var dosegroup;
	   dosegroup=$('#dosegroup').datagrid({
					url : sybp()+"/tblTiprpAppIndAction_SelectDoseSettingList.action?studyNoPara="+$('#studyNoPara').val(),
					title:'',
					height: 260,
					width:400,
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
						field:'ck',
						checkbox:true
					},
					{
						title : '剂量组编号',
						field : 'dosageNum',
						width : 40
					},{
						title : '剂量组说明',
						field : 'dosageDesc',
						width : 50
					},{
						title :'剂量',
						field :'dosage',
						width :40,
						align :'right',
					},{
						title :'',
						field :'dosageUnit',
						width :30,
						align :'left'
					},{
						title :'动物数量',
						field :'remarks',
						width :40
					}]],
					//单击选择
					onClickRow :function(rowIndex, rowData){
		                $(this).datagrid('selectRow',rowIndex);		               
					},
					//双击取消
					onDblClickRow :function(rowIndex, rowData){
						$(this).datagrid('unselectRow',rowIndex);		
					},
					
				}); //end 
				
	    //显示整个布局
		$('#tblTiprpAppIndActionMainDiv').css('display','');  
		MemberReadOnly();       
	   
	});

	function toaddWeighInd(){
		parent.addoneNewWeighIndTab($('#studyNoPara').val());
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
    <div id="tblTiprpAppIndActionMainDiv" style="width:100%;height:100%; display:none;">
    <s:hidden id="left_member" name="left_member"></s:hidden>
    <s:hidden id="studyNoPara" name="studyNoPara"></s:hidden>
    <s:hidden id="AppInd" name="AppInd"></s:hidden>
    <s:hidden id="RecDt" name="RecDt"></s:hidden>
    <s:hidden id="size" name="size"></s:hidden>
   <a id="aclick"  href="${pageContext.request.contextPath}/tblStudyPlanAction_studyPlanMain.action?studyNoPara=${studyNoPara}&toWhere=animalWeight" target="main"></a>
	<div id="toolbar"  style="display:none;">
		<a id="addClinicalTestReqButton"  class="easyui-linkbutton" onclick="onNewTblTiprpAppIndButton();" data-options="iconCls:'icon-add',plain:true">新增申请</a>
	    <a id="AppIndrescindSignButton" class="easyui-linkbutton" onclick="AppIndrescindSign();" data-options="iconCls:'icon-redo',plain:true">撤销</a>
	</div>
	
	
	<div class="easyui-layout" fit="true" border="false" >
		<div region="west" border="false" style="width:300px;">
			<table id="tiprpAppInd" ></table>
		</div>
		
		<div region="center" border="false" style="width:300px;">
			<div class="easyui-layout" fit="true" border="false" >
				<div region="center" border="false">
					<table id="tiprpAppData" ></table>
				</div>

			</div>
		</div>
		
		<div region="east" border="false" style="width:480px;">
			<table id="tiprpAppRecDt"></table>
		</div>
	</div>
	
     <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
	 <%@include file="/WEB-INF/jsp/tblTiprpAppIndAction/newTiprpAppInd.jspf" %>
	 <%@include file="/WEB-INF/jsp/tblTiprpAppIndAction/newTblTiprpAppRecDt.jspf" %>
	 <%@include file="/WEB-INF/jsp/tblTiprpAppIndAction/AppWeightInd.jspf" %>
	 <!-- 剂量设置分组 -->
	 <%@include file="/WEB-INF/jsp/tblTiprpAppIndAction/Selectdosegroup.jspf" %>
	 </div>
</body>
</html>
