//https://pokeristby.ru/baza-znaniy/primery-po-opredeleniju-vyigryshnoj-kombinatsij-na-vskrytii-post-387/
package holdem.texasHoldem.mainSortingService;

import holdem.core.mappers.CardRankMapper;
import holdem.texasHoldem.DataInputConversionService;
import holdem.texasHoldem.DataOutputConversionService;
import holdem.texasHoldem.MainSortingService;
import holdem.texasHoldem.strengthCalculationServices.HandRankCalculationService;
import holdem.texasHoldem.strengthCalculationServices.PairRangCalculationService;
import holdem.texasHoldem.strengthCalculationServices.StraightAndSuitedRangCalculationService;
import holdem.texasHoldem.validation.UserInputValidation;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PairAndStraightSuitedCombinationControversialTests {
    private final MainSortingService victim = new MainSortingService(
            new DataInputConversionService(new UserInputValidation(), new CardRankMapper()),
            new HandRankCalculationService(new PairRangCalculationService(), new StraightAndSuitedRangCalculationService()),
            new DataOutputConversionService(new CardRankMapper()));

    @Test//5
    public void flashWithTwoFullHouses() {
        String result = victim.mainSorting("5s7h5hAh7s As5d 7d8s Jh9h");

        assertEquals("Jh9h As5d 7d8s", result);
    }

    @Test//10
    public void fleshAndTwoPair() {
        String result = victim.mainSorting("ThJhKhJc2h AhAs AdAc");

        assertEquals("AdAc AhAs", result);
    }

    @Test//11
    public void straightAndSet() {
        String result = victim.mainSorting("ThJdKh3c2h 3h3s AdQs");

        assertEquals("3h3s AdQs", result);
    }

    @Test//12
    public void BadBeat_FlashRoyalAndKing() {
        String result = victim.mainSorting("TsJs3d3cKs 3h3s AsQs");

        assertEquals("3h3s AsQs", result);
    }

    @Test//17
    public void straightAndPair() {
        String result = victim.mainSorting("6d8sJc9hTs AdAc 7h7c");

        assertEquals("AdAc 7h7c", result);
    }

    @Test//27
    public void boardCombinationMainBankDivision() {
        String result = victim.mainSorting("6h3d5c7s4s 6s7c 4d5d");

        assertEquals("6s7c=4d5d", result);
    }

}
