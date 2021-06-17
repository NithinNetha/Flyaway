package com.flyaway.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exception.BusinessException;
import com.flyaway.dao.AirlineDAO;
import com.flyaway.dao.FlightDAO;
import com.flyaway.model.Airline;

/**
 * Servlet implementation class ADAirlineServlet
 */
public class ADAirlineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ADAirlineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		AirlineDAO airlinedao=new AirlineDAO();
		FlightDAO flightdao = new FlightDAO();
		List<Integer> flightIds = new ArrayList<Integer>();
		if(session!=null) {
			try {
				int airlineId=Integer.parseInt(request.getParameter("airlineId_delete"));
				String airlineName=airlinedao.getName(airlineId);
				flightIds=flightdao.getIdsList(airlineName);
				System.out.println();
				for(int flightId:flightIds) {
					int c = flightdao.deleteFlight(flightId);
					c=0;
				}
				int status=airlinedao.deleteAirline(airlineId);
				if(status!=1) {
					session.setAttribute("exception", "Deletion Failed" );
				}
				else {
				
					session.setAttribute("action", "Deleted Successfully" );		
				}
				List<Airline> listAirline = airlinedao.airlineList();
				session.setAttribute("listAirline", listAirline);
				response.sendRedirect("ManageAirline.jsp");
			}catch(BusinessException e) {
				session.setAttribute("exception", e);
				response.sendRedirect("ManageAirline.jsp");
			}catch(Exception el) {
				session.setAttribute("exception", el);
				response.sendRedirect("ManageAirline.jsp");
			}
		}else {
			out.print("<center><h3>Session has expired.. Navigating to HomePage</h3></center>");
			response.setHeader("refresh", "5;url='/Flyway'");}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		AirlineDAO airlinedao=new AirlineDAO();
		Airline airline = new Airline();
		boolean status=false;
		if(session!=null) {
			try{
				airline.setAirlineName(request.getParameter("airlineName"));
				airline.setContactName(request.getParameter("contactName"));
				airline.setContactNumber(request.getParameter("contactNumber"));
				status=airlinedao.addAirline(airline);
				if(status==true) {
					session.setAttribute("action","Added Sucessfully");
				}
				List<Airline> listAirline = airlinedao.airlineList();
				session.setAttribute("listAirline", listAirline);
				response.sendRedirect("ManageAirline.jsp");
			}catch(BusinessException e) {
				session.setAttribute("exception", e.getMessage());
				response.sendRedirect("ManageAirline.jsp");
			}catch(Exception e) {
				session.setAttribute("exception", e.getMessage());
				response.sendRedirect("ManageAirline.jsp");
			}
		}else {
			out.print("\"<center><h3>Your session has expired.. Navigating you to home page.....</h3></center>\"");
			response.setHeader("refresh", "5;url='/Flyway'");
		}
	}

}
