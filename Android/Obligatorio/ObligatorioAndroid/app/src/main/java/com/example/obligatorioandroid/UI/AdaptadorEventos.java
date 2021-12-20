package com.example.obligatorioandroid.UI;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.obligatorioandroid.Compartidos.DataTypes.DTEvento;

import java.util.List;
import java.util.Map;

public class AdaptadorEventos extends BaseAdapter{
   private Context contexto;
   private List<DTEvento> empleados;

    private Map<Integer, Integer> idsImagenes;


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
