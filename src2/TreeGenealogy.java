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

public class TreeGenealogy{
    private Map<String, Relationship> relations = new HashMap<>();
    private Map<String, Person> people          = new HashMap<>();
    private int initialPersonCapacity = 0;
    private int initialRelationshipCapacity = 0;

    public List<Person> getGrandParents(String pID){
        List<Person> result = new ArrayList<>();

        if (p != null && p.getChildOfR() != null)
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
            String[] d = DataPrep.scrapeData(data.get(i));

            people.get(d[1]).setChildOfR(d[0]);
            relations.get(d[0]).addChild(people.get(d[1]));
        }
        //All children loaded

    }



}