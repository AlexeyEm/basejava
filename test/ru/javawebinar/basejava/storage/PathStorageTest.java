package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.Serialization.ObjectStreamStorage;

public class PathStorageTest extends AbstractStorageTest {
    public PathStorageTest() {
        super(new PathStorage(STORAGE_DIR.toString(), new ObjectStreamStorage()));
    }
}