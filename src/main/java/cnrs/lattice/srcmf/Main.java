package cnrs.lattice.srcmf;

import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;

//import org.apache.catalina.Context;
//import org.apache.catalina.core.AprLifecycleListener;
//import org.apache.catalina.core.StandardServer;
//import org.apache.catalina.startup.Tomcat;

public class Main {
    
    public static final Optional<String> PORT = Optional.ofNullable(System.getenv("PORT"));
    public static final Optional<String> HOSTNAME = Optional.ofNullable(System.getenv("HOSTNAME"));
    
//    public static void main(String[] args) throws Exception {
//        String contextPath = "/" ;
//        String appBase = ".";
//        Tomcat tomcat = new Tomcat();   
//        tomcat.setPort(Integer.valueOf(PORT.orElse("8080") ));
//        tomcat.setHostname(HOSTNAME.orElse("localhost"));
//        tomcat.getHost().setAppBase(appBase);
//        tomcat.addWebapp(contextPath, appBase);
//        tomcat.start();
//        openWebpage(new URL("http://localhost:8080"));
//        tomcat.getServer().await();
//    }

//    public static void main(String[] args) throws Exception {
//
//        String webappDirLocation = "src/main/webapp/";
//        Tomcat tomcat = new Tomcat();
//        tomcat.setPort(8080);
//
//        tomcat.setBaseDir(".");
//        tomcat.getHost().setAppBase(".");
//        tomcat.setSilent(false);
//
//        // Add AprLifecycleListener
//        StandardServer server = (StandardServer) tomcat.getServer();
//        AprLifecycleListener listener = new AprLifecycleListener();
//        server.addLifecycleListener(listener);
//
//        Context ctx = tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
//        Tomcat.addServlet(ctx, "jsf_servlet", "javax.faces.webapp.FacesServlet");
//        ctx.addServletMapping("*.xhtml", "jsf_servlet");
//        tomcat.start();
////        openWebpage(new URL("http://localhost:8080"));
//        tomcat.getServer().await();
//        
//      }
    
    public static void openWebpage(URI uri) {
    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
        try { 
//        	FileUtils.cleanDirectory(new File(Params.userhome + Params.dir + "uploaded/"));
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