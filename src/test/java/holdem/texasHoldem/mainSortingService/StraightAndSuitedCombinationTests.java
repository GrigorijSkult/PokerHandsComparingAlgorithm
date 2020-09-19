// https://pokeristby.ru/baza-znaniy/primery-po-opredeleniju-vyigryshnoj-kombinatsij-na-vskrytii-post-387/
package holdem.texasHoldem.mainSortingService;

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

public class StraightAndSuitedCombinationTests {
    private final MainSortingService victim = new MainSortingService(
            new DataInputConversionService(new UserInputValidation(), new CardRankMapper()),
            new HandRankCalculationService(new PairRangCalculationService(), new StraightAndSuitedRangCalculationService()),
            new DataOutputSortingService(),
            new OutputStringBuildingService(new CardRankMapper()));

    @Test//3
    public void bankDivisionStraightSimilarCards() {
        String result = victim.mainSorting("7s4d6c3h5h 7cAc Js5s");

        assertEquals("7cAc=Js5s", result);
    }

    @Test//13
    public void twoFlashes() {
        String result = victim.mainSorting("2sJs3s3c2h 8s9s QsKs 7sTs");

        assertEquals("8s9s 7sTs QsKs", result);
    }

    @Test//16
    public void twoStraights() {
        String result = victim.mainSorting("6d8sJc9hTs QsAc 7h7c QhAh");

        assertEquals("7h7c QsAc=QhAh", result);
    }

    @Test//19
    public void flashAndStraight() {
        String result = victim.mainSorting("ThJdKhJc2h Ah3h AdQs AsQh 2h7s");

        assertEquals("2h7s AdQs=AsQh Ah3h", result);
    }

    @Test//20
    public void twoFlashesAllOneSuitType() {
        String result = victim.mainSorting("2sKs3sAsJs 8s9s 6sTs");

        assertEquals("8s9s 6sTs", result);
    }

    @Test//21
    public void bankDivisionTwoFlashesAllOneSuitType() {
        String result = victim.mainSorting("9sKs8sAsJs 2s6s 3s7s");

        assertEquals("2s6s=3s7s", result);
    }

    @Test//25
    public void twoStraight() {
        String result = victim.mainSorting("7d8sJcTs9h KdQc 7h7c Qh3s");

        assertEquals("7h7c Qh3s KdQc", result);
    }

    @Test
    public void twoFlashAndStraight() {
        assertEquals("4h5h Ts5c As4d",
                victim.mainSorting("4s6s7d8s9s As4d Ts5c 4h5h"));
    }
}
