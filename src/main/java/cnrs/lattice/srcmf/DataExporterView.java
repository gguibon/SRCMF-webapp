package cnrs.lattice.srcmf;
 
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
	private List<Sentence> sentences;
         
    @ManagedProperty(value="#{wordService}")
    private WordService wordService;
     
    @ManagedProperty(value="#{modelService}")
    private ModelService modelService;
    
    @ManagedProperty(value="#{testService}")
    private TestService testService;
    
	@ManagedProperty(value="#{sentenceService}")
	private SentenceService sentenceService;
    
    @PostConstruct
    public void init() {
        try {
			words = wordService.createWords();
			models = modelService.createModels();
			testFiles = testService.createTestFiles();
			sentences = sentenceService.createSentences();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
    
    
    public List<Word> getWords() {
        return words;
    }
    
    public void setWordService(WordService wordService) {
        this.wordService = wordService;
    }
    
    public List<Model> getModels() {
        return models;
    }
    
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }
    
    public List<TestFile> getTestFiles(){
    	return testFiles;
    }
    
    public void setTestService(TestService testService){
    	this.testService = testService;
    }
    
    public List<Sentence> getSentences(){
    	return sentences;
    }
    // do not forget the second is service
    public void setSentenceService(SentenceService sentenceService){
    	this.sentenceService = sentenceService;
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