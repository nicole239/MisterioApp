package com.nicole.misterioapp.data;

import com.nicole.misterioapp.model.AbstractCard;
import com.nicole.misterioapp.model.CardType;
import com.nicole.misterioapp.model.Coordinates;
import com.nicole.misterioapp.model.Room;
import com.nicole.misterioapp.model.Suspect;
import com.nicole.misterioapp.model.Weapon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultData implements IData {

    private List<Suspect> players ;
    static DefaultData instance;

    private DefaultData(){
        players  = new ArrayList<>();
    }

    public static DefaultData getInstance(){
        if (instance == null){
            instance = new DefaultData();
        }
        return instance;
    }

    @Override
    public List<Suspect> getSuspects(){
        List<Suspect> suspects = new ArrayList<>();
        suspects.add(new Suspect(1, "Prince", new Coordinates(17,16)));
        suspects.add(new Suspect(2, "Duke", new Coordinates(1,7)));
        suspects.add(new Suspect(3, "Maid", new Coordinates(1,13)));
        suspects.add(new Suspect(4, "Queen", new Coordinates(21,10)));
        suspects.add(new Suspect(5, "Knight", new Coordinates(5,16)));
        suspects.add(new Suspect(6, "Priest", new Coordinates(11,1)));
        return suspects;
    }

    @Override
    public List<Room> getRooms(){
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(1,"Pantry", new Coordinates(1,15),3,2,
                new ArrayList<>(Arrays.asList(new Coordinates(1,15))),
                new ArrayList<>()));

        rooms.add(new Room(2,"Kitchen",new Coordinates(1,9),3,3,
                new ArrayList<>(Arrays.asList(new Coordinates(1,11), new Coordinates(3,9))),
                new ArrayList<>()));

        rooms.add(new Room(3,"Dinning Room",new Coordinates(1,1),3,5,
                new ArrayList<>(Arrays.asList(new Coordinates(3,5), new Coordinates(3,4))),
                new ArrayList<>(Arrays.asList(8))));

        rooms.add(new Room(4,"Ball Room",new Coordinates(6,1),5,3,
                new ArrayList<>(Arrays.asList(new Coordinates(7,3), new Coordinates(8,3))),
                new ArrayList<>()));

        rooms.add(new Room(5,"Chapel",new Coordinates(13,1),3,5,
                new ArrayList<>(Arrays.asList(new Coordinates(13,3), new Coordinates(14,5), new Coordinates(15,3))),
                new ArrayList<>()));

        rooms.add(new Room(6,"Library",new Coordinates(19,1),3,4,
                new ArrayList<>(Arrays.asList(new Coordinates(19,2), new Coordinates(20,4))),
                new ArrayList<>()));

        rooms.add(new Room(7,"Office",new Coordinates(18,7),4,2,
                new ArrayList<>(Arrays.asList(new Coordinates(18,8))),
                new ArrayList<>()));

        rooms.add(new Room(8,"Bedchamber",new Coordinates(19,12),3,5,
                new ArrayList<>(Arrays.asList(new Coordinates(20,12))),
                new ArrayList<>(Arrays.asList(3))));

        rooms.add(new Room(9,"Gatehouse",new Coordinates(7,15),8,2,
                new ArrayList<>(Arrays.asList(new Coordinates(8,15), new Coordinates(13,15))),
                new ArrayList<>()));

        rooms.add(new Room(10,"Gardens",new Coordinates(8,8),6,4,
                new ArrayList<>(Arrays.asList(new Coordinates(8,10), new Coordinates(10,8),
                        new Coordinates(11,11), new Coordinates(13,9))),
                new ArrayList<>()));

        return rooms;
    }

    @Override
    public List<Weapon> getWeapons(){
        List<Weapon> weapons = new ArrayList<>();
        weapons.add(new Weapon(1, "Sword"));
        weapons.add(new Weapon(2, "Poison"));
        weapons.add(new Weapon(3, "Axe"));
        weapons.add(new Weapon(4, "Rope"));
        weapons.add(new Weapon(5, "Dagger"));
        weapons.add(new Weapon(6, "Candlestick"));
        return weapons;
    }

    @Override
    public int getBoardWidth(){
        return 23;
    }

    @Override
    public int getBoardHeight(){
        return 18;
    }

    @Override
    public List<Suspect> getPlayers(){
        return this.players;
    }

    @Override
    public int getTotalDice() {return 1;}

    public void addPlayerChosenSuspect(int suspectId){
        List<Suspect> allSuspects = getSuspects();
        Suspect sus = allSuspects.get(suspectId);
        for(Suspect suspect : players){
            if(sus.equals(suspect)){
                this.players.remove(sus);
                return;
            }
        }
        this.players.add(sus);
    }

    public List<AbstractCard> getCards(CardType cardType){
        List<AbstractCard> cards = new ArrayList<>();
        switch (cardType) {
            case SUSPECT:
                cards.addAll(getSuspects());
                break;
            case ROOM:
                cards.addAll(getRooms());
                break;
            case WEAPON:
                cards.addAll(getWeapons());
                break;
        }
        return cards;
    }
}
