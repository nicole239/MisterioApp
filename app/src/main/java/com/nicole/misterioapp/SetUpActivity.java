package com.nicole.misterioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.nicole.misterioapp.data.Constants;
import com.nicole.misterioapp.data.DefaultData;
import com.nicole.misterioapp.data.IData;
import com.nicole.misterioapp.model.Suspect;

import java.util.ArrayList;
import java.util.List;

public class SetUpActivity extends AppCompatActivity {

    IData data;
    TextView lblPlayersPicked;
    Button btnBeginGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);

        data = DefaultData.getInstance();

        lblPlayersPicked = (TextView) findViewById(R.id.lblPlayersPicked);
        btnBeginGame = (Button) findViewById(R.id.btnBeginGame);
        btnBeginGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewActivity();
            }
        });

        List<Integer> btnSuspects = new ArrayList<>();
        btnSuspects.add(R.id.btnAddSus1);
        btnSuspects.add(R.id.btnAddSus2);
        btnSuspects.add(R.id.btnAddSus3);
        btnSuspects.add(R.id.btnAddSus4);
        btnSuspects.add(R.id.btnAddSus5);
        btnSuspects.add(R.id.btnAddSus6);

        for(int i=0; i < btnSuspects.size(); i++){
            ImageButton button = findViewById(btnSuspects.get(i));
            int finalI = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addPlayer(finalI);
                }
            });
        }
    }

    private void addPlayer(int id){
        data.addPlayerChosenSuspect(id);
        lblPlayersPicked.setText(textPlayers());
        btnBeginGame.setEnabled(data.getPlayers().size() > 0);
    }

    private String textPlayers(){
        String allPlayers = "You've picked:  ";
        for(Suspect player : data.getPlayers()){
            allPlayers += player.name + ", ";
        }
         allPlayers= allPlayers.substring(0, allPlayers.length() - 2);
        return allPlayers;
    }

    private void openNewActivity(){
        Intent intent = new Intent(this, BoardActivity.class);
        startActivity(intent);
    }


}