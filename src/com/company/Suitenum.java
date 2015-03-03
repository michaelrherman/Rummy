package com.company;

public enum Suitenum {
    SPADES((char) 9824), CLUBS((char) 9827), DIAMONDS((char) 9829), HEARTS((char) 9830);

    public String toString() {
        return String.valueOf(this.pvalue);
    }

    private char pvalue;

    private Suitenum(char pvalue) {
        this.pvalue = pvalue;

    }

    public boolean equals(Suitenum other) {
        return (this.pvalue == other.pvalue);
    }
}
