package CS180Project4;

import java.util.ArrayList;
/**
 * Question
 * <p>
 * Project 4
 *
 * @author Liam
 * @version 4/10/2022
 */
public class Student {
    private String username;
    private ArrayList<String> quizSubmissions;

    public Student(String username) {
        this.username = username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public ArrayList<String> getQuizSubmissions() {
        return quizSubmissions;
    }

    public void setQuizSubmissions(ArrayList<String> submissions) {
        this.quizSubmissions = submissions;
    }

    public String getUsername() {
        return username;
    }

    public void addquizSumbission(String quizName, String answers, String timeStamp) {
        quizSubmissions.add(quizName);
        quizSubmissions.add(answers);
        quizSubmissions.add(timeStamp);
    }

}