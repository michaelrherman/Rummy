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
        Object Card; //Initializes a card as its own object
        Object newCard; //Initializes a new card as an object

        //Starts the game by filling the player's hands
        for (int x = 0; playerHand.size() < 10; x++) { //Draws cards until hand is 10 cards
            newCard = getCard();
            Card = newCard;
            String newcardString = cardtoString(Card);
            boolean retry = takenCards.contains(newcardString); //Verifies it's not one that's already generated
            if (retry == true) { //If it is,
                newCard = getCard(); //gets a new card
            } else if (retry == false) { //If not,
                takenCards.add(newcardString); //Adds to takenCards
                playerHand.add(newCard); //Adds to playerHand
            }
        }

        //Then fills the computer's hand
        for (int x = 0; computerHand.size() < 10; x++) { //Draws cards until hand is 10 cards
            newCard = getCard();
            Card = newCard;
            String newcardString = cardtoString(Card);
            boolean retry = takenCards.contains(newcardString); //Verifies it's not one that's already generated
            if (retry == true) { //If it is,
                newCard = getCard(); //gets a new card
            } else if (retry == false) { //If not,
                takenCards.add(newcardString); //Adds to takenCards
                computerHand.add(newCard); //Adds to computerHand
            }
        }

        int counter = 0;

        while (playing == true) {
            if (playbyGame == true) {
                if (counter < finish) {
/*                    printHand(playerHand); //Prints the cards which are in the player's hand
                    printPlayed(playedCards); //Prints the cards which have been played

                    LinkedList checkHand; //Starts new LinkedList which will be used to check the hands

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
                if (counter < finish) {
                    //The game plays
                } else if (counter >= finish) {
                    // Determine who won
                    playing = false;
                    break;
                }
            }
        }

        System.out.println("Thanks for playing!");
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

        Object alpha = cards[card]; //Gets array cards at position card
        Object bravo = suits[suit]; // Then array suits at position suit
        Object[] drawnCard = {alpha, bravo}; //Puts them into an object array drawnCard

        return drawnCard; //Returns the drawn card.
    }

    public static String cardtoString(Object Card) {
        Object[] lima = (Object[]) Card;
        Object mike = lima[0];
        Object november = lima[1];
        String cardString = mike + "" + november;
    return cardString;
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
    public static void checkBox (LinkedList checkHand) {
        for (int x = 0; x < checkHand.size(); x++) {
            Object[] foxtrot = (Object[]) checkHand.get(x);
            Object golf = foxtrot[0];
            for (int y = x + 1; y < checkHand.size(); y++) {
                Object[] hotel = (Object[]) checkHand.get(y);
                Object india = hotel[0];

                if (golf.equals(india)) {
                    for (int z = x + 2; z < checkHand.size(); z++) {
                    Object[] juliet = (Object[]) checkHand.get(z);
                    Object kilo = juliet[0];
                    if (india.equals(kilo))
                        System.out.println("Box Made!");
                    }
                }
            }
        }
    }
}