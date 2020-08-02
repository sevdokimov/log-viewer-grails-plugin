package com.logviewer.grails;

import com.logviewer.api.LvUiConfigurer;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GrailsUiConfigurer implements LvUiConfigurer {
    @Autowired
    private Environment environment;

    @Override
    public Config getUiConfig() {
        String appPackages = environment.getProperty("log-viewer.home-package");
        if (appPackages == null)
            return null;
        
        List<String> packagesList = Arrays.stream(appPackages.split("[, ;]+")).filter(s -> !s.isEmpty()).collect(Collectors.toList());
        if (packagesList.isEmpty())
            return null;

        return ConfigFactory.parseMap(Collections.singletonMap("properties.\"java-exception-renderer.home-package\"", packagesList));
    }
}
