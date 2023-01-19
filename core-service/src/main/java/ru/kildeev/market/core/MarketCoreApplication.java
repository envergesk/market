package ru.kildeev.market.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarketCoreApplication {
    //ifAvailable для проверки продукта в базе
    //

    public static void main(String[] args) {
        SpringApplication.run(MarketCoreApplication.class, args);
    }

}
