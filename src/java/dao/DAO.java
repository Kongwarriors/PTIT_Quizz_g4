/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
public class DAO {
    private final String dbName = "quizz_system";
    private final String userID = "root";
    private final String password = "Qu@n13122001";
    public static Connection connection;
    public DAO(){
        if(connection == null){
            String dbUrl = "jdbc:mysql://localhost:3306/" + dbName + "?autoReconnect=true&useSSL=false";
            String dbClass = "com.mysql.cj.jdbc.Driver";
            try{
                Class.forName(dbClass);
		connection = DriverManager.getConnection(dbUrl, userID, password);
                System.out.println("Connected to MySQL successfully.");
            }catch(ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


