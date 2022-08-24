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

    /**
     *
     * @param qid  -> Unique Question Id
     * @param topic -> topic to which this question belongs
     * @param subject -> Course Name
     * @param qType -> question Type (MCQ,MSQ,DESC)
     */
    public Questions(String qid, String topic, String subject, String qType) {
        this.qid = qid;
        this.topic = topic;
        this.subject = subject;
        this.qType = qType;
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
}
