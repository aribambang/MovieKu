package com.movieku.app.Model;

public class Movie {

    private int id_movie;
    private String nama_movie,durasi_movie,sutradara_movie,produser_movie, tahun_movie,trailer_movie,bahasa_movie,sinopsis_movie,umur_movie,negara_movie,image_movie;


    public int getId_movie() {
        return id_movie;
    }

    public void setId_movie(int id_movie){
        this.id_movie = id_movie;
    }

    public String getNama_movie() {
        return nama_movie;
    }

    public void setNama_movie(String nama_movie){
        this.nama_movie = nama_movie;
    }

    public String getSutradara_movie() {
        return sutradara_movie;
    }

    public void setSutradara_movie(String sutradara_movie){
        this.sutradara_movie = sutradara_movie;
    }

    public String getProduser_movie() {
        return produser_movie;
    }

    public void setProduser_movie(String produser_movie) {
        this.produser_movie = produser_movie;
    }

    public String getBahasa_movie() {
        return bahasa_movie;
    }

    public void  setBahasa_movie(String bahasa_movie){
        this.bahasa_movie = bahasa_movie;
    }

    public String getDurasi_movie() {
        return durasi_movie;
    }

    public void setDurasi_movie(String durasi_movie){
        this.durasi_movie = durasi_movie;
    }


    public String getSinopsis_movie() {
        return sinopsis_movie;
    }

    public void setSinopsis_movie(String sinopsis_movie){
        this.sinopsis_movie = sinopsis_movie;
    }

    public String getTahun_movie() {
        return tahun_movie;
    }

    public void setTahun_movie(String tahun_movie){
        this.tahun_movie = tahun_movie;
    }

    public String getTrailer_movie() {
        return trailer_movie;
    }

    public void setTrailer_movie(String trailer_movie) {
        this.trailer_movie = trailer_movie;
    }

    public String getUmur_movie() {
        return umur_movie;
    }

    public void setUmur_movie(String umur_movie) {
        this.umur_movie = umur_movie;
    }

    public String getNegara_movie() {
        return negara_movie;
    }

    public void setNegara_movie(String negara_movie) {
        this.negara_movie = negara_movie;
    }

    public String getImage_movie() {
        return image_movie;
    }

    public void setImage_movie(String image_movie) {
        this.image_movie = image_movie;
    }
}
