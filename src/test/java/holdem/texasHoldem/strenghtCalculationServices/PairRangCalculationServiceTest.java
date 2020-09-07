package holdem.texasHoldem.strenghtCalculationServices;

import holdem.core.domain.CardForParsing;
import holdem.core.dto.Board;
import holdem.core.dto.Hand;
import holdem.core.dto.RankCalculationDto;
import holdem.texasHoldem.strengthCalculationServices.PairRangCalculationService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class PairRangCalculationServiceTest {

    private final PairRangCalculationService victim = new PairRangCalculationService();

    @Test
    public void noPairI() {
        RankCalculationDto result = victim.calculation(hand((byte) 8, (byte) 7), board((byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6));

        assertEquals(1.00, result.getStrength(), 0);
    }

    @Test
    public void pairI() {
        RankCalculationDto result = victim.calculation(hand((byte) 2, (byte) 7), board((byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6));

        assertEquals(2.00, result.getStrength(), 0);
    }

    @Test
    public void pairII() {
        RankCalculationDto result = victim.calculation(hand((byte) 2, (byte) 2), board((byte) 7, (byte) 3, (byte) 4, (byte) 5, (byte) 6));

        assertEquals(2.00, result.getStrength(), 0);
    }

    @Test
    public void noHandPair() {
        RankCalculationDto result = victim.calculation(hand((byte) 8, (byte) 7), board((byte) 2, (byte) 3, (byte) 4, (byte) 2, (byte) 6));

        assertEquals(2.00, result.getStrength(), 0);
    }

    @Test
    public void twoPairI() {
        RankCalculationDto result = victim.calculation(hand((byte) 2, (byte) 7), board((byte) 2, (byte) 7, (byte) 4, (byte) 5, (byte) 6));

        assertEquals(3.00, result.getStrength(), 0);
    }

    @Test
    public void noHandPairTwoPair() {
        RankCalculationDto result = victim.calculation(hand((byte) 8, (byte) 7), board((byte) 2, (byte) 2, (byte) 4, (byte) 6, (byte) 6));

        assertEquals(3.00, result.getStrength(), 0);
    }

    @Test
    public void setI() {
        RankCalculationDto result = victim.calculation(hand((byte) 2, (byte) 2), board((byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6));

        assertEquals(4.00, result.getStrength(), 0);
    }

    @Test
    public void setII() {
        RankCalculationDto result = victim.calculation(hand((byte) 2, (byte) 7), board((byte) 2, (byte) 3, (byte) 4, (byte) 2, (byte) 6));

        assertEquals(4.00, result.getStrength(), 0);
    }

    @Test
    public void noHandSet() {
        RankCalculationDto result = victim.calculation(hand((byte) 8, (byte) 7), board((byte) 2, (byte) 2, (byte) 4, (byte) 2, (byte) 6));

        assertEquals(4.00, result.getStrength(), 0);
    }

    @Test
    public void fullHouseI() {
        RankCalculationDto result = victim.calculation(hand((byte) 2, (byte) 7), board((byte) 2, (byte) 2, (byte) 4, (byte) 5, (byte) 7));

        assertEquals(7.00, result.getStrength(), 0);
    }

    @Test
    public void fullHouseII() {
        RankCalculationDto result = victim.calculation(hand((byte) 2, (byte) 7), board((byte) 2, (byte) 7, (byte) 4, (byte) 5, (byte) 7));

        assertEquals(7.00, result.getStrength(), 0);
    }

    @Test
    public void fullHouseIII() {
        RankCalculationDto result = victim.calculation(hand((byte) 7, (byte) 7), board((byte) 2, (byte) 2, (byte) 4, (byte) 5, (byte) 2));

        assertEquals(7.00, result.getStrength(), 0);
    }

    @Test
    public void fullHouseIV() {
        RankCalculationDto result = victim.calculation(hand((byte) 7, (byte) 7), board((byte) 7, (byte) 2, (byte) 4, (byte) 4, (byte) 2));

        assertEquals(7.00, result.getStrength(), 0);
    }

    @Test
    public void noHandFullHouse() {
        RankCalculationDto result = victim.calculation(hand((byte) 8, (byte) 7), board((byte) 2, (byte) 2, (byte) 6, (byte) 2, (byte) 6));

        assertEquals(7.00, result.getStrength(), 0);
    }

    @Test
    public void kingI() {
        RankCalculationDto result = victim.calculation(hand((byte) 2, (byte) 2), board((byte) 2, (byte) 3, (byte) 4, (byte) 2, (byte) 6));

        assertEquals(8.00, result.getStrength(), 0);
    }

    @Test
    public void kingII() {
        RankCalculationDto result = victim.calculation(hand((byte) 2, (byte) 7), board((byte) 2, (byte) 2, (byte) 4, (byte) 2, (byte) 6));

        assertEquals(8.00, result.getStrength(), 0);
    }

    @Test
    public void kingIII() {
        RankCalculationDto result = victim.calculation(hand((byte) 2, (byte) 7), board((byte) 2, (byte) 2, (byte) 7, (byte) 2, (byte) 6));

        assertEquals(8.00, result.getStrength(), 0);
    }

    @Test
    public void noHandKing() {
        RankCalculationDto result = victim.calculation(hand((byte) 8, (byte) 7), board((byte) 2, (byte) 2, (byte) 2, (byte) 2, (byte) 6));

        assertEquals(8.00, result.getStrength(), 0);
    }

    private Board board(byte a, byte b, byte c, byte d, byte e) {
        return new Board(new ArrayList<>(
                Arrays.asList(
                        new CardForParsing(a, "c"),
                        new CardForParsing(b, "s"),
                        new CardForParsing(c, "h"),
                        new CardForParsing(d, "s"),
                        new CardForParsing(e, "s"))));
    }

    private Hand hand(byte a, byte b) {
        return new Hand(new ArrayList<>(
                Arrays.asList(
                        new CardForParsing(a, "d"),
                        new CardForParsing(b, "s"))));
    }
}
