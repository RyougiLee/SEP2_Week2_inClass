package com.example.calculatorapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorModelTest {

    private CalculatorModel model;

    @BeforeEach
    void setUp(){
        model = new CalculatorModel();
    }

    @Test
    @DisplayName("Test calculation of single item")
    void testCalculateItemCost(){
        assertEquals(50.0, model.calculateItemCost(2, 25.0));
        assertEquals(0.0, model.calculateItemCost(0, 100));
        assertEquals(0.0, model.calculateItemCost(-1, 100));
        assertEquals(0.0, model.calculateItemCost(1, -1));
    }

    @Test
    @DisplayName("Test calculation of all the items in the cart")
    void testCalculationTotalSum(){
        List<CalculatorModel.Item> cart = Arrays.asList(
                new CalculatorModel.Item(2, 10.0),
                new CalculatorModel.Item(1, 5.0),
                new CalculatorModel.Item(3, 100.0)
        );

        assertEquals(325.0, model.calculateTotal(cart));
    }
}
