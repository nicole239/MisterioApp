package com.nicole.misterioapp.logic;

import com.nicole.misterioapp.model.AbstractCard;
import com.nicole.misterioapp.model.Board;
import com.nicole.misterioapp.model.CardType;
import com.nicole.misterioapp.model.Deck;
import com.nicole.misterioapp.model.Discovery;
import com.nicole.misterioapp.model.Notebook;
import com.nicole.misterioapp.model.Suspect;

import java.util.List;

public class Game {

    protected Board board;

    protected Deck fullDeck;

    protected List<Suspect> players;

    protected Solution solution;

    protected Dice dice;

    protected int currentPlayer;

    protected boolean isBuilt = false;

    private static Game instance;

    private Game(){}

    public static Game getInstance(){
        if (instance == null){
            instance = new Game();
        }
        return instance;
    }

    public void Annotate(CardType type, int idCard, Discovery discovery){
        players.get(currentPlayer)
                .getNotebook()
                .Annotate(type, idCard, discovery);
    }

    public boolean isBuilt(){
        return isBuilt;
    }

    public Deck getCurrentPlayerDeck(){
        return players.get(currentPlayer).getDeck();
    }

    public Notebook getCurrentPlayerNotebook(){
        return players.get(currentPlayer).getNotebook();
    }
}
