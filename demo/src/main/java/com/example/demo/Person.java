package com.example.demo;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Person {

    private String Name;

    private String Address;

    private int exp1;

    private int exp2;

    private int age1;

    private int age2;


    public Person(String Name, String Address,int exp1, int exp2, int age1, int age2) {
        this.Name = Name;
        this.Address = Address;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.age1=age1;
        this.age2=age2;
    }
}
