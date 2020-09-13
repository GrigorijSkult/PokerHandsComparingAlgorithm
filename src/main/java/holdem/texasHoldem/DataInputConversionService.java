package holdem.texasHoldem;

import holdem.core.domain.CardForParsing;
import holdem.core.dto.Board;
import holdem.core.dto.Hand;
import holdem.core.dto.HandsAndBoardDto;
import holdem.core.mappers.CardRankMapper;
import holdem.texasHoldem.validation.UserInputValidation;

import java.util.ArrayList;
import java.util.List;

public class DataInputConversionService {
    private final UserInputValidation userInputValidation;
    private final CardRankMapper cardRankMapper;

    public DataInputConversionService(UserInputValidation userInputValidation, CardRankMapper cardRankMapper) {
        this.userInputValidation = userInputValidation;
        this.cardRankMapper = cardRankMapper;
    }

    public HandsAndBoardDto inputConversion(String userInput) {
        HandsAndBoardDto handsAndBoardDto = new HandsAndBoardDto();
        userInputValidation.inputValidate(userInput);
        String[] parts = userInput.split(" ");
        int i = 1;
        for (String part : parts) {
            if (part.length() != 0 && part.length() % 10 == 0) {
                handsAndBoardDto.setBoard(new Board(cardCreation(part, false)));
            } else {
                handsAndBoardDto.addHand(new Hand(cardCreation(part, true)));
            }
        }
        return handsAndBoardDto;
    }

    private ArrayList<CardForParsing> cardCreation(String part, boolean handCard) {
        List<CardForParsing> detectedCards = new ArrayList<>();
        ArrayList<CardForParsing> carts = new ArrayList<>();
        byte x = 0;
        byte y = 1;
        CardForParsing newCard;
        for (int i = 0; i < part.length() / 2; i++) {
            String[] letterArray = part.split("");
            userInputValidation.cardSuitValidate(letterArray[y]);
            newCard = new CardForParsing(
                    cardRankMapper.rankToByte(letterArray[x]),
                    letterArray[y]
            );
            userInputValidation.cardNumberValidation(newCard, detectedCards);
            if (handCard) {
                newCard.setHandCard(true);
            }
            carts.add(newCard);
            x += 2;
            y += 2;
        }
        return carts;
    }
}
