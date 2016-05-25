package cnrs.lattice.srcmf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import cnrs.lattice.tools.utils.Tools;

//@SuppressWarnings("unused")
@ManagedBean
public class Params implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String dir = "/srcmf-docs/";
	public static String userhome = System.getProperty( "catalina.base" );//System.getProperty("user.home");
	public static String catalinabase = System.getProperty( "catalina.base" );
	
	public Params(){
		
	}
	
//	public static Params create() {
//		return new Params();
//	}

	
	
	//// geters
	
	public static String getDir() throws IOException{
		File catalinaBase = new File( System.getProperty( "catalina.base" ) ).getAbsoluteFile();
		File propertyFile = new File( catalinaBase, "temp/" );

		InputStream inputStream = new FileInputStream( propertyFile );
		//dir = propertyFile.getAbsolutePath();
		final String mydir = dir;
		final String conf = Tools.readFile("/WEB-INF/conf");
		return conf;
	}
	
}
