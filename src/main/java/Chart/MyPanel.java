package Chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.github.davidmoten.rtree.geometry.Point;
import com.github.davidmoten.rtree.geometry.Rectangle;
/**
 * �������
 */
public class MyPanel extends JPanel {
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyFrame frame;
    public ArrayList<Rtree.Object> listpoint=null;
    public ArrayList<Rectangle> rect=null;
    public void setListpoint(ArrayList<Rtree.Object> listpoint){
    	this.listpoint=listpoint;
    	updateUI();
    }
    public void setRects(ArrayList<Rectangle>  rec)
    {
    	this.rect=rec;
    	updateUI();
    }
    public MyPanel(MyFrame frame) {
        super();
        this.frame = frame;
    }

    /**
     * ������������: ���� JPanel ������һ�θ÷�����������,
     * ֮��������ݸı���Ҫ���»���, �ɵ��� updateUI() ��������
     * ϵͳ�ٴε��ø÷������Ƹ��� JPanel �����ݡ�
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // ���µ��� Graphics �Ļ��Ʒ�������ʱ���Զ������ɵ�����

        /* ���д�����ע�Ͳ鿴������Ч�� */

        // 1. �߶� / ����
        //drawLine(g);

        // 2. ���� / �����
        // drawRect(g,this.rect);
        if(this.rect!=null){
            drawRects(g,this.rect);
        }
 
        // 3. Բ�� / ����
         drawManyArc(g,this.listpoint);

        // 4. ��Բ
        // drawOval(g);

        // 5. ͼƬ
        // drawImage(g);

        // 6. �ı�
        // drawString(g);
    }
    /**
     * 1. �߶� / ����
     */
    private void drawLine(Graphics g) {
        frame.setTitle("1. �߶� / ����");

        // ���� Graphics �ĸ���, ��Ҫ�ı� Graphics �Ĳ���,
        // �������ʹ�ø���, ����Ӱ�쵽 Graphics ԭ�е�����
        Graphics2D g2d = (Graphics2D) g.create();

        // �����
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // ���û�����ɫ
        g2d.setColor(Color.GRAY);

        // 1. ��������߶�: ��(20, 50), ��(200, 50)
        g2d.drawLine(50, 50, 200, 50);

        // 2. ����������: ��(50, 100), ��(100, 130), ��(150, 70), ��(200, 100)
        int[] xPoints = new int[] { 50, 100, 150, 200 };
        int[] yPoints = new int[] { 100, 120, 80, 100 };
        int nPoints = 4;
        g2d.drawPolyline(xPoints, yPoints, nPoints);

        // 3. ��������߶Σ������߿�Ϊ5px��: ��(50, 150), ��(200, 150)
        BasicStroke bs1 = new BasicStroke(5);       // �ʻ������������ʿ��/�߿�Ϊ5px��
        g2d.setStroke(bs1);
        g2d.drawLine(50, 150, 200, 150);

        // 4. ��������: �����߷�Ϊ���ɶΣ� ʵ�߶� �� �հ׶� ����Ϊ��һ�Σ�, ʵ�߶� �� �հ׶� �������,
        //             ���Ƶ�ÿһ�Σ����� ʵ�߶� �� �հ׶Σ��� ���� �� dash ����ģʽ������ȡֵ������
        //             Ԫ�ؿ�ʼѭ��ȡֵ��, �������鼴��ʾÿ�γ��ȷֱ�Ϊ: 5px, 10px, 5px, 10px, ...
        float[] dash = new float[] { 5, 10 };
        BasicStroke bs2 = new BasicStroke(
                1,                      // ���ʿ��/�߿�
                BasicStroke.CAP_SQUARE,
                BasicStroke.JOIN_MITER,
                10.0f,
                dash,                   // ����ģʽ����
                0.0f
        );
        g2d.setStroke(bs2);
        g2d.drawLine(50, 200, 200, 200);

        // �Լ������ĸ�������Ҫ���ٵ�
        g2d.dispose();
    }
    /**
     * 2. ���� / �����
     */
    private void drawRect(Graphics g,Rectangle rect) {
        frame.setTitle("2. ���� / �����");
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.GRAY);

        // 1. ����һ������: ���(30, 20), ��80, ��100
        g2d.drawRect((int)rect.x1()-2, (int)rect.y1()-2,(int)( rect.x2()-rect.x1())+2, (int)(rect.y2()-rect.y1())+2);

        // 2. ���һ������
       // g2d.fillRect(140, 20, 80, 100);

        // 3. ����һ��Բ�Ǿ���: ���(30, 150), ��80, ��100, Բ�ǿ�30, Բ�Ǹ�30
       // g2d.drawRoundRect(30, 150, 80, 100, 30, 30);

        // 4. ����һ�������(��β����): ��(140, 150), ��(180, 250), ��(220, 200)
//        int[] xPoints = new int[] { 140, 180, 220};
//        int[] yPoints = new int[] { 150,  250, 200};
//        int nPoints = 3;
//        g2d.drawPolygon(xPoints, yPoints, nPoints);

        g2d.dispose();
    }
    public void drawManyArc(Graphics g,ArrayList<Rtree.Object> points){
    	for(int i=0;i<points.size();i++)
    	{
    		drawArc(g,points.get(i));
    	}
    	
    }
    public void drawRects(Graphics g,ArrayList<Rectangle> rec){
    	for(int i=0;i<rec.size();i++)
    	{
    		drawRect(g,rec.get(i));
    	}
    	
    }
    /**
     * 3. Բ�� / ����
     */
    private void drawArc(Graphics g,Rtree.Object point) {
        frame.setTitle("3. Բ�� / ����");
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.GRAY);

        // 1. ����һ��Բ��: ��Բ�����о��� ���Ͻ�����Ϊ(0, 0), ��100, ��100,
        //                ���Ŀ�ʼ�Ƕ�Ϊ0��, ��Ҫ���ƵĽǶ���Ϊ-90��,
        //                ��Բ�ұ�ˮƽ��Ϊ0��, ��ʱ��Ϊ���Ƕ�, ˳ʱ��Ϊ���Ƕ�
        g2d.fillArc((int)point.getPoint().x1()-2, (int)point.getPoint().y1()-2, 4, 4, 0, 360);

        // 2. ����һ��Բ: Բ�����о��� ���Ͻ�����Ϊ(120, 20), ���Ϊ100
     //   g2d.drawArc(120, 20, 100, 100, 0, 360);

       // g2d.setColor(Color.GRAY);

        // 3. ���һ������
       // g2d.fillArc(80, 150, 100, 100, 90, 270);

        g2d.dispose();
    }
    }
