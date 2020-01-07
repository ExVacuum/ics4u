package tobemarked.paintballs;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BallPaint extends JFrame {

    Container container;
    Emitter emitter;
    Deflector deflector;
    JPanel pane;
    public static ArrayList<PaintBall> balls = new ArrayList<PaintBall>();
    public static ArrayList<PaintBall> destroyBalls = new ArrayList<PaintBall>();
    public static ArrayList<ColorWorm> worms = new ArrayList<ColorWorm>();

    public static void main(String[] args) {
        new BallPaint();
    }

    //Framerate Control
    static final int TPS = 60;
    static final int TSKIP = 1000 / TPS;
    static final int FRAMESKIPTOLERANCE = 5;
    long startTime = System.currentTimeMillis();
    long nextTick = GetTickCount();
    int loops;

    //Graphical Interpolation
    float interpolation;

    BallPaint(){
        setVisible(false);
        pane = new Pane();
        pane.setBackground(Color.BLACK);
        add(pane);
        setSize(1280,720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        container = new Container(300, this);
        emitter = new Emitter(this);
        emitter.start();
        deflector = new Deflector();
        while(true) {
            loops = 0;

            //Limit Step Rate
            while (GetTickCount() > nextTick && loops < FRAMESKIPTOLERANCE) {
                step();

                nextTick += TSKIP;
                loops++;
            }

            //Calculate Interpolation
            interpolation = (float) (GetTickCount() + TSKIP - nextTick)
                    / (float) (TSKIP);

            //Unlimited Graphics FPS
            draw();
        }
    }

    public void step(){
        balls.removeAll(destroyBalls);
        for(int i = 0; i<balls.size(); i++){
            PaintBall ball = balls.get(i);
            if(!ball.isDead()) ball.move();
        }
        deflector.update();
    }

    public void draw(){
        pane.repaint();
    }

    //Update Tick Count
    long GetTickCount(){
        return System.currentTimeMillis()-startTime;
    }

    public void addBall(){
        new PaintBall(Emitter.x+33,Emitter.y+16, Emitter.dir, this);
    }


    public class Pane extends JPanel{
        @Override
        public void paintComponent(Graphics g){
            Graphics2D g2 = (Graphics2D)g;
            super.paintComponent(g2);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            container.draw(g2);
            emitter.draw(g2);
            deflector.draw(g2);
            for(int i = 0; i<balls.size(); i++){
                PaintBall ball = balls.get(i);
                if(!ball.isDead()) ball.draw(g2, interpolation);
                else destroyBalls.add(ball);
            }
            for(int i = 0; i<worms.size(); i++){
                ColorWorm worm = worms.get(i);
                worm.draw(g2);
            }
        }
    }

}
