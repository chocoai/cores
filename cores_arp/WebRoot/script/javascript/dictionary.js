/**
 * 顺序调整
 * @param index 索引
 * @param moveType 1上移或2下移
 * @param dictType 字典类型
 * @return
 */
function moveOrder(index,moveType,dictType){
	var orderNoPara = $("#h"+index).val();
	if(moveType==1){
		var orderNoNext = $("#h"+(index-1)).val();
	}else if (moveType==2) {
		var orderNoNext = $("#h"+(index+1)).val();
	}else {
		return;
	}
	var url1="${pageContext.request.contextPath}/";
	var url2="";
	var url3="_moveOrder.action?orderNoPara="+orderNoPara+"&orderNoNext="+orderNoNext;
	if(dictType==1){
		url2="dictBioChemAction";
	}else if (dictType==2) {
		url2="dictHematAction";
	}else if (dictType==3) {
		url2="dictBloodCoagAction";
	}else if (dictType==4) {
		url2="dictUrineAction";
	}else {
		return;
	}
	window.location.href=url1+url2+url3;
}

function formSubmit(){
	document.forms[0].submit();
}