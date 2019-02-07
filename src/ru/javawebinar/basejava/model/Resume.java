package ru.javawebinar.basejava.model;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * ru.javawebinar.basejava.model.Resume class
 */
public class Resume {

    // Unique identifier
    private final String uuid;
    private String fullName;
    private Map<ContactType, ContactData> contact;
    private Map<SectionType, AbstractSection> section;

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
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) && Objects.equals(fullName, resume.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName);
    }

//    @Override
//    public String toString() {
//        return "Resume{" +
//                "uuid='" + uuid + '\'' +
//                ", fullName='" + fullName + '\'' +
//                '}';
//    }

    public String toString() {
        return fullName + '\n' +
               contact + '\n' +
               section;
    }
}