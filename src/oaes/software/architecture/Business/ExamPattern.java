package oaes.software.architecture.Business;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Author : Group 19
 * This class gives a format of creating a exam pattern based on which a question paper will be generated.
 */
public class ExamPattern {
    private String patternID;
    private String patternName;
    private int totalMarks;
    private int mcqCount;
    private int msqCount;
    private int descCount;
    private String subject;
    private String topics;

    /**
     *
     * @param patternID  : unique identifier for a pattern
     * @param patternName : Name of the exam pattern
     * @param mcqCount : No of MCQ to be included in the Question Paper
     * @param msqCount : No of MSQ to be included in the Question Paper
     * @param descCount :No of Descriptive Type Questions to be included in the Question Paper
     * @param subject : Course Name for which Question Paper have to be generated
     * @param topics : topic from course from which questions to be taken;
     */
    public ExamPattern(String patternID, String patternName, int totalMarks, int mcqCount, int msqCount, int descCount, String subject, String topics) {
        this.patternID = patternID;
        this.patternName = patternName;
        this.totalMarks = totalMarks;
        this.mcqCount = mcqCount;
        this.msqCount = msqCount;
        this.descCount = descCount;
        this.subject = subject;
        this.topics = topics;
    }

    public ExamPattern() {
    }

    public String getPatternID() {
        return patternID;
    }

    public void setPatternID(String patternID) {
        this.patternID = patternID;
    }

    public String getPatternName() {
        return patternName;
    }

    public void setPatternName(String patternName) {
        this.patternName = patternName;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public int getMcqCount() {
        return mcqCount;
    }

    public void setMcqCount(int mcqCount) {
        this.mcqCount = mcqCount;
    }

    public int getMsqCount() {
        return msqCount;
    }

    public void setMsqCount(int msqCount) {
        this.msqCount = msqCount;
    }

    public int getDescCount() {
        return descCount;
    }

    public void setDescCount(int descCount) {
        this.descCount = descCount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    @Override
    public String toString() {
        return "ExamPattern{" +
                "patternID='" + patternID + '\'' +
                ", patternName='" + patternName + '\'' +
                ", totalMarks=" + totalMarks +
                ", mcqCount=" + mcqCount +
                ", msqCount=" + msqCount +
                ", descCount=" + descCount +
                ", subject='" + subject + '\'' +
                ", topics='" + topics + '\'' +
                '}';
    }
    public void getExamPattern()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        this.setPatternID("EXAM101");
        System.out.println("Enter Exam Pattern Name");
        this.setPatternName(scanner.nextLine());
        System.out.println("Enter Course Name");
        this.setSubject(scanner.nextLine());
        System.out.println("Enter Total Marks");
        this.setTotalMarks(scanner.nextInt());
        System.out.println("Enter No of MCQ :");
        this.setMcqCount(scanner.nextInt());
        System.out.println("Enter No of MSQ :");
        this.setMsqCount(scanner.nextInt());
        System.out.println("Enter No of Descriptive Questions :");
        this.setDescCount(scanner.nextInt());
    }
    public static void showPatterns(ArrayList<ExamPattern> examPatterns){
        System.out.println("-------------Existing Exam Patterns--------------");
        for(int i = 0 ; i<examPatterns.size(); i++){
            System.out.println(String.valueOf(i+1)+"." + examPatterns.get(i).getPatternName()) ;
        }
        System.out.println("--------------------------------------------------");
    }

}
