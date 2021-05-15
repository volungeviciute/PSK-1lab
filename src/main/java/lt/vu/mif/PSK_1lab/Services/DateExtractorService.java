package lt.vu.mif.PSK_1lab.Services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ApplicationScoped
public class DateExtractorService implements Serializable {
    public String getCurrentDate(){
        try{
            Thread.sleep(3000);

        } catch(InterruptedException e){

        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();

        return dtf.format(now);
    }
}
