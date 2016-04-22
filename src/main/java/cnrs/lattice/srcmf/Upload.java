package cnrs.lattice.srcmf;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "Upload", urlPatterns = {
		"/upload" }, initParams = @WebInitParam(name = "chemin", value = Params.dir) )
@MultipartConfig(location = Params.dir, fileSizeThreshold = 1048576, maxFileSize = 10485760, maxRequestSize = 52428800)
public class Upload extends HttpServlet {
	public static final String VUE = "/jsp/upload.jsp";
	public static String CHAMP_DESCRIPTION = "description";
	public static String CHAMP_FICHIER = "fichier";
	public static final String CHEMIN        = "chemin";
	public static final int TAILLE_TAMPON = 10240; // 10 ko

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Affichage de la page d'envoi de fichiers */
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
	    /*
	     * Lecture du paramètre 'chemin' passé à la servlet via la déclaration
	     * dans le web.xml
	     */
	    String chemin = this.getServletConfig().getInitParameter( CHEMIN );

	    /* Récupération du contenu du champ de description */
	    String description = request.getParameter( CHAMP_DESCRIPTION );
	    request.setAttribute( CHAMP_DESCRIPTION, description );

	    /*
	     * Les données reçues sont multipart, on doit donc utiliser la méthode
	     * getPart() pour traiter le champ d'envoi de fichiers.
	     */
	    Part part = request.getPart( CHAMP_FICHIER );
	        
	    /*
	     * Il faut déterminer s'il s'agit d'un champ classique 
	     * ou d'un champ de type fichier : on délègue cette opération 
	     * à la méthode utilitaire getNomFichier().
	     */
	    String nomFichier = getNomFichier( part );

	    /*
	     * Si la méthode a renvoyé quelque chose, il s'agit donc d'un champ
	     * de type fichier (input type="file").
	     */
	    if ( nomFichier != null && !nomFichier.isEmpty() ) {
	        String nomChamp = part.getName();
	        /*
	         * Antibug pour Internet Explorer, qui transmet pour une raison
	         * mystique le chemin du fichier local à la machine du client...
	         * 
	         * Ex : C:/dossier/sous-dossier/fichier.ext
	         * 
	         * On doit donc faire en sorte de ne sélectionner que le nom et
	         * l'extension du fichier, et de se débarrasser du superflu.
	         */
	         nomFichier = nomFichier.substring( nomFichier.lastIndexOf( '/' ) + 1 )
	                .substring( nomFichier.lastIndexOf( '\\' ) + 1 );

	        /* Écriture du fichier sur le disque */
	        ecrireFichier( part, nomFichier, chemin );

	       
	    }

	    this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

	/*
	 * Méthode utilitaire qui a pour unique but d'analyser l'en-tête
	 * "content-disposition", et de vérifier si le paramètre "filename" y est
	 * présent. Si oui, alors le champ traité est de type File et la méthode
	 * retourne son nom, sinon il s'agit d'un champ de formulaire classique et
	 * la méthode retourne null.
	 */
	private static String getNomFichier(Part part) {
		/*
		 * Boucle sur chacun des paramètres de l'en-tête "content-disposition".
		 */
		for (String contentDisposition : part.getHeader("content-disposition").split(";")) {
			/* Recherche de l'éventuelle présence du paramètre "filename". */
			if (contentDisposition.trim().startsWith("filename")) {
				/*
				 * Si "filename" est présent, alors renvoi de sa valeur,
				 * c'est-à-dire du nom de fichier sans guillemets.
				 */
				return contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		/* Et pour terminer, si rien n'a été trouvé... */
		return null;
	}

	/*
	 * Méthode utilitaire qui a pour unique but de lire l'InputStream contenu
	 * dans l'objet part, et de le convertir en une banale chaîne de caractères.
	 */
	private String getValeur(Part part) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8"));
		StringBuilder valeur = new StringBuilder();
		char[] buffer = new char[1024];
		int longueur = 0;
		while ((longueur = reader.read(buffer)) > 0) {
			valeur.append(buffer, 0, longueur);
		}
		return valeur.toString();
	}
	
	/*
	 * Méthode utilitaire qui a pour but d'écrire le fichier passé en paramètre
	 * sur le disque, dans le répertoire donné et avec le nom donné.
	 */
	private void ecrireFichier( Part part, String nomFichier, String chemin ) throws IOException {
	    /* Prépare les flux. */
	    BufferedInputStream entree = null;
	    BufferedOutputStream sortie = null;
	    try {
	        /* Ouvre les flux. */
	        entree = new BufferedInputStream( part.getInputStream(), TAILLE_TAMPON );
	        sortie = new BufferedOutputStream( new FileOutputStream( new File( chemin + nomFichier ) ),
	                TAILLE_TAMPON );

	        /*
	         * Lit le fichier reçu et écrit son contenu dans un fichier sur le
	         * disque.
	         */
	        byte[] tampon = new byte[TAILLE_TAMPON];
	        int longueur;
	        while ( ( longueur = entree.read( tampon ) ) > 0 ) {
	            sortie.write( tampon, 0, longueur );
	        }
	    } finally {
	        try {
	            sortie.close();
	        } catch ( IOException ignore ) {
	        }
	        try {
	            entree.close();
	        } catch ( IOException ignore ) {
	        }
	    }
	}
	
	public void setChampDescription(String str){
		this.CHAMP_DESCRIPTION = str;
	}
	
	public void setChampFichier(String str){
		this.CHAMP_FICHIER = str;
	}
}