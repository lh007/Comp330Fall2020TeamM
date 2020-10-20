import java.lang.Math;

/**
 *  Contains only details of an individual. No relationships are tracked on this class.
 *
 */
public class Person {
    //Global Variables
    private enum Orientation {
        CENTER_JUSTIFY,
        LEFT_JUSTIFY,
        RIGHT_JUSTIFY,
        OUTER_EDGES
    }
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

    private int maxWidth = 0;

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
        return generatePrintableVisual(maxWidth);
    }

    public String generatePrintableVisual(int widthToUse){
        return String.join("\n", generateVisuals(widthToUse));
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
        return generateVisuals(maxWidth);
    }

    public String[] generateVisuals(int widthToUse){
        determineMaxWidth();

        //this prevents the width from being anything less than the minimum determined in maxWidth
        widthToUse = Math.max(maxWidth, widthToUse);
        String[] s = new String[6];

        //[0]: top star border
        s[0] = "*".repeat(widthToUse+2);

        //[1]: ID row
        s[1] = twoStringCLR(parentString(), pIDString(), Orientation.OUTER_EDGES, widthToUse);

        //[2]: Name row
        s[2] = oneStringCLR(fullNameString(), Orientation.CENTER_JUSTIFY, widthToUse);

        //[3]: birth date/place row
        if (birthDate == null && birthPlace == null){
            s[3] = blankRow(widthToUse);
        } else {
            s[3] = twoStringCLR(bornDateString(), bornPlaceString(), Orientation.OUTER_EDGES, widthToUse);
        }

        //[4]: death date/place row
        if (deathDate == null && deathPlace == null){
            s[4] = blankRow(widthToUse);
        } else {
            s[4] = twoStringCLR(deathDateString(), deathPlaceString(), Orientation.OUTER_EDGES, widthToUse);
        }

        //[5]: bottom star border
        s[5] = s[0];

        return s;
    }

    private String blankRow()               { return blankRow(maxWidth);}
    private String blankRow(int widthToUse) { return "*" + " ".repeat(widthToUse) + "*";}
    private String parentString()           { return "Parents: " + (childOfR == null ? "N/A" : childOfR); }
    private String pIDString()              { return "ID: " + personID; }
    private String fullNameString()         { return firstName + " " + lastName + (suffix == null ? "" : ", " + suffix); }
    private String bornDateString()         { return "Born: " + (birthDate == null ? "N/A" : birthDate); }
    private String bornPlaceString()        { return "birthPlace: " + (birthPlace == null ? "N/A" : birthPlace); }
    private String deathDateString()        { return "Death: " + (deathDate == null ? "N/A" : deathDate); }
    private String deathPlaceString()       { return "deathPlace: " + (deathPlace == null ? "N/A" : deathPlace); }

    public int getMaxWidth(){
        determineMaxWidth();
        return maxWidth;
    }

    private void determineMaxWidth(){
        int idWidth = parentString().length() + pIDString().length();
        int fullNameWidth = fullNameString().length();
        int birthLengthMin = bornDateString().length() + bornPlaceString().length();
        int deathLengthMin = deathDateString().length() + deathPlaceString().length();

        maxWidth = Math.max(maxWidth, idWidth + 4);
        maxWidth = Math.max(maxWidth, fullNameWidth + 4);
        maxWidth = Math.max(maxWidth, birthLengthMin + 4);
        maxWidth = Math.max(maxWidth, deathLengthMin + 4);
    }

    private static String oneStringCLR(String s, Orientation o, int widthToUse){
        int sLength = s.length();
        String result = "";
        switch (o){
            case LEFT_JUSTIFY:
                result = "*" + s + " ".repeat(widthToUse - sLength) + "*";
                break;
            case RIGHT_JUSTIFY:
                result =  "*"  + " ".repeat(widthToUse - sLength) + s +  "*";
                break;
            case CENTER_JUSTIFY:
                int fnSpacing = (widthToUse - sLength)/2;
                result = "*" + " ".repeat(fnSpacing) + s + " ".repeat(widthToUse - fnSpacing - sLength) + "*";
                break;
        }
        return result;
    }

    private static String twoStringCLR(String leftString, String rightString, Orientation o, int widthToUse){
        int leftLength = leftString.length();
        int rightLength = rightString.length();
        int sumLength = leftLength + rightLength;

        //this prevents the width from being anything less than the minimum determined in sumLength+1
        widthToUse = Math.max(widthToUse, sumLength+1);

        String result = "";

        //only one enum currently in use. Utility may be increased in a later update.
        switch (o){
            case OUTER_EDGES:
                result = "*" + leftString + " ".repeat(widthToUse - sumLength) + rightString + "*";
                break;
        }

        return result;
    }

    //generic toString for data testing. May be altered later.
    public String toString(){
        return "Child of: " + (childOfR == null ? "Unknown" : childOfR) + " " + personID + " " + lastName + " " + firstName + " " + suffix + " " +
                birthDate + " " + birthPlace + " " + deathDate + " " + deathPlace;
    }

}//class end