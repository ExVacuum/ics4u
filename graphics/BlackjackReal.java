import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BlackjackReal extends JFrame implements MouseListener{
	static final int SPADES = 0;
	static final int HEARTS = 1;
	static final int CLUBS = 2;
	static final int DIAMONDS = 3;

	int mx = -1; 
	int my = -1;
	
	static int PTotal = 0;
	static int DTotal = 0;


	ArrayList <Card> deck;
	ArrayList <Card> DHand;
	ArrayList <Card> PHand;

	JPanel grpanel;

	public static void main(String[] args) {
		new BlackjackReal();
	}

	//These could easily be BufferedImage
	Image imgSprites, imgback;

	static final int CARDW = 71;
	static final int CARDH = 96;

	BlackjackReal(){	
		int o = 1;
		int suit = 0; 
		int value = 0;
		int n=0;

		deck = new ArrayList<Card>();
		DHand = new ArrayList<Card>();
		PHand = new ArrayList<Card>();

		for (suit = 0; suit < 4; suit++) {
			for (int i = 0; i < 13; i++) {
				value = i;
				Card c = new Card(suit, value);
				deck.add(c);
			}
		}

		Collections.shuffle(deck);
		dealCards();
		printTotals();
		checkWin();


		this.setTitle("Drawing images");
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		//this.setLocationRelativeTo(null);
		this.setSize(600,400);
		this.setLocationRelativeTo(null);
		grpanel = new GrPanel();
		grpanel.setBackground(Color.BLUE);

		JLabel lblStatus = new JLabel("This fish has a white background (not transparent)");
		lblStatus.setBackground(Color.YELLOW);
		lblStatus.setOpaque(true);

		String filename = "SolitaireCards.png";
		imgback = null;

		filename = "SolitaireCards.png";		
		imgSprites = loadImageR1(filename);

		grpanel.setBackground(Color.green.darker());
		grpanel.add(lblStatus);
		this.add(lblStatus,BorderLayout.NORTH);
		this.add(grpanel,BorderLayout.CENTER);
		this.setVisible(true);
		addMouseListener(this);


	}



	void dealCards() {
		for(int i = 0; i < 2; i ++) {
			System.out.println("You were dealt the " + deck.get(i).getValue() + " of " + deck.get(i).getSuit());
			PHand.add(deck.get(i));
			deck.remove(i); 
		}
		for(int i = 0; i < 2; i ++) {
			System.out.print("\nThe Dealer has the " + deck.get(i).getValue() + " of " + deck.get(i).getSuit());
			DHand.add(deck.get(i));
			deck.remove(i); 
		}
	}

	void dHit() {
		for(int i = 0; i < 1; i ++) {
			System.out.println("The Dealer Gets the " + deck.get(i).getValue() + " of " + deck.get(i).getSuit());
			DHand.add(deck.get(i));
			deck.remove(i); 
			int DTotal = 0;
			for (int o = 0; o < DHand.size(); o++) {
				DTotal += DHand.get(o).getValue();
				if(DTotal < 17) {
					i--;
				}
			}
		}
	}
	void pStand() {
		
	}
	void pHit() {
		for(int i = 0; i < 1; i ++) {
			System.out.println("You were dealt the " + deck.get(i).getValue() + " of " + deck.get(i).getSuit());
			PHand.add(deck.get(i));
			deck.remove(i);
			grpanel.repaint();
		}
	}
	void checkWin() {
		int DTotal = 0;
		int PTotal = 0;
		for (int o = 0; o < DHand.size(); o++) {
			DTotal += DHand.get(o).getValue();
		}
		for (int o = 0; o < PHand.size(); o++) {
			PTotal += PHand.get(o).getSuit();
		}
		if (PTotal > DTotal) {
			System.out.println("YOU ARE A BEAST!! GOOD SHIT YOU WON");
		}
		if(DTotal > PTotal && DTotal < 22) {
			System.out.println("You are trash. wow. unbelievably GARBAGE.");
		}
		else {
			System.out.println("You tied, AKA you are a loser, but you didnt have the strength to admit it.");
		}
	}

	void printTotals() {

		for (Card card : PHand) {
			if(card.value > 10) {
				card.value = 10;
			}
			PTotal += card.value + 1;
		}


		for (int i = 0; i < DHand.size(); i ++) {
			DTotal += DHand.get(i).getValue();
		}


	}
	public void mouseClicked(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		if(mx > 50 && mx < 95 && my > 295 && my < 320) {
			pHit();
		//int q ++;
		}
		if(mx > 110 && mx < 155 && my > 295 && my < 320) {
			pStand();
		}
	}

	public void mouseReleased(MouseEvent e) {

	}
	//This is the only method that requires try-catch, but it can also handle inputstreams


	BufferedImage loadImageR1(String fn) {	

		//Only works with ImageIO.read. Does not work with imageIcon
		InputStream inputStr = BlackjackReal.class.getClassLoader().getResourceAsStream(fn);

		//Works with both ImageIO.read and ImageIcon
		java.net.URL imageURL = BlackjackReal.class.getClassLoader().getResource(fn);
		if (imageURL == null) {
			System.out.println("null URL from filname");
			System.exit(0);
		}

		BufferedImage image = null;

		//1. ImageIO.read inputstream
		try {
			image = ImageIO.read(inputStr);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;
	}

	private class GrPanel extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
		

			//make the background image fill up the whole window
			if (imgback != null) g.drawImage(imgback, 0, 0, getWidth(), getHeight(), null);

			//draw 1 card
			//g.drawImage(imgSprites, 10,10, 10+CARDW, 10+CARDH, 0,0,CARDW, CARDH, null);

			Point pos = new Point(50,100); //starting point
			
			for (Card card : PHand) {
				g.drawImage(imgSprites, pos.x, pos.y, pos.x+CARDW, pos.y+CARDH, card.value*CARDW, card.suit*CARDH,(card.value+1)*CARDW, (card.suit+1)*CARDH, null);
				pos.x+=30;
				pos.y+=20;
			}
			pos.x-=30;
			pos.y-=20;
			for (Card card : DHand) {
				g.drawImage(imgSprites, pos.x+350, pos.y, pos.x+CARDW+350, pos.y+CARDH, card.value*CARDW, card.suit*CARDH,(card.value+1)*CARDW, (card.suit+1)*CARDH, null);
				pos.x+=30;
				pos.y+=20;
			}
			g.drawImage(imgSprites, pos.x+320, pos.y-20, pos.x+CARDW+320, pos.y+CARDH-20, 1*CARDW, 4*CARDH, 2*CARDW, 5*CARDH, null);

			
			g.drawRect(50, 250, 45, 20);
			g.drawRect(100, 250, 45, 20);
			g.drawString("Hit", pos.x-75, pos.y+105);
			g.drawString("Stand", pos.x-32, pos.y+105);

			//
			//			g.drawString( "Your Total Is " + Integer.toString(PTotal) , pos.x-85, pos.y+140);
		} 
	}

} 