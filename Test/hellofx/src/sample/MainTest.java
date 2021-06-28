package sample;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void main () throws FileNotFoundException {
        Main test = new Main();
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