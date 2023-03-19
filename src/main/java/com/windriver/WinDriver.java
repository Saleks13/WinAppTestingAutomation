package com.windriver;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class WinDriver
{
    //make sure the location is correct
    public static void start()
    {
        try
        {
            Desktop desktop = Desktop.getDesktop();

            File file = new File("C:\\Program Files (x86)\\Windows Application Driver\\WinAppDriver.exe");

            /* Check if there is support for Desktop or not */
            if(!Desktop.isDesktopSupported())
            {
                System.out.println("not supported");
                return;
            }

            if (file.exists())
            {
                System.out.println("Open WinAppDriver.exe\n");
                desktop.open(file);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Encountered Exception\n");
            throw new RuntimeException(e);
        }
    }

    public static void stop()
    {
        try
        {
            Runtime.getRuntime().exec("cmd.exe /c taskkill /IM WinAppDriver.exe /F");
            System.out.println("Closing app");
            //ProcessBuilder processBuilder = new ProcessBuilder("taskkill ","/f","/IM","WinAppDriver.exe");
            //processBuilder.start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
