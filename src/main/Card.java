package main;

public class Card implements Comparable<Card> {

    private Suit suit;
    private Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public Rank getRank() {
        return this.rank;
    }

    @Override
    public int compareTo(Card card) {
        if (this.rank.ordinal() == card.rank.ordinal()) {
            if (this.suit.ordinal() == card.suit.ordinal()) {
                return 0;
            }
            else if (this.suit.ordinal() > card.suit.ordinal()) {
                return 1;
            }
            return -1;
        }
        else if (this.rank.ordinal() > card.rank.ordinal()) {
            return 1;
        }
        return -1;
    }

    public String toString() {
        return this.rank.toString() + " of " + this.suit.toString();
    }
}
