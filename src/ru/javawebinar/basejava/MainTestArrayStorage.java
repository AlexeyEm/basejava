package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.ArrayStorage;
import ru.javawebinar.basejava.storage.SortedArrayStorage;
import ru.javawebinar.basejava.storage.Storage;

/**
 * Test ru.javawebinar.basejava.storage.ArrayStorage
 */
public class MainTestArrayStorage {
    static final Storage ARRAY_STORAGE = new ArrayStorage();
    static final Storage SORTED_ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1");
        Resume r2 = new Resume("uuid2");
        Resume r3 = new Resume("uuid3");
        Resume r4 = new Resume("uuid4");
        Resume r5 = new Resume("uuid5");
        Resume r6 = new Resume("uuid6");
        Resume r8 = new Resume("uuid8");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("----------Testing SORTED_ARRAY_STORAGE:");
        SORTED_ARRAY_STORAGE.save(r1);
        SORTED_ARRAY_STORAGE.save(r2);
        SORTED_ARRAY_STORAGE.save(r3);
        SORTED_ARRAY_STORAGE.save(r4);
        SORTED_ARRAY_STORAGE.save(r5);
        SORTED_ARRAY_STORAGE.save(r1);
        SORTED_ARRAY_STORAGE.save(r2);

        System.out.println("Get r6: " + SORTED_ARRAY_STORAGE.get(r6.getUuid()));

        SORTED_ARRAY_STORAGE.delete(r1.getUuid());
        SORTED_ARRAY_STORAGE.delete(r3.getUuid());
        System.out.println("Size: " + SORTED_ARRAY_STORAGE.size());
        printAllSorted();
        SORTED_ARRAY_STORAGE.save(r8);
        SORTED_ARRAY_STORAGE.save(r3);
        SORTED_ARRAY_STORAGE.save(r6);
        SORTED_ARRAY_STORAGE.save(r1);
        printAllSorted();

        SORTED_ARRAY_STORAGE.clear();
        printAllSorted();
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }

    static void printAllSorted() {
        System.out.println("\nGet All");
        for (Resume r : SORTED_ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}