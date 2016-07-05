function addVirus(){
    	$('#layoutMainDiv').css('display','none');
    	$('#layoutOneDiv').css('display','');
    	var row= $('#exportTable').datagrid('getSelected');
    	$('#exportOneTable').datagrid({
			url : sybp()+'/exportAction_loadMonkeyList.action?orderid='+row.id+'&kind=virus',
			title:'',
			height:tableHeight,
			width:tableWidth,
			nowarp:  false,//单元格里自动换行
			singleSelect:true,
			fitColumns:false,
		    //pagination:true,//分页
			sortName:'id',
			columns :[[{
				title:'id',
				field:'id',
				width:150,
				hidden:true
			},{
				title:'动物编号',
				field:'monkeyid',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'检疫时间',
				field:'checkdate',
				width:80,
				halign:'center',
				align:'center'
			},{
				title:'血清号',
				field:'xueq',
				width:80,
				halign:'center',
				align:'center',
				editor:{type:'validatebox',options:{}}
			},{
				title:'B-V',
				field:'bv',
				width:60,
				halign:'center',
				align:'center',
				editor:{type:'validatebox',options:{}}
			},{
				title:'STLV',
				field:'stlv',
				width:80,
				halign:'center',
				align:'center',
				editor:{type:'validatebox',options:{}}
			},{
				title:'SRV',
				field:'srv',
				width:80,
				halign:'center',
				align:'center',
				editor:{type:'validatebox',options:{}}
			},{
				title:'SIV',
				field:'siv',
				width:80,
				halign:'center',
				align:'center',
				editor:{type:'validatebox',options:{}}
			},{
				title:'FILO',
				field:'filo',
				width:80,
				halign:'center',
				align:'center',
				editor:{type:'validatebox',options:{}}
			},{
				title:'备注',
				field:'remark',
				width:90,
				halign:'center',
				align:'center',
				editor: { type: 'validatebox', options: { } }
			}
			]],
			onSelect:function(rowIndex, rowData){
    		  //$('#exportCheckItemsButton').linkbutton('enable');
    		  //if(row.status=="1"){
    		  		//$('#exportCheckItemsAddButton').linkbutton('enable');
    		  //}
			},
			onLoadSuccess:function(data){
				$('#exportCheckItemsButton').linkbutton('disable');
	    	   //$('#exportCheckItemsButton').linkbutton('disable');
	    	   //if(row.status=="1"){
	           		$('#exportCheckItemsAddButton').linkbutton('enable');
	           //}
	           var selectid=$('#exid').val();
	           $('#kind').val('virus');
	           document.getElementById("titleAndCheckItem1").innerHTML="病毒检疫 ("+row.title+")";
	           if(selectid != ""){
			          for(var i = 0 ; i< data.rows.length;i++){
			             if(selectid == data.rows[i].id){
			            	$('#exportOneTable').datagrid('selectRow',i);
			             }
			          }
			   }
			   var rows=$("#exportOneTable").datagrid("getRows");
    			 for(var i=0;i<rows.length;i++){
      				var rowIndex = $('#exportOneTable').datagrid('getRowIndex', rows[i]);
      				$('#exportOneTable').datagrid('beginEdit', rowIndex);
      			}
			},
			toolbar:'#toolbar1',
			pageSize : 12,//默认选择的分页是每页5行数据         
			pageList : [ 5, 10, 12, 15, 20 ],//可以选择的分页集合       
		    nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取       
		    striped : true,//设置为true将交替显示行背景。      
		    collapsible : true//显示可折叠按钮 
			
	   	});
    
}

