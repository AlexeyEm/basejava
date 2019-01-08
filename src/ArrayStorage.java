import static java.util.Arrays.fill;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0; //Размер массива без null

    int getStorageIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equalsIgnoreCase(uuid))
                return i;
        }
        return -1;
    }

    void clear() {
        fill(storage, 0, size + 1, null);
        size = 0;
    }

    Resume get(String uuid) {
        int storageIndex = getStorageIndex(uuid);
        if (storageIndex != -1)
            return storage[storageIndex];
        else {
            System.out.println("Get method: Resume '" + uuid + "' not found in Storage");
            return null;
        }
    }

    void save(Resume r) {
        if (storage.length == size - 1) {
            System.out.println("Save method: Storage is full");
        } else {
            if (getStorageIndex(r.uuid) == -1) {
                storage[size] = r;
                size++;
            }
        }
    }

    void delete(String uuid) {
        int storageIndex = getStorageIndex(uuid);
        if (storageIndex == -1) {
            System.out.println("Delete method: Resume '" + uuid + "' not found in Storage");
        } else {
            storage[storageIndex] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    void update(Resume resume) {
        int storageIndex = getStorageIndex(resume.uuid);
        if (storageIndex != -1) {
            storage[storageIndex] = resume;
        } else {
            System.out.println("Update method: Resume '" + resume.uuid + "' not found in Storage");
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