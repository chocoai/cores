<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>繁殖管理</title>
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
	var quyu;
	$(function(){
		tableHeight = document.body.clientHeight - 160;
		tableWidth  = document.body.clientWidth;
		 //区域
		 quyuCombobox();
		//roomConditionCombobox('');
		 //饲养员   
		 keeperLondCombobox();
		 //运进地址
//		 yjaddressLondCombobox();
		 //源地址
//		 sourceaddressLondCombobox();
		 //动物种类下拉
		 animaltypeLondCombobox();
		initDatagrid('');
		//显示整个布局
		$('#layoutMainDiv').css('display','');   
    });//匿名函数结束
    function initDatagrid(quyu){
    	//初始化datagrid
		$('#individualTable').datagrid({
			url : sybp()+'/individualAction_loadBreedingList.action?quyu='+quyu,
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
				title:'种类',
				field:'animaltypeName',
				width:50,
				halign:'center',
				align:'center'//,
				//formatter: function(value,row,index){
				//	if ( value == 240 ){
				//		return "恒河猴";
				//	}else if(value == 241){
				//		return "食蟹猴";
				//	}else{
				//	   return value;
				//	}
				//}
			}
			,
			{
				title:'年龄阶段',
				field:'agetype',
				width:55,
				halign:'center',
				align:'center',
				hidden:true ,
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
				align:'left',
				hidden:true 
			},
			{
				title:'区域',
				field:'quyu',
				width:120,
				halign:'left',
				align:'left'
			},
			{
				title:'房舍',
				field:'roomName',
				width:120,
				halign:'left',
				align:'left'
			},
			{
				title:'称重日期',
				field:'weighingDate',
				width:80,
				halign:'center',
				align:'center',
				hidden:true 
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
				align:'center',
				hidden:true 
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
				align:'left',
				hidden:true 
			},
			{
				title:'母亲',
				field:'motherid',
				width:70,
				halign:'left',
				align:'left',
				hidden:true 
			},
			{
				title:'国家编号',
				field:'tnid',
				width:100,
				halign:'left',
				align:'left',
				hidden:true
			},
			{
				title:'引进地',
				field:'yjaddress',
				width:100,
				halign:'left',
				align:'left',
				hidden:true 
			}
			,
			{
				title:'状态',
				field:'status',
				width:55,
				halign:'center',
				align:'center',
				hidden:true,
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
				align:'left',
				hidden:true 
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
    }
    function onSearchButton(){
       if( $('#monkeyidForm').form('validate') ){
           var monkeyid = $('#monkeyid').val();
           var animaltype = $('#animaltype').combobox('getValue');
           var sex = $('#sex').combobox('getValue');
           var keeper = $('#keeper').combobox('getValue');

           var birthdaymin = $('#birthdaymin').datebox('getValue');
           var birthdaymax = $('#birthdaymax').datebox('getValue');

           var roomCondition=$('#roomCondition').combobox('getValue');
           var quyu = $('#quyu').combobox('getValue');
           if(birthdaymin==""&&birthdaymax!=""){parent.showMessager(2,'请选择查询开始日期！',true,5000);return;}
           if(birthdaymin!=""&&birthdaymax==""){parent.showMessager(2,'请选择查询结束日期！',true,5000);return;}
           $('#individualTable').datagrid({url : sybp()+'/individualAction_loadBreedingList.action?monkeyid='+monkeyid+'&animaltype='+animaltype
           +'&sex='+sex+'&keeper='+keeper
           +'&quyu='+quyu+'&room='+roomCondition+'&birthdaymin='+birthdaymin+'&birthdaymax='+birthdaymax,
           pageNumber:1
           });
       
       }
    }
   //饲养员下拉选 
   function keeperLondCombobox(){
     $('#keeper').combobox({    
		    url:sybp()+'/employeeAction_loadListEmployee.action',
		    valueField:'id',    
		    textField:'text',
		     onSelect:function(record){
		      if(record.id == "-1"){
		         $('#keeper').combobox('select', "");
		      }
		    }  
		    
		});  
		
   }
   //yjaddress运进地址下拉
   function yjaddressLondCombobox(){
     $('#yjaddress').combobox({    
		    url:sybp()+'/individualAction_loadListYjaddress.action',
		    valueField:'id',    
		    textField:'text',
		     onSelect:function(record){
		      if(record.id == "-1"){
		         $('#yjaddress').combobox('select', "");
		      }
		    }  
		    
		});  
		
   }
   
   //sourceaddress源地址下拉
   function sourceaddressLondCombobox(){
     $('#sourceaddress').combobox({    
		    url:sybp()+'/individualAction_loadListSourceaddress.action',
		    valueField:'id',    
		    textField:'text',
		     onSelect:function(record){
		      if(record.id == "-1"){
		         $('#sourceaddress').combobox('select', "");
		      }
		    }  
		    
		});  
		
   }
   
   //animaltype动物种类下拉
   function animaltypeLondCombobox(){
     $('#animaltype').combobox({    
		    url:sybp()+'/individualAction_loadListAnimaltype.action',
		    valueField:'id',    
		    textField:'text',
		     onSelect:function(record){
		      if(record.id == "-1"){
		         $('#animaltype').combobox('select', "");
		      }
		    }  
		    
		});  
		
   }
   //区域下拉
   function quyuCombobox(){
//     
//            $("#quyu").combotree({
//			   url:sybp()+'/areaAction_loadAreaCom.action',
//			   multiple:false,//设置是否多选
//			   onSelect:function(record){
//                 }
//		    }
//		 );
	   $('#quyu').combobox({
           url:sybp()+'/areaAction_getPareaNameId.action',
           valueField:'id',    
           textField:'text' ,
           //disabled:true,
           onSelect:function(record){
		       var data=$('#quyu').combobox('getData');
               for(var i=0;i<data.length;i++){
                   if(data[i].text){
                	   roomConditionCombobox(data[i].id);
                	   //initDatagrid(data[i].id);
                   }
               }
           },
           onLoadSuccess:function(){
           //列表繁殖区动物。
               //var data=$('#quyu').combobox('getData');
               //for(var i=0;i<data.length;i++){
               //    if(data[i].text=='繁殖区'){
               // 	   $('#quyu').combobox('setValue',data[i].id);
               // 	   roomConditionCombobox(data[i].id);
              //  	   initDatagrid(data[i].id);
               //    }
               //}
           }
        });
  }
   //房舍下拉（找出繁殖区里所有房舍）
   function roomConditionCombobox(blongId){
        $('#roomCondition').combobox({
           url:sybp()+'/areaAction_getAllRoomIdName1.action?blongarea='+blongId,
           valueField:'id',    
           textField:'text' ,
           editable:false,
           onLoadSuccess:function(){
        	   //$('#roomCondition').combobox('setValue',-1);
            
           }  
                  
        });
   }
   //导出EXCEL
   function exportExcel(){
           var monkeyid = $('#monkeyid').val();
           var animaltype = $('#animaltype').combobox('getValue');
           var sex = $('#sex').combobox('getValue');
           var keeper = $('#keeper').combobox('getValue');
          // var yjaddress = $('#yjaddress').combobox('getValue');
          // var sourceaddress = $('#sourceaddress').combobox('getValue');
         //  var mincurrentweight = $('#mincurrentweight').val();
          // var maxcurrentweight = $('#maxcurrentweight').val();
           var birthdaymin = $('#birthdaymin').datebox('getValue');
           var birthdaymax = $('#birthdaymax').datebox('getValue');
         //  var yjdatemin = $('#yjdatemin').datebox('getValue');
         //  var yjdatemax = $('#yjdatemax').datebox('getValue');
          // var monkeyidMax = $('#monkeyidMax').val();
          // var monkeyidMin = $('#monkeyidMin').val();
           var quyu = $('#quyu').combotree('getValue');
           var path = sybp()+'/individualAction_export.action?monkeyid='+monkeyid+'&animaltype='+animaltype
           +'&sex='+sex+'&keeper='+keeper+'&yjaddress='+yjaddress+'&sourceaddress='+sourceaddress+'&mincurrentweight='+mincurrentweight
           +'&maxcurrentweight='+maxcurrentweight+'&birthdaymin='+birthdaymin+'&birthdaymax='+birthdaymax+'&yjdatemin='
           +yjdatemin+'&yjdatemax='+yjdatemax+'&monkeyidMax='+monkeyidMax+'&monkeyidMin='+monkeyidMin+'&quyu='+quyu;
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
   
   /**添加操作*/
   function onAddButton(){
   		initBlongareaCombobox();
      document.individualAddEditForm.reset();
      $('#oldMonkeyid').val('');
      showIndividualAddEditDialog('afterAddDialog','add','添加个体');
      addOreditanimaltypeLondCombobox();
      keeperNoLondCombobox();
      initSourceCombobox();
      initAddOrEdityjaddress();
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
        fullIndividualDialog(row.id);
        initBlongareaCombobox();
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
		  	            //$('#blongarea').val(r.blongarea);
		  	           // $('#room').combotree('setValue',r.room);
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
		  	            $('#yjaddress').val(r.yjaddress);
		  	            $('#yjdate').datebox('setValue',r.yjdate);
		  	            $('#ysz').val(r.ysz);
		  	            $('#chipid').val(r.chipid);
		  	            $('#remark').val(r.remark);
						$('#AddOrEdityjaddress').combobox('select',r.yjaddress);
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
			<div title="个体档案" border="false" style="overflow: auto;">
			    <div style="height:120px;background-color:#fafafa; ">
			     <form id="monkeyidForm" action="" method="post">
			    
			     <table class="tableCss">
			       <tr><td  >动物编号</td><td><input id="monkeyid" type="text" name="monkeyid" class="easyui-validatebox" data-options="validType:'maxLength[20]'"  style="width:100px;"></input></td>
			           <td>动物种类</td><td>
				           <select id="animaltype" class="easyui-combobox" name="animaltype" style="width:80px;"  data-options="panelHeight:100" >  
							</select>
			           </td>
			           <td>动物性别</td><td>
			           <select id="sex" class="easyui-combobox" name="sex" style="width:80px;"  data-options="panelHeight:100" >  
			                <option value="-1">全部</option>   
						    <option value="0">公</option>   
						    <option value="1">母</option>   
						</select>  </td>
			           <td>饲养员</td><td><input id="keeper" type="text" name="keeper" class="easyui-validatebox" data-options="validType:'maxLength[20]'"  style="width:100px;"></input></td>
			          
			       </tr>
			         <tr>
			           <td>区域</td><td colspan="1"><input id="quyu" type="text" name="quyu" class="easyui-combobox"  data-options="validType:'maxLength[20]'"  style="width:100px;"></input></td>
			           <td>房舍</td><td colspan="1"><input id="roomCondition" type="text" name="roomCondition" class="easyui-combobox" data-options="validType:'maxLength[20]'"  style="width:80px;"></input></td>
			          
			           <td>出生日期</td><td colspan="3"><input id="birthdaymin" type="text" name="birthdaymin" class="easyui-datebox"   style="width:120px;"/>
			            &nbsp; 至  &nbsp;  <input id="birthdaymax" type="text" name="birthdaymax" class="easyui-datebox" style="width:120px;"/></td>
			          
			            <td  colspan="5"  ><a id="searchButton" class="easyui-linkbutton" onclick="onSearchButton();" data-options="iconCls:'icon-search',plain:true">搜索</a>
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