package com.lrosa.bingrewards.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import lombok.Getter;

@Getter
@Configuration
public class BingConfig {

    @Value("${bing.rewards.desktop.search.count}")
    private Integer desktopSearchCount;

    @Value("${bing.rewards.mobile.search.count}")
    private Integer mobileSearchCount;

    @Value("${bing.rewards.user}")
    private String user;

    @Value("${bing.rewards.password}")
    private String password;
}
