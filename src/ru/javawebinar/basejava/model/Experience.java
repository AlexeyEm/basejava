package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Experience {
    private String name;
    private LocalDate beginDate;
    private LocalDate endDate;
    private String title;
    private String text;

    public Experience(String name, LocalDate beginDate, LocalDate endDate, String title, String text) {
        this.name = name;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.title = title;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Experience{" +
                "name='" + name + '\'' +
                ", beginDate=" + beginDate.format(DateTimeFormatter.ofPattern("MM/yyyy")) +
                ", endDate=" + endDate.format(DateTimeFormatter.ofPattern("MM/yyyy")) +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
