/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.perpustakaanuns;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author user
 */
public class Main {
    String jenisId = null;
    String namaKolomId = null;
    String namaTabel = null;
    String semua = "*";
    String id = null;
    int nilaiVerifikasi=0;
    String[] hasilVerifikasi;
    
    Connection connection;

    public Main() {
        connection = DatabaseConnection.getInstance().getConnection();
    }
    
    public ArrayList<String> getColumnName(){
        ArrayList<String> data = new ArrayList();
        String query = String.format("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'perpustakaanuns' AND TABLE_NAME = '%s';",this.namaTabel);
        try {
            PreparedStatement ps = this.connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
           while (rs.next()) { 
               String columnName = rs.getString("COLUMN_NAME");
               data.add(columnName);
           }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
    
    public ArrayList<String> getTableName() {
        ArrayList<String> data = new ArrayList();
        String query = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'perpustakaanuns';";
        try {
            PreparedStatement ps = this.connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
           while (rs.next()) { 
               String columnName = rs.getString("TABLE_NAME");
               data.add(columnName);
           }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
    
    private String generateId() {
        try {
            String kondisi = String.format("ORDER BY CAST(SUBSTRING(%s FROM 2) AS UNSIGNED) ASC;", this.namaKolomId);
            String query = this.getReadQuery(this.namaKolomId, kondisi);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            String id_transaksi = null;
            int sebelum = 0;
            int sesudah = 0;
            while (resultSet.next()) {
                sesudah = Integer.parseInt(resultSet.getString(this.namaKolomId).substring(1));
                if(sesudah == sebelum + 1) {
                    sebelum = sesudah;
                } else {
                    id_transaksi = jenisId.toUpperCase() + String.valueOf(sebelum + 1);
                    break;
                }
            } if(id_transaksi == null) {
                id_transaksi = jenisId.toUpperCase() + String.valueOf(sesudah + 1);
            }
            return id_transaksi;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    protected void setGenerateId() {
        this.id = this.generateId();
    }
    
    public void setId(String id) {
        this.id = id.toUpperCase();
    }
    
    public boolean getVerifikasiId(String ID){
        if (this.getVerifikasi(namaKolomId, ID)){
            this.setId(ID);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean getVerifikasi(String kolomSelect, Object... value){
        Object[] newValue;
        if(kolomSelect == null ? this.semua != null : !kolomSelect.equals(this.semua)){    
            newValue = new Object[value.length+1];
            newValue[0] = kolomSelect;
            for(int i = 1; i < newValue.length; i++){
                newValue[i] = value[i-1];
            }
        } else{
            newValue = new Object[value.length];
            newValue = value;
        }
        int panjang = newValue.length/2;
        String[] kondisi = new String[panjang];
        Object[] nilai = new String[panjang];
        int index = 0;
        for(int i = 0; i < newValue.length; i+=2) {
            nilai[index] = newValue[i+1];
            kondisi[index] = this.getSingleCondition(String.valueOf(newValue[i]));
            if(newValue[i+1] == null) {
                kondisi[index] = this.getSingleCondition(String.valueOf(newValue[i]), "IS");
            }
            index += 1;
        }
        String gabungan = this.getBatchCondition("and", kondisi);
        String wherequery = this.getWhereClauseQuery(gabungan);
        String queryread = this.getReadQuery(kolomSelect, wherequery);
        String query = String.valueOf(queryread);
        String[] rs;
        PreparedStatement preparedStatement = this.getPreparedStatement(query, nilai);
        rs = executeR(preparedStatement);
        this.hasilVerifikasi = rs;
        return rs.length > 0;
    }
    
    public String[] getHasilVerfikasi(){
        return this.hasilVerifikasi;
    }
    
    protected String getSingleCondition(String namaKolom) {
        return String.format("%s = ?", namaKolom);
    }
    
    protected String getSingleCondition(String namaKolom, String operator) {
        return String.format("%s %s ?", namaKolom, operator.toUpperCase());
    }
    
    protected String getBatchCondition(String operator, String... kondisi) {
        String op = "";
        switch (operator.toLowerCase()) {
            case "and" -> op = "AND";
            case "or" -> op = "OR";
            case "update" -> op =",";
            default -> throw new IllegalArgumentException("Unsupported operator: " + operator);
        }
        String hasil = String.join(String.format(" %s ", op), kondisi);
        return hasil;
    }
    
    protected String getWhereClauseQuery(String query){
        return String.format("WHERE %s", query);
    }
    
    private String[][] getKolomValueForCreateQuery() {
        ArrayList<String> namaKolomList = this.getColumnName();
        String[] namaKolom = namaKolomList.toArray(String[]::new);
        String[] valueKolom = new String[namaKolom.length];
        Arrays.fill(valueKolom, "?");
        String[][] resultArray = {namaKolom, valueKolom};
        return resultArray;
    }
    
    protected String getReadQuery(String kolomSelect, String conditions) {
        return String.format("SELECT %s FROM %s %s", kolomSelect, this.namaTabel, conditions);
    }
    
    protected String getUpdateQuery(String kolomSet, String conditions) {
        return String.format("UPDATE %s SET %s %s", this.namaTabel, kolomSet, conditions);
    }

    protected String getCreateQuery() {
        String[][] kolom = this.getKolomValueForCreateQuery();
        String separator = ", ";
        return String.format("INSERT INTO %s (%s) VALUES (%s)", this.namaTabel, String.join(separator, kolom[0]), String.join(separator, kolom[1]));
    }
    
    protected String getDeleteQuery(String conditions) {
        return String.format("DELETE FROM %s %s", this.namaTabel, conditions);
    }
    
    protected PreparedStatement getPreparedStatement(String query, Object... parameters){
        try{
            PreparedStatement ps = this.connection.prepareStatement(query);
            int sumHadHandle = 0;
            int indexThatCantHandle = -1;
            for (int i = 0; i < parameters.length; i++) {
                if (parameters[i] instanceof String) {
                    ps.setString(i + 1, (String) parameters[i]);
                    sumHadHandle += 1;
                } 
                else if (parameters[i] instanceof Integer) {
                    ps.setInt(i + 1, (Integer) parameters[i]);
                    sumHadHandle += 1;
                } 
                else if(parameters[i] instanceof Timestamp) {
                    ps.setTimestamp(i+1, (Timestamp) parameters[i]);
                    sumHadHandle += 1;
                } if (parameters[i] == null || parameters[i].equals("null")) {
                    ps.setString(i+1, null);
                    sumHadHandle += 1;
                } else if (parameters[i] instanceof String[]) {
                    indexThatCantHandle = i;
                    break;
                }
                else {
                    System.out.println("Parameter yang dapat diterima adalah String, Integer, dan Timestamp");
                }
            }
            if(indexThatCantHandle != -1){
                String[] stringArray = (String[]) parameters[indexThatCantHandle];
                for (int j = indexThatCantHandle; j < stringArray.length; j++) {
                    ps.setString(j + 1, stringArray[j]);
                    indexThatCantHandle = j;
                    sumHadHandle += 1;
                }
            }
            if(parameters[indexThatCantHandle+1] != null){
                for (int i = indexThatCantHandle+1; i < parameters.length-sumHadHandle+1; i++) {
                    if (parameters[i] instanceof String) {
                        ps.setString(i + 1, (String) parameters[i]);
                    } 
                    else if (parameters[i] instanceof Integer) {
                        ps.setInt(i + 1, (Integer) parameters[i]);
                    } else {
                        System.out.println("Setelah list ada kesalahan");
                    }  
                }
            }
            return ps;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }   
    }
    
    public String[] readAll(){
        String query = this.getReadQuery(this.semua, "");
        try{
            PreparedStatement ps = this.connection.prepareStatement(query);
            String[] hasil = this.executeR(ps);
            return hasil;
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public String[] readWithMultipleCondition(String kondisi){
        String where = this.getWhereClauseQuery(kondisi);
        String query = this.getReadQuery(this.semua, where);     
        try{
            PreparedStatement ps = this.connection.prepareStatement(query);
            String[] hasil = this.executeR(ps);
            return hasil;
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }        
    }
    
    protected boolean executeCUD(PreparedStatement ps) {
        try {
            System.out.println(ps);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    protected String[] executeR(PreparedStatement ps){
        try {
            List<String> resultList = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnValue = rs.getString(i);
                    System.out.println(ps);
                    resultList.add(columnValue);
                }
            }
            String[] resultArray = resultList.toArray(String[]::new);
            return resultArray;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public void setUlangNilaiVerifikasi(){
        this.nilaiVerifikasi = 0;
    }
    
    public int getNilaiVerifikasi(){
        return this.nilaiVerifikasi;
    }
    
    public void closeConnection () {
        DatabaseConnection.getInstance().closeConnection();
    }
}
