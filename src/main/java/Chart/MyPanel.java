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
 * 内容面板
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
     * 绘制面板的内容: 创建 JPanel 后会调用一次该方法绘制内容,
     * 之后如果数据改变需要重新绘制, 可调用 updateUI() 方法触发
     * 系统再次调用该方法绘制更新 JPanel 的内容。
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 重新调用 Graphics 的绘制方法绘制时将自动擦除旧的内容

        /* 自行打开下面注释查看各绘制效果 */

        // 1. 线段 / 折线
        //drawLine(g);

        // 2. 矩形 / 多边形
        // drawRect(g,this.rect);
        if(this.rect!=null){
            drawRects(g,this.rect);
        }
 
        // 3. 圆弧 / 扇形
         drawManyArc(g,this.listpoint);

        // 4. 椭圆
        // drawOval(g);

        // 5. 图片
        // drawImage(g);

        // 6. 文本
        // drawString(g);
    }
    /**
     * 1. 线段 / 折线
     */
    private void drawLine(Graphics g) {
        frame.setTitle("1. 线段 / 折线");

        // 创建 Graphics 的副本, 需要改变 Graphics 的参数,
        // 这里必须使用副本, 避免影响到 Graphics 原有的设置
        Graphics2D g2d = (Graphics2D) g.create();

        // 抗锯齿
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 设置画笔颜色
        g2d.setColor(Color.GRAY);

        // 1. 两点绘制线段: 点(20, 50), 点(200, 50)
        g2d.drawLine(50, 50, 200, 50);

        // 2. 多点绘制折线: 点(50, 100), 点(100, 130), 点(150, 70), 点(200, 100)
        int[] xPoints = new int[] { 50, 100, 150, 200 };
        int[] yPoints = new int[] { 100, 120, 80, 100 };
        int nPoints = 4;
        g2d.drawPolyline(xPoints, yPoints, nPoints);

        // 3. 两点绘制线段（设置线宽为5px）: 点(50, 150), 点(200, 150)
        BasicStroke bs1 = new BasicStroke(5);       // 笔画的轮廓（画笔宽度/线宽为5px）
        g2d.setStroke(bs1);
        g2d.drawLine(50, 150, 200, 150);

        // 4. 绘制虚线: 将虚线分为若干段（ 实线段 和 空白段 都认为是一段）, 实线段 和 空白段 交替绘制,
        //             绘制的每一段（包括 实线段 和 空白段）的 长度 从 dash 虚线模式数组中取值（从首
        //             元素开始循环取值）, 下面数组即表示每段长度分别为: 5px, 10px, 5px, 10px, ...
        float[] dash = new float[] { 5, 10 };
        BasicStroke bs2 = new BasicStroke(
                1,                      // 画笔宽度/线宽
                BasicStroke.CAP_SQUARE,
                BasicStroke.JOIN_MITER,
                10.0f,
                dash,                   // 虚线模式数组
                0.0f
        );
        g2d.setStroke(bs2);
        g2d.drawLine(50, 200, 200, 200);

        // 自己创建的副本用完要销毁掉
        g2d.dispose();
    }
    /**
     * 2. 矩形 / 多边形
     */
    private void drawRect(Graphics g,Rectangle rect) {
        frame.setTitle("2. 矩形 / 多边形");
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.GRAY);

        // 1. 绘制一个矩形: 起点(30, 20), 宽80, 高100
        g2d.drawRect((int)rect.x1()-2, (int)rect.y1()-2,(int)( rect.x2()-rect.x1())+2, (int)(rect.y2()-rect.y1())+2);

        // 2. 填充一个矩形
       // g2d.fillRect(140, 20, 80, 100);

        // 3. 绘制一个圆角矩形: 起点(30, 150), 宽80, 高100, 圆角宽30, 圆角高30
       // g2d.drawRoundRect(30, 150, 80, 100, 30, 30);

        // 4. 绘制一个多边形(收尾相连): 点(140, 150), 点(180, 250), 点(220, 200)
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
     * 3. 圆弧 / 扇形
     */
    private void drawArc(Graphics g,Rtree.Object point) {
        frame.setTitle("3. 圆弧 / 扇形");
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.GRAY);

        // 1. 绘制一条圆弧: 椭圆的外切矩形 左上角坐标为(0, 0), 宽100, 高100,
        //                弧的开始角度为0度, 需要绘制的角度数为-90度,
        //                椭圆右边水平线为0度, 逆时针为正角度, 顺时针为负角度
        g2d.fillArc((int)point.getPoint().x1()-2, (int)point.getPoint().y1()-2, 4, 4, 0, 360);

        // 2. 绘制一个圆: 圆的外切矩形 左上角坐标为(120, 20), 宽高为100
     //   g2d.drawArc(120, 20, 100, 100, 0, 360);

       // g2d.setColor(Color.GRAY);

        // 3. 填充一个扇形
       // g2d.fillArc(80, 150, 100, 100, 90, 270);

        g2d.dispose();
    }
    }
