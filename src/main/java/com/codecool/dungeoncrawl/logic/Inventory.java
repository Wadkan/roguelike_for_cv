package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Item;

import java.util.List;

public class Inventory {
    private List<Item> itemList;

    public Inventory(Item item) {
        itemList = null;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(Item item) {
        this.itemList.add(item);
    }

    public int getItemsNumber() {
        return this.itemList.size();
    }
}
