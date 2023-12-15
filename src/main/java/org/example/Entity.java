package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entities.Dog;

import java.util.Random;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Entity {
    private String data = String.valueOf(new Random().nextInt(100));
    private int val = new Random().nextInt(50);
    private Dog dog = new Dog();
}

