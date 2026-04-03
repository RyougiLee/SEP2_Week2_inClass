package com.example.calculatorapp;

import java.util.List;

public class CalculatorModel {

    private int totalItems;
    private double totalCost;
    private String language;

    public CalculatorModel(String language){
        this.language = language;
    }

    public int getTotalItems(){
        return totalItems;
    }

    public double getTotalCost(){
        return totalCost;
    }

    public String getLanguage(){
        return language;
    }

    public void setLanguage(String language){
        this.language = language;
    }

    public double calculateItemCost(double quantity, double unitPrice) {
        if (quantity < 0 || unitPrice < 0) {
            return 0;
        }
        return quantity * unitPrice;
    }

    public double calculateTotal(List<Item> items) {
        double total = 0;
        int itemNumber = 0;
        for (Item item : items) {
            total += calculateItemCost(item.getQuantity(), item.getUnitPrice());
            itemNumber += (int) item.getQuantity();
        }
        totalItems = itemNumber;
        totalCost = total;
        return total;
    }

    public static class Item {
        private final double quantity;
        private final double unitPrice;

        public Item(double quantity, double unitPrice) {
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }

        public double getQuantity() { return quantity; }
        public double getUnitPrice() { return unitPrice; }
    }
}
