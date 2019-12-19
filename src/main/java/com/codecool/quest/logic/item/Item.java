package com.codecool.quest.logic.item;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;

public abstract class Item implements Drawable {
    private String name;
    private Cell cell;
    public Item(Cell cell) {
        this.cell = cell;
    }

    public String giveName(String itemName) {
        name = itemName;
        return name;
    }

}
