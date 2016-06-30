package com.example.william.to_do;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.InjectView;

public class MostrarTarefaActivity extends AppCompatActivity {

    @InjectView(R.id.link_signup)
    TextView nomeNota;
    TextView nomeDescricao;
    TextView tvHora;
    TextView tvData;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_tarefa);

        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        mToolbar.setTitle("Tarefas");

        Bundle dados = getIntent().getExtras();

        nomeNota = (TextView) findViewById(R.id.nomenota);
        String titulo = dados.getString("titulo");
        nomeNota.setText(titulo);

        nomeDescricao = (TextView) findViewById(R.id.nomedescricao);
        String descricao = dados.getString("descricao");
        nomeDescricao.setText(descricao);

        tvHora = (TextView) findViewById(R.id.tvhora2);
        String tvhora2 = dados.getString("hora");
        tvHora.setText(tvhora2);

        tvData = (TextView) findViewById(R.id.tvdata2);
        String tvdata2 = dados.getString("data");
        tvData.setText(tvdata2);


    }

}
