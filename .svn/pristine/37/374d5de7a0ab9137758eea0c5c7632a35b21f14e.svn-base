//不支持多选
function animalHouseUI1(){
	$('#AnimalHouseUl').tree({    
	    url:  sybp()+'/tblAnimalHouseAction_loadList.action?resKind=1',    
	    lines :true,
	    onBeforeExpand:function(node,param){  
        $('#AnimalHouseUl').tree('options').url =  sybp()+'/tblAnimalHouseAction_loadList.action?parentId='+node.id;
        },  
	    onClick :function(node){
        	$('#nodeid').val(node.id);
			$('#editAnimalHouseButton').linkbutton('enable');
			$('#delAnimalHouseButton').linkbutton('enable');
	    },
	    onLoadSuccess:function(node, data){
		},
		onLoadError:function(arguments){
		}
	}); 
}

	  
//动物房资源
function AnimalHouseRes (){
	AnimalHouseTable= $('#AnimalHouseTable').treegrid({ 
		url : sybp()+'/tblAnimalHouseAction_loadList2.action',
		idField:'id',    
	    treeField:'resName', 
	    height: 200,
		width:200,
	    animate:false,   
	    singleSelect: true, //不支持多选
	    columns:[[  
	                {title:'id',field:'id',width:150,checkbox:true},
			        {field:'resKind',title:'resKind',width:150,hidden:true}, 
			        {title:'资源名称',field:'resName',width:150},    
			        {field:'_parentId',title:'_parentId',width:150,hidden:true},
			    ]],
			  //工具栏
				toolbar:'#toolbar',
		    	onClickRow :function(row){
		         // $(this).treegrid('select',row.id);
		       }
		       
	 });
}
	
//只可以选择房间但可以多选
function onlyChooseAnimalHouseRes(){
	AnimalHouseTable= $('#AnimalHouseTable').treegrid({ 
		url : sybp()+'/tblAnimalHouseAction_loadList2.action',
		idField:'id',    
	    treeField:'resName', 
	    height: 300,
		width:260,
	    animate:false,   
	    singleSelect: false, //支持多选
	    columns:[[  
	                {title:'id',field:'id',width:10,hidden:true},
			        {field:'resKind',title:'resKind',width:10,hidden:true}, 
			        {title:'资源名称',field:'resName',width:260},    
			        {field:'_parentId',title:'_parentId',width:10,hidden:true},
			    ]],
			  //工具栏
				toolbar:'#toolbar',
		    	onClickRow :function(row){
		           var resKind = row.resKind;
		           if(resKind != "3"){
		        	   var cid = $('#AnimalHouseTable').treegrid('getChildren',row.id);
		        	   if(cid.length>0){
		        		   $('#AnimalHouseTable').treegrid('expandTo',cid[0].id);
		        	   }
		        	   $('#AnimalHouseTable').treegrid('unselectRow',row.id);
		           }
		       },
		       onCheckAll:function(){
		    	   $.messager.alert('提示','请选择具体房间!','info');
		    
		       }
		       
	 });
}


//动物房资源只可以单选
function chooseAnimalHouseRes (){
	AnimalHouseTable= $('#AnimalHouseTable').treegrid({ 
		url : sybp()+'/tblAnimalHouseAction_loadList2.action',
		idField:'id',    
	    treeField:'resName', 
	    height: 200,
		width:200,
	    animate:false,   
	    singleSelect: true, //不支持多选
	    columns:[[  
	                {title:'id',field:'id',width:150,hidden:true},
			        {field:'resKind',title:'resKind',width:150,hidden:true}, 
			        {title:'资源名称',field:'resName',width:175},    
			        {field:'_parentId',title:'_parentId',width:150,hidden:true},
			    ]],
			  //工具栏
				toolbar:'#toolbar',
		    	onClickRow :function(row){
					var resKind = row.resKind;
			        if(resKind != "3"){
			     	   var cid = $('#AnimalHouseTable').treegrid('getChildren',row.id);
			     	   if(cid.length>0){
			     		   $('#AnimalHouseTable').treegrid('expandTo',cid[0].id);
			     	   }
			     	   $('#AnimalHouseTable').treegrid('unselectRow',row.id);
			        }
		       }
		       
	 });
}
