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
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/schedulePlan.js" charset="utf-8"></script>
<style type="text/css">
    .tree-icon{
    	width:0px;
    }
    </style>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/tblTiprpAppInd.css" media="screen" />
<script type="text/javascript">
	var tblSchedulePlanTable;
	var taskNameTable;
	var studyNoPara=$('#studyNoPara').val();
	var taskItemTypecomtree;
	$(function(){

	//添加日程安排的时候，任务类型的树形下拉选
	
		   //taskItemTypecomtree = $("#taskItemTypecomtree").combotree({
		  
		   taskItemTypecomtree = $("#taskItemTypecomtree").tree({
			   url:sybp()+'/tblSchedulePlanAction_loadComboTreeData.action',
			   
		   });
		   
		    taskNameTable=$('#taskNameTable').datagrid({
			url : sybp()+"/tblSchedulePlanAction_selectTaskItemType.action?studyNoPara="+$('#studyNoPara').val(),
			title:'',
			height: 445,
			width:140,
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
				title : '任务类型',
				field : 'taskName',
				width : 60,
				align :'center',
			}]],
			onClickRow :function(rowIndex, rowData){	
		   	  $('#tblSchedulePlanTable').datagrid('rejectChanges');
			   var rows =$('#tblSchedulePlanTable').datagrid('getRows');
			  var length = rows.length;
			  var j = 0;
		          for(var i=0;i<length;i++){
		        	 var PlantaskName =  $('#tblSchedulePlanTable').datagrid('getRows')[j].taskName;
		             if(rowData.taskName == "全部"){
		        	    break;
		             }else{
		            	 if(PlantaskName != rowData.taskName ){
			        	       $('#tblSchedulePlanTable').datagrid('deleteRow',j);
			        	  }else{
			                   j++;
			             }
			         }
		             
		           }	 
		    }      
		}); //end 
		
		tblSchedulePlanTable=$('#tblSchedulePlanTable').datagrid({
			url : sybp()+"/tblSchedulePlanAction_selecttblSchedulePlan.action?studyNoPara="+$('#studyNoPara').val(),
			title:'',
			height: 445,
			width:620,
			iconCls:'',
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
	        },{	title : 'id',
				field : 'scheduleID',
				width : 85,
				hidden:'true'
			},
			{	title : '首次执行日期',
				field : 'showstartTime',
				width : 85,
				align :'center'
			},{
				title : '结束日期',
				field : 'showendTime',
				width : 85,
				align :'center',
				formatter: function(value,row,index){
				   var startTime = $('#tblSchedulePlanTable').datagrid('getRows')[index].showstartTime;
				   var endTime = $('#tblSchedulePlanTable').datagrid('getRows')[index].showendTime;
				   if(startTime == endTime){
					   return  '';
			       }else{
			           return value;
			       }
				}
			},{
				title :'周期',
				field :'period',
				width :70,
				align :'center',
				formatter: function(value,row,index){
				var periodUnit = $('#tblSchedulePlanTable').datagrid('getRows')[index].periodUnit;
				var startTime = $('#tblSchedulePlanTable').datagrid('getRows')[index].showstartTime;
				var endTime = $('#tblSchedulePlanTable').datagrid('getRows')[index].showendTime;
				if(startTime == endTime){
					return  '单次';
			    }else{
			        if(periodUnit == 1){
						return  '每  '+value+' 天';
					}else if(periodUnit == 2){
						return  '每  '+value+' 周';
					}else if(periodUnit == 3){
						return  '每  '+value+' 月';
					}else if(periodUnit == 4){
						return  '每  '+value+' 年';
				    }
			    }
			  }
			},{
				title :'周期单位',
				field :'periodUnit',
				width :100,
				align :'center',
				hidden:'true'
			},{
				title :'任务类型',
				field :'taskName',
				width :148
			},{
				title :'签字人',
				field :'signName',
				width :80,
				hidden:'true'
			},{
				title :'',
				field :'remark',
				width :80,
				hidden:'true'
			},{	title : 'taskKind',
				field : 'taskKind',
				width : 85,
				hidden:'true'
			}]],
			onClickRow :function(rowIndex, rowData){	
			   var studyState =  $('#studyState').val();
	  	       if(studyState == "0" || studyState == "3" ){
					  if(rowData.remark == 1){
						   rowData.remark = 2 ;
						   $(this).datagrid('refreshRow',rowIndex);
						   $(this).datagrid('selectRow',rowIndex);
						   $(this).datagrid('checkRow',rowIndex);
						}else if(rowData.remark == 2){
							 rowData.remark = 1;
							 $(this).datagrid('refreshRow',rowIndex);
							 $(this).datagrid('unselectRow',rowIndex);
							 $(this).datagrid('uncheckRow',rowIndex);
					    }
					    //编辑  editSchedulePlanButton
					    //删除  delSchedulePlanButton
					    //签字  signSchedulePlanButton
					     var rows = $(this).datagrid('getSelections');
					     if(rows.length > 1){
					          $('#editSchedulePlanButton').linkbutton('disable');
					     }else{
					          $('#editSchedulePlanButton').linkbutton('enable');
					     }
					     
					     //$('#signSchedulePlanButton').linkbutton('enable');
					    // for(var i = 0;i<rows.length;i++){
					     //   if(rows[i].signName != ""){
					    //        $('#signSchedulePlanButton').linkbutton('disable');
					    //        break;
					    //    }
					    // }
					     
					     $('#delSchedulePlanButton').linkbutton('enable');
					      for(var i = 0;i<rows.length;i++){
					        if(rows[i].signName != ""){
					            $('#delSchedulePlanButton').linkbutton('disable');
					            break;
					        }
					     }
			     }

			},
			rowStyler: function(index,row){
				 if (row.remark == 1 ){
					return 'background-color:#F2F2F2;color:#080808;';
				 }else if(row.remark == 2){
					 return 'background-color:#BFEFFF;color:#fff;';
			    }	
			},
			onCheckAll:function(rows){
                for(var i = 0 ;i<rows.length;i++){
                	if(rows[i].remark == 1){
                		rows[i].remark = 2 ;
                		 $(this).datagrid('refreshRow',i);
     				}
                  }
			},
			onUncheckAll:function(rows){
                for(var i = 0 ;i<rows.length;i++){
                	if(rows[i].remark == 2){
     					rows[i].remark = 1;
     					 $(this).datagrid('refreshRow',i);
     			    }
                  }
			},
			          
		}); //end 
		usetblWeighInd();
		//表头居中
		$('.datagrid-header div').css('textAlign','center');
		//显示整个布局
		$('#SchedulePlanMainDiv').css('display','');  
		$('#toolbar').css('display',''); 
		 CustomerInspection();
		 $('#periodUnit').combobox('setValue', '  ') ;
		 $('#periodUnit').combobox({
			    onSelect: function(param){
			    var val = $('#periodUnit').combobox('getText');
			    if( val== "&nbsp;"){
			    	 $('#periodUnit').combobox('setValue', '  ') ;
		        }
		   }
		});
		 $('#updateperiodUnit').combobox('setValue', '  ') ;
		 $('#updateperiodUnit').combobox({
			    onSelect: function(param){
			    var val = $('#updateperiodUnit').combobox('getText');
			    if( val== "&nbsp;"){
			    	 $('#updateperiodUnit').combobox('setValue', '  ') ;
		        }
		   }
			
		});

		 SelectTaskEndType();
		 SchedulePlannumber();
		 updateSelectTaskEndType();
		 updatSchedulePlannumber();
		 initTblStudyInfo();
		 initButton();
	});
	//初始化按钮
	function initButton(){
	  	  var studyState =  $('#studyState').val();
	  	  if(studyState == "0" || studyState == "3" ){
	  	    // $('#printSchedulePlanButton').linkbutton('enable');
	  	     $('#addSchedulePlanButton').linkbutton('enable');
	  	     $('#editSchedulePlanButton').linkbutton('disable');
	  	     $('#delSchedulePlanButton').linkbutton('disable');
	  	     $('#selectResButton').linkbutton('enable');
	  	    // $('#signSchedulePlanButton').linkbutton('enable');
	  	    // $('#applyChangeButton').linkbutton('disable');
	  	  }else{
	  	     //$('#printSchedulePlanButton').linkbutton('disable');
	  	     $('#addSchedulePlanButton').linkbutton('disable');
	  	     $('#editSchedulePlanButton').linkbutton('disable');
	  	     $('#delSchedulePlanButton').linkbutton('disable');
	  	     $('#selectResButton').linkbutton('disable');
	  	     //$('#signSchedulePlanButton').linkbutton('disable');
	  	   	 //$('#applyChangeButton').linkbutton('enable');
	  	  }
	  	  
	}
	//初始化区域选择按钮
	function initTblStudyInfo(){
	      var studyNo = $("#studyNoPara").val();
	      $.ajax({
	  		url : sybp()+'/tblStudyInfoAction_initTblStudyInfoBut.action',
				type: 'post',
				contentType: "application/x-www-form-urlencoded; charset=utf-8", 
				dataType:'json',
				data:{
				   studyNo:studyNo
				},
				success:function(r){
					if(r.success){
					   $('#tblSchedulePlanTable').datagrid({title:"试验安置区域 : "+r.resName});
					  if(r.sign){
					      $('#selectResButton').linkbutton('disable');
					  }
				    }else{
				      $('#tblSchedulePlanTable').datagrid({title:"试验安置区域：无"});
					}
				}
	       });
	}
	
    //esType:16 删除日程
    function  afterDelSchedulePlanButton(){
	    var rows = $('#tblSchedulePlanTable').datagrid('getChecked');
        var ary = new Array();
        if(rows.length > 0){
        	 for(var j=0;j<rows.length;j++){
   				var scheduleID  =  rows[j].scheduleID;
   				var gettaskName =  rows[j].signName;
   				if( gettaskName == "" ){
   					ary = ary.concat(scheduleID);
   	  			}
   		      }
            var getSelections = ary.join(",");
            $.ajax({
    			url : sybp()+'/tblSchedulePlanAction_delSchedulePlan.action',
    			type: 'post',
    			contentType: "application/x-www-form-urlencoded; charset=utf-8", 
    			data: {
    				getTaskNames:getSelections,
    				esType:16,
    			},
    			dataType:'json',
    			success:function(r){
    				if(r.success){
    					$('#tblSchedulePlanTable').datagrid('reload');
    					$('#tblSchedulePlanTable').datagrid('uncheckAll');
    					parent.parent.showMessager(1,'删除成功',true,5000);
    				}
    			}
            });
            
      }else{
    	  $.messager.alert('提示','请选择要删除的日程','info');
      }
      
     }
  
    function  delSchedulePlanButton(){
    	  var rows = $('#tblSchedulePlanTable').datagrid('getChecked');
          if( rows.length > 0){
              var flage = true;
              
        	  for(var j=0;j<rows.length;j++){
  				var scheduleID  =  rows[j].scheduleID;
  				var gettaskName =  rows[j].signName;
  				if( gettaskName != "" ){
  					 $.messager.alert('提示','已签字日程无法删除','info');
  					 flage = false;
  					 break;
  	  			}
  		      }
  		      if(flage){
  		    	qm_showQianmingDialog('afterDelSchedulePlanButton');
  	  		  }
        	  

          }else{
        	  $.messager.alert('提示','请选择要删除的日程','info');
          }
          
    }

    function updatSchedulePlannumber(){
    	var checkOne = document.getElementById('updateselectOne'); 
       	var checkMore = document.getElementById('updateselectMore'); 
       	if(checkOne.checked){
       	$('#updateendTimeTr').css('display','none'); 
       	$('#updateperiodTr').css('display','none');
       	$('#updatestartTimeTr').val('执行日期');
       	$('#updatetaskEndTypeTr').css('display','none');
        $('#updatetaskEndNumTr').css('display','none');
        
       	}else if(checkMore.checked){
       	$('#updateendTimeTr').css('display',''); 
       	$('#updateperiodTr').css('display',''); 
       	$('#updatestartTimeTr').val('首次执行日期');
       	$('#updatetaskEndTypeTr').css('display','');
       	updateSelectTaskEndType();
       	}
    }
    
    //单选框选择次数时间
    function updateSelectTaskEndType(){
    	var checkOne = document.getElementById('updateselectTime'); 
    	var checkMore = document.getElementById('updateselectNum'); 
    	if(checkOne.checked){
    	$('#updateendTimeTr').css('display',''); 
        $('#updatetaskEndNumTr').css('display','none');
    	}else if(checkMore.checked){
    	$('#updateendTimeTr').css('display','none');
    	$('#updatetaskEndNumTr').css('display',''); 
    	}
    }

    //查看同类日程安排
    function schedulePlanSelectOther(){
       var studyNoPara=$('#studyNoPara').val();
       var taskName = $('#taskName').val();
       if(taskName == ""){
    	   $.messager.alert('提示','任务名称不能为空','info');
       }else{
           var url = '${pageContext.request.contextPath}/tblSchedulePlanAction_jointInquirySchedulePlan.action?isValidationPara=1&disPlaytype=1&studyNoPara='+studyNoPara+'&taskName='+ encodeURIComponent(taskName);
    	   //window.showModelessDialog(url, '查看日程',  "status:no;left:yes;scroll:yes;resizable:yes;help:no;dialogWidth:800px;dialogHeight:600px;minimize:yes;maximize:yes;");
           window.open(url,'查看日程','width=800,height=600');
       }
    }
    
    function sameTaskName(){
       var pid = $("#taskItemType").val();
       var name = $("#taskName").val();
       if( pid == "0" || pid == "" ){
          $.ajax({
			url : sybp()+'/tblSchedulePlanAction_sameTaskName.action?sameTaskName='+encodeURIComponent(name),
			type: 'post',
			contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			dataType:'json',
			success:function(r){
				if(r.success){
					
				}else{
				 $.messager.alert('提示','该任务名称已存在于'+r.msg+'下','info');
				 $("#taskName").val('');
				 $("#taskName").focus();
				}
			}
        });
       }
    }
