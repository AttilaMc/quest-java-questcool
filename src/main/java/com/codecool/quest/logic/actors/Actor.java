package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.Drawable;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 10;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        int validMove = preventWall(nextCell);
            if (validMove == 1){
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
            }

    }
    public int preventWall(Cell cell){
        String cellType = cell.getType().toString();
        if (cellType.equals("WALL")){
            return 0;
        }
        else{
            return 1;
        }
    }

    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
}
