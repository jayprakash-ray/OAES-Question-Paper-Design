package oaes.software.architecture;

import oaes.software.architecture.QuestionTypes.Mcq;
import oaes.software.architecture.QuestionTypes.Msq;

import java.sql.*;
import java.util.ArrayList;

public class JdbcConnect {
    static final String DB_URL = "jdbc:mysql://localhost/oaes_question_bank";
    static final String USER = "root";
    static final String PASS = "Jay@1998";

     public ArrayList<Questions> getQuestionByType(ExamPattern examPattern, String type) throws SQLException{
         String table = "mcqs";
         int count = 0;
         if(type == "MCQ")
         {
             table = "mcqs";
             count = examPattern.getMcqCount();
         }
         else if(type == "MSQ"){
             table = "msqs";
             count = examPattern.getMsqCount();
         }
         else{
             table = "Desc";
             count = examPattern.getDescCount();
         }

         String query = "SELECT * FROM "+ table +" WHERE subject = \'"+examPattern.getSubject()+"\' ORDER BY RAND() LIMIT "+ examPattern.getMcqCount();
         ArrayList<Questions> qs = new ArrayList<Questions>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
        ) {
            System.out.println("Connected to DB");
            ResultSet resultSet = stmt.executeQuery(query);
            int index = 0;

            while (resultSet.next()) {
                String topic = resultSet.getString("topic");
                String subject = resultSet.getString("subject");
                String qType =type;
                String questionText = resultSet.getString("question_text");
                String options = resultSet.getString("options");
                String ans = resultSet.getString("ans");
                int marks = resultSet.getInt("marks");
                Questions question = new Msq(String.valueOf(index),topic,subject,qType,questionText,marks,options,ans);
                qs.add(question);
//                if(type == "MCQ")
//                {
//                    Questions question = new Mcq(String.valueOf(index),topic,subject,qType,questionText,marks,options,ans);
//                    qs.add(question);
//                }
//                if(type == "MSQ")
//                {
//
//                }
                index++;
            }
            System.out.println("qs size" + qs.size());
            if(index < count)
            {
                System.out.println("Insufficient "+ type + " Available in Item Bank");
                System.out.println("Total Required :"+count +"  Total Available : "+index);
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return qs;
    }
}
