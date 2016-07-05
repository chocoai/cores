package com.lanen.view.action.arp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.jsonAndModel.JsonPluginsUtil;
import com.lanen.model.Approval;
import com.lanen.model.Customer;
import com.lanen.model.Employee;
import com.lanen.model.Individual;
import com.lanen.model.Sale;
import com.lanen.model.Sale_Json;
import com.lanen.model.Salemonkey;
import com.lanen.service.arp.ApprovalService;
import com.lanen.service.arp.CustomerService;
import com.lanen.service.arp.EmployeeService;
import com.lanen.service.arp.IndividualService;
import com.lanen.service.arp.SaleService;
import com.lanen.service.arp.SalemonkeyService;
import com.lanen.util.DateUtil;

@Controller
@Scope("prototype")
public class SalemonkeyAction extends BaseAction<Salemonkey> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4429818445440615849L;
	@Resource
	private SalemonkeyService salemonkeyService;
	@Resource
	private SaleService saleService;
	@Resource
	private EmployeeService employeeService;
	@Resource
	private CustomerService customerService;
	@Resource
	private IndividualService individualService;

	private String rows;// 每页显示的记录数
	private String page;// 当前第几页
	private Date outdate;// 出场日期
	private String blongsale;// 所属订单的名字（编号）

	@Resource
	private ApprovalService approvalService;
	
	public String list() {
		return "list";
	}

	/**
	 * 加载所有的出场记录
	 */
	public void loadList() {
		Map<String, Object> map = salemonkeyService.getListByConditions(page,
				rows, model.getMonkeyid(), outdate, blongsale);
		String jsonStr = JsonPluginsUtil.beanToJson(map, "yyyy-MM-dd");
		writeJson(jsonStr);
	}

	/**
	 * 加载所有在确认选猴状态的订单--加载所有状态订单.
	 */
	public void loadSaleListBySelect() {
		Map<String,Object> listMap = saleService.getListBySelect1(page,rows);
		List<?> list=(List<?>) listMap.get("rows");
		List<Sale_Json> list2 = new ArrayList<Sale_Json>();
		if (list != null) {
			for (Object ob : list) {
				Object[]obj=(Object[])ob;
				Sale_Json json = new Sale_Json();
				json.setId(Long.parseLong(obj[0]+""));
				json.setTitle((String)obj[1]);
				String status = (String)obj[2];
				if (status.equals("1")) {
					json.setStatus("确认选猴");
				} else if (status.equals("3")) {
					json.setStatus("完成选猴");
				} else if (status.equals("5")) {
					json.setStatus("猴子出场");
				} else {//7
					json.setStatus("订单完成");
				}
				json.setTiaojian((String)obj[3]);
				json.setSalecount((String)obj[4]);
				String saletype = (String)obj[5];
				
				json.setSaletypeName(saletype);
				
				json.setSaleaddress((String)obj[7]);
				
				json.setBossName((String)obj[8]);
				list2.add(json);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list2);
		map.put("total", listMap.get("total"));
		String json = JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	
	/**
	 * 加载所有猴子已出场但是未在出场记录里面添加记录的订单--只显示订单扎状态为5的订单。
	 * 1，添加订单--订单状态1
	 * 3，添加猴子--订单状态3，动物状态2(待出场)
	 * 5，确认订单，猴子--订单状态5
	 * 7，订单完成--订单状态7，动物删除标识1
	 */
	public void loadSaleListBySale() {
		List<?> list = saleService.getListByAddSalemonkey();
		List<Sale_Json> list2 = new ArrayList<Sale_Json>();
		if (list != null) {
			for (Object obj : list) {
				Sale_Json json = new Sale_Json();
				Object[] objs = (Object[]) obj;
				Integer id = (Integer) objs[0];
				json.setId(Long.parseLong(id + ""));
				json.setTitle((String) objs[1]);
				String status = (String) objs[2];
				if (status.equals("1")) {
					json.setStatus("确认选猴");
				} else if (status.equals("3")) {
					json.setStatus("完成选猴");
				} else if (status.equals("5")) {
					json.setStatus("猴子出场");
				} else {
					json.setStatus("订单完成");
				}
				json.setOutdate((Date) objs[5]);
				json.setTiaojian((String) objs[3]);
				String saleType = (String) objs[7];
				json.setSaletypeName(saleType);
				json.setSalecount((String) objs[4]);
				
				json.setSaleaddress((String)objs[8]);
					json.setBossName((String)objs[9]);
				
				
				json.setMonkeylist((String) objs[6]);
				list2.add(json);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list2);
		// map.put("total", list.size());
		String jsonStr = JsonPluginsUtil.beanToJson(map, "yyyy-MM-dd");
		writeJson(jsonStr);
	}

	/**
	 * 按找订单的猴子列表批量添加出场记录，并更新猴子个体表
	 */
	public void addSaleMonkey() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getSale_id() != null) {
			Sale sale = saleService.getById(model.getSale_id());
			String monkeylist = sale.getMonkeylist();
			String[] monkeyArray = monkeylist.split(",");
			for (int i = 0; i < monkeyArray.length; i++) {
				Salemonkey salemonkey = new Salemonkey();
				salemonkey.setSale_id(model.getSale_id());
				salemonkey.setMonkeyid(monkeyArray[i]);
				salemonkeyService.save(salemonkey);
				Individual individual = individualService.getByMonkeyidAndDeleted(monkeyArray[i]);
				if(!"".equals(individual)&&individual!=null){
				//出场确认完成时将动物status置为2带销售，订单完成时将动物deleted置为1.
				individual.setDeleted(1);
				individual.setRemark(individual.getRemark() + "已出场");
				individualService.update(individual);}
				//进行出场录入记录的。订单完成
				sale.setStatus("7");
				sale.setOutdate(new Date());
				saleService.update(sale);
			}
			map.put("success", true);
			map.put("msg", "出场记录添加成功");
		}
		String jsonStr = JsonPluginsUtil.beanToJson(map);
		writeJson(jsonStr);
	}

	/**
	 * 根据员工ID获得员工姓名
	 * 
	 * @param id
	 * @return
	 */
	public String getEmpName(Long id) {
		String name = null;
		if (id != null) {
			Employee e = employeeService.getById(id);
			if (e != null) {
				name = e.getName();
			}
		}
		return name;
	}

	/**
	 * 运送地信息。
	 * @return
	 */
	public void loadListAddress(){
		List<Customer> list=(List<Customer>)saleService.getListAddress();
		String strJson=JsonPluginsUtil.beanListToJson(list);
		writeJson(strJson);
	}
	/**
	 * shanchu运送地信息.
	 * @return
	 */
	public void delAddress(){
		Map<String,Object> map=new HashMap<String,Object>();
		if(!"".equals(model.getId())){
			Customer c=saleService.getAddressById(model.getId());
			c.setDeleted(1);
			customerService.update(c);
			
			map.put("success", true);
			map.put("msg", "删除成功");
		}
		String strJson=JsonPluginsUtil.beanToJson(map);
		writeJson(strJson);
		
	}
	/**
	 * 运送地编辑
	 * @return
	 */
	public void toEditAddress(){
		if(!"".equals(model.getId())){
			Customer c=saleService.getAddressById(model.getId());
			String json=JsonPluginsUtil.beanToJson(c);
			writeJson(json);
		}
	}
	public void editSaveAddress(){
		Map<String,Object> map=new HashMap<String,Object>();
		String id=request.getParameter("customerId");
		if(!"".equals(id)&&id!=null){
			Customer c=saleService.getAddressById(Long.parseLong(id));
			
			if (!"".equals(request.getParameter("name"))) {
				c.setName(request.getParameter("name"));
			}
			c.setFax(request.getParameter("fax"));
			c.setTelephone(request.getParameter("telephone"));
			c.setAddress(request.getParameter("address"));
			c.setRemark(request.getParameter("remark"));
			customerService.update(c);
			map.put("success", true);
			map.put("msg", "编辑成功");
			map.put("id", c.getId());
		}
		String str=JsonPluginsUtil.beanToJson(map);
		writeJson(str);
	}
	/**
	 * tianjia运送地.
	 * @return
	 */
	public void addAddress(){
		Map<String,Object> map=new HashMap<String,Object>();
		Customer c=new Customer();
		String name=request.getParameter("name");
		String fax=request.getParameter("fax");
		String telephone=request.getParameter("telephone");
		String address=request.getParameter("address");
		String remark=request.getParameter("remark");
		c.setName(name);
		c.setFax(fax);
		c.setTelephone(telephone);
		c.setAddress(address);
		c.setRemark(remark);
		c.setDeleted(0);
		customerService.save(c);
		map.put("success", true);
		map.put("msg", "添加成功");
		map.put("id", c.getId());
		String json=JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	
	/**
	 * 许可证
	 * @return
	 */
	public void loadListApproval(){
		Map<String,Object> map=approvalService.getAllApprovalInfo(page,rows);
		String json=JsonPluginsUtil.beanToJson(map,"yyyy-MM-dd");
		writeJson(json);
	}
	public void addApproval(){
		Map<String ,Object> map=new HashMap<String,Object>();
		Approval approval=new Approval();
		approval.setTitle(request.getParameter("title"));
		approval.setPhao(request.getParameter("phao"));
		approval.setHead(request.getParameter("head"));
		approval.setContent(request.getParameter("content"));
		String approvaldate=request.getParameter("approvaldate");
		if(approvaldate!=null&&!"".equals(approvaldate)){
			approval.setApprovaldate(DateUtil.stringToDate(approvaldate, "yyyy-MM-dd"));
		}
		approval.setDeleted(0);
		approval.setCreatetime(new Date());
		approvalService.save(approval);
		map.put("id", approval.getId());
		map.put("success", true);
		map.put("msg", "添加成功");
		String json=JsonPluginsUtil.beanToJson(map);
		writeJson(json);
		
	}
	public void toEditApproval(){
		
		Long approvalId=model.getId();
		if(!"".equals(approvalId)&&approvalId!=null){
			Approval approval=approvalService.getById(approvalId);
			String json=JsonPluginsUtil.beanToJson(approval);
			writeJson(json);
		}
	}
	public void editSaveApproval(){
		Map<String,Object> map=new HashMap<String,Object>();
		String approvalId=request.getParameter("approvalId");
		if(!"".equals(approvalId)&&approvalId!=null){
			Approval approval=approvalService.getById(Long.parseLong(approvalId));
			approval.setTitle(request.getParameter("title"));
			approval.setHead(request.getParameter("head"));
			approval.setPhao(request.getParameter("phao"));
			approval.setContent(request.getParameter("content"));
			String approvaldate=request.getParameter("approvaldate");
			if(approvaldate!=null&&!"".equals(approvaldate)){
				approval.setApprovaldate(DateUtil.stringToDate(approvaldate, "yyyy-MM-dd"));
			}
			approvalService.update(approval);
			map.put("id", approval.getId());
			map.put("success", true);
			map.put("msg", "编辑成功");
			String json=JsonPluginsUtil.beanToJson(map);
			writeJson(json);
		}
	}
	public void delApproval(){
		Map<String,Object> map=new HashMap<String,Object>();
		if(!"".equals(model.getId())){
			Approval approval=approvalService.getById(model.getId());
			approval.setDeleted(1);
			approvalService.update(approval);
			map.put("success", true);
			map.put("msg", "删除成功");
		}
		String json=JsonPluginsUtil.beanToJson(map);
		writeJson(json);
	}
	/**
	 * 许可批号
	 * @return
	 */
	public void loadListApprovalMap(){
		List<Map<String,Object>> list=approvalService.getApprovalMap();
		String json=JsonPluginsUtil.beanListToJson(list);
		writeJson(json);
	}
	public String selectmonkey() {
		return "selectmonkey";
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

	public Date getOutdate() {
		return outdate;
	}

	public void setOutdate(Date outdate) {
		this.outdate = outdate;
	}

	public String getBlongsale() {
		return blongsale;
	}

	public void setBlongsale(String blongsale) {
		this.blongsale = blongsale;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
