package com.company;

import java.util.*;

public class Card {
    protected static final char spade = 9824; //This is the code you gave on D2L for the Unicode of the suits-mh
    protected static final char club = 9827;
    protected static final char heart = 9829;
    protected static final char diamond = 9830;

    protected static final int[] cards = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    //An array seems proper here since we won't be adding or removing data and we can load the info directly-mh
    protected static final char[] suits = {heart, diamond, club, spade}; //Ditto
    protected int card;
    protected int suit1;

    protected int face = cards[card]; //Gets array cards at position card
    protected char suit = suits[suit1]; // Then array suits at position suit-mh

    public Card(int face, char suit) {
        this.face = face;
        this.suit = suit;
    }

    public int getFace() {
        return face;
    }

    public char getSuit() {
        return suit;
    }

    public static int[] getCards() {
        return cards;
    }

    public static char[] getSuits() {
        return suits;
    }

    public static Card getrandomCard() {

        Random random = new Random();
        int alpha = random.nextInt(14 - 1); //Picks random card-mh
        int bravo = random.nextInt(5 - 1); //Of a random suit-mh

        int[] charlie = cards;
        char[] delta = suits;

        int echo = charlie[alpha];
        char foxtrot = delta[bravo];

        Card drawnCard = new Card(echo, foxtrot);

        return drawnCard; //Returns the drawn card.-mh
    }

    public boolean isNextinSuit(Card other) {//Finds ascending sequence between cards in the same suit(helps check for runs). ED

        return ((this.suit == other.suit) && (this.isNext(face)));
    }

    public boolean checkSet(Card other) {//This checks for Sets only for face value. (helps find pairs and triplets. ED

        return ((this.face == other.face));
    }

    public boolean equals(Card other) { //checks for equality of the facevalue of the cards (used in Card Class) ED
        return (this.face == other.face);
    }

    public boolean isNext(int Card) {
        return ((this.face + 1) == face);
    }//Checks if the value of the next card in face is the next one. ED

    public boolean isPrev(Card other) {
        return (this.face == (other.face + 1));
    } //Checks if the value
//of  the previous card is sequential. ED

}