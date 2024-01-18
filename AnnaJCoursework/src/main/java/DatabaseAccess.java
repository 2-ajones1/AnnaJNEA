
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

    public static boolean sqlTestDBConnection() {
        //declare return variable so only one return statement is needed
        boolean connection;
        
        //connect to database with correct credentials
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

    public static boolean existsInDatabase(String testString, String tableName, String columnName) {
        //variables declaration
        ArrayList<String> entries = new ArrayList<>();
        boolean exists = false;
        
        //get all items in the column from that table and put it in a list
        entries = new DatabaseAccess().selectFromDatabaseArrayList("SELECT " + columnName + " FROM " + tableName + ";");
        
        //search for wanted string in the list (could use rec binary search)
        if (entries.contains(testString)) {
            exists = true;
        } else {
            exists = false;
        }
        return exists;
    }

    public static boolean validatePresence(String testString) {
        boolean present = false;
        
        if (testString.isEmpty()) {
            present = false;
        } else {
            present = true;
        }
        return present;
    }

    public static boolean validateLength(String testString, int maxLength) {
        boolean validLength = false;
        
        if (testString.length() <= maxLength) {
            validLength = true;
        }
        return validLength;
    }

    public static ArrayList<String> selectFromDatabaseArrayList(String sqlStatement) {
        //declare list to be used and returned
        ArrayList<String> stringList = new ArrayList<>();
        
        //connect to database
        try ( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)) {
            ResultSet rs = null;
            
            try ( Statement statement = con.createStatement()) {
                
                //execute the given sql query and put the results in a result set
                rs = statement.executeQuery(sqlStatement);
                
                //iterate through the result set and place each item in the list
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
        //declare string to be returned
        String string = "";
        
        //connect to database
        try ( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)) {
            ResultSet rs = null;
            try ( Statement statement = con.createStatement()) {
                rs = statement.executeQuery(sqlStatement);
                
                //get the first item in the result set and place it in the return string
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

    public static void insertIntoDatabase(String sqlStatement) {
        try ( Connection con = DriverManager.getConnection(DB_URL + DB_NAME, USERNAME, PASSWORD)) {
            try ( Statement statement = con.createStatement()) {
                statement.execute(sqlStatement);
                System.out.println("Success");
            }
            con.close();
        } catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG..." + e.getMessage());
        }
    }

    public static boolean verifyUser(String email, String username, String password) {
        boolean verified = false;
        
        //get the password matching the given username in the database
        String DBpassword = new DatabaseAccess().selectFromDatabaseString("SELECT UserPassword FROM Users WHERE UserEmail = '" + email + "' AND Username = '" + username + "';");
        //compare it with the given password
        if (DBpassword.equals(password)) {
            verified = true;
        }
        return verified;
    }

    public static boolean verifyUserEmail(User user, String password, String email) {
        boolean verified = false;
        String DBpassword = "";
        
        //check if the email given matches the one they logged in with
        if (email.equals(user.getEmail())) {
            //get the password associated with the given email
            DBpassword = new DatabaseAccess().selectFromDatabaseString("SELECT UserPassword FROM Users WHERE UserEmail = '" + email + "';");
        }
        //compare the two passwords
        if (DBpassword.equals(password)) {
            verified = true;
        }
        return verified;
    }

    public static boolean verifyUserName(User user, String password, String username) {
        boolean verified = false;
        String DBpassword = "";
        if (username.equals(user.getUsername())) {
            DBpassword = new DatabaseAccess().selectFromDatabaseString("SELECT UserPassword FROM Users WHERE Username = '" + username + "';");
        }
        if (DBpassword.equals(password)) {
            verified = true;
        }
        return verified;
    }

    public static String getUserID(String email) {
        String userID = new DatabaseAccess().selectFromDatabaseString("SELECT UserID FROM Users WHERE UserEmail = '" + email + "';");
        return userID;
    }

    public static boolean getStudent(String email) {
        boolean student = false;
        String studentStr = new DatabaseAccess().selectFromDatabaseString("SELECT Student FROM Users WHERE UserEmail = '" + email + "';");
        student = studentStr.equals("1");

        return student;
    }

    public static String getEmail(String username) {
        String email = new DatabaseAccess().selectFromDatabaseString("SELECT UserEmail FROM Users WHERE Username = '" + username + "';");
        return email;
    }

    public static String getUsername(String userID) {
        String username = new DatabaseAccess().selectFromDatabaseString("SELECT Username FROM Users WHERE UserID = '" + userID + "';");
        return username;
    }

    public static String getTopicName(String topicID) {
        String topicName = new DatabaseAccess().selectFromDatabaseString("SELECT TopicName FROM Topics WHERE TopicID = '" + topicID + "';");
        return topicName;
    }

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

    public static ArrayList<String> getAnalytics(String userID, String topicID) {
        //variables declaration
        ArrayList<String> topicExamQIDs = new ArrayList<>();
        ArrayList<String> userExamQIDs = new ArrayList<>();
        ArrayList<String> examQIDs = new ArrayList<>();
        ArrayList<String> percentAccuracies = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();
        double total = 0;
        double mean = 0;
        //to be returned
        ArrayList<String> analytics = new ArrayList<>();

        //get all the exam questions for the topic and add them to a list
        topicExamQIDs = new DatabaseAccess().selectFromDatabaseArrayList("SELECT ExamQID FROM ExamQuestions WHERE TopicID = '" + topicID + "';");

        //get all the exam questions that the user has completed for that topic
        userExamQIDs = new DatabaseAccess().selectFromDatabaseArrayList("SELECT ExamQID FROM Analytics WHERE UserID = '" + userID + "';");

        //compare the user answered questions with the full list of questions
        for (String topicEQID : topicExamQIDs) {
            if (userExamQIDs.contains(topicEQID)) {
                examQIDs.add(topicEQID);
                //examQIDs = list of completed exam question IDs for one particular topic
                //return length of examQIDs for questions attempted
            }
        }
        //for each examQID, get its percentage accuracy(s)
        for (String examQID : examQIDs) {
            temp = new DatabaseAccess().selectFromDatabaseArrayList("SELECT PercentAccuracy FROM Analytics WHERE ExamQID = '" + examQID + "'");
            for (String percent : temp) {
                percentAccuracies.add(percent);
            }
        }
        //get mean percent accuracy
        //add all percentages together
        for(String percent : percentAccuracies){
            total = total + Double.valueOf(percent);
        }
        //divide by total number
        mean = total/percentAccuracies.size();
        
        //return analytics with its first index being questions attempted and second being the mean accuracy
        analytics.add(String.valueOf(examQIDs.size()));
        analytics.add(String.valueOf(mean));
        return analytics;
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
            for (String userEQID : userExamQIDs) {
                if (examIDs.contains(userEQID)) {
                    examIDs.remove(userEQID);
                }
            }
            if (examIDs.isEmpty()) {
                examIDs = new DatabaseAccess().getListOfExamQIDs();
            }

            Random rand = new Random();

            //get random exam question from the list
            String examQID = examIDs.get(rand.nextInt(examIDs.size()));

            topicIDs = new DatabaseAccess().selectFromDatabaseArrayList("SELECT TopicID FROM ExamQuestions WHERE ExamQID = '" + examQID + "';");
            String topicID = topicIDs.get(0);
            examQuestion = new DatabaseAccess().selectFromDatabaseArrayList("SELECT Question, MarksAvailable, TopicName, Answer, ExamQID FROM ExamQuestions, Topics WHERE ExamQuestions.ExamQID = '" + examQID + "' AND Topics.TopicID = '" + topicID + "';");
        } else if (modifier == 2) {
            //by lowest accuracy
            examQuestion = new DatabaseAccess().selectFromDatabaseArrayList("SELECT ExamQID FROM Analytics WHERE UserID = '" + user.getUserID() + "' ORDER BY(PercentAccuracy);");
        } else if (modifier == 3) {
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

    public static String createUser(String email, String username, String password, boolean student) {
        Random rand = new Random();
        String message = "";
        String userID = "US";
        for (int x = 0; x < 4; x++) {
            userID = userID + String.valueOf(rand.nextInt(10));
        }
        if (new DatabaseAccess().existsInDatabase(userID, "Users", "UserID")) {
            while (new DatabaseAccess().existsInDatabase(userID, "Users", "UserID")) {
                userID = "US";
                for (int x = 0; x < 4; x++) {
                    userID = userID + String.valueOf(rand.nextInt(10));
                }
            }
        }
        if (!(new DatabaseAccess().existsInDatabase(username, "Users", "Username"))) {
            if (!(new DatabaseAccess().existsInDatabase(email, "Users", "UserEmail"))) {
                new DatabaseAccess().insertIntoDatabase("INSERT INTO Users VALUES ('" + userID + "', '" + username + "', '" + password + "', '" + email + "', " + student + ")");
            } else {
                message = "An account with this email already exists";
            }
        } else {
            message = "Username has been taken";
        }

        return message;
    }

    public static String createClass(String name, User user) {
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
        return classCode;
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
    
    public static String[] myClasses(User user){
        ArrayList<String> classes = new ArrayList<>();
        ArrayList<String> classNames = new ArrayList<>();
        classes = selectFromDatabaseArrayList("SELECT ClassID FROM ClassMembers WHERE UserID = '"+ user.getUserID()+"';");
        for(String classID : classes){
            classNames.add(selectFromDatabaseString("SELECT ClassName FROM Classes WHERE ClassID = '"+classID+"';"));
        }
        String[] classNamesArray = new String[15];
        int i = -1;
        for(String className : classNames){
            i++;
            classNamesArray[i] = className;
        }
        return classNamesArray;
    }
    
    public static String[] teacherViewClass(String classCode, String teacherID){
        ArrayList<String> classMembers = new ArrayList<>();
        ArrayList<String> studentNames = new ArrayList<>();
        String[] studentArray = new String[29];
        String classID = selectFromDatabaseString("SELECT ClassID FROM Classes WHERE ClassCode = '"+classCode+"';");
        classMembers = selectFromDatabaseArrayList("SELECT UserID FROM ClassMembers WHERE ClassID = '"+classID+"';");
        classMembers.remove(teacherID);
        for(String userID : classMembers){
            studentNames.add(selectFromDatabaseString("SELECT Username FROM Users WHERE UserID = '"+userID+"';"));
        }
        int i = -1;
        for(String item : studentNames){
            i++;
            studentArray[i] = item;
        }
        return studentArray;
    }

    public static User logIn(String username, String password) {
        User user = new User("", "");
        String DBpassword = new DatabaseAccess().selectFromDatabaseString("SELECT UserPassword FROM Users WHERE Username = '" + username + "';");
        if (DBpassword.equals(password)) {
            String email = new DatabaseAccess().selectFromDatabaseString("SELECT UserEmail FROM Users WHERE Username = '" + username + "';");
            user = new User(username, email);
            if (new DatabaseAccess().verifyUser(email, username, password)) {
                user = new User(username, email);

            } else {
                user = new User("", "");
            }
        }
        return user;
    }

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

    public static ArrayList<String> findPages(String keyword) {
        ArrayList<String> pageTitles = new DatabaseAccess().selectFromDatabaseArrayList("SELECT PageTitle FROM InformationPages WHERE PageContent LIKE '%" + keyword + "%'");
        return pageTitles;
    }

    public static ArrayList<String> getPageInfo(String title) {
        ArrayList<String> pageInfo = new DatabaseAccess().selectFromDatabaseArrayList("SELECT PageTitle, PageContent FROM InformationPages WHERE PageTitle = '" + title + "'");

        return pageInfo;
    }

    public static int[] findPositions(String valueName1, String valueName2) {
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
        int valuePosition1 = Integer.valueOf(new DatabaseAccess().selectFromDatabaseString("SELECT Position FROM ValuePositions WHERE TypeID = '" + valueID1 + "' AND EquationID = '" + equationID + "';"));
        int valuePosition2 = Integer.valueOf(new DatabaseAccess().selectFromDatabaseString("SELECT Position FROM ValuePositions WHERE TypeID = '" + valueID2 + "' AND EquationID = '" + equationID + "';"));
        positions[0] = valuePosition1;
        positions[1] = valuePosition2;
        return positions;
    }
    
    public static ArrayList<String> getConstant(String valueName){
        //get typeid using name
        String typeID = new DatabaseAccess().selectFromDatabaseString("SELECT TypeID FROM ValueTypes WHERE ValueName = '"+valueName+"';");
        //get constant value & magnitude using typeid
        ArrayList<String> constantData = selectFromDatabaseArrayList("SELECT ActualValue, SFMagnitude FROM Constants WHERE TypeID = '"+typeID+"';");
        return constantData;
    }

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
