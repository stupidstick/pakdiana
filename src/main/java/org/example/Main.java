package org.example;


import org.example.entities.Animal;
import org.example.entities.Dog;
import org.example.vector.SortedVector;

public class Main {
    public static void main(String[] args) {
        SortedVector vector = new SortedVector(Animal.class);
        vector.setSortField("age");
        for (int i = 0; i < 3; i++){
            vector.add(new Dog());
            vector.add(new Animal());
        }
        System.out.println("--------- <age: int> field -----------");
        System.out.println("vector: " + vector.cloneVector());
        Animal animal = (Animal) vector.get(0);
        System.out.println("first element: " + animal);
        System.out.println("search by age result: " + vector.search(animal.getAge()));
        System.out.println("--------------------------------------");
        vector.setSortField("name");

        System.out.println("------- <name: String> field ---------");
        System.out.println("vector: " + vector.cloneVector());
        animal = (Animal) vector.get(0);
        System.out.println("first element: " + animal);
        System.out.println("search by name result: " + vector.search(animal.getName()));
        System.out.println("--------------------------------------");
    }
}

