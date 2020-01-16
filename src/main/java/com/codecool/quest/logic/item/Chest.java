package com.codecool.quest.logic.item;

import com.codecool.quest.logic.Cell;

public class Chest extends Item {

    public Chest(Cell cell){
        super(cell);
    }

    @Override
    public String getTileName() {
        return "chest";

    }
}
