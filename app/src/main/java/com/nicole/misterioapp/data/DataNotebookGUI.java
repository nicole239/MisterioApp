package com.nicole.misterioapp.data;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.nicole.misterioapp.R;
import com.nicole.misterioapp.model.AbstractCard;
import com.nicole.misterioapp.model.CardType;
import com.nicole.misterioapp.model.Discovery;
import com.nicole.misterioapp.model.Notebook;

import java.util.List;
import java.util.Map;

public class DataNotebookGUI {

    public String[] names;
    public int[] images;
    public Map<Integer, Discovery> marked;
    public int color;
    public CardType cardType;

    public DataNotebookGUI(String[] names, int[] images, int color) {
        this.names = names;
        this.images = images;
        this.color = color;
    }

    public DataNotebookGUI(){}

    public DataNotebookGUI(CardType cardType, IData data, Notebook notebook, Context context){
        this.cardType = cardType;
        setMarked(notebook);
        List<AbstractCard> cards = data.getCards(cardType);
        this.names = new String[cards.size()];
        for(int i=0; i<cards.size(); i++){
            this.names[i] = cards.get(i).name;
        }
        switch (cardType){
            case SUSPECT:
                this.color = ContextCompat.getColor(context, R.color.wine);
                this.images = new int[] {R.drawable.ic_sus_1_prince, R.drawable.ic_sus_2_duke, R.drawable.ic_sus_3_maid, R.drawable.ic_sus_4_queen, R.drawable.ic_sus_5_knight, R.drawable.ic_sus_6_priest};
                break;
            case ROOM:
                this.color = ContextCompat.getColor(context, R.color.teal);
                this.images = new int[] {R.drawable.ic_sus_6_priest,R.drawable.ic_sus_6_priest,R.drawable.ic_sus_6_priest,R.drawable.ic_sus_6_priest,R.drawable.ic_sus_6_priest,R.drawable.ic_sus_6_priest,R.drawable.ic_sus_6_priest,R.drawable.ic_sus_6_priest,R.drawable.ic_sus_6_priest,R.drawable.ic_sus_6_priest};
                break;
            case WEAPON:
                this.color = ContextCompat.getColor(context, R.color.pink);
                this.images = new int[] {R.drawable.ic_sus_5_knight,R.drawable.ic_sus_5_knight,R.drawable.ic_sus_5_knight,R.drawable.ic_sus_5_knight,R.drawable.ic_sus_5_knight,R.drawable.ic_sus_5_knight};
                break;
        }
    }

    public void setMarked(Notebook notebook){
        this.marked = notebook.getNotebookPageDiscoveries(this.cardType);
    }
}
