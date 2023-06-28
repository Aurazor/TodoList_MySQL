package com.nikhilaukhaj;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Main {
   static UserService userService = new UserService();
   static NoteService noteService = new NoteService();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Long userId = 0L;
        String choice;

        //option to login or register
        //if register then display menu for login
        //if login then create new note
        //enter title and enter desc
        //display all notes and display option to create note


        boolean userRegistered = false;

        while(!userRegistered) {
            System.out.println("Enter 1 - Login, 2 - Register");
            String username = "";
            String password = "";

            choice = sc.nextLine();
            switch(choice){
                case "1":
                    System.out.println("Enter username: ");
                    username = sc.nextLine();
                    System.out.println("Enter password: ");
                    password = sc .nextLine();
                    userId =  userService.login(username, password);
                    if(userId == 0L){
                        System.out.println("Invalid Login");
                        continue;
                    }else{
                        userRegistered = true;
                        System.out.println("Successfully Login");

                        break;
                    }

                case "2":
                    System.out.println("Enter username: ");
                    username = sc.nextLine();
                    System.out.println("Enter password: ");
                    password = sc .nextLine();
                    if(userService.register(username, password)){
                        System.out.println("Registration Successful");
                    }else{
                        System.out.println("Registration Failed!");
                    }
                    break;

                default:
                    System.out.println("Invalid choice!!");

            }

        }

        boolean exitApp = false;
        while(!exitApp){
            displayNotes(userId);
            System.out.println("Enter \n1 -> create note, \n2 -> exit");
            choice = sc.nextLine();

            switch(choice){
                case "1":
                    //create new note
                    System.out.println("Enter title for note: ");
                    String title = sc.nextLine();
                    System.out.println("Enter description for note: ");
                    String desc = sc.nextLine();
                    Note note = new Note();
                    note.setUserId(userId);
                    note.setTitle(title);
                    note.setDesc(desc);
                    boolean success = noteService.saveNote(note);
                    System.out.println(success ? "Note saved successfully":"Note did not save");
                    break;
                case "2":
                    exitApp = true;
                    System.out.println("Exiting app!");
                    break;
                default:
                    System.out.println("Invalid option!!");
            }
        }

    }

    private static void displayNotes(Long userId){
        List<Note> notes = noteService.viewNoteForUser(userId);
        System.out.println("Displaying notes: ");
        for(Note note : notes){
            System.out.println("Title ---" + note.getTitle());
            System.out.println("Description ---" + note.getDesc());
        }

    }
}