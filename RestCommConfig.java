package com.simplifyme.restcommventas;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RestCommConfig extends Activity {
    private TextView tvSucursal;
    private TextView tvServidor;
    private TextView tvPuerto;
    private TextView tvUsuario;
    private TextView tvContra;

    private EditText etSucursal;
    private EditText etServidor;
    private EditText etPuerto;
    private EditText etUsuario;
    private EditText etContra;

    public static final String Sucursal = "sucursalKey";
    public static final String Servidor = "servidorKey";
    public static final String Puerto = "puertoKey";
    public static final String Usuario = "usuarioKey";
    public static final String Contrasena = "contrasenaKey";

    SharedPreferences sharedpreferences;

    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_comm_config);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        tvSucursal = (TextView) findViewById(R.id.tfSucursalEt);
        tvServidor = (TextView) findViewById(R.id.tfServidorEt);
        tvPuerto = (TextView) findViewById(R.id.tfPuertoEt);
        tvUsuario = (TextView) findViewById(R.id.autoComUsuario);
        tvContra = (TextView) findViewById(R.id.tfContraEt);

        etSucursal = (EditText) findViewById(R.id.tfSucursalEt);
        etServidor = (EditText) findViewById(R.id.tfServidorEt);
        etPuerto = (EditText) findViewById(R.id.tfPuertoEt);
        etUsuario = (EditText) findViewById(R.id.autoComUsuario);
        etContra = (EditText) findViewById(R.id.tfContraEt);

        /*Context myContext;
        try {
            myContext = getApplicationContext().createPackageContext
                    ("com.simplifyme.crearperfil", Context.MODE_WORLD_WRITEABLE);
            SharedPreferences preferencia = myContext.getSharedPreferences
                    ("simplify", Context.MODE_WORLD_READABLE);
        } catch (NameNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }*/

        sharedpreferences = getSharedPreferences("perfil", Context.MODE_PRIVATE);

        if (sharedpreferences.contains(Sucursal)){
            tvSucursal.setText(sharedpreferences.getString(Sucursal, ""));}
        if (sharedpreferences.contains(Servidor)){
            tvServidor.setText(sharedpreferences.getString(Servidor, ""));}
        if (sharedpreferences.contains(Puerto)){
            tvPuerto.setText(sharedpreferences.getString(Puerto, ""));}
        if (sharedpreferences.contains(Usuario)){
            tvUsuario.setText(sharedpreferences.getString(Usuario, ""));}
        if (sharedpreferences.contains(Contrasena)){
            tvContra.setText(sharedpreferences.getString(Contrasena,""));}

        bt = (Button)findViewById(R.id.GuardarPerfil);

        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {
                Ejecutar();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.rest_comm_config, menu);
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


    public void Ejecutar() {
        //sharedpreferences=getSharedPreferences("perfil",Context.MODE_PRIVATE);
        String suc  = etSucursal.getText().toString();
        String ser  = etServidor.getText().toString();
        String pue  = etPuerto.getText().toString();
        String usu  = etUsuario.getText().toString();
        String con  = etContra.getText().toString();
        Editor editor = sharedpreferences.edit();
        editor.putString(Sucursal, suc);
        editor.putString(Servidor, ser);
        editor.putString(Puerto, pue);
        editor.putString(Usuario, usu);
        editor.putString(Contrasena, con);
        editor.commit();

        finish();
    }
}
