package holdem.core.domain;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class CardForParsing implements Serializable {
    private final byte rank;
    private final String suit;
    private boolean kicker = false;
    private boolean handCard = false;

    public CardForParsing(byte rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public CardForParsing(byte rank, String suit, boolean kicker, boolean handCard) {
        this.rank = rank;
        this.suit = suit;
        this.kicker = kicker;
        this.handCard = handCard;
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

    public boolean isHandCard() {
        return handCard;
    }

    public void setHandCard(boolean handCard) {
        this.handCard = handCard;
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
        CardForParsing that = (CardForParsing) o;
        return rank == that.rank &&
                kicker == that.kicker &&
                handCard == that.handCard &&
                Objects.equals(suit, that.suit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit, kicker, handCard);
    }

    @Override
    public String toString() {
        return "CardForParsing{" +
                "rank=" + rank +
                ", suit='" + suit + '\'' +
                ", kicker=" + kicker +
                ", handCard=" + handCard +
                '}';
    }

    public String cardRankSuitInfo() {
        return rank + suit;
    }

}
