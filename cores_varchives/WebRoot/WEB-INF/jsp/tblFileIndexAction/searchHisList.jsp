<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ taglib prefix="s" uri="/struts-tags" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-easyui1.3/jquery-easyui-1.3.2/jquery-1.8.0.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-easyui1.3/syUtils.js" charset="utf-8"></script>
<style type="text/css">
* { margin: 0; padding: 0;}
html,body{
	height:100%;
	color:#000;
	font-size:12px;
	font-family:microsoft yahei;
	background-color:#fff;
}

ul{
	list-style:none;
	padding:0px;
	margin:0px;
	background:white;
	
	
}
ul li{
	width:200px;
	height:20px;
	padding:0px 10px;
	float:none;
	border-bottom:0px solid #131313;
	border-top:0px solid #323232;
	opacity: 1;
}

ul li a{
	float:none;
	height:32px;
	line-height:32px;
	width:190px;
	margin-right:0px;
	color:#FFF;
	text-decoration:none;
	display:inline-block;
}
ul li:mouseover{
	color:#fc7404;
}
ul li:mouseout{
	color:#fc7404;
}
</style>
</head>

<body >
	<ul >

		<s:iterator value="searchHisList" id='number'> 
			<li  onclick="parent.$('#searchStringBox').searchbox('setValue',$(this).text());
				 		parent.$('#searchHisList').css('display','none')"
				 ><span name="searchHis_con" ><s:property value='number'/></span></li>
	    </s:iterator>   
	
	</ul>

</body>
</html>

