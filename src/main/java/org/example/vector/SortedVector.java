package org.example.vector;

import java.util.Vector;

public class SortedVector<T> {
    private final Class<T> tClass;
    private final String sortField;
    private final Vector<T> vector = new Vector<>();

    public SortedVector(Class<T> tClass) {
        this.tClass = tClass;
        sortField = SortedVectorUtils.findSortField(tClass).getName();
    }

    public void add(T val) {
        int i = 0;
        System.out.println(SortedVectorUtils.getFieldValue(val, sortField));
        for (; i < vector.size(); i++) {

        }
    }

    public void sort(T obj1, T obj2) {
        System.out.println(SortedVectorUtils.compareFields(obj1, obj2, sortField));
    }
}
