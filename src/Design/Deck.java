package Design;

import java.util.Random;

//http://www.dreamincode.net/forums/topic/110380-deck-of-cards-using-various-methods/
//use arraylist for cards rather than array is much helpful
public class Deck {
   //constant
	public static final int SUIT_SIZE=4;
	public static final int FACE_SIZE=13;
	
	private Card[] cards=new Card[SUIT_SIZE*FACE_SIZE];
	int cardsNumberLeft; //0-51
	public Card[] getCards(){
		return cards;
	}
	
	Deck(){
		int index=0;
		for(int i=0;i<SUIT_SIZE;i++){
			for(int j=0;j<FACE_SIZE;j++){
				index=i*FACE_SIZE+j;
				//get enum values by index
				cards[index]=new Card(Suit.values()[i],Face.values()[j]);				
			}			
		}
		cardsNumberLeft=51;
	}
	
	Deck(Deck d){
		this.cards=d.getCards();
	}
	
	public Card drawFromDeck(){
		Random generator =  new Random();
		int index=0;
		
		index=generator.nextInt(cardsNumberLeft);
		
		Card temp=cards[index];
		cards[index]=cards[cardsNumberLeft];
		cards[cardsNumberLeft]=null;
		cardsNumberLeft--;
		return temp;
		
	}
	
	public int getCardsNumberLeft(){
		return cardsNumberLeft;
	}
	
	//from the end of the array, randomly pick one element before the it, swap the index and it. leave
	// the randomly picked one at the end of error
	public void shuffle(){
		for(int i=cardsNumberLeft;i>=0;i--){
			Random generator =  new Random();
			int index=generator.nextInt(i+1);
			swap(cards,i,index);
			System.out.println("i: "+i);
			
			
		}
		
	}
	
	private static void swap(Card[] arr, int i, int j){
		Card temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;		
	}
	
	
	public static void main(String[] args){
		
		Deck deck = new Deck();
		deck.shuffle();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
