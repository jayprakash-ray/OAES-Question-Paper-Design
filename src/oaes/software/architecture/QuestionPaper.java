package oaes.software.architecture;

import oaes.software.architecture.QuestionTypes.Desc;
import oaes.software.architecture.QuestionTypes.Mcq;
import oaes.software.architecture.QuestionTypes.Msq;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class represent format for QuestionPaper
 **/
public class QuestionPaper {
        private int qid;
        private String qpName;
        private char setId;
        private ExamPattern examPattern;
        private ArrayList<ArrayList<Questions>> questions;
        private QuestionFactory questionFactory;


        /**
         * qid -> unique identifier to a Question Paper
         * qpName -> Name of the Question Paper
         * setId -> Set ID of the Question Paper
         * examPattern -> Object of Exampattern class representing Question Paper pattern used to
         *                generate question paper
         * mcqs[] -> contains randomly selected required number of MCQ from Item Bank based on
         *            Paper Pattern Specified
         * msqs[] -> contains randomly selected required number of MSQ from Item Bank based on
         *            Paper Pattern Specified.
         * Desc[] -> contains randomly selected required number of Descriptive Questions from Item Bank based on
         *          Paper Pattern Specified.
         */
        public QuestionPaper() {
                questionFactory = new QuestionFactory();
        }

        public QuestionPaper(int qid, String qpName, char setId, ExamPattern examPattern, ArrayList<ArrayList<Questions>> questions, QuestionFactory questionFactory) {
                this.qid = qid;
                this.qpName = qpName;
                this.setId = setId;
                this.examPattern = examPattern;
                this.questions = questions;
                this.questionFactory = questionFactory;
        }


        public void generateQuestionPaper(ExamPattern examPattern) throws SQLException {
                int noOfMcq = examPattern.getMcqCount();
                int noOfmsq = examPattern.getMsqCount();
//                int noOfdesc = examPattern.getDescCount();
//                System.out.println("In Generate QP");
                QuestionFactory questionFactory = new QuestionFactory();
                if(noOfMcq>0)
                {
                        ArrayList<Questions> qs = questionFactory.getQuestions("MCQ",examPattern);
                        if(qs == null)
                                return;
                        this.questions = new ArrayList<>();
                        this.questions.add(qs);
                }
//                System.out.println("MCQ Generated");
                if(noOfmsq>0)
                {
                        ArrayList<Questions> qs = questionFactory.getQuestions("MSQ",examPattern);
                        if(qs == null)
                                return;
                        if(this.questions == null)
                                this.questions = new ArrayList<>();
                        this.questions.add(qs);
                }
                System.out.println("Size of questions "+ questions.size());
//                if(noOfdesc > 0){
//                        Desc[] desc = new Desc[noOfdesc];
//                        desc=getDesc(examPattern);
//                        if(desc == null) {
//                                return null;
//                        }
//                        questionPaper.setDesc(desc);
//                }
                this.examPattern = examPattern;
                this.setId = 'A';
                this.qpName = examPattern.getPatternName()+":"+examPattern.getSubject();
        }
        public void displayQuestionPaper(){
                int index = 1;
                int qindex = 0;
                System.out.println("------------"+this.examPattern.getPatternName()+"-----------------------");
                System.out.println("Course Name :" + this.examPattern.getSubject() +"     "+"Total Marks : " + this.examPattern.getTotalMarks());
                System.out.println("Set :" + this.setId);
                System.out.println("---------------------------------------------------------------------------");
                if(this.examPattern.getMcqCount()>0)
                {
                        System.out.println("Section : Multiple Choice Questions [2 Marks Each]");
                       ArrayList<Questions> mcqs = this.questions.get(qindex++);
                       //Iterator Design Pattern
                        Iterator<Questions> iterator = (Iterator<Questions>) mcqs;
                        while(iterator.hasNext()){
                                Mcq mcq = (Mcq) iterator.next();
                                System.out.println(String.valueOf(index)+"."+mcq.getQuestionText());
                                System.out.println(mcq.getOptions());
                                System.out.println("");
                                index++;
                        }
//                        for(int i = 0 ; i<mcqs.size(); i++)
//                        {
//                                Mcq mcq = (Mcq) mcqs.get(i);
//                                System.out.println(String.valueOf(index)+"."+mcq.getQuestionText());
//                                System.out.println(mcq.getOptions());
//                                System.out.println("");
//                                index++;
//                        }
                }
                System.out.println("-------------------------------------------------------------------");
                if(this.examPattern.getMsqCount()>0)
                {
                        System.out.println("Section : Multiple Select Questions  [3 Marks Each]");
                        ArrayList<Questions> msqs = this.questions.get(qindex++);
                        for(int i = 0 ; i<msqs.size(); i++)
                        {
                                Msq msq = (Msq) msqs.get(i);
                                System.out.println(String.valueOf(index)+"."+msq.getQuestionText());
                                System.out.println(msq.getOptions());
                                System.out.println("");
                                index++;
                        }
                }

//                System.out.println("-------------------------------------------------------------------");
//                if(this.examPattern.getDescCount()>0)
//
//                {
//                        System.out.println("Section : Descriptive Questions [5 Marks Each]");
//                        Desc[] desc = this.getDesc();
//                        for(int i = 0 ; i<desc.length; i++)
//                        {
//                                System.out.println(String.valueOf(index)+"."+desc[i].getQuestionText());
//                                System.out.println("");
//                                index++;
//                        }
//                }

        }



}

