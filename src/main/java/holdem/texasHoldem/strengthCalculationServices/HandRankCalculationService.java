package holdem.texasHoldem.strengthCalculationServices;

import holdem.core.domain.CardForParsing;
import holdem.core.dto.CalculationResultDto;
import holdem.core.dto.Hand;
import holdem.core.dto.HandsAndBoardDto;
import holdem.core.dto.RankCalculationDto;

import java.util.ArrayList;

public class HandRankCalculationService {
    private final PairRangCalculationService pairRangCalculationService;
    private final StraightAndSuitedRangCalculationService straightAndSuitedRangCalculationService;

    public HandRankCalculationService(PairRangCalculationService pairRangCalculationService, StraightAndSuitedRangCalculationService straightAndSuitedRangCalculationService) {
        this.pairRangCalculationService = pairRangCalculationService;
        this.straightAndSuitedRangCalculationService = straightAndSuitedRangCalculationService;
    }

    public ArrayList<CalculationResultDto> calculation(HandsAndBoardDto inputData) {
        ArrayList<CalculationResultDto> handsCalculationResults = new ArrayList<>();
        for (Hand hand : inputData.getHands()) {
            RankCalculationDto pairResult = pairRangCalculationService.calculation(hand, inputData.getBoard());
            RankCalculationDto straightAndSuitedResult = straightAndSuitedRangCalculationService.calculation(hand, inputData.getBoard());

            CalculationResultDto newResult;
            if (pairResult.getStrength() >= straightAndSuitedResult.getStrength()) {
                newResult = new CalculationResultDto(
                        pairResult.getStrength(),
                        hand.getCards(),
                        (ArrayList<CardForParsing>) pairResult.getSelectedCards()
                );
            } else {
                newResult = new CalculationResultDto(
                        straightAndSuitedResult.getStrength(),
                        hand.getCards(),
                        (ArrayList<CardForParsing>) straightAndSuitedResult.getSelectedCards()
                );
            }
            handsCalculationResults.add(newResult);
        }
        return handsCalculationResults;
    }
}