function selectResButton(){
    loadAnimalHouseTable();
    $('#selectStudyResDialog').dialog('open');
	$('#selectStudyResDialog2').dialog('open');
}
    //加载试验区域
function loadAnimalHouseTable(){
          $('#AnimalHouseTable').combotree({
				url:sybp()+'/tblStudyInfoAction_loadRes.action',
				valueField:'id',
				textField:'text',
		        width:150,
		        height:19,
				onSelect: function(node){ 
				   var tree = $(this).tree;  
                  //选中的节点是否为叶子节点,如果不是叶子节点,清除选中  
                   var isLeaf = tree('isLeaf', node.target);  
			       if (!isLeaf) {  
			            //清除选中  
			           $('#AnimalHouseTable').combotree('clear');  
			           $('#AnimalHouseTable').combotree('onShowPanel'); 
			            
			       }  
 				},
 				onLoadSuccess:function(){
 				}
			});
}
//保存所选择区域
function saveStudyRes(){
  //alert("保存");
  var studyNo = $("#studyNoPara").val();
  var res =  $('#AnimalHouseTable').combotree("getValue");
  $.ajax({
  		url : sybp()+'/tblStudyInfoAction_saveRes.action',
			type: 'post',
			contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			dataType:'json',
			data:{
			   studyNo:studyNo,
			   resID:res
			},
			success:function(r){
				if(r.success){
					 $('#selectStudyResDialog').dialog('close');
					  $('#AnimalHouseTable').combotree('clear');  
					  initTblStudyInfo();
			    }else{
				}
			}
        });
  
}
</script>
</head>
<body>
    <div id="SchedulePlanMainDiv"  style="width:100%;height:100%; display:none;">
    <s:hidden id="left_member" name="left_member"></s:hidden>
    <s:hidden id="studyNoPara" name="studyNoPara"></s:hidden>
    <s:hidden id="studyState" name="scheduleState"></s:hidden>
	<div id="toolbar" style="display:none;">
	    <a id="printSchedulePlanButton"  class="easyui-linkbutton" onclick="previewSchedulePlan();" data-options="iconCls:'icon-back',plain:true"">预览</a>
		<a id="addSchedulePlanButton"  class="easyui-linkbutton" onclick="addSchedulePlan(0);" data-options="iconCls:'icon-add',plain:true">增加日程</a>
		<a id="editSchedulePlanButton"  class="easyui-linkbutton" onclick="addSchedulePlan(1);" data-options="iconCls:'icon-edit',plain:true">编辑日程</a>
		<a id="delSchedulePlanButton" class="easyui-linkbutton" onclick="delSchedulePlanButton();" data-options="iconCls:'icon-remove',plain:true">删除</a>
		<a id="selectResButton" class="easyui-linkbutton" onclick="selectResButton();" data-options="iconCls:'icon-house',plain:true">试验安置区域</a>
		
	</div>
	<div class="easyui-layout" fit="true" border="false" >
		<div region="west" border="false" style="width:150px;">
        	<table id="taskNameTable" ></table> 
		</div>
		<div region="center" border="false" style="width:400px;">
			<div class="easyui-layout" fit="true" border="false" >
				<div region="center" border="false">
					<table id="tblSchedulePlanTable" ></table>
				</div>
			</div>
		</div>
	</div>
     <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
     <%@ include file="/WEB-INF/jsp/schedulePlan/addSchedulePlan.jspf" %>
     <%@ include file="/WEB-INF/jsp/schedulePlan/updateSchedulePlan.jspf" %>
     <%@ include file="/WEB-INF/jsp/schedulePlan/selectRes.jspf" %>
	 </div>
</body>
</html>
