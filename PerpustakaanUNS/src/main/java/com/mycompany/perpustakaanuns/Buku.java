/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.perpustakaanuns;

import java.sql.PreparedStatement;
import java.util.Arrays;

/**
 *
 * @author user
 */
public class Buku extends Main implements CRUD{
    boolean hasilCUD;
    private final int defaultBanyakBukuDipinjam = 0;
    String kolomSelect;
    
    public Buku() {
        this.namaTabel = "buku";
        this.jenisId = "B";
        this.namaKolomId = "id_buku";
    }
        
    @Override
    public boolean create(Object... value) {
        String judul = String.valueOf(value[1]);
        String pengarang = String.valueOf(value[3]);
        String penerbit = String.valueOf(value[5]);
        int tahun_terbit = Integer.parseInt(String.valueOf(value[7]));
        int jumlah = Integer.parseInt(String.valueOf(value[9]));
        Object[] newData = excludeTwoLastElement(value);
        if (this.getVerifikasi(this.semua, newData)) {
            System.out.println("Data buku sudah terdaftar.");
            return false;
        } else {
            this.setGenerateId();
            String query = this.getCreateQuery();
            PreparedStatement ps = this.getPreparedStatement(query, this.id,judul,pengarang, penerbit, tahun_terbit, jumlah);
            System.out.println(ps);
            this.hasilCUD = this.executeCUD(ps);
            System.out.println("Berhasil mendaftarkan buku baru");
            return this.hasilCUD;
        }
    }

    @Override
    public String[] read(Object... value) {
        String[] rs;
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
        String query = this.getReadQuery(this.semua, wherequery);
        if(this.kolomSelect != null){
            query = this.getReadQuery(this.kolomSelect, wherequery);
        }
        PreparedStatement preparedStatement = this.getPreparedStatement(query, nilai);
        System.out.println(preparedStatement);
        rs = executeR(preparedStatement);
        return rs;
    }

    @Override
    public boolean update(String ID, Object... value) {
        this.setId(ID);
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
        String kondisi1 = this.getSingleCondition(namaKolomId);
        String where = this.getWhereClauseQuery(kondisi1);
        String query = this.getUpdateQuery(gabungan, where);
        PreparedStatement ps = this.getPreparedStatement(query, nilai, ID);
        this.hasilCUD = this.executeCUD(ps);
        return this.hasilCUD;
    }

    @Override
    public boolean delete(String ID) {
        String kondisi = this.getSingleCondition(namaKolomId);
        String where = this.getWhereClauseQuery(kondisi);
        String query = this.getDeleteQuery(where);
        PreparedStatement ps = this.getPreparedStatement(query, ID);
        this.hasilCUD = this.executeCUD(ps);
        System.out.println("Berhasil menghapus data buku dengan id="+ID);
        return this.hasilCUD;
    }    
    
    public boolean getUpdateJumlahBuku(String jumlah){
        return this.update(this.id, "jumlah", jumlah);
    }
    
    public String[] getBukuBaru(){
        return this.read(this.namaKolomId, this.id);
    }
    
    public String getJumlahBuku(String idn){
        this.kolomSelect = "jumlah";
        return this.read(this.namaKolomId, idn)[0];
    }
    
    public static Object[] excludeTwoLastElement(Object... data) {
        if (data.length <= 1) {
            return new Object[0];
        }
        return Arrays.copyOfRange(data, 0, data.length - 2);
    }

}
