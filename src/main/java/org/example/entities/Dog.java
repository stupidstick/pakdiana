package org.example.entities;

import lombok.Data;
import lombok.ToString;


@Data
@ToString(callSuper = true)
public class Dog extends Animal {
    private Double tail_len = random.nextInt(100) + (double) random.nextInt(10) / 10;
}

