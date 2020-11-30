package com.smv.helloandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    //spremenljivke brez navedbe nivoja skrivanja so neke sorte internal (C#)
    //dostopne so v celotnem package-u
    TextView hello;
    Button buttonHello;
    //sayHello ne rabimo, dokler se nanj ne skličemo, v našem primeru za longClick
    //za click ni treba, ker smo uporabili metodo in se na gumb sklicujemo le v xml
    Button buttonBye;
    ConstraintLayout activity;


    //uporaba poimenovanega listenerja
    //deklariramo ga izven metod, potem ga lahko povežemo z različnimi objekti
    View.OnLongClickListener longCLickListener = new View.OnLongClickListener()
    {
        @Override
        public boolean onLongClick(View v)
        {
            hello.setVisibility(View.INVISIBLE);
            Toast.makeText(MainActivity.this, "Say hello or goodbye", Toast.LENGTH_SHORT).show();
            //return true konča z obdelavo
            //return false obdela še dogodek Click
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hello = findViewById(R.id.hello);
        //cast v ciljni tip pogosto vidimo v tutorialih, ni pa potreben
        buttonHello = (Button) findViewById(R.id.buttonSayHello);
        buttonBye = findViewById(R.id.buttonSayBye);
        activity = findViewById(R.id.activity);

        //uporaba anonimnega OnCLickListenerja, pogost način,
        //uporaben, če je koda le za en objekt
        buttonBye.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                hello.setVisibility(View.VISIBLE);
                hello.setText("Bye, Android!");
            }
        });

        //uporaba poimenovanega LongClickLIstenerja
        hello.setOnLongClickListener(longCLickListener);
        buttonHello.setOnLongClickListener(longCLickListener);
        buttonBye.setOnLongClickListener(longCLickListener);
        activity.setOnLongClickListener(longCLickListener);
    }

    //uporaba onclick metode, deluje le za klik, za druge dogodke ne
    //ni najbolj priporočljiv način, v java kodi ni razvidna povezava med metodeo in objektom
    public void SayHello(View view)
    {
        hello.setVisibility(View.VISIBLE);
        hello.setText("Hello, Android!");
    }
}