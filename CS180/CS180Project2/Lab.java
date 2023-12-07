package CS180Project2;

import javax.tools.DocumentationTool.Location;
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
public class Lab {
    private Session morning;
    private Session afternoon;
    private int capacity;
    private String location;

    public Lab(Session morning, Session afternoon, int capacity, String location){
        this.morning = morning;
        this.afternoon = afternoon;
        this.capacity = capacity;
        this.location = location;
        
    }//end of constructer

    public Lab(int capacity,String location){
        this.morning = new Session();
        this.afternoon = new Session();
        this.capacity = capacity;
        this.location = location;
    }//end of constructer

    public Session getMorning(){
        return morning;
    }//end of getMorning 

    public Session getAfternoon(){
        return afternoon;
    }//end of getAfternoon

    public int getCapacity(){
        return capacity;
    }//end of getCapacity

    public String getLocation(){
        return location;
    }//end of getLocation

    public void setMorning(Session morning){
        this.morning = morning;
    }//end of setMorning

    public void setAfternoon(Session afternoom){
        this.afternoon = afternoom;
    }//end of setAfternoon

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }//end of setCapacity

    public void setLocation(String location){
        this.location = location;
    }

    public String listAvailabilities(){
        String availableString ="";
        if (this.morning.getEnrollment() == 0){
            availableString ="Morning: Available\n";
        }
        if (this.afternoon.getEnrollment() == 0){

            availableString = availableString + "Afternoon: Available\n";
        }
        if (this.afternoon.getEnrollment() > 0 && this.morning.getEnrollment() > 0){

            availableString = availableString + "No Availabilities";
        }
        
        return availableString;


    }//end listAvailabilities

    public boolean isMorningAvailable(){
        if (this.morning.getEnrollment() == 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean isAfternoonAvailable(){
        if (this.afternoon.getEnrollment() == 0){
            return true;
        }else{
            return false;
        }
    }

    public String listReservations(){
        String availableReservation ="";
        if (this.morning.getEnrollment() > 0){
            availableReservation ="Morning: Reserved\n";
        }
        if (this.afternoon.getEnrollment() > 0){

            availableReservation = availableReservation + "Afternoon: Reserved\n";
        }
        if (this.afternoon.getEnrollment() == 0 && this.morning.getEnrollment() == 0){

            availableReservation = availableReservation + "No Reservations\n";
        }
        
        return availableReservation;
    }//end listReservations

    public String toString(){
        
        String format = "Lab{Capacity - %d, Location - %s, Morning: %s, Afternoon: %s}";
        return String.format(format, this.capacity, this.location, this.morning.toString() ,this.afternoon.toString());
        // String result;
        // result = this.capacity + this.location + this.morning.toString() + this.afternoon.toString();
        // return result;
    }//end toString 

  


}

