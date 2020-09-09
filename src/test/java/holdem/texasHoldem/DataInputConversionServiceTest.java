package holdem.texasHoldem;

import holdem.core.domain.CardForParsing;
import holdem.core.dto.Board;
import holdem.core.dto.Hand;
import holdem.core.dto.HandsAndBoardDto;
import holdem.core.mappers.CardRankMapper;
import holdem.texasHoldem.validation.UserInputValidation;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

public class DataInputConversionServiceTest {
    private final DataInputConversionService victim = new DataInputConversionService(
            new UserInputValidation(),
            new CardRankMapper());

    @Test
    public void correctOutput() {
        HandsAndBoardDto result = victim.inputConversion("4cKs4h8s7s Ad4s Ac4d As9s ");

        assertEquals(new Board(new ArrayList<>(
                Arrays.asList(
                        new CardForParsing((byte) 4, "c"),
                        new CardForParsing((byte) 13, "s"),
                        new CardForParsing((byte) 4, "h"),
                        new CardForParsing((byte) 8, "s"),
                        new CardForParsing((byte) 7, "s")))), result.getBoard());
        assertEquals(new Hand(new ArrayList<>(
                Arrays.asList(
                        new CardForParsing((byte) 14, "d"),
                        new CardForParsing((byte) 4, "s")))), result.getHands().get(0));
        assertEquals(new Hand(new ArrayList<>(
                Arrays.asList(
                        new CardForParsing((byte) 14, "c"),
                        new CardForParsing((byte) 4, "d")))), result.getHands().get(1));
        assertEquals(new Hand(new ArrayList<>(
                Arrays.asList(
                        new CardForParsing((byte) 14, "s"),
                        new CardForParsing((byte) 9, "s")))), result.getHands().get(2));
    }


 @Test
    public void noBoardException() {
        assertThatThrownBy(() -> victim.inputConversion("6h3d 5c7s"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Missing 5 board cards;");
    }

      @Test
    public void MoreThanOneBoardException() {
        assertThatThrownBy(() -> victim.inputConversion("6h3d5c7s4s 6h3d5c7s4s 6h3d"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Multiple boards were detected;");
    }

    @Test
    public void smallHandException() {
        assertThatThrownBy(() -> victim.inputConversion("6h3d5c7s4s 6h3d 5c 7s"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Incorrect number of entered hands cards;");
    }

    @Test
    public void bigHandException() {
        assertThatThrownBy(() -> victim.inputConversion("6h3d5c7s4s 6h3d4h 5c7s"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Incorrect number of entered hands cards;");
    }

    @Test
    public void invalidCardSuitException() {
        assertThatThrownBy(() -> victim.inputConversion("6h3g5c7s4s 6h4w 5c7s"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid card suit value;");
    }

    @Test
    public void invalidCardRankException() {
        assertThatThrownBy(() -> victim.inputConversion("6h3d5c7s4s 0h3d"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid card rank value;");
    }

    @Test
    public void invalidInputException() {
        assertThatThrownBy(() -> victim.inputConversion(" 5h3d4h3d5c"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Incorrect data input, check the format;");
    }

    @Test
    public void invalidSpaceException() {
        assertThatThrownBy(() -> victim.inputConversion("6h3d5c7s4s_6h3d"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Incorrect format, Enter cards using space ' ' for card group separation;");
    }

    @Test
    public void duplicateCardException() {
        assertThatThrownBy(() -> victim.inputConversion("6h6h5c7s4s 5c3d"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Duplicate card detected: '6h';");
    }

    @Test
    public void incorrectTenRankException() {
        assertThatThrownBy(() -> victim.inputConversion("6h3d5c7s4s 10hQd"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Incorrect number of entered hands cards;");
    }

    //    @Test
//    public void moreThanFiftyFourCardsException() {
//        assertThatThrownBy(() -> victim.inputConversion("...."))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessage("More than 54 cards were detected;");
//    }

}
