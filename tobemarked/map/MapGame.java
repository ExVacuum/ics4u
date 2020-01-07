package tobemarked.map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class MapGame extends JFrame implements MouseListener, MouseMotionListener{
    float[][] elevation = new float[250][250];
    float[][] water = new float[250][250];
    ArrayList<Point> loneWater = new ArrayList<Point>();
    ArrayList<Point> resolvedLoneWater = new ArrayList<Point>();
    JPanel p;

    public static void main(String[] args) {
        new MapGame();
    }

    public MapGame(){
        init();
        generate();
        System.out.println("Generated");
        while (true){
            draw();
        }
    }

    public void init(){
        setVisible(false);
        setSize(1000,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        p = new  Panel();
        add(p);
        addMouseListener(this);
        addMouseMotionListener(this);
        setVisible(true);
    }

    public void generate(){
        new Noise();

        float scl = 0.025f;
        for (int i = 0; i < elevation[0].length; i++){

            //Noise should be roughly half either way
            for (int j = 0; j < elevation[1].length; j++){
                elevation[i][j] = (float) Noise.noise(i*scl,j*scl)+0.5f;
                elevation[i][j] = Math.max(0,elevation[i][j]);
                elevation[i][j] = Math.min(1,elevation[i][j]);
                if(j%5==0){
                    System.out.print(elevation[i][j] + ", ");
                }
            }
            System.out.println();
        }
    }

    public void draw(){
        p.repaint();
    }

    public void makeWater(int col, int row){
        for(int dir = 0; dir < 8; dir++){
            try {
                water[col][row] = 0;
                water(col,row, dir);
            }catch (IndexOutOfBoundsException e){
            }
        }
    }

    public void water(int col, int row, int dir){
        try {
            if (elevation[col][row]<=0.5&&water[col][row]!=1){
                water[col][row]=1;
            } else {
                return;
            }

            switch (dir) {
                case 0:
                    for (int i = -1; i < 2; i++) {
                        water(col + i, row - 1, 0);
                    }
                    return;
                case 1:
                    for (int i = -1; i < 2; i++) {
                        water(col - 1, row + i, 1);
                    }
                    return;
                case 2:
                    for (int i = -1; i < 2; i++) {
                        water(col + 1, row + i, 2);
                    }
                    return;
                case 3:
                    for (int i = -1; i < 2; i++) {
                        water(col + i, row + 1, 3);
                    }
                    return;
            }
        }catch(IndexOutOfBoundsException e){
            //Do nothing
        }
    }

    public boolean isLone(int col, int row){
        if(water[row][col]==1) {
            for (int i = -1; i < 1; i++) {
                try {
                    if (water[row + 1][col + i] != 1 && elevation[row + 1][col + i] < 0.5) {
                        return true;
                    }
                } catch (IndexOutOfBoundsException e) {
                }

                try {
                    if (water[row - 1][col + i] != 1 && elevation[row - 1][col + i] < 0.5) {
                        return true;
                    }
                } catch (IndexOutOfBoundsException e) {
                }
            }

            try {
                if (water[row][col - 1] != 1 && elevation[row][col - 1] < 0.5) {
                    return true;
                }
            } catch (IndexOutOfBoundsException e) {
            }

            try {
                if (water[row][col + 1] != 1 && elevation[row][col + 1] < 0.5) {
                    return true;
                }
            } catch (IndexOutOfBoundsException e) {
            }
        }
        return false;
    }

    public void fillTerrain(int rad, int row, int col){
        int cx = col*4;
        int cy = row*4;
        for(int i= 1; i < rad*2; i++){
            for(int j= 1; j < rad*2; j++){
                if(Math.abs(new Point(cx-rad*4+i*4,cy+rad*4-j*4).distance(new Point(cx,cy)))<=rad*4){
                    try {
                        int type = (elevation[row+j-rad][col+i-rad]>0.5f)?0:1;
                        elevation[row + j - rad][col + i - rad] = (type+((Math.abs((rad-i)/(float)rad)/2+Math.abs((rad-j)/(float)rad)/2)*((type==0?0.5f:-0.5f))));
                    }catch(IndexOutOfBoundsException e){
                        //Do nothing
                    }
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    public void checkForLoneWater(){
        for(int i = 0; i < water.length; i++){
            for(int j = 0; j < water[0].length; j++){
                if(isLone(j,i)&&!loneWater.contains(new Point(j,i))){ loneWater.add(new Point(j, i));}
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(SwingUtilities.isLeftMouseButton(e)) {
            int col = (e.getX() - 10) / 4;
            int row = (e.getY() - 29) / 4;
            System.out.println(row + ", " + col);
            fillTerrain(5, col, row);
        }
        if(SwingUtilities.isRightMouseButton(e)) {
            int col = (e.getX() - 10) / 4;
            int row = (e.getY() - 29) / 4;
            makeWater(col,row);
        }

        checkForLoneWater();
        while (loneWater.size()>0) {
            for (int i = 0; i < loneWater.size(); i++) {
                Point p = loneWater.get(i);
                makeWater(p.y, p.x);
                if(!isLone(p.x,p.y)) resolvedLoneWater.add(p);
            }

            loneWater.removeAll(resolvedLoneWater);
            resolvedLoneWater.clear();

            checkForLoneWater();
        }
    }

    

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(SwingUtilities.isLeftMouseButton(e)) {
            int col = (e.getX() - 10) / 4;
            int row = (e.getY() - 29) / 4;
            System.out.println(row + ", " + col);
            fillTerrain(5, col, row);
        }
        if(SwingUtilities.isRightMouseButton(e)) {
            int col = (e.getX() - 10) / 4;
            int row = (e.getY() - 29) / 4;
            makeWater(col,row);
        }

        checkForLoneWater();
        while (loneWater.size()>0) {
            for (int i = 0; i < loneWater.size(); i++) {
                Point p = loneWater.get(i);
                makeWater(p.y, p.x);
                if(!isLone(p.x,p.y)) resolvedLoneWater.add(p);
            }

            loneWater.removeAll(resolvedLoneWater);
            resolvedLoneWater.clear();

            checkForLoneWater();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }


    class Panel extends JPanel{
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            for (int i = 0; i < elevation[0].length; i++){
                for (int j = 0; j < elevation[1].length; j++){
                    float col = 30f;
                    if(elevation[i][j]>=0){
                        col = 30f;
                        if(elevation[i][j]>=0.5f){
                            col = 92f;
                        }
                    }
                    if (water[i][j]==1){
                        if(isLone(j,i)){
                            col = 0;
                        }else
                        col = 150f;
                    }
                    g.setColor(new Color(Color.HSBtoRGB(col/255,1f, Math.abs(elevation[i][j])/2+0.1f)));
                    g.fillRect(i*4,j*4, 4,4);
                }
            }
        }
    }
}
