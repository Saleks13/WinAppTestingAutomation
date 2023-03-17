package com.tests.Demo;

import com.config.BaseWinAppDriver;
import com.pagesWin.Printers;
import com.pagesWin.ScriptFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


public class WinAppTestDemo extends BaseWinAppDriver {

    //@Test
    @DisplayName("Select specific printer model of specific vendor")
    public void selectPrinterTest() throws InterruptedException
    {
        Printers printers = new Printers(driver);
        ScriptFrame scriptFrame = new ScriptFrame(driver);
        //select vendor of the printer
        printers.selectVendor("PZ (ZEBRA)");
        Thread.sleep(1000);
        //select specific model of the printer
        printers.selectPrinterModel("BROTHER_RJ3230B");
        Thread.sleep(1000);
        //select script to be executed
        scriptFrame.selectScript("_Setup dialog");
        Thread.sleep(1000);
        //press Test button to execute selected script for selected printer
        driver.findElementByName("Test").click();
        Thread.sleep(1000);
        //open Print Speed dropdown list:
        driver.findElementByAccessibilityId("1030").click();
        //select third in the list
        driver.findElementByAccessibilityId("1030").sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
        //set focus to Temperature field
        driver.findElementByAccessibilityId("107").click();
        //remove current value and replace it with new value "25"
        driver.findElementByAccessibilityId("107").sendKeys(Keys.BACK_SPACE, Keys.BACK_SPACE, "25");

    }
    
}
