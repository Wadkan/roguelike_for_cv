package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Item;
import com.codecool.dungeoncrawl.logic.actors.Sword;

import java.util.LinkedList;
import java.util.List;

public class Inventory {
    private List<Item> itemList;

    public Inventory() {
        itemList = new LinkedList<>();
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void addItemToInventory(Item item) {
        if (item != null) {
            this.itemList.add(item);
        }
    }

    public Integer getItemsNumber() {
        try {
            return this.itemList.size();
        } catch (Exception ignored) {
        }
        return null;
    }
}
