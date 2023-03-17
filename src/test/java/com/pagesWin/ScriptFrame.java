package com.pagesWin;

import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

public class ScriptFrame extends BaseWinPage {
    public ScriptFrame(WindowsDriver driver) {
        //this.driver = driver;
        super(driver);
    }
    @Step("Select script to be executed - {0}")
    public void selectScript(String scriptName){
        driver.findElementByName(scriptName).click();
        driver.findElementByName(scriptName).sendKeys(Keys.SPACE);
    }



}
