package edu.ucf.cop4331.supersweetsurveyapp;

/**
 *  A single question in a survey
 */
public class SurveyQuestion {

    String questionId, qTypeId, questionText;
    String option1, option2, option3, option4;

    public SurveyQuestion(){
    }

    public void setOption1(String option1){
        this.option1 = option1;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public void setTFOptions(){
        option1 = "True";
        option2 = "False";
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getqTypeId() {
        return qTypeId;
    }

    public void setqTypeId(String qTypeId) {
        this.qTypeId = qTypeId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
}