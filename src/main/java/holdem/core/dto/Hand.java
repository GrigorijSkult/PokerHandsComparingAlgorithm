package holdem.core.dto;

import holdem.core.domain.CardForParsing;

import java.util.ArrayList;
import java.util.Objects;

public class Hand {
    private final ArrayList<CardForParsing> cards;

    public Hand(ArrayList<CardForParsing> cards) {
        this.cards = cards;
    }

    public ArrayList<CardForParsing> getCards() {
        return cards;
    }

    public CardForParsing getCard(int id) {
        return cards.get(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hand hand = (Hand) o;
        return
                Objects.equals(cards, hand.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    @Override
    public String toString() {
        return "Hand{" +
                ", cards=" + cards +
                '}';
    }
}
