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
        }
        catch (NoSuchFieldException | IllegalAccessException exception) {
            throw new IllegalArgumentException("Class " + object.getClass().getName() + " not contain field " + field);
        }
    }

    public static int compareFields(Object obj1, Object obj2, String field) {
        var val1 = getFieldValue(obj1, field);
        var val2 = getFieldValue(obj2, field);
        if (val1 instanceof Number && val2 instanceof Number) {
            return (((Number) val1).doubleValue() > ((Number) val2).doubleValue()) ? 1 : (((Number) val1).doubleValue() == ((Number) val2).doubleValue()) ? 0 : 1;
        }
        else throw new IllegalArgumentException();
    }
}
