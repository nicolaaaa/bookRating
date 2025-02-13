/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ibb.rest;

import com.ibb.service.MyService;
import com.ibb.model.Person;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author trainer
 */
@RestController
@RequestMapping(path = "/api")
@Scope(scopeName = "session",proxyMode = ScopedProxyMode.TARGET_CLASS )
public class MyRest implements Serializable {
    
    //@Autowired
    private MyService myService;
    

    public MyRest(MyService service) {
        this.myService=service;
        System.out.println("Restcontroller wurde instantiiert!");
    }

    
    
    
    
    @GetMapping(path = "/hello")
    public String hello() {

        return "Hello Rest";
    }

    @GetMapping(path = "/personx", produces = {MediaType.APPLICATION_XML_VALUE})
    public Person myPerson() {
        Person p = new Person("Hans", "Mustermann");

        return p;
    }
    @GetMapping(path = "/personj")
    public Person myPersonj() {
        Person p = new Person("Hans", "Mustermann");

        return p;
    }
    @GetMapping(path = "/listperson")
    public List<Person> listPersonj() {
        

        return myService.listPersonen();
    }
    
    @PostMapping(path="/add")
    public ResponseEntity<String> addPerson(@RequestBody Person person){
        myService.addPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body("Person hinzugefügt");
    }
    
}
