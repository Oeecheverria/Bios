package com.example.pickers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    public static final String TAG_SELECTOR_FECHA = "TAG_SELECTOR_FECHA";
    public static final String TAG_SELECTOR_HORA = "TAG_SELECTOR_HORA";


    protected EditText etFecha;
    protected EditText etHora;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        etFecha = (EditText)findViewById(R.id.etFecha);
        etHora = (EditText)findViewById(R.id.etHora);
    }

    public void btnElegirFechaOnClick(View view) {
        SelectorFechaDialog.newInstance()
                .show(getSupportFragmentManager(), TAG_SELECTOR_FECHA);
    }

    public void btnElegirHoraOnClick(View view) {
        SelectorHoraDialog.newInstance().show(getSupportFragmentManager(), TAG_SELECTOR_HORA);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        etFecha.setText(year + "/" +
                String.format("%1$02d", (month + 1)) + "/" + String.format("%1$02d", dayOfMonth));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        etHora.setText(String.format("%1$02d", hourOfDay) + ":" + String.format("%1$02d", minute));
    }


    public static class SelectorFechaDialog extends DialogFragment {

        public static SelectorFechaDialog newInstance() {
            return new SelectorFechaDialog();
        }


        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            Calendar calendario = Calendar.getInstance();
            int anio = calendario.get(Calendar.YEAR);
            int mes = calendario.get(Calendar.MONTH);
            int dia = calendario.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(),
                    (DatePickerDialog.OnDateSetListener)getActivity(), anio, mes, dia);
        }

    }

    public static class SelectorHoraDialog extends DialogFragment {

        public static SelectorHoraDialog newInstance() {
            return new SelectorHoraDialog();
        }


        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar calendario = Calendar.getInstance();
            int hora = calendario.get(Calendar.HOUR_OF_DAY);
            int minutos = calendario.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener)getActivity(), hora, minutos, true);
        }

    }

}