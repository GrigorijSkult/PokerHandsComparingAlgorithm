package holdem.texasHoldem;

import holdem.core.dto.CalculationResultDto;
import holdem.core.dto.HandsAndBoardDto;
import holdem.texasHoldem.strengthCalculationServices.HandRankCalculationService;

import java.util.ArrayList;

public class MainSortingService {
    private final DataInputConversionService dataInputConversionService;
    private final HandRankCalculationService handRankCalculationService;
    private final DataOutputConversionService dataOutputConversionService;

    public MainSortingService(DataInputConversionService dataInputConversionService,
                              HandRankCalculationService handRankCalculationService,
                              DataOutputConversionService dataOutputConversionService) {
        this.dataInputConversionService = dataInputConversionService;
        this.handRankCalculationService = handRankCalculationService;
        this.dataOutputConversionService = dataOutputConversionService;
    }

    public String mainSorting(String userInputData) {
        HandsAndBoardDto handsAndBoardDto = dataInputConversionService.inputConversion(userInputData);

        ArrayList<CalculationResultDto> handsCalculationResults = handRankCalculationService.calculation(handsAndBoardDto);

        return dataOutputConversionService.execute(handsCalculationResults);
    }
}
