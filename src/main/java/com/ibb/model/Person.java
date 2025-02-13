package com.ibb.model;

import lombok.Data;

@Data
public class Person {

    private String firstname;

    private String lastname;
    
    private String city;

    public Person() {
    }

    
    
    public Person(String firstname,String lastname){
        this.firstname=firstname;
        this.lastname=lastname;

    }

    public Person(String firstname, String lastname, String city) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
    }

}
