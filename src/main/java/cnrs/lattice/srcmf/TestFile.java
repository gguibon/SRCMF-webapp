package cnrs.lattice.srcmf;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

//@SuppressWarnings("unused")
@ManagedBean
public class TestFile implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name = "";
	private String absolutePath = "";
	private String content = "";
	
	public TestFile(){
		name = "";
		absolutePath = "";
		content = "";
	}
	
	public static TestFile create() {
		return new TestFile();
	}
	
	//// setters
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setAbsolutePath(String abspolutePath){
		this.absolutePath = abspolutePath;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	//// geters
	
	public String getName(){
		return this.name;
	}
	
	public String getAbsolutePath(){
		return this.absolutePath;
	}
	
	public String getContent(){
		return this.content;
	}
}
