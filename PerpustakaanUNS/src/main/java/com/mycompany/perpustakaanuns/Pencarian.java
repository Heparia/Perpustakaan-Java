/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.perpustakaanuns;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Pencarian extends Main{
    
    ArrayList<String> columns = new ArrayList();
    String defaultTabel = "buku";
    String whereQuery;
    
    public boolean setNamaSemuaKolom(ArrayList<String> columns) {
        try{
            this.columns = columns;
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public ArrayList<String> getNamaSemuaKolom() {
        return this.getColumnName();
    }
    
    public String[] getHasilPencarian(String namaTabel, String namaKolom, String value) {
        this.namaTabel = namaTabel;
        System.out.println(value);
        String kondisi = this.getSingleCondition(namaKolom);
        String where = this.getWhereClauseQuery(kondisi);
        String query = this.getReadQuery(this.semua, where);
        this.whereQuery = where;
        String[] hasil = this.executeR(this.getPreparedStatement(query, value));
        return hasil;
    }
    
    public boolean updateTabel(String namaTabel, String namaKolomId, String namaKolom, String value, String id){
        this.setId(id);
        this.namaTabel = namaTabel;
        this.namaKolomId = namaKolomId;
        String kondisi = this.getSingleCondition(this.namaKolomId);
        String where = this.getWhereClauseQuery(kondisi);
        String setdata = this.getSingleCondition(namaKolom);
        String query = this.getUpdateQuery(setdata, where);
        this.whereQuery = where;
        boolean hasil = this.executeCUD(this.getPreparedStatement(query, value, this.id));
        return hasil;
    }
    
    public boolean deleteColumn(String namaTabel, String namaKolomId, String id) {
        this.namaKolomId = namaKolomId;
        this.namaTabel = namaTabel;
        this.setId(id);
        String kondisi = this.getSingleCondition(this.namaKolomId);
        String where = this.getWhereClauseQuery(kondisi);
        String query = this.getDeleteQuery(where);
        this.whereQuery = where;
        boolean hasil = this.executeCUD(this.getPreparedStatement(query, this.id));
        return hasil;
    }
    
    public String[] getRefreshWithWhereQuery(String namaTabel, String namaKolom, String value){
        String kondisi = this.getSingleCondition(namaKolom);
        String where = this.getWhereClauseQuery(kondisi);
        String query = this.getReadQuery(this.semua, where);
        this.whereQuery = where;
        String[] hasil = this.executeR(this.getPreparedStatement(query, value));
        return hasil;
    }
}
