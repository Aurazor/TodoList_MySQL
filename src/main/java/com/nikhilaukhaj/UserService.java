package com.nikhilaukhaj;

import java.sql.*;

import static com.nikhilaukhaj.Config.CONNECTION_STRING;

public class UserService implements UserInterface{

    @Override
    public boolean register(String username, String password) {
        String SQL_INSERT = "INSERT INTO USER (USERNAME,PASSWORD) VALUES (?,?)";
        try{
            Connection conn = Config.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            int row = preparedStatement.executeUpdate();
            return row == 1;

        }catch (SQLException e){
            System.out.println("MYSQL EXCEPTION : " + e.getMessage());
        }catch (Exception e){
            System.out.println("Exception : " + e.getMessage());
        }
        return false;
    }

    @Override
    public Long login(String username, String password) {
        String SQL_SELECT = "SELECT * FROM USER WHERE username = ? AND password = ?";
        try{
            Connection conn = Config.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
               Long id = rs.getLong("ID");
                return id;
            }

        }catch(SQLException e){
            System.out.println("MYSQL EXCEPTION : " + e.getMessage());
        }catch(Exception e){
            System.out.println("Exception : " + e.getMessage());
        }
        return 0L;
    }
}
