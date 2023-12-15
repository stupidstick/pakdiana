package org.example.entities;

import lombok.Data;

import java.util.Random;

@Data
public class Animal {
    protected final static Random random = new Random();
    private String name = String.valueOf(random.nextInt(100));
    private int age = new Random().nextInt(50);
    private double weight = random.nextInt(100) + (double) random.nextInt(100) / 100;
}

