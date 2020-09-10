package holdem.texasHoldem;

import holdem.core.domain.CardForParsing;
import holdem.core.dto.CalculationResultDto;
import holdem.core.mappers.CardRankMapper;

import java.util.ArrayList;

public class OutputStringBuildingService {

    private final CardRankMapper cardRankMapper;

    public OutputStringBuildingService(CardRankMapper cardRankMapper) {
        this.cardRankMapper = cardRankMapper;
    }

    public String execute(ArrayList<CalculationResultDto> handsCalculationResults) {

        StringBuilder outputData = new StringBuilder();
        int i1 = 0;
        int i2 = 1;
        while (handsCalculationResults.size() > i1) {
            if (!(i2 >= handsCalculationResults.size())) {
                if (handsCalculationResults.get(i1).getHandStrengths().equals(handsCalculationResults.get(i2).getHandStrengths())) {
                    outputData.append(stringCollection(handsCalculationResults.get(i1).getHandsPrimaryCombination(), "="));
                } else {
                    outputData.append(stringCollection(handsCalculationResults.get(i1).getHandsPrimaryCombination(), " "));
                }
                i1++;
                i2++;
            } else {
                outputData.append(stringCollection(handsCalculationResults.get(i1).getHandsPrimaryCombination(), ""));
                i1++;
            }
        }

        return outputData.toString();
    }

    private String stringCollection(ArrayList<CardForParsing> handsPrimaryCombination, String space) {
        String result = cardRankMapper.rankToString(handsPrimaryCombination.get(0).getRank()) + handsPrimaryCombination.get(0).getSuit();
        result += cardRankMapper.rankToString(handsPrimaryCombination.get(1).getRank()) + handsPrimaryCombination.get(1).getSuit() + space;
        return result;
    }

}
