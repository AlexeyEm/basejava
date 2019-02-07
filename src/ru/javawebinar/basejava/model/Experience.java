package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Experience {
    private String name;
    private String url;
    private LocalDate beginDate;
    private LocalDate endDate;
    private String title;
    private String text;

    public Experience(String name, String url, LocalDate beginDate, LocalDate endDate, String title, String text) {
        this.name = name;
        this.url = url;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.title = title;
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Experience)) return false;

        Experience that = (Experience) o;

        if (!name.equals(that.name)) return false;
        if (!Objects.equals(url, that.url)) return false;
        if (!beginDate.equals(that.beginDate)) return false;
        if (!endDate.equals(that.endDate)) return false;
        if (!title.equals(that.title)) return false;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + beginDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Experience{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", beginDate=" + beginDate.format(DateTimeFormatter.ofPattern("MM/yyyy")) +
                ", endDate=" + endDate.format(DateTimeFormatter.ofPattern("MM/yyyy")) +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
