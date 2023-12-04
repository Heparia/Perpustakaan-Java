/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.perpustakaanuns;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author user
 */
public class Pengembalian extends Transaksi{
    int totalDenda;
    Timestamp tanggalMax;
    Timestamp tanggalKembali;
    private int denda = 1000;
    String[] kolomSet = {"tanggal_pengembalian", "denda"};
    String kolomSelect = "id_transaksi, id_buku, id_anggota, tanggal_peminjaman, tanggal_max_pengembalian";
    String[] kolomsSelect = {"id_transaksi", "id_buku", "id_anggota", "tanggal_peminjaman", "tanggal_max_pengembalian"};
    
    protected void setDenda(int denda) {
        this.denda = denda;
    }    
    
    public Timestamp getTanggalPengembalian() {
        this.setTimestamp();
        this.tanggalKembali = this.tanggalAwal;
        System.out.println(this.tanggalKembali);
        return this.tanggalKembali;
    }
    
    public void setTanggalMaximalPengembalian() {
        String kondisi = this.getSingleCondition(this.namaKolomId);
        String wherequery = this.getWhereClauseQuery(kondisi);
        String queryread = this.getReadQuery("tanggal_max_pengembalian", wherequery);
        this.query = queryread;
        String tanggal = this.read(this.id)[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(tanggal, formatter);
        this.tanggalMax = Timestamp.valueOf(dateTime);
    }
    
    public int getTotalDenda() {
        this.getTanggalPengembalian();
        this.setTanggalMaximalPengembalian();
        long selisihMilidetik = this.tanggalKembali.getTime() - this.tanggalMax.getTime();
        long selisihHari = selisihMilidetik / (1000 * 60 * 60 * 24);
        int total = (int) (selisihHari * this.denda);
        if (total < 0) {
            total = 0;
        }
        this.totalDenda = total;
        System.out.println(totalDenda);
        return this.totalDenda;
    }
    
    public boolean buatPengembalian(String ID){
        if(this.getVerifikasi("tanggal_pengembalian", null, this.namaKolomId, ID)){
            this.setId(ID);
            this.getTotalDenda();
            String[] kolomDiset = new String[kolomSet.length];
            for(int i = 0; i < kolomDiset.length; i++) {
                kolomDiset[i] = this.getSingleCondition(kolomSet[i]);
            }
            String batchSet = this.getBatchCondition("update", kolomDiset);
            String kondisi = this.getSingleCondition(this.namaKolomId);
            String where = this.getWhereClauseQuery(kondisi);
            this.kolomSetUpdate = batchSet;
            System.out.println("Buku berhasil dikembalikan");
            this.id = ID;
            return this.update(where, this.tanggalKembali, this.totalDenda, this.id);
        } else{
            System.out.println("Buku sudah dikembalikan oleh peminjam");
            return false;
        }
    }
    
    public String[] bacaPengembalianTerlambat(){
        if(this.totalDenda >0){
            String kondisi = this.getSingleCondition(namaKolomId);
            String where = this.getWhereClauseQuery(kondisi);
            this.query = this.getReadQuery(this.semua, where);
            return this.read(this.id);
        } else {
            return null;
        }
    }
    
    public String[] cariPengembalian(String namaKolom, Object... value){
        ArrayList<String> kumpulanIdList = new ArrayList<>();
        for (Object value1 : value) {
            String kondisi = this.getSingleCondition(namaKolom);
            String where = this.getWhereClauseQuery(kondisi);
            this.query = this.getReadQuery(this.namaKolomId, where);
            String[] hasil = this.read(String.valueOf(query), value1);
            System.out.println(hasil.length);
            kumpulanIdList.addAll(Arrays.asList(hasil));
        }
        String[] kumpulanId = kumpulanIdList.toArray(String[]::new);
        System.out.println("mulai");
        for (String idn: kumpulanId){
            System.out.println(idn);
        } System.out.println("berakhir");
        return kumpulanId;
    }
    
    public String[] cariPengembalianAll(){
        String kondisi = this.getSingleCondition("tanggal_pengembalian", "IS");
        String where = this.getWhereClauseQuery(kondisi);
        this.query = this.getReadQuery(kolomSelect, where);
        return this.read("null");
    }
    
    public String[] cariPengembalianInput(String namaKolom, String value){
        String kondisi1 = this.getSingleCondition(namaKolom);
        String kondisi2 = this.getSingleCondition("tanggal_pengembalian", "IS");
        String gabungan = this.getBatchCondition("and", kondisi1, kondisi2);
        String where = this.getWhereClauseQuery(gabungan);
        this.query = this.getReadQuery(kolomSelect, where);
        return this.read(value, "null");
    }
}
