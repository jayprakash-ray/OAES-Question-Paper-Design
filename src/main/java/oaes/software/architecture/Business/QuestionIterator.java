package oaes.software.architecture.Business;

import java.util.ArrayList;
import java.util.Iterator;

public class QuestionIterator implements Iterator {
    ArrayList<Questions> questionList;
    int pos = 0;

    public QuestionIterator(ArrayList<Questions> questionList)
    {
        this.questionList = questionList;
    }

    @Override
    public Object next() {
        Object question = questionList.get(pos);
        pos = pos + 1;
        return question;
    }
    public boolean hasNext()
    {
        if (pos >= questionList.size())
            return false;
        else
            return true;
    }
}
