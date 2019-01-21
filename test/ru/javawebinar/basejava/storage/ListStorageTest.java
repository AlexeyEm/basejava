package ru.javawebinar.basejava.storage;

import org.junit.Test;

public class ListStorageTest extends AbstractArrayStorageTest {
    public ListStorageTest() {
        super(new ListStorage());
    }

    @Test
    @Override
    public void saveOverflow() throws Exception {

    }
}