package cnrs.lattice.srcmf;
 
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;
 
@ManagedBean
//@ViewScoped
public class Launch1on1 implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private String chemin = Params.dir;
    private String TEST = "";
//    private List<Word> words = new ArrayList<Word>();
//    private List<String> fileNames = new ArrayList<String>();
    private String MODEL = "";
    
    
//    public Launch1on1(){
//    	TEST = "";
//    	MODEL = "";
//    }
//    
//	public static Launch1on1 create() {
//		return new Launch1on1();
//	}
    
    public void setModel(String model)throws Exception{
    	this.MODEL = model+"/"+model;
    }
    
    public void setTest(String test)throws Exception{
    	this.TEST = test;
    }
    
    public String getModel() throws Exception{
    	return MODEL;
    }
    
    public String getTest() throws Exception{
    	return TEST;
    }
    
    public void apiLaunch() throws Exception {
//    	FacesMessage message1 = new FacesMessage(System.getProperty("user.dir"));
//    	FacesContext.getCurrentInstance().addMessage(null, message1);
    	System.out.println(System.getProperty("user.dir"));
    	
		String[] arguments = { "1on1", "-test", chemin +"tests/"+ TEST + ".conll", 
				"-matemodel", chemin+"models/"+MODEL+".mategoldmodel",
				"-wapitimodel", chemin+"models/"+MODEL+".wapitimodel",
				"-out",	chemin + "res.conll" };
		cnrs.lattice.engines.main.Main.main(arguments);
		
		FacesMessage message = new FacesMessage("Successful", "Applying tagging and parsing models.");
    	FacesContext.getCurrentInstance().addMessage(null, message);
    	//RequestContext.getCurrentInstance().scrollTo("charts");
    
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