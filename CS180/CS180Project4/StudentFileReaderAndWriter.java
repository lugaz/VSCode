package CS180Project4;

import java.io.*;
import java.util.Scanner;
import javax.swing.plaf.TextUI;
import java.util.ArrayList;
/**
 * StudentFileReaderAndWriter
 *
 * Project 4
 *
 * @author Lucas 
 *
 * @version 4/10/2022
 *
 */

public class StudentFileReaderAndWriter {
    public String userName;
    public ArrayList<String> studentQuizes = new ArrayList<>();


    public StudentFileReaderAndWriter (String userName){
        this.userName = userName;
    }

    public static void readFile(String userName){
        BufferedReader bfr = null;
        String marker = "";
        int rowNumber = 0;
        ArrayList<String> studentQuizes = new ArrayList<>();
        try {
            //System.out.println(Paths.get("").toAbsolutePath());
            File f = new File(userName + ".txt");
            FileReader fr = new FileReader(f);
            bfr = new BufferedReader(fr);
            String line;
            while ((line = bfr.readLine()) != null) {
                studentQuizes.add(line);
       
            }
            bfr.close();
           
          
        } catch (FileNotFoundException e) {
            System.out.println("The file doesn't exist!");
            e.printStackTrace();
          

        } catch (IOException e) {
            System.out.println("IOException reading file!");
            e.printStackTrace();
           
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


    	
    public static void writeFile(String userName, ArrayList<String> studentQuizes){
        FileOutputStream fos = null;
        PrintWriter pw;
        String line = "";
        boolean returnValue = false;
        String [] studentDataArray;
        studentDataArray = studentQuizes.toArray(new String[studentQuizes.size()]);
        try {
            File f = new File(userName + ".txt");
            if (!f.exists()) {
                f.createNewFile();

            } 
                fos = new FileOutputStream(f); 
                pw = new PrintWriter(fos); 
                for (int i = 0; i < studentDataArray.length ; i++){
                    line = studentDataArray [i];
                   
                    
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
