/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ibb.ctrl;

import com.ibb.util.ShelveGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nicola
 */
@Controller
public class BookShelveController {

    @GetMapping("/shelves")
    public String getShelves(Model model) {
        List<String> shelves = new ArrayList<>();

        for (int i = 0; i < 5; i++) { // Generate 4 shelves
            shelves.add(ShelveGenerator.generateRandomShelf());
        }

        model.addAttribute("shelves", shelves);
        return "shelves"; // This will return shelves.xhtml
    }

}
