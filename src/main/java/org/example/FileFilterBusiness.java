package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.model.Params;
import org.example.service.FileService;
import org.example.service.StatisticService;

@Slf4j
public class FileFilterBusiness {

    public static void startBusiness(Params params){
        StatisticService statisticService = new StatisticService();
        FileService fileService = new FileService(statisticService);
        fileService.filesFilter(params);
        if(params.sEnabled()){
            String statistic = statisticService.getStatistic();
            log.info(statistic);
        }
        if(params.fEnabled()){
            String fullStatistic = statisticService.getFullStatistic();
            log.info(fullStatistic);
        }
    }
}
