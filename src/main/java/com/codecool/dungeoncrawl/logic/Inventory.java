package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Item;
import com.codecool.dungeoncrawl.logic.actors.Key;
import com.codecool.dungeoncrawl.logic.actors.Sword;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Inventory {
    private List<Item> itemList;
    int keysNumber;

    public Inventory() {
        this.itemList = new LinkedList<>();
        this.keysNumber = 0;
    }

    public List<Item> getItems() {
        return itemList;
    }

    public void addItemToInventory(Item item) {
        if (item != null) {
            this.itemList.add(item);
        }
        if (item instanceof Key){
            this.keysNumber += 1;
        }
    }

    public Integer getItemsNumber() {
        try {
            return this.itemList.size();
        } catch (Exception ignored) {
        }
        return null;
    }

    public String getItemsList() {
        StringBuilder list = new StringBuilder();
        for (Item item : this.itemList) {
            list.append(item.getTileName());
            list.append("\n");
        }
        return list.toString();
    }

    public int getKeysNumber() {
        return this.keysNumber;
    }
}
