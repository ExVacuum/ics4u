package tobemarked.map;

import java.awt.*;
import java.util.ArrayList;

public class WaterBody {

    //Boolean to determine if this body is an ocean.
    public boolean isOcean;

    //Tiles this body will cover
    public float[][] water = new float[250][250];

    //Any unfinished filling will keep the edge bits in this array list
    public ArrayList<Point> loneWater = new ArrayList<Point>();

    //Any lone water points that are later resolved will be sent here to be deleted
    public ArrayList<Point> resolvedLoneWater = new ArrayList<Point>();

    /**
     * Create a new body of water at a specified point
     * @param x X-Coordinate
     * @param y Y-Coordinate
     */
    WaterBody(int x,int y) {

        //Fill, and repeat until complete
        makeWater(x, y);
        update();
    }

    /**
     * Checks for incomplete filling, and attempts to resolve it.
     */
    public void update(){
        checkForLoneWater();
        while (loneWater.size() > 0) {
            for (int i = 0; i < loneWater.size(); i++) {
                Point p = loneWater.get(i);
                makeWater(p.y, p.x);
                if (!isLone(p.x, p.y)) resolvedLoneWater.add(p);
            }

            loneWater.removeAll(resolvedLoneWater);
            resolvedLoneWater.clear();

            checkForLoneWater();
        }
    }

    /**
     * Check for incomplete filling.
     */
    public void checkForLoneWater(){
        for(int i = 0; i < water.length; i++){
            for(int j = 0; j < water[0].length; j++){
                if(isLone(j,i)&&!loneWater.contains(new Point(j,i))){ loneWater.add(new Point(j, i));}
            }
        }
    }

    /**
     * Checks if a given tile is on the edge of an incomplete fill.
     * @param col Column
     * @param row Row
     * @return if a given tile is on the edge of an incomplete fill.
     */
    public boolean isLone(int col, int row){
        if(water[row][col]==1) {
            for (int i = -1; i < 1; i++) {
                try {
                    if (water[row + 1][col + i] != 1 && MapGame.elevation[row + 1][col + i] < 0.5) {
                        return true;
                    }
                } catch (IndexOutOfBoundsException e) {
                }

                try {
                    if (water[row - 1][col + i] != 1 && MapGame.elevation[row - 1][col + i] < 0.5) {
                        return true;
                    }
                } catch (IndexOutOfBoundsException e) {
                }
            }

            try {
                if (water[row][col - 1] != 1 && MapGame.elevation[row][col - 1] < 0.5) {
                    return true;
                }
            } catch (IndexOutOfBoundsException e) {
            }

            try {
                if (water[row][col + 1] != 1 && MapGame.elevation[row][col + 1] < 0.5) {
                    return true;
                }
            } catch (IndexOutOfBoundsException e) {
            }
        }
        return false;
    }

    /**
     * Flood fills water in 8 directions.
     * @param col Origin column
     * @param row Origin row
     */
    public void makeWater(int col, int row){
        for(int dir = 0; dir < 8; dir++){
            try {
                water[col][row] = 0;
                water(col,row, dir);
            }catch (IndexOutOfBoundsException e){
            }
        }
    }

    /**
     * Flood fills recursively in a given direction
     * @param col Origin column
     * @param row Origin row
     * @param dir Quadrant
     */
    public void water(int col, int row, int dir){
        try {
            if (MapGame.elevation[col][row]<=0.5&& water[col][row]!=1){
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

            //If the edge of the map is reached, this will be an ocean.
            isOcean = true;
        }
    }
}
