package com.stock.util;

import com.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by Fly on 2018/12/21.
 */

@Configuration
@EnableScheduling
public class Timer {

    @Autowired
    private StockService stockService;

    //@Scheduled(cron = "0 43 9  * * ?" )
    @Scheduled(cron = "0 0 0  * * ?" )
    public void produceStock() {
        stockService.produceStock();

        System.out.println("-------------produce success");
    }
}
