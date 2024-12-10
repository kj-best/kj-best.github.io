package com.cow.horse;

import com.cow.horse.common.utils.OrderTaskHandler;
import com.cow.horse.common.utils.PathUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FleaMarketApplication {

    public static void main(String[] args) {
        System.out.println("Project Path: " + PathUtils.getClassLoadRootPath());
        SpringApplication.run(FleaMarketApplication.class, args);
        OrderTaskHandler.run();
    }

}
