package CS180Project5;

import java.util.ArrayList;
import java.util.Collections;
/**
 * Quiz
 * <p>
 * Project 5
 *
 * @author Liam
 * @version 4/10/2022
 */
public class Quiz {
    private String quizCourse;
    private String quizTitle;
    private ArrayList<Question> questions;


    public Quiz(String quizTitle, String quizCourse) {
        this.quizTitle = quizTitle;
        this.quizCourse = quizCourse;
    }

    public Quiz(String quizTitle) {
        this.quizTitle = quizTitle;
    }

    public String getQuizCourse() {
        return quizCourse;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }


    public void removeQuestion(int questionNumber) {
        questions.remove(questionNumber-1);
    }

    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }

    public String getQuizTitle() {
        return this.quizTitle;
    }

    public void editQuizQuestion(int i, String newQuestion, String answer1
            , String answer2, String answer3, String answer4, String correctAnswer) {
        Question question = questions.get(i-1);
        question.setQuestionLabel(newQuestion);
        ArrayList<String> newAnswers = new ArrayList<>();
        newAnswers.add(answer1);
        newAnswers.add(answer2);
        newAnswers.add(answer3);
        newAnswers.add(answer4);
        question.setChoices(newAnswers);
        question.setCorrectAnswer(correctAnswer);
        questions.set(i-1, question);
    }

    public void setQuestions(ArrayList<Question> questions1) {
        this.questions = questions1;
    }
    

}

