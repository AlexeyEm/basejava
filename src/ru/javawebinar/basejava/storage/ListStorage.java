package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> list = new ArrayList<>();

    @Override
    protected Object getSearchKey(Resume r) {
        for (int i = 0; i < list.size(); i++) {
            if (r.equals(list.get(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (int) searchKey >= 0;
    }

    @Override
    protected void doUpdate(Object searchKey, Resume r) {
        list.set((int) searchKey, r);
    }

    @Override
    protected void doSave(Object searchKey, Resume r) {
        list.add(r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return list.get((int) searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        list.remove((int) searchKey);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    protected List<Resume> getList() {
        return list;
    }

    @Override
    public int size() {
        return list.size();
    }
}