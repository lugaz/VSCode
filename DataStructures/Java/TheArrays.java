package DataStructures.Java;

import java.util.Arrays;

/**
 * TheArrays
 * Example of Array
 */
public class TheArrays {

    public static void main(String[] args) {
        //String
        String[] colors = new String[5];
        colors[0] = "purple";
        colors[1] = "blue";

        System.out.println(Arrays.toString(colors));

        System.out.println(colors[0]);
        
        colors[2] = "yellow";

        System.out.println(Arrays.toString(colors));

        //Int
        int[] numbers = {1,2,3,4,5};

        System.out.println(Arrays.toString(numbers));

        for (int i = 0; i < colors.length; i++){
            System.out.println(colors[i]);
        }

       
    }
}