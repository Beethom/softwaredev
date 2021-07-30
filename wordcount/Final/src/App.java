
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
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

import com.mysql.cj.protocol.Resultset;

public class App extends Application {



    String a[] = {"Descending", "Ascending", "Top 5 Descending","Top 5 Ascending"};
    /**
     * AN upgrade of the previous program
     * Beethoven MArhone
     */
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {


//Declare collection for returning pair of words and numbers, the collection name is words
        HashMap <String, Integer>words = new HashMap<String, Integer>();
        String fileName = "src//poem.txt";
        File textFile = new File(fileName);
        Scanner in = new Scanner(textFile);
        // File in2 = new File("src\\poem.txt");
        //Scanner in = new Scanner(in2);
        //loop to repeat condition while reading the file.

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
            
            
        }catch(Exception e){System.out.println(e);}

        try{
            Connection conn = getConnection();

            PreparedStatement create = conn.prepareStatement("CREATE TABLE IF NOT EXISTS word2(id int NOT NULL AUTO_INCREMENT, word varchar(45), wordcount Integer, PRIMARY KEY(id))");
            create.executeUpdate();	
            Statement stmt = conn.createStatement();
            String sql = "TRUNCATE word2";
            // Execute deletion
            stmt.executeUpdate(sql);		
        }catch(Exception e){System.out.println(e);}
        finally{
            System.out.println("Function Complete.");
            };


