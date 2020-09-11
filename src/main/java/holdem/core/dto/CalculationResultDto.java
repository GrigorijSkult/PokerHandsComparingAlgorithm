package holdem.core.dto;

import holdem.core.domain.CardForParsing;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class CalculationResultDto {

    private Double handStrengths;
    private ArrayList<CardForParsing> handsPrimaryCombination = new ArrayList<>();
    private ArrayList<CardForParsing> combinationCards = new ArrayList<>();

    public CalculationResultDto() {
    }

    public CalculationResultDto(Double handStrengths, ArrayList<CardForParsing> handsPrimaryCombination, ArrayList<CardForParsing> combinationCards) {
        this.handStrengths = handStrengths;
        this.handsPrimaryCombination = handsPrimaryCombination;
        this.combinationCards = combinationCards;
    }

    public Double getHandStrengths() {
        return handStrengths;
    }

    public void setHandStrengths(Double handStrengths) {
        this.handStrengths = handStrengths;
    }

    public ArrayList<CardForParsing> getHandsPrimaryCombination() {
        return handsPrimaryCombination;
    }

    public void setHandsPrimaryCombination(ArrayList<CardForParsing> handsPrimaryCombination) {
        this.handsPrimaryCombination = handsPrimaryCombination;
    }

    public ArrayList<CardForParsing> getCombinationCards() {
        return combinationCards;
    }

    public void setCombinationCards(ArrayList<CardForParsing> combinationCards) {
        this.combinationCards = combinationCards;
    }

    public static Comparator<CalculationResultDto> HandStrengthsArrayComparator = new Comparator<CalculationResultDto>() {
        public int compare(CalculationResultDto c1, CalculationResultDto c2) {
            double CalculationResultDtoOne = c1.getHandStrengths();
            double CalculationResultDtoTwo = c2.getHandStrengths();

            //ascending order
            return Double.compare(CalculationResultDtoOne, CalculationResultDtoTwo);
        }
    };

    public static Comparator<CalculationResultDto> FlashArrayComparator = new Comparator<CalculationResultDto>() {
        public int compare(CalculationResultDto c1, CalculationResultDto c2) {
            ArrayList<CardForParsing> k1 = c1.getCombinationCards();
            ArrayList<CardForParsing> k2 = c2.getCombinationCards();
            for (int i = 0; i < k1.size() - 1; i++) {
                byte card1 = k1.get(i).getRank();
                byte card2 = k2.get(i).getRank();
                if (card1 != card2) {
                    return Byte.compare(card2, card1);
                }
            }
            return 0;
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalculationResultDto that = (CalculationResultDto) o;
        return Objects.equals(handStrengths, that.handStrengths) &&
                Objects.equals(handsPrimaryCombination, that.handsPrimaryCombination) &&
                Objects.equals(combinationCards, that.combinationCards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(handStrengths, handsPrimaryCombination, combinationCards);
    }

    @Override
    public String toString() {
        return "CalculationDto{" +
                ", handStrengths=" + handStrengths +
                ", handsPrimaryCards=" + handsPrimaryCombination +
                ", combinationCards=" + combinationCards +
                '}';
    }

}
