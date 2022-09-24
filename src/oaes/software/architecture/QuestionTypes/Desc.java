package oaes.software.architecture.QuestionTypes;

import oaes.software.architecture.*;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Desc Class inherits Questions class.
 * It respresents a format in which Descriptive question will be manipulated i.e blueprint for
 * Descriptive Type Questions.
 */
public class Desc implements Questions {
    private String qid;
    private String topic;
    private String subject;
    private String qType;
    private String QuestionText;
    private int marks;

    public Desc(String qid, String topic, String subject, String qType, String questionText, int marks) {
        this.qid = qid;
        this.topic = topic;
        this.subject = subject;
        this.qType = qType;
        QuestionText = questionText;
        this.marks = marks;
    }

    public Desc() {
    }

    public ArrayList<Questions> getQuestions(ExamPattern examPattern) throws SQLException {
        JdbcConnection jdbcConnection = new JdbcConnectionProxy();
        //Delegation of Heavy Object -> Proxy Pattern
        return jdbcConnection.getQuestionByType(examPattern,"DESC");
    }
    public static QuestionIterator createIterator(ArrayList<Questions> questions)
    {
        return new QuestionIterator(questions);
    }
    public String getQuestionText() {
        return QuestionText;
    }
}
