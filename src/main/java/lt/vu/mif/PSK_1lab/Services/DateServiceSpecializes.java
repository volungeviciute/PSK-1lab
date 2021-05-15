package lt.vu.mif.PSK_1lab.Services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ApplicationScoped
@Specializes
public class DateServiceSpecializes extends DateService{
    public String getCurrentDate(){
        try{
            Thread.sleep(100);

        } catch(InterruptedException e){

        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDateTime now = LocalDateTime.now();

        return dtf.format(now);
    }
}
