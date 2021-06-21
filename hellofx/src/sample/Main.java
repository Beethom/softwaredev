package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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

public class Main extends Application {



    String a[] = {"Descending", "Ascending"};
    /**
     * AN upgrade of the previous program
     * Beethoven MArhone
     */
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

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



            Button button = new Button("Click to View Word Occurences");
            Button exit = new Button("Exit");
            exit.setOnAction(e -> System.exit(0));

            button.setOnAction(new EventHandler<ActionEvent>() {



                public void handle(ActionEvent event) {

                    if(comboBox.getValue() == "Descending") {
                        for(Map.Entry<String, Integer> i:wordSorted1){  // printing the list
                            System.out.println(i.getKey()+"="+i.getValue());
                        }
                    }
                    if(comboBox.getValue() == "Ascending") {
                        for(Map.Entry<String, Integer> i:wordSorted){  // printing the list
                            System.out.println(i.getKey()+"="+i.getValue());
                        }
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
            pane.getChildren().addAll(button, exit);



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

    public static void main(String[] args) throws FileNotFoundException {

        launch(args);
    }

}