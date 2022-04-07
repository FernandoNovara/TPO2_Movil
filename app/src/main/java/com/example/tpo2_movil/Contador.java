package com.example.tpo2_movil;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.provider.CallLog;
import android.provider.Telephony;
import android.telecom.Call;
import android.util.Log;

import androidx.annotation.Nullable;

import java.security.Provider;

public class Contador extends Service {

    private static int bandera;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("salida","iniciado");
        bandera = 1;
        Thread trabajador = new Thread(new Cuenta());
        trabajador.start();
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bandera = 0;
        Log.d("salida","Servicio destruido");
    }

    @SuppressLint("Range")
    public void leerMensajes()
    {
        Uri Mensaje = Uri.parse("content://sms/");
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(Mensaje,null,null,null,null);

        String numero = null,mensaje = null,nombre = null;

        if (cursor.getCount() > 0) {
            for (int i = 0; i < 5; i++) {
                cursor.moveToPosition(i);
                if(cursor.moveToNext()) {
                    mensaje = cursor.getString(12);
                    numero = cursor.getString(cursor.getColumnIndex(Telephony.Sms.ADDRESS));
                    nombre = cursor.getString(cursor.getColumnIndex(Telephony.Sms.CREATOR));
                }
                Log.d("Mensajes: ", nombre + " " + mensaje + " " + numero);
            }
            Log.d("Mensajes: ","---------------------------------------------------------");
        }
    }

    private class Cuenta implements Runnable{

        @Override
        public void run() {
            while (bandera == 1)
            {
                try
                {
                    leerMensajes();
                    Thread.sleep(9000);
                }
                catch (InterruptedException e)
                {
                    e.getStackTrace();
                }
            }
        }
    }
}
