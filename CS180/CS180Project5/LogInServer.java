package CS180Project5;

import java.io.*;
import java.net.*;
import java.util.Formatter;
import java.io.*;
import java.util.Scanner;
import javax.swing.plaf.TextUI;


import java.util.ArrayList; 
/**
 * ButtonServer
 *
 * Hw-11
 *
 * @author Lucas 
 *
 * @version 4/7/2022
 *
 */
public  class LogInServer {
    public String username;
    public String password;
    public ArrayList<String> importData = new ArrayList<String>();
    public  static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
        
        boolean keepPlaying = true;
       
        String message;
        String response = ""; 
        String username = "";
        String password= "";
        LogIn li = new LogIn(username, password);
        Socket socket = null;
       
    
        try ( 
            ServerSocket serverSocket = new ServerSocket(4242);
           

           
        ) {

            while (true) {
                try {
                    socket = serverSocket.accept();
                } catch (IOException e) {
                   e.printStackTrace();
                }
                // new thread for a client
                new LogInThread(socket).start();
                
                
            }
        } catch (IOException e) {
            
            System.out.println(e.getMessage());
        }
    }

    
}


    