function addVaccine(){
	$('#layoutMainDiv').css('display','none');
	$('#layoutOneDiv').css('display','');
	var row= $('#exportTable').datagrid('getSelected');
	$('#exportOneTable').datagrid({
		url : sybp()+'/exportAction_loadMonkeyList.action?orderid='+row.id+'&kind=vaccine',
		title:'',
		height:tableHeight,
		width:tableWidth,
		nowarp:  false,//单元格里自动换行
		singleSelect:false,
		fitColumns:false,
	    //pagination:true,//分页
		sortName:'id',
		columns :[[{
			title:'id',
			field:'id',
			width:150,
			hidden:true
		},{
			title:'动物编号',
			field:'monkeyid',
			width:80,
			halign:'center',
			align:'center'
		},{
			title:'检疫时间',
			field:'checkdate',
			width:80,
			halign:'center',
			align:'center'
		},{
			title:'麻疹',
			field:'m',
			width:80,
			halign:'center',
			align:'center',
			editor:{type:'validatebox'}
			//editor:{type:'checkbox',options:{}}
			//formatter:function(value,row,index){
			//	if(row.checked){
			//		return '<input type="checkbox" name="DataGridCheckbox" checked="checked">';
			//	}else{
			//		return '<input type="checkbox" name="DataGridCheckbox">';
			//	}
			//}
		},{
			title:'甲肝',
			field:'a',
			width:80,
			halign:'center',
			align:'center',
			checkbox:false,
			editor:{type:'validatebox',options:{}}
		},{
			title:'乙肝',
			field:'b',
			width:80,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'备注',
			field:'remark',
			width:90,
			halign:'center',
			align:'center',
			editor: { type: 'validatebox', options: {} }
		}
		]],
		onSelect:function(rowIndex, rowData){
		  //$('#exportCheckItemsButton').linkbutton('enable');
		  //if(row.status=="1"){
		  //		$('#exportCheckItemsAddButton').linkbutton('enable');
		 // }
		},
		onLoadSuccess:function(data){
    	   //$('#exportCheckItemsButton').linkbutton('enable');
			$('#exportCheckItemsButton').linkbutton('disable');
    	   //if(row.status=="1"){
           		$('#exportCheckItemsAddButton').linkbutton('enable');
           //}
           var selectid=$('#exid').val();
           $('#kind').val('vaccine');
           //$('#title').val(row.title);
           document.getElementById("titleAndCheckItem1").innerHTML="疫苗检疫("+row.title+")";
           if(selectid != ""){
		          for(var i = 0 ; i< data.rows.length;i++){
		             if(selectid == data.rows[i].id){
		            	$('#exportOneTable').datagrid('selectRow',i);
		             }
		          }
		   }
		   var rows=$("#exportOneTable").datagrid("getRows");
			 for(var i=0;i<rows.length;i++){
  				var rowIndex = $('#exportOneTable').datagrid('getRowIndex', rows[i]);
  				$('#exportOneTable').datagrid('beginEdit', rowIndex);
  			}
		},
		toolbar:'#toolbar1',
		pageSize : 12,//默认选择的分页是每页5行数据         
		pageList : [ 5, 10, 12, 15, 20 ],//可以选择的分页集合       
	    nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取       
	    striped : true,//设置为true将交替显示行背景。      
	    collapsible : true//显示可折叠按钮 
		
   	});

}
function addBacteria(){
	$('#layoutMainDiv').css('display','none');
	$('#layoutOneDiv').css('display','');
	var row= $('#exportTable').datagrid('getSelected');
	$('#exportOneTable').datagrid({
		url : sybp()+'/exportAction_loadMonkeyList.action?orderid='+row.id+'&kind=bacteria',
		title:'',
		height:tableHeight,
		width:tableWidth,
		nowarp:  false,//单元格里自动换行
		singleSelect:true,
		fitColumns:false,
	    //pagination:true,//分页
		sortName:'id',
		columns :[[{
			title:'id',
			field:'id',
			width:150,
			hidden:true
		},{
			title:'动物编号',
			field:'monkeyid',
			width:80,
			halign:'center',
			align:'center'
		},{
			title:'检疫时间',
			field:'checkdate',
			width:80,
			halign:'center',
			align:'center'
		},{
			title:'肛拭号',
			field:'ypid',
			width:80,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'沙门氏菌',
			field:'salm',
			width:60,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'志贺氏菌',
			field:'shig',
			width:80,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'耶尔森氏菌',
			field:'yers',
			width:80,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'备注',
			field:'remark',
			width:90,
			halign:'center',
			align:'center',
			editor: { type: 'validatebox', options: { } }
		}
		]],
		onSelect:function(rowIndex, rowData){
		  //$('#exportCheckItemsButton').linkbutton('enable');
		  //if(row.status=="1"){
		  //		$('#exportCheckItemsAddButton').linkbutton('enable');
		  //}
		},
		onLoadSuccess:function(data){
    	   //$('#exportCheckItemsButton').linkbutton('enable');
			$('#exportCheckItemsButton').linkbutton('disable');
    	  // if(row.status=="1"){
           		$('#exportCheckItemsAddButton').linkbutton('enable');
          // }
           var selectid=$('#exid').val();
           $('#kind').val('bacteria');
           document.getElementById("titleAndCheckItem1").innerHTML="细菌检疫("+row.title+")";
           if(selectid != ""){
		          for(var i = 0 ; i< data.rows.length;i++){
		             if(selectid == data.rows[i].id){
		            	$('#exportOneTable').datagrid('selectRow',i);
		             }
		          }
		   }
		   var rows=$("#exportOneTable").datagrid("getRows");
			 for(var i=0;i<rows.length;i++){
  				var rowIndex = $('#exportOneTable').datagrid('getRowIndex', rows[i]);
  				$('#exportOneTable').datagrid('beginEdit', rowIndex);
  			}
		},
		toolbar:'#toolbar1',
		pageSize : 12,//默认选择的分页是每页5行数据         
		pageList : [ 5, 10, 12, 15, 20 ],//可以选择的分页集合       
	    nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取       
	    striped : true,//设置为true将交替显示行背景。      
	    collapsible : true//显示可折叠按钮 
		
   	});

}
function addInParasite(){
	$('#layoutMainDiv').css('display','none');
	$('#layoutOneDiv').css('display','');
	var row= $('#exportTable').datagrid('getSelected');
	$('#exportOneTable').datagrid({
		url : sybp()+'/exportAction_loadMonkeyList.action?orderid='+row.id+'&kind=parasite',
		title:'',
		height:tableHeight,
		width:tableWidth,
		nowarp:  false,//单元格里自动换行
		singleSelect:true,
		fitColumns:false,
	    //pagination:true,//分页
		sortName:'id',
		columns :[[{
			title:'id',
			field:'id',
			width:150,
			hidden:true
		},{
			title:'动物编号',
			field:'monkeyid',
			width:80,
			halign:'center',
			align:'center'
		},{
			title:'检疫时间',
			field:'checkdate',
			width:80,
			halign:'center',
			align:'center'
		},{
			title:'样本号',
			field:'bhao',
			width:80,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'溶组织内内阿米',
			field:'amb',
			width:100,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'蠕虫',
			field:'rc',
			width:80,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'鞭毛虫',
			field:'bmc',
			width:80,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'体外寄生虫',
			field:'twjsc',
			width:100,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'备注',
			field:'remark',
			width:90,
			halign:'center',
			align:'center',
			editor: { type: 'validatebox', options: { } }
		}
		]],
		onSelect:function(rowIndex, rowData){
		  //$('#exportCheckItemsButton').linkbutton('enable');
		  //if(row.status=="1"){
		  //		$('#exportCheckItemsAddButton').linkbutton('enable');
		  //}
		},
		onLoadSuccess:function(data){
    	   $('#exportCheckItemsButton').linkbutton('disable');
    	   //if(row.status=="1"){
           		$('#exportCheckItemsAddButton').linkbutton('enable');
           //}
           var selectid=$('#exid').val();
           $('#kind').val('parasite');
           document.getElementById("titleAndCheckItem1").innerHTML="寄生虫检疫("+row.title+")";
           if(selectid != ""){
		          for(var i = 0 ; i< data.rows.length;i++){
		             if(selectid == data.rows[i].id){
		            	$('#exportOneTable').datagrid('selectRow',i);
		             }
		          }
		   }
		   var rows=$("#exportOneTable").datagrid("getRows");
			 for(var i=0;i<rows.length;i++){
  				var rowIndex = $('#exportOneTable').datagrid('getRowIndex', rows[i]);
  				$('#exportOneTable').datagrid('beginEdit', rowIndex);
  			}
		},
		toolbar:'#toolbar1',
		pageSize : 12,//默认选择的分页是每页5行数据         
		pageList : [ 5, 10, 12, 15, 20 ],//可以选择的分页集合       
	    nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取       
	    striped : true,//设置为true将交替显示行背景。      
	    collapsible : true//显示可折叠按钮 
		
   	});

}
function addOutParasite(){
	$('#layoutMainDiv').css('display','none');
	$('#layoutOneDiv').css('display','');
	var row= $('#exportTable').datagrid('getSelected');
	$('#exportOneTable').datagrid({
		url : sybp()+'/exportAction_loadMonkeyList.action?orderid='+row.id+'&kind=parasite',
		title:'',
		height:tableHeight,
		width:tableWidth,
		nowarp:  false,//单元格里自动换行
		singleSelect:true,
		fitColumns:false,
	    //pagination:true,//分页
		sortName:'id',
		columns :[[{
			title:'id',
			field:'id',
			width:150,
			hidden:true
		},{
			title:'动物编号',
			field:'monkeyid',
			width:80,
			halign:'center',
			align:'center'
		},{
			title:'检疫时间',
			field:'checkdate',
			width:80,
			halign:'center',
			align:'center'
		},{
			title:'样本号',
			field:'bhao',
			width:80,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'体外寄生虫',
			field:'twjsc',
			width:100,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'其他',
			field:'other',
			width:80,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'备注',
			field:'remark',
			width:90,
			halign:'center',
			align:'center',
			editor: { type: 'validatebox', options: { } }
		}
		]],
		onSelect:function(rowIndex, rowData){
		  $('#exportCheckItemsButton').linkbutton('enable');
		  if(row.status=="1"){
		  		$('#exportCheckItemsAddButton').linkbutton('enable');
		  }
		},
		onLoadSuccess:function(data){
    	   $('#exportCheckItemsButton').linkbutton('enable');
    	   if(row.status=="1"){
           		$('#exportCheckItemsAddButton').linkbutton('enable');
           }
           var selectid=$('#exid').val();
           $('#kind').val('parasite');
           document.getElementById("titleAndCheckItem1").innerHTML="体外寄生虫检疫("+row.title+")";
           if(selectid != ""){
		          for(var i = 0 ; i< data.rows.length;i++){
		             if(selectid == data.rows[i].id){
		            	$('#exportOneTable').datagrid('selectRow',i);
		             }
		          }
		   }
		   var rows=$("#exportOneTable").datagrid("getRows");
			 for(var i=0;i<rows.length;i++){
  				var rowIndex = $('#exportOneTable').datagrid('getRowIndex', rows[i]);
  				$('#exportOneTable').datagrid('beginEdit', rowIndex);
  			}
		},
		toolbar:'#toolbar1',
		pageSize : 12,//默认选择的分页是每页5行数据         
		pageList : [ 5, 10, 12, 15, 20 ],//可以选择的分页集合       
	    nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取       
	    striped : true,//设置为true将交替显示行背景。      
	    collapsible : true//显示可折叠按钮 
		
   	});

}
function addTb(){
	$('#layoutMainDiv').css('display','none');
	$('#layoutOneDiv').css('display','');
	var row= $('#exportTable').datagrid('getSelected');
	$('#exportOneTable').datagrid({
		url : sybp()+'/exportAction_loadMonkeyList.action?orderid='+row.id+'&kind=tb',
		title:'',
		height:tableHeight,
		width:tableWidth,
		nowarp:  false,//单元格里自动换行
		singleSelect:true,
		fitColumns:false,
	    //pagination:true,//分页
		sortName:'id',
		columns :[[{
			title:'id',
			field:'id',
			width:150,
			hidden:true
		},{
			title:'动物编号',
			field:'monkeyid',
			width:80,
			halign:'center',
			align:'center'
		},{
			title:'检疫时间',
			field:'checkdate',
			width:80,
			halign:'center',
			align:'center'
		},{
			title:'右眼',
			field:'surface',
			width:80,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'TB24',
			field:'tb24',
			width:60,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'TB48',
			field:'tb48',
			width:80,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'TB72',
			field:'tb72',
			width:80,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'备注',
			field:'remark',
			width:90,
			halign:'center',
			align:'center',
			editor: { type: 'validatebox', options: { } }
		}
		]],
		onSelect:function(rowIndex, rowData){
		  //$('#exportCheckItemsButton').linkbutton('enable');
		  //if(row.status=="1"){
		  //		$('#exportCheckItemsAddButton').linkbutton('enable');
		  //}
		},
		onLoadSuccess:function(data){
    	   $('#exportCheckItemsButton').linkbutton('disable');
    	   //if(row.status=="1"){
           		$('#exportCheckItemsAddButton').linkbutton('enable');
          // }
           var selectid=$('#exid').val();
           $('#kind').val('tb');
           document.getElementById("titleAndCheckItem1").innerHTML="TB检疫("+row.title+")";
           if(selectid != ""){
		          for(var i = 0 ; i< data.rows.length;i++){
		             if(selectid == data.rows[i].id){
		            	$('#exportOneTable').datagrid('selectRow',i);
		             }
		          }
		   }
		   var rows=$("#exportOneTable").datagrid("getRows");
			 for(var i=0;i<rows.length;i++){
  				var rowIndex = $('#exportOneTable').datagrid('getRowIndex', rows[i]);
  				$('#exportOneTable').datagrid('beginEdit', rowIndex);
  			}
		},
		toolbar:'#toolbar1',
		pageSize : 12,//默认选择的分页是每页5行数据         
		pageList : [ 5, 10, 12, 15, 20 ],//可以选择的分页集合       
	    nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取       
	    striped : true,//设置为true将交替显示行背景。      
	    collapsible : true//显示可折叠按钮 
		
   	});

}
function addQc(){
	$('#layoutMainDiv').css('display','none');
	$('#layoutOneDiv').css('display','');
	var row= $('#exportTable').datagrid('getSelected');
	$('#exportOneTable').datagrid({
		url : sybp()+'/exportAction_loadMonkeyList.action?orderid='+row.id+'&kind=qc',
		title:'',
		height:tableHeight,
		width:tableWidth,
		nowarp:  false,//单元格里自动换行
		singleSelect:true,
		fitColumns:false,
	    //pagination:true,//分页
		sortName:'id',
		columns :[[{
			title:'id',
			field:'id',
			width:150,
			hidden:true
		},{
			title:'动物编号',
			field:'monkeyid',
			width:80,
			halign:'center',
			align:'center'
		},{
			title:'检疫时间',
			field:'checkdate',
			width:80,
			halign:'center',
			align:'center'
		},{
			title:'驱虫药品',
			field:'qcyp',
			width:80,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'驱虫用量',
			field:'qcyl',
			width:60,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'驱虫日期',
			field:'qcrq',
			width:80,
			halign:'center',
			align:'center',
			editor:{type:'datebox',options:{}}
		},{
			title:'备注',
			field:'remark',
			width:90,
			halign:'center',
			align:'center',
			editor: { type: 'validatebox', options: { } }
		}
		]],
		onSelect:function(rowIndex, rowData){
		  //$('#exportCheckItemsButton').linkbutton('enable');
		  //if(row.status=="1"){
		  //		$('#exportCheckItemsAddButton').linkbutton('enable');
		  //}
		},
		onLoadSuccess:function(data){
    	   $('#exportCheckItemsButton').linkbutton('disable');
    	  // if(row.status=="1"){
           		$('#exportCheckItemsAddButton').linkbutton('enable');
          // }
           var selectid=$('#exid').val();
           $('#kind').val('qc');
           document.getElementById("titleAndCheckItem1").innerHTML="驱虫检疫("+row.title+")";
           if(selectid != ""){
		          for(var i = 0 ; i< data.rows.length;i++){
		             if(selectid == data.rows[i].id){
		            	$('#exportOneTable').datagrid('selectRow',i);
		             }
		          }
		   }
		   var rows=$("#exportOneTable").datagrid("getRows");
			 for(var i=0;i<rows.length;i++){
  				var rowIndex = $('#exportOneTable').datagrid('getRowIndex', rows[i]);
  				$('#exportOneTable').datagrid('beginEdit', rowIndex);
  			}
		},
		toolbar:'#toolbar1',
		pageSize : 12,//默认选择的分页是每页5行数据         
		pageList : [ 5, 10, 12, 15, 20 ],//可以选择的分页集合       
	    nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取       
	    striped : true,//设置为true将交替显示行背景。      
	    collapsible : true//显示可折叠按钮 
		
   	});

}
function addXcg(){
	$('#layoutMainDiv').css('display','none');
	$('#layoutOneDiv').css('display','');
	var row= $('#exportTable').datagrid('getSelected');
	$('#exportOneTable').datagrid({
		url : sybp()+'/exportAction_loadMonkeyList.action?orderid='+row.id+'&kind=xcg',
		title:'',
		height:tableHeight,
		width:tableWidth,
		nowarp:  false,//单元格里自动换行
		singleSelect:true,
		fitColumns:false,
	    //pagination:true,//分页
		sortName:'id',
		columns :[[{
			title:'id',
			field:'id',
			width:150,
			hidden:true
		},{
			title:'动物编号',
			field:'monkeyid',
			width:80,
			halign:'center',
			align:'center'
		},{
			title:'检疫时间',
			field:'checkdate',
			width:80,
			halign:'center',
			align:'center'
		},{
			title:'血样号',
			field:'bhao',
			width:90,
			halign:'center',
			align:'center',
			editor: { type: 'validatebox', options: { } }
		},{
			title:'WBC',
			field:'wbc',
			width:80,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'RBC',
			field:'rbc',
			width:60,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'HGB',
			field:'hgb',
			width:80,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'HCT',
			field:'hct',
			width:80,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'PLT',
			field:'plt',
			width:90,
			halign:'center',
			align:'center',
			editor: { type: 'validatebox', options: { } }
		},{
			title:'MCV',
			field:'mcv',
			width:80,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'MCH',
			field:'mch',
			width:60,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'MCHC',
			field:'mchc',
			width:80,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'LYM',
			field:'lym',
			width:80,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'MID',
			field:'mid',
			width:90,
			halign:'center',
			align:'center',
			editor: { type: 'validatebox', options: { } }
		},{
			title:'GRA',
			field:'gra',
			width:90,
			halign:'center',
			align:'center',
			editor: { type: 'validatebox', options: { } }
		}
		]],
		onSelect:function(rowIndex, rowData){
		  //$('#exportCheckItemsButton').linkbutton('enable');
		  //if(row.status=="1"){
		  //		$('#exportCheckItemsAddButton').linkbutton('enable');
		  //}
		},
		onLoadSuccess:function(data){
    	   $('#exportCheckItemsButton').linkbutton('disable');
    	   //if(row.status=="1"){
           		$('#exportCheckItemsAddButton').linkbutton('enable');
           //}
           var selectid=$('#exid').val();
           $('#kind').val('xcg');
           document.getElementById("titleAndCheckItem1").innerHTML="血常规检疫("+row.title+")";
           if(selectid != ""){
		          for(var i = 0 ; i< data.rows.length;i++){
		             if(selectid == data.rows[i].id){
		            	$('#exportOneTable').datagrid('selectRow',i);
		             }
		          }
		   }
		   var rows=$("#exportOneTable").datagrid("getRows");
			 for(var i=0;i<rows.length;i++){
  				var rowIndex = $('#exportOneTable').datagrid('getRowIndex', rows[i]);
  				$('#exportOneTable').datagrid('beginEdit', rowIndex);
  			}
		},
		toolbar:'#toolbar1',
		pageSize : 12,//默认选择的分页是每页5行数据         
		pageList : [ 5, 10, 12, 15, 20 ],//可以选择的分页集合       
	    nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取       
	    striped : true,//设置为true将交替显示行背景。      
	    collapsible : true//显示可折叠按钮 
		
   	});

}
function addXysh(){
	$('#layoutMainDiv').css('display','none');
	$('#layoutOneDiv').css('display','');
	var row= $('#exportTable').datagrid('getSelected');
	$('#exportOneTable').datagrid({
		url : sybp()+'/exportAction_loadMonkeyList.action?orderid='+row.id+'&kind=xysh',
		title:'',
		height:tableHeight,
		width:tableWidth,
		nowarp:  false,//单元格里自动换行
		singleSelect:true,
		fitColumns:false,
	    //pagination:true,//分页
		sortName:'id',
		columns :[[{
			title:'id',
			field:'id',
			width:150,
			hidden:true
		},{
			title:'动物编号',
			field:'monkeyid',
			width:80,
			halign:'center',
			align:'center'
		},{
			title:'检疫时间',
			field:'checkdate',
			width:80,
			halign:'center',
			align:'center'
		},{
			title:'血样号',
			field:'bhao',
			width:80,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'AST',
			field:'ast',
			width:60,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'ALT',
			field:'alt',
			width:80,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'ALP',
			field:'alp',
			width:80,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'TP',
			field:'tp',
			width:90,
			halign:'center',
			align:'center',
			editor: { type: 'validatebox', options: { } }
		},{
			title:'ALB',
			field:'alb',
			width:60,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'GGT',
			field:'ggt',
			width:80,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'TBIL',
			field:'tbil',
			width:80,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'BUN',
			field:'bun',
			width:90,
			halign:'center',
			align:'center',
			editor: { type: 'validatebox', options: { } }
		},{
			title:'CREA',
			field:'crea',
			width:60,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'GLU',
			field:'glu',
			width:80,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'TG',
			field:'tg',
			width:80,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'CHOL',
			field:'chol',
			width:90,
			halign:'center',
			align:'center',
			editor: { type: 'validatebox', options: { } }
		},{
			title:'LDH',
			field:'ldh',
			width:60,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'CK',
			field:'ck',
			width:80,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'NA',
			field:'na',
			width:80,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'K',
			field:'k',
			width:90,
			halign:'center',
			align:'center',
			editor: { type: 'validatebox', options: { } }
		},{
			title:'CI',
			field:'ci',
			width:90,
			halign:'center',
			align:'center',
			editor: { type: 'validatebox', options: { } }
		}
		]],
		onSelect:function(rowIndex, rowData){
		  //$('#exportCheckItemsButton').linkbutton('enable');
		  //if(row.status=="1"){
		  //		$('#exportCheckItemsAddButton').linkbutton('enable');
		  //}
		},
		onLoadSuccess:function(data){
    	   $('#exportCheckItemsButton').linkbutton('disable');
    	   //if(row.status=="1"){
           		$('#exportCheckItemsAddButton').linkbutton('enable');
          // }
           var selectid=$('#exid').val();
           $('#kind').val('xysh');
           document.getElementById("titleAndCheckItem1").innerHTML="血生化检疫("+row.title+")";
           if(selectid != ""){
		          for(var i = 0 ; i< data.rows.length;i++){
		             if(selectid == data.rows[i].id){
		            	$('#exportOneTable').datagrid('selectRow',i);
		             }
		          }
		   }
		   var rows=$("#exportOneTable").datagrid("getRows");
			 for(var i=0;i<rows.length;i++){
  				var rowIndex = $('#exportOneTable').datagrid('getRowIndex', rows[i]);
  				$('#exportOneTable').datagrid('beginEdit', rowIndex);
  			}
		},
		toolbar:'#toolbar1',
		pageSize : 12,//默认选择的分页是每页5行数据         
		pageList : [ 5, 10, 12, 15, 20 ],//可以选择的分页集合       
	    nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取       
	    striped : true,//设置为true将交替显示行背景。      
	    collapsible : true//显示可折叠按钮 
		
   	});

}

