package org.example.vector;


import java.lang.reflect.Field;
import java.util.Arrays;

public class SortedVectorUtils {
    public static boolean containField(String field, Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields()).anyMatch(f -> f.getName().equals(field));
    }

    public static Class<?> getFieldType(String field, Class<?> clazz) {
        try{
            return clazz.getDeclaredField(field).getType();
        }
        catch (NoSuchFieldException exception) {
            throw new IllegalArgumentException("Class" + clazz.getName() + " not contain field " + field);
        }
    }


    public static Object getFieldValue(Object object, String field, Class<?> clazz) {
        try {
            Field f = clazz.getDeclaredField(field);
            f.setAccessible(true);
            return f.get(object);
        } catch (NoSuchFieldException | IllegalAccessException exception) {
            throw new IllegalArgumentException("Class " + object.getClass().getName() + " not contain field " + field);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static int compareFields(Object obj1, Object obj2, String field, Class<?> clazz) {
        var val1 = getFieldValue(obj1, field, clazz);
        var val2 = getFieldValue(obj2, field, clazz);
        if (val1.getClass() == val2.getClass() && val1 instanceof Comparable)
            return ((Comparable) val1).compareTo(val2);
        else throw new IllegalArgumentException("Incomparable objects");
    }
}
