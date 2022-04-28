/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hedi & Aziz
 */
public class DataSource {
    
    private String URL="jdbc:mysql://localhost:3306/campnet";
    private String login="root";
    private String pwd="";
    Connection cnx;

    public static DataSource instance;
    private DataSource() {
        try {
            cnx=DriverManager.getConnection(URL,login,pwd);
            System.out.println("Connection established");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Connection getCnx() {
        return cnx;
    }
    
    public static DataSource getInstance() {
        if (instance == null)
            instance=new DataSource();
        return instance;
    }
    
    
    
    
    
}
