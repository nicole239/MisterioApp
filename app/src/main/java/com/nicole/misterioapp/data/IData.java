package com.nicole.misterioapp.data;

import com.nicole.misterioapp.model.AbstractCard;
import com.nicole.misterioapp.model.CardType;
import com.nicole.misterioapp.model.Room;
import com.nicole.misterioapp.model.Suspect;
import com.nicole.misterioapp.model.Weapon;

import java.util.List;

public interface IData {
    List<Suspect> getSuspects();
    List<Room> getRooms();
    List<Weapon> getWeapons();
    int getBoardWidth();
    int getBoardHeight();
    int getTotalDice();
    List<Suspect> getPlayers();
    void addPlayerChosenSuspect(int id);
    List<AbstractCard> getCards(CardType cardType);
}
