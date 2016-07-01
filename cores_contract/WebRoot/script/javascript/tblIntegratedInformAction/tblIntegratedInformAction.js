//初始化供试品下拉框
function initTestItemAndNOCombobox(){
	$('#studyType_testItemAndNo').combobox({
		url:sybp()+'/tblStudyItemAction_loadTestItemAndNOList.action',
		valueField:'id',
		textField:'text',
		onSelect: function(record){    
          if(record.id == -1){
        	  $('#studyType_testItemAndNo').combobox('clear');
          }
		}
	});
}