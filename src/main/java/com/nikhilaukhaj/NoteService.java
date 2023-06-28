package com.nikhilaukhaj;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.nikhilaukhaj.Config.CONNECTION_STRING;

public class NoteService implements NotesInterface{
    @Override
    public boolean saveNote(Note note) {
        String SQL_INSERT = "INSERT INTO NOTE (TITLE, DESCRIPTION, USERID) VALUES (?,?,?)";
        try{
            Connection conn = Config.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT);

            preparedStatement.setString(1, note.getTitle());
            preparedStatement.setString(2, note.getDesc());
            preparedStatement.setLong(3, note.getUserId());

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
    public List<Note> viewNoteForUser(Long userId) {
        List<Note> notes = new ArrayList<>();

        String SQL_SELECT = "SELECT * FROM NOTE WHERE userId = ?";
        try{
            Connection conn = Config.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);

            preparedStatement.setLong(1, userId);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                Long id = rs.getLong("ID");
                String title = rs.getString("title");
                String desc = rs.getString("description");

                Note note = new Note(id,userId, title,desc);
                notes.add(note);
            }

        }catch(SQLException e){
            System.out.println("MYSQL EXCEPTION : " + e.getMessage());
        }catch(Exception e){
            System.out.println("Exception : " + e.getMessage());
        }

        return notes;
    }
}
