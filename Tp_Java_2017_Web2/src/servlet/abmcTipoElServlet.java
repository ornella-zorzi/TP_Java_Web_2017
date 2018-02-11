package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import controlers.CtrlABMCPersona;
import controlers.CtrlABMCTipoElemento;
import entity.Persona;
import entity.TipoElemento;
import util.ApplicationException;    

/**
 * Servlet implementation class abmcPerServlet
 */
@WebServlet({ "/tipoElemento/*", "/tipoElementos/*", "/TipoElementos/*", "/TipoElemento/*" })
public class abmcTipoElServlet extends HttpServlet  {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public abmcTipoElServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch (request.getPathInfo()) {
		case "/te":
			request.getRequestDispatcher("/WEB-INF/tipoElemento.jsp").forward(request, response);
			break;
	case "/tipoElemento/persona":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/persona/per");
		break;
	case "/persona":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/persona/per");
		break;	
		
	case "/tipoElemento/listadoEl":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoEl/le");
		break;
	case "/listadoEl":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoEl/le");
		break;
		
	case "/tipoElemento/Start":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/Start");
		break;
	case "/Start":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/Start");
		break;
		
	case "/tipoElemento/listadoPer":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoPer/lp");
		break;
	case "/listadoPer":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoPer/lp");
		break;
	case "/tipoElemento/tipoElemento":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/tipoElemento/te");
		break;
	case "/tipoElemento":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/tipoElemento/te");
		break;
	case "/tipoElemento/listadoTe":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoTe/lte");
		break;
	case "/listadoTe":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoTe/lte");
		break;
	case "/reserva":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/reserva/re");
		break;
	case "/tipoElemento/reserva":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/reserva/re");
		break;
	case "/listadoRe/listado":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoRe/listado");
		break;
	case "/tipoElemento/listadoRe/listado":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoRe/listado");
		break;
	case "/elemento":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/elemento/el");
		break;
	case "/tipoElemento/elemento":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/elemento/el");
		break;
			}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch (request.getPathInfo()) {
		case "/tipoElemento/alta":
			this.alta(request,response);
			break;
			
		case "/tipoElemento/tipoElemento/baja":
			this.baja(request,response);
			break;
			
		case "/tipoElemento/tipoElemento/modificacion":
			this.modificacion(request,response);
			break;
			
		case "/tipoElemento/consulta":
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
		  try{
			  CtrlABMCTipoElemento ctrl = new CtrlABMCTipoElemento();
			   String nombre_te =request.getParameter("nombre_te");
			   TipoElemento te = new TipoElemento();
			   te.setNombre_TE(nombre_te);
			   te= ctrl.getByNombre(te);
			   request.setAttribute("encontrado", te);
	

			request.getRequestDispatcher("/WEB-INF/tipoElemento.jsp").forward(request, response);

		
	     }catch (ApplicationException ade) {
				request.setAttribute("Error", ade.getMessage());
			} catch (Exception e) {
				response.setStatus(500);
			}
		
		}
		

		

	private void modificacion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			TipoElemento te = new TipoElemento();
			 CtrlABMCTipoElemento ctrl = new CtrlABMCTipoElemento();
			 te.setId_TE(Integer.parseInt (request.getParameter("id")));
			 te.setNombre_TE(request.getParameter("nombre_te"));
			 te.setCant_reserva_max(Integer.parseInt(request.getParameter("cant_reserva_max")));
			 te.setTiempo_limite(Integer.parseInt(request.getParameter("tiempo_limite")));
			 te.setDias_anticipacion(Integer.parseInt(request.getParameter("dias_anticipacion")));
			 if (request.getParameter("encargado")!=null){
					te.setEncargado(true);
				}
			 
			ctrl.update(te);			
			response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/notificacion");
		  }
		catch (ApplicationException ade) {
			request.setAttribute("Error", ade.getMessage());
		} catch (Exception e) {
			response.setStatus(500);
		}

	}

	private void baja(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try{
			TipoElemento te = new TipoElemento();
			te.setId_TE(Integer.parseInt(request.getParameter("id")));
			 CtrlABMCTipoElemento ctrl = new CtrlABMCTipoElemento();
			 int i=ctrl.validabaja(te.getId_TE());
			 if (i==1)
			 {response.getWriter().append("no se pueden eliminar tipos de elementos que esten reservados o que pertenezcan a algun elemento");}
			 else {ctrl.delete(te);
			 response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/notificacion");}
			
		}
		catch (ApplicationException ade) {
			request.setAttribute("Error", ade.getMessage());
		} catch (Exception e) {
			response.setStatus(500);
		}
	}

	

	private void alta(HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		  try { 
			    CtrlABMCTipoElemento ctrl = new CtrlABMCTipoElemento();
                TipoElemento te = new TipoElemento();
                te.setNombre_TE(request.getParameter("nombre_te"));
                te.setCant_reserva_max(Integer.parseInt(request.getParameter("cant_reserva_max")));
                te.setTiempo_limite(Integer.parseInt(request.getParameter("tiempo_limite")));
                te.setDias_anticipacion(Integer.parseInt(request.getParameter("dias_anticipacion")));
                if (request.getParameter("encargado")!=null){
					te.setEncargado(true);
				}
				ctrl.add(te);
				response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/notificacion");
		  }
		  catch (ApplicationException ade) {
				request.setAttribute("Error", ade.getMessage());
			} catch (Exception e) {
				response.setStatus(500);
			}
		 
	}

}
