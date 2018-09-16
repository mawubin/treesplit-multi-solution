package MBRSegProblem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.uma.jmetal.problem.ConstrainedProblem;
import org.uma.jmetal.problem.impl.AbstractIntegerProblem;
import org.uma.jmetal.solution.IntegerSolution;
import org.uma.jmetal.util.JMetalLogger;
import org.uma.jmetal.util.solutionattribute.impl.NumberOfViolatedConstraints;
import org.uma.jmetal.util.solutionattribute.impl.OverallConstraintViolation;

import Chart.Paint;
import Rtree.Objects;
import Rtree.RectangleMBR;
import util.Const;



/** Class representing problem ConstrainedSegProblem */
@SuppressWarnings("serial")
public class ConstrainedSegProblem extends AbstractIntegerProblem implements ConstrainedProblem<IntegerSolution> {

  public OverallConstraintViolation<IntegerSolution> overallConstraintViolationDegree ;
  public NumberOfViolatedConstraints<IntegerSolution> numberOfViolatedConstraints ;
  /**
   * Constructor
   * Creates a default instance of the ConstrainedSegProblem problem
   */
	public int count=0;
	public Objects ranbojs;

  XYSeries mSeriestotalCross = new XYSeries("totalCross");
  XYSeries mSeriestotalSqure = new XYSeries("totalSqure");
  XYSeries mSeriestotalSimil = new XYSeries("totalSimil");
  XYSeries mSeriestotalVarian = new XYSeries("totalVarian");
  public ConstrainedSegProblem(int numberOfVariables,Objects ranobjs) {
	  setNumberOfVariables(numberOfVariables);
		setNumberOfObjectives(3);
		setNumberOfConstraints(Const.numberOfClass+1);
	    setName("segProblem");
	    this.ranbojs=ranobjs;
	    List<Integer> lowerLimit = new ArrayList<Integer>(getNumberOfVariables()) ;
	    List<Integer> upperLimit = new ArrayList<Integer>(getNumberOfVariables()) ;
		
	    for (int i = 0; i < getNumberOfVariables(); i++) {
	        lowerLimit.add(0);
	        upperLimit.add(Const.numberOfClass-1);
	       // upperLimit.add(100);
	      }
	    setLowerLimit(lowerLimit);
	    setUpperLimit(upperLimit);

    overallConstraintViolationDegree = new OverallConstraintViolation<IntegerSolution>() ;
    numberOfViolatedConstraints = new NumberOfViolatedConstraints<IntegerSolution>() ;
  }



/** Evaluate() method */
  public void evaluate(IntegerSolution solution) {
	  double[] f = new double[getNumberOfVariables()];
		 int numberofvara=getNumberOfVariables();
		 //构建jsonmap存储每一个类别的对象	 
		 HashMap<Integer, RectangleMBR> jsomap = segProblem.getjsonmap(numberofvara,solution,this.ranbojs);
		 int classNumber= jsomap.size();
		 float totalCross=0;
		 float totalSqure=0;
		 float totalSimil=0;
		 for(int i=0;i<classNumber;i++)
		 {
			 for(int j=i+1;j<classNumber;j++)
			 {
				 if((RectangleMBR)jsomap.get(i)!=null&&(RectangleMBR)jsomap.get(j)!=null)
				 {
				 totalCross=totalCross+RectangleMBR.crossSqureRect( (RectangleMBR)jsomap.get(i), (RectangleMBR) jsomap.get(j)); 
				 totalSqure=totalSqure+ RectangleMBR.totalSqureRect( (RectangleMBR)jsomap.get(i), (RectangleMBR) jsomap.get(j));
				 if(totalSqure==0)
				 {
					 System.out.println("totalCross is zero");
				 }
				 totalSimil=totalSimil+RectangleMBR.totalSimil((RectangleMBR)jsomap.get(i), (RectangleMBR) jsomap.get(j));
				 }
			}
		 }
		 if(totalSqure==0)
		 {
			 System.out.println("totalCross is zero");
		 }
		f[0]=totalCross;
		f[1]=totalSqure;
		f[2]=totalSimil;
		solution.setObjective(0, f[0]);
		solution.setObjective(1, f[1]);
		solution.setObjective(2, f[2]);
	    count++;
	    if(count/100==0){
	    	mSeriestotalCross.add(count, totalCross);
	    	mSeriestotalCross.add(count, totalSqure);
	    	mSeriestotalSimil.add(count,totalSimil);
	    }
		 JMetalLogger.logger.info("Total totalCross: " + totalCross);
		 JMetalLogger.logger.info("Total tatalsqure: " + totalSqure);
		 JMetalLogger.logger.info("Total classNumber: " + classNumber);
  }

  /** EvaluateConstraints() method */
  public void evaluateConstraints(IntegerSolution solution)  {
    double[] constraint = new double[this.getNumberOfConstraints()];
	 int numberofvara=getNumberOfVariables();
	 HashMap<Integer, RectangleMBR> jsomap = segProblem.getjsonmap(numberofvara,solution,this.ranbojs);	 
     constraint[0] =jsomap.size()- Const.numberOfClass;
    for(int i=1;i<Const.numberOfClass+1;i++){
    	int number=(jsomap.get(i-1)==null)?0:jsomap.get(i-1).arrRects.size();
    	constraint[i]=number-Const.minNumberofEachClass;	
    }
    double overallConstraintViolation = 0.0;
    int violatedConstraints = 0;
    for (int i = 0; i < this.getNumberOfConstraints(); i++) {
      if (constraint[i] < 0.0) {
        overallConstraintViolation += constraint[i];
        violatedConstraints++;
      }
    }
	JMetalLogger.logger.info("Total violatedConstraints: " + violatedConstraints);
	JMetalLogger.logger.info("Total violatedConstraints: " + violatedConstraints);
    overallConstraintViolationDegree.setAttribute(solution, overallConstraintViolation);
    numberOfViolatedConstraints.setAttribute(solution, violatedConstraints);
  }
	public void paint()
	{
	    XYSeriesCollection mCollection = new XYSeriesCollection();
	    XYSeriesCollection mCollectiontotalVarian = new XYSeriesCollection();
	    
			//JFrame frame=new JFrame("Java数据统计�?");  
		   // frame.setLayout(new GridLayout(2,2,10,10));  
		    mCollection.addSeries(mSeriestotalCross);
		    mCollection.addSeries(mSeriestotalSqure);
		    mCollectiontotalVarian.addSeries(mSeriestotalSimil);
		 //   mCollection.addSeries(mSeriestotalCross);
		    Paint.paintXY(mCollection);
		    Paint.paintXY(mCollectiontotalVarian);
		    
	}

}


