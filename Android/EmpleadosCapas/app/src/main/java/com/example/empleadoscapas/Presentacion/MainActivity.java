package com.example.empleadoscapas.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.empleadoscapas.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnEmpleadosOnClick(View view) {

        Intent intencionEmpleados = new Intent(getApplicationContext(), EmpleadoActivity.class);

        startActivity(intencionEmpleados);


    }
}