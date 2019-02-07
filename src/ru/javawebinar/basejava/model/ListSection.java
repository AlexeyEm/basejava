package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.stream.Collectors;

public class ListSection extends AbstractSection {
    private List<String> list;

    public ListSection(List<String> list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListSection)) return false;

        ListSection that = (ListSection) o;

        return list.equals(that.list);
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }

    @Override
    public String toString() {
        return list.stream()
                .map(String::valueOf)
                .collect(Collectors.joining("\n"));
    }
}