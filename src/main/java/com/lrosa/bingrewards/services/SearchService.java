package com.lrosa.bingrewards.services;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {
  private static final String BING_URL = "https://bing.com";
  private static final Integer WEB_DRIVER_WAIT_SHORT = 5000;

  @SneakyThrows
  public void search(WebDriver driver, Integer searchCount) {
    new WebDriverWait(driver, WEB_DRIVER_WAIT_SHORT)
        .until(ExpectedConditions.visibilityOfElementLocated(By.id("bnp_btn_accept")))
        .click();
    for (int i = 1; i < searchCount + 1; i++) {
      driver.get(BING_URL);
      var searchBox =
          new WebDriverWait(driver, WEB_DRIVER_WAIT_SHORT)
              .until(ExpectedConditions.visibilityOfElementLocated((By.id("sb_form_q"))));
      searchBox.clear();

      log.info("Searching bing {}/{}", i, searchCount);
      searchBox.sendKeys(genFakeName(), Keys.RETURN);
      Thread.sleep(WEB_DRIVER_WAIT_SHORT);
    }
  }

  private void openNewTab(WebDriver webDriver) {}

  private String genFakeName() {
    return new Faker().name().fullName();
  }
}
