package com.codecool.quest.logic.item;

import com.codecool.quest.logic.Cell;

public class Health extends Item {

    public Health(Cell cell){
        super(cell);
    }

    @Override
    public String getTileName() {
        return "key";
    }
}