        while (in.hasNext()) {
            String ocu = in.next();

            ocu=ocu.replaceAll("[^a-zA-Z]","");
            ocu.toLowerCase();
            if (words.containsKey(ocu)) {
                int wordcount = words.get(ocu);
                words.remove(ocu);
                words.put(ocu, wordcount+1);
                try {
                    Connection conn = getConnection();
                    PreparedStatement put = conn.prepareStatement("UPDATE  word2 set wordcount = wordcount + 1 where word = '"+ocu+"';");
                put.executeUpdate();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            else {
                try {
                    
                
                Connection conn = getConnection();
                PreparedStatement put = conn.prepareStatement("INSERT INTO word2 (word, wordcount) VALUES ('"+ocu+"', 1)");
        put.executeUpdate();}
        catch (Exception e){
            System.out.println(e);
        }
        
                words.put(ocu, 1);
            }
        }
        in.close();
        Set<Map.Entry<String, Integer>> Hash_set = words.entrySet();
        List<Map.Entry<String, Integer>> wordSorted = new ArrayList<Map.Entry<String, Integer>>(Hash_set);
        List<Map.Entry<String, Integer>> wordSorted1 = new ArrayList<Map.Entry<String, Integer>>(Hash_set);
        Collections.sort( wordSorted, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare( Map.Entry<String, Integer> x, Map.Entry<String, Integer> y )

            {
                // asc
                return ( x.getValue()).compareTo( y.getValue() );

            }

        } );
        Collections.sort( wordSorted1, new Comparator<Map.Entry<String, Integer>>(){
        public int compare( Map.Entry<String, Integer> x, Map.Entry<String, Integer> y )

        {
            // sortList in descending order
            return ( y.getValue()).compareTo( x.getValue() );

        }

    });


        try {

            ObservableList<String> options = FXCollections.observableArrayList(a);
            final ComboBox comboBox = new ComboBox(options);


            TextArea textArea = new TextArea();
            textArea.setEditable(false);
            Button button = new Button("Click to View Word Occurences");
            Button exit = new Button("Exit");
            exit.setOnAction(e -> System.exit(0));

            button.setOnAction(new EventHandler<ActionEvent>() {



                public void handle(ActionEvent event) {

                    if(comboBox.getValue() == "Descending") {
                        String result ="";
                        textArea.setText("");
                        String result2 = "";
                        try {
                            
                        
                            Connection conn = getConnection();
                            String sql = "select * from word2 order by wordcount DESC;";
                            PreparedStatement stmt = conn.prepareStatement(sql);
                            ResultSet rs = stmt.executeQuery();
                            
                            while (rs.next()){
                                result2 += rs.getString("word")+ " " + rs.getInt("wordcount") + " \n";
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        //for(Map.Entry<String, Integer> i:wordSorted1){  // printing the list
                          //  result += i.getKey()+"="+i.getValue() + "\n";

                            //System.out.println(i.getKey()+"="+i.getValue());
                       // }
                        textArea.setText(result2);
                    }
                    if(comboBox.getValue() == "Ascending") {
                        String result ="";
                        String result2 ="";
                        textArea.setText("");
                        try {
                            
                        
                        Connection conn = getConnection();
                        String sql = "select * from word2 order by wordcount ASC;";
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        ResultSet rs = stmt.executeQuery();
                        
                        while (rs.next()){
                            result2 += rs.getString("word")+ " " + rs.getInt("wordcount") + " \n";
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                      //  for(Map.Entry<String, Integer> i:wordSorted){  // printing the list
                            //result += i.getKey()+"="+i.getValue() + "\n";
                            //System.out.println(i.getKey()+"="+i.getValue());
                        //}
                        textArea.setText(result2);
                    }
                    if(comboBox.getValue() == "Top 5 Descending") {
                        String result ="";
                        textArea.setText("");
                        String result2 = "";

                        try {
                            
                        
                            Connection conn = getConnection();
                            String sql = "select * from word2 order by wordcount DESC limit 5;";
                            PreparedStatement stmt = conn.prepareStatement(sql);
                            ResultSet rs = stmt.executeQuery();
                            
                            while (rs.next()){
                                result2 += rs.getString("word")+ " " + rs.getInt("wordcount") + " \n";
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                       // int count =0;
                        //for(Map.Entry<String, Integer> i:wordSorted1){  // printing the list
                            //////result += i.getKey()+"="+i.getValue() + "\n";
                            //count++;
                           // if (count>=5)
                               // break;

                            //System.out.println(i.getKey()+"="+i.getValue());
                        //}
                        textArea.setText(result2);
                    }
                    if(comboBox.getValue() == "Top 5 Ascending") {
                       // String result ="";
                       // int count =0;
                        textArea.setText("");
                        String result2 = "";

                        try {
                            
                        
                            Connection conn = getConnection();
                            String sql = "select * from word2 order by wordcount ASC limit 5;";
                            PreparedStatement stmt = conn.prepareStatement(sql);
                            ResultSet rs = stmt.executeQuery();
                            
                            while (rs.next()){
                                result2 += rs.getString("word")+ " " + rs.getInt("wordcount") + " \n";
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                       // for(Map.Entry<String, Integer> i:wordSorted){  // printing the list
                          //  result += i.getKey()+"="+i.getValue() + "\n";
                           // count++;
                           // if (count>=5)
                               // break;
                            //System.out.println(i.getKey()+"="+i.getValue());
                       // }
                        textArea.setText(result2);
                    }
                }
            });



            StackPane holder = new StackPane();
            Canvas canvas = new Canvas(400,  300);

            holder.getChildren().add(canvas);


            holder.setStyle("-fx-background-color: white");



            Label label = new Label("Order Choice: ");
            FlowPane pane = new FlowPane();
            pane.setLayoutX(1);
            pane.setLayoutY(4);
            pane.getChildren().addAll(label, comboBox);
            pane.getChildren().addAll(button, exit,textArea);



            Scene scene = new Scene(pane, 800, 400, Color.DARKRED);
            final Circle circ = new Circle(40, 40, 30);
            //final Group root = new Group(circ);


            primaryStage.setScene(scene);
            primaryStage.setTitle("Text Analyzer");

            primaryStage.show();

        } catch(Exception e) {

            e.printStackTrace();

        }

        
       
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

        return null;}}

    public static void main(String[] args) throws FileNotFoundException {

        launch(args);
        
    }

}