/**
 * This is a module class that includes the following methods that will perform operations
 * Two matrix addition; two matrix substraction; and two matrix multiplcation
 *
 * @Bohan Yang
 * @version 2021 Feb 20th
 */
public class ArrayMath
{
   public static int[][] additionOfArrays(int[][] theA, int[][] theB)
   {
      int[][] sumedArray = new int[theA.length][theA[0].length];
      for (int row = 0; row < theA.length; row++)
      {
         for (int col = 0; col < theA[row].length; col++)
         {
            sumedArray[row][col] = theA[row][col] + theB[row][col];
         }
      }
      return sumedArray;
   }
   public static int[][] substractionOfArrays(int[][] theA, int[][] theB)
   {
      int[][] substractedArray = new int[theA.length][theA[0].length];
      for (int row = 0; row < theA.length; row++)
      {
         for (int col = 0; col < theA[row].length; col++)
         {
            substractedArray[row][col] = theA[row][col] - theB[row][col];
         }
      }
      return substractedArray;
   }
   public static int[][] multiplicationOfArrays(int[][] theA, int[][] theB)
   {
      int[][] multiplication = new int[theA.length][theB[0].length];
      for(int i = 0; i < theA.length; i++)  // row from theA
      {
         for(int j = 0; j < theB[0].length; j++)  // column from theB
         {
            for(int k = 0; k < theA[0].length; k++)  //column from theA == row from theB
            {
               multiplication[i][j] += theA[i][k] * theB[k][j];
            }
         }
      }
      return multiplication;
   }
}