package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

public class Police extends Actor {

    public Police(Cell cell){
        super(cell);
    }

    @Override
    public String getTileName() {
        return "police";
    }

    public void moveRandomly(){

    }
}
