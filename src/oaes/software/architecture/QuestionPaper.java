package oaes.software.architecture;

import oaes.software.architecture.QuestionTypes.Desc;
import oaes.software.architecture.QuestionTypes.Mcq;
import oaes.software.architecture.QuestionTypes.Msq;

/**
 * This class represent format for QuestionPaper
 **/
public class QuestionPaper {
        private int qid;
        private String qpName;
        private char setId;
        private ExamPattern examPattern;
        private Mcq[] mcqs;
        private Msq[] msqs;
        private Desc[] desc;

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
        }

        public QuestionPaper(int qid, String qpName, char setId, ExamPattern examPattern, Mcq[] mcqs, Msq[] msqs, Desc[] desc) {
                this.qid = qid;
                this.qpName = qpName;
                this.setId = setId;
                this.examPattern = examPattern;
                this.mcqs = mcqs;
                this.msqs = msqs;
                this.desc = desc;
        }

        public int getQid() {
                return qid;
        }

        public void setQid(int qid) {
                this.qid = qid;
        }

        public String getQpName() {
                return qpName;
        }

        public void setQpName(String qpName) {
                this.qpName = qpName;
        }

        public char getSetId() {
                return setId;
        }

        public void setSetId(char setId) {
                this.setId = setId;
        }

        public ExamPattern getExamPattern() {
                return examPattern;
        }

        public void setExamPattern(ExamPattern examPattern) {
                this.examPattern = examPattern;
        }

        public Mcq[] getMcqs() {
                return mcqs;
        }

        public void setMcqs(Mcq[] mcqs) {
                this.mcqs = mcqs;
        }

        public Msq[] getMsqs() {
                return msqs;
        }

        public void setMsqs(Msq[] msqs) {
                this.msqs = msqs;
        }

        public Desc[] getDesc() {
                return desc;
        }

        public void setDesc(Desc[] desc) {
                this.desc = desc;
        }
}

