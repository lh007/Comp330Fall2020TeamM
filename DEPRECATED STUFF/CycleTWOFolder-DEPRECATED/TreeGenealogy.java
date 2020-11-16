import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.lang.Math;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;

public class TreeGenealogy{
    private Map<String, Relationship> relations = new HashMap<>();
    private static Map<String, Person> people          = new HashMap<>();
    private int initialPersonCapacity = 0;
    private int initialRelationshipCapacity = 0;

    /*
     *  FAMILY TITLES SECTION
     */

    /// **Added for temporary Visuals** \\\
    public static Map<String,Person> getPeople() {
        return people;
    }

    public void determineFamilyTitles(String sourceID){
        if (people.containsKey(sourceID)){
            Person p = people.get(sourceID);
            long timeStamp = System.currentTimeMillis();
            String currentHierarchy = "";
            List<Person> currentPersons = new ArrayList<>();
            currentPersons.add(p);
            Set<String> rExcluded = new HashSet<>();

            Person.sourcePerson = p;

            determineUpFamilyTitles(timeStamp, currentHierarchy, currentPersons, 0, rExcluded);
        }
    }

    private void determineUpFamilyTitles(long timeStamp, String currentHierarchy, List<Person>currentPersons, int depth, Set<String> rExcluded){
        List<Person> nextGeneration = new ArrayList<>();
        List<Person> prevGeneration = new ArrayList<>();
        List<Person> siblingGeneration = new ArrayList<>();
        final String nextHierarchy = currentHierarchy + "c";
        final String prevHierarchy = currentHierarchy + "p";
        final String siblingHierarchy = currentHierarchy + "p" + "c";

        for(Person p : currentPersons){

            //first step, determine the current person's title. Clear any previous titles if this is a new calculation based on timeStamp
            if (p.titleTimeStamp != timeStamp){
                p.relationChain.clear();
                p.familyTitle.clear();
                p.titleTimeStamp = timeStamp;
            }

            if (depth == 0){
                p.relationChain.add("0 ?");
                p.familyTitle.add("Self");
            } else {
                String currentGender = p.getGender();
                String specificHierarchy = String.valueOf(depth) + " ";
                if (currentGender != null)
                    specificHierarchy += currentHierarchy.substring(0, currentHierarchy.length() - 1) + (currentGender.equals("m") ? "f" : "m");
                else
                    specificHierarchy += currentHierarchy;

                p.relationChain.add(specificHierarchy);
                p.familyTitle.add(DataPrep.decodeFamilyTitle(specificHierarchy));
            }

            //done with the current person

            //prepare the prev generation of parents
            if (p.getChildOfR() != null){
                Relationship r = relations.get(p.getChildOfR());
                List<Person> parents = r.getParents();
                if (!parents.isEmpty()) {
                    prevGeneration.addAll(parents);
                }

                //for determining siblings that have no parents listed
                for(Person child : r.getChildren()){
                    if(!siblingGeneration.contains(child) && child != p)
                        siblingGeneration.add(child);
                }

                rExcluded.add(p.getChildOfR());
            }

            //prepare the next generation by adding all children from all of the current person's relationships.
            for(String s : p.getRelations()){
                if (!rExcluded.contains(s)){
                    Relationship r = relations.get(s);

                    Person femaleParent = r.getFemaleParent();
                    Person maleParent = r.getMaleParent();

                    if (depth == 0 && femaleParent != null && femaleParent != p) {
                        femaleParent.relationChain.add("0 w");
                        femaleParent.familyTitle.add(DataPrep.decodeFamilyTitle("0 w"));
                    } else if (depth == 0 && maleParent != null && maleParent != p){
                        maleParent.relationChain.add("0 h");
                        maleParent.familyTitle.add(DataPrep.decodeFamilyTitle("0 h"));
                    }

                    for (Person child : r.getChildren()){
                        if (!nextGeneration.contains(child))
                            nextGeneration.add(child);
                    }
                    rExcluded.add(s);
                }
            }

        }

        if (!prevGeneration.isEmpty())
            determineUpFamilyTitles(timeStamp, prevHierarchy, prevGeneration, depth + 1, rExcluded);

        if (!nextGeneration.isEmpty())
            determineDownFamilyTitles(timeStamp, nextHierarchy, nextGeneration, depth + 1, rExcluded);

        if (!siblingGeneration.isEmpty())
            determineDownFamilyTitles(timeStamp, siblingHierarchy, siblingGeneration, depth + 1, rExcluded);

    }

