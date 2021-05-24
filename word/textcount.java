
    import java.io.File;
import java.io.FileNotFoundException;
    import java.util.*;

    //app for word count
    public class textcount{
        public static void main (String [] args) throws FileNotFoundException {
            // Stores words from text
            HashMap <String, Integer>words = new HashMap<String, Integer>();
            //insert file
            File in2 = new File("C:\\\\Users\\\\vante\\\\eclipse-workspace\\\\File reader\\\\src\\\\poem.txt");
            Scanner in = new Scanner(in2);
            //loop to repeat condition while reading the file.
            while (in.hasNext()) {
                String ocu = in.next();
                Integer wordcount = words.get(ocu);
                if (wordcount != null)//check condition for the words
                    wordcount++;
                            else
                                wordcount=1;
                            words.put(ocu,wordcount);


            }
            //close the file
            in.close();
            //print result
            for (Map.Entry<String, Integer > entry: words.entrySet()) {

                System.out.println(entry);


            }
        }

    }


