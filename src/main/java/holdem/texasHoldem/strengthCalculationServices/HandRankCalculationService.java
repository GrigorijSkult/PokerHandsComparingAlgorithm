package holdem.texasHoldem.strengthCalculationServices;

import holdem.core.domain.CardForParsing;
import holdem.core.dto.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
            Hand handCopyOne = (Hand) deepCopy(hand);
            Hand handCopyTwo = (Hand) deepCopy(hand);
            Board boardCopyOne = (Board) deepCopy(inputData.getBoard());
            Board boardCopyTwo = (Board) deepCopy(inputData.getBoard());

            RankCalculationDto straightAndSuitedResult = straightAndSuitedRangCalculationService.calculation(handCopyOne, boardCopyOne);
            RankCalculationDto pairResult = new RankCalculationDto();
            if (straightAndSuitedResult.getStrength() == 9.00 || straightAndSuitedResult.getStrength() == 10.00) {
                pairResult.setStrength(1.00);
            } else {
                pairResult = pairRangCalculationService.calculation(handCopyTwo, boardCopyTwo);
            }

            CalculationResultDto newResult = null;
            if (pairResult.getStrength() >= straightAndSuitedResult.getStrength()) {
                newResult = new CalculationResultDto(
                        pairResult.getStrength(),
                        handCopyOne.getCards(),
                        (ArrayList<CardForParsing>) pairResult.getSelectedCards()
                );
            } else {
                newResult = new CalculationResultDto(
                        straightAndSuitedResult.getStrength(),
                        handCopyTwo.getCards(),
                        (ArrayList<CardForParsing>) straightAndSuitedResult.getSelectedCards()
                );
            }
            handsCalculationResults.add(newResult);
        }
        return handsCalculationResults;
    }

    private static Object deepCopy(Object object) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream outputStrm = new ObjectOutputStream(outputStream);
            outputStrm.writeObject(object);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
            return objInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
