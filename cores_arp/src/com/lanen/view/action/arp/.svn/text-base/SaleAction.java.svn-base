package com.lanen.view.action.arp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Individual;
import com.lanen.model.Sale;
import com.lanen.service.arp.CustomerService;
import com.lanen.service.arp.IndividualService;
import com.lanen.service.arp.SaleService;

@Controller
@Scope("prototype")
public class SaleAction extends BaseAction<Sale> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5414620358111509040L;
	@Resource
	private SaleService saleService;
	@Resource
	private IndividualService individualService;
	@Resource
	private CustomerService customerService;

	private String selectmonkeyid;
	/**
	 * 确认选猴
	 */
	public void addSelectMonkey() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null && model.getId() != 0) {
			Sale sale = saleService.getById(model.getId());
			if (sale != null) {
				String monkeylist = model.getMonkeylist();
				sale.setMonkeylist(monkeylist);
				//完成选猴
				sale.setStatus("3");
				String[] monkeyArray = monkeylist.split(",");
				for (int i = 0; i < monkeyArray.length; i++) {
					Individual individual = individualService
							.getByMonkeyid(monkeyArray[i]);
					individual.setStatus(2);//完成选候并将动物状态置为2：待销售状态。
					individualService.update(individual);
				}
				sale.setSalecount(monkeyArray.length+"");
				saleService.update(sale);

				map.put("success", true);
				map.put("msg", "选择成功");
				map.put("id", sale.getId());
			}
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	/**
	 * 订单添加
	 */
	public void addOrder() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getTitle() != null && !"".equals(model.getTitle())) {
			Sale sale = new Sale();
			
				sale.setId(model.getId());
				sale.setTitle(model.getTitle());
				sale.setTiaojian(model.getTiaojian());
				sale.setBoss(model.getBoss());
				sale.setCarriagenumber(model.getCarriagenumber());
				sale.setCites(model.getCites());
				sale.setDeleted(0);
				sale.setMonkeytype(model.getMonkeytype());
				sale.setSaleaddress_id(model.getSaleaddress_id());
				sale.setSaledate(model.getSaledate());
				sale.setSaletype(model.getSaletype());
				
				sale.setApproveserial(model.getApproveserial());//销售许可
				sale.setTrance(model.getTrance());//运输许可
				//设置订单状态.确认选猴
				sale.setStatus("1");

				saleService.save(sale);
				map.put("success", true);
				map.put("msg", "选择成功");
				map.put("id", sale.getId());
			
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	public void delOrder() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null) {
			Sale i = saleService.getById(model.getId());
			Long saleid = i.getId();
			i.setDeleted(1);
			saleService.update(i);
			map.put("success", true);
			map.put("msg", "删除成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	/**
	 * 编辑的是新建订单编辑。1
	 */
	public void toEditOrder() {
		if (model.getId() != null) {
			Sale d = saleService.getById(model.getId());
			String jsonStr = JsonPluginsUtil.beanToJson(d, "yyyy-MM-dd");
			writeJson(jsonStr);
		}
	}
	/**
	 * 编辑后保存.
	 */
	public void editSaveOrder() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null && model.getTitle() != null
				&& !"".equals(model.getTitle())) {
			Sale s = saleService.getById(model.getId());
			s.setMonkeytype(model.getMonkeytype());
			s.setSaleaddress_id(model.getSaleaddress_id());
			s.setSaledate(model.getSaledate());
			s.setBoss(model.getBoss());
			s.setSaletype(model.getSaletype());
			s.setCites(model.getCites());
			s.setCarriagenumber(model.getCarriagenumber());
			s.setTiaojian(model.getTiaojian());
			s.setRemark(model.getRemark());
			s.setApproveserial(model.getApproveserial());
			s.setTrance(model.getTrance());
			s.setTitle(model.getTitle());
			saleService.update(s);
			map.put("success", true);
			map.put("msg", "编辑成功");
			map.put("id", s.getId());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	/**
	 * 检查订单编号
	 */
	public void checkTitleHave(){
		if (null != model.getTitle() && !"".equals(model.getTitle())) {
			boolean isExist = saleService.isExistTitle(model
					.getTitle());
			if (!isExist) {
				writeJson("false");
			} else {
				writeJson("true");
			}
		} else {
			writeJson("false");
		}
	}
	/**
	 * 检查被选猴子是否出场.
	 */
	public void checkOutMonkeyHave(){
		if (null != selectmonkeyid && !"".equals(selectmonkeyid)) {
			boolean isExist = saleService.isExistOutMonkey(selectmonkeyid);
			if (isExist) {
				writeJson("true");
			} else {
				writeJson("false");
			}
		} else {
			writeJson("false");
		}
	}
	/**
	 * 联系人地址列表
	 */
	public void loadListAddress() {
		List<Map<String, String>> mapList = customerService
				.getAddressMap();
		String jsonStr = JsonPluginsUtil.beanListToJson(mapList);
		writeJson(jsonStr);
	}
	/**
	 * 销售类型
	 */
	public void loadListSaleType(){
		List<Map<String,String>> mapList=saleService.getSaleTypeMap();
		String jsonStr=JsonPluginsUtil.beanListToJson(mapList);
		writeJson(jsonStr);
	}
	/**
	 * 出场订单确认
	 */
	public void toSubmitOrder(){
		if(!"".equals(model.getId())&&model.getId()!=null){
			Sale d = saleService.getById(model.getId());
			String jsonStr = JsonPluginsUtil.beanToJson(d, "yyyy-MM-dd");
			writeJson(jsonStr);
		}
	}
	/**
	 * 确认出场//猴子出场
			s.setStatus("5");
	 * @return
	 */
	public void saveSubmitOrder(){
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getId() != null&&!"".equals(model.getId())) {
			Sale s = saleService.getById(model.getId());
			//禁止编辑.
			/*s.setMonkeytype(model.getMonkeytype());
			s.setSaleaddress_id(model.getSaleaddress_id());
			s.setSaledate(model.getSaledate());
			s.setBoss(model.getBoss());
			s.setSaletype(model.getSaletype());
			s.setCites(model.getCites());
			s.setCarriagenumber(model.getCarriagenumber());
			s.setTiaojian(model.getTiaojian());
			s.setRemark(model.getRemark());*/
			//猴子出场
			s.setStatus("5");
			saleService.update(s);
			map.put("success", true);
			map.put("msg", "编辑成功");
			map.put("id", s.getId());
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}
	
	public String getSelectmonkeyid() {
		return selectmonkeyid;
	}
	public void setSelectmonkeyid(String selectmonkeyid) {
		this.selectmonkeyid = selectmonkeyid;
	}
}
