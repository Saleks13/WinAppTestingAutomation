package com.tests.Functional;

import com.config.BaseWinAppDriver;
import com.pagesWin.MainMenu;
import com.pagesWin.Printers;
import com.pagesWin.ScriptFrame;
import com.pagesWin.setupDialogs.EpsonCwC6050A;
import com.service.MdbReader;

import com.service.SupportFile;
import io.qameta.allure.Description;
import io.qameta.allure.Muted;
import io.qameta.allure.Step;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.*;

import java.sql.SQLException;

@Tag("Functional")
public class FunctionalTests extends BaseWinAppDriver {

    @Test
    @Tag("Regression") //
    @Muted // in this case because it always failed due to known issue.
    @TmsLink("Here might be the test case ID from management system")
    @DisplayName("Functional Test: check that barcode output is not changed with driver in development")
    @Description("Functional Test: check that barcode output is not changed with driver in development")
    public void checkBarcodePrintoutForNewDriver() throws InterruptedException, SQLException {

        String printerVendor = "PEPSN (EPSON)";
        String printerName = "EPSON_CW_C6050A";
        String scriptName = "Barcodes";
        String mdbFilePath = "TestDriverScript/Data/PEPSN.mdb";

        MainMenu mainMenu = new MainMenu(driver);
        Printers printers = new Printers(driver);
        ScriptFrame scriptFrame = new ScriptFrame(driver);
        EpsonCwC6050A epson = new EpsonCwC6050A(driver);


        //select vendor of the printer
        printers.selectVendor(printerVendor);
        //select specific model of the printer
        printers.selectPrinterModel(printerName);
        //select script to be executed
        scriptFrame.selectScript(scriptName);
        //press Test button to execute selected script for selected printer
        mainMenu.pressBuildButton();
        //switch to current driver in prod to get Expected results
        mainMenu.selectMakeSnapshotRadioBox();
        //press Test button to execute selected script for selected printer
        mainMenu.pressBuildButton();

        //get the number of rows in the results mdb
        System.out.println("number of rows is: " + MdbReader.getRowsCount(mdbFilePath));
        //get the status of the test: OK - true / KO - false
        Boolean testResult = MdbReader.isTestPassed(mdbFilePath, printerName);
        //if test fails - print the results with Barcode type and result
        if (!testResult) MdbReader.printResultsForPrinter(mdbFilePath, printerName);
        //assert the result is OK
        Assertions.assertTrue(testResult, "Test did not pass. Printouts via old and new driver are not the same");
    }

    @Test
    @TmsLink("Here might be the test case ID from management system")
    @DisplayName("Functional test: check that Completion Beeper setting \"No beep\" are applied")
    @Description("Functional test: check that Completion Beeper setting \"No beep\" are applied")
    public void checkNoBeepApplied() throws InterruptedException {
        //test input data
        String printerVendor = "PEPSN (EPSON)";
        String printerName = "EPSON_CW_C6050A";
        String scriptName = "_Setup dialog";
        String beepType = "No beep";
        String textToBePresent = "^S(CUB,S,N";
        String txtFilePath = "TestDriverScript/Output/print.txt";
        Boolean result = false;

        MainMenu mainMenu = new MainMenu(driver);
        Printers printers = new Printers(driver);
        ScriptFrame scriptFrame = new ScriptFrame(driver);
        EpsonCwC6050A epson = new EpsonCwC6050A(driver);

        //select vendor of the printer
        printers.selectVendor(printerVendor);
        //select specific model of the printer
        printers.selectPrinterModel(printerName);
        //select script name
        scriptFrame.selectScript(scriptName);
        //press test button to open setup dialog
        mainMenu.pressTestButton();
        Thread.sleep(2000);
        //on the setup dialog select completion beep parameter
        epson.selectCompletionBeepByName(beepType);
        epson.pressOkButton();
        //^S(CUB,S,N
        Thread.sleep(2000);

        //does the print settings contain required fragment
        result = SupportFile.checkTxtFileContains(txtFilePath, textToBePresent);
        //assert the result is True
        Assertions.assertTrue(result, "The file does not contain the string.");
    }

    @Test
    @TmsLink("Here might be the test case ID from management system")
    @DisplayName("Functional test: check that Completion Beeper setting \"Beep at job end\" are applied")
    @Description("Functional test: check that Completion Beeper setting \"Beep at job end\" are applied")
    public void checkBeepAtJobEndApplied() throws InterruptedException {
        String printerVendor = "PEPSN (EPSON)";
        String printerName = "EPSON_CW_C6050A";
        String scriptName = "_Setup dialog";
        String beepType = "Beep at job end";
        String textToBePresent = "^S(CUB,S,L";
        String txtFilePath = "TestDriverScript/Output/print.txt";
        Boolean result = false;

        MainMenu mainMenu = new MainMenu(driver);
        Printers printers = new Printers(driver);
        ScriptFrame scriptFrame = new ScriptFrame(driver);
        EpsonCwC6050A epson = new EpsonCwC6050A(driver);

        //select vendor of the printer
        printers.selectVendor(printerVendor);
        //select specific model of the printer
        printers.selectPrinterModel(printerName);
        //select script name
        scriptFrame.selectScript(scriptName);
        //press test button to open setup dialog
        mainMenu.pressTestButton();
        Thread.sleep(2000);
        //on the setup dialog select completion beep parameter
        epson.selectCompletionBeepByName(beepType);
        epson.pressOkButton();
        //^S(CUB,S,N
        Thread.sleep(2000);

        //does the print settings contain required fragment
        result = SupportFile.checkTxtFileContains(txtFilePath, textToBePresent);
        //assert the result is True
        Assertions.assertTrue(result, "The file does not contain the string.");
    }

    @Test
    @TmsLink("Here might be the test case ID from management system")
    @DisplayName("Functional test: check that Completion Beeper setting \"After each label\" are applied")
    @Description("Functional test: check that Completion Beeper setting \"After each label\" are applied")
    public void checkAfterEachLabelApplied() throws InterruptedException {

        String printerVendor = "PEPSN (EPSON)";
        String printerName = "EPSON_CW_C6050A";
        String scriptName = "_Setup dialog";
        String beepType = "After each label";
        String textToBePresent = "^S(CUB,S,E";
        String txtFilePath = "TestDriverScript/Output/print.txt";
        Boolean result = false;

        MainMenu mainMenu = new MainMenu(driver);
        Printers printers = new Printers(driver);
        ScriptFrame scriptFrame = new ScriptFrame(driver);
        //EpsonCwC6050A epson = new EpsonCwC6050A(driver);

        //select vendor of the printer
        printers.selectVendor(printerVendor);
        //select specific model of the printer
        printers.selectPrinterModel(printerName);
        //select script name
        scriptFrame.selectScript(scriptName);
        //press test button to open setup dialog
        mainMenu.pressTestButton();
        Thread.sleep(2000);

        //on the setup dialog select completion beep parameter
        EpsonCwC6050A epson = new EpsonCwC6050A(driver);
        epson.selectCompletionBeepByName(beepType);
        epson.pressOkButton();
        //^S(CUB,S,N
        Thread.sleep(2000);

        //does the print settings contain required fragment
        result = SupportFile.checkTxtFileContains(txtFilePath, textToBePresent);
        //assert the result is True
        Assertions.assertTrue(result, "The file does not contain the string.");
    }


}
