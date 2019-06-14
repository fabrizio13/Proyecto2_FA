package com.exampledemo.parsaniahardik.sqlitemultitabledemonuts;

import java.io.Serializable;

/**
 * Created by Parsania Hardik on 26-Apr-17.
 */
public class Empleados implements Serializable{

    private String nombre, /*color,*/ nombre_turno, /*estado_reserva,*/ dia;
    private int id/*, cont_turno*/;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   /* public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }*/

    public String getNombre_turno() {
        return nombre_turno;
    }

    public void setNombre_turno(String nombre_turno) {
        this.nombre_turno = nombre_turno;
    }

    /*public String getEstado_reserva() {
        return estado_reserva;
    }

    public void setEstado_reserva(String estado_reserva) {
        this.estado_reserva = estado_reserva;
    }*/

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

  /*  public String getCont_turno() {
        return cont_turno;
    }

    public void setCont_turno(String cont_turno) {
        this.cont_turno = cont_turno;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
