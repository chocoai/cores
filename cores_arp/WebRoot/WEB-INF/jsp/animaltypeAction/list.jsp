<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>猴场配置</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/animaltypeAction/animaltypeAddEdit.js" charset="utf-8"></script>
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/animaltypeAction/cellTip.js" charset="utf-8"></script>
<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 30
	var tableWidth;
	$(function(){
		tableHeight = document.body.clientHeight - 60;
		tableWidth  = document.body.clientWidth-60;
		$('#SearchButton').searchbox({ 
		    height:19,
		    width:450,
			searcher:function(value,name){ 
		     showAnimaltypeTable();
			}, 
			prompt:'模糊查询,请输入结论关键字' 
			}); 
				
		showAnimaltypeTable();
		
		
		//显示整个布局
		$('#layoutMainDiv').css('display','');
    });//匿名函数结束
    function showAnimaltypeTable(){
    	$('#animaltypeTable').datagrid({
			url : sybp()+'/animaltypeAction_loadList.action',
			title:'',
			height:tableHeight,
			width:tableWidth,
			nowarp:  false,//单元格里自动换行
			singleSelect:true,
			fitColumns:false,
		    pagination:true,//分页
			sortName:'id',
			columns :[[{
				title:'id',
				field:'id',
				width:150,
				hidden:true
			},{
				title:'品种名称',
				field:'name',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'描述',
				field:'desciption',
				width:980,
				rowspan:5,
				colspan:10,
				halign:'center',
				align:'center',
				nowarp:  false
			}
			]],
			onSelect:function(rowIndex, rowData){
				$('#addAnimaltypeButton').linkbutton('enable');
    		  $('#editAnimaltypeButton').linkbutton('enable');
    		  $('#deleteAnimaltypeButton').linkbutton('enable');
			},
			onLoadSuccess:function(data){
				//调用提示功能. onlyShowInterrupt: false, 是否只有在文字被截断时才显示tip，默认值为false
				//position: 'bottom',   tip的位置，可以为top,botom,right,left
				// cls: { 'background-color': '#D1EEEE' }, tip的样式
				//delay: 100   tip 响应时间
				$('#animaltypeTable').datagrid('doCellTip',{onlyShowInterrupt: false, position: 'bottom', cls: { 'background-color': '#D1EEEE' },delay: 100 ,'max-width':'100px'});
				$('#addAnimaltypeButton').linkbutton('enable');
	    	   $('#editAnimaltypeButton').linkbutton('disable');
	           $('#deleteAnimaltypeButton').linkbutton('disable');
	           var selectid=$('#Animaltypeid').val();
	           if(selectid != ""){
			          for(var i = 0 ; i< data.rows.length;i++){
			             if(selectid == data.rows[i].id){
			            	$('#animaltypeTable').datagrid('selectRow',i);
			             }
			          }
			   }
	         
			},
			toolbar:'#toolbar',
			pageSize : 12,//默认选择的分页是每页5行数据         
			pageList : [ 5, 10, 12, 15, 20 ],//可以选择的分页集合       
		    nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取       
		    striped : true,//设置为true将交替显示行背景。      
		    collapsible : true//显示可折叠按钮 
			
	   	});
    }
    //添加按钮事件
    function onAddButton(){
    	showAnimaltypeAddEditDialog('afterAddDialog','add','添加猴场配置');
    }
    
	//	添加Dialog后事件
    function afterAddDialog(){
       parent.showMessager(1,'添加成功',true,5000);
       $('#animaltypeTable').datagrid('reload');
    }
    // 编辑按钮事件
    function onEditButton(){
         var row= $('#animaltypeTable').datagrid('getSelected');
        if(row != null ){
        	showAnimaltypeAddEditDialog('afterEditDialog','edit','编辑猴场配置信息');
        }else{
           $.messager.alert('提示','请选择编辑的品种!');
        }
    }
   //编辑后事件
     function afterEditDialog(){
    	 parent.showMessager(1,'编辑成功',true,5000);
    	 $('#animaltypeTable').datagrid('reload');
     }
  
   
   //删除按钮事件(数据库还保存)
       function onDeleteButton(){
     	  var row= $('#animaltypeTable').datagrid('getSelected');
     	 if(null != row ){
          	$.messager.confirm('确认对话框', '确认删除此品种记录？', function(r){
 				if (r){
 					delAnimaltype(row.id);
 					
 				}
 			});
         }else{
           $.messager.alert('提示','请选择要删除的品种记录!');
         }
      }
       function delAnimaltype(id){
   	   	$.ajax({
   			url:sybp()+'/animaltypeAction_delAnimaltype.action',
   			type:'post',
   			data:{id:id},
   			dataType:'json',
   			success:function(r){
   				if(r && r.success){
   					 parent.showMessager(1,'记录删除成功',true,5000);
   					$('#animaltypeTable').datagrid('reload');
   				}else{
   					 parent.showMessager(2,'记录删除失败',true,5000);
   				}
   			}
   		});
       } 
    
     function onSearchButton(){
          showAnimaltypeTable();
      }
 
</script>
</head>
<body>
<s:hidden id="Animaltypeid" name="Animaltypeid"></s:hidden>
<s:hidden id="onchange" name="onchange"></s:hidden>
<div id = "progressBar" style="position:absolute;z-index:100;left:30%; top:50%; "> <div id="p" style="width:400px;"></div> 
</div>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
 	<div region="center" title="" style="overflow: hidden;">
 	 	<div id="tabs" class="easyui-tabs" fit="true" border="false">
            <div title="猴场配置" border="false" style="overflow: auto;">
                <!--  <div style="height:10px;">
			       <div style="float:left; width:100%;padding-top:5px;height:22px;">
			       </div>
			    </div>-->
				<table id="animaltypeTable" style="height:auto;"></table>
            </div>
		</div>
        <div id="toolbar">
			<a id="addAnimaltypeButton" class="easyui-linkbutton" onclick="onAddButton();"  data-options="iconCls:'icon-groupadd',plain:true,disabled:true">添加</a>
			<a id="editAnimaltypeButton" class="easyui-linkbutton" onclick="onEditButton();" data-options="iconCls:'icon-groupedit',plain:true,disabled:true">编辑</a>
			<a id="deleteAnimaltypeButton" class="easyui-linkbutton" onclick="onDeleteButton()" data-options="iconCls:'icon-groupdelete',plain:true,disabled:true">删除</a>
		</div>
 	</div>
 	<%@include file="/WEB-INF/jsp/animaltypeAction/animaltypeAddEdit.jspf" %>
</div>
</body>
</html>