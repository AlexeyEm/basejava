package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume r) {
        Object searchKey = getSearchKey(r.getUuid());
        if (!isExistResume(searchKey)) {
            throw new NotExistStorageException(r.getUuid());
        }
        updateResume(searchKey, r);
    }

    public void save(Resume r) {
        Object searchKey = getSearchKey(r.getUuid());
        if (isExistResume(searchKey)) {
            throw new ExistStorageException(r.getUuid());
        }
        saveResume(searchKey, r);
    }

    public Resume get(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExistResume(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return doGet(searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExistResume(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        deleteResume(searchKey);
    }

    protected abstract Resume doGet(Object searchKey);

    protected abstract void saveResume(Object searchKey, Resume r);

    protected abstract void updateResume(Object searchKey, Resume r);

    protected abstract void deleteResume(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isExistResume(Object searchKey);
}
