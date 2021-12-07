package com.example.empleadoscapas.Presentacion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.empleadoscapas.Compartidos.DataType.DTEmpleado;
import com.example.empleadoscapas.R;

import java.text.DecimalFormat;
import java.util.List;

public class EmpleadosAdapter extends BaseAdapter {

   private Context contexto;
   private List<DTEmpleado> empleados;

   public EmpleadosAdapter (Context contexto, List<DTEmpleado>lista)
   {
       this.contexto= contexto;
       this.empleados = lista;
   }

    @Override
    public int getCount() {
        return empleados.size();
    }

    @Override
    public Object getItem(int position) {
        return empleados.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       View item = convertView;
       EmpleadoViewHolder empleadoViewHolder;

       if (item == null){
           LayoutInflater inflador = LayoutInflater.from(contexto);
           item = inflador.inflate(R.layout.listitem_empleado, null);

           empleadoViewHolder = new EmpleadoViewHolder(item);
           item.setTag(empleadoViewHolder);
       }
       else
       {
           empleadoViewHolder= (EmpleadoViewHolder) item.getTag();
       }
       empleadoViewHolder.enlazarEmpleado(empleados.get(position));
       return item;
    }

    protected class EmpleadoViewHolder {
       private TextView tvCedula;
       private TextView tvNombre;
       private TextView tvSueldo;

       public EmpleadoViewHolder (View vista){
           tvCedula = (TextView) vista.findViewById(R.id.tvCedula);
           tvNombre = (TextView) vista.findViewById(R.id.tvNombre);
           tvSueldo = (TextView) vista.findViewById(R.id.tvSueldo);
       }

       public void enlazarEmpleado(DTEmpleado empleado){
           tvCedula.setText(String.valueOf(empleado.getCedula()));
           tvNombre.setText(empleado.getNombre());
           tvSueldo.setText("$" + new DecimalFormat("#.00").format(empleado.getSueldo()));
       }
    }
}
