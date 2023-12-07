package CS180Project4;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Quiz {

    private String quizTitle;
    public ArrayList<Question> questions;


    public Quiz(String quizTitle, ArrayList<Question> questions) {
        this.quizTitle = quizTitle;
        this.questions = questions;
    }

    public Quiz(String quizTitle) {
        this.quizTitle = quizTitle;
    }

    public Quiz() {

    }

    public void removeQuestion(int questionNumber) {
        questions.remove(questionNumber);
    }

    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }

    public String getQuizTitle() {
        return this.quizTitle;
    }


    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public void printQuiz() {
        if (this.questions == null) {
            System.out.println("No quiz found");
            return;
        }
        System.out.println("QuizTitle: " + quizTitle);
        for (Question i : questions) {
            i.printQuestion();
        }
    }

    public void printRandomizedQuiz() {
        System.out.println("Quiz Title: " + quizTitle);
        ArrayList<Question> questions1 = questions;
        Collections.shuffle(questions1);
        for (Question i : questions1) {
            i.printRandomizedQuestion();
        }
    }

    public static Quiz createQuiz(String fileName) {
        Quiz quiz = new Quiz();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            quiz.setQuizTitle(br.readLine());
            System.out.println(quiz.getQuizTitle());
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
                    for (int w = i+1; w < 3; w++) {
                        if (quizImport.get(w).contains("ANSWER")) {
                            question.setCorrectAnswer(quizImport.get(w));
                            question.setChoices(choices);
                        } else {
                            choices.add(quizImport.get(w));
                        }
                    }
                    question.setChoices(choices);
                    quizQuestions.add(question);
                    choices.clear();
                }
            }
            quiz.setQuestions(quizQuestions);


        } catch (IOException e) {
            System.out.println("Failure to Create Quiz");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Failure to Create Quiz");
            e.printStackTrace();
        }

        return quiz;
    }


}