    private void determineDownFamilyTitles(long timeStamp, String currentHierarchy, List<Person> currentPersons, int depth, Set<String> rExcluded){
        List<Person> nextGeneration = new ArrayList<>();
        final String nextHierarchy = currentHierarchy + "c";
        for(Person p : currentPersons){

            //first step, determine the current person's title. Clear any previous titles if this is a new calculation based on timeStamp
            if (p.titleTimeStamp != timeStamp){
                p.relationChain.clear();
                p.familyTitle.clear();
                p.titleTimeStamp = timeStamp;
            }

            String currentGender = p.getGender();
            String specificHierarchy = String.valueOf(depth) + " ";
            if (currentGender != null)
                specificHierarchy += currentHierarchy.substring(0, currentHierarchy.length() - 1) + (currentGender.equals("m") ? "s" : "d");
            else
                specificHierarchy += currentHierarchy;

            p.relationChain.add(specificHierarchy);
            p.familyTitle.add(DataPrep.decodeFamilyTitle(specificHierarchy));

            //done with the current person

            //prepare the next generation by adding all children from all of the current person's relationships.
            for(String s : p.getRelations()){
                if (!rExcluded.contains(s)) {
                    Relationship r = relations.get(s);

                    //give the "in law" title to the married people if one exists
                    Person femaleParent = r.getFemaleParent();
                    Person maleParent = r.getMaleParent();
                    if (femaleParent != null && femaleParent != p){
                        String marriageHierarchy = String.valueOf(depth) + " " + currentHierarchy.substring(0, currentHierarchy.length() - 1) + "d -";
                        femaleParent.relationChain.add(marriageHierarchy);
                        femaleParent.familyTitle.add(DataPrep.decodeFamilyTitle(marriageHierarchy));
                    } else if (maleParent != null && maleParent != p){
                        String marriageHierarchy = String.valueOf(depth) + " " + currentHierarchy.substring(0, currentHierarchy.length() - 1) + "s -";
                        maleParent.relationChain.add(marriageHierarchy);
                        maleParent.familyTitle.add(DataPrep.decodeFamilyTitle(marriageHierarchy));
                    }

                    for (Person child : r.getChildren()) {
                        if (!nextGeneration.contains(child))
                            nextGeneration.add(child);
                    }
                    rExcluded.add(s);
                }
            }

        }

        if (!nextGeneration.isEmpty())
            determineDownFamilyTitles(timeStamp, nextHierarchy, nextGeneration, depth + 1, rExcluded);
    }

    /*
     * END OF FAMILY TITLES SECTION
     */

    public List<Person> getGrandParents(String pID){
        List<Person> result = new ArrayList<>();

        if (people.containsKey(pID))
            result = getGrandParents(people.get(pID));

        return result;
    }

    public List<Person> getGrandParents(Person p){
        List<Person> result = new ArrayList<>();

        if (p != null && p.getChildOfR() != null) {
            List<Person> parents = getParents(p);
            for(Person parent : parents){
                List<Person> grandParents = getParents(parent);
                if (!grandParents.isEmpty())
                    result.addAll(grandParents);
            }
        }

        return result;
    }

    public List<Person> getParents(String pID){
        List<Person> result = new ArrayList<>();
        if (people.containsKey(pID))
            result = getParents(people.get(pID));

        return result;
    }

    public List<Person> getParents(Person p){
        List<Person> result = new ArrayList<>();

        if (p != null && p.getChildOfR() != null)
            result = relations.get(p.getChildOfR()).getParents();

        return result;
    }


    public List<Person> searchFirstName(String firstName){
        List<Person> result = new ArrayList<>();

        for(Person p : people.values())
            if(p.getFirstName().equals(firstName))
                result.add(p);

        return result;
    }

    public List<Person> searchLastName(String lastName){
        List<Person> result = new ArrayList<>();

        for(Person p : people.values())
            if(p.getLastName().equals(lastName))
                result.add(p);

        return result;
    }

    public Relationship getRelationship(String rID) { return (relations.containsKey(rID) ? relations.get(rID) : null); }
    public Person       getPerson(String pID)       { return (people.containsKey(pID) ? people.get(pID) : null); }



    /*
    ----------------------------------------------------------
    ----------------------------------------------------------
    -------------General loading and constructors-------------
    ----------------------------------------------------------
    ----------------------------------------------------------
     */

    public TreeGenealogy(String dataFileName) throws IOException {
        loadData(dataFileName);
    }

    public void loadData(String dataFileName) throws IOException {
        relations.clear();
        people.clear();
        List<String> data = DataPrep.readFile(dataFileName);

        //Loading Persons
        initialPersonCapacity = Integer.parseInt((data.get(0).split(","))[1]);
        int i = 1;
        while( !((data.get(i).substring(0,7)).equals(",,,,,,,"))){
            String[] d = DataPrep.scrapeData(data.get(i));
            Person p = new Person(d[0],d[1],d[2],d[3],d[4],d[5],d[6],d[7]);
            people.put(d[0], p);
            i++;
        }
        //all Persons are now loaded

        i++; //skip the empty row between sections

        //Loading Relationships
        initialRelationshipCapacity = Integer.parseInt((data.get(i++).split(","))[1]);
        while( !(data.get(i).substring(0,7).equals(",,,,,,,"))) {
            String[] d = DataPrep.scrapeData(data.get(i));

            Person p1 = people.get(d[1]);
            Person p2 = people.get(d[2]);
            if(p1 != null) {
                p1.setGender("f");
                p1.addRelation(d[0]);
            }
            if(p2 != null) {
                p2.setGender("m");
                p2.addRelation(d[1]);
            }

            Relationship r = new Relationship(d[0],p1,p2,d[3],d[4],d[5]);
            relations.put(d[0], r);
            i++;
        }
        //all Relationships are now loaded

        i += 2; //skip the empty row between sections

        //Loading Children into the JavaClasses.Relationship children array
        for(; i<data.size(); i++){
            String[] d = DataPrep.scrapeData(data.get(i));

            people.get(d[1]).setChildOfR(d[0]);
            relations.get(d[0]).addChild(people.get(d[1]));
        }
        //All children loaded

    }



}