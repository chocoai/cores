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
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/commCheck.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/ajaxfileupload.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblWeight.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblTiprpAppInd.js" charset="utf-8"></script>
<script type="text/javascript">
var talAnimalTable;
var studyNoPara=$('#studyNoPara').val();
var OneCurrentSerialNum ;
var tableDIV;
$(function(){   
	$('#AnimalTable').css('display','');   
	editRow = 'undefined';
	talAnimalTable=$('#talAnimalTable').datagrid({
		url : sybp()+"/tblWeightIndAction_loadList.action?studyNoPara="+$('#studyNoPara').val(),
		title:'',
		//height: 240,
		//width:560,
		iconCls:'',//'icon-save',
		pagination:false,//下面状态栏
		//pageSize:50,
		//pageList:[50,100],
		fit:true,
		fitColumns:false,//不出现横向滚动条
		nowarp:  false,//单元格里自动换行
		border:false,
		idField:'id', //pk
		singleSelect:true,
		//sortName:'aniSerialNum',//排序字段
		//sortOrder:'desc',//排序方法
	    columns :[[{
			title :'',
			field :'id',
			//checkbox:true
			hidden:true
		},{
			title:'专题编号',
			field:'studyNo',
			width:100
		},{
			title:'称重日期',
			field:'showtime',
			width:80
		},{
			title:'动物编号',
			field:'aniCode',
			width:60
		},{
			title:'体重',
			field:'weight',
			width:70,
			align:'right',
			//formartter 一定要返回个字符串
			formatter : function(value,rowData,rowIndex){
				return '<span title="'+value+'">'+value+'</span>';
		}
		},{
			title:'',
			field:'unit',
			width:30,
			align:'left'
		}]],
		//工具栏
		toolbar:'#toolbar',
		//单击事件
		onClickRow : function(rowIndex, rowData){
			$('#OneCurrentSerialNum').combobox('select',rowData.aniCode);
	 		$('#currentSerialNum').combobox('select',rowData.aniCode);
	 		$("#oneWeightInd").val(rowData.weight);
	 		$(this).datagrid('selectRow',rowIndex);
		},

		onLoadSuccess:function(data){
			var getvalue = $('#OneCurrentSerialNum').combobox('getValue');
            var rows = $('#talAnimalTable').datagrid('getRows');
            for(var i = 0 ;i<rows.length;i++){
            	var aniCode = $('#talAnimalTable').datagrid('getRows')[i].aniCode;
            	var weight = $('#talAnimalTable').datagrid('getRows')[i].weight;
                if( aniCode == getvalue ){
                	$('#talAnimalTable').datagrid('selectRow',i);
                	//$("#oneWeightInd").numberbox('setValue',weight);
                	$("#oneWeightInd").val(weight);
                }
            }
			
			
		}
		
		
	}); //end datagrid
	
	//表头居中
	$('.datagrid-header div').css('textAlign','center');
	//管理按钮状态
	manageButtonState();
	
	//序号下拉框初始化 
	initSerialNum();
    //获取焦点
	$('#oneWeightInd').focus();
	
	$('#toolbar').css('display','');  
	
	$("#oneWeightInd").keyup(function(e){
        var code = e.charCode || e.keyCode;
        if(code == 13){
      	  $('#oneWeightInd').blur();
      	  submitAddOneWegihtForm();
         }
      });
	
});//匿名函数   end

	//管理按钮状态
	
	function manageButtonState(){
		
		var isAnimalIdES=$('#isAnimalWegihtES').val();
		var isAnimalFY=$('#isAnimalWegihtFY').val();
		if(isAnimalIdES == 0 && isAnimalFY== 0){
			$('#signButton1').linkbutton('enable');
			$('#signButton2').linkbutton('disable');
		}else if(isAnimalIdES == 1&&isAnimalFY== 0){
			$('#signButton1').linkbutton('disable');
			$('#signButton2').linkbutton('enable');
			$('#submitAddOneButton').linkbutton('disable');
			$('#signButton3').linkbutton('disable');
			$('#signButton4').linkbutton('disable');
		}else if(isAnimalFY==1&&isAnimalIdES == 1){
			$('#signButton1').linkbutton('disable');
			$('#signButton2').linkbutton('disable');
			$('#signButton3').linkbutton('disable');
			$('#submitAddOneButton').linkbutton('disable');
			$('#signButton3').linkbutton('disable');
			$('#signButton4').linkbutton('disable');
		}
	}
	//序号下拉框初始化--->保留 
	function initSerialNum(){
		$.ajax({
	        type:"POST",
	        url:"${pageContext.request.contextPath}/tblWeightIndAction_initSerialNum.action",
	        contentType: "application/x-www-form-urlencoded; charset=utf-8",
	        dataType:"json",
	        data:"studyNoPara="+$('#studyNoPara').val(),
	        async:false,
	        success:function(r)
	        {
				if(r){
					$('#OneCurrentSerialNum').combobox('select',r.currentSerialNum);
				}
	        },
	        error:function()
	        {
	             parent.parent.showMessager(3,'与数据库交互错误',false);
	        }
	   });
	}
	/** 签字检查*/      
	function checkBeforeAnimalIdSign(){
		var studyNoPara =$('#studyNoPara').val();

		if(studyNoPara == '' || studyNoPara == null){
			return;
		}else {
			$.ajax({
		        type:"POST",
		        url:"${pageContext.request.contextPath}/tblWeightIndAction_signCheck.action",
		        contentType: "application/x-www-form-urlencoded; charset=utf-8",
		        dataType:"json",
		        data:"studyNoPara="+studyNoPara,
		        async:false,
		        success:function(r)
		        {
					if(r && r.success){
						if(r.animalIdError){
							 parent.parent.showMessager(2,r.animalIdError,true,5000);
							 //$("#errorMsg").html(r.animalIdError);
						}else if(r.msg){
							parent.parent.showMessager(2,r.msg,true,5000);
							 //$("#errorMsg").html(r.msg);
						}else{
							//$("#errorMsg").html('');
							$.messager.confirm('确认对话框', '确认动物体重录入完毕过后，确定继续吗？', function(r){
									if (r){
										qm_showQianmingDialog('eventAfterAnimalWeightSign');
									}
							});
						}
					}
		        },
		        error:function()
		        {
		             //$("#errorMsg").html("与服务器交互错误!");
		             parent.parent.showMessager(3,'与服务器交互错误!',true,5000);
		        }
		   });
		}
		
	}
	/** 复核检查*/      
	function checkBeforeAnimalweightSign(){
		var studyNoPara =$('#studyNoPara').val();
		if(studyNoPara == '' || studyNoPara == null){
			return;
		}else {
			$.ajax({
		        type:"POST",
		        url:"${pageContext.request.contextPath}/tblWeightIndAction_signCheck.action",
		        contentType: "application/x-www-form-urlencoded; charset=utf-8",
		        dataType:"json",
		        data:"studyNoPara="+studyNoPara,
		        async:false,
		        success:function(r)
		        {
					if(r && r.success){
						if(r.animalIdError){
							 parent.parent.showMessager(2,r.animalIdError,true,5000);
							 //$("#errorMsg").html(r.animalIdError);
						}else if(r.msg){
							parent.parent.showMessager(2,r.msg,true,5000);
							 //$("#errorMsg").html(r.msg);
						}else{
							//$("#errorMsg").html('');
						    fuheqm_showQianmingDialog('eventAfterAnimalWeightsSign');
							
						}
					}
		        },
		        error:function()
		        {
		             //$("#errorMsg").html("与服务器交互错误!");
		             parent.parent.showMessager(3,'与服务器交互错误!',true,5000);
		        }
		   });
		}
		
	}
	/** 签字检查*/      
	function checkBeforeAnimalIdSign(){
		var studyNoPara =$('#studyNoPara').val();
		if(studyNoPara == '' || studyNoPara == null){
			return;
		}else {
			$.ajax({
		        type:"POST",
		        url:"${pageContext.request.contextPath}/tblWeightIndAction_signCheck.action",
		        contentType: "application/x-www-form-urlencoded; charset=utf-8",
		        dataType:"json",
		        data:"studyNoPara="+studyNoPara,
		        async:false,
		        success:function(r)
		        {
					if(r && r.success){
						if(r.animalIdError){
							 parent.parent.showMessager(2,r.animalIdError,true,5000);
							 //$("#errorMsg").html(r.animalIdError);
						}else if(r.msg){
							parent.parent.showMessager(2,r.msg,true,5000);
							 //$("#errorMsg").html(r.msg);
						}else{
							//$("#errorMsg").html('');
							$.messager.confirm('确认对话框', '确认动物体重录入完毕过后，动物体重将不可以再编辑，确定继续吗？', function(r){
									if (r){
										qm_showQianmingDialog('eventAfterAnimalWeightSign');
									}
							});
						}
					}
		        },
		        error:function()
		        {
		             //$("#errorMsg").html("与服务器交互错误!");
		             parent.parent.showMessager(3,'与服务器交互错误!',true,5000);
		             
		        }
		   });
		}
		
	}
	
	
	/**签字过后方法*/
	function eventAfterAnimalWeightSign(){
		$.ajax({
			url:sybp()+"/tblWeightIndAction_animalWeightSign.action?studyNoPara="+$('#studyNoPara').val()+"&esType=8",
			type:'post',
			dataType:'json',
			success:function(r){
				if(r && r.success){
					parent.parent.showMessager(1,'签字成功',true,5000);
					$('#signButton1').linkbutton('disable');
					$('#signButton2').linkbutton('enable');
					$('#signButton3').linkbutton('disable');
					$('#signButton4').linkbutton('disable');
					$('#submitAddOneButton').linkbutton('disable');
				}else{
				    parent.parent.showMessager(3,'签字失败',true,5000);
				}
			}
		});
		
	}
	/**复核过后方法*/
	function eventAfterAnimalWeightsSign(){
		$.ajax({
			url:sybp()+"/tblWeightIndAction_animalWeightSign.action?studyNoPara="+$('#studyNoPara').val()+"&esType=9",
			type:'post',
			dataType:'json',
			data:{
			    FHsingName:$('#TheFHUserName').val(),
    		},
			success:function(r){
				if(r && r.success){
					parent.parent.showMessager(1,'复核签字成功',true,5000);
					var aclick=document.getElementById("aclick")
					aclick.click();
				}else{
				    parent.parent.showMessager(3,'复核签字失败',true,5000);
				}
			}
		});
		
	}
	/**显示动物编号批量导入Dialog*/
	function showwightFileDialog(){
		var rows =$('#talAnimalTable').datagrid('getRows');
		var noFull = false;
		for(var i=0;i<rows.length;i++){
			if(rows[i].animalId !=''){
				noFull=true;
				break;
			}
		}
		if(noFull){
			$.messager.confirm('确认对话框', '动物体重批量导入将覆盖原有数据，确定继续吗？', function(r){
				if (r){
					/*Dialog*/
					document.getElementById("idFileDialog2").style.display="block";
					$('#idFileDialog').dialog('open');
				}
			});
		}else{
			/*Dialog*/
			document.getElementById("idFileDialog2").style.display="block";
			$('#idFileDialog').dialog('open'); 
		}
	}
	/**执行文件上传操作*/
	function ajaxFileUpload(){
		$('#errorSpan').html('');
		if($('#excelCodeFile').val()!=''){
	         $.ajaxFileUpload({
		          url:sybp()+'/tblWeightIndAction_importExcel.action?studyNoPara='+$('#studyNoPara').val(),            //需要链接到服务器地址
		          secureuri:false,
		          fileElementId:'excelCodeFile',                        //文件选择框的id属性
		          dataType: 'json',                                     //服务器返回的格式，可以是xml
		          success: function (data, status){     
		              if(data &&data.success){
		            	  $('#idFileDialog').dialog('close'); 
		            	  for(var i = 0;i<data.animalMapList.length;i++){
								$('#talAnimalTable').datagrid('updateRow',{
						 			index: i,
						 			row: data.animalMapList[i]
						 		});
								$('#talAnimalTable').datagrid('selectRow',data.animalMapList[i].index-1);
							}
							
							$('#OneCurrentSerialNum').combobox('select',data.currentSerialNum);
					 		$('#currentSerialNum').combobox('select',data.currentSerialNum);
					 		
					 		parent.parent.showMessager(1,data.msg,true,5000);
		            	  
		              }else{
		            	$('#errorSpan').html(data.msg);
		              }
		          },
		          error: function (data, status, e){
		        	  $('#errorSpan').html('文件上传失败，请刷新页面后重试');
		          }
	      	});
		}else{
			$('#errorSpan').html('请选择待上传的文件');
		}
    }

    function editTblWeighInd(){
    	$.ajax({
    		url:sybp()+'/tblWeightIndAction_toeditTblWeighInd.action',
    		type:'post',
    		cache:false,
    		data:{
    			studyNoPara:$('#studyNoPara').val(),
    		},
    		dataType:'json',
    		success:function(r){
    			if(r ){
    				$('#tblWeighIndStudyNo').val(r.StudyNo);
    				$('#WeighSn').val(r.WeighSn);
    				$('#WeightUnit1').combobox('setValue', r.WeightUnit1);
    				$('#WeighDate').datebox('setValue', r.NowDate);
    				$('#editTblWeighInd').dialog('open');
    			    $('#editTblWeighInd2').dialog('open');
    			    
    			}
    		}
    	});		
        }

    function editTblWeighIndButton(){
    	$.ajax({
    		url:sybp()+'/tblWeightIndAction_toeditTblWeighIndinfo.action',
    		type:'post',
    		cache:false,
    		data:$('#tblWeighIndForm').serialize(),
    		dataType:'json',
    		success:function(r){
    			if(r ){
    				window.location.reload();
    			}
    		}
    	});		

        }

 		
	 //  function checkWeight(){
		//  var value=$('#oneWeightInd').val();
		//  alert(1);
		//     alert(/^(?<!0)[1-9]+(\d+)?(\.\d+)?$/.test(value));
	    ///   if(/^(?<!0)[1-9]+(\d+)?(\.\d+)?$/.test(value)){
	    //       alert('规范数字')
	    //        $('#errorWeight').css('display','none');
	    //   }else{
	    //     $('#errorWeight').css('display','');
	    //   };
		    
		//}
         
