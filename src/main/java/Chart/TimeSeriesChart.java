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
       
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("Ԥ��", "����", "�۸�",xydataset, true, true, true);  
        XYPlot xyplot = (XYPlot) jfreechart.getPlot();  
        DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();  
        dateaxis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));  
        frame1=new ChartPanel(jfreechart,true);  
        dateaxis.setLabelFont(new Font("����",Font.BOLD,14));         //ˮƽ�ײ�����  
        dateaxis.setTickLabelFont(new Font("����",Font.BOLD,12));  //��ֱ����  
        ValueAxis rangeAxis=xyplot.getRangeAxis();//��ȡ��״  
        rangeAxis.setLabelFont(new Font("����",Font.BOLD,15));  
        jfreechart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));  
        jfreechart.getTitle().setFont(new Font("����",Font.BOLD,20));//���ñ�������  
  
    }   
//     private static XYDataset createDataset() {  //������ݼ��е�࣬�����������  
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
