<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>血常规</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script type="text/javascript">
	var tableHeight;//当前页面可见区域高度 - 30
	var tableWidth;
	$(function(){
		tableHeight = document.body.clientHeight - 60;
		tableWidth  = document.body.clientWidth;
		$('#tabs').tabs({    
		    onSelect:function(title,index){
		      if(index == 1){
		    	  var tab2=$('#tabs').tabs('getTab',index);
		    	  $('#tabs').tabs('update', {
		    			tab: tab2,
		    			options: {
		    				title: '血生化',
		    				content: '<iframe src="' + '${pageContext.request.contextPath}/xyshAction_listXYSH.action'+ ' "frameborder="0" style="border:0;width:100%;height:100%;"></iframe>'
		    			}
		    		});
		      }
		     
		     
		    }    
		});  
				
		showXcgTable('','');
		
		
		//显示整个布局
		$('#layoutMainDiv').css('display','');
    });//匿名函数结束
    function showXcgTable(monkeyid,cdate){
    	$('#xcgTable').datagrid({
			url : sybp()+'/xcgAction_xcgByJson.action?monkeyid='+monkeyid+'&cdate='+cdate,
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
				title:'房号',
				field:'roomid',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'笼号',
				field:'lhao',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'猴号',
				field:'monkeyid',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'性别',
				field:'sex',
				width:80,
				halign:'center',
				align:'center',
				formatter:function(value,row,index){
					if(value==0){
						return "公";
					}else{
						return "母";
					}
				}
			},{
				title:'体重(kg)',
				field:'weight',
				width:80,
				halign:'center',
				align:'center'
			},/**{
				title:'检疫类型',
				field:'ptype',
				width:80,
				halign:'center',
				align:'center',
				formatter:function(value,row,index){
					if ( value == 'QuarantineN' ){
						return "常规检疫";
					}else{
					    return "其他检疫";
					}
				}
			},*/{
				title:'检疫编号',
				field:'checkId',
				width:120,
				halign:'center',
				align:'center'
			},{
				title:'血样号',
				field:'xueyh',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'采样',
				field:'caiy',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'WBC',
				field:'wbc',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'RBC',
				field:'rbc',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'HGB',
				field:'hgb',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'HCT',
				field:'hct',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'PLT',
				field:'plt',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'MCV',
				field:'mcv',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'MCH',
				field:'mch',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'MCHC',
				field:'mchc',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'LYM',
				field:'lym',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'MID',
				field:'mid',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'GRA',
				field:'gra',
				width:80,
				halign:'center',
				align:'center'
			}	
			]],
			
			onSelect:function(rowIndex, rowData){
    		  $('#addTreasuryButton').linkbutton('enable');
   		  	  $('#editTreasuryButton').linkbutton('enable');
    		  $('#deleteTreasuryButton').linkbutton('enable');
			},
			onLoadSuccess:function(data){
			   
			   $('#addTreasuryButton').linkbutton('enable');
	    	   $('#editTreasuryButton').linkbutton('disable');
	           $('#deleteTreasuryButton').linkbutton('disable');
	           var selectid=$('#ttid').val();
	           if(selectid != ""){
			          for(var i = 0 ; i< data.rows.length;i++){
			             if(selectid == data.rows[i].id){
			            	$('#xcgTable').datagrid('selectRow',i);
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
  //查找按钮事件
    function onSearchButton(){
    	var monkeyid =  $('#monkeyid').val();
    	var cdate =$('#cdate').datebox('getValue');
    	showXcgTable(monkeyid,cdate);
    }
function onPrintButton(){
        var date=$('#cdate').datebox('getValue');
        var checkId=$('#checkId').val();
        if(checkId!=""){
        	parent.parent.showMessager(2,'数据加载中...',true,5000);
    		window.location.href=sybp()+'/xcgAction_reportXCG.action?cdate='+date+'&checkId='+checkId;
    	}else{
    		$.messager.alert('提示','请输入检疫编号!');
    	}
}
</script>
</head>
<body>
<s:hidden id="ttid" name="ttid"></s:hidden>
<s:hidden id="onchange" name="onchange"></s:hidden>
<div id = "progressBar" style="position:absolute;z-index:100;left:30%; top:50%; "> <div id="p" style="width:400px;"></div> 
</div>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
 	<div region="center" title="" style="overflow: hidden;">
 	 	 <div id="tabs" class="easyui-tabs" fit="true" border="false"> 
            <div title="血常规" border="false" style="overflow: auto;">
                <div style="height:30px;">
			       <div style="float:left; width:100%;padding-top:5px;height:22px;">
			    		&nbsp;&nbsp;猴子编号:
			    		<input id="monkeyid" type="text"  name=""  style="width:100px;height:16px"></input> 
			      	  	&nbsp;采样日期:&nbsp;<input id="cdate" type="text" class="easyui-datebox" style="width:100px;height:19px;" editable="false"/>
			      	  	&nbsp;<a id="searchButton" class="easyui-linkbutton" onclick="onSearchButton();" data-options="iconCls:'icon-search',plain:true">搜索</a>
			    	</div>
			    </div>
				<table id="xcgTable" ></table>
            </div>
            <div title="血生化" border="false" style="overflow: auto;">
				<table id="xyshTable" ></table>
            </div>           
		</div> 
        <div id="toolbar">
			<!--<a id="addTreasuryButton" class="easyui-linkbutton" onclick="onAddButton();"  data-options="iconCls:'icon-groupadd',plain:true,disabled:true">添加</a>
		  	<a id="editTreasuryButton" class="easyui-linkbutton" onclick="onEditButton();" data-options="iconCls:'icon-groupedit',plain:true,disabled:true">编辑</a>
			<a id="deleteTreasuryButton" class="easyui-linkbutton" onclick="onDeleteButton()" data-options="iconCls:'icon-groupdelete',plain:true,disabled:true">删除</a>-->
		 	&nbsp;检疫编号:&nbsp;<input id="checkId" type="text" style="width:180px;height:16px;" />
		 	<a id="printButton_list1"  class="easyui-linkbutton" onclick="onPrintButton();" data-options="iconCls:'icon-print',plain:true">打印</a>
		</div>
 	</div>
</div>
</body>
</html>