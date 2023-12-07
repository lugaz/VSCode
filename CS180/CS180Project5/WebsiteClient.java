package CS180Project5;
import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
/**
 * WebsiteClient
 * <p>
 * Project 5
 *
 * @author Lucas and Liam
 * @version 4/10/2022
 */
public class WebsiteClient {
    public static String welcomeMessage = "Welcome to Quizzer\nPlease select option to continue";
    public static String signInOrSignUp = "1. Sign in\n2. Sign up \n3. Delete account\n4. Exit";
    public static String exitMessage = "Thank you for using Quizzer";
    private static String studentSelection = "Enter quiz name of quiz you would like to take";
    private static String teacherChooser = "1. Create Quiz\n2. Edit Quiz\n3. Delete Quiz\n4. View Quiz Submissions" +
        "\n5. Exit";

    private static ArrayList < Quiz > quizzes = new ArrayList < > (); // Array of Saved Quizzes from Server
    private static ArrayList < Student > students = new ArrayList < > (); // Array of Student Submissions from Server

    //Quiz Gui Creator
    public static String QuizStringer(ArrayList < String > quiz) {
        String quizList = "";
        for (String quizPart: quiz) {
            quizList = quizList + quizPart + "\n";
        }
        return quizList;
    }

    // Submission Gui Creator
    public static String SubmissionGui(ArrayList < String > submissions) {
        String submission = "";
        for (String submissionPart: submissions) {
            submission = submission + submissionPart + "\n";
        }
        return submission;
    }

    // Displays Existing Quiz Names for User and Asks Which Quiz to Take
    public static String printQuizNames(ArrayList < String > quizNames) {
        String quizNames1 = "";
        for (String quizname: quizNames) {
            quizNames1 = quizNames1 + quizname + "\n";
        }

        String quizToTake = JOptionPane.showInputDialog(null, quizNames1 + studentSelection, "Student Options", JOptionPane.QUESTION_MESSAGE);

        return quizToTake;

    }

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
        String usernameAndPasswordDontExsitValue = "";
        int signinOrSignUpOption;
        String usernameExsistOrNot;
        String deleteOrNotString;
        String signInMenuUsername = "Please enter username"; //username for sign in
        String userUsername = ""; // username of the user
        String signInMenuPassword = "Please enter password"; //password for sign in
        String userPassword = ""; // password or user
        //LogIn c = new LogIn(userUsername, userPassword);
        //CurrentDateTime d = new CurrentDateTime();//to get current date and time.
        // System.out.println(d.currentTime());
        //c.readUsernameAndPasswordFile();


        Socket s = new Socket("localhost", 4242);
        BufferedReader r = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter w = new PrintWriter(s.getOutputStream());
        boolean exitOperator = true; //keeps you in loop until you want to exit
        do {
            boolean isSignedIn = false; //checks if user is signed in
            int deleteOrNot; //checks if the user wants to acctuly delete their account
            int studentOrTeacher = 0;

            Socket socket = new Socket("localhost", 5353);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            JOptionPane.showMessageDialog(null, "Welcome to Quizzer\nPlease select option to continue",
                "Clicker", JOptionPane.INFORMATION_MESSAGE);

            do {
                signInOrSignUp = JOptionPane.showInputDialog(null, "1. Sign in\n2. Sign up \n3. Delete account\n4. Exit",
                    "Clicker", JOptionPane.QUESTION_MESSAGE);

                if ((signInOrSignUp == null)) {
                    return;
                }

                if (signInOrSignUp.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Option cannot be empty!", "Clicker",
                        JOptionPane.ERROR_MESSAGE);
                } //end if
                if (!signInOrSignUp.equals("1") && !signInOrSignUp.equals("2") && !signInOrSignUp.equals("3") && !signInOrSignUp.equals("4")) {
                    JOptionPane.showMessageDialog(null, "Please enter valid value!", "Clicker",
                        JOptionPane.ERROR_MESSAGE);
                }


            } while (((signInOrSignUp == null) || (signInOrSignUp.isEmpty())) && ((!signInOrSignUp.equals("1")) && !signInOrSignUp.equals("2") && (!signInOrSignUp.equals("3")) && (!signInOrSignUp.equals("4"))));


