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
    private static Resume resumeOne = new Resume(UUID_1);
    private static Resume resumeTwo = new Resume(UUID_2);
    private static Resume resumeThree = new Resume(UUID_3);
    private static Resume resumeDummy = new Resume(DUMMY);

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(resumeOne);
        storage.save(resumeTwo);
        storage.save(resumeThree);
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
        storage.update(resumeOne);
        Assert.assertSame(resumeOne, storage.get(resumeOne.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(resumeDummy);
    }

    @Test
    public void getAll() throws Exception {
        Resume[] expectedStorage = new Resume[3];
        expectedStorage[0] = resumeOne;
        expectedStorage[1] = resumeTwo;
        expectedStorage[2] = resumeThree;
        Assert.assertEquals(expectedStorage.length, storage.getAll().length);
    }

    @Test
    public void save() throws Exception {
        storage.save(resumeDummy);
        Assert.assertSame(resumeDummy, storage.get(resumeDummy.getUuid()));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(resumeOne);
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
        } finally {
            storage.save(new Resume());
        }
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_1);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete(DUMMY);
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(resumeOne, storage.get(resumeOne.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get(DUMMY);
    }
}