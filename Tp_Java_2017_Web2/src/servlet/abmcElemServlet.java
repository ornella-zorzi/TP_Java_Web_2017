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
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/elemento.jsp").forward(request, response);
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
			
		case "/elemento/baja":
			this.baja(request,response);
			break;
			
		case "/elemento/modificacion":
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
		try {
		
			CtrlABMCElemento ctrl= new CtrlABMCElemento();
			String nombre_el = request.getParameter("nombre_el");
			Elemento el = new Elemento();
			el.setNombre_El(nombre_el);
			el=ctrl.getByNombre(el);
			request.setAttribute("encontrado", el);
			request.getRequestDispatcher("/WEB-INF/elemento.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
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
		response.getWriter().append("Modificación, requested action: ").append(request.getPathInfo()).append(" through post");

		  }
	      catch (Exception e) {
			e.printStackTrace();
		   }
	
	}

	private void baja(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try{
			Elemento el = new Elemento();
			el.setId_El(Integer.parseInt(request.getParameter("id_el")));
			CtrlABMCElemento ctrl = new CtrlABMCElemento();
			ctrl.delete(el);
		response.getWriter().append("baja, requested action: ").append(request.getPathInfo()).append(" through post");
		
		}
	    catch (Exception e) {
			e.printStackTrace();
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
		  }
	      catch (Exception e) {
			e.printStackTrace();
		   }
		

	}
}
