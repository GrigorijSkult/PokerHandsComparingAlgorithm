package holdem.console;

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

public class CardMainUITest {

    private final MainSortingService victim = new MainSortingService(
            new DataInputConversionService(new UserInputValidation(), new CardRankMapper()),
            new HandRankCalculationService(new PairRangCalculationService(), new StraightAndSuitedRangCalculationService()),
            new DataOutputSortingService(),
            new OutputStringBuildingService(new CardRankMapper()));

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

    @Test
    public void runMainUI0003() {
        String result = victim.mainSorting("3s7sAhQhTd 8h4s 9c4h Kd9s 3hTs 9h6c TcKh 6sKs 8cAs 2c6h");

        assertEquals("2c6h 8h4s 9c4h=9h6c 6sKs Kd9s TcKh 8cAs 3hTs", result);
    }

}
