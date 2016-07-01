<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>解剖动物设置</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/dissectPlan.js"></script>
<script type="text/javascript">
	var studyNoPara ;
	var groupNum;
	$(function(){
		groupNum=$('#groupNum').val();
		studyNoPara = $('#studyNoPara').val();
		for(var i=0;i<groupNum;i++){
			$('#datagridTable_'+(i+1)).datagrid({
				url:sybp()+'/tblDissectPlanAction_loadDetialList.action?studyNoPara='+encodeURIComponent(studyNoPara)+'&groupNum='+(i+1),
			});
		}
		//解剖次数
		$('#dissectNum').combobox({
			url:sybp()+'/tblDissectPlanAction_dissectNum.action?studyNoPara='+encodeURIComponent(studyNoPara)
		});
		//第几组
		$('#minNum').combobox({
			url:sybp()+'/tblDissectPlanAction_selectNum.action?studyNoPara='+encodeURIComponent(studyNoPara)
		});
		$('#maxNum').combobox({
			url:sybp()+'/tblDissectPlanAction_selectNum.action?studyNoPara='+encodeURIComponent(studyNoPara)
		});
		
	});
	/**确定按钮事件*/
	function onConfirm(){
		//获取雌雄的选择
		var sex1 = document.getElementById("Sex1").checked;
		var sex2 = document.getElementById("Sex2").checked;
		
		var maxNum = $('#maxNum').combobox('getValue');
		var minNum = $('#minNum').combobox('getValue');
		if(Number(maxNum)<Number(minNum)){
			minNum = Number(minNum)+Number(maxNum);
			maxNum = Number(minNum) -Number(maxNum);
			minNum = Number(minNum) -Number(maxNum);
		}
		for(var i=0;i<groupNum;i++){
			//取消所有行选择
			$('#datagridTable_'+(i+1)).datagrid('unselectAll');
			//当前组 雄 的第几个
			var currentNum1=0;
			//当前组 次 的第几个
			var currentNum2=0;
			//所有数据
			var rows = $('#datagridTable_'+(i+1)).datagrid('getRows');
			for(var j = 0;Number(j)<Number(rows.length);j++){
				if(sex1==true&&rows[j].gender == 1){
					currentNum1++;
					if(Number(currentNum1)<= Number(maxNum) && Number(currentNum1)>=Number(minNum) ){
						$('#datagridTable_'+(i+1)).datagrid('selectRow',j);
					}
				}else if(sex2==true&&rows[j].gender == 2){
					currentNum2++;
					if(Number(currentNum2)<= Number(maxNum) && Number(currentNum2)>=Number(minNum) ){
						$('#datagridTable_'+(i+1)).datagrid('selectRow',j);
					}
				}
			}
		}
	}
	/**保存按钮事件*/
	function onSave(){
		var dissectNum = $('#dissectNum').combobox('getValue');
		if(!dissectNum){
			parent.parent.showMessager(2,'请选择解剖次数',true,5000);
			return ;
		}
		var selectNum = 0;
		for(var i=0;i<groupNum;i++){
			var dataRows =$('#datagridTable_'+(i+1)).datagrid('getSelections');
			selectNum = Number(selectNum)+Number(dataRows.length);
		}
		if(Number(selectNum) == 0){
			parent.parent.showMessager(2,'请选择动物',true,5000);
			return ;
		}
		var data ='';//animalDetailList
		for(var i=0;i<groupNum;i++){
			var dataRows =$('#datagridTable_'+(i+1)).datagrid('getSelections');
			for(var j=0;j<dataRows.length;j++){
				data=data+'&animalDetailList='+dataRows[j].id;
			}
		}
		$.ajax({
			url:sybp()+'/tblDissectPlanAction_updateData.action?studyNoPara='+encodeURIComponent(studyNoPara)+'&dissectNum='+dissectNum,
			type:'post',
			data:data,
			dataType:'json',
			success:function(r){
				if(r && r.success){
					
					for(var i=0;i<groupNum;i++){
						var dataRows =$('#datagridTable_'+(i+1)).datagrid('getSelections');
						for(var j=0;j<dataRows.length;j++){
							var rowIndex =$('#datagridTable_'+(i+1)).datagrid('getRowIndex',dataRows[j]);
							$('#datagridTable_'+(i+1)).datagrid('updateRow',{
								index:rowIndex,
								row:{
									id:dataRows[j].id,
									animalCode:dataRows[j].animalCode,
									dissectNum:dissectNum,
									gender:dataRows[j].gender
								}	
							});
						}
						//取消所有行选择
						$('#datagridTable_'+(i+1)).datagrid('unselectAll');
					}
					parent.parent.showMessager(1,r.msg,true,5000);
				}else{
					parent.parent.showMessager(3,r.msg,true,5000);
				}
			}
		});
	}
