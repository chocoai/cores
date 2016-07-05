<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>CoRES动物实验管理系统-登录</title>
	<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
	  <script language="javascript" src="${pageContext.request.contextPath}/script/javascript/alterpassword.js" charset="utf-8"></script>
	<script type="text/javascript">
		var myUrl;
		var webName;
		$(function(){
			myUrl ='';
			//在被 嵌套时，就刷新上级窗口
			if(window.parent!=window){
				window.parent.location.reload(true);
			}
			$('#password').bind('keyup', function(event) {/* 增加回车提交功能 */
				if (event.keyCode == '13') {
					login_submit();
				}
			});
			$('#userid').bind('keyup', function(event) {/* 增加回车提交功能 */
				if (event.keyCode == '13') {
					$('#password').focus();
				}
			});
		});
		function login_submit(){
			$('#errorMsg').html('');
			if($('#userLoginForm').form('validate')){
				//document.forms[0].submit();
				$.ajax({
					url:sybp()+'/userAction_newCheckPrivilege.action',
					type:'post',
					data:$('#userLoginForm').serialize(),
					dataType:'json',
					success:function(r){
						//用户名是否为空
						if('' == r.nullUserError){
							//密码是否错误
							if('' == r.forbidden){
										window.location.href="/cores_arp/homeAction_indexSystemSet.action";
										//window.open(myUrl+r.ticket,webName); 
										//window.opener=null;
										//window.open('','_self');
										//window.close();
							}else{
							//账户用户
								$('#errorMsg').html(r.forbidden);	
							}
						}else{
							$('#errorMsg').html(r.nullUserError);
						}
					}
				});
			}
		}
		/**密码修改后事件*/
		function eventAfterAlterPasswrod(){
			$.ajax({
				url:sybp()+'/userAction_getTicket.action',
				type:'post',
				dataType:'json',
				success:function(r){
					window.location.href=myUrl+r.ticket;
					//window.open(myUrl+r.ticket,webName); 
					//window.opener=null;
					//window.open('','_self');
					//window.close();
				}
			});
			
			//window.location.href="${pageContext.request.contextPath}/userAction_login.action"
		}
	</script>
</head>

<body onload="document.all.userid.focus();">
<div class="container">
	<div class="login">
    	<div class="left">
        	<div class="pic"></div>
            <div class="text">
            	     <p class="text_big">CoRES系统登录</p>
            <!-- <p>请输入您的帐号以及密码，进入系统</p>
                <p>蓝恩CoRES临检实验室管理系统软件v1.0</p>
                <p>版权所有：苏州蓝恩信息科技有限公司</p>-->
            </div>
        </div>
        <div class="right">
        	<div class="logo"></div>
            <div>
            <s:form	id="userLoginForm"  action="userAction_login"  focusElement="userName">
           	  <table width="100%">
           	  	  <tr>
           	  	  	<td>
           	  	  	<!-- 显示错误 <font color="red"><s:fielderror/></font>-->
           	  	  	<span id="errorMsg" style="color:red"></span>
           	  	  	</td>
           	  	  </tr>
                  <tr>
                    <td>请输入用户名</td>
                  </tr>
                  <tr>
                    <td><input type="text" id="userid" name="userid" id="userid" class="input_user easyui-validatebox" data-options="required:'true',validType:'maxLength[15]'" /></td>
                  </tr>
                  <tr>
                    <td>请输入密码</td>
                  </tr>
                  <tr>
                    <td><input type="password" id="password" name="password" id="password" class="input_password easyui-validatebox" data-options="required:'true',validType:'maxLength[11]'"/></td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                     <td><a href="javascript:login_submit();" class="login_button" style="width:100px; margin-top:20px;">登录</a></td>
                   <!-- 
                    <td><a href="javascript:ap_showQianmingDialog('nihao',1);" class="login_button" style="width:100px; margin-top:20px;">登录</a></td>
                    -->
                  </tr>
                  <tr>
                    <td>&nbsp;   </td>
                  </tr>
                </table>
            </s:form>
            <%@ include file="/WEB-INF/jsp/public/alterpassword.jspf"%>
            </div>
        </div>
    </div>
</div>   
</body>
</html>

