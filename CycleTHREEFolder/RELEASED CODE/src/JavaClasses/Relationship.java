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
    private Person firstParent      = null;
    private Person secondParent     = null;
    private String startDate        = null;
    private String endDate          = null;
    private String marriageLocation = null;

    private List<Person> children   = new ArrayList<>();

    //Default Constructor
    public Relationship() {}

    public Relationship(
            String relationshipID, Person firstParent, Person secondParent,
            String startDate, String endDate, String marriageLocation
    ){
        this.relationshipID   = relationshipID;
        this.firstParent = firstParent;
        this.secondParent = secondParent;
        this.startDate        = startDate;
        this.endDate          = endDate;
        this.marriageLocation = marriageLocation;
    }

    public String   getRelationshipID()                         { return relationshipID; }
    public void     setRelationshipID(String relationshipID)    { this.relationshipID = relationshipID; }

    public Person getFirstParent()                           { return firstParent; }
    public void setFirstParent(Person firstParent)        { this.firstParent = firstParent; }

    public Person getSecondParent()                             { return secondParent; }
    public void setSecondParent(Person secondParent)            { this.secondParent = secondParent; }


    public List<Person> getParents(){
        List<Person> result = new ArrayList<>();
        if (firstParent != null)
            result.add(firstParent);
        if (secondParent != null)
            result.add(secondParent);

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

    /*
     * PRINTING TREE VISUALS SECTION
     */

    /*
     ************************************** <-- [0]
     *                  rID               * <-- [1]
     * Female Parent
     *
     *
     *
     */
    public int determineMaxWidth(){
        int result = 0;
        result = (firstParent == null ? result : firstParent.getMaxWidth());
        result = (secondParent   == null ? result : Math.max(result, secondParent.getMaxWidth()));

        if(!children.isEmpty())
            for(Person p : children)
                result = Math.max(result, p.getMaxWidth());

        return result;
    }

    private String rID() {return "rID: " + relationshipID;}

    public String[] printVisuals(){
        int widthToUse = determineMaxWidth();


        int parentCount = (firstParent == null ? 0 : 1) + (secondParent == null ? 0 : 1);
        int childCount = children.size();
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


            if (firstParent != null) {
                int j = i;
                for (String s : firstParent.encapsulateWithBorder(widthToUse))
                    resultToPrint[j++] = preParentSpace + s;
            } else {
                for (int j = i; j < i+6; j++)
                    resultToPrint[j] = preParentSpace + " ".repeat(widthToUse+2);
            }


            if (secondParent != null) {
                int j = i;
                for (String s : secondParent.encapsulateWithBorder(widthToUse))
                    resultToPrint[j++] += s + postParentSpace;
            } else {
                for (int j = i; j < i+6; j++)
                    resultToPrint[j] += " ".repeat(widthToUse+2) + postParentSpace;
            }
            i += 6;
        }

        //now for the title of the children rows:
        String childTitle = (parentCount == 0 ? "Siblings" : childCount > 1 ? "Children" : childCount == 0 ? " ".repeat(overallWidthToUse) : "Child");

        if(children.size() > 1)
            childTitle += " (" + String.valueOf(children.size()) + ")";

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

            for(Person c : children){
                int j = i;
                for(String s : c.encapsulateWithBorder(widthToUse))
                    resultToPrint[j++] += s;
            }

            for(int j=i; j<i+6; j++)
                resultToPrint[j] += postChildSpace;
        }

        return Person.encapsulateWithBorder('*', resultToPrint);

    }






    /*
     * END OF TREE VISUALS SECTION
     */

    public String toString(){
        String s = "rID: " + relationshipID + "\n";
        s += "First Parent: " + (firstParent == null ? "Unknown" : firstParent.toString()) + "\n";
        s += "Second Parent: " + (secondParent == null ? "Unknown" : secondParent.toString()) + "\n";
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
        s += "First Parent: " + (firstParent == null ? "Unknown" :
                (firstParent.getFirstName() + "" + firstParent.getLastName() + "" + firstParent.getSuffix())) + "\n";
        s += "Second Parent: " + (secondParent == null ? "Unknown" :
                (secondParent.getFirstName() + "" + secondParent.getLastName() + "" + secondParent.getSuffix())) + "\n";
        s += "Child: " + child.getFirstName() + "" + child.getLastName() + "" + child.getSuffix() + "\n";
        return s;
    }

    // to be used in Swing class TreeEdit for adding a partner relationship
    public String partnerSelect_toString() {
        String s = "rID: " + relationshipID + "\n";
        s += "First Partner: " + firstParent + "\n";
        s += "Second Partner: " + secondParent + "\n";
        s += "Start Date: " + (startDate == null ? "Unknown" : startDate) + "\n";
        s += "End Date: " + (endDate == null ? "N/A or Unknown" : endDate) + "\n";
        s += "Marriage Location: " + (marriageLocation == null ? "N/A or Unknown" : marriageLocation) + "\n";
        return s;
    }



}
