package CS180Project4;

import java.io.*;
import java.util.Scanner;
import javax.swing.plaf.TextUI;
import java.util.ArrayList; 
/**
 * LogIn
 *
 * Project 4
 *
 * @author Lucas 
 *
 * @version 4/10/2022
 *
 */
public class LogIn {
    public String username;
    public String password;
    public ArrayList<String> importData = new ArrayList<String>();
    
    

    public LogIn (String username, String password){
        this.username = username;
        this.password = password;
    }

    public ArrayList<String>  readUsernameAndPasswordFile(){
        BufferedReader bfr = null;
        
       
        
        try {
            //System.out.println(Paths.get("").toAbsolutePath());
            File f = new File("SavedUsernameAndPasswords.txt");
            FileReader fr = new FileReader(f);
            bfr = new BufferedReader(fr);
            String line;
            while ((line = bfr.readLine()) != null) {
               importData.add(line);
       
            }
            bfr.close();
            return importData;
        } catch (FileNotFoundException e) {
            System.out.println("Username or password does not match!");
            return null;
            

        } catch (Exception e) {
            System.out.println("IOException reading file!");
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

    public boolean checkIfUsernameAndPasswordExist(String username, String password){
        
        String [] importDataArray;
        importDataArray = importData.toArray(new String[importData.size()]);
        for (int i = 0; i < importData.size(); i++ ){
            if (importDataArray[i].equals(username)){
                i++;
                if(importDataArray[i].equals(password)){
                    return true;

                }
                
            }else{
                i = i + 1;
                
                
            }
        }
        return false;
    }

    public ArrayList<String>  deleteAccountFromFile(String username, String password){

        String [] importDataArray;
        importDataArray = importData.toArray(new String[importData.size()]);
        for (int i = 0; i < importData.size(); i++ ){
            if (importDataArray[i].equals(username)){
                importData.remove(i);
                importData.remove(i);
                
            }
            
        }
        return importData;
        
    }

    public void addAccountToFile(String username, String password){
        importData.add(username);
        importData.add(password);

    }


    public void writeAccountToFile(){
        FileOutputStream fos = null;
        PrintWriter pw;
        String line = "";
        String [] importDataArray;
        importDataArray = importData.toArray(new String[importData.size()]);
        try {
            File f = new File("SavedUsernameAndPasswords.txt");
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
