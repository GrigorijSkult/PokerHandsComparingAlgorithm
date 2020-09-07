package holdem.core.dto;

import holdem.core.domain.CardForParsing;

import java.util.ArrayList;
import java.util.Objects;

public class Board {

    private final ArrayList<CardForParsing> cards;

    public Board(ArrayList<CardForParsing> cards) {
        this.cards = cards;
    }

    public ArrayList<CardForParsing> getCards() {
        return cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board that = (Board) o;
        return Objects.equals(cards, that.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    @Override
    public String toString() {
        return "Board{" +
                "cards=" + cards +
                '}';
    }
}
