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
    private static final String DUMMY = "dummy";
    private static final Resume RESUME_ONE = new Resume(UUID_1);
    private static final Resume RESUME_TWO = new Resume(UUID_2);
    private static final Resume RESUME_THREE = new Resume(UUID_3);
    private static final Resume RESUME_DUMMY = new Resume(DUMMY);

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_ONE);
        storage.save(RESUME_TWO);
        storage.save(RESUME_THREE);
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
        Assert.assertSame(resume, storage.get(resume.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(RESUME_DUMMY);
    }

    @Test
    public void getAll() throws Exception {
        Resume[] actualStorage = storage.getAll();
        Resume[] expectedStorage = new Resume[3];
        expectedStorage[0] = RESUME_ONE;
        expectedStorage[1] = RESUME_TWO;
        expectedStorage[2] = RESUME_THREE;
        Assert.assertArrayEquals(expectedStorage, actualStorage);
        Assert.assertEquals(expectedStorage.length, actualStorage.length);
    }

    @Test
    public void save() throws Exception {
        storage.save(RESUME_DUMMY);
        Assert.assertSame(RESUME_DUMMY, storage.get(RESUME_DUMMY.getUuid()));
        Assert.assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME_ONE);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail("Test of saveOverflow failed");
        }
        storage.save(new Resume());
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete(DUMMY);
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(RESUME_ONE, storage.get(RESUME_ONE.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get(DUMMY);
    }
}