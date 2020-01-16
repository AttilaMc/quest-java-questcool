package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

public class PudingMonster extends Actor{

    public PudingMonster(Cell cell) {
        super(cell);
        setType("monster");

    }


    @Override
    public String getTileName() {
        return "pudingMonster";
    }
}
