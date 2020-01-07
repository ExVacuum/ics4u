package tobemarked.paintballs;

import java.awt.*;

public class ColorWorm {

    private Color color;
    private double dir, x, y;
    private static final BasicStroke stroke = new BasicStroke(16);
    public ColorWorm(double dir, Color color){
        this.dir = dir;
        this.color = color;
        BallPaint.worms.add(this);
    }

    public void draw(Graphics2D g2){
        g2.setColor(color);
        g2.setStroke(stroke);
        g2.drawLine((int)(Container.center.x+(Container.radius+8)*Math.cos(dir)),(int)(Container.center.y+(Container.radius+8)*Math.sin(dir)),(int)(Container.center.x+(Container.radius*5+8)*Math.cos(dir)),(int)(Container.center.y+(Container.radius*5+8)*Math.sin(dir)));
    }
}
