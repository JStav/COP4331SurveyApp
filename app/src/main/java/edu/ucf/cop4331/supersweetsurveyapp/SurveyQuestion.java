package edu.ucf.cop4331.supersweetsurveyapp;

/**
 *  A single question in a survey
 */
public class SurveyQuestion {

    String questionId, qTypeId, questionText;
    String option1, option2, option3, option4;

    public SurveyQuestion(){
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

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public String getCheckedId(String option){

        if(option.equals(option4)){
            return "4";
        } else if(option.equals(option2)) {
            return "2";
        } else if(option.equals(option3)){
            return "3";
        } else {                                // Return 1 if as the default if nothing is entered
            return "1";
        }
    }

}
