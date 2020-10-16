//Relationships

public class Partnership {
    private String rID;
    private String person1;
    private String person2;
    private String startDate;
    private String endDate;
    private String marrLocation;

    //Default Constructor
    public Partnership() {
        rID=null;
        person1=null;
        person2=null;
        startDate=null;
        endDate=null;
        marrLocation=null;
    }

    public void setrID(String s) {
        rID=s;
    }
    public String getrID() {
        return rID;
    }
    public void setPerson1(String s) {
        person1=s;
    }
    public String getPerson1() {
        return person1;
    }
    public void setPerson2(String s) {
        person2=s;
    }
    public String getPerson2(String s) {
        return person2;
    }
    public void setStartDate(String s) {
        startDate=s;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setEndDate(String s) {
        endDate=s;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setMarrLocation(String s) {
        marrLocation=s;
    }
    public String getMarrLocation() {
        return marrLocation;
    }
}
