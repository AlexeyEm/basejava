package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            writeWithException(contacts.entrySet(), dos, contact -> {
                dos.writeUTF(contact.getKey().name());
                dos.writeUTF(contact.getValue());
            });

            Map<SectionType, AbstractSection> sections = r.getSections();
            writeWithException(sections.entrySet(), dos, section -> {
                SectionType keyName = section.getKey();
                dos.writeUTF(keyName.name());
                switch (keyName) {
                    case OBJECTIVE:
                    case PERSONAL:
                        dos.writeUTF(section.getValue().toString());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> list = ((ListSection) section.getValue()).getItems();
                        writeWithException(list, dos, dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> listOrganizations = ((OrganizationSection) section.getValue()).getOrganizations();
                        writeWithException(listOrganizations, dos, organization -> {
                            dos.writeUTF(organization.getHomePage().getName());
                            dos.writeUTF(ifNull(organization.getHomePage().getUrl()));

                            List<Organization.Position> listPositions = organization.getPositions();
                            writeWithException(listPositions, dos, position -> {
                                dos.writeUTF(position.getStartDate().toString());
                                dos.writeUTF(position.getEndDate().toString());
                                dos.writeUTF(position.getTitle());
                                dos.writeUTF(ifNull(position.getDescription()));
                            });
                        });
                        break;
                    default:
                        break;
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int contactSize = dis.readInt();
            for (int i = 0; i < contactSize; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            int sectionSize = dis.readInt();
            for (int i = 0; i < sectionSize; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        resume.addSection(sectionType, new TextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> list = new ArrayList<>();
                        int listSize = dis.readInt();
                        for (int s = 0; s < listSize; s++) {
                            list.add(dis.readUTF());
                        }
                        resume.addSection(sectionType, new ListSection(list));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> listOrganizations = new ArrayList<>();
                        int orgSize = dis.readInt();
                        for (int s = 0; s < orgSize; s++) {
                            String name = dis.readUTF();
                            String url = ifEmpty(dis.readUTF());

                            List<Organization.Position> listPositions = new ArrayList<>();

                            int positionSize = dis.readInt();
                            for (int p = 0; p < positionSize; p++) {
                                listPositions.add(
                                        new Organization.Position(
                                                LocalDate.parse(dis.readUTF()),
                                                LocalDate.parse(dis.readUTF()),
                                                dis.readUTF(),
                                                ifEmpty(dis.readUTF())
                                        ));
                            }
                            listOrganizations.add(new Organization(new Link(name, url), listPositions));
                        }
                        resume.addSection(sectionType, new OrganizationSection(listOrganizations));
                        break;
                    default:
                        break;
                }
            }
            return resume;
        }
    }

    private String ifNull(String string) {
        if (string == null) {
            return "";
        }
        return string;
    }

    private String ifEmpty(String string) {
        if (string.equals("")) {
            return null;
        }
        return string;
    }

    private interface Writer<T> {
        void write(T t) throws IOException;
    }

    private <T> void writeWithException(Collection<T> collection, DataOutputStream dos, Writer<? super T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T t : collection) {
            writer.write(t);
        }
    }
}