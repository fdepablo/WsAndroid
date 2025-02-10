package com.example.a13_firebase_01;

import java.io.Serializable;

public class Saludo implements Serializable {
    private String texto;

    public Saludo(){}

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
