package cnrs.lattice.srcmf;

import java.util.Arrays;
import java.util.Optional;
import java.awt.Desktop;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Context;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.scan.StandardJarScanner;

//import com.sun.faces.action.RequestMapping;


//@RequestMapping("/index.xhtml")
public class Main{

	public static final Optional<String> PORT = Optional.ofNullable(System.getenv("PORT"));
	public static final Optional<String> HOSTNAME = Optional.ofNullable(System.getenv("HOSTNAME"));

	public Main(){
		
	}

	 public static void main(String[] args) throws Exception {
	
	 Tools tool = new Tools();
	 String webappDirLocation = "src/main/webapp/";
			 //Main.class.getResource("/META-INF/resources/").toExternalForm();
//	 tool.accessRessourceFile("/META-INF/resources/");//"src/main/webapp/";
	 Tomcat tomcat = new Tomcat();
	 tomcat.setPort(8080);
//	 new Main().getClass().getResourceAsStream("");
//	 String basedir = Main.class.getResource("/META-INF/resources/").toExternalForm();
	 tomcat.setBaseDir(".");
	 tomcat.getHost().setAppBase(".");
	 tomcat.setSilent(false);
	
	 // Add AprLifecycleListener
	 StandardServer server = (StandardServer) tomcat.getServer();
	 AprLifecycleListener listener = new AprLifecycleListener();
	 server.addLifecycleListener(listener);
	
	 Context ctx = tomcat.addWebapp("/", new
	 File(webappDirLocation).getAbsolutePath());
	 Tomcat.addServlet(ctx, "jsf_servlet", "javax.faces.webapp.FacesServlet");
	 ctx.addServletMapping("*.xhtml", "jsf_servlet");
	 ((StandardJarScanner) ctx.getJarScanner()).setScanAllDirectories(true);
	 tomcat.start();
	// openWebpage(new URL("http://localhost:8080"));
	 tomcat.getServer().await();
	
	 }

	public static void openWebpage(URI uri) {
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				desktop.browse(uri);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void openWebpage(URL url) {
		try {
			openWebpage(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
}