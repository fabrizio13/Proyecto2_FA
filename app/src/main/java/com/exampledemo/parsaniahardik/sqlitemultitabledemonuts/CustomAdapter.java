package com.exampledemo.parsaniahardik.sqlitemultitabledemonuts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Parsania Hardik on 26-Apr-17.
 */
public class CustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Empleados> empleadosArrayList;

    public CustomAdapter(Context context, ArrayList<Empleados> empleadosArrayList) {

        this.context = context;
        this.empleadosArrayList = empleadosArrayList;
    }


    @Override
    public int getCount() {
        return empleadosArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return empleadosArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lv_item, null, true);

            holder.tvnombre = (TextView) convertView.findViewById(R.id.nombre);
            holder.tvturno = (TextView) convertView.findViewById(R.id.turno);
            holder.tvdia = (TextView) convertView.findViewById(R.id.dia);


            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        holder.tvnombre.setText("Nombre: "+empleadosArrayList.get(position).getNombre());
        holder.tvturno.setText("Turno: "+empleadosArrayList.get(position).getNombre_turno());
        holder.tvdia.setText("Dia: "+empleadosArrayList.get(position).getDia());

        return convertView;
    }

    private class ViewHolder {

        protected TextView tvnombre, tvturno, tvdia;
    }

}