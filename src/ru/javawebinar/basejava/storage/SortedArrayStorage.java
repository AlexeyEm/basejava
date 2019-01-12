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
    protected void deleteElement(int index) {
        //Copy with shift left
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }

    @Override
    protected void saveElement(int index, Resume r) {
        if (size < -index) {
            storage[size] = r;
        } else {
            int indexNewElement = -index - 1;
            //Copy with shift right
            System.arraycopy(storage, indexNewElement, storage, indexNewElement + 1, size - indexNewElement);
            storage[indexNewElement] = r;
        }
    }
}