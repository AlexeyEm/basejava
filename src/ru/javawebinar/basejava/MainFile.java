package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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
        readFiles("./src/ru/javawebinar/basejava");
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
}
