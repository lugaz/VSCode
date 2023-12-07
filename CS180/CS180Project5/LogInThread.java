package CS180Project5;

import java.io.IOException;
import java.net.Socket;
import java.io.*;
import java.net.*;
import java.util.Formatter;
import java.util.Scanner;
import javax.swing.plaf.TextUI;
/**
 * LogInThread
 * <p>
 * Project 5
 *
 * @author Lucas
 * @version 4/10/2022
 */
public class LogInThread extends Thread {
    protected Socket socket;

    public LogInThread(Socket clientSocket) {
        this.socket = clientSocket;
    }

    public void run() {

        String message;
        String response = "";
        String username = "";
        String password = "";
        LogIn li = new LogIn(username, password);
        PrintWriter writer = null;
        BufferedReader reader = null;
        try {
            writer =
                new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        while (true) {
            try {
                message = reader.readLine();
                if (message != null) {
                    if (message.equals("readUsernameAndPasswordFile")) {
                        li.readUsernameAndPasswordFile();
                    }
                    if (message.equals("checkIfUsernameAndPasswordExist")) {
                        li.checkIfUsernameAndPasswordExist(username, password);
                        if (li.checkIfUsernameAndPasswordExist(username, password) == true) {
                            writer.write("true");
                            writer.println();
                            writer.flush();
                        }
                        if (li.checkIfUsernameAndPasswordExist(username, password) == false) {
                            writer.write("false");
                            writer.println();
                            writer.flush();
                        }
                    }
                    if (message.equals("deleteAccount")) {
                        li.deleteAccountFromFile(username, password);

                    }
                    if (message.equals("addAccount")) {
                        li.addAccountToFile(username, password);
                    }
                    if (message.equals("writeAccount")) {
                        li.writeAccountToFile();
                    }
                    if (message.contains("1122")) {
                        String usernameSub = message.substring(4);
                        username = usernameSub;
                    }
                    if (message.contains("1133")) {
                        String passwordSub = message.substring(4);
                        password = passwordSub;
                    }


                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }
}
