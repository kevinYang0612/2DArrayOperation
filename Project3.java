import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Objects;
import java.util.Scanner;
/**
 * This is a Java executable class that will read files called "in3a.txt" and "in3b.txt"
 * This class will execute operations and will save to a text file called "out3.txt"
 *
 * @Bohan Yang
 * @version 2021 Feb 20th
 */

public class Project3
{
   public static void main(String[] theArgs)
   {
      Scanner console = new Scanner(System.in);
      Scanner input = null;
      PrintStream output = null;

      String inFileName = "in3b.txt";
      //String inFileName = "in3a.txt";
      String outFileName = "out3.txt";
      int[][] array1;
      int[][] array2;
      int[][] array3 =null;
      int[][] array4;
      boolean filesOk = false;
      do           
      {
         try
         {
            input = new Scanner(new File(inFileName));  
            output = new PrintStream(new File(outFileName)); 
            filesOk = true;
         }
         catch(FileNotFoundException e)
         {
            System.out.print("Can't open file - " + e + "\nEnter File Name: ");
            inFileName = console.nextLine(); 
         }
      }
      while(!filesOk);
      array1 = getArray(input);
      if(input.hasNext())
      {
         array2 = getArray(input);
         int[][] sumResult = ArrayMath.additionOfArrays(array1, array2);
         int[][] subResult = ArrayMath.substractionOfArrays(array1, array2);
         writeArrayAddOrSubstract("plus", sumResult, array1, array2, output);
         writeArrayAddOrSubstract("minus", subResult, array1, array2, output);
      }
      if(input.hasNext())
      {
         array3 = getArray(input);
      }
      if(input.hasNext())
      {
         array4 = getArray(input);
         int[][] multiResult = ArrayMath.multiplicationOfArrays(array3, array4);
         writeArrayTimes(multiResult, array3, array4, output);
      }


      input.close();
      output.close();
   }
   public static int[][] getArray(Scanner theIn)
   {
      int[][] a = new int[theIn.nextInt()][theIn.nextInt()]; 
      for(int row = 0; row < a.length; row++)
      {
         for(int col = 0; col < a[row].length; col++)
         {
            a[row][col] = theIn.nextInt();
         }
      }
      return a;
   }
   public static void writeArrayTimes(int[][] theA, int[][] theArray1, int[][] theArray2, PrintStream theOut)
   {
      theOut.println("Matrix C times Matrix D: ");
      String numToStr = "";                        // purpose is getting numbers to string, using string.length() to get which number takes most places, (negative numbers included)
      int maxDigits = 0;                           // getting a max digits
      String totalSpace = "";                      // Space that will use that is based on the digits of max number + 5 space
      for (int[] ints : theA) {
         for (int col = 0; col < ints.length; col++) {
            numToStr = String.valueOf(ints[col]);     // getting resulting numbers from the multiplication convert to string
            maxDigits = Math.max(numToStr.length(), maxDigits);  // comparing......

         }
      }
      totalSpace = "%" + String.format("%d", maxDigits + 5) + "s"; // get that maxDigits and concatenate to look like "%10", 10 is from maxDigits + 5
      int max = Math.max(theArray1.length, theArray1[0].length);  // matrix c's row != matrix d's row, we want to find who has a greater row
      for(int row = 0; row < max; row++)                          // printing the first row 
      {
          if(row >= theArray1.length)
          {
             for(int col = 0; col < theArray1[0].length; col++)
             {
                
                theOut.printf("%5s", " ");              // last row will print all white space
                
             }
          }
          else
         {
         if(row == theArray1.length / 2 - 1)                         // determine where the operator should be at 
         {
            theOut.printf("%5s", "*");
         }
         else
         {
            theOut.printf("%5s", " ");                    // white spaces exist where operator doesn't
         }
         if(row >= theArray2.length)                           
         {
            for(int col = 0; col < theArray2[0].length; col++)
            {
               theOut.printf("%5s", " ");
            }
         }
         else
         {
            for(int col = 0; col < theArray2[0].length; col++)
            {
               theOut.printf("%5s", theArray2[row][col]);  // print out Matrix D
            }
         }
         if(row == theArray1.length / 2 - 1)
         {
            theOut.printf("%5s", "=");                 // determine where = should be at
         }
         else
         {
            theOut.printf("%5s", " ");                 // white spaces will fill out where = does not have
         }
         if(row >= theA.length)
         {
            for(int col = 0; col < theA[0].length; col++)
            {
               theOut.printf("%5s", " ");
            }
         }
         else
         {
            for(int col = 0; col < theA[0].length; col++)
            {
               theOut.printf(totalSpace, theA[row][col]);  // print out resulting matrix after muiltiply, totalSpace is based on the maxDigits + 5
            }
         }
         theOut.println(); // new line 
      }
   }
   }
   public static void writeArrayAddOrSubstract(String theSign, int[][] theA, int[][] theArray1, int[][] theArray2, PrintStream theOut)
   {
      if(theA == null || theArray1 == null || theArray2 == null)
      {
         return;
      }      
      theOut.println("Matrix A " + theSign + " Matrix B: ");      // sign will determine which println will display
      for(int row = 0; row < theArray1.length; row++)
      {
         for(int col = 0; col < theArray1[row].length; col++)
         {
            theOut.printf("%5d", theArray1[row][col]);  // display Matrix A
         }
         if(row == theArray1.length / 2 - 1)
         {
            if(Objects.equals(theSign, "plus"))
            {
               theOut.printf("%5s", "+");              // determine where the + should be
            }
            else if(Objects.equals(theSign, "minus"))
            {
               theOut.printf("%5s", "-");
            }
         }
         else
         {
            theOut.printf("%5s", " ");
         }
         for(int col = 0; col < theArray2[row].length; col++)
         {
            theOut.printf("%5d", theArray2[row][col]);       // display matrix B
         }
         if(row == theArray1.length / 2 - 1)
         {
            theOut.printf("%5s", "=");                       // display =
         }
         else
         {
            theOut.printf("%5s", " ");
         }
         for(int col = 0; col < theA[row].length; col++)
         {
            theOut.printf("%5d", theA[row][col]);            // display resulting matrix after operation
         }
         theOut.println(); 
      }
   }
   
}