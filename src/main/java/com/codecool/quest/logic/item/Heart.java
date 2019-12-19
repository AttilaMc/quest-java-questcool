package com.codecool.quest.logic.item;

import com.codecool.quest.logic.Cell;

public class Heart extends Item {

    public Heart(Cell cell){
        super(cell);
    }

    @Override
    public String getTileName() {
        return "key";
    }
}
