import java.lang.reflect.Array;
import java.util.Arrays;

public class DynamicArray<T> {
    private T[] array;
    private int size;
    private Class<T> type;

    public DynamicArray(Class<T> type) {
        this.type = type;
        size = 0;
    }

    public int size() {
        if (array == null) {
            return 0;
        }
        size = array.length;
        return size;

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T get(int index){
        if (index > size-1) {
            return null;
        }
        return array[index];

    }

    public boolean contains (T element) {
        for (int i = 0; i < size; i++) {
            if( element == array[i]) {
                return true;
            }
        }
        return false;
    }

    public void add (T element) {
        size+=1;
        T[] resizedArray;
        if (array == null) {
            resizedArray = (T[]) Array.newInstance(type, 1);
            resizedArray[0] = element;

        }
        else {
            resizedArray = Arrays.copyOf (array, size);
            resizedArray[size-1] = element;
        }
        array = resizedArray;


    }

    public void add (int index, T element) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Array Index out of Bounds!");
        }
        size+=1;
        T[] resizedArray;
        if (array == null) {
            resizedArray = (T[]) Array.newInstance(type, 1);
            resizedArray[0] = element;

        }
        else {
            resizedArray = Arrays.copyOf (array, size);
            boolean putIn = false;
            for (int i = 0; i < size; i++) {
                if (i == index) {
                    resizedArray[i] = element;
                    putIn = true;
                } else if (putIn) {
                    resizedArray[i] = array[i - 1];
                } else {
                    resizedArray[i] = array[i];
                }
            }
        }

        array = resizedArray;

    }

    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Array Index out of Bounds!");
        }
        array[index] = element;

    }

    public T remove (int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Array Index out of Bounds!");
        }
        size-=1;
        T element = array[index];
        array[index] = null;
        boolean removed = false;
        if (size <= 0) {
            size = 0;
            array = null;
        }
        else {
            T[] arrayTemp = (T[]) Array.newInstance(type, size);
            for (int i = 0; i < size; i++) {
                if (array[i] == null) {
                    i++;
                    removed = true;
                    arrayTemp[i-1] = array[i];
                } else if (removed) {
                    arrayTemp[i-1] = array[i];
                } else {
                    arrayTemp[i] = array[i];
                }
            }

            array = arrayTemp;

        }
        return element;
    }

    public T remove (T element) {
        size -= 1;
        boolean removed = false;
        if (size <= 0) {
            size = 0;
            array = null;
        }
        else {

            T[] arrayTemp = (T[]) Array.newInstance(type, size);
            for (int i = 0; i < size+1; i++) {
                if (array[i] == element && !removed) {
                    i++;
                    removed=true;
                    arrayTemp[i-1] = array[i];
                }
                else if(removed){
                    arrayTemp[i-1] = array[i];
                }
                else{
                    arrayTemp[i] = array[i];
                }
            }
            array = arrayTemp;
        }
        return element;

    }

    public T removeAll (T element) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (array[i] == element) {
                count++;
            }
        }
        size-=count;
        int removed = 0;
        if (size <= 0) {
            size = 0;
            array = null;
        }
        else {
            T[] arrayTemp = (T[]) Array.newInstance(type, size);
            for (int i = 0; i < size+count; i++) {
                if (array[i] == element) {
                    i++;
                    removed++;
                    arrayTemp[i-removed] = array[i];
                }
                else{
                    arrayTemp[i-removed] = array[i];
                }

            }

            array = arrayTemp;

        }

        return element;

    }

    public void clear(){
        size = 0;
        array = null;


    }

}