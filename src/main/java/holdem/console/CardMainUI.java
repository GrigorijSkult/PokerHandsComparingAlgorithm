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

import java.util.Scanner;

public class CardMainUI {

    public static String gameType = "texas";
    private final MainSortingService mainSortingService;

    public CardMainUI() {
        this.mainSortingService = new MainSortingService(
                new DataInputConversionService(new UserInputValidation(), new CardRankMapper()),
                new HandRankCalculationService(new PairRangCalculationService(), new StraightAndSuitedRangCalculationService()),
                new DataOutputSortingService(),
                new OutputStringBuildingService(new CardRankMapper()));
    }

    public void runMainUI() {
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (userInput != null) {
            try {
                if (userInput.equals("--omaha")) {
                    gameType = "omaha";
                } else if (userInput.equals("--texas")) {
                    gameType = "texas";
                }
                String result = mainSortingService.mainSorting(userInput);
                System.out.println(result);
            } catch (IllegalArgumentException e) {
                System.out.println("Calculation cant be performed: " + e.getMessage());
            }
            userInput = sc.nextLine();
        }
        sc.close();
    }
}
