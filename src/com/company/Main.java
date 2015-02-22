package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to Rummy!");
        System.out.println("Would you like to play by games or to a total? \n" + "Enter games or total");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("games")) {
            System.out.println("How many games would you like to play?");
            Scanner scanner1 = new Scanner(System.in);
            int games = scanner1.nextInt();
        } else if (response.equalsIgnoreCase("total")) {
            System.out.println("What total would you like to play to?");
            Scanner scanner2 = new Scanner(System.in);
            int total = scanner2.nextInt();
        } else {
            System.out.println("Something went wrong. Please start again.");
        }

        System.out.println("\r\n");

        LinkedList takenCards = new LinkedList(); //Starts LinkedList of cards which have already been drawn
        LinkedList playedCards = new LinkedList(); //Starts LinkedList of cards which have been played
        LinkedList playerHand = new LinkedList(); //Starts LinkedList representing the player's hand
        LinkedList computerHand = new LinkedList(); //Starts LinkedList representing the computer's hand

        //Starts the game by filling the players hands
        Object newCard = new Object();
        for (int x = 0; playerHand.size() < 10; x++) { //Draws cards until hand is 10 cards
            newCard = getCard();
            boolean retry = takenCards.contains(newCard); //Verifies it's not one that's already generated
            if (retry == true) { //If it is,
                newCard = getCard(); //gets a new card
            } else if (retry == false) { //If not,
                takenCards.add(newCard); //Adds to takenCards
                playerHand.add(newCard); //Adds to playerHand
            }
        }

        for (int x = 0; computerHand.size() < 10; x++) {
            newCard = getCard();
            boolean retry = takenCards.contains(newCard);
            if (retry == true) { //If it is,
                newCard = getCard(); //gets a new card
            } else if (retry == false) { //If not,
                takenCards.add(newCard); //Adds to takenCards
                computerHand.add(newCard); //Adds to computerHand
            }
        }
        printHand(playerHand);
        printPlayed(playedCards);
    }

    public static Object getCard() {

        char spade = 9824; //This is the code you gave on D2L for the Unicode of the suits
        char club = 9827;
        char heart = 9829;
        char diamond = 9830;

        Object[] cards = {"Ace", 2, 3, 4, 5, 6, 7, 8, 9, 10, "Jack", "Queen", "King"};
        //An array seems proper here since we won't be adding or removing data and we can load the info directly
        Object[] suits = {heart, diamond, club, spade}; //Ditto

        Random random = new Random();
        int card = random.nextInt(14 - 1); //Picks random card
        int suit = random.nextInt(5 - 1); //Of a random suit

        String drawnCard = (cards[card] + "" + suits[suit]); //Gets array cards at position card, then array suits at position suit

        return drawnCard; //Returns the drawn card.
    }

    public static void printHand(LinkedList playerHand) {
        System.out.println("Player's hand");
        for (int x = 0; x < playerHand.size(); x++) {
            System.out.print(playerHand.get(x) + " ");
        } System.out.println("\r\n");
    }

    public static void printPlayed (LinkedList playedCards) {
        System.out.println("Cards on table");
        for (int x = 0; x < playedCards.size(); x++) {
            System.out.println(playedCards.get(x) + " ");
        }
    }
}