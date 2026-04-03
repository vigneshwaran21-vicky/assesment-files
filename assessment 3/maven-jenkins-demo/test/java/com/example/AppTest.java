package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    App app = new App();

    @Test
    void testAddition() {
        assertEquals(8, app.add(5,3));
    }

    @Test
    void testSubtraction() {
        assertEquals(2, app.subtract(5,3));
    }

    @Test
    void testMultiplication() {
        assertEquals(15, app.multiply(5,3));
    }

    @Test
    void testDivision() {
        assertEquals(2, app.divide(6,3));
    }
}