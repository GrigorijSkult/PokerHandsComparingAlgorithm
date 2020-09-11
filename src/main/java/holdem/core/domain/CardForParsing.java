package holdem.core.domain;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class CardForParsing implements Serializable {
    private final byte rank;
    private final String suit;
    private boolean kicker = false;

    public CardForParsing(byte rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public CardForParsing(byte rank, String suit, boolean kicker) {
        this.rank = rank;
        this.suit = suit;
        this.kicker = kicker;
    }

    public byte getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    public boolean isKicker() {
        return kicker;
    }

    public void setKicker(boolean kicker) {
        this.kicker = kicker;
    }

    public static Comparator<CardForParsing> CardForParsingRankComparator = new Comparator<CardForParsing>() {
        public int compare(CardForParsing c1, CardForParsing c2) {
            byte CardForParsingOne = c1.rank;
            byte CardForParsingTwo = c2.rank;
            //ascending order
            return CardForParsingOne - CardForParsingTwo;
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardForParsing cardForParsing = (CardForParsing) o;
        return rank == cardForParsing.rank &&
                kicker == cardForParsing.kicker &&
                Objects.equals(suit, cardForParsing.suit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit, kicker);
    }

    @Override
    public String toString() {
        return "CardForParsing{" +
                "rank=" + rank +
                ", suit='" + suit + '\'' +
                ", kicker=" + kicker +
                '}';
    }

    public String cardRankSuitInfo() {
        return rank + suit;
    }

}
