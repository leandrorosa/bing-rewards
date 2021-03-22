package com.lrosa.bingrewards.services;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppService {
  private final WebDriver mobileDriver;
  private final WebDriver desktopDriver;
  private final LoginService loginService;
  private final DesktopSearchService desktopSearchService;
  private final MobileSearchService mobileSearchService;

  public void run() {
    loginService.login(desktopDriver);
    desktopSearchService.search(desktopDriver);

    loginService.login(mobileDriver);
    mobileSearchService.search(mobileDriver);
  }
}
