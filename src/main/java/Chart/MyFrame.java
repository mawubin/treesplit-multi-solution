package Chart;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.github.davidmoten.rtree.geometry.Point;
import com.github.davidmoten.rtree.geometry.Rectangle;

import Rtree.Object;

/**
 * ����
 */
public class MyFrame extends JFrame {

    public static final String TITLE = "Javaͼ�λ���";

    public static final int WIDTH = 1300;
    public static final int HEIGHT = 800;
    public ArrayList<Object> listpoint=null;
    public ArrayList<Rectangle> rect=null;
    public MyFrame() {
        super();
        initFrame();
    }
    public void setListpoint(ArrayList<Object> listpoint){
    	this.listpoint=listpoint;
    }
    public void setRect(ArrayList<Rectangle> rec)
    {
    	this.rect=rec;
    }
    public void setPanel(){
    	MyPanel panel = new MyPanel(this);
    	panel.setListpoint(listpoint);
    	panel.setRects(rect);
        setContentPane(panel);
    }

    private void initFrame() {
        // ���� ���ڱ��� �� ���ڴ�С
        setTitle(TITLE);
        setSize(WIDTH, HEIGHT);

        // ���ô��ڹرհ�ť��Ĭ�ϲ���(����ر�ʱ�˳�����)
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // �Ѵ���λ�����õ���Ļ������
        setLocationRelativeTo(null);
       
        // ���ô��ڵ��������
   
    }


}
