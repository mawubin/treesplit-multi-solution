package jmetelmain;
//ZDT1.java
//

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.JMetalLogger;

import Chart.Paint;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

/** Class representing problem ZDT1 */
@SuppressWarnings("serial")
public class zdttest extends AbstractDoubleProblem {
	public int count=0;
    XYSeriesCollection mCollection = new XYSeriesCollection();
    XYSeries mSeriestotalCross = new XYSeries("totalCross");
    XYSeries mSeriestotalSqure = new XYSeries("totalSqure");
/** Constructor. Creates default instance of problem ZDT1 (30 decision variables) */
public zdttest() {
this(30);
}

/**
* Creates a new instance of problem ZDT1.
*
* @param numberOfVariables Number of variables.
*/
public zdttest(Integer numberOfVariables) {
setNumberOfVariables(numberOfVariables);
setNumberOfObjectives(2);
setName("ZDT1");


List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables()) ;
List<Double> upperLimit = new ArrayList<>(getNumberOfVariables()) ;

for (int i = 0; i < getNumberOfVariables(); i++) {
  lowerLimit.add(0.0);
  upperLimit.add(1.0);
}

setLowerLimit(lowerLimit);
setUpperLimit(upperLimit);
}

/** Evaluate() method */
public void evaluate(DoubleSolution solution) {
double[] f = new double[getNumberOfObjectives()];

f[0] = solution.getVariableValue(0);
double g = this.evalG(solution);
double h = this.evalH(f[0], g);
f[1] = h * g;

solution.setObjective(0, f[0]);
solution.setObjective(1, f[1]);
mSeriestotalCross.add(count++, f[0]);
mSeriestotalSqure.add(count++, f[1]);
JMetalLogger.logger.info("Total totalCross: " + f[0]);
JMetalLogger.logger.info("Total tatalsqure: " + f[1]);

}

/**
* Returns the value of the ZDT1 function G.
*
* @param solution Solution
*/
private double evalG(DoubleSolution solution) {
double g = 0.0;
for (int i = 1; i < solution.getNumberOfVariables(); i++) {
  g += solution.getVariableValue(i);
}
double constant = 9.0 / (solution.getNumberOfVariables() - 1);
g = constant * g;
g = g + 1.0;
return g;
}

/**
* Returns the value of the ZDT1 function H.
*
* @param f First argument of the function H.
* @param g Second argument of the function H.
*/
public double evalH(double f, double g) {
double h ;
h = 1.0 - Math.sqrt(f / g);
return h;
}

public void paint()
{
		JFrame frame=new JFrame("Java数据统计图");  
	    frame.setLayout(new GridLayout(2,2,10,10));  
	    mCollection.addSeries(mSeriestotalCross);
	    mCollection.addSeries(mSeriestotalSqure);
	 //   mCollection.addSeries(mSeriestotalCross);
	    Paint.paintXY(mCollection);
	
}

}

