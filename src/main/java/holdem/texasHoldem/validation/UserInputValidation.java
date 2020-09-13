package holdem.texasHoldem.validation;

import holdem.console.CardMainUI;
import holdem.core.domain.CardForParsing;

import java.util.List;

public class UserInputValidation {

    public void inputValidate(String userInput) {
        if (!userInput.contains(" ")) {
            throw new IllegalArgumentException("Incorrect format, Enter cards using space ' ' for card group separation;");
        }
        String[] parts = userInput.split(" ");
        int boardExist = 0;
        for (String part : parts) {
            if (part.length() == 0) {
                throw new IllegalArgumentException("Incorrect data input, check the format;");
            }
            if (CardMainUI.gameType.equals("texas")) {
                if (part.length() != 10 && part.length() != 4) {
                    throw new IllegalArgumentException("Incorrect number of entered hands cards;");
                }
            } else if (CardMainUI.gameType.equals("omaha")) {
                if (part.length() != 10 && part.length() != 8) {
                    throw new IllegalArgumentException("Incorrect number of entered hands cards;");
                }
            }
            if (part.length() == 10) {
                boardExist++;
            }
        }
        if (boardExist == 0) {
            throw new IllegalArgumentException("Missing 5 board cards;");
        } else if (boardExist > 1) {
            throw new IllegalArgumentException("Multiple boards were detected;");
        }
    }

    public void cardSuitValidate(String card) {
        if (!(card.equals("h") || card.equals("d") || card.equals("c") || card.equals("s"))) {
            throw new IllegalArgumentException("Invalid card suit value;");
        }
    }

    public void cardNumberValidation(CardForParsing newCard, List<CardForParsing> detectedCards) {
        if (detectedCards.contains(newCard)) {
            throw new IllegalArgumentException("Duplicate card detected: '" + newCard.cardRankSuitInfo() + "';");
        } else {
            detectedCards.add(newCard);
        }
        if (detectedCards.size() > 51) {
            throw new IllegalArgumentException("More than 52 cards were detected;");
        }
    }
}
