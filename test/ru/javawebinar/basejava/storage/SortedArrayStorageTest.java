package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class SortedArrayStorageTest extends AbstractArrayStorageTest{
    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }

    @Override
    protected Resume[] ExpectedStorage() {
        Resume[] storage = new Resume[3];
        storage[0] = new Resume("uuid1");
        storage[1] = new Resume("uuid2");
        storage[2] = new Resume("uuid3");
        return storage;
    }
}