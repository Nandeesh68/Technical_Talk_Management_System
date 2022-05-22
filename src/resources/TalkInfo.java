package resources;

public class TalkInfo {

    Integer serialNo;
    String academicYear;
    String dateTime;
    String duration;
    String personName;
    String topicTitle;
    String keyPoints;

    public TalkInfo()
    {

    }

    public TalkInfo(Integer serialNo, String academicYear, String dateTime, String duration, String personName, String topicTitle, String keyPoints){
        this.serialNo = serialNo;
        this.academicYear = academicYear;
        this.dateTime = dateTime;
        this.duration = duration;
        this.personName = personName;
        this.topicTitle = topicTitle;
        this.keyPoints = keyPoints;
    }

    public void setSerialNo(Integer serialNo){
        this.serialNo =  serialNo;
    }
    public void setAcademicYear(String academicYear){
        this.academicYear =  academicYear;
    }
    public void setDateTime(String dateTime){
        this.dateTime =  dateTime;
    }
    public void setDuration(String duration){
        this.duration = duration;

    }
    public void setPersonName(String personName){
        this.personName =  personName;
    }
    public void setTopicTitle(String topicTitle){
        this.topicTitle = topicTitle;
    }
    public void setKeyPoints(String keyPoints){
        this.keyPoints =  keyPoints;
    }

    public Integer getSerialNo(){
        return serialNo;
    }
    public String getAcademicYear(){
        return academicYear;
    }
    public String getDateTime(){
        return dateTime;
    }
    public String getDuration(){
        return duration;
    }
    public String getPersonName(){
        return personName;
    }
    public String getTopicTitle(){
        return topicTitle;
    }
    public String getKeyPoints(){
        return keyPoints;
    }

}
