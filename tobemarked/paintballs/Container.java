package tobemarked.paintballs;

import java.awt.*;
import java.util.Vector;

public class Container {
    public static int radius;
    public static Point center;

    public Container(int radius, BallPaint paint){
        Insets decoration = paint.getInsets();
        this.radius = radius;
        this.center = new Point((paint.getWidth()-decoration.right-decoration.left)/2, (paint.getHeight()-decoration.top-decoration.bottom)/2);
    }

    public static boolean contains(PaintBall ball){
        double dist = center.distance(ball.getX(),ball.getY());
        if(dist<radius){
            return true;
        }
        return false;
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.GRAY);
        g2.fillOval(center.x-radius, center.y-radius,radius*2,radius*2);
    }
}
