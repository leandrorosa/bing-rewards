package com.lrosa.bingrewards;

import com.lrosa.bingrewards.services.AppService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BingRewardsApplication {

  public static void main(String[] args) {
    var ctx = SpringApplication.run(BingRewardsApplication.class, args);
    var appService = ctx.getBean(AppService.class);
    appService.run();
  }
}
