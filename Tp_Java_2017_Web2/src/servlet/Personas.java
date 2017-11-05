package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang. * ; 

import controlers.*;
import entity.*;
import util.*;
/**
 * Servlet implementation class Persona
 */
@WebServlet({ "/Personas", "/personas", "/Persona", "/persona" })
public class Personas extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Personas() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CtrlABMCPersona ctrl = (CtrlABMCPersona)request.getSession().getAttribute("ctrl");
		//String rta ="" ; 
		 if (request.getParameter("btnAgregar")!=null){
			  try {
				  Persona per=new Persona();
					per.setDni(request.getParameter("dni"));
					per.setNombre(request.getParameter("nombre"));
					per.setApellido(request.getParameter("apellido"));
					per.setEmail(request.getParameter("mail"));
					
					
					//CtrlABMCPersona ctrl= new CtrlABMCPersona();
					ctrl.add(per);
					request.getSession().setAttribute("PER", per);
					request.getRequestDispatcher("WEB-INF/welcome.jsp").forward(request, response);
					//tratar de poner cartel
					
			  }
		      catch (Exception e) {
				e.printStackTrace();
			   }
		 } else if(request.getParameter("btnBorrar")!=null){ 
			 
		 } else if(request.getParameter("btnBuscar")!=null)
		 {
			 try {
					String dni=request.getParameter("dni");
					Persona per=new Persona();
					per.setDni(dni);
				//	CtrlABMCPersona ctrl= new CtrlABMCPersona();
					Persona pers=ctrl.getByDni(per);
					request.getSession().setAttribute("user", pers);
					request.getRequestDispatcher("WEB-INF/persona.jsp").forward(request, response);
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
		 }
	}
	
}

		//agregar
		/*try {
			/*String dni=request.getParameter("dni");
			String nombre=request.getParameter("nombre");
			String apellido=request.getParameter("apellido");
			String email=request.getParameter("email");*/
	        //Boolean habilitado=request.getParameter("habilitado");
			//Categoria
			
		/*	Persona per=new Persona();
			per.setDni(request.getParameter("dni"));
			per.setNombre(request.getParameter("nombre"));
			per.setApellido(request.getParameter("apellido"));
			per.setEmail(request.getParameter("mail"));
			
			CtrlABMCPersona ctrl= new CtrlABMCPersona();
			ctrl.add(per);
			request.getSession().setAttribute("PER", per);
			request.getRequestDispatcher("WEB-INF/welcome.jsp").forward(request, response);
			//tratar de poner cartel
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//doGet(request, response);
	}
		
		/*buscar
		try {
			String dni=request.getParameter("dni");
			Persona per=new Persona();
			per.setDni(dni);
			CtrlABMCPersona ctrl= new CtrlABMCPersona();
			Persona pers=ctrl.getByDni(per);
			request.getSession().setAttribute("user", pers);
			request.getRequestDispatcher("WEB-INF/persona.jsp").forward(request, response);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//doGet(request, response);
	}*/
	
	

//}