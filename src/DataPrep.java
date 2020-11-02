import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class DataPrep
{
    //converts a string into a string array and
    // converts any empty values to null through a mapping
    public static String[] scrapeData(String data){
        List<String> d = Arrays.asList(data.split(","));
        Object[] o = d.stream().map(n -> (n.equals(" ") ? null : n)).toArray();
        return Arrays.copyOf(o, o.length, String[].class);
    }

    //file reader and loads into a list of strings.
    public static List<String> readFile(String fileName) throws IOException {
        Scanner sc = new Scanner(new File(fileName));
        List<String> lines = new ArrayList<>();

        while(sc.hasNext())
        lines.add(sc.nextLine());

        sc.close();
        return lines;
    }



    /* TODO: Need to decode the family hierarchy to properly get titles such as:
     * mmd = "Aunt"
     * mmdd = "First Cousin"
     * fff = "Great Grandfather"
     *
     * Also must include a failsafe when the gender is unkown:
     * mmc = "Aunt or Uncle"
     * fsc = "Niece or Nephew"
     * etc etc.
     */
    public static String decodeFamilyTitle(String hierarchy){
        String result = "";


        return result;
    }

    public static String generalizeHierarchy(String Hierarchy){
        String result = "";
        String definedHierarchy = Hierarchy.split(" ")[1];
        for(char c : definedHierarchy.toCharArray()){
            switch (c){
                case 'f':
                case 'm':
                case 'p':
                    result += 'p';
                    break;
                case 's':
                case 'd':
                case 'c':
                    result += 'c';
            }
        }

        return result;
    }

}