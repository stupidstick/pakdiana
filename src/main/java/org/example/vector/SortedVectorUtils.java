package org.example.vector;

import java.lang.reflect.Field;

public class SortedVectorUtils {
    public static Field findSortField(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        Field sortField = null;
        for (Field field : fields) {
            if (field.isAnnotationPresent(SortField.class))
                if (sortField != null)
                    throw new IllegalArgumentException("The class must contain only one field annotated with @SortField");
                else sortField = field;
        }
        if (sortField == null)
            throw new IllegalArgumentException("The class must contain field annotated with @SortField");
        return sortField;
    }

    public static Object getFieldValue(Object object, String field) {
        try {
            Field f = object.getClass().getDeclaredField(field);
            f.setAccessible(true);
            return f.get(object);
        } catch (NoSuchFieldException | IllegalAccessException exception) {
            throw new IllegalArgumentException("Class " + object.getClass().getName() + " not contain field " + field);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static int compareFields(Object obj1, Object obj2, String field) {
        var val1 = getFieldValue(obj1, field);
        var val2 = getFieldValue(obj2, field);
        if (val1.getClass() == val2.getClass() && val1 instanceof Comparable)
            return ((Comparable) val1).compareTo(val2);
        else throw new IllegalArgumentException("Incomparable objects");
    }
}
