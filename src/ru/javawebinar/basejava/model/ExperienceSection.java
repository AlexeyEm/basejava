package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.stream.Collectors;

public class ExperienceSection extends AbstractSection {
    private List<Experience> list;

    public ExperienceSection(List<Experience> list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExperienceSection)) return false;

        ExperienceSection that = (ExperienceSection) o;

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