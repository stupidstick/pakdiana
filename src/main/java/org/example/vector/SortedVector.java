package org.example.vector;

import java.lang.reflect.Field;
import java.util.Vector;


public class SortedVector<T> {
    private final Class<T> tClass;
    private final String sortField;
    private final Class<?> fieldType;
    private final Vector<T> vector = new Vector<>();

    public SortedVector(Class<T> tClass) {
        this.tClass = tClass;
        Field field = SortedVectorUtils.findSortField(this.tClass);
        sortField = field.getName();
        fieldType = field.getType();
    }

    public void add(T val) {
        vector.add(val);
        sort();

    }

    public Vector<T> cloneVector() {
        sort();
        return new Vector<>(vector);
    }

    //binary search
    @SuppressWarnings({"rawtypes", "unchecked"})
    public T search(Object key) {
        if (key.getClass() != fieldType)
            throw new IllegalArgumentException("key must be " + fieldType.getName());
        int low = 0;
        int high = vector.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            T midElement = vector.get(mid);

            int comparison = ((Comparable) key).compareTo(SortedVectorUtils.getFieldValue(midElement, sortField));

            if (comparison == 0) {
                return midElement;
            } else if (comparison < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return null;
    }

    public T get(Integer index) {
        sort();
        return vector.get(index);
    }

    //insertion sort
    public void sort() {
        for (int i = 1; i < vector.size(); ++i) {
            T key = vector.get(i);
            int j = i - 1;
            while (j >= 0 && SortedVectorUtils.compareFields(vector.get(j), key, sortField) > 0) {
                vector.set(j + 1, vector.get(j));
                j = j - 1;
            }
            vector.set(j + 1, key);
        }
    }

    private boolean isSorted() {
        if (vector.isEmpty() || vector.size() == 1) return true;
        for (int i = 1; i < vector.size(); i++) {
            if (SortedVectorUtils.compareFields(vector.get(i), vector.get(i - 1), sortField) < 0) {
                return false;
            }
        }
        return true;
    }
}
