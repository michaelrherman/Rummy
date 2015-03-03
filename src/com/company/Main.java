package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        boolean playing = true;
        boolean playbyGame = false;
        boolean playbyTotal = false;
        int finish = 0;

        System.out.println("Welcome to Rummy!");

        while(true) {
        System.out.println("Would you like to play by games or to a total? \n" + "Enter games or total");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();
            if (response.equalsIgnoreCase("games")) {
                playbyGame = true;
                System.out.println("How many games would you like to play?");
                Scanner scanner1 = new Scanner(System.in);
                int games = scanner1.nextInt();
                finish = games;
                break;
            } else if (response.equalsIgnoreCase("total")) {
                playbyTotal = true;
                System.out.println("What total would you like to play to?");
                Scanner scanner2 = new Scanner(System.in);
                int total = scanner2.nextInt();
                finish = total;
                break;
            } else {
                System.out.println("Something went wrong. Please re-enter. \n");
            }
        }

        System.out.println("\r\n");

        LinkedList takenCards = new LinkedList(); //Starts LinkedList of cards which have already been drawn
        LinkedList playedCards = new LinkedList(); //Starts LinkedList of cards which have been played
        LinkedList playerHand = new LinkedList(); //Starts LinkedList representing the player's hand
        LinkedList computerHand = new LinkedList(); //Starts LinkedList representing the computer's hand
        Card1 Card; //Initializes a card as its own object
        Card1 newCard; //Initializes a new card as an object

        //Starts the game by filling the player's hands
        for (int x = 0; playerHand.size() < 10; x++) { //Draws cards until hand is 10 cards
            newCard = Card1.getrandomCard();
            Card = newCard;
            String newcardString = Card.toString();
            boolean retry = takenCards.contains(newcardString); //Verifies it's not one that's already generated
            if (retry == true) { //If it is,
                newCard = Card1.getrandomCard(); //gets a new card
            } else if (retry == false) { //If not,
                takenCards.add(newcardString); //Adds to takenCards
                playerHand.add(newCard); //Adds to playerHand
            }
        }

        //Then fills the computer's hand
        for (int x = 0; computerHand.size() < 10; x++) { //Draws cards until hand is 10 cards
            newCard = Card1.getrandomCard();
            Card = newCard;
            String newcardString = Card.toString();
            boolean retry = takenCards.contains(newcardString); //Verifies it's not one that's already generated
            if (retry == true) { //If it is,
                newCard = Card1.getrandomCard(); //gets a new card
            } else if (retry == false) { //If not,
                takenCards.add(newcardString); //Adds to takenCards
                computerHand.add(newCard); //Adds to playerHand
            }
        }

        int counter = 0;
        int playerScore = 0;
        int computerScore = 0;

        while (playing == true) {
            if (playbyGame == true) {
                if (counter < finish) {
                    printHand(playerHand); //Prints the cards which are in the player's hand
                    printPlayed(playedCards); //Prints the cards which have been played

/*                    LinkedList checkHand; //Starts new LinkedList which will be used to check the hands

                    checkHand = computerHand; //Assigns the computer's hand to checkHand
                    checkBox(checkHand); //Checks the hand for boxes

                    checkHand = playerHand; //Assigns the player's hand to checkHand
                    checkBox(checkHand);//Checks the hand for boxes
                    */
                    counter++;
                } else if (counter >= finish) {
                    // Determine who won
                    playing = false;
                    break;
                }
            } else if (playbyTotal == true) {
                if (playerScore < finish && computerScore < finish) {
                    int playerPoints = 0;
                    int computerPoints = 0;

                    playerPoints++;
                    computerPoints++;

                    playerScore = playerScore + playerPoints;
                    computerScore = computerScore + computerPoints;
                } else if (playerScore >= finish && computerScore < finish) {
                    System.out.println("Player wins!");
                    System.out.println("Player score: "+playerScore);
                    System.out.println("Computer score: "+computerScore);
                    playing = false;
                    break;
                } else if (playerScore < finish && computerScore >= finish) {
                    System.out.println("Computer wins!");
                    System.out.println("Computer score: "+computerScore);
                    System.out.println("Player score: "+playerScore);
                    playing = false;
                    break;
                } else if (playerScore >= finish && computerScore >= finish && playerScore > computerScore) {
                    System.out.println("Player wins!");
                    System.out.println("Player score: "+playerScore);
                    System.out.println("Computer score: "+computerScore);
                    playing = false;
                    break;
                } else if (playerScore >= finish && computerScore >= finish && playerScore < computerScore) {
                    System.out.println("Computer wins!");
                    System.out.println("Computer score: "+computerScore);
                    System.out.println("Player score: "+playerScore);
                    playing = false;
                    break;
                } else {
                    System.out.println("It's a tie!");
                    System.out.println("Player score: "+playerScore);
                    System.out.println("Computer score: "+computerScore);
                    playing = false;
                    break;
                }
            }
        }

        System.out.println("Thanks for playing!");
    }

    public static void printHand(LinkedList playerHand) {
        System.out.println("Player's hand");
        for (int x = 0; x < playerHand.size(); x++) {
            Object[] charlie = (Object[]) playerHand.get(x);
            Object delta; Object echo;
            delta = charlie[0];
            echo = charlie[1];
            System.out.print(delta + "" + echo + " ");
        } System.out.println("\r\n");
    }

    public static void printPlayed(LinkedList playedCards) {
        System.out.println("Cards on table");
        for (int x = 0; x < playedCards.size(); x++) {
            Object[] charlie = (Object[]) playedCards.get(x);
            Object delta; Object echo;
            delta = charlie[0];
            echo = charlie[1];
            System.out.print(delta + "" + echo + " ");
        }
    }
}