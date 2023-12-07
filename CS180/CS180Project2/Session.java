package CS180Project2;

/**
 * LabManager
 *
 * Project 2 
 *
 * @author Lucas 
 *
 * @version 3/3/2022
 *
 */
public class Session{
    private String name;
    private int enrollment;

    public Session (String name, int enrollment){
        this.name = name;
        this.enrollment = enrollment;
    }//end of constructer
    public Session (){
        this.name = "";
        this.enrollment = 0;
    }//end of constructer

    public String getName(){
        return name;
    }//end of getName
    
    public int getEnrollment(){
        return enrollment;
    }//end of getEnrollment

    public void setName(String name){
        this.name = name;
    }//end of setName

    public void setEnrollment(int enrollment){
        this.enrollment = enrollment;
    }//end of setEnrollment

    public String toString(){
        if (enrollment > 0 && !name.equals("")){
            String format = "Session{Name - %s, Enrollment - %d}";
            return String.format(format, this.name, this.enrollment);
        }else {
            String format = "Available";
            return format;
        }
        
    }//end toString

    

} //end of class
