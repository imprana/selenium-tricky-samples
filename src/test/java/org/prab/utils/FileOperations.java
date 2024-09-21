package org.prab.utils;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class FileOperations {
    public static void deleteFilesFromDir(String folderName) {
        File dir = new File(folderName);
        File[] files = null;

        if (dir.exists())
            files = dir.listFiles();

        if (files == null || files.length == 0) {
            System.out.println("No files available to delete");
        } else {
            for (File file : files) {
                file.delete();
            }
            System.out.println("All files are deleted");
        }
    }

    public static File getLatestFileFromDir(String folderName) {
        File dir = new File(folderName);
        File[] files = dir.listFiles();

        if (files != null) {
            while (Objects.requireNonNull(files).length == 0) {
                if (files.length == 0)
                    files = dir.listFiles();
            }
        }

        File lastModifiedFile = Objects.requireNonNull(files)[0];
        for (int i = 1; i < files.length; i++) {
            if (lastModifiedFile.lastModified() < files[i].lastModified()) {
                lastModifiedFile = files[i];
            }
        }
        return lastModifiedFile;
    }

    public static boolean validateFileDownload(String folderName, String fileName, boolean delFile) throws Exception {
        int time = 30; // Maximum timeout 30 seconds
        boolean isFileDownloaded = false;
        int i = 0;

        System.out.println("Waiting for file to be downloaded");
        while (!isFileDownloaded && i <= time) {
            File file = getLatestFileFromDir(folderName);
            if (file.getName().equals(fileName)) {
                isFileDownloaded = true;
                Thread.sleep(1000);
                if (delFile) {
                    file.delete(); // delete the file once found
                }
                System.out.println("File downloaded in: " + folderName + File.separator + file.getName());
            } else {
                i += 5;
                Thread.sleep(2000);
            }
        }
        return isFileDownloaded;
    }

    public static String getPDFContent(String filePath) throws IOException {

        File file = new File(filePath);
        PDDocument document = Loader.loadPDF(file);
        PDFTextStripper stripper = new PDFTextStripper();
        String content = stripper.getText(document);
        document.close();

        return content;
    }
}
