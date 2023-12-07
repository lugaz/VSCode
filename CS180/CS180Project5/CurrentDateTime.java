package CS180Project5;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
/**
 * CurrentDateTime
 * <p>
 * Project 5
 *
 * @author Lucas
 * @version 4/10/2022
 */
public class CurrentDateTime {
    public static String currentTime() {    //Gets date and time for the current moment
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}    
