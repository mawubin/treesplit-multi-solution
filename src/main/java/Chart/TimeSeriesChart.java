package Chart;

import java.awt.Font;
import java.text.SimpleDateFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;

public class TimeSeriesChart {
	ChartPanel frame1;  
    public TimeSeriesChart(XYDataset xydataset){  
       
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("预测", "日期", "价格",xydataset, true, true, true);  
        XYPlot xyplot = (XYPlot) jfreechart.getPlot();  
        DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();  
        dateaxis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));  
        frame1=new ChartPanel(jfreechart,true);  
        dateaxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题  
        dateaxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //垂直标题  
        ValueAxis rangeAxis=xyplot.getRangeAxis();//获取柱状  
        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));  
        jfreechart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));  
        jfreechart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体  
  
    }   
//     private static XYDataset createDataset() {  //这个数据集有点多，但都不难理解  
//    	 	TimeSeries timeseries = new TimeSeries();
//            TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();  
//            timeseriescollection.addSeries(timeseries);  
//            timeseriescollection.addSeries(timeseries1);  
//            return timeseriescollection;  
//        }  
      public ChartPanel getChartPanel(){  
            return frame1;  
              
        }  
      
}
