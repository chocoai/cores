package com.lanen.view.action.arp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Disinfectantmaterial;
import com.lanen.service.arp.DisinfectantmaterialService;

@Controller
@Scope("prototype")
public class DisinfectantMaterialAction extends
		BaseAction<Disinfectantmaterial> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8001271124159867887L;
	@Resource
	private DisinfectantmaterialService disinfectantmaterialService;

	public void loadListByCode() {
		List<Disinfectantmaterial> list = new ArrayList<Disinfectantmaterial>();
		if (model.getDisinfectant_id() != null
				&& model.getDisinfectant_id() != 0) {
			list = disinfectantmaterialService.getListByDisinfectantId(model
					.getDisinfectant_id());
		} else {

		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", list.size());
		String jsonStr = JsonPluginsUtil.beanToJson(map, "yyyy-MM-dd");
		writeJson(jsonStr);
	}
}
