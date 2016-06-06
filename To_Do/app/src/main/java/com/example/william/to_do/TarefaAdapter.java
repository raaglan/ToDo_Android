package com.example.william.to_do;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by willi on 20/05/2016.
 */
public class TarefaAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private Context context;
    private ArrayList<Tarefa> lista;


    public TarefaAdapter(Context context, ArrayList<Tarefa> lista){
        this.context = context;
        this.lista = lista;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ItemSuporte itemHolder;
        Tarefa tarefa = lista.get(position);

        if(convertView == null){

            convertView = mInflater.inflate(R.layout.custom_layout, null);
            itemHolder = new ItemSuporte();

            itemHolder.txtTitulo = (TextView) convertView.findViewById(R.id.txtTitulo);
            itemHolder.txtDescricao = (TextView) convertView.findViewById(R.id.txtDescricao);
            itemHolder.txtHora = (TextView) convertView.findViewById(R.id.txtHora);

            itemHolder.txtTitulo.setText(tarefa.getTitulo());
            itemHolder.txtDescricao.setText(tarefa.getDescricao());
            itemHolder.txtHora.setText(tarefa.getData() + " às "+ tarefa.getHora());

        }
        else{
            itemHolder = (ItemSuporte) convertView.getTag();
        }
        return convertView;
    }

    public ArrayList<Tarefa> getList(){
        return lista;
    }

    private static class ItemSuporte{
        TextView txtTitulo;
        TextView txtDescricao;
        TextView txtHora;
    }



}