</script>
</head>
<body>
<s:hidden id="studyNoPara" name="studyNoPara"></s:hidden>
<s:hidden id="isAnimalWegihtES" name="isAnimalWegihtES"></s:hidden>
<s:hidden id="isAnimalWegihtFY" name="isAnimalWegihtFY"></s:hidden>
<s:hidden id="listSize" name="listSize"></s:hidden>
<!-- 跳转到试验计划主页，如后转到animalList.jsp -->
<a id="aclick"  href="${pageContext.request.contextPath}/tblStudyPlanAction_studyPlanMain.action?studyNoPara=${studyNoPara}&toWhere=Weight1" target="main"></a>
<div id="talAnimalTablePlanDIV" class="easyui-layout" fit="true" border="false"   >
		<div region="center" border=false >
		<!-- table start -->
				<table id="talAnimalTable" ></table>
		<!-- table end -->
		</div>
		
		
		<div region="south"  border="false" style="height:180px; width:100%">
			<!-- 状态栏  开始 -->
			<div style="width:100%;height:20px;border-top:1px solid #c8c8c8; padding-top:10px; ">
			<span id="errorMsg" style="color:red;position:absolute;left:20px;"></span>
			<span style="position:absolute;right:20px;">共&nbsp;${listSize}&nbsp;条记录</span>
			</div>
			<!-- 状态栏  结束 -->
			<div id="tabs" class="easyui-tabs"  fit="true"  style="width:100%;height:100%;">    

			    <div id ="AnimalTable" title="动物体重录入" style="padding:10px 20px;display:none;"  data-options="closable:false" >  
			    	<s:form id="addOneForm" action="tblWeightIndAction_editOneAnimalIdSave" method="post">
					<s:hidden id="studyNoPara" name="studyNoPara"></s:hidden> 
					
			        <table width="440px;">
			        	<tr>
			        		<td width="80px">动物编号</td>
			        		<td width="300px">
			        			<input id="OneCurrentSerialNum" name="currentSerialNum" style="width:60px;text-align:left"
			        			class="easyui-combobox" data-options="valueField:'id',textField:'text',editable:false,panelHeight:0" required="true" missingMessage="动物编号" />
			        		</td>
			        		<td width="270px;"></td>
			        	</tr>
			        	<tr>
			        		<td>动物体重</td>
			        		<td><input id ="oneWeightInd" name="newWeightInd"  style="width:80px;"/>&nbsp;&nbsp;<span id="errorWeight" style="color:red;display:none">请检查录入数据</span>
			        		</td>
			        		<td></td>
			        	</tr>
			        	<tr>
			        		<td></td>
			        		<td><a id="submitAddOneButton" class="easyui-linkbutton" onclick="submitAddOneWegihtForm();" >确定</a></td>
			        		<td></td>
			        	</tr>
			        </table>
			        
			        </s:form>
			    </div>     
			</div> 
		</div>
		
		
