import java.io.File;
import java.io.IOException;
import java.lang.Integer;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.lang.Math;
import java.util.Random;
import java.util.Set;

public class TreeGenealogy{
    private static Random r = new Random();
    private static final String defaultFamilyTree = "FamilyTreeInputTextFileV2.txt";
    private Map<String, Relationship> relations = new HashMap<>();
    private Map<String, Person> people          = new HashMap<>();
    private List<String> personList = new ArrayList<>();
    private int initialPersonCapacity = 0;
    private int initialRelationshipCapacity = 0;

    //main function meant to test loading of data
    public static void main(String[] args) throws IOException{
        String treeFile = (args.length == 1 ? args[0] : defaultFamilyTree);

        TreeGenealogy t = new TreeGenealogy(treeFile);
        //at this point t is now populated with all data from the family tree text file.

        Scanner myScan = new Scanner(System.in);
        int menuSelection = 0;

        while(menuSelection != 9){
            displayMainMenu();
            try {
                menuSelection = myScan.nextInt();
                switch (menuSelection){
                    case 1:
                        t.printAllRelationships();
                        break;
                    case 2:
                        t.searchRelationShip(myScan);
                        break;
                    case 3:
                        t.searchPerson(myScan);
                        break;
                    case 4:
                        t.searchGrandParents(myScan);
                        break;
                    case 5:
                        t.searchFirstName(myScan);
                        break;
                    case 6:
                        t.searchLastName(myScan);
                        break;
                    case 7:
                        t.addAPerson(myScan);
                        break;
                    case 8:
                        t.loadFile(myScan);
                        break;
                    case 9:
                        //this is just an exit condition for the while loop
                        break;
                    default:
                        System.out.println("Entry " + menuSelection + " is not a listed option.");
                }
            } catch (Exception e){
                System.out.println("ERROR INPUT: " + myScan.next() + " is not valid.");
            }
            System.out.println("\n\n");
        }
    }

    public static void displayMainMenu(){
        /*
         * 1. List all relationships
         * 2. Print relationship with rID
         * 3. Print person with pID
         * 4. Print grandparents of pID
         * 5. Search by first name
         * 6. Search by last name
         */
        String[] menuItems = new String[9];
        menuItems[0] = "1. List all relationships     ";
        menuItems[1] = "2. Print relationship with rID";
        menuItems[2] = "3. print person with pID      ";
        menuItems[3] = "4. Print grandparents of pID  ";
        menuItems[4] = "5. Search by first name       ";
        menuItems[5] = "6. Search by last name        ";
        menuItems[6] = "7. Add a person               ";
        menuItems[7] = "8. Load Data                  ";
        menuItems[8] = "9. Exit program               ";

        for(String s : Person.encapsulateWithBorder('+', menuItems))
            System.out.println(s);

    }

    public void addAPerson(Scanner myScan){
        myScan.nextLine();

        String[] pD = new String[9];

        System.out.println("Enter Last Name: ");
        pD[0] = myScan.nextLine();

        System.out.println("Enter First Name: ");
        pD[1] = myScan.nextLine();

        System.out.println("Enter Suffix Name (enter None if no data): ");
        pD[2] = myScan.nextLine();

        System.out.println("Enter Birthdate (enter None if no data): ");
        pD[3] = myScan.nextLine();

        System.out.println("Enter Birth Place (enter None if no data): ");
        pD[4] = myScan.nextLine();

        System.out.println("Enter Death Date (enter None if no data): ");
        pD[5] = myScan.nextLine();

        System.out.println("Enter Death Place (enter None if no data): ");
        pD[6] = myScan.nextLine();

        System.out.println("Enter if this person is a child of a relationship (R## or enter None if no data): ");
        pD[7] = myScan.nextLine();

        System.out.println("Enter if this person is a parent of a relationship (\"R## M\" or \"R## F\" for example, \"R12 M\" is the male parent of relationship 12, \"R5 F\" is female parent of relationship 5, or enter None if no data): ");
        pD[8] = myScan.nextLine();


        for(int i=2; i<pD.length; i++)
            pD[i] = (pD[i].equals("None") ? null : pD[i]);

        List<String> pValues = Arrays.asList(people.keySet().toArray(new String[0]));
        Integer[] oData = pValues.stream().map(n -> ( Integer.valueOf(n.substring(1,n.length())) )).toArray(Integer[]::new);

        int maxP = 0;
        for(int i = 0; i<oData.length; i++)
            maxP = (maxP < oData[i] ? oData[i] : maxP);

        maxP++;

        String pID = "P" + String.valueOf(maxP);

        Person p = new Person(pID,pD[0],pD[1],pD[2],pD[3],pD[4],pD[5],pD[6]);

        people.put(pID,p);

        if (pD[7] != null) {
            p.setChildOfR(pD[7]);
            relations.get(pD[7]).addChild(p);
        }

        if (pD[8] != null){
            String[] parentData = pD[8].split(" ");
            p.addRelation(parentData[0]);
            Relationship r = relations.get(parentData[0]);
            if (parentData[1].equals("M")) {
                r.setMaleParent(p);
            } else {
                r.setFemaleParent(p);
            }
        }


        System.out.println(p.generatePrintableVisual());

    }

