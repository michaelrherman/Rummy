package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        boolean playing = true; //The game starts and playing is true
        boolean playbyGame = false; //Initialized false
        boolean playbyTotal = false; //Initialized false
        int finish = 0; //Initialized to 0

        System.out.println("Welcome to Rummy!");

        while (true) {
            System.out.println("Would you like to play by games or to a total? \n" + "Enter games or total");
            Scanner scanner = new Scanner(System.in);
            String response = scanner.nextLine(); //Gets player's decision to play by games or by total
            if (response.equalsIgnoreCase("games")) {
                playbyGame = true;
                System.out.println("How many games would you like to play?");
                Scanner scanner1 = new Scanner(System.in);
                int games = scanner1.nextInt(); //Gets how many games they'd like to play
                finish = games; //Sets the finish to the number of games they'd like to play
                break;
            } else if (response.equalsIgnoreCase("total")) {
                playbyTotal = true;
                System.out.println("What total would you like to play to?");
                Scanner scanner2 = new Scanner(System.in);
                int total = scanner2.nextInt(); //Gets the total they'd like to play to
                finish = total; //Sets the finish to the total they'd like to play to
                break;
            } else {
                System.out.println("Something went wrong. Please re-enter. \n");
            }
        }

        System.out.println("\r\n");

        int counter = 0; //Sets a counter to zero for playing by games
        int playerScore = 0; //Initializes player's score to zero
        int computerScore = 0; //Initializes computer's score to zero

        while (playing == true) {

            LinkedList<Card> takenCards = new LinkedList<Card>(); //Starts LinkedList of cards which have already been drawn
            LinkedList<Card> playedCards = new LinkedList<Card>(); //Starts LinkedList of cards which have been played
            LinkedList<Card> playerHand = new LinkedList<Card>(); //Starts LinkedList representing the player's hand
            LinkedList<Card> computerHand = new LinkedList<Card>(); //Starts LinkedList representing the computer's hand

            //Starts the game by filling the player's hands
            LinkedList<Card> hand = playerHand;
            for (int x = 0; playerHand.size() < 10; x++) { //Draws cards until hand is 10 cards
                getnewCard(hand, takenCards);
            }
            //Then fills the computer's hand
            hand = computerHand;
            for (int x = 0; computerHand.size() < 10; x++) { //Draws cards until hand is 10 cards
                getnewCard(hand, takenCards);
            }

            if (playbyGame == true) { //Plays by game
                if (counter < finish) {
                    printHand(playerHand); //Prints the cards which are in the player's hand
                    printPlayed(playedCards); //Prints the cards which have been played
                    draw(playerHand, takenCards);
                    toPlay(playerHand);
                    counter++;
                } else if (counter >= finish) { //Once chosen number of games is played, determines winner
                    if (playerScore > computerScore) {
                        System.out.println("Player wins!");
                        System.out.println("Players score: " + playerScore);
                        System.out.println("Computer score: " + computerScore);
                    } else if (playerScore < computerScore) {
                        System.out.println("Computer wins!");
                        System.out.println("Computer score: " + computerScore);
                        System.out.println("Players score: " + playerScore);
                    } else {
                        System.out.println("It's a tie."); //Unless it's a tie
                        System.out.println("Players score: " + playerScore);
                        System.out.println("Computer score: " + computerScore);
                    }
                    playing = false;
                    break;
                }
            } else if (playbyTotal == true) { //Plays by total
                if (playerScore < finish && computerScore < finish) {
                    int playerPoints = 0;
                    int computerPoints = 0;

                    printHand(playerHand); //Prints the cards which are in the player's hand
                    printPlayed(playedCards); //Prints the cards which have been played
                    draw(playerHand, takenCards);
                    toPlay(playerHand);
                    //EDWIN - Add your comparison code here
                    playerScore = playerScore + playerPoints;
                    computerScore = computerScore + computerPoints;
                } else if (playerScore >= finish && computerScore < finish) { //Series of checks for winner once a score goes over chosen total
                    System.out.println("Player wins!");
                    System.out.println("Player score: " + playerScore);
                    System.out.println("Computer score: " + computerScore);
                    playing = false;
                    break;
                } else if (playerScore < finish && computerScore >= finish) {
                    System.out.println("Computer wins!");
                    System.out.println("Computer score: " + computerScore);
                    System.out.println("Player score: " + playerScore);
                    playing = false;
                    break;
                } else if (playerScore >= finish && computerScore >= finish && playerScore > computerScore) {
                    System.out.println("Player wins!");
                    System.out.println("Player score: " + playerScore);
                    System.out.println("Computer score: " + computerScore);
                    playing = false;
                    break;
                } else if (playerScore >= finish && computerScore >= finish && playerScore < computerScore) {
                    System.out.println("Computer wins!");
                    System.out.println("Computer score: " + computerScore);
                    System.out.println("Player score: " + playerScore);
                    playing = false;
                    break;
                } else {
                    System.out.println("It's a tie.");
                    System.out.println("Player score: " + playerScore);
                    System.out.println("Computer score: " + computerScore);
                    playing = false;
                    break;
                }
            }
        }

        System.out.println("Thanks for playing!"); //You're welcome
    }

    public static void printHand(LinkedList<Card> playerHand) { //Prints the player's hand
        System.out.println("Player's hand");
        Integer counter = 0;
        for (Card card1 : playerHand) {
            String victor = counter.toString(); //Creates leading integer so user can indicate their choices
            int william = card1.getFace();
            char yankee = card1.getSuit();
            if (william == 1) {
                System.out.print("Ace" + yankee + " ");
            } else if (william == 11) {
                System.out.print("Jack" + yankee + " ");
            } else if (william == 12) {
                System.out.print("Queen" + yankee + " ");
            } else if (william == 13) {
                System.out.print("King" + yankee + " ");
            } else {
                System.out.print(william + "" + yankee + " ");
            }
            counter++;
        }
    }

    public static void printPlayed(LinkedList<Card> playedCards) { //Prints the cards that have been played
        System.out.println("\n");
        System.out.println("Cards on table");
        for (Card card1 : playedCards) { //No need for a counter here
            int william = card1.getFace();
            char yankee = card1.getSuit();
            if (william == 1) {
                System.out.print("Ace " + yankee + " ");
            } else if (william == 11) {
                System.out.print("Jack" + yankee + " ");
            } else if (william == 12) {
                System.out.print("Queen " + yankee + " ");
            } else if (william == 13) {
                System.out.print("King " + yankee + " ");
            } else {
                System.out.print(william + "" + yankee + " ");
            }
        }
    }

    public static LinkedList<Card> getnewCard(LinkedList<Card> hand, LinkedList<Card> takenCards) { //Gets a new card
        Card newCard = Card.getrandomCard(); //Calls getrandomCard() from Card1 class

        boolean retry = takenCards.contains(newCard); //Verifies it's not one that's already generated
        if (retry == true) { //If it is,
            newCard = Card.getrandomCard(); //gets a new card
        } else if (retry == false) { //If not,
            takenCards.add(newCard); //Adds to takenCards
            hand.add(newCard); //Adds to playerHand
        }
        return hand; //
    }

    public static LinkedList<Card> draw(LinkedList<Card> playerHand, LinkedList<Card> takenCards) { //Draws a card
        Scanner scanner = new Scanner(System.in);
        boolean cont = true;

        while (cont == true) {
            System.out.println("\n");
            System.out.println("Would you like to draw a card? \n Y or N");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("N")) {
                cont = false;
                break;
            } else if (response.equalsIgnoreCase("Y")) {
                LinkedList<Card> hand = playerHand;
                playerHand = getnewCard(hand, takenCards); //Gets another card
                printHand(playerHand); //Prints the player's new hand
            } else {
                System.out.println("Input error - try again.");
            }
        }
        return playerHand;
    }

    public static LinkedList<Card> toPlay(LinkedList<Card> playerHand) { //Takes input from player on which cards to play.
        LinkedList<Card> toPlay = new LinkedList<Card>();
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        boolean cont = true;

        while (cont == true) {
            System.out.println("What cards would you like to play?");
            int position = scanner.nextInt(); //Gets position of card to play
            Card play = playerHand.get(position); //Gets card at that position
            toPlay.add(play);
            System.out.println("Would you like to play another card? \n Y or N");
            String response = scanner1.nextLine();
            if (response.equalsIgnoreCase("N")) {
                cont = false;
                break;
            }
        }
        return toPlay;
    }
}