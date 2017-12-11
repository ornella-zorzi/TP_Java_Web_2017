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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch (request.getPathInfo()) {
		case "/editar":
			CtrlABMCReserva ctrl= new CtrlABMCReserva();
			Reserva r=new Reserva();
			r.setId_res(Integer.parseInt(request.getParameter("id_res")));
			System.out.println(r.getId_res());
			Reserva re=null;
			try {
				re = ctrl.getById(r);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			request.setAttribute("reserva", re);
			request.getRequestDispatcher("/WEB-INF/reserva.jsp").forward(request, response);
			break;
		case "/listado":
			CtrlABMCReserva ctrl2= new CtrlABMCReserva();
			try {
				request.setAttribute("listaReservas", ctrl2.getAll());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("/WEB-INF/listadoReserva.jsp").forward(request, response);
			break;
			
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

}
}
