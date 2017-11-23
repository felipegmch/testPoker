package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class Player implements Comparable<Player>{

    private String name;
    private ArrayList<Card> hand;
    private HandsOrder order;
    private Card highest;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<Card>();
        this.order = HandsOrder.NOTHING;
        this.highest = null;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Card> getHand() {
        return this.hand;
    }

    public HandsOrder getOrder() {
        return this.order;
    }

    public void receiveCard(Card card) {
        this.hand.add(card);
    }

    public void clearHand() {
        this.hand = new ArrayList<Card>();
    }

    public void sortHand() {
        Collections.sort(this.hand);
    }

    public String toString() {
        return this.name + ": " + this.hand.stream().map(Object::toString).collect(Collectors.joining(", "));
    }

    public String toStringByOrder() {
        return this.name + ": " + this.order;
    }

    @Override
    public int compareTo(Player player){
        if (this.order.ordinal() == player.order.ordinal()) {
            if (this.order == HandsOrder.ROYALFLUSH ||
                    this.order == HandsOrder.STRAIGHTFLUSH ||
                    this.order == HandsOrder.FLUSH ||
                    this.order == HandsOrder.STRAIGHT ||
                    this.order == HandsOrder.HIGHCARD) {
                return this.hand.get(4).compareTo(player.hand.get(4));
            }
            if (this.order == HandsOrder.FOURKIND ||
                    this.order == HandsOrder.FULLHOUSE ||
                    this.order == HandsOrder.THREEKIND ||
                    this.order == HandsOrder.TWOPAIR ||
                    this.order == HandsOrder.ONEPAIR) {
                return this.highest.compareTo(player.highest);
            }
            return 0;
        }
        else if(this.order.ordinal() > player.order.ordinal()) {
            return 1;
        }
        return -1;
    }

    public void calculateHandOrder() {

        if (this.hand.get(0).getRank() == Rank.TEN && this.isStraight()) {
            this.order = this.isFlush() ? HandsOrder.ROYALFLUSH : HandsOrder.STRAIGHT;
            return;
        }
        if (this.isStraight() && isFlush()) {
            this.order = HandsOrder.STRAIGHTFLUSH;
            return;
        }
        if (this.isFourKind()) {
            this.order = HandsOrder.FOURKIND;
            return;
        }
        if (this.isFullHouse()) {
            this.order = HandsOrder.FULLHOUSE;
            return;
        }
        if (this.isFlush()) {
            this.order = HandsOrder.FLUSH;
            return;
        }
        if (this.isStraight()) {
            this.order = HandsOrder.STRAIGHT;
            return;
        }
        if (this.isThreeKind()) {
            this.order = HandsOrder.THREEKIND;
            return;
        }
        if (this.isTwoPair()) {
            this.order = HandsOrder.TWOPAIR;
            return;
        }
        if (this.isOnePair()) {
            this.order = HandsOrder.ONEPAIR;
            return;
        }
        this.order = HandsOrder.HIGHCARD;
    }

    public boolean isStraight() {
        int rank = this.hand.get(0).getRank().ordinal();
        for (int i = 1; i < this.hand.size(); i++) {
            if (rank + 1 != this.hand.get(i).getRank().ordinal()) {
                return false;
            }
            rank = this.hand.get(i).getRank().ordinal();
        }
        return true;
    }

    public boolean isFlush() {
        Suit suit = this.hand.get(0).getSuit();
        for (Card card: this.hand) {
            if (card.getSuit() != suit) {
                return false;
            }
        }
        return true;
    }

    public boolean isFourKind() {
        if (this.hand.get(0).getRank().compareTo(this.hand.get(3).getRank()) == 0) {
            this.highest = this.hand.get(3);
            return true;
        }
        if (this.hand.get(1).getRank().compareTo(this.hand.get(4).getRank()) == 0) {
            this.highest = this.hand.get(4);
            return true;
        }
        return false;
    }

    public boolean isFullHouse() {

        if (this.hand.get(0).getRank().compareTo(this.hand.get(2).getRank()) == 0 &&
                this.hand.get(3).getRank().compareTo(this.hand.get(4).getRank()) == 0) {
            this.highest = this.hand.get(2);
            return true;
        }
        if (this.hand.get(0).getRank().compareTo(this.hand.get(1).getRank()) == 0 &&
                this.hand.get(2).getRank().compareTo(this.hand.get(4).getRank()) == 0) {
            this.highest = this.hand.get(4);
            return true;
        }
        return false;
    }

    public boolean isThreeKind() {

        if (this.hand.get(0).getRank().compareTo(this.hand.get(2).getRank()) == 0) {
            this.highest = this.hand.get(2);
            return true;
        }
        if (this.hand.get(1).getRank().compareTo(this.hand.get(3).getRank()) == 0) {
            this.highest = this.hand.get(3);
            return true;
        }
        if (this.hand.get(2).getRank().compareTo(this.hand.get(4).getRank()) == 0) {
            this.highest = this.hand.get(4);
            return true;
        }
        return false;
    }

    public boolean isTwoPair() {
        int pairs = 0;
        for (int i = 1; i < this.hand.size(); i++) {
            if (this.hand.get(i-1).getRank().compareTo(this.hand.get(i).getRank()) == 0) {
                this.highest = this.hand.get(i);
                pairs++;
            }
        }
        return pairs == 2;
    }

    public boolean isOnePair() {

        int pairs = 0;
        for (int i = 1; i < this.hand.size(); i++) {
            if (this.hand.get(i-1).getRank().compareTo(this.hand.get(i).getRank()) == 0) {
                this.highest = this.hand.get(i);
                pairs++;
            }
        }
        return pairs == 1;
    }

}
