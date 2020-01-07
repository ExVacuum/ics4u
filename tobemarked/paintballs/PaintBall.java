package tobemarked.paintballs;

import java.awt.*;
import java.util.ArrayList;

public class PaintBall {
    private double x, ox;
    private double y, oy;
    private double vel = 10;
    private double dir;
    private float interpolation;
    private boolean collides = true;
    private boolean dead = false;
    private BallPaint app;
    private Color color;

    public PaintBall(int x, int y, double dir, BallPaint app){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.app = app;
        app.balls.add(this);
        color = new Color(Color.HSBtoRGB((float)Math.random(), 1f, 1f));
    }

    public double getDir(){
        return dir;
    }

    public void setDir(double dir) {
        this.dir = dir;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void move(){
        ox = x;
        oy = y;
        x += (vel * Math.cos(dir));
        y += (vel * Math.sin(dir));
        if(!Container.contains(this)){
           dead = true;
           new ColorWorm(Math.atan2(-(Container.center.getY()-y),-(Container.center.getX()-x)),color);
        }
        if(collides) app.deflector.collisions(this);
    }

    public void draw(Graphics2D g2, float interpolation){
        this.interpolation = interpolation;
        g2.setColor(color);
        g2.fillOval((int)x-8, (int)y-8, 16,16);
        g2.drawLine((int)x-8,(int)y-8, (int)((x+50)* Math.cos(dir)), (int)((y+50) * Math.sin(dir)));
    }

    public boolean isDead() {
        return dead;
    }

    public boolean collides() {
        return collides;
    }

    public void setCollides(boolean collides) {
        this.collides = collides;
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y,16,16);
    }

    public void flipDir(){
        dir = -dir;
    }

    public void revert(){
        x = ox;
        y = oy;
    };
}
