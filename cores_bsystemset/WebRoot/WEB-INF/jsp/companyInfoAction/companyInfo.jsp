<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.lanen.model.company.CompanyInfo"%>
<%@page import="java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>公司信息设置</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>

<script type="text/javascript">
	
	$(function(){
	
		$('#companyInfoEditForm').form({    
			    url:sybp()+'/companyInfoAction_saveCompanyInfo.action',
			    type:'post',
				dataType:'json',
			    onSubmit: function(){    
					var isValid = $(this).form('validate');
					if (!isValid){
						$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
					}
					return isValid;	// 返回false终止表单提交
			    },    
			    success:function(data){   
			    
			    	$.messager.progress('close');	// 如果提交成功则隐藏进度条
			    	data = $.parseJSON(data);
			    	
			    	if(data.success==true){
			    		$('#companyNameL').html($('#companyName').val());
			    		$('#companyLogoImg').attr("src","sybp()+/companyInfoAction_image.action?t="+Math.random() );//src改变了，图片重新加载
			
						$('#editCompanyInfoDialog').dialog('close');
			    	}else{
			    		$.messager.alert('警告','公司logo上传失败！'); 
			    	}
			    } 
			});  
			
			
			//response.setContentType("application/binary;charset=ISO8859_1");
           // OutputStream outs = response.getOutputStream();
           // outs.write(${companyInfo.companyLogo });
           // outs.flush();
           // out.clear();
           // out = pageContext.pushBody();
	});
	
	//编辑
	function editCompanyInfo()
	{
			/* 显示Dialog */
			document.getElementById("editCompanyInfoDialog2").style.display="block";
				
			$('#companyName').val("${companyInfo.companyName }" );
			$('#uploadImg').val("");
				
				
			$('#editCompanyInfoDialog').dialog('setTitle','编辑公司信息');
			$('#editCompanyInfoDialog').dialog('open'); 
						
			
	}
	
	function saveCompanyInfo()
	{
		var companyName = $('#companyName').val();
		var uploadImg = $('#uploadImg').val();
		{
			
			$.messager.progress({title: '请稍后',
				msg: '处理中...',text:''});	// 显示进度条
			$('#companyInfoEditForm').submit();	
			//parent.showMessager(3,r.msg,true,5000);
					
		}
		
	}
	
	
</script>
</head>
<body>
	<div class="easyui-layout" fit="true" border="false" ">
		<div region="north" border="false"  style="height:26px;">
			<div class="page_tab">
		      <ul>
		          <li class="active"><span><span><a href="javascript:void(0);">公司信息设置</a></span></span></li>
		      </ul>
			</div>
		</div>
		
		<!-- table start -->
		<div region="center" border=false style="border: 1px solid #c8c8c8;">
			<a id="editToolbarButton" class="easyui-linkbutton" onclick="editCompanyInfo();" data-options="iconCls:'icon-edit',disabled:false,plain:true">编辑</a>
			
			<table id="companyInfoTable"  border="1" style="margin:10px;">
				<tr>
				
					<td width="100">公司名称</td>
					<td width="300"><div id="companyNameL">${companyInfo.companyName }</div> </td>
				</tr>
				<tr>
					<td>公司logo</td>
					<td><div id="companyLogoL"><img id="companyLogoImg" width="100" height="100" src="sybp()+/companyInfoAction_image.action" alt="公司logo" /></div> </td>
					
				</tr>
			
			</table>
		</div>
		
		<%@include file="/WEB-INF/jsp/companyInfoAction/addCompanyInfo.jspf" %>
		<%@include file="/WEB-INF/jsp/public/qianming.jspf" %>
	</div>
</body>
</html>