package com.perscholars.corejava;

public class CartSystem extends TheSystem {
    public CartSystem() {
        super();
    }

    public void display() {
        double preTaxTotal = 0;
        for (Item s : getItemCollection().values()) {
            preTaxTotal += s.getItemPrice() * s.getQuantity();
        }
        double tax = preTaxTotal * 0.05;
        double total = preTaxTotal + tax;

        //expected:<[Cart: Name Description Price Quantity Sub Total Pre-tax Total 0.00 Tax 0.00 Total 0.00 ] > but was:
        // <[AppSystem Inventory Name Description Price Quantity Sub Total Pre-tax Total 0.00 Tax 0.00 Total 0.00] >
        System.out.println("Cart:");
        System.out.printf("%-20s%-20s%-10s%-10s%-10s%n", "Name", "Description", "Price", "Quantity", "Sub Total");
        getItemCollection().values().forEach(item -> {
            double subTotal = item.getItemPrice() * item.getQuantity();
            System.out.printf("%-20s%-20s%-10.2f%-10d%-10.2f%n", item.getItemName(), item.getItemDesc(), item.getItemPrice(), item.getQuantity(), subTotal);
        });
        System.out.printf("%-20s%-10.2f%n", "Pre-tax Total", preTaxTotal);
        System.out.printf("%-20s%-10.2f%n", "Tax", tax);
        System.out.printf("%-20s%-10.2f%n", "Total", total);


    }
}
