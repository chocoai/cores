<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/tblAnimalHouse.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/schedule.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/public.js" charset="utf-8"></script>
<script type="text/javascript">
	var taskKind;
	var allLeaderTable;
	var studyNoTable;
	var schedulePlanTable;
	var taskNameTable;
	var registerFinishTable;
	var oneDaySchedulePlanTable;
	var realName;
	
	 var tableHeight;//当前页面可见区域高度 - 86
	 var tableWidth;
	 
	$(function(){
	   
	      realName = $("#realName11").combotree({
			   url:sybp()+'/tblSchedulePlanAction_loadComboTreeData.action',
			   multiple:false,//设置是否多选
			   onChange: function(newValue,oldValue){
			       $("#thisSelectUserName").val(newValue);
			       $('#selectqianming_message').html("");			       
		       }
		  });
	
	    var tableHeightt =  parent.parent.parent.tableHeight() - 260;
	    var tableWidthtt  = parent.parent.parent.tableWidth() - 205;
        //根据任务类型生成不同的表格
		taskKind = $('#taskKind').val();
		
		$('#showStartime').datebox({
		     width:90,
		     onSelect: function(date){
			     if(document.getElementById("checkboxOneDay").checked){
			        $('#showEndtime').datebox('setValue', date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate());
			     }
		     }
		   });
		$('#showEndtime').datebox({width:90,'disabled':false, onSelect: function(date){}});
		$('#showStartime').datebox('setValue', $('#startime').val());
		$('#showEndtime').datebox('setValue', $('#endtime').val());
		
		schedulePlanTable=$('#schedulePlanTable').datagrid({
			height:tableHeightt,
			width:tableWidthtt,
			fit:false,
			fitColumns:false,
			resizable:true,
			frozenColumns:[[
			                {field:'dateCol',title:'日期',width:100,rowspan:2,halign:'center'}
			           ]],
			onClickRow :function(rowIndex, rowData){
			$(this).datagrid('unselectAll');
			$(this).datagrid('selectRow',rowIndex);
		    },
		    rowStyler: function(index,row){
			    if(row.dateCol.indexOf('六') != -1 ){
					return 'background-color:#F2F2F2;color:#080808;';
				}else if(row.dateCol.indexOf('日') != -1){
					return 'background-color:#BFEFFF;color:#080808;';
				}	
			}
        });
		 $.ajax({
				url : sybp()+'/tblSchedulePlanAction_getSchedulePlandatagrid.action',
				type: 'post',
				data: {
			         startTime: $('#startime').val(),
	                 endTime:$('#endtime').val(),
	                 allDate:true,
	                 taskKind:taskKind
				},
				dataType:'json',
				success:function(r){
					if(r){
						schedulePlanTable.datagrid({
							columns:r.columns
						});
						schedulePlanTable.datagrid('loadData',r.rows);
					}
				},
				beforeSend:function(){ 
				        //$('#progressbar').css('display',''); 
			            var value = $('#p').progressbar('getValue');
			            if(value == 100){
		            	  value = 10;
		                }  
						if (value < 100){
							value += Math.floor(Math.random() * 10);
							$('#p').progressbar('setValue', value);
							setTimeout(arguments.callee, 200);
						}
                }, 
                complete:function(){ 
                   $('#progressbar').css('display',''); 
                   $('#p').progressbar('setValue', 100);
                   $('#progressbar').css('display','none'); 
                } 
         });
         
         $('#p').progressbar({ 
             text: "数据加载中..." 
          }); 
         
         $('#registerTime').datebox({    
            onSelect :function(date){
               var myDate = new Date();
               var datea=dateCompare(myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate(),date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate());
              if(datea){
                 $('#saveDialogButton1').linkbutton('disable');
              }else{
                 $('#saveDialogButton1').linkbutton('enable');
              }
               selectRegisterFinishTable();
            }
         });  
		 selectStudyNoTable();
	   	 selectAllUserDialong();
	 	 AnimalHouseRes();
	 	 selecttaskNameTable();
	 	 $('#selectLayoutMainDiv').css('display','');   
	 	//document.onkeydown = KeyDown;
        });//匿名函数结束
       //单日勾选
       function checkboxOneDay(){
           if(document.getElementById("checkboxOneDay").checked){
		     	document.getElementById("checkboxOneDay").checked = false;
		     	$('#showEndtime').datebox({'disabled':false});
		   }else{
			    document.getElementById("checkboxOneDay").checked = true;
			    var startime= $('#showStartime').datebox('getValue');
		     	$('#showEndtime').datebox('setValue', startime);
			    $('#showEndtime').datebox({'disabled':true});
		   }
       }
       function afterCheckboxOneDay(obj){
          if(obj.checked == true){
                 var startime= $('#showStartime').datebox('getValue');
		         $('#showEndtime').datebox('setValue', startime);
		     	 $('#showEndtime').datebox({'disabled':true});
		   }else{
		       $('#showEndtime').datebox({'disabled':false});
			   
		   }
       }
	   function makeFullScreen() {
	    
	      var fullScreen = $('#fullScreen').val();
	      if(fullScreen != "all"){
	         var tableHeight = parent.parent.parent.window.screen.height;
		     var tableWidth  = parent.parent.parent.window.screen.width;		 
	          parent. parent.parent.makeFullScreen();
	          parent.parent.sidebardisplayNone();	
	          parent.makeFullScreenlist();
	          $("#selectLayoutMainDiv").css("height",'100%');
              $("#selectLayoutMainDiv").css("width",'100%');
		      $('#layoutDiv1').layout("resize",{ height:tableHeight-30,width:tableWidth-28});
		      $('#layoutDiv1').layout("panel","center").panel("resize",{ height:tableHeight-30,width:tableWidth-28});
		      var select =  $('#selectConditions').val();
		      if(select == "one"){
		        $('#oneDaySchedulePlanTable').datagrid({height:tableHeight-168,width:tableWidth-29});
		      }else{
		        $('#schedulePlanTable').datagrid({height:tableHeight-168,width:tableWidth-29});
		      }
			 
	          $('#fullScreen').val("all");
	      }else{
	          var tableHeight = parent.parent.parent.tableHeight();
		      var tableWidth  = parent.parent.parent.tableWidth();		
	          parent.parent.parent.reset();
	          parent.parent.sidebardisplayshow();	
	          parent.resetScreenlist();
	          $("#selectLayoutMainDiv").css("height",'98%');
              $("#selectLayoutMainDiv").css("width",'98%');
		      $('#layoutDiv1').layout("resize",{ height:tableHeight-65,width:tableWidth-190});
		      $('#layoutDiv1').layout("panel","center").panel("resize",{ height:tableHeight-65,width:tableWidth-190});
		      var  tableHeight1 = document.body.clientHeight - 145;
	          var  tableWidth1  = document.body.clientWidth-6;
	          var select =  $('#selectConditions').val();
		      if(select == "one"){
		         $('#oneDaySchedulePlanTable').datagrid({height:tableHeight1,width:tableWidth1-18});
		      }else{
		          $('#schedulePlanTable').datagrid({height:tableHeight1,width:tableWidth1-18});
		      }
	          $('#fullScreen').val("reset");
	      }
      }
	    
       
	
	function selectTheUserName(){
	    var name =  $("#thisSelectUserName").val();
	    $('#realName11').combotree('setValue', name);
	    var name1  =$('#realName11').combotree('getText');
	       $('#selectqianming_message').html("");
        if(name == name1){
           $('#realName11').combotree('setValue', "");
           $('#selectqianming_message').html("用户名错误");
        }
	    
	}
	 
	 function selectUserNameonBlur(){
	  if(event.keyCode == 13){
	 	selectTheUserName();
		selectqm_passwordCheck();
	 }
    }
    
    function makeSureTime(){
      selectStudyNoTable();
    }
    
