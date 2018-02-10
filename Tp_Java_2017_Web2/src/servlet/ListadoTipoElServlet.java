package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlers.CtrlABMCElemento;
import controlers.CtrlABMCTipoElemento;
import util.ApplicationException;

/**
 * Servlet implementation class ListadoTipoElServlet
 */
@WebServlet({ "/listadoTe/*", "/ListadoTe/*", "/LISTADOTE/*","/listadote/*" })
public class ListadoTipoElServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListadoTipoElServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch (request.getPathInfo()) {
		case "/lte":
			CtrlABMCTipoElemento ctrl = new CtrlABMCTipoElemento();	
			try {
				request.setAttribute("listaTipoElementos", ctrl.getAll());
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("/WEB-INF/listadoTipoElemento.jsp").forward(request, response);
			break;
	case "/persona":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/persona/per");
		break;		
	case "/listadoEl":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoEl/le");
		break;
	case "/Start":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/Start");
		break;	
	case "/listadoPer":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoPer/lp");
		break;
	case "/tipoElemento":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/tipoElemento/te");
		break;
	case "/listadoTe":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoTe/lte");
		break;
	case "/reserva":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/reserva/re");
		break;
	case "/listadoRe/listado":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoRe/listado");
		break;
	case "/elemento":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/elemento/el");
		break;
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
