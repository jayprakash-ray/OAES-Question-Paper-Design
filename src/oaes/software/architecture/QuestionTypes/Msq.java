package oaes.software.architecture.QuestionTypes;

import oaes.software.architecture.ExamPattern;
import oaes.software.architecture.JdbcConnect;
import oaes.software.architecture.Questions;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Msq Class inherits Questions class.
 * It respresents a format in which msq question will be manipulated i.e blueprint for MSQ Type Questions.
 */
public class Msq extends Questions {
    private String options;
    private String ans;
    /**
     * @param qid -> unique question id of a question
     * @param questionText -> Question Text
     * @param options -> Options for the Question
     * @param ans -> Correct Answer for the question
     * @param marks -> marks associated with the question
     */

    public Msq(String qid, String topic, String subject, String qType, String questionText, int marks, String options, String ans) {
        super(qid, topic, subject, qType, questionText, marks);
        this.options = options;
        this.ans = ans;
    }

    public Msq() {

    }

    public static ArrayList<Questions> getMsqs(ExamPattern examPattern) throws SQLException {
        JdbcConnect jdbcConnect = new JdbcConnect();
        //Delegation of Heavy Object -> Proxy Pattern
        return jdbcConnect.getQuestionByType(examPattern,"MSQ");
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
}
