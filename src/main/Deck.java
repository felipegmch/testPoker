package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class Deck {

    private ArrayList<Card> deck;

    public Deck (){
        this.deck = new ArrayList<Card>();

        for (Suit suit: Suit.values()) {
            for (Rank rank: Rank.values()) {
                this.deck.add(new Card(suit, rank));
            }
        }
    }

    public ArrayList<Card> getDeck() {
        return this.deck;
    }

    public void shuffle(int players) {
        System.out.printf("Shuffling deck for %d players\n", players);
        Collections.shuffle(this.deck);
    }

    public String toString() {
        return this.deck.stream().map(Object::toString).collect(Collectors.joining(", "));
    }

    public Card pop() {
        return this.deck.remove(0);
    }

    public void push(Card card) {
        this.deck.add(card);
    }

    public int count() {
        return this.deck.size();
    }
}
