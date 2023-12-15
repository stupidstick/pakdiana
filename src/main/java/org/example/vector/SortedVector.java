package org.example.vector;

import org.apache.commons.lang3.ClassUtils;

import java.util.Vector;


public class SortedVector {
    private final Class<?> objClass;
    private String sortField = null;
    private final Vector<Object> vector = new Vector<>();

    public SortedVector(Class<?> objClass) {
        this.objClass = objClass;
    }

    public void add(Object val) {
        if (!objClass.isAssignableFrom(val.getClass()))
            throw new IllegalArgumentException("val class must be a subclass of " + objClass.getName());
        vector.add(val);
        sort();
    }

    public Vector<Object> cloneVector() {
        sort();
        return new Vector<>(vector);
    }

    //binary search
    @SuppressWarnings({"rawtypes", "unchecked"})
    public Object search(Object key) {
        if (sortField == null) throw new NullPointerException("sort field cannot be null when using the search() method");
        sort();
        Class<?> fieldType = SortedVectorUtils.getFieldType(sortField, objClass);
        if (key.getClass() != (fieldType.isPrimitive() ? ClassUtils.primitiveToWrapper(fieldType): fieldType))
            throw new IllegalArgumentException("key must be " + fieldType.getName());
        int low = 0;
        int high = vector.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            Object midElement = vector.get(mid);

            int comparison = ((Comparable) key).compareTo(SortedVectorUtils.getFieldValue(midElement, sortField, objClass));

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

    public void setSortField(String field) {
        if (field != null && !SortedVectorUtils.containField(field, objClass))
            throw new IllegalArgumentException("Class" + objClass.getName() + " not contain field " + field);
        Class<?> fieldClass = SortedVectorUtils.getFieldType(field, objClass);
        if (fieldClass != String.class && !ClassUtils.isPrimitiveOrWrapper(fieldClass))
            throw new IllegalArgumentException("Field type not primitive or string");
        this.sortField = field;
    }

    public Object get(Integer index) {
        sort();
        return vector.get(index);
    }

    //insertion sort
    public void sort() {
        if (sortField == null) return;
        for (int i = 1; i < vector.size(); ++i) {
            Object key = vector.get(i);
            int j = i - 1;
            while (j >= 0 && SortedVectorUtils.compareFields(vector.get(j), key, sortField, objClass) > 0) {
                vector.set(j + 1, vector.get(j));
                j = j - 1;
            }
            vector.set(j + 1, key);
        }
    }

    private boolean isSorted() {
        if (sortField == null) return true;
        if (vector.isEmpty() || vector.size() == 1) return true;
        for (int i = 1; i < vector.size(); i++) {
            if (SortedVectorUtils.compareFields(vector.get(i), vector.get(i - 1), sortField, objClass) < 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "SortedVector{" +
                "objClass=" + objClass +
                ", sortField='" + sortField + '\'' +
                ", vector=" + vector +
                '}';
    }
}
