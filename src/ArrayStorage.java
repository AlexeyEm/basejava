/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = -1; //Размер массива без null

    void clear() {
        for (int i = 0; i < size + 1; i++) {
            storage[i] = null;
        }
        size = -1;
    }

    Resume get(String uuid) {
        Resume resume = null;
        for (int i = 0; i < size + 1; i++) {
            if (storage[i].uuid.equalsIgnoreCase(uuid))
                resume = storage[i];
        }
        return resume;
    }

    void save(Resume r) {
        Resume resume = this.get(r.uuid);
        //Если резюме нет в массиве, то создаем
        if (resume == null) {
            size++;
            storage[size] = r;
        }
    }

    void delete(String uuid) {
        boolean isNeedShift = false; //Признак: нужен сдвиг

        for (int i = 0; i < size + 1; i++) {
            //Если удаляется непоследний элемент массива
            if (storage[i].uuid.equalsIgnoreCase(uuid) && i != size) {
                isNeedShift = true;
                size--;
            }
            //Если удаляется последний элемент массива
            if (storage[i].uuid.equalsIgnoreCase(uuid) && i == size) {
                storage[i] = null;
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
        Resume[] resultStorage = new Resume[size + 1];
        if (size >= 0) {
            System.arraycopy(storage, 0, resultStorage, 0, size + 1);
        }
        return resultStorage;
    }

    int size() {
        return this.size + 1;
    }
}