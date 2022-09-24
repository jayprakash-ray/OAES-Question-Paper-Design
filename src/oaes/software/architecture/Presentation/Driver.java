package oaes.software.architecture.Presentation;

import oaes.software.architecture.Business.AssesmentCenter;
import oaes.software.architecture.Business.ExamPattern;
import oaes.software.architecture.Business.QuestionPaper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import static oaes.software.architecture.Business.ExamPattern.showPatterns;

public class Driver {
    public static void main(String args[]) throws SQLException {
        int choice;
        Scanner scanner = new Scanner(System.in);
//        int patternCount = 0;
//        int qpCount = 0;
        System.out.println("Welcome to Online Assessment and Evaluation System !");
        ArrayList<ExamPattern> examPatterns=new ArrayList<ExamPattern>();
        ArrayList<QuestionPaper> questionPapers =new ArrayList<QuestionPaper>();
        ArrayList<AssesmentCenter> assesmentCenters = new ArrayList<AssesmentCenter>();
        initializeAssessmentCenters(assesmentCenters);
        do{

            System.out.println("1. Generate Question Paper Pattern");
            System.out.println("2. Show Existing Patterns");
            System.out.println("3. Generate Question Paper");
            System.out.println("4. Deliver Question Paper to Assessment Centers");
            System.out.println("5. Show Question Paper Delivery Status");
            System.out.println("6. Exit");
            System.out.println("Enter Your Choice : ");
            choice = scanner.nextInt();

            switch (choice)
            {
                case 1 :
                    ExamPattern pattern = new ExamPattern();
                    pattern.getExamPattern();
                    if(pattern.getMcqCount()*2+ pattern.getMsqCount()*3+ pattern.getDescCount()*5 != pattern.getTotalMarks()) {
                        System.out.println("Total Marks is not correct ! Please check.");
                        break;
                    }
                    System.out.println("Exam Pattern Created with ID " +pattern.getPatternName());
                    examPatterns.add(pattern);
                    break;
                case 2:
                    showPatterns(examPatterns);
                    break;
                case 3:
                    if(examPatterns.size() == 0)
                    {
                        System.out.println("No Patterns Available :( Please Create Exam Pattern to Generate Question Papers!");
                        break;
                    }
                    showPatterns(examPatterns);
                    System.out.println("Select Exam Pattern Serial No. to Proceed :");
                    int patternIndex = scanner.nextInt();
                    ExamPattern selectedPattern = examPatterns.get(patternIndex-1);
                    QuestionPaper qp = new QuestionPaper();
                    System.out.println("selected" + selectedPattern.getPatternName());
                    qp.generateQuestionPaper(selectedPattern);
                    if(qp == null)
                        break;
                    questionPapers.add(qp);
                    qp.displayQuestionPaper();
                    break;
                case 4:
                    if(questionPapers.size()==0)
                    {
                        System.out.println("No Question Paper Available :( Please Generate Question Papers to proceed!");
                        break;
                    }
                    showDeliveryStatus(assesmentCenters);
                    System.out.println("Question Paper Delivery in Progress......");
                    System.out.println("");
                    System.out.println("-----------------------------------------------");
                    deliverPapers(questionPapers,assesmentCenters);
                    showDeliveryStatus(assesmentCenters);
                    System.out.println("-----------------------------------------------");
                    System.out.println("");
                    System.out.println("Delivery Successful !");
                    break;
                case 5:
                    showDeliveryStatus(assesmentCenters);
                    break;
            }
        }while(choice != 6);

//        ExamPattern examPattern = new ExamPattern();
//        getExamPattern(examPattern);

    }
    static void deliverPapers(ArrayList<QuestionPaper> questionPapers,ArrayList<AssesmentCenter> assesmentCenters)
    {
            for(int j= 0 ; j<assesmentCenters.size(); j++){
                assesmentCenters.get(j).setQuestionPapers(questionPapers);
                assesmentCenters.get(j).setQuestionPaperAvailable(true);
            }
    }
    static void showDeliveryStatus(ArrayList<AssesmentCenter> assesmentCenters)
    {
        System.out.println("Center Code     Center Name     QuestionPaper Available?");
        for(int i = 0 ; i<assesmentCenters.size(); i++){
            System.out.println(assesmentCenters.get(i).getCenterID()+"                  "+assesmentCenters.get(i).getCenterName()+"                 "+assesmentCenters.get(i).isQuestionPaperAvailable());
        }
    }
    static void initializeAssessmentCenters(ArrayList<AssesmentCenter> assesmentCenters)
    {
        AssesmentCenter assesmentCenter1 = new AssesmentCenter(101,"Pune",false,null);
        AssesmentCenter assesmentCenter2 = new AssesmentCenter(102,"Bangalore",false,null);
        AssesmentCenter assesmentCenter3 = new AssesmentCenter(103,"Nashik",false,null);
        assesmentCenters.add(assesmentCenter1);
        assesmentCenters.add(assesmentCenter2);
        assesmentCenters.add(assesmentCenter3);
    }

//
//    static Msq[] getMsqs(ExamPattern examPattern) throws SQLException {
//        String query = "SELECT * FROM msqs WHERE subject = \'" + examPattern.getSubject() + "\' ORDER BY RAND() LIMIT " + examPattern.getMcqCount();
//        Msq[] msqs = new Msq[examPattern.getMsqCount()];
//        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//             Statement stmt = conn.createStatement();
//        ) {
//
//            ResultSet resultSet = stmt.executeQuery(query);
//            int index = 0;
//            while (resultSet.next()) {
//                String topic = resultSet.getString("topic");
//                String subject = resultSet.getString("subject");
//                String qType = "MSQ";
//                String questionText = resultSet.getString("question_text");
//                String options = resultSet.getString("options");
//                String ans = resultSet.getString("ans");
//                int marks = resultSet.getInt("marks");
//                Msq question = new Msq(String.valueOf(index), topic, subject, qType, questionText, options, ans, marks);
//                msqs[index++] = question;
//            }
//            if(index < examPattern.getMsqCount())
//            {
//                System.out.println("Insufficient MSQ Available in Item Bank");
//                System.out.println("Total Required :"+examPattern.getMsqCount() +"  Total Available : "+index);
//                return null;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return msqs;
//    }
//    static Desc[] getDesc(ExamPattern examPattern) throws SQLException {
//        String query = "SELECT * FROM msqs WHERE subject = \'" + examPattern.getSubject() + "\' ORDER BY RAND() LIMIT " + examPattern.getDescCount();
//        Desc[] descs = new Desc[examPattern.getDescCount()];
//        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//             Statement stmt = conn.createStatement();
//        ) {
//
//            ResultSet resultSet = stmt.executeQuery(query);
//            int index = 0;
//            while (resultSet.next()) {
//                String topic = resultSet.getString("topic");
//                String subject = resultSet.getString("subject");
//                String qType = "DESC";
//                String questionText = resultSet.getString("question_text");
////                String options = resultSet.getString("options");
////                String ans = resultSet.getString("ans");
//                int marks = resultSet.getInt("marks");
//                Desc question = new Desc(String.valueOf(index), topic, subject, qType, questionText,marks);
//                descs[index++] = question;
//            }
//            if(index < examPattern.getDescCount())
//            {
//                System.out.println("Insufficient Descriptive Questions Available in Item Bank");
//                System.out.println("Total Required :"+examPattern.getMcqCount() +"  Total Available : "+index);
//                return null;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return descs;
//    }

}
