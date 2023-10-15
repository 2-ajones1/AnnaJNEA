
import java.util.Random;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

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
    
    protected static final String DB_URL = "jdbc:mysql://computing.gfmat.org:3306/";
    protected static final String DB_NAME = "2022c_AJones_test";
    protected static final String USERNAME = "2022c_AJones";
    protected static final String PASSWORD = "gS8Hd5ASpw6K2ufw";
    
    //working
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
    
    //working
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
    
    public static boolean verifyUser(String email, String username, String password){
        try( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)){
            String sqlStatement = "SELECT UserPassword FROM Users WHERE UserEmail = '" + email + "' AND Username = '" + username +"';";
            ResultSet rs = null;
            String DBpassword = "";
            try(Statement statement = con.createStatement()){
                statement.execute(sqlStatement);
                rs = statement.executeQuery(sqlStatement);
                if(rs.next()){
                    DBpassword = rs.getString(1);
                }
            }
            if(DBpassword.equals(password)){
                return true;
            }
            con.close();
        }catch (Exception e){
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
        return false;
    }
    
    public static String getUserID(String email){
        try( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)){
            String sqlStatement = "SELECT UserID FROM Users WHERE UserEmail = '" + email + "';";
            ResultSet rs = null;
            try(Statement statement = con.createStatement()){
                statement.execute(sqlStatement);
                rs = statement.executeQuery(sqlStatement);
                if(rs.next()){
                    return rs.getString(1);
                }
            }
            con.close();
        }catch (Exception e){
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
        return "";
    }
    
    public static String getEmail(String username){
        try( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)){
            String sqlStatement = "SELECT UserEmail FROM Users WHERE Username = '" + username + "';";
            ResultSet rs = null;
            try(Statement statement = con.createStatement()){
                statement.execute(sqlStatement);
                rs = statement.executeQuery(sqlStatement);
                if(rs.next()){
                    return rs.getString(1);
                }
            }
            con.close();
        }catch (Exception e){
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
        return "";
    }
    
    public static String getUsername(String userID){
        try( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)){
            String sqlStatement = "SELECT Username FROM Users WHERE UserID = '" + userID + "';";
            ResultSet rs = null;
            try(Statement statement = con.createStatement()){
                statement.execute(sqlStatement);
                rs = statement.executeQuery(sqlStatement);
                if(rs.next()){
                    return rs.getString(1);
                }
            }
            con.close();
        }catch (Exception e){
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
        return "";
    }
    
    //working
    public static void createUser(String email, String username, String password, boolean student){
        Random rand = new Random();
        //random gen userID
        //INSERT INTO Users
        //VALUES <userID>, Username, Password, Email, StudentorTeacher
        String userID = "US";
        for(int x = 0; x < 4; x++){
            userID = userID + String.valueOf(rand.nextInt(10));
        }
        try( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)){
            String sqlStatement = ("INSERT INTO Users VALUES ('"+userID +"', '"+username+"', '"+password+"', '"+email+ "', " + student + ")");
            System.out.println(sqlStatement);
            try(Statement statement = con.createStatement()){
                statement.execute(sqlStatement);
                System.out.println("Success");
            }
            con.close();
        }catch (Exception e){
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
    }
    
    //working
    public static void createClass(String name){
        Random rand = new Random();
        String classID = "CL";
        for(int x = 0; x < 4; x++){
            classID = classID + String.valueOf(rand.nextInt(10));
        }
        String classCode = "";
        for(int x = 0; x < 9; x++){
            classCode = classCode + String.valueOf(rand.nextInt(10));
        }
        try( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)){
            String sqlStatement = ("INSERT INTO Classes VALUES ('" + classID + "', '" + name + "', '" + classCode + "');");
            System.out.println(sqlStatement);
            try(Statement statement = con.createStatement()){
                statement.execute(sqlStatement);
                System.out.println("Success");
            }
            con.close();
        }catch (Exception e){
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
    }
    
    //working
    public static boolean logIn(String username, String password){
        try( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)){
            String sqlStatement = "SELECT UserPassword FROM Users WHERE Username = '" + username + "';";
            ResultSet rs = null;
            String DBpassword = "";
            try(Statement statement = con.createStatement()){
                rs = statement.executeQuery(sqlStatement);
                if(rs.next()){
                    DBpassword = rs.getString(1);
                }
            }
            con.close();
            if (DBpassword.equals(password)){
                    return true;
                }else{
                    return false;
                }
        }catch (Exception e){
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
            return false;
        }
    }
    
    //working
    public static void deleteUser(String email, String username, String password){
        try( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)){
            ResultSet rs = null;
            String userID = "";
            String sqlStatement = ("SELECT UserID FROM Users WHERE Username = '" + username + "' AND UserEmail = '"+ email + "' AND UserPassword = '" + password + "';");
            try(Statement statement = con.createStatement()){
                rs = statement.executeQuery(sqlStatement);
                if(rs.next()){
                    userID = rs.getString(1);
                }
                System.out.println("Success");
            }
            sqlStatement = ("DELETE FROM Users WHERE UserID = '" + userID +  "';");
            System.out.println(sqlStatement);
            try(Statement statement = con.createStatement()){
                statement.execute(sqlStatement);
                System.out.println("Success");
            }
            con.close();
        }catch (Exception e){
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
    }
    
    //working
    public static void changeEmail(String email, String newEmail, String password){
        try( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)){
            String sqlStatement = "SELECT UserPassword FROM Users WHERE UserEmail = '" + email + "';";
            ResultSet rs = null;
            String userID = "";
            String DBpassword = "";
            try(Statement statement = con.createStatement()){
                statement.execute(sqlStatement);
                rs = statement.executeQuery(sqlStatement);
                if(rs.next()){
                    DBpassword = rs.getString(1);
                }
            }
            if(DBpassword.equals(password)){
                sqlStatement = "SELECT UserID FROM Users WHERE UserEmail = '" + email + "';";
                try(Statement statement = con.createStatement()){
                    statement.execute(sqlStatement);
                    rs = statement.executeQuery(sqlStatement);
                    if(rs.next()){
                        userID = rs.getString(1);
                    }
                }
                sqlStatement = "UPDATE Users SET UserEmail = '" + newEmail +"' WHERE UserID = '"+ userID +"';";
                try(Statement statement = con.createStatement()){
                    statement.execute(sqlStatement);
                }
            }
            con.close();
        }catch (Exception e){
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
    }
    
    //working
    public static void changePassword(String email, String password, String newPassword){
        try( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)){
            String sqlStatement = "SELECT UserPassword FROM Users WHERE UserEmail = '" + email + "';";
            ResultSet rs = null;
            String userID = "";
            String DBpassword = "";
            try(Statement statement = con.createStatement()){
                statement.execute(sqlStatement);
                rs = statement.executeQuery(sqlStatement);
                if(rs.next()){
                    DBpassword = rs.getString(1);
                }
            }
            if(DBpassword.equals(password)){
                sqlStatement = "SELECT UserID FROM Users WHERE UserEmail = '" + email + "';";
                try(Statement statement = con.createStatement()){
                    statement.execute(sqlStatement);
                    rs = statement.executeQuery(sqlStatement);
                    if(rs.next()){
                        userID = rs.getString(1);
                    }
                }
                sqlStatement = "UPDATE Users SET UserPassword = '" + newPassword +"' WHERE UserID = '"+ userID +"';";
                try(Statement statement = con.createStatement()){
                    statement.execute(sqlStatement);
                }
            }
            con.close();
        }catch (Exception e){
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
    }
    
    //working
    public static void changeUsername(String username, String newUsername, String password){
        try( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)){
            String sqlStatement = "SELECT UserPassword FROM Users WHERE Username = '" + username + "';";
            ResultSet rs = null;
            String userID = "";
            String DBpassword = "";
            try(Statement statement = con.createStatement()){
                statement.execute(sqlStatement);
                rs = statement.executeQuery(sqlStatement);
                if(rs.next()){
                    DBpassword = rs.getString(1);
                }
            }
            if(DBpassword.equals(password)){
                sqlStatement = "SELECT UserID FROM Users WHERE Username = '" + username + "';";
                try(Statement statement = con.createStatement()){
                    statement.execute(sqlStatement);
                    rs = statement.executeQuery(sqlStatement);
                    if(rs.next()){
                        userID = rs.getString(1);
                    }
                }
                sqlStatement = "UPDATE Users SET Username = '" + newUsername +"' WHERE UserID = '"+ userID +"';";
                try(Statement statement = con.createStatement()){
                    statement.execute(sqlStatement);
                }
            }
            con.close();
        }catch (Exception e){
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
    }
    
    //working on the database side
    public static void findPages (String keyword){
        try( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)){
            String sqlStatement = "SELECT PageID FROM InformationPages WHERE PageContent LIKE '%" + keyword + "%'";
            ResultSet rs = null;
            String pageID = "";
            try(Statement statement = con.createStatement()){
                statement.execute(sqlStatement);
                rs = statement.executeQuery(sqlStatement);
                if(rs.next()){
                    pageID = rs.getString(1);
                }
            }
            System.out.println(pageID);
            con.close();
        }catch (Exception e){
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
    }
    
    public static void getEquation(String[] values){
        ArrayList<String> typeIDs = new ArrayList<>();
        ResultSet rs = null;
    
        try( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)){
            //for each value name in the passed list, get its primary key
            for(String value : values){
                String sqlStatement = ("SELECT TypeID FROM ValueTypes WHERE ValueName = '" + value + "';");
                System.out.println(sqlStatement);
                
                try(Statement statement = con.createStatement()){
                    rs = statement.executeQuery(sqlStatement);
                    System.out.println(rs.toString());
                    
                    ResultSetMetaData rsmd = rs.getMetaData(); 
                    int columnCount = rsmd.getColumnCount();
                     while (rs.next()) {              
                        int i = 1;
                        while(i <= columnCount) {
                            typeIDs.add(rs.getString(i++));
                        }
                    } 
                }
            }
            System.out.println(typeIDs.toString());
            con.close();
        }catch (Exception e){
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
    }
}
