package oaes.software.architecture.Data;

import oaes.software.architecture.Business.ExamPattern;
import oaes.software.architecture.Business.Questions;

import java.util.ArrayList;

public interface JdbcConnection {
    public
    ArrayList<Questions> getQuestionByType(ExamPattern examPattern, String type);
}
