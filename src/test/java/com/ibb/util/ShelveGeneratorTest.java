/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.ibb.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Nicola
 */
public class ShelveGeneratorTest {

    public ShelveGeneratorTest() {
    }

    @Test
    void generateRandomShelf_ShouldReturnNonEmptyString() {
        // Act
        String result = ShelveGenerator.generateRandomShelf();

        // Assert
        assertNotNull(result, "Generated shelf HTML should not be null");
        assertFalse(result.isEmpty(), "Generated shelf HTML should not be empty");
    }

    @Test
    void generateRandomShelf_ShouldContainShelfFlexClass() {
        // Act
        String result = ShelveGenerator.generateRandomShelf();

        // Assert
        assertTrue(result.contains("<div class='shelf-flex'>"), "Output should contain the 'shelf-flex' class");
    }

    @Test
    void generateRandomShelf_ShouldContainAtLeastOneElement() {
        // Act
        String result = ShelveGenerator.generateRandomShelf();

        // Assert
        assertTrue(result.contains("<div"), "Output should contain at least one HTML element");
    }

    @Test
    void generateRandomShelf_ShouldHaveBalancedDivTags() {
        // Act
        String result = ShelveGenerator.generateRandomShelf();

        // Count the number of <div> and </div> tags
        long openDivs = result.split("<div").length - 1;   // Matches both <div> and self-closing ones
        long closeDivs = result.split("</div").length - 1; // Matches only closing div tags

        // Assert
        assertEquals(openDivs, closeDivs, "Number of <div> and </div> tags should be equal");
    }

}
