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
 * @author Lucas and Liam
 * @version 4/10/2022
 */
public class WebsiteServerThread extends Thread{
    private static ArrayList<Quiz> quizzes = new ArrayList<>(); // Array of Saved Quizzes
    private static ArrayList<Student> students = new ArrayList<>(); // Array of Student Submissions

public static void ReadQuizzes2(String fileName) {

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        String line;
        ArrayList<String> quizImport = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            quizImport.add(line);
        }
        for (int b = 0; b < quizImport.size(); b++) {
            if (b == 0) {
                String quizCourse = quizImport.get(b);
                String quizTitle = quizImport.get(b + 1);
                Quiz quiz = new Quiz(quizTitle, quizCourse);
                ArrayList<Question> quizQuestions = new ArrayList<>();

                for (int i = 2; i <= 19; i++) {
                    if (quizImport.get(i + b).contains("QUESTION")) {
                        Question question = new Question();
                        question.setQuestionLabel(quizImport.get(i + b));
                        ArrayList<String> choices = new ArrayList<>();
                        boolean choicesCheck = true;
                        do {
                            for (int w = (i + b) + 1; w < quizImport.size(); w++) {
                                if (quizImport.get(w).contains("ANSWER")) {
                                    question.setCorrectAnswer(quizImport.get(w));
                                    choicesCheck = false;
                                    w = quizImport.size() + 1;
                                } else {
                                    choices.add(quizImport.get(w));
                                }
                            }
                        } while (choicesCheck == true);

                        question.setChoices(choices);
                        quizQuestions.add(question);
                    }
                }
                quiz.setQuestions(quizQuestions);
                quizzes.add(quiz);

            } else if (b % 20 == 0) {
                String quizCourse = quizImport.get(b);
                String quizTitle = quizImport.get(b + 1);
                Quiz quiz = new Quiz(quizTitle, quizCourse);
                ArrayList<Question> quizQuestions = new ArrayList<>();

                for (int i = 2; i <= 19; i++) {
                    if (quizImport.get(i + b).contains("QUESTION")) {
                        Question question = new Question();
                        question.setQuestionLabel(quizImport.get(i + b));
                        ArrayList<String> choices = new ArrayList<>();
                        boolean choicesCheck = true;
                        do {
                            for (int w = (i + b) + 1; w < quizImport.size(); w++)
                                if (quizImport.get(w).contains("ANSWER")) {
                                    question.setCorrectAnswer(quizImport.get(w));
                                    choicesCheck = false;
                                    w = quizImport.size() + 1;
                                } else {
                                    choices.add(quizImport.get(w));
                                }
                        } while (choicesCheck == true);

                        question.setChoices(choices);
                        quizQuestions.add(question);
                    }
                }
                quiz.setQuestions(quizQuestions);
                quizzes.add(quiz);
            } else {
                continue;
            }
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Failure to Create Quiz!",
                "Quiz Creation Failure", JOptionPane.PLAIN_MESSAGE);
        e.printStackTrace();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Failure to Create Quiz!",
                "Quiz Creation Failure", JOptionPane.PLAIN_MESSAGE);
        e.printStackTrace();
    }
}

public static void ReadSubmissions(String fileName) {
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        String line;
        ArrayList<String> quizImport = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            quizImport.add(line);
        }

        ArrayList<String> submissions = new ArrayList<>(quizImport.size());

        for (int i = 3; i < quizImport.size(); i += 3) {
            String studentName = quizImport.get(i - 3);
            Student student = new Student(studentName);
            student.setQuizSubmissions(submissions);
            student.addquizSumbission(quizImport.get(i - 2), quizImport.get(i - 1), quizImport.get(i));
            students.add(student);
        }


    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Failure to Download Submissions!",
                "Download Submissions Failure", JOptionPane.PLAIN_MESSAGE);
        e.printStackTrace();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Failure to Download Submissions!",
                "Download Submissions Failure", JOptionPane.PLAIN_MESSAGE);
        e.printStackTrace();
    }
}

