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
    
    public User(String userName, String userEmail){
        this.username = userName;
        this.userEmail = userEmail;
        this.userID = userDB.getUserID(this.userEmail);
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
