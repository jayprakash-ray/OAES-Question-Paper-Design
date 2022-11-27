package oaes.software.architecture.Data;

import oaes.software.architecture.Business.ExamPattern;
import oaes.software.architecture.Business.Questions;

import java.util.ArrayList;

public class QuestionDaoFactory implements JdbcConnection {
    private static JdbcConnection jdbcConnection;

    @Override
    public ArrayList<Questions> getQuestionByType(ExamPattern examPattern, String type) throws ClassNotFoundException {
        ArrayList<Questions> questions = null;
        if(jdbcConnection == null)
        {
            jdbcConnection = new QuestionsDAO("jdbc:mysql://localhost/oaes_question_bank","root","Jay@1998");
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

    @Override
    public int addExamPattern(ExamPattern examPattern)
    {
        if(jdbcConnection == null)
        {
            jdbcConnection = new QuestionsDAO("jdbc:mysql://localhost/oaes_question_bank","root","Jay@1998");
        }
        int row= jdbcConnection.addExamPattern(examPattern);
        return row;
    }

    public ArrayList<ExamPattern> getExamPatterns() {
        if(jdbcConnection == null)
        {
            jdbcConnection = new QuestionsDAO("jdbc:mysql://localhost/oaes_question_bank","root","Jay@1998");
        }
        return jdbcConnection.getExamPatterns();
    }
}
