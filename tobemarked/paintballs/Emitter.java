package tobemarked.paintballs;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

public class Emitter extends Thread{

    public static double dir = 0;
    public static int x,y;
    BallPaint app;

    private Timer emitterTimer = new Timer(500, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            shoot();
        }});

    @Override
    public void run(){
        emitterTimer.start();
    }

    public Emitter(BallPaint app){
        this.x = Container.center.x-Container.radius-32;
        this.y = Container.center.y-16;
        this.app = app;
    }

    public void shoot(){
        app.addBall();
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(x,y,32,32);
    }
}