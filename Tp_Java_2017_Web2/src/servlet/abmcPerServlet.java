package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import controlers.CtrlABMCPersona;
import entity.Persona;
import util.ApplicationException;

/**
 * Servlet implementation class abmcPerServlet
 */
@WebServlet({ "/persona/*", "/Persona/*", "/PERSONA/*" })
public class abmcPerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public abmcPerServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch (request.getPathInfo()) {
		case "/per":
			request.getRequestDispatcher("/WEB-INF/persona.jsp").forward(request, response);
			break;
	case "/persona/persona":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/persona/per");
		break;
	case "/persona":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/persona/per");
		break;	
		
	case "/persona/listadoEl":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoEl/le");
		break;
	case "/listadoEl":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoEl/le");
		break;
		
	case "/persona/Start":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/Start");
		break;
	case "/Start":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/Start");
		break;
		
	case "/persona/listadoPer":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoPer/lp");
		break;
	case "/listadoPer":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoPer/lp");
		break;
	case "/persona/tipoElemento":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/tipoElemento/te");
		break;
	case "/tipoElemento":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/tipoElemento/te");
		break;
	case "/persona/listadoTe":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoTe/lte");
		break;
	case "/listadoTe":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoTe/lte");
		break;
	case "/reserva":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/reserva/re");
		break;
	case "/persona/reserva":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/reserva/re");
		break;
	case "/listadoRe/listado":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoRe/listado");
		break;
	case "/persona/listadoRe/listado":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoRe/listado");
		break;
	case "/elemento":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/elemento/el");
		break;
	case "/persona/elemento":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/elemento/el");
		break;
			}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch (request.getPathInfo()) {
		case "/persona/alta":
			this.alta(request,response);
			break;
			
		case "/persona/persona/baja":
			this.baja(request,response);
			break;
		
		case "/persona/persona/modificacion":
				this.modificacion(request,response);
			break;
			
		case "/persona/consulta":
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
			CtrlABMCPersona ctrl= new CtrlABMCPersona();
			String dni=request.getParameter("dni");
			Persona per=new Persona();
			per.setDni(dni);
			per=ctrl.getByDni(per);
			request.setAttribute("encontrada", per);

		request.getRequestDispatcher("/WEB-INF/persona.jsp").forward(request, response);
	
	
     }catch (ApplicationException ade) {
			request.setAttribute("Error", ade.getMessage());
		} catch (Exception e) {
			response.setStatus(500);
		}

	
	}

	private void modificacion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {	
		Persona per=new Persona();
		CtrlABMCPersona ctrl= new CtrlABMCPersona();
		per.setDni(request.getParameter("dni"));
		per.setNombre(request.getParameter("nombre"));
		per.setApellido(request.getParameter("apellido"));
		per.setEmail(request.getParameter("email"));
		int id_cat=Integer.parseInt(request.getParameter("categoria"));
		per.setCategoria(ctrl.getById(id_cat));
		if (request.getParameter("habilitado")!=null){
			per.setHabilitado(true);
		}
		per.setUsuario(request.getParameter("usuario"));
		per.setContraseña(request.getParameter("contraseña"));
		per.setId_per(Integer.parseInt(request.getParameter("id")));
		ctrl.update(per);

		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/notificacion");
		  }catch (ApplicationException ade) {
				request.setAttribute("Error", ade.getMessage());
			} catch (Exception e) {
				response.setStatus(500);
			}
	
	}

	private void baja(HttpServletRequest request, HttpServletResponse response) throws IOException {
	try{
		Persona per= new Persona();
		per.setId_per(Integer.parseInt(request.getParameter("id")));
		System.out.println(per.getId_per());
		CtrlABMCPersona ctrl= new CtrlABMCPersona();
		int i=ctrl.validabaja(per.getId_per());
		 if (i==1)
		 {response.getWriter().append("no se pueden eliminar personas que tengan reservas");}
		 else {ctrl.delete(per);
		 response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/notificacion");}
		
		ctrl.delete(per);
		//response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/notificacion");
		response.getWriter().append("baja, requested action: ").append(request.getPathInfo()).append(" through post");
	
	}catch (ApplicationException ade) {
		request.setAttribute("Error", ade.getMessage());
	} catch (Exception e) {
		response.setStatus(502);
	}

	}

	private void alta(HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		  try {
			    CtrlABMCPersona ctrl= new CtrlABMCPersona();
			    Persona per=new Persona();
				per.setDni(request.getParameter("dni"));
				per.setNombre(request.getParameter("nombre"));
				per.setApellido(request.getParameter("apellido"));
				per.setEmail(request.getParameter("email"));
				int id_cat=Integer.parseInt(request.getParameter("categoria"));
				per.setCategoria(ctrl.getById(id_cat));
				if (request.getParameter("habilitado")!=null){
					per.setHabilitado(true);
				}
				per.setUsuario(request.getParameter("usuario"));
				per.setContraseña(request.getParameter("contraseña"));
				int i=ctrl.validaDni(per.getDni());
				 int a=ctrl.validaUsuario(per.getUsuario());
				if (i==1)
				 {response.getWriter().append("No se puede registrar persona. Dni ya existe en el sistema");}
				 if (a==1)
				 {response.getWriter().append("No se puede registrar persona. Usuario ya existe en el sistema");
				 }
				if(a!=1 & i!=1) {ctrl.add(per);
				 response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/notificacion");}
			
		  }catch (ApplicationException ade) {
				request.setAttribute("Error", ade.getMessage());
			} catch (Exception e) {
				response.setStatus(500);
			}
	

	}

	
}
