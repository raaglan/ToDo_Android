package com.example.william.to_do;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.Firebase;

import java.util.ArrayList;

/**
 * Created by cragl on 04/06/2016.
 */
public class FragmentToDo extends Fragment {

    private ArrayList<Tarefa> minhasTarefas = new ArrayList<>();
    private TarefaAdapter tarefaAdapter;
    private ListView toDoList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo, container, false);
        toDoList = (ListView) view.findViewById(R.id.lvToDo);

        Bundle bundle = getArguments();

        minhasTarefas = bundle.getParcelableArrayList("listaTarefas");

        tarefaAdapter = new TarefaAdapter(getContext(), minhasTarefas);

        toDoList.setAdapter(tarefaAdapter);
        tarefaAdapter.notifyDataSetChanged();

        return view;
    }

}