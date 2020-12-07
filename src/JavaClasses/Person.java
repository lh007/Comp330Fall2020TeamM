package JavaClasses;

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

    public String       getGender()                     { return gender; }
    public void         setGender(String gender)        { this.gender = gender; }

    public String toString(){
        String s = "pID: " + personID + "\n";
        s += "First Name: " + firstName + "\n";
        s += "Last Name: " + lastName + "\n";
        s += "Suffix: " + (suffix == null ? "N/A" : suffix) + "\n";
        s += "Birth Date: " + birthDate + "\n";
        s += "Birth Place: " + (birthPlace == null ? "Unknown" : birthPlace) + "\n";
        s += "Death Date: " + (deathDate == null ? "N/A or Unknown" : deathDate) + "\n";
        s += "Death Place: " + (deathPlace == null ? "N/A or Unknown" : deathPlace) + "\n";
        return s;
    }
}//class end