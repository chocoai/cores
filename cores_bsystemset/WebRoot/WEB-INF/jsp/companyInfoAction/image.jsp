<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.lanen.model.company.CompanyInfo"%>
<%@page import="java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<script language="javascript" src="${pageContext.request.contextPath}/script/javascript/qianming.js" charset="utf-8"></script>
<script type="text/javascript">
<%
	System.out.print("enter...");

try {
		response.setContentType("application/binary;charset=ISO8859_1");

            OutputStream outs = response.getOutputStream();
			
			CompanyInfo companyInfo= (CompanyInfo)request.getAttribute("companyInfo");
            byte[] logo = companyInfo.getCompanyLogo();
            
            outs.write(logo);

            outs.flush();

            out.clear();

            out = pageContext.pushBody();

} catch (FileNotFoundException e) {

e.printStackTrace();

} catch (IOException e) {

e.printStackTrace();

}

%>
</script>