package org.example;

import org.example.vector.SortedVector;

public class Main {

    public static void main(String[] args) {
        var vector = new SortedVector<Entity>(Entity.class);
        Entity entity = new Entity();
        entity.data = 13;


        Entity entity1 = new Entity();
        entity1.data = 14;

        vector.sort(entity, entity1);
    }
}