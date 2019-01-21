package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void clear() {

    }

    public void update(Resume r) {
        if (containsResume(r)) {
            setStorage(r);
        } else {
            throw new NotExistStorageException(r.getUuid());
        }
    }

    public void save(Resume r) {
        if (containsResume(r)) {
            throw new ExistStorageException(r.getUuid());
        } else {
            addResume(r);
        }
    }

    public Resume get(String uuid) {
        Resume resume = new Resume(uuid);
        if (containsResume(resume)) {
            return getResume(resume);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public void delete(String uuid) {

    }

    public Resume[] getAll() {
        return new Resume[0];
    }

    public int size() {
        return 0;
    }

    protected abstract boolean containsResume(Resume r);
    protected abstract void addResume(Resume r);
    protected abstract Resume getResume(Resume r);
    protected abstract void setStorage(Resume r);
}
