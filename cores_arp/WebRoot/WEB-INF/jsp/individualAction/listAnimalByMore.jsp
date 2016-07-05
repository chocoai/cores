<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>综合查询</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/tableCss.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/employeeAction/employeeAddEdit.js" charset="utf-8"></script>
<style type="text/css">
	 .tree-icon {
		width:0px;
	}
	
</style>
<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 30
	var tableWidth;
	$(function(){
		tableHeight = document.body.clientHeight - 250;
		tableWidth  = document.body.clientWidth-100;
		
		//初始化datagrid
		$('#individualTable').datagrid({
			url : sybp()+'/individualAction_loadList.action',
			title:'',
			height:tableHeight,
			width:tableWidth,
			nowarp:  false,//单元格里自动换行
			singleSelect:true,
			fitColumns:false,
		    pagination:true,//分页
			sortName:'id',
			idField:'id',
			columns :[[{
				title:'id',
				field:'id',
				width:150,
				halign:'center',
				align:'center',
				hidden:true
			},
			{
				title:'动物编号',
				field:'monkeyid',
				width:75,
				halign:'center',
				align:'center'
			}
			,
			{
				title:'性别',
				field:'sex',
				width:35,
				halign:'center',
				align:'center',
				formatter: function(value,row,index){
					if ( value == 0 ){
						return "公";
					}else{
						return "母";
					}
				}
			},
			{
				title:'动物芯片号',
				field:'chipid',
				width:100,
				halign:'center',
				align:'center'
			},
			{
				title:'种类',
				field:'animaltypeName',
				width:50,
				halign:'center',
				align:'center'
			}
			,
			{
				title:'年龄阶段',
				field:'agetype',
				width:55,
				halign:'center',
				align:'center',
				formatter: function(value,row,index){
					if ( value == 1 ){
						return "仔猴";
					}else if(value == 2){
						return "育成猴";
					}else if(value == 3){
					   return "成年猴";
					}else{
					    return value;
					}
				}
			}
			,
			{
				title:'饲养员',
				field:'keeperp',
				width:55,
				halign:'left',
				align:'left'
			}
			,
			{
				title:'目前体重(kg)',
				field:'currentweight',
				width:80,
				halign:'left',
				align:'left'
			},
			{
				title:'区域',
				field:'quyu',
				width:120,
				halign:'left',
				align:'left'
			},
			{
				title:'称重日期',
				field:'weighingDate',
				width:80,
				halign:'center',
				align:'center'
			},
			{
				title:'出生日期',
				field:'birthday',
				width:80,
				halign:'center',
				align:'center'
			},
			{
				title:'离乳日期',
				field:'leavebreastdate',
				width:80,
				halign:'center',
				align:'center'
			},
			//{
			//	title:'出生体重（kg）',
			//	field:'birthdayweight',
			//	width:100,
			//	halign:'left',
			//	align:'left'
			//},
			{
				title:'父亲',
				field:'fatherid',
				width:70,
				halign:'left',
				align:'left'
			},
			{
				title:'母亲',
				field:'motherid',
				width:70,
				halign:'left',
				align:'left'
			},
			{
				title:'国家编号',
				field:'tnid',
				width:100,
				halign:'left',
				align:'left'
			},
			{
				title:'引进地',
				field:'yjaddress',
				width:100,
				halign:'left',
				align:'left'
			}
			,
			{
				title:'状态',
				field:'status',
				width:55,
				halign:'center',
				align:'center',
				formatter: function(value,row,index){
					if ( value == 1 ){
						return "在场";
					}else if(value == 2){
						return "待销售";
					}else {
					    return "";
					}
				}
			}
			,
			{
				title:'备注',
				field:'remark',
				width:100,
				halign:'left',
				align:'left'
			}
			]],
			onSelect:function(rowIndex, rowData){
			    $('#addIndividualButton').linkbutton('enable');
			    $('#editIndividualButton').linkbutton('enable');
			    $('#deleteIndividualButton').linkbutton('enable');
			    if(rowData.contractMark==0&&rowData.viewMark==0){
			      $('#selectContractButton').linkbutton('enable');
			    }else{
			      $('#selectContractButton').linkbutton('disable'); 
			    }
                 $('#addContractButton').linkbutton('enable');
                  $('#exportExcelButton').linkbutton('enable');
			    
			},
			onLoadSuccess:function(data){
				$('#addIndividualButton').linkbutton('enable');
			    $('#editIndividualButton').linkbutton('disable');
			    $('#deleteIndividualButton').linkbutton('disable');
			    $('#exportExcelButton').linkbutton('disable');
			},
			toolbar:'#toolbar',
			pageSize : 12,//默认选择的分页是每页5行数据         
			pageList : [ 5, 10, 12, 15, 20 ],//可以选择的分页集合       
		    nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取       
		    striped : true,//设置为true将交替显示行背景。      
		    collapsible : true//显示可折叠按钮 
			
	   	}); //end datagrid
		
		 //饲养员   
		 //keeperLondCombobox();
		 //运进地址
		 //yjaddressLondCombobox();
		 //源地址
		 //sourceaddressLondCombobox();
		 //动物种类下拉
		 //animaltypeLondCombobox();
		 //区域树形
		 //quyuCombotree1();
		//显示整个布局
		$('#layoutMainDiv').css('display','');   
    });//匿名函数结束

    function onSearchButton(){
       if( $('#monkeyidForm').form('validate') ){
           
         //病毒 
          var bv = $('#bv').combobox('getValue');
          var stlv = $('#stlv').combobox('getValue');
          var srv = $('#srv').combobox('getValue');
          var siv = $('#siv').combobox('getValue');
          var measles = $('#measles').combobox('getValue');
          var hepatitisa = $('#hepatitisa').combobox('getValue');
          //寄生虫
          var amb = $('#amb').combobox('getValue');
          var gxc = $('#gxc').combobox('getValue');
          var lyc = $('#lyc').combobox('getValue');
          var bmc = $('#bmc').combobox('getValue');
          var twjsc = $('#twjsc').combobox('getValue');
          //细菌
          var shig = $('#shig').combobox('getValue');
          var salm = $('#salm').combobox('getValue');
          var yers = $('#yers').combobox('getValue');
          //疫苗
          var mazhen = $('#mazhen').combobox('getValue');
         
           $('#individualTable').datagrid({url : sybp()+'/individualAction_loadListAnimalByMore.action?'+
           'bv='+bv+'&stlv='+stlv+'&srv='+srv+'&siv='+siv+'&measles='+measles+'&hepatitisa='+hepatitisa+
           '&amb='+amb+'&gxc='+gxc+'&lyc='+lyc+'&bmc='+bmc+'&twjsc='+twjsc+
           '&shig='+shig+'&salm='+salm+'&yers='+yers+'&mazhen='+mazhen,
           pageNumber:1
           });
       
       }
    }

   //导出EXCEL
   function exportExcel(){
           var path = sybp()+'/individualAction_export.action';
           window.open(path,'target','param');
   }
   /**删除操作*/
   function onDeleteButton(){
      var   row = $('#individualTable').datagrid("getSelected");
      if(null != row ){
           $.messager.confirm('确认对话框', '确认删除此动物，确定继续吗？', function(r){
									if (r){
										afterDelete(row.monkeyid);
									}
							});
      }
      
      
   }
   /**执行删除操作*/
   function afterDelete(monkeyid){
      $.ajax({
				url:sybp()+'/individualAction_delIndividual.action',
				type:'post',
				cache:false,
				data:{
					monkeyid:monkeyid
				},
				dataType:'json',
				success:function(r){
					if(r && r.success){
					   	$('#individualTable').datagrid('reload');
						parent.parent.showMessager(1,'删除成功',true,5000);
					}else{
						parent.parent.showMessager(2,"与数据库交互异常",true,5000);
					}
				}
			});
   }
   
   
   /**添加操作*/
   function onAddButton(){
   initBlongareaCombobox();
      document.individualAddEditForm.reset();
      $('#oldMonkeyid').val('');
      showIndividualAddEditDialog('afterAddDialog','add','添加个体');
      addOreditanimaltypeLondCombobox();
      keeperNoLondCombobox();
      initSourceCombobox();//来源类型
      initAddOrEdityjaddress();//引进地.
      $("#addOrEditMonkeyid").removeAttr("readonly");
   }
   /**执行完添加操作*/
   function afterAddDialog(){
      var monkeyid = $('#addOrEditMonkeyid').val();
      $('#individualTable').datagrid({url : sybp()+'/individualAction_loadList.action?monkeyid='+monkeyid,
        pageNumber:1});
      $('#individualAddEditDialog').dialog('close'); 
      
   }
    
   //animaltype动物种类下拉
   function addOreditanimaltypeLondCombobox(){
     $('#addOreditAnimaltype').combobox({    
		    url:sybp()+'/individualAction_addOreditloadListAnimaltype.action',
		    valueField:'id',    
		    textField:'text',
		     onSelect:function(record){
		     }  
		    
		});  
		
   }
   
     //饲养员、兽医下拉选 无全选
   function keeperNoLondCombobox(){
     $('#addOrEditkeeper').combobox({    
		    url:sybp()+'/employeeAction_loadListEmployeeNo.action',
		    valueField:'id',    
		    textField:'text',
		    required:true,
		     onSelect:function(record){
		      
		    }  
		}); 
		 $('#addOrEditVeterinarian').combobox({    
		    url:sybp()+'/employeeAction_loadListEmployeeNo.action',
		    valueField:'id',    
		    textField:'text',
		     onSelect:function(record){
		      
		    }  
		}); 
   }
    //区域下拉
   function quyuCombotree(){
            $("#room").combotree({
			   url:sybp()+'/areaAction_loadAreaComNo.action',
			   multiple:false,//设置是否多选
			   onSelect:function(record){
			        var falg =  $("#room").tree('isLeaf',record.target);
			        if(falg){
			          var row =  $("#room").tree('getParent',record.target);
			           if(null != row){
			           $("#blongarea").val(row.id);
			          }
			        }else{
			            $("#blongarea").val(record.id);
			        }
                }
		    }
		 );
   
   }
   /**编辑操作*/
   function onEditButton(){
       var row= $('#individualTable').datagrid('getSelected');
        showIndividualAddEditDialog('afterEditDialog','edit','编辑个体');
        addOreditanimaltypeLondCombobox();
        keeperNoLondCombobox();
        initBlongareaCombobox();
        fullIndividualDialog(row.id);
        initSourceCombobox();
        initAddOrEdityjaddress();
        $("#addOrEditMonkeyid").attr({ readonly: 'true' });
   }
   /**执行完编辑操作*/
   function afterEditDialog(){
       var monkeyid = $('#addOrEditMonkeyid').val();
      $('#individualTable').datagrid({url : sybp()+'/individualAction_loadList.action?monkeyid='+monkeyid,
        pageNumber:1});
      $('#individualAddEditDialog').dialog('close'); 
   }
   /**根据id填充*/
   function fullIndividualDialog(id){
      $.ajax({
          url:sybp()+'/individualAction_findIndividula.action',
          type:'post',
          cache:false,
          data:{id:id },
          dataType:'json',
		  success:function(r){
		  	     if(r){
		  	            $('#inid').val(r.id);
		  	            $('#oldMonkeyid').val(r.monkeyid);
		  	            $('#addOrEditMonkeyid').val(r.monkeyid);
		  	            $('#sex').combobox('select',r.sex);
		  	            $('#addOreditAnimaltype').combobox('select',r.animaltype);
		  	            $('#birthday').datebox('setValue', r.birthday);
		  	            $('#agetype').combobox('select',r.agetype);
		  	            $('#generation').combobox('select',r.generation);
		  	            $('#source').combobox('select',r.source);
		  	            $('#addOrEditkeeper').combobox('select',r.keeper);
		  	            $('#addOrEditVeterinarian').combobox('select',r.veterinarian);
		  	            $('#currentweight').val(r.currentweight);
		  	            $('#weighingDate').datebox('setValue',r.weighingDate);
		  	            $('#blongarea').combobox('select',r.blongarea);
		  	            $('#room').combobox('select',r.room);
		  	            $('#tnid').val(r.tnid);
		  	            $('#status').combobox('select',r.status);
		  	            $('#lhao').val(r.lhao);
		  	            $('#motherid').val(r.motherid);
		  	            $('#fatherid').val(r.fatherid);
		  	            $('#leavebreastdate').datebox('setValue',r.leavebreastdate);
		  	            $('#leavebreastweight').val(r.leavebreastweight);
		  	            $('#birthdayweight').val(r.birthdayweight);
		  	            $('#AddOrEdityjaddress').combobox('select',r.yjaddress);
		  	            $('#yjdate').datebox('setValue',r.yjdate);
		  	            $('#ysz').val(r.ysz);
		  	            $('#chipid').val(r.chipid);
		  	            $('#remark').val(r.remark);
		  	     }
		  }
          
      });
   }
   
   
   /**显示Dialog*/
    function showIndividualAddEditDialog(clickName,addOrEdit,title){
    
		document.getElementById("individualAddEditDialog2").style.display="block";
	    if(addOrEdit == "add"){
	    }else if(addOrEdit == "edit"){
  		}
  		$('#addOrEdit').val(addOrEdit);
	    $('#individualAddEditDialog').dialog('setTitle',title);
	    $('#individualAddEditDialog').dialog('open'); 
	    document.getElementById("individualAddEdit_event").href="javascript:"+clickName+"();";
     }
     /**确定按钮*/
     function onDialogSaveButton(){
        if( $('#individualAddEditForm').form('validate') ){
				$('#saveDialogButton').linkbutton('disable');
				if($('#addOrEdit').val() == 'add'){
					$.ajax({
						url:sybp()+'/individualAction_addSave.action',
						type:'post',
						data:$('#individualAddEditForm').serialize(),
						dataType:'json',
						success:function(r){
							$('#saveDialogButton').linkbutton('enable');
							if(r && r.success){
							   parent.parent.showMessager(1,'添加成功',true,5000);
							   document.getElementById("individualAddEdit_event").click();
							}else{
								$.messager.alert('提示','请检查录入的数据');
							}
						}
					});
				}else{
					$.ajax({
						url:sybp()+'/individualAction_editSave.action',
						type:'post',
						data:$('#individualAddEditForm').serialize(),
						dataType:'json',
						success:function(r){
							$('#saveDialogButton').linkbutton('enable');
							if(r && r.success){
							    parent.parent.showMessager(1,'编辑成功',true,5000);
							    document.getElementById("individualAddEdit_event").click();
							}else{
								$.messager.alert('提示','请检查录入的数据');
							}
						}
					});
				}
				
			}

     }
     /**导出个体档案*/
     function exportExcelOne(){
           var row= $('#individualTable').datagrid('getSelected');   
           var path = sybp()+'/individualAction_exportExcelOne.action?id='+row.id;
           window.open(path,'target','param');
     }
     //区域
     function initBlongareaCombobox(){
		$('#blongarea').combobox({    
		url : sybp()+'/areaAction_getPareaNameId.action',
	    valueField:'id',    
	    textField:'text',
	    editable:false,
	    required:true,
	    onLoadSuccess:function(none){
		  
		},
		onSelect:function(rec){
			var id=$('#blongarea').combobox('getValue');
		    $('#room').combobox({
		    	url : sybp()+'/areaAction_getAllRoomIdName.action?blongarea='+id,
		    	valueField:'id',    
	    		textField:'text',
	    		editable:false,
	    		required:true,
	    		onLoadSuccess:function(nonee){
				},
				onSelect:function(rec){
					//var areaid=$('#blongarea').combobox('getValue');
					//var roomid=$('#room').combobox('getValue');
					//$('#monkeyid').combobox({
						//url : sybp()+'/individualAction_getRoomIndividual.action?roomid='+roomid+'&area='+areaid,
						//valueField:'id',
						//textField:'text',
						//editable:false,
						//onLoadSuccess:function(none){
						//}
					//});
				}
		    }); 
		}
			
	});
}

