package com.lrosa.bingrewards.config;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import lombok.Getter;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DriverConfig {
  private static final String DESKTOP_AGENT =
      "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 "
          + "(KHTML, lie Gecko) Chrome/64.0.3282.140 Safari/537.36 Edge/17.17134";
  private static final String MOBILE_AGENT =
      "Mozilla/5.0 (Windows Phone 10.0; Android 4.2.1; WebView/3.0) "
          + "AppleWebKit/537.36 (KHTML, like Gecko) coc_coc_browser/64.118.222 Chrome/52.0.2743.116 Mobile "
          + "Safari/537.36 Edge/15.15063";

  @Bean
  public ChromeDriver mobileDriver() {
    return buildDriver(MOBILE_AGENT);
  }

  @Bean
  public ChromeDriver desktopDriver() {
    return buildDriver(DESKTOP_AGENT);
  }



  private ChromeDriver buildDriver(String agent) {
    var options = new ChromeOptions();
    options.addArguments("--headless");
    options.addArguments("--disable-gpu", "--ignore-certificate-errors");
    options.addArguments(String.format("user-agent=%s", agent));
    options.addArguments("--disable-extensions");
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");

    Map<String, Object> prefs = new HashMap<String, Object>();
    prefs.put("googlegeolocationaccess.enabled", true);
    prefs.put("profile.default_content_setting_values.geolocation", 1); // 1:allow 2:block
    prefs.put("profile.default_content_setting_values.notifications", 1);
    prefs.put("profile.managed_default_content_settings", 1);
    options.setExperimentalOption("prefs", prefs);

    var driver = new ChromeDriver(options);
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    return driver;
  }
}
