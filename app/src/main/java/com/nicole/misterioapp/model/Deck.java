package com.nicole.misterioapp.model;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    public List<AbstractCard> weaponCards;
    public List<AbstractCard> suspectCards;
    public List<AbstractCard> roomCards;

    public Deck(List<Weapon> weapons, List<Suspect> suspects, List<Room> rooms) {
        this.weaponCards = new ArrayList<>();
        weaponCards.addAll(weapons);
        this.suspectCards = new ArrayList<>();
        suspectCards.addAll(suspects);
        this.roomCards = new ArrayList<>();
        roomCards.addAll(rooms);
    }

    public Deck(){
        this.weaponCards = new ArrayList<>();
        this.suspectCards = new ArrayList<>();
        this.roomCards = new ArrayList<>();
    }

    public List<AbstractCard> getDeck(CardType type){
        switch (type){
            case SUSPECT:
                return suspectCards;
            case ROOM:
                return roomCards;
            case WEAPON: default:
                return weaponCards;
        }
    }

    public void addCard(AbstractCard card){
        switch(card.type){
            case SUSPECT:
                this.suspectCards.add(card);
                break;
            case ROOM:
                this.roomCards.add(card);
                break;
            case WEAPON:
                this.weaponCards.add(card);
                break;
        }
    }

    public int FindCards(AbstractCard... cards){
        for(AbstractCard card : cards){
            int foundCard = FindCardInDeck(card, getDeck(card.type));
            if(foundCard != -1) {
                return foundCard;
            }
        }
        return -1;
    }

    private int FindCardInDeck(AbstractCard card, List<AbstractCard> deck){
        for(AbstractCard deckCard : deck){
            if (deckCard.equals(card)){
                return card.id;
            }
        }
        return -1;
    }
}
