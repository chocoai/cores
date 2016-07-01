<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>top</title>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/style.css" media="screen" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/topMessager.css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/script/javascript/index.js"></script>

<script language="javascript">
	var second = 40;
	var time = 0;
	var height =0;
	var timer_up;
	var down = function(){
		height = height+2;
		time = time+second;
		$('#syMessagerShow').css('top',-28+height);
		if(height >= 28){
			 clearInterval(timer_down);
			 height=0;
			 time =0;
		}
    }
    var up = function(){
    	height = height+2;
		time = time+second;
		$('#syMessagerShow').css('top',0-height);
		if(height >= 28){
			 clearInterval(timer_up);
		}
	}
	var timeout = function(){
		//重复的定时器向上
		timer_up = setInterval( up , second);
		clearTimeout(timer_out);
	}
	var timer_down;
	var timer_out;

/**关闭消息*/
function close(){
	$('#syMessagerShow').css('display','none');
}
function showTopMessager(msg,isShowClose,showTime){
	second = 40;
	time = 0;
	height =0;
	timer_up;
	$('#syMessagerShow').css('top',-28);
	$('#syMessagerShow').css('display','');
	//消息
	$('#messager').html(msg);
	if(isShowClose){//true显示 关闭按钮
		$('#messager_closebutton').css('display','');
	}else{//不显示
		$('#messager_closebutton').css('display','none');
	}
	if(timer_down){
		clearInterval(timer_down);
	}
	if(timer_up){
		clearInterval(timer_up);
	}
	if(timer_out){
		clearTimeout(timer_out);
	}
	//重复的定时器向下
	timer_down = setInterval( down , second);
	if(showTime){
		//不重复的定时器
		timer_out = setTimeout(timeout,showTime);
	}
}
/*显示提示信息（1：类型（1 2 3 ），2：提示消息   3：是否可关闭   4：显示时间）*/
function showMessager(messagerType,msg,isShowClose,showTime){
	if(messagerType == 1){
		$('#syMessagerShow').removeClass().addClass('topMessager_success');
	}else if(messagerType == 3){
		$('#syMessagerShow').removeClass().addClass('topMessager_fail');
	}else{
		$('#syMessagerShow').removeClass().addClass('topMessager_warn');
	}
	second = 40;
	time = 0;
	height =0;
	timer_up;
	$('#syMessagerShow').css('top',-28);
	$('#syMessagerShow').css('display','');
	//消息
	$('#messager').html(msg);
	if(isShowClose){//true显示 关闭按钮
		$('#messager_closebutton').css('display','');
	}else{//不显示
		$('#messager_closebutton').css('display','none');
	}
	if(timer_down){
		clearInterval(timer_down);
	}
	if(timer_up){
		clearInterval(timer_up);
	}
	if(timer_out){
		clearTimeout(timer_out);
	}
	//设置信息框宽度
	if(getByteLen(msg)<26){
		$('#syMessagerShow').css('width','200');
	}else{
		$('#syMessagerShow').css('width',200+(Number(getByteLen(msg))-26)*6.5);
	}
	
	
	//重复的定时器向下
	timer_down = setInterval( down , second);
	if(showTime){
		//不重复的定时器
		timer_out = setTimeout(timeout,showTime);
	}
}

/**刷新main 区域*/
function updateMain(){
	var a_updateMain=document.getElementById("a_updateMain");
	a_updateMain.click();
	$('#system_box').css('display','none');
	setTopStudyNoSD('','');
}
/**显示system——box*/
function showSystembox(){
	$('#system_box').css('display','block');
}
/**隐藏system——box*/
function hiddenSystembox(){
	$('#system_box').css('display','none');
}
/**修改密码前的方法*/
function beforeAlterPassword(){
	ap_showQianmingDialog('eventAfterAlterPassword','');
}
/***/
 function eventAfterAlterPassword(){
	 /*签名Dialog*/
		document.getElementById("alterpassword2").style.display="block";
		//提示消息
		$('#alterPassword_message').html('');
		//用户名赋值
		$('#ap_userName').val('');
		//密码清空
		$('#ap_password').val('');
		$('#ap_newPassword').val('');
		$('#ap_reNewpassword').val('');
		//关闭对话框
		$('#alterpassword').dialog('close'); 
		$.messager.show({
			title:'提示',
			msg:'密码修改成功',
			timeout:5000
		});
}
/**设置专题编号 SD*/
function setTopStudyNoSD(studyNo,sd){
	$('#top_studyNo').html(studyNo);
	$('#top_sd').html(sd);
}

