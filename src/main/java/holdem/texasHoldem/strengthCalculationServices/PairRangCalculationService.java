package holdem.texasHoldem.strengthCalculationServices;

import holdem.core.domain.CardForParsing;
import holdem.core.dto.Board;
import holdem.core.dto.Hand;
import holdem.core.dto.RankCalculationDto;

import java.util.ArrayList;
import java.util.List;

import static holdem.core.domain.CardForParsing.CardForParsingRankComparator;

public class PairRangCalculationService {
    public RankCalculationDto calculation(Hand hand, Board board) {
        RankCalculationDto outData = new RankCalculationDto();
        List<CardForParsing> playableCards = new ArrayList<>();
        playableCards.addAll(hand.getCards());
        playableCards.addAll(board.getCards());
        playableCards.sort(CardForParsingRankComparator);

        int setCounter = 0;
        int twoPairCounter = 0;
        ArrayList<Integer> pairSizes = new ArrayList<>();
        ArrayList<List<CardForParsing>> pairCards = new ArrayList<>();

        for (int i = 0; i < playableCards.size() - 1; ) {
            List<CardForParsing> cardCollector = new ArrayList<>();
            CardForParsing handOne = playableCards.get(i);
            CardForParsing handTwo = playableCards.get(i + 1 >= playableCards.size() ? i : i + 1);
            if (handOne.getRank() == handTwo.getRank() && handOne != handTwo) {
                for (CardForParsing card : playableCards) {
                    if (card.getRank() == handOne.getRank()) {
                        cardCollector.add(card);
                        i++;
                    }
                }
                pairCards.add(cardCollector);
                int counter = cardCollector.size();
                pairSizes.add(counter);
                if (counter == 2) {
                    twoPairCounter++;
                }
                if (counter == 3) {
                    setCounter++;
                }
            } else {
                i++;
            }
        }
        if (!pairCards.isEmpty()) {
            boolean twoPairsDouble = twoPairCounter > 1;
            boolean setPairsDouble = setCounter > 1;
            outData.setStrength(strengthFinder(pairSizes, twoPairsDouble, setPairsDouble));

            boolean secondCheckIsDone = false;
            int maxPreviousSize = 0;
            for (int i = 0; i <= pairCards.size() - 1; i++) {
                int maxSize = 0;
                for (List<CardForParsing> pairCard : pairCards) {
                    if (maxSize < pairCard.size()) {
                        maxSize = pairCard.size();
                    }
                }
                boolean performSecondInput = true;
                if (!outData.selectedCardsAreEmpty()) {
                    secondCheckIsDone = true;
                    if (!(((maxPreviousSize == 3 && maxSize == 2) ||
                            (maxPreviousSize == 2 && maxSize == 2)) ||
                            (maxPreviousSize == 3 && maxSize == 3))) {
                        performSecondInput = false;
                    }
                }

                if (performSecondInput) {
                    int counter = 0;
                    for (List<CardForParsing> pairCard : pairCards) {
                        if (maxSize == pairCard.size()) {
                            counter++;
                        }
                    }
                    byte maxRang = 0;
                    if (counter >= 2) {
                        byte b;
                        for (List<CardForParsing> pairCard : pairCards) {
                            if (maxSize == pairCard.size()) {
                                b = pairCard.get(0).getRank();
                                if (maxRang < b) {
                                    maxRang = b;
                                }
                            }
                        }
                    }
                    for (int i2 = 0; i2 < pairCards.size(); i2++) {
                        if (maxSize == pairCards.get(i2).size() && outData.getSelectedCards().size() <= 5) {
                            if (maxRang == 0 || maxRang == pairCards.get(i2).get(0).getRank()) {
                                for (CardForParsing card : pairCards.get(i2)) {
                                    outData.addSelectedCad(card);
                                    if (outData.getSelectedCards().size() == 5) {
                                        break;
                                    }
                                }
                                for (CardForParsing removedCards : pairCards.get(i2)) {
                                    playableCards.remove(removedCards);
                                }
                                pairCards.remove(pairCards.get(i2));
                                pairSizes.remove((Object) maxSize);
                                break;
                            }
                        }
                    }
                }

                if ((pairCards.isEmpty() && maxSize != 4) || outData.getSelectedCards().size() == 5) {
                    break;
                } else {
                    if (secondCheckIsDone) {
                        break;
                    } else {
                        i = -1;//особенности цикла for
                        maxPreviousSize = maxSize;
                    }
                }
            }
        } else {
            outData.setStrength(1.00);
        }

        if (outData.getSelectedCards().size() <= 5) {
            for (int i = playableCards.size() - 1; outData.getSelectedCards().size() <= 4; i--) {
                CardForParsing kicker = playableCards.get(i);
                kicker.setKicker(true);
                outData.addSelectedCad(kicker);
                playableCards.remove(i);
            }
        }

        return outData;
    }

    private double strengthFinder(List<Integer> pairSizes, boolean twoPairsDouble, boolean setPairsDouble) {
        if (pairSizes.contains(4)) {
            return 8.00;
        } else {
            if (pairSizes.contains(3)) {
                if (setPairsDouble) {
                    return 7.00;
                } else {
                    if (pairSizes.contains(2)) {
                        return 7.00;
                    } else {
                        return 4.00;
                    }
                }
            } else {
                if (pairSizes.contains(2)) {
                    if (twoPairsDouble) {
                        return 3.00;
                    } else {
                        return 2.00;
                    }
                } else {
                    return 1.00;
                }
            }
        }
    }
}
