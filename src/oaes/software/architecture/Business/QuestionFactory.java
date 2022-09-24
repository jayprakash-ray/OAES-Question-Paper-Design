package oaes.software.architecture.Business;

import oaes.software.architecture.Business.QuestionTypes.Desc;
import oaes.software.architecture.Business.QuestionTypes.Mcq;
import oaes.software.architecture.Business.QuestionTypes.Msq;

import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionFactory {
    ArrayList<Questions> getQuestions(String qtype, ExamPattern examPattern) throws SQLException {
        ArrayList<Questions> questions = new ArrayList<Questions>();
        if(qtype.equals("MCQ"))
        {
            Mcq mcq = new Mcq();
            questions = mcq.getQuestions(examPattern);
        }
        else if(qtype.equals("MSQ"))
        {
            Msq msq = new Msq();
            questions = msq.getQuestions(examPattern);
        }
        else if(qtype.equals("DESC"))
        {
            Desc desc = new Desc();
            questions = desc.getQuestions(examPattern);
        }
        return questions;
    }
}
