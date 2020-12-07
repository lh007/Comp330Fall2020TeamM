package JavaClasses;

import java.util.ArrayList;
import java.util.List;

/**
 * JavaClasses.JavaClasses.Relationship class stores parents and a list of children of the JavaClasses.JavaClasses.Person class.
 */
public class Relationship {
    /*
        Note: Current file order:
        Key/ID, femaleParent, maleParent, startDate, endDate, marriageLocation
     */
    private String relationshipID   = null;
    private Person femaleParent     = null;
    private Person maleParent       = null;
    private String startDate        = null;
    private String endDate          = null;
    private String marriageLocation = null;

    private List<Person> children   = new ArrayList<>();

    //Default Constructor
    public Relationship() {}

    public Relationship(
            String relationshipID, Person femaleParent, Person maleParent,
            String startDate, String endDate, String marriageLocation
    ){
        this.relationshipID   = relationshipID;
        this.femaleParent     = femaleParent;
        this.maleParent       = maleParent;
        this.startDate        = startDate;
        this.endDate          = endDate;
        this.marriageLocation = marriageLocation;
    }

    public String   getRelationshipID()                         { return relationshipID; }
    public void     setRelationshipID(String relationshipID)    { this.relationshipID = relationshipID; }

    public Person   getFemaleParent()                           { return femaleParent; }
    public void     setFemaleParent(Person femaleParent)        { this.femaleParent = femaleParent; }

    public Person   getMaleParent()                             { return maleParent; }
    public void     setMaleParent(Person maleParent)            { this.maleParent = maleParent; }


    public List<Person> getParents(){
        List<Person> result = new ArrayList<>();
        if (femaleParent != null)
            result.add(femaleParent);
        if (maleParent != null)
            result.add(maleParent);

        return result;
    }

    public String   getStartDate()                              { return startDate; }
    public void     setStartDate(String startDate)              { this.startDate = startDate; }

    public String   getEndDate()                                { return endDate; }
    public void     setEndDate(String endDate)                  { this.endDate = endDate; }

    public String   getMarriageLocation()                       { return marriageLocation; }
    public void     setMarriageLocation(String marriageLocation){ this.marriageLocation = marriageLocation; }

    public List<Person> getChildren()                           { return children; }

    public void addChild(Person child) {
        if (!this.children.contains(child))
            this.children.add(child);
    }
    public void addChildren(List<Person> children) {
        for(Person p : children){
            addChild(p);
        }
    }

    public String toString(){
        String s = "rID: " + relationshipID + "\n";
        s += "Mother: " + (femaleParent == null ? "Unknown" : femaleParent.toString()) + "\n";
        s += "Father: " + (maleParent == null ? "Unknown" : maleParent.toString()) + "\n";
        if (children.size()==0) {
            s += "No children found" + "\n";
        }
        else {
            for(Person p : children){
                s += "Child: " + p.toString() + "\n";
            }
        }
        s += "Start Date: " + (startDate == null ? "Unknown" : startDate) + "\n";
        s += "End Date: " + (endDate == null ? "N/A or Unknown" : endDate) + "\n";
        s += "Marriage Location: " + (marriageLocation == null ? "N/A or Unknown" : marriageLocation) + "\n";
        return s;
    }

    // to be used in Swing class TreeEdit for adding a child relationship
    public String childSelect_toString(Person child) {
        String s = "rID: " + relationshipID + "\n";
        s += "Mother: " + (femaleParent == null ? "Unknown" :
                (femaleParent.getFirstName() + "" + femaleParent.getLastName() + "" + femaleParent.getSuffix())) + "\n";
        s += "Father: " + (maleParent == null ? "Unknown" :
                (maleParent.getFirstName() + "" + maleParent.getLastName() + "" + maleParent.getSuffix())) + "\n";
        s += "Child: " + child.getFirstName() + "" + child.getLastName() + "" + child.getSuffix() + "\n";
        return s;
    }

    // to be used in Swing class TreeEdit for adding a partner relationship
    public String partnerSelect_toString(Person p1, Person p2) {
        String s = "rID: " + relationshipID + "\n";
        s += "First Partner: " + (p1).toString() + "\n";
        s += "Second Partner: " + (p2).toString() + "\n";
        s += "Start Date: " + (startDate == null ? "Unknown" : startDate) + "\n";
        s += "End Date: " + (endDate == null ? "N/A or Unknown" : endDate) + "\n";
        s += "Marriage Location: " + (marriageLocation == null ? "N/A or Unknown" : marriageLocation) + "\n";
        return s;
    }

}
