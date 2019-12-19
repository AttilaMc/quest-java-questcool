package com.codecool.quest.logic.item;

import com.codecool.quest.logic.Cell;


public class Sword extends Item {
    private int damage = 2;

    public Sword(Cell cell) {
        super(cell);
    }

    public int getDamageBonus() {
        return this.damage;
    }


    private String itemName = giveName("Sword");

    public String getName() {
        return itemName;
    }

    @Override
    public String getTileName() {
        return "sword";
    }

}
