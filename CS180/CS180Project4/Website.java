package CS180Project4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

import javax.security.auth.login.LoginContext;
/**
 * Website
 *
 * Project 4
 *
 * @author Lucas,Liam,James
 *
 * @version 4/10/2022
 *
 */

public class Website implements Serializable { //main class hosting all the text

    public static String welcomeMessage = "Welcome to Quizzer\nPlease select option to continue";
    public static String signInOrSignUp = "1. Sign in\n2. Sign up \n3. Delete account\n4. Exit";
    public static String exitMessage = "Thank you for using Quizzer";
    private static String teacherChooser = "1. Create Quiz\n2. Edit Quiz\n3. Delete Quiz\n4. View Quiz Submissions" +
            "\n5. Exit";
    private static String studentSelection = "Enter quiz name of quiz you would like to take";


    /// Arrays need to be updated with past data to work
    private static ArrayList<Course> courses = new ArrayList<>();
    private static ArrayList<Student> students = new ArrayList<>();

    public static void QuizzesSaver(ArrayList<Course> courses) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("SavedQuizzes.txt", true))) {
            for (Course course : courses) {
                pw.append(course.getCourseName());
                pw.println();
                for (Quiz quiz : course.getCourseQuizzes()) {
                    pw.append(quiz.getQuizTitle());
                    pw.println();
                    for (Question question : quiz.questions) {
                        pw.append(question.getQuestionLabel());
                        pw.println();
                        for (int i = 0; i < question.getChoices().size(); i++) {
                            pw.append(question.getChoices().get(i));
                        }
                        pw.append(question.getCorrectAnswer());
                        pw.println();
                    }
                }
            }


        } catch (Exception e) {
            System.out.println("Error Saving!");
        }
    }

    public static void SubmissionsSaver(ArrayList<Student> students) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("Submissions.txt", true))) {
            for (Student student : students) {
                pw.append(student.getUsername());
                pw.println();
                for (int i = 0; i < student.getQuizSubmissions().size(); i++) {
                    pw.append(student.getQuizSubmissions().get(i));
                    pw.println();
                }
            }
        } catch (Exception e) {
            System.out.println("Error Saving!");
        }
    }

    public static ArrayList<Student> ReadStudents() {
        ArrayList<Student> students2 = new ArrayList<>(100000);
        try (BufferedReader br = new BufferedReader(new FileReader("Submissions.txt"))) {

            int i = 0;
            while (br.readLine() != null) {
                for (Student student : students2) {
                    student.setUsername(br.readLine());
                    for (String submissioner : student.getQuizSubmissions()) {
                        submissioner = br.readLine();
                    }
                }

            }
            return students2;

        } catch (Exception e) {
            e.printStackTrace();
            return students2;
        }
    }

    public static ArrayList<Course> ReadQuiz() {
        ArrayList<Course> courses2 = new ArrayList<>(10000);
        try (BufferedReader br = new BufferedReader(new FileReader("SavedQuizzes.txt"))) {

            int i = 0;
            while (br.readLine() != null) {
                for (Course course : courses2) {
                    course.setCourseName(br.readLine());
                    for (Quiz quiz : course.getCourseQuizzes()) {
                        quiz.setQuizTitle(br.readLine());
                        for (Question question : quiz.questions) {
                            question.setQuestionLabel(br.readLine());
                            for (int w = 0; w < question.getChoices().size(); w++) {
                                question.getChoices().set(w,br.readLine());
                            }
                            question.setCorrectAnswer(br.readLine());
                        }
                    }
                }
            }

            return courses2;


        } catch (Exception e) {
            e.printStackTrace();
            return courses2;
        }
    }

    public static boolean studentChecker(String userName) {
        for (Student student : students) {
            if (student.getUsername().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    public static Student studentGetter(String userName) {
        for (Student student : students) {
            if (student.getUsername().equals(userName)) {
                return student;
            }
        }

        return null;
    }

    public static void printQuiz(String quizName) {
        for (Course course : courses) {
            for (Quiz quiz : course.courseQuizzes) {
                if (quiz.getQuizTitle().equals(quizName)) {
                    quiz.printRandomizedQuiz();
                }
            }
        }
    }

    public static void removeQuestion(int questionNumber, String quizLabel) {
        for (Course course : courses) {
            for (Quiz quiz : course.courseQuizzes) {
                if (quizLabel.equals(quiz.getQuizTitle())) {
                    quiz.removeQuestion(questionNumber);
                }
            }
        }
    }


    public static void printQuizNames() {
        for (Course course : courses) {
            course.printCourseQuizzes();
        }
    }


    public static void addCourse(Course course) {
        courses.add(course);
    }

    public static boolean courseExists(String course) {
        Course course1 = new Course(course);
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).equals(course1.getCourseName())) {
                return true;
            }
        }
        return false;
    }

    public static void deleteQuiz(String courseName, String quizName) {
        for (int i = 0; i < getCourse(courseName).courseQuizzes.size(); i++) {
            if (getCourse(courseName).courseQuizzes.get(i).getQuizTitle().equals(quizName)) {
                getCourse(courseName).deleteQuiz(getCourse(courseName).courseQuizzes.get(i));
            }
        }

    }

    public static Course getCourse(String courseName) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).courseName.equals(courseName)) {
                return courses.get(i);
            }
        }

        return null;
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int signinOrSignUpOption;
        String signInMenuUsername = "Please enter username"; //username for sign in
        String userUsername = ""; // username of the user
        String signInMenuPassword = "Please enter password"; //password for sign in
        String userPassword = ""; // password or user
        LogIn c = new LogIn(userUsername, userPassword);
        CurrentDateTime d = new CurrentDateTime();//to get current date and time.
        System.out.println(d.currentTime());
        c.readUsernameAndPasswordFile();
        boolean exitOperator = true; //keeps you in loop until you want to exit
        boolean isSignedIn = false; //checks if user is signed in
        int studentOrTeacher;//to know if the user is student or teacher

        int deleteOrNot;//checks if the user wants to acctuly delete their account


        do {

            System.out.println(welcomeMessage);//welcome message


            do {
                System.out.println(signInOrSignUp);//ask if they want to sign in sign up, delete or exit menu
                signinOrSignUpOption = scanner.nextInt();//gets int of signin or signup
                scanner.nextLine();
                if ((signinOrSignUpOption != 1) && (signinOrSignUpOption != 2) && (signinOrSignUpOption != 3)
                        && (signinOrSignUpOption != 4)) {
                    System.out.println("Please select one of the available options! (1,2,3,4)");
                }
            } while ((signinOrSignUpOption != 1) && (signinOrSignUpOption != 2) && (signinOrSignUpOption != 3)
                    && (signinOrSignUpOption != 4));


            if (signinOrSignUpOption == 1) { //sign in
                boolean signInWorkOrNot = true; //checks if the sign in worked or not
                int wantToTryToSignInAgain; //checks if you want to try to sign in again or not
                do {//do for signing in
                    System.out.println(signInMenuUsername);
                    userUsername = scanner.nextLine();

                    System.out.println(signInMenuPassword);
                    userPassword = scanner.nextLine();


                    c.checkIfUsernameAndPasswordExist(userUsername, userPassword);//checks if username exists
                    wantToTryToSignInAgain = 1;
                    do {
                        if (c.checkIfUsernameAndPasswordExist(userUsername, userPassword) == false) {//if username does not exist
                            System.out.println("Username or Password does not match.\nWould you like to try again?\n1.Yes\n2.No");
                            wantToTryToSignInAgain = scanner.nextInt();
                            scanner.nextLine();
                            if (wantToTryToSignInAgain == 1) {//user wants to sign in agian
                                signInWorkOrNot = false;//stays in do
                            } else if (wantToTryToSignInAgain == 2) {//user does not want to sign in again
                                signInWorkOrNot = true;//gets out of the signin do
                                exitOperator = false;
                                SubmissionsSaver(students);
                                QuizzesSaver(courses);
                                System.out.println(exitMessage);
                                return;
                            } else {
                                System.out.println("Please select one of the available options! (1,2,3,4)");
                            }


                        }
                    } while ((wantToTryToSignInAgain != 1) && (wantToTryToSignInAgain != 2));


                    if (c.checkIfUsernameAndPasswordExist(userUsername, userPassword) == true) {//if username exists
                        signInWorkOrNot = true;
                        isSignedIn = true;
                    }
                } while (signInWorkOrNot == false);


            } else if (signinOrSignUpOption == 2) { //sign up
                System.out.println("Please create username");
                userUsername = scanner.nextLine();

                System.out.println("Please create password");
                userPassword = scanner.nextLine();
                c.addAccountToFile(userUsername, userPassword);//adds username and password to array list
                isSignedIn = true;
            } else if (signinOrSignUpOption == 3) { //delete account

                System.out.println("You are now deleting your account.  Would you like to proceed.\n1.Yes\n2.No");
                deleteOrNot = scanner.nextInt();
                scanner.nextLine();
                if (deleteOrNot == 1) {
                    boolean signInWorkOrNot = true; //checks if the sign in worked or not
                    int wantToTryToSignInAgain; //checks if you want to try to sign in again or not
                    do {
                        System.out.println(signInMenuUsername);
                        userUsername = scanner.nextLine();

                        System.out.println(signInMenuPassword);
                        userPassword = scanner.nextLine();


                        c.checkIfUsernameAndPasswordExist(userUsername, userPassword);//checks if username and password exist


                        if (c.checkIfUsernameAndPasswordExist(userUsername, userPassword) == false) {//if it does not exist you can try again
                            System.out.println("Username or Password does not match.\nWould you like to try again?\n1.yes\n2.No");
                            wantToTryToSignInAgain = scanner.nextInt();
                            scanner.nextLine();
                            if (wantToTryToSignInAgain == 1) {//try again to make sure there account is registered to delete it
                                signInWorkOrNot = false;
                            } else if (wantToTryToSignInAgain == 2) {//they dont want to delete account or try again
                                signInWorkOrNot = true;
                            }

                        }
                        if (c.checkIfUsernameAndPasswordExist(userUsername, userPassword) == true) {//if it exists it delets account
                            c.deleteAccountFromFile(userUsername, userPassword);
                            signInWorkOrNot = true;
                        }
                    } while (signInWorkOrNot == false);
                } else if (deleteOrNot == 2) {
                    exitOperator = true;
                }

            }
            if (signinOrSignUpOption == 4) { //exit
                exitOperator = false;
                QuizzesSaver(courses);
                SubmissionsSaver(students);
                return;
            }

            do {
                System.out.println("Are you a student or teacher?\n1.Student\n2.Teacher");
                studentOrTeacher = scanner.nextInt();//reads if they are student or teacher
                scanner.nextLine();
                if ((studentOrTeacher) != 1 && (studentOrTeacher != 2)) {
                    System.out.println("Please select one of the available options! (1,2)");
                }
            } while ((studentOrTeacher) != 1 && (studentOrTeacher != 2));


            while (studentOrTeacher == 2) { //teacher implemation

                /// Asks teacher what they want to do
                int teacherChoice; // teachers selection
                System.out.println(teacherChooser);
                do {
                    teacherChoice = scanner.nextInt();
                    scanner.nextLine();
                    if ((teacherChoice != 1) && (teacherChoice != 2) && (teacherChoice != 3) && (teacherChoice != 4) &&
                            (teacherChoice != 5)) {
                        System.out.println("Please select one of the available options! (1,2,3,4)");
                    }
                } while ((teacherChoice != 1) && (teacherChoice != 2) && (teacherChoice != 3) && (teacherChoice != 4) && (teacherChoice != 5));
                // reprompots if teacher's selection doesn't match options

                /// Create Quiz
                if (teacherChoice == 1) {
                    System.out.println("What is the file name of the quiz you would like to create?");
                    String fileName = scanner.nextLine();
                    Quiz quiz = Quiz.createQuiz(fileName);
                    System.out.println("What course would you like to add this quiz to?");
                    String courseName = scanner.nextLine();
                    Course course = new Course(courseName);
                    if (!courseExists(courseName)) {
                        addCourse(course);
                    }
                    course.addQuiz(quiz);

                    System.out.println("Quiz successfully added!");

                    /// Edit Quiz
                } else if (teacherChoice == 2) {
                    System.out.println("What is the name of quiz you would like to edit?");
                    String quizEditor = scanner.nextLine();
                    printQuiz(quizEditor);
                    System.out.println("What questions would you like to delete?");
                    int questionDelete = scanner.nextInt();
                    scanner.nextLine();
                    removeQuestion(questionDelete, quizEditor);
                    System.out.println("Successfully removed quiz!");


                    /// Delete Quiz
                } else if (teacherChoice == 3) {

                    System.out.println("What course contains the quiz you would like to delete?");
                    String courseName = scanner.nextLine();
                    if (getCourse(courseName) == null) {
                        System.out.println("That course doesn't exist!");
                    } else {
                        System.out.println("What is the name of quiz you would like to delete?");
                        String quizName = scanner.nextLine();
                        deleteQuiz(courseName, quizName);
                    }

                    /// Exit
                } else if (teacherChoice == 5) {
                    studentOrTeacher = 3;
                    exitOperator = false;
                    //System.out.println(exitMessage);
                    // QuizzesSaver(courses);
                    //SubmissionsSaver(students);
                    //return;

                    /// View Quiz Submissions
                } else if (teacherChoice == 4) {
                    try (BufferedReader br = new BufferedReader(new FileReader("Submissions.txt"))) {
                        String submission;
                        while ((submission = br.readLine()) != null) {
                            System.out.println(submission + "\n");
                        }

                    } catch (IOException e) {
                        System.out.println("Error viewing submissions!");
                    } catch (Exception e) {
                        System.out.println("Error viewing submissions!");
                    }
                }


            }


            while (studentOrTeacher == 1) {//Student implementation
                if (studentChecker(userUsername) == false) {
                    Student student = new Student(userUsername);
                    students.add(student);
                    student.setQuizSubmissions(new ArrayList<String>());
                }

                Student student = studentGetter(userUsername);
                String quizToTake;
                System.out.println(studentSelection);
                printQuizNames();
                quizToTake = scanner.nextLine();
                printQuiz(quizToTake);
                System.out.println("Enter Answer to Question 1");
                String answer1 = scanner.nextLine();
                System.out.println("Enter Answer to Question 2");
                String answer2 = scanner.nextLine();
                System.out.println("Enter Answer to Question 3");
                String answer3 = scanner.nextLine();
                System.out.println(d.currentTime());
                String timeStamp = d.currentTime();
                String quizAnswers = answer1 + "," + answer2 + "," + answer3;
                student.addquizSumbission(quizToTake, quizAnswers, timeStamp);

                int goAgain;
                do {
                    System.out.println("Would you like to take another quiz?\n1. Yes\n2. No");
                    goAgain = scanner.nextInt();
                    scanner.nextLine();
                    if ((goAgain != 1) && (goAgain != 2)) {
                        System.out.println("Invalid input: please enter either 1 or 2");
                    } else if (goAgain == 2) {
                        studentOrTeacher = 3;
                        //System.out.println(exitMessage);
                        //return;
                    }
                } while ((goAgain != 1) && (goAgain != 2));


            }
            //ADD CODE FOR REST OF WEBSITE


        } while (exitOperator == true); //exit menu

        System.out.println(exitMessage);
        QuizzesSaver(courses);
        SubmissionsSaver(students);
        c.writeAccountToFile();//writes username and password from the array to file so it is saved for the next user
        /// Save the created quiz to a file



    }


}
