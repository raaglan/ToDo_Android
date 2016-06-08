package com.example.william.to_do;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by cragl on 04/06/2016.
 */
public class FragmentToDo extends Fragment {

    private static final int NOVATAREFA = 1;
    private ArrayList<Tarefa> tarefas = new ArrayList<>();
    private TarefaAdapter tarefaAdapter;
    private ListView toDoList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_todo, container);

        Button btnOk = (Button) view.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }

//            @Override
            public void onActivityResult(){
                Intent it = new Intent();
                Tarefa tarefa = new Tarefa();

                String nome = it.getStringExtra("titulo");
                tarefa.setTitulo(nome);

                String descricao = it.getStringExtra("descricao");
                tarefa.setDescricao(descricao);

                String hora = it.getStringExtra("hora");
                tarefa.setHora(hora);

                String date = it.getStringExtra("data");
                tarefa.setData(date);

                tarefas.add(tarefa);

                tarefaAdapter = new TarefaAdapter(getView().getContext(), tarefas);

                toDoList.setAdapter(tarefaAdapter);
                tarefaAdapter.notifyDataSetChanged();

            }
        });



//        onActivityResult();
//        ArrayList<Tarefa> tarefas = new ArrayList<>();
//        toDoList.setAdapter(new TarefaAdapter(getView().getContext(), tarefas));

        return view;
    }

    // passa os campos para o fragment da tela de nova tarefa
//    public void onActivityResult(){
//            Intent it = new Intent();
//            Tarefa tarefa = new Tarefa();
//
//            String nome = it.getStringExtra("titulo");
//            tarefa.setTitulo(nome);
//
//            String descricao = it.getStringExtra("descricao");
//            tarefa.setDescricao(descricao);
//
//            String hora = it.getStringExtra("hora");
//            tarefa.setHora(hora);
//
//            String date = it.getStringExtra("data");
//            tarefa.setData(date);
//
//            tarefas.add(tarefa);
//
//            tarefaAdapter = new TarefaAdapter(getView().getContext(), tarefas);
//
//            toDoList.setAdapter(tarefaAdapter);
//            tarefaAdapter.notifyDataSetChanged();
//
//        }

}
