package servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlers.*;
import entity.*;
import util.Emailer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;

/**
 * Servlet implementation class Start
 */
@WebServlet({ "/Start", "/start" })
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger;
    /**
     * Default constructor. 
     */
    public Start() {
    	logger = LogManager.getLogger(getClass());
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
		try {
			String user=request.getParameter("user");
			String pass=request.getParameter("pass");
			
			Persona per=new Persona();
			per.setUsuario(user);
			per.setContraseña(pass);
			
			CtrlABMCPersona ctrl1= new CtrlABMCPersona();
			
			Persona pers=ctrl1.getValidacionUsario(per);
			
			request.getSession().setAttribute("user", pers);
			
			logger.log(Level.INFO,"log in "+pers.getDni());
		
		
			request.getRequestDispatcher("WEB-INF/menu.jsp").forward(request, response);
			
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//doGet(request, response);
	}

}

