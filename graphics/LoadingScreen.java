//Silas Bartha, Nov. 27 2019, loading screen program
package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
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
        if(opacity < (0.02)){
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
        setSize(800,800);
        setLocationRelativeTo(null);
        renderer = new Renderer();
        renderer.setOpaque(false);


        //Add Renderer
        add(renderer);

        setVisible(true);

        //Setup Arcs
        arcs.add(new LoadingArc(10,3,1,Color.WHITE));
        arcs.add(new LoadingArc(20,3,-8,Color.WHITE));
        arcs.add(new LoadingArc(30,3,2,Color.WHITE));
        arcs.add(new LoadingArc(40,3,-7,Color.WHITE));
        arcs.add(new LoadingArc(50,3,3,Color.WHITE));
        arcs.add(new LoadingArc(60,3,-6,Color.WHITE));
        arcs.add(new LoadingArc(70,3,4,Color.WHITE));
        arcs.add(new LoadingArc(80,3,-5,Color.WHITE));
        arcs.add(new LoadingArc(90,3,5,Color.WHITE));
        arcs.add(new LoadingArc(100,3,-4,Color.WHITE));
        arcs.add(new LoadingArc(110,3,6,Color.WHITE));
        arcs.add(new LoadingArc(120,3,-3,Color.WHITE));
        arcs.add(new LoadingArc(130,3,7,Color.WHITE));
        arcs.add(new LoadingArc(140,3,-2,Color.WHITE));
        arcs.add(new LoadingArc(150,3,8,Color.WHITE));
        arcs.add(new LoadingArc(160,3,-1,Color.WHITE));
        arcs.add(new LoadingArc(170,3,8,Color.WHITE));
        arcs.add(new LoadingArc(180,3,-1,Color.WHITE));
        arcs.add(new LoadingArc(190,3,7,Color.WHITE));
        arcs.add(new LoadingArc(200,3,-2,Color.WHITE));
        arcs.add(new LoadingArc(210,3,6,Color.WHITE));
        arcs.add(new LoadingArc(220,3,-3,Color.WHITE));
        arcs.add(new LoadingArc(230,3,5,Color.WHITE));
        arcs.add(new LoadingArc(240,3,-4,Color.WHITE));
        arcs.add(new LoadingArc(250,3,4,Color.WHITE));
        arcs.add(new LoadingArc(260,3,-5,Color.WHITE));
        arcs.add(new LoadingArc(270,3,3,Color.WHITE));
        arcs.add(new LoadingArc(280,3,-6,Color.WHITE));
        arcs.add(new LoadingArc(290,3,2,Color.WHITE));
        arcs.add(new LoadingArc(300,3,-7,Color.WHITE));
        arcs.add(new LoadingArc(310,3,1,Color.WHITE));
        arcs.add(new LoadingArc(320,3,-8,Color.WHITE));

        renderer.repaint();


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
        double angle = 90, spd;
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
            g2.setColor(new Color(Color.HSBtoRGB( (float)((System.currentTimeMillis()-timerStartTime)/(5000.0+rad*5)),1.0f,1.0f)));
            g2.setStroke(new BasicStroke(size));
            g2.drawArc(400-rad,400-rad,rad*2,rad*2, (int)(angle + (spd*interpolation)), (int)((System.currentTimeMillis()-timerStartTime)/10000.0*360));
        }
    }

    //Renderer
    private class Renderer extends JPanel{

        //Interpolation
        float interpolation = 0f;

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            super.paintComponent(g2);

            g2.setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, opacity));

            //Anti-alias
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(Color.DARK_GRAY);
            g2.fill(new Arc2D.Double(70, 70,
                    660,
                    660,
                    Math.max(270 - (int) ((System.currentTimeMillis() - timerStartTime) / 10000.0 * 180), 90),
                    Math.min(2 * (int) ((System.currentTimeMillis() - timerStartTime) / 10000.0 * 180), 360),
                    Arc2D.CHORD));

            //Draw Arcs
            for (LoadingArc arc : arcs) {
                arc.draw(g2, interpolation);
            }
        }
    }
}
