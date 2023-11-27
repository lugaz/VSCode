package DataStructures.Java;

import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.SourceDataLine;



public class TheList {
   public static void main(String[] args) {
        List<String> colors = new ArrayList<>();
        colors.add("blue");
        colors.add("purple");
        colors.add("yellow");
        System.out.println(colors);

        for (String color : colors){
            System.out.println(color);
        }

      


   } 
}
