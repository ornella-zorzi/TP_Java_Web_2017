package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlers.CtrlABMCElemento;
import util.ApplicationException;

/**
 * Servlet implementation class ListadoElServlet
 */
@WebServlet ({"/listadoEl/*", "/ListadoEl/*", "/LISTADOEL/*","/listadoel/*"})
public class ListadoElServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListadoElServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		switch (request.getPathInfo()) {
		case "/le":
			CtrlABMCElemento ctrl= new CtrlABMCElemento();
			try {
				request.setAttribute("listaElementos", ctrl.getAll());
			} catch (ApplicationException ade) {
				request.setAttribute("Error", ade.getMessage());
			} catch (Exception e) {
				response.setStatus(502);
			}
			request.getRequestDispatcher("/WEB-INF/listadoElemento.jsp").forward(request, response);
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
