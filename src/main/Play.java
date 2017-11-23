package main;

import java.util.Scanner;

public class Play {

    public static void main(String[] args) {

        System.out.println("Welcome to the Casino.");

        Scanner reader = new Scanner(System.in);
        System.out.println("Your name is?");
        String playerName = reader.next();

        System.out.println("How many players are on the table? (2 - 10)");
        int players = reader.nextInt();

        String[] playersNames = new String[players];
        playersNames[0] = playerName;
        for (int i = 1; i < players; i++) {
            playersNames[i] = "Player " + i;
        }

        Round round = new Round(players, playersNames);
        round.newRound();
    }


}
