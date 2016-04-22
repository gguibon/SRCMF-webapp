package cnrs.lattice.srcmf;
 
import java.io.File;
import java.nio.file.Files;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
 
@ManagedBean
public class FileUploadView {
	private static final File LOCATION = new File("./temp/");
    private UploadedFile file;
 
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
    public void upload() {
        if(file != null) {
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    

    public void storeUpload() throws Exception {
        if (file != null) {
            String prefix = FilenameUtils.getBaseName(file.getFileName()); 
            String suffix = FilenameUtils.getExtension(file.getFileName());
            
            File save = File.createTempFile(prefix + "-", "." + suffix, LOCATION);
            Files.write(save.toPath(), file.getContents());
            
            // Add success message here.
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded"
            		+ "and stored in" + save.toPath());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
}