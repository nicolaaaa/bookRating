/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ibb.demo;

import com.ibb.model.MyList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author trainer
 */
@Configuration
public class MyConfiguration {
    
    @Bean
    @Scope(scopeName = "application") 
    public MyList myList(){
        
        return new MyList();
    }
    
}
