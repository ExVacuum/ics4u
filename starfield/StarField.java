//Silas Bartha Oct. 2, 2019, Starfield
package starfield;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class StarField  {
	public static void main(String[] args) {
		new StarField();  
	}

	/****************/
	/* Variables       */
	/****************/

	//window sizes
	static int winWidth =0;  
	static int winHeight =0;

	JPanel mainPanel;  // the main JPanel

	//booleans
	boolean toggle = true;

	//timer variables
	Timer timer; // timer object
	static final int T_SPEED =35; //speed of timer repeats (ms)
	static final int TDELAY = 1000;  //initial delay (ms)
	
	//x and y position of stars
	static final int numStars =600;
	
	ArrayList<Star> starCollection = new ArrayList<Star>(); //make an array list of stars 

	//x and y position of centre of screen
	int cx =400;  //estimate for now (to avoid errors)
	int cy =300;
	
	//Constructor
	StarField() {

		//Setup timers
		TimerListener tl = new TimerListener();  
		timer = new Timer(T_SPEED, tl);
		timer.setInitialDelay(TDELAY);
		timer.start(); 

		JFrame window = new JFrame("Star Field");
		mainPanel = new GrPanel();
		mainPanel.setBackground(Color.BLACK);

		window.add(mainPanel);
		
		//window setup
		window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		//set window size to full screen
		Toolkit tk = Toolkit.getDefaultToolkit();  
		winWidth = ((int) tk.getScreenSize().getWidth());  
		winHeight = ((int) tk.getScreenSize().getHeight());  
		window.setSize(winWidth-50,winHeight-100);  //ideally this should make sure that the title bar still displays
		cx = winWidth/2;
		cy = winHeight/2;
		
		createStars(500);
		
		window.setLocationRelativeTo(null); //centre window
		window.setVisible(true);	//must be the last (almost) thing that you do
		window.setResizable(false);
	}

	/**********************************/
	/* put any special functions here */
	/**********************************/
	void createStars(int num) { 	
		for(int i=0; i < num; i++){
			starCollection.add(new Star(cx, cy));				
		}
		//System.out.print("*");
	}  

	
	/**************************************/
	/* JPanel inner class  to draw graphics */
	/**************************************/
	//
	private class GrPanel extends JPanel{
		
		GrPanel() {} // empty constructor. You could set the background colour here.

		/* paintComponent() does the painting on the Graphics object.
		 * It is called by repaint(), never run directly.	 */
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g); //this draws the background
			
			//Graphics2D allows smoother graphics
			Graphics2D g2 = (Graphics2D) g; 
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
						
			/***************************/
			/* Draw all objects here */
			/***************************/
			
			for(Star s : starCollection) {	
				g2.setColor(new Color(Color.HSBtoRGB(1.0f, 0.0f, (float)s.opacity)));
			//	double size = Math.abs(sx[i]-cx)/15.0 + Math.abs(sy[i]-cy)/15.0;				
				g2.fillOval((int)s.x, (int)s.y, (int)s.size, (int)s.size);
				//this could be moved to a paint method like the following
					//s.paint(g2);
			}
				
		}
	}

	//###############################################
	//inner class for Timer Listener
	private class TimerListener implements ActionListener {  
		
		//this method will run every T_SPEED milliseconds
		@Override
		public void actionPerformed(ActionEvent e) {

		/***************************/
		/* All timer tasks here        */
		/***************************/
		//if (starCollection.size()< numStars) createStars(1);
		
		//move all stars
		for (Star s : starCollection) {
			s.move();
		}
		 
		//request screen to be redrawn at the computer's convenience
			mainPanel.repaint();
		}
	}
}
