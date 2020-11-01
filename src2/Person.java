import java.lang.Math;
import java.util.Set;
import java.util.HashSet;

/**
 *  Contains only details of an individual. No relationships are tracked on this class.
 *
 */
public class Person {
    /*
        Note: The current order from the family tree text file is (all data in one row):
            personID, lastName, firstName, suffix, birthDate,
            birthPlace, deathDate, deathPlace, parents (not used here)
     */
    private String personID     = null;
    private String lastName     = null;
    private String firstName    = null;
    private String suffix       = null;
    private String birthDate    = null;
    private String birthPlace   = null;
    private String deathDate    = null;
    private String deathPlace   = null;

    private String childOfR = null;

    private Set<String> belongToRelations = new HashSet<>();

    //Default Constructor
    public Person() {}

    public Person(
            String personID, String lastName, String firstName, String suffix,
            String birthDate, String birthPlace, String deathDate, String deathPlace
    ){
        this.personID = personID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.suffix = suffix;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        this.deathDate = deathDate;
        this.deathPlace = deathPlace;
    }

    public String       getPersonID()                   { return personID; }
    public void         setPersonID(String personID)    { this.personID = personID; }

    public String       getLastName()                   { return lastName; }
    public void         setLastName(String lastName)    { this.lastName = lastName; }

    public String       getFirstName()                  { return firstName; }
    public void         setFirstName(String firstName)  { this.firstName = firstName; }

    public String       getSuffix()                     { return suffix; }
    public void         setSuffix(String suffix)        { this.suffix=suffix; }

    public String       getBirthDate()                  { return birthDate; }
    public void         setBirthDate(String birthDate)  { this.birthDate = birthDate; }

    public String       getBirthPlace()                 { return birthPlace; }
    public void         setBirthPlace(String birthPlace){ this.birthPlace = birthPlace; }

    public String       getDeathDate()                  { return deathDate; }
    public void         setDeathDate(String deathDate)  { this.deathDate = deathDate; }

    public String       getDeathPlace()                 { return deathPlace; }
    public void         setDeathPlace(String deathPlace){ this.deathPlace = deathPlace; }

    public String       getChildOfR()                   { return childOfR; }
    public void         setChildOfR(String childOfR)    { this.childOfR = childOfR; }

    public Set<String>  getRelations()                  { return belongToRelations; }
    public void         addRelation(String s)           { this.belongToRelations.add(s); }

}//class end