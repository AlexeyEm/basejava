package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.List;

public abstract class AbstractStorage implements Storage {

    public void update(Resume r) {
        Object searchKey = getSearchKey(r);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(r.getUuid());
        }
        doUpdate(searchKey, r);
    }

    public void save(Resume r) {
        Object searchKey = getSearchKey(r);
        if (isExist(searchKey)) {
            throw new ExistStorageException(r.getUuid());
        }
        doSave(searchKey, r);
    }

    public Resume get(Resume r) {
        Object searchKey = getSearchKey(r);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(r.getUuid());
        }
        return doGet(searchKey);
    }

    public void delete(Resume r) {
        Object searchKey = getSearchKey(r);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(r.getUuid());
        }
        doDelete(searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = getList();
        list.sort((o1, o2) -> o1.getFullName().compareTo(o2.getFullName()));
        return list;
    }

    protected abstract List<Resume> getList();

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doSave(Object searchKey, Resume r);

    protected abstract void doUpdate(Object searchKey, Resume r);

    protected abstract void doDelete(Object searchKey);

    protected abstract Object getSearchKey(Resume r);

    protected abstract boolean isExist(Object searchKey);
}
