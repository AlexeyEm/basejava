package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;

public class MainFile {
    public static void main(String[] args) {
        String filePath = ".\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println('\n');
        File dir = new File("./src/ru/javawebinar/basejava");
        readFilesPretty("./src/ru/javawebinar/basejava", 0);
    }

    public static void readFiles(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            String[] list = file.list();
            if (list != null) {
                for (String name : list) {
                    readFiles(path + "/" + name);
                }
            }
        } else {
            System.out.println(file.getName());
        }
    }

    public static void readFilesPretty(String path, int tabNum) {
        File file = new File(path);
        int r = tabNum;
        if (file.isDirectory()) {
            String[] list = file.list();
            if (list != null) {
                for (int i = 0; i < list.length; i++) {
                    if (i == 0) {
                        tabNum++;
                    }
                    System.out.println(String.join("", Collections.nCopies(r, "\t")) + list[i]);
                    readFilesPretty(path + "/" + list[i], tabNum);
                }
            }
        }
    }
}