</script>
</head>
<body >
  <div id="selectLayoutMainDiv"  style="width:99.6%;height:100%; display:none;"> 
    <s:hidden id="selectConditions" name="selectConditions" ></s:hidden>
    <!-- 任务类型 -->
    <s:hidden id="layoutHeight" name="layoutHeight" value="515"></s:hidden>
	<s:hidden id="taskKind" name="taskKind"></s:hidden>
	<s:hidden id="endtime" name="endtime"></s:hidden>
	<s:hidden id="startime" name="startime"></s:hidden>
	<s:hidden id="fullScreen" name="fullScreen"></s:hidden>
	<div id="layoutDiv1" class="easyui-layout" fit="true" border="false"  >
		<div region="north" border="false"  style="height:30px;">
		<div style="margin-left:20px;">
	      <a id="selectSchedulePlanButton" style="overflow:auto;margin-top: 0px;" class="easyui-linkbutton" onclick="selectConditionsButton();" data-options="iconCls:'icon-search',plain:true">条件查询</a>
	      <!--  <a id="selectScheduleButton" style="overflow:auto;margin-top: 0px;" class="easyui-linkbutton" onclick="selectOneConditionButton();" data-options="iconCls:'icon-search',plain:true">单日查询</a> -->
	      <a id="taskFinishRegisterButton" style="overflow:auto;margin-top: 0px;" class="easyui-linkbutton" onclick="taskFinishRegisterButton();" data-options="plain:true">任务完成登记</a>
	      <a id="easyui-linkbutton" style="overflow:auto;margin-top: 0px;" class="easyui-linkbutton" onclick="makeFullScreen();" data-options="iconCls:'icon-monitor',plain:true">全屏/退出</a>
		</div>
		</div>
		<!-- table start -->
		<div  region="center" border=false style="border: 0px solid #c8c8c8;">
		    <div id="progressbar" style="position:absolute;z-index:100;width:400px;margin-top:-100px;margin-left:-200px;left:50%;top:50%;display:'';">
		    <div id="p" class="easyui-progressbar" style="width:400px;"></div>
		    </div>
		    <div id="schedulePlanTableDiv" >
			<table id="schedulePlanTable" style="display:'';" ></table></div>
			<div id="oneDaySchedulePlanTableDiv"  >
			<table id="oneDaySchedulePlanTable" style="display:none" ></table>
			</div>
			<br/>
			<div >
			<a  style="overflow:auto;margin-top: 0px;" 
			class="easyui-linkbutton" onclick="" data-options="iconCls:'',plain:true">描述</a>
	        <textarea id= "description" cols="125" rows="3"  style="overflow:auto;" readonly="readonly" ></textarea>
	       </div>
		</div>
		<!-- table end -->
	</div>
          <%@ include file="/WEB-INF/jsp/public/qianming.jspf"%>
	      <%@ include file="/WEB-INF/jsp/public/fuheqianming.jspf" %>
	      <%@ include file="/WEB-INF/jsp/tblSchedulePlanAction/selectionConditions.jspf" %>
	      <%@ include file="/WEB-INF/jsp/tblSchedulePlanAction/selectOneConditions.jspf" %>
	      <%@ include file="/WEB-INF/jsp/tblSchedulePlanAction/registerFinishTask.jspf" %>
	      <%@ include file="/WEB-INF/jsp/public/selectUserName.jspf" %>	
 </div>
	      
</body>
</html>