</div>
	<div id="toolbar" style="display:none;">
	    <a id="signButton4" href="javascript:void(0);" class="easyui-linkbutton" onclick="editTblWeighInd();"  data-options="iconCls:'icon-edit',plain:true" >称重信息</a>
  		<a id="signButton1" class="easyui-linkbutton" onclick="checkBeforeAnimalIdSign();" data-options="iconCls:'icon-ok',plain:true" >数据确认</a>
  		<a id="signButton2" class="easyui-linkbutton" onclick="checkBeforeAnimalweightSign();" data-options="iconCls:'icon-ok',plain:true" >复核</a>
  		<a id="signButton3" href="javascript:void(0);" class="easyui-linkbutton" onclick="showwightFileDialog();"  data-options="iconCls:'icon-excel',plain:true" >批量导入</a> 
        
  		<a id="signButton5" href='${pageContext.request.contextPath}/tblWeightIndAction_list.action?studyNoPara=${studyNoPara}' class="easyui-linkbutton" onclick=""  data-options="iconCls:'icon-back',plain:true" >返回</a>
        <font color="red"><span id="span1"></span></font>
    </div>
    <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%> 
    <%@ include file="/WEB-INF/jsp/public/fuheqianming.jspf"%>
    <!-- 动物体重批量导入   弹窗开始 -->
	<div id="idFileDialog" class="easyui-dialog" title="动物体重批量导入" modal="true" closed="true" draggable="false" style="display:'none';width:320px;height:420px;" closable="true">
	<div id="idFileDialog2" style="display:none;">
		<s:form id="codeFileFrom" action="" method="post"
			enctype="multipart/form-data" theme="simple" >
			
			<table width="100%">
				<tr><td height="10px;" colspan="2"></td></tr>
				<tr>
					<td><s:file id="excelCodeFile" name="excelCodeFile" cssStyle="width:220px;height:20px;" onclick="javascript:$('#errorSpan').html('');"></s:file></td>
					<td>
						<input type="button" value="上传文件" onclick="ajaxFileUpload();"/>
					</td>
				</tr>
				<tr><td height="10px;" colspan="2">&nbsp;<font color="red"><span id="errorSpan"></span></font></td></tr>
				<tr><td height="10px;" colspan="2">样例：</td></tr>
				<tr>
				<td colspan="2">
					<img src="${pageContext.request.contextPath}/style/images/weight.png" alt="动物体重.xls" />
				</td>
				</tr>
			</table>
			</s:form>
	</div>
	</div>
	

	<!-- 修改 -->
	<div id="editTblWeighInd" class="easyui-dialog" title="称重信息" modal="true" closed="true" draggable="false"
		data-options="toolbar:'#dialogToolbar'" style="display:'';width:380px;height:220px;" closable="true">
			<div id="editTblWeighInd2" style="display:'';" closable="true">
		    <div >
		     <input type="hidden" id="tblWeighInd.id">
			  <form id="tblWeighIndForm" action="" method="post">
		   	  <div class="edit_table">
                <table class="tblWeighInd" >
                    <tr><td width="100px;">专题编号</td>
                        <td width="200px;">
                        	<input id="tblWeighIndStudyNo" type="text" name="tblWeighInd.StudyNo" class="validatebox" required="true" readonly="true">
                        </td>
                    </tr>
                     <tr><td width="100px;">序号</td>
                        <td width="200px;">
                        	<input id="WeighSn" type="text" name="tblWeighInd.WeighSn" class="validatebox" required="true" readonly="true">
                        </td>
                    </tr>
                     <tr><td width="100px;">称重日期</td>
                        <td width="200px;">
                            	<input id="WeighDate" type="text" name="tblWeighInd.WeighDate" class="easyui-datebox" required="true" /> 
                        </td>
                    </tr>
                     <tr><td width="100px;">体重单位</td>
                     <td>
                        <select id="WeightUnit1" class="easyui-combobox" style="width:100px;height:19px;"  panelHeight="38px" name="tblWeighInd.WeightUnit" required="true" >
                        		<option value="kg">kg</option>
                        		<option value="g">g</option>
                        </select>
                        </td>
                    </tr>
                </table>
            </div>
               </form>	   
		    </div>
		    </div>
	</div>
	<!-- 工具栏 开始-->
	<div id="dialogToolbar">
		<a id="saveDialogButton" class="easyui-linkbutton" onclick="editTblWeighIndButton();" data-options="iconCls:'icon-add',plain:true">确定</a>
		<a id="backButton" class="easyui-linkbutton" onclick="javascript:$('#editTblWeighInd').dialog('close');" data-options="iconCls:'icon-back',plain:true">返回</a>
	</div>
	<!-- 工具栏 结束-->
</body>
</html>