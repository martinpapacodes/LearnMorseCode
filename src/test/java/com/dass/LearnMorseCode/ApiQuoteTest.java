package com.dass.LearnMorseCode;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiQuoteTest {

    @Test
    void testToString() {
        String expected = "Hello!";
        ApiQuote testApiQuote = new ApiQuote(expected);
        assertEquals("QuoteText : Hello!", testApiQuote.toString());
    }
}