function addInfectious(){
	$('#layoutMainDiv').css('display','none');
	$('#layoutOneDiv').css('display','');
	var row= $('#exportTable').datagrid('getSelected');
	$('#exportOneTable').datagrid({
		url : sybp()+'/exportAction_loadMonkeyList.action?orderid='+row.id+'&kind=infectious',
		title:'',
		height:tableHeight,
		width:tableWidth,
		nowarp:  false,//单元格里自动换行
		singleSelect:true,
		fitColumns:false,
	    //pagination:true,//分页
		sortName:'id',
		columns :[[{
			title:'id',
			field:'id',
			width:150,
			hidden:true
		},{
			title:'动物编号',
			field:'monkeyid',
			width:80,
			halign:'center',
			align:'center'
		},{
			title:'检疫时间',
			field:'checkdate',
			width:80,
			halign:'center',
			align:'center'
		},{
			title:'样本号',
			field:'ypin',
			width:80,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'药名',
			field:'drugs_name',
			width:160,
			halign:'center',
			align:'center',
			editor:{type:'combobox',options:{//url : sybp()+'/infectiousAction_loadListInfectious.action',
											//valueField:'drugId',
											valueField:'drugName',
            								textField:'drugName',
            								data:drugsDatas,
            								required:true}},
            //formatter:myselfformatter
            /*formatter:function(value,rowData,rowIndex){
            			for(var i=0;i<drugsDatas.length;i++){
            				if(drugsDatas[i].drugsId==value){
            					alert(value+"------"+drugsDatas[i].drugsId);
            					return drugsDatas[i].drugsName;
            				}
            			}
            }*/
		},{
			title:'药量',
			field:'drugs_count',
			width:80,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		},{
			title:'备注',
			field:'remark',
			width:180,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		}
		]],
		onSelect:function(rowIndex, rowData){
		},
		onLoadSuccess:function(data){
    	   $('#exportCheckItemsButton').linkbutton('disable');
           $('#exportCheckItemsAddButton').linkbutton('enable');
          
           var selectid=$('#exid').val();
           $('#kind').val('infectious');
           document.getElementById("titleAndCheckItem1").innerHTML="传染病检疫("+row.title+")";
           if(selectid != ""){
		          for(var i = 0 ; i< data.rows.length;i++){
		             if(selectid == data.rows[i].id){
		            	$('#exportOneTable').datagrid('selectRow',i);
		             }
		          }
		   }
		   var rows=$("#exportOneTable").datagrid("getRows");
			 for(var i=0;i<rows.length;i++){
  				var rowIndex = $('#exportOneTable').datagrid('getRowIndex', rows[i]);
  				$('#exportOneTable').datagrid('beginEdit', rowIndex);
  			}
		},
		toolbar:'#toolbar1',
		pageSize : 12,//默认选择的分页是每页5行数据         
		pageList : [ 5, 10, 12, 15, 20 ],//可以选择的分页集合       
	    nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取       
	    striped : true,//设置为true将交替显示行背景。      
	    collapsible : true//显示可折叠按钮 
		
   	});

}

