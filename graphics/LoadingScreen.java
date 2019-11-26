package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoadingScreen extends JFrame{

    public static void main(String[] args) {
        new LoadingScreen();
    }

    //ArrayList of arc objects
    ArrayList<LoadingArc> arcs = new ArrayList<LoadingArc>();

    //Framerate Control
    static final int TPS = 60;
    static final int TSKIP = 1000 / TPS;
    static final int FRAMESKIPTOLERANCE = 5;
    long startTime = System.currentTimeMillis();
    long nextTick = GetTickCount();
    int loops;

    //Taskbar
    Taskbar taskbar;

    //Graphical Interpolation
    float interpolation;

    //Renderer
    Renderer renderer;

    //Opacity of arcs
    float opacity = 1f;

    //Fade Out Control
    long timerStartTime = System.currentTimeMillis();
    Timer fadeTimer = new Timer(10000, new FadeTimerListener());
    boolean fadeOut;

    class FadeTimerListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            fadeOut = true;
        }
    }

    LoadingScreen(){

        //Set up Window
        init();
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
            draw(interpolation);
        }
    }

    //Steps
    void step(){
        //Rotate Arcs
        for(LoadingArc arc: arcs){
            arc.step();
        }

        //Taskbar Progress
        taskbar.setWindowProgressValue(this,(int)(((System.currentTimeMillis()-timerStartTime)/10000.0)*100));

        //Fade Out if Necessary
        if(fadeOut){
           opacity -= 0.01;
        }
    }

    //Draw
    void draw(float interpolation){

        //Exit Once Invisible
        if(opacity < (1/255.0)){
            System.exit(0);
        }

        //Repaint Window
        renderer.interpolation = interpolation;
        renderer.repaint();
    }

    //Window Initialization
    void init(){

        //Taskbar for progress
        taskbar = Taskbar.getTaskbar();

        //Make Transparent
        setUndecorated(true);
        setBackground(new Color(0,0,0,0));

        //Set Dimensions
        setSize(100,100);
        setLocationRelativeTo(null);
        renderer = new Renderer();
        renderer.setOpaque(false);


        //Add Renderer
        add(renderer);

        setVisible(true);

        //Setup Arcs
        arcs.add(new LoadingArc(10,5,5,Color.RED));
        arcs.add(new LoadingArc(20,5,-6,Color.BLUE));
        arcs.add(new LoadingArc(30,5,-2,Color.GREEN));
        arcs.add(new LoadingArc(40,5,4,Color.YELLOW));

        //Start Fade Timer
        fadeTimer.start();
    }

    //Update Tick Count
    long GetTickCount(){
        return System.currentTimeMillis()-startTime;
    }

    //Arc
    private class LoadingArc{

        //Radius, Size, Angle, Color, and Rotational Speed
        int rad, size;
        double angle = 0, spd;
        Color c;

        LoadingArc(int rad, int size, double spd, Color c){
            this.rad = rad;
            this.size = size;
            this.spd = spd;
            this.c = c;
        }

        //Rotate
        void step(){
            angle+=spd;
        }

        //Draw Arc Based on Interpolation
        void draw(Graphics2D g2, float interpolation){
            g2.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), (int)(opacity*255)));
            g2.setStroke(new BasicStroke(size));
            g2.drawArc(50-rad,50-rad,rad*2,rad*2, (int)(angle-60 + (spd*interpolation)), 120);
        }

    }

    //Renderer
    private class Renderer extends JPanel{

        //Interpolation
        float interpolation = 0f;

        @Override
        public void paintComponent(Graphics g){
            Graphics2D g2 = (Graphics2D)g;

            //Anti-alias
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

            super.paintComponent(g2);

            //Draw Arcs
            for(LoadingArc arc: arcs){
                arc.draw(g2, interpolation);
            }
        }
    }
}
