package cnrs.lattice.srcmf;
 
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
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
//	static {
//	    System.load(Params.catalinabase + Params.dir + "library.so");
//	  }
//	static {
//	    try {
//	        System.loadLibrary("wapiti"); // used for tests. This library in classpath only
//	    } catch (UnsatisfiedLinkError e) {
//	        try {
////	            NativeUtils.loadLibraryFromJar("/natives/crypt.dll"); // during runtime. .DLL within .JAR
//	            NativeUtils.loadLibraryFromJar("/natives/"+System.mapLibraryName("crypt"));
//	        } catch (IOException e1) {
//	            throw new RuntimeException(e1);
//	        }
//	    }
//	}
	
	private String tmpResPath = "";
    private String chemin = Params.userhome + Params.dir;
    private String logName = "res.log";
    private String TEST = "";
    private String MODEL_USED = "";
    private String model = "";
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
        this.model = model;
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
    
    public void apiLaunch() {
    	
    	try{
	    	tmpResPath = Tools.tempFile("tmp", ".res", "");
			String[] arguments = { "1on1", "-test", chemin +"tests/"+ TEST + ".conll", 
					"-matemodel", chemin+"models/"+model+"/"+model+".matemodel",
					"-wapitimodel", chemin+"models/"+model+"/"+model+".wapitimodel",
					"-out",	tmpResPath };
			cnrs.lattice.engines.main.Main.main(arguments);
			
			//Tools.ecrire(chemin+logName, MODEL_USED+"\n"+TEST);
    	}catch(Exception e){
    		Tools.ecrire(chemin+logName, Tools.getDateTime() + "\n" +e.getMessage());
    		FacesMessage message = new FacesMessage("Warning", e.getMessage());
        	FacesContext.getCurrentInstance().addMessage(null, message);    	
    	}
    	RequestContext context = RequestContext.getCurrentInstance();
		//update panel
        // context.update("arborator");
        context.execute("location.reload();");        
    	context.scrollTo("arborator");
    	FacesMessage message = new FacesMessage("Successful", "Annotation finished.");
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
    	try{
	    	String path = Tools.tempFile("tmp", ".conll", this.uploadedFileContent);
	    	tmpResPath = Tools.tempFile("tmp", ".res", "");
	    	System.out.println(path);
			String[] arguments = { "1on1", "-test", path, 
					"-matemodel", chemin+"models/"+model+"/"+model+".matemodel",
					"-wapitimodel", chemin+"models/"+model+"/"+model+".wapitimodel",
					"-out",	tmpResPath };//chemin + tmpResPath
			cnrs.lattice.engines.main.Main.main(arguments);
			//Tools.ecrire(chemin+logName, MODEL_USED+"\n"+path);
			
	    }catch(Exception e){
	    	Tools.ecrire(chemin+logName, Tools.getDateTime() + "\n" +e.getMessage());
			FacesMessage message = new FacesMessage("Warning", e.getMessage());
	    	FacesContext.getCurrentInstance().addMessage(null, message);    	
		}
    	RequestContext context = RequestContext.getCurrentInstance();
		//update panel
        // context.update("arborator");
        context.execute("location.reload();");        
    	context.scrollTo("arborator");
    	FacesMessage message = new FacesMessage("Successful", "Annotation finished.");
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
    
    public void updateArbo() {
        RequestContext context = RequestContext.getCurrentInstance();
//        context.addCallbackParam("saved", true);    //basic parameter
//        context.addCallbackParam("user", user);     //pojo as json
         
        //execute javascript oncomplete
//        context.execute("PrimeFaces.info('Hello from the Backing Bean');");
        context.execute("location.reload();");
         
        //update panel
        context.update("arborator");
         
        //scroll to panel
//        context.scrollTo("arborator");
    }
    
    public void refreshPage(){
    	FacesContext context = FacesContext.getCurrentInstance();
    	String viewId = context.getViewRoot().getViewId();
    	ViewHandler handler = context.getApplication().getViewHandler();
    	UIViewRoot root = handler.createView(context, viewId);
    	root.setViewId(viewId);
    	context.setViewRoot(root);
    }
    
    public void growlSuccess(String msg){
    	FacesMessage message = new FacesMessage("Successful", msg);
     	FacesContext.getCurrentInstance().addMessage(null, message); 
    }
    
    public void exit() {
    	System.exit(0);
    }
    
}