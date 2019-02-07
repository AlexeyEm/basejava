package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.stream.Collectors;

public class ExperienceSection extends AbstractSection {
    private List<Experience> list;

    public ExperienceSection(List<Experience> list) {
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