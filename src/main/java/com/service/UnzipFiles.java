package com.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;

import java.util.zip.ZipInputStream;

import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;

import net.lingala.zip4j.core.ZipFile;



public class UnzipFiles {

    public static void unzip(String source, String destination){
        //String source = "some/compressed/file.zip";
        //String destination = "some/destination/folder";
        String password = "password";

        try {
            ZipFile zipFile = new ZipFile(source);
            System.out.println("source is: " + source);
            if (zipFile.isEncrypted()) {
                zipFile.setPassword(password);
            }
            zipFile.extractAll(destination);
        } catch (net.lingala.zip4j.exception.ZipException e) {
            e.printStackTrace();
        }
    }

    public static void unzip2(String zipFilePath, String destDir) {
        File dir = new File(destDir);
        // create output directory if it doesn't exist
        if(!dir.exists()) dir.mkdirs();
        FileInputStream fis;
        //buffer for read and write data to file
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while(ze != null){
                String fileName = ze.getName();
                File newFile = new File(destDir + File.separator + fileName);
                System.out.println("Unzipping to " + newFile.getAbsolutePath());
                //create directories for sub directories in zip
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                //close this ZipEntry
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            //close last ZipEntry
            zis.closeEntry();
            zis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void extract(String zipFilePath, String destDir) throws IOException {

        File destDirectory = new File(destDir);
        if (!destDirectory.exists()) {
            System.out.println("Folder does not exit");
            destDirectory.mkdirs();
            System.out.println("Creating a folder");
        }

        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                File destFile = new File(destDir, zipEntry.getName());
                if (zipEntry.isDirectory()) {
                    destFile.mkdirs();
                } else {
                    FileOutputStream outputStream = new FileOutputStream(destFile);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = zipInputStream.read(buffer)) > 0) {
                        outputStream.write(buffer, 0, length);
                    }
                    outputStream.close();
                }
            }
        }
    }

    public static void unzipIt(String fileZip, String destinationPath) throws IOException {

        File destDir = new File(destinationPath);

        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            File newFile = newFile(destDir, zipEntry);
            if (zipEntry.isDirectory()) {
                if (!newFile.isDirectory() && !newFile.mkdirs()) {
                    throw new IOException("Failed to create directory " + newFile);
                }
            } else {
                // fix for Windows-created archives
                File parent = newFile.getParentFile();
                if (!parent.isDirectory() && !parent.mkdirs()) {
                    throw new IOException("Failed to create directory " + parent);
                }

                // write file content
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
            }
            zipEntry = zis.getNextEntry();
        }

        zis.closeEntry();
        zis.close();
    }

    public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }

    public static void unZipFile( String zipFilePath, String destDir ) {
        File dir = new File( destDir ) ;
        // creating an output directory if it doesn't exist already
        if( !dir.exists( ) ) dir.mkdirs( ) ;
        FileInputStream fis ;
        // buffer to read and write data in the file
        byte[ ] buffer = new byte[ 1024 ] ;
        try {
            fis = new FileInputStream( zipFilePath ) ;
            ZipInputStream zis = new ZipInputStream( fis ) ;
            ZipEntry ZE = zis.getNextEntry( ) ;
            while( ZE != null ) {
                String fileName = ZE.getName( ) ;
                File newFile = new File( destDir + File.separator + fileName ) ;
                System.out.println( " Unzipping to " + newFile.getAbsolutePath( ) ) ;
                // create directories for sub directories in zip
                new File( newFile.getParent( ) ).mkdirs( ) ;
                FileOutputStream fos = new FileOutputStream( newFile ) ;
                int len ;
                while ( ( len = zis.read( buffer ) )  > 0 ) {
                    fos.write( buffer, 0, len ) ;
                }
                fos.close( ) ;
                // close this ZipEntry
                zis.closeEntry( ) ;
                ZE = zis.getNextEntry( ) ;
            }
            // close last ZipEntry
            zis.closeEntry( ) ;
            zis.close( ) ;
            fis.close( ) ;
        } catch ( IOException e ) {
            e.printStackTrace( ) ;
        }
    }

    public static void sevenZExtractor (String sourceFilePath, String destinationFolderPath) throws IOException {


        try (SevenZFile sevenZFile = new SevenZFile(new File(sourceFilePath))) {
            SevenZArchiveEntry entry;
            while ((entry = sevenZFile.getNextEntry()) != null) {
                String entryFilePath = destinationFolderPath + File.separator + entry.getName();
                File entryFile = new File(entryFilePath);
                if (entry.isDirectory()) {
                    entryFile.mkdirs();
                } else {
                    File parentFile = entryFile.getParentFile();
                    if (!parentFile.exists()) {
                        parentFile.mkdirs();
                    }
                    byte[] buffer = new byte[(int) entry.getSize()];
                    sevenZFile.read(buffer);
                    try (FileOutputStream fos = new FileOutputStream(entryFile)) {
                        fos.write(buffer);
                    }
                }
            }
        }
    }

}
