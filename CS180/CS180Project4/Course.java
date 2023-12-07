package CS180Project4;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
/**
 * Question
 * <p>
 * Project 4
 *
 * @author Liam
 * @version 4/10/2022
 */
public class Course {
    public ArrayList<Quiz> courseQuizzes;
    public String courseName;

    public Course (String courseName) {
        this.courseName = courseName;
        this.courseQuizzes = new ArrayList<>();
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }
    public ArrayList<Quiz> getCourseQuizzes(){
        return courseQuizzes;
    }

    public void addQuiz (Quiz quiz) {
        courseQuizzes.add(quiz);
    }

    public void deleteQuiz (Quiz quiz) {
        if (!courseQuizzes.contains(quiz)) {
            System.out.println("No quiz found!");
        } else {
            courseQuizzes.remove(quiz);
        }
    }

    public void printCourseQuizzes() {
        for (Quiz quiz : courseQuizzes) {
            System.out.println(quiz.getQuizTitle());
        }
    }




}
