package com.movieku.app.Model;

public class Movie {

    private int id_movie;
    private String nama_movie,durasi_movie,direktor_movie,kategori_movie, tahun_movie,trailer_movie,bahasa_movie,sinopsis_movie,umur_movie,negara_movie;

    public Movie(int id_movie, String nama_movie, String durasi_movie,String direktur_movie, String kategori_movie,String tahun_movie, String trailer_movie, String bahasa_movie, String sinopsis_movie, String umur_movie, String negara_movie) {
        this.id_movie = id_movie;
        this.nama_movie = nama_movie;
        this.direktor_movie = direktur_movie;
        this.durasi_movie = durasi_movie;
        this.kategori_movie = kategori_movie;
        this.trailer_movie = trailer_movie;
        this.tahun_movie = tahun_movie;
        this.bahasa_movie = bahasa_movie;
        this.sinopsis_movie = sinopsis_movie;
        this.umur_movie = umur_movie;
        this.negara_movie = negara_movie;

    }

    public int getId_movie() {
        return id_movie;
    }

    public String getNama_movie() {
        return nama_movie;
    }

    public String getDirektor_movie() {
        return direktor_movie;
    }

    public String getBahasa_movie() {
        return bahasa_movie;
    }

    public String getDurasi_movie() {
        return durasi_movie;
    }

    public String getKategori_movie() {
        return kategori_movie;
    }

    public String getSinopsis_movie() {
        return sinopsis_movie;
    }

    public String getTahun_movie() {
        return tahun_movie;
    }

    public String getTrailer_movie() {
        return trailer_movie;
    }

    public String getUmur_movie() {
        return umur_movie;
    }

    public String getNegara_movie() {
        return negara_movie;
    }

}
