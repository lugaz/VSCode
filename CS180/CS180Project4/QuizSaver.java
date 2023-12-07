package CS180Project4;

import java.io.*;
import java.util.Scanner;
import javax.swing.plaf.TextUI;
import java.util.ArrayList; 
/**
 * QuizSaver
 *
 * Project 4
 *
 * @author Lucas 
 *
 * @version 4/10/2022
 *
 */
public class QuizSaver {
    private  ArrayList<String> coursesSaved;

    public ArrayList<String>  readUsernameAndPasswordFile(){
        BufferedReader bfr = null;
        
       
        
        try {
            //System.out.println(Paths.get("").toAbsolutePath());
            File f = new File("SavedQuestions.txt");
            FileReader fr = new FileReader(f);
            bfr = new BufferedReader(fr);
            String line;
            while ((line = bfr.readLine()) != null) {
                coursesSaved.add(line);
       
            }
            bfr.close();
            return coursesSaved;
        } catch (FileNotFoundException e) {
          
            return null;
            

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (bfr != null) {
                try {
                    bfr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public void writeQuizToFile(){
        FileOutputStream fos = null;
        PrintWriter pw;
        String line = "";
        String [] importDataArray;
        importDataArray = coursesSaved.toArray(new String[coursesSaved.size()]);
        try {
            File f = new File("SavedQuestions.txt");
                fos = new FileOutputStream(f); 
                pw = new PrintWriter(fos); 
            
                for (int i = 0; i < importDataArray.length ; i++){
                    line = importDataArray [i];
                   
                    pw.println(importDataArray [i]);
                       
        
                    
                }
                
        
                
                pw.close();
               
            
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
           
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}