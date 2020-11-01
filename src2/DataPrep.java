import java.io.File;
import java.il.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class DataPrep
{
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
        }