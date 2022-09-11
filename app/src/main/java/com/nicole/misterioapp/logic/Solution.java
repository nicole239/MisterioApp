package com.nicole.misterioapp.logic;

import com.nicole.misterioapp.model.AbstractCard;
import com.nicole.misterioapp.model.Room;
import com.nicole.misterioapp.model.Suspect;
import com.nicole.misterioapp.model.Weapon;

import java.util.List;

public class Solution {
    private AbstractCard room;
    private AbstractCard suspect;
    private AbstractCard weapon;

    public List<AbstractCard> setWhere(List<AbstractCard> rooms){
        int numberOfRooms = rooms.size();
        int roomId = getRandomNumber(0,numberOfRooms);
        this.room = rooms.remove(roomId);
        return rooms;
    }

    public List<AbstractCard> setWho(List<AbstractCard> suspects){
        int numberOfSuspects = suspects.size();
        int roomId = getRandomNumber(0,numberOfSuspects);
        this.suspect = suspects.remove(roomId);
        return suspects;
    }

    public List<AbstractCard> setHow(List<AbstractCard> weapons){
        int numberOfWeapons = weapons.size();
        int roomId = getRandomNumber(0,numberOfWeapons);
        this.weapon = weapons.remove(roomId);
        return weapons;
    }

    public AbstractCard getWhere(){
        return this.room;
    }

    public AbstractCard getWho(){
        return this.suspect;
    }

    public AbstractCard getHow(){
        return this.weapon;
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
