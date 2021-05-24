import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
//app for word count
public class textcount{
	public static void main (String [] args) throws FileNotFoundException {
		HashMap <String, Integer>words = new HashMap<String, Integer>();
		File in2 = new File("C:\\\\Users\\\\vante\\\\eclipse-workspace\\\\File reader\\\\src\\\\poem.txt");
		Scanner in = new Scanner(in2);
		//loop to repeat condition while reading the file.
		while (in.hasNext()) {
			String ocu = in.next();
			if (words.containsKey(ocu)) {
				int wordcount = words.get(ocu);
				words.put(ocu, wordcount);
			}
				else {
					words.put(ocu, 1);
				}
			}
		in.close();
		for (Map.Entry<String, Integer > entry: words.entrySet()) {
			System.out.println(entry);

		}
		}
		
	}
