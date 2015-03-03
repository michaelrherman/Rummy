package com.company;

import java.util.Random;


public class Card1 {
    private static final char spade = 9824; //This is the code you gave on D2L for the Unicode of the suits
    private static final char club = 9827;
    private static final char heart = 9829;
    private static final char diamond = 9830;

    protected static final Object[] cards = {"Ace", 2, 3, 4, 5, 6, 7, 8, 9, 10, "Jack", "Queen", "King"};
    //An array seems proper here since we won't be adding or removing data and we can load the info directly
    protected static final char[] suits = {heart, diamond, club, spade}; //Ditto
    protected int card;
    protected int suit1;

    protected Object face = cards[card]; //Gets array cards at position card
    protected char suit = suits[suit1]; // Then array suits at position suit

    public Card1(Object face, char suit) {
        this.face = face;
        this.suit = suit;
    }

    public Object getFace() {
        return face;
    }

    public char getSuit() {
        return suit;
    }

    public static Object[] getCards() {
        return cards;
    }

    public static char[] getSuits() {
        return suits;
    }

    public static Card1 getrandomCard() {

        Random random = new Random();
        int alpha = random.nextInt(14 - 1); //Picks random card
        int bravo = random.nextInt(5 - 1); //Of a random suit

        Object[] charlie = cards;
        char[] delta = suits;

        Object echo = charlie[alpha];
        char foxtrot = delta [bravo];

        Card1 drawnCard = new Card1(echo, foxtrot);

        return drawnCard; //Returns the drawn card.
    }
}