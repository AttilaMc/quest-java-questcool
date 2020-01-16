package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

public class ManBearPig extends Actor {

    public ManBearPig(Cell cell) {
        super(cell);
        setType("monster");

    }


    @Override
    public String getTileName() {
        return "manBearPig";
    }
}
