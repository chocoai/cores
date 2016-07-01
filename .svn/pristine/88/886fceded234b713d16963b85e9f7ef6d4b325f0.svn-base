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
<script type="text/javascript">
	var tblWeighInd;
	var tblWeighData;
	var usetblWeighInd;
	var studyNoPara=$('#studyNoPara').val();
	var AppInd;

	var addClinicalTestReqButton;
    var editClinicalTestReqButton;
    var checkClinicalTestReqButton;

	$(function(){
		tblWeighInd=$('#tblWeighInd').datagrid({
			url : sybp()+"/tblWeightIndAction_selectWeighIndList.action?studyNoPara="+$('#studyNoPara').val(),
			title:'',
			height: 445,
			width:480,
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
				title : '称重序号',
				field : 'weighSn',
				width : 60,
				align :'center',
			},{
				title :'称重时间',
				field :'weighDate1',
				width :100,
			},{
				title :'操作',
				field :'audit',
				width :148,
				align:'center',
				formatter: function(value,row,index){
				if ( !value ){
					return "";
				}else{
                    return value;
				}
			 }
			},{
				title :'复核',
				field :'review',
				width :148,
				align:'center',
				formatter: function(value,row,index){
				if ( !value){
					return "";
				}else{
                    return value;
				}
			 }
			}]],
			onClickRow :function(rowIndex, rowData){
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow',rowIndex);	
				 var	member = $('#left_member').val();
				 var rows= $('#tblWeighInd').datagrid('getRows');
				 var index = rows.length-1;
				 var review = $('#tblWeighInd').datagrid('getRows')[index].review;
				 var center1 = $('#tblWeighInd').datagrid('getRows')[index].audit;
				 var review1 = $('#tblWeighInd').datagrid('getRows')[rowIndex].review;
				 var center = $('#tblWeighInd').datagrid('getRows')[rowIndex].audit;
				 if( member == ""){
					    if(center == ""){
					    	addClinicalTestReqButton=$('#addClinicalTestReqButton').linkbutton('disable');
					    	editClinicalTestReqButton=$('#editClinicalTestReqButton').linkbutton('enable');
					    	checkClinicalTestReqButton=$('#checkClinicalTestReqButton').linkbutton('disable');
						}else{
							if(review1 == ""){
								addClinicalTestReqButton=$('#addClinicalTestReqButton').linkbutton('disable');
						    	editClinicalTestReqButton=$('#editClinicalTestReqButton').linkbutton('disable');
						    	checkClinicalTestReqButton=$('#checkClinicalTestReqButton').linkbutton('enable');
							}else{
								
						    	editClinicalTestReqButton=$('#editClinicalTestReqButton').linkbutton('disable');
						    	checkClinicalTestReqButton=$('#checkClinicalTestReqButton').linkbutton('disable');
						    	if(review =="" || center1 =="" ){
						    		addClinicalTestReqButton=$('#addClinicalTestReqButton').linkbutton('disable');
								}else{
									addClinicalTestReqButton=$('#addClinicalTestReqButton').linkbutton('enable');
								}
							}
							
						 }
						 
					    }else{
					    	if(center == ""){
						    	addClinicalTestReqButton=$('#addClinicalTestReqButton').linkbutton('disable');
						    	editClinicalTestReqButton=$('#editClinicalTestReqButton').linkbutton('disable');
						    	checkClinicalTestReqButton=$('#checkClinicalTestReqButton').linkbutton('disable');
							}else{
								if(review1 == ""){
									addClinicalTestReqButton=$('#addClinicalTestReqButton').linkbutton('disable');
							    	editClinicalTestReqButton=$('#editClinicalTestReqButton').linkbutton('disable');
							    	checkClinicalTestReqButton=$('#checkClinicalTestReqButton').linkbutton('enable');
								}else{
									addClinicalTestReqButton=$('#addClinicalTestReqButton').linkbutton('disable');
							    	editClinicalTestReqButton=$('#editClinicalTestReqButton').linkbutton('disable');
							    	checkClinicalTestReqButton=$('#checkClinicalTestReqButton').linkbutton('disable');
								}
								
							 }
						}
					
				$('#AppInd').val(rowData.weighSn);	
				tblWeighData=$('#tblWeighData').datagrid({
					url : sybp()+"/tblWeightIndAction_selectTbLWeighDataList.action?studyNoPara="+$('#studyNoPara').val()+"&AppInd="+rowData.weighSn,
					title:'',
					height: 445,
					width:220,
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
						width : 70,
					},{
						title :'动物体重',
						field :'weight',
						width :70,
						align:'right',
					},{
						title :'',
						field :'unit',
						width :35,
						align:'left'
					}]],
					onClickRow :function(rowIndex, rowData){
						$(this).datagrid('unselectAll');
						$(this).datagrid('selectRow',rowIndex);
						$('#rowIndex').val(rowIndex);	
					},
					
					
				}); //end 
				
			},
			onLoadSuccess:function(data){
		        var rows= $('#tblWeighInd').datagrid('getRows');
				var index = rows.length-1;
			    var review = $('#tblWeighInd').datagrid('getRows')[index].review;
			    var center = $('#tblWeighInd').datagrid('getRows')[index].audit;
			    var	member = $('#left_member').val();
			    if( member == ""){
			    if(center == ""){
			    	addClinicalTestReqButton=$('#addClinicalTestReqButton').linkbutton('disable');
			    	editClinicalTestReqButton=$('#editClinicalTestReqButton').linkbutton('enable');
			    	checkClinicalTestReqButton=$('#checkClinicalTestReqButton').linkbutton('disable');
				}else{
					if(review == ""){
						addClinicalTestReqButton=$('#addClinicalTestReqButton').linkbutton('disable');
				    	editClinicalTestReqButton=$('#editClinicalTestReqButton').linkbutton('disable');
				    	checkClinicalTestReqButton=$('#checkClinicalTestReqButton').linkbutton('enable');
					}else{
						addClinicalTestReqButton=$('#addClinicalTestReqButton').linkbutton('enable');
				    	editClinicalTestReqButton=$('#editClinicalTestReqButton').linkbutton('disable');
				    	checkClinicalTestReqButton=$('#checkClinicalTestReqButton').linkbutton('disable');
					}
					
				 }
			    }else{
			    	if(center == ""){
				    	addClinicalTestReqButton=$('#addClinicalTestReqButton').linkbutton('disable');
				    	editClinicalTestReqButton=$('#editClinicalTestReqButton').linkbutton('disable');
				    	checkClinicalTestReqButton=$('#checkClinicalTestReqButton').linkbutton('disable');
					}else{
						if(review == ""){
							addClinicalTestReqButton=$('#addClinicalTestReqButton').linkbutton('disable');
					    	editClinicalTestReqButton=$('#editClinicalTestReqButton').linkbutton('disable');
					    	checkClinicalTestReqButton=$('#checkClinicalTestReqButton').linkbutton('enable');
						}else{
							addClinicalTestReqButton=$('#addClinicalTestReqButton').linkbutton('disable');
					    	editClinicalTestReqButton=$('#editClinicalTestReqButton').linkbutton('disable');
					    	checkClinicalTestReqButton=$('#checkClinicalTestReqButton').linkbutton('disable');
						}
						
					 }
				}
		    
				
				var AppInd1 = $('#tblWeighInd').datagrid("getRows").length
				$('#tblWeighInd').datagrid('selectRow',AppInd1-1);

				 tblWeighData=$('#tblWeighData').datagrid({
						url : sybp()+"/tblWeightIndAction_selectTbLWeighDataList.action?studyNoPara="+$('#studyNoPara').val()+"&AppInd="+AppInd1,
						title:'',
						height: 445,
						width:220,
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
							width : 70,
						},{
							title :'动物体重',
							field :'weight',
							width :70,
							align:'right',
						},{
							title :'',
							field :'unit',
							width :35,
							align:'left'
						}]],
						onClickRow :function(rowIndex, rowData){
							$(this).datagrid('unselectAll');
							$(this).datagrid('selectRow',rowIndex);
							$('#rowIndex').val(rowIndex);	
						},
						
						
					}); //end 
					    
				}

                
			    
			
		}); //end 
		
		
		usetblWeighInd();
		
		//表头居中
		$('.datagrid-header div').css('textAlign','center');

         
		if($('#sign').val()==1){
			newanimalweight();
		}

		//显示整个布局
		$('#WeighIndMainDiv').css('display','');  
		 $('#toolbar').css('display',''); 
		 showdatagrid();
	});
	

	function showdatagrid(){
		var rows= $('#tblWeighInd').datagrid('getRows');
		var	member = $('#left_member').val();
		if(member == ""){
		if(rows== ""){
			addClinicalTestReqButton=$('#addClinicalTestReqButton').linkbutton('enable');
	    	editClinicalTestReqButton=$('#editClinicalTestReqButton').linkbutton('disable');
	    	checkClinicalTestReqButton=$('#checkClinicalTestReqButton').linkbutton('disable');
		}
		}else{
			addClinicalTestReqButton=$('#addClinicalTestReqButton').linkbutton('disable');
	    	editClinicalTestReqButton=$('#editClinicalTestReqButton').linkbutton('disable');
	    	checkClinicalTestReqButton=$('#checkClinicalTestReqButton').linkbutton('disable');
		}
	}
	function toaddWeighInd(){
		window.location.href= sybp()+"/tblWeightIndAction_toadd.action?studyNoPara="+$('#studyNoPara').val();
	}	

    function newanimalweight(){
    	$.ajax({
    		url:sybp()+'/tblWeightIndAction_tonewanimalweight.action',
    		type:'post',
    		cache:false,
    		data:{
    			studyNoPara:$('#studyNoPara').val(),
    		},
    		dataType:'json',
    		success:function(r){
        		if(r && r.sign == 1){
        			$.messager.alert('提示','无实验动物请检查!');
            		}else{
    			if(r && r.success){
    				$('#tblWeighIndStudyNo').val(r.StudyNo);
    				$('#WeighSn').val(r.WeighSn);
    				$('#WeightUnit').combobox('setValue',r.WeightUnit);
    				$('#WeighDate').datebox('setValue', r.NowDate);
    				$('#tblWeighIndDialog').dialog('open');
    			    $('#tblWeighIndDialog2').dialog('open');
    			    
    			}else{
    				window.location.href= sybp()+"/tblWeightIndAction_toadd.action?studyNoPara="+$('#studyNoPara').val();
    				$('#isAnimalWegihtES').val(r.isAnimalWegihtES);
    				$('#isAnimalWegihtFY').val(r.isAnimalWegihtFY);
        		}
        	  }
    		}
    	});		
    }

    function tblWeighIndDialogSaveButton(){
    	$.ajax({
    		url:sybp()+'/tblWeightIndAction_toSavetblWeighInd.action',
    		type:'post',
    		cache:false,
    		data:$('#tblWeighIndForm').serialize(),
    		dataType:'json',
    		success:function(r){
    			if(r && r.success ){
    				window.location.href= sybp()+"/tblWeightIndAction_toadd.action?studyNoPara="+r.StudyNo;
    			}else if(r.AniCode){
                   $.messager.alert('提示','请先设置动物编号','info');
        		}else{
    				$.messager.alert('提示','请检查输入称重日期   ');
                }
    		}
    	});

    }