/**js获取长度*/
function getByteLen(val) {
    var len = 0;
    for (var i = 0; i < val.length; i++) {
        if (val[i].match(/[^\x00-\xff]/ig) != null) //全角
            len += 2;
        else
            len += 1;
    }
    return len;
}
</script>
</head>

<body>

<!--header-->
        <div class="header" >
        	<div class="logo_box"  onmouseover="showSystembox();" onmouseout="hiddenSystembox();"><a href="#" class="logo" ></a>
            	<div id="system_box" class="system_box"  >
                        <div id="system" class="system">
                        	<span class="arr"></span>
                            <ul>
                            <s:if test="isSystemSet > 0">
	                            <li>
	                                <a href="${pageContext.request.contextPath}/homeAction_indexSystemSet.action" >系统管理</a>
	                                <a id="a_updateMain" href="${pageContext.request.contextPath}/homeAction_main.action" target="main" style="display: none"></a>
	                            </li>
                            </s:if>
                            <s:if test="isStudyPlan == 1">
	                            <li>
	                                <a href="${pageContext.request.contextPath}/homeAction_index.action" >专题管理系统</a>
	                            </li>
                            </s:if>
                          <!--    <li><a href="#">动物实验管理系统</a></li>-->
                            </ul>
                        </div>
                    </div>
            </div>
            <div class="system_name">
            	<div class="name">${currentNum==1 ? '专题管理系统':'系统管理'}</div>
                <div class="path">
                <!-- 
                    <ul>
                    <li class="icon"></li>
                    <li><a href="#">首页</a></li>
                    <li class="arr"></li>
                    <li>实验室数据采集</li>
                    </ul>
                 -->
                </div>
            </div>
            <div class="studyPlan">
            	
            </div>
            <div class="quicklink">
            	<ul>
            	<!-- 
                <li><a href="#" class="home"></a></li>
                <li><a href="#" class="setting"></a></li>
            	 -->
                <li><a href="#" class="user"></a>
                	<div  class="dorpdown_box">
                        <div class="dorpdown">
                        	<span class="arr"></span>
                            <div class="title"><a >${user.realName}&nbsp;&nbsp;欢迎您！</a></div>
                            <ul>
                            <li><a href="javascript:void(0);" onclick="beforeAlterPassword();">修改密码</a></li>
                            <li><a href="javascript:void(0);" onclick="logout();">注销</a></li>
                            </ul>
                        </div>
                    </div>
                </li>
                </ul>
            </div>
            <div class="principal_box">
            	<div class="principal"><b>当前用户：</b>${user.realName }<span></span></div>
            </div>
            <div class="clear"></div>
        </div>
       <div style="position:absolute;top:36px; left:40%; z-index:1;width:405px;height:28px;background:white;">
       </div>
       <div style="position:absolute;top:64px; left:40%;width:300px;height:28px;">
       	  <!-- 公共提示信息 开始 -->
	       	<span id="syMessagerShow" class="topMessager_success" >
	       		<span class="topMessager_icon"></span>
	       		<span id="messager" class="messager" >你得信息有误，请检查</span>
	       		<a id="messager_closebutton" href="javascript:close();" class="messager_closebutton"  ></a>
	       	</span> 
	      <!-- 公共提示信息 结束 -->
       </div>
</body>
</html>