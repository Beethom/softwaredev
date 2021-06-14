import java.util.Scanner;

public class TestFib {
    //Iterative
    private static int FibIt(int n) {
        int[] fibArray = new int[n+2];
        int i;

// index 1 is 0 and index 2 is 1
        fibArray[0] = 0;
        fibArray[1] = 1;

        for (i = 2; i <= n; i++)
        {

            fibArray[i] = fibArray[i-1] + fibArray[i-2];
        }

        return fibArray[n];
    }
    private static int fibRe(int  n) {
        if (n == 0)  return 0;
        if (n<= 2) return 1;

        int fibRec = fibRe(n - 1) + fibRe(n - 2);
        return fibRec;
    }
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
       int [] fib = new int[5];

        int Fibrec;
        int fibIt;
        long start;
        long end;
        long totalOftime;
       // loop to iterate throgh in input in the array
        for (int i = 0; i < fib.length; i++)
        {
         System.out.println("Please enter 5 number");
           fib [i] = input.nextInt();
        }



        for(int i=0 ; i<5; i++) {

            start = System.nanoTime();
             Fibrec = fibRe(fib[i]);
            end   = System.nanoTime();
            totalOftime = end - start;

            //Call recursive method and print out the results
            System.out.println(fib[i]+" the number using recusion method is "+Fibrec+" and the time to finish is "+ totalOftime);
            System.out.println();//space for readability

            //Call the iterative method with new startTime, etc and print out the results
            start = System.nanoTime();
            fibIt = FibIt(fib[i]);
            end   = System.nanoTime();
            totalOftime = end - start;
            System.out.println(fib[i]+ " the number with iteration method is "+fibIt+" and the time to finish is "+ totalOftime);
            System.out.println();//clearance space


        }

    }


}
