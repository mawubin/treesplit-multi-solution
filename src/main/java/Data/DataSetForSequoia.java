package Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.Point;

import Rtree.Object;
import Rtree.Objects;
import util.Const;
import util.util;

public class DataSetForSequoia extends Objects {
	public DataSetForSequoia(String fileAddress) throws IOException
	{
		this.fileAddress=fileAddress;
		readData();
	}
	String fileAddress ="";
	//public ArrayList<Object> sequoiaobjects=new ArrayList<Object>();
	public void readData() throws IOException{
		ArrayList<Object> objs=this.randomobjects;
		 File filename = new File(fileAddress);
		   try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
			 BufferedReader br = new BufferedReader(reader);
			 String line = "";  
             line = br.readLine();  
             float maxx=0;
             float minx=0;
             float miny=0;
             float maxy=0;
             int numbofobject=0;
             while (line != null&&numbofobject<Const.numberOfSpatialObjects) {  
                 line = br.readLine(); // 一次读入一行数据  
                 if(line==null)
                 {break;}
                 Rtree.Object obj= new Object();
                 String[] str=line.split(":");
                 float x1,y1;
                 x1= Math.abs(Float.valueOf(str[0]));
                 y1= Math.abs(Float.valueOf(str[1]));  
                 maxx=Max(x1,maxx);
                 minx=Min(x1,minx);
                 maxy=Max(y1,maxy);
                 miny=Min(y1,miny);
                 numbofobject++;
            	 obj.setPoint(Geometries.point(x1,y1));
            	 obj.setText(str[2]);
            	 objs.add(obj);
             } 
       
      	   for(int i=0;i<objs.size();i++)
		   {	 //对数据进行标准化
			   float[] xy=util.handleSquData(objs.get(i).getPoint(), maxx, maxy, minx, miny);
			   Point poi=Geometries.point(xy[0],xy[1]);
			   objs.get(i).setPoint(poi);
		   }  
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 建立一个输入流对象reade
	
	
		   
		   
	}
	private float Max(float x,float y)
	{
		return x>y?x:y;
	}
	private float Min(float x,float y)
	{
		return x>y?y:x;
	}


	
}
