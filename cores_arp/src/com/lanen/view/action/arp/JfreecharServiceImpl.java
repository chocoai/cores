package com.lanen.view.action.arp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseLongDaoImpl;
import com.lanen.model.Treasury;

@Service
public class JfreecharServiceImpl extends BaseLongDaoImpl<Treasury> implements JfreecharService{

	public JFreeChart createBarChart() {
		CategoryDataset dataset = getDataSet2();
		JFreeChart chart = ChartFactory.createBarChart3D(
				"疾病发病图", // 图表标题
				"月份", // 目录轴的显示标签
				"疾病个数", // 数值轴的显示标签
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				true, 	// 是否显示图例(对于简单的柱状图必须是false)
				false, 	// 是否生成工具
				false 	// 是否生成URL链接
				);
		//PiePlot plot = (PiePlot) chart.getPlot();  
        //resetPiePlot(plot);
		CategoryPlot plot=chart.getCategoryPlot();
		//CategoryPlot plot=chart.getCategoryPlot();
	    //设置标题字体样式
	    chart.getTitle().setFont(new Font("黑体", Font.ITALIC,22));
	    //取得横轴和设置横轴样式
	    CategoryAxis categoryAxis = plot.getDomainAxis();
	    categoryAxis.setLabelFont(new Font("宋体", Font.BOLD, 22));
	    //横轴分类标签
	    categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);
	    categoryAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 22));
	    //设置说明字体
	    chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 18));
	    //取得纵轴和设置纵轴样式
	    NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();
	    numberAxis.setLabelFont(new Font("宋体", Font.BOLD, 22));
	     
	    //显示每个柱的数值，并修改该数值的字体属性   
	    BarRenderer3D renderer = new BarRenderer3D();    
	    renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());  
	    renderer.setBaseItemLabelsVisible(true);  
	    //默认的数字显示在柱子中，通过如下两句可调整数字的显示   
	    //注意：此句很关键，若无此句，那数字的显示会被覆盖，给人数字没有显示出来的问题 ，将数字显示在柱状图上面
	    renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(  
	            ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));  
	    renderer.setItemLabelAnchorOffset(10D);  
	     //设置每个地区所包含的平行柱的之间距离   
	     //renderer.setItemMargin(0.3);   
	    plot.setRenderer(renderer);  
		return chart;
	}

	private CategoryDataset getDataSet2() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		Map<String,Object> map=null;
		Date nows=new Date();
		int year=nows.getYear()+1900;
		for(int i=1;i<=12;i++){
			
			List<?> list=null;
			for (int j = 1; j <= 4; j++) {
				map=new HashMap<String,Object>();
				
				if (i == 2) {
					list = getTreasuryCount(year + "-02-01",year + "-02-29",j);
				}
				else if(10<=i&&i<=12){
					list = getTreasuryCount(year + "-"+i + "-01", year + "-"+ i + "-31",j);
				}else{
					list = getTreasuryCount(year + "-0" + i + "-01", year + "-0" + i + "-31",j);
				}
				BigInteger count = (BigInteger) list.get(0);
				if (count.intValue() > 0) {
					map.put("treasurySum", count);
				} else {
					map.put("treasurySum", 0);
				}
				map.put("treasuryDate", i);
				if (j==1) {
					dataset.addValue(count, "呼吸系统",i);
				}else if(j==2){
					dataset.addValue(count, "生殖系统",i);
				}else if(j==3){
					dataset.addValue(count, "消化系统",i);
				}else{
					dataset.addValue(count, "外伤",i);
				}
				
			}
		}
		
		return dataset;
	}
     
    
	
	private static void resetPiePlot(PiePlot plot) {
		String unitSytle = "{0}={1}({2})";
		
		plot.setNoDataMessage("无对应的数据，请重新查询。");
		plot.setNoDataMessagePaint(Color.red);
		
		//指定 section 轮廓线的厚度(OutlinePaint不能为null)
		plot.setOutlineStroke(new BasicStroke(0));
		
		//设置第一个 section 的开始位置，默认是12点钟方向
		plot.setStartAngle(90);			

		plot.setToolTipGenerator(new StandardPieToolTipGenerator(unitSytle,
				NumberFormat.getNumberInstance(),
				new DecimalFormat("0.00%")));
		
		//指定图片的透明度
		plot.setForegroundAlpha(0.65f);
		
		//引出标签显示样式
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator(unitSytle,
				NumberFormat.getNumberInstance(),
				new DecimalFormat("0.00%")));
			
		//图例显示样式
		plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator(unitSytle,
				NumberFormat.getNumberInstance(),
				new DecimalFormat("0.00%")));
	}
	
	public  List<?> getTreasuryCount(String start,String end ,int symptomssite){
		String sql="select count(id) from treasury where deleted!=1 and treasurydate between :start and :end and symptomssite=:symptomssite";
		Query query=getSession().createSQLQuery(sql);
		query.setParameter("start", start);
		query.setParameter("end", end);
		query.setParameter("symptomssite", symptomssite);
		return (List<?>)query.list();
	}

}

