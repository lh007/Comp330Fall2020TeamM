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

    /* ***********************************************
     * PRINT FUNCTIONS USED FOR DISPLAYING AS A BLOCK
     * ***********************************************
     */



    private int maxWidth = 0;
    private enum Orientation {
        CENTER_JUSTIFY,
        LEFT_JUSTIFY,
        RIGHT_JUSTIFY,
        OUTER_EDGES
    }
    private final char defaultBorder = '*';

    public String generatePrintableVisual(){
        return generatePrintableVisual(maxWidth);
    }

    public String generatePrintableVisual(int widthToUse){
        return String.join("\n", encapsulateWithBorder(generateVisuals(widthToUse)));
    }

    public String[] encapsulateWithBorder()              { return encapsulateWithBorder(defaultBorder,generateVisuals());}
    public String[] encapsulateWithBorder(int width)     { return encapsulateWithBorder(defaultBorder, generateVisuals(width));}
    public String[] encapsulateWithBorder(String[] data) { return encapsulateWithBorder(defaultBorder, data); }


    public static String[] encapsulateWithBorder(char c, String[] data){
        String sChar = String.valueOf(c);
        String[] result = new String[data.length+2];
        result[0] = sChar.repeat(data[0].length()+2);
        result[result.length-1] = result[0];
        for(int i = 0; i< data.length; i++){
            result[i+1] = sChar + data[i] + sChar;
        }
        return result;
    }

    public String[] generateVisuals(){
        return generateVisuals(maxWidth);
    }

    /*
       childOf:      pID <--[0]
            fullName     <--[1]
        Born:     Place: <--[2]
        Death:    Place: <--[3]
    */
    public String[] generateVisuals(int widthToUse){
        determineMaxWidth();

        //this prevents the width from being anything less than the minimum determined in maxWidth
        widthToUse = Math.max(maxWidth, widthToUse);
        String[] s = new String[4];

        //[0]: ID row
        s[0] = twoStringCLR(parentString(), pIDString(), Orientation.OUTER_EDGES, widthToUse);

        //[1]: Name row
        s[1] = oneStringCLR(fullNameString(), Orientation.CENTER_JUSTIFY, widthToUse);

        //[2]: birth date/place row
        if (birthDate == null && birthPlace == null){
            s[2] = blankRow(widthToUse);
        } else {
            s[2] = twoStringCLR(bornDateString(), bornPlaceString(), Orientation.OUTER_EDGES, widthToUse);
        }

        //[3]: death date/place row
        if (deathDate == null && deathPlace == null){
            s[3] = blankRow(widthToUse);
        } else {
            s[3] = twoStringCLR(deathDateString(), deathPlaceString(), Orientation.OUTER_EDGES, widthToUse);
        }

        return s;
    }

    private String blankRow()               { return blankRow(maxWidth);}
    private String blankRow(int widthToUse) { return  " ".repeat(widthToUse);}
    private String parentString()           { return "Parents: " + (childOfR == null ? "N/A" : childOfR); }
    private String pIDString()              { return "ID: " + personID; }
    public  String fullNameString()         { return firstName + " " + lastName + (suffix == null ? "" : ", " + suffix); }
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

        maxWidth = Math.max(maxWidth, idWidth);
        maxWidth = Math.max(maxWidth, fullNameWidth);
        maxWidth = Math.max(maxWidth, birthLengthMin);
        maxWidth = Math.max(maxWidth, deathLengthMin);

        if (maxWidth == birthLengthMin || maxWidth == deathLengthMin)
            maxWidth++;
    }

    private static String oneStringCLR(String s, Orientation o, int widthToUse){
        int sLength = s.length();
        String result = "";
        switch (o){
            case LEFT_JUSTIFY:
                result = s + " ".repeat(widthToUse - sLength);
                break;
            case RIGHT_JUSTIFY:
                result =   " ".repeat(widthToUse - sLength) + s;
                break;
            case CENTER_JUSTIFY:
                int fnSpacing = (widthToUse - sLength)/2;
                result =  " ".repeat(fnSpacing) + s + " ".repeat(widthToUse - fnSpacing - sLength);
                break;
        }
        return result;
    }

    private static String twoStringCLR(String leftString, String rightString, Orientation o, int widthToUse){
        int sumLength = leftString.length() + rightString.length();
        String result = "";

        //this prevents the width from being anything less than the minimum determined in sumLength+1
        widthToUse = Math.max(widthToUse, sumLength);

        //only one enum currently in use. Utility may be increased in a later update.
        switch (o){
            case OUTER_EDGES:
                result = leftString + " ".repeat(widthToUse - sumLength) + rightString;
                break;
        }

        return result;
    }

}//class end