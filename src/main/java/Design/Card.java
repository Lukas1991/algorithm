package Design;

enum Suit {SPADES,CLUBS,HEARTS,DIAMONDS}
enum Face {ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING}

public class Card {

	private Suit suit;
	private Face face;
	
	public Suit getSuit(){
		return suit;
	}
	
	public Face getFace(){
		return face;
	}
	
	//public void setSuit
	
	//constructor
	Card(Suit suit, Face face){
		this.suit=suit;
		this.face=face;
	}

	Card(Card card){
		this.suit = card.suit;
		this.face = card.face;
	}

}
