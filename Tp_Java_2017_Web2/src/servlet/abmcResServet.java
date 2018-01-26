package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import controlers.*;
import entity.*;
import util.Emailer;

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
		case "/reserva/alta":
			this.alta(request,response);
			break;
			
		case "/baja":
			this.baja(request,response);
			break;
			
		case "/cancelar/modificacion":
			try {
				this.modificacion(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
    	 CtrlABMCReserva ctrl= new CtrlABMCReserva();
			int id_te = (Integer.parseInt(request.getParameter("tipo_elemento")));
			ArrayList<Elemento> els=new ArrayList();
			els=ctrl.getElementosDeTipo(id_te);
			request.setAttribute("elementos_tipo", els);
			request.getRequestDispatcher("/WEB-INF/reserva.jsp").forward(request, response);
	  }
     catch (Exception e) {
		e.printStackTrace();
	   }
	
	}

	private void modificacion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("hola");
	}

	private void baja(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.getWriter().append("baja, requested action: ").append(request.getPathInfo()).append(" through post");
		//crear el controlador y ejecutar el delete/remove
	}

	private void alta(HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		  try {
			    CtrlABMCReserva ctrl = new CtrlABMCReserva();
			    CtrlABMCTipoElemento ctrlte = new CtrlABMCTipoElemento();
			    CtrlABMCElemento ctrle = new CtrlABMCElemento();
			    Persona p=(Persona) request.getSession().getAttribute("user");
                Reserva re = new Reserva();
            	TipoElemento t = new TipoElemento();
            	Elemento e=new Elemento();
            	
                re.setElemento(new Elemento());
                re.setPersona(new Persona());
                re.setTipoelemento(new TipoElemento());
                re.getPersona().setId_per(p.getId_per());
                int id_el=(Integer.parseInt(request.getParameter("elemento")));
    			re.setElemento(ctrle.getById(id_el));
                int id_te=(Integer.parseInt(request.getParameter("tipo_elemento")));
    			re.setTipoelemento(ctrlte.getById(id_te));
                re.setFecha(Date.valueOf(request.getParameter("fecha")));
                re.setHora(Time.valueOf(request.getParameter("hora"))); 
                re.setDetalle(request.getParameter("detalle"));
                int valida=ctrl.validaDisponibilidad(re);
    			Date fecha=Date.valueOf(ctrl.getFechaActual());
                int dias=(int) ((re.getFecha().getTime()-fecha.getTime())/86400000);
    			if (dias<t.getDias_anticipacion()){
    				
                 if (valida==0){
                ctrl.add(re);
                response.getWriter().append("Reserva creada con exito");
                Emailer.getInstance().send(p.getEmail(),"reserva",ctrl.getMailReserva(re,p));
    			}
                 else if (valida==1){
    				response.getWriter().append("elemento ocupado para esa fecha/hora");
    			}}
    			else { response.getWriter().append("Supero los dias maximo de anticipacion a la reserva"); }
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
