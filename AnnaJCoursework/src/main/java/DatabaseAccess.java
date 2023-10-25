
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
    
    public static String getTopicName(String topicID){
        try( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)){
            String sqlStatement = "SELECT TopicName FROM Topics WHERE TopicID = '" + topicID + "';";
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
    
    public static String getClassName(String classCode){
        try( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)){
            String sqlStatement = "SELECT ClassName FROM Classes WHERE ClassCode = '" + classCode + "';";
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
    
    public static ArrayList<String> getListOfExamQIDs(){
        ArrayList<String> examQIDs = new ArrayList<> ();
        try ( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)) {
            String sqlStatement = "SELECT ExamQID FROM ExamQuestions;";
            ResultSet rs = null;
            try ( Statement statement = con.createStatement()) {
                statement.execute(sqlStatement);
                rs = statement.executeQuery(sqlStatement);
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                while (rs.next()) {
                    int i = 1;
                    while (i <= columnCount) {
                        examQIDs.add(rs.getString(i++));
                    }
                }
            }
            con.close();
        } catch (Exception e){
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
        return examQIDs;
    }

    public static ArrayList<String> getExamQuestion(int modifier) {
        ArrayList<String> examQuestion = new ArrayList<>();
        if (modifier == 1) {
            ArrayList<String> examIDs = new ArrayList<>();
            ArrayList<String> topicIDs = new ArrayList<>();
            examIDs = new DatabaseAccess().getListOfExamQIDs();
            Random rand = new Random();
            String examQID = examIDs.get(rand.nextInt(examIDs.size()));
            try ( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)) {
                String sqlStatement = "SELECT TopicID FROM ExamQuestions WHERE ExamQID = '" + examQID + "';";
                ResultSet rs = null;
                try ( Statement statement = con.createStatement()) {
                    statement.execute(sqlStatement);
                    rs = statement.executeQuery(sqlStatement);
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnCount = rsmd.getColumnCount();
                    while (rs.next()) {
                        int i = 1;
                        while (i <= columnCount) {
                            topicIDs.add(rs.getString(i++));
                        }
                    }
                }
                String topicID = topicIDs.get(0);
                System.out.println(examQID);
                System.out.println(topicID);
                sqlStatement = "SELECT Question, MarksAvailable, TopicName, Answer, ExamQID FROM ExamQuestions, Topics WHERE ExamQuestions.ExamQID = '" + examQID + "' AND Topics.TopicID = '" + topicID + "';";
                System.out.println(sqlStatement);
                try ( Statement statement = con.createStatement()) {
                    statement.execute(sqlStatement);
                    rs = statement.executeQuery(sqlStatement);
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnCount = rsmd.getColumnCount();
                    while (rs.next()) {
                        int i = 1;
                        while (i <= columnCount) {
                            examQuestion.add(rs.getString(i++));
                        }
                    }
                }
                con.close();
            } catch (Exception e) {
                System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
            }
        }
        return examQuestion;
    }
    
    public static void setAnalytics(String userID, String examQID, double marksAwarded, double marksAvailable){
        double percentAccuracy = (marksAwarded/marksAvailable) * 100;
        try( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)){
            String sqlStatement = "INSERT INTO Analytics VALUES ('" + userID + "', '" + examQID + "', " + marksAwarded + ", " + percentAccuracy + ");";
            try(Statement statement = con.createStatement()){
                statement.execute(sqlStatement);
            }
            con.close();
        }catch (Exception e){
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
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
    public static void createClass(String name, User user){
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
            sqlStatement = "INSERT INTO ClassMembers VALUES ('" + user.getUserID() + "', '" + classID + "')";
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
    
    public static boolean joinClass(String classCode, User user){
        ResultSet rs = null;
        String classID = "";
        try( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)){
            String sqlStatement = ("SELECT ClassID FROM Classes WHERE ClassCode = '" + classCode + "';");
            System.out.println(sqlStatement);
            try(Statement statement = con.createStatement()){
                rs = statement.executeQuery(sqlStatement);
                if (rs.next()) {
                    classID = rs.getString(1);
                }
            }
            sqlStatement = "INSERT INTO ClassMembers VALUES ('" + user.getUserID() + "', '" + classID + "')";
            System.out.println(sqlStatement);
            try(Statement statement = con.createStatement()){
                statement.execute(sqlStatement);
                System.out.println("Success");
            }
            con.close();
            return true;
        }catch (Exception e){
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
            return false;
        }
    }
    
    //working
    public static User logIn(String username, String password){
        User user = new User("", "");
        ArrayList<String> userInfo = new ArrayList<> ();
        try( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)){
            String sqlStatement = "SELECT UserPassword FROM Users WHERE Username = '" + username + "';";
            ResultSet rs = null;
            String DBpassword = "";
            try ( Statement statement = con.createStatement()) {
                rs = statement.executeQuery(sqlStatement);
                if (rs.next()) {
                    DBpassword = rs.getString(1);
                }
                if (DBpassword.equals(password)) {
                    sqlStatement = "SELECT UserEmail FROM Users WHERE Username = '" + username + "';";
                    rs = statement.executeQuery(sqlStatement);
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnCount = rsmd.getColumnCount();
                    while (rs.next()) {
                        int i = 1;
                        while (i <= columnCount) {
                            userInfo.add(rs.getString(i++));
                        }
                    }
                    String email = userInfo.get(0);
                    user = new User(username, email);
                    HomePage home = new HomePage(user);
                    home.setVisible(true);
                    
                }

            }
            con.close();

        } catch (Exception e){
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
        return user;
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
    public static ArrayList<String> findPages (String keyword){
        ArrayList<String> pageTitles = new ArrayList<>();
        try( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)){
            String sqlStatement = "SELECT PageTitle FROM InformationPages WHERE PageContent LIKE '%" + keyword + "%'";
            ResultSet rs = null;
            
            try(Statement statement = con.createStatement()){
                statement.execute(sqlStatement);
                rs = statement.executeQuery(sqlStatement);
                rs = statement.executeQuery(sqlStatement);
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnCount = rsmd.getColumnCount();
                    while (rs.next()) {
                        int i = 1;
                        while (i <= columnCount) {
                            pageTitles.add(rs.getString(i++));
                        }
                    }
            }
            con.close();
        }catch (Exception e){
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
        return pageTitles;
    }
    
    public static void getEquation(String[] values){
        ArrayList<String> typeIDs = new ArrayList<>();
        ResultSet rs = null;
        String valueName1 = values[0];
        String valueName2 = values[1];
        int position1 = 0;
        int position2 = 0;
        int position3 = 0;
    
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
            String typeid1 = typeIDs.get(0);
            String typeid2 = typeIDs.get(1);
            ArrayList<String> equationIDs = new ArrayList<>();
             ArrayList<String> positions = new ArrayList<>();
            String equationid = "";
            String sqlStatement = ("SELECT EquationID FROM Equations WHERE TypeID1 = '" + typeid1 + "' OR TypeID2 = '" + typeid1 + "' OR TypeID3 = '" + typeid1 + "';");
            System.out.println(sqlStatement);
                try(Statement statement = con.createStatement()){
                    if(statement.execute(sqlStatement)){
                        rs = statement.executeQuery(sqlStatement);
                        ResultSetMetaData rsmd = rs.getMetaData(); 
                        int columnCount = rsmd.getColumnCount();
                        while (rs.next()) {              
                            int i = 1;
                            while(i <= columnCount) {
                                equationIDs.add(rs.getString(i++));
                            }
                        }
                        sqlStatement = ("SELECT EquationID FROM Equations WHERE TypeID1 = '" + typeid2 + "' OR TypeID2 = '" + typeid2 + "' OR TypeID3 = '" + typeid2 + "';");
                        if(statement.execute(sqlStatement)){
                            rs = statement.executeQuery(sqlStatement);
                            if(rs.next()){
                                equationid = rs.getString(1);
                            }
                        }
                        
                        for(String id : equationIDs){
                            if(id.equals(equationid)){
                                equationid = id;
                            }
                        }
                        positions = new DatabaseAccess().getPositions(typeid1, typeid2);
                    }
                }
            System.out.println(equationid);
            con.close();
        }catch (Exception e){
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
    }
    
    public static ArrayList<String> getPositions(String typeid1, String typeid2){
        ResultSet rs = null;
        String typeidx = "";
        ArrayList<String> positions = new ArrayList<>(3);
        ArrayList<String> equationIDs = new ArrayList<>();
        try( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)){
            int position1 = 0;
            int position2 = 0;
            String sqlStatement = ("SELECT EquationID FROM Equations WHERE TypeID1 = '" + typeid1 + "';");
            try(Statement statement = con.createStatement()){
                if(statement.execute(sqlStatement)){
                    position1 = 1;
                }else{
                    sqlStatement = ("SELECT EquationID FROM Equations WHERE TypeID2 = '" + typeid1 + "';");
                    if(statement.execute(sqlStatement)){
                        position1 = 2;
                    }else{
                        sqlStatement = ("SELECT EquationID FROM Equations WHERE TypeID3 = '" + typeid1 + "';");
                        if(statement.execute(sqlStatement)){
                            position1 = 3;
                        }
                    } 
                }
                sqlStatement = ("SELECT EquationID FROM Equations WHERE TypeID1 = '" + typeid2 + "';");
                if(statement.execute(sqlStatement)){
                    position2 = 1;
                }else{
                    sqlStatement = ("SELECT EquationID FROM Equations WHERE TypeID2 = '" + typeid2 + "';");
                    if(statement.execute(sqlStatement)){
                        position2 = 2;
                    }else{
                        sqlStatement = ("SELECT EquationID FROM Equations WHERE TypeID3 = '" + typeid2 + "';");
                        if(statement.execute(sqlStatement)){
                            position2 = 3;
                        }
                    } 
                }
                positions.add(position1 - 1, typeid1);
                positions.add(position2 - 1, typeid2);
                for (int i = 0; i < positions.size(); i++){
                    if(positions.get(i) == null){
                        int x = i+ 1;
                        sqlStatement = ("SELECT TypeID" + x + " FROM Equations" );
                        rs = statement.executeQuery(sqlStatement);
                            if(rs.next()){
                                typeidx = rs.getString(1);
                            }
                        positions.add(i, typeidx);
                    }
                }
                System.out.println(positions.toString());
            }
            con.close();
            return positions;
        }catch (Exception e){
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
            return positions;
        }
    }
}
