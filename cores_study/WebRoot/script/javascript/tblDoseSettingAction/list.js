

var tableHeight;	//datagrid 高度
var member;//成员
$(function(){
	tableHeight = document.body.clientHeight - 30;
	member = $('#left_member').val();
	$('#layoutMainDiv').css('display','');
	initDoseSettingDatagrid();
	initApplyReviseVer();
	updateWeb();
});

function initApplyReviseVer(){
	var studyNoPara = encodeURIComponent($('#studyNoPara').val());
	$('#applyReviseVer').combogrid({
		 url:sybp()+'/tblDoseSettingAction_loadVerListByStudyNo.action?studyNoPara='+studyNoPara,
		 showHeader:false,
		 idField: 'id',
		 textField: 'text',
		 columns: [[
		        {field:'id',title:'id',hidden:true},
		        {field:'text',title:'',width:150},
		        {field:'doseEffectiveDate',title:'',hidden:true}
		 ]],
		 onSelect: function(rowIndex, rec){
			$('#doseSettingTable').datagrid('unselectAll');
			if(rec.id=='9999')//现版本
			{
				$('#doseSettingTable').datagrid({
					 url:sybp()+'/tblDoseSettingAction_loadList.action?studyNoPara='+studyNoPara,
				});
			}else{
				$('#doseSettingTable').datagrid({
					 url:sybp()+'/tblDoseSettingAction_loadListByStudyNoAndVer.action?studyNoPara='+studyNoPara+'&version='+rec.id,
				});
			}
			
			if(rec.doseEffectiveDate!=null&&rec.doseEffectiveDate!='')//没有日期的就不可以修改
			{
				$('#changeEffectiveDateButton').linkbutton('enable');
				$('#effectiveDateLabel').html("生效日期："+rec.doseEffectiveDate);
			}else{
				$('#changeEffectiveDateButton').linkbutton('disable');
				$('#effectiveDateLabel').html("");
			}
			
	        
	    }
			 
	});
}

/**
 *打开修改日期对话框 
 */
function changeApplyReviseEffectionDate(){
	
	var row = $('#applyReviseVer').combogrid('grid').datagrid('getSelected');
	
	document.getElementById("changeEffectiveDateDialog2").style.display="block";
	$('#doseSettingEffectiveDate2').datebox('setValue',row.doseEffectiveDate);
	$('#changeEffectiveDateDialog').dialog('open');
	
}

/**
 * 修改生效日期
 */
function onChangeDoseEffectiveButton(){
	
	var row = $('#applyReviseVer').combogrid('grid').datagrid('getSelected');
	var newDate = $('#doseSettingEffectiveDate2').datebox('getValue');
	if(newDate!=row.doseEffectiveDate)
	{
		qm_showQianmingDialog('afterSignChangeEffectiveDate');
		
	}else{
		$.messager.alert('提示框','新的生效日期和原先的相同！');
	}
	
}
function afterSignChangeEffectiveDate(){
	var row = $('#applyReviseVer').combogrid('grid').datagrid('getSelected');
	var newDate = $('#doseSettingEffectiveDate2').datebox('getValue');
	var studyNoPara = encodeURIComponent($('#studyNoPara').val());
	$.ajax({
		url:sybp()+'/tblDoseSettingAction_changeDoseEffectiveDateSign.action?studyNoPara='+studyNoPara+'&version='+row.id,
		type:'post',
		dataType:'json',
		data:{
			newEffectiveDate:newDate,
		},
		success:function(r){
			if(r&&r.success)
			{
				parent.parent.showMessager(1,r.msg,true,5000);
				$('#changeEffectiveDateDialog').dialog('close');
				$('#effectiveDateLabel').html("生效日期："+newDate);
				var index = $('#applyReviseVer').combogrid('grid').datagrid('getRowIndex',row);
				$('#applyReviseVer').combogrid('grid').datagrid('updateRow',{
					index: index,
					row: {
						doseEffectiveDate: newDate,
					}
				});
			}else{
				$.messager.alert('提示框',r.msg);
			}
			
		}
	});
		
	
}

/**
 * 初始化datagrid
 */
