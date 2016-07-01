/**显示Dialog*/
function showPathAddEditDialog(selectItem,callback){
	/*签名Dialog*/
	document.getElementById("reportNumberDialog2").style.display="block";
	$('#id').val(selectItem.id);
	$('#reportName').text(selectItem.reportName);
	$('#number').val(selectItem.number);
	$('#reportNumberDialog').dialog('open'); 
	this.myFunction = callback;
}

function onConfirmButton(){
	if($('#reportNumberForm').form('validate')){
		$.ajax({
			url:sybp()+'/dictReportNumberAction_save.action',
			type:'post',
			data:$('#reportNumberForm').serialize(),
			dataType:'json',
			success:function(r){
				if(r && r.success){
					$('#reportNumberDialog').dialog('close');
					myFunction();
				}else{
					$.messager.alert('警告',r.msg);    
				}
			}
		});
	}
}