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
		request.getRequestDispatcher("/WEB-INF/tipoElemento.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch (request.getPathInfo()) {
		case "/alta":
			this.alta(request,response);
			break;
			
		case "/tipoElemento/baja":
			this.baja(request,response);
			break;
			
		case "/tipoElemento/modificacion":
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
		response.getWriter().append("Modificación, requested action: ").append(request.getPathInfo()).append(" through post");
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
			 ctrl.delete(te);
		
			response.getWriter().append("baja, requested action: ").append(request.getPathInfo()).append(" through post");
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
				response.getWriter().append("Alta, requested action: ").append(request.getPathInfo()).append(" through post");
		  }
		  catch (ApplicationException ade) {
				request.setAttribute("Error", ade.getMessage());
			} catch (Exception e) {
				response.setStatus(500);
			}
		 
	}

}
