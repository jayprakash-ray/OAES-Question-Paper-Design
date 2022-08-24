package oaes.software.architecture.QuestionTypes;

import oaes.software.architecture.Questions;

/**
 * Msq Class inherits Questions class.
 * It respresents a format in which msq question will be manipulated i.e blueprint for MSQ Type Questions.
 */
public class Msq extends Questions {
    private String questionText;
    private String options;
    private String ans;
    private int marks;
    /**
     * @param qid -> unique question id of a question
     * @param questionText -> Question Text
     * @param options -> Options for the Question
     * @param ans -> Correct Answers for the question (multiple)
     * @param marks -> marks associated with the question
     */
    public Msq(String qid, String topic, String subject, String qType, String questionText, String options, String ans, int marks) {
        super(qid, topic, subject, qType);
        this.questionText = questionText;
        this.options = options;
        this.ans = ans;
        this.marks = marks;
    }



    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
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

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}
