package com.dass.LearnMorseCode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LevelTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void convertToMorseCode() {
        String expected = "...././.-../.-../---";
        Level test  = new Level("Hello");
        String actual = test.convertToMorseCode("Hello");
        assertEquals(expected, actual);
    }

}