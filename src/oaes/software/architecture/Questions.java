package oaes.software.architecture;

/**
 * This class act as a base class for all different type of questions , various common properties of questions are
 * members of this class
 */
public class Questions {
    private String qid;
    private String topic;
    private String subject;
    private String qType;
    private String QuestionText;
    private int marks;


    /**
     *
     * @param qid  -> Unique Question Id
     * @param topic -> topic to which this question belongs
     * @param subject -> Course Name
     * @param qType -> question Type (MCQ,MSQ,DESC)
     */
    public Questions(String qid, String topic, String subject, String qType, String questionText, int marks) {
        this.qid = qid;
        this.topic = topic;
        this.subject = subject;
        this.qType = qType;
        QuestionText = questionText;
        this.marks = marks;
    }

    public Questions() {

    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getqType() {
        return qType;
    }

    public void setqType(String qType) {
        this.qType = qType;
    }

    public String getQuestionText() {
        return QuestionText;
    }

    public void setQuestionText(String questionText) {
        QuestionText = questionText;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}