function initDoseSettingDatagrid(){
	
	var studyNoPara = encodeURIComponent($('#studyNoPara').val());
	//表格初始化
	$('#doseSettingTable').datagrid({    
		height:tableHeight,
		nowarp:  false,//单元格里自动换行
		fitColumns:false,
		singleSelect:true,
		idField:'id',
		onSelect:function(rowIndex, rowData){
			$('#editbutton').linkbutton('disable');
			$('#delbutton').linkbutton('disable');
			
			$('#upToolbarButton').linkbutton('disable');
			$('#downToolbarButton').linkbutton('disable');
			
			
			$.ajax({
				url:sybp()+'/tblDoseSettingAction_loadDoseGroupinfo.action?studyNoPara='+encodeURIComponent(rowData.studyNo),
				type:'post',
				data:{
					version:$('#applyReviseVer').combogrid('getValue'),
				},
				dataType:'json',
				success:function(r){
	    			if(r && r.success == 'true'){
	    				if(r.history == 'true')
	    				{
	    					
	    				}else{
		    				if(r.doseSettingFlag == 0){
		    					$('#editbutton').linkbutton('enable');
		    					$('#delbutton').linkbutton('enable');
		    					
		    					$('#upToolbarButton').linkbutton('enable');
		    					$('#downToolbarButton').linkbutton('enable');
		    					if(rowIndex == 0){
		    						$('#upToolbarButton').linkbutton('disable');
		    					}
		    					var rows = $('#doseSettingTable').datagrid('getRows');
		    					if(rowIndex == (rows.length-1)){
		    						$('#downToolbarButton').linkbutton('disable');
		    					}
		    				}
		    				if(r.state == "0" && r.doseSettingFlag == 1){
		    					$('#editbutton').linkbutton('enable');
		    				}
		    				if(r.state == "3" && r.doseSettingFlag == 1){
		    					$('#editbutton').linkbutton('enable');
		    				}
	    				}
	    			}
	    			if(member != ""){
	    				$('#basicbutton').linkbutton('disable');  
	    				$('#addbutton').linkbutton('disable');  
	    				$('#okbutton').linkbutton('disable');
	    				$('#editbutton').linkbutton('disable');
	    				$('#delbutton').linkbutton('disable');
	    				
	    				$('#upToolbarButton').linkbutton('disable');
	    				$('#downToolbarButton').linkbutton('disable');
	    			}
				}
			});
			
		},
		toolbar:'#toolbar',
		onLoadSuccess:function(data){
			var selectedId = $('#selectedId').val();
			if(selectedId){
				$('#doseSettingTable').datagrid('selectRecord',selectedId);
				$('#selectedId').val('');
			}
		}
   	});
}


/**
 * 基本信息设置
 * @return
 */
function onDoseSetting(){
	showDoseSettingDialog(updateWeb);
}
/**
 * 刷新页面
 * @return
 */
