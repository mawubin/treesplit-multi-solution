package Rtree;

import java.util.ArrayList;

public abstract class Objects {
	public ArrayList<Object> randomobjects=new ArrayList<Object>();
	public Object getObject(int key)
	{
		Object newobject= new Object();
		newobject=this.randomobjects.get(key);
		return newobject;
	}
	public RectangleMBR getRectMBR(){
		RectangleMBR mbr= new RectangleMBR(0, 0, 0, 0);
		for(int i=0;i<randomobjects.size();i++)
		{
			mbr.accumulateRect(mbr, randomobjects.get(i).getPoint());
		}
		return mbr;
	}
}
