package holdem.texasHoldem;

import holdem.core.dto.CalculationResultDto;
import holdem.core.dto.HandsAndBoardDto;
import holdem.texasHoldem.strengthCalculationServices.HandRankCalculationService;

import java.util.ArrayList;

public class MainSortingService {
    private final DataInputConversionService dataInputConversionService;
    private final HandRankCalculationService handRankCalculationService;
    private final DataOutputSortingService dataOutputSortingService;
    private final OutputStringBuildingService outputStringBuildingService;

    public MainSortingService(DataInputConversionService dataInputConversionService,
                              HandRankCalculationService handRankCalculationService,
                              DataOutputSortingService dataOutputSortingService, OutputStringBuildingService outputStringBuildingService) {
        this.dataInputConversionService = dataInputConversionService;
        this.handRankCalculationService = handRankCalculationService;
        this.dataOutputSortingService = dataOutputSortingService;
        this.outputStringBuildingService = outputStringBuildingService;
    }

    public String mainSorting(String userInputData) {

        HandsAndBoardDto handsAndBoardDto = dataInputConversionService.inputConversion(userInputData);

        ArrayList<CalculationResultDto> handsCalculationResults = handRankCalculationService.calculation(handsAndBoardDto);

        handsCalculationResults = dataOutputSortingService.execute(handsCalculationResults);

        return outputStringBuildingService.execute(handsCalculationResults);
    }
}
