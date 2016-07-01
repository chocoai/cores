package com.lanen.view.action.path;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.Json;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.path.TblSuperficialTumorViscera;
import com.lanen.service.path.TblSuperficialTumorVisceraService;

/**浅表肿瘤脏器登记 Action
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class TblSuperficialTumorVisceraAction extends BaseAction<TblSuperficialTumorViscera>{

	private static final long serialVersionUID = 8295535641695717097L;

	@Resource
	private TblSuperficialTumorVisceraService tblSuperficialTumorVisceraService;
	
	/**
	 * 转到list页面
	 * @return
	 */
	public String list(){
		return "list";
	}
	/**
	 * 加载浅表肿瘤脏器列表
	 * @return
	 */
	public void superficialList(){
		List<Map<String,Object>> mapList = tblSuperficialTumorVisceraService.getSuperficialTumorVisceraList();
		String json = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(json);
	}
	/**
	 * 加载非浅表肿瘤脏器列表
	 * @return
	 */
	public void noSuperficialList(){
		List<Map<String,Object>> mapList = tblSuperficialTumorVisceraService.getNoSuperficialTumorVisceraList();
		String json = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(json);
	}
	
	/**
	 * 添加
	 */
	public void addOne(){
		Json json = new Json();
		if(null != model.getVisceraCode() && !"".equals(model.getVisceraCode())
			&& null != model.getVisceraName() && !"".equals(model.getVisceraName())){
			boolean exist = tblSuperficialTumorVisceraService.isExistByVisceraCode(model.getVisceraCode());
			if(!exist){
				TblSuperficialTumorViscera tblSuperficialTumorViscera = new TblSuperficialTumorViscera();
				tblSuperficialTumorViscera.setVisceraCode(model.getVisceraCode());
				tblSuperficialTumorViscera.setVisceraName(model.getVisceraName());
				tblSuperficialTumorVisceraService.save(tblSuperficialTumorViscera);
				json.setSuccess(true);
			}else{
				json.setMsg("不可以重复添加！");
			}
		}else{
			json.setMsg("参数错误！");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
	/**
	 * 删除
	 */
	public void removeOne(){
		Json json = new Json();
		if(null != model.getVisceraCode() && !"".equals(model.getVisceraCode())
			&& null != model.getVisceraName() && !"".equals(model.getVisceraName())){
			boolean exist = tblSuperficialTumorVisceraService.isExistByVisceraCode(model.getVisceraCode());
			if(exist){
				tblSuperficialTumorVisceraService.delete(model.getVisceraCode());
				json.setSuccess(true);
			}else{
				json.setMsg("不可以重复删除！");
			}
		}else{
			json.setMsg("参数错误！");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(json);
		writeJson(jsonStr);
	}
}
