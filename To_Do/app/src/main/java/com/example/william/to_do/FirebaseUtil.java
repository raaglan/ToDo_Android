package com.example.william.to_do;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseUtil {

    // FIREBASE
    public static final String FIREBASE_REPO = "todofirebase-65f3a";
    public static final String FIREBASE_URL = "https://" + FIREBASE_REPO + ".firebaseio.com";
    private Firebase firebase;
    private StringBuffer sb;

    public void iniciarFirebase() {
        // Create a connection to your Firebase database
        firebase = new Firebase(FIREBASE_URL);
    }

    public ArrayList<Tarefa> obterListagemTarefas() {

        final ArrayList<Tarefa> lista = new ArrayList<Tarefa>();

        // FIREBASE
        // Listen for realtime changes
        firebase.child("task").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {

                sb = new StringBuffer();

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                    if (postSnapshot != null) {

                        Tarefa tarefa = postSnapshot.getValue(Tarefa.class);

                        if (tarefa != null) {
                            lista.add(tarefa);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError error) {
            }
        });

        return lista;
    }

    public void inserirTask(Tarefa t) {
        Log.e("buto","buto nesse krai");
        if(firebase == null) {
            iniciarFirebase();
        }

        firebase.child("task").push().setValue(t);
    }
}