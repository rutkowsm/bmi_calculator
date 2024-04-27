package com.example.bmi_calculator;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorUnitTest {

    @Test
    public void calculateBMI_typicalValues() {
        assertEquals(29.530996, MainActivity.calculateBMI(110, 193), 0.0001);
    }

    @Test
    public void calculateBMI_lowValues() {
        assertEquals(15.0355, MainActivity.calculateBMI(45, 173), 0.0001);
    }

    @Test
    public void calculateBMI_highValues() {
        assertEquals(62.43496, MainActivity.calculateBMI(150, 155), 0.0001);
    }

}
