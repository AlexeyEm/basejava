package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileStorage extends AbstractFileStorage {

    protected SerializationMethod sm;

    public FileStorage(File dir, SerializationMethod sm) {
        super(dir);
        this.sm = sm;
    }

    @Override
    protected void doWrite(Resume r, OutputStream os) throws IOException {
        sm.doWrite(r, os);
    }

    @Override
    protected Resume doRead(InputStream is) throws IOException {
        return sm.doRead(is);
    }
}
