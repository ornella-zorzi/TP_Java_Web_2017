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
	 request.getRequestDispatcher("/WEB-INF/persona.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch (request.getPathInfo()) {
		case "/alta":
			this.alta(request,response);
			break;
			
		case "/persona/baja":
			this.baja(request,response);
			break;
		
		case "/persona/modificacion":
				this.modificacion(request,response);
			break;
			
		case "/consulta":
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

		response.getWriter().append("Modificación, requested action: ").append(request.getPathInfo()).append(" through post");
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
		ctrl.delete(per);
		
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
				
				ctrl.add(per);
			
				response.getWriter().append("Alta, requested action: ").append(request.getPathInfo()).append(" through post");
		  }catch (ApplicationException ade) {
				request.setAttribute("Error", ade.getMessage());
			} catch (Exception e) {
				response.setStatus(500);
			}
	

	}

	
}