function updateWeb(){
	
	var studyNoPara = encodeURIComponent($('#studyNoPara').val());
	$.ajax({
		url:sybp()+'/tblDoseSettingAction_loadDoseGroupinfo.action?studyNoPara='+studyNoPara,
		type:'post',
		data:{
			version:$('#applyReviseVer').combogrid('getValue'),
		},
		dataType:'json',
		success:function(r){
			if(r && r.success == 'true'){
				//设置   动物编号设置按钮
				if(r.animalType && r.animalCodeMode == 9){
					$('#codebuttonSpan').css('display','');
					if(r.doseSettingFlag == 0){
						$('#codebutton').linkbutton('enable');  
					}else{
						$('#codebutton').linkbutton('disable');  
					}
				}else{
					$('#codebuttonSpan').css('display','none');
				}
				if(r.doseSettingFlag == 0){
					//剂量单位
					if(r.animalType){
						//有动物
						if(r.isNoGender == 0){
							$('#addbutton').linkbutton('disable');  
							$('#okbutton').linkbutton('disable');  
						}else if(r.isNoGender == 1){
							//分雄雌
							if(r.isIndentical == 1){
								//雌雄剂量相同
								$('#doseSettingTable').datagrid({    
									url:sybp()+"/tblDoseSettingAction_loadList.action?studyNoPara="+studyNoPara,
							   	    columns :[[{
										title:'',
										field:'id',
										width:10,
										halign:'center',
										align:'center',
										hidden:true
									},{
										title:'专题编号',
										field:'studyNo',
										width:10,
										halign:'center',
										align:'center',
										hidden:true
									},{
										title:'剂量组',
										field:'dosageNum',
										width:50,
										halign:'center',
										align:'center'
									},{
										title:'剂量组说明',
										field:'dosageDesc',
										width:90,
										halign:'center',
										align:'center',
									},{
										title:'剂量('+r.dosageUnit+')',
										field:'dosage',
										width:100,
										halign:'center',
										align:'center'
									},{
										title:'雌性剂量',
										field:'femaleDosage',
										width:100,
										halign:'center',
										align:'center',
										hidden:true
									},{
										title:'雄性动物数量',
										field:'maleNum',
										width:90,
										halign:'center',
										align:'center',
										hidden:false
									},{
										title:'雌性动物数量',
										field:'femaleNum',
										width:90,
										halign:'center',
										align:'center',
										hidden:false
									},{
										title:'给药容积('+r.volumeUnit+')',
										field:'maleVolume',
										width:150,
										halign:'center',
										align:'center',
										hidden:true
									},{
										title:'雌性动物给药容积('+r.volumeUnit+')',
										field:'femaleVolume',
										width:150,
										halign:'center',
										align:'center',
										hidden:true
									},{
										title:'给药浓度('+r.thicknessUnit+')',
										field:'maleThickness',
										width:150,
										halign:'center',
										align:'center',
										hidden:true
									},{
										title:'雌性动物给药浓度('+r.thicknessUnit+')',
										field:'femaleThickness',
										width:150,
										halign:'center',
										align:'center',
										hidden:true
									}]]
							   	});
									
								//给药容积
								if(r.volumeUnit){
									$('#doseSettingTable').datagrid('showColumn','maleVolume');
								}
	    						if(r.thicknessUnit){
	    							$('#doseSettingTable').datagrid('showColumn','maleThickness');
	    						}
							}else{
								//不相同
								$('#doseSettingTable').datagrid({    
									url:sybp()+"/tblDoseSettingAction_loadList.action?studyNoPara="+studyNoPara,
							   	    columns :[[{
										title:'',
										field:'id',
										width:10,
										halign:'center',
										align:'center',
										hidden:true
									},{
										title:'专题编号',
										field:'studyNo',
										width:10,
										halign:'center',
										align:'center',
										hidden:true
									},{
										title:'剂量组',
										field:'dosageNum',
										width:50,
										halign:'center',
										align:'center'
									},{
										title:'剂量组说明',
										field:'dosageDesc',
										width:90,
										halign:'center',
										align:'center',
									},{
										title:'雄性剂量('+r.dosageUnit+')',
										field:'dosage',
										width:100,
										halign:'center',
										align:'center'
									},{
										title:'雌性剂量('+r.dosageUnit+')',
										field:'femaleDosage',
										width:100,
										halign:'center',
										align:'center',
										hidden:false
									},{
										title:'雄性动物数量',
										field:'maleNum',
										width:90,
										halign:'center',
										align:'center',
										hidden:false
									},{
										title:'雌性动物数量',
										field:'femaleNum',
										width:90,
										halign:'center',
										align:'center',
										hidden:false
									},{
										title:'雄性动物给药容积('+r.volumeUnit+')',
										field:'maleVolume',
										width:150,
										halign:'center',
										align:'center',
										hidden:true
									},{
										title:'雌性动物给药容积('+r.volumeUnit+')',
										field:'femaleVolume',
										width:145,
										halign:'center',
										align:'center',
										hidden:true
									},{
										title:'雄性动物给药浓度('+r.thicknessUnit+')',
										field:'maleThickness',
										width:145,
										halign:'center',
										align:'center',
										hidden:true
									},{
										title:'雌性动物给药浓度('+r.thicknessUnit+')',
										field:'femaleThickness',
										width:150,
										halign:'center',
										align:'center',
										hidden:true
									}]]
							   	});
									
								//给药容积
								if(r.volumeUnit){
									$('#doseSettingTable').datagrid('showColumn','maleVolume');
									$('#doseSettingTable').datagrid('showColumn','femaleVolume');
								}
	    						if(r.thicknessUnit){
	    							$('#doseSettingTable').datagrid('showColumn','maleThickness');
	    							$('#doseSettingTable').datagrid('showColumn','femaleThickness');
	    						}
							}
							
							$('#addbutton').linkbutton('enable');  
							$('#okbutton').linkbutton('enable');
						}else{
							//不分雄雌
							
							$('#doseSettingTable').datagrid({    
								url:sybp()+"/tblDoseSettingAction_loadList.action?studyNoPara="+studyNoPara,
						   	    columns :[[{
									title:'',
									field:'id',
									width:10,
									halign:'center',
									align:'center',
									hidden:true
								},{
									title:'专题编号',
									field:'studyNo',
									width:10,
									halign:'center',
									align:'center',
									hidden:true
								},{
									title:'剂量组',
									field:'dosageNum',
									width:50,
									halign:'center',
									align:'center'
								},{
									title:'剂量组说明',
									field:'dosageDesc',
									width:90,
									halign:'center',
									align:'center',
								},{
									title:'剂量('+r.dosageUnit+')',
									field:'dosage',
									width:100,
									halign:'center',
									align:'center'
								},{
									title:'雌性剂量',
									field:'femaleDosage',
									width:100,
									halign:'center',
									align:'center',
									hidden:true
								},{
									title:'动物数量',
									field:'maleNum',
									width:90,
									halign:'center',
									align:'center',
									hidden:false
								},{
									title:'雌性动物数量',
									field:'femaleNum',
									width:90,
									halign:'center',
									align:'center',
									hidden:true
								},{
									title:'给药容积('+r.volumeUnit+')',
									field:'maleVolume',
									width:150,
									halign:'center',
									align:'center',
									hidden:true
								},{
									title:'雌性动物给药容积('+r.volumeUnit+')',
									field:'femaleVolume',
									width:150,
									halign:'center',
									align:'center',
									hidden:true
								},{
									title:'给药浓度('+r.thicknessUnit+')',
									field:'maleThickness',
									width:150,
									halign:'center',
									align:'center',
									hidden:true
								},{
									title:'雌性动物给药浓度('+r.thicknessUnit+')',
									field:'femaleThickness',
									width:150,
									halign:'center',
									align:'center',
									hidden:true
								}]]
						   	});
								
								//给药容积
								if(r.volumeUnit){
									$('#doseSettingTable').datagrid('showColumn','maleVolume');
								}
	    						if(r.thicknessUnit){
	    							$('#doseSettingTable').datagrid('showColumn','maleThickness');
	    						}
	    						$('#addbutton').linkbutton('enable');  
								$('#okbutton').linkbutton('enable');
						}
						
						//有动物,三按钮可见可用
						$('#basicbutton').linkbutton('enable');  
						$('#basicbutton').css('display','');  
					}else{
						//无动物
						$('#doseSettingTable').datagrid({    
							url:sybp()+"/tblDoseSettingAction_loadList.action?studyNoPara="+studyNoPara,
					   	    columns :[[{
								title:'',
								field:'id',
								width:10,
								halign:'center',
								align:'center',
								hidden:true
							},{
								title:'专题编号',
								field:'studyNo',
								width:10,
								halign:'center',
								align:'center',
								hidden:true
							},{
								title:'剂量组',
								field:'dosageNum',
								width:50,
								halign:'center',
								align:'center'
							},{
								title:'剂量组说明',
								field:'dosageDesc',
								width:90,
								halign:'center',
								align:'center',
							},{
								title:'剂量('+r.dosageUnit+')',
								field:'dosage',
								width:100,
								halign:'center',
								align:'center'
							},{
								title:'雌性剂量',
								field:'femaleDosage',
								width:100,
								halign:'center',
								align:'center',
								hidden:true
							},{
								title:'雄性动物数量',
								field:'maleNum',
								width:90,
								halign:'center',
								align:'center',
								hidden:true
							},{
								title:'雌性动物数量',
								field:'femaleNum',
								width:90,
								halign:'center',
								align:'center',
								hidden:true
							},{
								title:'雄性动物给药容积',
								field:'maleVolume',
								width:150,
								halign:'center',
								align:'center',
								hidden:true
							},{
								title:'雌性动物给药容积',
								field:'femaleVolume',
								width:150,
								halign:'center',
								align:'center',
								hidden:true
							},{
								title:'雄性动物给药浓度',
								field:'maleThickness',
								width:150,
								halign:'center',
								align:'center',
								hidden:true
							},{
								title:'雌性动物给药浓度',
								field:'femaleThickness',
								width:150,
								halign:'center',
								align:'center',
								hidden:true
							}]]
					   	});
						
						//无动物，基本信息按钮不可见
						$('#basicbutton').css('display','none');
						$('#addbutton').linkbutton('enable');  
						$('#okbutton').linkbutton('enable');  
					}
				}else if(r.state == "3" && r.doseSettingFlag == 1){

					//剂量单位
					if(r.animalType){
						//有动物
						if(r.isNoGender == 0){
							$('#addbutton').linkbutton('disable');  
							$('#okbutton').linkbutton('disable');  
						}else if(r.isNoGender == 1){
							//分雄雌
							if(r.isIndentical == 1){
								//雌雄剂量相同
								$('#doseSettingTable').datagrid({    
									url:sybp()+"/tblDoseSettingAction_loadList.action?studyNoPara="+studyNoPara,
							   	    columns :[[{
										title:'',
										field:'id',
										width:10,
										halign:'center',
										align:'center',
										hidden:true
									},{
										title:'专题编号',
										field:'studyNo',
										width:10,
										halign:'center',
										align:'center',
										hidden:true
									},{
										title:'剂量组',
										field:'dosageNum',
										width:50,
										halign:'center',
										align:'center'
									},{
										title:'剂量组说明',
										field:'dosageDesc',
										width:90,
										halign:'center',
										align:'center',
									},{
										title:'剂量('+r.dosageUnit+')',
										field:'dosage',
										width:100,
										halign:'center',
										align:'center'
									},{
										title:'雌性剂量',
										field:'femaleDosage',
										width:100,
										halign:'center',
										align:'center',
										hidden:true
									},{
										title:'雄性动物数量',
										field:'maleNum',
										width:90,
										halign:'center',
										align:'center',
										hidden:false
									},{
										title:'雌性动物数量',
										field:'femaleNum',
										width:90,
										halign:'center',
										align:'center',
										hidden:false
									},{
										title:'给药容积('+r.volumeUnit+')',
										field:'maleVolume',
										width:150,
										halign:'center',
										align:'center',
										hidden:true
									},{
										title:'雌性动物给药容积('+r.volumeUnit+')',
										field:'femaleVolume',
										width:150,
										halign:'center',
										align:'center',
										hidden:true
									},{
										title:'给药浓度('+r.thicknessUnit+')',
										field:'maleThickness',
										width:150,
										halign:'center',
										align:'center',
										hidden:true
									},{
										title:'雌性动物给药浓度('+r.thicknessUnit+')',
										field:'femaleThickness',
										width:150,
										halign:'center',
										align:'center',
										hidden:true
									}]]
							   	});
									
								//给药容积
								if(r.volumeUnit){
									$('#doseSettingTable').datagrid('showColumn','maleVolume');
								}
	    						if(r.thicknessUnit){
	    							$('#doseSettingTable').datagrid('showColumn','maleThickness');
	    						}
							}else{
								//不相同
								$('#doseSettingTable').datagrid({    
									url:sybp()+"/tblDoseSettingAction_loadList.action?studyNoPara="+studyNoPara,
							   	    columns :[[{
										title:'',
										field:'id',
										width:10,
										halign:'center',
										align:'center',
										hidden:true
									},{
										title:'专题编号',
										field:'studyNo',
										width:10,
										halign:'center',
										align:'center',
										hidden:true
									},{
										title:'剂量组',
										field:'dosageNum',
										width:50,
										halign:'center',
										align:'center'
									},{
										title:'剂量组说明',
										field:'dosageDesc',
										width:90,
										halign:'center',
										align:'center',
									},{
										title:'雄性剂量('+r.dosageUnit+')',
										field:'dosage',
										width:100,
										halign:'center',
										align:'center'
									},{
										title:'雌性剂量('+r.dosageUnit+')',
										field:'femaleDosage',
										width:100,
										halign:'center',
										align:'center',
										hidden:false
									},{
										title:'雄性动物数量',
										field:'maleNum',
										width:90,
										halign:'center',
										align:'center',
										hidden:false
									},{
										title:'雌性动物数量',
										field:'femaleNum',
										width:90,
										halign:'center',
										align:'center',
										hidden:false
									},{
										title:'雄性动物给药容积('+r.volumeUnit+')',
										field:'maleVolume',
										width:150,
										halign:'center',
										align:'center',
										hidden:true
									},{
										title:'雌性动物给药容积('+r.volumeUnit+')',
										field:'femaleVolume',
										width:145,
										halign:'center',
										align:'center',
										hidden:true
									},{
										title:'雄性动物给药浓度('+r.thicknessUnit+')',
										field:'maleThickness',
										width:145,
										halign:'center',
										align:'center',
										hidden:true
									},{
										title:'雌性动物给药浓度('+r.thicknessUnit+')',
										field:'femaleThickness',
										width:150,
										halign:'center',
										align:'center',
										hidden:true
									}]]
							   	});
									
								//给药容积
								if(r.volumeUnit){
									$('#doseSettingTable').datagrid('showColumn','maleVolume');
									$('#doseSettingTable').datagrid('showColumn','femaleVolume');
								}
	    						if(r.thicknessUnit){
	    							$('#doseSettingTable').datagrid('showColumn','maleThickness');
	    							$('#doseSettingTable').datagrid('showColumn','femaleThickness');
	    						}
							}
							
							$('#addbutton').linkbutton('enable');  
							$('#okbutton').linkbutton('enable');
						}else{
							//不分雄雌
							
							$('#doseSettingTable').datagrid({    
								url:sybp()+"/tblDoseSettingAction_loadList.action?studyNoPara="+studyNoPara,
						   	    columns :[[{
									title:'',
									field:'id',
									width:10,
									halign:'center',
									align:'center',
									hidden:true
								},{
									title:'专题编号',
									field:'studyNo',
									width:10,
									halign:'center',
									align:'center',
									hidden:true
								},{
									title:'剂量组',
									field:'dosageNum',
									width:50,
									halign:'center',
									align:'center'
								},{
									title:'剂量组说明',
									field:'dosageDesc',
									width:90,
									halign:'center',
									align:'center',
								},{
									title:'剂量('+r.dosageUnit+')',
									field:'dosage',
									width:100,
									halign:'center',
									align:'center'
								},{
									title:'雌性剂量',
									field:'femaleDosage',
									width:100,
									halign:'center',
									align:'center',
									hidden:true
								},{
									title:'动物数量',
									field:'maleNum',
									width:90,
									halign:'center',
									align:'center',
									hidden:false
								},{
									title:'雌性动物数量',
									field:'femaleNum',
									width:90,
									halign:'center',
									align:'center',
									hidden:true
								},{
									title:'给药容积('+r.volumeUnit+')',
									field:'maleVolume',
									width:150,
									halign:'center',
									align:'center',
									hidden:true
								},{
									title:'雌性动物给药容积('+r.volumeUnit+')',
									field:'femaleVolume',
									width:150,
									halign:'center',
									align:'center',
									hidden:true
								},{
									title:'给药浓度('+r.thicknessUnit+')',
									field:'maleThickness',
									width:150,
									halign:'center',
									align:'center',
									hidden:true
								},{
									title:'雌性动物给药浓度('+r.thicknessUnit+')',
									field:'femaleThickness',
									width:150,
									halign:'center',
									align:'center',
									hidden:true
								}]]
						   	});
								
								//给药容积
								if(r.volumeUnit){
									$('#doseSettingTable').datagrid('showColumn','maleVolume');
								}
	    						if(r.thicknessUnit){
	    							$('#doseSettingTable').datagrid('showColumn','maleThickness');
	    						}
	    						$('#addbutton').linkbutton('enable');  
								$('#okbutton').linkbutton('enable');
						}
						
						//有动物,三按钮可见可用
						$('#basicbutton').linkbutton('enable');  
						$('#basicbutton').css('display','');  
					}else{
						//无动物
						$('#doseSettingTable').datagrid({    
							url:sybp()+"/tblDoseSettingAction_loadList.action?studyNoPara="+studyNoPara,
					   	    columns :[[{
								title:'',
								field:'id',
								width:10,
								halign:'center',
								align:'center',
								hidden:true
							},{
								title:'专题编号',
								field:'studyNo',
								width:10,
								halign:'center',
								align:'center',
								hidden:true
							},{
								title:'剂量组',
								field:'dosageNum',
								width:50,
								halign:'center',
								align:'center'
							},{
								title:'剂量组说明',
								field:'dosageDesc',
								width:90,
								halign:'center',
								align:'center',
							},{
								title:'剂量('+r.dosageUnit+')',
								field:'dosage',
								width:100,
								halign:'center',
								align:'center'
							},{
								title:'雌性剂量',
								field:'femaleDosage',
								width:100,
								halign:'center',
								align:'center',
								hidden:true
							},{
								title:'雄性动物数量',
								field:'maleNum',
								width:90,
								halign:'center',
								align:'center',
								hidden:true
							},{
								title:'雌性动物数量',
								field:'femaleNum',
								width:90,
								halign:'center',
								align:'center',
								hidden:true
							},{
								title:'雄性动物给药容积',
								field:'maleVolume',
								width:150,
								halign:'center',
								align:'center',
								hidden:true
							},{
								title:'雌性动物给药容积',
								field:'femaleVolume',
								width:150,
								halign:'center',
								align:'center',
								hidden:true
							},{
								title:'雄性动物给药浓度',
								field:'maleThickness',
								width:150,
								halign:'center',
								align:'center',
								hidden:true
							},{
								title:'雌性动物给药浓度',
								field:'femaleThickness',
								width:150,
								halign:'center',
								align:'center',
								hidden:true
							}]]
					   	});
						
						//无动物，基本信息按钮不可见
						$('#basicbutton').css('display','none');
						$('#addbutton').linkbutton('enable');  
						$('#okbutton').linkbutton('enable');  
					}
					
					$('#editbutton').linkbutton('disable'); 
					$('#basicbutton').linkbutton('disable');  
					$('#addbutton').linkbutton('disable');  
					$('#okbutton').linkbutton('disable');
					$('#delbutton').linkbutton('disable');
				}else{
					//已确认基本信息，按钮不可用
					$('#basicbutton').linkbutton('disable');  
					$('#addbutton').linkbutton('disable');  
					$('#okbutton').linkbutton('disable');
					//剂量单位
					if(r.animalType){
						//有动物
						if(r.isNoGender == 0){
						}else if(r.isNoGender == 1){
							//分雄雌
							if(r.isIndentical == 1){
								//雌雄剂量相同
								$('#doseSettingTable').datagrid({    
									url:sybp()+"/tblDoseSettingAction_loadList.action?studyNoPara="+studyNoPara,
							   	    columns :[[{
										title:'',
										field:'id',
										width:10,
										halign:'center',
										align:'center',
										hidden:true
									},{
										title:'专题编号',
										field:'studyNo',
										width:10,
										halign:'center',
										align:'center',
										hidden:true
									},{
										title:'剂量组',
										field:'dosageNum',
										width:50,
										halign:'center',
										align:'center'
									},{
										title:'剂量组说明',
										field:'dosageDesc',
										width:90,
										halign:'center',
										align:'center',
									},{
										title:'剂量('+r.dosageUnit+')',
										field:'dosage',
										width:100,
										halign:'center',
										align:'center'
									},{
										title:'雌性剂量',
										field:'femaleDosage',
										width:100,
										halign:'center',
										align:'center',
										hidden:true
									},{
										title:'雄性动物数量',
										field:'maleNum',
										width:90,
										halign:'center',
										align:'center',
										hidden:false
									},{
										title:'雌性动物数量',
										field:'femaleNum',
										width:90,
										halign:'center',
										align:'center',
										hidden:false
									},{
										title:'给药容积('+r.volumeUnit+')',
										field:'maleVolume',
										width:150,
										halign:'center',
										align:'center',
										hidden:true
									},{
										title:'雌性动物给药容积('+r.volumeUnit+')',
										field:'femaleVolume',
										width:150,
										halign:'center',
										align:'center',
										hidden:true
									},{
										title:'给药浓度('+r.thicknessUnit+')',
										field:'maleThickness',
										width:150,
										halign:'center',
										align:'center',
										hidden:true
									},{
										title:'雌性动物给药浓度('+r.thicknessUnit+')',
										field:'femaleThickness',
										width:150,
										halign:'center',
										align:'center',
										hidden:true
									}]]
							   	});
									
								//给药容积
								if(r.volumeUnit){
									$('#doseSettingTable').datagrid('showColumn','maleVolume');
								}
	    						if(r.thicknessUnit){
	    							$('#doseSettingTable').datagrid('showColumn','maleThickness');
	    						}
							}else{
								//不相同
								$('#doseSettingTable').datagrid({    
									url:sybp()+"/tblDoseSettingAction_loadList.action?studyNoPara="+studyNoPara,
							   	    columns :[[{
										title:'',
										field:'id',
										width:10,
										halign:'center',
										align:'center',
										hidden:true
									},{
										title:'专题编号',
										field:'studyNo',
										width:10,
										halign:'center',
										align:'center',
										hidden:true
									},{
										title:'剂量组',
										field:'dosageNum',
										width:50,
										halign:'center',
										align:'center'
									},{
										title:'剂量组说明',
										field:'dosageDesc',
										width:90,
										halign:'center',
										align:'center',
									},{
										title:'雄性剂量('+r.dosageUnit+')',
										field:'dosage',
										width:100,
										halign:'center',
										align:'center'
									},{
										title:'雌性剂量('+r.dosageUnit+')',
										field:'femaleDosage',
										width:100,
										halign:'center',
										align:'center',
										hidden:false
									},{
										title:'雄性动物数量',
										field:'maleNum',
										width:90,
										halign:'center',
										align:'center',
										hidden:false
									},{
										title:'雌性动物数量',
										field:'femaleNum',
										width:90,
										halign:'center',
										align:'center',
										hidden:false
									},{
										title:'雄性动物给药容积('+r.volumeUnit+')',
										field:'maleVolume',
										width:150,
										halign:'center',
										align:'center',
										hidden:true
									},{
										title:'雌性动物给药容积('+r.volumeUnit+')',
										field:'femaleVolume',
										width:145,
										halign:'center',
										align:'center',
										hidden:true
									},{
										title:'雄性动物给药浓度('+r.thicknessUnit+')',
										field:'maleThickness',
										width:145,
										halign:'center',
										align:'center',
										hidden:true
									},{
										title:'雌性动物给药浓度('+r.thicknessUnit+')',
										field:'femaleThickness',
										width:150,
										halign:'center',
										align:'center',
										hidden:true
									}]]
							   	});
									
								//给药容积
								if(r.volumeUnit){
									$('#doseSettingTable').datagrid('showColumn','maleVolume');
									$('#doseSettingTable').datagrid('showColumn','femaleVolume');
								}
	    						if(r.thicknessUnit){
	    							$('#doseSettingTable').datagrid('showColumn','maleThickness');
	    							$('#doseSettingTable').datagrid('showColumn','femaleThickness');
	    						}
							}
							
						}else{
							//不分雄雌
							
							$('#doseSettingTable').datagrid({    
								url:sybp()+"/tblDoseSettingAction_loadList.action?studyNoPara="+studyNoPara,
						   	    columns :[[{
									title:'',
									field:'id',
									width:10,
									halign:'center',
									align:'center',
									hidden:true
								},{
									title:'专题编号',
									field:'studyNo',
									width:10,
									halign:'center',
									align:'center',
									hidden:true
								},{
									title:'剂量组',
									field:'dosageNum',
									width:50,
									halign:'center',
									align:'center'
								},{
									title:'剂量组说明',
									field:'dosageDesc',
									width:90,
									halign:'center',
									align:'center',
								},{
									title:'剂量('+r.dosageUnit+')',
									field:'dosage',
									width:100,
									halign:'center',
									align:'center'
								},{
									title:'雌性剂量',
									field:'femaleDosage',
									width:100,
									halign:'center',
									align:'center',
									hidden:true
								},{
									title:'动物数量',
									field:'maleNum',
									width:90,
									halign:'center',
									align:'center',
									hidden:false
								},{
									title:'雌性动物数量',
									field:'femaleNum',
									width:90,
									halign:'center',
									align:'center',
									hidden:true
								},{
									title:'给药容积('+r.volumeUnit+')',
									field:'maleVolume',
									width:150,
									halign:'center',
									align:'center',
									hidden:true
								},{
									title:'雌性动物给药容积('+r.volumeUnit+')',
									field:'femaleVolume',
									width:150,
									halign:'center',
									align:'center',
									hidden:true
								},{
									title:'给药浓度('+r.thicknessUnit+')',
									field:'maleThickness',
									width:150,
									halign:'center',
									align:'center',
									hidden:true
								},{
									title:'雌性动物给药浓度('+r.thicknessUnit+')',
									field:'femaleThickness',
									width:150,
									halign:'center',
									align:'center',
									hidden:true
								}]]
						   	});
								
								//给药容积
								if(r.volumeUnit){
									$('#doseSettingTable').datagrid('showColumn','maleVolume');
								}
	    						if(r.thicknessUnit){
	    							$('#doseSettingTable').datagrid('showColumn','maleThickness');
	    						}
	    						$('#addbutton').linkbutton('enable');  
								$('#okbutton').linkbutton('enable');
						}
						
						//有动物,三按钮可见可用
						$('#basicbutton').css('display','');  
					}else{
						//无动物
						$('#doseSettingTable').datagrid({    
							url:sybp()+"/tblDoseSettingAction_loadList.action?studyNoPara="+studyNoPara,
					   	    columns :[[{
								title:'',
								field:'id',
								width:10,
								halign:'center',
								align:'center',
								hidden:true
							},{
								title:'专题编号',
								field:'studyNo',
								width:10,
								halign:'center',
								align:'center',
								hidden:true
							},{
								title:'剂量组',
								field:'dosageNum',
								width:50,
								halign:'center',
								align:'center'
							},{
								title:'剂量组说明',
								field:'dosageDesc',
								width:90,
								halign:'center',
								align:'center',
							},{
								title:'剂量('+r.dosageUnit+')',
								field:'dosage',
								width:100,
								halign:'center',
								align:'center'
							},{
								title:'雌性剂量',
								field:'femaleDosage',
								width:100,
								halign:'center',
								align:'center',
								hidden:true
							},{
								title:'雄性动物数量',
								field:'maleNum',
								width:90,
								halign:'center',
								align:'center',
								hidden:true
							},{
								title:'雌性动物数量',
								field:'femaleNum',
								width:90,
								halign:'center',
								align:'center',
								hidden:true
							},{
								title:'雄性动物给药容积',
								field:'maleVolume',
								width:150,
								halign:'center',
								align:'center',
								hidden:true
							},{
								title:'雌性动物给药容积',
								field:'femaleVolume',
								width:150,
								halign:'center',
								align:'center',
								hidden:true
							},{
								title:'雄性动物给药浓度',
								field:'maleThickness',
								width:150,
								halign:'center',
								align:'center',
								hidden:true
							},{
								title:'雌性动物给药浓度',
								field:'femaleThickness',
								width:150,
								halign:'center',
								align:'center',
								hidden:true
							}]]
					   	});
						
						//无动物，基本信息按钮不可见
						$('#basicbutton').css('display','none');
					}
				}
				
			}
			if(member != ""){
				$('#basicbutton').linkbutton('disable');  
				$('#addbutton').linkbutton('disable');  
				$('#okbutton').linkbutton('disable');
				$('#editbutton').linkbutton('disable');
				$('#delbutton').linkbutton('disable');
			}
		}
	});
}
/**
 * 添加
 * @return
 */
