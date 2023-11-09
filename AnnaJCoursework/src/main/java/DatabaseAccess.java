
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
    public static boolean sqlTestDBConnection() {
        boolean connection;
        try ( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)) {
            System.out.println("CONNECTION MADE!");
            connection = true;
            con.close();
        } catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
            connection = false;
        }
        return connection;
    }

    //working
    public static void sqlExecution() {
        try ( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)) {
            System.out.println("CONNECTION MADE!");
            String sqlStatement = "SELECT * FROM Users";
            try ( Statement statement = con.createStatement()) {
                statement.execute(sqlStatement);
            }
            con.close();
        } catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
    }

    //working
    public static boolean verifyUser(String email, String username, String password) {
        try ( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)) {
            String sqlStatement = "SELECT UserPassword FROM Users WHERE UserEmail = '" + email + "' AND Username = '" + username + "';";
            ResultSet rs = null;
            String DBpassword = "";
            try ( Statement statement = con.createStatement()) {
                statement.execute(sqlStatement);
                rs = statement.executeQuery(sqlStatement);
                if (rs.next()) {
                    DBpassword = rs.getString(1);
                }
            }
            if (DBpassword.equals(password)) {
                return true;
            }
            con.close();
        } catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
        return false;
    }

    //working
    public static String getUserID(String email) {
        try ( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)) {
            String sqlStatement = "SELECT UserID FROM Users WHERE UserEmail = '" + email + "';";
            ResultSet rs = null;
            try ( Statement statement = con.createStatement()) {
                statement.execute(sqlStatement);
                rs = statement.executeQuery(sqlStatement);
                if (rs.next()) {
                    return rs.getString(1);
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
        return "";
    }
    
    public static Boolean getStudent(String email) {
        Boolean student = null;
        try ( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)) {
            String sqlStatement = "SELECT Student FROM Users WHERE UserEmail = '" + email + "';";
            String studentStr = "";
            
            ResultSet rs = null;
            try ( Statement statement = con.createStatement()) {
                statement.execute(sqlStatement);
                rs = statement.executeQuery(sqlStatement);
                if (rs.next()) {
                    studentStr = rs.getString(1);
                    System.out.println(studentStr);
                }
                if(studentStr.equals("1")){
                    student = true;
                }else{
                    student = false;
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
        return student;
    }

    //working
    public static String getEmail(String username) {
        try ( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)) {
            String sqlStatement = "SELECT UserEmail FROM Users WHERE Username = '" + username + "';";
            ResultSet rs = null;
            try ( Statement statement = con.createStatement()) {
                statement.execute(sqlStatement);
                rs = statement.executeQuery(sqlStatement);
                if (rs.next()) {
                    return rs.getString(1);
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
        return "";
    }

    //working
    public static String getUsername(String userID) {
        try ( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)) {
            String sqlStatement = "SELECT Username FROM Users WHERE UserID = '" + userID + "';";
            ResultSet rs = null;
            try ( Statement statement = con.createStatement()) {
                statement.execute(sqlStatement);
                rs = statement.executeQuery(sqlStatement);
                if (rs.next()) {
                    return rs.getString(1);
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
        return "";
    }

    //working
    public static String getTopicName(String topicID) {
        try ( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)) {
            String sqlStatement = "SELECT TopicName FROM Topics WHERE TopicID = '" + topicID + "';";
            ResultSet rs = null;
            try ( Statement statement = con.createStatement()) {
                statement.execute(sqlStatement);
                rs = statement.executeQuery(sqlStatement);
                if (rs.next()) {
                    return rs.getString(1);
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
        return "";
    }

    //working
    public static String getClassName(String classCode) {
        try ( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)) {
            String sqlStatement = "SELECT ClassName FROM Classes WHERE ClassCode = '" + classCode + "';";
            ResultSet rs = null;
            try ( Statement statement = con.createStatement()) {
                statement.execute(sqlStatement);
                rs = statement.executeQuery(sqlStatement);
                if (rs.next()) {
                    return rs.getString(1);
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
        return "";
    }

    public static ArrayList<String> getListOfExamQIDs() {
        ArrayList<String> examQIDs = new ArrayList<>();
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
        } catch (Exception e) {
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

    public static void setAnalytics(String userID, String examQID, double marksAwarded, double marksAvailable) {
        double percentAccuracy = (marksAwarded / marksAvailable) * 100;
        try ( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)) {
            String sqlStatement = "INSERT INTO Analytics VALUES ('" + userID + "', '" + examQID + "', " + marksAwarded + ", " + percentAccuracy + ");";
            try ( Statement statement = con.createStatement()) {
                statement.execute(sqlStatement);
            }
            con.close();
        } catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
    }

    //working
    public static void createUser(String email, String username, String password, boolean student) {
        Random rand = new Random();
        //random gen userID
        //INSERT INTO Users
        //VALUES <userID>, Username, Password, Email, StudentorTeacher
        String userID = "US";
        for (int x = 0; x < 4; x++) {
            userID = userID + String.valueOf(rand.nextInt(10));
        }
        try ( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)) {
            String sqlStatement = ("INSERT INTO Users VALUES ('" + userID + "', '" + username + "', '" + password + "', '" + email + "', " + student + ")");
            System.out.println(sqlStatement);
            try ( Statement statement = con.createStatement()) {
                statement.execute(sqlStatement);
                System.out.println("Success");
            }
            con.close();
        } catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
    }

    //working
    public static void createClass(String name, User user) {
        Random rand = new Random();
        String classID = "CL";
        for (int x = 0; x < 4; x++) {
            classID = classID + String.valueOf(rand.nextInt(10));
        }
        String classCode = "";
        for (int x = 0; x < 9; x++) {
            classCode = classCode + String.valueOf(rand.nextInt(10));
        }
        try ( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)) {
            String sqlStatement = ("INSERT INTO Classes VALUES ('" + classID + "', '" + name + "', '" + classCode + "');");
            System.out.println(sqlStatement);
            try ( Statement statement = con.createStatement()) {
                statement.execute(sqlStatement);
                System.out.println("Success");
            }
            sqlStatement = "INSERT INTO ClassMembers VALUES ('" + user.getUserID() + "', '" + classID + "')";
            System.out.println(sqlStatement);
            try ( Statement statement = con.createStatement()) {
                statement.execute(sqlStatement);
                System.out.println("Success");
            }
            con.close();
        } catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
    }

    public static boolean joinClass(String classCode, User user) {
        ResultSet rs = null;
        String classID = "";
        try ( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)) {
            String sqlStatement = ("SELECT ClassID FROM Classes WHERE ClassCode = '" + classCode + "';");
            System.out.println(sqlStatement);
            try ( Statement statement = con.createStatement()) {
                rs = statement.executeQuery(sqlStatement);
                if (rs.next()) {
                    classID = rs.getString(1);
                }
            }
            sqlStatement = "INSERT INTO ClassMembers VALUES ('" + user.getUserID() + "', '" + classID + "')";
            System.out.println(sqlStatement);
            try ( Statement statement = con.createStatement()) {
                statement.execute(sqlStatement);
                System.out.println("Success");
            }
            con.close();
            return true;
        } catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
            return false;
        }
    }

    //working
    public static User logIn(String username, String password) {
        User user = new User("", "");
        ArrayList<String> userInfo = new ArrayList<>();
        try ( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)) {
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

        } catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
        return user;
    }

    //working
    public static void deleteUser(String email, String username, String password) {
        try ( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)) {
            ResultSet rs = null;
            String userID = "";
            String sqlStatement = ("SELECT UserID FROM Users WHERE Username = '" + username + "' AND UserEmail = '" + email + "' AND UserPassword = '" + password + "';");
            try ( Statement statement = con.createStatement()) {
                rs = statement.executeQuery(sqlStatement);
                if (rs.next()) {
                    userID = rs.getString(1);
                }
                System.out.println("Success");
            }
            sqlStatement = ("DELETE FROM Users WHERE UserID = '" + userID + "';");
            System.out.println(sqlStatement);
            try ( Statement statement = con.createStatement()) {
                statement.execute(sqlStatement);
                System.out.println("Success");
            }
            con.close();
        } catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
    }

    //working
    public static void changeEmail(String email, String newEmail, String password) {
        try ( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)) {
            String sqlStatement = "SELECT UserPassword FROM Users WHERE UserEmail = '" + email + "';";
            ResultSet rs = null;
            String userID = "";
            String DBpassword = "";
            try ( Statement statement = con.createStatement()) {
                statement.execute(sqlStatement);
                rs = statement.executeQuery(sqlStatement);
                if (rs.next()) {
                    DBpassword = rs.getString(1);
                }
            }
            if (DBpassword.equals(password)) {
                sqlStatement = "SELECT UserID FROM Users WHERE UserEmail = '" + email + "';";
                try ( Statement statement = con.createStatement()) {
                    statement.execute(sqlStatement);
                    rs = statement.executeQuery(sqlStatement);
                    if (rs.next()) {
                        userID = rs.getString(1);
                    }
                }
                sqlStatement = "UPDATE Users SET UserEmail = '" + newEmail + "' WHERE UserID = '" + userID + "';";
                try ( Statement statement = con.createStatement()) {
                    statement.execute(sqlStatement);
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
    }

    //working
    public static void changePassword(String email, String password, String newPassword) {
        try ( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)) {
            String sqlStatement = "SELECT UserPassword FROM Users WHERE UserEmail = '" + email + "';";
            ResultSet rs = null;
            String userID = "";
            String DBpassword = "";
            try ( Statement statement = con.createStatement()) {
                statement.execute(sqlStatement);
                rs = statement.executeQuery(sqlStatement);
                if (rs.next()) {
                    DBpassword = rs.getString(1);
                }
            }
            if (DBpassword.equals(password)) {
                sqlStatement = "SELECT UserID FROM Users WHERE UserEmail = '" + email + "';";
                try ( Statement statement = con.createStatement()) {
                    statement.execute(sqlStatement);
                    rs = statement.executeQuery(sqlStatement);
                    if (rs.next()) {
                        userID = rs.getString(1);
                    }
                }
                sqlStatement = "UPDATE Users SET UserPassword = '" + newPassword + "' WHERE UserID = '" + userID + "';";
                try ( Statement statement = con.createStatement()) {
                    statement.execute(sqlStatement);
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
    }

    //working
    public static void changeUsername(String username, String newUsername, String password) {
        try ( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)) {
            String sqlStatement = "SELECT UserPassword FROM Users WHERE Username = '" + username + "';";
            ResultSet rs = null;
            String userID = "";
            String DBpassword = "";
            try ( Statement statement = con.createStatement()) {
                statement.execute(sqlStatement);
                rs = statement.executeQuery(sqlStatement);
                if (rs.next()) {
                    DBpassword = rs.getString(1);
                }
            }
            if (DBpassword.equals(password)) {
                sqlStatement = "SELECT UserID FROM Users WHERE Username = '" + username + "';";
                try ( Statement statement = con.createStatement()) {
                    statement.execute(sqlStatement);
                    rs = statement.executeQuery(sqlStatement);
                    if (rs.next()) {
                        userID = rs.getString(1);
                    }
                }
                sqlStatement = "UPDATE Users SET Username = '" + newUsername + "' WHERE UserID = '" + userID + "';";
                try ( Statement statement = con.createStatement()) {
                    statement.execute(sqlStatement);
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
    }

    //working on the database side
    public static ArrayList<String> findPages(String keyword) {
        ArrayList<String> pageTitles = new ArrayList<>();
        try ( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)) {
            String sqlStatement = "SELECT PageTitle FROM InformationPages WHERE PageContent LIKE '%" + keyword + "%'";
            ResultSet rs = null;

            try ( Statement statement = con.createStatement()) {
                statement.execute(sqlStatement);
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
        } catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
        return pageTitles;
    }

    //working
    public static int[] calculate(String valueName1, String valueName2) {
        int[] positions = {0, 0};
        String valueID1 = "";
        String valueID2 = "";
        String equationID = "";
        int valuePosition1 = 0;
        int valuePosition2 = 0;
        //get valueIDs from their names
        try ( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)) {
            String sqlStatement = "SELECT TypeID FROM ValueTypes WHERE ValueName = '" + valueName1 + "';";
            System.out.println(sqlStatement);
            ResultSet rs = null;
            try ( Statement statement = con.createStatement()) {
                statement.execute(sqlStatement);
                rs = statement.executeQuery(sqlStatement);
                if (rs.next()) {
                    valueID1 = rs.getString(1);
                }
            }
            sqlStatement = "SELECT TypeID FROM ValueTypes WHERE ValueName = '" + valueName2 + "';";
            System.out.println(sqlStatement);
            try ( Statement statement = con.createStatement()) {
                statement.execute(sqlStatement);
                rs = statement.executeQuery(sqlStatement);
                if (rs.next()) {
                    valueID2 = rs.getString(1);
                }
            }
            //get correct equationID
            sqlStatement = "SELECT EquationID FROM Equations WHERE \n"
                    + "TypeID1 = '" + valueID1 + "' AND TypeID2 = '" + valueID2 + "' OR\n"
                    + "TypeID2 = '" + valueID1 + "' AND TypeID1 = '" + valueID2 + "' OR\n"
                    + "TypeID1 = '" + valueID1 + "' AND TypeID3 = '" + valueID2 + "' OR\n"
                    + "TypeID3 = '" + valueID1 + "' AND TypeID1 = '" + valueID2 + "' OR\n"
                    + "TypeID2 = '" + valueID1 + "' AND TypeID3 = '" + valueID2 + "' OR\n"
                    + "TypeID3 = '" + valueID1 + "' AND TypeID2 = '" + valueID2 + "';";
            try ( Statement statement = con.createStatement()) {
                statement.execute(sqlStatement);
                rs = statement.executeQuery(sqlStatement);
                if (rs.next()) {
                    equationID = rs.getString(1);
                }
            }
            //get positions
            sqlStatement = "SELECT Position FROM ValuePositions WHERE TypeID = '" + valueID1 + "' AND EquationID = '" + equationID + "'";
            System.out.println(sqlStatement);
            try ( Statement statement = con.createStatement()) {
                statement.execute(sqlStatement);
                rs = statement.executeQuery(sqlStatement);
                if (rs.next()) {
                    valuePosition1 = Integer.valueOf(rs.getString(1));
                }
            }
            sqlStatement = "SELECT Position FROM ValuePositions WHERE TypeID = '" + valueID2 + "' AND EquationID = '" + equationID + "'";
            try ( Statement statement = con.createStatement()) {
                statement.execute(sqlStatement);
                rs = statement.executeQuery(sqlStatement);
                if (rs.next()) {
                    valuePosition2 = Integer.valueOf(rs.getString(1));
                }
            }
            positions[0] = valuePosition1;
            positions[1] = valuePosition2;
            con.close();
        } catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
        return positions;
    }

    //working
    public static String getUnits(int[] positions, String valueName1, String valueName2) {
        String units = "";
        String valueID1 = "";
        String valueID2 = "";
        String equationID = "";
        String typeID = "";
        //get valueIDs from their names
        try ( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)) {
            String sqlStatement = "SELECT TypeID FROM ValueTypes WHERE ValueName = '" + valueName1 + "';";
            System.out.println(sqlStatement);
            ResultSet rs = null;
            try ( Statement statement = con.createStatement()) {
                statement.execute(sqlStatement);
                rs = statement.executeQuery(sqlStatement);
                if (rs.next()) {
                    valueID1 = rs.getString(1);
                }
            }
            sqlStatement = "SELECT TypeID FROM ValueTypes WHERE ValueName = '" + valueName2 + "';";
            System.out.println(sqlStatement);
            try ( Statement statement = con.createStatement()) {
                statement.execute(sqlStatement);
                rs = statement.executeQuery(sqlStatement);
                if (rs.next()) {
                    valueID2 = rs.getString(1);
                }
            }
            //get correct equationID
            sqlStatement = "SELECT EquationID FROM Equations WHERE \n"
                    + "TypeID1 = '" + valueID1 + "' AND TypeID2 = '" + valueID2 + "' OR\n"
                    + "TypeID2 = '" + valueID1 + "' AND TypeID1 = '" + valueID2 + "' OR\n"
                    + "TypeID1 = '" + valueID1 + "' AND TypeID3 = '" + valueID2 + "' OR\n"
                    + "TypeID3 = '" + valueID1 + "' AND TypeID1 = '" + valueID2 + "' OR\n"
                    + "TypeID2 = '" + valueID1 + "' AND TypeID3 = '" + valueID2 + "' OR\n"
                    + "TypeID3 = '" + valueID1 + "' AND TypeID2 = '" + valueID2 + "';";
            try ( Statement statement = con.createStatement()) {
                statement.execute(sqlStatement);
                rs = statement.executeQuery(sqlStatement);
                if (rs.next()) {
                    equationID = rs.getString(1);
                }
            }
            String typeIDNumber = "";
            if ((positions[0] == 1 && positions[1] == 2) || (positions[0] == 2 && positions[1] == 1)) {
                typeIDNumber = "3";
            } else if ((positions[0] == 3 && positions[1] == 1) || (positions[0] == 1 && positions[1] == 3)) {
                typeIDNumber = "2";
            } else if ((positions[0] == 3 && positions[1] == 2) || (positions[0] == 2 && positions[1] == 3)) {
                typeIDNumber = "1";
            }
            sqlStatement = "SELECT TypeID" + typeIDNumber + " FROM Equations WHERE TypeID" + String.valueOf(positions[0]) + " = '" + valueID1 + "' AND TypeID" + String.valueOf(positions[1]) + " = '" + valueID2 + "';";
            System.out.println(sqlStatement);
            try ( Statement statement = con.createStatement()) {
                statement.execute(sqlStatement);
                rs = statement.executeQuery(sqlStatement);
                if (rs.next()) {
                    typeID = rs.getString(1);
                }
            }
            System.out.println(typeID);
            sqlStatement = "SELECT ValueUnits FROM ValueTypes WHERE TypeID = '" + typeID + "';";
            System.out.println(sqlStatement);
            try ( Statement statement = con.createStatement()) {
                statement.execute(sqlStatement);
                rs = statement.executeQuery(sqlStatement);
                if (rs.next()) {
                    units = rs.getString(1);
                }
            }
        } catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
        return units;
    }

    //working
    public static String[] periodicity(String positiveName, String negativeName) {
        String[] result = {"formula","pos","neg","pcharge","ncharge","pA", "nA"};
        try ( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)) {
            int positiveCharge = 0;
            int negativeCharge = 0;
            ResultSet rs = null;
            String[] parts = positiveName.split(" ");
            if (parts[0].equals("Group")) {
                positiveCharge = Integer.valueOf(parts[1]);
                positiveName = "X";
            } else {
                positiveName = parts[0];
                String sqlStatement = "SELECT IonChargeValue FROM Ions WHERE IonSymbol = '" + parts[0] + "';";
                try ( Statement statement = con.createStatement()) {
                    statement.execute(sqlStatement);
                    rs = statement.executeQuery(sqlStatement);
                    if (rs.next()) {
                        positiveCharge = Integer.valueOf(rs.getString(1));
                    }
                }  
            }
            String[] parts2 = negativeName.split(" ");
            if (parts2[0].equals("Group")) {
                negativeCharge = 8 - Integer.valueOf(parts2[1]);
                negativeName = "Y";
            } else {
                negativeName = parts2[0];
                String sqlStatement = "SELECT IonChargeValue FROM Ions WHERE IonSymbol = '" + parts2[0] + "';";
                try ( Statement statement = con.createStatement()) {
                    statement.execute(sqlStatement);
                    rs = statement.executeQuery(sqlStatement);
                    if (rs.next()) {
                        negativeCharge = Integer.valueOf(rs.getString(1));
                    }
                }  
            }
            int lcm = new Calculator().findLCM(positiveCharge, negativeCharge);
            String positiveAtoms = String.valueOf(lcm/positiveCharge);
            String negativeAtoms = String.valueOf(lcm/negativeCharge);
            if(positiveAtoms.equals("1")){
                positiveAtoms = "";
            }else if (positiveName.length() > 1){
                positiveName = "(" + positiveName + ")";
            }
            if(negativeAtoms.equals("1")){
                negativeAtoms = "";
            }else if (negativeName.length() > 1){
                negativeName = "(" + negativeName + ")";
            }
            //String[] result = {"formula","pos","neg","pcharge","ncharge","pA", "nA"};
            result[0] = (positiveName + positiveAtoms + negativeName + negativeAtoms);
            result[1] = positiveName;
            result[2] = negativeName;
            result[3] = String.valueOf(positiveCharge);
            result[4] = String.valueOf(negativeCharge);
            if(positiveAtoms.equals("")){
                positiveAtoms = "1";
            }
            if(negativeAtoms.equals("")){
                negativeAtoms = "1";
            }
            result[5] = positiveAtoms;
            result[6] = negativeAtoms;
            con.close();
        } catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
        return result;
    }
}
