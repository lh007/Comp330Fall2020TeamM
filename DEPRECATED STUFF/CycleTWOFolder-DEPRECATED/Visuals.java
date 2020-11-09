public class Visuals {

    ///////////////////FROM PERSON////////////////////////////////
    private enum Orientation {
        CENTER_JUSTIFY,
        LEFT_JUSTIFY,
        RIGHT_JUSTIFY,
        OUTER_EDGES
    }
    private final char defaultBorder = '*';
    private int maxWidth = 0;
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
        determineMaxWidth_Per();

        //this prevents the width from being anything less than the minimum determined in maxWidth
        widthToUse = Math.max(maxWidth, widthToUse);
        String[] s = new String[4];

        //[0]: ID row
        s[0] = twoStringCLR(parentString(), pIDString(), Orientation.OUTER_EDGES, widthToUse);

        //[1]: Name row
        s[1] = oneStringCLR(fullNameString(), Orientation.CENTER_JUSTIFY, widthToUse);

        //[2]: birth date/place row
        if (Person.getBirthDate() == null && Person.getBirthPlace() == null){
            s[2] = blankRow(widthToUse);
        } else {
            s[2] = twoStringCLR(bornDateString(), bornPlaceString(), Orientation.OUTER_EDGES, widthToUse);
        }

        //[3]: death date/place row
        if (Person.getDeathDate() == null && Person.getDeathPlace() == null){
            s[3] = blankRow(widthToUse);
        } else {
            s[3] = twoStringCLR(deathDateString(), deathPlaceString(), Orientation.OUTER_EDGES, widthToUse);
        }

        return s;
    }

    private String blankRow()               { return blankRow(maxWidth);}
    private String blankRow(int widthToUse) { return  " ".repeat(widthToUse);}
    private String parentString()           { return "Parents: " + (Person.getChildOfR() == null ? "N/A" : Person.getChildOfR()); }
    private String pIDString()              { return "ID: " + Person.getPersonID(); }
    public  String fullNameString()         { return Person.getFirstName() + " " + Person.getLastName() + (Person.getSuffix() == null ? "" : ", " + Person.getSuffix()); }
    private String bornDateString()         { return "Born: " + (Person.getBirthDate() == null ? "N/A" : Person.getBirthDate()); }
    private String bornPlaceString()        { return "birthPlace: " + (Person.getBirthPlace() == null ? "N/A" : Person.getBirthPlace()); }
    private String deathDateString()        { return "Death: " + (Person.getDeathDate() == null ? "N/A" : Person.getDeathDate()); }
    private String deathPlaceString()       { return "deathPlace: " + (Person.getDeathPlace() == null ? "N/A" : Person.getDeathPlace()); }

    public int getMaxWidth(){
        determineMaxWidth_Per();
        return maxWidth;
    }

    private void determineMaxWidth_Per(){
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

    //generic toString for data testing. May be altered later.
    public String toString_Per(){
        return "Child of: " + (Person.getChildOfR() == null ? "Unknown" : Person.getChildOfR()) + " " + Person.getPersonID() + " " + Person.getLastName() + " " + Person.getFirstName() + " " + Person.getSuffix() + " " +
                Person.getBirthDate() + " " + Person.getBirthPlace() + " " + Person.getDeathDate() + " " + Person.getDeathPlace();
    }
    //////////////////////////////////////////////////////////////

    /////////////FROM RELATIONSHIP////////////////////////////////
    /*
     ************************************** <-- [0]
     *                  rID               * <-- [1]
     * Female Parent
     *
     *
     *
     */
    public int determineMaxWidth_Rel(){
        int result = 0;
        result = (Relationship.getFemaleParent() == null ? result : getMaxWidth());
        result = (Relationship.getMaleParent() == null ? result : Math.max(result, getMaxWidth()));

        if(!Relationship.getChildren().isEmpty())
            for(Person p : Relationship.getChildren())
                result = Math.max(result, getMaxWidth());

        return result;
    }


    public void printVisuals(){
        int widthToUse = determineMaxWidth_Rel();


        int parentCount = (Relationship.getFemaleParent() == null ? 0 : 1) + (Relationship.getMaleParent() == null ? 0 : 1);
        int childCount = Relationship.getChildren().size();
        int maxUnitCount = Math.max(2,Math.max(parentCount, childCount));

        int overallWidthToUse = (2 + widthToUse) * (maxUnitCount);

        String[] resultToPrint = new String[2 + 6 * ((parentCount > 0 ? 1 : 0) + (childCount > 0 ? 1 : 0))];

        int i = 0;

        //[0] relationship ID visual
        resultToPrint[i++] = rID() + " ".repeat(overallWidthToUse - rID().length());

        if (parentCount > 0) {
            String preParentSpace = "";
            String postParentSpace = "";

            if(maxUnitCount > 2){
                int parentSpacer = (overallWidthToUse - (2 + widthToUse) * 2) /2;
                preParentSpace = " ".repeat(parentSpacer);
                postParentSpace = " ".repeat(overallWidthToUse - parentSpacer - (2 + widthToUse) * 2);
            }


            if (Relationship.getFemaleParent() != null) {
                int j = i;
                for (String s : encapsulateWithBorder(widthToUse))
                    resultToPrint[j++] = preParentSpace + s;
            } else {
                for (int j = i; j < i+6; j++)
                    resultToPrint[j] = preParentSpace + " ".repeat(widthToUse+2);
            }


            if (Relationship.getMaleParent() != null) {
                int j = i;
                for (String s : encapsulateWithBorder(widthToUse))
                    resultToPrint[j++] += s + postParentSpace;
            } else {
                for (int j = i; j < i+6; j++)
                    resultToPrint[j] += " ".repeat(widthToUse+2) + postParentSpace;
            }
            i += 6;
        }

        //now for the title of the children rows:
        String childTitle = (parentCount == 0 ? "Siblings" : childCount > 1 ? "Children" : childCount == 0 ? " ".repeat(overallWidthToUse) : "Child");

        if(Relationship.getChildren().size() > 1)
            childTitle += " (" + String.valueOf(Relationship.getChildren().size()) + ")";

        if (childCount == 0)
            resultToPrint[i] = childTitle;
        else{
            int childTitleSpacer = (overallWidthToUse - childTitle.length())/2;
            resultToPrint[i] = " ".repeat(childTitleSpacer) + childTitle + " ".repeat(overallWidthToUse - childTitleSpacer - childTitle.length());
        }

        i++;

        if (childCount > 0) {
            int childSpacer = 0;
            if (childCount < maxUnitCount)
                childSpacer = (overallWidthToUse - (2 + widthToUse) * childCount) / 2;

            String preChildSpace = (childSpacer == 0 ? "" : " ".repeat(childSpacer));
            String postChildSpace = (childSpacer == 0 ? "" : " ".repeat(overallWidthToUse - childSpacer - (2 + widthToUse) * childCount));

            for(int j=i; j<i+6; j++)
                resultToPrint[j] = preChildSpace;

            for(Person c : Relationship.getChildren()){
                int j = i;
                for(String s : encapsulateWithBorder(widthToUse))
                    resultToPrint[j++] += s;
            }

            for(int j=i; j<i+6; j++)
                resultToPrint[j] += postChildSpace;
        }

        for(String s : encapsulateWithBorder('*', resultToPrint))
            System.out.println(s);

    }

    private String rID() {return "rID: " + Relationship.getRelationshipID();}
    /*
    public String toString_Rel(){
        String s = "";
        s += "--------Parents:-------\n" +
                "Mother: " + (Person.getFemaleParent() == null ? "N/A" : Person.getFemaleParent().toString()) + "\n" +
                "Father: " + (Person.getMaleParent() == null ? "N/A" : getMaleParent().toString()) + "\n" +
                "-------Children:-----\n";
        for(Person p : Relationship.getChildren()){
            s += p.toString() + "\n";
        }

        return s;
    } */
    //////////////////////////////////////////////////////////////

    /////////////FROM TREE GENEALOGY//////////////////////////////
    private int getOverallMaxWidth(){
        int maxValFound = 0;
        for( Person p : TreeGenealogy.getPeople().values())
            maxValFound = Math.max(maxValFound, getMaxWidth());
        return maxValFound;
    }

}