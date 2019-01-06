/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0; //Размер массива без null

    void clear() {
        for (int i = 0; i < size + 1; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equalsIgnoreCase(uuid))
                return storage[i];
        }
        return null;
    }

    void save(Resume r) {
        Resume resume = get(r.uuid);
        if (resume == null) {
            storage[size] = r;
            size++;
        }
    }

    void delete(String uuid) {
        boolean isNeedShift = false; //Признак: нужен сдвиг

        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equalsIgnoreCase(uuid)) {
                isNeedShift = true;
                size--;
            }
            //Сдвиг влево
            if (isNeedShift) {
                storage[i] = storage[i + 1];
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resultStorage = new Resume[size];
        if (size >= 0) {
            System.arraycopy(storage, 0, resultStorage, 0, size);
        }
        return resultStorage;
    }

    int size() {
        return this.size;
    }
}