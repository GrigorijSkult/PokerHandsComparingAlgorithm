//https://pokeristby.ru/baza-znaniy/primery-po-opredeleniju-vyigryshnoj-kombinatsij-na-vskrytii-post-387/
package holdem.texasHoldem.texasHoldem.mainSortingService;

import holdem.core.mappers.CardRankMapper;
import holdem.texasHoldem.DataInputConversionService;
import holdem.texasHoldem.DataOutputSortingService;
import holdem.texasHoldem.MainSortingService;
import holdem.texasHoldem.OutputStringBuildingService;
import holdem.texasHoldem.strengthCalculationServices.HandRankCalculationService;
import holdem.texasHoldem.strengthCalculationServices.PairRangCalculationService;
import holdem.texasHoldem.strengthCalculationServices.StraightAndSuitedRangCalculationService;
import holdem.texasHoldem.validation.UserInputValidation;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PairCombinationTests {
    private final MainSortingService victim = new MainSortingService(
            new DataInputConversionService(new UserInputValidation(), new CardRankMapper()),
            new HandRankCalculationService(new PairRangCalculationService(), new StraightAndSuitedRangCalculationService()),
            new DataOutputSortingService(),
            new OutputStringBuildingService(new CardRankMapper()));

    @Test//1
    public void bankDivisionSquare() {
        String result = victim.mainSorting("AsAcAhAdKd QcQs KcKs");

        assertEquals("QcQs=KcKs", result);
    }

    @Test//1.2
    public void boardCombinationAndKicker() {
        String result = victim.mainSorting("AsAcAhAdQd QcQs KcKs");

        assertEquals("QcQs KcKs", result);
    }

    @Test//2
    public void bankDivisionBoardCombination() {
        String result = victim.mainSorting("AsAcAhAdKd KdQs KcKs");

        assertEquals("KdQs=KcKs", result);
    }

    @Test//4
    public void onePlayerPair() {
        String result = victim.mainSorting("TsJdQcThAh 7c8c 8s8h");

        assertEquals("7c8c 8s8h", result);
    }

    @Test//6
    public void bankDivisionOnlyBoardCombination() {
        String result = victim.mainSorting("JsJd8c8h8s 7s7d TcKs");

        assertEquals("7s7d=TcKs", result);
    }

    @Test//7
    public void highPair() {
        String result = victim.mainSorting("3s7d3c5h7h Ah5d Ad9s");

        assertEquals("Ad9s Ah5d", result);
    }

    @Test//14
    public void twoTwoPairs() {
        String result = victim.mainSorting("AsKs5cJhQd KdQs KhJc");

        assertEquals("KhJc KdQs", result);
    }

    @Test//22
    public void twoPairsKickerChoice() {
        String result = victim.mainSorting("8dKs5c3h9s KdQs KhJc");

        assertEquals("KhJc KdQs", result);
    }

    @Test//23
    public void twoFullHousesTripsChoice() {
        String result = victim.mainSorting("KdKsJcJhJs AdAc KhTc");

        assertEquals("AdAc KhTc", result);
    }

    @Test//24
    public void onePair() {
        String result = victim.mainSorting("AhQd4c5s3s 3sQh 9cQc");

        assertEquals("9cQc 3sQh", result);
    }

    @Test//8
    public void bankDivisionNoCombination() {
        String result = victim.mainSorting("3sTd3hKc2h Ah5d Ac9s");

        assertEquals("Ah5d=Ac9s", result);
    }

    @Test//9
    public void noCombinationKickerChoice() {
        String result = victim.mainSorting("3s7d3hJc2h AhKd AdQs");

        assertEquals("AdQs AhKd", result);
    }

    @Test//15
    public void kickerChoiceTwoPairs() {
        String result = victim.mainSorting("8d8h9cTh9s KcQc 7h7c");

        assertEquals("7h7c KcQc", result);
    }

    @Test//18
    public void bankDivisionBoardCombinationKickerChoice() {
        String result = victim.mainSorting("Kc9sThKdTs As3s Ad9d");

        assertEquals("As3s=Ad9d", result);
    }

    @Test//26
    public void boardCombinationKickerChoice() {
        String result = victim.mainSorting("2d5s5c9hTs KdQs KhJc");

        assertEquals("KhJc KdQs", result);
    }
}
