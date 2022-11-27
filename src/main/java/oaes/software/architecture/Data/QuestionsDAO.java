package oaes.software.architecture.Data;

import oaes.software.architecture.Business.ExamPattern;
import oaes.software.architecture.Business.QuestionTypes.Desc;
import oaes.software.architecture.Business.QuestionTypes.Mcq;
import oaes.software.architecture.Business.QuestionTypes.Msq;
import oaes.software.architecture.Business.Questions;

import java.sql.*;
import java.util.ArrayList;

public class QuestionsDAO implements JdbcConnection {
    String DB_URL;
    String USER;
    String PASS;

    public QuestionsDAO(String db_url, String User, String pass) {
        this.DB_URL = db_url;//"jdbc:mysql://localhost/oaes_question_bank";
        this.USER = User;//"root";
        this.PASS = pass;//"Jay@1998";
    }

    public ArrayList<Questions> getQuestionByType(ExamPattern examPattern, String type) throws ClassNotFoundException {
        String table = "mcqs";
        int count = 0;
        if (type == "MCQ") {
            table = "mcqs";
            count = examPattern.getMcqCount();
        } else if (type == "MSQ") {
            table = "msqs";
            count = examPattern.getMsqCount();
        } else {
            table = "descriptive";
            count = examPattern.getDescCount();
        }
//        Class.forName("com.mysql.cj.jdbc.Driver");
        String query = "SELECT * FROM " + table + " WHERE subject = \'" + examPattern.getSubject() + "\' ORDER BY RAND() LIMIT " + count;
        ArrayList<Questions> qs = new ArrayList<Questions>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
//            System.out.println("Connected to DB");
            ResultSet resultSet = stmt.executeQuery(query);
            int index = 0;

            //Iterator Design Pattern
            while (resultSet.next()) {
                String topic = resultSet.getString("topic");
                String subject = resultSet.getString("subject");
                String qType = type;
                String questionText = resultSet.getString("question_text");


                int marks = resultSet.getInt("marks");
                if (type == "MCQ") {
                    String options = resultSet.getString("options");
                    String ans = resultSet.getString("ans");
                    Questions question = new Mcq(String.valueOf(index), topic, subject, qType, questionText, marks, options, ans);
                    qs.add(question);
                } else if (type == "MSQ") {
                    String options = resultSet.getString("options");
                    String ans = resultSet.getString("ans");
                    Questions question = new Msq(String.valueOf(index), topic, subject, qType, questionText, marks, options, ans);
                    qs.add(question);
                } else if (type == "DESC") {
                    Questions question = new Desc(String.valueOf(index), topic, subject, qType, questionText, marks);
                    qs.add(question);
                }
                index++;
            }
            System.out.println("qs size" + qs.size());
            if (index < count) {
                System.out.println("Insufficient " + type + " Available in Item Bank");
                System.out.println("Total Required :" + count + "  Total Available : " + index);
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return qs;
    }

    @Override
    public int addExamPattern(ExamPattern examPattern) {
        String query = "INSERT INTO oaes_question_bank.exam_pattern VALUES (\'" + examPattern.getPatternID() + "\',\'" + examPattern.getPatternName()
                + "\',\'" + examPattern.getSubject() + "\'," + examPattern.getTotalMarks() + "," + examPattern.getMcqCount() + "," + examPattern.getMsqCount()
                + "," + examPattern.getDescCount() + ")";
//        System.out.println(query);
        int row = 0;
        try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
            row = stmt.executeUpdate(query);


            //Iterator Design Pattern

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return row;
    }

    public ArrayList<ExamPattern> getExamPatterns() {
        String query = "Select * from oaes_question_bank.exam_pattern";
        ArrayList<ExamPattern> examPatterns = new ArrayList<>();
//        String query = "SELECT * FROM "+ table +" WHERE subject = \'"+examPattern.getSubject()+"\' ORDER BY RAND() LIMIT "+ count;
        try {
//            System.out.println("Connected to DB");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                ExamPattern examPattern = new ExamPattern();
                examPattern.setPatternID(resultSet.getString("pattern_id"));
                examPattern.setPatternName(resultSet.getString("pattern_name"));
                examPattern.setSubject(resultSet.getString("course_name"));
                examPattern.setTotalMarks(resultSet.getInt("total_marks"));
                examPattern.setMcqCount(resultSet.getInt("no_of_mcq"));
                examPattern.setMsqCount(resultSet.getInt("no_of_msq"));
                examPattern.setDescCount(resultSet.getInt("no_of_desc"));
//                System.out.println("FRom DB" + resultSet.getString("pattern_id"));
                examPatterns.add(examPattern);
            }

            //Iterator Design Pattern

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        System.out.println(examPatterns.size());
//        if(examPatterns == null)
//            System.out.println("NULL BRO");
        return examPatterns;
    }

}
