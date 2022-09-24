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

    public QuestionsDAO(String db_url, String User, String pass)
    {
        this.DB_URL = db_url;//"jdbc:mysql://localhost/oaes_question_bank";
        this.USER = User;//"root";
        this.PASS =pass;//"Jay@1998";
    }

    public ArrayList<Questions> getQuestionByType(ExamPattern examPattern, String type) {
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
            table = "descriptive";
            count = examPattern.getDescCount();
        }

        String query = "SELECT * FROM "+ table +" WHERE subject = \'"+examPattern.getSubject()+"\' ORDER BY RAND() LIMIT "+ count;
        ArrayList<Questions> qs = new ArrayList<Questions>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
        ) {
//            System.out.println("Connected to DB");
            ResultSet resultSet = stmt.executeQuery(query);
            int index = 0;

            //Iterator Design Pattern
            while (resultSet.next()) {
                String topic = resultSet.getString("topic");
                String subject = resultSet.getString("subject");
                String qType =type;
                String questionText = resultSet.getString("question_text");


                int marks = resultSet.getInt("marks");
                if(type == "MCQ")
                {
                    String options = resultSet.getString("options");
                    String ans = resultSet.getString("ans");
                    Questions question = new Mcq(String.valueOf(index),topic,subject,qType,questionText,marks,options,ans);
                    qs.add(question);
                }
                else if(type == "MSQ")
                {
                    String options = resultSet.getString("options");
                    String ans = resultSet.getString("ans");
                    Questions question = new Msq(String.valueOf(index),topic,subject,qType,questionText,marks,options,ans);
                    qs.add(question);
                }
                else if(type == "DESC")
                {
                    Questions question = new Desc(String.valueOf(index),topic,subject,qType,questionText,marks);
                    qs.add(question);
                }
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
