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
        CardMainUI.gameType = "texas";
        String result = victim.mainSorting("4cKs4h8s7s Ad4s Ac4d As9s KhKd 5d6d");

        assertEquals("Ad4s=Ac4d 5d6d As9s KhKd", result);
    }

    @Test
    public void runMainUI0002() {
        CardMainUI.gameType = "texas";
        String result = victim.mainSorting("2h3h4h5d8d KdKs 9hJh");

        assertEquals("KdKs 9hJh", result);
    }

    @Test
    public void runMainUI0003() {
        CardMainUI.gameType = "texas";
        String result = victim.mainSorting("3s7sAhQhTd 8h4s 9c4h Kd9s 3hTs 9h6c TcKh 6sKs 8cAs 2c6h");

        assertEquals("2c6h 8h4s 9c4h=9h6c 6sKs Kd9s TcKh 8cAs 3hTs", result);
    }

    @Test
    public void megaOmahaHoldemTest() {
        CardMainUI.gameType = "omaha";
        assertEquals("2c2d3c3d 4c4d5c5d 6c6d7c7h", victim.mainSorting(("7s7dKsKhQs 2c2d3c3d 4c4d5c5d 6c6d7c7h")));
//        assertEquals("4d3h2hQh 4c8s9d5d", victim.mainSorting(("QdTd3sJcTs 4d3h2hQh 4c8s9d5d")));
//        assertEquals("AcAs5h2c KcQcJd7d", victim.mainSorting(("KhQdQsKs5d KcQcJd7d AcAs5h2c")));
//        assertEquals("KdKs7h5d AhJc5h4c", victim.mainSorting(("4h7s4d2h5c KdKs7h5d AhJc5h4c")));
//        assertEquals("Td9s8d4c AsJcJs3c", victim.mainSorting(("4s5cQc3hQh AsJcJs3c Td9s8d4c")));
//        assertEquals("8h7d5s2d AcKhTs8c", victim.mainSorting(("6c4s7c9c9d 8h7d5s2d AcKhTs8c")));
//        assertEquals("KdTdTs2s AsKcKs9d", victim.mainSorting(("9c8cKh3sQs AsKcKs9d KdTdTs2s")));
//        assertEquals("8h7d5s2d AcKhTs8c", victim.mainSorting(("6c4s7c9c9d 8h7d5s2d AcKhTs8c")));
//        assertEquals("AcKhTc2d AhAsJs6h", victim.mainSorting(("7h8dKcTh9h AhAsJs6h AcKhTc2d")));
//        assertEquals("Ac7c5c2c Kh6h3h3s", victim.mainSorting(("4h6s3dAhQh Ac7c5c2c Kh6h3h3s")));
//        assertEquals("Tc9d9h3h 6c5d4d2c", victim.mainSorting(("4c3s9c2d6h 6c5d4d2c Tc9d9h3h")));
//        assertEquals("Ad8h7c4d AsTs8d2h", victim.mainSorting("AhTc9c4sQh Ad8h7c4d AsTs8d2h"));
//        assertEquals("Ad9c7s4d KcKhQsJh", victim.mainSorting(("9sQd7c8c8s KcKhQsJh Ad9c7s4d")));
//        assertEquals("JcTh9h6s JsTs9s7h As7d5s2d", victim.mainSorting(("Tc8sQs6d2s JcTh9h6s JsTs9s7h As7d5s2d")));
//        assertEquals("Ac7c4h3h AdAhQd6d", victim.mainSorting(("3dJd8d8sAs Ac7c4h3h AdAhQd6d")));
//        assertEquals("Kh8d7s6h AhJh9h6d QdTdTs2h", victim.mainSorting(("4hJdTh3s8c AhJh9h6d QdTdTs2h Kh8d7s6h")));
//        assertEquals("JhTh7s6s QdQh4d3d", victim.mainSorting(("Qs2cKs8c6h JhTh7s6s QdQh4d3d")));
//        assertEquals("JdJs9d6s KdQd9c5c", victim.mainSorting(("5dQs9h8s4c KdQd9c5c JdJs9d6s")));
//        assertEquals("AsQdQsJd Jh8s7d6h Ah8h6s4c", victim.mainSorting(("KhQh4hAc3h AsQdQsJd Jh8s7d6h Ah8h6s4c")));
//        assertEquals("AhQdTcTh KdQcQs4d JhTs7c4c", victim.mainSorting(("4sQhKh3sAc AhQdTcTh KdQcQs4d JhTs7c4c")));
//        assertEquals("Kc6c6d2c KhQhJh7c", victim.mainSorting(("Th2sQcQd4s KhQhJh7c Kc6c6d2c")));
//        assertEquals("KhTd4c4h AcAdTh8c", victim.mainSorting(("2hJcJh6s2d KhTd4c4h AcAdTh8c")));
//        assertEquals("Ts9s8s6d AsQsTh3h", victim.mainSorting(("8c3sAdAh5s Ts9s8s6d AsQsTh3h")));
//        assertEquals("QdQsJh7h KcTs7s2c", victim.mainSorting("Jc9cKs4s3d QdQsJh7h KcTs7s2c"));
//        assertEquals("AcKh9h2c Kd5d5h3c", victim.mainSorting(("KcJcKs3h5c AcKh9h2c Kd5d5h3c")));
//        assertEquals("KhQdQs4s Ac6h3c2d", victim.mainSorting(("5d4dKs2s3d KhQdQs4s Ac6h3c2d")));
//        assertEquals("AsKhQhJh AcAh9c9h 6h4c3c2h", victim.mainSorting(("6cJs3h5dTh AcAh9c9h AsKhQhJh 6h4c3c2h")));
//        assertEquals("AcAh6c4c AsKcKhJd", victim.mainSorting(("2hKd8s5hKs AcAh6c4c AsKcKhJd")));
//        assertEquals("QhTs9d7h KhTcTd9s", victim.mainSorting(("Jd3cTh9hAc QhTs9d7h KhTcTd9s")));
//        assertEquals("JcJs5d5s AdAs9c6c KcKsQh8h", victim.mainSorting(("ThJdAh8c2c JcJs5d5s AdAs9c6c KcKsQh8h")));
    }
}
