package database;
import java.sql.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
public class db {

	public static void main(String[] args) throws Exception {
		
			        //Declare collection for returning pair of words and numbers, the collection name is words
	        HashMap <String, Integer>words = new HashMap<String, Integer>();
	        String fileName = "C:\\Users\\vante\\eclipse-workspace\\Javadb\\src\\poem.txt";
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
	        for(Map.Entry<String, Integer> i:wordSorted){
				System.out.println("The word: " + i.getKey()  + " " +  i.getValue());
			}
			getConnection();
			newTable();
			put();
			get();
	        }
			public static ArrayList<String> get() throws Exception{
				try{
					Connection con = getConnection();
					PreparedStatement statement = con.prepareStatement("SELECT * FROM word");
					
					ResultSet result = statement.executeQuery();
					
					ArrayList<String> array = new ArrayList<String>();
					while(result.next()){
						System.out.print(result.getString("word"));
						System.out.print(" ");
						array.add(result.getString("word"));
					}
					System.out.println("All records have been selected!");
					return array;
					
				}catch(Exception e){System.out.println(e);}
				return null;
				
			}
		
		
			
			public static void newTable() throws Exception{
				try{
					Connection conn = getConnection();
					PreparedStatement create = conn.prepareStatement("CREATE TABLE IF NOT EXISTS word(id int NOT NULL AUTO_INCREMENT, word varchar(45), PRIMARY KEY(id))");
					create.executeUpdate();			
				}catch(Exception e){System.out.println(e);}
				finally{
					System.out.println("Function Complete.");
					};
				
			}
			//Insert Data into word db
				public static void put() throws Exception{
					 String word1 = "Hello";
					 String word2 = "Friend";
					 String word3 = "New";
					 String word4 = "Entry";
					try{
						Connection con = getConnection();
						PreparedStatement put = con.prepareStatement("INSERT INTO word (word) VALUES ('"+word1+"')");
						put.executeUpdate();
						PreparedStatement putA = con.prepareStatement("INSERT INTO word (word) VALUES ('"+word2+"')");
						putA.executeUpdate();
						PreparedStatement putB = con.prepareStatement("INSERT INTO word (word) VALUES ('"+word3+"')");
						putB.executeUpdate();
						PreparedStatement putC = con.prepareStatement("INSERT INTO word (word) VALUES ('"+word4+"')");
						putC.executeUpdate();
						
					} catch(Exception e){System.out.println(e);}
					finally {
						System.out.println("Insert Completed.");
					}
				}
			public static Connection getConnection() throws Exception{
				try {
					String driver = "com.mysql.jdbc.Driver";
					String url = "jdbc:mysql://localhost:3306/sys";
					String username = "root";
					String password = "admin";
					Class.forName(driver);
		
					Connection conn = DriverManager.getConnection(url, username, password);
					System.out.println("Connected");
					return conn;
		
				}catch(Exception e) {System.out.println(e);
		
				return null;
				}
		
}}

	
	



