package com.lrosa.bingrewards.services;

import com.lrosa.bingrewards.config.BingConfig;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MobileSearchService {
  private static final String BING_URL = "https://bing.com";
  private static final Integer WEB_DRIVER_WAIT_SHORT = 5000;

  private final SearchService searchService;
  private final BingConfig bingConfig;

  @SneakyThrows
  public void search(WebDriver driver) {
    driver.get(BING_URL);
    new WebDriverWait(driver, WEB_DRIVER_WAIT_SHORT)
        .until(ExpectedConditions.visibilityOfElementLocated(By.id("mHamburger")))
        .click();
    new WebDriverWait(driver, WEB_DRIVER_WAIT_SHORT)
        .until(ExpectedConditions.visibilityOfElementLocated(By.id("HBSignIn")))
        .click();
    searchService.search(driver, bingConfig.getMobileSearchCount());
  }
}
