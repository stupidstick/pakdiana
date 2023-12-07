package org.example;

import org.apache.commons.lang3.ClassUtils;

public class Main {

    public static void main(String[] args) {
       var vector =  new SortedVector<Entity>(Entity.class);
       Entity entity = new Entity();
       entity.data = 12;
        vector.add(entity);
    }
}