package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    protected void saveResume(int index, Resume r) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            insertElement(r, index);
            size++;
        }
    }

    @Override
    protected void updateResume(int index, Resume r) {
        storage[index] = r;
    }

    @Override
    protected void deleteResume(int index) {
        fillDeletedElement(index);
        storage[size - 1] = null;
        size--;
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume r, int index);

    protected abstract int getIndex(String uuid);

    @Override
    protected Resume doGet(int index) {
        return storage[index];
    }
}