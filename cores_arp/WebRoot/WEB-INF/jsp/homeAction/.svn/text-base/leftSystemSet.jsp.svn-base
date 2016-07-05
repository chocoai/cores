<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ taglib prefix="s" uri="/struts-tags" %>
<title>CoRES项目管理系统</title>
<%@ include file="/WEB-INF/jsp/public/easyui.jspf"%>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/home_left.css"  />
<style type='text/css'>
  /*accordion 选中样式*/
.panel .accordion-header-selected{
	background:#f3f3f3;
}

.panel .accordion-header-selected .panel-title{
	background:#f3f3f3;
	color:#575765;
}

</style>
<script type="text/javascript">
	var tableHeight ;
	$(function(){
		tableHeight = document.body.clientHeight;
		$('#accordion').accordion({
			height:tableHeight,
			onSelect:function(title,index){
			}
		}); 
		
		//选择1
		$('#accordion').accordion('select',0);
		
		selectTable= $('#selectTable0').datagrid({ 
	   	    showHeader:false,
	   	    showFooter:false,
		    animate:false,   
		    singleSelect: true, //不支持多选
		    border:false,
			onSelect:function(rowIndex, rowData){
			   afterSelectDatagrid('selectTable0');
			    var id = rowData.id;
			   if(id == 1 ){//工作安排
			       window.open(sybp()+'/schedulePlanAction_list.action','main');
			    }
			    if(id == 2 ){//总揽
			       window.open(sybp()+'/individualAction_listAnimalByArea.action','main');
			    }
			    if(id == 3 ){//综合查询
			       window.open(sybp()+'/individualAction_listAnimalByMore.action','main');
			    }
			    if(id == 4 ){//检疫查询
			       window.open(sybp()+'/reportAction_listCheckItems.action','main');
			       //window.open(sybp()+'/qcAction_listQC.action','main');
			    }
			    if(id == 5 ){//查看日程
			       window.open(sybp()+'/schedulePlanAction_look.action','main');
			    }
			   
			},	
			onLoadSuccess:function(row, data){
			   
		    },
		    onLoadError:function(row, data){
		    },
	        rowStyler:function(index,row){   
     		}    
		      
		 });
		 /**报表*/
		selectTable= $('#selectTable7').datagrid({ 
	   	    showHeader:false,
	   	    showFooter:false,
		    animate:false,   
		    singleSelect: true, //不支持多选
		    border:false,
			onSelect:function(rowIndex, rowData){
			   afterSelectDatagrid('selectTable1');
			    var id = rowData.id;
			   if(id == 1 ){//饲养
			       window.open(sybp()+'/areaAction_listArea.action','main');
			   }else if(id == 2){//动物在库明细
			       window.open(sybp()+'/areaAction_listAnimal.action','main');
			   }else if(id == 3){//仔猴离乳
				   window.open(sybp()+'/leavebreastAction_listLeavebreast.action','main');
			   }else if(id == 4){//猴群调拨
				   window.open(sybp()+'/changeroomAction_listChangeroom.action','main');
		       }else if(id == 5){//体格检查
				   window.open(sybp()+'/weightAction_listWeight.action','main');
		       }else if(id == 6){//病毒检测记录
				   window.open(sybp()+'/virusAction_listVirus.action','main');
		       }else if(id == 7){//体外寄生虫记录
		    	   window.open(sybp()+'/parasiteAction_listOutParasite.action','main');
		       }else if(id == 8){//体内寄生虫记录
		    	   window.open(sybp()+'/parasiteAction_listInParasite.action','main');
		       }else if(id == 9){//TB检测记录
		    	   window.open(sybp()+'/tbAction_listTB.action','main');
		       }else if(id == 10){//细菌检测记录
		    	   window.open(sybp()+'/bacteriaAction_listBacteria.action','main');
		       }else if(id == 11){//驱虫记录
		    	   window.open(sybp()+'/qcAction_listQC.action','main');
		       }else if(id == 12){//血液，生化记录
		    	   window.open(sybp()+'/xcgAction_listXCG.action','main');
		       }else if(id == 13){//疫苗接种
			       window.open(sybp()+'/vaccineAction_listVaccine.action','main');
		       }
			},	
			onLoadSuccess:function(row, data){
		    },
		    onLoadError:function(row, data){
		    },
	        rowStyler:function(index,row){   
     		}    
		      
		 });
		/**饲养管理*/
		selectTable= $('#selectTable1').datagrid({ 
	   	    showHeader:false,
	   	    showFooter:false,
		    animate:false,   
		    singleSelect: true, //不支持多选
		    border:false,
			onSelect:function(rowIndex, rowData){
			   afterSelectDatagrid('selectTable1');
			    var id = rowData.id;
			   if(id == 1 ){
			       window.open(sybp()+'/individualAction_list.action','main');
			   }else if(id == 2){
			       window.open(sybp()+'/weightAction_list.action','main');
			   }else if(id == 3){
				   window.open(sybp()+'/areaAction_list.action','main');
			   }else if(id == 4){
				   window.open(sybp()+'/changeroomAction_list.action','main');
		       }else if(id == 5){
				   window.open(sybp()+'/deathAction_list.action','main');
		       }else if(id == 6){
				   window.open(sybp()+'/salemonkeyAction_list.action','main');
		       }else if(id == 7){
		    	   window.open(sybp()+'/inventoryAction_list.action','main');
		       }
			},	
			onLoadSuccess:function(row, data){
		    },
		    onLoadError:function(row, data){
		    },
	        rowStyler:function(index,row){   
     		}    
		      
		 });
		 selectTable= $('#selectTable2').datagrid({ 
	   	    showHeader:false,
	   	    showFooter:false,
		    animate:false,   
		    singleSelect: true, //不支持多选
		    border:false,
			onSelect:function(rowIndex, rowData){
			   afterSelectDatagrid('selectTable2');
			   var id = rowData.id;
			   if(id == 1 ){
			       window.open(sybp()+'/individualAction_breedList.action','main');
			   }else if(id == 2){
			       window.open(sybp()+'/breedingAction_list.action','main');
			   }else if(id == 3){
				   window.open(sybp()+'/gestationAction_list.action','main');
			   }else if(id == 4){
				   window.open(sybp()+'/childbirthAction_list.action','main');
		       }else if(id == 5){
				   window.open(sybp()+'/weaningAction_list.action','main');
		       }else if(id == 6){
				   window.open(sybp()+'/miscarriageAction_list.action','main');
		       }else if(id == 7){
		    	   window.open(sybp()+'/miscarriageAction_list.action','main');
		       }
			},	
			onLoadSuccess:function(row, data){
		    },
		    onLoadError:function(row, data){
		    },
	        rowStyler:function(index,row){   
     		}    
		      
		 });
		 
		 selectTable= $('#selectTable3').datagrid({ 
	   	    showHeader:false,
	   	    showFooter:false,
		    animate:false,   
		    singleSelect: true, //不支持多选
		    border:false,
			onSelect:function(rowIndex, rowData){
			  afterSelectDatagrid('selectTable3');
			  var id = rowData.id;
			  if(id == 1 ){
			       window.open(sybp()+'/routineAction_list.action','main');
			  }else if(id == 2){
			       window.open(sybp()+'/exportAction_list.action','main');
			  }else if(id == 3){
				   window.open(sybp()+'/quarantineAction_list.action','main');
			  }else if(id == 4){
				   window.open(sybp()+'/childbirthAction_list.action','main');
		      }
			},	
			onLoadSuccess:function(row, data){
		    },
		    onLoadError:function(row, data){
		    },
	        rowStyler:function(index,row){   
     		}    
		      
		 });
		  selectTable= $('#selectTable4').datagrid({ 
	   	    showHeader:false,
	   	    showFooter:false,
		    animate:false,   
		    singleSelect: true, //不支持多选
		    border:false,
			onSelect:function(rowIndex, rowData){
			   afterSelectDatagrid('selectTable4');
			   var id = rowData.id;
			  if(id == 1 ){
			       window.open(sybp()+'/hospitaldlAction_list.action','main');
			  }else if(id == 2){
			       window.open(sybp()+'/hospitalAction_list.action','main');
			  }else if(id == 3){
				   window.open(sybp()+'/treasuryAction_list.action','main');
			  }else if(id == 4){//疾病分析
				   //window.open(sybp()+'/treasuryAction_reportTreasury.action','main');
				   window.open(sybp()+'/jfreecharAction_loadJfreechar.action','main');
		      }
			},	
			onLoadSuccess:function(row, data){
		    },
		    onLoadError:function(row, data){
		    },
	        rowStyler:function(index,row){   
     		}    
		      
		 });
		
		
			selectTable= $('#selectTable5').datagrid({ 
	   	    showHeader:false,
	   	    showFooter:false,
		    animate:false,   
		    singleSelect: true, //不支持多选
		    border:false,
			onSelect:function(rowIndex, rowData){
			    var id = rowData.id;
			    afterSelectDatagrid('selectTable5');
			    if(id == 1){
			    	window.open(sybp()+'/employeeAction_list.action','main');
				}else if(id == 2){//系统日志
					window.open(sybp()+'/iploginAction_list.action','main');
				}else if(id == 3){//角色权限
					window.open(sybp()+'/roleAction_list.action','main');
				}else if(id == 4 ){//养殖场信息
					window.open(sybp()+'/yzcAction_list.action','main');
				}else if(id == 5){//猴场配置
					window.open(sybp()+'/animaltypeAction_list.action','main');
				}else if(id == 6){
				     window.open(sybp()+'/tblLogAction_list.action','main');
				}
			},	
			onLoadSuccess:function(row, data){
			    $('#selectTableDiv').css('display','');   
		    },
		    onLoadError:function(row, data){
		    },
	        rowStyler:function(index,row){   
     		}    
		      
		 });
		 
	});
	/**选择时清除其他Datagrid的SELECT*/
	function afterSelectDatagrid(select){
	   if(select != "selectTable0"){
	      $('#selectTable0').datagrid("uncheckAll");
	   }
	   if(select != "selectTable1"){
	      $('#selectTable1').datagrid("uncheckAll");
	   }
	   if(select != "selectTable2"){
	      $('#selectTable2').datagrid("uncheckAll");
	   }
	   if(select != "selectTable3"){
	      $('#selectTable3').datagrid("uncheckAll");
	   }
	   if(select != "selectTable4"){
	      $('#selectTable4').datagrid("uncheckAll");
	   }
	   if(select != "selectTable"){
	      $('#selectTable5').datagrid("uncheckAll");
	   }
	   
	}
