package com.lrosa.bingrewards.services;

import com.lrosa.bingrewards.config.BingConfig;
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
public class LoginService {
  private final BingConfig bingConfig;

  @SneakyThrows
  public void login(WebDriver driver) {
    var user = bingConfig.getUser();
    var passwd = bingConfig.getPassword();

    var url = "https://login.live.com";
    log.info("Accessing {}", url);
    driver.get(url);
    driver.findElement(By.name("loginfmt")).sendKeys(user, Keys.RETURN);

    new WebDriverWait(driver, 5)
        .until(ExpectedConditions.visibilityOfElementLocated(By.id("i0118")))
        .sendKeys(passwd);
    new WebDriverWait(driver, 2)
        .until(ExpectedConditions.elementToBeClickable((By.name("KMSI"))))
        .click();
    driver.findElement(By.id("idSIButton9")).click();

    log.info("Processing login with user {}", user);
    new WebDriverWait(driver, 15)
        .until(ExpectedConditions.urlContains("https://account.microsoft.com/"));

    log.info("Logged in with user {}", user);
  }
}
