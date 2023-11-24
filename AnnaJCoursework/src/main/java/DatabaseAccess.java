
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

    public static ArrayList<String> selectFromDatabaseArrayList(String sqlStatement) {
        ArrayList<String> stringList = new ArrayList<>();
        try ( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)) {
            ResultSet rs = null;
            try ( Statement statement = con.createStatement()) {
                statement.execute(sqlStatement);
                rs = statement.executeQuery(sqlStatement);
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                while (rs.next()) {
                    int i = 1;
                    while (i <= columnCount) {
                        stringList.add(rs.getString(i++));
                    }
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
        return stringList;
    }

    public static String selectFromDatabaseString(String sqlStatement) {
        String string = "";
        try ( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)) {
            ResultSet rs = null;
            try ( Statement statement = con.createStatement()) {
                statement.execute(sqlStatement);
                rs = statement.executeQuery(sqlStatement);
                if (rs.next()) {
                    string = rs.getString(1);
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
        return string;
    }

    public static int selectFromDatabaseInt(String sqlStatement) {
        String string = "";
        try ( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)) {
            ResultSet rs = null;
            try ( Statement statement = con.createStatement()) {
                statement.execute(sqlStatement);
                rs = statement.executeQuery(sqlStatement);
                if (rs.next()) {
                    string = rs.getString(1);
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
        return Integer.valueOf(string);
    }

    //working
    public static boolean verifyUser(String email, String username, String password) {
        boolean verified = false;
        String DBpassword = new DatabaseAccess().selectFromDatabaseString("SELECT UserPassword FROM Users WHERE UserEmail = '" + email + "' AND Username = '" + username + "';");
        if (DBpassword.equals(password)) {
            verified = true;
        }
        return verified;
    }

    //working
    public static String getUserID(String email) {
        String userID = new DatabaseAccess().selectFromDatabaseString("SELECT UserID FROM Users WHERE UserEmail = '" + email + "';");
        return userID;
    }

    public static Boolean getStudent(String email) {
        Boolean student = null;
        String studentStr = new DatabaseAccess().selectFromDatabaseString("SELECT Student FROM Users WHERE UserEmail = '" + email + "';");
        if (studentStr.equals("1")) {
            student = true;
        } else {
            student = false;
        }

        return student;
    }

    //working
    public static String getEmail(String username) {
        String email = new DatabaseAccess().selectFromDatabaseString("SELECT UserEmail FROM Users WHERE Username = '" + username + "';");
        return email;
    }

    //working
    public static String getUsername(String userID) {
        String username = new DatabaseAccess().selectFromDatabaseString("SELECT Username FROM Users WHERE UserID = '" + userID + "';");
        return username;
    }

    //working
    public static String getTopicName(String topicID) {
        String topicName = new DatabaseAccess().selectFromDatabaseString("SELECT TopicName FROM Topics WHERE TopicID = '" + topicID + "';");
        return topicName;
    }

    //working
    public static String getClassName(String classCode) {
        String className = new DatabaseAccess().selectFromDatabaseString("SELECT ClassName FROM Classes WHERE ClassCode = '" + classCode + "';");
        return className;
    }

    public static ArrayList<String> getListOfExamQIDs() {
        ArrayList<String> examQIDs = new DatabaseAccess().selectFromDatabaseArrayList("SELECT ExamQID FROM ExamQuestions;");
        return examQIDs;
    }

    public static ArrayList<String> getListOfTopicIDs() {
        ArrayList<String> topicIDs = new DatabaseAccess().selectFromDatabaseArrayList("SELECT TopicID FROM Topics;");

        return topicIDs;
    }

    public static void getAnalytics(String userID) {
        ArrayList<String> topicIDs = new ArrayList<>();
        topicIDs = new DatabaseAccess().getListOfTopicIDs();
        ArrayList<String> topicExamQIDs = new ArrayList<>();
        ArrayList<String> userExamQIDs = new ArrayList<>();
        ArrayList<String> examQIDs = new ArrayList<>();
        ArrayList<String> percentAccuracies = new ArrayList<>();

        for (String topicID : topicIDs) {
            topicExamQIDs = new DatabaseAccess().selectFromDatabaseArrayList("SELECT ExamQID FROM ExamQuestions WHERE TopicID = '" + topicID + "'");
        }

        userExamQIDs = new DatabaseAccess().selectFromDatabaseArrayList("SELECT ExamQID FROM Analytics WHERE UserID = '" + userID + "'");
        for (String topicEQID : topicExamQIDs) {
            if (userExamQIDs.contains(topicEQID)) {
                examQIDs.add(topicEQID);
            }

            for (String examQID : examQIDs) {
                percentAccuracies = new DatabaseAccess().selectFromDatabaseArrayList("SELECT PercentAccuracy FROM Analytics WHERE ExamQID = '" + examQID + "'");
            }
        }
    }

    public static ArrayList<String> getExamQuestion(int modifier, User user) {
        ArrayList<String> examQuestion = new ArrayList<>();
        if (modifier == 1) {
            ArrayList<String> examIDs = new ArrayList<>();
            ArrayList<String> topicIDs = new ArrayList<>();
            ArrayList<String> userExamQIDs = new ArrayList<>();

            //get a list of all exam questions in the database
            examIDs = new DatabaseAccess().getListOfExamQIDs();
            
            //determine which ones user has already answered
            userExamQIDs = new DatabaseAccess().selectFromDatabaseArrayList("SELECT ExamQID FROM Analytics WHERE UserID = '" + user.getUserID() + "'");
            for(String userEQID : userExamQIDs){
                if (examIDs.contains(userEQID)){
                    examIDs.remove(userEQID);
                }
            }
            if(examIDs.isEmpty()){
                examIDs = new DatabaseAccess().getListOfExamQIDs();
            }

            Random rand = new Random();

            //get random exam question from the list
            String examQID = examIDs.get(rand.nextInt(examIDs.size()));

            topicIDs = new DatabaseAccess().selectFromDatabaseArrayList("SELECT TopicID FROM ExamQuestions WHERE ExamQID = '" + examQID + "';");
            String topicID = topicIDs.get(0);
            examQuestion = new DatabaseAccess().selectFromDatabaseArrayList("SELECT Question, MarksAvailable, TopicName, Answer, ExamQID FROM ExamQuestions, Topics WHERE ExamQuestions.ExamQID = '" + examQID + "' AND Topics.TopicID = '" + topicID + "';");
        }else if(modifier == 2){
            //by lowest accuracy
            examQuestion = new DatabaseAccess().selectFromDatabaseArrayList("SELECT ExamQID FROM Analytics WHERE UserID = '"+user.getUserID()+"' ORDER BY(PercentAccuracy);");
        }else if(modifier == 3){
            //by topic
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
            try ( Statement statement = con.createStatement()) {
                statement.execute(sqlStatement);
            }
            sqlStatement = "INSERT INTO ClassMembers VALUES ('" + user.getUserID() + "', '" + classID + "')";
            System.out.println(sqlStatement);
            try ( Statement statement = con.createStatement()) {
                statement.execute(sqlStatement);
            }
            con.close();
        } catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
    }

    public static boolean joinClass(String classCode, User user) {
        ResultSet rs = null;
        String classID = new DatabaseAccess().selectFromDatabaseString("SELECT ClassID FROM Classes WHERE ClassCode = '" + classCode + "';");
        try ( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)) {
            String sqlStatement = "INSERT INTO ClassMembers VALUES ('" + user.getUserID() + "', '" + classID + "')";
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
        ResultSet rs = null;
        String DBpassword = new DatabaseAccess().selectFromDatabaseString("SELECT UserPassword FROM Users WHERE Username = '" + username + "';");
        if (DBpassword.equals(password)) {
            userInfo = new DatabaseAccess().selectFromDatabaseArrayList("SELECT UserEmail FROM Users WHERE Username = '" + username + "';");
            String email = userInfo.get(0);
            user = new User(username, email);
            HomePage home = new HomePage(user);
            home.setVisible(true);
        }
        return user;
    }

    //working
    public static void deleteUser(String email, String username, String password) {
        try ( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)) {
            ResultSet rs = null;
            String userID = new DatabaseAccess().selectFromDatabaseString("SELECT UserID FROM Users WHERE Username = '" + username + "' AND UserEmail = '" + email + "' AND UserPassword = '" + password + "';");
            String sqlStatement = ("DELETE FROM Users WHERE UserID = '" + userID + "';");
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
            String DBpassword = new DatabaseAccess().selectFromDatabaseString("SELECT UserPassword FROM Users WHERE UserEmail = '" + email + "';");
            if (DBpassword.equals(password)) {
                String userID = new DatabaseAccess().selectFromDatabaseString("SELECT UserID FROM Users WHERE UserEmail = '" + email + "';");
                String sqlStatement = "UPDATE Users SET UserEmail = '" + newEmail + "' WHERE UserID = '" + userID + "';";
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

            String DBpassword = new DatabaseAccess().selectFromDatabaseString("SELECT UserPassword FROM Users WHERE UserEmail = '" + email + "';");

            if (DBpassword.equals(password)) {
                String userID = new DatabaseAccess().selectFromDatabaseString("SELECT UserID FROM Users WHERE UserEmail = '" + email + "';");
                String sqlStatement = "UPDATE Users SET UserPassword = '" + newPassword + "' WHERE UserID = '" + userID + "';";
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
            String DBpassword = new DatabaseAccess().selectFromDatabaseString("SELECT UserPassword FROM Users WHERE Username = '" + username + "';");
            if (DBpassword.equals(password)) {
                String userID = new DatabaseAccess().selectFromDatabaseString("SELECT UserID FROM Users WHERE Username = '" + username + "';");
                String sqlStatement = "UPDATE Users SET Username = '" + newUsername + "' WHERE UserID = '" + userID + "';";
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
        ArrayList<String> pageTitles = new DatabaseAccess().selectFromDatabaseArrayList("SELECT PageTitle FROM InformationPages WHERE PageContent LIKE '%" + keyword + "%'");
        return pageTitles;
    }

    public static ArrayList<String> getPageInfo(String title) {
        ArrayList<String> pageInfo = new DatabaseAccess().selectFromDatabaseArrayList("SELECT PageTitle, PageContent FROM InformationPages WHERE PageTitle = '" + title + "'");

        return pageInfo;
    }

    //working
    public static int[] calculate(String valueName1, String valueName2) {
        int[] positions = {0, 0};
        String valueID1 = new DatabaseAccess().selectFromDatabaseString("SELECT TypeID FROM ValueTypes WHERE ValueName = '" + valueName1 + "';");
        String valueID2 = new DatabaseAccess().selectFromDatabaseString("SELECT TypeID FROM ValueTypes WHERE ValueName = '" + valueName2 + "';");
        String equationID = new DatabaseAccess().selectFromDatabaseString("SELECT EquationID FROM Equations WHERE \n"
                + "TypeID1 = '" + valueID1 + "' AND TypeID2 = '" + valueID2 + "' OR\n"
                + "TypeID2 = '" + valueID1 + "' AND TypeID1 = '" + valueID2 + "' OR\n"
                + "TypeID1 = '" + valueID1 + "' AND TypeID3 = '" + valueID2 + "' OR\n"
                + "TypeID3 = '" + valueID1 + "' AND TypeID1 = '" + valueID2 + "' OR\n"
                + "TypeID2 = '" + valueID1 + "' AND TypeID3 = '" + valueID2 + "' OR\n"
                + "TypeID3 = '" + valueID1 + "' AND TypeID2 = '" + valueID2 + "';");
        int valuePosition1 = Integer.valueOf(new DatabaseAccess().selectFromDatabaseString("SELECT Position FROM ValuePositions WHERE TypeID = '" + valueID1 + "' AND EquationID = '" + equationID + "'"));
        int valuePosition2 = Integer.valueOf(new DatabaseAccess().selectFromDatabaseString("SELECT Position FROM ValuePositions WHERE TypeID = '" + valueID2 + "' AND EquationID = '" + equationID + "'"));;
        positions[0] = valuePosition1;
        positions[1] = valuePosition2;
        return positions;
    }

    //working
    public static String getUnits(int[] positions, String valueName1, String valueName2) {
        String units = "";
        String valueID1 = new DatabaseAccess().selectFromDatabaseString("SELECT TypeID FROM ValueTypes WHERE ValueName = '" + valueName1 + "';");
        String valueID2 = new DatabaseAccess().selectFromDatabaseString("SELECT TypeID FROM ValueTypes WHERE ValueName = '" + valueName2 + "';");
        String equationID = new DatabaseAccess().selectFromDatabaseString("SELECT EquationID FROM Equations WHERE \n"
                + "TypeID1 = '" + valueID1 + "' AND TypeID2 = '" + valueID2 + "' OR\n"
                + "TypeID2 = '" + valueID1 + "' AND TypeID1 = '" + valueID2 + "' OR\n"
                + "TypeID1 = '" + valueID1 + "' AND TypeID3 = '" + valueID2 + "' OR\n"
                + "TypeID3 = '" + valueID1 + "' AND TypeID1 = '" + valueID2 + "' OR\n"
                + "TypeID2 = '" + valueID1 + "' AND TypeID3 = '" + valueID2 + "' OR\n"
                + "TypeID3 = '" + valueID1 + "' AND TypeID2 = '" + valueID2 + "';");
        String typeIDNumber = "";
        if ((positions[0] == 1 && positions[1] == 2) || (positions[0] == 2 && positions[1] == 1)) {
            typeIDNumber = "3";
        } else if ((positions[0] == 3 && positions[1] == 1) || (positions[0] == 1 && positions[1] == 3)) {
            typeIDNumber = "2";
        } else if ((positions[0] == 3 && positions[1] == 2) || (positions[0] == 2 && positions[1] == 3)) {
            typeIDNumber = "1";
        }
        String typeID = new DatabaseAccess().selectFromDatabaseString("SELECT TypeID" + typeIDNumber + " FROM Equations WHERE TypeID" + String.valueOf(positions[0]) + " = '" + valueID1 + "' AND TypeID" + String.valueOf(positions[1]) + " = '" + valueID2 + "';");
        units = new DatabaseAccess().selectFromDatabaseString("SELECT ValueUnits FROM ValueTypes WHERE TypeID = '" + typeID + "';");
        return units;
    }

    //working
    public static String[] periodicity(String positiveName, String negativeName) {
        String[] result = {"formula", "pos", "neg", "pcharge", "ncharge", "pA", "nA"};
        int positiveCharge = 0;
        int negativeCharge = 0;
        String[] parts = positiveName.split(" ");
        if (parts[0].equals("Group")) {
            positiveCharge = Integer.valueOf(parts[1]);
            positiveName = "X";
        } else {
            positiveName = parts[0];
            positiveCharge = new DatabaseAccess().selectFromDatabaseInt("SELECT IonChargeValue FROM Ions WHERE IonSymbol = '" + parts[0] + "';");

        }
        String[] parts2 = negativeName.split(" ");
        if (parts2[0].equals("Group")) {
            negativeCharge = 8 - Integer.valueOf(parts2[1]);
            negativeName = "Y";
        } else {
            negativeName = parts2[0];
            negativeCharge = new DatabaseAccess().selectFromDatabaseInt("SELECT IonChargeValue FROM Ions WHERE IonSymbol = '" + parts2[0] + "';");

        }
        int lcm = new Calculator().findLCM(positiveCharge, negativeCharge);
        String positiveAtoms = String.valueOf(lcm / positiveCharge);
        String negativeAtoms = String.valueOf(lcm / negativeCharge);
        if (positiveAtoms.equals("1")) {
            positiveAtoms = "";
        } else if (positiveName.length() > 1) {
            positiveName = "(" + positiveName + ")";
        }
        if (negativeAtoms.equals("1")) {
            negativeAtoms = "";
        } else if (negativeName.length() > 1) {
            negativeName = "(" + negativeName + ")";
        }
        //String[] result = {"formula","pos","neg","pcharge","ncharge","pA", "nA"};
        result[0] = (positiveName + positiveAtoms + negativeName + negativeAtoms);
        result[1] = positiveName;
        result[2] = negativeName;
        result[3] = String.valueOf(positiveCharge);
        result[4] = String.valueOf(negativeCharge);
        if (positiveAtoms.equals("")) {
            positiveAtoms = "1";
        }
        if (negativeAtoms.equals("")) {
            negativeAtoms = "1";
        }
        result[5] = positiveAtoms;
        result[6] = negativeAtoms;
        return result;
    }
}