    public void printAllRelationships(){
        for(Map.Entry<String, Relationship> entry : relations.entrySet()){
            entry.getValue().printVisuals();
            System.out.println();
        }
    }

    public void loadFile(Scanner myScan){
        myScan.nextLine();
        String fileName;
        System.out.println("Enter file name to load: ");
        try {
            fileName = myScan.nextLine();
            try {
                loadData(fileName);
            } catch (IOException ioe){
                System.out.println("Error opening the file. File " + fileName + " may not exist.");
            }
        } catch (Exception e){
            System.out.println("ERROR INPUT: " + myScan.next() + " is not valid.");
        }
    }

    public void searchGrandParents(Scanner myScan){
        myScan.nextLine();
        String pEntered;
        System.out.println("Enter pID to search for grand parents: ");
        try{
            pEntered = myScan.nextLine();
            printGrandParents(pEntered);
        } catch (Exception e){
            System.out.println("ERROR INPUT: " + myScan.next() + " is not valid.");
        }
    }

    public void printGrandParents(String pID){
        if (people.containsKey(pID)) {
            Person p = people.get(pID);
            String r = p.getChildOfR();
            if (r == null || (relations.get(r).getFemaleParent() == null && relations.get(r).getMaleParent() == null ))
                System.out.println(p.fullNameString() + " does not have any parents listed, so there are no grandparents listed.\n");
            else {
                int grandParentsFound = 0;
                Relationship rel = relations.get(r);

                grandParentsFound += printParents(rel.getFemaleParent());
                grandParentsFound += printParents(rel.getMaleParent());

                if(grandParentsFound == 0)
                    System.out.println("No grandparents were found.\n");
                else
                    System.out.println("Total grandparents found: " + String.valueOf(grandParentsFound) + "\n");
            }
        } else
            System.out.println("No person with the personID: " + pID + " exists.\n");
    }

    public int printParents(Person p){
        int count = 0;

        if(p != null && p.getChildOfR() != null){
            Relationship r = relations.get(p.getChildOfR());

            if(r.getFemaleParent() != null) {
                printPerson(r.getFemaleParent().getPersonID());
                count++;
            }

            if(r.getMaleParent() != null) {
                printPerson(r.getMaleParent().getPersonID());
                count++;
            }

        }
        return count;
    }

    public void searchFirstName(Scanner myScan){
        myScan.nextLine();
        String fEntered;
        System.out.println("Enter first name to search");
        try{
            fEntered = myScan.nextLine();
            searchFirstNameAndPrint(fEntered);
        } catch (Exception e){
            System.out.println("ERROR INPUT: " + myScan.next() + " is not valid.");
        }
    }

    private void searchFirstNameAndPrint(String firstName){
        int count = 0;
        for(Person p : people.values()){
            if (p.getFirstName().equals(firstName)) {
                System.out.println(p.generatePrintableVisual());
                System.out.println();
                count++;
            }
        }
        if (count > 0)
            System.out.println("Total people found: " + String.valueOf(count) + "\n");
        else
            System.out.println("No people found by the first name of: " + firstName + "\n");
    }

    public void searchLastName(Scanner myScan){
        myScan.nextLine();
        String lEntered;
        System.out.println("Enter last name to search");
        try{
            lEntered = myScan.nextLine();
            searchLastNameAndPrint(lEntered);
        } catch (Exception e){
            System.out.println("ERROR INPUT: " + myScan.next() + " is not valid.");
        }
    }

