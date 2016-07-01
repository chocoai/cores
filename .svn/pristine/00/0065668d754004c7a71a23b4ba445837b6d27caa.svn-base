  //选择条件用户信息
	function selectAllUserDialong(){
		var taskKind = $('#taskKind').val();
		allLeaderTable=$('#allLeaderTable').datagrid({
			url : sybp()+'/tblSchedulePlanAction_selectAllUsers.action?taskKind='+taskKind,
	   	    title:'',
			height: 340,
			width:200,
			iconCls:'',//'icon-save',
			//pagination:true,//下面状态栏
			pageSize:100,
			pageList:[50,100],
			//fit:true,
			fitColumns:true,//不出现横向滚动条
			nowarp:  false,//单元格里自动换行
			//border:false,
			//idField:'id', //pk
			//sortName:'aniSerialNum',//排序字段
			//sortOrder:'desc',//排序方法
			columns :[[{
				title:'',
				field:'id',
				checkbox:true
			},{
				title:'负责人',
				field:'realName',
				width:60
			}]],
			onClickRow :function(rowIndex, rowData){
			},
			onLoadSuccess:function(data){
			}
		}); //end 
	}

    //动物房资源
	function AnimalHouseRes (){
		var taskKind = $('#taskKind').val();
    	if(taskKind == 2){
    		$('#animalHouseTableTd').css('visibility',''); 
    	AnimalHouseTable= $('#AnimalHouseTable').treegrid({ 
			url : sybp()+'/tblAnimalHouseAction_loadList2.action',
			idField:'id',    
		    treeField:'resName', 
		    height: 340,
			width:200,
		    animate:false,   
		    singleSelect: false, //支持多选
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
			        
			       },
			       onSelect:function(row){
			    	   var rows = $(this).treegrid('getChildren',row.id);
				    	 for(var i = 0;i<rows.length;i++){
				    		 $(this).treegrid('select',rows[i].id);
				    	 }
				   },
				   onUnselect:function(row){
			    	   var rows = $(this).treegrid('getChildren',row.id);
				    	 for(var i = 0;i<rows.length;i++){
				    		 $(this).treegrid('unselect',rows[i].id);
				    	 }
				   }
		 });
    	}else{
	    		$('#animalHouseTableTd').css('visibility','hidden'); 
	    	}
    }

    //专题编号
	function selectStudyNoTable(){
		var startime= $('#showStartime').datebox('getValue');
		var endtime= $('#showEndtime').datebox('getValue');
		var taskKind = $('#taskKind').val();
		if(endtime == ""){
			endtime = startime; 
		}
    	studyNoTable=$('#studyNoTable').datagrid({
			url : sybp()+'/tblSchedulePlanAction_selectStudyNoTable.action?startTime='+startime+'&endTime='+endtime+'&taskKind1='+taskKind,
	   	    title:'',
			height: 340,
			width:200,
			iconCls:'',//'icon-save',
			//pagination:true,//下面状态栏
			pageSize:100,
			pageList:[50,100],
			//fit:true,
			fitColumns:true,//不出现横向滚动条
			nowarp:  false,//单元格里自动换行
			//border:false,
			//idField:'id', //pk
			//sortName:'aniSerialNum',//排序字段
			//sortOrder:'desc',//排序方法
			columns :[[{
				title:'',
				field:'id',
				checkbox:true
			},{
				title:'专题编号',
				field:'studyNo',
				width:60
			}]],
			onClickRow :function(rowIndex, rowData){
			    
			},
			onLoadSuccess:function(data){
				 
			}
		}); //end 
    }

    //任务名称
	function selecttaskNameTable(){
		var taskKind = $('#taskKind').val();
    		taskNameTable=$('#taskNameTable').datagrid({
    			url : sybp()+'/tblSchedulePlanAction_selectTaskNameTable.action?taskKind1='+taskKind,
    	   	    title:'',
    			height:340,
    			width:200,
    			iconCls:'',//'icon-save',
    			//pagination:true,//下面状态栏
    			pageSize:100,
    			pageList:[50,100],
    			//fit:true,
    			fitColumns:true,//不出现横向滚动条
    			nowarp:  false,//单元格里自动换行
    			//border:false,
    			//idField:'id', //pk
    			//sortName:'aniSerialNum',//排序字段
    			//sortOrder:'desc',//排序方法
    			columns :[[{
    				title:'',
    				field:'id',
    				checkbox:true
    			},{
    				title:'任务名称',
    				field:'taskName',
    				width:60
    			}]],
    			onClickRow :function(rowIndex, rowData){
    			    
    			},
    			onLoadSuccess:function(data){
    				 
    			}
    		}); //end 
    	
    	
    }

    //选择条件窗口
	function selectConditionsButton(){
	 $('#selectConditionsDialog').dialog('open');
	 $('#selectConditionsDialog2').dialog('open');
    }


	function afterSureTheSelect(){
		
		$('#progressbar').css('display',''); 
		var taskKind = $('#taskKind').val();
		makeSureTheSelect(taskKind);
		var description = $('#description').val();
	
		var studyNoRow = $('#studyNoTable').datagrid('getSelections');
		var taskNameRow = $('#taskNameTable').datagrid('getSelections');
		var leaderRow =$('#allLeaderTable').datagrid('getSelections');
		var houseRow;
		if( taskKind== 2){
			houseRow = $('#AnimalHouseTable').treegrid('getSelections');
		}
        //开始结束日期
   	    var startTime =   $('#showStartime').datebox('getValue');
	    var endTime =  $('#showEndtime').datebox('getValue');
        //空白日期
	    var checked;
	    if(document.getElementById("checkbox1").checked){
			checked = true;
		}else{
			checked = false;
		}
        //专题编号
	    var ary1 = new Array();
	    for(var j=0;j<studyNoRow.length;j++){
	    	var studyNo = studyNoRow[j].studyNo;
			ary1 = ary1.concat(studyNo);
	     }
		 var studyNos = ary1.join(",");

		 //人员选择
		  var ary2 = new Array();
		  for(var j=0;j<leaderRow.length;j++){
			  var name = leaderRow[j].realName;
			  var id = leaderRow[j].id;
			  ary2 = ary2.concat(name);
		  }
		  var leaders = ary2.join(",");

          //任务名称
          var ary3 = new Array();
          for(var j = 0;j<taskNameRow.length;j++){
              var taskName = taskNameRow[j].taskName;
              ary3 = ary3.concat(taskName);
          }
          var taskNames = ary3.join(",");
          var houseNames;
          if( taskKind== 2){
	          //动物房houseRow
	  		  var ary4 = new Array();
	  		 for(var j = 0;j<houseRow.length;j++){
	             var houseName = houseRow[j].id;
	             ary4 = ary4.concat(houseName);
	         }
	  		houseNames = ary4.join(",");	
          }else{
        	  houseNames = null;
          }
          
         var fullScreen = $('#fullScreen').val();
         var tableHeight;
         var tableWidth;
         if(fullScreen == "all"){
	  	    	tableHeight =window.screen.height-168;
	  		    tableWidth  =window.screen.width-29;	
	  	 }else{
	  	    	tableHeight = document.body.clientHeight - 146;
	 		    tableWidth  = document.body.clientWidth - 24;
	  	 }
	    
        //当开始结束时间不等的时候
	    if(!document.getElementById("checkboxOneDay").checked ){
	    	$('#selectConditions').val("more");
	    	schedulePlanTable=$('#schedulePlanTable').datagrid({
		    	height:tableHeight,
				width:tableWidth,
				fit:false,
				fitColumns:false,
				resizable:true,
				frozenColumns:[[
				                 {field:'dateCol',title:'日期',width:100,rowspan:2,halign:'center'}
				             ]],
				onClickRow :function(rowIndex, rowData){
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow',rowIndex);
			    }
	        });
		    schedulePlanTable.datagrid({
				columns:[]
			});
		    schedulePlanTable.datagrid('loadData',{total:0,rows:[]});
	        $('#schedulePlanTableDiv').css('display',''); 
	        $('#oneDaySchedulePlanTableDiv').css('display','none');  
	    	$.ajax({
					url : sybp()+'/tblSchedulePlanAction_getSchedulePlandatagrid.action',
					type: 'post',
					data: {
				         startTime:startTime,
		                 endTime:endTime,
		                 description:description,
		                 allDate:checked,
		                 studyNos:studyNos,
		                 taskKind:taskKind,
		                 leaders:leaders,
		                 taskNames:taskNames,
		                 houseNames:houseNames
					},
					dataType:'json',
					success:function(r){
						if(r){
							schedulePlanTable.datagrid({
								columns:r.columns
							});
							schedulePlanTable.datagrid('loadData',r.rows);
						}
					},
					beforeSend:function(){ 
				        //$('#progressbar').css('display',''); 
			            var value = $('#p').progressbar('getValue');
			            if(value == 100){
			            	value = 10;
			            }
						if (value < 100){
							value += Math.floor(Math.random() * 10);
							$('#p').progressbar('setValue', value);
							setTimeout(arguments.callee, 200);
						}
	            }, 
	            complete:function(){ 
	               $('#progressbar').css('display',''); 
	               $('#p').progressbar('setValue', 100);
	               $('#progressbar').css('display','none'); 
	            } 
	         });
	    }else{
	    	endTime = startTime ;
			$('#selectConditions').val("one");
	    	oneDaySchedulePlanTable=$('#oneDaySchedulePlanTable').datagrid({
	    		height:tableHeight,
				width:tableWidth,
				fit:false,
				fitColumns:false,
				resizable:true,
				frozenColumns:[[
				                 {field:'taskCode',title:'专题编号',width:100,rowspan:0,halign:'center'}
				             ]],
				onClickRow :function(rowIndex, rowData){
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow',rowIndex);
			    }
	        });
	        $('#schedulePlanTableDiv').css('display','none'); 
	        $('#oneDaySchedulePlanTableDiv').css('display','');  
	        oneDaySchedulePlanTable.datagrid({
				columns:[]
			});
	        oneDaySchedulePlanTable.datagrid('loadData',{total:0,rows:[]});
			 $.ajax({
					url : sybp()+'/tblSchedulePlanAction_getOneDateSchedulePlandatagrid.action',
					type: 'post',
					data: {
						 startTime:startTime,
		                 endTime:endTime,
		                 description:description,
		                 allDate:checked,
		                 studyNos:studyNos,
		                 taskKind:taskKind,
		                 leaders:leaders,
		                 taskNames:taskNames,
		                 houseNames:houseNames
					},
					dataType:'json',
					success:function(r){
						if(r){
							oneDaySchedulePlanTable.datagrid({
								columns:r.columns
							});
							oneDaySchedulePlanTable.datagrid('loadData',r.rows);
							// $('#description').val("查询日期："+oneDayTime +" 日程信息");
						}
					},
					beforeSend:function(){ 
				        //$('#progressbar').css('display',''); 
			            var value = $('#p').progressbar('getValue');
			            if(value == 100){
			            	value = 10;
			            }
						if (value < 100){
							value += Math.floor(Math.random() * 10);
							$('#p').progressbar('setValue', value);
							setTimeout(arguments.callee, 200);
						}
	            }, 
	            complete:function(){ 
	               $('#progressbar').css('display',''); 
	               $('#p').progressbar('setValue', 100);
	               $('#progressbar').css('display','none'); 
	            } 
	         });
	    	
	    }

		 
		 
		 $('#selectConditionsDialog').dialog('close');
		 $('#selectConditionsDialog2').dialog('close');
	   
	}
    //组合字符串
	function makeSureTheSelect(taskKind){
		var leaderRow =$('#allLeaderTable').datagrid('getSelections');
		var studyNoRow = $('#studyNoTable').datagrid('getSelections');
		var taskNameRow = $('#taskNameTable').datagrid('getSelections');
		if(taskKind == 2){
			 var houseRow = $('#AnimalHouseTable').treegrid('getSelections');
		}
   	   
   	    var startTime =   $('#showStartime').datebox('getValue');
	    var endTime =  $('#showEndtime').datebox('getValue');
	    var date = "开始日期："+startTime+",结束日期："+endTime+"。";
        var leader = "人员：";
	    for(var i = 0;i<leaderRow.length;i++){
	    	name = leaderRow[i].realName;
            if(leader == "人员："){
               leader = leader +name; 	
            }else{
               leader = leader +","+name;
            }
		}
		if(leader == "人员："){
           leader = "所有人员";
		}
        var studyNos = "。专题编号：";
        for(var i = 0;i < studyNoRow.length;i++){
        	var studyNo = studyNoRow[i].studyNo;
        	if( studyNos == "。专题编号："){
        		studyNos = studyNos + studyNo;
            }else{
            	studyNos = studyNos +","+ studyNo;
            }
        }
        if( studyNos == "。专题编号："){
    		studyNos = "。所有专题编号";
        }
       var taskNames = "。任务名称：";
        for(var i = 0;i < taskNameRow.length;i++){
            var taskName = taskNameRow[i].taskName;
            if(taskNames == "。任务名称："){
            	taskNames = taskNames +taskName;
            }else{
            	taskNames = taskNames + ","+taskName;
            }
         }
		 if(taskNames == "。任务名称："){
			 taskNames = "。全部任务名称"
	     }
		 var houses;
		 if(taskKind == 2){
		  houses = "。资源：";
		     for(var i = 0;i<houseRow.length;i++){
	            var house = houseRow[i].resName;
	            if(houses == "。资源："){
	            	houses = houses + house;
	            }else{
	            	houses = houses +","+house;
	            }
			 }   
		 }else{
			 houses = "";
		 }
		 if( houses == "。资源："){
			 houses = "。资源：所有房间"
		 }
		houses = houses +"。";
		$('#description').val(date+leader+studyNos+taskNames+houses);
    }
   
	function  onCheckbox1(){
		if(document.getElementById("checkbox1").checked){
			document.getElementById("checkbox1").checked = false;
		}else{
			document.getElementById("checkbox1").checked = true;
		}
    }


	function appendZero(s){
		return ("00"+ s).substr((s+"").length);
	}  //补0函数
	
	
	function selectOneConditionButton(){
	   var d = new Date();
	   var oneDayTime = d.getFullYear() + "-" + appendZero(d.getMonth() + 1) + "-" + appendZero(d.getDate());
	   $('#oneDayTime').datebox('setValue', oneDayTime);
       $('#selectOneConditionDialog').dialog('open');
	   $('#selectOneConditionDialog2').dialog('open');
	}
    //查询单日日程
	function sureOneSelect(){
		$('#selectConditions').val("one");
		$('#progressbar').css('display',''); 
		var taskKind = $('#taskKind').val();
		var oneDayTime = $('#oneDayTime').datebox('getValue');
		//$('#description').val("日程日期："+oneDayTime+"。");
        var description = "日程日期："+oneDayTime+"。";
        var fullScreen = $('#fullScreen').val();
        var tableHeight;
        var tableWidth;
	    if(fullScreen == "all"){
	    	tableHeight =window.screen.height-168;
 		    tableWidth  =window.screen.width-29;	
	    }else{
	    	tableHeight = document.body.clientHeight - 146;
	    	 tableWidth  = document.body.clientWidth - 24;
	    }
 	    
 	    
        oneDaySchedulePlanTable=$('#oneDaySchedulePlanTable').datagrid({
        	height:tableHeight,
			width:tableWidth,
			fit:false,
			fitColumns:false,
			resizable:true,
			frozenColumns:[[
			                 {field:'taskCode',title:'专题编号',width:100,rowspan:0,halign:'center'}
			             ]],
			onClickRow :function(rowIndex, rowData){
			$(this).datagrid('unselectAll');
			$(this).datagrid('selectRow',rowIndex);
		    }
        });
        $('#schedulePlanTableDiv').css('display','none'); 
        $('#oneDaySchedulePlanTableDiv').css('display','');  
        oneDaySchedulePlanTable.datagrid({
			columns:[]
		});
        oneDaySchedulePlanTable.datagrid('loadData',{total:0,rows:[]});
		 $.ajax({
				url : sybp()+'/tblSchedulePlanAction_getOneDateSchedulePlandatagrid.action',
				type: 'post',
				data: {
			         startTime:oneDayTime,
			         taskKind:taskKind,
	                 description:description
				},
				dataType:'json',
				success:function(r){
					if(r){
						oneDaySchedulePlanTable.datagrid({
							columns:r.columns
						});
						oneDaySchedulePlanTable.datagrid('loadData',r.rows);
						 $('#description').val("查询日期："+oneDayTime +" 日程信息");
					}
				},
				beforeSend:function(){ 
			        //$('#progressbar').css('display',''); 
		            var value = $('#p').progressbar('getValue');
		            if(value == 100){
		            	value = 10;
		            }
					if (value < 100){
						value += Math.floor(Math.random() * 10);
						$('#p').progressbar('setValue', value);
						setTimeout(arguments.callee, 200);
					}
            }, 
            complete:function(){ 
               $('#progressbar').css('display',''); 
               $('#p').progressbar('setValue', 100);
               $('#progressbar').css('display','none'); 
            } 
         });
		 $('#selectOneConditionDialog').dialog('close');
		 $('#selectOneConditionDialog2').dialog('close');
		
    }

	//任务完成登记(弹出签字窗口)
	function taskFinishRegisterButton(){
		//签名
		//fuheqm_showQianmingDialog('eventAfterQingming');
		select_showQianmingDialog('eventAfterQingming');
		document.getElementById('thisSelectUserName').readOnly=false;
	}
	//任务完成登记（签字完成后事件）
	function eventAfterQingming(){
		var d = new Date();
		var nowDayTime = d.getFullYear() + "-" + appendZero(d.getMonth() + 1) + "-" + appendZero(d.getDate());
		$('#registerTime').datebox('setValue', nowDayTime);
		$('#leaderName').html('操作者：'+ $('#realName11').combotree('getText')+"&nbsp;&nbsp;");
		$('#currentUserCode').val($('#thisSelectUserName').val());
		
		selectRegisterFinishTable();
		$('#registerFinishDialog').dialog('open');
		$('#registerFinishDialog2').dialog('open');
		 
	}
	//任务完成登记（上的查询）
	function selectRegisterFinishTable(){
		$('#selectedDate').val($('#registerTime').datebox('getValue'));
    	registerFinishTable=$('#registerFinishTable').datagrid({
			url : sybp()+'/tblTaskStateAction_loadList.action?selectedDate='+$('#selectedDate').val()
						+"&currentUserCode="+$('#currentUserCode').val(),
	   	    title:'',
			height: 200,
			width:300,
			iconCls:'',//'icon-save',
			//pagination:true,//下面状态栏
			pageSize:100,
			pageList:[50,100],
			//fit:true,
			fitColumns:true,//不出现横向滚动条
			nowarp:  false,//单元格里自动换行
			//border:false,
			//idField:'id', //pk
			//sortName:'aniSerialNum',//排序字段
			//sortOrder:'desc',//排序方法
			columns :[[{
							title:'选择',
							field:'scheduleId',
							checkbox:true,
							width:40
						},{
							title:'任务编号',
							field:'taskCode',
							width:120
						},{
							title:'任务名称',
							field:'taskName',
							width:100
						},{
							title:'是否完成',
							field:'isFinish',
							width:60,
							hidden:true
						}]],
			onUncheck :function(rowIndex, rowData){
			    if(rowData.isFinish == 1){
			    	$('#registerFinishTable').datagrid('selectRow',rowIndex);
				}
			},
			onLoadSuccess:function(data){
					var rows = data.rows;
					for(var i= 0;i<rows.length;i++){
						if(rows[i].isFinish == 1){
							var index = $('#registerFinishTable').datagrid('getRowIndex',rows[i]);
							$('#registerFinishTable').datagrid('selectRow',index);
						}
					}
			},
			onUncheckAll:function(rows){
				for(var i= 0;i<rows.length;i++){
					if(rows[i].isFinish == 1){
						var index = $('#registerFinishTable').datagrid('getRowIndex',rows[i]);
						$('#registerFinishTable').datagrid('selectRow',index);
					}
				}
			},
			rowStyler: function(index,row){
			    if(row.isFinish == 1 ){
					return 'background-color:#F2F2F2;color:#080808;';
				}	
			}
		}); //end 
    }
	//任务完成登记（上的登记）
	function registerButton(){
		var selectedRows = $('#registerFinishTable').datagrid('getSelections');

		var scheduleIds = new Array();

		for(var i =0;i<selectedRows.length;i++){
			if(selectedRows[i].isFinish == 0){
				var scheduleId = selectedRows[i].scheduleId;
				scheduleIds = scheduleIds.concat(scheduleId);
			}
		}
		var scheduleIdsStr = scheduleIds.join(",");
		var selectedDate = $('#selectedDate').val();
		var currentUserCode = $('#currentUserCode').val();
		if(scheduleIds.length > 0){
			//签名
			fuheqm_showQianmingDialog('eventAfterQingming2');
			$("#thisUserName").val($('#currentUserCode').val());
			document.getElementById('thisUserName').readOnly=true;
			$('#TheFhPassword').focus();
		}else{
			$.messager.alert('提示','请先选择待登记任务');  
		}
	}
	//任务完成登记 （签字后提交数据）
	function eventAfterQingming2(){

		
		var selectedRows = $('#registerFinishTable').datagrid('getSelections');
		var scheduleIds = new Array();

		for(var i =0;i<selectedRows.length;i++){
			if(selectedRows[i].isFinish == 0){
				var scheduleId = selectedRows[i].scheduleId;
				scheduleIds = scheduleIds.concat(scheduleId);
			}
		}
		var scheduleIdsStr = scheduleIds.join(",");
		var selectedDate = $('#selectedDate').val();
		var currentUserCode = $('#currentUserCode').val();
		
		if(scheduleIds.length > 0){
			$.ajax({
				url :sybp()+'/tblTaskStateAction_save.action',
				type:'post',
				data:{
						scheduleIdsStr:scheduleIdsStr,
						selectedDate:selectedDate,
						currentUserCode:currentUserCode
					},
				dataType:'json',
				success:function(r){
						if(r && r.success){
							//重新加载数据
							 $('#registerFinishDialog').dialog('close');
							 var select =  $('#selectConditions').val();
						      if(select == "one"){
						    	  sureOneSelect();
						      }else{
						    	  afterSureTheSelect();
						      }
						    
						}else{
							$.messager.alert('提示','数据传输出错，请刷新页面'); 
						}
				}
				 
			});
		}else{
			$.messager.alert('提示','请先选择待登记任务');  
		}
		
	}