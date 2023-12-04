/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.perpustakaanuns;

/**
 *
 * @author hp
 */
interface CRUD {
    boolean create(Object... value);
    String[] read(Object... value);
    boolean update(String kondisi, Object... value);
    boolean delete(String id);
}
