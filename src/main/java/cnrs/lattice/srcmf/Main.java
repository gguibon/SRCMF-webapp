package cnrs.lattice.srcmf;

import java.util.Optional;
import java.awt.Desktop;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Logger;

import org.apache.catalina.startup.Tomcat;

public class Main {
    
    public static final Optional<String> PORT = Optional.ofNullable(System.getenv("PORT"));
    public static final Optional<String> HOSTNAME = Optional.ofNullable(System.getenv("HOSTNAME"));
    
    public static void main(String[] args) throws Exception {
        String contextPath = "/" ;
        String appBase = ".";
        Tomcat tomcat = new Tomcat();   
        tomcat.setPort(Integer.valueOf(PORT.orElse("8080") ));
        tomcat.setHostname(HOSTNAME.orElse("localhost"));
        tomcat.getHost().setAppBase(appBase);
        tomcat.addWebapp(contextPath, appBase);
        tomcat.start();
        openWebpage(new URL("http://localhost:8080"));
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