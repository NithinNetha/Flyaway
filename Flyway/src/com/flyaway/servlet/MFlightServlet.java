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
import com.flyaway.model.Flight;

/**
 * Servlet implementation class MFlightServlet
 */
public class MFlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MFlightServlet() {
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
		FlightDAO flightdao = new FlightDAO();
		List<Flight> listFlight = new ArrayList<Flight>();
		List<String> lop=new ArrayList<String>();
		List<String> loa=new ArrayList<String>();
		if(session!=null) {
			try {
				listFlight=flightdao.flightList();
				session.setAttribute("listFlight", listFlight);
				lop=flightdao.listOfPlaces();
				session.setAttribute("lop", lop);
				loa=flightdao.listOfAirlines();
				session.setAttribute("loa", loa);
				response.sendRedirect("ManageFlight.jsp");
			}catch(BusinessException e) {
				session.setAttribute("exception", e);
				response.sendRedirect("AdminDashboard.jsp");
			}catch(Exception el) {
				session.setAttribute("exception", el);
				response.sendRedirect("AdminDashboard.jsp");
			}
		}else {
			out.print("<center><h3>Your session has expired.. Navigating you to home page.....</h3></center>");
			response.setHeader("refresh", "5;url='/Flyway'");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer flightId = Integer.parseInt(request.getParameter("flightId_update"));
		FlightDAO flightdao = new FlightDAO();
		try {
			List<Flight> singleFlight=flightdao.getFlight(flightId);
			session.setAttribute("singleFlight", singleFlight);
			response.sendRedirect("updateJSP/UpdateFlight.jsp");
		}catch(BusinessException e) {
			session.setAttribute("exception", e.getMessage());
			response.sendRedirect("ManageFlight.jsp");
		}catch(Exception er) {
			session.setAttribute("exception", er.getMessage());
			response.sendRedirect("ManageFlight.jsp");
		}
	}

}