</script>
</head>
<body>
<div  class="easyui-layout" style="width:100%;height:100%;">   
	<s:hidden id="studyNoPara" name="studyNoPara"></s:hidden>
	<s:hidden id="groupNum" name="groupNum"></s:hidden>
    <div data-options="region:'north'," style="height:60px;">
        <div style="border-bottom:1px solid #c9c9c9;padding:2px 0px;">
		    &nbsp;&nbsp;
		    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="onSave();" onclick="onSave();">保存</a>
		    <a class="easyui-linkbutton" iconCls="icon-back" plain="true" href="${pageContext.request.contextPath}/tblDissectPlanAction_dissectPlanList.action?studyNoPara=${studyNoPara}">返回</a>
        </div>
	    <div style="padding:4px 10px;">
	    	<span style="padding:0px 5px;">解剖次数</span><input id="dissectNum" class="easyui-combobox" style="width:80px;height:19px;" data-options="valueField:'id',textField:'text', panelHeight:'auto'"/>
	    </div>
    </div>   
    <div data-options="region:'center',title:'动物列表'" style="padding: 0px 5px;">
    	<div style="border-bottom:1px solid #c9c9c9;padding:5px 3px; height:23px;">
    		<span style="padding:0px 7px;">快速选择&nbsp;&nbsp;&nbsp;每组第</span>
    		<input id="minNum" style="width:50px;height:19px;" class="easyui-combobox" value="1" data-options="valueField:'id',textField:'text',editable:false, panelHeight:300,
	    	url:'${pageContext.request.contextPath}/tblDissectPlanAction_selectNum.action?studyNoPara=encodeURIComponent(${studyNoPara})'"/>
	    	
	    	&nbsp;至&nbsp;
    		<input id="maxNum" style="width:50px;height:19px;" class="easyui-combobox" value="1" data-options="valueField:'id',textField:'text',editable:false, panelHeight:300"/>
	    	
	    	<span>&nbsp;只</span>&nbsp;&nbsp;
	    	性别：
	    	<input style="vertical-align:middle;" id="Sex1" value="1" type="checkbox" class="sexList" checked="checked" />雄 
            <input style="vertical-align:middle;" id="Sex2" value="2" type="checkbox" class="sexList" checked="checked" />雌 &nbsp;
            
            &nbsp;&nbsp;&nbsp;			
    		<a class="easyui-linkbutton" plain="true" onclick="onConfirm();">选择</a>
    	</div>
    	<s:iterator begin="1" step="1" end="%{groupNum}" status="statu">
	    	<div style="float:left;width:190px;">
		    	<table id="datagridTable_${statu.index+1}" class="easyui-datagrid" style="width:180px;height:360px"   
				        data-options="fit:false,striped:true,fitColumns:false,pagination:false,">   
				    <thead>   
				        <tr>   
				            <th data-options="field:'id',width:20,hidden:true">id</th>   
				            <th data-options="field:'animalCode',width:60,halign:'center'">动物编号</th>   
				            <th data-options="field:'gender',width:30,halign:'center',
				            	formatter: function(value,row,index){
										return value == 1 ? '♂':'♀';
								}
				            ">性别</th>   
				            <th data-options="field:'dissectNum',width:60,halign:'center',
				            	formatter: function(value,row,index){
										return value == 0 ? '': value;
								}
				            ">解剖次数</th>   
				        </tr>   
				    </thead>   
				</table>
	    	</div>
    	</s:iterator>
    </div>
    
</div>  
</body>
</html>
