package com.exampledemo.parsaniahardik.sqlitemultitabledemonuts;

/**
 * Created by Parsania Hardik on 15/01/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Parsania Hardik on 11/01/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "user_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_EMPLEADO = "empleado";
    private static final String TABLE_TURNO = "turnos_empleados";
    private static final String TABLE_DIA = "dias_empleados";
    private static final String KEY_ID = "id";
    private static final String KEY_NOMBRE = "nombre";
    private static final String KEY_COLOR = "color";
    private static final String KEY_CONT_TURNO = "cont_turno";
    private static final String KEY_TURNO = "turno";
    private static final String KEY_ESTADO_RESERVA = "estado_reserva";
    private static final String KEY_DIA = "dias";

    /*CREATE TABLE students ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone_number TEXT......);*/

    private static final String CREATE_TABLE_EMPLEADOS = "CREATE TABLE "
            + TABLE_EMPLEADO + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NOMBRE + " TEXT, " + KEY_COLOR + " TEXT, " + KEY_CONT_TURNO + "INTEGER);";

    private static final String CREATE_TABLE_TURNOS = "CREATE TABLE "
            + TABLE_TURNO + "(" + KEY_ID + " INTEGER,"+ KEY_TURNO + " TEXT, "+ KEY_ESTADO_RESERVA + "TEXT  );";

    private static final String CREATE_TABLE_DIAS = "CREATE TABLE "
            + TABLE_DIA + "(" + KEY_ID + " INTEGER,"+ KEY_DIA + " TEXT );";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        Log.d("table", CREATE_TABLE_EMPLEADOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_EMPLEADOS);
        db.execSQL(CREATE_TABLE_TURNOS);
        db.execSQL(CREATE_TABLE_DIAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_EMPLEADO + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_TURNO + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_DIA + "'");
        onCreate(db);
    }

    public void addEmpleado(String nombre,/* String color,*/ String turno, String dia/*, String estado_reserva, int cont_turno*/) {
        SQLiteDatabase db = this.getWritableDatabase();
        //adding user name in users table
        ContentValues values = new ContentValues();
        values.put(KEY_NOMBRE, nombre);
        //values.put(KEY_COLOR, color);
        //values.put(KEY_CONT_TURNO, cont_turno);
        //db.insert(TABLE_EMPLEADO, null, values);
        long id = db.insertWithOnConflict(TABLE_EMPLEADO, null, values, SQLiteDatabase.CONFLICT_IGNORE);

        //adding user hobby in users_hobby table
        ContentValues valuesTurnos = new ContentValues();
        valuesTurnos.put(KEY_ID, id);
        valuesTurnos.put(KEY_TURNO, turno);
        //valuesTurnos.put(KEY_ESTADO_RESERVA, estado_reserva);
        db.insert(TABLE_TURNO, null, valuesTurnos);

        //adding user city in users_city table
        ContentValues valuesDia = new ContentValues();
        valuesDia.put(KEY_ID, id);
        valuesDia.put(KEY_DIA, dia);
        db.insert(TABLE_DIA, null, valuesDia);
    }

    public ArrayList<Empleados> getAllEmpleados() {
        ArrayList<Empleados> empleadosArrayList = new ArrayList<Empleados>();

        String selectQuery = "SELECT  * FROM " + TABLE_EMPLEADO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Empleados empleado = new Empleados();
                empleado.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                empleado.setNombre(c.getString(c.getColumnIndex(KEY_NOMBRE)));

                            //getting user hobby where id = id from user_hobby table
                            String selectTurnoQuery = "SELECT  * FROM " + TABLE_TURNO +" WHERE "+KEY_ID+" = "+ empleado.getId();
                            Log.d("oppp",selectTurnoQuery);
                            //SQLiteDatabase dbhobby = this.getReadableDatabase();
                            Cursor cTurno = db.rawQuery(selectTurnoQuery, null);

                                        if (cTurno.moveToFirst()) {
                                            do {
                                                empleado.setNombre_turno(cTurno.getString(cTurno.getColumnIndex(KEY_TURNO)));
                                            } while (cTurno.moveToNext());
                                        }

                            //getting user city where id = id from user_city table
                            String selectDiaQuery = "SELECT  * FROM " + TABLE_DIA+" WHERE "+KEY_ID+" = "+ empleado.getId();;
                            //SQLiteDatabase dbCity = this.getReadableDatabase();
                            Cursor cDia= db.rawQuery(selectDiaQuery, null);

                            if (cDia.moveToFirst()) {
                                do {
                                    empleado.setDia(cDia.getString(cDia.getColumnIndex(KEY_DIA)));
                                } while (cDia.moveToNext());
                            }

                    // adding to Students list
                   empleadosArrayList.add(empleado);
                } while (c.moveToNext());
         }
        return empleadosArrayList;
    }

    public void updateEmpleado(int id, String name, String hobby, String city) {
        SQLiteDatabase db = this.getWritableDatabase();

        // updating name in users table
        ContentValues values = new ContentValues();
        values.put(KEY_NOMBRE, name);
        db.update(TABLE_EMPLEADO, values, KEY_ID + " = ?", new String[]{String.valueOf(id)});

        // updating hobby in users_hobby table
        ContentValues valuesHobby = new ContentValues();
        valuesHobby.put(KEY_TURNO, hobby);
        db.update(TABLE_TURNO, valuesHobby, KEY_ID + " = ?", new String[]{String.valueOf(id)});

        // updating city in users_city table
        ContentValues valuesCity = new ContentValues();
        valuesCity.put(KEY_DIA, city);
        db.update(TABLE_DIA, valuesCity, KEY_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public void deleteEmpleado(int id) {

        // delete row in students table based on id
        SQLiteDatabase db = this.getWritableDatabase();

        //deleting from users table
        db.delete(TABLE_EMPLEADO, KEY_ID + " = ?",new String[]{String.valueOf(id)});

        //deleting from users_hobby table
        db.delete(TABLE_TURNO, KEY_ID + " = ?", new String[]{String.valueOf(id)});

        //deleting from users_city table
        db.delete(TABLE_DIA, KEY_ID + " = ?",new String[]{String.valueOf(id)});
    }

}

