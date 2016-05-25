package cnrs.lattice.srcmf;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

//@SuppressWarnings("unused")
@ManagedBean
public class Model implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name = "";
	private String displayName = "";
	private String wapitiName = "";
	private String mateName = "";
	
	public Model(){
		name = "";
		displayName="";
		wapitiName = "";
		mateName = "";
	}
	
	public static Model create() {
		return new Model();
	}
	
	//// setters
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setdisplayName(String displayName){
		this.displayName = displayName;
	}
	
	public void setWapitiName(String name){
		this.wapitiName = name;
	}
	
	public void setMateName(String name){
		this.mateName = name;
	}
	
	//// geters
	
	public String getName(){
		return this.name;
	}
	
	public String getDisplayName(){
		return this.displayName;
	}
	
	public String getWapitiName(){
		return this.wapitiName;
	}
	
	public String getMateName(){
		return this.mateName;
	}
}
