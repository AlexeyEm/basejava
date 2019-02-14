package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ExperienceSection extends AbstractSection {
    private List<Experience> organizations;

    public ExperienceSection(List<Experience> organizations) {
        Objects.requireNonNull(organizations, "organizations must not be null");
        this.organizations = organizations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExperienceSection)) return false;

        ExperienceSection that = (ExperienceSection) o;

        return organizations.equals(that.organizations);
    }

    @Override
    public int hashCode() {
        return organizations.hashCode();
    }

    @Override
    public String toString() {
        return organizations.stream()
                .map(String::valueOf)
                .collect(Collectors.joining("\n"));
    }
}