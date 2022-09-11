package com.nicole.misterioapp.data;

import com.nicole.misterioapp.R;
import com.nicole.misterioapp.model.AbstractCard;
import com.nicole.misterioapp.model.CardType;
import com.nicole.misterioapp.model.Deck;

public class DataHandGUI {

    private Deck deck;
    private int[] images;

    public DataHandGUI(Deck deck, CardType cardType){
        this.deck = deck;
        this.images = new int[deck.getDeck(cardType).size()];
        int[] suspectImages = new int[] {R.drawable.card_sus_1_prince_clean, R.drawable.card_sus_2_duke_clean, R.drawable.card_sus_3_maid_clean, R.drawable.card_sus_4_queen_clean, R.drawable.card_sus_5_knight_clean, R.drawable.card_sus_6_priest_clean};

        int i = 0;
        for(AbstractCard card : deck.getDeck(cardType)){
            images[i] = suspectImages[card.id - 1];
            i++;
        }
    }

    public int[] getImages() {
        return images;
    }
}
