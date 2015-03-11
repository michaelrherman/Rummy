package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        boolean playing = true; //The game starts and playing is true-mh
        boolean playbyGame = false; //Initialized false-mh
        boolean playbyTotal = false; //Initialized false-mh
        int finish = 0; //Initialized to 0

        System.out.println("Welcome to Rummy!");
//        compareCard(); Used for testing -mh

        while (true) {
            System.out.println("Would you like to play by games or to a total? \n" + "Enter games or total");
            Scanner scanner = new Scanner(System.in);
            String response = scanner.nextLine(); //Gets player's decision to play by games or by total-mh
            if (response.equalsIgnoreCase("games")) {
                playbyGame = true;
                System.out.println("How many games would you like to play?");
                Scanner scanner1 = new Scanner(System.in);
                int games = scanner1.nextInt(); //Gets how many games they'd like to play-mh
                finish = games; //Sets the finish to the number of games they'd like to play-mh
                break;
            } else if (response.equalsIgnoreCase("total")) {
                playbyTotal = true;
                System.out.println("What total would you like to play to?");
                Scanner scanner2 = new Scanner(System.in);
                int total = scanner2.nextInt(); //Gets the total they'd like to play to-mh
                finish = total; //Sets the finish to the total they'd like to play to-mh
                break;
            } else {
                System.out.println("Something went wrong. Please re-enter. \n");
            }
        }

        System.out.println("\r\n");

        int counter = 0; //Sets a counter to zero for playing by games-mh
        int playerScore = 0; //Initializes player's score to zero-mh
        int computerScore = 0; //Initializes computer's score to zero-mh

        while (playing == true) {

            LinkedList<Card> takenCards = new LinkedList<Card>(); //Starts LinkedList of cards which have already been drawn-mh
            LinkedList<Card> playedCards = new LinkedList<Card>(); //Starts LinkedList of cards which have been played-mh
            LinkedList<Card> playerHand = new LinkedList<Card>(); //Starts LinkedList representing the player's hand-mh
            LinkedList<Card> computerHand = new LinkedList<Card>(); //Starts LinkedList representing the computer's hand-mh

            //Starts the game by filling the player's hands-mh
            LinkedList<Card> hand = playerHand;
            for (int x = 0; playerHand.size() < 10; x++) { //Draws cards until hand is 10 cards-mh
                getnewCard(hand, takenCards);
            }
            //Then fills the computer's hand-mh
            hand = computerHand;
            for (int x = 0; computerHand.size() < 10; x++) { //Draws cards until hand is 10 cards-mh
                getnewCard(hand, takenCards);
            }

            if (playbyGame == true) { //Plays by game-mh
                if (counter < finish) {
                    printHand(playerHand); //Prints the cards which are in the player's hand-mh
                    printPlayed(playedCards); //Prints the cards which have been played-mh
                    draw(playerHand, takenCards);
                    toPlay(playerHand);
                    counter++;
                } else if (counter >= finish) { //Once chosen number of games is played, determines winner-mh
                    if (playerScore > computerScore) {
                        System.out.println("Player wins!");
                        System.out.println("Players score: " + playerScore);
                        System.out.println("Computer score: " + computerScore);
                    } else if (playerScore < computerScore) {
                        System.out.println("Computer wins!");
                        System.out.println("Computer score: " + computerScore);
                        System.out.println("Players score: " + playerScore);
                    } else {
                        System.out.println("It's a tie."); //Unless it's a tie-mh
                        System.out.println("Players score: " + playerScore);
                        System.out.println("Computer score: " + computerScore);
                    }
                    playing = false;
                    break;
                }
            } else if (playbyTotal == true) { //Plays by total-mh
                if (playerScore < finish && computerScore < finish) {
                    int playerPoints = 0;
                    int computerPoints = 0;

                    printHand(playerHand); //Prints the cards which are in the player's hand-mh
                    printPlayed(playedCards); //Prints the cards which have been played-mh
                    draw(playerHand, takenCards);
                    toPlay(playerHand);
                    //EDWIN - Add your comparison code here
                    playerScore = playerScore + playerPoints;
                    computerScore = computerScore + computerPoints;
                } else if (playerScore >= finish && computerScore < finish) { //Series of checks for winner once a score goes over chosen total-mh
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

        System.out.println("Thanks for playing!"); //You're welcome-mh
    }

    public static void printHand(LinkedList<Card> playerHand) { //Prints the player's hand-mh
        System.out.println("Player's hand");
        Integer counter = 0;
        for (Card card1 : playerHand) {
            String victor = counter.toString(); //Creates leading integer so user can indicate their choices-mh
            int william = card1.getFace();
            char yankee = card1.getSuit();
            if (william == 1) {
                System.out.print(victor + ".)" + "Ace" + yankee + " "); //Prints ace if face is 1-mh
            } else if (william == 11) {
                System.out.print(victor + ".)" + "Jack" + yankee + " "); //Prints jack if face is 11-mh
            } else if (william == 12) {
                System.out.print(victor + ".)" + "Queen" + yankee + " "); //Prints queen if face is 12-mh
            } else if (william == 13) {
                System.out.print(victor + ".)" + "King" + yankee + " "); //Prints king if face is 13-mh
            } else {
                System.out.print(victor + ".)" + william + "" + yankee + " "); //Otherwise prints int value of face-mh
            }
            counter++;
        }
    }

    public static void printPlayed(LinkedList<Card> playedCards) { //Prints the cards that have been played-mh
        System.out.println("\n");
        System.out.println("Cards on table");
        for (Card card1 : playedCards) { //No need for a counter here-mh
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

    public static LinkedList<Card> getnewCard(LinkedList<Card> hand, LinkedList<Card> takenCards) { //Gets a new card-mh
        Card newCard = Card.getrandomCard(); //Calls getrandomCard() from Card class-mh

        boolean retry = takenCards.contains(newCard); //Verifies it's not one that's already generated-mh
        if (retry == true) { //If it is,
            newCard = Card.getrandomCard(); //gets a new card-mh
        } else if (retry == false) { //If not,
            takenCards.add(newCard); //Adds to takenCards
            hand.add(newCard); //Adds to playerHand-mh
        }
        return hand; //
    }

    public static LinkedList<Card> draw(LinkedList<Card> playerHand, LinkedList<Card> takenCards) { //Draws a card-mh
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
                playerHand = getnewCard(hand, takenCards); //Gets another card-mh
                printHand(playerHand); //Prints the player's new hand-mh
            } else {
                System.out.println("Input error - try again.");
            }
        }
        return playerHand;
    }

    public static LinkedList<Card> toPlay(LinkedList<Card> playerHand) { //Takes input from player on which cards to play.-mh
        LinkedList<Card> toPlay = new LinkedList<Card>();
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        boolean cont = true;

        while (cont == true) {
            System.out.println("What cards would you like to play?");
            int position = scanner.nextInt(); //Gets position of card to play-mh
            Card play = playerHand.get(position); //Gets card at that position-mh
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

    public int checkRun(LinkedList<Card> toPlay) {
        Integer Counter = 0; //Counts how many runs there are. ED
        Integer inaRow = 0; //Counts how many cards there are in a set. ED
        Card firstCard = new Card(Card.cards[1], Card.suits[1]);
        Card previousCard = new Card(Card.cards[1], Card.suits[1]);//This is just a throwaway to initialize previousCard.

        for (Card testCard : toPlay) {
            if (Counter > 0) {
                if (previousCard.isNextinSuit(testCard)) {//THis checks for a run by checking the next facevalue. ED
                    if (inaRow == 0) {
                        firstCard = testCard;
                    }
                    inaRow++;
                    if (inaRow >= 3)//this checks for three in a row or more  (since runs more cards) ED
                    {
                        System.out.println(firstCard + "is first. Three in a row");
                    }
                } else {
                    inaRow = 0;
                }
            }
            previousCard = testCard;
            Counter++;
        }
        return inaRow;

    }

    public int checkSet(LinkedList<Card> toPlay) {
        Integer Counter = 0;// Counts how many sets there are.
        Integer inaSet = 0;// Counts how many are in a set.
        Card firstCard = new Card(Card.cards[1], Card.suits[1]);//
        Card previousCard = new Card(Card.cards[1], Card.suits[1]);//This is just a throwaway to initialize previousCard to find the first in the set. ED

        for (Card testCard : toPlay) {
            if (Counter > 0) {
                if (previousCard.checkSet(testCard)) {
                    if (inaSet == 0) {
                        firstCard = testCard;
                    }
                    inaSet++;
                    if (inaSet == 3) {
                        System.out.println(firstCard + "is first. Three in a set");//this checks for three of a kind.
                    }
                } else {
                    inaSet = 0;
                }
            }
            previousCard = testCard;
            Counter++;
        }
        return inaSet;
    }
}


//    public static void compareCard() {
//        Card cardA = new Card(1, Card.spade);
//        Card cardB = new Card(1, Card.club);
//        Card cardC = new Card(2, Card.diamond);
//
//        LinkedList<Card> toCheck = new LinkedList<Card>();
//        toCheck.add(cardA);
//        toCheck.add(cardB);
//        toCheck.add(cardC);
//
//        for (int x = 0; x < toCheck.size(); x++) {
//            Card cardX = toCheck.get(x);
//            int cardXface = cardX.getFace();
//            char cardXsuit = cardX.getSuit();
//
//            for (Card cardY: toCheck) {
//                int cardYface = cardY.getFace();
//                char cardYsuit = cardY.getSuit();
//
//                if (cardXface == cardYface && cardXsuit != cardYsuit) {//checks cards for matches. ED
//                    System.out.println("Match");
//                } else if (cardXface == cardYface && cardXsuit == cardYsuit) {
//                    System.out.println("Same Card");
//                } else {
//                    System.out.println("Other");
//                }
//            }
//            System.out.println("\r\n");
//        }
//    }
//}