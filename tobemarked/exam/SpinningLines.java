import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

//Silas Bartha, Exam Program 1

/***********************************************************************************************************
* This is part of a nice attractive program written by a student as part of their loading icon project.
* Your job is to put the three lines into objects, instead of having 12 global variables and another 6 variables for the centres!
*
* 1. Make a Line class; you can make this an inner class if you want. 
     Create the three lines (each Line object can be a global variable).
*    (If we had more than 3, we'd use an arraylist as our only global variable, with 'panel' and 'angle' of course)
*
*  Make sure that you can still draw the lines and that they still spin.
*
* 2. Now fix up the paintComponent so that the actual drawing (ie. the 3 lines below) is done as a method in the Line class
     	 	g2.rotate(angle,cx,cy);
			g2.drawLine( (int)x1, (int)y1, (int)x2,  (int)y2);
			g2.rotate(-angle,cx,cy);

* Your paintComponent can then be quite simplified, so that it looks similar to this:

		@Override
		public void paintComponent(Graphics g) {			
			super.paintComponent(g);
			
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setStroke(new BasicStroke(3));
			g2.setColor(Color.GREEN);

			line1.draw(g2); //the drawing of the line has been moved to a method in the Line class.
			line2.draw(g2);
			line3.draw(g2);
		}
*
* 3. The program should still look identical to the user when it runs, no matter what code changes you make.
*
***********************************************************************************************************/
public class SpinningLines extends JFrame implements ActionListener {

	public static void main(String[] args) {
		new SpinningLines();
	}
	
	static final int panW = 425;
	static final int panH = 450;

	DrawingPanel panel;

	//lines
	Line l1, l2, l3;

	double x1, y1, x2, y2;
	double a1, b1, a2, b2;
	double c1, d1, c2, d2;
	
	double angle = 0.0;

	SpinningLines(){

		//right line
		l1 = new Line(212,40,355,280);

		//left line
		l2 = new Line(212,40,70,280);

		//bottom line
		l3 = new Line(70,280,355,280);

		//Set up window
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		panel = new DrawingPanel();
		this.add(panel);
		this.pack();
		this.setVisible(true);

		Timer timer = new Timer(30, this);
		timer.start();
	}

	
	class DrawingPanel extends JPanel{
		
		DrawingPanel(){
			this.setBackground(Color.BLACK);
			this.setPreferredSize(new Dimension(panW, panH));
		}

		@Override
		public void paintComponent(Graphics g) {			
			super.paintComponent(g);

			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setStroke(new BasicStroke(3));
			g2.setColor(Color.GREEN);
			
			//drawing lines
			l1.draw(g2);

			l2.draw(g2);

			l3.draw(g2);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		angle = angle + 0.05;
		panel.repaint();
	}

	class Line{

		double x1,y1,x2,y2;
		Line(double x1, double y1, double x2,double y2){
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}

		void draw(Graphics2D g2){
			double cx = (x1+x2)/2.0;
			double cy = (y1+y2)/2.0;

			g2.rotate(angle,cx,cy);
			g2.drawLine( (int)x1, (int)y1, (int)x2,  (int)y2);
			g2.rotate(-angle,cx,cy);
		}

	}
}

