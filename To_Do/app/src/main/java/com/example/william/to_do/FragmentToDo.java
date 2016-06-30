package com.example.william.to_do;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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


