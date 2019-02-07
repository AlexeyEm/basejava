package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.stream.Collectors;

public class ListSection extends AbstractSection {
    private List<String> list;

    public ListSection(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        String result = list.stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining("\n"));
        return result;
    }
}