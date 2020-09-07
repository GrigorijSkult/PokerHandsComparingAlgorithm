package holdem.console;

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

public class CardMainUITest {

    private final MainSortingService victim = new MainSortingService(
            new DataInputConversionService(new UserInputValidation(), new CardRankMapper()),
            new HandRankCalculationService(new PairRangCalculationService(), new StraightAndSuitedRangCalculationService()),
            new DataOutputConversionService(new CardRankMapper()));

    @Test
    public void runMainUI0001() {
        String result = victim.mainSorting("4cKs4h8s7s Ad4s Ac4d As9s KhKd 5d6d");

        assertEquals("Ad4s=Ac4d 5d6d As9s KhKd", result);
    }

    @Test
    public void runMainUI0002() {
        String result = victim.mainSorting("2h3h4h5d8d KdKs 9hJh");

        assertEquals("KdKs 9hJh", result);
    }
}
