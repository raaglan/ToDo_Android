package com.example.william.to_do;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private ArrayList<Tarefa> tarefas = new ArrayList<>();
    private TarefaAdapter tarefaAdapter;
    private ListView toDoList;
    private static final int NOVATAREFA = 1;
    private static MainActivity instance;
    private Tarefa tarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        instance = this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(view.getContext(), NovaTarefaActivity.class);
                startActivityForResult(it, NOVATAREFA);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toDoList = (ListView) findViewById(R.id.lvToDo);
        toDoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tarefa t = (Tarefa) parent.getAdapter().getItem(position);
                gerarNotificacao(view.getContext(), new Intent(view.getContext(), MainActivity.class), t);

            }
        });

        //lança a notificação automatica, quando a classe BroadCast nao estiver comentada.
        //Mas terá que ser feita uma comparação com as datas das tarefas cadastradas com a hora do sistema
        //para poder lançar a notificaçao. Do jeito que está ele lança após um determinado tempo sem buscar nenhum dado da tarefa
        Intent intent = new Intent("ALARME_DISPARADO");
        PendingIntent p = PendingIntent.getBroadcast(this, 0, intent, 0);

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.add(Calendar.SECOND, 3);

        AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarme.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), p);

    }

    public static MainActivity getInstance(){
        return instance;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        Intent intent = new Intent("ALARME_DISPARADO");
        PendingIntent p = PendingIntent.getBroadcast(this, 0, intent, 0);

        AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarme.cancel(p);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == NOVATAREFA ){
            Tarefa t = new Tarefa();
            this.tarefa = t;

            String nome = data.getStringExtra("titulo");
            t.setTitulo(nome);

            String descricao = data.getStringExtra("descricao");
            t.setDescricao(descricao);

            String hora = data.getStringExtra("hora");
            t.setHora(hora);

            String date = data.getStringExtra("data");
            t.setData(date);

            tarefas.add(t);

            tarefaAdapter = new TarefaAdapter(this, tarefas);

            toDoList.setAdapter(tarefaAdapter);
            tarefaAdapter.notifyDataSetChanged();



        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_todo) {

        } else if (id == R.id.nav_done) {

        } else if (id == R.id.nav_notes){

        } else if (id == R.id.nav_info) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void replaceFragment(){}
    

    public void gerarNotificacao(Context context, Intent intent, Tarefa t) {

        Toast toast2 = Toast.makeText(this, "Clicou em " + t.getDescricao(), Toast.LENGTH_SHORT);
        toast2.show();

        String titulo = t.getTitulo();
        String descricao = t.getDescricao();
        t.setTitulo(titulo);
        t.setDescricao(descricao);

        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent p = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setTicker("Voce tem uma tarefa agendada para hoje!");
        builder.setContentTitle(t.getTitulo());
        builder.setContentText(t.getDescricao());
        builder.setSmallIcon(R.drawable.ic_not_small);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_assignment_late_red_24dp));
        builder.setContentIntent(p);

        Notification n = builder.build();
        n.vibrate = new long[]{150, 300, 150, 600};
        n.flags = Notification.FLAG_AUTO_CANCEL;
        nm.notify(R.drawable.ic_not_small, n);


        try {
            Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            MediaPlayer mp = MediaPlayer.create(context, som);
            mp.start();
        } catch (Exception e) {
        }

        Toast toast = Toast.makeText(this, "Clicou em " + titulo, Toast.LENGTH_SHORT);
        toast.show();

    }
    //esse método terá q fazer a mesma comparacao de horas entre as tarefas e o sistema, e
    //após encontrar uma tarefa com a hora igual irá retornar a respectiva tarefa, que será
    //usado também no BroadCastReceiver, para lançar a notifcação sem precisar o app está aberto.
    public Tarefa getTarefa(){
        return tarefa;
    }





}
