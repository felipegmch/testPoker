package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Round {

    private Scanner reader;
    private boolean playAgain;
    private Deck deck;
    private ArrayList<Player> players;
    private String winner;

    public Round(int players, String[] playersNames) {
        this.reader = new Scanner(System.in);
        this.playAgain = true;
        this.deck = new Deck();

        this.players = new ArrayList<>();
        for (int i = 0; i < players; i++) {
            this.players.add(new Player(playersNames[i]));
        }

        this.winner = "";
    }

    public void newRound() {

        while (this.playAgain) {

            this.deck.shuffle(this.players.size());

            this.deal(this.players.size());

            this.flop();

            this.winner();

            this.playAgain = this.playAgain();
        }
        this.reader.close();
        System.out.println("Come back soon.");
    }

    public void deal(int players) {
        System.out.printf("There is %d cards in our decks\n", this.deck.count());
        System.out.printf("Dealing cards for %d players\n", players);

        for (int i = 0; i < 5; i++) {
            for (Player player : this.players) {
                player.receiveCard(this.deck.pop());
            }
        }
        this.sortPlayersCards();
    }

    public void sortPlayersCards() {
        System.out.println("We have sorted the cards for you.");
        for (Player player: this.players) {
            player.sortHand();
        }
        System.out.println(this.toString());
    }

    public void flop() {

        System.out.println("Flop");
        for (Player player: this.players) {
            player.calculateHandOrder();
        }

        this.sortPlayersByHandOrder();

        System.out.println("The ascending rankings are: ");
        for (Player player : this.players) {
            System.out.println(player.toStringByOrder());
        }

        this.winner = this.players.get(this.players.size() - 1).getName();

        System.out.println("Flop");
    }

    public void sortPlayersByHandOrder() {
        Collections.sort(this.players);
    }

    public void winner() {
        System.out.printf("The winner of this round is %s with a hand %s\n",
                this.winner, this.players.get(this.players.size() - 1).getOrder());
        this.retrieveCards();
    }

    public void retrieveCards() {
        for (Player player: this.players) {
            for (Card card: player.getHand()){
                this.deck.push(card);
            }
            player.clearHand();
        }
    }

    public boolean playAgain() {
        System.out.println("Play again? (YES - NO)");
        return this.reader.next().toLowerCase().equals("yes");
    }

    public String toString() {
        return this.players.stream().map(Object::toString).collect(Collectors.joining(", \n"));
    }
}
