<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ taglib prefix="s" uri="/struts-tags" %>
<title>CoRES项目管理系统</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/home_left.css"  />
<script type="text/javascript">
	var tableHeight ;
	$(function(){
		tableHeight = document.body.clientHeight;
		$('#accordion').accordion({
			height:tableHeight,
			onSelect:function(title,index){
			  	if(title == '客户信息管理'){
				  	if(!document.getElementById("iframe_customer").src){
				  		document.getElementById("iframe_customer").src=sybp()+'/tblRegionAction_list.action';
				  		window.open(sybp()+'/tblCustomerAction_list.action','main');
					}else{
						window.frames["left0"].reloadDataGrid();
					}
						
				}else if(title == '合同信息管理'){
					if(!document.getElementById("iframe_contract").src){
						document.getElementById("iframe_contract").src=sybp()+'/tblContractAction_left.action';
					}else{
						window.frames['left1'].selectedLeft1();
					}
				}else if(title == '综合查询管理'){
				    if(!document.getElementById("iframe_tblIntegratedInformAction").src){
						document.getElementById("iframe_tblIntegratedInformAction").src=sybp()+'/tblIntegratedInformAction_left.action';
					}else{
						window.frames['left2'].selectLeft2();
					}
				}
			}
		}); 
		//选择1
		$('#accordion').accordion('select',1);
	});
	

	//选择index
	function selectAccordion(index){
		$('#accordion').accordion('select',index);
	}
</script>
</head>
<body>
<s:hidden id="cid" name="cid"></s:hidden>
<div id="accordion" class="easyui-accordion" style="width:190px;">   
    	<div title="客户信息管理" data-options="selected:false" style="overflow:hidden;">
    	  	<iframe id="iframe_customer" name="left0"  width=100% height=100% frameborder=no border=0 scrolling=no ></iframe>
    	  	<!-- 
    	   		src="${pageContext.request.contextPath}/tblRegionAction_list.action" 
    	  	 -->
    	</div>   
    	<div title="合同信息管理" data-options="selected:false"  style="margin:3px;overflow:hidden;" >
    		<iframe id="iframe_contract" name="left1" width=100% height=100% frameborder=no border=0 scrolling=no ></iframe>
    		<!-- 
    	   		src="${pageContext.request.contextPath}/tblContractAction_left.action" 
    	  	 -->
    	</div>   
    	<div title="综合查询管理" data-options="selected:false" style="overflow:hidden;">
    		<iframe id="iframe_tblIntegratedInformAction" name="left2" width=100% height=100% frameborder=no border=0 scrolling=no ></iframe>
    	</div> 
</div>
</body>
</html>
