package com.example.agendamj;

import java.io.Serializable;

public class Usuario implements Serializable {
    int id;

    String tipoDocumento;
    String nombre;
    String apellido;
    int edad;
    String email;
    int telefono;
    String nivelEducativo;
    String generoMusicalPreferido;
    String deporteFavorito;

    public Usuario(int id, String tipoDocumento, String nombre, String apellido, int edad, String email, int telefono, String nivelEducativo, String generoMusicalPreferido, String deporteFavorito) {
        this.id = id;
        this.tipoDocumento = tipoDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.email = email;
        this.telefono = telefono;
        this.nivelEducativo = nivelEducativo;
        this.generoMusicalPreferido = generoMusicalPreferido;
        this.deporteFavorito = deporteFavorito;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getNivelEducativo() {
        return nivelEducativo;
    }

    public void setNivelEducativo(String nivelEducativo) {
        this.nivelEducativo = nivelEducativo;
    }

    public String getGeneroMusicalPreferido() {
        return generoMusicalPreferido;
    }

    public void setGeneroMusicalPreferido(String generoMusicalPreferido) {
        this.generoMusicalPreferido = generoMusicalPreferido;
    }

    public String getDeporteFavorito() {
        return deporteFavorito;
    }

    public void setDeporteFavorito(String deporteFavorito) {
        this.deporteFavorito = deporteFavorito;
    }
}
