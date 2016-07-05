package com.lanen.view.action.arp;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Individual;
import com.lanen.model.Leavebreast;
import com.lanen.service.arp.IndividualService;
import com.lanen.service.arp.WeaningService;
import com.lanen.util.DateUtil;

@Controller
@Scope("prototype")
public class WeaningAction extends BaseAction<Leavebreast> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -899736196629903425L;
	@Resource
	private WeaningService weaningService;
	@Resource
	private IndividualService individualService;
	private String rows;
	private String page;
	private Date startweaningdate;
	private Date endweaningdate;

	public String list() {
		return "weaningList";
	}

	public void loadList() {
		Map<String, Object> map = weaningService.getChildMonkey(page, rows,
				model.getMonkeyid(),
				DateUtil.dateToString(startweaningdate, "yyyy-MM-dd"),
				DateUtil.dateToString(endweaningdate, "yyyy-MM-dd"));
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}

	public void add() {
		Map<String, Object> map = new HashMap<String, Object>();
		Leavebreast l = new Leavebreast();
		if (model.getMonkeyid() != null && !"".equals(model.getMonkeyid())) {
			l.setMonkeyid(model.getMonkeyid());
			l.setLeavebreastdate(model.getLeavebreastdate());
			l.setLeavebreastweight(model.getLeavebreastweight());
			l.setMotherid(model.getMotherid());
			l.setKeeper(model.getKeeper());
			l.setOperater(model.getOperater());
			l.setRecorder(model.getRecorder());
			l.setRemark(model.getRemark());
			l.setCreatetime(new Date());
			l.setDeleted(0);
			
			weaningService.save(l);
			if(model.getLeavebreastdate()!=null&&!"".equals(model.getLeavebreastdate())){
				Individual i=(Individual)weaningService.getIndividualByMonkeyid(model.getMonkeyid());
				i.setLeavebreastdate(model.getLeavebreastdate());
				i.setLeavebreastweight((Double.valueOf(model.getLeavebreastweight()+"")));
				individualService.update(i);
			}
			map.put("success", true);
			map.put("msg", "添加成功");
			map.put("id", l.getId());//选中原数据.
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void delWeaning() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null) {
			Leavebreast i = weaningService.getById(model.getId());
			String monkeyid = i.getMonkeyid();
			i.setDeleted(1);
			weaningService.update(i);
			
			Individual in=individualService.getByMonkeyid(model.getMonkeyid());
			in.setLeavebreastdate(null);
			in.setLeavebreastweight(null);
			individualService.update(in);
			
			map.put("success", true);
			map.put("msg", "删除成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public void toEdit() {
		if (model.getId() != null) {
			Leavebreast d = weaningService.getById(model.getId());
			String jsonStr = JsonPluginsUtil.beanToJson(d, "yyyy-MM-dd");
			writeJson(jsonStr);
		}
	}

	public void editSave() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null && model.getMonkeyid() != null
				&& !"".equals(model.getMonkeyid())) {
			Leavebreast l = weaningService.getById(model.getId());
			l.setMonkeyid(model.getMonkeyid());
			l.setLeavebreastdate(model.getLeavebreastdate());
			l.setLeavebreastweight(model.getLeavebreastweight());
			l.setMotherid(model.getMotherid());
			l.setKeeper(model.getKeeper());
			l.setRecorder(model.getRecorder());
			l.setOperater(model.getOperater());
			l.setRemark(model.getRemark());
			l.setLastmodifytime(new Date());
			weaningService.update(l);
			
			Individual individual=individualService.getByMonkeyid(model.getMonkeyid());
			individual.setLeavebreastdate(model.getLeavebreastdate());
			individual.setLeavebreastweight(Double.valueOf(""+model.getLeavebreastweight()));
			individualService.update(individual);
			map.put("success", true);
			map.put("msg", "编辑成功");
			map.put("id", l.getId());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Date getStartweaningdate() {
		return startweaningdate;
	}

	public void setStartweaningdate(Date startweaningdate) {
		this.startweaningdate = startweaningdate;
	}

	public Date getEndweaningdate() {
		return endweaningdate;
	}

	public void setEndweaningdate(Date endweaningdate) {
		this.endweaningdate = endweaningdate;
	}
}
