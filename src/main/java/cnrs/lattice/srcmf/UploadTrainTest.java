package cnrs.lattice.srcmf;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import cnrs.lattice.pipelines.Pipeline;

@WebServlet(name = "UploadTrainTest", urlPatterns = {
		"/ooo" }, initParams = @WebInitParam(name = "chemin", value = Params.dir) )
@MultipartConfig(location = Params.dir, fileSizeThreshold = 1048576, maxFileSize = 10485760, maxRequestSize = 52428800)
public class UploadTrainTest extends HttpServlet {
	public static final String VUE = "/jsp/button-test.jsp";
	public static final String CHAMP_DESCRIPTION_TRAIN = "descriptionTrain";
	public static final String CHAMP_FICHIER_TRAIN = "fichierTrain";
	public static final String CHAMP_DESCRIPTION_TEST = "descriptionTest";
	public static final String CHAMP_FICHIER_TEST = "fichierTest";
	public static final String CHEMIN = "chemin";
	public static final int TAILLE_TAMPON = 10240; // 10 ko

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Affichage de la page d'envoi de fichiers */
		// String nextJSP = "/jsp/loading2.jsp";
		// RequestDispatcher dispatcher =
		// getServletContext().getRequestDispatcher(nextJSP);
		// // req.setAttribute("employeeList", employeeList);
		// dispatcher.forward(request, response);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(VUE);
		// req.setAttribute("employeeList", employeeList);
		dispatcher.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		if (request.getParameter("button1") != null) {
//			String chemin = this.getServletConfig().getInitParameter(CHEMIN);
//
//			try {
//				request.getRequestDispatcher("/jsp/loading.jsp").forward(request, response);
//				apiLaunch(chemin, request, response);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//		}

		// else if (request.getParameter("button2") != null) {
		// myClass.method2();
		// } else if (request.getParameter("button3") != null) {
		// myClass.method3();
		// } else {
		// // ???
		// }

		// Upload upload = new Upload();
		// upload.setChampDescription(CHAMP_DESCRIPTION_TRAIN);
		// upload.setChampFichier(CHAMP_FICHIER_TRAIN);
		// upload.doPost(request, response);
		//
		// upload.setChampDescription(CHAMP_DESCRIPTION_TEST);
		// upload.setChampFichier(CHAMP_FICHIER_TEST);
		// upload.doPost(request, response);

		// doPostTrain(request, response);
		// doPostTest(request, response);

		// OOO ooo = new OOO();
		// ooo.setTrain(trainPath);
		// ooo.setTest(testPath);
		//// System.out.println(trainPath + "||" + testPath);

	}

	private void apiLaunch(String chemin, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String[] arguments = { "-train", chemin + "lapidaire.conll", "-test", chemin + "beroul.conll", "-output",
				chemin + "res.conll", "-template", chemin + "template_tlt" };
		cnrs.lattice.engines.main.Main.main(arguments);
		
//		Pipeline.create().stopwatchStart()
//			.setConllContent(chemin+"lapidaire.conll")
//			;
		req.getRequestDispatcher("/jsp/button-test.jsp").forward(req, resp);
	}

	private void loading(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nextJSP = "/jsp/loading.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(req, resp);
	}
}