function areaAndRoomCombobox(){
     $('#blongarea').combobox({    
		    url:sybp()+'/areaAction_getPareaNameId.action',
		    valueField:'id',    
		    textField:'text',
		     onSelect:function(record){
		      
		    }  
		}); 
		 $('#room').combobox({    
		    url:sybp()+'/areaAction_loadListRoomNo.action',
		    valueField:'id',    
		    textField:'text',
		     onSelect:function(record){
		      
		    }  
		}); 
   }

function initSourceCombobox(){
	$('#source').combobox({url:sybp()+'/individualAction_listAllSourceType.action',
      						valueField:'id',    
		    				textField:'text',
		    				required:true
      });
}
function initAddOrEdityjaddress(){
	$('#AddOrEdityjaddress').combobox({url:sybp()+'/individualAction_loadListYjaddress.action',
							valueField:'id',
							textField:'text',
							required:true,
							onSelect:function(rec){
								if(rec.id == "-1"){
		         					$('#AddOrEdityjaddress').combobox('select', "");
		      					}
							}
	});
}
</script>
</head>
<body>
<s:hidden id="onchange" name="onchange"></s:hidden>
<!-- 选择id -->
<s:hidden id="selectId" name="selectId"></s:hidden>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
 	<div region="center" title="" style="overflow: hidden;">
 	 	<div  class="easyui-tabs" fit="true" border="false">
			<div title="检疫综合查询" border="false" style="overflow: auto;">
			    <div style="height:200px;background-color:#fafafa; ">
			     <form id="monkeyidForm" action="" method="post">
			    
			     <table class="tableCss">
			         <tr>
			           <td>病毒</td><td colspan="11">
			           <select id="bv" class="easyui-combobox" name="bv" style="width:100px;">
			           		<option value="">--B-V--</option>   
    						<option value="无">B-V --无</option>   
    						<option value="阴">B-V --阴</option>   
    						<option value="阳">B-V --阳</option>   
						</select>&nbsp;
						<select id="stlv" class="easyui-combobox" name="stlv" style="width:100px;">   
    						<option value="">--STLV--</option>
    						<option value="无">STLV --无</option>   
    						<option value="阴">STLV --阴</option>   
    						<option value="阳">STLV --阳</option>   
						</select>&nbsp;
						<select id="srv" class="easyui-combobox" name="srv" style="width:100px;">   
    						<option value="">--SRV--</option>
    						<option value="无">SRV --无</option>   
    						<option value="阴">SRV --阴</option>   
    						<option value="阳">SRV --阳</option>   
						</select>&nbsp;
						<select id="siv" class="easyui-combobox" name="siv" style="width:100px;">   
    						<option value="">--SIV--</option>
    						<option value="无">SIV --无</option>   
    						<option value="阴">SIV --阴</option>   
    						<option value="阳">SIV --阳</option>   
						</select>&nbsp;
						<select id="measles" class="easyui-combobox" name="measles" style="width:100px;">   
    						<option value="">--Measles--</option>
    						<option value="无">Measles --无</option>   
    						<option value="阴">Measles --阴</option>   
    						<option value="阳">Measles --阳</option>   
						</select>&nbsp;
						<select id="hepatitisa" class="easyui-combobox" name="hepatitisa" style="width:100px;">   
    						<option value="">--Hepatitis A--</option>
    						<option value="无">Hepatitis A --无</option>   
    						<option value="阴">Hepatitis A --阴</option>   
    						<option value="阳">Hepatitis A --阳</option>   
						</select>&nbsp;
			            </td>
			         </tr>
			         <tr>
			           <td>寄生虫</td><td colspan="11">
			           <select id="amb" class="easyui-combobox" name="amb" style="width:100px;">   
    						<option value="">--阿米巴--</option>
    						<option value="无">阿米巴 --无</option>   
    						<option value="阴">阿米巴 --阴</option>   
    						<option value="阳">阿米巴 --阳</option>   
						</select>&nbsp;
						<select id="gxc" class="easyui-combobox" name="gxc" style="width:100px;">   
    						<option value="">--蠕虫--</option>
    						<option value="无">蠕虫 --无</option>   
    						<option value="阴">蠕虫 --阴</option>   
    						<option value="阳">蠕虫 --阳</option>   
						</select>&nbsp;
						<select id="lyc" class="easyui-combobox" name="lyc" style="width:100px;">   
    						<option value="">--疟原虫--</option>
    						<option value="无">疟原虫 --无</option>   
    						<option value="阴">疟原虫 --阴</option>   
    						<option value="阳">疟原虫 --阳</option>   
						</select>&nbsp;
						<select id="bmc" class="easyui-combobox" name="bmc" style="width:100px;">   
    						<option value="">--鞭毛虫--</option>
    						<option value="无">鞭毛虫 --无</option>   
    						<option value="阴">鞭毛虫 --阴</option>   
    						<option value="阳">鞭毛虫 --阳</option>   
						</select>&nbsp;
						<select id="twjsc" class="easyui-combobox" name="twjsc" style="width:100px;">   
    						<option value="">--体外寄生虫--</option>
    						<option value="无">体外寄生虫 --无</option>   
    						<option value="阴">体外寄生虫 --阴</option>   
    						<option value="阳">体外寄生虫 --阳</option>
    					</select>&nbsp;	   
			            </td>
			         </tr>
			         <tr>
			           <td>细菌</td><td colspan="11">
			           <select id="shig" class="easyui-combobox" name="shig" style="width:100px;">   
    						<option value="">--Shig--</option>
    						<option value="无">Shig --无</option>   
    						<option value="阴">Shig --阴</option>   
    						<option value="阳">Shig --阳</option>   
						</select>&nbsp;
						<select id="salm" class="easyui-combobox" name="salm" style="width:100px;">   
    						<option value="">--Salm--</option>
    						<option value="无">Salm --无</option>   
    						<option value="阴">Salm --阴</option>   
    						<option value="阳">Salm --阳</option>   
						</select>&nbsp;
						<select id="yers" class="easyui-combobox" name="yers" style="width:100px;">   
    						<option value="">--Yers--</option>
    						<option value="无">Yers --无</option>   
    						<option value="阴">Yers --阴</option>   
    						<option value="阳">Yers --阳</option>   
						</select>&nbsp;
			            </td>
			         </tr>
			         <tr>
			           <td>疫苗</td><td colspan="11">
			           <select id="mazhen" class="easyui-combobox" name="shig" style="width:100px;">   
    						<option value="">--疫苗--</option>
    						<option value="Measles">麻疹 ---</option>   
    						<option value="HepatitisA">甲肝 ---</option>   
    						<option value="HepatitisB">乙肝 ---</option>   
						</select>&nbsp;
						
			            </td>
			         </tr>
			         <tr>
			           <td  colspan="12"  ><a id="searchButton" class="easyui-linkbutton" onclick="onSearchButton();" data-options="iconCls:'icon-search',plain:true">搜索</a>
			           <!--  <a id="exportExcel" class="easyui-linkbutton" onclick="exportExcel();" data-options="iconCls:'icon-excel',plain:true">导出Excel</a></td>-->
			         </tr>
			      </table>
 	          </form>
 	    
 	            </div>
				<table id="individualTable" ></table>
            </div>
		</div>
        <div id="toolbar">
			<a id="addIndividualButton" class="easyui-linkbutton" onclick="onAddButton();"  data-options="iconCls:'icon-monkeyadd',plain:true,disabled:true">添加</a>
			<a id="editIndividualButton" class="easyui-linkbutton" onclick="onEditButton();" data-options="iconCls:'icon-monkeyedit',plain:true,disabled:true">编辑</a>
			<a id="deleteIndividualButton" class="easyui-linkbutton" onclick="onDeleteButton()" data-options="iconCls:'icon-monkeydel',plain:true,disabled:true">删除</a>
			<a id="exportExcelButton" class="easyui-linkbutton" onclick="exportExcelOne();" data-options="iconCls:'icon-text',plain:true">导出个体档案</a></td>
			
		</div>
 	</div>
    <%@include file="/WEB-INF/jsp/individualAction/individualAddEdit.jspf" %>
</div>
</body>
</html>