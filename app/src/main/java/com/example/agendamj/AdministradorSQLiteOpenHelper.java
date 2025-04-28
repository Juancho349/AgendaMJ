package com.example.agendamj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AdministradorSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdministradorSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE usuario (" +
                        "id INTEGER PRIMARY KEY, " +
                        "tipoDocumento TEXT, " +
                        "nombre TEXT, " +
                        "apellido TEXT, " +
                        "edad INTEGER, " +
                        "email TEXT, " +
                        "telefono INTEGER, " +
                        "nivelEducativo TEXT, " +
                        "generoMusical TEXT, " +
                        "deporteFavorito TEXT)"
        );;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void crear(Usuario usuario){
        SQLiteDatabase bd = this.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("id",usuario.getId());
        registro.put("tipoDocumento", usuario.getTipoDocumento());
        registro.put("nombre", usuario.getNombre());
        registro.put("apellido", usuario.getApellido());
        registro.put("edad", usuario.getEdad());
        registro.put("email", usuario.getEmail());
        registro.put("telefono", usuario.getTelefono());
        registro.put("nivelEducativo", usuario.getNivelEducativo());
        registro.put("generoMusical", usuario.getGeneroMusicalPreferido());
        registro.put("deporteFavorito", usuario.getDeporteFavorito());
        bd.insert("usuario",null, registro);
        bd.close();

    }

    public void actualizar (Usuario usuario){
        SQLiteDatabase bd = this.getWritableDatabase();
        ContentValues registro = new ContentValues();
        int id = usuario.getId();
        registro.put("id",usuario.getId());
        registro.put("tipoDocumento", usuario.getTipoDocumento());
        registro.put("nombre", usuario.getNombre());
        registro.put("apellido", usuario.getApellido());
        registro.put("edad", usuario.getEdad());
        registro.put("email", usuario.getEmail());
        registro.put("telefono", usuario.getTelefono());
        registro.put("nivelEducativo", usuario.getNivelEducativo());
        registro.put("generoMusical", usuario.getGeneroMusicalPreferido());
        registro.put("deporteFavorito", usuario.getDeporteFavorito());
        int respuesta = bd.update("usuario", registro, "id="+id, null);
        bd.close();
    }

    public ArrayList<Usuario> listadoUsuarios(){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        SQLiteDatabase bd = this.getWritableDatabase();
        Cursor fila = bd.rawQuery("select * from usuario", null);
        if (fila.moveToFirst()){
            do {
                Usuario usuario = new Usuario(
                        fila.getInt(0),
                        fila.getString(1),
                        fila.getString(2),
                        fila.getString(3),
                        fila.getInt(4),
                        fila.getString(5),
                        fila.getInt(6),
                        fila.getString(7),
                        fila.getString(8),
                        fila.getString(9)
                );
                usuarios.add(usuario);
            }while (fila.moveToNext());

        }
        fila.close();
        bd.close();
        return usuarios;
    }

}
