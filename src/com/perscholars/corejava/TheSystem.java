package com.perscholars.corejava;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public abstract class TheSystem {
    private HashMap<String, Item> itemCollection;

    public TheSystem() {
        itemCollection = new HashMap<>();
        if (getClass().getSimpleName().equals("AppSystem")) {
            try {
                File file = new File("sample.txt");
                Scanner reader = new Scanner(file);
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    String[] iteminfo = line.split("\s ");
                    Item newItem = new Item(iteminfo[0], iteminfo[1], Double.parseDouble(iteminfo[2]), Integer.parseInt(iteminfo[3]));
                    itemCollection.put(iteminfo[0], newItem);
                }
                reader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred");
                e.printStackTrace();
            }
        }

    }

    public HashMap<String, Item> getItemCollection() {
        return itemCollection;
    }

    public void setItemCollection(HashMap<String, Item> hashMap) {
        this.itemCollection = hashMap;
    }

    public boolean checkAvailability(Item item) {
        if (item.getAvailableQuantity() == null || item.getAvailableQuantity() <1) {
            System.out.println("System is unable to add " + item.getItemName() + " to the card. System only has " +
                    item.getAvailableQuantity() + " " + item.getItemName() + "s.");
            return false;
        }else if (item.getQuantity() >= item.getAvailableQuantity()) {
            return false;
        }else
            return true;
    }

    public boolean add(Item item) {
        if (item == null) {
            return false;
        } else if (itemCollection.containsKey(item.getItemName()) && item.getAvailableQuantity() > 0) {
            item.setQuantity(item.getQuantity() + 1);
            return true;
        } else if (!itemCollection.containsKey(item.getItemName())) {
            itemCollection.put(item.getItemName(), item);
            return true;
        } else
            return false;
    }

    public Item remove(String itemName) {
        if (itemCollection.containsKey(itemName)) {
            return itemCollection.remove(itemName);
        } else
            return null;
    }

    public abstract void display();
}
