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

    private final String[][] familyTitles = {
            {"Self", "Son/Daughter",   "Grandson/Granddaughter","Great Grandson/Great Granddaughter", "Great Great Grandson/Great Great Granddaughter", "3rd Great Grandson/3rd Great Granddaughter", "4th Great Grandson/4th Great Granddaughter",         "5th Great Grandson/5th Great Granddaughter"},
            {"Father/Mother",          "Brother/Sister",        "Nephew/Niece",                       "Grand Nephew/Grand Niece",                       "Great Grand Nephew/Great Grand Niece",       "Great Great Grand Nephew/Great Great Grand Niece",   "3rd Great Grand Nephew/3rd Great Grand Niece"},
            {"Grandfather/Grandmother","Uncle/Aunt",            "1st Cousin",                         "1st Cousin Once Removed",                        "1st Cousin Twice Removed",                   "1st Cousin Thrice Removed",                          "1st Cousin 4 Times Removed"},
            {"Great Grandfather/Great Grandmother", "Great Aunt/Great Uncle","1st Cousin Once Removed","2nd Cousin", "2nd Cousin Once Removed", "2nd Cousin Twice Removed", "2nd Cousin Thrice Removed"},
            {"Great Great Grandfather/Great Great Grandmother", "Great Grand Uncle/Great Grand Aunt","1st Cousin Twice Removed", "2nd Cousin Once Removed", "3rd Cousin", "3rd Cousin Once Removed", "3rd Cousin Twice Removed"},
            {"3rd Great Grandfather/3rd Great Grandmother", "Great Great Grand Uncle/Great Great Grand Aunt", "1st Cousin Thrice Removed", "2nd Cousin Twice Removed", "3rd Cousin Once Removed", "4th Cousin", "4th Cousin Once Removed"}
    };

    /* TODO: Need to decode the family hierarchy to properly get titles such as:
     * mmd = "Aunt"
     * mmdd = "First Cousin"
     * fff = "Great Grandfather"
     *
     * Also must include a failsafe when the gender is unkown:
     * mmc = "Aunt or Uncle"
     * fsc = "Niece or Nephew"
     * etc etc.
     *
     * defined characters and their meanings:
     * p = parent
     * c = child
     * f = father (parent)
     * m = mother (parent)
     * s = son (child)
     * d = daughter (child)
     * w = wife
     * h = husband
     * - = "in-law"
     *
     * example:
     * mfd- = aunt-in-law
     * mfds- = first cousin-in-law
     *
     */
    public static String decodeFamilyTitle(String hierarchy){
        if (hierarchy.startsWith("0")){
            if (hierarchy.equals("0 w"))
                return "Wife";
            else if (hierarchy.equals("0 h"))
                return "Husband";
            else if (hierarchy.equals("0 ?"))
                return "Self";
            else
                return "Unkown Self";
        } else {

            String result = "";
            String currentHierarchy = generalizeHierarchy(hierarchy);

            int upCount = 0;
            int downCount = 0;
            boolean inLaw = (hierarchy.split(" ").length == 3 ? true : false);

            char[] chars = currentHierarchy.toCharArray();
            for (int i = 0; i < chars.length - 1; i++) {
                if (chars[i] == 'p')
                    upCount++;
                else
                    downCount++;
            }
            char lastChar = chars[chars.length - 1];
            if (lastChar == 'p' || lastChar == 'f' || lastChar == 'm')
                upCount++;
            else
                downCount++;

            //now ready to determine family tree title name. lastChar will be used to distinguish male/female titles
            // also inLaw will potentially add in-law to the name.


            if (inLaw)
                result += "-in-law";
            return result;
        }
    }

    private static String generalizeHierarchy(String Hierarchy){
        String result = "";
        String definedHierarchy = Hierarchy.split(" ")[1];

        char[] chars = definedHierarchy.toCharArray();

        for(int i=0; i< chars.length-1; i++){
            char c = chars[i];
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
                    break;
                default:
                    result += c;
                    break;
            }
        }
        result += chars[chars.length-1];

        return result;
    }


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
}