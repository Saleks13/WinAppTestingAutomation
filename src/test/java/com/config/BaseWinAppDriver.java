package com.config;

import com.service.SupportFile;
import com.windriver.WinDriver;
import io.appium.java_client.windows.WindowsDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;



import java.net.URL;

public class BaseWinAppDriver {

    public WindowsDriver driver = null;
    //public WindowsDriverWait wait = new WindowsDriverWait(driver, Duration.ofSeconds(30));
    public String appPath="\"C:\\workspace\\WinAppTestingAutomation\\TestDriverScript\\DriverTestScript.exe\"";

    @BeforeEach
    public void testSetUp() throws Exception
    {
        SupportFile.createIniFile();

        DesiredCapabilities capability = new DesiredCapabilities();

        capability.setCapability("ms:experimental-webdriver", true);
        capability.setCapability("app", appPath);
        capability.setCapability("platformName", "Windows");
        capability.setCapability("deviceName", "DESKTOP-G1J38S9");

        //start WinAppDriver.exe so that is start listening to incoming requests
        WinDriver.start();
        driver = new WindowsDriver(new URL("http://127.0.0.1:4723/"), capability);
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        if (driver != null)
        {
           /* The instance of WinAppDriver will be freed once
           last test is complete
           */
            driver.close();
            Thread.sleep(1000);

        }
        WinDriver.stop();
        //remove ini file after executing test
        SupportFile.removeIniFile();
        SupportFile.killAllNotePads();
    }

}