// Saves student submissions to submissions array
public static void SubmissionsSaver(ArrayList<Student> students) {
    try (PrintWriter pw = new PrintWriter(new FileWriter("Submissions.txt", false))) {
        for (Student student : students) {
            pw.write(student.getUsername());
            pw.println();
            for (int i = 0; i < student.getQuizSubmissions().size(); i++) {
                pw.write(student.getQuizSubmissions().get(i));
                pw.println();
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error Saving!",
                "Saving Error", JOptionPane.PLAIN_MESSAGE);
    }
}

// Saves quizzes array to SavedQuizzes.txt
public static void QuizzesSaver(ArrayList<Quiz> quizzes) {
    try (PrintWriter pw = new PrintWriter(new FileWriter("SavedQuizzes.txt", false))) {
        for (Quiz quiz : quizzes) {
            pw.write(quiz.getQuizCourse());
            pw.println();
            pw.write(quiz.getQuizTitle());
            pw.println();
            for (Question question : quiz.getQuestions()) {
                pw.write(question.getQuestionLabel());
                pw.println();
                for (String answers : question.getChoices()) {
                    pw.write(answers);
                    pw.println();
                }
                pw.write(question.getCorrectAnswer());
                pw.println();
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error Saving!",
                "Saving Error", JOptionPane.PLAIN_MESSAGE);
    }
}

// Creates a quiz given a filename of quiz in proper format and adds it to quizzes
public static void createQuiz(String fileName) {
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        String quizCourse = br.readLine();
        String quizTitle = br.readLine();
        Quiz quiz = new Quiz(quizTitle, quizCourse);
        ArrayList<Question> quizQuestions = new ArrayList<>();
        ArrayList<String> quizImport = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            quizImport.add(line);
        }

        for (int i = 0; i < quizImport.size(); i++) {
            if (quizImport.get(i).contains("QUESTION")) {
                Question question = new Question();
                question.setQuestionLabel(quizImport.get(i));
                ArrayList<String> choices = new ArrayList<>();
                boolean choicesCheck = true;
                do {
                    for (int w = i; w < quizImport.size(); w++) {
                        if (quizImport.get(w).contains("ANSWER")) {
                            question.setCorrectAnswer(quizImport.get(w));
                            choicesCheck = false;
                            w = quizImport.size();
                        } else if (quizImport.get(w).contains("QUESTION")) {
                            continue;
                        } else {
                            choices.add(quizImport.get(w));
                        }
                    }
                } while (choicesCheck == true);
                question.setChoices(choices);
                quizQuestions.add(question);
            }
        }
        quiz.setQuestions(quizQuestions);
        quizzes.add(quiz);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Failure to Create Quiz!",
                "Quiz Creation", JOptionPane.PLAIN_MESSAGE);
        e.printStackTrace();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Failure to Create Quiz!",
                "Quiz Creation", JOptionPane.PLAIN_MESSAGE);
        e.printStackTrace();
    }
}

/// Sends a requested quiz to Client
public static ArrayList<String> sendQuiz(String quizName) {
    ArrayList<String> quizSender = new ArrayList<>();
    for (Quiz quiz : quizzes) {
        if (quiz.getQuizTitle().equals(quizName)) {
            String course = quiz.getQuizCourse();
            String quizTitle = quiz.getQuizTitle();
            quizSender.add(course);
            quizSender.add(quizTitle);
            for (Question question : quiz.getQuestions()) {
                quizSender.add(question.getQuestionLabel());
                for (String answers : question.getChoices()) {
                    quizSender.add(answers);
                }
            }
        }
    }
    return quizSender;
}

/// Sends a Submissions to Client
public static ArrayList<String> sendSubmissions() {
    ArrayList<String> submissionsSender = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader("Submissions.txt"))) {
        String submission;
        while ((submission = br.readLine()) != null) {
            submissionsSender.add(submission);
        }

    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error Viewing Submissions!",
                "Submission Viewing", JOptionPane.PLAIN_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error Viewing Submissions!",
                "Submission Viewing", JOptionPane.PLAIN_MESSAGE);
    }

    return submissionsSender;
}

/// Removes a question from a quiz given the question number and quiz name
public static void editQuiz(String quizLabel, String newQuestion, int questionDelete, String answer1
        , String answer2, String answer3, String answer4, String correctAnswer) {
    for (Quiz quiz : quizzes) {
        if (quiz.getQuizTitle().equals(quizLabel)) {
            quiz.editQuizQuestion(questionDelete, newQuestion, answer1, answer2, answer3, answer4, correctAnswer);
        }
    }
}

// Deletes a quiz given the quizzes name
public static void deleteQuiz(String quizName) {
    try {
        quizzes.removeIf(quiz -> quiz.getQuizTitle().equals(quizName));
        JOptionPane.showMessageDialog(null, "Successfully Deleted Quiz!",
                "Delete Quiz", JOptionPane.PLAIN_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Failure deleting quiz! " +
                        "Make sure you input the correct quiz name.",
                "Delete Quiz", JOptionPane.PLAIN_MESSAGE);
    }
}

// checks if student already exists
public static boolean studentChecker(String userName) {
    for (Student student : students) {
        if (student.getUsername().equals(userName)) {
            return true;
        }
    }
    return false;
}

// returns requested student object given student username
public static Student studentGetter(String userName) {
    for (Student student : students) {
        if (student.getUsername().equals(userName)) {
            return student;
        }
    }

    return null;
}


