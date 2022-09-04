package oaes.software.architecture;

import java.util.ArrayList;

public class JdbcConnectionProxy implements JdbcConnection {
    private static JdbcConnection jdbcConnection;

    @Override
    public ArrayList<Questions> getQuestionByType(ExamPattern examPattern, String type) {
        ArrayList<Questions> questions = null;
        if(jdbcConnection == null)
        {
            jdbcConnection = new JdbcConnectionImpl("jdbc:mysql://localhost/oaes_question_bank","root","Jay@1998");
        }
        if(type == "MCQ"){
            questions = jdbcConnection.getQuestionByType(examPattern,"MCQ");
        }
        else if(type == "MSQ")
        {
           questions = jdbcConnection.getQuestionByType(examPattern,"MSQ");
        }
        else
        {
            questions = jdbcConnection.getQuestionByType(examPattern,"DESC");
        }
        return questions;
    }
}
