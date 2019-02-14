package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ListSection extends AbstractSection {
    private List<String> items;

    public ListSection(List<String> items) {
        Objects.requireNonNull(items, "items must not be null");
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListSection)) return false;

        ListSection that = (ListSection) o;

        return items.equals(that.items);
    }

    @Override
    public int hashCode() {
        return items.hashCode();
    }

    @Override
    public String toString() {
        return items.stream()
                .map(String::valueOf)
                .collect(Collectors.joining("\n"));
    }
}