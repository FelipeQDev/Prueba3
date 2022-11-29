package com.example.prueba3;

import com.google.firebase.firestore.DocumentReference;

public class Armas {

    public String nombre;
    public String elemento;
    public DocumentReference tipo_arma;
    public int ataque;
    public int nivel_requerido;

    public Armas() {}

    public Armas(String nombre, String elemento, DocumentReference tipo_arma, int ataque, int nivel_requerido) {
        this.nombre = nombre;
        this.elemento = elemento;
        this.tipo_arma = tipo_arma;
        this.ataque = ataque;
        this.nivel_requerido = nivel_requerido;
    }
}
