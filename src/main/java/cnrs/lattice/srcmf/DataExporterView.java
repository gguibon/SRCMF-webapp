package cnrs.lattice.srcmf;
 
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;


 
@ManagedBean
public class DataExporterView implements Serializable {
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Word> words;
	private List<Model> models;
	private List<TestFile> testFiles;
         
    @ManagedProperty(value="#{wordService}")
    private WordService service;
     
    @ManagedProperty(value="#{modelService}")
    private ModelService modelService;
    
    @ManagedProperty(value="#{testService}")
    private TestService testService;
    
    @PostConstruct
    public void init() {
//    	System.out.println("hello");
        try {
			words = service.createWords();
			models = modelService.createModels();
			testFiles = testService.createTestFiles();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//        System.out.println(words.get(0).getForm());
    }
 
    public List<Word> getWords() {
        return words;
    }
    
    public List<Model> getModels() {
        return models;
    }
    
    public List<TestFile> getTestFiles(){
    	return testFiles;
    }
 
    public void setService(WordService service) {
        this.service = service;
    }
    
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }
    
    public void setTestService(TestService testService){
    	this.testService = testService;
    }
    
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    
}