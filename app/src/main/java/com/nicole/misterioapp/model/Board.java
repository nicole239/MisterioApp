package com.nicole.misterioapp.model;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public GridBox[][] grid;
    public List<Room> rooms;

    public Board(int width, int height){
        grid = new GridBox[width][height];
        for (int x=0;x<width; x++){
            for(int y=0; y<height; y++){
                grid[x][y] = GridBox.EMPTY;
            }
        }
        rooms = new ArrayList<>();
        DrawWalls(new Coordinates(0,0), width, height);
    }

    private void DrawWalls(Coordinates upLeftCorner, int width, int height){
        for (int x = upLeftCorner.x; x < width; x++){
            for(int y = upLeftCorner.y; y < height; y++){
                if(x == upLeftCorner.x || x == (upLeftCorner.x + width - 1)
                        || y == upLeftCorner.y || y == (upLeftCorner.y + height - 1) ) {
                    grid[x][y] = GridBox.WALL;
                }
            }
        }
    }

    public void AddRooms(List<Room> rooms){
        for(Room room : rooms){
            DrawWalls(room.upLeftCorner, room.width, room.height);
            for (Coordinates door : room.doors){
                grid[door.x][door.y] = GridBox.DOOR;
            }
            this.rooms.add(room);
        }
    }

    public void AddSuspects(List<Suspect> suspects){
        for (Suspect suspect : suspects){
            grid[suspect.position.x][suspect.position.y] = GridBox.SUSPECT;
        }
    }

    public boolean isFree(Coordinates position){
        return grid[position.x][position.y ] == GridBox.EMPTY || isDoor(position);
    }

    public boolean isDoor(Coordinates position){
        return grid[position.x][position.y ] == GridBox.DOOR;
    }

    private int getTheDoorsRoomId(Coordinates door){
        for(int roomId = 0; roomId < rooms.size(); roomId++){
            for(Coordinates roomDoor : rooms.get(roomId).doors){
                if(roomDoor.equals(door)) return roomId;
            }
        }
        return -1;
    }

    public Coordinates MovePlayer(Coordinates position, Direction direction){
        grid[position.x][position.y] = GridBox.EMPTY;
        position.Move(direction);
        if(isFree(position)) {
            grid[position.x][position.y] = GridBox.SUSPECT;
        }else{
            position.Return(direction);
        }
        return position;
    }

    public List<Coordinates> AvailableMoves(Coordinates position){
        List<Coordinates> availableCoordinates = new ArrayList<>();

        if(isDoor(position)){
            //Player is inside a room. So he can exit through any door of its current room
            int roomId = getTheDoorsRoomId(position);
            availableCoordinates.addAll(this.rooms.get(roomId).doors);
            //or through any door of another room connected to his current location by a secret passage
            for(int connectedRoomId : this.rooms.get(roomId).connectedRoomIds){
                availableCoordinates.addAll(this.rooms.get(connectedRoomId).doors);
            }
        }else{
            //Player is outside a room. He can move in any direction
            //as long as said direction isn't blocked by a wall or another player.
            for (Direction direction : Direction.values()){
                Coordinates movedPosition = position.NewMoved(direction);
                if(isFree(movedPosition)){
                    availableCoordinates.add(movedPosition);
                }
            }
        }
        return availableCoordinates;
    }
}
