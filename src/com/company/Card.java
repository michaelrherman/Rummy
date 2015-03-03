package com.company;

public class Card {

    public Suitenum Suit;
    public Faceenum Face;

    public String toString() {
        String tempString = new String();
        tempString = tempString.concat(Face.toString());
        tempString = tempString.concat(Suit.toString());
        return tempString;


    }
    public Card(Faceenum Face, Suitenum Suit) {
        this.Suit = Suit;
        this.Face = Face;

    }
    public boolean equals(Card other) {

        return((this.Suit == other.Suit) && (this.Face == other.Face));


    }
    public boolean isNextinSuit(Card other) {

        return((this.Suit == other.Suit) && (this.Face.isNext(  other.Face)));
    }
    public boolean isPrevinSuit(Card other) {

        return ((this.Suit == other.Suit) && (this.Face.isPrev(other.Face)));
    }

}
