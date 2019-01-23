package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void delete(String uuid) {
        if (storage.remove(uuid) == null) {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[storage.size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected int getIndex(String uuid) {
        return -1;
    }

    @Override
    protected Resume doGet(int index) {
        return null;
    }

    @Override
    protected void saveResume(Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void updateResume(Resume r) {
        saveResume(r);
    }

    @Override
    protected void deleteResume(String uuid) {

    }
}