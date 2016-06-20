package com.example.william.to_do;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by willi on 25/05/2016.
 */
    public class NovaTarefaActivity extends AppCompatActivity {

    private TextView edtTitulo;
    private TextView edtDescricao;
    private TextView tvHora;
    private TextView tvData;
    private Button btnOk;
    final Tarefa tarefa = new Tarefa();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_tarefa);



        edtTitulo = (TextView) findViewById(R.id.editTitulo);
        edtTitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtTitulo.setText("");
                tarefa.setTitulo(edtTitulo.getText().toString());
            }
        });

        edtDescricao = (TextView) findViewById(R.id.editDescricao);
        edtDescricao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtDescricao.setText("");
                tarefa.setDescricao(edtDescricao.getText().toString());
            }
        });

        btnOk = (Button) findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent();

                String titulo = edtTitulo.getText().toString();
                String descricao = edtDescricao.getText().toString();
                String hora = tvHora.getText().toString();
                String data = tvData.getText().toString();

                it.putExtra("titulo", titulo);
                it.putExtra("descricao", descricao);
                it.putExtra("hora", hora);
                it.putExtra("data", data);

                setResult(RESULT_OK, it);

                finish();
            }
        });

        tvHora = (TextView) findViewById(R.id.tvHora);
        tvHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(),"TimePicker");
                tarefa.setHora(tvHora.getText().toString());

            }
        });

        tvData = (TextView) findViewById(R.id.tvData);
        tvData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "DatePicker");
                tarefa.setData(tvData.getText().toString());
            }
        });


    }


}










