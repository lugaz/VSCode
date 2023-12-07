package CS180Project4;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;   
/**
 * CurrentDateTime
 *
 * Project 4
 *
 * @author Lucas 
 *
 * @version 4/10/2022
 *
 */
public class CurrentDateTime {    
  public String currentTime() {    //Gets date and time for the current moment
   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
   LocalDateTime now = LocalDateTime.now();  
    return dtf.format(now);
  }    
}    