package com.example.william.to_do;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.firebase.client.Firebase.*;

public class FirebaseUtil {

    // FIREBASE
    public static final String FIREBASE_REPO = "todoandroid-b7830";
    public static final String FIREBASE_URL = "https://" + FIREBASE_REPO + ".firebaseio.com";
    private Firebase firebase;
    private StringBuffer sb;

    public void iniciarFirebase() {
        // Create a connection to your Firebase database
        firebase = new Firebase(FIREBASE_URL);
    }

    public ArrayList<Tarefa> obterListagemTarefas() {
        final ArrayList<Tarefa> lista = new ArrayList<>();
        // FIREBASE
        // Listen for realtime changes
        firebase.child("task").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {

                sb = new StringBuffer();

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                    if (postSnapshot != null) {

                        Tarefa tarefa = postSnapshot.getValue(Tarefa.class);
                        tarefa.setKey(postSnapshot.getKey());

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

    public ArrayList<Tarefa> obterListagemTarefasRealizadas() {
        final ArrayList<Tarefa> listaDone = new ArrayList<>();
        // FIREBASE
        // Listen for realtime changes
        firebase.child("taskDone").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {

                sb = new StringBuffer();

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                    if (postSnapshot != null) {

                        Tarefa tarefa = postSnapshot.getValue(Tarefa.class);
                        tarefa.setKey(postSnapshot.getKey());

                        if (tarefa != null) {
                            listaDone.add(tarefa);
                        }

                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError error) {
            }
        });

        return listaDone;
    }

    //-------------------------------------------------------------

//    public ArrayList<String> tarefas istagemTarefasR34ealizadas() {
//        final ArrayList<Tarefa> listaDone = new ArrayList<>();
//        // FIREBASE
//        // Listen for realtime changes
//        firebase.child("taskDone").addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//
//                sb = new StringBuffer();
//
//
//            }
//
//            @Override
//            public void onCancelled(FirebaseError error) {
//            }
//        });
//
//    }
    //---------------------------------------------------------------------------------------

    public void inserirTask(Tarefa t) {

        if(firebase == null) {
            iniciarFirebase();
        }

        firebase.child("task").push().setValue(t);
//        notifyDataSetChanged();
    }
    public void inserirTaskRealizada(Tarefa t){

        if(firebase == null) {
            iniciarFirebase();
        }

        firebase.child("taskDone").push().setValue(t);
    }

    public void removerTask(String key){

        if(firebase == null) {
            iniciarFirebase();
        }
        Log.e("String", key);
        firebase.child("task/"+key).removeValue();
    }

    public void removerTaskDone(String key){

        if(firebase == null) {
            iniciarFirebase();
        }
        Log.e("String", key);
        firebase.child("taskDone/"+key).removeValue();
    }



    public void inserirUsuario(Usuario u) {

        if(firebase == null) {
            iniciarFirebase();
        }

        firebase.child("user").push().setValue(u);
    }

    public void onChildRemoved(ArrayList<Tarefa> lista, String key){
        for (Tarefa t : lista){
            if (t.getKey().equals(key)){
                lista.remove(t);
                break;
            }
        }
    }

    public ArrayList<Usuario> getUsersByEmailAndPassword(Usuario u) {
        final Usuario user = u;
        final ArrayList<Usuario> users = new ArrayList<>();

        firebase.child("user").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {

                sb = new StringBuffer();

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                    if (postSnapshot != null) {

                        Usuario userTemp = postSnapshot.getValue(Usuario.class);

                        if (userTemp != null) {

                            if (userTemp.getEmail().equals(user.getEmail()) &&  userTemp.getSenha().equals(user.getSenha())){
                                users.add(userTemp);

                            }
                        }

                    }
                }

            }

            @Override
            public void onCancelled(FirebaseError error) {



            }
        });

        return users;



    }
}