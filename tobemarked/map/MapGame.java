package tobemarked.map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class MapGame extends JFrame implements MouseListener, MouseMotionListener{

    //Elevation (For World Generation/Brightness)
    public static float[][] elevation = new float[250][250];

    //Bodies of Water (For distinguishing oceanic bodies)
    ArrayList<WaterBody> waterBodies = new ArrayList<WaterBody>();

    //Main Drawing Panel
    JPanel p;

    public static void main(String[] args) {
        new MapGame();
    }

    public MapGame(){

        //Initialize Window
        init();

        //Generate World
        generate();

        //Main Loop
        while (true){
            draw();
        }
    }


    /**
     * Initialize game window. Create and add main Panel, and mouse listeners.
     */
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


    /**
     * Generate world  using Perlin noise.
     * scl = scale of perlin noise (Larger values increase fuzziness)
     */
    public void generate(){
        new Noise();

        float scl = 0.025f;
        for (int i = 0; i < elevation[0].length; i++){
            for (int j = 0; j < elevation[1].length; j++){
                elevation[i][j] = (float) Noise.noise(i*scl,j*scl)+0.65f;

                //Cap elevation to avoid weird color errors
                elevation[i][j] = Math.max(0,elevation[i][j]);
                elevation[i][j] = Math.min(1,elevation[i][j]);
            }
        }
    }

    public void draw(){
        p.repaint();
    }

    /**
     * Modify terrain values in a given radius from center point. Update water as necessary.
     * @param rad Radius
     * @param row Center Row
     * @param col Center Column
     */
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
        for(WaterBody wb : waterBodies){
            wb.update();
        }
    }

    /**
     * When called from a mouse listener, modifies terrain if left button pressed, or creates water if right button pressed.
     * @param e Mouse Event to check
     */
    public void updateMouse(MouseEvent e){
        if(SwingUtilities.isLeftMouseButton(e)) {
            int col = (e.getX() - 10) / 4;
            int row = (e.getY() - 29) / 4;
            System.out.println(row + ", " + col);
            fillTerrain(5, col, row);
        }
        if(SwingUtilities.isRightMouseButton(e)) {
            int col = (e.getX() - 10) / 4;
            int row = (e.getY() - 29) / 4;
            waterBodies.add(new WaterBody(col,row));
        }
    }

    /**
     * Custom JPanel class
     */
    class Panel extends JPanel{
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);

            //Determine whether tiles are green (hill) or brown (valley).
            for (int i = 0; i < elevation[0].length; i++){
                for (int j = 0; j < elevation[1].length; j++){
                    float col = 30f;
                    if(elevation[i][j]>=0){
                        col = 30f;
                        if(elevation[i][j]>=0.5f){
                            col = 92f;
                        }
                    }

                    //Determine if tiles contain water, and if so, are part of an ocean, or are an error in the fill method.
                    for(WaterBody wb : waterBodies) {
                        if (wb.water[i][j] == 1) {
                            if (wb.isLone(j, i)) {
                                col = 0;
                            }else{
                                if(wb.isOcean){
                                    col = 190f;
                                }else{
                                    col = 130f;
                                }
                            }
                        }
                    }

                    //Set color with "col" value determined above, and the elevation value.
                    g.setColor(new Color(Color.HSBtoRGB(col/255,1f, Math.abs(elevation[i][j])/2+0.1f)));

                    //Draw tile
                    g.fillRect(i*4,j*4, 4,4);
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        updateMouse(e);
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
        updateMouse(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
