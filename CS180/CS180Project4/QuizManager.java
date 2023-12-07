package CS180Project4;

import java.util.ArrayList;
/**
 * QuizManager
 *
 * Project 4
 *
 * @author James 
 *
 * @version 4/10/2022
 *
 */
public class QuizManager {
    ArrayList<Quiz> quizzes;
    
    //Set the quiz manager using the constructor
    public QuizManager(ArrayList<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    //return the quizzes of a course
    public ArrayList<Quiz> getQuizzes() {
        return quizzes;
    }
    
    //get the Quizzes of a course
    public void setQuizzes(ArrayList<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    //add a quiz to a course
    public void addQuiz(Quiz quiz) {
        this.quizzes.add(quiz);
    }

    //delete a quiz from a course
    public void removeQuiz(Quiz quiz) {
        this.quizzes.remove(quiz);
    }

    //modify a quiz from a course
    public void modifyQuiz(Quiz oldQuiz, Quiz newQuiz) {
        this.quizzes.set(quizzes.indexOf(oldQuiz), newQuiz);
    }
}
