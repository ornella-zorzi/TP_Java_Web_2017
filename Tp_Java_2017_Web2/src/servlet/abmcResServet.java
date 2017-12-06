package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import controlers.*;
import entity.*;

@WebServlet({ "/reserva/*", "/Reserva/*",  "/RESERVA/*" , "/RESERVAS/*"  })
public class abmcResServet  extends HttpServlet  {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public abmcResServet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/reserva.jsp").forward(request, response);
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
     try{
    	 
	  }
     catch (Exception e) {
		e.printStackTrace();
	   }
	
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
			    CtrlABMCReserva ctrl = new CtrlABMCReserva();
			    Persona p=(Persona) request.getSession().getAttribute("user");
                Reserva re = new Reserva();
                re.setElemento(new Elemento());
                re.setPersona(new Persona());
                re.setTipoelemento(new TipoElemento());
                re.getPersona().setId_per(p.getId_per());
                re.getElemento().setId_El(Integer.parseInt(request.getParameter("elemento")));
                re.getTipoelemento().setId_TE(Integer.parseInt(request.getParameter("tipo_elemento")));
                re.setFecha(Date.valueOf(request.getParameter("fecha")));
                re.setHora(Time.valueOf(request.getParameter("hora"))); 
                re.setDetalle(request.getParameter("detalle"));
                re.setEstado(request.getParameter("estado"));
                int valida=ctrl.validaDisponibilidad(re);
    			System.out.println(valida);
    			if (valida==0){
                ctrl.add(re);
                response.getWriter().append("Reserva creada con exito");
    			}
    			if (valida==1){
    				response.getWriter().append("elemento ocupado para esa fecha/hora");
    			}
				/*
				 * 1- guardar la categoria id en una variabe
				 * 2- buscar la categoria de ese id mediante un controlador getById
				 * 3- guardar el objeto categoria recuperado en per con setCategoria 
				 */
				//select
				//checkbox
				//user,pass
				//categoria
				
				//tratar de poner cartel
				
		  }
	      catch (Exception e) {
			e.printStackTrace();
		   }
		
		//crear el controlador y ejecutar el new/add
	}


}