            int signInOrSignUpOption = Integer.parseInt(signInOrSignUp);


            if (signInOrSignUpOption == 1) { //sign in

                boolean signInWorkOrNot = true; //checks if the sign in worked or not
                int wantToTryToSignInAgain; //checks if you want to try to sign in again or not
                do { //do for signing in
                    do {
                        userUsername = JOptionPane.showInputDialog(null, "What is your username?",
                            "Clicker", JOptionPane.QUESTION_MESSAGE);
                        if (userUsername == null) {
                            return;
                        } else if (userUsername.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Name cannot be empty!", "Clicker",
                                JOptionPane.ERROR_MESSAGE);
                        } //end if

                    } while ((userUsername == null) || (userUsername.isEmpty()));
                    w.write("1122" + userUsername);
                    w.println();
                    w.flush();
                    do {
                        userPassword = JOptionPane.showInputDialog(null, "What is your password?",
                            "Clicker", JOptionPane.QUESTION_MESSAGE);
                        if (userPassword == null) {
                            return;
                        } else if (userPassword.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Name cannot be empty!", "Clicker",
                                JOptionPane.ERROR_MESSAGE);

                        } //end if

                    } while ((userPassword == null) || (userPassword.isEmpty()));


                    w.write("1133" + userPassword);
                    w.println();
                    w.flush();
                    w.write("checkIfUsernameAndPasswordExist");
                    w.println();
                    w.flush();
                    usernameExsistOrNot = r.readLine();
                    // c.checkIfUsernameAndPasswordExist(userUsername, userPassword);//checks if username exists
                    wantToTryToSignInAgain = 1;
                    do {
                        if (usernameExsistOrNot.equals("false")) { //if username does not exist
                            do {
                                usernameAndPasswordDontExsitValue = JOptionPane.showInputDialog(null, "Username or Password does not match.\nWould you like to try again?\n1.Yes\n2.No",
                                    "Clicker", JOptionPane.QUESTION_MESSAGE);
                                if (usernameAndPasswordDontExsitValue == null) {
                                    return;
                                } else if (usernameAndPasswordDontExsitValue.isEmpty()) {
                                    JOptionPane.showMessageDialog(null, "Option cannot be empty!", "Clicker",
                                        JOptionPane.ERROR_MESSAGE);


                                } //end if
                                if (!usernameAndPasswordDontExsitValue.equals("1") && !usernameAndPasswordDontExsitValue.equals("2")) {
                                    JOptionPane.showMessageDialog(null, "Please enter valid value!", "Clicker",
                                        JOptionPane.ERROR_MESSAGE);
                                }

                            } while ((usernameAndPasswordDontExsitValue == null) || (usernameAndPasswordDontExsitValue.isEmpty()) || (!usernameAndPasswordDontExsitValue.equals("1") && usernameAndPasswordDontExsitValue.equals("2")));
                            wantToTryToSignInAgain = Integer.parseInt(usernameAndPasswordDontExsitValue);

                            if (wantToTryToSignInAgain == 1) { //user wants to sign in agian
                                signInWorkOrNot = false; //stays in do
                            } else if (wantToTryToSignInAgain == 2) { //user does not want to sign in again
                                signInWorkOrNot = true; //gets out of the signin do
                                exitOperator = false;
                                JOptionPane.showMessageDialog(null, "Thank you for using clicker",
                                    "Clicker", JOptionPane.INFORMATION_MESSAGE);
                                return;
                            }


                        }
                    } while ((wantToTryToSignInAgain != 1) && (wantToTryToSignInAgain != 2));


