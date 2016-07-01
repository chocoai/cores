package com.lanen.view.action.path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.Json;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.path.DictViscera;
import com.lanen.service.path.DictVisceraService;

/**
 * 脏器字典Action
 * @author 黄国刚
 *
 */

@Controller
@Scope("prototype")
public class DictVisceraAction extends BaseAction<DictViscera> {

	private static final long serialVersionUID = 1285567512160239358L;
	
	/**
	 * 脏器字典
	 */
	@Resource
	private DictVisceraService dictVisceraService;
	/**
	 * 父节点编号
	 */
	private String pvisceraCode;
	
	private String animaTypeNames;
	//排序方式
	private Integer sortType;	//1:系统 2：体重 3：固定
	
	private Integer lineNum;	//移动行数
	
	/**
	 * list.jsp
	 * @return
	 */
	public String list(){
		
		return "list";
	}
	
	/**
	 * 加载list数据
	 */
	public void loadList(){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Map<String,Object>> dataMapList = dictVisceraService.queryDataMapList(sortType);
		if(null != dataMapList){
			for(Map<String,Object> obj:dataMapList){
				Integer animalFlag = (Integer) obj.get("animalFlag");
				if(null != animalFlag && animalFlag == 0){
					String visceraCode = (String) obj.get("visceraCode");
					List<String> animalTypeNameList = dictVisceraService.getAnimalTypeNameByVisceraCode(visceraCode);
					if(null != animalTypeNameList){
						String animalTypeNames = "";
						int i = 0;
						for(String animalTypeName:animalTypeNameList){
							if(i != 0){
								animalTypeNames = animalTypeNames+","+animalTypeName;
							}else{
								animalTypeNames = animalTypeName;
							}
							i++;
						}
						obj.put("animalTypeNames", animalTypeNames);
					}
				}
			}
		}
		map.put("rows", dataMapList);
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	
	/**
	 * 加载脏器类别数据(combobox)
	 */
	public void loadVisceraType(){
		List<Map<String,Object>> dataMapList = dictVisceraService.queryVisceraTypeDataMapList();
		String jsonStr = JsonPluginsUtil.beanListToJson(dataMapList);
		writeJson(jsonStr);
	}
	/**
	 * 加载所属动物类别数据(combobox)
	 */
	public void loadAnimalType(){
		List<Map<String,Object>> dataMapList = dictVisceraService.queryAnimalTypeDataMapList();
//		Map<String,Object>  map = new HashMap<String,Object>();
//		map.put("id", "0");
//		map.put("typeName", "所有");
//		dataMapList.add(0, map);
		String jsonStr = JsonPluginsUtil.beanListToJson(dataMapList);
		writeJson(jsonStr);
	}

	/**
	 * 检查脏器名称是否被占用
	 */
	public void checkVisceraName(){
		if(null != model.getVisceraName()){
			boolean isExist = dictVisceraService.isExistByVisceraName(model.getVisceraName());
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
	 * 新建(一级),保存
	 */
	public void addOne(){
		Json json = new Json();
		if(checkModel("addOne")){
			//脏器编号(主键)
			String visceraCode = dictVisceraService.getKey();
			//序号
			Integer nextSn = dictVisceraService.getNextSn();
			//待保存对象
			DictViscera dictViscera = new DictViscera();
			//脏器编号(主键)
			dictViscera.setVisceraCode(visceraCode);
			//序号
			dictViscera.setSn(nextSn);
			dictViscera.setSnWeight(nextSn);
			dictViscera.setSnFixed(nextSn);
			
			dictViscera.setVisceraType(model.getVisceraType());
			dictViscera.setVisceraName(model.getVisceraName());
			dictViscera.setPy(model.getPy());
			dictViscera.setVisceraNameEn(model.getVisceraNameEn());
			dictViscera.setVisceraNameJp(model.getVisceraNameJp());
			dictViscera.setGender(model.getGender());
			dictViscera.setIsPart(model.getIsPart());
//			dictViscera.setAnimalType(model.getAnimalType());TODO
			
			dictViscera.setpVisceraCode(null);
			dictViscera.setLevel(1);
			
			String[] animalTypeNameArray = animaTypeNames.split(",");
			int allAnimalTypeNumber = dictVisceraService.allAnimalTypeNumber();
			if(allAnimalTypeNumber != 0 && allAnimalTypeNumber == animalTypeNameArray.length){
				dictViscera.setAnimalFlag(1);
				//添加一个(所有动物)
				dictVisceraService.addOne(dictViscera,null);
			}else{
				dictViscera.setAnimalFlag(0);
				List<String> list = new ArrayList<String>();
				for(int i = 0;i < animalTypeNameArray.length ;i++){
					list.add(animalTypeNameArray[i]);
				}
				//添加一个(非所有动物)
				dictVisceraService.addOne(dictViscera,list);
			}
			
			json.setSuccess(true);
			//存放的是visceraCode
			json.setMsg(visceraCode);
		}
		
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	/**
	 * 编辑(一级),保存
	 */
	public void editOne(){
		Json json = new Json();
		if(checkModel("editOne")){
			//待编辑对象
			DictViscera dictViscera = dictVisceraService.getById(model.getVisceraCode());
			//visceraCode\sn不变
			
			dictViscera.setVisceraType(model.getVisceraType());
			dictViscera.setVisceraName(model.getVisceraName());
			dictViscera.setPy(model.getPy());
			dictViscera.setVisceraNameEn(model.getVisceraNameEn());
			dictViscera.setVisceraNameJp(model.getVisceraNameJp());
			dictViscera.setGender(model.getGender());
			dictViscera.setIsPart(model.getIsPart());
//			dictViscera.setAnimalType(model.getAnimalType());TODO
			
			//dictViscera.setpVisceraCode(null);//
			//dictViscera.setLevel(1);
			String[] animalTypeNameArray = animaTypeNames.split(",");
			int allAnimalTypeNumber = dictVisceraService.allAnimalTypeNumber();
			if(allAnimalTypeNumber != 0 && allAnimalTypeNumber == animalTypeNameArray.length){
				dictViscera.setAnimalFlag(1);
				//更新一个及其脏器(所有动物)
				dictVisceraService.updateOne(dictViscera,null);
			}else{
				dictViscera.setAnimalFlag(0);
				//更新一个及其脏器(非所有动物)
				dictVisceraService.updateOne(dictViscera,animalTypeNameArray);
			}
			//更新一级,同时对于二级的动物类别与脏器类别,是否成对,所属进行更新
//			dictVisceraService.updateOne(dictViscera);
			
			json.setSuccess(true);
			json.setMsg(model.getVisceraCode());
		}
		
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}

	/**
	 * 新建(二级),保存
	 */
	public void addSon(){
		Json json = new Json();
		System.out.println(pvisceraCode);
		if(checkModel("addSon")){
			//父节点对象
			DictViscera pDictViscera = dictVisceraService.getById(pvisceraCode);
			//脏器编号(主键)
			String visceraCode = dictVisceraService.getKey();
			//序号
			Integer nextSn = dictVisceraService.getNextSn();
			//待保存对象
			DictViscera dictViscera = new DictViscera();
			//脏器编号(主键)
			dictViscera.setVisceraCode(visceraCode);
			//序号
			dictViscera.setSn(nextSn);
			dictViscera.setSnWeight(nextSn);
			dictViscera.setSnFixed(nextSn);
			dictViscera.setVisceraType(pDictViscera.getVisceraType());
			dictViscera.setVisceraName(model.getVisceraName());
			dictViscera.setPy(model.getPy());
			dictViscera.setVisceraNameEn(model.getVisceraNameEn());
			dictViscera.setVisceraNameJp(model.getVisceraNameJp());
			dictViscera.setGender(pDictViscera.getGender());
			dictViscera.setIsPart(0);
//			dictViscera.setAnimalType(pDictViscera.getAnimalType());TODO
			
			dictViscera.setAnimalFlag(pDictViscera.getAnimalFlag());
			dictViscera.setpVisceraCode(pvisceraCode);//
			dictViscera.setLevel(2);
//			List<String> animalTypeNameList = dictVisceraService.getAnimalTypeNameByVisceraCode(pvisceraCode);
			String[] animalTypeNameArray = animaTypeNames.split(",");
			int allAnimalTypeNumber = dictVisceraService.allAnimalTypeNumber();
			if(allAnimalTypeNumber != 0 && allAnimalTypeNumber == animalTypeNameArray.length){
				dictViscera.setAnimalFlag(1);
				//添加一个(所有动物)
				dictVisceraService.addOne(dictViscera,null);
			}else{
				dictViscera.setAnimalFlag(0);
				List<String> list = new ArrayList<String>();
				for(int i = 0;i < animalTypeNameArray.length ;i++){
					list.add(animalTypeNameArray[i]);
				}
				//添加一个(非所有动物)
				dictVisceraService.addOne(dictViscera,list);
			}
//			if(null == animalTypeNameList){
//				//添加一对象
//				dictVisceraService.addOne(dictViscera,null);
//			}else{
//				//添加一对象
//				dictVisceraService.addOne(dictViscera,animalTypeNameList);
//			}
			
			json.setSuccess(true);
			//存放的是visceraCode
			json.setMsg(visceraCode);
		}
		
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	/**
	 * 编辑(二级),保存
	 */
	public void editSon(){
		Json json = new Json();
		if(checkModel("editSon")){
			//待编辑对象
			DictViscera dictViscera = dictVisceraService.getById(model.getVisceraCode());
			dictViscera.setVisceraName(model.getVisceraName());
			dictViscera.setPy(model.getPy());
			dictViscera.setVisceraNameEn(model.getVisceraNameEn());
			dictViscera.setVisceraNameJp(model.getVisceraNameJp());
			dictViscera.setIsPart(0);
			
			
			String[] animalTypeNameArray = animaTypeNames.split(",");
			int allAnimalTypeNumber = dictVisceraService.allAnimalTypeNumber();
			if(allAnimalTypeNumber != 0 && allAnimalTypeNumber == animalTypeNameArray.length){
				dictViscera.setAnimalFlag(1);
				//更新二级脏器(所有动物)
				dictVisceraService.updateSon(dictViscera,null);
			}else{
				dictViscera.setAnimalFlag(0);
				//更新二级脏器(非所有动物)
				dictVisceraService.updateSon(dictViscera,animalTypeNameArray);
			}
			
			
////			//更新一对象
//			dictVisceraService.updateSon(dictViscera);
			
			json.setSuccess(true);
			//存放的是visceraCode
			json.setMsg(model.getVisceraCode());
		}
		
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	/**
	 * 删除脏器(包括子脏器)
	 */
	public void delOne(){
		Json json = new Json();
		if(null != model.getVisceraCode() && !"".equals(model.getVisceraCode())){
			dictVisceraService.delOne(model.getVisceraCode());
			json.setSuccess(true);
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	/**
	 * 上移
	 * 
	 */
	public void upOne(){
		Json json = new Json();
		if(null != model.getSn() && !"".equals(model.getSn()) && null != lineNum){
			//不为空,在未二级脏器
			if(null != pvisceraCode && !"".equals(pvisceraCode)){
				json.setSuccess(dictVisceraService.upOneBySnPvisceraCode(model.getSn(),pvisceraCode,sortType,lineNum));
			}else{
				//为空,一级脏器
				json.setSuccess(dictVisceraService.upOneBySn(model.getSn(),sortType,lineNum));
			}
			
			
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	/**
	 * 下移
	 * 
	 */
	public void downOne(){
		Json json = new Json();
		if(null != model.getSn() && !"".equals(model.getSn())&& null != lineNum){
			//不为空,在未二级脏器
			if(null != pvisceraCode && !"".equals(pvisceraCode)){
				json.setSuccess(dictVisceraService.downOneBySnPvisceraCode(model.getSn(),pvisceraCode,sortType,lineNum));
			}else{
				//为空,一级脏器
				
				json.setSuccess(dictVisceraService.downOneBySn(model.getSn(),sortType,lineNum));
			}
			
			
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	
	
	/**
	 * 加载一级脏器Code,Name(for combobox)
	 */
	public void load1LViscera(){
		List<Map<String,Object>> dataList = dictVisceraService.query1LCodeNameList();
		String jsonStr = "";
		
		if(null != dataList){
			//组织学所见，14，16时使用
			//"00000000000","其他"
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("visceraCode", "00000000000");
			map.put("visceraName", "其他");
			dataList.add(map);
			
			jsonStr = JsonPluginsUtil.beanListToJson(dataList);
		}
		writeJson(jsonStr);
	}
	
	/**
	 * 检查参数值
	 * @return
	 */
	private boolean checkModel(String addOrEdit) {
		if(null == model.getVisceraName() || model.getVisceraName().getBytes().length>60){
			return false;
		}else if(null == model.getVisceraName() || model.getVisceraName().getBytes().length>60){
				return false;
		}else if(null != model.getVisceraNameEn() && model.getVisceraNameEn().getBytes().length>60){
			return false;
		}else if(null != model.getVisceraNameJp() && model.getVisceraNameJp().getBytes().length>60){
			return false;
		}else if(null == model.getPy() || model.getPy().getBytes().length>100){
			return false;
		}
		
		boolean isExist = false;
		if(addOrEdit.equals("addOne") || addOrEdit.equals("addSon")){
			//新建
			isExist = dictVisceraService.isExistByVisceraName(model.getVisceraName());
			if(isExist){
				return false;
			}
			if(addOrEdit.equals("addSon")){
				if(null == pvisceraCode || "".equals(pvisceraCode)){
					return false;
				}
			}
		}else if(addOrEdit.equals("editOne") || addOrEdit.equals("editSon")){
			
			//visceraCode 为null
			if(null == model.getVisceraCode()){
				return false;
			}
			isExist = dictVisceraService.isExistByVisceraName(model.getVisceraName(),model.getVisceraCode());
			if(isExist){
				return false;
			}
		}	
			
		return true;
	}
//--------------------------------------------------------------------------------------------------
	public String getPvisceraCode() {
		return pvisceraCode;
	}

	public void setPvisceraCode(String pvisceraCode) {
		this.pvisceraCode = pvisceraCode;
	}

	public String getAnimaTypeNames() {
		return animaTypeNames;
	}

	public void setAnimaTypeNames(String animaTypeNames) {
		this.animaTypeNames = animaTypeNames;
	}

	public Integer getSortType() {
		return sortType;
	}

	public void setSortType(Integer sortType) {
		this.sortType = sortType;
	}

	public Integer getLineNum() {
		return lineNum;
	}

	public void setLineNum(Integer lineNum) {
		this.lineNum = lineNum;
	}

}
