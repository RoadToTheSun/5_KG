import java.awt.*;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.GeneralPath;

public class Flame {

    private final int x, y, size;
    private final Point controlPoint;
    private final Point[] points;
    private double ax = 0, k = 1;
    CubicCurve2D c1, c2;
    GradientPaint gp, gp1;
    private boolean visible;

    public Flame(int x, int y, int size){
        this.x = x;
        this.y = y;
        this.size = size;
        this.visible = true;
        controlPoint = new Point(x, y - size*4);
        points = new Point[]{
                new Point(x,y),
                new Point(x,y - size*2),
                new Point(x - size, y - size),
                new Point( x + size, y - size),
        };
        c1 = new CubicCurve2D.Double();
        c2 = new CubicCurve2D.Double();
        gp = new GradientPaint(controlPoint, Color.YELLOW, points[1], Color.RED);
        gp1 = new GradientPaint(controlPoint, Color.YELLOW, points[1], Color.RED);
    }

    public void paint(Graphics2D g2d){
        g2d.setPaint(gp);
        c1.setCurve(controlPoint, points[1], points[2], points[0]);
        c2.setCurve(controlPoint, points[1], points[3], points[0]);
        if(visible){
            g2d.fill(c1);
            g2d.fill(c2);
        }
    }

    public void reset(){
        visible = true;
        this.k = 0;
    }

    public void animation(int dx){
        if(Math.abs(dx) > size * 7){
            visible = false;
        }
        controlPoint.x = dx + x + (int) (Math.sin(ax) * 20);
        controlPoint.y = y - (int)(size*4*k + Math.cos(ax) * 30);
        ax += 0.003;
        if(k < 1){
            k += 0.005;
        }
    }

}
