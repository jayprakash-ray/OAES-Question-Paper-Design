package oaes.software.architecture.QuestionTypes;

import oaes.software.architecture.Questions;

/**
 * Desc Class inherits Questions class.
 * It respresents a format in which Descriptive question will be manipulated i.e blueprint for
 * Descriptive Type Questions.
 */
public class Desc extends Questions {

    public Desc(String qid, String topic, String subject, String qType, String questionText, int marks) {
        super(qid, topic, subject, qType, questionText, marks);
    }
}
