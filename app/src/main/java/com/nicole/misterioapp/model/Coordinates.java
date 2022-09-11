package com.nicole.misterioapp.model;

public class Coordinates {
    public int x;
    public int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void Move(Direction direction){
        switch(direction){
            case UP:
                Up();
                break;
            case DOWN:
                Down();
                break;
            case LEFT:
                Left();
                break;
            case RIGHT:
                Right();
                break;
        }
    }

    public Coordinates NewMoved(Direction direction){
        Coordinates newCoordinate = new Coordinates(this.x, this.y);
        newCoordinate.Move(direction);
        return newCoordinate;
    }

    public void Return(Direction direction){
        switch(direction){
            case UP:
                Down();
                break;
            case DOWN:
                Up();
                break;
            case LEFT:
                Right();
                break;
            case RIGHT:
                Left();
                break;
        }
    }

    private void Up(){
        this.y -= 1;
    }

    private void Left(){
        this.x -= 1;
    }

    private void Down(){
        this.y += 1;
    }

    private void Right(){
        this.x += 1;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinates coordinates = (Coordinates) o;
        return this.x == coordinates.x && this.y == coordinates.y;
    }
}
