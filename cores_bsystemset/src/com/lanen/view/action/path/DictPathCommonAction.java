package com.lanen.view.action.path;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.Json;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.path.DictPathCommon;
import com.lanen.model.path.DictViscera;
import com.lanen.service.path.DictPathCommonService;
import com.lanen.service.path.DictVisceraService;

/**
 * 毒性病理控制器
 * @author 黄国刚
 *
 */
@Controller
@Scope("prototype")
public class DictPathCommonAction extends BaseAction<DictPathCommon>{

	private static final long serialVersionUID = -2958685755591587895L;
	
	/**
	 * 病理字典service
	 */
	@Resource
	private DictPathCommonService dictPathCommonService;
	/**
	 * 脏器字典service
	 */
	@Resource
	private DictVisceraService dictVisceraService;
	
	/**
	 * 序号,移动时使用
	 */
	private Integer nextSortNum;
	
	/**
	 * 转到list页面
	 * @return
	 */
	public String list(){
		return "list";
	}
	
	/**
	 * 加载数据(datagrid)
	 */
	public void loadList(){
		List<DictPathCommon> dataList = null;
		if(null != model.getDictType()){
			//1,3,13,14,15 与脏器有关
			if(model.getDictType() == 1 || model.getDictType() == 3 
					|| model.getDictType() == 13 || model.getDictType() == 14
					|| model.getDictType() == 15 || model.getDictType() == 16){
				String visceraCode = model.getVisceraCode();
				if(null != visceraCode){
					dataList = dictPathCommonService.queryList(model.getDictType(),visceraCode);
				}
			}else{
				dataList = dictPathCommonService.queryList(model.getDictType());
			}
		}
		String jsonStr = "";
		if(null != dataList){
			jsonStr = JsonPluginsUtil.beanListToJson(dataList);
		}
		writeJson(jsonStr);
	}
	
	/**
	 * 字典项 combobox
	 */
	public void loadDictType(){
		List<Map<String,Object>> dataList = DictTypeForPath.getList();
		String jsonStr = "";
		if(null != dataList){
			jsonStr =  JsonPluginsUtil.beanListToJson(dataList);
		}
		writeJson(jsonStr);
	}
	
	public void checkDescCn(){
		if(null != model.getDescCn()){
			System.out.println(model.getDescCn());
			System.out.println(model.getDictType());
			boolean isExist = false;
			if(model.getDictType() == 1 ||model.getDictType() == 3 
					||model.getDictType() == 13 ||model.getDictType() == 14 ||
					model.getDictType() == 15 ||model.getDictType() == 16){
				isExist = dictPathCommonService.hasDescCn(model.getDescCn(),model.getDictType(),model.getVisceraCode());
			}else{
				
				isExist = dictPathCommonService.hasDescCn(model.getDescCn(),model.getDictType());
			}
			if(isExist){
				writeJson("false");
			}else{
				writeJson("true");
			}
		}else{
			writeJson("false");
		}
	}
	
