package org.selenium.utils;

import com.github.javafaker.Faker;

// Generates all kind of fake data
public class FakerUtils {

    public Long generateRandomNumber(){
        Faker faker = new Faker();
        return faker.number().randomNumber();
    }
}