                    if (usernameExsistOrNot.equals("true")) { //if username exists
                        signInWorkOrNot = true;
                        isSignedIn = true;
                    }
                } while (signInWorkOrNot == false);


            } else if (signInOrSignUpOption == 2) { //sign up
                boolean createdAccount = false;
                do {
                    do {
                        userUsername = JOptionPane.showInputDialog(null, "What is your username?",
                            "Clicker", JOptionPane.QUESTION_MESSAGE);
                        if (userUsername == null) {
                            return;
                        } else if (userUsername.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Name cannot be empty!", "Clicker",
                                JOptionPane.ERROR_MESSAGE);

                        } //end if

                    } while ((userUsername == null) || (userUsername.isEmpty()));
                    w.write("1122" + userUsername);
                    w.println();
                    w.flush();


                    do {
                        userPassword = JOptionPane.showInputDialog(null, "What is your password?",
                            "Clicker", JOptionPane.QUESTION_MESSAGE);
                        if (userPassword == null) {
                            return;
                        } else if (userPassword.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Name cannot be empty!", "Clicker",
                                JOptionPane.ERROR_MESSAGE);

                        } //end if

                    } while ((userPassword == null) || (userPassword.isEmpty()));
                    w.write("1133" + userPassword);
                    w.println();
                    w.flush();
                    w.write("checkIfUsernameAndPasswordExist");
                    w.println();
                    w.flush();
                    usernameExsistOrNot = r.readLine();


                    if (usernameExsistOrNot.equals("false")) {

                        isSignedIn = true;
                        createdAccount = true;
                        w.write("addAccount");
                        w.println();
                        w.flush(); //adds username and password to array list
                    } else {
                        JOptionPane.showMessageDialog(null, "Account already exists", "Clicker",
                            JOptionPane.ERROR_MESSAGE);

                    }
                } while (createdAccount == false);


            } else if (signInOrSignUpOption == 3) { //delete account

                do {
                    deleteOrNotString = JOptionPane.showInputDialog(null, "You are now deleting your account.  Would you like to proceed.\n1.Yes\n2.No",
                        "Clicker", JOptionPane.QUESTION_MESSAGE);
                    if (deleteOrNotString == null) {
                        return;
                    } else if (deleteOrNotString.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Option cannot be empty!", "Clicker",
                            JOptionPane.ERROR_MESSAGE);


                    } //end if
                    if (!deleteOrNotString.equals("1") && deleteOrNotString.equals("2")) {
                        JOptionPane.showMessageDialog(null, "Please enter valid value!", "Clicker",
                            JOptionPane.ERROR_MESSAGE);
                    }

                } while ((deleteOrNotString == null) || (deleteOrNotString.isEmpty()));
                deleteOrNot = Integer.parseInt(deleteOrNotString);

                if (deleteOrNot == 1) {
                    boolean signInWorkOrNot = true; //checks if the sign in worked or not
                    int wantToTryToSignInAgain; //checks if you want to try to sign in again or not
                    do {


                        do {
                            userUsername = JOptionPane.showInputDialog(null, "What is your username?",
                                "Clicker", JOptionPane.QUESTION_MESSAGE);
                            if (userUsername == null) {
                                return;
                            } else if (userUsername.isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Name cannot be empty!", "Clicker",
                                    JOptionPane.ERROR_MESSAGE);

                            } //end if

                        } while ((userUsername == null) || (userUsername.isEmpty()));
                        w.write("1122" + userUsername);
                        w.println();
                        w.flush();


                        do {
                            userPassword = JOptionPane.showInputDialog(null, "What is your password?",
                                "Clicker", JOptionPane.QUESTION_MESSAGE);
                            if (userPassword == null) {
                                return;
                            } else if (userPassword.isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Name cannot be empty!", "Clicker",
                                    JOptionPane.ERROR_MESSAGE);

                            } //end if

                        } while ((userPassword == null) || (userPassword.isEmpty()));
                        w.write("1133" + userPassword);
                        w.println();
                        w.flush();
                        w.write("checkIfUsernameAndPasswordExist");
                        w.println();
                        w.flush();
                        usernameExsistOrNot = r.readLine();


                        if (usernameExsistOrNot.equals("false")) { //if it does not exist you can try again
                            do {
                                usernameAndPasswordDontExsitValue = JOptionPane.showInputDialog(null, "Username or Password does not match.\nWould you like to try again?\n1.Yes\n2.No",
                                    "Clicker", JOptionPane.QUESTION_MESSAGE);
                                if (usernameAndPasswordDontExsitValue == null) {
                                    return;
                                } else if (usernameAndPasswordDontExsitValue.isEmpty()) {
                                    JOptionPane.showMessageDialog(null, "Option cannot be empty!", "Clicker",
                                        JOptionPane.ERROR_MESSAGE);


                                } //end if
                                if ((!usernameAndPasswordDontExsitValue.equals("1")) && (!usernameAndPasswordDontExsitValue.equals("2"))) {
                                    JOptionPane.showMessageDialog(null, "Please enter valid value!", "Clicker",
                                        JOptionPane.ERROR_MESSAGE);
                                }

                            } while ((usernameAndPasswordDontExsitValue == null) || (usernameAndPasswordDontExsitValue.isEmpty()));
                            wantToTryToSignInAgain = Integer.parseInt(usernameAndPasswordDontExsitValue);

                            if (wantToTryToSignInAgain == 1) { //try again to make sure there account is registered to delete it
                                signInWorkOrNot = false;
                            } else if (wantToTryToSignInAgain == 2) { //they dont want to delete account or try again
                                signInWorkOrNot = true;
                            }

                        }
                        if (usernameExsistOrNot.equals("true")) { //if it exists it delets account
                            w.write("deleteAccount");
                            w.println();
                            w.flush();
                            signInWorkOrNot = true;
                        }
                    } while (signInWorkOrNot == false);
                } else if (deleteOrNot == 2) {
                    exitOperator = true;
                }

            } else if (signInOrSignUpOption == 4) { //exit
                exitOperator = false;
                JOptionPane.showMessageDialog(null, "Thank you for using clicker",
                    "Clicker", JOptionPane.INFORMATION_MESSAGE);
                return;
            }


            do {
                try {
                    String studentorTeacher1 = JOptionPane.showInputDialog(null, "Are you a student ot teacher" +
                        "?\n1.Student\n2.Teacher", "Teacher or Student",
                        JOptionPane.QUESTION_MESSAGE);

                    if (studentorTeacher1 == null) {
                        return;
                    } else {
                        studentOrTeacher = Integer.parseInt(studentorTeacher1);
                    }

                    if ((studentOrTeacher) != 1 && (studentOrTeacher != 2)) {
                        JOptionPane.showMessageDialog(null, "Please select one of the available options! (1,2)",
                            "Teacher or Student", JOptionPane.PLAIN_MESSAGE);
                    }

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please select one of the available options! (1,2)",
                        "Teacher or Student", JOptionPane.PLAIN_MESSAGE);
                }
            } while ((studentOrTeacher != 1) && (studentOrTeacher != 2));

            writer.write(String.valueOf(studentOrTeacher));
            writer.println();
            writer.flush();

            while (studentOrTeacher == 2) { //teacher implemation
                int teacherChoice; // teachers selection
                do {
                    /// Asks teacher what they want to do
                    String teacherChoice1 = JOptionPane.showInputDialog(null, teacherChooser, "Teacher",
                        JOptionPane.QUESTION_MESSAGE);
                    if (teacherChoice1 == null) {
                        return;
                    } else {
                        teacherChoice = Integer.parseInt(teacherChoice1);
                    }
                    if ((teacherChoice != 1) && (teacherChoice != 2) && (teacherChoice != 3) && (teacherChoice != 4) &&
                        (teacherChoice != 5)) {
                        JOptionPane.showMessageDialog(null, "Please select one of the available options! (1,2,3,4)",
                            "Teacher", JOptionPane.PLAIN_MESSAGE);
                    }
                } while ((teacherChoice != 1) && (teacherChoice != 2) && (teacherChoice != 3) && (teacherChoice != 4) && (teacherChoice != 5));
                // reprompots if teacher's selection doesn't match options

                writer.write(String.valueOf(teacherChoice));
                writer.println();
                writer.flush();

                /// Create Quiz
                if (teacherChoice == 1) {
                    String fileName = JOptionPane.showInputDialog(null, "What is the file name of the quiz you would like to create?", "Quiz File",
                        JOptionPane.QUESTION_MESSAGE);

                    if (fileName == null) {
                        return;
                    }

                    writer.write(fileName);
                    writer.println();
                    writer.flush();
                    String randomized = "";
                    do {
                        randomized = JOptionPane.showInputDialog(null, "Would you " +
                            "like this quiz randomized when taken?",
                            "Randomized Quiz",
                            JOptionPane.QUESTION_MESSAGE);
                        if (randomized == null) {
                            return;
                        }

                        if (randomized.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Option cannot be empty!", "Clicker",
                                JOptionPane.ERROR_MESSAGE);
                        }
                    } while (randomized.isEmpty());

                    JOptionPane.showMessageDialog(null, "Quiz Successfully Created!",
                        "Quiz Creation", JOptionPane.PLAIN_MESSAGE);

                    /// Edit Quiz
                } else if (teacherChoice == 2) {
                    String quizEditor = "";
                    do {
                        quizEditor = JOptionPane.showInputDialog(null, "What is the name of " +
                            "quiz you would like to edit?", "Edit Quiz", JOptionPane.QUESTION_MESSAGE);

                        if (quizEditor == null) {
                            return;
                        }

                        if (quizEditor.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Option cannot be empty!", "Clicker",
                                JOptionPane.ERROR_MESSAGE);
                        }

                    } while (quizEditor.isEmpty());

                    writer.write(quizEditor);

                    /// Gets the quiz to print for teacher to see
                    ArrayList < String > quizToPrint = new ArrayList < String > ();
                    String imports = "";

                    writer.println();
                    writer.flush();

                    for (int i = 0; i < 17; i++) { // Has to be 17 because thats how many parts in printed quiz
                        imports = reader.readLine();
                        quizToPrint.add(imports);
                    }
                    String quizString = QuizStringer(quizToPrint);
                    String questionEdit1;
                    do {
                        questionEdit1 = JOptionPane.showInputDialog(null, quizString + "\nWhat question " +
                            "would you like to edit?", "Edit Quiz",
                            JOptionPane.QUESTION_MESSAGE);

                        if (questionEdit1 == null) {
                            return;
                        }

                        if (questionEdit1.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Option cannot be empty!", "Clicker",
                                JOptionPane.ERROR_MESSAGE);
                        }

                    } while (questionEdit1.isEmpty());

                    int questionEdit = Integer.parseInt(questionEdit1);
                    writer.write(String.valueOf(questionEdit));
                    writer.println();
                    writer.flush();

                    String newQuestion = "";
                    do {
                        newQuestion = JOptionPane.showInputDialog(null, "Please input " +
                            "the new question", "Edit Quiz", JOptionPane.QUESTION_MESSAGE);

                        if (newQuestion == null) {
                            return;
                        }

                        if (newQuestion.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Option cannot be empty!", "Clicker",
                                JOptionPane.ERROR_MESSAGE);
                        }

                    } while (newQuestion.isEmpty());

                    String newQuestionformat = String.format("QUESTION %s", newQuestion);
                    writer.write(newQuestionformat);
                    writer.println();
                    writer.flush();

                    String answer1 = "";

                    do {
                        answer1 = JOptionPane.showInputDialog(null, "What is" +
                            " the first answer choice?", "Edit Quiz", JOptionPane.QUESTION_MESSAGE);

                        if (answer1 == null) {
                            return;
                        }

                        if (answer1.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Option cannot be empty!", "Clicker",
                                JOptionPane.ERROR_MESSAGE);
                        }

                    } while (answer1.isEmpty());
                    writer.write(answer1);
                    writer.println();
                    writer.flush();


                    String answer2 = "";

                    do {
                        answer2 = JOptionPane.showInputDialog(null, "What is" +
                            " the second answer choice?", "Edit Quiz", JOptionPane.QUESTION_MESSAGE);

                        if (answer2 == null) {
                            return;
                        }

                        if (answer2.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Option cannot be empty!", "Clicker",
                                JOptionPane.ERROR_MESSAGE);
                        }

                    } while (answer2.isEmpty());

                    writer.write(answer2);
                    writer.println();
                    writer.flush();


                    String answer3 = "";

                    do {
                        answer3 = JOptionPane.showInputDialog(null, "What is" +
                            " the third answer choice?", "Edit Quiz", JOptionPane.QUESTION_MESSAGE);

                        if (answer3 == null) {
                            return;
                        }

                        if (answer3.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Option cannot be empty!", "Clicker",
                                JOptionPane.ERROR_MESSAGE);
                        }

                    } while (answer3.isEmpty());

                    writer.write(answer3);
                    writer.println();
                    writer.flush();


                    String answer4 = "";

                    do {
                        answer4 = JOptionPane.showInputDialog(null, "What is" +
                            " the fourth answer choice?", "Edit Quiz", JOptionPane.QUESTION_MESSAGE);

                        if (answer4 == null) {
                            return;
                        }

                        if (answer4.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Option cannot be empty!", "Clicker",
                                JOptionPane.ERROR_MESSAGE);
                        }

                    } while (answer4.isEmpty());

                    writer.write(answer4);
                    writer.println();
                    writer.flush();


                    String answer = "";

                    do {
                        answer = JOptionPane.showInputDialog(null, "What is the " +
                            "correct answer to this question?" +
                            "", "Edit Quiz", JOptionPane.QUESTION_MESSAGE);

                        if (answer == null) {
                            return;
                        }

                        if (answer.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Option cannot be empty!", "Clicker",
                                JOptionPane.ERROR_MESSAGE);
                        }

                    } while (answer.isEmpty());

                    String answer5 = String.format("ANSWER %s", answer);
                    writer.write(answer5);
                    writer.println();
                    writer.flush();


                    /// Delete Quiz
                } else if (teacherChoice == 3) {
                    String quizName = "";
                    do {
                        quizName = JOptionPane.showInputDialog(null, "What is the " +
                            "name of the quiz you would like to delete?", "Delete Quiz", JOptionPane.QUESTION_MESSAGE);

                        if (quizName == null) {
                            return;
                        }

                        if (quizName.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Option cannot be empty!", "Clicker",
                                JOptionPane.ERROR_MESSAGE);
                        }


                    } while (quizName.isEmpty());
                    writer.write(quizName);
                    writer.println();
                    writer.flush();

                    /// exit
                } else if (teacherChoice == 5) {
                    studentOrTeacher = 3;
                    writer.close();
                    reader.close();
                    socket.close();
                    exitOperator = false;

                    /// View Quiz Submissions
                } else if (teacherChoice == 4) {
                    ArrayList < String > Subimports = new ArrayList < > ();
                    String submission;
                    int length = Integer.parseInt(reader.readLine());
                    for (int i = 0; i < length; i++) {
                        submission = reader.readLine();
                        Subimports.add(submission);
                    }
                    String submissions = SubmissionGui(Subimports);
                    JOptionPane.showMessageDialog(null, submissions,
                        "View Quiz Submissions", JOptionPane.PLAIN_MESSAGE);
                }
            }

            String username = "";
            CurrentDateTime d = new CurrentDateTime();

            // Student Implementation
            while (studentOrTeacher == 1) {
                writer.write(username);
                writer.println();
                writer.flush();

                String quizNameSize = reader.readLine();
                int quizNameLength = Integer.parseInt(quizNameSize);
                ArrayList < String > quizNames = new ArrayList < > ();

                for (int i = 0; i < quizNameLength; i++) {
                    quizNames.add(reader.readLine());
                }

                String quizName = printQuizNames(quizNames);

                writer.write(quizName);
                writer.println();
                writer.flush();

                String fileorDirect;




                do {
                    fileorDirect = JOptionPane.showInputDialog(null, "Would you like to input answers " +
                        "directly (1) or though a file (2)", "Input Answers", JOptionPane.QUESTION_MESSAGE);

                    if (fileorDirect == null) {
                        return;
                    }

                    if (fileorDirect.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Option cannot be empty!", "Clicker",
                            JOptionPane.ERROR_MESSAGE);
                    }
                } while ((!fileorDirect.equals("1")) && (!fileorDirect.equals("2")));

                writer.write(fileorDirect);
                writer.println();
                writer.flush();

                int fileorDirect2 = Integer.parseInt(fileorDirect);



                /// Gets the quiz to print for teacher to see
                ArrayList < String > quizToPrint = new ArrayList < String > ();
                String imports = "";


                for (int i = 0; i < 17; i++) { // Has to be 17 because thats how many parts in printed quiz
                    imports = reader.readLine();
                    quizToPrint.add(imports);
                }
                String quizString = QuizStringer(quizToPrint);


                if (fileorDirect2 == 1) {

                    String answer1 = JOptionPane.showInputDialog(null, quizString + "\nEnter Answer" +
                        " to Question 1", "Quiz", JOptionPane.QUESTION_MESSAGE);

                    if (answer1 == null) {
                        return;
                    }

                    String answer2 = JOptionPane.showInputDialog(null, quizString + "\nEnter Answer" +
                        " to Question 2", "Quiz", JOptionPane.QUESTION_MESSAGE);

                    if (answer2 == null) {
                        return;
                    }

                    String answer3 = JOptionPane.showInputDialog(null, quizString + "\nEnter Answer" +
                        " to Question 3", "Quiz", JOptionPane.QUESTION_MESSAGE);

                    if (answer3 == null) {
                        return;
                    }

                    String timeStamp = d.currentTime();
                    JOptionPane.showMessageDialog(null, timeStamp,
                        "Time Stamp", JOptionPane.PLAIN_MESSAGE);

                    String quizAnswers = answer1 + "," + answer2 + "," + answer3;

                    writer.write(quizAnswers);
                    writer.println();
                    writer.flush();

                    writer.write(timeStamp);
                    writer.println();
                    writer.flush();

                    JOptionPane.showMessageDialog(null, "Quiz Submitted Successfully!",
                        "Quiz Submission", JOptionPane.PLAIN_MESSAGE);


                } else if (fileorDirect2 == 2) {
                    String fileName = JOptionPane.showInputDialog(null, quizString + "\nEnter the " +
                        "filename containing your answers", "Quiz", JOptionPane.QUESTION_MESSAGE);

                    if (fileName == null) {
                        return;
                    }

                    writer.write(fileName);
                    writer.println();
                    writer.flush();

                    JOptionPane.showMessageDialog(null, "Quiz Submitted Successfully!",
                        "Quiz Submission", JOptionPane.PLAIN_MESSAGE);

                }


                String goAgain;

                do {

                    goAgain = JOptionPane.showInputDialog(null, "Would you " +
                        "like to take another quiz?\n1. Yes\n2. No", "Go Again?", JOptionPane.QUESTION_MESSAGE);

                    if (goAgain == null) {
                        return;
                    }

                    if (goAgain.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Option cannot be empty!", "Clicker",
                            JOptionPane.ERROR_MESSAGE);
                    }

                } while ((!goAgain.equals("1")) && (!goAgain.equals("2")));

                writer.write(goAgain);
                writer.println();
                writer.flush();

                if (goAgain.equals("2")) {
                    writer.write(goAgain);
                    writer.println();
                    writer.flush();
                    studentOrTeacher = 3;
                    exitOperator = false;
                }


            }

        } while (exitOperator == true);
        JOptionPane.showMessageDialog(null, exitMessage,
            "Goodbye!", JOptionPane.PLAIN_MESSAGE);
    }
}
