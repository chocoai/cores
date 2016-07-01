/****开始********************************************************/

	
	
	function searchLog2(archiveTypeFlag,logStartDate,logEndDate,isFileDate,start,end,keepEndDate,isDestory,isValid,searchString)
	{
		var contentWind = document.getElementById('archiveMainFrame').contentWindow;
		var childWind = contentWind.document.getElementById('tblLog2Frame').contentWindow;
		if(childWind.$){
			childWind.$('#tblLog2Datagrid').datagrid({
		    		url : sybp()+'/tblLogAction_loadList.action?logStartDate='+logStartDate+'&logEndDate='+logEndDate+'&archiveTypeFlag='+archiveTypeFlag+'&fileStartDate='+start+'&fileEndDate='+end+'&keepEndDate='+keepEndDate+'&isDestory='+isDestory+'&isValid='+isValid+'&searchString='+searchString,
			});
			
		 }
			 
		
	}
	
	
	
/****结束********************************************************/
