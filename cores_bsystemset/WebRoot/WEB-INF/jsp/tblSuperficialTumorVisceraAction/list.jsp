<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>main</title>
	<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/tblSuperficialTumorVisceraAction/list.js"></script>
</head>
<body>
<!-- 存放treegrid加载后,待选中的行id -->
<input type="hidden" id="selectVisceraCoce1"/>
<input type="hidden" id="selectVisceraCoce2"/>
<div id="layoutMainDiv" class="easyui-layout"  style="width:100%;height:100%; display:none;"> 
	<div region="center" title="" style="overflow: hidden;">
		<div class="easyui-tabs" fit="true" border="false">
			<div title="浅表肿瘤脏器" border="false" style="overflow: auto;">
			 	<div class="easyui-layout" fit="true" border="false" >
				 	<div region="west" border="false" style="width:200px;">
						<table id="visceraTable"></table>
					</div>
					<div region="center" border="false" style="">
						<div class="easyui-layout" fit="true" border="false" >
							<div region="west" border="false" style="width:100px;">
								<a class="easyui-linkbutton" onclick="addSuperficialTumorViscera();" style="position:absolute;top:150px;left:16px;font-weight:bold;" plain="true">&nbsp;&nbsp;>&nbsp;&nbsp;</a><br/>
								<a class="easyui-linkbutton" onclick="removeSuperficialTumorViscera();" style="position:absolute;top:200px;left:16px;font-weight:bold;" plain="true">&nbsp;&nbsp;<&nbsp;&nbsp;</a><br/>
							</div>
							<div region="center" border="false">
								<table id="superficialTumorVisceraTable" ></table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</div>
</body>
</html>