function onAddButton(){
	showDoseAddEditDialog(updateWeb,"add");
}

/**
 * 删除
 * @return
 */
function onDelButton(){
	var row = $('#doseSettingTable').datagrid('getSelected');
	if(row && row.id){
		$.ajax({
			url:sybp()+'/tblDoseSettingAction_del.action',
			type:'post',
			data:{
				id:row.id
			},
			dataType:'json',
			success:function(r){
				if(r && r.success){
					parent.parent.showMessager(1,r.msg,true,5000);
					updateWeb();
				}else{
					parent.parent.showMessager(2,r.msg,true,5000);
				}
			}
		});
	}
}

/**
 * 编辑
 * @return
 */
function onEditButton(){
	var row = $('#doseSettingTable').datagrid('getSelected');
	if(row && row.id){
		showDoseAddEditDialog(updateWeb,"edit");
	}
}
/**
 * 动物编号设置
 * @return
 */
function onCodeButton(){
	showAnimalCodeDialog();
}

/**
 * 剂量组设计确认
 * @return
 */
function onConfirm(){
	var studyNoPara = $('#studyNoPara').val();
	$.ajax({
		url:sybp()+'/tblDoseSettingAction_signCheck.action',
		type:'post',
		data:{
			studyNoPara:studyNoPara
		},
		dataType:'json',
		success:function(r){
			if(r && r.success){
				if(r.msg == '9'){
					showAnimalCodeConfirmDialog();
				}else{
					qm_showQianmingDialog('confirmSign');
				}
			}else{
				parent.parent.showMessager(2,r.msg,true,5000);
			}
		}
	});
}
/**
 * 确认签字
 * @return
 */
