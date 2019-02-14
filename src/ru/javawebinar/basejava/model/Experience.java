package ru.javawebinar.basejava.model;

import java.util.List;

public class Experience {
    private final Link homePage;
    private List<Position> positions;

    public Experience(String name, String url, List<Position> positions) {
        this.homePage = new Link(name, url);
        this.positions = positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Experience)) return false;

        Experience that = (Experience) o;

        if (!homePage.equals(that.homePage)) return false;
        return positions.equals(that.positions);
    }

    @Override
    public int hashCode() {
        int result = homePage.hashCode();
        result = 31 * result + positions.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Experience{" +
                "homePage=" + homePage +
                ", positions=" + positions +
                '}';
    }
}
