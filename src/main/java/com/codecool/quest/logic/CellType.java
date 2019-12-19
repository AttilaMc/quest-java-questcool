package com.codecool.quest.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    SWORD("sword"),
    KEY("key"),
    OPENDOOR("opendoor"),
    CLOSEDDOOR("closeddoor");



    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
