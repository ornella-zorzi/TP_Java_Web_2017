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
import util.ApplicationException;
import util.Emailer;

@WebServlet({ "/reserva/*", "/Reserva/*",  "/RESERVA/*" , "/RESERVAS/*"  })
public class abmcResServet  extends HttpServlet  {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public abmcResServet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch (request.getPathInfo()) {
		case "/re":
			request.getRequestDispatcher("/WEB-INF/reserva.jsp").forward(request, response);
			break;
	case "/reserva/persona":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/persona/per");
		break;
	case "/persona":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/persona/per");
		break;	
		
	case "/reserva/listadoEl":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoEl/le");
		break;
	case "/listadoEl":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoEl/le");
		break;
		
	case "/reserva/Start":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/Start");
		break;
	case "/Start":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/Start");
		break;
		
	case "/reserva/listadoPer":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoPer/lp");
		break;
	case "/listadoPer":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoPer/lp");
		break;
	case "/reserva/tipoElemento":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/tipoElemento/te");
		break;
	case "/tipoElemento":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/tipoElemento/te");
		break;
	case "/reserva/listadoTe":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoTe/lte");
		break;
	case "/listadoTe":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoTe/lte");
		break;
	case "/reserva":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/reserva/re");
		break;
	case "/reserva/reserva":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/reserva/re");
		break;
	case "/listadoRe/listado":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoRe/listado");
		break;
	case "/reserva/listadoRe/listado":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/listadoRe/listado");
		break;
	case "/elemento":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/elemento/el");
		break;
	case "/reserva/elemento":
		response.sendRedirect("http://localhost:8080/Tp_Java_2017_Web2/elemento/el");
		break;
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch (request.getPathInfo()) {
		case "/reserva/reserva/alta":
			this.alta(request,response);
			break;
			
		case "/reserva/consulta":
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
    	 CtrlABMCReserva ctrl= new CtrlABMCReserva();
			int id_te = (Integer.parseInt(request.getParameter("tipo_elemento")));
			ArrayList<Elemento> els=new ArrayList();
			els=ctrl.getElementosDeTipo(id_te);
			request.setAttribute("elementos_tipo", els);
			request.getRequestDispatcher("/WEB-INF/reserva.jsp").forward(request, response);
	  }catch (ApplicationException ade) {
			request.setAttribute("Error", ade.getMessage());
		} catch (Exception e) {
			response.setStatus(500);
		}
	
	}

	private void alta(HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		  try {
			    CtrlABMCReserva ctrl = new CtrlABMCReserva();
			    CtrlABMCTipoElemento ctrlte = new CtrlABMCTipoElemento();
			    CtrlABMCElemento ctrle = new CtrlABMCElemento();
			    Persona p=(Persona) request.getSession().getAttribute("user");
                Reserva re = new Reserva();
            	//TipoElemento t = new TipoElemento();
            	//Elemento e=new Elemento();
            	
                re.setElemento(new Elemento());
                re.setPersona(new Persona());
                re.setTipoelemento(new TipoElemento());
                
                re.getPersona().setId_per(p.getId_per());
                int id_el=(Integer.parseInt(request.getParameter("elemento")));
    			re.setElemento(ctrle.getById(id_el));
                int id_te=(Integer.parseInt(request.getParameter("tipo_elemento")));
    			re.setTipoelemento(ctrlte.getById(id_te));
                re.setFecha(Date.valueOf(request.getParameter("fecha")));
                re.setHora_inicio(Time.valueOf(request.getParameter("hora_inicio")));
                re.setHora_fin(Time.valueOf(request.getParameter("hora_fin")));
                re.setDetalle(request.getParameter("detalle"));
                int valida=ctrl.validaDisponibilidad(re);
    			Date fecha=Date.valueOf(ctrl.getFechaActual());
                int dias=(int) ((re.getFecha().getTime()-fecha.getTime())/86400000);
                double horas =((re.getHora_fin().getTime() - re.getHora_inicio().getTime())/3600)/1000;
                System.out.println(horas);
    			if (dias<=re.getTipoelemento().getDias_anticipacion()){
    				if(horas<=re.getTipoelemento().getTiempo_limite()){
                 if (valida==0){
                ctrl.add(re);
                response.getWriter().append("Reserva creada con exito");
                Emailer.getInstance().send(p.getEmail(),"reserva",ctrl.getMailReserva(re,p));
    			}
                 else if (valida==1){
    				response.getWriter().append("elemento ocupado para esa fecha/hora");
    			}}
        		else {response.getWriter().append("Supero el limite de tiempo en horas para reservar un elemento");}}
    			else { response.getWriter().append("Supero los dias maximo de anticipacion a la reserva"); }
				
				
		  }catch (ApplicationException ade) {
				request.setAttribute("Error", ade.getMessage());
			} catch (Exception e) {
				response.setStatus(500);
			}
	}


}
