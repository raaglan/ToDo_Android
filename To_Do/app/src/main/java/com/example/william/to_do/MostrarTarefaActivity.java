package com.example.william.to_do;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MostrarTarefaActivity extends AppCompatActivity {

    @InjectView(R.id.link_signup)
    TextView nomeNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_tarefa);
            ButterKnife.inject(this);
        Bundle dados = getIntent().getExtras();
        Tarefa tarefa = dados.getParcelable("tarefa");
        nomeNota.setText(tarefa.getTitulo());
    }

}
