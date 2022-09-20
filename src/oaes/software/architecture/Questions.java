package oaes.software.architecture;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Questions {
    ArrayList<Questions> getQuestions(ExamPattern examPattern) throws SQLException;
}
