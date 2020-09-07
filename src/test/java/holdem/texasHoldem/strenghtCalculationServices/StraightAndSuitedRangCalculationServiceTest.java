package holdem.texasHoldem.strenghtCalculationServices;

import holdem.core.domain.CardForParsing;
import holdem.core.dto.Board;
import holdem.core.dto.Hand;
import holdem.core.dto.RankCalculationDto;
import holdem.texasHoldem.strengthCalculationServices.StraightAndSuitedRangCalculationService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class StraightAndSuitedRangCalculationServiceTest {

    private final StraightAndSuitedRangCalculationService victim = new StraightAndSuitedRangCalculationService();

    @Test
    public void noCombination() {
        RankCalculationDto result = victim.calculation(hand("c", (byte) 2, "h", (byte) 2),
                board("d", (byte) 2, "c", (byte) 3, "s", (byte) 8, "h", (byte) 5, "d", (byte) 6));

        assertEquals(1.00, result.getStrength(), 0);
    }

    @Test
    public void straightI() {
        RankCalculationDto result = victim.calculation(hand("h", (byte) 2, "h", (byte) 9),
                board("d", (byte) 7, "c", (byte) 4, "s", (byte) 8, "h", (byte) 5, "d", (byte) 6));

        assertEquals(5.00, result.getStrength(), 0);
    }

    @Test
    public void straightII() {
        RankCalculationDto result = victim.calculation(hand("s", (byte) 3, "h", (byte) 4),
                board("s", (byte) 2, "c", (byte) 6, "h", (byte) 9, "h", (byte) 5, "h", (byte) 6));

        assertEquals(5.00, result.getStrength(), 0);
    }

    @Test
    public void straightIII() {
        RankCalculationDto result = victim.calculation(hand("s", (byte) 3, "h", (byte) 10),
                board("s", (byte) 12, "c", (byte) 14, "h", (byte) 5, "h", (byte) 13, "h", (byte) 11));

        assertEquals(5.00, result.getStrength(), 0);
    }

    @Test
    public void straightIV() {
        RankCalculationDto result = victim.calculation(hand("c", (byte) 11, "c", (byte) 9),
                board("h", (byte) 7, "h", (byte) 4, "d", (byte) 8, "h", (byte) 5, "s", (byte) 6));

        assertEquals(5.00, result.getStrength(), 0);
    }

    @Test
    public void noHandStraight() {
        RankCalculationDto result = victim.calculation(hand("d", (byte) 2, "h", (byte) 10),
                board("d", (byte) 7, "c", (byte) 8, "s", (byte) 4, "h", (byte) 5, "d", (byte) 6));

        assertEquals(5.00, result.getStrength(), 0);
    }

    @Test
    public void flashI() {
        RankCalculationDto result = victim.calculation(hand("h", (byte) 11, "h", (byte) 9),
                board("h", (byte) 7, "h", (byte) 4, "s", (byte) 8, "h", (byte) 5, "d", (byte) 6));

        assertEquals(6.00, result.getStrength(), 0);
    }

    @Test
    public void flashII() {
        RankCalculationDto result = victim.calculation(hand("c", (byte) 10, "h", (byte) 9),
                board("h", (byte) 13, "h", (byte) 4, "h", (byte) 8, "h", (byte) 14, "d", (byte) 6));

        assertEquals(6.00, result.getStrength(), 0);
    }

    @Test
    public void flashIII() {////?8-c / 6-s
        RankCalculationDto result = victim.calculation(hand("s", (byte) 8, "s", (byte) 4),
                board("s", (byte) 2, "h", (byte) 2, "s", (byte) 5, "c", (byte) 6, "s", (byte) 3));

        assertEquals(6.00, result.getStrength(), 0);
    }

    @Test
    public void noHandFlash() {
        RankCalculationDto result = victim.calculation(hand("c", (byte) 11, "c", (byte) 13),
                board("h", (byte) 7, "h", (byte) 4, "h", (byte) 8, "h", (byte) 5, "d", (byte) 6));

        assertEquals(5.00, result.getStrength(), 0);
    }

    @Test
    public void straightFlashI() {
        RankCalculationDto result = victim.calculation(hand("s", (byte) 3, "s", (byte) 4),
                board("s", (byte) 2, "h", (byte) 2, "s", (byte) 5, "c", (byte) 6, "s", (byte) 6));

        assertEquals(9.00, result.getStrength(), 0);
    }

    @Test
    public void straightFlashII() {
        RankCalculationDto result = victim.calculation(hand("c", (byte) 8, "s", (byte) 4),
                board("s", (byte) 2, "h", (byte) 2, "s", (byte) 5, "s", (byte) 6, "s", (byte) 3));

        assertEquals(9.00, result.getStrength(), 0);
    }

    @Test
    public void noHandStraightFlashII() {
        RankCalculationDto result = victim.calculation(hand("c", (byte) 8, "h", (byte) 2),
                board("s", (byte) 2, "s", (byte) 4, "s", (byte) 5, "s", (byte) 6, "s", (byte) 3));

        assertEquals(9.00, result.getStrength(), 0);
    }

    @Test
    public void royalStraightFlashI() {
        RankCalculationDto result = victim.calculation(hand("s", (byte) 14, "s", (byte) 10),
                board("s", (byte) 12, "h", (byte) 4, "s", (byte) 11, "c", (byte) 6, "s", (byte) 13));

        assertEquals(10.00, result.getStrength(), 0);
    }

    @Test
    public void royalStraightFlashII() {
        RankCalculationDto result = victim.calculation(hand("h", (byte) 12, "s", (byte) 10),
                board("s", (byte) 12, "h", (byte) 4, "s", (byte) 11, "s", (byte) 14, "s", (byte) 13));

        assertEquals(10.00, result.getStrength(), 0);
    }

    @Test
    public void noHandRoyalStraightFlash() {
        RankCalculationDto result = victim.calculation(hand("s", (byte) 12, "h", (byte) 4),
                board("h", (byte) 12, "s", (byte) 10, "s", (byte) 11, "s", (byte) 14, "s", (byte) 13));

        assertEquals(10.00, result.getStrength(), 0);
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
                        new CardForParsing(a2, a),
                        new CardForParsing(b2, b))));
    }
}
