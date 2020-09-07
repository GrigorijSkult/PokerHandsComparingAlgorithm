package holdem.texasHoldem;

import holdem.core.domain.CardForParsing;
import holdem.core.dto.CalculationResultDto;
import holdem.core.mappers.CardRankMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static holdem.core.dto.CalculationResultDto.FlashArrayComparator;
import static holdem.core.dto.CalculationResultDto.HandStrengthsArrayComparator;

public class DataOutputConversionService {

    private final CardRankMapper cardRankMapper;

    public DataOutputConversionService(CardRankMapper cardRankMapper) {
        this.cardRankMapper = cardRankMapper;
    }

    public String execute(ArrayList<CalculationResultDto> handsCalculationResults) {
        Map<Double, ArrayList<CalculationResultDto>> oneStrengthHandsMap = new HashMap<>();
        handsCalculationResults.sort(HandStrengthsArrayComparator);

        for (int i = 0; i < handsCalculationResults.size() - 1; ) {
            ArrayList<CalculationResultDto> cardCollector = new ArrayList<>();
            CalculationResultDto resultOne = handsCalculationResults.get(i);
            CalculationResultDto resultTwo = handsCalculationResults.get(i + 1 >= handsCalculationResults.size() ? i : i + 1);
            if (resultOne.getHandStrengths().equals(resultTwo.getHandStrengths()) && resultOne != resultTwo) {
                for (CalculationResultDto dto : handsCalculationResults) {
                    if (dto.getHandStrengths().equals(resultOne.getHandStrengths())) {
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
                double strengthCounter = 0.1;
                if (oneStrengthHand.getValue().size() == 1) {
                    oneStrengthHand.getValue().get(0).setHandStrengths(oneStrengthHand.getKey() + strengthCounter);
                    strengthCounter = +0.1;
                } else {
                    for (CalculationResultDto value : oneStrengthHand.getValue()) {
                        value.setHandStrengths(oneStrengthHand.getKey() + strengthCounter);
                    }
                }

                if (oneStrengthHand.getKey() == 5.0 || oneStrengthHand.getKey() == 6.0 || oneStrengthHand.getKey() == 9.0 || oneStrengthHand.getKey() == 10.0) {
                    //сравниваем "стриты и флеши
                    for (ArrayList<CalculationResultDto> hand : oneStrengthHandsMap.values()) {
                        if (oneStrengthHand.getKey() == 6.0) {
                            hand.sort(FlashArrayComparator);
                        } else {
                            hand.sort(HandStrengthsArrayComparator);
                        }
                        strengthCounter = (hand.size() - 1) * 0.01;
                        for (int i = 0; i < hand.size() - 1 && strengthCounter > 0; i++) {
                            if (oneStrengthHand.getKey() == 6.0) {
                                for (int i2 = 0; i2 < 4 && strengthCounter > 0; i2++) {
                                    if (hand.get(i).getCombinationCards().get(i2).getRank() != hand.get(i + 1).getCombinationCards().get(i2).getRank()) {
                                        hand.get(i).setHandStrengths(hand.get(i).getHandStrengths() + strengthCounter);
                                        strengthCounter -= 0.010;
                                        break;
                                    }
                                }
                            } else {
                                if (hand.get(i).getCombinationCards().get(4).getRank() != hand.get(i + 1).getCombinationCards().get(4).getRank()) {
                                    hand.get(i).setHandStrengths(hand.get(i).getHandStrengths() + strengthCounter);
                                    strengthCounter -= 0.010;
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    Map<Byte, ArrayList<CalculationResultDto>> oneRangAndStrengthHands = new HashMap<>();
                    if (oneStrengthHand.getKey() != 7.00 && oneStrengthHand.getKey() != 8.00) {
                        for (int i = 0; i < oneStrengthHand.getValue().size(); ) {

                            ArrayList<CalculationResultDto> cardCollector = new ArrayList<>();
                            CalculationResultDto resultOne = oneStrengthHand.getValue().get(i);
                            CalculationResultDto resultTwo = oneStrengthHand.getValue().get(i + 1 >= oneStrengthHand.getValue().size() ? i : i + 1);
                            if ((resultOne.getCombinationCards().get(0).getRank() == resultTwo.getCombinationCards().get(0).getRank())
                                    && resultOne != resultTwo) {
                                for (CalculationResultDto dto : oneStrengthHand.getValue()) {
                                    if (dto.getCombinationCards().get(0).getRank() == resultOne.getCombinationCards().get(0).getRank()) {
                                        cardCollector.add(dto);
                                        i++;
                                    }
                                }
                                oneRangAndStrengthHands.put(resultOne.getCombinationCards().get(0).getRank(), cardCollector);
                            } else {
                                i++;
                            }
                        }
                    } else {
                        System.out.println();
                        oneRangAndStrengthHands.put((byte) 1, new ArrayList<CalculationResultDto>(oneStrengthHand.getValue()));
                    }
                    if (!oneRangAndStrengthHands.isEmpty()) {
                        for (ArrayList<CalculationResultDto> value : oneRangAndStrengthHands.values()) {
                            Double handStrengths = value.get(0).getHandStrengths();
                            if (oneStrengthHand.getKey() == 3 || oneStrengthHand.getKey() == 7) {
                                if (oneStrengthHand.getKey() == 7) {
                                    System.out.println();
                                    if (value.get(0).getCombinationCards().get(0).getRank() > value.get(1).getCombinationCards().get(0).getRank()) {
                                        value.get(0).setHandStrengths(handStrengths += 0.01);
                                        break;
                                    } else if (value.get(0).getCombinationCards().get(0).getRank() < value.get(1).getCombinationCards().get(0).getRank()) {
                                        value.get(1).setHandStrengths(handStrengths += 0.01);
                                        break;
                                    }
                                }
                                if (value.get(0).getCombinationCards().get(3).getRank() > value.get(1).getCombinationCards().get(3).getRank()) {
                                    value.get(0).setHandStrengths(handStrengths += 0.01);
                                    break;
                                } else if (value.get(0).getCombinationCards().get(3).getRank() < value.get(1).getCombinationCards().get(3).getRank()) {
                                    value.get(1).setHandStrengths(handStrengths += 0.01);
                                    break;
                                }
                            }
                            //2 || 4|| 8
                            switch (choseHighCombinationByKicker(value.get(0).getCombinationCards(), value.get(1).getCombinationCards())) {
                                case 1:
                                    value.get(0).setHandStrengths(handStrengths += 0.01);
                                    break;
                                case 2:
                                    value.get(1).setHandStrengths(handStrengths += 0.01);
                                    break;
                                case 0:
                                    break;
                            }
                        }
                    }
                }
            }
        }
        handsCalculationResults.sort(HandStrengthsArrayComparator);

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

    private int choseHighCombinationByKicker(ArrayList<CardForParsing> combinationCardsOne, ArrayList<CardForParsing> combinationCardsTwo) {
        for (int i = 0; i < combinationCardsOne.size(); i++) {
            if (combinationCardsOne.get(i).isKicker()) {
                if (combinationCardsOne.get(i).getRank() > combinationCardsTwo.get(i).getRank()) {
                    return 1;
                } else if (combinationCardsOne.get(i).getRank() < combinationCardsTwo.get(i).getRank()) {
                    return 2;
                }
            }
        }
        return 0;
    }

    private String stringCollection(ArrayList<CardForParsing> handsPrimaryCombination, String space) {
        String result = cardRankMapper.rankToString(handsPrimaryCombination.get(0).getRank()) + handsPrimaryCombination.get(0).getSuit();
        result += cardRankMapper.rankToString(handsPrimaryCombination.get(1).getRank()) + handsPrimaryCombination.get(1).getSuit() + space;
        return result;
    }
}
