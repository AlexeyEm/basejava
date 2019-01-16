package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() throws Exception {
        Resume resume = new Resume(UUID_1);
        storage.update(resume);
        Assert.assertEquals(resume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExists() throws Exception {
        storage.update(new Resume("dummy"));
    }

    @Test
    public void getAll() throws Exception {
        storage.delete(UUID_2);
        storage.save(new Resume(UUID_2));
        Assert.assertArrayEquals(ExpectedStorage(), storage.getAll());
    }

    protected abstract Resume[] ExpectedStorage();

    @Test
    public void save() throws Exception {
        storage.save(new Resume());
        Assert.assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(new Resume(UUID_1));
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        storage.clear();
        int i = 0;
        try {
            for (; i < 6; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            if (i < 5) {
                Assert.fail("Test of saveOverflow failed");
            } else {
                throw new StorageException(e.getMessage(), "");
            }
        }
    }

    @Test
    public void delete() throws Exception {
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(new Resume(UUID_1), storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }
}