package lt.vu.mif.PSK_1lab.usecases;
import lt.vu.mif.PSK_1lab.Services.DateService;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class SchoolDate implements Serializable {
    @Inject
    DateService dateExtractorService;

    private CompletableFuture<String> dateGetterTask = null;

    public String generateDate() throws ExecutionException, InterruptedException {
        System.out.println("getCurrentDate called");
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        dateGetterTask = CompletableFuture.supplyAsync(() -> dateExtractorService.getCurrentDate());

        return "students.xhtml?faces-redirect=true&schoolId=" + requestParameters.get("schoolId");
       // return getDateExtractorStatus();
    }

    public boolean isDateExtractorRunning(){
        return dateGetterTask != null && !dateGetterTask.isDone();
    }

    public String getDateExtractorStatus() throws ExecutionException, InterruptedException{
        if(dateGetterTask == null){
            System.out.println("DATEGETTERTASK NULL");
            return null;
        } else if (isDateExtractorRunning()){

            return "DateExtractorService is running";
        }
        return "Current Date: " + dateGetterTask.get();
    }
}
