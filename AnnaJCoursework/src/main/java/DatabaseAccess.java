
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 2-ajones1
 */
public class DatabaseAccess {
    
    private static final String DB_URL = "jdbc:mysql://computing.gfmat.org:3306/";
    private static final String DB_NAME = "2022c_AJones_test";
    private static final String USERNAME = "2022c_AJones";
    private static final String PASSWORD = "gS8Hd5ASpw6K2ufw";
    
    public static boolean sqlTestDBConnection(){
        boolean connection;
        try( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)){
            System.out.println("CONNECTION MADE!");
            connection = true;
            con.close();
        }catch (Exception e){
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
            connection = false;
        }
        return connection;
    }
    
    //public static ResultSet getData() throwsSQL Exception{
        //String sql = "<query>";
        //Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD);
        //Statement st = con.createStatement();
        //ResultSet rs = st.executeQuery(sql);
    //}
    
    public static void sqlExecution(){
        try( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)){
            System.out.println("CONNECTION MADE!");
            String sqlStatement = "SELECT * FROM Users";
            try(Statement statement = con.createStatement()){
                statement.execute(sqlStatement);
            }
            con.close();
        }catch (Exception e){
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
    }
    
    public static void createUser(){
        //random gen userID
        //INSERT INTO Users
        //VALUES <userID>, Username, Password, Email, StudentorTeacher
    }
    
    public static void deleteUser(){
        //get userID
        //check user and password
        //send email
        //confirm in email
        //verify password
        //DELETE FROM Users WHERE userID = userID
    }
    
    public static void changeEmail(){
        //get userID with email
        //check password
        //verify with email or send notification
        //UPDATE Users
        //SET UserEmail = <newEmail>
        //WHERE userID
    }
}
