package holdem.texasHoldem.strengthCalculationServices;

import holdem.core.domain.CardForParsing;
import holdem.core.dto.Board;
import holdem.core.dto.Hand;
import holdem.core.dto.RankCalculationDto;

import java.util.ArrayList;
import java.util.List;

import static holdem.core.domain.CardForParsing.CardForParsingRankReversComparator;

public class StraightAndSuitedRangCalculationService {

    public RankCalculationDto calculation(Hand hand, Board board) {
        RankCalculationDto outData = new RankCalculationDto();
        ArrayList<CardForParsing> playableCards = new ArrayList<>();
        playableCards.addAll(hand.getCards());
        playableCards.addAll(board.getCards());
        playableCards.sort(CardForParsingRankReversComparator);

        ArrayList<CardForParsing> oneSuitCardSet = oneSuitCardsSearch(playableCards);
        boolean fullOneSuitCardSet = (!oneSuitCardSet.isEmpty() && oneSuitCardSet.size() >= 5);

        ArrayList<CardForParsing> straightCardSet = straightCardSearch(playableCards);
        boolean fullStraightCardSet = (!straightCardSet.isEmpty() && straightCardSet.size() >= 5);

        boolean fullStraightFlashSet = false;
        ArrayList<CardForParsing> straightFlashCardSet = new ArrayList<>();
        if (fullOneSuitCardSet && fullStraightCardSet) {
            straightFlashCardSet = straightFlashSearch(oneSuitCardSet, straightCardSet);
            fullStraightFlashSet = (!straightFlashCardSet.isEmpty() && straightFlashCardSet.size() >= 5);
        }

        if (fullStraightFlashSet) {
            makeFiveCardSet(straightFlashCardSet);
        } else {
            if (fullOneSuitCardSet) {
                makeFiveCardSet(oneSuitCardSet);
            }
            if (fullStraightCardSet) {
                makeFiveStraightCardSet(straightCardSet);
                fullStraightCardSet = (!straightCardSet.isEmpty() && straightCardSet.size() >= 5);
            }
        }
        double handStrengthValue = straightAndSuitedRangValue(fullOneSuitCardSet, fullStraightCardSet, fullStraightFlashSet, straightFlashCardSet);
        outData.setStrength(handStrengthValue);

        if (handStrengthValue == 5.00) {
            outData.setSelectedCards(straightCardSet);
        } else if (handStrengthValue == 6.00) {
            outData.setSelectedCards(oneSuitCardSet);
        } else if (handStrengthValue == 9.00 || handStrengthValue == 10.00) {
            outData.setSelectedCards(straightFlashCardSet);
        } else if (handStrengthValue == 1.00) {
            makeFiveCardSet(playableCards);
            outData.setSelectedCards(playableCards);
        }

        return outData;
    }

    private ArrayList<CardForParsing> oneSuitCardsSearch(List<CardForParsing> playableCards) {
        int hearts = 0;
        int diamonds = 0;
        int clubs = 0;
        int spades = 0;
        for (CardForParsing playerCard : playableCards) {
            switch (playerCard.getSuit()) {
                case "h":
                    hearts++;
                    break;
                case "d":
                    diamonds++;
                    break;
                case "c":
                    clubs++;
                    break;
                case "s":
                    spades++;
                    break;
            }
        }
        int maxSuitNumber = Math.max(Math.max(hearts, diamonds), Math.max(clubs, spades));

        String value = null;
        if (maxSuitNumber >= 5) {
            if (maxSuitNumber == hearts) {
                value = "h";
            }
            if (maxSuitNumber == diamonds) {
                value = "d";
            }
            if (maxSuitNumber == clubs) {
                value = "c";
            }
            if (maxSuitNumber == spades) {
                value = "s";
            }
        }

        ArrayList<CardForParsing> oneSuitCardSet = new ArrayList<>();
        if (value != null) {
            for (CardForParsing playableCard : playableCards) {
                if (playableCard.getSuit().equals(value)) {
                    oneSuitCardSet.add(playableCard);
                }
            }
        }
        return oneSuitCardSet;
    }

