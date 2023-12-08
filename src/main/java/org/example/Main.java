package org.example;

import org.example.vector.SortedVector;


public class Main {

    public static void main(String[] args) {
        SortedVector<Entity> vector = new SortedVector<>(Entity.class);
        for (int i = 0; i < 5; i++) {
            vector.add(new Entity());
        }
        System.out.println(vector.cloneVector());
        Entity e = vector.get(3);
        System.out.println(e);
        e.setData("bebra");
        System.out.println(vector.search("bebra"));
        System.out.println(vector.cloneVector());
    }
}