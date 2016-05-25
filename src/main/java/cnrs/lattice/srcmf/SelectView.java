package cnrs.lattice.srcmf;
 
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.UploadedFile;

import cnrs.lattice.tools.utils.Tools;
import cnrs.lattice.srcmf.Params; 

@ManagedBean
@SessionScoped
public class SelectView implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String tmpResPath = "";
    private String chemin = Params.userhome + Params.dir;
    private String logName = "res.log";
    private String TEST = "";
    private String MODEL = "";
    private String MODEL_USED = "";
    private String model;
    private UploadedFile uploadedFile = new UploadedFile() {
		
		@Override
		public void write(String arg0) throws Exception {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public long getSize() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public InputStream getInputstream() throws IOException {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getFileName() {
			// TODO Auto-generated method stub
			return "";
		}
		
		@Override
		public byte[] getContents() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public String getContentType() {
			// TODO Auto-generated method stub
			return null;
		}
	};
	private String uploadedFileContent;    
	
	public String getTmpResPath(){
		return tmpResPath;
	}
	
	public void setTmpResPath(String path){
		this.tmpResPath = path;
	}
	
    public String getModel() {
        return model;
    }
 
    public void setModel(String model) {
        this.model = model+"/"+model;
        this.MODEL_USED = model;
    }
    
    public void setTest(String test){
    	this.TEST = test;
    }
    
    public String getTest(){
    	return TEST;
    }    
 
    public void dummy() {
    	System.out.println("dummy");
    }
    
    public void apiLaunch() throws Exception {
    	tmpResPath = Tools.tempFile("tmp", ".res", "");
		String[] arguments = { "1on1", "-test", chemin +"tests/"+ TEST + ".conll", 
				"-matemodel", chemin+"models/"+model+".mategoldmodel",
				"-wapitimodel", chemin+"models/"+model+".wapitimodel",
				"-out",	tmpResPath };
		cnrs.lattice.engines.main.Main.main(arguments);
		
		Tools.ecrire(chemin+logName, MODEL_USED+"\n"+TEST);
		
		FacesMessage message = new FacesMessage("Successful", "Applying tagging and parsing models.");
    	FacesContext.getCurrentInstance().addMessage(null, message);    	
	}
    
    
    
    
    
    /**
     * returns the uploaded file name with extension
     * @return
     */
    public String getUploadedFileName(){
    	return this.uploadedFile.getFileName();
    }
    
    /**
     * returns the uploaded file string content
     * @return
     */
    public String getUploadedFileContent(){
    	return this.uploadedFileContent;
    }
    
    /**
     * upload the file in the dedicated object
     * @param event
     * @throws Exception
     */
    public void upload(FileUploadEvent event) throws Exception {
        if (event.getFile() != null) {
        	this.uploadedFile = event.getFile();
        	String s = new String(uploadedFile.getContents());
        	this.uploadedFileContent = s;        	
            FacesMessage message = new FacesMessage("Succesful", this.uploadedFile.getFileName() + " is uploaded");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    
    
    
    public void apiLaunchCustom() throws Exception {    	
    	String path = Tools.tempFile("tmp", ".conll", this.uploadedFileContent);
//    	Tools.ecrire("/home/gael/Programmes/apache-tomcat-8.0.30/test.conll", this.uploadedFileContent);
		String[] arguments = { "1on1", "-test", path, 
				"-matemodel", chemin+"models/"+model+".mategoldmodel",
				"-wapitimodel", chemin+"models/"+model+".wapitimodel",
				"-out",	chemin + tmpResPath };
		cnrs.lattice.engines.main.Main.main(arguments);
		Tools.ecrire(chemin+logName, MODEL_USED+"\n"+path);
		
		FacesMessage message = new FacesMessage("Successful", "Applying tagging and parsing models.");
    	FacesContext.getCurrentInstance().addMessage(null, message);
	}
    
    public void toCharts() {
    	RequestContext.getCurrentInstance().scrollTo("charts");
    }
    
    public void toTable() {
    	RequestContext.getCurrentInstance().scrollTo("tab");
    }
    
    public void toSelect() {
    	RequestContext.getCurrentInstance().scrollTo("select");
    }
    
    public void onTabChange(TabChangeEvent event){
    	RequestContext.getCurrentInstance().update("tab");
    	RequestContext.getCurrentInstance().update("charts");
    }
    
    public void exit() {
    	System.exit(0);
    }
    
}