	/**
	 * 新建
	 */
	public void addOne(){
		Json json = new Json();
		if(checkValue("addOne")){
			DictPathCommon dictPathCommon = new DictPathCommon();
			
			String id = dictPathCommonService.getKey();
			int sortNum = dictPathCommonService.getNextSn();
			
			dictPathCommon.setItemCode(id);//主键
			dictPathCommon.setDescCn(model.getDescCn());//名称
			dictPathCommon.setPy(model.getPy());//拼音
			dictPathCommon.setDescEn(model.getDescEn());//英文
			dictPathCommon.setDescJp(model.getDescJp());//中文
			dictPathCommon.setSortNum(sortNum);//序号
			
			dictPathCommon.setDictType(model.getDictType());//字典项
			
			// 2,3 是否存在  特殊所见
			if(model.getDictType() == 2 || model.getDictType() == 3){
				dictPathCommon.setSpecicalFlag(model.getSpecicalFlag());
			}
			
			//1,3,13,14,15 与脏器有关
			if(model.getDictType() == 1 || model.getDictType() == 3 
					|| model.getDictType() == 13 || model.getDictType() == 14
					|| model.getDictType() == 15 ||model.getDictType() == 16){
				String visceraCode = model.getVisceraCode();
				DictViscera dictViscera = dictVisceraService.getById(visceraCode);
				dictPathCommon.setVisceraCode(visceraCode);
				if(null != dictViscera){
					dictPathCommon.setVisceraName(dictViscera.getVisceraName());
					dictPathCommon.setVisceraType(dictViscera.getVisceraType());
				}else if(!"00000000000".equals(visceraCode)){
					return ;
				}
			}
			//新建保存
			dictPathCommonService.addOne(dictPathCommon);
			json.setSuccess(true);
			json.setMsg(id);
		}
		
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	
	/**
	 * 检查 
	 * @param addOrEdit
	 * @return
	 */
	private boolean checkValue(String addOrEdit) {
		//名称,拼音必填,     
		if(model.getDescCn() == null || model.getDescCn().getBytes().length>100){
			return false;
		}else if(model.getDescEn() != null && model.getDescEn().getBytes().length>100){
			return false;
		}else if(model.getDescJp() != null && model.getDescJp().getBytes().length>100){
			return false;
		}else if(model.getPy() == null || model.getPy().getBytes().length>100){
			return false;
		}else if(model.getDictType() == null || model.getDictType()<1 || model.getDictType()>17){
			return false;
		}
		//编辑时有主键
		if(addOrEdit.equals("editOne")){
			if(null == model.getItemCode()){
				return false;
			}
			boolean isExist = false;
			if(model.getDictType() == 1 ||model.getDictType() == 3 
					||model.getDictType() == 13 ||model.getDictType() == 14 ||
					model.getDictType() == 15  || model.getDictType() == 16){
				isExist = dictPathCommonService.hasDescCn(model.getDescCn(), model.getItemCode(),model.getDictType(),model.getVisceraCode());
			}else{
				isExist = dictPathCommonService.hasDescCn(model.getDescCn(), model.getItemCode(),model.getDictType());
			}
			if(isExist){
				return false;
			}
		}else{
			boolean isExist = false;
			if(model.getDictType() == 1 ||model.getDictType() == 3 
					||model.getDictType() == 13 ||model.getDictType() == 14 ||
					model.getDictType() == 15 || model.getDictType() == 16){
				isExist = dictPathCommonService.hasDescCn(model.getDescCn(),model.getDictType(),model.getVisceraCode());
			}else{
				isExist = dictPathCommonService.hasDescCn(model.getDescCn(),model.getDictType());
			}
			if(isExist){
				return false;
			}
		}
		// 2,3 是否存在  特殊所见
		if(model.getDictType() == 2 || model.getDictType() == 3){
			if(null == model.getSpecicalFlag() ){
				return false;
			}
		}else{
			if(null != model.getSpecicalFlag() && model.getSpecicalFlag() != 0){
				return false;
			}
		}
		//1,3,13,14,15 与脏器有关
		if(model.getDictType() == 1 || model.getDictType() == 3 
				|| model.getDictType() == 13 || model.getDictType() == 14
				|| model.getDictType() == 15 || model.getDictType() == 16){
			if(null == model.getVisceraCode() || model.getVisceraCode().equals("")){
				return false;
			}
		}else{
			if(null != model.getVisceraCode() && !model.getVisceraCode().equals("")){
				return false;
			}
		}
		
		
		return true;
	}

	/**
	 * 编辑
	 */
	public void editOne(){

		Json json = new Json();
		if(checkValue("editOne")){
			DictPathCommon dictPathCommon = dictPathCommonService.getById(model.getItemCode());
			
			
//			dictPathCommon.setItemCode(id);//主键
			dictPathCommon.setDescCn(model.getDescCn());//名称
			dictPathCommon.setPy(model.getPy());//拼音
			dictPathCommon.setDescEn(model.getDescEn());//英文
			dictPathCommon.setDescJp(model.getDescJp());//中文
//			dictPathCommon.setSortNum(sortNum);//序号
			
//			dictPathCommon.setDictType(model.getDictType());//字典项
			
			// 2,3 是否存在  特殊所见
			if(model.getDictType() == 2 || model.getDictType() == 3){
				dictPathCommon.setSpecicalFlag(model.getSpecicalFlag());
			}
			
//			//1,3,13,14,15 与脏器有关
//			if(model.getDictType() == 1 || model.getDictType() == 3 
//					|| model.getDictType() == 13 || model.getDictType() == 14
//					|| model.getDictType() == 15){
//				String visceraCode = model.getVisceraCode();
//				DictViscera dictViscera = dictVisceraService.getById(visceraCode);
//				dictPathCommon.setVisceraCode(visceraCode);
//				dictPathCommon.setVisceraName(dictViscera.getVisceraName());
//				dictPathCommon.setVisceraType(dictViscera.getVisceraType());
//			}
			//编辑保存
			dictPathCommonService.editOne(dictPathCommon);
			json.setSuccess(true);
			json.setMsg(model.getItemCode());
		}
		
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	
	}
	/**
	 * 删除
	 */
	public void delOne(){
		Json json = new Json();
		if(null != model.getItemCode()){
			boolean success = dictPathCommonService.delOne(model.getItemCode());
			json.setSuccess(success);
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	/**
	 * 上移
	 */
	public void upOne(){
		Json json = new Json();
		if(null != model.getItemCode() && null != model.getSortNum() && null != nextSortNum){
			boolean success = dictPathCommonService.sortOne(model.getItemCode(),model.getSortNum(),nextSortNum);
			json.setSuccess(success);
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	/**
	 * 下移
	 */
	public void downOne(){
		Json json = new Json();
		if(null != model.getItemCode() && null != model.getSortNum() && null != nextSortNum){
			boolean success = dictPathCommonService.sortOne(model.getItemCode(),model.getSortNum(),nextSortNum);
			json.setSuccess(success);
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
//--------------------------------------------------------------------
	public Integer getNextSortNum() {
		return nextSortNum;
	}

	public void setNextSortNum(Integer nextSortNum) {
		this.nextSortNum = nextSortNum;
	}
	
	
	
	
}
