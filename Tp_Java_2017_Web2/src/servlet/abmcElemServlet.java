package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import controlers.*;
import entity.*;
import util.ApplicationException;

/**
 * Servlet implementation class abmcPerServlet
 */
@WebServlet({ "/elemento/*", "/Elemento/*", "/elementos/*" , "/Elementos/" })

public class abmcElemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public abmcElemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch (request.getPathInfo()) {
		case "/el":
			request.getRequestDispatcher("/WEB-INF/elemento.jsp").forward(request, response);
			break;
	case "/elemento/persona":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/persona/per");
		break;
	case "/persona":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/persona/per");
		break;	
		
	case "/elemento/listadoEl":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoEl/le");
		break;
	case "/listadoEl":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoEl/le");
		break;
		
	case "/elemento/Start":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/Start");
		break;
	case "/Start":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/Start");
		break;
		
	case "/elemento/listadoPer":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoPer/lp");
		break;
	case "/listadoPer":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoPer/lp");
		break;
	case "/elemento/tipoElemento":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/tipoElemento/te");
		break;
	case "/tipoElemento":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/tipoElemento/te");
		break;
	case "/elemento/listadoTe":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoTe/lte");
		break;
	case "/listadoTe":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoTe/lte");
		break;
	case "/reserva":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/reserva/re");
		break;
	case "/elemento/reserva":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/reserva/re");
		break;
	case "/listadoRe/listado":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoRe/listado");
		break;
	case "/elemento/listadoRe/listado":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoRe/listado");
		break;
	case "/elemento":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/elemento/el");
		break;
	case "/elemento/elemento":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/elemento/el");
		break;
			}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getPathInfo()).append(" through post");
		switch (request.getPathInfo()) {
		case "elemento/alta":
			this.alta(request,response);
			break;
			
		case "/elemento/elemento/baja":
			this.baja(request,response);
			break;
			
		case "/elemento/elemento/modificacion":
			this.modificacion(request,response);
			break;
			
		case "/elemento/consulta":
			this.consulta(request,response);
			break;

		default:
			this.error(request,response);
			break;
		}
	}

	private void error(HttpServletRequest request, HttpServletResponse response) {
		response.setStatus(404);
	}

	private void consulta(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
		
			CtrlABMCElemento ctrl= new CtrlABMCElemento();
			String nombre_el = request.getParameter("nombre_el");
			Elemento el = new Elemento();
			el.setNombre_El(nombre_el);
			el=ctrl.getByNombre(el);
			request.setAttribute("encontrado", el);
			request.getRequestDispatcher("/WEB-INF/elemento.jsp").forward(request, response);
			
		
		} catch (ApplicationException ade) {
			request.setAttribute("Error", ade.getMessage());
		} catch (Exception e) {
			response.setStatus(500);
			
		}
	}

	private void modificacion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
		
		Elemento el = new Elemento();
		CtrlABMCElemento ctrl = new CtrlABMCElemento();
		CtrlABMCTipoElemento ctrlte = new CtrlABMCTipoElemento();
		TipoElemento te=new TipoElemento();
		el.setId_El(Integer.parseInt(request.getParameter("id_el")));
		el.setNombre_El(request.getParameter("nombre_el"));
		System.out.println(el.getNombre_El());
		int id_TE =Integer.parseInt(request.getParameter("tipoElemento"));
		el.setTipoElemento(ctrlte.getById(id_TE));
		ctrl.update(el);
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/notificacion");
		  }catch (ApplicationException ade) {
				request.setAttribute("Error", ade.getMessage());
			} catch (Exception e) {
				response.setStatus(500);
			}
	
	}

	private void baja(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try{
			Elemento el = new Elemento();
			el.setId_El(Integer.parseInt(request.getParameter("id_el")));
			CtrlABMCElemento ctrl = new CtrlABMCElemento();
			ctrl.delete(el);
		response.getWriter().append("baja, requested action: ").append(request.getPathInfo()).append(" through post");
		
		}catch (ApplicationException ade) {
			request.setAttribute("Error", ade.getMessage());
		} catch (Exception e) {
			response.setStatus(500);
		}

		}


	private void alta(HttpServletRequest request, HttpServletResponse response) throws IOException {
		  try {
			  
			    CtrlABMCElemento ctrl= new CtrlABMCElemento();
			    CtrlABMCTipoElemento ctrlte= new CtrlABMCTipoElemento();
			    Elemento el = new Elemento();
			    el.setNombre_El(request.getParameter("nombre_el"));
				int id_te=Integer.parseInt(request.getParameter("tipoElemento"));
				el.setTipoElemento(ctrlte.getById(id_te));
				ctrl.add(el);
				response.getWriter().append("Elemento creado con exito");
		  }catch (ApplicationException ade) {
				request.setAttribute("Error", ade.getMessage());
			} catch (Exception e) {
				response.setStatus(500);
			}
	
		

	}
}