function addX(){
	$('#layoutMainDiv').css('display','none');
	$('#layoutOneDiv').css('display','');
	var row= $('#exportTable').datagrid('getSelected');
	$('#exportOneTable').datagrid({
		url : sybp()+'/exportAction_loadMonkeyList.action?orderid='+row.id+'&kind=x',
		title:'',
		height:tableHeight,
		width:tableWidth,
		nowarp:  false,//单元格里自动换行
		singleSelect:true,
		fitColumns:false,
	    pagination:true,//分页
		sortName:'id',
		columns :[[{
			title:'id',
			field:'id',
			width:150,
			hidden:true
		},{
			title:'动物编号',
			field:'monkeyid',
			width:80,
			halign:'center',
			align:'center'
		},{
			title:'检疫时间',
			field:'checkdate',
			width:80,
			halign:'center',
			align:'center'
		},{
			title:'检查部位',
			field:'checkarea',
			width:160,
			halign:'center',
			align:'center',
			editor:{type:'combobox',options:{url : sybp()+'/xAction_loadListCheckArea.action',
											valueField:'checkAreaId',
            								textField:'checkAreaName',
            								required:true}}
		},{
			title:'备注',
			field:'remark',
			width:180,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		}
		]],
		onSelect:function(rowIndex, rowData){
		},
		onLoadSuccess:function(data){
    	   $('#exportCheckItemsButton').linkbutton('disable');
           $('#exportCheckItemsAddButton').linkbutton('enable');
          
           var selectid=$('#exid').val();
           $('#kind').val('x');
           document.getElementById("titleAndCheckItem1").innerHTML="X光检疫("+row.title+")";
           if(selectid != ""){
		          for(var i = 0 ; i< data.rows.length;i++){
		             if(selectid == data.rows[i].id){
		            	$('#exportOneTable').datagrid('selectRow',i);
		             }
		          }
		   }
		   var rows=$("#exportOneTable").datagrid("getRows");
			 for(var i=0;i<rows.length;i++){
  				var rowIndex = $('#exportOneTable').datagrid('getRowIndex', rows[i]);
  				$('#exportOneTable').datagrid('beginEdit', rowIndex);
  			}
		},
		toolbar:'#toolbar1',
		pageSize : 12,//默认选择的分页是每页5行数据         
		pageList : [ 5, 10, 12, 15, 20 ],//可以选择的分页集合       
	    nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取       
	    striped : true,//设置为true将交替显示行背景。      
	    collapsible : true//显示可折叠按钮 
		
   	});

}

