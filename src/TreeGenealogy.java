//import familyTree.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TreeGenealogy{
    private static final String defaultFamilyTree = "FamilyTreeInputTextFileV2.txt";
    private Map<String, Relationship> relations = new HashMap<>();
    private Map<String, Person> people          = new HashMap<>();
    private int initialPersonCapacity = 0;
    private int initialRelationshipCapacity = 0;

    //main function meant to test loading of data
    public static void main(String[] args) throws IOException{
        String treeFile = (args.length == 1 ? args[0] : defaultFamilyTree);

        TreeGenealogy t = new TreeGenealogy(treeFile);
        //at this point t is now populated with all data from the family tree text file.

        t.printPeople();
    }

    public TreeGenealogy(String dataFileName) throws IOException {
        loadData(dataFileName);
    }

    public void loadData(String dataFileName) throws IOException {
        relations.clear();
        people.clear();

        List<String> data = readFile(dataFileName);

        //Loading Persons
        initialPersonCapacity = Integer.parseInt((data.get(0).split(","))[1]);
        int i = 1;
        while( !((data.get(i).substring(0,7)).equals(",,,,,,,"))){
            String[] d = scrapeData(data.get(i));

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

            Relationship r = new Relationship(d[0],people.get(d[1]),people.get(d[2]),d[3],d[4],d[5]);
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

    //simple console printing of all Persons
    public void printPeople(){
        //simple printing test of the data
        for(Map.Entry<String, Person> entry : people.entrySet()){
            System.out.println("Key: " + entry.getKey() + "\n" + "Value: \n" + entry.getValue().generatePrintableVisual());
        }
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