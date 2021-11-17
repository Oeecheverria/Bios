package com.example.intercambiarfagments;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    
    protected Fragment fragmentoA;
    protected Fragment fragmentoB;

    AFragment frag1 = new AFragment();
    BFragment frag2 = new BFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentoA= frag1.newInstance("este es el fragmento A.");
        fragmentoB= frag2.newInstance("este es el fragmento B.");

        cambiarFragmento(fragmentoA, false);

    }

    public void btnIntercambiarOnClick(View view) {
        Fragment nuevoFragmento = fragmentoA.isAdded() ? fragmentoB: fragmentoA;
        cambiarFragmento(nuevoFragmento, false);
    }

    protected void cambiarFragmento (Fragment nuevoFragmento, boolean agregarAPilaVolver)
    {
        FragmentTransaction transaccionFragmentos = getSupportFragmentManager().beginTransaction();

        transaccionFragmentos.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaccionFragmentos.replace(R.id.frlZonaIntercambio, nuevoFragmento);

        if (agregarAPilaVolver){
            transaccionFragmentos.addToBackStack(nuevoFragmento.getClass().getSimpleName());
        }
        transaccionFragmentos.commit();
    }
}