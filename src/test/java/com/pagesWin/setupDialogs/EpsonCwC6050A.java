package com.pagesWin.setupDialogs;

import com.pagesWin.BaseWinPage;
import io.appium.java_client.pagefactory.WindowsFindBy;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EpsonCwC6050A extends BaseWinPage {

    public EpsonCwC6050A(WindowsDriver driver) {
        super(driver);
        //PageFactory.initElements(driver, this);
    }

    String nozzleCheckSelector = "Enable/Disable nozzle self-test";
    String nozzleSelfTestDropdownSelector = "1134";
    String cloggedNozzleDetectionDropdownSelector = "1135";
    String nozzleClogToleranceFieldSelector = "1184";
    String selfTestIntervalFieldSelector = "1187";
    String inkTypeDropdownSelector = "1175";
    String mediaContentTypeDropdownSelector = "1124";
    String printQualityDropdownSelector = "1125";
    //Completion Beeper selectors
    String completionBeeperDropdownSelector = "1130";
    String noBeep = "No beep"; //default option0
    String beepAtEndJob = "Beep at job end"; //option1
    String afterEachLabel = "After each label"; //option2

    //bottom footer buttons OK/Cancel/Help
    String okButtonSelector = "1";

    public static WebDriverWait wait = new WebDriverWait(driver, 30);

    @Step("Select \"Nozzle self test\" checkbox")
    public void selectNozzleCheckbox() {
        if (driver.findElementByName(nozzleCheckSelector).isSelected()) {
            System.out.println("Already Selected");

        } else {
            driver.findElementByName(nozzleCheckSelector).click();
            System.out.println("Enabled nozzle self-test");
        }
    }

    @Step("Deselect \"Nozzle self test\" checkbox")
    public void deselectNozzleCheckbox() {
        if (driver.findElementByName(nozzleCheckSelector).isSelected()) {
            driver.findElementByName(nozzleCheckSelector).click();
            System.out.println("Disabled nozzle self-test");
        } else {
            System.out.println("Already Selected");
        }
    }

    public Boolean nozzleSelfTestDropdownEnabled() {
        Boolean result = null;
        result = driver.findElementByAccessibilityId(nozzleSelfTestDropdownSelector).isEnabled();
        return result;
    }

    public Boolean cloggedNozzleDetectionDropdownEnabled() {
        Boolean result = null;
        result = driver.findElementByAccessibilityId(cloggedNozzleDetectionDropdownSelector).isEnabled();
        return result;
    }

    public Boolean nozzleClogToleranceFieldEnabled() {
        Boolean result = null;
        result = driver.findElementByAccessibilityId(nozzleClogToleranceFieldSelector).isEnabled();
        return result;
    }

    public Boolean selfTestIntervalFieldEnabled() {
        Boolean result = null;
        result = driver.findElementByAccessibilityId(selfTestIntervalFieldSelector).isEnabled();
        return result;
    }

    public void openInkTypeDropdown() {
        driver.findElementByAccessibilityId(inkTypeDropdownSelector).click();
    }

    @Step("Select in type - {0}")
    public void selectInkTypeByName(String name) {

        driver.findElementByAccessibilityId(inkTypeDropdownSelector).click();
        WebElement inkType = driver.findElementByName(name);
        Actions action = new Actions(driver);
        action.moveToElement(inkType).perform();
        inkType.sendKeys(Keys.ENTER);
    }

    public String getMediaContentTypeDropdownValue() {
        //String result = null;
        return driver.findElementByAccessibilityId(mediaContentTypeDropdownSelector).getText();
    }

    public String getPrintQualityDropdownValue() {
        //String result = null;
        return driver.findElementByAccessibilityId(printQualityDropdownSelector).getText();
    }

    @Step("On the setup dialog select completion beep parameter - {0}")
    public void selectCompletionBeepByName(String name) {

        WebElement dropdownElement = driver.findElementByAccessibilityId(completionBeeperDropdownSelector);
        wait.until(ExpectedConditions.visibilityOf(dropdownElement));
        dropdownElement.click();
        WebElement beepType = driver.findElementByName(name);
        Actions action = new Actions(driver);
        action.moveToElement(beepType).perform();
        beepType.sendKeys(Keys.ENTER);
    }

    @Step("Press OK button")
    public void pressOkButton() {
        driver.findElementByAccessibilityId(okButtonSelector).click();
    }


}