function confirmSign(){
	var studyNoPara =$('#studyNoPara').val();
	$.ajax({
		url:sybp()+'/tblDoseSettingAction_signConfirm.action',
		type:'post',
		data:{
			studyNoPara:studyNoPara
		},
		dataType:'json',
		success:function(r){
			if(r && r.success){
				parent.parent.showMessager(1,r.msg,true,5000);
				updateWeb();
			}else{
				parent.parent.showMessager(2,r.msg,true,5000);
			}
		}
	});
}

/**
 * 上移
 * @return
 */
function onUpToolbarButton(){
	var row = $('#doseSettingTable').datagrid('getSelected');
	var studyNoPara = $('#studyNoPara').val();
	if(row && studyNoPara){
		$.ajax({
			url:sybp()+'/tblDoseSettingAction_upMove.action',
			type:'post',
			data:{
				studyNoPara:studyNoPara,
				dosageNum:row.dosageNum
			},
			dataType:'json',
			success:function(r){
				if(r && r.success){
					//parent.parent.showMessager(1,r.msg,true,5000);
					updateWeb();
				}else{
					parent.parent.showMessager(2,r.msg,true,5000);
				}
			}
		});
	}
}

/**
 * 下移
 * @return
 */
function onDownToolbarButton(){
	var row = $('#doseSettingTable').datagrid('getSelected');
	var studyNoPara = $('#studyNoPara').val();
	if(row && studyNoPara){
		$.ajax({
			url:sybp()+'/tblDoseSettingAction_downMove.action',
			type:'post',
			data:{
				studyNoPara:studyNoPara,
				dosageNum:row.dosageNum
			},
			dataType:'json',
			success:function(r){
				if(r && r.success){
					//parent.parent.showMessager(1,r.msg,true,5000);
					updateWeb();
				}else{
					parent.parent.showMessager(2,r.msg,true,5000);
				}
			}
		});
	}
}