package com.dass.LearnMorseCode;

import com.google.common.collect.MoreCollectors;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MorseCodeTest {

    @Test
    void morseToEnglish() {
        MorseCode test = new MorseCode();
        String expected = "come";
        String actual = test.morseToEnglish("-.-./---/--/.");
        assertEquals(expected, actual);
    }
}