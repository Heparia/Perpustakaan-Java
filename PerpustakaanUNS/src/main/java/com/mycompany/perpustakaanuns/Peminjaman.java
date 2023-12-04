/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.perpustakaanuns;

/**
 *
 * @author user
 */
public class Peminjaman extends Transaksi{
    
    int maxHari = 5;
    private int batas;
    private String tanggal_kembali;
    private final int denda = 0;
    
    public Peminjaman() {
        this.setBatas();
    }
    
    private void setBatas() {
        this.batas = this.maxHari* 24 * 60 * 60 * 1000;
    }
    
    private void setTanggalPeminjaman() {
        this.setTimestamp();
    } 
    
    private void setTanggalMaximalPeminjaman() {
        this.setTimestamp(this.batas);
    }
    
    public void bacaPeminjaman(Object... value)  {
        int panjang = value.length/2;
        String[] kondisi = new String[panjang];
        Object[] nilai = new String[panjang];
        int index = 0;
        for(int i = 0; i < value.length; i+=2) {
            nilai[index] = value[i+1];
            kondisi[index] = this.getSingleCondition(String.valueOf(value[i]));
            index += 1;
        }
        String gabungan = this.getBatchCondition("and", kondisi);
        String wherequery = this.getWhereClauseQuery(gabungan);
        String queryread = this.getReadQuery(this.semua, wherequery);
        this.query = String.valueOf(queryread);
        this.read(nilai);
    }
    
    public String[] bacaHasilBuatPeminjaman(){
        String kondisi = this.getSingleCondition(namaKolomId);
        String where = this.getWhereClauseQuery(kondisi);
        this.query = this.getReadQuery(this.semua, where);
        return this.read(this.id);
    }

    public boolean buatPeminjaman(String idBuku, String idPeminjam){
        this.setGenerateId();
        System.out.println(this.id);
        this.setTanggalPeminjaman();
        this.setTanggalMaximalPeminjaman();
        return this.create(this.id, idBuku, idPeminjam,this.tanggalAwal, this.tanggalAkhir, this.tanggal_kembali, this.denda);
    }
}
