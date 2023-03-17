package com.pagesWin;

import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Step;

public class MainMenu extends BaseWinPage {
    //public static WindowsDriver driver;

    public MainMenu(WindowsDriver driver) {
        //this.driver = driver;
        super(driver);
    }

    String compareResultRadioBoxSelector = "Compare results";
    String makeSnapshotRadioBoxSelector = "Make a snapshot of results";
    String newButtonSelector = "New";
    String deleteButtonSelector = "Delete";
    String testButtonSelector = "Test";
    String buildButtonSelector = "Build";
    String viewButtonSelector = "View";

    public void pressTestButton() {
        driver.findElementByName(testButtonSelector).click();
    }

    @Step("Press Test button to execute selected script for selected printer")
    public void pressBuildButton() {
        driver.findElementByName(buildButtonSelector).click();
        driver.findElementByName("Yes").click();
    }

    @Step("Switch to current driver in prod to get Expected results")
    public void selectMakeSnapshotRadioBox() {
        driver.findElementByName(makeSnapshotRadioBoxSelector).click();
    }


}
