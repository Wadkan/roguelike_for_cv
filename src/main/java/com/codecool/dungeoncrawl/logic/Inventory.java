package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Item;
import com.codecool.dungeoncrawl.logic.actors.Sword;

import java.util.List;

public class Inventory {
    private List<Item> itemList;

    public Inventory() {
        itemList = null;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void addItemToInventory(Item item) {
        this.itemList.add(item);
    }

    public int getItemsNumber() {
//        return this.itemList.size();
        return 123;
    }
}