public static void readQuizAttempt(String fileName, String userName, String quizToTake) {
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        String line;
        ArrayList<String> quizImport = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            quizImport.add(line);
        }
        Student student = studentGetter(userName);
        String answer1 = quizImport.get(0);
        String answer2 = quizImport.get(1);
        String answer3 = quizImport.get(2);
        String answer4 = quizImport.get(3);
        CurrentDateTime d = new CurrentDateTime();
        String timeStamp = d.currentTime();
        String quizAnswers = answer1 + "," + answer2 + "," + answer3 + "," + answer4;
        student.addquizSumbission(quizToTake, quizAnswers, timeStamp);
        student.setUsername(userName);
        JOptionPane.showMessageDialog(null, "Successful Submissions with File!",
                "Quiz Submission", JOptionPane.PLAIN_MESSAGE);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error Reading Answers!",
                "Quiz Submission", JOptionPane.PLAIN_MESSAGE);
    }
}
protected Socket socket;
public WebsiteServerThread(Socket clientSocket) {
    this.socket = clientSocket;
}
public void run(){
    boolean runner = true;
    BufferedReader reader = null;
    PrintWriter writer = null;

    do {
        try {
               
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new PrintWriter(socket.getOutputStream());
     }catch (IOException e){
         e.printStackTrace();
         return;
     } {
            try {

                ReadQuizzes2("SavedQuizzes.txt");
                ReadSubmissions("Submissions.txt");

                int studentOrTeacher = 3000;

                try {
                    studentOrTeacher = Integer.parseInt(reader.readLine());
                } catch (Exception e) {
                    runner = true;
                }

                while (studentOrTeacher == 2) {

                    int teacherChoice = Integer.parseInt(reader.readLine());
                    // If Teacher choice is 1 - Create Quiz
                    if (teacherChoice == 1) {
                        String fileName = reader.readLine();
                        createQuiz(fileName);

                    } else if (teacherChoice == 2) { // If Teacher choice is 2 - Edit Quiz
                        String quizEditor = reader.readLine();
                        ArrayList<String> quizToSend = sendQuiz(quizEditor);
                        for (String quizPrinter : quizToSend) {
                            writer.write(quizPrinter);
                            writer.println();
                            writer.flush();
                        }

                        int questionEdit = Integer.parseInt(reader.readLine());
                        String newQuestionformat = reader.readLine();
                        String answer1 = reader.readLine();
                        String answer2 = reader.readLine();
                        String answer3 = reader.readLine();
                        String answer4 = reader.readLine();
                        String answer = reader.readLine();

                        editQuiz(quizEditor, newQuestionformat, questionEdit, answer1, answer2, answer3, answer4, answer);
                    } else if (teacherChoice == 3) { // If Teacher choice is 3 - Delete quiz
                        String fileName = reader.readLine();
                        deleteQuiz(fileName);
                    } else if (teacherChoice == 4) {
                        int length = sendSubmissions().size();
                        writer.write(String.valueOf(length));
                        writer.println();
                        writer.flush();
                        for (String subPrinter : sendSubmissions()) {
                            writer.write(subPrinter);
                            writer.println();
                            writer.flush();
                        }

                    } else {
                        studentOrTeacher = 3;
                        QuizzesSaver(quizzes);
                        SubmissionsSaver(students);
                    }

                    QuizzesSaver(quizzes);
                    SubmissionsSaver(students);

                }

                while (studentOrTeacher == 1) {
                    String userUsername = reader.readLine();

                    if (!studentChecker(userUsername)) {
                        Student student = new Student(userUsername);
                        students.add(student);
                        student.setQuizSubmissions(new ArrayList<String>());
                    }

                    Student student = studentGetter(userUsername);

                    ArrayList<String> quiznames = new ArrayList<>();
                    for (Quiz quiz : quizzes) {
                        quiznames.add(quiz.getQuizTitle());
                    }

                    String size = String.valueOf(quiznames.size());
                    writer.write(size);
                    writer.println();
                    writer.flush();

                    for (String quizname : quiznames) {
                        writer.write(quizname);
                        writer.println();
                        writer.flush();
                    }


                    String quizname = reader.readLine();

                    int fileorDirect = Integer.parseInt(reader.readLine());


                    ArrayList<String> quizToSend = sendQuiz(quizname);
                    for (String quizPrinter : quizToSend) {
                        writer.write(quizPrinter);
                        writer.println();
                        writer.flush();
                    }

                    if (fileorDirect == 1) {

                        String quizAnswers = reader.readLine();
                        String timeStamp = reader.readLine();

                        assert student != null;
                        student.addquizSumbission(quizname, quizAnswers, timeStamp);
                        student.setUsername(userUsername);


                    } else if (fileorDirect == 2) {
                        String fileName = reader.readLine();
                        readQuizAttempt(fileName, userUsername, quizname);
                        assert student != null;
                        student.setUsername(userUsername);

                    }

                    String goAgain;
                    do {
                        goAgain = reader.readLine();

                        if (goAgain.equals("2")) {
                            studentOrTeacher = 3;
                        }

                    } while ((!goAgain.equals("1")) && (!goAgain.equals("2")));

                    QuizzesSaver(quizzes);
                    SubmissionsSaver(students);
                }


            } catch (Exception e) {
                runner = true;
                JOptionPane.showMessageDialog(null, "Error Performing this Action!",
                        "Quiz Submission", JOptionPane.PLAIN_MESSAGE);
            }

        }
    } while (runner) ;
}
}
