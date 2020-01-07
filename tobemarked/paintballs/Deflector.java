package tobemarked.paintballs;

import java.awt.*;

public class Deflector {

    int cx, cy;
    double sideLength = 50;
    double rot = 0;
    double rotSpd = 1;
    Polygon triangle;
    int[] xPoints;
    int[] yPoints;
    Point n;

    public Deflector(){
        cx = Container.center.x;
        cy = Container.center.y;
    }

    public void update(){
        rot += rotSpd;
        if(rot>=360)rot %= 360.0;
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.WHITE);
        xPoints = new int[]{(int)(cx+sideLength*Math.sin(Math.toRadians(rot)-30)), (int)(cx+sideLength*Math.sin(Math.toRadians(rot+120)-30)), (int)(cx+sideLength*Math.sin(Math.toRadians(rot+240)-30))};
        yPoints = new int[]{(int)(cy+sideLength*Math.cos(Math.toRadians(rot)-30)), (int)(cy+sideLength*Math.cos(Math.toRadians(rot+120)-30)), (int)(cy+sideLength*Math.cos(Math.toRadians(rot+240)-30))};
        triangle = new Polygon(xPoints,yPoints,3);
        g2.fillPolygon(triangle);
        g2.setColor(Color.BLACK);
        g2.drawArc(cx-75,cy-75,150,150 ,(int)rot,60);
        g2.setStroke(new BasicStroke(3));
    }

    public void collisions(PaintBall ball){
        if(triangle.intersects(ball.getBounds())){
            int side = (int)(rot)/120;
            n = new Point(-(yPoints[side]-yPoints[side==0?2:side-1]),-(xPoints[side]-xPoints[side==0?2:side-1]));

            ball.setDir(Math.toDegrees(Math.atan2(Container.center.x-n.x,Container.center.y-n.y)));
            System.out.println("Ball:" + ball.getDir());
            System.out.println("Rotation:" + rot);
            //ball.flipDir();
            //ball.revert();
            ball.setCollides(false);
        }
    }
}
