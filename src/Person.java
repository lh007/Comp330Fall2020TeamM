//package familyTree;
import java.lang.Math;

/**
 *  Contains only details of an individual. No relationships are tracked on this class.
 *
 */
public class Person {
    //Global Variables
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

    public String   getPersonID()                   { return personID; }
    public void     setPersonID(String personID)    { this.personID = personID; }

    public String   getLastName()                   { return lastName; }
    public void     setLastName(String lastName)    { this.lastName = lastName; }

    public String   getFirstName()                  { return firstName; }
    public void     setFirstName(String firstName)  { this.firstName = firstName; }

    public String   getSuffix()                     { return suffix; }
    public void     setSuffix(String suffix)        { this.suffix=suffix; }

    public String   getBirthDate()                  { return birthDate; }
    public void     setBirthDate(String birthDate)  { this.birthDate = birthDate; }

    public String   getBirthPlace()                 { return birthPlace; }
    public void     setBirthPlace(String birthPlace){ this.birthPlace = birthPlace; }

    public String   getDeathDate()                  { return deathDate; }
    public void     setDeathDate(String deathDate)  { this.deathDate = deathDate; }

    public String   getDeathPlace()                 { return deathPlace; }
    public void     setDeathPlace(String deathPlace){ this.deathPlace = deathPlace; }

    public String   getChildOfR()                   { return childOfR;}
    public void     setChildOfR(String childOfR)    { this.childOfR = childOfR;}

    public String generatePrintableVisual(){
        return String.join("\n", generateVisuals());
    }


    /*
           ******************* <--[0]
           *childOf:      pID* <--[1]
           *     fullName    * <--[2]
           * Born:     Place:* <--[3]
           * Death:    Place:* <--[4]
           ******************* <--[5]
     */
    public String[] generateVisuals(){
        int maxWidth;
        String[] s = new String[6];

        String parentSide = "Parents: " + (childOfR == null ? "N/A" : childOfR);
        String pIDSide = "ID: " + personID;
        int idWidth = parentSide.length() + pIDSide.length();
        maxWidth = idWidth;

        String fullName = firstName + " " + lastName + (suffix == null ? "" : ", " + suffix);
        int fullNameWidth = fullName.length();
        maxWidth = Math.max(fullNameWidth,maxWidth);

        String bornDateSide = "Born: " + (birthDate == null ? "N/A" : birthDate);
        String bornPlaceSide = "birthPlace: " + (birthPlace == null ? "N/A" : birthPlace);
        int birthLengthMin = bornDateSide.length() + bornPlaceSide.length();
        maxWidth = Math.max(birthLengthMin,maxWidth);


        String deathDateSide = "Death: " + (deathDate == null ? "N/A" : deathDate);
        String deathPlaceSide = "deathPlace: " + (deathPlace == null ? "N/A" : deathPlace);
        int deathLengthMin = deathDateSide.length() + deathPlaceSide.length();
        maxWidth = Math.max(deathLengthMin,maxWidth);

        maxWidth += 4;

        //[0]: top star border
        s[0] = "*".repeat(maxWidth+2);

        //[1]: ID row
        s[1] = "*" + parentSide + " ".repeat(maxWidth - idWidth) + pIDSide + "*";

        //[2]: Name row
        int fnSpacing = (maxWidth - fullNameWidth)/2;
        s[2] = "*" + " ".repeat(fnSpacing) + fullName + " ".repeat(maxWidth - fnSpacing - fullNameWidth) + "*";

        String emptyRow = "*" + " ".repeat(maxWidth) + "*";

        //[3]: birth date/place row
        if (birthDate == null && birthPlace == null){
            s[3] = emptyRow;
        } else {
            s[3] = "*" + bornDateSide + " ".repeat(maxWidth - birthLengthMin) + bornPlaceSide + "*";
        }

        //[4]: death date/place row
        if (deathDate == null && deathPlace == null){
            s[4] = emptyRow;
        } else {
            s[4] = "*" + deathDateSide + " ".repeat(maxWidth - deathLengthMin) + deathPlaceSide + "*";
        }

        //[5]: bottom star border
        s[5] = s[0];

        return s;
    }

    //generic toString for data testing. May be altered later.
    public String toString(){
        return "Child of: " + (childOfR == null ? "Unknown" : childOfR) + " " + personID + " " + lastName + " " + firstName + " " + suffix + " " +
                birthDate + " " + birthPlace + " " + deathDate + " " + deathPlace;
    }

}//class end