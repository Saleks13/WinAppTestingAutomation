package com.service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesFile {

    static Properties prop = new Properties();

    // this method reads config.properties and returns value of the key, depending on the specified key name
    private static String getProperty(String propertyName) {
        String result = null;

        try {

            InputStream input = Files.newInputStream(Paths.get("src/main/java/com/testData/config.properties"));
            prop.load(input);
            result = prop.getProperty(propertyName);

        } catch (Exception exp) {
            System.out.println(exp.getMessage());
            System.out.println(exp.getCause());
            exp.printStackTrace();

        }
        return result;
    }

    //this get method returns value of the baseUrl from properties file
    public static String getBaseUrl() {
        return getProperty("baseUrl");
    }



}
