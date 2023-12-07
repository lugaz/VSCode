package CS180Project5;

import java.util.ArrayList;
import java.util.Collections;
/**
 * Question
 * <p>
 * Project 5
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
        this.answers = choices;
    }

    public String getCorrectAnswer() {
        return (correctAnswer);
    }

    public void setCorrectAnswer(String correctAnswers) {this.correctAnswer = correctAnswers; }

    

}
