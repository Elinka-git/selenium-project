package com.selenium.course.utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.util.Properties;

public class Configuration {

    Properties configProps = new Properties();

    private Configuration() {
        try {
            configProps.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class SingletonHelper {
        private static final Configuration INSTANCE = new Configuration();
    }

    public static Configuration getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public String get(String key) {
        return get(key, String.class);
    }

    public <T> T get(String key, Class<T> claz) {
        return get(key, claz, null);
    }

    @SuppressWarnings("unchecked")
	public <T> T get(String key, Class<T> claz, T defaultValue) {
        Object value = null;

        String stringValue = resolve(key);

        if (stringValue == null) {
            value = defaultValue;
        } else {
            if (String.class.equals(claz)) {
                value = stringValue;
            } else if (Integer.class.equals(claz)) {
                value = Integer.parseInt(stringValue);
            } else if (Boolean.class.equals(claz)) {
                value = Boolean.parseBoolean(stringValue);
            } else {
                throw new IllegalArgumentException("Unsupported property class :: " + claz);
            }
        }

        return (T) value;

    }

    public String resolve(String key) {
        String envVarName = "AT_" + key.replaceAll("\\.", "_").toUpperCase();

        String value = System.getenv(envVarName);

        if (value == null || value.isEmpty()) {
            value = System.getProperty(key);
        }

        if (value == null || value.isEmpty()) {
            value = configProps.getProperty(key);
        }

        return value == null ? value : value.trim();
    }

    public WebDriver getSeleniumDriver() {
        if (configProps.get("browser").equals("firefox")) {
        	System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + configProps.getProperty("gecko.driver.location"));
            return new FirefoxDriver();
        } else if (configProps.get("browser").equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + configProps.getProperty("chrome.driver.location"));
            return new ChromeDriver();
        } else {
            throw new RuntimeException("No driver location/property specified");
        }
    }

}
