package com.esparza.nacricardoesparzarm71238;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText etNomeUsuario;
    private String nomeUsuarioPref = "UserName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNomeUsuario = findViewById(R.id.etNomeUsuario);
        etNomeUsuario.setText(getPrefs().getString(nomeUsuarioPref, ""));
    }

    public void btConfirmarOnClick(View view){
        getPrefs().edit().putString(nomeUsuarioPref, etNomeUsuario.getText().toString()).apply();
        Intent intent = new Intent(MainActivity.this, ProdutosActivity.class);
        startActivity(intent);
    }

    private SharedPreferences getPrefs(){
        return PreferenceManager.getDefaultSharedPreferences(this);
    }
}
