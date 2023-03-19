package com.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SupportFile {

    //this method is used before starting execution of the test, so that we know the initial state of the app nad its settings
    public static void createIniFile() {
        try {
            //make sure to specify your *.ini file location string
            Runtime.getRuntime().exec("cmd.exe /c copy \"C:\\workspace\\WinAppTestingAutomation\\TestDriverScript\\start.ini\" \"C:\\workspace\\WinAppTestingAutomation\\TestDriverScript\\DriverTestScript.ini\"");
            System.out.println("Ini file created.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //method to remove ini file - used after each test
    public static void removeIniFile() {
        try {
            //make sure to specify your *.ini file location string
            Runtime.getRuntime().exec("cmd.exe /c del /f \"C:\\workspace\\WinAppTestingAutomation\\TestDriverScript\\DriverTestScript.ini\"");
            System.out.println("Ini file removed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //method to kill notepad windows after tests
    public static void killAllNotePads(){
        try {
            Runtime.getRuntime().exec("cmd.exe /c taskkill /IM notepad.exe /F");
            System.out.println("Notepads closed");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getExePath(String sourcePath) {
        String newExeFileFullPath = sourcePath + ".exe";
        try {
            String command = "cmd.exe /c copy " + sourcePath + " " + newExeFileFullPath;
            System.out.println("command: " + command);
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newExeFileFullPath;
    }

    public static void installDriver(String path) throws IOException, InterruptedException {
        System.out.println("Path: " + path);
        String fullPath = System.getProperty("user.dir") + "\\" + path;
        System.out.println("Full path: " + fullPath);
        String argument = "/c start /wait " + fullPath;
        ProcessBuilder builder = new ProcessBuilder("cmd", argument);
        builder.inheritIO();
        Process p = builder.start();
        int exitCode = p.waitFor();
        System.out.println("Installation completed with exit code " + exitCode);
    }



    public static Boolean checkTxtFileContains(String filePath, String stringToHave) {

        boolean containsString = false;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                //print each line in the file
                //System.out.println(line);
                if (line.contains(stringToHave)) {
                    containsString = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (containsString) {
            System.out.println("The file contains the string.");
        } else {
            System.out.println("The file does not contain the string.");
        }
        return containsString;
    }


}
