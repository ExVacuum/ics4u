import java.awt.image.BufferedImage;

public class Card {

	int suit, value;
	BufferedImage image;
	
	
	Card (int suit, int v) {
		this.suit = suit;
		this.value = v;		
	}
	
	public int getSuit() {
        return suit;
	}
	
	public void setSuit(int suit) {
		this.suit = suit;
	}
	public int getValue() {
        return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
}
