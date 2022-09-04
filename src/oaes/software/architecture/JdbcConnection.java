package oaes.software.architecture;

import java.util.ArrayList;

public interface JdbcConnection {
    public
    ArrayList<Questions> getQuestionByType(ExamPattern examPattern, String type);
}
