package com.company;

/**
 * Created by Edwin on 3/1/2015.
 */
public enum Faceenum {
    ACE("Ace", 1), TWO("2", 2), THREE("3", 3), FOUR("4", 4), FIVE("5", 5), SIX("6", 6), SEVEN("7", 7), EIGHT("8", 8),
    NINE("9", 9), TEN("10", 10), JACK("Jack", 11), QUEEN("Queen", 12), KING("King", 13);


    public String toString() {
        return String.valueOf(this.fvalue);}

    private int fvalue;
    private String fstring;

    private Faceenum(String fstring, int fvalue) {
        this.fvalue = fvalue;
        this.fstring = fstring;
    }

    public boolean equals(Faceenum other) {
        return (this.fvalue == other.fvalue);
    }
    public boolean isNext(Faceenum other) {
        return ((this.fvalue+1) == other.fvalue);
    }
    public boolean isPrev(Faceenum other) {
        return (this.fvalue == (other.fvalue+1 ));
    }
}
