import java.util.List;
import java.util.ArrayList;
import java.lang.Math;

/**
 * JavaClasses.Relationship class stores parents and a list of children of the JavaClasses.Person class.
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
        ************************************** <-- [0]
        *                  rID               * <-- [1]
        * Female Parent
        *
        *
        *
     */
    public int determineMaxWidth(){
        int result = 0;
        result = (femaleParent == null ? result : femaleParent.getMaxWidth());
        result = (maleParent   == null ? result : Math.max(result, maleParent.getMaxWidth()));

        if(!children.isEmpty())
            for(Person p : children)
                result = Math.max(result, p.getMaxWidth());

        return result;
    }


    public void printVisuals(){
        int widthToUse = determineMaxWidth();


        int parentCount = (femaleParent == null ? 0 : 1) + (maleParent == null ? 0 : 1);
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


            if (femaleParent != null) {
                int j = i;
                for (String s : femaleParent.encapsulateWithBorder(widthToUse))
                    resultToPrint[j++] = preParentSpace + s;
            } else {
                for (int j = i; j < i+6; j++)
                    resultToPrint[j] = preParentSpace + " ".repeat(widthToUse+2);
            }


            if (maleParent != null) {
                int j = i;
                for (String s : maleParent.encapsulateWithBorder(widthToUse))
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

        for(String s : Person.encapsulateWithBorder('*', resultToPrint))
            System.out.println(s);

    }

    private String rID() {return "rID: " + relationshipID;}

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