</script>
</head>
<body>
<div id="accordion" class="easyui-accordion" style="width:173px;">   
    	<s:if test="isZonglSet == 1">
    	<div title="总览" data-options="selected:false" style="overflow:hidden;">
    	      <%@ include file="/WEB-INF/jsp/homeAction/left/overView.jspf"%>  
    	</div>
    	</s:if>
    	<s:if test="isReportSet == 1">
    	<div title="报表" data-options="selected:false"  style="overflow:hidden;" >
    	   <%@ include file="/WEB-INF/jsp/homeAction/left/report.jspf"%>
    	</div>
    	</s:if>
    	<s:if test="isSiySet == 1 ">   
    	<div title="饲养管理" data-options="selected:false"  style="overflow:hidden;" >
    	   <%@ include file="/WEB-INF/jsp/homeAction/left/feeding.jspf"%>
    	</div>
    	</s:if>
    	<s:if test="isFanzSet == 1">   
    	<div title="繁殖管理" data-options="selected:false" style="overflow:hidden;">
    	   <%@ include file="/WEB-INF/jsp/homeAction/left/breed.jspf"%>
    	</div>
    	</s:if>
    	<s:if test="isFangySet ==1 "> 
    	<div title="防疫管理" data-options="selected:false"  style="overflow:hidden;" >
    	   <%@ include file="/WEB-INF/jsp/homeAction/left/epidemic.jspf"%>
    	</div>
    	</s:if>
    	<s:if test="isZhilSet ==1 ">   
    	<div title="治疗管理" data-options="selected:false" style="overflow:hidden;">
    	   <%@ include file="/WEB-INF/jsp/homeAction/left/treat.jspf"%>
    	</div>
    	</s:if>
    	<s:if test="isSystemSet == 1">
    	<div title="系统设置" data-options="selected:false"  style="overflow:hidden;" >
    	    <%@ include file="/WEB-INF/jsp/homeAction/left/system.jspf"%>
      </div>
      </s:if>   
</div>
</body>
</html>
