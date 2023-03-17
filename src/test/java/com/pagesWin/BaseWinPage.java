package com.pagesWin;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.support.PageFactory;


public class BaseWinPage {

    public static WindowsDriver driver;

    public BaseWinPage(WindowsDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
