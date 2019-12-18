package com.codecool.quest.logic.items;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;
import com.codecool.quest.logic.actors.Actor;


public class Sword extends Items {
    private int damage = 2;

    public Sword(Cell cell){
        super(cell);
    }



    public int getDamageBonus(){
        return this.damage;
    }



    @Override
    public String getTileName() {
        return "sword";
    }

}
