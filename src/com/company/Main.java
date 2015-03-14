package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        LinkedList<Card> takenCards = new LinkedList<Card>(); //Starts LinkedList of cards which have already been drawn-mh
        LinkedList<Card> playedCards = new LinkedList<Card>(); //Starts LinkedList of cards which have been played-mh
        LinkedList<Card> playerHand = new LinkedList<Card>(); //Starts LinkedList representing the player's hand-mh
        LinkedList<Card> computerHand = new LinkedList<Card>();


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
        int playerPoints = 0; //Initializes player points to zero-mh
        int computerPoints = 0; //Initializes computer points to zero-mh

        while (playing == true) {

            //Starts LinkedList representing the computer's hand-mh

            //Starts the game by filling the player's hands-mh
            LinkedList<Card> hand = playerHand;
            for (int x = 0; playerHand.size() < 10; x++) { //Draws cards until hand is 10 cards-mh
                getnewCard(hand, takenCards);
            }
            //Then fills the computer's hand-mh
            hand = computerHand;
            for (int x = 0; computerHand.size() < 10; x++) { //Draws cards until hand is 10 cards-mh
                getnewCard(hand, takenCards);
/*                if (x > 100) {
                    System.out.println("Something busted"); //Was used in testing-mh
                    break;
                }*/
            }

            if (playbyGame == true) { //Plays by game-mh
                boolean complete = false; //Continues game until complete -mh

                while (counter < finish) {
                    printHand(playerHand); //Prints the cards which are in the player's hand-mh
                    draw(playerHand, takenCards); //Asks the player if they want to draw-mh
                    toPlay(playerHand); //Plays the player's hand-mh
                    toPlayComputer(computerHand); //Plays the computer's hand-mh

                    if (playerHand.isEmpty()) { //If the player goes out
                        int points = 0;
                        for (Card card : computerHand) //Totals points for computer hand -mh
                            points = card.getFace();
                        if (points > 10) { //If it's greater than 10
                            playerPoints = playerPoints + 10; //Adds 10
                        } else if (points < 10) {
                            playerPoints = playerPoints + points; //Otherwise adds the face value -mh
                        }
                        complete = true;
                    } else if (computerHand.isEmpty()) { //If the computer goes out
                        int points = 0;
                        for (Card card : playerHand) //Totals points for player hand -mh
                            points = card.getFace();
                        if (points > 10) { //If it's greater than 10
                            playerPoints = playerPoints + 10; //Adds 10
                        } else if (points < 10) {
                            playerPoints = playerPoints + points; //Otherwise adds the face value -mh
                        }
                        complete = true;
                    }

                    if (complete == true) { //Once the game is complete
                        playerScore = playerScore + playerPoints; //Adds player's points to score
                        computerScore = computerScore + computerPoints; //Adds computer's points to score-mh
                        counter++; //Increase counter by one -mh
                        break;
                    }

                    while (counter >= finish) { //Once chosen number of games is played, determines winner-mh
                        if (playerScore < computerScore) { //If computer has more points, declares player the winner
                            System.out.println("Player wins!");
                            System.out.println("Players score: " + playerScore);
                            System.out.println("Computer score: " + computerScore);
                        } else if (playerScore > computerScore) { //If player has more points, declares computer the winner
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
                }
            } else if (playbyTotal == true) { //Plays by total-mh
                boolean end = false; //Acts as 1st-to-the-post check
                boolean complete = false;

                while (playerScore < finish && computerScore < finish) { //Plays as long as noone has exceeded total score
                    playerPoints = 0; //Resets player and computer points (but not score) each hand
                    computerPoints = 0;

                    printHand(playerHand); //Prints the cards which are in the player's hand-mh
                    printPlayed(playedCards); //Prints the cards which have been played-mh
                    draw(playerHand, takenCards); //Asks the player if they want to draw-mh
                    toPlay(playerHand); //Plays the player's hand-mh
                    toPlayComputer(computerHand); //Plays the computer's hand-mh


                    if (playerHand.isEmpty()) { //If the player goes out
                        int points = 0;
                        for (Card card : computerHand) //Totals points for computer hand -mh
                            points = card.getFace();
                        if (points > 10) { //If it's greater than 10
                            playerPoints = playerPoints + 10; //Adds 10
                        } else if (points < 10) {
                            playerPoints = playerPoints + points; //Otherwise adds the face value -mh
                        }
                        complete = true;
                    } else if (computerHand.isEmpty()) { //If the computer goes out
                        int points = 0;
                        for (Card card : playerHand) //Totals points for player hand -mh
                            points = card.getFace();
                        if (points > 10) { //If it's greater than 10
                            playerPoints = playerPoints + 10; //Adds 10
                        } else if (points < 10) {
                            playerPoints = playerPoints + points; //Otherwise adds the face value -mh
                        }
                        complete = true;
                    }
                }

                if (complete == true) { //Once complete
                    playerScore = playerScore + playerPoints; //Adds player's points to score
                    computerScore = computerScore + computerPoints; //Adds computer's points to score-mh
                    if (playerScore > finish) { //Checks if player exceeded total-mh
                        end = true;
                        break;
                    } else if (computerScore > finish) { //Checks if computer exceeded total-mh
                        end = true;
                        break;
                    }

                }

                while (end == true) { //Once total is exceeded
                    if (playerScore < computerScore) { //If computer score is greater, player wins
                        System.out.println("Player wins!");
                        System.out.println("Players score: " + playerScore);
                        System.out.println("Computer score: " + computerScore);
                        playing = false;
                        break;
                    } else if (playerScore > computerScore) { //If player score is greater, computer wins
                        System.out.println("Computer wins!");
                        System.out.println("Computer score: " + computerScore);
                        System.out.println("Players score: " + playerScore);
                        playing = false;
                        break;
                    } else {
                        System.out.println("It's a tie."); //Unless it's a tie-mh
                        System.out.println("Players score: " + playerScore);
                        System.out.println("Computer score: " + computerScore);
                        playing = false;
                        break;
                    }
                }
            }

            System.out.println("Thanks for playing!"); //You're welcome-mh
        }
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
                checkRun(toPlay);//takes the toPlay Linkedlist and sends it to checkRun method. ED
                checkSet(toPlay);//takes the toPlay Linkedlist and sends it to checkSet method. ED
                break;
            }
        }
        return toPlay;
    }


    public static LinkedList<Card> toPlayComputer(LinkedList<Card> computerHand) {
        LinkedList<Card> toPlay = new LinkedList<Card>();
        toPlay = computerHand;
        checkRun(toPlay);
        checkSet(toPlay);
        return toPlay;
    }

    public static int checkRun(LinkedList<Card> toPlay) {
        LinkedList<Card> playedRun = new LinkedList<Card>();
        Integer Counter = 0; //Counts how many runs there are. ED
        Integer inaRow = 0; //Counts how many cards there are in a set. ED
        Card firstCard = new Card(Card.cards[1], Card.suits[1]);
        Card previousCard = new Card(Card.cards[1], Card.suits[1]);//This is just a throwaway to initialize previousCard.

        for (Card testCard : toPlay) {//loops through the toPlay Linkedlist. ED
            if (Counter > 0) {//
                if (previousCard.isNextinSuit(testCard)) {//THis checks for a run by checking the next facevalue. ED
                    if (inaRow == 0) {
                        firstCard = testCard;
                    }
                    inaRow++;
                    if (inaRow >= 3)//this checks for three in a row or more  (since runs can be more cards) ED
                    {
                        System.out.println(firstCard + "is first. Three in a row");
                        playedRun.addAll(toPlay);
                        printPlayed(playedRun);
                    }
                } else {
                    inaRow = 0;
                    System.out.println("not a run. No cards transferred to table.");
                    printPlayed(playedRun);
                }
            }
            previousCard = testCard;
            Counter++;
        }
        return inaRow;

    }

    public static int checkSet(LinkedList<Card> toPlay) {
        LinkedList<Card> playedSet = new LinkedList<Card>();
        Integer Counter = 0;// Counts how many sets there are.
        Integer inaSet = 0;// Counts how many are in a set.
        Card firstCard = new Card(Card.cards[1], Card.suits[1]);//
        Card previousCard = new Card(Card.cards[1], Card.suits[1]);//This is just a throwaway to initialize previousCard to find the first in the set. ED

        for (Card testCard : toPlay) {//loops toPlay linkedList to check for cards that player wants to play.
            if (Counter > 0) {
                if (previousCard.checkSet(testCard)) {
                    if (inaSet == 0) {
                        firstCard = testCard;
                    }
                    inaSet++;
                    if (inaSet == 3) {
                        System.out.println(firstCard + "is first." + toPlay + "Three in a set");//this checks for three of a kind.
                        playedSet.addAll(toPlay);
                        printPlayed(playedSet);
                    }
                } else {
                    inaSet = 0;
                    System.out.println("not a set. No cards transferred to table.");
                    printPlayed(playedSet);
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