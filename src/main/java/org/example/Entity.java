package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.vector.SortField;

import java.util.Random;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Entity {
    @SortField
    private String data = String.valueOf(new Random().nextInt(100));
    private double dVal = new Random().nextDouble(50);
}
