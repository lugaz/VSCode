package CS180Project4;

import java.util.ArrayList;
import java.util.Collections;
/**
 * Question
 * <p>
 * Project 4
 *
 * @author Liam
 * @version 4/10/2022
 */
//// Question Class containing the questionLabel- Question, answers- Question answers, and correct answer
public class Question {
    private String questionLabel;
    private ArrayList<String> answers;
    private String correctAnswer;


    /// Question object
    public Question(String questionLabel, ArrayList<String> answers, String correctAnswer) {
        this.questionLabel = questionLabel;
        this.correctAnswer = correctAnswer;
        this.answers = answers;
    }

    public Question() {

    }


    public String getQuestionLabel() {
        return questionLabel;
    }

    public void setQuestionLabel(String questionLabel) {
        this.questionLabel = questionLabel;
    }

    public ArrayList<String> getChoices() {
        return answers;
    }

    public void setChoices(ArrayList<String> choices) {
        this.answers = answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }


    /// print just question and choices/answers
    public void printQuestion() {
        System.out.println(questionLabel);
        System.out.println(answers.toString() + "\n");
    }

    public void printRandomizedQuestion() {
        ArrayList<String> randomizedAnswers = answers;
        Collections.shuffle(randomizedAnswers);
        System.out.println(questionLabel);
        System.out.println(randomizedAnswers.toString() + "\n");
    }


}