package com.movieku.app.Model;

public class Aktor {
    int id_aktor;
    String nama_aktor;

    public Aktor(int id_aktor, String nama_aktor){
        this.id_aktor = id_aktor;
        this.nama_aktor = nama_aktor;
    }

    public int getId_aktor() {
        return id_aktor;
    }

    public String getNama_aktor() {
        return nama_aktor;
    }

}
