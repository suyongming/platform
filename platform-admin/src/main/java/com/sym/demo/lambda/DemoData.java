package com.sym.demo.lambda;

import com.sym.demo.entity.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * @Author: sym
 * @Date: 2024/8/12 15:25
 */
public class DemoData {
    public static List<Person> javaProgrammers = new ArrayList<Person>() {
        {
            add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
            add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
            add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));
            add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 22, 2000));
            add(new Person("Vere", "Hervey", "Java programmer", "male", 22, 1200));
            add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
            add(new Person("Shawn", "Randall", "Java programmer", "male", 30, 2300));
            add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
            add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));
            add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1300));
        }
    };

    public static List<Person> phpProgrammers = new ArrayList<Person>() {
        {
            add(new Person("Elsdon", "Jaycob", "PHP programmer", "male", 34, 1550));
            add(new Person("Ethan", "Cicely", "PHP programmer", "female", 23, 1200));
            add(new Person("Elsdon", "Channing", "PHP programmer", "male", 32, 1600));
            add(new Person("Tori", "Sheryl", "PHP programmer", "female", 21, 1000));
            add(new Person("Floyd", "Shad", "PHP programmer", "male", 32, 1100));
            add(new Person("Rosalind", "Layla", "PHP programmer", "female", 25, 1300));
            add(new Person("Fraser", "Hewie", "PHP programmer", "male", 36, 1100));
            add(new Person("Quinn", "Tamara", "PHP programmer", "female", 21, 1000));
            add(new Person("Elsdon", "Jaycob", "PHP programmer", "male", 38, 1600));
            add(new Person("Evonne", "Shari", "PHP programmer", "female", 40, 1800));
        }
    };
}
