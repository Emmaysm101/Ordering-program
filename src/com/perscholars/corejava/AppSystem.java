package com.perscholars.corejava;

public class AppSystem extends TheSystem {

    AppSystem() {
        super();
    }
    //checks if the AppSystem is invoking the constructor, if so, it adds the items from the sample.txt file to the itemCollection:
    // expected:<[items added to ItemCollection is > 0]> but was:<[No items added to ItemCollection]>
    public void display() {
        System.out.println("AppSystem Inventory:");
        System.out.printf("%-20s %-20s%-10s%-10s%n", "Name", "Description", "Price", "Available Quantity");
        for (Item item : getItemCollection().values()) {
            System.out.printf("%-20s%-20s%-10.2f%-10d%n", item.getItemName(),item.getItemDesc(),item.getItemPrice(),item.getAvailableQuantity());
        }
//        getItemCollection().values().forEach(item -> {
//            System.out.printf("%-20s %-20s %-10.2f %-10d %n", item.getItemName(),item.getItemDesc(),item.getItemPrice(),item.getAvailableQuantity());
//        });
    }

    @Override
    public boolean add(Item item) {
        if (item == null) {
            return false;
        } else if (getItemCollection().containsKey(item.getItemName())) {
            System.out.println(item.getItemName() + " is already in the App System");
            return false;
        } else {
            getItemCollection().put(item.getItemName(), item);
            return true;
        }
    }


    public Item reduceAvailableQuantity(String item_name) {
        if (getItemCollection().containsKey(item_name)) {
            getItemCollection().get(item_name).setAvailableQuantity(getItemCollection().get(item_name).getAvailableQuantity() - 1);
            if (getItemCollection().get(item_name).getAvailableQuantity() == 0) {
                getItemCollection().remove(item_name);
                return null;
            }
            return getItemCollection().get(item_name);
        } else
            return null;
    }
}
