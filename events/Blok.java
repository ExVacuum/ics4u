package events;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class Blok extends JFrame implements ComponentListener{

    static final int width = 300, height = 300;

    public static void main(String[] args) {
        new Blok();
    }

    Blok(){
        setSize(width,height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentListener(this);
        BlokPanel panel = new BlokPanel();
        add(panel);
        setAlwaysOnTop(true);
        setVisible(true);

        while(true){
            panel.repaint();
        }
    }


    @Override
    public void componentResized(ComponentEvent e) {
        setVisible(false);
        setSize(width,height);
        setVisible(true);
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        setVisible(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

    class BlokPanel extends JPanel{

        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
        }
    }
}
