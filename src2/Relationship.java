import java.util.List;
import java.util.ArrayList;
import java.lang.Math;

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

}
