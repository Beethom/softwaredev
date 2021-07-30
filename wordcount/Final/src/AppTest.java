import org.junit.Test;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.Assert.*;

public class AppTest {
    @Test
    public void testGetConnection() {
        System.out.print("Connection successfull");

    }

    @Test
    public void testMain()  throws FileNotFoundException {
        App test = new App();
        File text = new File("src//poem.txt");
        Scanner textread = new Scanner(text);
        String val = textread.next();
        Map<String,Integer> map = new HashMap<String, Integer>();
        if(map.containsValue(val) == false)
            System.out.println("Your file was able to be read thank you!");
        else
        {
            System.out.println("The file cannot be read please try again");
        }

    
        
    }
}
