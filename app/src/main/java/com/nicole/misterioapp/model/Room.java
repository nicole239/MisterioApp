package com.nicole.misterioapp.model;

import java.util.List;

public class Room extends AbstractCard{
    public Coordinates upLeftCorner;
    public int width;
    public int height;
    public List<Coordinates> doors;
    public List<Integer> connectedRoomIds;


    public Room(int id, String name, Coordinates upLeftCorner, int width, int height, List<Coordinates> doors, List<Integer> connectedRoomIds) {
        super(id, name, CardType.ROOM);
        this.upLeftCorner = upLeftCorner;
        this.width = width;
        this.height = height;
        this.doors = doors;
        this.connectedRoomIds = connectedRoomIds;
    }
}