</script>
</head>
<body>
    <div id="WeighIndMainDiv"  style="width:100%;height:100%; display:none;">
    <s:hidden id="left_member" name="left_member"></s:hidden>
    <s:hidden id="studyNoPara" name="studyNoPara"></s:hidden>
    <s:hidden id="AppInd" name="AppInd"></s:hidden>
    <s:hidden id="RecDt" name="RecDt"></s:hidden>
    <s:hidden id="size" name="size"></s:hidden>
    <s:hidden id="sign" name="sign"></s:hidden>
   <a id="aclick"  href="${pageContext.request.contextPath}/tblStudyPlanAction_studyPlanMain.action?studyNoPara=${studyNoPara}&toWhere=Weight1" target="main"></a>
	<div id="toolbar" style="display:none;">
		<a id="addClinicalTestReqButton"  class="easyui-linkbutton" onclick="newanimalweight();" data-options="iconCls:'icon-add',plain:true">称重记录</a>
		<a id="editClinicalTestReqButton"  class="easyui-linkbutton" onclick="newanimalweight();" data-options="iconCls:'icon-edit',plain:true">编辑</a>
		<a id="checkClinicalTestReqButton"  class="easyui-linkbutton" onclick="newanimalweight();" data-options="iconCls:'icon-ok',plain:true">复核</a>
	</div>
	
	
	<div class="easyui-layout" fit="true" border="false" >
		<div region="west" border="false" style="width:500px;">
			<table id="tblWeighInd" ></table>
		</div>
		
		<div region="center" border="false" style="width:400px;">
			<div class="easyui-layout" fit="true" border="false" >
				<div region="center" border="false">
					<table id="tblWeighData" ></table>
				</div>

			</div>
		</div>
	</div>
	
     <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
      <%@ include file="/WEB-INF/jsp/public/fuheqianming.jspf"%>
	 <%@include file="/WEB-INF/jsp/tblTiprpAppIndAction/newTiprpAppInd.jspf" %>
	 <%@include file="/WEB-INF/jsp/tblTiprpAppIndAction/newTblTiprpAppRecDt.jspf" %>
	 <%@include file="/WEB-INF/jsp/TblWeighInd/AppWeightInd.jspf" %>
	 </div>
</body>
</html>
