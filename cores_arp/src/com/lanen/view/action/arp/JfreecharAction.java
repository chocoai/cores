package com.lanen.view.action.arp;

import javax.annotation.Resource;

import org.jfree.chart.JFreeChart;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lanen.base.BaseAction;
import com.lanen.model.Treasury;

@Controller
@Scope("prototype")
public class JfreecharAction extends BaseAction<Treasury>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6808821972934822510L;

	@Resource
	private JfreecharService jfreecharService;
	private JFreeChart chart;
	
	public String loadJfreechar(){
		chart=jfreecharService.createBarChart();
		return SUCCESS;
	}
	public JFreeChart getChart() {
		return chart;
	}
	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}
}
