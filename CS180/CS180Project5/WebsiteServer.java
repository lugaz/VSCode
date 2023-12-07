package CS180Project5;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
/**
 * Question
 * <p>
 * Project 5
 *
 * @author Liam
 * @version 4/10/2022
 */
public  class WebsiteServer {


    




    public  static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
       
        boolean keepPlaying = true;
       
        String message;
        String response = ""; 
        String username = "";
        String password= "";
      
        Socket socket = null;
        
         
        try ( 
            ServerSocket serverSocket = new ServerSocket(5353);
           
        
           
        ) {
        
            while (true) {
                try {
                     socket = serverSocket.accept();
                } catch (IOException e) {
                   e.printStackTrace();
                }
                // new thread for a client
                new WebsiteServerThread(socket).start();
                
                
            }
        } catch (IOException e) {
            
            System.out.println(e.getMessage());
        }
        }
       
}


