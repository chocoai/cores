function editSave(){
	var statu=0;
	//var span1 = document.getElementById("success");
	var dictHematList = document.getElementsByName("dictHematList");
	for(var i=0;i<dictHematList.length;i++){
		if(dictHematList[i].checked){
			statu++;
		}
	}
	var dictBloodCoagList = document.getElementsByName("dictBloodCoagList");
	for(var i=0;i<dictBloodCoagList.length;i++){
		if(dictBloodCoagList[i].checked){
			statu++;
		}
	}
	var dictBioChemList = document.getElementsByName("dictBioChemList");
	for(var i=0;i<dictBioChemList.length;i++){
		if(dictBioChemList[i].checked){
			statu++;
		}
	}
	var dictUrineList = document.getElementsByName("dictUrineList");
	for(var i=0;i<dictUrineList.length;i++){
		if(dictUrineList[i].checked){
			statu++;
		}
	}
	if(statu==0){
		//span1.innerHTML="请选择检验指标"
		parent.parent.showMessager(2,'请选择检验指标',true,5000);
	}else{
		//span1.innerHTML="";
		//document.forms[0].submit();
		$.ajax({
			url:sybp()+'/tblTestIndexPlanAction_addSave.action',
			type:'post',
			data:$('#testIndexAction').serialize(),
			dataType:'json',
			success:function(r){
				if(r && r.success){
					parent.parent.showMessager(1,r.msg,true,5000);
				}else{
					parent.parent.showMessager(2,r.msg,true,5000);
				}
			}
		});
	}
}
//选中对应的checkBox
function selectCheckbox(type,obj){
	//type:1,2,3,4
	var id= type+obj.id;
	var checkbox =document.getElementById(id);
	if(checkbox.checked){
		checkbox.checked = false;
	}else{
		checkbox.checked = true;
	}
	var success=document.getElementById("success");
	success.innerHTML="";
}

/**全选*/
function selectAll(checkName){
	var allCheckBox = $('.'+checkName);
	for(var i=0;i<allCheckBox.length;i++){
		allCheckBox[i].checked = true;
	}
}
/**全不选*/
function unSelectAll(checkName){
	var allCheckBox = $('.'+checkName);
	for(var i=0;i<allCheckBox.length;i++){
		allCheckBox[i].checked = false;
	}
}