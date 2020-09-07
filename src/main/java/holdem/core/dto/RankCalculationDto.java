package holdem.core.dto;

import holdem.core.domain.CardForParsing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RankCalculationDto {
    private double strength = 0.00;
    private ArrayList<CardForParsing> selectedCards = new ArrayList<>();

    public RankCalculationDto() {
    }

    public RankCalculationDto(double strength, ArrayList<CardForParsing> selectedCards) {
        this.strength = strength;
        this.selectedCards = selectedCards;
    }

    public void addSelectedCad(CardForParsing newCard) {
        if (selectedCards != null) {
            selectedCards.add(newCard);
        } else {
            selectedCards = new ArrayList<>(Collections.singletonList(newCard));
        }
    }

    public void addSelectedCards(ArrayList<CardForParsing> newSelectedCards) {
        selectedCards.addAll(newSelectedCards);
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public boolean selectedCardsAreEmpty() {
        return selectedCards.isEmpty();
    }

    public List<CardForParsing> getSelectedCards() {
        return selectedCards;
    }

    public void setSelectedCards(ArrayList<CardForParsing> selectedCards) {
        this.selectedCards = selectedCards;
    }

}
