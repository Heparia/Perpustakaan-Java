/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.perpustakaanuns;

import java.sql.PreparedStatement;

/**
 *
 * @author user
 */
public class Anggota extends Main implements CRUD{
    boolean hasilCUD;
    private final int defaultBanyakBukuDipinjam = 0;
    String kolomSelect;
    int batasPinjamBukuAnggota = 3;
    
    public Anggota() {
        this.namaTabel = "anggota";
        this.jenisId = "A";
        this.namaKolomId = "id_anggota";
    }
        
    @Override
    public boolean create(Object... value) {
        String nama = String.valueOf(value[1]);
        String nim = String.valueOf(value[3]);
        String prodi = String.valueOf(value[5]);
        String email = String.valueOf(value[7]);
        if (this.getVerifikasi(this.semua, value)) {
            System.out.println("Data anggota sudah terdaftar.");
            return false;
        } else {
            this.setGenerateId();
            String query = this.getCreateQuery();
            PreparedStatement ps = this.getPreparedStatement(query, this.id, nama, nim, prodi, email, this.defaultBanyakBukuDipinjam);
            System.out.println(ps);
            this.hasilCUD = this.executeCUD(ps);
            System.out.println("Berhasil mendaftarkan anggota baru");
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
        System.out.println(ID);
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
        System.out.println("Berhasil menghapus data Anggota dengan id="+ID);
        return this.hasilCUD;
    }
    
    public String[] getAnggotaBaru(){
        return this.read(this.namaKolomId, this.id);
    }
    
    public String getBanyakBukuDipinjam(String namaKolomid, String idn){
        this.kolomSelect = "banyak_buku_dipinjam";
        return this.read(namaKolomid, this.id)[0];
    }
}
