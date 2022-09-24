package oaes.software.architecture.Business;

import oaes.software.architecture.Business.QuestionPaper;

import java.util.ArrayList;

/**
 * This class represents Assesment centers from which test is conducted
 */
public class AssesmentCenter {
    private int centerID;
    private String centerName;
    private boolean questionPaperAvailable = false;
    private ArrayList<QuestionPaper> questionPapers;

    /**
     * centerID  -> Unique Id for Assessment Center
     * centerName -> CenterName for Assessment Center
     * questionPaperAvailable -> Shows status os question Paper Delivery
     * questionPapers -> Question Paper Received will be stored here.
     */
    public AssesmentCenter() {
    }

    public AssesmentCenter(int centerID, String centerName, boolean questionPaperAvailable, ArrayList<QuestionPaper> questionPapers) {
        this.centerID = centerID;
        this.centerName = centerName;
        this.questionPaperAvailable = questionPaperAvailable;
        this.questionPapers = questionPapers;
    }

    public int getCenterID() {
        return centerID;
    }

    public void setCenterID(int centerID) {
        this.centerID = centerID;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public boolean isQuestionPaperAvailable() {
        return questionPaperAvailable;
    }

    public void setQuestionPaperAvailable(boolean questionPaperAvailable) {
        this.questionPaperAvailable = questionPaperAvailable;
    }

    public ArrayList<QuestionPaper> getQuestionPapers() {
        return questionPapers;
    }

    public void setQuestionPapers(ArrayList<QuestionPaper> questionPapers) {
        this.questionPapers = questionPapers;
    }
}
