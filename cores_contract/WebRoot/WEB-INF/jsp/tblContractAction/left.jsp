<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>leftContract</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/home_left.css"  />
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/index.js"></script>
<script type="text/javascript">

	var tableHeight;//当前页面可见区域高度 - 162
	var tableWidth;
	
	$(function(){
		tableHeight = document.body.clientHeight - 180;//318
		tableWidth  = document.body.clientWidth-10;//174
		
		//初始化contractCodeTable
		$('#contractCodeTable').datagrid({
			title:'',
			height:tableHeight,
			width:tableWidth,
			nowarp:  false,//单元格里自动换行
			singleSelect:true,
			columns :[[{
				title:'合同编号',
				field:'contractCode',
				width:157,
				halign:'center',
				align:'center'
			},{
				title:'id',
				field:'id',
				width:10,
				halign:'center',
				align:'center',
				hidden:true
			}]],
			onSelect:function(rowIndex, rowData){
				window.open(sybp()+'/tblContractAction_main.action?currentId='+rowData.id,'main');
			},
			onLoadSuccess:function(data){
				if(data && data.currentId){
					var rows = data.rows;
				    var rowIndex = -1;
				    if( rows.length){
					    for(var i = 0;i <rows.length; i++){
						    if(data.currentId == rows[i].id){
							    rowIndex = $('#contractCodeTable').datagrid('getRowIndex',rows[i]);
						    	$('#contractCodeTable').datagrid('selectRow',rowIndex);
							}
						}
					}
				}
			}
		});


		//初始化合同编号下拉框
		$('#codeCombobox').combobox({
			onChange:function(newValue, oldValue){
				if (/^@.*$/.test(newValue) && newValue) {　//是@开头的　
				　　$('#codeCombobox').combobox('select',newValue);
					$('#nameCombobox').combobox('clear');
					$.ajax({    
					    url:sybp()+'/tblContractAction_loadMinMaxDate.action?contractCode='+ encodeURIComponent(newValue),
					    type:'post',
					    dataType:'json',
					    success:function(r){
					    	if(r && r.success){
					    		$('#mindatebox').datebox('setValue', '');
					    		$('#maxdatebox').datebox('setValue', r.maxDate);
					    		$('#mindatebox').datebox('setValue', r.minDate);

					    		//$('#nameCombobox').combobox('loadData',r.data);
					    		//$('#nameCombobox').combobox('setValue',r.data.text);
						    }
					    }   
					});
				}else if(oldValue && !newValue){
					$('#codeCombobox').combobox('clear');
					$('#codeCombobox').combobox('loadData',[]);
					$('#codeCombobox').combobox('hidePanel');
					//$('#nameCombobox').focus();
					//document.getElementById("codeCombobox").blur();
					var minDate = $('#mindatebox').datebox('getValue');
					$('#mindatebox').datebox('setValue', '');
		    		$('#mindatebox').datebox('setValue', minDate);
				} else {　　　　
					$.ajax({    
					    url:sybp()+'/tblContractAction_loadCodeComboboxData.action?contractCode='+ encodeURIComponent(newValue),
					    type:'post',
					    dataType:'json',
					    success:function(r){
					    	if(r){
					    		$('#codeCombobox').combobox('loadData',r);
						    }
					    }   
					}); 
				}
			}
		});
		
		//初始化客户名称下拉框
		$('#nameCombobox').combobox({
			onChange:function(newValue, oldValue){
				if (/^(\d)*$/.test(newValue) && newValue) {　//是数字　
				　　$('#nameCombobox').combobox('select',newValue);
					$('#codeCombobox').combobox('clear');
					$.ajax({    
					    url:sybp()+'/tblContractAction_loadMinMaxDate.action?sponsorId='+ encodeURIComponent(newValue),
					    type:'post',
					    dataType:'json',
					    success:function(r){
					    	if(r && r.success){
					    		$('#mindatebox').datebox('setValue', '');
					    		$('#maxdatebox').datebox('setValue', r.maxDate);
					    		$('#mindatebox').datebox('setValue', r.minDate);
						    }
					    }   
					});
				}else if(oldValue && !newValue){
					$('#nameCombobox').combobox('clear');
					$('#nameCombobox').combobox('loadData',[]);
					$('#nameCombobox').combobox('hidePanel');
					//$('#nameCombobox').focus();
					//document.getElementById("codeCombobox").blur();
					var minDate = $('#mindatebox').datebox('getValue');
					$('#mindatebox').datebox('setValue', '');
		    		$('#mindatebox').datebox('setValue', minDate);
				}  else {　　　　
					$.ajax({    
					    url:sybp()+'/tblContractAction_loadNameComboboxData.action?customerName='+ encodeURIComponent(newValue),
					    type:'post',
					    dataType:'json',
					    success:function(r){
					    	if(r){
					    		$('#nameCombobox').combobox('loadData',r);
						    }
					    }   
					}); 
				}
			}
		});


		//初始化日期选择框
		$('#mindatebox').datebox({
			onChange: function(newValue, oldValue){
				if(newValue){
					dataChange();
				}
		    }
		});
		//初始化日期选择框
		$('#maxdatebox').datebox({
			onChange: function(newValue, oldValue){
				if(newValue){
					dataChange();
				}
		    }
		});
		//默认日期区间
		$('#mindatebox').datebox('setValue',$('#minDate').val());
		$('#maxdatebox').datebox('setValue',$('#maxDate').val());
		//显示整个页面
		$('#tableview').css('display','');
				
	});//匿名函数end
	
	//日期选择框change事件
	function dataChange(){
		var contractCode = $('#codeCombobox').combobox('getValue');
		var sponsorId = $('#nameCombobox').combobox('getValue');
		var minDate = $('#mindatebox').datebox('getValue');
		var maxDate = $('#maxdatebox').datebox('getValue');
		//alert(/@\w+/.test('@'));
		if(minDate && maxDate){
			$.ajax({
				url:sybp()+'/tblContractAction_loadContractCode.action',
				type:'post',
				data:{
					minDate:minDate,
					maxDate:maxDate,
					contractCode:contractCode,
					sponsorId:sponsorId
				},
				dataType:'json',
				success:function(r){
					if(r && r.success){
						$('#contractCodeTable').datagrid('loadData',r);
					}
				}
			});
		}
	}
	//左边导航(left1)被选中是调用
	function selectedLeft1(){
		var selected = $('#contractCodeTable').datagrid('getSelected');
		if(selected){
			window.open(sybp()+'/tblContractAction_main.action?currentId='+selected.id,'main');
		}else{
			window.open(sybp()+'/tblContractAction_main.action','main');
		}
	}
	
	//main 来调用
	function updateCodeCombobox(currentCode){
		if(currentCode){
			$('#codeCombobox').combobox('clear');
			$('#codeCombobox').combobox('loadData',[{id:'@'+currentCode,text:currentCode}]);
			$('#codeCombobox').combobox('select','@'+currentCode);
			parent.selectAccordion(1);
		}
	}
	//main 来调用
	function updateNameCombobox(id,currentName){
		if(id && currentName){
			$('#nameCombobox').combobox('clear');
			$('#nameCombobox').combobox('loadData',[{id:id,text:currentName}]);
			$('#nameCombobox').combobox('select',id);
			parent.selectAccordion(1);
		}
	}
	
