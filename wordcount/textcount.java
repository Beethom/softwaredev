import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//app for word count
public class textcount{
    public static void main (String [] args) throws FileNotFoundException {
        //Declare collection for returning pair of words and numbers, the collection name is words
        HashMap <String, Integer>words = new HashMap<String, Integer>();
        String fileName = "src\\poem.txt";
        File textFile = new File(fileName);
        Scanner in = new Scanner(textFile);
       // File in2 = new File("src\\poem.txt");
        //Scanner in = new Scanner(in2);
        //loop to repeat condition while reading the file.
        while (in.hasNext()) {
            String ocu = in.next();

            ocu=ocu.replaceAll("[^a-zA-Z]","");
            ocu.toLowerCase();
            if (words.containsKey(ocu)) {
                int wordcount = words.get(ocu);
                words.remove(ocu);
                words.put(ocu, wordcount+1);
            }
            else {
                words.put(ocu, 1);
            }
        }
        in.close();
        Set<Map.Entry<String, Integer>> Hash_set = words.entrySet();
        List<Map.Entry<String, Integer>> wordSorted = new ArrayList<Map.Entry<String, Integer>>(Hash_set);
        Collections.sort( wordSorted, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare( Map.Entry<String, Integer> x, Map.Entry<String, Integer> y )

            {
                // sortList in descending order
                return ( y.getValue()).compareTo( x.getValue() );

            }

        } );
        Map<String, Integer> hm1 = sortByValue(wordCountPairs);
        // Loop through 20 times and print the Top 20 Words - outputting the word and its count
        for(int i =1; i < 21; i++){
            System.out.println("" + i + " - " + hm1.entrySet().toArray()[hm1.size()-i] );
        }


        }
    }
