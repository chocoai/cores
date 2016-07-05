<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>饲养信息</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/areaAction/areaAddEdit.js" charset="utf-8"></script>
<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 30
	var tableWidth;
	$(function(){
		tableHeight = document.body.clientHeight - 30;
		tableWidth  = document.body.clientWidth;
		//进度条
		$('#p').progressbar({ 
			text: "数据加载中..." 
		}); 
		$('#tabs').tabs({    
		    onSelect:function(title,index){
		      if(index == 1){
		    	  var tab2=$('#tabs').tabs('getTab',index);
		    	  $('#tabs').tabs('update', {
		    			tab: tab2,
		    			options: {
		    				title: '房舍消毒',
		    				content: '<iframe src="' + '${pageContext.request.contextPath}/roomDisinfectRecordAction_list.action'+ ' "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>'
		    			}
		    		});
//		    	  window.open(sybp()+'/roomDisinfectRecordAction_list.action','main');
//		    	  initroomDisinfectTable();
		      }    
		    }    
		});  
				
		//初始化房舍信息表
		$('#areaTable').treegrid({
			url : sybp()+'/areaAction_loadList.action',
			title:'',
			height:tableHeight,
			width:tableWidth,
			idField:'id',    
			treeField:'areanname',   
			animate:true,
			
			columns :[[{
				title:'id',
				field:'id',
				width:150,
				hidden:true
			},{
				title:'区域名',
				field:'areanname',
				width:150
			},{
				title:'所属区域',
				field:'_parentId',
				width:60,
				hidden:true
			},{
				title:'动物类型 ',
				field:'animalType',
				width:60
			},{
				title:'饲养员 ',
				field:'keeperName',
				width:60
			},{
				title:'主管',
				field:'bossName',
				width:60
			},{
				title:'备注',
				field:'remarks',
				width:60
			},{
				title:'档案录入',
				field:'readerName',
				width:60
			},{
				title:'兽医',
				field:'veterinarianName',
				width:60
			}
			]],
			onSelect:function(node){
    		  $('#addAreaButton').linkbutton('enable');
    		  $('#editAreaButton').linkbutton('enable');
    		  $('#deleteAreaButton').linkbutton('enable');
			},
			onLoadSuccess:function(row, data){
				
				$('#progressBar').css('display','none');
			   $('#addAreaButton').linkbutton('enable');
	    	   $('#editAreaButton').linkbutton('disable');
	           $('#deleteAreaButton').linkbutton('disable');
	           $('#printButton_list').linkbutton('enable');
	           var selectId=$('#aid').val();
	           if(selectId){
	        	   $('#areaTable').treegrid('expandTo',selectId);
	                $('#areaTable').treegrid('select',selectId);
	                $('#aid').val("");
	          }
			},
			onBeforeLoad:function(row, param){
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
			toolbar:'#toolbar'
			
	   	});
		 //end treegrid
		
		//显示整个布局
		$('#layoutMainDiv').css('display','');
    });//匿名函数结束


	
	//添加按钮事件
    function onAddButton(){
    	showAreaAddEditDialog('afterAddDialog','add','添加区域信息');
    }
    
	//	添加Dialog后事件
    function afterAddDialog(){
       parent.showMessager(1,'添加成功',true,5000);
       $('#areaTable').treegrid('reload');
    }
    //wan 编辑按钮事件
    function onEditButton(){
         var row= $('#areaTable').treegrid('getSelected');
        if(row != null ){
        	showAreaAddEditDialog('afterEditDialog','edit','编辑区域信息');
        }else{
           $.messager.alert('提示','请选择编辑的区域!');
        }
    }
   //编辑后事件
     function afterEditDialog(){
    	 parent.showMessager(1,'编辑成功',true,5000);
		 $('#areaTable').treegrid('reload');
     }
  
   
   //删除按钮事件(数据库还保存)
       function onDeleteButton(){
     	  var row= $('#areaTable').treegrid('getSelected');
     	 if(null != row ){
         	 if(row._parentId!=0){
	          	$.messager.confirm('确认对话框', '确认删除此房间？', function(r){
	 				if (r){
	 					delArea(row.id);
	 				}
	 			});
         	 }else{
         		$.messager.confirm('确认对话框', '确认删除此区域？将删除区域下所有房间', function(r){
	 				if (r){
	 					delArea(row.id);
	 				}
	 			}); 
         	 }
         }else{
           $.messager.alert('提示','请选择要删除的区域!');
         }
      }
     //根据Id删除相应员工(数据库还保存)
       function delArea(id){
   	   	$.ajax({
   			url:sybp()+'/areaAction_delArea.action',
   			type:'post',
   			data:{id:id},
   			dataType:'json',
   			success:function(r){
   				if(r && r.success){
   					 parent.showMessager(1,'区域删除成功',true,5000);
   					 $('#areaTable').treegrid('remove',id);
   				}else{
   					 parent.showMessager(2,'区域删除失败',true,5000);
   				}
   			}
   		});
       } 
       //查找按钮事件
     function onSearchButton(roomname){
         if(roomname!=''){
        	 $.ajax({
     			url:sybp()+'/areaAction_getAreaByAreaname.action?areaname='+encodeURIComponent(roomname),
     			type:'post',
     			dataType:'json',
     			success:function(r){
     				if(r){
     					$('#aid').val(r.id); 
     					 var selectId=$('#aid').val();
     			           if(selectId){
     			        	   $('#areaTable').treegrid('expandTo',selectId);
     			                $('#areaTable').treegrid('select',selectId);
     			                $('#aid').val("");
     			          }
     				}else{
     				}
     			}
     		});
         }
     }
      //打印按钮事件
    function onPrintButton(){
        //var row=$('#anatomyReqable').datagrid('getSelected');
    	parent.parent.showMessager(2,'数据加载中...',true,5000);
    	window.location.href=sybp()+'/reportAction_toReport.action';
    }
</script>
</head>
<body>
<!-- 区域id -->
<s:hidden id="aid" name="aid"></s:hidden>
<s:hidden id="onchange" name="onchange"></s:hidden>
<div id = "progressBar" style="position:absolute;z-index:100;left:30%; top:50%; "> <div id="p" style="width:400px;"></div> 
</div>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
 	<div region="center" title="" style="overflow: hidden;">
 	 	<div id="tabs" class="easyui-tabs" fit="true" border="false">
			<div title="饲养信息统计" border="false" style="overflow: auto;">
				<table id="areaTable" ></table>
            </div>
            <div title="房舍消毒" border="false" style="overflow: auto;">
				<table id="roomDisinfectTable" ></table>
            </div>
		</div>
        <div id="toolbar">
        <!-- <a id="addCustomerButton" class="easyui-linkbutton" onclick="onAddButton();" data-options="iconCls:'icon-add',plain:true">添加</a> -->
			<a id="addAreaButton" class="easyui-linkbutton" onclick="onAddButton();"  data-options="iconCls:'icon-groupadd',plain:true,disabled:true">添加</a>
			<a id="editAreaButton" class="easyui-linkbutton" onclick="onEditButton();" data-options="iconCls:'icon-groupedit',plain:true,disabled:true">编辑</a>
			<a id="deleteAreaButton" class="easyui-linkbutton" onclick="onDeleteButton()" data-options="iconCls:'icon-groupdelete',plain:true,disabled:true">删除</a>
			<a id="printButton_list"  class="easyui-linkbutton" onclick="onPrintButton()" data-options="iconCls:'icon-print',plain:true">打印</a>
		 	<!-- <a id="deleteEmployeeRealButton" class="easyui-linkbutton" onclick="onDeleteRealButton();" data-options="iconCls:'icon-remove',plain:true,disabled:true">真删</a>
			<a id="departmentButton" class="easyui-linkbutton" onclick="onDepartmentButton();" data-options="iconCls:'icon-link',plain:true,disabled:true">部门设置</a>
			<a id="positionButton" class="easyui-linkbutton" onclick="onPositionButton();" data-options="iconCls:'icon-server',plain:true,disabled:true">职位设置</a>
			<a id="zhicButton" class="easyui-linkbutton" onclick="onZhicButton();" data-options="iconCls:'icon-drive',plain:true,disabled:true">职称设置</a> -->
		  <span style="position:absolute; top:3px;left:500px">
			    		房间名：<input id="roomName"  name="roomName"/>
			    		<a id="searchButton" class="easyui-linkbutton" onclick="onSearchButton($('#roomName').val());" data-options="iconCls:'icon-search',plain:true">搜索</a>
			    		</span> 
		</div>
 	</div>
    <%@include file="/WEB-INF/jsp/areaAction/areaAddEdit.jspf" %>
</div>
</body>
</html>