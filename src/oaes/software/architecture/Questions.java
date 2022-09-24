package oaes.software.architecture;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public interface Questions {
    ArrayList<Questions> getQuestions(ExamPattern examPattern) throws SQLException;

}
