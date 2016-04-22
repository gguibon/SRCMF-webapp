package cnrs.lattice.srcmf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;

//@SuppressWarnings("unused")
@ManagedBean
public class Params implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String dir = "/temp/";
	
//	public Params(){
//		dir = "";
//	}
	
//	public static Params create() {
//		return new Params();
//	}

	
	
	//// geters
	
	public static String getDir() throws FileNotFoundException{
		File catalinaBase = new File( System.getProperty( "catalina.base" ) ).getAbsoluteFile();
		File propertyFile = new File( catalinaBase, "temp/" );

		InputStream inputStream = new FileInputStream( propertyFile );
		//dir = propertyFile.getAbsolutePath();
		final String mydir = dir;
		return dir;
	}
	
}
