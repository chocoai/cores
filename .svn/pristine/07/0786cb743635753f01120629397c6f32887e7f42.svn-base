//点击链接，打开窗口
function openWindow(obj){
	
	var myticket = $('#myticket').val();
	var from = $('#from').val();
	
	var id =obj.id;
	var myUrl = "";
	var webName = "";
    if(id == "schedule"){
    	myUrl='/cores_schedule/userAction_login.action?ticket=';
		webName='schedule';
    }else if(id == "study"){
    	myUrl='/cores_study/userAction_login.action?ticket=';
		webName='study';
    }else if(id == "contract"){
    	myUrl='/cores_contract/userAction_login.action?ticket=';
		webName='contract';
    }else if(id == "systemset"){
    	myUrl='/cores_bsystemset/userAction_login.action?ticket=';
		webName='systemset';
    }else if(id == "qa"){
    	myUrl='/cores_tqa/userAction_login.action?ticket=';
		webName='tqa';
    }else if(id == "sop"){
		myUrl='/cores_vsop/userAction_login.action?ticket=';
		webName='vsop';
	}else if(id == "varchive"){
		myUrl='/cores_varchives/userAction_login.action?ticket=';
		webName='varchive';
	}else if(id == "audit"){
		myUrl='/cores_uaudit/userAction_login.action?ticket=';
		webName='audit';
	}
    $.ajax({
		url:'/'+from+'/userAction_isvail.action',
		type:'post',
		dataType:'json',
		success:function(r){
	    	if(r && r.success){
				window.open(myUrl+myticket,webName);
			}
		}
	});
}
//点击链接，打开窗口
function openWindow2(id){
	
	var myticket = $('#myticket').val();
	var from = $('#from').val();
	
//	var id =obj.id;
	var myUrl = "";
	var webName = "";
	if(id == "schedule"){
		myUrl='/cores_schedule/userAction_login.action?ticket=';
		webName='schedule';
	}else if(id == "study"){
		myUrl='/cores_study/userAction_login.action?ticket=';
		webName='study';
	}else if(id == "contract"){
		myUrl='/cores_contract/userAction_login.action?ticket=';
		webName='contract';
	}else if(id == "systemset"){
		myUrl='/cores_bsystemset/userAction_login.action?ticket=';
		webName='systemset';
	}else if(id == "qa"){
		myUrl='/cores_tqa/userAction_login.action?ticket=';
		webName='tqa';
	}else if(id == "audit"){
		myUrl='/cores_uaudit/userAction_login.action?ticket=';
		webName='audit';
	}
	$.ajax({
		url:'/'+from+'/userAction_isvail.action',
		type:'post',
		dataType:'json',
		success:function(r){
		if(r && r.success){
//			window.open(myUrl+myticket,webName);
			document.getElementById("clickId").href=myUrl+myticket;
//			document.getElementById("clickId").target=webName;
			var clickId =document.getElementById("clickId");
			clickId.click();
		}
	}
	});
}