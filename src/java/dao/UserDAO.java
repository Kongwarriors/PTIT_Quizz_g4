/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import models.User;

/**
 *
 * @author PC
 */
public class UserDAO extends DAO {

    public UserDAO(){
        super();
    }

    public boolean checkLogin(User u) {
        boolean result = false;
        String query = "SELECT username, role FROM Users WHERE username = ? AND password = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {       
                u.setRole(rs.getString("role"));
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public int saveNewUser(User u){
        boolean result = false;
        String querySelectByEmail = "SELECT COUNT(*) AS count FROM Users WHERE email = ?" ;
        String querySelectByUsername = "SELECT COUNT(*) AS count FROM Users WHERE username = ? AND email = ? ";
        String queryInsert = "INSERT INTO Users (email, username, password, role) values (?, ?, ?, ?)";
        try {
            //Get all user in database
            PreparedStatement selectByEmailPs = connection.prepareCall(querySelectByEmail);
            selectByEmailPs.setString(1, u.getEmail());
            ResultSet resultSet = selectByEmailPs.executeQuery();
            if(resultSet.next()){
                int count = resultSet.getInt("count");
                if(count > 0){
                    return 0; //Email is exist on system
                }
  
            }
            PreparedStatement selectByUsernamePs = connection.prepareCall(querySelectByUsername);
            selectByUsernamePs.setString(1, u.getUsername());
            selectByUsernamePs.setString(2, u.getEmail());
            resultSet = selectByUsernamePs.executeQuery();
            if(resultSet.next()){
                int count = resultSet.getInt("count");
                if(count>0){
                        return 1; //Username is exist on system
                }
            }                       
            PreparedStatement ps = connection.prepareCall(queryInsert);
            ps.setString(1, u.getEmail());
            ps.setString(2, u.getUsername());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getRole());
            int number_row = ps.executeUpdate();
            if (number_row > 0) {
                result = true;
                System.out.println("Dữ liệu đã được chèn thành công!");
                return 2; //Successed
            } else {
                System.out.println("Không có dữ liệu nào được chèn.");
                return 3; //Failed
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi thực hiện câu lệnh INSERT: " + e.getMessage());
            return 3; //Failed
        }
    }
}
