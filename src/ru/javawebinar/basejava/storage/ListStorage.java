package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {
    private ArrayList<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void delete(String uuid) {
        Resume resume = new Resume(uuid);
        if (storage.contains(resume)) {
            storage.remove(resume);
            storage.trimToSize();
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[storage.size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected boolean containsResume(Resume r) {
        return storage.contains(r);
    }

    @Override
    protected void addResume(Resume r) {
        storage.add(r);
    }

    @Override
    protected Resume getResume(Resume r) {
        return storage.get(storage.indexOf(r));
    }

    @Override
    protected void setStorage(Resume r) {
        storage.set(storage.indexOf(r), r);
    }
}