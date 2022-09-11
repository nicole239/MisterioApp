package com.nicole.misterioapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.nicole.misterioapp.data.DataHandGUI;
import com.nicole.misterioapp.data.DataNotebookGUI;
import com.nicole.misterioapp.data.DefaultData;
import com.nicole.misterioapp.data.IData;
import com.nicole.misterioapp.logic.Game;
import com.nicole.misterioapp.model.CardType;
import com.nicole.misterioapp.model.Deck;
import com.nicole.misterioapp.utils.HandAdapter;
import com.nicole.misterioapp.utils.NotebookAdapter;

public class HandActivity extends AppCompatActivity {

    private Deck playerDeck;
    RecyclerView[] handView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hand);

        playerDeck = Game.getInstance().getCurrentPlayerDeck();
        handView = new RecyclerView[3];
        setupLists(0, R.id.handSuspectCards, CardType.SUSPECT);
        setupLists(1, R.id.handRoomCards, CardType.SUSPECT);
        setupLists(2, R.id.handWeaponCards, CardType.SUSPECT);
    }

    private void setupLists(int position, int viewId, CardType cardType){
        RecyclerView recyclerView = (RecyclerView) findViewById(viewId);
        IData data = DefaultData.getInstance();
        HandAdapter adapter = new HandAdapter(new DataHandGUI(playerDeck, cardType));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        handView[position] = recyclerView;
    }
}