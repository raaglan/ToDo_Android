package com.example.william.to_do;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by willi on 20/05/2016.
 */
public class TarefaAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private Context context;
    private ArrayList<Tarefa> lista;



    public TarefaAdapter(Context context,ArrayList<Tarefa> tarefas){
        this.context = context;
        this.lista = tarefas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Tarefa getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ItemSuporte itemHolder;
        final Tarefa tarefa = lista.get(position);

        if(convertView == null){

            convertView = mInflater.inflate(R.layout.custom_layout, null);
            itemHolder = new ItemSuporte();

            itemHolder.txtTitulo = (TextView) convertView.findViewById(R.id.txtTitulo);
            itemHolder.txtDescricao = (TextView) convertView.findViewById(R.id.txtDescricao);
            itemHolder.txtHora = (TextView) convertView.findViewById(R.id.txtHora);
            itemHolder.container = (RelativeLayout) convertView.findViewById(R.id.container);
            itemHolder.btnDone = (ImageButton) convertView.findViewById(R.id.btnDone);
            itemHolder.btnDelete = (ImageButton) convertView.findViewById(R.id.btnDelete);

            itemHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemHolder.btnDelete.setImageResource(R.drawable.btn_delete);
                    Toast t = Toast.makeText(context, "Click Delete", Toast.LENGTH_SHORT);
                    t.show();
                }
            });

            itemHolder.btnDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemHolder.btnDone.setImageResource(R.drawable.btn_done);
                    Toast t = Toast.makeText(context, "Click Done", Toast.LENGTH_SHORT);
                    t.show();
                }
            });

            itemHolder.mDetector = new GestureDetectorCompat(context,
                    new MyGestureListener(context, convertView));
            convertView.setTag(itemHolder);

            itemHolder.txtTitulo.setText(tarefa.getTitulo());
            itemHolder.txtDescricao.setText(tarefa.getDescricao());
            itemHolder.txtHora.setText(tarefa.getData() + " às "+ tarefa.getHora());


        }
        else{
            itemHolder = (ItemSuporte) convertView.getTag();
        }
        final ItemSuporte holder = (ItemSuporte) convertView.getTag();
        itemHolder.container.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                itemHolder.mDetector.onTouchEvent(event);

                return false;
            }
        });
//
//       itemHolder.container.setOnTouchListener(new View.OnTouchListener() {
//           @Override
//           public boolean onTouch(View v, MotionEvent event) {
//
//
//
//               return false;
//           }
//       });

        itemHolder.container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent it = new Intent(v.getContext(),MostrarTarefaActivity.class);
                Bundle dados = new Bundle();
               String titulo = tarefa.getTitulo();
               dados.putString("titulo",titulo);
               it.putExtras(dados);
                context.startActivity(it);
                return false;
            }
        });
        return convertView;
    }

    public ArrayList<Tarefa> getList(){
        return lista;
    }

    private static class ItemSuporte{
        TextView txtTitulo;
        TextView txtDescricao;
        TextView txtHora;
        GestureDetectorCompat mDetector;
        RelativeLayout container;
        ImageButton btnDone;
        ImageButton btnDelete;
    }





}
