package com.example.william.to_do;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

/**
 * Created by raglan on 25/05/2016.
 */

public class NovaTarefaActivity extends AppCompatActivity {

    private TextView titulo;
    private TextView descricao;
    private DatePicker data;
    private TimePicker hora;
    private Button buttonOK;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_tarefa);



        titulo = (TextView)findViewById(R.id.editTitulo);
        titulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        descricao = (TextView)findViewById(R.id.editDescricao);
        descricao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        data = (DatePicker)findViewById(R.id.datePicker);
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        hora = (TimePicker)findViewById(R.id.timePicker);
        hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttonOK = (Button)findViewById(R.id.buttonOK);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}