function addSurface(){
	$('#layoutMainDiv').css('display','none');
	$('#layoutOneDiv').css('display','');
	var row= $('#exportTable').datagrid('getSelected');
	$('#exportOneTable').datagrid({
		url : sybp()+'/exportAction_loadMonkeyList.action?orderid='+row.id+'&kind=surface',
		title:'',
		height:tableHeight,
		width:tableWidth,
		nowarp:  false,//单元格里自动换行
		singleSelect:true,
		fitColumns:false,
	    pagination:true,//分页
		sortName:'id',
		columns :[[{
			title:'id',
			field:'id',
			width:150,
			hidden:true
		},{
			title:'动物编号',
			field:'monkeyid',
			width:80,
			halign:'center',
			align:'center'
		},{
			title:'检疫时间',
			field:'checkdate',
			width:100,
			halign:'center',
			align:'center'
		},{
			title:'备注',
			field:'remark',
			width:180,
			halign:'center',
			align:'center',
			editor:{type:'validatebox',options:{}}
		}
		]],
		onSelect:function(rowIndex, rowData){
		},
		onLoadSuccess:function(data){
    	   $('#exportCheckItemsButton').linkbutton('disable');
           $('#exportCheckItemsAddButton').linkbutton('enable');
          
           var selectid=$('#exid').val();
           $('#kind').val('surface');
           document.getElementById("titleAndCheckItem1").innerHTML="体表检疫("+row.title+")";
           if(selectid != ""){
		          for(var i = 0 ; i< data.rows.length;i++){
		             if(selectid == data.rows[i].id){
		            	$('#exportOneTable').datagrid('selectRow',i);
		             }
		          }
		   }
		   var rows=$("#exportOneTable").datagrid("getRows");
			 for(var i=0;i<rows.length;i++){
  				var rowIndex = $('#exportOneTable').datagrid('getRowIndex', rows[i]);
  				$('#exportOneTable').datagrid('beginEdit', rowIndex);
  			}
		},
		toolbar:'#toolbar1',
		pageSize : 12,//默认选择的分页是每页5行数据         
		pageList : [ 5, 10, 12, 15, 20 ],//可以选择的分页集合       
	    nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取       
	    striped : true,//设置为true将交替显示行背景。      
	    collapsible : true//显示可折叠按钮 
		
   	});

}
//保存按钮事件
function uploadCheckItemsButton1(){
	
		$.messager.confirm('确认对话框', '是否确认保存？', function(r){
			if (r){
					saveInventoryRecord();
				}else{
					for(var i=0;i<rows.length;i++){
	      				var rowIndex = $('#exportOneTable').datagrid('getRowIndex', rows[i]);
	      				$('#exportOneTable').datagrid('beginEdit', rowIndex);
	      			} 
				}
		})
	
}
//后台保存盘点记录，保存后打开记录
function saveInventoryRecord(){
    var kind=$('#kind').val();
    var row=$("#exportTable").datagrid("getSelected");
    if(kind=='vaccine'){
    	var rows=$("#exportOneTable").datagrid("getRows");
    	for(var i=0;i<rows.length;i++){
			var rowIndex = $('#exportOneTable').datagrid('getRowIndex', rows[i]);
			$('#exportOneTable').datagrid('endEdit', rowIndex);
		}
		rows=$("#exportOneTable").datagrid("getRows");
		var ypins=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].ypin==''){
            	ypins.push('-'); 
            }else{
            	ypins.push(rows[i].ypin); 
            }
        }
		//麻疹
		var ms=[];
		var checkedItems = $('#exportOneTable').datagrid('getChecked');
		$.each(checkedItems, function(index, item){
			//names.push(item.productname);
		});
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].m==''){
            	ms.push('-');   
            }else{
            	ms.push(rows[i].m); 
            }
        }
        //甲肝
        var as=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].a==''){
            	as.push('-');   
            }else{
            	as.push(rows[i].a); 
            }
        }
        //乙肝
        var bs=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].b==''){
            	bs.push('-');   
            }else{
            	bs.push(rows[i].b); 
            }
        }
        var monkeyids=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].monkeyid==''){
            	monkeyids.push(0);   
            }else{
            	monkeyids.push(rows[i].monkeyid); 
            }
        }
        var remarks=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].remark==''){
            	remarks.push("-");   
            }else{
            	remarks.push(rows[i].remark); 
            }
        }
	$.ajax({
			url:sybp()+'/vaccineAction_saveRecord.action?orderid='+row.id+'&monkeylist='+monkeyids+'&ypins='+ypins+'&ms='+ms+'&as='+as+'&bs='+bs+'&remarks='+remarks,
			type:'post',
			dataType:'json',
			success:function(r){
				if(r && r.success){
					parent.showMessager(1,'疫苗检疫信息保存成功',true,5000);
					//$('#inventoryDate').datebox('setValue',r.inventoryDate);
					//onSearchButton();
					$('#exportCheckItemsAddButton').linkbutton('disable');
					//导出按钮打开.
					$('#exportCheckItemsButton').linkbutton('enable');
				}else{
					$.messager.alert('提示','请检查录入的数据');
					$('#exportCheckItemsAddButton').linkbutton('enable');
					parent.parent.showMessager(2,'疫苗检疫信息保存失败',true,5000);
				}
			}
		});
    }
    if(kind=='qc'){
    	var rows=$("#exportOneTable").datagrid("getRows");
    	for(var i=0;i<rows.length;i++){
			var rowIndex = $('#exportOneTable').datagrid('getRowIndex', rows[i]);
			$('#exportOneTable').datagrid('endEdit', rowIndex);
		}
		rows=$("#exportOneTable").datagrid("getRows");
        var qcyps=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].qcyp==''){
            	qcyps.push('-');   
            }else{
            	qcyps.push(rows[i].qcyp); 
            }
        }
        var monkeyids=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].monkeyid==''){
            	monkeyids.push(0);   
            }else{
            	monkeyids.push(rows[i].monkeyid); 
            }
        }
        var qcyls=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].qcyl==''){
            	qcyls.push('-');   
            }else{
            	qcyls.push(rows[i].qcyl); 
            }
        }
        var qcrqs=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].qcrq==''){
            	qcrqs.push('-');   
            }else{
            	qcrqs.push(rows[i].qcrq); 
            }
        }
        var remarks=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].remark==''){
            	remarks.push('-');   
            }else{
            	remarks.push(rows[i].remark); 
            }
        }
    	$.ajax({
    			url:sybp()+'/qcAction_saveRecord.action?orderid='+row.id+'&qcyps='+qcyps+'&qcyls='+qcyls+'&qcrqs='+qcrqs+'&monkeylist='+monkeyids
    			+'&remarks='+remarks,
    			type:'post',
    			dataType:'json',
    			success:function(r){
    				if(r && r.success){
    					parent.showMessager(1,'驱虫检疫信息保存成功',true,5000);
    					$('#exportCheckItemsAddButton').linkbutton('disable');
    					$('#exportCheckItemsButton').linkbutton('enable');
    				}else{
    					parent.parent.showMessager(2,'驱虫检疫信息保存失败',true,5000);
    				}
    			}
    		});
        }
    if(kind=='bacteria'){
    	var rows=$("#exportOneTable").datagrid("getRows");
    	for(var i=0;i<rows.length;i++){
			var rowIndex = $('#exportOneTable').datagrid('getRowIndex', rows[i]);
			$('#exportOneTable').datagrid('endEdit', rowIndex);
		}
		rows=$("#exportOneTable").datagrid("getRows");
        var ypids=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].ypid==''){
            	ypids.push('-');   
            }else{
            	ypids.push(rows[i].ypid); 
            }
        }
        var monkeyids=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].monkeyid==''){
            	monkeyids.push(0);   
            }else{
            	monkeyids.push(rows[i].monkeyid); 
            }
        }
        var salms=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].salm==''){
            	salms.push('-');   
            }else{
            	salms.push(rows[i].salm); 
            }
        }
        var shigs=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].shig==''){
            	shigs.push('-');   
            }else{
            	shigs.push(rows[i].shig); 
            }
        }
        var yerss=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].yers==''){
            	yerss.push('-');   
            }else{
            	yerss.push(rows[i].yers); 
            }
        }
        var remarks=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].remark==''){
            	remarks.push('-');   
            }else{
            	remarks.push(rows[i].remark); 
            }
        }
    	$.ajax({
    			url:sybp()+'/bacteriaAction_saveRecord.action?orderid='+row.id+'&monkeylist='+monkeyids+'&ypids='+ypids+
    			'&salms='+salms+'&shigs='+shigs+'&yerss='+yerss+'&remarks='+remarks,
    			type:'post',
    			dataType:'json',
    			success:function(r){
    				if(r && r.success){
    					parent.showMessager(1,'细菌检疫信息保存成功',true,5000);
    					$('#exportCheckItemsAddButton').linkbutton('disable');
    					$('#exportCheckItemsButton').linkbutton('enable');
    				}else{
    					parent.parent.showMessager(2,'细菌检疫信息保存失败',true,5000);
    				}
    			}
    		});
        }
    if(kind=='virus'){
    	var rows=$("#exportOneTable").datagrid("getRows");
    	for(var i=0;i<rows.length;i++){
			var rowIndex = $('#exportOneTable').datagrid('getRowIndex', rows[i]);
			$('#exportOneTable').datagrid('endEdit', rowIndex);
		}
		rows=$("#exportOneTable").datagrid("getRows");
        var xueqs=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].xueq==''){
            	xueqs.push('-');   
            }else{
            	xueqs.push(rows[i].xueq); 
            }
        }
        var monkeyids=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].monkeyid==''){
            	monkeyids.push(0);   
            }else{
            	monkeyids.push(rows[i].monkeyid); 
            }
        }
        var bvs=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].bv==''){
            	bvs.push('-');   
            }else{
            	bvs.push(rows[i].bv); 
            }
        }
        var stlvs=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].stlv==''){
            	stlvs.push('-');   
            }else{
            	stlvs.push(rows[i].stlv); 
            }
        }
        var srvs=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].srv==''){
            	srvs.push('-');   
            }else{
            	srvs.push(rows[i].srv); 
            }
        }
        var sivs=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].siv==''){
            	sivs.push('-');   
            }else{
            	sivs.push(rows[i].siv); 
            }
        }
        var filos=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].filo==''){
            	filos.push('-');   
            }else{
            	filos.push(rows[i].filo); 
            }
        }
        var remarks=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].remark==''){
            	remarks.push('-');   
            }else{
            	remarks.push(rows[i].remark); 
            }
        }
    	$.ajax({
    			url:sybp()+'/virusAction_saveRecord.action?orderid='+row.id+'&monkeylist='+monkeyids
    			+'&xueqs='+xueqs+'&bvs='+bvs+'&stlvs='+stlvs+'&srvs='+srvs+'&sivs='+sivs+'&filos='+filos+'&remarks='+remarks,
    			type:'post',
    			dataType:'json',
    			success:function(r){
    				if(r && r.success){
    					parent.showMessager(1,'病毒检疫信息保存成功',true,5000);
    					$('#exportCheckItemsAddButton').linkbutton('disable');
    					$('#exportCheckItemsButton').linkbutton('enable');
    				}else{
    					parent.parent.showMessager(2,'病毒检疫信息保存失败',true,5000);
    				}
    			}
    		});
        }
    if(kind=='xcg'){
    	var rows=$("#exportOneTable").datagrid("getRows");
    	for(var i=0;i<rows.length;i++){
			var rowIndex = $('#exportOneTable').datagrid('getRowIndex', rows[i]);
			$('#exportOneTable').datagrid('endEdit', rowIndex);
		}
		rows=$("#exportOneTable").datagrid("getRows");
        var bhaos=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].bhao==''){
            	bhaos.push('-');
            }else{
            	bhaos.push(rows[i].bhao); 
            }
        }
        var monkeyids=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].monkeyid==''){
            	monkeyids.push(0);   
            }else{
            	monkeyids.push(rows[i].monkeyid); 
            }
        }
        var gras=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].gra==''){
            	gras.push('-');   
            }else{
            	gras.push(rows[i].gra); 
            }
        }
        var mids=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].mid==''){
            	mids.push('-');   
            }else{
            	mids.push(rows[i].mid); 
            }
        }
        var lyms=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].lym==''){
            	lyms.push('-');   
            }else{
            	lyms.push(rows[i].lym); 
            }
        }
        var mchcs=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].mchc==''){
            	mchcs.push('-');   
            }else{
            	mchcs.push(rows[i].mchc); 
            }
        }
        var mchs=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].mch==''){
            	mchs.push('-');   
            }else{
            	mchs.push(rows[i].mch); 
            }
        }
        var mcvs=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].mcv==''){
            	mcvs.push('-');   
            }else{
            	mcvs.push(rows[i].mcv); 
            }
        }
        var plts=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].plt==''){
            	plts.push('-');   
            }else{
            	plts.push(rows[i].plt); 
            }
        }
        var hcts=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].hct==''){
            	hcts.push('-');   
            }else{
            	hcts.push(rows[i].hct); 
            }
        }
        var hgbs=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].hgb==''){
            	hgbs.push('-');   
            }else{
            	hgbs.push(rows[i].hgb); 
            }
        }
        var rbcs=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].rbc==''){
            	rbcs.push('-');   
            }else{
            	rbcs.push(rows[i].rbc); 
            }
        }
        var wbcs=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].wbc==''){
            	wbcs.push('-');   
            }else{
            	wbcs.push(rows[i].wbc); 
            }
        }
       
    	$.ajax({
    			url:sybp()+'/xcgAction_saveRecord.action?orderid='+row.id+'&monkeylist='+monkeyids+'&bhaos='+bhaos
    			+'&wbcs='+wbcs+'&rbcs='+rbcs+'&hgbs='+hgbs+'&hcts='+hcts
    			+'&plts='+plts+'&mcvs='+mcvs+'&mchs='+mchs+'&mchcs='+mchcs
    			+'&lyms='+lyms+'&mids='+mids+'&gras='+gras,
    			type:'post',
    			dataType:'json',
    			success:function(r){
    				if(r && r.success){
    					parent.showMessager(1,'血常规检疫信息保存成功',true,5000);
    					$('#exportCheckItemsAddButton').linkbutton('disable');
    					$('#exportCheckItemsButton').linkbutton('enable');
    				}else{
    					parent.parent.showMessager(2,'血常规检疫信息保存失败',true,5000);
    				}
    			}
    		});
        }
    if(kind=='xysh'){
    	var rows=$("#exportOneTable").datagrid("getRows");
    	for(var i=0;i<rows.length;i++){
			var rowIndex = $('#exportOneTable').datagrid('getRowIndex', rows[i]);
			$('#exportOneTable').datagrid('endEdit', rowIndex);
		}
		rows=$("#exportOneTable").datagrid("getRows");
        var bhaos=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].bhao==''){
            	bhaos.push('-');   
            }else{
            	bhaos.push(rows[i].bhao); 
            }
        }
        var monkeyids=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].monkeyid==''){
            	monkeyids.push(0);   
            }else{
            	monkeyids.push(rows[i].monkeyid); 
            }
        }
        var asts=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].ast==''){
            	asts.push('-');   
            }else{
            	asts.push(rows[i].ast); 
            }
        }
        var alts=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].alt==''){
            	alts.push('-');   
            }else{
            	alts.push(rows[i].alt); 
            }
        }
        var alps=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].alp==''){
            	alps.push('-');   
            }else{
            	alps.push(rows[i].alp); 
            }
        }
        var tps=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].tp==''){
            	tps.push('-');   
            }else{
            	tps.push(rows[i].tp); 
            }
        }
        var albs=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].alb==''){
            	albs.push('-');   
            }else{
            	albs.push(rows[i].alb); 
            }
        }
        var ggts=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].ggt==''){
            	ggts.push('-');   
            }else{
            	ggts.push(rows[i].ggt); 
            }
        }
        var tbils=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].tbil==''){
            	tbils.push('-');   
            }else{
            	tbils.push(rows[i].tbil); 
            }
        }
        var buns=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].bun==''){
            	buns.push('-');   
            }else{
            	buns.push(rows[i].bun); 
            }
        }
        var creas=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].crea==''){
            	creas.push('-');   
            }else{
            	creas.push(rows[i].crea); 
            }
        }
        var glus=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].glu==''){
            	glus.push('-');   
            }else{
            	glus.push(rows[i].glu); 
            }
        }
        var tgs=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].tg==''){
            	tgs.push('-');   
            }else{
            	tgs.push(rows[i].tg); 
            }
        }
        var chols=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].chol==''){
            	chols.push('-');   
            }else{
            	chols.push(rows[i].chol); 
            }
        }
        
        var ldhs=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].ldh==''){
            	ldhs.push('-');   
            }else{
            	ldhs.push(rows[i].ldh); 
            }
        }
        var cks=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].ck==''){
            	cks.push('-');   
            }else{
            	cks.push(rows[i].ck); 
            }
        }
        var nas=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].na==''){
            	nas.push('-');   
            }else{
            	nas.push(rows[i].na); 
            }
        }
        var ks=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].k==''){
            	ks.push('-');   
            }else{
            	ks.push(rows[i].k); 
            }
        }
        var cis=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].ci==''){
            	cis.push('-');   
            }else{
            	cis.push(rows[i].ci); 
            }
        }
    	$.ajax({
    			url:sybp()+'/xyshAction_saveRecord.action?orderid='+row.id+'&monkeylist='+monkeyids+'&bhaos='+bhaos
    			+'&chols='+chols+'&tgs='+tgs+'&glus='+glus+'&creas='+creas
    			+'&buns='+buns+'&tbils='+tbils+'&ggts='+ggts+'&albs='+albs
    			+'&tps='+tps+'&alps='+alps+'&alts='+alts+'&asts='+asts+'&ldhs='+ldhs+'&cks='+cks+'&nas='+nas+'&ks='+ks+'&cis='+cis,
    			type:'post',
    			dataType:'json',
    			success:function(r){
    				if(r && r.success){
    					parent.showMessager(1,'血生化检疫信息保存成功',true,5000);
    					$('#exportCheckItemsAddButton').linkbutton('disable');
    					$('#exportCheckItemsButton').linkbutton('enable');
    				}else{
    					parent.parent.showMessager(2,'血生化检疫信息保存失败',true,5000);
    				}
    			}
    		});
        }
    if(kind=='parasite'){
    	var rows=$("#exportOneTable").datagrid("getRows");
    	for(var i=0;i<rows.length;i++){
			var rowIndex = $('#exportOneTable').datagrid('getRowIndex', rows[i]);
			$('#exportOneTable').datagrid('endEdit', rowIndex);
		}
		rows=$("#exportOneTable").datagrid("getRows");
        var bhaos=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].bhao==''){
            	bhaos.push('-');   
            }else{
            	bhaos.push(rows[i].bhao);
            }
        }
        var monkeyids=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].monkeyid==''){
            	monkeyids.push(0);   
            }else{
            	monkeyids.push(rows[i].monkeyid); 
            }
        }
        var ambs=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].amb==''){
            	ambs.push('-');   
            }else{
            	ambs.push(rows[i].amb); 
            }
        }
        var rcs=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].rc==''){
            	rcs.push('-');   
            }else{
            	rcs.push(rows[i].rc); 
            }
        }
        var bmcs=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].bmc==''){
            	bmcs.push('-');   
            }else{
            	bmcs.push(rows[i].bmc); 
            }
        }
        var twjscs=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].twjsc==''){
            	twjscs.push('-');   
            }else{
            	twjscs.push(rows[i].twjsc); 
            }
        }
        var remarks=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].remark==''){
            	remarks.push('-');   
            }else{
            	remarks.push(rows[i].remark); 
            }
        }
    	$.ajax({
    			url:sybp()+'/parasiteAction_saveRecord.action?orderid='+row.id+'&monkeylist='+monkeyids+'&bhaos='+bhaos+
    			'&ambs='+ambs+'&rcs='+rcs+'&bmcs='+bmcs+'&twjscs='+twjscs+'&remarks='+remarks,
    			type:'post',
    			dataType:'json',
    			success:function(r){
    				if(r && r.success){
    					parent.showMessager(1,'寄生虫检疫信息保存成功',true,5000);
    					$('#exportCheckItemsAddButton').linkbutton('disable');
    					$('#exportCheckItemsButton').linkbutton('enable');
    				}else{
    					parent.parent.showMessager(2,'寄生虫检疫信息保存失败',true,5000);
    				}
    			}
    		});
        }
    if(kind=='tb'){
    	var rows=$("#exportOneTable").datagrid("getRows");
    	for(var i=0;i<rows.length;i++){
			var rowIndex = $('#exportOneTable').datagrid('getRowIndex', rows[i]);
			$('#exportOneTable').datagrid('endEdit', rowIndex);
		}
		rows=$("#exportOneTable").datagrid("getRows");
        var tb24s=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].tb24==''){
            	tb24s.push('-');   
            }else{
            	tb24s.push(rows[i].tb24); 
            }
        }
        var monkeyids=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].monkeyid==''){
            	monkeyids.push(0);   
            }else{
            	monkeyids.push(rows[i].monkeyid); 
            }
        }
        var tb48s=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].tb48==''){
            	tb48s.push('-');   
            }else{
            	tb48s.push(rows[i].tb48); 
            }
        }
        var tb72s=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].tb72==''){
            	tb72s.push('-');   
            }else{
            	tb72s.push(rows[i].tb72); 
            }
        }
        var remarks=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].remark==''){
            	remarks.push('-');   
            }else{
            	remarks.push(rows[i].remark); 
            }
        }
    	$.ajax({
    			url:sybp()+'/tbAction_saveRecord.action?orderid='+row.id+'&monkeylist='+monkeyids+'&tb24s='+tb24s+'&tb48s='+tb48s+'&tb72s='+tb72s+'&remarks='+remarks,
    			type:'post',
    			dataType:'json',
    			success:function(r){
    				if(r && r.success){
    					parent.showMessager(1,'TB检疫信息保存成功',true,5000);
    					$('#exportCheckItemsAddButton').linkbutton('disable');
    					$('#exportCheckItemsButton').linkbutton('enable');
    				}else{
    					parent.parent.showMessager(2,'TB检疫信息保存失败',true,5000);
    				}
    			}
    		});
        }
    if(kind=='infectious'){
    	var rows=$("#exportOneTable").datagrid("getRows");
    	for(var i=0;i<rows.length;i++){
			var rowIndex = $('#exportOneTable').datagrid('getRowIndex', rows[i]);
			
			
			//var ed = $('#exportOneTable').datagrid('getEditor', {index:rowIndex,field:'drugs_name'});
			//var id=(ed.target).combobox('getValue');
			//$(ed.target).combobox('setValue',id);
			
			$('#exportOneTable').datagrid('endEdit', rowIndex);
			//$('#drugs_name').combobox('setValue',id);
		}
    	
   		rows=$("#exportOneTable").datagrid("getRows");
        var ypins=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].ypin==''){
            	ypins.push('-');   
            }else{
            	ypins.push(rows[i].ypin); 
            }
        }
        var monkeyids=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].monkeyid==''){
            	monkeyids.push(0);   
            }else{
            	monkeyids.push(rows[i].monkeyid); 
            }
        }
        var drugs_names=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].drugs_name==''){
            	drugs_names.push('-');   
            }else{
            	drugs_names.push(rows[i].drugs_name); 
            }
        }
        var drugs_counts=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].drugs_count==''){
            	drugs_counts.push('-');   
            }else{
            	drugs_counts.push(rows[i].drugs_count); 
            }
        }
        
        var remarks=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].remark==''){
            	remarks.push('-');   
            }else{
            	remarks.push(rows[i].remark); 
            }
        }
    	$.ajax({
    			url:sybp()+'/infectiousAction_saveRecord.action?orderid='+row.id+'&monkeylist='+monkeyids+'&ypins='+ypins+
    			'&drugs_names='+drugs_names+'&drugs_counts='+drugs_counts+'&remarks='+remarks,
    			type:'post',
    			dataType:'json',
    			success:function(r){
    				if(r && r.success){
    					parent.showMessager(1,'传染病检疫信息保存成功',true,5000);
    					$('#exportCheckItemsAddButton').linkbutton('disable');
    					$('#exportCheckItemsButton').linkbutton('enable');
    				}else{
    					parent.parent.showMessager(2,'传染病检疫信息保存失败',true,5000);
    				}
    			}
    		});
        }
    
    if(kind=='x'){
    	var rows=$("#exportOneTable").datagrid("getRows");
    	for(var i=0;i<rows.length;i++){
			var rowIndex = $('#exportOneTable').datagrid('getRowIndex', rows[i]);
			
			$('#exportOneTable').datagrid('endEdit', rowIndex);
		}
    	
   		rows=$("#exportOneTable").datagrid("getRows");
       
        var monkeyids=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].monkeyid==''){
            	monkeyids.push(0);   
            }else{
            	monkeyids.push(rows[i].monkeyid); 
            }
        }
        var checkareas=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].checkarea==''){
            	checkareas.push('-');   
            }else{
            	checkareas.push(rows[i].checkarea); 
            }
        }
        
        var remarks=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].remark==''){
            	remarks.push('-');   
            }else{
            	remarks.push(rows[i].remark); 
            }
        }
    	$.ajax({
    			url:sybp()+'/xAction_saveRecord.action?orderid='+row.id+'&monkeylist='+monkeyids+
    			'&checkareas='+checkareas+'&remarks='+remarks,
    			type:'post',
    			dataType:'json',
    			success:function(r){
    				if(r && r.success){
    					parent.showMessager(1,'X光检疫信息保存成功',true,5000);
    					$('#exportCheckItemsAddButton').linkbutton('disable');
    					$('#exportCheckItemsButton').linkbutton('enable');
    				}else{
    					parent.parent.showMessager(2,'X光检疫信息保存失败',true,5000);
    				}
    			}
    		});
        }
    
    if(kind=='surface'){
    	var rows=$("#exportOneTable").datagrid("getRows");
    	for(var i=0;i<rows.length;i++){
			var rowIndex = $('#exportOneTable').datagrid('getRowIndex', rows[i]);
			
			$('#exportOneTable').datagrid('endEdit', rowIndex);
		}
    	
   		rows=$("#exportOneTable").datagrid("getRows");
       
        var monkeyids=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].monkeyid==''){
            	monkeyids.push(0);   
            }else{
            	monkeyids.push(rows[i].monkeyid); 
            }
        }
        
        var remarks=[];
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].remark==''){
            	remarks.push('-');   
            }else{
            	remarks.push(rows[i].remark); 
            }
        }
    	$.ajax({
    			url:sybp()+'/surfaceAction_saveRecord.action?orderid='+row.id+'&monkeylist='+monkeyids+
    			'&remarks='+remarks,
    			type:'post',
    			dataType:'json',
    			success:function(r){
    				if(r && r.success){
    					parent.showMessager(1,'体表检疫信息保存成功',true,5000);
    					$('#exportCheckItemsAddButton').linkbutton('disable');
    					$('#exportCheckItemsButton').linkbutton('enable');
    				}else{
    					parent.parent.showMessager(2,'体表检疫信息保存失败',true,5000);
    				}
    			}
    		});
        }
}

function myselfformatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }
   // var data=$('#drugs_name').combobox('getData');
    var data=[{"value":"1","text":"sd"},{"value":"2","text":"df"}];
    for (var i = 0; i < data.length; i++) {
        if (data[i].value == value) {
            return data[i].text;
        }
    }
}

window.drugsDatas=synchroAjaxByUrl(sybp()+'/infectiousAction_loadListInfectious.action');

function synchroAjaxByUrl(url){
	var temp;
	$.ajax({
	url:url,
	type:"get",
	async:false,
	dataType:"json",
	success:function(data){
	temp = data;
	}
	});
	return temp;

}