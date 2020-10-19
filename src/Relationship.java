//package familyTree;
//import familyTree.Person;
import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;

/**
 * Relationship class stores parents and a list of children of the Person class.
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

    public String   getStartDate()                              { return startDate; }
    public void     setStartDate(String startDate)              { this.startDate = startDate; }

    public String   getEndDate()                                { return endDate; }
    public void     setEndDate(String endDate)                  { this.endDate = endDate; }

    public String   getMarriageLocation()                       { return marriageLocation; }
    public void     setMarriageLocation(String marriageLocation){ this.marriageLocation = marriageLocation; }

    public List<Person> getChildren()                       { return children;}
    public void         addChild(Person child)              { this.children.add(child);}
    public void         addChildren(List<Person> children)  {this.children.addAll(children);}

    /*
        ************************************** <-- [0]
        *                  rID               * <-- [1]
        * Female Parent
        *
        *
        *
     */

    /* STILL IN DEVELOPMENT
    public String[] generateVisuals(){
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }
     */

    public String toString(){
        String s = "";
        s += "--------Parents:-------\n" +
                "Mother: " + (femaleParent == null ? "N/A" : femaleParent.toString()) + "\n" +
                "Father: " + (maleParent == null ? "N/A" : maleParent.toString()) + "\n" +
                "-------Children:-----\n";
        for(Person p : children){
            s += p.toString() + "\n";
        }

        return s;
    }

}
