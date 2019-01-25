package ru.javawebinar.basejava.storage;

import org.junit.Test;

public class ListStorageTest extends AbstractStorageTest {
    public ListStorageTest() {
        super(new ListStorage());
    }

    @Test
    @Override
    public void saveOverflow() throws Exception {

    }
}