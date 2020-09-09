package holdem.console;

import holdem.core.mappers.CardRankMapper;
import holdem.texasHoldem.DataInputConversionService;
import holdem.texasHoldem.DataOutputConversionService;
import holdem.texasHoldem.MainSortingService;
import holdem.texasHoldem.strengthCalculationServices.HandRankCalculationService;
import holdem.texasHoldem.strengthCalculationServices.PairRangCalculationService;
import holdem.texasHoldem.strengthCalculationServices.StraightAndSuitedRangCalculationService;
import holdem.texasHoldem.validation.UserInputValidation;

import java.util.Scanner;

public class CardMainUI {
    private final MainSortingService mainSortingService;

    public CardMainUI() {
        this.mainSortingService = new MainSortingService(
                new DataInputConversionService(new UserInputValidation(), new CardRankMapper()),
                new HandRankCalculationService(new PairRangCalculationService(), new StraightAndSuitedRangCalculationService()),
                new DataOutputConversionService(new CardRankMapper()));
    }

    public void runMainUI() {
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (userInput != null) {
            try {
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
