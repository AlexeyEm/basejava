package ru.javawebinar.basejava.model;

import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;

/**
 * ru.javawebinar.basejava.model.Resume class
 */
public class Resume {

    // Unique identifier
    private final String uuid;
    private String fullName;
    private Map<ContactType, ContactData> contact = new EnumMap<>(ContactType.class);
    private Map<SectionType, AbstractSection> section = new EnumMap<>(SectionType.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setContact(Map<ContactType, ContactData> contact) {
        this.contact = contact;
    }

    public void setSection(Map<SectionType, AbstractSection> section) {
        this.section = section;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resume)) return false;

        Resume resume = (Resume) o;

        if (!uuid.equals(resume.uuid)) return false;
        if (!fullName.equals(resume.fullName)) return false;
        if (!contact.equals(resume.contact)) return false;
        return section.equals(resume.section);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        result = 31 * result + contact.hashCode();
        result = 31 * result + section.hashCode();
        return result;
    }

    public String toString() {
        return fullName + '\n' +
               contact + '\n' +
               section;
    }
}