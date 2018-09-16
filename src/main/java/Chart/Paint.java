package Chart;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.uma.jmetal.solution.IntegerSolution;
import org.uma.jmetal.solution.Solution;

import com.github.davidmoten.rtree.geometry.Point;
import com.github.davidmoten.rtree.geometry.Rectangle;

import MBRSegProblem.segProblem;
import Rtree.Objects;
import Rtree.RandomObjects;
import Rtree.RectangleMBR;
import util.Const;

public class Paint {

	//ֻ����ͼ
	public static void paintObjects(Objects objs){
		MyFrame frame = new MyFrame();
		frame.setListpoint(objs.randomobjects);
		//frame.setRect(rect);
		frame.setPanel();
		frame.setVisible(true);
	}
	//����Ϳ�ͼ
	public static void paintObjectsAndRect(Objects objs,ArrayList<Rectangle> rect){	
		MyFrame frame = new MyFrame();
		frame.setListpoint(objs.randomobjects);
		frame.setRect(rect);
		//frame.setRect(rect);
		frame.setPanel();
		frame.setVisible(true);
	}

	//��������ͼ
	public static void paintResult(List<IntegerSolution> population, Objects ranObjects){
//		MyFrame frame = new MyFrame();
//	//	frame.setVisible(true);
//		frame.setListpoint(ranObjects.randomobjects);
		for(int i=0;i<population.size();i++)
		{
			ArrayList<Rectangle> rect=new ArrayList<Rectangle>();
			int var= population.get(i).getNumberOfVariables();
			HashMap<Integer, RectangleMBR> jsomap = segProblem.getjsonmap(var,population.get(i),ranObjects);
			if(jsomap.size()==Const.numberOfClass){
			for(int j=0;j<jsomap.size();j++){
				rect.add(jsomap.get(j));		
			}
			paintObjectsAndRect(ranObjects,rect);
			}
		}

		
	}
	
	//������ͼ
	public static void paintXY( XYSeriesCollection mCollection ){
  	  StandardChartTheme mChartTheme = new StandardChartTheme("CN");
  	    mChartTheme.setLargeFont(new Font("����", Font.BOLD, 20));
  	    mChartTheme.setExtraLargeFont(new Font("����", Font.PLAIN, 15));
  	    mChartTheme.setRegularFont(new Font("����", Font.PLAIN, 15));
  	    ChartFactory.setChartTheme(mChartTheme);		
  	    JFreeChart mChart = ChartFactory.createScatterPlot(
  	        "����ͼ",
  	        "X",
  	        "Y",				
  	        mCollection,
  	        PlotOrientation.VERTICAL,
  	        true, 
  	        true, 
  	        false);
  	    ChartFrame mChartFrame = new ChartFrame("����ͼ", mChart);
  	    mChartFrame.pack();
  	    mChartFrame.setVisible(true);
    }

	public static void paintResult(List<? extends Solution<?>> solutionList) {
		XYSeriesCollection mCollection = new XYSeriesCollection();
		XYSeries xyseries = new XYSeries("result");
		if (solutionList.size() > 0) {
			for (int i = 0; i < solutionList.size(); i++) {
				xyseries.add(solutionList.get(i).getObjective(0), solutionList.get(i).getObjective(1));
			}
		}
		mCollection.addSeries(xyseries);
		paintXY(mCollection);
	}

}






   