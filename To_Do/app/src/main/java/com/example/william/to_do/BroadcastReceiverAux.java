package com.example.william.to_do;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by willi on 01/06/2016.
 */
public class BroadcastReceiverAux extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Script", "Notificou");



//        Tarefa t = MainActivity.getInstance().getTarefa();
//        MainActivity.getInstance().gerarNotificacao(context, new Intent(context, MainActivity.class), t);

    }


}
