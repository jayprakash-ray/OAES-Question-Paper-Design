package oaes.software.architecture;
import oaes.software.architecture.QuestionTypes.Desc;
import oaes.software.architecture.QuestionTypes.Mcq;
import oaes.software.architecture.QuestionTypes.Msq;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import static oaes.software.architecture.JdbcConnect.*;

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
                    pattern = getExamPattern();
                    if(pattern == null)
                        break;
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
                    qp = generateQuestionPaper(selectedPattern);
                    if(qp == null)
                        break;
                    questionPapers.add(qp);
                    displayQuestionPaper(qp);
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
    static ExamPattern getExamPattern()
    {
        ExamPattern examPattern = new ExamPattern();
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        examPattern.setPatternID("EXAM101");
        System.out.println("Enter Exam Pattern Name");
        examPattern.setPatternName(scanner.nextLine());
        System.out.println("Enter Course Name");
        examPattern.setSubject(scanner.nextLine());
        System.out.println("Enter Total Marks");
        examPattern.setTotalMarks(scanner.nextInt());
        System.out.println("Enter No of MCQ :");
        examPattern.setMcqCount(scanner.nextInt());
        System.out.println("Enter No of MSQ :");
        examPattern.setMsqCount(scanner.nextInt());
        System.out.println("Enter No of Descriptive Questions :");
        examPattern.setDescCount(scanner.nextInt());
        if(examPattern.getMcqCount()*2+ examPattern.getMsqCount()*3+ examPattern.getDescCount()*5 != examPattern.getTotalMarks())
        {
            System.out.println("Total Marks is not correct ! Please check.");
            return null;
        }
        return examPattern;
    }
    static Mcq[] getMcqs(ExamPattern examPattern) throws SQLException{
        String query = "SELECT * FROM mcqs WHERE subject = \'"+examPattern.getSubject()+"\' ORDER BY RAND() LIMIT "+ examPattern.getMcqCount();
        Mcq[] mcqs = new Mcq[examPattern.getMcqCount()];
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
        ) {
//            System.out.println("Connected to DB");
            ResultSet resultSet = stmt.executeQuery(query);
            int index = 0;

            while (resultSet.next()) {
                String topic = resultSet.getString("topic");
                String subject = resultSet.getString("subject");
                String qType ="MCQ";
                String questionText = resultSet.getString("question_text");
                String options = resultSet.getString("options");
                String ans = resultSet.getString("ans");
                int marks = resultSet.getInt("marks");
               Mcq question = new Mcq(String.valueOf(index),topic,subject,qType,questionText,options,ans,marks);
                mcqs[index++] = question;
            }
            if(index < examPattern.getMcqCount())
            {
                System.out.println("Insufficient MCQ Available in Item Bank");
                System.out.println("Total Required :"+examPattern.getMcqCount() +"  Total Available : "+index);
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mcqs;
    }
    static Msq[] getMsqs(ExamPattern examPattern) throws SQLException {
        String query = "SELECT * FROM msqs WHERE subject = \'" + examPattern.getSubject() + "\' ORDER BY RAND() LIMIT " + examPattern.getMcqCount();
        Msq[] msqs = new Msq[examPattern.getMsqCount()];
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
        ) {

            ResultSet resultSet = stmt.executeQuery(query);
            int index = 0;
            while (resultSet.next()) {
                String topic = resultSet.getString("topic");
                String subject = resultSet.getString("subject");
                String qType = "MSQ";
                String questionText = resultSet.getString("question_text");
                String options = resultSet.getString("options");
                String ans = resultSet.getString("ans");
                int marks = resultSet.getInt("marks");
                Msq question = new Msq(String.valueOf(index), topic, subject, qType, questionText, options, ans, marks);
                msqs[index++] = question;
            }
            if(index < examPattern.getMsqCount())
            {
                System.out.println("Insufficient MSQ Available in Item Bank");
                System.out.println("Total Required :"+examPattern.getMsqCount() +"  Total Available : "+index);
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return msqs;
    }
    static Desc[] getDesc(ExamPattern examPattern) throws SQLException {
        String query = "SELECT * FROM msqs WHERE subject = \'" + examPattern.getSubject() + "\' ORDER BY RAND() LIMIT " + examPattern.getDescCount();
        Desc[] descs = new Desc[examPattern.getDescCount()];
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
        ) {

            ResultSet resultSet = stmt.executeQuery(query);
            int index = 0;
            while (resultSet.next()) {
                String topic = resultSet.getString("topic");
                String subject = resultSet.getString("subject");
                String qType = "DESC";
                String questionText = resultSet.getString("question_text");
//                String options = resultSet.getString("options");
//                String ans = resultSet.getString("ans");
                int marks = resultSet.getInt("marks");
                Desc question = new Desc(String.valueOf(index), topic, subject, qType, questionText,marks);
                descs[index++] = question;
            }
            if(index < examPattern.getDescCount())
            {
                System.out.println("Insufficient Descriptive Questions Available in Item Bank");
                System.out.println("Total Required :"+examPattern.getMcqCount() +"  Total Available : "+index);
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return descs;
    }
    public static QuestionPaper generateQuestionPaper(ExamPattern examPattern) throws SQLException {
        QuestionPaper questionPaper = new QuestionPaper();
        int noOfMcq = examPattern.getMcqCount();
        int noOfmsq = examPattern.getMsqCount();
        int noOfdesc = examPattern.getDescCount();

        if(noOfMcq>0)
        {
            Mcq[] mcqs = new Mcq[noOfMcq];
            mcqs = getMcqs(examPattern);
            if(mcqs == null) {

                return null;
            }
            questionPaper.setMcqs(mcqs);
        }
        if(noOfmsq>0)
        {
            Msq[] msqs = new Msq[noOfmsq];
            msqs=getMsqs(examPattern);
            if(msqs == null) {
                return null;
            }
            questionPaper.setMsqs(msqs);
        }
        if(noOfdesc > 0){
            Desc[] desc = new Desc[noOfdesc];
            desc=getDesc(examPattern);
            if(desc == null) {
                return null;
            }
            questionPaper.setDesc(desc);
        }
        questionPaper.setExamPattern(examPattern);
        questionPaper.setSetId('A');
        questionPaper.setQpName(examPattern.getPatternName()+":"+examPattern.getSubject());
        return questionPaper;
    }
    public static void showPatterns(  ArrayList<ExamPattern> examPatterns){
        System.out.println("-------------Existing Exam Patterns--------------");
        for(int i = 0 ; i<examPatterns.size(); i++){
            System.out.println(String.valueOf(i+1)+"." + examPatterns.get(i).getPatternName()) ;
        }
        System.out.println("--------------------------------------------------");
    }
    public static void displayQuestionPaper(QuestionPaper qp){
        int index = 1;
        System.out.println("------------"+qp.getExamPattern().getPatternName()+"-----------------------");
        System.out.println("Course Name :" + qp.getExamPattern().getSubject() +"     "+"Total Marks : " + qp.getExamPattern().getTotalMarks());
        System.out.println("Set :" + qp.getSetId());
        System.out.println("---------------------------------------------------------------------------");
        if(qp.getExamPattern().getMcqCount()>0)
        {
            System.out.println("Section : Multiple Choice Questions [2 Marks Each]");
            Mcq[] mcqs = qp.getMcqs();
            for(int i = 0 ; i<mcqs.length; i++)
            {
                System.out.println(String.valueOf(index)+"."+mcqs[i].getQuestionText());
                System.out.println(mcqs[i].getOptions());
                System.out.println("");
                index++;
            }
        }
        System.out.println("-------------------------------------------------------------------");
        if(qp.getExamPattern().getMsqCount()>0)
        {
            System.out.println("Section : Multiple Select Questions  [3 Marks Each]");
            Msq[] msqs = qp.getMsqs();
            for(int i = 0 ; i<msqs.length; i++)
            {
                System.out.println(String.valueOf(index)+"."+msqs[i].getQuestionText());
                System.out.println(msqs[i].getOptions());
                System.out.println("");
                index++;
            }
        }

        System.out.println("-------------------------------------------------------------------");
        if(qp.getExamPattern().getDescCount()>0)
        {
            System.out.println("Section : Descriptive Questions [5 Marks Each]");
            Desc[] desc = qp.getDesc();
            for(int i = 0 ; i<desc.length; i++)
            {
                System.out.println(String.valueOf(index)+"."+desc[i].getQuestionText());
                System.out.println("");
                index++;
            }
        }

    }

}
