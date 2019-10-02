package starfield;

import java.util.Random;

public class Star{
	
	double x, y;
	int dir;
	double size;
	double speed, opacity;
	int cx, cy;
	int lifespan;


	//Random
	Random rand = new Random();
	
	Star(int cx, int cy){
		this.cx = cx;
		this.cy = cy;
		reset();
	}
	
	//Move Star
	void move() {	
		lifespan++;
		x += speed * Math.cos(dir);
		y += speed * Math.sin(dir);
		speed+=0.1*1+(lifespan/120.0);
		opacity = 1/(size/10);
		if(x<0-size||x>(cx*2)||y<0-size||y>(cy*2)){
			reset();
		}
	}
	
	//Randomize Values and Reset
	void reset() {
		lifespan = 0;
		dir = rand.nextInt(360);
		x=cx+(rand.nextInt(20)*(dir<180?1:-1));
		y=cy+(rand.nextInt(20)*(dir<180?1:-1));
		speed = rand.nextDouble();
		size = rand.nextInt(10);
	}
}