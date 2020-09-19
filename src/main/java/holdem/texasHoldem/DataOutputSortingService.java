package holdem.texasHoldem;

import holdem.core.domain.CardForParsing;
import holdem.core.dto.CalculationResultDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static holdem.core.dto.CalculationResultDto.*;

public class DataOutputSortingService {

    public ArrayList<CalculationResultDto> execute(ArrayList<CalculationResultDto> handsCalculationResults) {
        Map<Double, ArrayList<CalculationResultDto>> oneStrengthHandsMap = new HashMap<>();
        handsCalculationResults.sort(HandStrengthsArrayComparator);

        for (int i = 0; i < handsCalculationResults.size() - 1; ) {
            ArrayList<CalculationResultDto> cardCollector = new ArrayList<>();
            CalculationResultDto resultOne = handsCalculationResults.get(i);
            CalculationResultDto resultTwo = handsCalculationResults.get(i + 1 >= handsCalculationResults.size() ? i : i + 1);
            if (resultOne.getHandStrengths().equals(resultTwo.getHandStrengths()) && resultOne != resultTwo) {
                for (CalculationResultDto dto : handsCalculationResults) {
                    if (dto.getHandStrengths().equals(resultOne.getHandStrengths()) && !dto.isBoardCombination()) {
                        cardCollector.add(dto);
                        i++;
                    }
                }
                oneStrengthHandsMap.put(resultOne.getHandStrengths(), cardCollector);
            } else {
                i++;
            }
        }
        if (!oneStrengthHandsMap.isEmpty()) {
            for (Map.Entry<Double, ArrayList<CalculationResultDto>> oneStrengthHand : oneStrengthHandsMap.entrySet()) {
                oneStrengthHand.getValue().sort(FlashArrayComparator);
                Double handStrengths = oneStrengthHand.getKey();
                double oneStrengthHandNumber = oneStrengthHand.getValue().size() * 0.01;
                for (int i = 0; i < oneStrengthHand.getValue().size(); i++) {
                    CalculationResultDto resultOne = oneStrengthHand.getValue().get(i);
                    CalculationResultDto resultTwo = oneStrengthHand.getValue().get(i + 1 >= oneStrengthHand.getValue().size() ? i : i + 1);
                    if (!resultOne.equals(resultTwo)) {
                        switch (choseHighCombinationByKicker(resultOne.getCombinationCards(), resultTwo.getCombinationCards())) {
                            case 1:
                                resultOne.setHandStrengths(handStrengths + oneStrengthHandNumber);
                                oneStrengthHandNumber -= 0.001;
                                break;
                            case 2:
                                resultTwo.setHandStrengths(handStrengths + oneStrengthHandNumber);
                                oneStrengthHandNumber -= 0.001;
                                break;
                            case 0:
                                resultOne.setHandStrengths(handStrengths + oneStrengthHandNumber);
                                resultTwo.setHandStrengths(handStrengths + oneStrengthHandNumber);
                                break;
                        }
                    }
                }
            }
        }
        handsCalculationResults.sort(HandStrengthsArrayComparator);
        return handsCalculationResults;
    }

    private int choseHighCombinationByKicker(ArrayList<CardForParsing> combinationCardsOne, ArrayList<CardForParsing> combinationCardsTwo) {
        for (int i = 0; i < combinationCardsOne.size(); i++) {
            if (combinationCardsOne.get(i).getRank() > combinationCardsTwo.get(i).getRank()) {
                return 1;
            } else if (combinationCardsOne.get(i).getRank() < combinationCardsTwo.get(i).getRank()) {
                return 2;
            }
        }
        return 0;
    }
}