    private void searchLastNameAndPrint(String lastName){
        int count = 0;
        for(Person p : people.values()){
            if (p.getLastName().equals(lastName)) {
                System.out.println(p.generatePrintableVisual());
                System.out.println();
                count++;
            }
        }
        if (count > 0)
            System.out.println("Total people found: " + String.valueOf(count) + "\n");
        else
            System.out.println("No people found by the last name of: " + lastName + "\n");
    }

    public void searchRelationShip(Scanner myScan){
        myScan.nextLine();
        String rEntered;
        System.out.println("Enter rID to print: ");
        try{
            rEntered = myScan.nextLine();
            printRelationship(rEntered);
        } catch (Exception e){
            System.out.println("ERROR INPUT: " + myScan.next() + " is not valid.");
        }
    }

    public void printRelationship(String rID){
        if (rID != null && relations.containsKey(rID)) {
            relations.get(rID).printVisuals();
        } else {
            System.out.println("Relation ID: " + String.valueOf(rID) + " does not exist.");
        }
    }

    public void printPersonParentalRelationship(String pID){
        relations.get(people.get(pID).getChildOfR()).printVisuals();
        System.out.println();
    }

    public void searchPerson(Scanner myScan){
        myScan.nextLine();
        String pEntered;
        System.out.println("Enter pID to print: ");
        try{
            pEntered = myScan.nextLine();
            printPerson(pEntered);
        } catch (Exception e){
            System.out.println("ERROR INPUT: " + myScan.next() + " is not valid.");
        }
    }

    public void printPerson(String pID){
        if (pID != null && people.containsKey(pID)) {
            System.out.println(people.get(pID).generatePrintableVisual());
        } else {
            System.out.println("Person ID: " + String.valueOf(pID) + " does not exist.");
        }

    }


    private int getOverallMaxWidth(){
        int maxValFound = 0;
        for( Person p : people.values())
            maxValFound = Math.max(maxValFound, p.getMaxWidth());
        return maxValFound;
    }


    /*
    ----------------------------------------------------------
    ----------------------------------------------------------
    ----------------------------------------------------------
     */

    public TreeGenealogy(String dataFileName) throws IOException {
        loadData(dataFileName);
    }

    public void loadData(String dataFileName) throws IOException {
        List<String> data = readFile(dataFileName);


        relations.clear();
        people.clear();


        //Loading Persons
        initialPersonCapacity = Integer.parseInt((data.get(0).split(","))[1]);
        int i = 1;
        while( !((data.get(i).substring(0,7)).equals(",,,,,,,"))){
            String[] d = scrapeData(data.get(i));
            personList.add(d[0]);
            Person p = new Person(d[0],d[1],d[2],d[3],d[4],d[5],d[6],d[7]);
            people.put(d[0], p);
            i++;
        }
        //all Persons are now loaded

        i++; //skip the empty row between sections

        //Loading Relationships
        initialRelationshipCapacity = Integer.parseInt((data.get(i++).split(","))[1]);
        while( !(data.get(i).substring(0,7).equals(",,,,,,,"))) {
            String[] d = scrapeData(data.get(i));

            Person p1 = people.get(d[1]);
            Person p2 = people.get(d[2]);
            if(p1 != null)
                p1.addRelation(d[0]);
            if(p2 != null)
                p2.addRelation(d[1]);

            Relationship r = new Relationship(d[0],p1,p2,d[3],d[4],d[5]);
            relations.put(d[0], r);
            i++;
        }
        //all Relationships are now loaded

        i += 2; //skip the empty row between sections

        //Loading Children into the Relationship children array
        for(; i<data.size(); i++){
            String[] d = scrapeData(data.get(i));

            people.get(d[1]).setChildOfR(d[0]);
            relations.get(d[0]).addChild(people.get(d[1]));
        }
        //All children loaded

    }

    //converts a string into a string array and converts any empty values to null through a mapping
    private static String[] scrapeData(String data){
        List<String> d = Arrays.asList(data.split(","));
        Object[] o = d.stream().map(n -> (n.equals(" ") ? null : n)).toArray();
        return Arrays.copyOf(o, o.length, String[].class);
    }

    //file reader and loads into a list of strings.
    private static List<String> readFile(String fileName) throws IOException {
        Scanner sc = new Scanner(new File(fileName));
        List<String> lines = new ArrayList<>();

        while(sc.hasNext())
            lines.add(sc.nextLine());

        sc.close();
        return lines;
    }

}