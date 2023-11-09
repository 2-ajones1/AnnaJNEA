/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 2-ajones1
 */
public class User{
    
    private static String username;
    private static String userEmail;
    private static String userID;
    private static DatabaseAccess userDB;
    private static boolean student;
    
    public User(String username, String userEmail){
        this.username = username;
        this.userEmail = userEmail;
        this.userID = userDB.getUserID(this.userEmail);
        this.student = userDB.getStudent(this.userEmail);
    }
    
    public static void setUsername(String username) {
        User.username = username;
    }

    public static void setUserEmail(String userEmail) {
        User.userEmail = userEmail;
    }

    public static boolean isStudent() {
        return student;
    }

    public static void setStudent(boolean student) {
        User.student = student;
    }
    
    public static String getUsername() {
        return username;
    }

    public static String getEmail() {
        return userEmail;
    }

    public static String getUserID() {
        return userID;
    }

    @Override
    public String toString(){
        return username;
    }
    
}
