package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

import static java.lang.Math.abs;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index > -1) {
            System.out.println("Resume " + r.getUuid() + " already exist");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {
            if (size < abs(index)) {
                storage[size] = r;
            } else {
                //Shift right
                for (int i = size; i > (abs(index) - 1); i--) {
                    storage[i] = storage[i - 1];
                }
                storage[abs(index) - 1] = r;
            }
            size++;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Delete Method: Resume " + uuid + " not exist");
        } else {
            for (int i = index; i < size; i++) {
                //Shift left
                storage[i] = storage[i + 1];
            }
            size--;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        int a = Arrays.binarySearch(storage, 0, size, searchKey);
        return a;
    }
}