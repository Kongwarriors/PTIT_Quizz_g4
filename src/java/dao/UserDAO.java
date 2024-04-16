/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
        String querySelectByUsername = "SELECT COUNT(*) AS count FROM Users WHERE username = ?";
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
    
    public List<User> getAllUser(){
        List<User> list = new ArrayList<>();
        String sql = "select * from users";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                User c = new User(rs.getInt("id"), 
                        rs.getString("username"), 
                            rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("email")
                );
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public void addNewUser(User user) {
        String sql = "INSERT INTO `quizz_system`.`users`\n"
                + "(`id`,\n"
                + "`username`,\n"
                + "`password`,\n"
                + "`role`,\n"
                + "`email`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?);";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, user.getId());
            st.setString(2, user.getUsername());
            st.setString(3, user.getPassword());
            st.setString(4, user.getRole());
            st.setString(5, user.getEmail());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public User getUserById(int userId) {
        String sql = "select * from users where id =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                User c = new User(rs.getInt("id"), 
                        rs.getString("username"), 
                            rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("email")
                );
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public void deleteUser(int id){
        String sql = "DELETE FROM `quizz_system`.`users`\n"
                + "WHERE id = ?;";
//        int isDelete = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
//            isDelete = st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
//        return isDelete;
    }
    
    public void update(User c){
        String sql = "UPDATE `quizz_system`.`users`\n"
                + "SET\n"
                + "`username` =  ?,\n"
                + "`password` = ?,\n"
                + "`role` = ?,\n"
                + "`email` = ?\n"
                + "WHERE `id` = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getUsername()); // Chỉ số bắt đầu từ 1
            st.setString(2, c.getPassword());
            st.setString(3, c.getRole());
            st.setString(4, c.getEmail());
            st.setInt(5, c.getId()); // Chỉ số bắt đầu từ 1
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }
}
