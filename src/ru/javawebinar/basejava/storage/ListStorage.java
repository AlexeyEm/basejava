package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
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
    protected int getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected Resume doGet(int index) {
        return storage.get(index);
    }

    @Override
    protected void saveResume(int index, Resume r) {
        storage.add(r);
    }

    @Override
    protected void updateResume(int index, Resume r) {
        storage.set(index, r);
    }

    @Override
    protected void deleteResume(int index) {
        storage.remove(index);
    }
}