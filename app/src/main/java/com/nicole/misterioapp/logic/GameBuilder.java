package com.nicole.misterioapp.logic;

import com.nicole.misterioapp.data.IData;
import com.nicole.misterioapp.model.AbstractCard;
import com.nicole.misterioapp.model.Board;
import com.nicole.misterioapp.model.Deck;
import com.nicole.misterioapp.model.Notebook;
import com.nicole.misterioapp.model.Suspect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameBuilder {

    private Board board;

    private Deck fullDeck;

    private List<Suspect> players;

    private Solution solution;

    private Dice dice;

    private Deck dealerDeck;

    public void setupGame(IData data){
        build(data);
        Game.getInstance().board = this.board;
        Game.getInstance().fullDeck = this.fullDeck;
        Game.getInstance().players = this.players;
        Game.getInstance().solution = this.solution;
        Game.getInstance().dice = this.dice;
        Game.getInstance().currentPlayer = 0;
    }

    private void build(IData data){
        setFullDeck(data);
        setBoard(data);
        setSolution(data);
        setPlayers(data);
        setDice(data);
    }

    public void setFullDeck(IData data){
        //Initializes full deck (serves as a catalog)
        fullDeck = new Deck(data.getWeapons(), data.getSuspects(), data.getRooms());
        dealerDeck = new Deck(data.getWeapons(), data.getSuspects(), data.getRooms());
    }

    public void setBoard(IData data){
        //Initializes the playing board
        board = new Board(data.getBoardWidth(), data.getBoardHeight());
        board.AddRooms(data.getRooms());
        board.AddSuspects(data.getSuspects());
    }

    public void setSolution(IData data){
        //Randomly picks the solution (and extracts the respective cards from the dealer's deck)
        solution = new Solution();
        dealerDeck.roomCards = solution.setWhere(dealerDeck.roomCards);
        dealerDeck.suspectCards = solution.setWho(dealerDeck.suspectCards);
        dealerDeck.weaponCards = solution.setHow(dealerDeck.weaponCards);
    }

    public void setPlayers(IData data) {
        //Distributes the dealer's deck amongst the players
        this.players = data.getPlayers();
        List<Deck> playersDecks = DealDeck(dealerDeck, players.size());
        AddPlayersNotebooks(playersDecks);
    }

    public void setDice(IData data) {
        this.dice = new Dice(data.getTotalDice());
    }

    private void AddPlayersNotebooks(List<Deck> playerDecks)  {
        int total = this.players.size();
        for(int i=0; i < total; i++){
            Deck playerDeck = playerDecks.get(i);
            this.players.get(i).setNotebook(new Notebook(fullDeck, playerDeck));
            this.players.get(i).setDeck(playerDeck);
        }
    }

    private List<Deck> DealDeck(Deck dealerDeck, int totalPlayers){
        //Initialize empty player decks
        List<Deck> playerDecks = new ArrayList<>();
        for(int i=0; i < totalPlayers; i++) playerDecks.add(new Deck());

        int[] totalCards = new int[] {
                dealerDeck.roomCards.size(),
                dealerDeck.weaponCards.size() ,
                dealerDeck.suspectCards.size()
        };

        //Numbers all cards from each deck from 0 to totalCardsInAllThreeDecks
        List<Integer> ids = new ArrayList<>();
        for(int i=0; i < totalCards[0] + totalCards[1] + totalCards[2]; i++) ids.add(i);
        //Shuffles the assigned numbers (thus, simulating the shuffling of the three decks combined)
        Collections.shuffle(ids);

        //Deals the shuffled cards to each player, round-robin style
        int playerWhoGetsTheCard = 0;
        for(int id : ids){
            AbstractCard nextCardInShuffledDeck = getCardFromId(id, dealerDeck, totalCards);
            playerDecks.get(playerWhoGetsTheCard).addCard(nextCardInShuffledDeck);
            playerWhoGetsTheCard = (playerWhoGetsTheCard + 1) % totalPlayers;
        }
        return playerDecks;
    }

    private AbstractCard getCardFromId(int id, Deck dealerDeck, int[] totalCards){
        if(id < totalCards[0]){
            return dealerDeck.roomCards.get(id);
        }else if( id < totalCards[0] + totalCards[1]){
            return dealerDeck.weaponCards.get(id - totalCards[0]);
        }else{
            return dealerDeck.suspectCards.get(id - totalCards[0] - totalCards[1]);
        }
    }

}
