import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

class Pocket {
    private Component canvas;
    private static final int XSIZE = 24;
    private static final int YSIZE = 24;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private int x = 0;
    private int y = 0;
    private int dx = 2;
    private int dy = 2;


    public Pocket(Component c, int x, int y){
        this.canvas = c;


        this.x = x;
        this.y = y;
    }

    public static void f(){
        int a = 0;
    }

    public void draw (Graphics2D g2){
        g2.setColor(Color.GREEN);
        g2.fill(new Rectangle2D.Double(x, y, XSIZE, YSIZE));
    }
}