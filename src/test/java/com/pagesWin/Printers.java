package com.pagesWin;

import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

public class Printers extends BaseWinPage {

    public Printers(WindowsDriver driver) {
        //this.driver = driver;
        super(driver);
    }
    @Step("Select vendor of the printer - {0}")
    public void selectVendor(String vendorName) throws InterruptedException {

        //open dropdown list with vendors
        driver.findElementByAccessibilityId("1005").click();
        //select required vendor
        driver.findElementByName(vendorName).click();
        Thread.sleep(1000);
    }

    @Step("Select specific model of the printer - {0}")
    public void selectPrinterModel(String printerModel) {

        //System.out.println(driver.findElementByName("DOMINES_200").getAttribute("Toggle.ToggleState"));
        if (driver.findElementByName(printerModel).isSelected()) {
            System.out.println("Printer " + printerModel + " selected");
        } else {
            driver.findElementByName(printerModel).click();
            driver.findElementByName(printerModel).sendKeys(Keys.SPACE);
        }


    }


}
