package com.simplifyme.restcommventas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class SplashScreen extends Activity {
    private ImageView ivAnimacion;
    private AnimationDrawable savingAnimation;
    TextView txt_anim;
    Animation trasladar;

    public static String Sucursal = "sucursalKey";
    public static String Servidor = "servidorKey";
    public static String Usuario = "usuarioKey";

    // Set the duration of the splash screen
    private static final long SPLASH_SCREEN_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Set portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);

        //COMPROBAR LA CONECTIVIDAD DEL DISPOSITIVO
        if (!verificaConexion(this)) {
            Toast toast1 = Toast.makeText(getApplicationContext(),"No tienes conectividad en tu dispositivo.", Toast.LENGTH_LONG);  toast1.show();
            finish();
        }



        String cad = getperfil();
        txt_anim = (TextView) findViewById(R.id.txt_anim);
        txt_anim.setText(cad);

        ivAnimacion = (ImageView)findViewById(R.id.linea_animacion);
        ivAnimacion.setBackgroundResource(R.drawable.frames);
        savingAnimation = (AnimationDrawable)ivAnimacion.getBackground();
        savingAnimation.start();


        trasladar = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.desplazar);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // Start the next activity
                Intent mainIntent = new Intent().setClass(
                        SplashScreen.this, VentasActivity.class);
                startActivity(mainIntent);
                // Close the activity so the user won't able to go back this
                // activity pressing Back button
                finish();
            }
        };

        // Simulate a long loading process on application startup.
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //METODO PARA COMPROBRACION DE CONECTIVIDAD EN DISPOSITIVO
    public static boolean verificaConexion(Context ctx) {
        boolean bConectado = false;
        ConnectivityManager connec = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        // No solo wifi, tambien GPRS
        NetworkInfo[] redes = connec.getAllNetworkInfo();
        // este bucle deberia no ser tan apa
        for (int i = 0; i < 2; i++) {
            // Tenemos conexin? ponemos a true
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                bConectado = true;
            }
        }
        return bConectado;
    }

    public String getperfil(){
        SharedPreferences sharedpreferences = getSharedPreferences("perfil", Context.MODE_PRIVATE);
        if (sharedpreferences.contains(Sucursal)){
            Sucursal = sharedpreferences.getString(Sucursal, "");
        }
        if (sharedpreferences.contains(Servidor)) {
            Servidor = sharedpreferences.getString(Servidor, "");
        }
        if (sharedpreferences.contains(Usuario)) {
            Usuario=sharedpreferences.getString(Usuario, "");
        }
        return "       " + Usuario+"."+Sucursal;
    }
}
