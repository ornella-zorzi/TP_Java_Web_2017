package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import controlers.CtrlABMCTipoElemento;
import entity.TipoElemento;    

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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("get");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getPathInfo()).append(" through post");
		switch (request.getPathInfo()) {
		case "/alta":
			this.alta(request,response);
			break;
			
		case "/baja":
			this.baja(request,response);
			break;
			
		case "/modificacion":
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
		//redirigir a página de error
	}

	private void consulta(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 /* try {
			
	
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.getWriter().append("Consulta, requested action: ").append(request.getPathInfo()).append(" through post");
	 */ 
	}

	private void modificacion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.getWriter().append("Modificación, requested action: ").append(request.getPathInfo()).append(" through post");
		//crear el controlador y ejecutar el modificar/update
	}

	private void baja(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.getWriter().append("baja, requested action: ").append(request.getPathInfo()).append(" through post");
		//crear el controlador y ejecutar el delete/remove
	}

	private void alta(HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		  try { 
			    CtrlABMCTipoElemento ctrl = new CtrlABMCTipoElemento();
                TipoElemento te = new TipoElemento();
                te.setNombre_TE(request.getParameter("nombre_te"));
                te.setCant_reserva_max(Integer.parseInt(request.getParameter("cant_reserva_max")));
                te.setTiempo_limite(Integer.parseInt(request.getParameter("tiempo_limite")));
                te.setDias_anticipacion(Integer.parseInt(request.getParameter("dias_anticipacion")));
				ctrl.add(te);
				response.getWriter().append("Alta, requested action: ").append(request.getPathInfo()).append(" through post");
		  }
	      catch (Exception e) {
			e.printStackTrace();
		   }
		
		//crear el controlador y ejecutar el new/add
	}

}
