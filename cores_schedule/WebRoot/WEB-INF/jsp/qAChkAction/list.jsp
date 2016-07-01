<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/main.css" media="screen" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<script type="text/javascript">
	$(function(){
		//显示整个布局
		$('#layoutMainDiv').css('display','');
		//document.onkeydown = KeyDown;
	    indexTabs=$('#qaTabs');
	    indexTabs.tabs({
	    onSelect:function(title,index){
		    if(title == "QA最新消息"){
		    	var tab0=indexTabs.tabs('getTab',index);
				indexTabs.tabs('update', {
				    tab: tab0,
				    options: {
				    	title: 'QA最新消息',
				    	content: '<iframe  src="${pageContext.request.contextPath}/qAChkReportAction_newestList.action" frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
				    }
				   });
		    }else if(title == "检查报告"){
		    	var tab0=indexTabs.tabs('getTab',index);
				indexTabs.tabs('update', {
				    tab: tab0,
				    options: {
				    	title: '检查报告',
				    	content: '<iframe  src="${pageContext.request.contextPath}/qAChkReportAction_list.action?selectReportCode="+$("#selectReportCodeReport").val() frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
				    }
				   });
		    }
	
		    		
	    }
	   });
	    
			if($('#selectReportCodeReport').val()!=null&&$('#selectReportCodeReport').val()!=''){
				$.ajax({
					url:sybp()+'/qAChkAction_putSelectChkReportCodeInSession.action?selectReportCode='+$('#selectReportCodeReport').val(),
					type:'post',
					dataType:'json',
					success:function(){
						indexTabs.tabs('select','检查报告');//选中
					}
				});
			} 
        });//匿名函数结束
      
  
</script>
</head>
 <body >
 	<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;">   
 		<s:hidden id="selectReportCodeReport" name="selectReportCode"></s:hidden>
 	
	 	<div region="center" title="" style="overflow: hidden;" border="false">
	 		<div id="qaTabs" region="center" title="" class="easyui-tabs" fit="true" border="false" style="width:100%;height:100%;">
	 			<div title="QA最新消息"  border="false" style="overflow: auto;">
				  QA最新消息
				</div>
		 		<div title="检查报告"  border="false" style="overflow: auto;">
				  检查报告
				</div>
	 		</div>
		</div>
 	</div>
</body>
</html>