    private ArrayList<CardForParsing> straightCardSearch(ArrayList<CardForParsing> playableCards) {
        ArrayList<CardForParsing> straightCardSet = new ArrayList<>(playableCards);
        int straightCounter = 0;
        int oneRangCounter = 0;
        ////
        if(playableCards.get(0).getRank() == 14 && playableCards.get (playableCards.size()-1).getRank() == 2){
            straightCardSet.add(playableCards.get(0));
        }
        ///
        for (int i = 0; i < straightCardSet.size() - 1; ) {
            CardForParsing cardOne = straightCardSet.get(i);
            CardForParsing cardTwo = straightCardSet.get(i + 1 >= straightCardSet.size() ? i : i + 1);
            if ((cardOne.getRank() - 1 == cardTwo.getRank()) || (cardOne.getRank() == cardTwo.getRank()) ||
                    (cardOne.getRank() == 2 && cardTwo.getRank() == 14)) {
                if (cardOne.getRank() == cardTwo.getRank()) {
                    oneRangCounter++;
                }
                straightCounter++;
                i++;
            } else {
                if (straightCounter - oneRangCounter >= 4) {
                    //удаляем следующие элементы если один из них не +1 к предыдущему
                    for (int i2 = i + 1; i2 <= straightCardSet.size() - 1; i++) {
                        straightCardSet.remove(i2);
                    }
                    break;
                } else {
                    //удаляем все прошлые элементы если новый не +1 к предыдущему
                    for (int i2 = 0; i2 <= i; i--) {
                        straightCardSet.remove(i2);
                    }
                    i = 0;
                    straightCounter = 0;
                    oneRangCounter = 0;
                }
            }
        }
        return straightCardSet;
    }

    private ArrayList<CardForParsing> straightFlashSearch(ArrayList<CardForParsing> oneSuitCardSet, ArrayList<CardForParsing> straightCardSet) {
        ArrayList<CardForParsing> straightFlashSet = new ArrayList<>();
        String suit = oneSuitCardSet.get(0).getSuit();
        for (CardForParsing suitCard : oneSuitCardSet) {
            for (CardForParsing straightCard : straightCardSet) {
                if (straightCard.getSuit().equals(suit) && suitCard.equals(straightCard)) {
                    straightFlashSet.add(straightCard);
                }
            }
        }
        return straightFlashSet;
    }

    private void makeFiveCardSet(ArrayList<CardForParsing> cardSet) {
        while (cardSet.size() > 5) {
            cardSet.remove(cardSet.size()-1);
        }
    }

    private void makeFiveStraightCardSet(ArrayList<CardForParsing> cardSet) {
        for (int i = 0; i < cardSet.size() - 1; i++) {
            CardForParsing cardOne = cardSet.get(i);
            CardForParsing cardTwo = cardSet.get(i + 1 >= cardSet.size() ? i : i + 1);
            if ((cardSet.get(i).getRank() == cardSet.get(i + 1 >= cardSet.size() ? i : i + 1).getRank()) && cardOne != cardTwo) {
                cardSet.remove(cardOne);
                i--;
            }
        }
        makeFiveCardSet(cardSet);
    }

    private double straightAndSuitedRangValue(boolean suitedCards, boolean straightCards, boolean straightFlash, ArrayList<CardForParsing> straightFlashSet) {
        if (straightCards) {
            if (suitedCards) {
                if (straightFlash) {
                    if (straightFlashSet.get(0).getRank() == 14) {
                        return 10.00;
                    }
                    return 9.00;
                } else {
                    return 6.00;
                }
            } else {
                return 5.00;
            }
        } else {
            if (suitedCards) {
                return 6.00;
            } else {
                return 1.00;
            }
        }
    }
}
