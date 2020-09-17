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

    @Test
    public void runMainUI0004() {
        assertEquals("2cJc Ks4c=Kh4h Kc7h KdJs 6h7d 2hAh",
                victim.mainSorting("5c6dAcAsQs Ks4c KdJs 2hAh Kh4h Kc7h 6h7d 2cJc"));
    }

    @Test
    public void runMainUI0005() {
        assertEquals("9h7h 2dTc KcAs 7sJd TsJc Qh8c 5c4h",
                victim.mainSorting("3d4s5dJsQd 5c4h 7sJd KcAs 9h7h 2dTc Qh8c TsJc"));
    }

    @Test
    public void runMainUI0006() {
        assertEquals("3sKc KhTd Kd7c=7sTs Ac8s AdQc 4d3c",
                victim.mainSorting("5d5h6d7dAh 3sKc KhTd Kd7c Ac8s 4d3c AdQc 7sTs"));
    }

    @Test
    public void runMainUI0007() {
        assertEquals("Ts8h Js2d Jh8d TdQs 2h5d",
                victim.mainSorting("3s4c7c7dAs TdQs Ts8h Jh8d Js2d 2h5d"));
    }
}
