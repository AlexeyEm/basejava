import static java.util.Arrays.fill;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size = 0; //Array's size without null

    public void clear() {
        fill(storage, 0, size, null);
        size = 0;
    }

    public Resume get(String uuid) {
        int storageIndex = getIndex(uuid);
        if (storageIndex != -1)
            return storage[storageIndex];
        else {
            System.out.println("Get method: Resume '" + uuid + "' not found in Storage");
            return null;
        }
    }

    public void save(Resume r) {
        if (storage.length == size) {
            System.out.println("Save method: Storage is full");
        } else {
            if (getIndex(r.getUuid()) == -1) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("Save method: Resume '" + r.getUuid() + "' already exists in Storage");
            }
        }
    }

    public void delete(String uuid) {
        int storageIndex = getIndex(uuid);
        if (storageIndex == -1) {
            System.out.println("Delete method: Resume '" + uuid + "' not found in Storage");
        } else {
            storage[storageIndex] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    public void update(Resume resume) {
        int storageIndex = getIndex(resume.getUuid());
        if (storageIndex != -1) {
            storage[storageIndex] = resume;
        } else {
            System.out.println("Update method: Resume '" + resume.getUuid() + "' not found in Storage");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resultStorage = new Resume[size];
        System.arraycopy(storage, 0, resultStorage, 0, size);
        return resultStorage;
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid))
                return i;
        }
        return -1;
    }
}