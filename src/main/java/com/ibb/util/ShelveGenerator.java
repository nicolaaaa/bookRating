/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ibb.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShelveGenerator {

    public static String generateRandomShelf() {
        List<String> shelfElements = new ArrayList<>(List.of(
                "<div class='flex book-row'><div class='book'></div><div class='book'></div><div class='book'></div><div class='book'></div><div class='book'></div><div class='book'></div><div class='book'></div><div class='book'></div><div class='book'></div><div class='book-stopper'></div></div>",
                "<div class='plant-1'><div class='plant-pot'><div class='leaf'></div><div class='leaf'></div><div class='leaf'></div><div class='leaf'></div><div class='leaf'></div></div></div>",
                "<div class='flex book-row'><div class='book-2'></div><div class='book-2'></div><div class='book-2'></div></div>",
                "<div class='plant-pot plant-pot-2 hanging-plant-pot'><div class='hanging-plant'><div class='hanging-plant-pole'></div><div><div class='vine'></div><div class='vine'></div><div class='vine'></div></div><div><div class='hanging-leaf'></div><div class='hanging-leaf'></div><div class='hanging-leaf'></div><div class='hanging-leaf'></div><div class='hanging-leaf'></div></div><div><div class='berry'></div><div class='berry'></div><div class='berry'></div><div class='berry'></div><div class='berry'></div><div class='berry'></div></div></div></div>",
                "<div class='flex book-row'><div class='book-stopper-2'></div><div class='book-3'></div><div class='book-3'></div><div class='book-3'></div><div class='book-3'></div><div class='book-3'></div><div class='book-3'></div><div class='book-3'></div><div class='book-3'></div><div class='book-3'></div><div class='book-3'></div><div class='book-3'></div><div class='book-3'></div><div class='book-3'></div></div>",
                "<div class='book-stack'><div class='book'></div><div class='book'></div><div class='book'></div></div>",
                "<div class='plant-pot plant-pot-2'><div class='cactus'><div class='spike'></div><div class='spike'></div><div class='spike'></div><div class='spike'></div><div class='spike'></div><div class='spike'></div><div class='spike'></div></div></div>",
                "<div class='potions'><div class='potion-parent'><div class='potion'></div><div class='potion-cap'></div></div><div class='potion-parent'><div class='potion'></div><div class='potion-cap'></div></div><div class='potion-parent'><div class='potion'></div><div class='potion-cap'></div></div><div class='potion-parent'><div class='potion'></div><div class='potion-cap'></div></div><div class='potion-stand'></div></div>",
                "<div class='potion-parent'><div class='potion-large potion-large-1'><div class='bubble'></div><div class='bubble'></div><div class='bubble'></div></div></div><div class='potion-parent'><div class='potion-large potion-large-2'><div class='bubble bubble-2'></div><div class='bubble bubble-2'></div><div class='bubble bubble-2'></div></div></div><div class='potion-parent'><div class='potion-large potion-large-3'></div></div>",
                "<div class='book-stack'><div class='book'></div><div class='book'></div><div class='book'></div></div>",
                "<div class='flex book-row'><div class='book-2'></div><div class='book-2'></div><div class='book-2'></div></div>"
        ));

        Collections.shuffle(shelfElements);

        StringBuilder shelfHtml = new StringBuilder();
        shelfHtml.append("<div class='shelf-flex'>");
        for (String element : shelfElements) {
            shelfHtml.append(element);
        }
        shelfHtml.append("</div>");

        return shelfHtml.toString();
    }
}
