/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.perpustakaanuns;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author user
 */
public class Transaksi extends Main implements CRUD{
    Timestamp tanggalAwal;
    Timestamp tanggalAkhir;
    boolean hasilCUD;
    String query;
    String kolomSetUpdate;
    
    public Transaksi() {
        this.namaTabel = "transaksi";
        this.jenisId = "T";
        this.namaKolomId = "id_transaksi";
    }

    @Override
    public boolean create(Object... value) {
        query = this.getCreateQuery();
        PreparedStatement ps = this.getPreparedStatement(query, value);
        System.out.println(ps);
        this.hasilCUD = this.executeCUD(ps);
        return this.hasilCUD;
    }

    @Override
    public String[] read(Object... value) {
        String[] rs;
        PreparedStatement preparedStatement = this.getPreparedStatement(query, value);
        System.out.println(preparedStatement);
        rs = executeR(preparedStatement);
        return rs;
    }

    @Override
    public boolean update(String kondisi, Object... value) {
        query = this.getUpdateQuery(this.kolomSetUpdate, kondisi);
        PreparedStatement ps = this.getPreparedStatement(query, value);
        this.hasilCUD = this.executeCUD(ps);
        return this.hasilCUD;
    }

    @Override
    public boolean delete(String id) {
        String kondisi = this.getSingleCondition(namaKolomId);
        String where = this.getWhereClauseQuery(kondisi);
        query = this.getDeleteQuery(where);
        PreparedStatement ps = this.getPreparedStatement(query, id);
        this.hasilCUD = this.executeCUD(ps);
        System.out.println("Berhasil menghapus data Transaksi dengan id="+id);
        return this.hasilCUD;
    }
    
    protected void setTimestamp() {
        Date tanggal = new Date();
        this.tanggalAwal = new Timestamp(tanggal.getTime());
    }
    
    protected void setTimestamp(int batas){
        Date tanggal = new Date(this.tanggalAwal.getTime() + batas);
        this.tanggalAkhir = new Timestamp(tanggal.getTime());
    }
    
    public String[] getIdBukuIdAnggota(String id){
        this.setId(id);
        String kondisi = this.getSingleCondition(this.namaKolomId);
        String where = this.getWhereClauseQuery(kondisi);
        this.query = this.getReadQuery("id_buku, id_anggota", where);
        return this.read(this.id);
    }
}
