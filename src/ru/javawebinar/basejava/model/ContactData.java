package ru.javawebinar.basejava.model;

public class ContactData {
    private String value;

    public ContactData(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}