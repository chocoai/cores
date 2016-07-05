package com.lanen.view.action.arp;

import org.jfree.chart.JFreeChart;

import com.lanen.base.BaseLongDao;
import com.lanen.model.Treasury;

public interface JfreecharService extends BaseLongDao<Treasury>{

	JFreeChart createBarChart();
}
