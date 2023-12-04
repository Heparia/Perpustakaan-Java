/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.perpustakaanuns;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance = null;
    private Connection connection;

    // Informasi koneksi database
    private String jdbcURL = "jdbc:mysql://localhost:3306/perpustakaanuns";
    private String username = "root";
    private String password = "";

    private DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Koneksi berhasil dibuka");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Koneksi berhasil ditutup");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


