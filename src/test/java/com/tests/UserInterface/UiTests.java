package com.tests.UserInterface;

import com.config.BaseWinAppDriver;
import com.pagesWin.MainMenu;
import com.pagesWin.Printers;
import com.pagesWin.ScriptFrame;
import com.pagesWin.setupDialogs.EpsonCwC6050A;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;


@Tag("UI")
public class UiTests extends BaseWinAppDriver {

    @Test
    @DisplayName("Test: check the UI elements state in “Auto nozzle check” area if “Enabled nozzle self-test” disabled.")
    @Description("Test: check the UI elements state in “Auto nozzle check” area if “Enabled nozzle self-test” disabled.")
    public void checkAutoNozzleCheckElementsDisabled() throws InterruptedException
    {
        //input data
        String vendor = "PEPSN (EPSON)";
        String model = "EPSON_CW_C6050A";
        String script = "_Setup dialog";

        //initializing required pages
        MainMenu mainMenu = new MainMenu(driver);
        Printers printers = new Printers(driver);
        ScriptFrame scriptFrame = new ScriptFrame(driver);
        EpsonCwC6050A epson = new EpsonCwC6050A(driver);

        //select vendor of the printer
        printers.selectVendor(vendor);
        Thread.sleep(1000);
        //select specific model of the printer
        printers.selectPrinterModel(model);
        Thread.sleep(1000);
        //select script to be executed
        scriptFrame.selectScript(script);
        Thread.sleep(1000);
        //press Test button to execute selected script for selected printer
        mainMenu.pressTestButton();
        Thread.sleep(1000);

        epson.deselectNozzleCheckbox();

        //check elements are active
        Assertions.assertEquals(epson.nozzleSelfTestDropdownEnabled().booleanValue(), false, "Nozzle self-test dropdown is still Enabled");
        Assertions.assertEquals(epson.cloggedNozzleDetectionDropdownEnabled().booleanValue(), false, "Nozzle detection dropdown is still Enabled");
        Assertions.assertEquals(epson.nozzleClogToleranceFieldEnabled().booleanValue(), false, "Nozzle clog tolerance field is still Enabled");
        Assertions.assertEquals(epson.selfTestIntervalFieldEnabled().booleanValue(), false, "Self test interval field is still Enabled");
    }

    @Test
    @DisplayName("Test: check the UI elements state in “Auto nozzle check” area if “Enabled nozzle self-test” enabled.")
    @Description("Test: check the UI elements state in “Auto nozzle check” area if “Enabled nozzle self-test” enabled.")
    public void checkAutoNozzleCheckElementsEnabled() throws InterruptedException
    {
        //input data
        String vendor = "PEPSN (EPSON)";
        String model = "EPSON_CW_C6050A";
        String script = "_Setup dialog";

        //initializing required pages
        MainMenu mainMenu = new MainMenu(driver);
        Printers printers = new Printers(driver);
        ScriptFrame scriptFrame = new ScriptFrame(driver);
        EpsonCwC6050A epson = new EpsonCwC6050A(driver);

        //select vendor of the printer
        printers.selectVendor(vendor);
        Thread.sleep(1000);
        //select specific model of the printer
        printers.selectPrinterModel(model);
        Thread.sleep(1000);
        //select script to be executed
        scriptFrame.selectScript(script);
        Thread.sleep(1000);
        //press Test button to execute selected script for selected printer
        mainMenu.pressTestButton();
        Thread.sleep(1000);
        epson.selectNozzleCheckbox();

        //check elements are active
        Assertions.assertEquals(epson.nozzleSelfTestDropdownEnabled().booleanValue(), true, "Nozzle self-test dropdown is still Disabled");
        Assertions.assertEquals(epson.cloggedNozzleDetectionDropdownEnabled().booleanValue(), true, "Nozzle detection dropdown is still Disabled");
        Assertions.assertEquals(epson.nozzleClogToleranceFieldEnabled().booleanValue(), true, "Nozzle clog tolerance field is still Disabled");
        Assertions.assertEquals(epson.selfTestIntervalFieldEnabled().booleanValue(), true, "Self test interval field is still Disabled");
    }

    @Test
    @DisplayName("Test: check the reaction of the groups of the GUI elements")
    @Description("Test: check the reaction of the groups of the GUI elements")
    public void checkGuiReaction() throws InterruptedException {

        //input data
        String vendor = "PEPSN (EPSON)";
        String model = "EPSON_CW_C6050A";
        String script = "_Setup dialog";

        //initializing required pages
        MainMenu mainMenu = new MainMenu(driver);
        Printers printers = new Printers(driver);
        ScriptFrame scriptFrame = new ScriptFrame(driver);
        EpsonCwC6050A epson = new EpsonCwC6050A(driver);

        //select vendor of the printer
        printers.selectVendor(vendor);
        Thread.sleep(1000);
        //select specific model of the printer
        printers.selectPrinterModel(model);
        Thread.sleep(1000);
        //select script to be executed
        scriptFrame.selectScript(script);
        Thread.sleep(1000);
        //press Test button to execute selected script for selected printer
        mainMenu.pressTestButton();
        Thread.sleep(1000);

        epson.selectInkTypeByName("Matte ink");
        Thread.sleep(1000);
        /*
        1st assert should be to check that 'Ratio of black to composite' should be set to '-2',
        but it's not possible to define the value of the slider + it's even not possible to define the slider itself.
        Will investigate further.
         */
        Assertions.assertEquals(epson.getMediaContentTypeDropdownValue(), "Plain paper", "Current value is not 'Plain paper'");
        Assertions.assertEquals(epson.getPrintQualityDropdownValue(), "Speed", "Current value is not 'Speed'");

    }
}
