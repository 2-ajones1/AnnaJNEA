/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 2-ajones1
 */
public class User extends DatabaseAccess{
    
    private static String username;
    private static String userEmail;
    private static String userID;
    private static DatabaseAccess userDB;
    
    public User(String inputName, String inputEmail, String password){
        if(userDB.verifyUser(inputEmail, inputName, password)){
            userID = userDB.getUserID(userEmail);
            username = userDB.getUsername(userID);
            userEmail = userDB.getEmail(username);
        }
    }

    @Override
    public String toString(){
        return username;
    }
    
}
