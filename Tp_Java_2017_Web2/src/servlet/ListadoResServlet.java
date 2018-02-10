package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlers.CtrlABMCPersona;
import controlers.CtrlABMCReserva;
import entity.Persona;
import entity.Reserva;

/**
 * Servlet implementation class ListadoResServlet
 */
@WebServlet({ "/listadoRe/*", "/ListadoRe/*", "/LISTADORE/*","/listadore/*" })
public class ListadoResServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListadoResServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch (request.getPathInfo()) {
		case "/listado/eliminar":
			CtrlABMCReserva ctrl= new CtrlABMCReserva();
			Reserva r=new Reserva();
			r.setId_res(Integer.parseInt(request.getParameter("cancelar")));
			Reserva re=null;
			try {
				re = ctrl.getById(r);
				ctrl.delete(re);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			break;
		case "/listado":
			CtrlABMCReserva ctrl2= new CtrlABMCReserva();
			Persona p=(Persona) request.getSession().getAttribute("user");
			try {
				request.setAttribute("listaReservas", ctrl2.getReservasPendientes(p));
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("/WEB-INF/listadoReserva.jsp").forward(request, response);
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
		

		
}
}
