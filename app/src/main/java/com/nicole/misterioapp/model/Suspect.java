package com.nicole.misterioapp.model;

public class Suspect extends AbstractCard {

    public Coordinates position;

    public Deck deck;

    public Notebook notebook;

    public Suspect(int id, String name, Coordinates position) {
        super(id, name, CardType.SUSPECT);
        this.position = position;
    }

    public void setDeck(Deck deck){
        this.deck = deck;
    }

    public void setNotebook(Notebook notebook){
        this.notebook = notebook;
    }

    public Notebook getNotebook(){
        return this.notebook;
    }
}
