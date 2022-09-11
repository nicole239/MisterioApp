package com.nicole.misterioapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.nicole.misterioapp.data.DefaultData;
import com.nicole.misterioapp.data.IData;
import com.nicole.misterioapp.logic.Game;
import com.nicole.misterioapp.logic.GameBuilder;
import com.nicole.misterioapp.model.CardType;
import com.nicole.misterioapp.model.Notebook;


public class BoardActivity extends AppCompatActivity  {
    IData data;
    LinearLayout boardLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        data = DefaultData.getInstance();
        GameBuilder gameBuilder = new GameBuilder();
        gameBuilder.setupGame(data);
        boardLayout = (LinearLayout) findViewById(R.id.layoutBoard);
        setupNotebook();
    }

    private void setupNotebook(){
        Notebook notebook = Game.getInstance().getCurrentPlayerNotebook();
        NotebookFragment fragment = NotebookFragment.newInstance(notebook);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentNotebook, fragment);
        fragmentTransaction.commit();
    }


}