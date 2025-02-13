/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ibb.service;

import com.ibb.model.Person;
import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author trainer
 */
@Service
public class MyService {

    private List<Person> lp;

    public MyService() {
        lp = new ArrayList<>();
        System.out.println("Service wurde instantiiert!");
    }

    public List<Person> listPersonen() {
        if (lp.isEmpty()) {
            Faker f = new Faker();
            for (int i = 0; i < 10; i++) {

                lp.add(new Person(f.address().firstName(), f.address().lastName(), f.address().cityName()));

            }
        }
        return lp;
    }

    public void addPerson(Person p){
        lp.add(p);
    }
    
    
}
