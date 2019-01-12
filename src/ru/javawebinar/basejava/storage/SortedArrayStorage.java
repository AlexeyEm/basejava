package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void delete(int index, String uuid) {
        if (index < 0) {
            System.out.println("Delete Method: Resume " + uuid + " not exist");
        } else {
            //Copy with shift left
            System.arraycopy(storage, index + 1, storage, index, size - index - 1);
            //Delete last element
            storage[size - 1] = null;
            size--;
        }
    }

    @Override
    protected void save(int index, Resume r) {
        if (index > -1) {
            System.out.println("Resume " + r.getUuid() + " already exist");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {
            if (size < -index) {
                storage[size] = r;
            } else {
                //Index of the new element
                index = -index - 1;
                //Copy with shift right
                System.arraycopy(storage, index, storage, index + 1, size - index);
                storage[index] = r;
            }
            size++;
        }
    }
}