</script>

</head>

<body>
<s:hidden id="minDate" name="minDate"/>
<s:hidden id="maxDate" name="maxDate"/>
<table id= "tableview" style="display:none;">
	<tr>
		<td width="120px">
			<div style="position:relative;background-color:#f2f2f2;   top:0px;left:0px;width:166px; height:142px; padding:5px; margin:0; border:1px solid #c8c8c8;">

		        <div style="margin-bottom:3px;">合同编号</div>
		        <div style="margin-bottom:3px;">
			        &nbsp;&nbsp;&nbsp;&nbsp;<input id="codeCombobox" class="easyui-combobox" style="width:146px;height:19px;" data-options="valueField:'id',textField:'text'" />  
		        </div>
		        <div style="margin-bottom:3px;">客户名称</div>
		        <div style="margin-bottom:3px;">
			        &nbsp;&nbsp;&nbsp;&nbsp;<input id="nameCombobox" class="easyui-combobox" style="width:146px;height:19px;" data-options="valueField:'id',textField:'text'" />  
		        </div>
		        <div style="margin-bottom:3px;">合同日期范围</div>
		        <div style="margin-bottom:3px;">
			        &nbsp;&nbsp;&nbsp;&nbsp;从&nbsp;&nbsp;<input id="mindatebox" type="text" class="easyui-datebox" style="width:127px;height:19px;" editable="false"></input>	
		        </div>
		        <div style="margin-bottom:3px;">
			       	&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;<input id="maxdatebox" type="text" class="easyui-datebox" style="width:127px;height:19px;" editable="false"></input>   	
		        </div>
	         </div>
		</td>
	</tr>
	<tr>
	<td>
		<div style=" position:relative; width:200px; height:330px; margin:0px; border: 0px solid #c8c8c8; overflow-x:auto; overflow-y:auto;">
		 	<table id="contractCodeTable"></table> 
		</div>
	</td>
	</tr>
</table>         
</body>
</html>
