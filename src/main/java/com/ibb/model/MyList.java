/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ibb.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 *
 * @author trainer
 */
@Data
public class MyList {

    private List<String> list = new ArrayList<String>();

    public MyList() {
        list.add("value 1");
        list.add("value 2");
        list.add("value 3");
        list.add("value 4");

    }

}
