package holdem.texasHoldem.omahaHoldem.strenghtCalculationServices;

import holdem.console.CardMainUI;
import holdem.core.domain.CardForParsing;
import holdem.core.dto.Board;
import holdem.core.dto.Hand;
import holdem.core.dto.RankCalculationDto;
import holdem.texasHoldem.strengthCalculationServices.StraightAndSuitedRangCalculationService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class OmahaStraightAndSuitedRangCalculationServiceTest {

    private final StraightAndSuitedRangCalculationService victim = new StraightAndSuitedRangCalculationService();

    @Before
    public void start() {
        CardMainUI.gameType = "omaha";
    }

    @Test
    public void noCombination() {
        RankCalculationDto result = victim.calculation(hand("c", (byte) 2, "h", (byte) 7),
                board("d", (byte) 4, "c", (byte) 3, "s", (byte) 9, "h", (byte) 5, "d", (byte) 10));

        assertEquals(1.00, result.getStrength(), 0);
        assertEquals(result.getSelectedCards(),
                new ArrayList<>(Arrays.asList(
                        new CardForParsing((byte) 2, "c", false, true),
                        new CardForParsing((byte) 5, "h", false, false),
                        new CardForParsing((byte) 7, "h", false, true),
                        new CardForParsing((byte) 9, "s", false, false),
                        new CardForParsing((byte) 10, "d", false, false)
                )));
    }


    private Board board(String a, byte a2, String b, byte b2, String c, byte c2,
                        String d, byte d2, String e, byte e2) {
        return new Board(new ArrayList<>(
                Arrays.asList(
                        new CardForParsing(a2, a),
                        new CardForParsing(b2, b),
                        new CardForParsing(c2, c),
                        new CardForParsing(d2, d),
                        new CardForParsing(e2, e))));
    }

    private Hand hand(String a, byte a2, String b, byte b2) {
        return new Hand(new ArrayList<>(
                Arrays.asList(
                        new CardForParsing(a2, a, false, true),
                        new CardForParsing(b2, b, false, true))));
    }


}
