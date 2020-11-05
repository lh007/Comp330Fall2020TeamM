import java.lang.Math;
import java.util.List;
import java.util.ArrayList;
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
    private static String personID     = null;
    private static String lastName     = null;
    private static String firstName    = null;
    private static String suffix       = null;
    private static String birthDate    = null;
    private static String birthPlace   = null;
    private static String deathDate    = null;
    private static String deathPlace   = null;

    private static String childOfR = null;

    private Set<String> belongToRelations = new HashSet<>();

    private String gender = null;



    /*
     * Values will be restricted to m, f, s, d and backups (if gender unkown) p and c for parent/child
     * Also, once the chain begins traversing down (s or d), it must only continue subsequent
     * traversals in that manner, for example:
     * mfs = uncle
     * mmd = aunt
     * mmds = first cousin (male)
     *
     * you cannot have s or d appear at any point before m or f.
     * Example of wrong relation chains:
     * smf
     * sdmf (this is technically the same as s)
    */
    public static Person sourcePerson = null;
    public long titleTimeStamp = 0;

    //structure will be "Depth# hierarchy" where depth# is the depth of the title recursion
    //Example: 3 mmf = aunt found after 3 recursions
    public List<String> relationChain = new ArrayList<>();
    public List<String> familyTitle = new ArrayList<>();

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

    public static String       getPersonID()                   { return personID; }
    public void         setPersonID(String personID)    { this.personID = personID; }

    public static String       getLastName()                   { return lastName; }
    public void         setLastName(String lastName)    { this.lastName = lastName; }

    public static String       getFirstName()                  { return firstName; }
    public void         setFirstName(String firstName)  { this.firstName = firstName; }

    public static String       getSuffix()                     { return suffix; }
    public void         setSuffix(String suffix)        { this.suffix=suffix; }

    public static String       getBirthDate()                  { return birthDate; }
    public void         setBirthDate(String birthDate)  { this.birthDate = birthDate; }

    public static String       getBirthPlace()                 { return birthPlace; }
    public void         setBirthPlace(String birthPlace){ this.birthPlace = birthPlace; }

    public static String       getDeathDate()                  { return deathDate; }
    public void         setDeathDate(String deathDate)  { this.deathDate = deathDate; }

    public static String       getDeathPlace()                 { return deathPlace; }
    public void         setDeathPlace(String deathPlace){ this.deathPlace = deathPlace; }

    public static String       getChildOfR()                   { return childOfR; }
    public void         setChildOfR(String childOfR)    { this.childOfR = childOfR; }

    public Set<String>  getRelations()                  { return belongToRelations; }
    public void         addRelation(String s)           { this.belongToRelations.add(s); }

    public String       getGender()                     { return gender; }
    public void         setGender(String gender)        { this.gender = gender; }

}//class end