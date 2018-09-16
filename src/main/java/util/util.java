package util;

import java.util.List;
import java.util.Random;

import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.JMetalLogger;
import org.uma.jmetal.util.fileoutput.SolutionListOutput;
import org.uma.jmetal.util.fileoutput.impl.DefaultFileOutputContext;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;

import com.github.davidmoten.rtree.geometry.Point;

import Data.DefaultOutPutDataMy;
import Data.OutPutDataMy;

public class util {
	  /**
	   * Write the population into two files and prints some data on screen
	   * @param population
	   */
	  public static void printFinalSolutionSetToObjectives(List<? extends Solution<?>> population) {

	    new OutPutDataMy(population)
	        .setSeparator("\t")
	        .setVarFileOutputContext(new DefaultOutPutDataMy("VAR.tsv"))
	        .setFunFileOutputContext(new DefaultOutPutDataMy("FUN.tsv"))
	        .print();

	    JMetalLogger.logger.info("Random seed: " + JMetalRandom.getInstance().getSeed());
	    JMetalLogger.logger.info("Objectives values have been written to file FUN.tsv");
	    JMetalLogger.logger.info("Variables values have been written to file VAR.tsv");
	  }
	
	public static String getRandomString(int length) { //length表示生成字符串的长度  
	    String base = "abcdefghijklmnopqrstuvwxyz";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	 } 
	public static float[] handleSquData(Point point,float maxx,float maxy,float minx,float miny)
	{
		
		float[] xyReturn= new float[2];
		xyReturn[0]=Const.pictureSize*(point.x1()-minx)/(maxx-minx);
		xyReturn[1]=Const.pictureSize*(point.y1()-miny)/(maxy-miny);
		return xyReturn;
	}
	public static boolean judgeList(List<?> a,List<?> b){
		if(a.size()!=b.size())
			return false;
		for(int i=0;i<a.size();i++)
		{
			if(a.get(i)!=b.get(i))
			{
				return false;
			}
			
		}
		return true;
	}
}
