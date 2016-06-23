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

import com.firebase.client.Firebase;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

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
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_tarefa);

        Firebase.setAndroidContext(this);

        final FirebaseUtil bdFirebase = new FirebaseUtil();
        bdFirebase.iniciarFirebase();



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

                Tarefa tarefaTemp = new Tarefa();

                tarefaTemp.setTitulo(titulo);
                tarefaTemp.setDescricao(descricao);
                tarefaTemp.setHora(hora);
                tarefaTemp.setData(data);

                bdFirebase.inserirTask(tarefaTemp);
                finish();

            }
        });

        tvHora = (TextView) findViewById(R.id.tvHora);
        tvHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "TimePicker");
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


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "NovaTarefa Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.william.to_do/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "NovaTarefa Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.william.to_do/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}










