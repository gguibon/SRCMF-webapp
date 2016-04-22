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

@WebServlet(name = "Exit", urlPatterns = {
		"/exit" }, initParams = @WebInitParam(name = "chemin", value = Params.dir) )
@MultipartConfig(location = Params.dir, fileSizeThreshold = 1048576, maxFileSize = 10485760, maxRequestSize = 52428800)
public class Exit extends HttpServlet {
	public static final String VUE = "/jsp/exit.jsp";
	public static final String CHEMIN = "chemin";
	public static final int TAILLE_TAMPON = 10240; // 10 ko

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.exit(0);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(VUE);
		// req.setAttribute("employeeList", employeeList);
		dispatcher.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}