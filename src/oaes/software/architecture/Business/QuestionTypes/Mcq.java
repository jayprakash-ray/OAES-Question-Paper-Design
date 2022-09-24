package oaes.software.architecture.Business.QuestionTypes;

import oaes.software.architecture.Business.ExamPattern;
import oaes.software.architecture.Business.QuestionIterator;
import oaes.software.architecture.Business.Questions;
import oaes.software.architecture.Data.JdbcConnection;
import oaes.software.architecture.Data.QuestionDaoFactory;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Mcq Class inherits Questions class.
 * It respresents a format in which mcq question will be manipulated i.e blueprint for MCQ Type Questions.
 */
public class Mcq implements Questions {
    private String qid;
    private String topic;
    private String subject;
    private String qType;
    private String QuestionText;
    private int marks;
    private String options;
    private String ans;

    public Mcq(String qid, String topic, String subject, String qType, String questionText, int marks, String options, String ans) {
        this.qid = qid;
        this.topic = topic;
        this.subject = subject;
        this.qType = qType;
        QuestionText = questionText;
        this.marks = marks;
        this.options = options;
        this.ans = ans;
    }

    /**
     * @param qid -> unique question id of a question
     * @param questionText -> Question Text
     * @param options -> Options for the Question
     * @param ans -> Correct Answer for the question
     * @param marks -> marks associated with the question
     */



    public Mcq() {
    }

    public ArrayList<Questions> getQuestions(ExamPattern examPattern) throws SQLException {
        JdbcConnection jdbcConnection = new QuestionDaoFactory();
        //Delegation of Heavy Object -> Proxy Pattern
        return jdbcConnection.getQuestionByType(examPattern,"MCQ");
    }
    public static QuestionIterator createIterator(ArrayList<Questions> questions)
    {
        return new QuestionIterator(questions);
    }
    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getQuestionText() {
        return QuestionText;
    }
}
